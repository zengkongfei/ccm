package com.ccm.api.model.log.vo;

import java.util.Date;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class SendOTALogCriteria extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6334164876791034328L;

	private String sendOTALogId;//PK

	private String hotelCode;// 酒店编码

	private String messageType;// 消息类型,参考MessageType

	private String action;// 处理类型,参考MfReservationAction

	private String status;// 状态 status就是 发送状态Transmit Status

	private String message;// 消息内容

	private String chainCode;//渠道代码
	
	private String oxiTrxId;//oxiMsgId
	private String transformStatus;//转换状态
	
	private String comments;//备注
	private Date createdTime;//创建日期
	private Date lastModifyTime;//更新日期
	
	private String channelCode;//渠道代码
	private String ratePlanCode;
	private String roomTypeCode;
	private Date startDate;//block 开始时间
	
	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getTransformStatus() {
		return transformStatus;
	}

	public void setTransformStatus(String transformStatus) {
		this.transformStatus = transformStatus;
	}


	public String getOxiTrxId() {
		return oxiTrxId;
	}

	public void setOxiTrxId(String oxiTrxId) {
		this.oxiTrxId = oxiTrxId;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getSendOTALogId() {
		return sendOTALogId;
	}

	public void setSendOTALogId(String sendOTALogId) {
		this.sendOTALogId = sendOTALogId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	private String tableName = "sendOTAlog";
}
