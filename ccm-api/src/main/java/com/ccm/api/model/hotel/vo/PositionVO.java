package com.ccm.api.model.hotel.vo;

import java.util.List;

import com.ccm.api.model.hotel.Position;
import com.ccm.api.model.hotel.PositionI18n;

public class PositionVO extends Position{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4092894387886428501L;

	private String relativePositionMId;//序号     
	private String languageCode;//语言       
	private String name;//地标名称           
	private String description;//描述        
	private String pointName;//周边类型名称
	List<PositionI18n> positionI18nList;
	
	public String getRelativePositionMId() {
		return relativePositionMId;
	}
	public void setRelativePositionMId(String relativePositionMId) {
		this.relativePositionMId = relativePositionMId;
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
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	
	
	
	public List<PositionI18n> getPositionI18nList() {
		return positionI18nList;
	}
	public void setPositionI18nList(List<PositionI18n> positionI18nList) {
		this.positionI18nList = positionI18nList;
	}
	@Override
	public String toString() {
		return "PositionVO [relativePositionMId=" + relativePositionMId
				+ ", languageCode=" + languageCode + ", name=" + name
				+ ", description=" + description + ", pointName=" + pointName
				+ "]";
	}
}
