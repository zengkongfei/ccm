package com.ccm.api.model.ratePlan.vo;


import java.util.Date;
import java.util.List;
import com.ccm.api.model.base.criteria.SearchCriteria;

public class SearchPriceValidCriteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1839723761875730680L;
	
	private String specialist;  //酒店维护专员
	private String hotelCode;   //酒店代码
	private Date startDate;     //开始日期
	private Date endDate;       //结束日期
	private List<String> hotelCodeList; //酒店code列表
	private boolean excelFlag = false;// 导出标志
	private String language;
	
	
	public String getSpecialist() {
		return specialist;
	}
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<String> getHotelCodeList() {
		return hotelCodeList;
	}
	public void setHotelCodeList(List<String> hotelCodeList) {
		this.hotelCodeList = hotelCodeList;
	}
	public boolean isExcelFlag() {
		return excelFlag;
	}
	public void setExcelFlag(boolean excelFlag) {
		this.excelFlag = excelFlag;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "SearchPriceValidCriteria [specialist=" + specialist
				+ ", hotelCode=" + hotelCode + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", hotelCodeList=" + hotelCodeList
				+ ", excelFlag=" + excelFlag + ", language=" + language + "]";
	}
}
