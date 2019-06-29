package com.gamecenter.gamecenter.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gamecenter.gamecenter.Defines.Defines;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateGroupActivity extends BaseActivity implements  View.OnClickListener {
    private Button BtnCreate;
    private EditText EditGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        init();
        BtnCreate.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_create_group:
                String groupName = EditGroupName.getText().toString();
                JSONObject obj = new JSONObject();
                try {
                    obj.put(Defines.CLIENT_REQUEST_TYPE_STR, Defines.REQUEST_TYPE_CREATE_GROUP);
                    obj.put("GroupName", groupName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                WebSocketManage.getInstance().sendTextMsg(obj.toString());
                break;
        }
    }
    //初始化注册页面数据199
    public void init() {
        BtnCreate = (Button) findViewById(R.id.button_create_group);
        EditGroupName = (EditText) findViewById(R.id.edit_group_name);
    }

    public void ShowCreateGroupResult(JSONObject msgobj) throws JSONException {
        boolean success = msgobj.getBoolean("Result");
        if(success) {
            Toast.makeText(getApplicationContext(), "群创建成功", Toast.LENGTH_SHORT).show();
        }
        else {
            String errorInfo = msgobj.getString("ErrorInfo");
            String showStr = "创建群失败：" + errorInfo;
            Toast.makeText(getApplicationContext(), showStr, Toast.LENGTH_LONG).show();
        }
    }
}