package com.gamecenter.gamecenter.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Administrator
 *
 */

/**
 * @author Administrator
 *
 */
public class SharePreferenceUtil {
	private SharedPreferences sp;//获得一个SharePreferences对象
	private SharedPreferences.Editor editor;//获得一个编辑器对象
	
	public SharePreferenceUtil(Context context, String File){
		sp = context.getSharedPreferences(File, context.MODE_PRIVATE);//私有的访问权限
		editor = sp.edit();
	}

	/**存储用户id
	 * @param id
	 */
	public void setUserid(int id){
		editor.putInt("userid", id);
		editor.commit();//提交
	}

	/**获取用户id
	 * @return id
	 */
	public int getUserid(){
		return sp.getInt("userid", 0);
	}

	/**存储用户密码
	 * @param password
	 */
	public void setUserpassword(String password){
		editor.putString("password", password);
		editor.commit();//提交
	}
	
	
	/**获取用户的密码
	 * @return
	 */
	public String getUserpassword(){
		return sp.getString("password", "");//获取用户的密码，如果为空则没有获取到
	}
	
	/**存储账号
	 * @param account
	 */
	public void setUserAccount(String account){
		editor.putString("account", account);
		editor.commit();
	}
	
	/**获得account
	 * @return
	 */
	public String getUserAccount(){
		return sp.getString("account", "");
	}
	
	/**存储用户昵称
	 * @param userName
	 */
	public void setUserName(String userName){
		editor.putString("username", userName);
		editor.commit();
	}
	
	/**获得用户昵称
	 * @return
	 */
	public String getUserName(){
		return sp.getString("username", "");
	}
	
	/**存储用户头像id
	 * @param imageid
	 */
	public void setUserHeadPortrait(int imageid){
		editor.putInt("userheadportrait", imageid);
		editor.commit();
	}
	
	/**获取用户头像id
	 * @return
	 */
	public int getUserHeadPortrait(){
		return sp.getInt("userheadportrait", 0);
	}
	
	/**存储是否第一次运行本应用标记
	 * @param isFirst
	 */
	public void setIsFirst(Boolean isFirst){
		editor.putBoolean("isFirst", isFirst);
		editor.commit();
	}
	
	/**获取是否第一次运行本应用标记
	 * @return
	 */
	public Boolean getIsFirst(){
		return sp.getBoolean("isFirst", true);
	}
}
