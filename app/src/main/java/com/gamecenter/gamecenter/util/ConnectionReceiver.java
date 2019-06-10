package com.gamecenter.gamecenter.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {
	private Boolean flag;

	@Override
	public void onReceive(Context context, Intent arg1) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				flag = mNetworkInfo.isAvailable(); // 查看网络是否可用，true可用，false不可用
				if (flag) {
					Log.i("TAG", "有网络");
					Toast.makeText(context, "有网络", Toast.LENGTH_SHORT).show();
				} 
			}
			else {
				Toast.makeText(context, "目前没有网络,请查看网络连接", Toast.LENGTH_SHORT)
						.show();
				Log.i("TAG", "无网络");
			}
		}

	}
}
