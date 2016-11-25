package com.ccm.api.model.hotel.vo;

import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class CustomCreteria extends SearchCriteria {

	private static final long serialVersionUID = -2623312015800158243L;

	private String hotelId;//
	private String name;
	private String type;
	private String corpIATANo;
	private String accessCode;

	private String channelId;
	private String channelCode;
	private List<String> channelCodeList;
	private List<String> channelIdList;
	
	
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public List<String> getChannelCodeList() {
		return channelCodeList;
	}

	public void setChannelCodeList(List<String> channelCodeList) {
		this.channelCodeList = channelCodeList;
	}

	public List<String> getChannelIdList() {
		return channelIdList;
	}

	public void setChannelIdList(List<String> channelIdList) {
		this.channelIdList = channelIdList;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
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

	@Override
	public String toString() {
		return "CustomCreteria [hotelId=" + hotelId + ", name=" + name + ", type=" + type + ", corpIATANo=" + corpIATANo + ", accessCode=" + accessCode + "]";
	}

}
