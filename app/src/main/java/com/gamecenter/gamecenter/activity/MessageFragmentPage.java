package com.gamecenter.gamecenter.activity;

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
import android.widget.ListView;
import android.widget.TextView;

import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.model.ADataManage;
import com.gamecenter.gamecenter.model.NearestContact;
import com.gamecenter.gamecenter.util.circleheadportraitutil.CircularImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageFragmentPage extends Fragment {
	private ListView messageListView;
	private List<NearestContact> nearestContactList = new ArrayList<NearestContact>();
	//private String URL_STRING;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case 66:
				messageListView.setAdapter(new MyUnReadMessageAdapter());
				break;

			default:
				break;
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.tab_item_messagelist, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Init(view);
		nearestContactList.clear();
		//Collections.reverse(list1)
		nearestContactList = MainActivity.getUnReadMsg();
		for(int i = 0; i<20; i++){
			NearestContact nearestContact = new NearestContact(i,i,"神秘人"+i,0);
			nearestContact.setMessage_info("测试内容能不能显示。"+i);
			nearestContactList.add(nearestContact);
		}
		//new Thread(new GameThread()).start();
		messageListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				NearestContact nearestContact = nearestContactList.get(arg2);
				//String groupid = nearestContact.getGroupid();
				Intent intent = new Intent(getActivity(),ChatActivity.class);
				Bundle bundle = new Bundle();
				//bundle.putString("friendname", nearestContact.getUser_nickName());
				bundle.putParcelable("friend", ADataManage.getInstance().getFridendMap().get(nearestContact.getMessage_senderId()));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		Message msg = new Message();
		msg.arg1 = 66;
		handler.sendMessage(msg);
	}

	private void Init(View view) {
		messageListView = (ListView) view	.findViewById(R.id.tab_item_message_listview);
	}
/*
	class GameThread implements Runnable
	{
		public void run()
		{

			while (!Thread.currentThread().isInterrupted())
			{
				URL_STRING = MainActivity.FIRSTSTRING
						+ "/qqServer/GetNearestContactServlet.action?user_id="
						+ MainTabActivity.user.getUser_id();
				new MyObtainUnReadMessageAsynTask().execute(URL_STRING);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

		}
	}
*/
/*
	class MyObtainUnReadMessageAsynTask extends
			AsyncTask<String, String, ArrayList<NearestContact>> {

		@Override
		protected ArrayList<NearestContact> doInBackground(String... arg0) {
			String content = new ObtainData(URL_STRING).getObtainData();
			ArrayList<NearestContact> nearestContactList2 = new JsonUnReadMessageData(content).getUnReadMessage();
			Log.i("TAG", content+"DDDDDDDDdddF");
			if (nearestContactList2 != null) {
				return nearestContactList2;
			}

			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<NearestContact> result) {
			super.onPostExecute(result);
			if (result != null) {
				for (NearestContact near : result) {
					Log.i("TAG", "fdsfssdfs0"+near.toString());
					
				}
				if(result.size()!=0){
					for (int i = 0; i < result.size(); i++) {
						nearestContactList.add(result.get(i));
					}
					nearestContactList = result;
					Message msg = new Message();
					msg.arg1 = 66;
					handler.sendMessage(msg);
				}
			}
		}
	}
	*/
	public void addUnReadMsg(NearestContact nearestContact){
		nearestContactList.add(nearestContact);
		Message msg = new Message();
		msg.arg1 = 66;
		handler.sendMessage(msg);
	}
	
	class MyUnReadMessageAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return nearestContactList.size();
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View row = arg1;
			Wrapper wrapper;
			if(row==null){
				row = LayoutInflater.from(getActivity()).inflate(R.layout.listitemmessage2, arg2,false);
				wrapper = new Wrapper(row);
				row.setTag(wrapper);
			}else{
				wrapper = (Wrapper) row.getTag();
			}
			CircularImage headportrait = wrapper.getFriendHeadPortrait();
			TextView tvfriendname = wrapper.getFriendName();
			TextView tvmessagenum = wrapper.getMessagenum();
			TextView tvmessagetime = wrapper.getMessagetime();
			TextView tvmsginfo = wrapper.getMessageInfo();
			headportrait.setImageResource(R.drawable.icon);
			NearestContact nearestContact = nearestContactList.get(arg0);
			tvfriendname.setText(nearestContact.getUser_nickName());
			tvmessagenum.setText(nearestContact.getSum_message()+"");
			tvmessagetime.setText("2018-10-11 11:33:10");
			tvmsginfo.setText(nearestContact.getMessage_info());
			return row;
		}
		
	}
	
	class Wrapper{
		private View row;
		private CircularImage headportraitimage;
		private TextView tvfriendname,tvmessagenum,tvmesginfo,tvmsgtime;
		public Wrapper(View row){
			this.row = row;
		}
		private CircularImage getFriendHeadPortrait(){
			if(headportraitimage==null){
				headportraitimage = (CircularImage) row.findViewById(R.id.listitemmessage2_cover_user_photo);
			}
			return headportraitimage;
		}
		private TextView getFriendName(){
			if(tvfriendname==null){
				tvfriendname = (TextView) row.findViewById(R.id.listitemmessage2_tv_name);
			}
			return tvfriendname;
		}
		private TextView getMessagenum(){
			if(tvmessagenum==null){
				tvmessagenum = (TextView) row.findViewById(R.id.listitemmessage2_tv_messagenum);
			}
			return tvmessagenum;
		}
		private TextView getMessagetime(){
			if(tvmsgtime == null){
				tvmsgtime = (TextView)row.findViewById(R.id.listitemmessage2_tv_time);
			}
			return tvmsgtime;
		}
		private TextView getMessageInfo(){
			if(tvmesginfo==null){
				tvmesginfo = (TextView) row.findViewById(R.id.listitemmessage2_tv_msginfo);
			}
			return tvmesginfo;
		}
	}
}
