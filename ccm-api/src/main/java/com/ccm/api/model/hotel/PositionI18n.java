package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 地标(多语言)
 * @author carr
 *
 */
public class PositionI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -41029266873640114L;

	private String relativePositionMId;//序号
	private String positionId;//地标序号     
	private String languageCode;//语言       
	private String name;//地标名称           
	private String description;//描述        
	
	public String getRelativePositionMId() {
		return relativePositionMId;
	}
	public void setRelativePositionMId(String relativePositionMId) {
		this.relativePositionMId = relativePositionMId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "PositionI18n [relativePositionMId=" + relativePositionMId
				+ ", positionId=" + positionId + ", languageCode="
				+ languageCode + ", name=" + name + ", description="
				+ description + "]";
	}
}
