package com.ccm.api.model.constant;

import java.util.HashMap;
import java.util.Map;

public class OrderStatus {
	public static String RESERVED  = "RESERVED";//RESERVED 
	public static String CHECKIN = "CHECKIN";// 已入住
	public static String CHECKOUT = "CHECKOUT";// 已离店
	public static String ADD = "ADD";// 预订(TB表示NEW)
	public static String CANCEL = "CANCEL";// 已取消
	public static String EDIT = "EDIT";// 已修改
	public static String NOSHOW = "NOSHOW";// 应到未到

	public static String HARDCANCEL = "HARDCANCEL";// 硬取消

	public static String FAIL = "FAILED";// 失败-TB使用

	public static String PAID = "PAID";// 支付状态，支付成功

	private static Map<String, String> orderStatus;

	/***
	 * 供报表查询使用
	 */
	public static Map<String, String> getOrderProductStatusMap() {
		if (orderStatus == null) {
			orderStatus = new HashMap<String, String>();
			orderStatus.put(ADD, ADD);
			orderStatus.put(EDIT, EDIT);
			orderStatus.put(CHECKIN, CHECKIN);
			orderStatus.put(CHECKOUT, CHECKOUT);
			orderStatus.put(CANCEL, CANCEL);
			orderStatus.put(HARDCANCEL, HARDCANCEL);
			orderStatus.put(NOSHOW, NOSHOW);
		}
		return orderStatus;
	}
}
