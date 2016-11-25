package com.ccm.api.model.ratePlan.vo;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class CancelPolicyCriteria extends SearchCriteria {

	private static final long serialVersionUID = -7750760556232333449L;

	private String cancelPolicyCode;// 取消代码
	private String policyName;// 规则名称
	private String language;// 语言

	public String getCancelPolicyCode() {
		return cancelPolicyCode;
	}

	public void setCancelPolicyCode(String cancelPolicyCode) {
		this.cancelPolicyCode = cancelPolicyCode;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "CancelPolicyCriteria [cancelPolicyCode=" + cancelPolicyCode + ", policyName=" + policyName + ", language=" + language + "]";
	}

}
