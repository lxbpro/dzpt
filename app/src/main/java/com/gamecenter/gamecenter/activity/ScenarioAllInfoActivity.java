package com.gamecenter.gamecenter.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecenter.gamecenter.model.UserModel;
import com.gamecenter.gamecenter.view.IRegisterView;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;

public class ScenarioAllInfoActivity extends BaseActivity implements IRegisterView, View.OnClickListener {
    private UserModel userModel = new UserModel();
    private EditText ScenarioAuthor;//想定作者
    private EditText ScenarioDescription;//想定描述
    private EditText ScenarioName;//想定名称
    private  String anthor;
    private  String strdescription;
    private  String  txtname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenarioallinfo);
        init();
        /* BtnSubmit.setOnClickListener(this);//注册监听事件*/
    }
    @Override
    public void onClick(View v) {


    }
    //初始化数据
    public void init() {
        Intent intent = getIntent();//获取意向
        int scenid = intent.getIntExtra("scenid", 0);
        try {
            userModel.setselectscenarioid(scenid);
            if (scenid != 0) {
                WebSocketManage.getInstance().sendTextMsg(userModel.toShowScenarioAllinfo());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //显示想定描述
    public  void showallinfo(String authorstr,String description,String strname)
    {
        android.os.Message msg2 = android.os.Message.obtain();
        msg2.arg2 = 22;
        handler.sendMessage(msg2);
        anthor=authorstr;
        strdescription=description;
        txtname=strname;
        //ScenarioComments.setTextAlignment(comments);
    }
    public void  showRollbackTest(String str)throws JSONException {

        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

    Handler handler = new Handler() {// 创建一个Handler
        @SuppressLint("NewApi")
        public void handleMessage(android.os.Message msg) {
            switch (msg.arg2) {
                case 22:
                    ScenarioAuthor = findViewById(R.id.txtauthor);
                    ScenarioName= findViewById(R.id.txtname);
                    ScenarioDescription = findViewById(R.id.txtdescription);


                    /*ScenarioComments = (WebView) findViewById(R.id.wv_webview);*/
                    ScenarioAuthor.setText(anthor);
                    ScenarioName.setText(txtname);
                    ScenarioDescription.setText(strdescription);
                    break;
            }
        }
    };




}
