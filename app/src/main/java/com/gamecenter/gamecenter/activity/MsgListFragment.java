package com.gamecenter.gamecenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gamecenter.gamecenter.activity.NewChat.NewChatActivity;
import com.gamecenter.gamecenter.bean.Message;
import com.gamecenter.gamecenter.bean.MsgBelong;
import com.gamecenter.gamecenter.bean.MsgSendStatus;
import com.gamecenter.gamecenter.bean.MsgType;
import com.gamecenter.gamecenter.bean.TextMsgBody;
import com.gamecenter.gamecenter.model.ADataManage;
import com.gamecenter.gamecenter.util.circleheadportraitutil.CircularImage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MsgListFragment extends Fragment{
    private ListView msgViewList;
    private List<Message> msgDataList = new ArrayList<>();

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.arg1) {
                case 66:
                    msgViewList.setAdapter(new UnReadMsgAdapter());
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        msgViewList = (ListView)view.findViewById(R.id.msg_lv_list);


        if(msgDataList.size() > 0)
            msgDataList.clear();
        msgDataList = ADataManage.getInstance().getMsgPageShowList();
        /*
        // 弄点假数据看看效果
        for (int i = 1; i < 16; i++){
            Message mMessgae=new Message();
            mMessgae.setUuid(UUID.randomUUID()+"");
            mMessgae.setSenderId("165");
            mMessgae.setTargetId("156");
            mMessgae.setSentTime(System.currentTimeMillis());
            mMessgae.setSentStatus(MsgSendStatus.SENDING);
            if(i % 2 == 0)
                mMessgae.setMsgBelong(MsgBelong.GroupMsg);
            else
                mMessgae.setMsgBelong(MsgBelong.FriendMsg);
            mMessgae.setMsgType(MsgType.TEXT);
            mMessgae.setSendername("神秘银"+i+"号");
            TextMsgBody msgBody = new TextMsgBody();
            msgBody.setMessage("我是 "+i+" 号，猜猜我是谁？");
            mMessgae.setBody(msgBody);
            msgDataList.add(mMessgae);
        }
        */
        msgViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 点击处理
                Message msg = msgDataList.get(position);
                Intent intent = new Intent(getActivity(), NewChatActivity.class);
                Bundle bundle = new Bundle();
                switch (msg.getMsgBelong()){
                    case FriendMsg: {
                        bundle.putParcelable("friend", ADataManage.getInstance().getFridendMap().get(Integer.parseInt(msg.getSenderId())));
                    }break;
                    case GroupMsg: {
                        bundle.putParcelable("group", ADataManage.getInstance().getGroupMap().get(msg.getMsgId()));
                    }break;
                    default:
                        break;
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        android.os.Message msg = new android.os.Message();
        msg.arg1 = 66;
        handler.sendMessage(msg);
    }

    public void addUnReadMsg(Message message){
        msgDataList.add(0,message);
        android.os.Message msg = new android.os.Message();
        msg.arg1 = 66;
        handler.sendMessage(msg);
    }

    class UnReadMsgAdapter extends BaseAdapter {

        @Override
        public int getCount(){
            return msgDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return msgDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            MsgItem msgitem;
            if(row==null){
                row = LayoutInflater.from(getActivity()).inflate(R.layout.message_list_fragment_item, parent,false);
                msgitem = new MsgItem(row);
                row.setTag(msgitem);
            }else{
                msgitem = (MsgItem) row.getTag();
            }
            CircularImage headportrait = msgitem.getFriendHeadPortrait();
            TextView tvfriendname = msgitem.getFriendName();
            TextView tvmessagenum = msgitem.getMessagenum();
            TextView tvmessagetime = msgitem.getMessagetime();
            TextView tvmsginfo = msgitem.getMessageInfo();
            Message msg = msgDataList.get(position);
            switch (msg.getMsgBelong()){
                case GroupMsg: {
                    headportrait.setImageResource(R.drawable.ic_launcher);
                    //tvfriendname.setText(msg.getSendername());
                    tvfriendname.setText(ADataManage.getInstance().getGroupMap().get(msg.getMsgId()).getGroupName());
                    tvmessagenum.setText(""+ADataManage.getInstance().getUnReadMsgByFriendId(msg.getMsgId()).size());
                }break;
                case FriendMsg: {
                    headportrait.setImageResource(R.drawable.icon);
                    tvfriendname.setText(msg.getSendername());
                    //tvfriendname.setText(ADataManage.getInstance().getFridendMap().get(Integer.parseInt(msg.getSenderId())).getUsername());
                    tvmessagenum.setText(""+ADataManage.getInstance().getUnReadMsgByFriendId(msg.getSenderId()).size());
                }break;
                default:
                    break;
            }
            switch (msg.getMsgType()){
                case TEXT: {
                    TextMsgBody textmsg = (TextMsgBody) msg.getBody();
                    tvmsginfo.setText(""+textmsg.getMessage());
                }break;
                case IMAGE: {
                    tvmsginfo.setText("[图片]");
                }break;
                case FILE: {
                    tvmsginfo.setText("[文件]");
                }break;
                case AUDIO: {
                    tvmsginfo.setText("[语音]");
                }break;
                case VIDEO: {
                    tvmsginfo.setText("[视频]");
                }break;
                default:
                    break;
            }
            SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
            Date dt = new Date(msg.getSentTime());
            //DateFormat df3 = DateFormat.getTimeInstance();//只显示出时分秒
            tvmessagetime.setText(sdf.format(dt));

            return row;
        }
    }

    class MsgItem{
        private View row;
        private CircularImage headportraitimage;
        private TextView tvfriendname,tvmessagenum,tvmesginfo,tvmsgtime;
        public MsgItem(View row){
            this.row = row;
        }
        private CircularImage getFriendHeadPortrait(){
            if(headportraitimage==null){
                headportraitimage = (CircularImage) row.findViewById(R.id.msg_list_item_photo);
            }
            return headportraitimage;
        }
        private TextView getFriendName(){
            if(tvfriendname==null){
                tvfriendname = (TextView) row.findViewById(R.id.msg_list_item_name);
            }
            return tvfriendname;
        }
        private TextView getMessagenum(){
            if(tvmessagenum==null){
                tvmessagenum = (TextView) row.findViewById(R.id.msg_list_item_sum);
            }
            return tvmessagenum;
        }
        private TextView getMessagetime(){
            if(tvmsgtime == null){
                tvmsgtime = (TextView)row.findViewById(R.id.msg_list_item_time);
            }
            return tvmsgtime;
        }
        private TextView getMessageInfo(){
            if(tvmesginfo==null){
                tvmesginfo = (TextView) row.findViewById(R.id.msg_list_item_content);
            }
            return tvmesginfo;
        }
    }

}
