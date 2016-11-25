package com.ccm.api.log.model;

import com.ccm.api.model.base.BaseObject;

public class LogDetail extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -183033595592988485L;

	private String logDetailId;// 日志明细ID
	private String logId;// 日志ID
	private String className;// 类名
	private String attributeName;// 属性名
	private String primaryKey;// 业务主键
	private String newValue;// 新值
	private String oldValue;// 旧值
	private String urlId;

	public String getLogDetailId() {
		return logDetailId;
	}

	public void setLogDetailId(String logDetailId) {
		this.logDetailId = logDetailId;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	@Override
	public String toString() {
		return "LogDetail [logDetailId=" + logDetailId + ", logId=" + logId + ", className=" + className + ", attributeName=" + attributeName + ", primaryKey=" + primaryKey + ", newValue=" + newValue + ", oldValue=" + oldValue + ", urlId=" + urlId + "]";
	}

}
