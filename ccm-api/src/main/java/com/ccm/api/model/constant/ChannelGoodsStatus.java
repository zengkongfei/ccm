package com.ccm.api.model.constant;

public class ChannelGoodsStatus {

	public static String DEFAULT = "0";// 默认值-未发布
	public static String OFF = "1";// 关闭
	public static String publish = "2";// 发布

	public static String process = "5";// 发布进行中
	public static String fail = "6";// 发布失败

	// 以下两个状态是计算出来的，不是存在数据库中的
	public static String synch = "3";// 同步
	public static String asynch = "4";// 不同步

}
