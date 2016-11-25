package com.ccm.api.model.order;

import java.io.Serializable;
import java.util.Date;

public class OrderEmailConfirm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6747158014776523018L;
	
	private String smsSendId; 
	private String channelCode;
	private String hotelCode;
	private String masterId;
	private String sta;
	private Date sendTime;
	private String emailAddress;
	private String resultCode;
	private Integer sendCount;
	private String source;
	private String smsType;
	private String smsContent;
	
	
	public String getSmsSendId() {
		return smsSendId;
	}
	public void setSmsSendId(String smsSendId) {
		this.smsSendId = smsSendId;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public Integer getSendCount() {
		return sendCount;
	}
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	
	
	

}
