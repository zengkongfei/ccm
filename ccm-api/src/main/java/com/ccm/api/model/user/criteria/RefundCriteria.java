package com.ccm.api.model.user.criteria;

import com.ccm.api.model.base.criteria.SearchCriteria;

/**
 * 退款查询条件类
 */
public class RefundCriteria extends SearchCriteria {

	private static final long serialVersionUID = -6080445635681508644L;

	private String userId;

	private String reason;

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
