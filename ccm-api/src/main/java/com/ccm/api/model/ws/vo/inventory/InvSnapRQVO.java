package com.ccm.api.model.ws.vo.inventory;

import java.util.Date;

public class InvSnapRQVO {
	private	String hotelCode;//
	private	Date datum;//rsvtype.date
	private	String roomType;//roomtypecode
	private	String physicalRooms;//所有房量
	private	String houseOverbook;//酒店超卖
	private String outOfOrder;//特定房型超卖
	private	String roomTypeOverbook;//特定房型不能卖
	private	String adultsInHouse;//最多入住成人
	private	String childrenInHouse;//最多入住小孩
	
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getPhysicalRooms() {
		return physicalRooms;
	}
	public void setPhysicalRooms(String physicalRooms) {
		this.physicalRooms = physicalRooms;
	}
	public String getHouseOverbook() {
		return houseOverbook;
	}
	public void setHouseOverbook(String houseOverbook) {
		this.houseOverbook = houseOverbook;
	}
	public String getOutOfOrder() {
		return outOfOrder;
	}
	public void setOutOfOrder(String outOfOrder) {
		this.outOfOrder = outOfOrder;
	}
	public String getRoomTypeOverbook() {
		return roomTypeOverbook;
	}
	public void setRoomTypeOverbook(String roomTypeOverbook) {
		this.roomTypeOverbook = roomTypeOverbook;
	}
	public String getAdultsInHouse() {
		return adultsInHouse;
	}
	public void setAdultsInHouse(String adultsInHouse) {
		this.adultsInHouse = adultsInHouse;
	}
	public String getChildrenInHouse() {
		return childrenInHouse;
	}
	public void setChildrenInHouse(String childrenInHouse) {
		this.childrenInHouse = childrenInHouse;
	}
	
	@Override
	public String toString() {
		return "InventoryRQVO [hotelCode=" + hotelCode + ", datum=" + datum
				+ ", roomType=" + roomType + ", physicalRooms=" + physicalRooms
				+ ", houseOverbook=" + houseOverbook + ", outOfOrder="
				+ outOfOrder + ", roomTypeOverbook=" + roomTypeOverbook
				+ ", adultsInHouse=" + adultsInHouse + ", childrenInHouse="
				+ childrenInHouse + "]";
	}
}
