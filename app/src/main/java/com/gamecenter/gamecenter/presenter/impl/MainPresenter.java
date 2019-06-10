package com.gamecenter.gamecenter.presenter.impl;

import com.gamecenter.gamecenter.Defines.Defines;
import com.gamecenter.gamecenter.activity.BaseActivity;
import com.gamecenter.gamecenter.model.FriendModel;
import com.gamecenter.gamecenter.model.UserModel;
import com.gamecenter.gamecenter.presenter.IMainPresenter;
import com.gamecenter.gamecenter.view.IMainView;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainPresenter implements IMainPresenter {

    UserModel currentUser;
    List<FriendModel> friendList;
    IMainView mainView;

    public MainPresenter(IMainView view){
        mainView = view;
        currentUser = BaseActivity.getCurrentUser();
    }

    @Override
    public void sendRequestFriendMsg() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_UPDATE_FRIEND_LIST);
        WebSocketManage.getInstance().sendTextMsg(object.toString());
    }

    @Override
    public void sendRequestGroupsMsg() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_UPDATE_GROUP);
        WebSocketManage.getInstance().sendTextMsg(object.toString());
    }

    @Override
    public void sendRequestGroupMemberMsg() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_UPDATE_GROUP_MEMBER);
        WebSocketManage.getInstance().sendTextMsg(object.toString());
    }

    @Override
    public void sendRequestScenListMsg() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_LOAD_SCENARIO_LIST);
        WebSocketManage.getInstance().sendTextMsg(object.toString());
    }

}
