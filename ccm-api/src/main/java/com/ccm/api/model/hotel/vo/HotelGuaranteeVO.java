package com.ccm.api.model.hotel.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ccm.api.model.hotel.HotelGuarantee;
import com.ccm.api.model.hotel.HotelGuaranteeI18n;

public class HotelGuaranteeVO extends HotelGuarantee{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7301795293789669610L;
	
	private String  hotelGuaranteeMId ;//主键
	private String  language;
	private String  policyName; //规则名称
	private String  description;//描述
	private String  hotelCode; //酒店代码
	private String  guaranteeCode;// 担保代码
	private String  guaranteeIds; 
	private String  guaranteeCodes; //担保代码组
	private List<String> guaranteeIdList;
	private List<HotelGuaranteeI18n> hotelGuaranteeI18nList;
	
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	
	
	public String getGuaranteeCode() {
		return guaranteeCode;
	}
	public void setGuaranteeCode(String guaranteeCode) {
		this.guaranteeCode = guaranteeCode;
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
	
	public String getHotelGuaranteeMId() {
		return hotelGuaranteeMId;
	}
	public void setHotelGuaranteeMId(String hotelGuaranteeMId) {
		this.hotelGuaranteeMId = hotelGuaranteeMId;
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
	public List<HotelGuaranteeI18n> getHotelGuaranteeI18nList() {
		return hotelGuaranteeI18nList;
	}
	public void setHotelGuaranteeI18nList(
			List<HotelGuaranteeI18n> hotelGuaranteeI18nList) {
		this.hotelGuaranteeI18nList = hotelGuaranteeI18nList;
	}

	
}
