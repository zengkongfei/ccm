package com.ccm.api.model.sell.vo;

import java.util.Date;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class SourceCreteria extends SearchCriteria{

	private static final long serialVersionUID = -6634041611351538120L;

	private String	sourceCode	;//	代码
	private Date	effectiveDate	;//	生效时间
	private Date	expireDate	;//	失效时间
	private String  language;
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	@Override
	public String toString() {
		return "SourceCreteria [sourceCode=" + sourceCode + ", effectiveDate="
				+ effectiveDate + ", expireDate=" + expireDate + "]";
	}
}
