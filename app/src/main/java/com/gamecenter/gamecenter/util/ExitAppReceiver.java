package com.gamecenter.gamecenter.util;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class ExitAppReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		if (context != null) {
			if (context instanceof Activity) {
				Log.i("TAG", "有EXitAPPActivity退出");
				((Activity) context).finish();// Activity的销毁
			} else if (context instanceof FragmentActivity) {
				((FragmentActivity) context).finish();// 销毁FragmentActivity
			} else if (context instanceof Service) {
				((Service) context).stopSelf();// 销毁广播
			}
		}
	}
}
