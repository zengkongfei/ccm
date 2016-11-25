package com.ccm.api.model.common.vo;

import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class MenuCriteria extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6523638738307432554L;

	private List<String> userIds;
	private List<String> parentIds;
	private String language;
	
	public List<String> getParentIds() {
		return parentIds;
	}
	public void setParentIds(List<String> parentIds) {
		this.parentIds = parentIds;
	}
	public List<String> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	} 

}
