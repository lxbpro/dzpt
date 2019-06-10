package com.gamecenter.gamecenter.activity.childFragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

// 共享想定页面
public class ShareScenFragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "ShareScenFragment==onCreateView",Toast.LENGTH_SHORT).show();
        TextView tv = new TextView(getActivity());
        tv.setTextSize(25);
        tv.setBackgroundColor(Color.parseColor("#FFA07A"));
        tv.setText("共享想定页面");
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}