/**
 * 
 */
package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class Brand extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6305695407340395830L;
	
	private String brandId;//序号         
	private String chainId;//集团序号     
	private String brandCode;//品牌代码   
	private String brandPic;//品牌图标    


	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandPic() {
		return brandPic;
	}

	public void setBrandPic(String brandPic) {
		this.brandPic = brandPic;
	}

	@Override
	public String toString() {
		return "Brand [brandId=" + brandId + ", chainId=" + chainId
				+ ", brandCode=" + brandCode + ", brandPic=" + brandPic + "]";
	}

}
