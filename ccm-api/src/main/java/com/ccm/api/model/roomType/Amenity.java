package com.ccm.api.model.roomType;

import com.ccm.api.model.base.BaseObject;

/**
 * 房型设备
 * @author carr
 *
 */
public class Amenity extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4465264191404113767L;

	private String amenityId;//序号        
	private String roomTypeId;//房型序号   
	private Integer amenityType;//设备类型  
	private Integer existsCode;//是否提供   
	private Integer quantity;//数量      
	
	public String getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(String amenityId) {
		this.amenityId = amenityId;
	}
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public Integer getAmenityType() {
		return amenityType;
	}
	public void setAmenityType(Integer amenityType) {
		this.amenityType = amenityType;
	}
	public Integer getExistsCode() {
		return existsCode;
	}
	public void setExistsCode(Integer existsCode) {
		this.existsCode = existsCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Amenity [amenityId=" + amenityId + ", roomTypeId=" + roomTypeId
				+ ", amenityType=" + amenityType + ", existsCode=" + existsCode
				+ ", quantity=" + quantity + "]";
	}
}
