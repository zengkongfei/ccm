package com.ccm.api.model.hotel.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class HotelCancelCreteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2213578456823797411L;

	private String hotelId; //酒店ID
	private String cancelIds;
	private String cancelPolicyCodes; //打包服务codes
	private String language;  //语言
	private List<String> cancelIdList;
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
	
	
}
