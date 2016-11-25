package com.ccm.api.model.ratePlan.vo;

import java.util.List;

import com.ccm.api.model.ratePlan.CancelPolicy;
import com.ccm.api.model.ratePlan.CancelPolicyI18n;

public class CancelPolicyVO extends CancelPolicy {

	private static final long serialVersionUID = 5575042102171336951L;

	private String cancelMId;// 序号
	private String language;// 语言
	private String policyName;// 规则名称
	private String description;// 描述
	private String absoluteTime;// 绝对时间
	private List<CancelPolicyI18n> cancelPolicyI18nList;

	public String getCancelMId() {
		return cancelMId;
	}

	public void setCancelMId(String cancelMId) {
		this.cancelMId = cancelMId;
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

	public String getAbsoluteTime() {
		return absoluteTime;
	}

	public void setAbsoluteTime(String absoluteTime) {
		this.absoluteTime = absoluteTime;
	}

	public List<CancelPolicyI18n> getCancelPolicyI18nList() {
		return cancelPolicyI18nList;
	}

	public void setCancelPolicyI18nList(List<CancelPolicyI18n> cancelPolicyI18nList) {
		this.cancelPolicyI18nList = cancelPolicyI18nList;
	}

	@Override
	public String toString() {
		return "CancelPolicyVO [cancelMId=" + cancelMId + ", language=" + language + ", policyName=" + policyName + ", description=" + description + ", absoluteTime=" + absoluteTime + "]";
	}
}
