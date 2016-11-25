package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 取消规则(多语言)
 * @author carr
 *
 */
public class CancelPolicyI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5684509209814420903L;
	
	private String cancelMId;//序号       
	private String cancelId;//取消规则序号
	private String language;//语言        
	private String policyName;//规则名称  
	private String description;//描述    
	
	public String getCancelMId() {
		return cancelMId;
	}
	public void setCancelMId(String cancelMId) {
		this.cancelMId = cancelMId;
	}
	public String getCancelId() {
		return cancelId;
	}
	public void setCancelId(String cancelId) {
		this.cancelId = cancelId;
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
		return "CancelPolicyI18n [cancelMId=" + cancelMId + ", cancelId="
				+ cancelId + ", language=" + language + ", policyName="
				+ policyName + ", description=" + description + "]";
	}
}
