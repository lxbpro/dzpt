package com.gamecenter.gamecenter.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.gamecenter.gamecenter.model.ADataManage;
import com.gamecenter.gamecenter.model.GroupModel;
import com.gamecenter.gamecenter.model.UserModel;
import com.gamecenter.gamecenter.websocketmanage.WebSocketManage;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ToFindTheGroup extends BaseActivity implements View.OnClickListener {
    private GridView mAppGridView = null;
    private GroupModel groupModel = new GroupModel();
    private Button btnaddgroupsearch = null;
    private EditText txtaddgroupsearch = null;
    private SimpleAdapter newsimpleAdapter=null;
    // 应用图标
    private int[] mAppIcons = {
            R.drawable.account_current, R.drawable.account_current, R.drawable.account_current,
            R.drawable.account_current, R.drawable.account_current, R.drawable.account_current,
            R.drawable.account_current, R.drawable.account_current, R.drawable.account_current
    };

    // 应用名
    private String[] mAppNames = {
            "群组名"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tofindthegroup);

        btnaddgroupsearch = findViewById(R.id.addgroupsearch);//查找按钮
        txtaddgroupsearch = findViewById(R.id.txtaddgroupsearch);//输入查询群的文本框

        btnaddgroupsearch.setOnClickListener(this);
        // 获取界面组件
        mAppGridView = findViewById(R.id.gridview);
        // 初始化数据，创建一个List对象，List对象的元素是Map
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        Map<String, Object> listItem = new HashMap<String, Object>();
        listItem.put("icon", R.drawable.account_current);
        listItem.put("name", mAppNames[0]);
        listItems.add(listItem);
        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listItems,
                R.layout.gridview_item,
                new String[]{"icon", "name"},
                new int[]{R.id.icon_img, R.id.name_tv});

        // 为GridView设置Adapter
       /* mAppGridView.setAdapter(simpleAdapter);*/

        // 添加列表项被单击的监听器
       /* mAppGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 显示被单击的图片
                Toast.makeText(ToFindTheGroup.this, mAppNames[position],
                        Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addgroupsearch:
                try {
                    groupModel.setGroupName(txtaddgroupsearch.getText().toString());
                    WebSocketManage.getInstance().sendTextMsg(groupModel.toFindGroupjson());//请求服务器-查询群
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void UpdataLoad() throws JSONException {
        Map<String, GroupModel> groupMap = ADataManage.getInstance().getGroupMap();
        Set<Map.Entry<String, GroupModel>> entrys = groupMap.entrySet();
        // 初始化数据，创建一个List对象，List对象的元素是Map
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (Map.Entry<String, GroupModel> entry : entrys) {
            GroupModel objectgroupInfo = entry.getValue();
            String groupid = objectgroupInfo.getGroupid();// 群组id
            String groupname = objectgroupInfo.getGroupName();// 群组名称
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("icon", R.drawable.account_current);
            listItem.put("name", groupname);
            listItems.add(listItem);
        }
        // 创建一个SimpleAdapter
        newsimpleAdapter = new SimpleAdapter(this,
                listItems,
                R.layout.gridview_item,
                new String[]{"icon", "name"},
                new int[]{R.id.icon_img, R.id.name_tv});

        Message msg = new Message();
        msg.obj = "";
        handler.sendMessage(msg);

    }
    private  Handler handler = new Handler() {
        // 创建一个Handler
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 为GridView设置Adapter
            mAppGridView.setAdapter(newsimpleAdapter);

        }
    };

}
