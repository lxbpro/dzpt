package com.gamecenter.gamecenter.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gamecenter.gamecenter.bean.Message;

public class ADataManage {
    private static ADataManage dataManage;

    public static ADataManage getInstance(){
        if(dataManage == null){
            synchronized (ADataManage.class){
                if(dataManage == null){
                    dataManage = new ADataManage();
                }
            }
        }
        return dataManage;
    }

    private UserModel currentUser;
    private FriendModel currentFriend;
    private GroupModel currentGroup;

    private List<ClassModel> friendClass = new ArrayList<>();           // 好友分组
    private Map<Integer, ClassModel> friendClassMap = new HashMap<>();    // 分组Map      <分组id， 分组对象>
    private Map<Integer, FriendModel> fridendMap = new HashMap<>();    // 好友  <好友id， 好友对象>

    private List<ClassModel> groupClass = new ArrayList<>();            // 群组分组
    private Map<Integer, ClassModel> groupClassMap = new HashMap<>(); // 分组Map      <分组id， 分组对象>
    private Map<String, GroupModel> groupMap = new HashMap<>();       // 群组   <群组id， 群组对象>

    private List<ClassModel> publicScenClass = new ArrayList<>();       // 公有想定分组
    private Map<Integer, ClassModel> publicScenClassMap = new HashMap<>();    // 分组Map      <分组id， 分组对象>
    private Map<Integer, ScenarioModel> publicScenMap = new HashMap<>();  // 公有想定     <想定id， 想定对象>

    private List<ClassModel> privateScenClass = new ArrayList<>();      // 私有想定分组
    private Map<Integer, ClassModel> privateScenClassMap = new HashMap<>();   // 分组Map      <分组id， 分组对象>
    private Map<Integer, ScenarioModel> privateScenMap = new HashMap<>();     // 私有想定     <想定id， 想定对象>

    private List<ClassModel> shareScenClass = new ArrayList<>();      // 共享想定分组
    private Map<Integer, ClassModel> shareScenClassMap = new HashMap<>();   // 分组Map      <分组id， 分组对象>
    private Map<Integer, ScenarioModel> shareScenMap = new HashMap<>();     // 共享想定     <想定id， 想定对象>

    private Map<String, List<Message>> unReadMsgListMap = new HashMap<>();     // 未读消息列表   <好友id, 消息对象>
    private Map<String, List<Message>> friendChatRecord = new HashMap<>();     // 好友(群组)聊天记录
    private Map<String, Message> msgPageShowMap = new HashMap<>();                  // 消息列表页面显示的消息


    // 添加分组
    public void addFriendClass(ClassModel fClass){
        friendClass.add(fClass);
        friendClassMap.put(fClass.getClassid(), fClass);
    }
    public void addGroupClass(ClassModel fClass){
        groupClass.add(fClass);
        groupClassMap.put(fClass.getClassid(), fClass);
    }
    public void addPublicScenClass(ClassModel fClass){
        publicScenClass.add(fClass);
        publicScenClassMap.put(fClass.getClassid(), fClass);
    }
    public void addPrivateScenClass(ClassModel fClass){
        privateScenClass.add(fClass);
        privateScenClassMap.put(fClass.getClassid(), fClass);
    }
    public void addShareScenClass(ClassModel fClass){
        shareScenClass.add(fClass);
        shareScenClassMap.put(fClass.getClassid(), fClass);
    }
    // 给分组添加child
    public void addFriendClassChild(int groupid, Object childid){
        friendClassMap.get(groupid).addChild(childid);
    }
    public void addGroupClassChild(int groupid, Object childid){
        groupClassMap.get(groupid).addChild(childid);
    }
    public void addPublicScenClassChild(int groupid, Object childid){
        publicScenClassMap.get(groupid).addChild(childid);
    }
    public void addPrivateScenClassChild(int groupid, Object childid){
        privateScenClassMap.get(groupid).addChild(childid);
    }
    public void addShareScenClassChild(int groupid, Object childid){
        shareScenClassMap.get(groupid).addChild(childid);
    }
    // 添加数据
    public void addFriendModel(FriendModel model){
        fridendMap.put(model.getUserid(),model);
    }
    public void addGroupModel(GroupModel model){
        groupMap.put(model.getGroupid(),model);
    }
    public void addPublicScenModel(ScenarioModel model){
        publicScenMap.put(model.getScenid(),model);
    }
    public void addPrivateScenModel(ScenarioModel model){
        privateScenMap.put(model.getScenid(),model);
    }
    public void addShareScenModel(ScenarioModel model){
        shareScenMap.put(model.getScenid(),model);
    }

    // 清空数据
    public void clearData(){
        clearFriendData();
        clearGroupData();
        clearPublicScenData();
        clearPrivateScenData();
        clearShareScenData();
    }
    public void clearFriendData(){
        if(friendClass.size() > 0){
            friendClass.clear();
        }
        if(friendClassMap.size() > 0){
            friendClassMap.clear();
        }
        ClassModel c = new ClassModel();
        c.setClassid(0);
        c.setClassName("我的好友");
        addFriendClass(c);
        if(fridendMap.size() > 0){
            fridendMap.clear();
        }
    }
    public void clearGroupData(){
        if(groupClass.size() > 0){
            groupClass.clear();
        }
        if(groupClassMap.size() > 0){
            groupClassMap.clear();
        }
        ClassModel c = new ClassModel();
        c.setClassid(0);
        c.setClassName("我的群组");
        addGroupClass(c);
        if(groupMap.size() > 0){
            groupMap.clear();
        }
    }
    public void clearPublicScenData(){
        if(publicScenClass.size() > 0){
            publicScenClass.clear();
        }
        if(publicScenClassMap.size() > 0){
            publicScenClassMap.clear();
        }
        if(publicScenMap.size() > 0){
            publicScenMap.clear();
        }
    }
    public void clearPrivateScenData(){
        if(privateScenClass.size() > 0){
            privateScenClass.clear();
        }
        if(privateScenClassMap.size() > 0){
            privateScenClassMap.clear();
        }
        ClassModel c = new ClassModel();
        c.setClassid(0);
        c.setClassName("我的想定");
        addPrivateScenClass(c);
        if(privateScenMap.size() > 0){
            privateScenMap.clear();
        }
    }
    public void clearShareScenData(){
        if(shareScenClass.size() > 0){
            shareScenClass.clear();
        }
        if(shareScenClassMap.size() > 0){
            shareScenClassMap.clear();
        }
        if(shareScenMap.size() > 0){
            shareScenMap.clear();
        }
    }

    // 清理未读信息数据
    public void clearUnReadMsgList(){
        if(unReadMsgListMap.size() > 0){
            unReadMsgListMap.clear();
        }
    }
    // 根据好友id清空未读消息
    public void clearUnReadMsgListByFriendId(String friendid){
        if (unReadMsgListMap.containsKey(friendid)) {
            unReadMsgListMap.get(friendid).clear();
        }
    }
    // 添加未读消息
    public void addUnReadMsgByFriend(Message msg){
        msgPageShowMap.put(msg.getSenderId(),msg);
        if(unReadMsgListMap.containsKey(msg.getSenderId())){
            unReadMsgListMap.get(msg.getSenderId()).add(msg);
        }else {
            List<Message> msglit = new ArrayList<>();
            msglit.add(msg);
            unReadMsgListMap.put(msg.getSenderId(),msglit);
        }
    }
    // 添加未读消息通过Id
    public void addUnReadMsgByFriendId(String id,Message msg){
        msgPageShowMap.put(id,msg);
        if(unReadMsgListMap.containsKey(id)){
            unReadMsgListMap.get(id).add(msg);
        }else {
            List<Message> msglit = new ArrayList<>();
            msglit.add(msg);
            unReadMsgListMap.put(id,msglit);
        }
    }
    // 获取未读消息
    public List<Message> getUnReadMsgByFriendId(String friendid){
        if (unReadMsgListMap.containsKey(friendid)) {
            return unReadMsgListMap.get(friendid);
        }
        return new ArrayList<>();
    }

    // 获取消息列表显示的内容
    public List<Message> getMsgPageShowList(){
        if(msgPageShowMap.isEmpty()){
            return new ArrayList<>();
        }
        List<Message> msglist = new ArrayList<Message>(msgPageShowMap.values());
        Collections.sort(msglist);
        return msglist;
    }

    // 添加/更新 消息列表
    public void putMsgPageShow(String id,Message msg){
        msgPageShowMap.put(id,msg);
    }



    // 清理内存中信息记录
    public void clearMsgRecordList(){
        if(friendChatRecord.size() > 0){
            friendChatRecord.clear();
        }
    }
    // 根据好友id清空消息记录
    public void clearMsgRecordByFriendId(Integer friendid){
        if (friendChatRecord.containsKey(friendid)) {
            friendChatRecord.get(friendid).clear();
        }
    }
    // 添加消息记录
    public void addMsgRecordByFriend(Message msg){
        if(friendChatRecord.containsKey(msg.getSenderId())){
            friendChatRecord.get(msg.getSenderId()).add(msg);
        }else {
            List<Message> msglit = new ArrayList<>();
            msglit.add(msg);
            friendChatRecord.put(msg.getSenderId(),msglit);
        }
    }
    // 根据好友id添加消息记录
    public void addMsgRecordByFriendId(String friendid ,Message msg){
        if(friendChatRecord.containsKey(friendid)){
            friendChatRecord.get(friendid).add(msg);
        }else {
            List<Message> msglit = new ArrayList<>();
            msglit.add(msg);
            friendChatRecord.put(friendid,msglit);
        }
    }
    // 获取消息记录
    public List<Message> loadMsgRecordByFriendId(String friendid){
        if (friendChatRecord.containsKey(friendid)) {
            return friendChatRecord.get(friendid);
        }
        return new ArrayList<>();
    }


    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    public FriendModel getCurrentFriend() {
        return currentFriend;
    }

    public void setCurrentFriend(FriendModel currentFriend) {
        this.currentFriend = currentFriend;
    }

    public GroupModel getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(GroupModel currentGroup) {
        this.currentGroup = currentGroup;
    }

    public List<ClassModel> getFriendClass() {
        return friendClass;
    }

    public void setFriendClass(List<ClassModel> friendClass) {
        this.friendClass = friendClass;
    }

    public Map<Integer, FriendModel> getFridendMap() {
        return fridendMap;
    }

    public void setFridendMap(Map<Integer, FriendModel> fridendMap) {
        this.fridendMap = fridendMap;
    }

    public List<ClassModel> getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(List<ClassModel> groupClass) {
        this.groupClass = groupClass;
    }

    public Map<String, GroupModel> getGroupMap() {
        return groupMap;
    }

    public void setGroupMap(Map<String, GroupModel> groupMap) {
        this.groupMap = groupMap;
    }

    public List<ClassModel> getPublicScenClass() {
        return publicScenClass;
    }

    public void setPublicScenClass(List<ClassModel> publicScenClass) {
        this.publicScenClass = publicScenClass;
    }

    public Map<Integer, ScenarioModel> getPublicScenMap() {
        return publicScenMap;
    }

    public void setPublicScenMap(Map<Integer, ScenarioModel> publicScenMap) {
        this.publicScenMap = publicScenMap;
    }

    public List<ClassModel> getPrivateScenClass() {
        return privateScenClass;
    }

    public void setPrivateScenClass(List<ClassModel> privateScenClass) {
        this.privateScenClass = privateScenClass;
    }

    public Map<Integer, ScenarioModel> getPrivateScenMap() {
        return privateScenMap;
    }

    public void setPrivateScenMap(Map<Integer, ScenarioModel> privateScenMap) {
        this.privateScenMap = privateScenMap;
    }

    public List<ClassModel> getShareScenClass() {
        return shareScenClass;
    }

    public void setShareScenClass(List<ClassModel> shareScenClass) {
        this.shareScenClass = shareScenClass;
    }

    public Map<Integer, ScenarioModel> getShareScenMap() {
        return shareScenMap;
    }

    public void setShareScenMap(Map<Integer, ScenarioModel> shareScenMap) {
        this.shareScenMap = shareScenMap;
    }
}
