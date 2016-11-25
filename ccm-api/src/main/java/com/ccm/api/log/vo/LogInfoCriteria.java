package com.ccm.api.log.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class LogInfoCriteria extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1499457396232392829L;

	private Date operateTimeBegin;// 操作日期开始
	private Date operateTimeEnd;// 操作日期结束
	private Integer operateType;// 操作类型，取值"1：新增,2：修改,3：删除"
	private String language;
	private List<String> hotelIds;
	
	private String operaterId;// 操作员ID
	private String businessId;// 业务ID 一级菜单
	private String functionId;// 功能ID 二级菜单
	private List<String> operaterIds;
	private List<String> businessIds;
	private List<String> functionIds;

	
	public List<String> getOperaterIds() {
		return operaterIds;
	}

	public void setOperaterIds(List<String> operaterIds) {
		this.operaterIds = operaterIds;
	}

	public List<String> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<String> businessIds) {
		this.businessIds = businessIds;
	}

	public List<String> getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(List<String> functionIds) {
		this.functionIds = functionIds;
	}

	public String getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}

	public Date getOperateTimeBegin() {
		return operateTimeBegin;
	}

	public void setOperateTimeBegin(Date operateTimeBegin) {
		this.operateTimeBegin = operateTimeBegin;
	}

	public Date getOperateTimeEnd() {
		return operateTimeEnd;
	}

	public void setOperateTimeEnd(Date operateTimeEnd) {
		this.operateTimeEnd = operateTimeEnd;
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

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getHotelIds() {
		return hotelIds;
	}

	public void setHotelIds(List<String> hotelIds) {
		this.hotelIds = hotelIds;
	}

	@Override
	public String toString() {
		return "LogInfoCriteria [operaterId=" + operaterId + ", operateTimeBegin=" + operateTimeBegin + ", operateTimeEnd=" + operateTimeEnd + ", businessId=" + businessId + ", functionId=" + functionId + ", operateType=" + operateType + "]";
	}
}
