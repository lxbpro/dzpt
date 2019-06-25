package com.gamecenter.gamecenter.model;

import com.gamecenter.gamecenter.Defines.Defines;
import com.gamecenter.gamecenter.util.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

public class UserModel {
    private int id;
    private String username;
    private String account;
    private String pwd;
    private String email;
    private int userstatus = 0;// 用户状态    0无；1在线；2离开；3忙碌；4免打扰；5隐身；6离线。
    private int user_webcam;// 头像？
    private int select_scenarioid;//选中的想定id

    public void setselectscenarioid(int scenrioid) {
        this.select_scenarioid = scenrioid;
    }
    public int getselectscenarioid(){ return  select_scenarioid; }
    public int getUser_webcam() {
        return user_webcam;
    }

    public void setUser_webcam(int user_webcam) {
        this.user_webcam = user_webcam;
    }

    public int getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(int userstatus) {
        this.userstatus = userstatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toLoginJson() throws JSONException {
        JSONObject userobj = new JSONObject();
        userobj.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_LOGIN);
        userobj.put(Defines.USER_ACCOUNT,account);
        userobj.put(Defines.USER_PASSWORD,MD5Util.toMD5(pwd));
        userobj.put(Defines.USER_LOGIN_STATUS, userstatus);
        return userobj.toString();
    }

    public String toRegistJson() throws JSONException {
        JSONObject userobj = new JSONObject();
        userobj.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_REGIST);
        userobj.put(Defines.USER_ACCOUNT,account);
        userobj.put(Defines.USER_PASSWORD,MD5Util.toMD5(pwd));
        userobj.put(Defines.USER_NAME,username);
        userobj.put(Defines.USER_EMAIL,email);
        return userobj.toString();
    }

    public void setUserInfo(JSONObject obj) throws JSONException {
        id = obj.getInt(Defines.USER_ID);
        account = obj.getString(Defines.USER_ACCOUNT);
        email = obj.getString(Defines.USER_EMAIL);
        userstatus = obj.getInt(Defines.USER_LOGIN_STATUS);
    }

    //请求显示选中的想定信息
    public String toShowScenarioAllinfo()throws JSONException
    {
        JSONObject userobj = new JSONObject();
        userobj.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_SCENARIO_INFO);
        String scenid =  String.valueOf(select_scenarioid) ;//想定的int类型转换为string
        userobj.put(Defines.SCENARIO_ID,scenid);
        return userobj.toString();
    }
}
