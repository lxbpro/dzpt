package com.gamecenter.gamecenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecenter.gamecenter.activity.NewChat.NewChatActivity;
import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.model.ADataManage;
import com.gamecenter.gamecenter.model.FriendModel;

import java.util.ArrayList;
import java.util.List;

public class FriendFragment2 extends Fragment implements View.OnClickListener{

    private ImageButton imagebtnaddfriend, imagebtnnewfriend, imagebtnspecialfriend, imagebtnscantwocode, imagebtnsearch;
    private EditText etsearch;;
    private ExpandableListView expandableListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_item_friendist, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 添加好友
        imagebtnaddfriend = (ImageButton) view.findViewById(R.id.tab_item_friendlist_btn_addfriend);
        imagebtnnewfriend = (ImageButton) view.findViewById(R.id.tab_item_friendlist_btn_newfriend);
        imagebtnspecialfriend = (ImageButton)view.findViewById(R.id.tab_item_friendlist_btn_specialfriend);
        imagebtnscantwocode = (ImageButton)view.findViewById(R.id.tab_item_friendlist_btn_scantwocode);
        imagebtnsearch = (ImageButton)view.findViewById(R.id.tab_item_friendlist_btn_search);
        etsearch = (EditText)view.findViewById(R.id.tab_item_friendlist_et_search);
        imagebtnaddfriend.setOnClickListener(this);
        imagebtnnewfriend.setOnClickListener(this);
        imagebtnspecialfriend.setOnClickListener(this);
        imagebtnscantwocode.setOnClickListener(this);
        imagebtnsearch.setOnClickListener(this);


        expandableListView = (ExpandableListView)view.findViewById(R.id.tab_item_friendlist_expandlistview);
        expandableListView.setAdapter(new MyExpandableListViewAdapter(getActivity()));
        //expandableListView.setGroupIndicator(null);
        //设置子选项点击监听事件
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            //groupPosition代表着要接收的数组的行数（组下标）
            // childPosition表示组下单元的下标
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {                         //childPosition代表着要接收的数组的列数
                int friendid = ADataManage.getInstance().getFriendClass().get(groupPosition).getChildIntegerId(childPosition);
                FriendModel Child = ADataManage.getInstance().getFridendMap().get(friendid);
                Toast.makeText(getActivity(), Child.getUsername(), Toast.LENGTH_SHORT).show();
                // 转到聊天页面
                //Intent intent = new Intent(getActivity(),ChatActivity.class);
                Intent intent = new Intent(getActivity(),NewChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("friend",Child);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_item_friendlist_btn_addfriend:
                Toast.makeText(getActivity(),"添加好友",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_item_friendlist_btn_newfriend:
                Toast.makeText(getActivity(),"新朋友？？",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_item_friendlist_btn_specialfriend:
                Toast.makeText(getActivity(),"@盆友？",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_item_friendlist_btn_scantwocode:
                Toast.makeText(getActivity(),"二维码么？",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_item_friendlist_et_search:
                String serachStr = etsearch.getText().toString();
                if("".equals(serachStr)){
                    Toast.makeText(getActivity(),"搜索框无内容！！！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"搜索-"+serachStr,Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        private Context context;

        public MyExpandableListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return ADataManage.getInstance().getFriendClass().size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return  ADataManage.getInstance().getFriendClass().get(groupPosition).getChild().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return ADataManage.getInstance().getFriendClass().get(groupPosition).getClassName();
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            int friendid = ADataManage.getInstance().getFriendClass().get(groupPosition).getChildIntegerId(childPosition);
            FriendModel Child = ADataManage.getInstance().getFridendMap().get(friendid);
            return Child;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;     //我们自己设定的一个简单类，用来存储控件的相关信息
            if (convertView == null) {          //这里的convertView其实是一个起缓冲作用的，工具，因为当一个item从屏幕中滚出，我们把它放到convertView里                                                 //，这样再滑回来的时候可以直接去取，不用重新创建，这里也推荐一个网址，大家可以详细了解
                convertView = (View) getActivity().getLayoutInflater().from(context).inflate(
                        R.layout.friendlist2_groupitem, null);      //把界面放到缓冲区
                groupHolder = new GroupHolder();          //实例化我们创建的这个类
                groupHolder.txt = (TextView) convertView.findViewById(R.id.notice_item_date);  //实例化类里的TextView
                convertView.setTag(groupHolder);                                 //给view对象一个标签，告诉计算机我们已经在缓冲区里放了一个view，下回直                                                                               //接来拿就行了
            } else {
                groupHolder = (GroupHolder) convertView.getTag();     //然后他就直接来拿
            }
            groupHolder.txt.setText(ADataManage.getInstance().getFriendClass().get(groupPosition).getClassName());//最后在相应的group里设置他相应的Text
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = (View) getActivity().getLayoutInflater().from(context).inflate( R.layout.friendlist2_childitem, null);
                itemHolder = new ItemHolder();
                itemHolder.txt = (TextView) convertView.findViewById(R.id.group);
                itemHolder.img = (ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }
            int friendid = ADataManage.getInstance().getFriendClass().get(groupPosition).getChildIntegerId(childPosition);
            FriendModel Child = ADataManage.getInstance().getFridendMap().get(friendid);
            itemHolder.txt.setText(Child.getUsername());
            itemHolder.img.setBackgroundResource(Child.getImgid());
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    class GroupHolder {
        public TextView txt;
        public ImageView img;
    }

    class ItemHolder {
        public ImageView img;
        public TextView txt;
    }

}
