/**
 * 
 */
package com.ccm.api.model.common;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class PessimisticLock extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4373848068397836768L;
	private String bizId;
	private String bizType;
	private String orderId;
	private String hotelCode;
	private String charsetName;
	private String transactionId;

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getCharsetName() {
		return charsetName;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "PessimisticLock [bizId=" + bizId + ", bizType=" + bizType + ", orderId=" + orderId + ", hotelCode=" + hotelCode + ", charsetName=" + charsetName + ", transactionId=" + transactionId + "]";
	}

}
