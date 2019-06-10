package com.gamecenter.gamecenter.model;

import java.util.ArrayList;
import java.util.List;

// 分类模型
// 好友分类，群分类
public class ClassModel {
    private int classid;
    private String className;
    private List<Object> childids = new ArrayList<>();

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Object> getChild() {
        return childids;
    }

    public void setChild(List<Object> childids) {
        this.childids = childids;
    }

    public void addChild(Object childid){
        childids.add(childid);
    }

    public Integer getChildIntegerId(int index){
        return Integer.parseInt(childids.get(index).toString());
    }
    public String getChildStringId(int index){
        return childids.get(index).toString();
    }
}
