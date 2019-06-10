package com.gamecenter.gamecenter.model;

import com.gamecenter.gamecenter.activity.MainActivity;
import com.gamecenter.gamecenter.util.MyDate;

/**
 *
 * @author xx
 *
 */
public class NearestContact {
	private int sum_message;			//未读消息总数
	private int message_senderId;	//发送人id
	private String user_nickName;	//发送人昵称
	private int message_state;		//消息状态
	private String groupid = null;	// 群组消息的此属性不为空
	private String message_info;		// 消息内容

	public NearestContact() {
		super();
	}

	public NearestContact(int sum_message, int message_senderId, String user_nickName, int message_state) {
		super();
		this.sum_message = sum_message;
		this.message_senderId = message_senderId;
		this.user_nickName = user_nickName;
		this.message_state = message_state;
	}

	public int getSum_message() {
		return sum_message;
	}

	public void setSum_message(int sum_message) {
		this.sum_message = sum_message;
	}

	public int getMessage_senderId() {
		return message_senderId;
	}

	public void setMessage_senderId(int message_senderId) {
		this.message_senderId = message_senderId;
	}

	public String getUser_nickName() {
		return user_nickName;
	}

	public void setUser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}

	public int getMessage_state() {
		return message_state;
	}

	public void setMessage_state(int message_state) {
		this.message_state = message_state;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getMessage_info() {
		return message_info;
	}

	public void setMessage_info(String message_info) {
		this.message_info = message_info;
	}

	public Message toMsg(){
		return new Message(message_senderId,
				MainActivity.getCurrentUser().getId(),
				message_info,
				new MyDate().getDateEN(),
				message_state,
				0
				);
	}

	@Override
	public String toString() {
		return "NearestContact{" +
				"sum_message=" + sum_message +
				", message_senderId=" + message_senderId +
				", user_nickName='" + user_nickName + '\'' +
				", message_state=" + message_state +
				", groupid='" + groupid + '\'' +
				'}';
	}


}
