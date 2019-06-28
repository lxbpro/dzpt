package com.gamecenter.gamecenter.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gamecenter.gamecenter.Defines.Defines;
import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.util.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

// 群组模型
public class GroupModel implements Parcelable {
    // 分组
    private String groupid;
    private String groupName;
    private String notice; // 群公告
    private int classid;    // 群分类id
    private int imgid = R.drawable.ic_launcher;      // 群头像id
    //List<FriendModel> childBeanList;
    private Map<Integer, FriendModel>    groupMember;    // 群成员

    public GroupModel() {

    }

    //反序列化
    protected GroupModel(Parcel in) {
        groupid = in.readString();
        groupName = in.readString();
        notice = in.readString();
        classid = in.readInt();
        imgid = in.readInt();
    }

    //序列化
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(groupid);
        dest.writeString(groupName);
        dest.writeString(notice);
        dest.writeInt(classid);
        dest.writeInt(imgid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GroupModel> CREATOR = new Creator<GroupModel>() {
        @Override
        public GroupModel createFromParcel(Parcel in) {
            return new GroupModel(in);
        }

        @Override
        public GroupModel[] newArray(int size) {
            return new GroupModel[size];
        }
    };

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public void clearMenberMap(){
        groupMember.clear();
    }
    public void addGroupMember(FriendModel friend){
        groupMember.put(friend.getUserid(), friend);
    }
    public void removeMemberById(Integer id){
        groupMember.remove(id);
    }


    public String toFindGroupjson() throws JSONException {
        JSONObject userobj = new JSONObject();
        userobj.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_FIND_GROUP);
        userobj.put(Defines.GROUP_NAME,groupName);
        return userobj.toString();
    }
}
