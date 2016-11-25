package com.ccm.api.model.log;

import com.ccm.api.model.base.BaseObject;

/**
 * PMS心跳监控
 * @author chay
 *
 */
public class HotelReceivepms extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9114084938235196214L;

	private String hotelReceivepmsId;
	private String hotelId;//酒店id
	private String hotelCode;//酒店代码
	private int number;//次数
	private Long spaceSec;//断开时长
	public String getHotelReceivepmsId() {
		return hotelReceivepmsId;
	}
	public void setHotelReceivepmsId(String hotelReceivepmsId) {
		this.hotelReceivepmsId = hotelReceivepmsId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Long getSpaceSec() {
		return spaceSec;
	}
	public void setSpaceSec(Long spaceSec) {
		this.spaceSec = spaceSec;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "hotelReceivepms [hotelReceivepmsId=" + hotelReceivepmsId
				+ ", hotelId=" + hotelId + ", hotelCode=" + hotelCode
				+ ", number=" + number + ", spaceSec=" + spaceSec + "]";
	}
	
	
	
}
