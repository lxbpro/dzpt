package com.gamecenter.gamecenter.model;

import com.gamecenter.gamecenter.activity.R;

public class ScenarioModel {
    private Integer scenid;         // 想定id
    private String scenname;        // 想定名称
    private Integer classid;        // 想定分组id
    private Boolean ispublic;       // 是否为公有
    private String comments;        // 想定描述
    private String master;          // 作者
    private int imgid = R.drawable.ic_launcher;

    public Integer getScenid() {
        return scenid;
    }

    public void setScenid(Integer scenid) {
        this.scenid = scenid;
    }

    public String getScenname() {
        return scenname;
    }

    public void setScenname(String scenname) {
        this.scenname = scenname;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Boolean getIspublic() {
        return ispublic;
    }

    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
