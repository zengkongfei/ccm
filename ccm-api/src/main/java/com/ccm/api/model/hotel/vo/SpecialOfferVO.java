package com.ccm.api.model.hotel.vo;

import java.util.List;

import com.ccm.api.model.hotel.SpecialOffer;
import com.ccm.api.model.hotel.SpecialOfferI18n;

public class SpecialOfferVO extends SpecialOffer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1702709386508989336L;
	
	private String specialOfferMId;
	
	private String language;
	
	private String summary;  //概要
	
	private String detail;   //详情
	
	private String hotelCode; //酒店代码
	
	//多语言列表
	private List<SpecialOfferI18n> specialOfferI18nList;

	public String getSpecialOfferMId() {
		return specialOfferMId;
	}

	public void setSpecialOfferMId(String specialOfferMId) {
		this.specialOfferMId = specialOfferMId;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public List<SpecialOfferI18n> getSpecialOfferI18nList() {
		return specialOfferI18nList;
	}

	public void setSpecialOfferI18nList(List<SpecialOfferI18n> specialOfferI18nList) {
		this.specialOfferI18nList = specialOfferI18nList;
	}
	
	
}
