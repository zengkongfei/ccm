package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

public class HotelCancelI18n extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3269855527400459103L;
	
	private String  hotelCancelMId ;//主键
	private String 	hotelCancelId  ;//序号
	private String  language;        //语言
	private String  policyName;      //规则名
	private String  description;     //描述
	public String getHotelCancelMId() {
		return hotelCancelMId;
	}
	public void setHotelCancelMId(String hotelCancelMId) {
		this.hotelCancelMId = hotelCancelMId;
	}
	public String getHotelCancelId() {
		return hotelCancelId;
	}
	public void setHotelCancelId(String hotelCancelId) {
		this.hotelCancelId = hotelCancelId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
