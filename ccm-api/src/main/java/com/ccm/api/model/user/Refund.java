/**
 * 
 */
package com.ccm.api.model.user;

/**
 * @author Jenny
 * 
 */
public class Refund {

	private String refundId;

	private String userId;

	private String reason;

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
