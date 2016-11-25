package com.ccm.api.dao.pms.utils;

public class LogUtils {

	public static void writeLog(String type, Exception e) {
		System.err.println("错误类型：" + type);
		e.printStackTrace();
	}

	public static void writeLog(String type, String msg,  Exception e) {
		System.err.println("错误类型：" + type);
		System.err.println("错误消息：" + msg);
		e.printStackTrace();
	}
}
