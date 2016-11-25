package com.ccm.api.model.hotel.vo;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class ChainCreteria extends SearchCriteria{
	
	private static final long serialVersionUID = 2364527277085078957L;

	private String chainCode;//集团代码
	private String chainName;//集团名称 
	private String language; //语言
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	public String getChainName() {
		return chainName;
	}
	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
	
	@Override
	public String toString() {
		return "ChainCreteria [chainCode=" + chainCode + ", chainName="
				+ chainName + "]";
	}
}
