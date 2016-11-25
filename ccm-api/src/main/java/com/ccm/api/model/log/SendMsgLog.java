/**
 * 
 */
package com.ccm.api.model.log;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class SendMsgLog extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2372448250975857781L;

	private String sendMsgLogId;

	private String interfaceId;// 接口ID

	private String hotelCode;// 酒店编码

	private String messageType;// 消息类型,参考MessageType

	private String msgId;// 系统产生的消息编号

	private String linkedMsgId;// 关联消息编号

	private String action;// 处理类型,参考MfReservationAction

	private String orderId;// 系统订单号

	private String status;// 状态

	private String message;// 消息内容

	// 用于查询
	private String byStatus;// 状态集

	private String tableName = "sendmsglog";

	public String getSendMsgLogId() {
		return sendMsgLogId;
	}

	public void setSendMsgLogId(String sendMsgLogId) {
		this.sendMsgLogId = sendMsgLogId;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHotelCode() {
		if (hotelCode == null) {
			return "";
		}
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getLinkedMsgId() {
		return linkedMsgId;
	}

	public void setLinkedMsgId(String linkedMsgId) {
		this.linkedMsgId = linkedMsgId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getByStatus() {
		return byStatus;
	}

	public void setByStatus(String byStatus) {
		this.byStatus = byStatus;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
