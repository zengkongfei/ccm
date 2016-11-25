package com.ccm.api.model.log;

import java.math.BigDecimal;

import com.ccm.api.model.base.BaseObject;

public class ChannelOrder extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4979057534414167482L;
	
	private String hotelName;
	private String channelCode;
	private Integer countOfOrder; //总订单数
	private Integer totalRoomNights; //总晚房数
	private BigDecimal totalAmountOfOrders;//订单总金额

	@Override
	public String toString() {
		return "ChannelOrder [hotelName=" + hotelName + ", channelCode="
				+ channelCode + ", countOfOrder=" + countOfOrder
				+ ", totalRoomNights=" + totalRoomNights
				+ ", totalAmountOfOrders=" + totalAmountOfOrders + "]";
	}
	
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public Integer getCountOfOrder() {
		return countOfOrder;
	}
	public void setCountOfOrder(Integer countOfOrder) {
		this.countOfOrder = countOfOrder;
	}
	public Integer getTotalRoomNights() {
		return totalRoomNights;
	}
	public void setTotalRoomNights(Integer totalRoomNights) {
		this.totalRoomNights = totalRoomNights;
	}
	public BigDecimal getTotalAmountOfOrders() {
		return totalAmountOfOrders;
	}
	public void setTotalAmountOfOrders(BigDecimal totalAmountOfOrders) {
		this.totalAmountOfOrders = totalAmountOfOrders;
	}
	
}

	
	
