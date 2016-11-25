package com.ccm.api.model.sms;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 短信发送记录表
 */
public class SmsSend extends BaseObject {

	private static final long serialVersionUID = -3055216702676545541L;

	private String smsSendId;

	private String smsType; // 模板类型

	private String mobileNumber; // 发送手机,hotelcode

	private String smsContent; // 邮件地址

	private String verifyCode; // 注册时候输入验证码

	private String bizId; // 注册时候输入验证码

	private Date sendTime; // 发送时间

	private String resultCode; // 结果代码，发送邮件确认函共有4个状态值：0-初始状态未发送;1-发送成功;2-发送失败;3-淘宝新建订单未发送；4-邮箱地址不通

	private String resultMsg; // 结果信息
	
	private String source;//信息来源

	private Integer sec;// 几秒内，目前用于发送手机验证码功能

	private String contentType;//信息类型
	
	private String bodycontent;//信息内容
	
	private String title;//subject
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getBodycontent() {
		return bodycontent;
	}

	public void setBodycontent(String bodycontent) {
		this.bodycontent = bodycontent;
	}

	public String getSmsSendId() {
		return smsSendId;
	}

	public void setSmsSendId(String smsSendId) {
		this.smsSendId = smsSendId;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Integer getSec() {
		return sec;
	}

	public void setSec(Integer sec) {
		this.sec = sec;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "SmsSend [smsSendId=" + smsSendId + ", smsType=" + smsType
				+ ", mobileNumber=" + mobileNumber + ", smsContent="
				+ smsContent + ", verifyCode=" + verifyCode + ", bizId="
				+ bizId + ", sendTime=" + sendTime + ", resultCode="
				+ resultCode + ", resultMsg=" + resultMsg + ", source="
				+ source + ", sec=" + sec + "]";
	}


}
