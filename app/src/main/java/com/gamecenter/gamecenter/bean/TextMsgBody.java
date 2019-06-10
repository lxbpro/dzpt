package com.gamecenter.gamecenter.bean;


import com.gamecenter.gamecenter.Defines.Defines;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;
import org.json.JSONObject;

public class TextMsgBody extends MsgBody {
     private String message;
     private String extra;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    public TextMsgBody() {
    }

    public TextMsgBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendTextMsgJson(String targetid) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_SEND_PRIVATE_MESSAGE);
        obj.put(Defines.PRIVATE_MESSAGE, message);
        obj.put(Defines.USER_ID, ""+ targetid);
        WebSocketManage.getInstance().sendTextMsg(obj.toString());
    }

    public void sendGroupTextMsgJson(String targetid) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_SEND_GROUP_MESSAGE);
        obj.put(Defines.GROUP_MESSAGE,message);
        obj.put(Defines.GROUP_ID, ""+ targetid);
        WebSocketManage.getInstance().sendTextMsg(obj.toString());
    }

    @Override
    public String toString() {
        return "TextMsgBody{" +
                "message='" + message + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
