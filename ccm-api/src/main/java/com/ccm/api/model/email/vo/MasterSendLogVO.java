/**
 * 
 */
package com.ccm.api.model.email.vo;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class MasterSendLogVO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String masterSendLogId;
	private String smsSendId;
	private String masterId;// 订单号
	private String hotelCode;
	private String hotelName;

	private String smsType; // 模板类型
	private String mobileNumber; // 订单状态
	private String smsContent; // 邮箱地址
	private String verifyCode; // 语言代码

	public String getMasterSendLogId() {
		return masterSendLogId;
	}

	public void setMasterSendLogId(String masterSendLogId) {
		this.masterSendLogId = masterSendLogId;
	}

	public String getSmsSendId() {
		return smsSendId;
	}

	public void setSmsSendId(String smsSendId) {
		this.smsSendId = smsSendId;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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

	@Override
	public String toString() {
		return "MasterSendLogVO [masterSendLogId=" + masterSendLogId + ", smsSendId=" + smsSendId + ", masterId=" + masterId + ", hotelCode=" + hotelCode + ", hotelName=" + hotelName + ", smsType=" + smsType + ", mobileNumber=" + mobileNumber + ", smsContent=" + smsContent + ", verifyCode=" + verifyCode + "]";
	}

}
