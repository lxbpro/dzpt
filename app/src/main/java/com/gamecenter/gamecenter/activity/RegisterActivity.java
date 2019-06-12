package com.gamecenter.gamecenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.model.UserModel;
import com.gamecenter.gamecenter.view.IRegisterView;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;

import static android.support.v4.content.ContextCompat.startActivity;

public class RegisterActivity extends BaseActivity implements IRegisterView, View.OnClickListener {

    private Button BtnSubmit;
    private EditText Editaccount;
    private EditText EditUsername;
    private EditText EditFrstpassword;
    private EditText EditNextpassword;
    //private  Te

    private UserModel userModel = new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //set listener
        //btnlogin.setOnClickListener(this);
        //btnRegister.setOnClickListener(this);
        init();
        BtnSubmit.setOnClickListener(this);//注册监听事件
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn_nextstep:
                try {
                    userModel.setUsername(EditUsername.getText().toString());
                    userModel.setAccount(Editaccount.getText().toString());
                    userModel.setPwd(EditFrstpassword.getText().toString());
                    if (comparPWD()) {
                        WebSocketManage.getInstance().sendTextMsg(userModel.toRegistJson());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    //校验两次输入的密码
    public boolean comparPWD() {
        EditFrstpassword = (EditText) findViewById(R.id.reg_et_firstpassword);
        EditNextpassword = (EditText) findViewById(R.id.reg_et_secondpassword);
        if (EditFrstpassword.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (EditNextpassword.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "请输入确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean result = EditFrstpassword.getText().toString().equals(EditNextpassword.getText().toString());
        if (!result) {
            Toast.makeText(getApplicationContext(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //初始化注册页面数据199
    public void init() {
        BtnSubmit = (Button) findViewById(R.id.register_btn_nextstep);
        Editaccount = (EditText) findViewById(R.id.reg_et_friendaccount);
        EditUsername = (EditText) findViewById(R.id.reg_et_username);
        EditFrstpassword = (EditText) findViewById(R.id.reg_et_firstpassword);
        EditNextpassword = (EditText) findViewById(R.id.reg_et_secondpassword);

    }

    public void  backLogin()throws JSONException {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
       /* Editaccount.setText("");
        EditUsername.setText("");
        EditFrstpassword.setText("");
        EditNextpassword.setText("");*/
    }
    public void  showRollbackTest(String str)throws JSONException {

        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}
