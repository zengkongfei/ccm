package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 地标
 * @author carr
 *
 */
public class Position extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4087306275979818122L;

	private String positionId;//序号    
	private Integer pointCode;//周边类型 
	private Double longitude;//经度     
	private Double latitude;//纬度   
	private String hotelId;//酒店ID
	
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public Integer getPointCode() {
		return pointCode;
	}
	public void setPointCode(Integer pointCode) {
		this.pointCode = pointCode;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	@Override
	public String toString() {
		return "position [positionId=" + positionId + ", pointCode="
				+ pointCode + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}
}
