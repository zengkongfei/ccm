package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 品牌（多语言）
 * @author carr
 *
 */
public class BrandI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2300501568545739257L;

	private String brandMId;//品牌多语言主键
	private String brandId;//品牌主键       
	private String language;//语言          
	private String brandName;//品牌名称     
	
	public String getBrandMId() {
		return brandMId;
	}
	public void setBrandMId(String brandMId) {
		this.brandMId = brandMId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
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
	
	@Override
	public String toString() {
		return "BrandI18n [brandMId=" + brandMId + ", brandId=" + brandId
				+ ", language=" + language + ", brandName=" + brandName + "]";
	}
}
