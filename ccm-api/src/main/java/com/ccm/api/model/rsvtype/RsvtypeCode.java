package com.ccm.api.model.rsvtype;

import org.springframework.data.annotation.Id;

public class RsvtypeCode {

	@Id
	private String rsvtypeCodeId;
	private String chainCode;
	private String hotelCode;
	private String type;
	private Integer count;
	private String machine;
	
	public String getRsvtypeCodeId() {
		return rsvtypeCodeId;
	}
	public void setRsvtypeCodeId(String rsvtypeCodeId) {
		this.rsvtypeCodeId = rsvtypeCodeId;
	}
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	
}
