package com.gamecenter.gamecenter.bean;


import com.gamecenter.gamecenter.Defines.Defines;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;
import org.json.JSONObject;

public  class Message implements Comparable<Message>{

     private String uuid;
     private String msgId;
     private MsgType msgType;
     private MsgBody body;
     private MsgSendStatus sentStatus;
     private MsgBelong msgBelong;
     private String senderId;
     private String targetId;
     private long sentTime;

     private String sendername;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public MsgBody getBody() {
        return body;
    }

    public void setBody(MsgBody body) {
        this.body = body;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public MsgSendStatus getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(MsgSendStatus sentStatus) {
        this.sentStatus = sentStatus;
    }

    public MsgBelong getMsgBelong() {
        return msgBelong;
    }

    public void setMsgBelong(MsgBelong msgBelong) {
        this.msgBelong = msgBelong;
    }

    public long getSentTime() {
        return sentTime;
    }

    public void setSentTime(long sentTime) {
        this.sentTime = sentTime;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    @Override
    public int compareTo(Message o) {
        return (int)(o.getSentTime()-this.sentTime);
    }
}
