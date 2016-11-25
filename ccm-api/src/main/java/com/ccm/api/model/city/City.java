package com.ccm.api.model.city;

import com.ccm.api.model.base.BaseObject;

/**
 * 城市/区域
 * @author chay.huang
 *
 */
public class City extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2083560522660934455L;
	private int id;
	private String cityName;//城市名称
	private String cityCode;//城市代码
	private String language;
	private int parentId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + ", cityCode="
				+ cityCode + ", language=" + language + ", parentId="
				+ parentId + "]";
	}
	
}
