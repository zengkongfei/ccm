package com.ccm.api.model.sell.vo;

import java.util.Date;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class MarketCreteria extends SearchCriteria{

	private static final long serialVersionUID = -6379155992060418884L;
	
	private String	marketCode	;//	代码
	private Date	effectiveDate	;//	生效时间
	private Date	expireDate	;//	失效时间
	private String  language;  //语言种类
	
	
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
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
	
	

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "MarketCreteria [marketCode=" + marketCode + ", effectiveDate="
				+ effectiveDate + ", expireDate=" + expireDate + "]";
	}
}
