package com.ccm.api.model.constant;

public class MessageType {

	public static String PROFILE = "PROFILE";// 客户列表上传

	public static String RTAV = "RTAV";// 开关房
	public static String RAVL = "RAVL";// 房量
	public static String RATE = "RATE";// 房价
	public static String RESERVATION = "RESERVATION";// 订单
	public static String FINTRX = "FINTRX";//
	public static String ALLOTMENT = "ALLOTMENT";
	public static String INVENTORY = "INVENTORY"; // 老版本的Inventory
	public static String RESULT = "RESULT";// 外部系统
	public static String RAVR = "RAVR";// ravr 控制freeSell或产品开关

	// 淘宝接收订单类型
	public static String Create = "CREATE";// 创建订单
	public static String Cancel = "CANCEL";// 取消订单
	public static String Query = "QUERY";// 查询 订单

	// 发布到淘宝信息
	public static final String TB_HOTEL = "hotel";
	public static final String TB_ROOMTYPE = "roomtype";
	public static final String TB_RATEPLAN = "rateplan";
	public static final String TB_PRODUCT = "product";
}
