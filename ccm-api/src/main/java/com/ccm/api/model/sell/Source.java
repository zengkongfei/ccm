package com.ccm.api.model.sell;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 来源
 * @author carr
 *
 */
public class Source extends BaseObject {

	private static final long serialVersionUID = -9064453229270697958L;

	private String	sourceId	;//	序号
	private String	sourceCode	;//	代码
	private Date	effectiveDate	;//	生效时间
	private Date	expireDate	;//	失效时间
	
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
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
		return "Source [sourceId=" + sourceId + ", sourceCode=" + sourceCode
				+ ", effectiveDate=" + effectiveDate + ", expireDate="
				+ expireDate + "]";
	}
}
