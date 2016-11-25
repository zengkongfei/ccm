package com.ccm.api.model.ratePlan.vo;

import java.util.List;

import com.ccm.api.model.ratePlan.RatePlanTemplate;
import com.ccm.api.model.ratePlan.RatePlanTemplateI18n;

/**
 * 房价模板VO
 * 
 * @author carr
 * 
 */
public class RatePlanTemplateVO extends RatePlanTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3163585623266123009L;
	

	private String ratePlanTemplateMId; //多语言表ID
	
	private String ratePlanTemplateId; //模板ID
	
	private String language;//语言        
	
	private String ratePlanTemplateName;       //模板名称
	
	private String description;//描述     
	
	private List<RatePlanTemplateI18n> ratePlanTempI18nList;

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

	public List<RatePlanTemplateI18n> getRatePlanTempI18nList() {
		return ratePlanTempI18nList;
	}

	public void setRatePlanTempI18nList(
			List<RatePlanTemplateI18n> ratePlanTempI18nList) {
		this.ratePlanTempI18nList = ratePlanTempI18nList;
	}

	@Override
	public String toString() {
		return "RatePlanTemplateVO [ratePlanTemplateMId=" + ratePlanTemplateMId
				+ ", ratePlanTemplateId=" + ratePlanTemplateId + ", language="
				+ language + ", ratePlanTemplateName=" + ratePlanTemplateName
				+ ", description=" + description + ", ratePlanTempI18nList="
				+ ratePlanTempI18nList + "]";
	}

	

}
