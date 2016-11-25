package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 客房概况
 * @author carr
 *
 */
public class GuestRoomInfo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4145973005126762963L;

	private String guestRoomInfoId;//序号 
	private String hotelId;//酒店序号     
	private Integer infoCode;//客房信息代码
	private Integer quantity;//数量        
	private String description;//描述     
	private Integer existsCode;//存在代码  
	
	public String getGuestRoomInfoId() {
		return guestRoomInfoId;
	}
	public void setGuestRoomInfoId(String guestRoomInfoId) {
		this.guestRoomInfoId = guestRoomInfoId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public Integer getInfoCode() {
		return infoCode;
	}
	public void setInfoCode(Integer infoCode) {
		this.infoCode = infoCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getExistsCode() {
		return existsCode;
	}
	public void setExistsCode(Integer existsCode) {
		this.existsCode = existsCode;
	}
	
	@Override
	public String toString() {
		return "GuestRoomInfo [guestRoomInfoId=" + guestRoomInfoId
				+ ", hotelId=" + hotelId + ", infoCode=" + infoCode
				+ ", quantity=" + quantity + ", description=" + description
				+ ", existsCode=" + existsCode + "]";
	}
}
