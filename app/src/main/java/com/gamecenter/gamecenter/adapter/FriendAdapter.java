package com.gamecenter.gamecenter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamecenter.gamecenter.adapter.commonAdapter.ChildHolder;
import com.gamecenter.gamecenter.adapter.commonAdapter.DataInfo;
import com.gamecenter.gamecenter.adapter.commonAdapter.DefaultAdapter;
import com.gamecenter.gamecenter.adapter.commonAdapter.ParentHolder;
import com.gamecenter.gamecenter.model.ClassModel;
import com.gamecenter.gamecenter.model.FriendModel;
import com.gamecenter.gamecenter.model.GroupModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendAdapter extends DefaultAdapter<ClassModel> {
    private List<ClassModel> pList = new ArrayList<>();
    private Map<ClassModel, List<FriendModel>> cMap = new HashMap<>();

    public FriendAdapter(Context context, List<ClassModel> parentList, Map<ClassModel, List<ClassModel>> map) {
        super(context, parentList, map);
    }

    @Override
    public ClassModel getGroup(int postion) {
        return pList.get(postion);
    }

    @Override
    protected ParentHolder<ClassModel> getParentHolder() {
        return null;
    }

    @Override
    protected ChildHolder<ClassModel> getChildHolder() {
        return null;
    }

}