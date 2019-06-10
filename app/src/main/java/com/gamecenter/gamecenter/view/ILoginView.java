package com.gamecenter.gamecenter.view;

import com.gamecenter.gamecenter.model.UserModel;

public interface ILoginView {
    public void onClearText();
    public void onLoginResult(Boolean result, String errorinfo);
}
