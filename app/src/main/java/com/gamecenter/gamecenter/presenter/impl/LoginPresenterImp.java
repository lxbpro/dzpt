package com.gamecenter.gamecenter.presenter.impl;

import android.os.Handler;
import android.os.Looper;

import com.gamecenter.gamecenter.Defines.Defines;
import com.gamecenter.gamecenter.activity.BaseActivity;
import com.gamecenter.gamecenter.model.UserModel;
import com.gamecenter.gamecenter.presenter.ILoginPresenter;
import com.gamecenter.gamecenter.util.MD5Util;
import com.gamecenter.gamecenter.util.SharePreferenceUtil;
import com.gamecenter.gamecenter.view.ILoginView;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginPresenterImp implements ILoginPresenter {
    ILoginView loginView;
    UserModel userModel;
    Handler handler;

    public LoginPresenterImp(ILoginView loginView) {
        this.loginView = loginView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void clear() {
        loginView.onClearText();
    }

    @Override
    public void doLogin(String account, String pwd) throws JSONException {
        userModel.setAccount(account);
        userModel.setPwd(pwd);
        WebSocketManage.getInstance().sendTextMsg(userModel.toLoginJson());
    }

    // 处理登陆结果
    @Override
    public void loginResult(JSONObject msgObj) throws JSONException {
        if(msgObj.has(Defines.REQUEST_TYPE_RESULT)){
            if(Defines.REQUEST_TYPE_RESULT_TRUE.equals(msgObj.get(Defines.REQUEST_TYPE_RESULT).toString())){
                if(msgObj.has(Defines.USER_INFO)){
                    JSONObject userdata = msgObj.getJSONObject(Defines.USER_INFO);
                    if(userModel == null){
                        userModel = new UserModel();
                    }
                    userModel.setUserInfo(userdata);
                    BaseActivity.setCurrentUser(userModel);
                    loginView.onLoginResult(true,null);
                }
            }else {
                loginView.onLoginResult(false,msgObj.getString(Defines.REQUEST_TYPE_ERROR_INFO));
            }
        }else {
            loginView.onLoginResult(false,"数据丢失，请重新登陆。");
        }
    }

    @Override
    public void rememberUserInfo(SharePreferenceUtil util) {
        util.setUserid(userModel.getId());
        util.setUserAccount(userModel.getAccount());
        util.setUserpassword(userModel.getPwd());
        util.setUserName(userModel.getUsername());
    }

    private void initUser(){
        userModel = new UserModel();
    }
}
