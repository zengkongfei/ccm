package com.ccm.api.model.shijicare.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class ShijicareCriteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7263735083602630308L;
	private String status;//shijicare 类型
	private Date beginDate;//开始时间
	private Date endDate;//结束时间
	private List<String> hotelCodeList;//酒店code
	private boolean excelFlag = false;// 导出标志
	private String problemType;
	private String caseStatus;//新建成功/新建失败/关闭成功/关闭失败  对应状态分别是 1，2，3，4
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isExcelFlag() {
		return excelFlag;
	}
	public void setExcelFlag(boolean excelFlag) {
		this.excelFlag = excelFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getHotelCodeList() {
		return hotelCodeList;
	}
	public void setHotelCodeList(List<String> hotelCodeList) {
		this.hotelCodeList = hotelCodeList;
	}
	public String getProblemType() {
		return problemType;
	}
	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	@Override
	public String toString() {
		return "ShijicareCriteria [status=" + status + ", beginDate=" + beginDate + ", endDate=" + endDate + ", hotelCodeList=" + hotelCodeList + ", excelFlag=" + excelFlag + ", problemType=" + problemType + ", caseStatus=" + caseStatus + "]";
	}
	
	

}
