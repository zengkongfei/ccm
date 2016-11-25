package com.ccm.api.model.log.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class ChannelOrderCriteria extends SearchCriteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2154240821365608393L;
	
	private Date changedDate;
	private String changedStr;
	private String languageCode;
	private String hotelId;
	private String hotelCode;
	private String chartType;
	private String chartValue;
	private List<String> channelCodeList;
	
	public List<String> getChannelCodeList() {
		return channelCodeList;
	}
	public void setChannelCodeList(List<String> channelCodeList) {
		this.channelCodeList = channelCodeList;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getChartValue() {
		return chartValue;
	}
	public void setChartValue(String chartValue) {
		this.chartValue = chartValue;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	public String getChangedStr() {
		return changedStr;
	}
	public void setChangedStr(String changedStr) {
		this.changedStr = changedStr;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

}

	
	
