package com.gamecenter.gamecenter.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecenter.gamecenter.model.UserModel;
import com.gamecenter.gamecenter.view.IRegisterView;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;
import org.xml.sax.XMLReader;

import java.net.URL;

public class ScenarioAllInfoActivity extends BaseActivity implements IRegisterView, View.OnClickListener {
    private UserModel userModel = new UserModel();
    private EditText ScenarioAuthor;//想定作者
    private EditText ScenarioDescription;//想定描述
    private EditText ScenarioName;//想定名称
    private WebView ScenarioComments;//想定内容 WebView;
    private  TextView Scenariotxtviewcontent;
    private String anthor;
    private String strdescription;
    private String txtname;
    private String txthtmlcomments;
    private WebView wv;

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
                WebSocketManage.getInstance().sendTextMsg(userModel.toShowScenarioAllinfo());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //显示想定描述
    public void showallinfo(String authorstr, String description, String strname,String strhtmlcomments) {
        android.os.Message msg2 = android.os.Message.obtain();
        msg2.arg2 = 22;
        handler.sendMessage(msg2);
        anthor = authorstr;
        strdescription = description;
        txtname = strname;
        txthtmlcomments=strhtmlcomments;
        //ScenarioComments.setTextAlignment(comments);
    }

    public void showRollbackTest(String str) throws JSONException {

        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }


    Handler handler = new Handler() {// 创建一个Handler
        @SuppressLint("NewApi")
        public void handleMessage(android.os.Message msg) {
            switch (msg.arg2) {
                case 22:
                    ScenarioAuthor = findViewById(R.id.txtauthor);
                    ScenarioName = findViewById(R.id.txtname);
                    ScenarioDescription = findViewById(R.id.txtdescription);
                    //ScenarioComments = findViewById(R.id.webcomments);
                    Scenariotxtviewcontent= findViewById(R.id.txtviewcontent);
                    /*ScenarioComments = (WebView) findViewById(R.id.wv_webview);*/
                    ScenarioAuthor.setText(anthor);
                    ScenarioName.setText(txtname);
                    ScenarioDescription.setText(strdescription);
                    //ScenarioComments.
                    Scenariotxtviewcontent.setMovementMethod(ScrollingMovementMethod.getInstance());
                    Scenariotxtviewcontent.setText(Html.fromHtml(txthtmlcomments));
                   /* Scenariotxtviewcontent.setText(Html.fromHtml(txthtmlcomments,null,new Html.TagHandler(){
                        int startTag;
                        int endTag;
                        @Override
                        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
                           *//* if(tag.equalsIgnoreCase("td")) {
                                if(opening) {
                                    startTag=output.length();
                                }else {
                                    endTag=output.length();
                                    output.setSpan(new StrikethroughSpan(),startTag,endTag, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }else  if(tag.equalsIgnoreCase("tr")) {
                                if(opening) {
                                    startTag=output.length();
                                }else {
                                    endTag=output.length();
                                    output.setSpan(new StrikethroughSpan(),startTag,endTag, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }else  if(tag.equalsIgnoreCase("th")) {
                                if(opening) {
                                    startTag=output.length();
                                }else {
                                    endTag=output.length();
                                    output.setSpan(new StrikethroughSpan(),startTag,endTag, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }*//*
                        }
                    }));*/
                    break;
            }
        }
    };

    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片
            } catch (Exception e) {
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
                    .getIntrinsicHeight());
            return drawable;
        }

    };
}
