package com.gamecenter.gamecenter.activity;

import android.os.Bundle;
import android.view.View;

import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.view.IRegisterView;

public class RegisterActivity extends BaseActivity implements IRegisterView,View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //set listener
        //btnlogin.setOnClickListener(this);
        //btnRegister.setOnClickListener(this);

        //Init();
    }

    @Override
    public void onClick(View v) {

    }
}
