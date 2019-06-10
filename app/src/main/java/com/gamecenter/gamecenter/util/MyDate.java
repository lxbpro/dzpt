package com.gamecenter.gamecenter.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyDate {
	public static String getDateCN() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		String date = format.format(new Date(System.currentTimeMillis()));
		return date;// 2012年10月03日 23:41:31
	}

	public static String getDateEN() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		String date1 = format1.format(new Date(System.currentTimeMillis()));
		return date1;// 2012-10-03 23:41:31
	}
	 public  int CompareDate(String DATE1, String DATE2) {
	        //小时和毫秒的关系：2*60*60*1000
	        DateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > (dt2.getTime())) {
	                return 1;
	            } else if (dt1.getTime() < (dt2.getTime())) {
	                return -1;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	
}
