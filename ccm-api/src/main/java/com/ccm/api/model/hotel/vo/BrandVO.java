package com.ccm.api.model.hotel.vo;

import java.util.List;

import com.ccm.api.model.hotel.Brand;
import com.ccm.api.model.hotel.BrandI18n;

public class BrandVO extends Brand{
	
	private static final long serialVersionUID = -4188842477779132648L;
	
	private String brandMId;//品牌多语言主键
	private String language;//语言          
	private String brandName;//品牌名称 
	private String chainName;//集团名称 
	private List<BrandI18n> brandI18nList;
	
	public String getBrandMId() {
		return brandMId;
	}
	public void setBrandMId(String brandMId) {
		this.brandMId = brandMId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getChainName() {
		return chainName;
	}
	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
	
	
	public List<BrandI18n> getBrandI18nList() {
		return brandI18nList;
	}
	public void setBrandI18nList(List<BrandI18n> brandI18nList) {
		this.brandI18nList = brandI18nList;
	}
	@Override
	public String toString() {
		return "BrandVO [brandMId=" + brandMId + ", language=" + language
				+ ", brandName=" + brandName + ", chainName=" + chainName + "]";
	}
}
