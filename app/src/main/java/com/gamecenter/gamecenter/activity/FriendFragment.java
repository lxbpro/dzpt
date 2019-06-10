package com.gamecenter.gamecenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.adapter.MyAdapter;
import com.gamecenter.gamecenter.adapter.commonAdapter.ChildHolder;
import com.gamecenter.gamecenter.adapter.commonAdapter.DataInfo;
import com.gamecenter.gamecenter.adapter.commonAdapter.DefaultAdapter;
import com.gamecenter.gamecenter.adapter.commonAdapter.ParentHolder;
import com.gamecenter.gamecenter.model.ClassModel;
import com.gamecenter.gamecenter.model.FriendModel;
import com.gamecenter.gamecenter.model.Friends;
import com.gamecenter.gamecenter.model.GroupModel;
import com.gamecenter.gamecenter.model.UserModel;
import com.gamecenter.gamecenter.util.circleheadportraitutil.CircularImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  此文件停用了，好友列表使用FriendFragment2类
 * 
 * @author Administrator
 * 
 */
public class FriendFragment extends Fragment {
	private ImageButton imagebtnaddfriend, imagebtnnewfriend;
	//private ListView listviewfriends;

	private Boolean flag = false;
	private ArrayList<String> groupnameList = new ArrayList<>();
	private ArrayList<ClassModel> groupList = new ArrayList<>();
	private ArrayList<FriendModel> friendsList = new ArrayList<>();
	private ArrayList<ArrayList<FriendModel>> groupuserList = new ArrayList<>();
	private UserModel user;
	private ArrayList<UserModel> userList = new ArrayList<>();
	private Friends friends;
	private MyAdapter myadapter2;
	private ExpandableListView expandlistview;

	private List<DataInfo> pList = new ArrayList<>();
	private Map<DataInfo, List<DataInfo>> cMap = new HashMap<>();
	private ExpandableAdapter adapter;
	private int groupItemSelect = -1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.tab_item_friendist, container, false);
	}

	@Override
	public void onResume() {
		super.onResume();
		/*
		friendsList.clear();
		groupnameList.clear();
		groupuserList.clear();
		friendsList = MainActivity.getFriendsList();
		groupnameList = MainActivity.getFriendClassNames();
		groupuserList = MainActivity.getFriendClassUserList();
		myadapter2 = new MyAdapter(getActivity(),groupnameList,groupuserList,friendsList);
		expandlistview.setAdapter(myadapter2);
		*/
		adapter = new ExpandableAdapter(getContext(), pList, cMap);
		//设置adapter
		expandlistview.setAdapter(adapter);
	}
	private void setChildSelect(int group, int child) {
		DataInfo dataInfo = pList.get(group);
		List<DataInfo> dataInfos = cMap.get(dataInfo);
		DataInfo dataInfo1 = dataInfos.get(child);
		String childItemId = dataInfo1.childItemId;
		for (DataInfo info : dataInfos) {
			String itemId = info.childItemId;
			if (childItemId.equals(itemId)) {
				info.childItemSelect = true;
			} else {
				info.childItemSelect = false;
			}
		}
		cMap.put(dataInfo, dataInfos);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Init(view);
		/*
		listviewfriends.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				user = userList.get(arg2);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				//intent.putExtras(bundle);
				//intent.setAction("com.wdy.qq.FriendDetailActivity");
				//startActivity(intent);
			}
		});
		*/
		// Init2();
		Log.i("TAG", "一遍 Created" + flag);
		// 添加好友
		imagebtnaddfriend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				//bundle.putParcelableArrayList("userList", userList);
				//intent.putExtras(bundle);
				//intent.setAction("com.wdy.action.AddFriendByAccountActivity");
				//startActivity(intent);
			}
		});
		// 新朋友
		imagebtnnewfriend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Intent intent = new Intent();
				//intent.setAction("com.wdy.qq.action.NewFriendsRequestListActivity");
				//startActivity(intent);
			}
		});
		Init2();
	}

	// expandlistview初始化
	private void Init2() {
//子条目点击事件
		expandlistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				if (groupItemSelect == -1 || groupItemSelect == groupPosition) {
					setChildSelect(groupPosition, childPosition);
				} else {
					setChildSelect(groupPosition, childPosition);

					DataInfo dataInfo2 = pList.get(groupItemSelect);
					List<DataInfo> dataInfos1 = cMap.get(dataInfo2);
					for (DataInfo info : dataInfos1) {
						info.childItemSelect = false;
					}
					cMap.put(dataInfo2, dataInfos1);
				}
				Toast.makeText(getActivity(), "groupPosition-->" + groupPosition + "childPosition-->" + childPosition, Toast.LENGTH_LONG).show();

				adapter.nodfiyMapData(cMap);
				groupItemSelect = groupPosition;
				return false;
			}
		});
		//Group点击事件
		expandlistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				DataInfo dataInfo = pList.get(groupPosition);
				Toast.makeText(getActivity(), dataInfo.itemTitle, Toast.LENGTH_LONG).show();
				return false;
			}
		});
	}

	private void Init(View view) {
		//listviewfriends = (ListView) view	.findViewById(R.id.tab_item_friendlist_listview);
		imagebtnaddfriend = (ImageButton) view
				.findViewById(R.id.tab_item_friendlist_btn_addfriend);
		imagebtnnewfriend = (ImageButton) view
				.findViewById(R.id.tab_item_friendlist_btn_newfriend);
		expandlistview = (ExpandableListView) view
				.findViewById(R.id.tab_item_friendlist_expandlistview);
	}

	class Wrapper {
		private View row;
		private CircularImage headportrait;
		private TextView tvfriendname;
		private ImageView imagebtnwebcam;
		private TextView tvfriendstatue;

		public Wrapper(View row) {
			this.row = row;
		}

		public CircularImage getFriendheadportrait() {
			if (headportrait == null) {
				headportrait = (CircularImage) row
						.findViewById(R.id.groupitemlist_cover_user_photo);
			}
			return headportrait;
		}

		public TextView getFriendName() {
			if (tvfriendname == null) {
				tvfriendname = (TextView) row
						.findViewById(R.id.groupitemlist_tv_friendname);
			}
			return tvfriendname;
		}

		public TextView getFriendStatue() {
			if (tvfriendstatue == null) {
				tvfriendstatue = (TextView) row
						.findViewById(R.id.groupitemlist_tv_friendstatue);
			}
			return tvfriendstatue;
		}

		public ImageView getWebcamStatue() {
			if (imagebtnwebcam == null) {
				imagebtnwebcam = (ImageView) row
						.findViewById(R.id.groupitemlist_imagebtn_webcam);
			}
			return imagebtnwebcam;
		}
	}

	/**
	 * 数据适配器
	 */
	class ExpandableAdapter extends DefaultAdapter<DataInfo> {

		public ExpandableAdapter(Context context, List<DataInfo> parentList, Map<DataInfo, List<DataInfo>> map) {
			super(context, parentList, map);
		}

		@Override
		protected ParentHolder<DataInfo> getParentHolder() {
			return new PHolder();
		}

		@Override
		protected ChildHolder<DataInfo> getChildHolder() {
			return new CHolder();
		}
	}
	/**
	 * group holder
	 */
	class PHolder extends ParentHolder<DataInfo> {
		TextView tvGroup;
		ImageView ivIcon;

		@Override
		public void refreshView(List<DataInfo> list, int position, boolean isExpanded) {
			DataInfo dataInfo = list.get(position);
			tvGroup.setText(dataInfo.itemTitle);
			//判断group是否有展开
			if (isExpanded) {
				ivIcon.setImageResource(R.drawable.up);
			} else {
				ivIcon.setImageResource(R.drawable.down);
			}
		}

		@Override
		public View initView() {
			View view = LayoutInflater.from(getActivity()).inflate(R.layout.expandable_group, null, false);
			tvGroup = findID(view, R.id.tv_group);
			ivIcon = findID(view, R.id.iv_icon);
			return view;
		}
	}

	/**
	 * child holder
	 */
	class CHolder extends ChildHolder<DataInfo> {
		TextView tvChild;
		ImageView ivCheck;

		@Override
		public void refreshView(List<DataInfo> list, int position) {
			DataInfo dataInfo = list.get(position);
			tvChild.setText(dataInfo.itemTitle);
			boolean childItemSelect = dataInfo.childItemSelect;
			if (childItemSelect) {
				ivCheck.setImageResource(R.drawable.check);
			} else {
				ivCheck.setImageResource(R.drawable.uncheck);
			}
		}

		@Override
		public View initView() {
			View view = LayoutInflater.from(getActivity()).inflate(R.layout.expandable_child, null, false);
			tvChild = findID(view, R.id.tv_child);
			ivCheck = findID(view, R.id.iv_check);
			return view;
		}
	}

	private void setData() {
		for (int i = 0; i < 5; i++) {
			DataInfo info = new DataInfo();
			info.itemTitle = "group" + i;
			pList.add(info);
		}
		for (DataInfo dataInfo : pList) {
			List<DataInfo> chList = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				DataInfo info = new DataInfo();
				info.itemTitle = "child" + i;
				info.childItemId = dataInfo.itemTitle + "child" + i;
				info.childItemSelect = false;
				chList.add(info);
			}
			cMap.put(dataInfo, chList);
		}
	}
}
