package com.gamecenter.gamecenter.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.gamecenter.gamecenter.activity.NewChat.NewChatActivity;
import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.bean.TextMsgBody;
import com.gamecenter.gamecenter.model.ADataManage;
import com.gamecenter.gamecenter.model.FriendModel;
import com.gamecenter.gamecenter.model.GroupModel;
import com.gamecenter.gamecenter.model.NearestContact;
import com.gamecenter.gamecenter.presenter.IMainPresenter;
import com.gamecenter.gamecenter.presenter.impl.MainPresenter;
import com.gamecenter.gamecenter.util.circleheadportraitutil.CircularImage;
import com.gamecenter.gamecenter.view.IMainView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements IMainView,View.OnClickListener {

    private FragmentTabHost mTabHost;// 定义一个FragmentTabHost对象
    private LayoutInflater layoutInflater;// 定义一个布局
    private CircularImage imageheadportrait;// 圆形头像
    private Spinner spinner;
    private String database[] = { "创建讨论组", "扫一扫", "秀一波", "动态","个人设置" };
    private int[] resId = { R.drawable.spinner_create_discussion_group,
            R.drawable.spinner_saosao,
            R.drawable.spinner_camera,
            R.drawable.spinner_donttai,
            R.drawable.spinner_settings};
    private TextView tvusername;
    private ImageView imageaccountstatue;
    private FrameLayout fraheadportrait;
    // 定义数组来存放Fragment界面
    private Class fragmentArray[] = { MsgListFragment.class,
            FriendFragment2.class, GroupFragment.class ,RoomFragment.class, ScenarioFragment.class};
    // 定义数组来存放按钮图片
    private int mImageViewArray[] = { R.drawable.tab_message_select,
            R.drawable.tab_friendlist_select, R.drawable.tab_group_select, R.drawable.tab_group_select, R.drawable.tab_group_select };
    // Tab选项卡的文字
    private String mTextviewArray[] = { "消息", "联系人", "群", "房间", "想定"};

    private IMainPresenter mainPresenter;

    private boolean currentMsgIsGroup = false;
    private com.gamecenter.gamecenter.bean.Message reciveMsg = new com.gamecenter.gamecenter.bean.Message();

    private Intent notificationIntent = null;

    Handler handler = new Handler() {// 创建一个Handler
        public void handleMessage(android.os.Message msg) {
            switch (msg.arg2){
                case 11:
                    setNotificationDemoSecond();
                    break;
                default:
                    break;
            }
        }
    };

    // 未读消息
    private static ArrayList<NearestContact> unreadMsgList = new ArrayList<NearestContact>();

    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.i("TAG", MainActivity.user.getUser_nickName()+"onRestart1");
        tvusername.setText(MainActivity.getCurrentUser().getUsername());
        String statue = MainActivity.getCurrentUser().getUserstatus();
        Log.i("TAG", statue+"zhuangtai");
        if(statue.equals("1")){
            imageaccountstatue.setImageResource(R.drawable.account_current);
        }else if(statue.equals("3")){
            imageaccountstatue.setImageResource(R.drawable.offline);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //set listener
        initView();
        Init();

        mainPresenter = new MainPresenter(this);

        tvusername.setText(BaseActivity.getCurrentUser().getUsername());
        // 对头像附图
        imageheadportrait.setImageResource(R.drawable.icon);

        spinner.setAdapter(new MySpinnerAdapter());

        //spinner点击事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (arg2) {
                    case 0:

                        break;
                    case 4:
                        //Intent intent = new Intent(MainActivity.this,SingleSettingActivity.class);
                        //startActivity(intent);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        //点击头像修改个人信息
        fraheadportrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Intent intent = new Intent(MainTabActivity.this,EditDataActivity.class);
                //startActivity(intent);
            }
        });

        // 发送请求好友列表信息
        try {
            mainPresenter.sendRequestFriendMsg();
            mainPresenter.sendRequestGroupsMsg();
            mainPresenter.sendRequestGroupMemberMsg();
            mainPresenter.sendRequestScenListMsg();
            BaseActivity.setCurrentFriend(null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

    }

    private void Init() {
        spinner = (Spinner) findViewById(R.id.main_spinner);
        imageheadportrait = (CircularImage) findViewById(R.id.main_cover_user_photo);
        fraheadportrait = (FrameLayout) findViewById(R.id.main_headportrait);
        tvusername = (TextView) findViewById(R.id.main_tv_username);
        imageaccountstatue = (ImageView) findViewById(R.id.main_imageview_userstatue);
    }

    private void initView() {
        // 实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        // 实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到Fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            // 为每个Tab按钮设置图标文字
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            // 将Tab按钮添加到Tab选项卡中去
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tab_item_background);
        }
    }

    // 给Tab按钮设置图标和文字
    private View getTabItemView(int i) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView image = (ImageView) view.findViewById(R.id.tab_item_view_image);
        TextView tv = (TextView) view.findViewById(R.id.tab_item_view_tv);
        image.setImageResource(mImageViewArray[i]);
        tv.setText(mTextviewArray[i]);
        return view;
    }

    // 未读消息列表数据
    public static void addUnReadMsg(NearestContact nearestContact){
        unreadMsgList.add(nearestContact);
    }
    public static List<NearestContact> getUnReadMsg(){
        return unreadMsgList;
    }

    public void showNotification(boolean isgroup, com.gamecenter.gamecenter.bean.Message  message ){
        reciveMsg = message;
        currentMsgIsGroup = isgroup;
        android.os.Message  msg = new android.os.Message();
        msg.arg2 = 11;
        handler.sendMessage(msg);
    }

    /**
     * 自定义通知栏
     */
    private void setNotificationDemoSecond() {
        String msgSender = "";
        Bundle bundle = new Bundle();

        if(currentMsgIsGroup){
            GroupModel group = ADataManage.getInstance().getGroupMap().get(reciveMsg.getMsgId());
            bundle.putParcelable("group",group);
            msgSender = group.getGroupName();
        }else {
            //Map<String,FriendModel> friends = ADataManage.getInstance().getFridendMap();
            FriendModel friend = ADataManage.getInstance().getFridendMap().get(Integer.parseInt(reciveMsg.getSenderId()));
            bundle.putParcelable("friend",friend);
            msgSender = friend.getUsername();
        }
        TextMsgBody textmsg = (TextMsgBody)reciveMsg.getBody();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(reciveMsg.getUuid(), reciveMsg.getUuid(), NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            Notification.Builder mBuilder = new Notification.Builder(this).setChannelId(reciveMsg.getUuid());
            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_msg_notification);
            mBuilder.setSmallIcon(R.drawable.icon);
            //Intent intent = new Intent(this, NewChatActivity.class);
            //intent.putExtras(bundle);
            //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            notificationIntent = new Intent(this, NewChatActivity.class);
            notificationIntent.putExtras(bundle);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setContent(remoteViews);
            remoteViews.setImageViewResource(R.id.msg_notification_image, R.drawable.icon);
            remoteViews.setTextViewText(R.id.msg_notification_title, msgSender);
            remoteViews.setTextViewText(R.id.msg_notification_content, textmsg.getMessage());
            notificationManager.notify(10, mBuilder.build());
        }else {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_msg_notification);
            mBuilder.setSmallIcon(R.drawable.icon);
            //Intent intent = new Intent(this, NewChatActivity.class);
            //intent.putExtras(bundle);
            //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            notificationIntent = new Intent(this, NewChatActivity.class);
            notificationIntent.putExtras(bundle);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setContent(remoteViews);
            remoteViews.setImageViewResource(R.id.msg_notification_image, R.drawable.icon);
            remoteViews.setTextViewText(R.id.msg_notification_title, msgSender);
            remoteViews.setTextViewText(R.id.msg_notification_content, textmsg.getMessage());
            notificationManager.notify(10, mBuilder.build());
        }
    }

    class MySpinnerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return database.length;
        }

        @Override
        public Object getItem(int arg0) {
            return arg0;
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @SuppressWarnings("static-access")
        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            View row = arg1;
            Wrapper wrapper;
            if (row == null) {
                row = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listitemofspinner, arg2,false);
                wrapper = new Wrapper(row);
                row.setTag(wrapper);
            }else{
                wrapper = (Wrapper) row.getTag();
            }
            TextView tvitemname = wrapper.getItemName();
            ImageView imageitem = wrapper.getItemImage();
            tvitemname.setText(database[arg0]);
            imageitem.setImageResource(resId[arg0]);
            return row;
        }
    }

    class Wrapper {
        private View row;
        private TextView tvitemname;
        private ImageView imageitem;

        public Wrapper(View row) {
            this.row = row;
        }

        public TextView getItemName() {
            if (tvitemname == null) {
                tvitemname = (TextView) row.findViewById(R.id.listitemofspinner_tv_itemname);
            }
            return tvitemname;
        }

        public ImageView getItemImage() {
            if (imageitem == null) {
                imageitem = (ImageView) row.findViewById(R.id.listitemofspinner_image_icon);
            }
            return imageitem;
        }
    }
}
