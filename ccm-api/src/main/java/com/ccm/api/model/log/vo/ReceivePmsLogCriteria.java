/**
 * 
 */
package com.ccm.api.model.log.vo;

import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

/**
 * @author Jenny Liao
 * 
 */
public class ReceivePmsLogCriteria extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3681085466414249493L;

	private String interfaceId;
	private String hotelCode;
	private String status;// 多个状态查询

	private List<String> hoteCodes;

	private String languageCode;

	

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getHoteCodes() {
		return hoteCodes;
	}

	public void setHoteCodes(List<String> hoteCodes) {
		this.hoteCodes = hoteCodes;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@Override
	public String toString() {
		return "ReceivePmsLogCriteria [interfaceId=" + interfaceId + ", hotelCode=" + hotelCode + ", status=" + status + ", hoteCodes=" + hoteCodes + ", languageCode=" + languageCode + "]";
	}

}
