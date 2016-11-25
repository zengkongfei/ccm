package com.ccm.api.model.log.vo;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class CustomLogCreteria extends SearchCriteria {

	private static final long serialVersionUID = -2623312015800158243L;

	private String hotelCode;//
	private String name;
	private String type;
	private String corpIATANo;
	private String accessCode;

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCorpIATANo() {
		return corpIATANo;
	}

	public void setCorpIATANo(String corpIATANo) {
		this.corpIATANo = corpIATANo;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

}
