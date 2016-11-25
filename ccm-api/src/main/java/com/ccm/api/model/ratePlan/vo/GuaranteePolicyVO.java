package com.ccm.api.model.ratePlan.vo;

import java.util.List;

import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.GuaranteePolicyI18n;

public class GuaranteePolicyVO extends GuaranteePolicy{

	private static final long serialVersionUID = 8621505707000796844L;
	
	private String guaranteeMId;//序号
	private String language;//语言
	private String policyName;//规则名称
	private String description;//描述
	private String hotelName;//酒店名称
	private String holdTimeStr;//保留时间
	private String validTimeStr;//几点前有效
	List<GuaranteePolicyI18n> guaranteePolicyI18nList;
	
	public String getGuaranteeMId() {
		return guaranteeMId;
	}
	public void setGuaranteeMId(String guaranteeMId) {
		this.guaranteeMId = guaranteeMId;
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
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHoldTimeStr() {
		return holdTimeStr;
	}
	public void setHoldTimeStr(String holdTimeStr) {
		this.holdTimeStr = holdTimeStr;
	}
	public String getValidTimeStr() {
		return validTimeStr;
	}
	public void setValidTimeStr(String validTimeStr) {
		this.validTimeStr = validTimeStr;
	}
	
	public List<GuaranteePolicyI18n> getGuaranteePolicyI18nList() {
		return guaranteePolicyI18nList;
	}
	public void setGuaranteePolicyI18nList(
			List<GuaranteePolicyI18n> guaranteePolicyI18nList) {
		this.guaranteePolicyI18nList = guaranteePolicyI18nList;
	}
	@Override
	public String toString() {
		return "GuaranteePolicyVO [guaranteeMId=" + guaranteeMId
				+ ", language=" + language + ", policyName=" + policyName
				+ ", description=" + description + ", hotelName=" + hotelName
				+ ", holdTimeStr=" + holdTimeStr + ", validTimeStr="
				+ validTimeStr + "]";
	}
}
