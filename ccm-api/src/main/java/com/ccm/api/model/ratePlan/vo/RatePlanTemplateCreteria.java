package com.ccm.api.model.ratePlan.vo;


import com.ccm.api.model.base.criteria.SearchCriteria;

public class RatePlanTemplateCreteria extends SearchCriteria {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8863156737865153513L;
	
	private String ratePlanId;// 房价序号
	private String ratePlanTemplateId;
	private String ratePlanTemplateCode;// 房价模板代码
	private String ratePlanTemplateName;// 房价模板名称
	private String userId;// 模板创建者
	private String language;
	public String getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public String getRatePlanTemplateId() {
		return ratePlanTemplateId;
	}
	public void setRatePlanTemplateId(String ratePlanTemplateId) {
		this.ratePlanTemplateId = ratePlanTemplateId;
	}
	public String getRatePlanTemplateCode() {
		return ratePlanTemplateCode;
	}
	public void setRatePlanTemplateCode(String ratePlanTemplateCode) {
		this.ratePlanTemplateCode = ratePlanTemplateCode;
	}
	
	public String getRatePlanTemplateName() {
		return ratePlanTemplateName;
	}
	public void setRatePlanTemplateName(String ratePlanTemplateName) {
		this.ratePlanTemplateName = ratePlanTemplateName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "RatePlanTemplateCreteria [ratePlanId=" + ratePlanId
				+ ", ratePlanTemplateId=" + ratePlanTemplateId
				+ ", ratePlanTemplateCode=" + ratePlanTemplateCode
				+ ", ratePlanTemplateName=" + ratePlanTemplateName
				+ ", userId=" + userId + ", language=" + language + "]";
	}

	
}
