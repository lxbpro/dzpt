package com.gamecenter.gamecenter.presenter;

import org.json.JSONException;

public interface IMainPresenter {
    void sendRequestFriendMsg() throws JSONException;
    void sendRequestGroupsMsg() throws JSONException;
    void sendRequestGroupMemberMsg() throws JSONException;
    void sendRequestScenListMsg() throws JSONException;
}
