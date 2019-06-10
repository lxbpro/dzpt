package com.gamecenter.gamecenter.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.activity.childFragment.MyRoomFragment;
import com.gamecenter.gamecenter.activity.childFragment.TyLobbyFragment;

public class RoomFragment extends Fragment implements View.OnClickListener {

    private LinearLayout myroom, tylobby;
    private ImageView mTabline;
    private Fragment myroomFragment;
    private Fragment tylobbyFragment;
    private int mScreen1_3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_item_roomlist, container, false);
        initView(view);
        setLinstener();
        initData();
        return view;
    }
    private void initView(View view) {
        myroom = (LinearLayout) view.findViewById(R.id.myroom);
        tylobby = (LinearLayout) view.findViewById(R.id.tylobby);
        mTabline = (ImageView) view.findViewById(R.id.imv_tabline);
    }
    protected void setLinstener() {
        myroom.setOnClickListener(this);
        tylobby.setOnClickListener(this);
    }

    protected void initData() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 2;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);
        setSubFragment(0);
        setmTabline(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myroom:
                showSubFragment(0);
                setmTabline(0);
                break;
            case R.id.tylobby:
                showSubFragment(1);
                setmTabline(1);
                break;
            default:
                break;
        }
    }

    public void setmTabline(int i) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline.getLayoutParams();
        lp.leftMargin = i * mScreen1_3;
        mTabline.setLayoutParams(lp);
    }

    public void setSubFragment(int i) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (0 == i) {
            myroomFragment = (myroomFragment == null ? new MyRoomFragment() : myroomFragment);
            transaction.replace(R.id.id_content, myroomFragment);
            transaction.commit();
        } else if (1 == i) {
            tylobbyFragment = (tylobbyFragment == null ? new TyLobbyFragment() : tylobbyFragment);
            transaction.replace(R.id.id_content, tylobbyFragment);
            transaction.commit();
        }
    }

    public void showSubFragment(int i) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideSubFragment(transaction);
        if (0 == i) {
            myroomFragment = (myroomFragment == null ? new MyRoomFragment() : myroomFragment);
            if(!myroomFragment.isAdded()){
                transaction.add(R.id.id_content, myroomFragment);
            }
            transaction.show(myroomFragment);
            transaction.commit();
        } else if (1 == i) {
            tylobbyFragment = (tylobbyFragment == null ? new TyLobbyFragment() : tylobbyFragment);
            if(!tylobbyFragment.isAdded()){
                transaction.add(R.id.id_content, tylobbyFragment);
            }
            transaction.show(tylobbyFragment);
            transaction.commit();
        }
    }

    public void hideSubFragment(FragmentTransaction transaction) {
        if (myroomFragment != null) {
            transaction.hide(myroomFragment);
        }
        if (tylobbyFragment != null) {
            transaction.hide(tylobbyFragment);
        }
    }
}
