package com.gamecenter.gamecenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.util.Constants;
import com.gamecenter.gamecenter.util.SharePreferenceUtil;

public class WelcomeActivity extends BaseActivity {
    private SharePreferenceUtil util ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        util = new SharePreferenceUtil(getApplicationContext(), Constants.SAVE_USER);
        util.setIsFirst(false);
        Init();
    }
    private void Init() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
