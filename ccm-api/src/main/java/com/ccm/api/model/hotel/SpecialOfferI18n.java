package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

public class SpecialOfferI18n extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1043282924342954404L;

	private String specialOfferMId;
	
	private String specialOfferId;
	
	private String language;
	
	private String summary;
	
	private String detail;

	public String getSpecialOfferMId() {
		return specialOfferMId;
	}

	public void setSpecialOfferMId(String specialOfferMId) {
		this.specialOfferMId = specialOfferMId;
	}

	public String getSpecialOfferId() {
		return specialOfferId;
	}

	public void setSpecialOfferId(String specialOfferId) {
		this.specialOfferId = specialOfferId;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
