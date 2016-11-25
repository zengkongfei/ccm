package com.ccm.api.model.ratePlan.vo;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class PackageCriteria extends SearchCriteria{

	private static final long serialVersionUID = 3372400721855935732L;
	
	private String packageCode;//打包服务代码
	private String packageName;//服务名称  
	private Integer calculation	;//计算方式
	private Integer rule;//计算规则
	private Integer basicType;//计算类型
	private String language;
	
	public String getPackageCode() {
		return packageCode;
	}
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Integer getCalculation() {
		return calculation;
	}
	public void setCalculation(Integer calculation) {
		this.calculation = calculation;
	}
	public Integer getRule() {
		return rule;
	}
	public void setRule(Integer rule) {
		this.rule = rule;
	}
	public Integer getBasicType() {
		return basicType;
	}
	public void setBasicType(Integer basicType) {
		this.basicType = basicType;
	}
	
	
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "PackageCriteria [packageCode=" + packageCode + ", packageName="
				+ packageName + ", calculation=" + calculation + ", rule="
				+ rule + ", basicType=" + basicType + "]";
	}
}
