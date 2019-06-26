package com.gamecenter.gamecenter.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToFindTheGroup extends BaseActivity {
    private GridView mAppGridView = null;
    // 应用图标
   /* private int[] mAppIcons = {
            R.drawable.account_current, R.drawable.account_current, R.drawable.account_current,
            R.drawable.account_current, R.drawable.account_current, R.drawable.account_current,
            R.drawable.account_current, R.drawable.account_current, R.drawable.account_current
    };*/
    // 应用名
    private String[] mAppNames = {
            "群组名"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tofindthegroup);
        // 获取界面组件
        mAppGridView = findViewById(R.id.gridview);
        // 初始化数据，创建一个List对象，List对象的元素是Map
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mAppNames.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("icon",R.drawable.account_current);
            listItem.put("name",mAppNames[i]);
            listItems.add(listItem);
        }
        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listItems,
                R.layout.gridview_item,
                new String[]{"icon", "name"},
                new int[]{R.id.icon_img, R.id.name_tv});

        // 为GridView设置Adapter
        mAppGridView.setAdapter(simpleAdapter);

        // 添加列表项被单击的监听器
        mAppGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 显示被单击的图片
                Toast.makeText(ToFindTheGroup.this, mAppNames[position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
