package com.ccm.api.model.fax;

import java.util.Date;

/**
 * 传真
 * @author chay
 *
 */
public class FaxSend {
	private static final long serialVersionUID = -3055216702676545541L;

	private String faxSendId;

	private String faxType; // 模板类型

	private String faxNumber; // 传真号码

	private String faxContent; // 内容

	private String verifyCode; // 语言代码

	private String bizId; // 订单id
	private String msgId; // 发送传真的消息id

	private Date sendTime; // 发送时间

	private String resultCode; // 结果状态代码

	private String resultMsg; // 结果信息
	
	private String faxStatus;// fax 在接口的状态
	private int number;// 重试次数
	private String faxMsg;//fax 在接口的描述

	public String getFaxSendId() {
		return faxSendId;
	}

	public void setFaxSendId(String faxSendId) {
		this.faxSendId = faxSendId;
	}

	public String getFaxType() {
		return faxType;
	}

	public void setFaxType(String faxType) {
		this.faxType = faxType;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getFaxContent() {
		return faxContent;
	}

	public void setFaxContent(String faxContent) {
		this.faxContent = faxContent;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFaxStatus() {
		return faxStatus;
	}

	public void setFaxStatus(String faxStatus) {
		this.faxStatus = faxStatus;
	}

	public String getFaxMsg() {
		return faxMsg;
	}

	public void setFaxMsg(String faxMsg) {
		this.faxMsg = faxMsg;
	}

	@Override
	public String toString() {
		return "FaxSend [faxSendId=" + faxSendId + ", faxType=" + faxType + ", faxNumber=" + faxNumber + ", faxContent=" + faxContent + ", verifyCode=" + verifyCode + ", bizId=" + bizId + ", msgId=" + msgId + ", sendTime=" + sendTime + ", resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", faxStatus=" + faxStatus + ", number=" + number + ", faxMsg=" + faxMsg + "]";
	}

	
	
}
