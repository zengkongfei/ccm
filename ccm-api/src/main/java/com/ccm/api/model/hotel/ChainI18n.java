package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 集团（多语言）
 * @author carr
 *
 */
public class ChainI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6666669701433435490L;

	private String chainMId;//集团多语言主键 
	private String chainId;//集团主键        
	private String language;//语言           
	private String chainName;//集团名称      
	private String description;//集团简介    
	
	public String getChainMId() {
		return chainMId;
	}
	public void setChainMId(String chainMId) {
		this.chainMId = chainMId;
	}
	public String getChainId() {
		return chainId;
	}
	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getChainName() {
		return chainName;
	}
	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "ChainI18n [chainMId=" + chainMId + ", chainId=" + chainId
				+ ", language=" + language + ", chainName=" + chainName
				+ ", description=" + description + "]";
	}
}
