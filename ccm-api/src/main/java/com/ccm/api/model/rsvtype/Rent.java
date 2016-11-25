package com.ccm.api.model.rsvtype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Rent implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2667447446083439607L;
	
	private String specialist;  //酒店维护专员
	private String hotelCode;   //酒店代码
	private Date date;     	    //日期
	private Integer physicalRooms;    //物理房量                             
	private Integer roomTypeOverbook; //超卖总数  ！非超卖值         
	private Integer available;        //可售房数
	private Integer resvCount;        //已买总房量                                  
	private Integer outoforder;       //订单超量
	private Integer unavailable;      //酒店已售房量                             
	private BigDecimal rentRate; //出租率
	
	public String getSpecialist() {
		return specialist;
	}
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getRentRate() {
		return rentRate;
	}
	public void setRentRate(BigDecimal rentRate) {
		this.rentRate = rentRate;
	}
	public Integer getPhysicalRooms() {
		return physicalRooms;
	}
	public void setPhysicalRooms(Integer physicalRooms) {
		this.physicalRooms = physicalRooms;
	}
	public Integer getRoomTypeOverbook() {
		return roomTypeOverbook;
	}
	public void setRoomTypeOverbook(Integer roomTypeOverbook) {
		this.roomTypeOverbook = roomTypeOverbook;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Integer getResvCount() {
		return resvCount;
	}
	public void setResvCount(Integer resvCount) {
		this.resvCount = resvCount;
	}
	public Integer getOutoforder() {
		return outoforder;
	}
	public void setOutoforder(Integer outoforder) {
		this.outoforder = outoforder;
	}
	public Integer getUnavailable() {
		return unavailable;
	}
	public void setUnavailable(Integer unavailable) {
		this.unavailable = unavailable;
	}
	@Override
	public String toString() {
		return "Rent [specialist=" + specialist + ", hotelCode=" + hotelCode
				+ ", date=" + date + ", physicalRooms=" + physicalRooms
				+ ", roomTypeOverbook=" + roomTypeOverbook + ", available="
				+ available + ", resvCount=" + resvCount + ", outoforder="
				+ outoforder + ", unavailable=" + unavailable + ", rentRate="
				+ rentRate + "]";
	}
	
	
	
}
