package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 担保规则(多语言)
 * @author carr
 *
 */
public class GuaranteePolicyI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7876117315871421367L;
	
	private String guaranteeMId;//序号
	private String guaranteeId;//担保规则序号
	private String language;//语言
	private String policyName;//规则名称
	private String description;//描述
	
	public String getGuaranteeMId() {
		return guaranteeMId;
	}
	public void setGuaranteeMId(String guaranteeMId) {
		this.guaranteeMId = guaranteeMId;
	}
	public String getGuaranteeId() {
		return guaranteeId;
	}
	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "GuaranteePolicyI18n [guaranteeMId=" + guaranteeMId
				+ ", guaranteeId=" + guaranteeId + ", language=" + language
				+ ", policyName=" + policyName + ", description=" + description
				+ "]";
	}
}
