package com.gamecenter.gamecenter.activity.NewChat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gamecenter.gamecenter.Defines.Defines;
import com.gamecenter.gamecenter.activity.BaseActivity;
import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.adapter.NewChatAdapter.ChatAdapter;
import com.gamecenter.gamecenter.bean.AudioMsgBody;
import com.gamecenter.gamecenter.bean.FileMsgBody;
import com.gamecenter.gamecenter.bean.ImageMsgBody;
import com.gamecenter.gamecenter.bean.Message;
import com.gamecenter.gamecenter.bean.MsgSendStatus;
import com.gamecenter.gamecenter.bean.MsgType;
import com.gamecenter.gamecenter.bean.TextMsgBody;
import com.gamecenter.gamecenter.bean.VideoMsgBody;
import com.gamecenter.gamecenter.model.ADataManage;
import com.gamecenter.gamecenter.model.FriendModel;
import com.gamecenter.gamecenter.model.GroupModel;
import com.gamecenter.gamecenter.util.chatutil.ChatUiHelper;
import com.gamecenter.gamecenter.util.chatutil.FileUtils;
import com.gamecenter.gamecenter.util.chatutil.LogUtil;
import com.gamecenter.gamecenter.util.chatutil.PictureFileUtil;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;
import com.gamecenter.gamecenter.widget.MediaManager;
import com.gamecenter.gamecenter.widget.RecordButton;
import com.gamecenter.gamecenter.widget.StateButton;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewChatActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.llContent)
    LinearLayout mLlContent;
    @BindView(R.id.rv_chat_list)
    RecyclerView mRvChat;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.bottom_layout)
    RelativeLayout mRlBottomLayout;//表情,添加底部布局
    @BindView(R.id.ivAdd)
    ImageView mIvAdd;
    @BindView(R.id.ivEmo)
    ImageView mIvEmo;
    @BindView(R.id.btn_send)
    StateButton mBtnSend;//发送按钮
    @BindView(R.id.ivAudio)
    ImageView mIvAudio;//录音图片
    @BindView(R.id.btnAudio)
    RecordButton mBtnAudio;//录音按钮
    @BindView(R.id.rlEmotion)
    LinearLayout mLlEmotion;//表情布局
    @BindView(R.id.llAdd)
    LinearLayout mLlAdd;//添加布局
    @BindView(R.id.swipe_chat)
    SwipeRefreshLayout mSwipeRefresh;//下拉刷新
    private ChatAdapter mAdapter;
    public static String 	  mSenderId="right";
    public static String    mTargetId="left";
    public static final int       REQUEST_CODE_IMAGE=0000;
    public static final int       REQUEST_CODE_VEDIO=1111;
    public static final int       REQUEST_CODE_FILE=2222;

    private String senderName = "";
    private String targetName = "";
    private boolean isgroup = false;
    private int currentShowMsgIndex = 0;
    private Message currentMsg;
    Handler handler =  null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_chat_activity);
        initContent();

        mSenderId = String.format("%d",BaseActivity.getCurrentUser().getId());
        senderName = BaseActivity.getCurrentUser().getUsername();
        // 获取另一个界面传来的信息
        Intent intent = getIntent();
        // 获取Bundle对象
        Bundle bundle = intent.getExtras();
        //remark = bundle.getString("remark");
        if(bundle.containsKey("friend")){
            FriendModel friend = bundle.getParcelable("friend");
            mTargetId = String.format("%d",friend.getUserid());
            targetName = friend.getUsername();
            TextView title = (TextView)findViewById(R.id.common_toolbar_title);
            title.setText(friend.getUsername());
            BaseActivity.setCurrentFriend(friend);
            isgroup = false;
        }else if(bundle.containsKey("group")){
            GroupModel group = bundle.getParcelable("group");
            mTargetId = group.getGroupid();
            TextView title = (TextView)findViewById(R.id.common_toolbar_title);
            title.setText(group.getGroupName());
            BaseActivity.setCurrentGroup(group);
            isgroup = true;
        }

        handler =  new Handler() {// 创建一个Handler
            public void handleMessage(android.os.Message msg) {
                switch (msg.arg2) {
                    case 22:
                        {
                            //mRvChat.scrollToPosition(mAdapter.getItemCount() - 1);
                            mRvChat.scrollToPosition(mAdapter.getItemCount());
                            currentMsg.setSentStatus(MsgSendStatus.SENT);
                            mAdapter.addData(currentMsg);
                            int position=0;
                            currentMsg.setSentStatus(MsgSendStatus.SENT);
                            //更新单个子条目
                            for (int i=0;i<mAdapter.getData().size();i++){
                                Message mAdapterMessage=mAdapter.getData().get(i);
                                if (currentMsg.getUuid().equals(mAdapterMessage.getUuid())){
                                    position=i;
                                }
                            }
                            mAdapter.notifyItemChanged(position);
                        }
                        break;
                    default:
                        break;
                }
            };
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    private  ImageView  ivAudio;

    protected void initContent() {
        ButterKnife.bind(this) ;
        mAdapter=new ChatAdapter(this, new ArrayList<Message>());
        LinearLayoutManager mLinearLayout=new LinearLayoutManager(this);
        mRvChat.setLayoutManager(mLinearLayout);
        mRvChat.setAdapter(mAdapter);
        mSwipeRefresh.setOnRefreshListener(this);
        initChatUi();
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (ivAudio != null) {
                    ivAudio.setBackgroundResource(R.mipmap.audio_animation_list_right_3);
                    ivAudio = null;
                    MediaManager.reset();
                }else{
                    ivAudio = view.findViewById(R.id.ivAudio);
                    MediaManager.reset();
                    ivAudio.setBackgroundResource(R.drawable.audio_animation_right_list);
                    AnimationDrawable drawable = (AnimationDrawable) ivAudio.getBackground();
                    drawable.start();
                    MediaManager.playSound(NewChatActivity.this,((AudioMsgBody)mAdapter.getData().get(position).getBody()).getLocalPath(), new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            LogUtil.d("开始播放结束");
                            ivAudio.setBackgroundResource(R.mipmap.audio_animation_list_right_3);
                            MediaManager.release();
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        //下拉刷新模拟获取历史消息
        List<Message> mReceiveMsgList=new ArrayList<Message>();
        // 一次加载5条记录
        int msgCount = ADataManage.getInstance().loadMsgRecordByFriendId(mTargetId).size();
        if(msgCount > currentShowMsgIndex){
            if((msgCount - currentShowMsgIndex) > 5){
                mReceiveMsgList = ADataManage.getInstance().loadMsgRecordByFriendId(mTargetId).subList(currentShowMsgIndex, currentShowMsgIndex+5);
            }else {
                mReceiveMsgList = ADataManage.getInstance().loadMsgRecordByFriendId(mTargetId).subList(currentShowMsgIndex,msgCount);
            }
            mAdapter.addData(0,mReceiveMsgList);
            mSwipeRefresh.setRefreshing(false);
            currentShowMsgIndex += 5;
        }else {
            Toast.makeText(getApplication(),"已加载全部记录", Toast.LENGTH_SHORT).show();
            mSwipeRefresh.setRefreshing(false);
            currentShowMsgIndex += msgCount;
        }
    }

    private void initChatUi(){
        //mBtnAudio
        final ChatUiHelper mUiHelper= ChatUiHelper.with(this);
        mUiHelper.bindContentLayout(mLlContent)
                .bindttToSendButton(mBtnSend)
                .bindEditText(mEtContent)
                .bindBottomLayout(mRlBottomLayout)
                .bindEmojiLayout(mLlEmotion)
                .bindAddLayout(mLlAdd)
                .bindToAddButton(mIvAdd)
                .bindToEmojiButton(mIvEmo)
                .bindAudioBtn(mBtnAudio)
                .bindAudioIv(mIvAudio)
                .bindEmojiData();
        //底部布局弹出,聊天列表上滑
        mRvChat.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    mRvChat.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mAdapter.getItemCount() > 0) {
                                mRvChat.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                            }
                        }
                    });
                }
            }
        });
        //点击空白区域关闭键盘
        mRvChat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mUiHelper.hideBottomLayout(false);
                mUiHelper.hideSoftInput();
                mEtContent.clearFocus();
                mIvEmo.setImageResource(R.mipmap.ic_emoji);
                return false;
            }
        });
        //
        ((RecordButton) mBtnAudio).setOnFinishedRecordListener(new RecordButton.OnFinishedRecordListener() {
            @Override
            public void onFinishedRecord(String audioPath, int time) {
                LogUtil.d("录音结束回调");
                File file = new File(audioPath);
                if (file.exists()) {
                    sendAudioMessage(audioPath,time);
                }
            }
        });

    }

    @OnClick({R.id.btn_send,R.id.rlPhoto,R.id.rlVideo,R.id.rlLocation,R.id.rlFile,R.id.common_toolbar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                sendTextMsg(mEtContent.getText().toString());
                mEtContent.setText("");
                break;
            case R.id.rlPhoto:
                PictureFileUtil.openGalleryPic(NewChatActivity.this,REQUEST_CODE_IMAGE);
                break;
            case R.id.rlVideo:
                PictureFileUtil.openGalleryAudio(NewChatActivity.this,REQUEST_CODE_VEDIO);
                break;
            case R.id.rlFile:
                PictureFileUtil.openFile(NewChatActivity.this,REQUEST_CODE_FILE);
                break;
            case R.id.rlLocation:
                break;
            case R.id.common_toolbar_back: {
                Toast.makeText(getApplication(), "返回主页", Toast.LENGTH_SHORT).show();
                if (isgroup) {
                    BaseActivity.setCurrentGroup(null);
                } else {
                    BaseActivity.setCurrentFriend(null);
                }
                finish();
            }break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FILE:
                    String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                    LogUtil.d("获取到的文件路径:"+filePath);
                    sendFileMessage(mSenderId, mTargetId, filePath);
                    break;
                case REQUEST_CODE_IMAGE:
                    // 图片选择结果回调
                    List<LocalMedia> selectListPic = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia media : selectListPic) {
                        LogUtil.d("获取图片路径成功:"+  media.getPath());
                        sendImageMessage(media);
                    }
                    break;
                case REQUEST_CODE_VEDIO:
                    // 视频选择结果回调
                    List<LocalMedia> selectListVideo = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia media : selectListVideo) {
                        LogUtil.d("获取视频路径成功:"+  media.getPath());
                        sendVedioMessage(media);
                    }
                    break;
            }
        }
    }

    // 发送消息
    public void sendReciveTextMsg(Message msg){
        //开始发送
        //mAdapter.addData(msg);
        //模拟两秒后发送成功
        updateMsg(msg);
    }

    //文本消息
    private void sendTextMsg(String hello) {
        final Message mMessgae=getBaseSendMessage(MsgType.TEXT);
        TextMsgBody mTextMsgBody=new TextMsgBody();
        mTextMsgBody.setMessage(hello);
        mMessgae.setBody(mTextMsgBody);
        //开始发送
        //mAdapter.addData( mMessgae);
        try {
            if (isgroup){
                mTextMsgBody.sendGroupTextMsgJson(mTargetId);
            }else {
                mTextMsgBody.sendTextMsgJson(mTargetId);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        //模拟两秒后发送成功
        updateMsg(mMessgae);
        ADataManage.getInstance().addMsgRecordByFriendId(mMessgae.getTargetId(), mMessgae);
    }

    //图片消息
    private void sendImageMessage(final LocalMedia media) {
        final Message mMessgae=getBaseSendMessage(MsgType.IMAGE);
        ImageMsgBody mImageMsgBody=new ImageMsgBody();
        mImageMsgBody.setThumbUrl(media.getCompressPath());
        mMessgae.setBody(mImageMsgBody);
        //开始发送
        mAdapter.addData( mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);
    }

    //视频消息
    private void sendVedioMessage(final LocalMedia media) {
        final Message mMessgae=getBaseSendMessage(MsgType.VIDEO);
        //生成缩略图路径
        String vedioPath=media.getPath();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(vedioPath);
        Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime();
        String imgname = System.currentTimeMillis() + ".jpg";
        String urlpath = Environment.getExternalStorageDirectory() + "/" + imgname;
        File f = new File(urlpath);
        try {
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        }catch ( Exception e) {
            LogUtil.d("视频缩略图路径获取失败："+e.toString());
            e.printStackTrace();
        }
        VideoMsgBody mImageMsgBody=new VideoMsgBody();
        mImageMsgBody.setExtra(urlpath);
        mMessgae.setBody(mImageMsgBody);
        //开始发送
        mAdapter.addData( mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);

    }

    //文件消息
    private void sendFileMessage(String from, String to, final String path) {
        final Message mMessgae=getBaseSendMessage(MsgType.FILE);
        FileMsgBody mFileMsgBody=new FileMsgBody();
        mFileMsgBody.setLocalPath(path);
        mFileMsgBody.setDisplayName(FileUtils.getFileName(path));
        mFileMsgBody.setSize(FileUtils.getFileLength(path));
        mMessgae.setBody(mFileMsgBody);
        //开始发送
        mAdapter.addData( mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);
    }

    //语音消息
    private void sendAudioMessage(  final String path,int time) {
        final Message mMessgae=getBaseSendMessage(MsgType.AUDIO);
        AudioMsgBody mFileMsgBody=new AudioMsgBody();
        mFileMsgBody.setLocalPath(path);
        mFileMsgBody.setDuration(time);
        mMessgae.setBody(mFileMsgBody);
        //开始发送
        mAdapter.addData( mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);
    }


    private Message getBaseSendMessage(MsgType msgType){
        Message mMessgae=new Message();
        mMessgae.setUuid(UUID.randomUUID()+"");
        mMessgae.setSenderId(mSenderId);
        mMessgae.setTargetId(mTargetId);
        mMessgae.setSentTime(System.currentTimeMillis());
        mMessgae.setSentStatus(MsgSendStatus.SENDING);
        mMessgae.setMsgType(msgType);
        mMessgae.setSendername(senderName);
        return mMessgae;
    }

    private Message getBaseReceiveMessage(MsgType msgType){
        Message mMessgae=new Message();
        mMessgae.setUuid(UUID.randomUUID()+"");
        mMessgae.setSenderId(mTargetId);
        mMessgae.setTargetId(mSenderId);
        mMessgae.setSentTime(System.currentTimeMillis());
        mMessgae.setSentStatus(MsgSendStatus.SENDING);
        mMessgae.setMsgType(msgType);
        return mMessgae;
    }

    private void updateMsg(final Message mMessgae) {
        currentMsg = mMessgae;
        android.os.Message msg2 = android.os.Message.obtain();
        msg2.arg2 = 22;
        handler.sendMessage(msg2);
        //List<Message> msgs = mAdapter.getData();
        //position = msgs.size()-1;
        /*
        //模拟2秒后发送成功
        new Handler().postDelayed(new Runnable() {
            public void run() {
                int position=0;
                mMessgae.setSentStatus(MsgSendStatus.SENT);
                //更新单个子条目
                for (int i=0;i<mAdapter.getData().size();i++){
                    Message mAdapterMessage=mAdapter.getData().get(i);
                    if (mMessgae.getUuid().equals(mAdapterMessage.getUuid())){
                        position=i;
                    }
                }
                mAdapter.notifyItemChanged(position);
            }
        }, 2000);
        */
    }

}
