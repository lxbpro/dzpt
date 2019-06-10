package com.gamecenter.gamecenter.activity;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gamecenter.gamecenter.model.FriendModel;
import com.gamecenter.gamecenter.model.GroupModel;
import com.gamecenter.gamecenter.model.UserModel;
import com.gamecenter.gamecenter.util.Constants;
import com.gamecenter.gamecenter.util.ExitAppReceiver;

public class BaseActivity extends AppCompatActivity {
    private static Activity mActivity = null;
    private static UserModel currentUser = null;
    private static FriendModel currentFriend = null;
    private static GroupModel currentGroup = null;

    private ExitAppReceiver exitReceiver = new ExitAppReceiver();//创建一个广播对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registeExitReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentActivity(this);
    }

    private void registeExitReceiver() {
        IntentFilter exitFilter = new IntentFilter();
        exitFilter.addAction(Constants.EXIT_APP_ACTION);
        registerReceiver(exitReceiver, exitFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exitReceiver);
    }

    public static Activity getCurrentActivity(){
        return mActivity;
    }

    public static UserModel getCurrentUser(){
        return currentUser;
    }

    private void setCurrentActivity(Activity activity){
        mActivity = activity;
    }

    public static void setCurrentUser(UserModel userModel){
        currentUser = userModel;
    }

    public static void setCurrentFriend(FriendModel friend){
        currentFriend = friend;
    }
    public static FriendModel getCurrentFriend(){
        return currentFriend;
    }

    public static void setCurrentGroup(GroupModel group){currentGroup = group;}
    public static GroupModel getCurrentGroup(){return currentGroup;}

}
