package com.ccm.api.model.rsvtype.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class SearchRentCriteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5604504717298693166L;
	
	private String specialist;  //酒店维护专员
	private String hotelCode;   //酒店代码
	private Date startDate;     //开始日期
	private Date endDate;       //结束日期
	private BigDecimal rentRate=new BigDecimal(0); //出租率 默认为0
	private List<String> hotelCodeList; //酒店code列表
	private boolean excelFlag = false;// 导出标志
	
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
	public BigDecimal getRentRate() {
		return rentRate;
	}
	public void setRentRate(BigDecimal rentRate) {
		this.rentRate = rentRate;
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
	@Override
	public String toString() {
		return "SearchRentCriteria [specialist=" + specialist + ", hotelCode="
				+ hotelCode + ", startDate=" + startDate + ", endDate="
				+ endDate + ", rentRate=" + rentRate + ", hotelCodeList="
				+ hotelCodeList + ", excelFlag=" + excelFlag + "]";
	}
	
}
