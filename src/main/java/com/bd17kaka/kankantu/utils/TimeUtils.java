package com.bd17kaka.kankantu.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtils {
	
	public static String getCurTime() {
		// 获取时间戳
		Date date = new Date();
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = formater.format(date);
		
		return time;
	}
}
