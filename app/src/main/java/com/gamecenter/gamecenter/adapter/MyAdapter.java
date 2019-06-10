package com.gamecenter.gamecenter.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.model.FriendModel;
import com.gamecenter.gamecenter.model.Friends;
import com.gamecenter.gamecenter.model.UserModel;
import com.gamecenter.gamecenter.util.circleheadportraitutil.CircularImage;

import java.util.ArrayList;

public class MyAdapter extends BaseExpandableListAdapter {
	private Context context;
	private ArrayList<String> groupnameList = new ArrayList<>();
	private ArrayList<ArrayList<FriendModel>> groupuserList = new ArrayList<>();
	private ArrayList<FriendModel> friendsList = new ArrayList<>();
	public MyAdapter(Context context, ArrayList<String> groupnameList,
                     ArrayList<ArrayList<FriendModel>> groupuserList, ArrayList<FriendModel> friendsList) {
		this.context = context;
		this.groupnameList = groupnameList;
		this.groupuserList = groupuserList;
		this.friendsList = friendsList;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		
		return groupuserList.get(arg0).get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3, ViewGroup arg4) {
		 Log.i("TAG",arg0+"!"+arg1+"@@");
		 View row = arg3;
		 WrapperChild wrapperChild;
		 if(row==null){
		 	  row = LayoutInflater.from(context).inflate(R.layout.groupitemlist, arg4,false);
			  wrapperChild = new WrapperChild(row);
			  row.setTag(wrapperChild);
		 }else{
		 	  wrapperChild = (WrapperChild) row.getTag();
		 }
		 CircularImage headportrait = wrapperChild.getFriendheadportrait();
		 TextView tvfriendname = wrapperChild.getFriendName();
		 ImageView imagebtnwebcam = wrapperChild.getWebcamStatue();
		 TextView tvfriendstatue = wrapperChild.getFriendStatue();
		 for (FriendModel f : friendsList) {
		 	  Log.i("TAG", f.toString()+"FFFFFFFFFFF");
		 }
		 Log.i("TAG",friendsList.get(arg0).getRemark() +"!!!!!!!!!!!!!!!!!");
		FriendModel user = groupuserList.get(arg0).get(arg1);
		 String name;
		 if(!friendsList.get(arg1).getRemark().equals("null")){
		 	  name= friendsList.get(arg1).getRemark();
		 }else{
		 	  name = user.getUsername();
		 }
		 String statue = user.getUserstatus();
		 switch (statue){
		 case "1":
		 	  tvfriendstatue.setText("在线");
		 	  break;
		 case "6":
		 	  tvfriendstatue.setText("离线");
		 	  break;
		 default:
		 	  break;
		 }
		//int webcam = user.getUsername();
		int webcam = 1;
		tvfriendname.setText(name);
		if (webcam == 0) {
			imagebtnwebcam.setVisibility(View.GONE);
		} else if (webcam == 1) {
			imagebtnwebcam.setVisibility(View.VISIBLE);
		}
		headportrait.setImageResource(R.drawable.icon);
		return row;
	}

	@Override
	public int getChildrenCount(int arg0) {
		return groupuserList.get(arg0).size();
	}

	@Override
	public Object getGroup(int arg0) {
		return groupuserList.get(arg0);
	}

	@Override
	public int getGroupCount() {
		return groupnameList.size();
	}

	@Override
	public long getGroupId(int arg0) {
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		View row = arg2 ;
		WrapperGroup wrappergroup;
		if(row==null){
			row = LayoutInflater.from(context).inflate(R.layout.listitemgroup, arg3,false);
			wrappergroup = new WrapperGroup(row);
			row.setTag(wrappergroup);
		}else{
			wrappergroup = (WrapperGroup) row.getTag();
		}
		TextView tvgroupname = (TextView) row.findViewById(R.id.listitemgroup_tv_groupname);
		TextView tvitemcount = (TextView) row.findViewById(R.id.listitemgroup_tv_itemcount);
		tvgroupname.setText(groupnameList.get(arg0));
		tvitemcount.setText(groupuserList.get(arg0).size()+"");
		return row;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	//子菜单的监听，有监听则返回true，无则返回false
	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}
	class WrapperChild{
		private View row;
		private CircularImage headportrait;
		private TextView tvfriendname;
		private ImageView imagebtnwebcam;
		private TextView tvfriendstatue;

		public WrapperChild(View row) {
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
				imagebtnwebcam = (ImageView) row.findViewById(R.id.groupitemlist_imagebtn_webcam);
			}
			return imagebtnwebcam;
		}
	}
		 
	class WrapperGroup{
		private View row;
		private TextView tvgroupname,tvitemcount;
		public WrapperGroup(View row){
			this.row = row;
		}
		public TextView getGroupName(){
			if(tvgroupname==null){
				tvgroupname = (TextView) row.findViewById(R.id.listitemgroup_tv_groupname);
			}
			return tvgroupname;
		}
		public TextView getItemCount(){
			if(tvitemcount==null){
				tvitemcount = (TextView) row.findViewById(R.id.listitemgroup_tv_groupname);
			}
			return tvitemcount;
		}
	}
	
}
