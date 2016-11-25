package com.ccm.api.model.hotel.vo;

import java.util.Date;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class SpecialOfferCreteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8427316203453569140L;

	private String hotelId;
	
	private String hotelCode;
	
	private String language; //语言
	
	private String summary;  //概要
	
	private Date beginTime;  //开始时间
	
	private Date endTime;    //结束时间
	

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
	
	
	
}
