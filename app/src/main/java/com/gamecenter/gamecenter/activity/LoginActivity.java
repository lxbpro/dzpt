package com.gamecenter.gamecenter.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.presenter.ILoginPresenter;
import com.gamecenter.gamecenter.presenter.impl.LoginPresenterImp;
import com.gamecenter.gamecenter.util.ConnectionReceiver;
import com.gamecenter.gamecenter.util.Constants;
import com.gamecenter.gamecenter.util.RemainderDialog;
import com.gamecenter.gamecenter.util.SharePreferenceUtil;
import com.gamecenter.gamecenter.view.ILoginView;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements ILoginView,View.OnClickListener {

    private SharePreferenceUtil util ;
    private EditText etaccount,etpassword;
    private Button btnRegister,btnlogin;
    private CheckBox checkBoxsavepassword;
    private ConnectionReceiver connRece = new ConnectionReceiver();
    private String account,password;
    private RemainderDialog remainderDialog;

    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        WebSocketManage.getInstance().init();
        Init();
        //set listener
        btnlogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        loginPresenter = new LoginPresenterImp(this);

        remainderDialog = new RemainderDialog(LoginActivity.this);
        util = new SharePreferenceUtil(getApplicationContext(), Constants.SAVE_USER);
        //注册网络连接的广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connRece, intentFilter);
        Boolean flag = util.getIsFirst();
        if(!flag){
            account = util.getUserAccount()+"";
            password = util.getUserpassword();
            Log.i("TAG", account+"********"+password);
            if(!password.equals("")){
                checkBoxsavepassword.setChecked(true);
                etpassword.setText(password);
            }else{
                etpassword.setText("");
            }
            if(!account.equals("")){
                etaccount.setText(account);
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(connRece);//销毁时，注销广播
        super.onDestroy();
    }

    private void Init() {
        btnRegister = (Button) findViewById(R.id.login_btn_register);
        btnlogin = (Button) findViewById(R.id.login_btn_login);
        etaccount = (EditText) findViewById(R.id.login_et_account);
        etpassword = (EditText) findViewById(R.id.login_et_password);
        checkBoxsavepassword = (CheckBox) findViewById(R.id.login_checkbox_savepassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_login:
                btnlogin.setEnabled(false);
                btnRegister.setEnabled(false);
                try {
                    loginPresenter.doLogin(etaccount.getText().toString(), etpassword.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.login_btn_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClearText() {
        etaccount.setText("");
        etpassword.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, String errorinfo) {
        if (result){
            if(checkBoxsavepassword.isChecked()){
                loginPresenter.rememberUserInfo(util);
            }
            loginToMain();
        }else {
            etpassword.setText("");
            btnlogin.setEnabled(true);
            btnRegister.setEnabled(true);
            Toast.makeText(getApplicationContext(), errorinfo, Toast.LENGTH_SHORT).show();
        }
    }

    public void dealLoginResult(JSONObject msgObj) throws JSONException {
        loginPresenter.loginResult(msgObj);
    }

    public void loginToMain(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        //intent.putExtra("userinfo",);
        startActivity(intent);
        finish();
    }

}
