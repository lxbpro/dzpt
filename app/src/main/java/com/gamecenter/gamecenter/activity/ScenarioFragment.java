package com.gamecenter.gamecenter.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gamecenter.gamecenter.activity.R;
import com.gamecenter.gamecenter.activity.childFragment.MyRoomFragment;
import com.gamecenter.gamecenter.activity.childFragment.PrivateScenFragment;
import com.gamecenter.gamecenter.activity.childFragment.PublicScenFragment;
import com.gamecenter.gamecenter.activity.childFragment.ShareScenFragment;
import com.gamecenter.gamecenter.activity.childFragment.TyLobbyFragment;

import java.util.ArrayList;
import java.util.List;

public class ScenarioFragment extends Fragment implements View.OnClickListener {

    private LinearLayout publicscen, privatescen, sharescen;
    private ImageView mscenTabline;
    private Fragment publicFragment;
    private Fragment privateFragment;
    private Fragment shareFragment;
    private int mScreen;
    private static int itemindex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_item_scenariolist, container, false);
        initView(view);
        setLinstener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //int    position   = pager.getCurrentItem();
        //String searchWord = searchtv.getSearchWord();
        //两层fragment嵌套，调用指定fragment的刷新操作，fuck,这方法找了好几天
        //Fragment fragment = getChildFragmentManager().getFragments().get(mScreen);
        //showSubFragment(0);
        //setmTabline(0);
        initData();
    }

    private void initView(View view) {
        publicscen = (LinearLayout) view.findViewById(R.id.public_scen);
        privatescen = (LinearLayout) view.findViewById(R.id.private_scen);
        sharescen = (LinearLayout) view.findViewById(R.id.share_scen);
        mscenTabline = (ImageView) view.findViewById(R.id.img_scen_tabline);
    }
    protected void setLinstener() {
        publicscen.setOnClickListener(this);
        privatescen.setOnClickListener(this);
        sharescen.setOnClickListener(this);
    }

    protected void initData() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen = outMetrics.widthPixels / 3;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mscenTabline.getLayoutParams();
        lp.width = mScreen;
        mscenTabline.setLayoutParams(lp);
        setSubFragment(itemindex);
        setmTabline(itemindex);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.public_scen:
                itemindex = 0;
                //showSubFragment(0);
                //setmTabline(0);
                break;
            case R.id.private_scen:
                itemindex = 1;
                //showSubFragment(1);
                //setmTabline(1);
                break;
            case R.id.share_scen:
                itemindex = 2;
                //showSubFragment(2);
                //setmTabline(2);
                break;
            default:
                break;
        }
        setSubFragment(itemindex);
        setmTabline(itemindex);
    }

    public void setmTabline(int i) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mscenTabline.getLayoutParams();
        lp.leftMargin = i * mScreen;
        mscenTabline.setLayoutParams(lp);
    }

    public void setSubFragment(int i) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (0 == i) {
            publicFragment = (publicFragment == null ? new PublicScenFragment() : publicFragment);
            transaction.replace(R.id.id_scen_content, publicFragment);
            transaction.commit();
        } else if (1 == i) {
            privateFragment = (privateFragment == null ? new PrivateScenFragment() : privateFragment);
            transaction.replace(R.id.id_scen_content, privateFragment);
            transaction.commit();
        }else if(2 == i){
            shareFragment = (shareFragment == null ? new ShareScenFragment() : shareFragment);
            transaction.replace(R.id.id_scen_content, shareFragment);
            transaction.commit();
        }
    }

    public void showSubFragment(int i) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideSubFragment(transaction);
        if (0 == i) {
            publicFragment = (publicFragment == null ? new PublicScenFragment() : publicFragment);
            if(!publicFragment.isAdded()){
                transaction.add(R.id.id_scen_content, publicFragment);
            }
            transaction.show(publicFragment);
            transaction.commit();
        } else if (1 == i) {
            privateFragment = (privateFragment == null ? new PrivateScenFragment() : privateFragment);
            if(!privateFragment.isAdded()){
                transaction.add(R.id.id_scen_content, privateFragment);
            }
            transaction.show(privateFragment);
            transaction.commit();
        } else if (2 == i) {
            shareFragment = (shareFragment == null ? new ShareScenFragment() : shareFragment);
            if(!shareFragment.isAdded()){
                transaction.add(R.id.id_scen_content, shareFragment);
            }
            transaction.show(shareFragment);
            transaction.commit();
        }
    }

    public void hideSubFragment(FragmentTransaction transaction) {
        if (publicFragment != null) {
            transaction.hide(publicFragment);
        }
        if (privateFragment != null) {
            transaction.hide(privateFragment);
        }
        if(shareFragment != null){
            transaction.hide(shareFragment);
        }
    }

}