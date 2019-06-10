package com.gamecenter.gamecenter.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.model.ADataManage;
import com.gamecenter.gamecenter.model.FriendModel;
import com.gamecenter.gamecenter.model.Message;
import com.gamecenter.gamecenter.model.UserModel;
import com.gamecenter.gamecenter.util.MyDate;
import com.gamecenter.gamecenter.util.circleheadportraitutil.CircularImage;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {
	private UserModel user;// 创建本身用户对象
	private FriendModel frienduser;// 创建聊天用户对象
	private TextView tvfriendname;// 标题聊天对象的昵称
	private String friendname;// 聊天用户名字
	private ListView listviewdialog;// 对话框
	public final static int REFRESH = 123;// 发送消息验证码
	private List<Message> messageList = new ArrayList<>();// 创建一个信息的集合
	private Integer friendid, userid;// 聊天的用户qq 用户qq
	private Button btnsendmessage;// 发送消息的按钮
	private Button btnreturn;// 返回按钮
	private ImageButton imagebtnsetting;
	private EditText etinputmessage;// 输入信息的编辑文本框
	private String usermesssage;// 用户的信息
	private Message message2;// 消息对象2
	private Message message3;// 消息对象3
	private Message message4;// 消息对象4
	private int count = 0;// 创建判断消息条件
	private Boolean flag;// 是否被屏蔽了的判断条件
	private Boolean fBoolean = false;
	private String remark = null;

	Handler handler = new Handler() {// 创建一个Handler
		@SuppressLint("NewApi")
		public void handleMessage(android.os.Message msg) {
			switch (msg.arg2) {
				case 22:
					// 清空输入框的信息
					etinputmessage.setText("");
					// 用冒泡排序来比较发送信息的时间进行排序
					for (int i = 0; i < messageList.size() - 1; i++) {
						for (int j = i + 1; j < messageList.size(); j++) {
							if (new MyDate().CompareDate(messageList.get(i)
									.getMessage_times(), messageList.get(j)
									.getMessage_times()) == 1) {
								// 交换两信息的位置
								message3 = messageList.get(i);
								message4 = messageList.get(j);
								messageList.set(j, message3);
								messageList.set(i, message4);
							}
						}
					}
					// 用户发送消息成功listView来显示消息
					Log.i("TAG", "fBoolean1"+fBoolean);
					if (!fBoolean) {
						listviewdialog.setAdapter(new MyChatAdapter());
					}
					break;
				case 23:
					// 用冒泡排序来比较发送信息的时间进行排序
					for (int i = 0; i < messageList.size() - 1; i++) {
						for (int j = i + 1; j < messageList.size(); j++) {
							if (new MyDate().CompareDate(messageList.get(i)
									.getMessage_times(), messageList.get(j)
									.getMessage_times()) == 1) {
								// 交换两信息的位置
								message3 = messageList.get(i);
								message4 = messageList.get(j);
								messageList.set(j, message3);
								messageList.set(i, message4);
							}
						}
					}
					// listView来显示消息
					Log.i("TAG", "fBoolean2"+fBoolean);
					if (!fBoolean) {
						listviewdialog.setAdapter(new MyChatAdapter());
					}
				case 17:
					if(fBoolean){
						Log.i("TAG", "22222222222");
						btnsendmessage.setEnabled(false);
						etinputmessage.setText("您已屏蔽此好友，不能发送消息！");
						Toast.makeText(getApplicationContext(), "您已屏蔽此好友，不能发送消息！",
								Toast.LENGTH_SHORT).show();
					}
					break;
				case 54:
					// 开启获取好友消息的异步线程
					//new MyObtainMessageAsynTask().execute(URL_STRING2);
					break;
				case 55:
					btnsendmessage.setEnabled(true);
					etinputmessage.setText("");
					fBoolean = false;
					Toast.makeText(getApplicationContext(), "您已解除屏蔽此好友，可以发送消息了！",Toast.LENGTH_SHORT).show();
					break;
				default:
					// 另类处理
					break;
			}
		};
	};

	// onCreate方法一个生命周期只处理一次
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置布局
		setContentView(R.layout.chat);
		// 控件的初始化
		Init();
		// 获取另一个界面传来的信息
		Intent intent = getIntent();
		// 获取Bundle对象
		Bundle bundle = intent.getExtras();
		//remark = bundle.getString("remark");
		frienduser = bundle.getParcelable("friend");
		// 这是在好友列表中传来时来获取聊天好友的信息
		if (bundle.getParcelable("friend") == null) {
			// 获取好友的昵称
			friendname = bundle.getString("username");
			// 获取好友的id
			friendid = bundle.getInt("userid");
		} else {// 这是在未读消息界面传来的信息
			// 获取好友的昵称
			if(remark != null && !remark.equals("null") ){
				friendname = remark;
			}else{
				friendname = frienduser.getUsername();
			}
			// 获取好友的id
			friendid = frienduser.getUserid();
		}
		// 取未读消息
		//messageList = ADataManage.getInstance().loadUnReadMsgByFriendId(friendid);

		BaseActivity.setCurrentFriend(frienduser);
		// 给聊天的对象名字赋值
		tvfriendname.setText(friendname);
		// 获取本地用户的对象
		user = BaseActivity.getCurrentUser();
		// 获取用户的qq
		userid = user.getId();
		// 发送信息的按钮
		btnsendmessage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// 判断用户输入的信息是不是空,空的就为true
				if (!TextUtils.isEmpty(etinputmessage.getText())) {
					// 获取用户输入的信息
					usermesssage = etinputmessage.getText() + "";
					// 获取系统时间(进行了封装，想要中文，英文应有尽有)
					String time = new MyDate().getDateEN();
					// 创建一个信息对象信息
					message2 = new Message(userid, friendid, usermesssage, time, 0, 0);
					// 发送消息
					try {
						message2.SendMessageTouser();
					} catch (JSONException e) {
						e.printStackTrace();
					}
					setChatMessageToView(message2);
				} else {
					// 当信息输入为空时，则Toast提醒用户信息不能为空
					Toast.makeText(getApplicationContext(), "不能发送空信息", Toast.LENGTH_SHORT).show();
				}
			}
		});
		// 返回按钮监听
		btnreturn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// 返回到主界面
				Intent intent = new Intent(ChatActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		// 对好友设置
		imagebtnsetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Intent intent = new Intent(ChatActivity.this, ChatSettingActivity.class);
				//Bundle bundle = new Bundle();
				//bundle.putString("friendname", friendname);
				//bundle.putInt("friendid", friendid);
				//intent.putExtras(bundle);
				//startActivity(intent);
			}
		});
	}

	// 关联控件初始化
	private void Init() {
		tvfriendname = (TextView) findViewById(R.id.chat_tv_friendname);
		listviewdialog = (ListView) findViewById(R.id.chat_listview_dialog);
		btnsendmessage = (Button) findViewById(R.id.chat_btn_sendmessage);
		etinputmessage = (EditText) findViewById(R.id.chat_et_inputmessage);
		btnreturn = (Button) findViewById(R.id.chat_btn_return);
		imagebtnsetting = (ImageButton) findViewById(R.id.chat_imagebtn_friendsetting);
	}

	public void setChatMessageToView(Message msg){
		messageList.add(msg);
		android.os.Message msg2 = android.os.Message.obtain();
		msg2.arg2 = 22;
		handler.sendMessage(msg2);
	}

	// 消息适配器
	class MyChatAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return messageList.size();
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
			View row = arg1;// 已拥有就不去再去获取
			ChatWrapper chatWrapper;// 持有者优化
			if (row == null) {
				row = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listitemchat, arg2, false);
				chatWrapper = new ChatWrapper(row);
				row.setTag(chatWrapper);
			} else {
				chatWrapper = (ChatWrapper) row.getTag();
			}
			// 调用持有者的方法
			CircularImage userheadportrait = chatWrapper.getUserportrait();
			CircularImage friendheadportrait = chatWrapper.getFriendportrait();
			TextView tvfriendmessage = chatWrapper.getFriendMessage();
			TextView tvusermessage = chatWrapper.getUserMessage();
			TextView tvtime = chatWrapper.getMessageTime();
			LinearLayout userlly = chatWrapper.getUserLly();
			LinearLayout friendlly = chatWrapper.getFriendLly();
			// friendheadportrait.setImageResource(R.drawable.icon);
			// userheadportrait.setImageResource(R.drawable.headportrait);
			// 取出对应消息
			Message msg = messageList.get(arg0);
			// 控件上赋值
			tvtime.setText(msg.getMessage_times());
			// 当为用户发送消息时，则显示在右边，显示左边隐藏
			if (msg.getMessage_senderId() == userid) {
				userlly.setVisibility(View.VISIBLE);
				userheadportrait.setImageResource(R.drawable.icon);
				tvusermessage.setText(msg.getMessage_content());
				if ((messageList.size() - 1) == arg0) {
					friendlly.setVisibility(View.INVISIBLE);
				}
				// 当为好友发来信息时，好友信息显示在左边，右边隐藏
			} else if (msg.getMessage_senderId() == friendid) {
				friendlly.setVisibility(View.VISIBLE);
				tvfriendmessage.setText(msg.getMessage_content());
				friendheadportrait.setImageResource(R.drawable.icon);
				if ((messageList.size() - 1) == arg0) {
					userlly.setVisibility(View.INVISIBLE);
				}
			}
			return row;// 务必返回
		}
	}

	// 信息的持有者优化
	class ChatWrapper {
		// 定义私有化控件
		private View row;
		private CircularImage userheadportrait, friendheadportrait;
		private TextView tvusermessage, tvfriendmessage, tvtime;
		private LinearLayout friendlly, userlly;

		// 创建一个带row的构造方法
		public ChatWrapper(View row) {
			this.row = row;
		}

		// 定义获得不同控件的方法
		public LinearLayout getFriendLly() {
			if (friendlly == null) {
				friendlly = (LinearLayout) row.findViewById(R.id.listitemchat_friendlly);
			}
			return friendlly;
		}

		public LinearLayout getUserLly() {
			if (userlly == null) {
				userlly = (LinearLayout) row.findViewById(R.id.listitemchat_userlly);
			}
			return userlly;
		}

		public CircularImage getUserportrait() {
			if (userheadportrait == null) {
				userheadportrait = (CircularImage) row.findViewById(R.id.listitemchat_cover_user_userphoto);
			}
			return userheadportrait;
		}

		public CircularImage getFriendportrait() {
			if (friendheadportrait == null) {
				friendheadportrait = (CircularImage) row.findViewById(R.id.listitemchat_cover_user_friendphoto);
			}
			return friendheadportrait;
		}

		public TextView getUserMessage() {
			if (tvusermessage == null) {
				tvusermessage = (TextView) row.findViewById(R.id.listitemchat_tv_usermessage);
			}
			return tvusermessage;
		}

		public TextView getFriendMessage() {
			if (tvfriendmessage == null) {
				tvfriendmessage = (TextView) row.findViewById(R.id.listitemchat_tv_friendmessage);
			}
			return tvfriendmessage;
		}

		public TextView getMessageTime() {
			if (tvtime == null) {
				tvtime = (TextView) row.findViewById(R.id.listitemchat_tv_time);
			}
			return tvtime;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onRestart() {
		super.onRestart();
	}
}
