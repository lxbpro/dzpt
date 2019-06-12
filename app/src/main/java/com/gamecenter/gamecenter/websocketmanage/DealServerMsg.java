package com.gamecenter.gamecenter.websocketmanage;

import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.gamecenter.gamecenter.Defines.Defines;
import com.gamecenter.gamecenter.activity.BaseActivity;
import com.gamecenter.gamecenter.activity.ChatActivity;
import com.gamecenter.gamecenter.activity.GroupChatActivity;
import com.gamecenter.gamecenter.activity.LoginActivity;
import com.gamecenter.gamecenter.activity.MainActivity;
import com.gamecenter.gamecenter.activity.NewChat.NewChatActivity;
import com.gamecenter.gamecenter.activity.RegisterActivity;
import com.gamecenter.gamecenter.bean.MsgBelong;
import com.gamecenter.gamecenter.bean.MsgBody;
import com.gamecenter.gamecenter.bean.MsgSendStatus;
import com.gamecenter.gamecenter.bean.MsgType;
import com.gamecenter.gamecenter.bean.TextMsgBody;
import com.gamecenter.gamecenter.model.ADataManage;
import com.gamecenter.gamecenter.model.ClassModel;
import com.gamecenter.gamecenter.model.FriendModel;
import com.gamecenter.gamecenter.model.GroupMessage;
import com.gamecenter.gamecenter.model.GroupModel;
import com.gamecenter.gamecenter.bean.Message;
import com.gamecenter.gamecenter.model.NearestContact;
import com.gamecenter.gamecenter.model.ScenarioModel;
import com.gamecenter.gamecenter.util.MyDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import static android.support.v4.content.ContextCompat.startActivity;

public class DealServerMsg {

    public static void dealServerWsMsg(String msg) throws JSONException, ParseException {
        JSONObject msgObj = new JSONObject(msg);
        if (msgObj.has(Defines.CLIENT_REQUEST_TYPE_STR)) {
            String type = msgObj.get(Defines.CLIENT_REQUEST_TYPE_STR).toString();
            switch (Integer.parseInt(type)) {
                case Defines.REQUEST_TYPE_LOGIN_RESULT:
                    // 登陆操作的返回
                    LoginActivity loginActivity = (LoginActivity) BaseActivity.getCurrentActivity();
                    if (!loginActivity.isFinishing()) {
                        loginActivity.dealLoginResult(msgObj);
                    }
                    break;
                case Defines.REQUEST_TYPE_UPDATE_FRIEND_LIST_RESULT:
                    parseFriendlistJson(msgObj);
                    break;
                case Defines.REQUEST_TYPE_SEND_PRIVATE_MESSAGE_RESULT:
                    parsePrivateMsg(msgObj);
                    break;
                case Defines.REQUEST_TYPE_UPDATE_GROUP_LIST_RESULT:
                    parseGroupListJson(msgObj);
                    break;
                case Defines.REQUEST_TYPE_LOAD_SCENARIO_LIST_RESULT:
                    parseScenListJson(msgObj);
                    break;
                case Defines.REQUEST_TYPE_SEND_GROUP_MESSAGE_RESULT:
                    parseGroupMsg(msgObj);
                    break;
                case Defines.REQUEST_TYPE_UPDATE_GROUP_MEMBER_RESULT:
                    parseGroupMemberMsg(msgObj);
                    break;
                case Defines.REQUEST_TYPE_REGIST_RESULT:
                    registerreturnMsg(msgObj);
                    break;
                default:
                    break;
            }
        }
    }

    private static void parseFriendlistJson(JSONObject msgobj) throws JSONException {
        ADataManage.getInstance().clearFriendData();
        JSONArray grouparr = msgobj.getJSONArray(Defines.USER_GROUPS);
        for (int i = 0; i < grouparr.length(); i++) {
            ClassModel group = new ClassModel();
            group.setClassid(grouparr.getJSONObject(i).getInt(Defines.USER_GROUP_ID));
            group.setClassName(grouparr.getJSONObject(i).getString(Defines.USER_GROUP_NAME));
            ADataManage.getInstance().addFriendClass(group);
        }
        JSONArray friendarr = msgobj.getJSONArray(Defines.USER_FRIEND_LIST);
        for (int j = 0; j < friendarr.length(); j++) {
            FriendModel friend = new FriendModel();
            friend.setUserid(friendarr.getJSONObject(j).getInt(Defines.USER_ID));
            friend.setUsername(friendarr.getJSONObject(j).getString(Defines.USER_NAME));
            friend.setUserstatus(friendarr.getJSONObject(j).getString(Defines.USER_LOGIN_STATUS));
            friend.setAccount(friendarr.getJSONObject(j).getString(Defines.USER_ACCOUNT));
            friend.setGroupid(friendarr.getJSONObject(j).getInt(Defines.USER_GROUP_ID));
            ADataManage.getInstance().addFriendModel(friend);
            ADataManage.getInstance().addFriendClassChild(friend.getGroupid(), friend.getUserid());
        }
    }

    private static void parseGroupMemberMsg(JSONObject msgobj) {

    }

    //用户注册后的回调信息【成功或失败】
    private static void registerreturnMsg(JSONObject msgobj) throws JSONException {

       String boolresult= msgobj.getString(Defines.REQUEST_TYPE_RESULT);
        String errotstr = msgobj.getString(Defines.REQUEST_TYPE_ERROR_INFO);
        Looper.prepare();//增加部
        RegisterActivity loginActivity = (RegisterActivity) BaseActivity.getCurrentActivity();
        loginActivity.showRollbackTest(errotstr);
        Looper.loop();
        if(boolresult.equals("True")) {
           loginActivity.backLogin();
        }

    }

    private static void parsePrivateMsg(JSONObject msgobj) throws JSONException, ParseException {
        int senderid = msgobj.getInt(Defines.USER_ID);
        Message message = new Message();
        message.setUuid(UUID.randomUUID() + "");
        //message.setMsgId();
        message.setMsgType(MsgType.TEXT);
        message.setSenderId(msgobj.getString(Defines.USER_ID));
        message.setTargetId(String.format("%d", BaseActivity.getCurrentUser().getId()));
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        message.setSentTime(sDateFormat.parse(msgobj.getString(Defines.SEND_TIME)).getTime());
        message.setSentStatus(MsgSendStatus.SENDING);
        message.setMsgBelong(MsgBelong.FriendMsg);
        TextMsgBody mbody = new TextMsgBody();
        //mbody.setLocalMsgType(MsgType.TEXT);
        mbody.setMessage(msgobj.getString(Defines.MESSAGE_INFO));
        //mbody.setExtra("");
        message.setBody(mbody);
        ADataManage.getInstance().addMsgRecordByFriend(message);
        if (BaseActivity.getCurrentFriend() != null && senderid == BaseActivity.getCurrentFriend().getUserid()) {
            // 当前好友对话页面为打开中，发送消息
            NewChatActivity chatac = (NewChatActivity) BaseActivity.getCurrentActivity();
            chatac.sendReciveTextMsg(message);
        } else {
            // 做未读消息处理
            ADataManage.getInstance().addUnReadMsgByFriend(message);
            MainActivity main = (MainActivity) BaseActivity.getCurrentActivity();
            main.showNotification(false, message);
        }

    }

    private static void parseGroupListJson(JSONObject msgobj) throws JSONException {
        ADataManage.getInstance().clearGroupData();
        JSONArray grouparr = msgobj.getJSONArray(Defines.GROUP_GROUPS);
        for (int i = 0; i < grouparr.length(); i++) {
            ClassModel group = new ClassModel();
            group.setClassid(grouparr.getJSONObject(i).getInt(Defines.GROUP_GROUP_ID));
            group.setClassName(grouparr.getJSONObject(i).getString(Defines.GROUP_GROUP_NAME));
            ADataManage.getInstance().addGroupClass(group);
        }
        JSONArray friendarr = msgobj.getJSONArray(Defines.USER_GROUP_LIST);
        for (int j = 0; j < friendarr.length(); j++) {
            GroupModel group = new GroupModel();
            group.setGroupid(friendarr.getJSONObject(j).getString(Defines.GROUP_ID));
            group.setGroupName(friendarr.getJSONObject(j).getString(Defines.GROUP_NAME));
            group.setNotice(friendarr.getJSONObject(j).getString(Defines.GROUP_NOTICE));
            group.setClassid(friendarr.getJSONObject(j).getInt(Defines.GROUP_GROUP_ID));
            ADataManage.getInstance().addGroupModel(group);
            ADataManage.getInstance().addGroupClassChild(group.getClassid(), group.getGroupid());
        }
    }

    private static void parseScenListJson(JSONObject msgobj) throws JSONException {
        // 公有想定
        ADataManage.getInstance().clearPublicScenData();
        JSONArray scengroupArr = msgobj.getJSONArray(Defines.PUBLIC_SCENARIO_GROUPS);
        for (int i = 0; i < scengroupArr.length(); i++) {
            ClassModel group = new ClassModel();
            group.setClassid(scengroupArr.getJSONObject(i).getInt(Defines.SCENARIO_GROUP_ID));
            group.setClassName(scengroupArr.getJSONObject(i).getString(Defines.SCENARIO_GROUP_NAME));
            ADataManage.getInstance().addPublicScenClass(group);
        }
        JSONArray publicscenArr = msgobj.getJSONArray(Defines.PUBLIC);
        for (int j = 0; j < publicscenArr.length(); j++) {
            ScenarioModel scenarioModel = new ScenarioModel();
            scenarioModel.setScenid(publicscenArr.getJSONObject(j).getInt(Defines.SCENARIO_ID));
            scenarioModel.setScenname(publicscenArr.getJSONObject(j).getString(Defines.SCENARIO_NAME));
            scenarioModel.setComments(publicscenArr.getJSONObject(j).getString(Defines.SCENARIO_COMMENTS));
            scenarioModel.setClassid(publicscenArr.getJSONObject(j).getInt(Defines.SCENARIO_GROUP_ID));
            scenarioModel.setIspublic(publicscenArr.getJSONObject(j).getBoolean(Defines.SCENARIO_IS_PUBLIC));
            scenarioModel.setMaster(publicscenArr.getJSONObject(j).getString(Defines.SCENARIO_MASTER));
            ADataManage.getInstance().addPublicScenModel(scenarioModel);
            ADataManage.getInstance().addPublicScenClassChild(scenarioModel.getClassid(), scenarioModel.getScenid());
        }
        // 私有想定
        ADataManage.getInstance().clearPrivateScenData();
        JSONArray privatescengroupArr = msgobj.getJSONArray(Defines.PRIVATE_SCENARIO_GROUPS);
        for (int i = 0; i < privatescengroupArr.length(); i++) {
            ClassModel group = new ClassModel();
            group.setClassid(privatescengroupArr.getJSONObject(i).getInt(Defines.SCENARIO_GROUP_ID));
            group.setClassName(privatescengroupArr.getJSONObject(i).getString(Defines.SCENARIO_GROUP_NAME));
            ADataManage.getInstance().addPrivateScenClass(group);
        }
        JSONArray privatescenArr = msgobj.getJSONArray(Defines.USER_PRIVATE_SCENARIO);
        for (int j = 0; j < privatescenArr.length(); j++) {
            ScenarioModel scenarioModel = new ScenarioModel();
            scenarioModel.setScenid(privatescenArr.getJSONObject(j).getInt(Defines.SCENARIO_ID));
            scenarioModel.setScenname(privatescenArr.getJSONObject(j).getString(Defines.SCENARIO_NAME));
            scenarioModel.setComments(privatescenArr.getJSONObject(j).getString(Defines.SCENARIO_COMMENTS));
            scenarioModel.setClassid(privatescenArr.getJSONObject(j).getInt(Defines.SCENARIO_GROUP_ID));
            scenarioModel.setIspublic(privatescenArr.getJSONObject(j).getBoolean(Defines.SCENARIO_IS_PUBLIC));
            scenarioModel.setMaster(privatescenArr.getJSONObject(j).getString(Defines.SCENARIO_MASTER));
            ADataManage.getInstance().addPrivateScenModel(scenarioModel);
            ADataManage.getInstance().addPrivateScenClassChild(scenarioModel.getClassid(), scenarioModel.getScenid());
        }
    }

    private static void parseGroupMsg(JSONObject msgobj) throws JSONException, ParseException {
        String groupid = msgobj.getString(Defines.GROUP_ID);
        Message message = new Message();
        message.setUuid(UUID.randomUUID() + "");
        message.setMsgId(groupid);      // 此处用msgid记录一下群组ID
        message.setMsgType(MsgType.TEXT);
        message.setSenderId(msgobj.getString(Defines.USER_ID));
        message.setTargetId(String.format("%d", BaseActivity.getCurrentUser().getId()));
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        message.setSentTime(sDateFormat.parse(msgobj.getString(Defines.SEND_TIME)).getTime());
        message.setSentStatus(MsgSendStatus.SENDING);
        message.setMsgBelong(MsgBelong.GroupMsg);
        TextMsgBody mbody = new TextMsgBody();
        mbody.setMessage(msgobj.getString(Defines.MESSAGE_INFO));
        message.setBody(mbody);
        ADataManage.getInstance().addMsgRecordByFriendId(groupid, message);
        if (BaseActivity.getCurrentGroup() != null && groupid.equals(BaseActivity.getCurrentGroup().getGroupid())) {
            // 当前好友对话页面为打开中，发送消息
            NewChatActivity chatac = (NewChatActivity) BaseActivity.getCurrentActivity();
            chatac.sendReciveTextMsg(message);
        } else {
            // 做未读消息处理
            ADataManage.getInstance().addUnReadMsgByFriendId(groupid, message);
            MainActivity main = (MainActivity) BaseActivity.getCurrentActivity();
            main.showNotification(true, message);
        }
    }

}
