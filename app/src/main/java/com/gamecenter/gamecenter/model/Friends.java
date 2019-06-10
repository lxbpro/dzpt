package com.gamecenter.gamecenter.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Friends implements Parcelable{
	private int friends_uid;//好友id
	private int friends_toUid;//锟斤拷锟斤拷锟斤拷锟矫伙拷ID
	private int friends_status;//好友状态
	private int chat_status;//好友之间的沟通状态
	private String remark;//备注
	public Friends(int friends_uid, int friends_toUid, int friends_status,
                   int chat_status, String remark) {
		super();
		this.friends_uid = friends_uid;
		this.friends_toUid = friends_toUid;
		this.friends_status = friends_status;
		this.chat_status = chat_status;
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Friends() {
		super();
	}

	public int getChat_status() {
		return chat_status;
	}

	public void setChat_status(int chat_status) {
		this.chat_status = chat_status;
	}

	public Friends(int friends_uid, int friends_toUid, int friends_status,
                   int chat_status) {
		super();
		this.friends_uid = friends_uid;
		this.friends_toUid = friends_toUid;
		this.friends_status = friends_status;
		this.chat_status = chat_status;
	}

	public Friends(int friends_uid, int friends_toUid, int friends_status) {
		super();
		this.friends_uid = friends_uid;
		this.friends_toUid = friends_toUid;
		this.friends_status = friends_status;
	}

	public int getFriends_uid() {
		return friends_uid;
	}

	public void setFriends_uid(int friends_uid) {
		this.friends_uid = friends_uid;
	}

	public int getFriends_toUid() {
		return friends_toUid;
	}

	public void setFriends_toUid(int friends_toUid) {
		this.friends_toUid = friends_toUid;
	}

	public int getFriends_status() {
		return friends_status;
	}

	public void setFriends_status(int friends_status) {
		this.friends_status = friends_status;
	}


	@Override
	public String toString() {
		return "Friends [friends_uid=" + friends_uid + ", friends_toUid="
				+ friends_toUid + ", friends_status=" + friends_status
				+ ", chat_status=" + chat_status + ", remark=" + remark + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}
	//序列化
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeInt(friends_uid);
		arg0.writeInt(friends_toUid);
		arg0.writeInt(friends_status);
	}
	//反序列化
	protected Friends(Parcel in){
		this.friends_uid = in.readInt();
		this.friends_toUid = in.readInt();
		this.friends_status = in.readInt();
	}
	public static final Creator<Friends> CREATOR = new Creator<Friends>() {

		@Override
		public Friends createFromParcel(Parcel arg0) {
			return new Friends(arg0);
		}

		@Override
		public Friends[] newArray(int arg0) {
			return new Friends[arg0];
		}
	};
	
	
}
