package com.gamecenter.gamecenter.activity.childFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecenter.gamecenter.activity.ChatActivity;
import com.gamecenter.gamecenter.activity.FriendFragment2;
import com.gamecenter.gamecenter.activity.MainActivity;
import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.model.ADataManage;
import com.gamecenter.gamecenter.model.FriendModel;
import com.gamecenter.gamecenter.model.ScenarioModel;

import java.util.List;

// 公共想定页面
public class PublicScenFragment extends Fragment {

    private ExpandableListView expandableListView;
    //private List<String> ScenClass;
    //private List<List<ScenarioModel>> ScenChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.public_scen_fragment, container, false);

        expandableListView = (ExpandableListView)view.findViewById(R.id.public_scen_expandlistview);
        //设置子选项点击监听事件
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            //groupPosition代表着要接收的数组的行数（组下标）
            // childPosition表示组下单元的下标
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {                         //childPosition代表着要接收的数组的列数
                int groupid = ADataManage.getInstance().getPublicScenClass().get(groupPosition).getChildIntegerId(childPosition);
                ScenarioModel ScenChild = ADataManage.getInstance().getPublicScenMap().get(groupid);
                Toast.makeText(getActivity(), ScenChild.getScenname(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //ScenClass = MainActivity.getPublicScenClassNames();
        //ScenChild = MainActivity.getPublicScenClassscenList();
        expandableListView.setAdapter(new PublicScenFragment.MyExpandableListViewAdapter(getActivity()));
    }

    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        private Context context;

        public MyExpandableListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return ADataManage.getInstance().getPublicScenClass().size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return ADataManage.getInstance().getPublicScenClass().get(groupPosition).getChild().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return ADataManage.getInstance().getPublicScenClass().get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            int groupid = ADataManage.getInstance().getPublicScenClass().get(groupPosition).getChildIntegerId(childPosition);
            ScenarioModel ScenChild = ADataManage.getInstance().getPublicScenMap().get(groupid);
            return ScenChild;
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
            PublicScenFragment.GroupHolder groupHolder = null;     //我们自己设定的一个简单类，用来存储控件的相关信息
            if (convertView == null) {          //这里的convertView其实是一个起缓冲作用的，工具，因为当一个item从屏幕中滚出，我们把它放到convertView里                                                 //，这样再滑回来的时候可以直接去取，不用重新创建，这里也推荐一个网址，大家可以详细了解

                convertView = (View) getActivity().getLayoutInflater().from(context).inflate(
                        R.layout.friendlist2_groupitem, null);      //把界面放到缓冲区
                groupHolder = new PublicScenFragment.GroupHolder();          //实例化我们创建的这个类
                groupHolder.txt = (TextView) convertView.findViewById(R.id.notice_item_date);  //实例化类里的TextView
                convertView.setTag(groupHolder);                                 //给view对象一个标签，告诉计算机我们已经在缓冲区里放了一个view，下回直                                                                               //接来拿就行了
            } else {
                groupHolder = (PublicScenFragment.GroupHolder) convertView.getTag();     //然后他就直接来拿
            }
            groupHolder.txt.setText(ADataManage.getInstance().getPublicScenClass().get(groupPosition).getClassName());//最后在相应的group里设置他相应的Text
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            PublicScenFragment.ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = (View) getActivity().getLayoutInflater().from(context).inflate(
                        R.layout.friendlist2_childitem, null);
                itemHolder = new PublicScenFragment.ItemHolder();
                itemHolder.txt = (TextView) convertView.findViewById(R.id.group);
                itemHolder.img = (ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (PublicScenFragment.ItemHolder) convertView.getTag();
            }
            int groupid = ADataManage.getInstance().getPublicScenClass().get(groupPosition).getChildIntegerId(childPosition);
            ScenarioModel ScenChild = ADataManage.getInstance().getPublicScenMap().get(groupid);
            itemHolder.txt.setText(ScenChild.getScenname());
            itemHolder.img.setBackgroundResource(ScenChild.getImgid());
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