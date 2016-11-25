/**
 * 
 */
package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class Chain extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4953916980030658834L;
	private String chainId;
	private String chainCode;

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	@Override
	public String toString() {
		return "Chain [chainId=" + chainId + ", chainCode=" + chainCode + "]";
	}

}
