package com.ccm.api.model.hotel.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class HotelGuaranteeCreteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2213578456823797411L;

	private String hotelId; //酒店ID
	private String guaranteeIds;
	private String guaranteeCodes; //担保codes
	private String language;  //语言
	private List<String> guaranteeIdList;
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getGuaranteeIds() {
		return guaranteeIds;
	}
	public void setGuaranteeIds(String guaranteeIds) {
		this.guaranteeIds = guaranteeIds;
	}
	
	public String getGuaranteeCodes() {
		return guaranteeCodes;
	}
	public void setGuaranteeCodes(String guaranteeCodes) {
		this.guaranteeCodes = guaranteeCodes;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<String> getGuaranteeIdList() {
		if(this.guaranteeIdList!=null&&this.guaranteeIdList.size()>0)
			return this.guaranteeIdList;
		
		if(StringUtils.isNotBlank(this.guaranteeIds)){
			List<String> guaranteeList = new ArrayList<String>();
			String[] guaranteeIdArry = this.guaranteeIds.split(",");
			for (String guaranteeId : guaranteeIdArry) {
				guaranteeList.add(guaranteeId);
			}
			return guaranteeList;
		}
		return this.guaranteeIdList;
	}
	public void setGuaranteeIdList(List<String> guaranteeIdList) {
		this.guaranteeIdList = guaranteeIdList;
	}
	
	
}
