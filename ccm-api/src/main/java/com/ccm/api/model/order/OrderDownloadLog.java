package com.ccm.api.model.order;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

public class OrderDownloadLog extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -489526148271779355L;

	private String loadLogId;
	
	private String userId;
	
	private Date lastLoadTime;

	public String getLoadLogId() {
		return loadLogId;
	}

	public void setLoadLogId(String loadLogId) {
		this.loadLogId = loadLogId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLastLoadTime() {
		return lastLoadTime;
	}

	public void setLastLoadTime(Date lastLoadTime) {
		this.lastLoadTime = lastLoadTime;
	}

	@Override
	public String toString() {
		return "OrderDownloadLog [lastLoadTime=" + lastLoadTime
				+ ", loadLogId=" + loadLogId + ", userId=" + userId + "]";
	}
	
	
}
