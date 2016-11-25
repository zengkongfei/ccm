/**
 * 
 */
package com.ccm.api.model.log;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class ReceiveMsgLog extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4040166497815621385L;

	private String receiveMsgLogId;

	private String interfaceId;// 接口ID

	private String hotelCode;// 酒店编码

	private String messageType;// 消息类型,参考MessageType

	private String beginMsgId;// 开始交易时产生的编号

	private String extMsgId;// 发送者的消息编号

	private String extOrderId;// 外部系统订单号

	private String status;// 状态

	private String processStatus;// 处理状态

	private Date processTime;// 处理时间

	private String message;// 消息内容

	private String tableName = "receivemsglog";

	public String getReceiveMsgLogId() {
		return receiveMsgLogId;
	}

	public void setReceiveMsgLogId(String receiveMsgLogId) {
		this.receiveMsgLogId = receiveMsgLogId;
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

	public String getBeginMsgId() {
		return beginMsgId;
	}

	public void setBeginMsgId(String beginMsgId) {
		this.beginMsgId = beginMsgId;
	}

	public String getExtMsgId() {
		return extMsgId;
	}

	public void setExtMsgId(String extMsgId) {
		this.extMsgId = extMsgId;
	}

	public String getExtOrderId() {
		return extOrderId;
	}

	public void setExtOrderId(String extOrderId) {
		this.extOrderId = extOrderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
