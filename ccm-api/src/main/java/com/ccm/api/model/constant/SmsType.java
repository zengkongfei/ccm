/**
 * 
 */
package com.ccm.api.model.constant;

/**
 * @author Jenny
 * 
 */
public class SmsType {

	public static final String HAS_NEW_ORDER = "HasNewOrder";// 有新的订单,调用发货时发送短信

	/**
	 * 新建预定
	 */
	public static final String SMS_TYPE_NEWRESERVATION = "NewReservation";

	/**
	 * 修改预定
	 */
	public static final String SMS_TYPE_EDITRESERVATION = "EditReservation";

	/**
	 * 取消预定
	 */
	public static final String SMS_TYPE_CANCELRESERVATION = "CancelReservation";
	
	/**
	 * 接口断开提醒
	 */
	public static final String SMS_TYPE_SMSREMIND = "SMSREMIND";

	// 发送EMail
	public static final String SMS_TYPE_EMAIL = "email";
	public static final String SMS_TYPE_EFAX = "efax";
	public static final String SMS_TYPE_SMS = "sms";
	public static final String SMS_TYPE_FORGETPASSWORD_EMAIL = "email";
	
	/**
	 * 消息来源，来源于接口监控
	 */
	public static String SMS_SOURCE_EMAIL_INGERFACE="INGERFACE";
	/**
	 * 消息来源，来源于订单监控
	 */
	public static String SMS_SOURCE_EMAIL_MASTER="MASTER";

}
