package com.gamecenter.gamecenter.util;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.gamecenter.gamecenter.activity.R;

public class RemainderDialog extends Dialog {
	protected RemainderDialog(Context context, boolean cancelable,
                              OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}
	protected RemainderDialog(Context context, int theme
			) {
		super(context, theme);
	}
	public RemainderDialog(Context context
			) {
		super(context);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.remaindermessage);
		super.onCreate(savedInstanceState);
	}
}
