package com.gamecenter.gamecenter.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gamecenter.gamecenter.activity.R;

public class FriendModel implements Parcelable {
    private int userid;
    private String account;
    private String username;
    private String email;
    private String userstatus = "0";// 用户状态    0无；1在线；2离开；3忙碌；4免打扰；5隐身；6离线。
    private int chat_status;//好友之间的沟通状态
    private int groupid;        // 分类id
    private String remark;      // 备注
    private int imgid = R.drawable.icon;

    public FriendModel(){}

    public static final Creator<FriendModel> CREATOR = new Creator<FriendModel>() {
        @Override
        public FriendModel createFromParcel(Parcel in) {
            return new FriendModel(in);
        }

        @Override
        public FriendModel[] newArray(int size) {
            return new FriendModel[size];
        }
    };

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public int getChat_status() {
        return chat_status;
    }

    public void setChat_status(int chat_status) {
        this.chat_status = chat_status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //序列化
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userid);
        dest.writeInt(groupid);
        dest.writeString(username);
        dest.writeString(account);
        dest.writeString(userstatus);
    }
    //反序列化
    public FriendModel(Parcel in){
        this.userid = in.readInt();
        this.groupid = in.readInt();
        this.username = in.readString();
        this.account = in.readString();
        this.userstatus = in.readString();
    }
}
