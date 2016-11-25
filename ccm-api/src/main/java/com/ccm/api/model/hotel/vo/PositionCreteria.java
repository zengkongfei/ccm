package com.ccm.api.model.hotel.vo;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class PositionCreteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2333639397145839274L;
	
	private Integer pointCode;//周边类型 
	private String name;//地标名称
	private String languageCode; //语言
	private String hotelId;
	
	public Integer getPointCode() {
		return pointCode;
	}
	public void setPointCode(Integer pointCode) {
		this.pointCode = pointCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	@Override
	public String toString() {
		return "PositionCreteria [pointCode=" + pointCode + ", name=" + name
				+ "]";
	}
}
