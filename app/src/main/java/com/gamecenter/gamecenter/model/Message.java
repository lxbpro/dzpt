package com.gamecenter.gamecenter.model;

import com.gamecenter.gamecenter.Defines.Defines;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
	private int message_senderId;//发送的id
	private int message_recipientId;//接受的id
	private String message_content;//内容
	private String message_times;//时间
	private int message_state;//读没 被读
	private int message_type;//信息类型
	
	public Message() {
		super();
	}

	public Message(int message_senderId, int message_recipientId,
                   String message_content, String message_times, int message_state,
                   int message_type) {
		super();
		this.message_senderId = message_senderId;
		this.message_recipientId = message_recipientId;
		this.message_content = message_content;
		this.message_times = message_times;
		this.message_state = message_state;
		this.message_type = message_type;
	}

	public int getMessage_senderId() {
		return message_senderId;
	}

	public void setMessage_senderId(int message_senderId) {
		this.message_senderId = message_senderId;
	}

	public int getMessage_recipientId() {
		return message_recipientId;
	}

	public void setMessage_recipientId(int message_recipientId) {
		this.message_recipientId = message_recipientId;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getMessage_times() {
		return message_times;
	}

	public void setMessage_times(String message_times) {
		this.message_times = message_times;
	}

	public int getMessage_state() {
		return message_state;
	}

	public void setMessage_state(int message_state) {
		this.message_state = message_state;
	}

	public int getMessage_type() {
		return message_type;
	}

	public void setMessage_type(int message_type) {
		this.message_type = message_type;
	}

	@Override
	public String toString() {
		return "Message [message_senderId=" + message_senderId
				+ ", message_recipientId=" + message_recipientId
				+ ", message_content=" + message_content + ", message_times="
				+ message_times + ", message_state=" + message_state
				+ ", message_type=" + message_type + "]";
	}

	public void SendMessageTouser() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_SEND_PRIVATE_MESSAGE);
		obj.put(Defines.PRIVATE_MESSAGE,message_content);
		obj.put(Defines.USER_ID, ""+ message_recipientId);
		WebSocketManage.getInstance().sendTextMsg(obj.toString());
	}

}
