package com.gamecenter.gamecenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.gamecenter.gamecenter.model.GroupModel;

import java.util.List;

public class GroupFragment extends Fragment implements View.OnClickListener{

	private ImageButton imagebtnaddgroup, imagebtnnewgroup, imagebtnnewgroupclass, imagebtngroupsearch;
	private EditText etsearch;
	private ExpandableListView expandableListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab_item_grouplist, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// 加群
		imagebtnaddgroup = view.findViewById(R.id.tab_item_grouplist_btn_addgroup);
		imagebtnnewgroup = view.findViewById(R.id.tab_item_grouplist_btn_newgroup);
		imagebtnnewgroupclass = view.findViewById(R.id.tab_item_grouplist_btn_newgroupclass);
		imagebtngroupsearch = view.findViewById(R.id.tab_item_grouplist_btn_search);
		etsearch = view.findViewById(R.id.tab_item_grouplist_et_search);
		imagebtnaddgroup.setOnClickListener(this);
		imagebtnnewgroup.setOnClickListener(this);
		imagebtnnewgroupclass.setOnClickListener(this);
		imagebtngroupsearch.setOnClickListener(this);


		expandableListView = view.findViewById(R.id.tab_item_grouplist_expandlistview);
		expandableListView.setAdapter(new GroupFragment.MyExpandableListViewAdapter(getActivity()));
		//expandableListView.setGroupIndicator(null);
		//设置子选项点击监听事件
		expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			//groupPosition代表着要接收的数组的行数（组下标）
			// childPosition表示组下单元的下标
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {                         //childPosition代表着要接收的数组的列数
				String groupid = ADataManage.getInstance().getGroupClass().get(groupPosition).getChildStringId(childPosition);
				GroupModel Child = ADataManage.getInstance().getGroupMap().get(groupid);
				Toast.makeText(getActivity(), Child.getGroupName(), Toast.LENGTH_SHORT).show();
				// 转到群聊天页面
				Intent intent = new Intent(getActivity(), NewChatActivity.class);
				Bundle bundle = new Bundle();
				bundle.putParcelable("group",Child);
				intent.putExtras(bundle);
				startActivity(intent);
				return true;
			}
		});
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.tab_item_grouplist_btn_addgroup:
				// 转到加群页面
				Intent intent = new Intent(getActivity(), ToFindTheGroup.class);
				Bundle bundle = new Bundle();
			/*	bundle.putParcelable("group",Child);*/
				intent.putExtras(bundle);
				startActivity(intent);
				Toast.makeText(getActivity(),"申请加群",Toast.LENGTH_SHORT).show();
				break;
			case R.id.tab_item_grouplist_btn_newgroup:
				// 转到建群页面
				Intent createGroupIntent = new Intent(getActivity(), CreateGroupActivity.class);
				startActivity(createGroupIntent);
//				Toast.makeText(getActivity(),"新建群",Toast.LENGTH_SHORT).show();
				break;
			case R.id.tab_item_grouplist_btn_newgroupclass:
				Toast.makeText(getActivity(),"新建群分类",Toast.LENGTH_SHORT).show();
				break;
			case R.id.tab_item_grouplist_et_search:
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
			return ADataManage.getInstance().getGroupClass().size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return ADataManage.getInstance().getGroupClass().get(groupPosition).getChild().size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return ADataManage.getInstance().getGroupClass().get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			String groupid = ADataManage.getInstance().getGroupClass().get(groupPosition).getChildStringId(childPosition);
			GroupModel Child = ADataManage.getInstance().getGroupMap().get(groupid);
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
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			GroupFragment.GroupHolder groupHolder = null;     //我们自己设定的一个简单类，用来存储控件的相关信息
			//这里的convertView其实是一个起缓冲作用的，工具，因为当一个item从屏幕中滚出，我们把它放到convertView里
			// ，这样再滑回来的时候可以直接去取，不用重新创建，这里也推荐一个网址，大家可以详细了解
			if (convertView == null) {
				//把界面放到缓冲区
				convertView = getActivity().getLayoutInflater().from(context).inflate(R.layout.friendlist2_groupitem, null);
				groupHolder = new GroupFragment.GroupHolder();          //实例化我们创建的这个类
				groupHolder.txt = convertView.findViewById(R.id.notice_item_date);  //实例化类里的TextView
				// 给view对象一个标签，告诉计算机我们已经在缓冲区里放了一个view，下回直接来拿就行了
				convertView.setTag(groupHolder);
			} else {
				groupHolder = (GroupFragment.GroupHolder) convertView.getTag();     //然后他就直接来拿
			}
			groupHolder.txt.setText(ADataManage.getInstance().getGroupClass().get(groupPosition).getClassName());//最后在相应的group里设置他相应的Text
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
			GroupFragment.ItemHolder itemHolder = null;
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().from(context).inflate(R.layout.friendlist2_childitem, null);
				itemHolder = new GroupFragment.ItemHolder();
				itemHolder.txt = convertView.findViewById(R.id.group);
				itemHolder.img = convertView.findViewById(R.id.iv);
				convertView.setTag(itemHolder);
			} else {
				itemHolder = (GroupFragment.ItemHolder) convertView.getTag();
			}
			String groupid = ADataManage.getInstance().getGroupClass().get(groupPosition).getChildStringId(childPosition);
			GroupModel Child = ADataManage.getInstance().getGroupMap().get(groupid);
			itemHolder.txt.setText(Child.getGroupName());
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