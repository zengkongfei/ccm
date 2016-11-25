package com.ccm.api.model.ratePlan.vo;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class GuaranteePolicyCriteria extends SearchCriteria {

	private static final long serialVersionUID = 1850638125896439844L;

	private String guaranteeCode;// 担保代码
	private Integer guaranteeType;// 担保类型
	private String policyName;// 规则名称
	private String language;// 语言

	public String getGuaranteeCode() {
		return guaranteeCode;
	}

	public void setGuaranteeCode(String guaranteeCode) {
		this.guaranteeCode = guaranteeCode;
	}

	public Integer getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(Integer guaranteeType) {
		this.guaranteeType = guaranteeType;
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
		return "GuaranteePolicyCriteria [guaranteeCode=" + guaranteeCode + ", guaranteeType=" + guaranteeType + ", policyName=" + policyName + ", language=" + language + "]";
	}

}
