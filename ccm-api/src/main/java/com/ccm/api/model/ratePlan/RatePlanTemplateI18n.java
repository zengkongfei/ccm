package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 房价模板
 * 
 * @author carr
 * 
 */
public class RatePlanTemplateI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9083144094460506561L;

	private String ratePlanTemplateMId; //多语言表ID
	
	private String ratePlanTemplateId; //模板ID
	
	private String language;//语言        
	
	private String ratePlanTemplateName;       //模板名称
	
	private String description;//描述     

	public String getRatePlanTemplateMId() {
		return ratePlanTemplateMId;
	}

	public void setRatePlanTemplateMId(String ratePlanTemplateMId) {
		this.ratePlanTemplateMId = ratePlanTemplateMId;
	}

	public String getRatePlanTemplateId() {
		return ratePlanTemplateId;
	}

	public void setRatePlanTemplateId(String ratePlanTemplateId) {
		this.ratePlanTemplateId = ratePlanTemplateId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRatePlanTemplateName() {
		return ratePlanTemplateName;
	}

	public void setRatePlanTemplateName(String ratePlanTemplateName) {
		this.ratePlanTemplateName = ratePlanTemplateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RatePlanTemplateI18n [ratePlanTemplateMId="
				+ ratePlanTemplateMId + ", ratePlanTemplateId="
				+ ratePlanTemplateId + ", language=" + language
				+ ", ratePlanTemplateName=" + ratePlanTemplateName
				+ ", description=" + description + "]";
	}
	
}
