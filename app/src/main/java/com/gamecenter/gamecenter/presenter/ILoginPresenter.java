package com.gamecenter.gamecenter.presenter;

import com.gamecenter.gamecenter.util.SharePreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

public interface ILoginPresenter {
    void clear();
    void doLogin(String account, String pwd) throws JSONException;
    void loginResult(JSONObject msgObj) throws JSONException;
    void rememberUserInfo(SharePreferenceUtil util);
}
