package com.ccm.api.log.model;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

public class LogInfo extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7051405624284321896L;

	private String logId;// ID
	private String operaterId;// 操作员ID
	private String hotelId;// 酒店ID
	private Date lastLoginTime;// 最后登录时间
	private Date operateTime;// 操作日期
	private String businessId;// 业务ID
	private String functionId;// 功能ID
	private String operateType;// 操作类型，取值"1：新增,2：修改,3：删除"
	private String primaryValue;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPrimaryValue() {
		return primaryValue;
	}

	public void setPrimaryValue(String primaryValue) {
		this.primaryValue = primaryValue;
	}

	@Override
	public String toString() {
		return "LogInfo [logId=" + logId + ", operaterId=" + operaterId + ", hotelId=" + hotelId + ", lastLoginTime=" + lastLoginTime + ", operateTime=" + operateTime + ", businessId=" + businessId + ", functionId=" + functionId + ", operateType=" + operateType + ", primaryValue=" + primaryValue + "]";
	}

}
