package com.ccm.api.model.hotel;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

public class SpecialOffer extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5948739091982097613L;
	
	private String specialOfferId;
	
	private String hotelId;
	
	private Date beginTime;
	
	private Date endTime;

	public String getSpecialOfferId() {
		return specialOfferId;
	}

	public void setSpecialOfferId(String specialOfferId) {
		this.specialOfferId = specialOfferId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
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
