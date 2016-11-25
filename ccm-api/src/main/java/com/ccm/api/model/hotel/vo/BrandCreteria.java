package com.ccm.api.model.hotel.vo;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class BrandCreteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6133314816660938916L;
	
	private String chainId;//集团序号     
	private String brandCode;//品牌代码
	private String brandName;//品牌名称
	private String language; //语言
	
	public String getChainId() {
		return chainId;
	}
	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "BrandCreteria [chainId=" + chainId + ", brandCode=" + brandCode
				+ ", brandName=" + brandName + "]";
	}
}
