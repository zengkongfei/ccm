package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 库存
 * @author carr
 *
 */
public class Inventory extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3722627457242807622L;

	private String inventoryId;//序号 
	private String hotelId;//酒店序号 
	private String invCode;//库存代码 
	
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getInvCode() {
		return invCode;
	}
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}
	
	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", hotelId=" + hotelId
				+ ", invCode=" + invCode + "]";
	}
}
