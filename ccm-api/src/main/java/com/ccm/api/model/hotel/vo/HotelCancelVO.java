package com.ccm.api.model.hotel.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ccm.api.model.hotel.HotelCancel;
import com.ccm.api.model.hotel.HotelCancelI18n;

public class HotelCancelVO extends HotelCancel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7301795293789669610L;
	
	private String  hotelCancelMId ;//主键
	private String  language;        //语言
	private String  policyName;      //规则名
	private String  description;     //描述
	private String  hotelCode; //酒店代码
	private String  cancelPolicyCode;// 取消代码
	private String  cancelIds; 
	private String  cancelPolicyCodes; //取消代码组
	private List<String> cancelIdList;
	private List<HotelCancelI18n> hotelCancelI18nList;
	
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getCancelPolicyCode() {
		return cancelPolicyCode;
	}
	public void setCancelPolicyCode(String cancelPolicyCode) {
		this.cancelPolicyCode = cancelPolicyCode;
	}
	public String getCancelIds() {
		return cancelIds;
	}
	public void setCancelIds(String cancelIds) {
		this.cancelIds = cancelIds;
	}
	public String getCancelPolicyCodes() {
		return cancelPolicyCodes;
	}
	public void setCancelPolicyCodes(String cancelPolicyCodes) {
		this.cancelPolicyCodes = cancelPolicyCodes;
	}
	public List<String> getCancelIdList() {
		if(this.cancelIdList!=null&&this.cancelIdList.size()>0)
			return this.cancelIdList;
		
		if(StringUtils.isNotBlank(this.cancelIds)){
			List<String> cancelIdList = new ArrayList<String>();
			String[] cancelIdArry = this.cancelIds.split(",");
			for (String cancelId : cancelIdArry) {
				cancelIdList.add(cancelId);
			}
			return cancelIdList;
		}
		return this.cancelIdList;
	}
	public void setCancelIdList(List<String> cancelIdList) {
		this.cancelIdList = cancelIdList;
	}
	public String getHotelCancelMId() {
		return hotelCancelMId;
	}
	public void setHotelCancelMId(String hotelCancelMId) {
		this.hotelCancelMId = hotelCancelMId;
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
	public List<HotelCancelI18n> getHotelCancelI18nList() {
		return hotelCancelI18nList;
	}
	public void setHotelCancelI18nList(List<HotelCancelI18n> hotelCancelI18nList) {
		this.hotelCancelI18nList = hotelCancelI18nList;
	} 

}
