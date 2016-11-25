package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

public class HotelGuaranteeI18n extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -84665138507190708L;
	
	private String  hotelGuaranteeMId ;//主键
	private String 	hotelGuaranteeId  ;//序号
	private String  language;        //语言
	private String  policyName;      //规则名
	private String  description;     //描述
	public String getHotelGuaranteeMId() {
		return hotelGuaranteeMId;
	}
	public void setHotelGuaranteeMId(String hotelGuaranteeMId) {
		this.hotelGuaranteeMId = hotelGuaranteeMId;
	}
	public String getHotelGuaranteeId() {
		return hotelGuaranteeId;
	}
	public void setHotelGuaranteeId(String hotelGuaranteeId) {
		this.hotelGuaranteeId = hotelGuaranteeId;
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
	@Override
	public String toString() {
		return "HotelGuaranteeI18n [hotelGuaranteeMId=" + hotelGuaranteeMId
				+ ", hotelGuaranteeId=" + hotelGuaranteeId + ", language="
				+ language + ", policyName=" + policyName + ", description="
				+ description + "]";
	}
	
	
}
