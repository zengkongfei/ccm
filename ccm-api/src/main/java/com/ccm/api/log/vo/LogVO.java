package com.ccm.api.log.vo;

import java.util.List;

import com.ccm.api.log.model.LogInfo;

public class LogVO extends LogInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5789000246187731791L;
	
	private List<String> changeList;//实体字段更新记录,如："rateCode=新值:rateCode=旧值"
	private String primaryKey;//业务主键
	private String entityName;//实体对象名称
	private String operaterName;//操作员名称
	private String hotelName;//酒店名称
	private String businessName;//业务名称
	private String functionName;//功能名称
	
	public List<String> getChangeList() {
		return changeList;
	}
	public void setChangeList(List<String> changeList) {
		this.changeList = changeList;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getOperaterName() {
		return operaterName;
	}
	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	@Override
	public String toString() {
		return "LogVO [changeList=" + changeList + ", primaryKey=" + primaryKey
				+ ", entityName=" + entityName + ", operaterName="
				+ operaterName + ", hotelName=" + hotelName + ", businessName="
				+ businessName + ", functionName=" + functionName
				+ "]";
	}
}
