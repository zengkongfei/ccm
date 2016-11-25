package com.ccm.api.model.hotel;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.ccm.api.model.base.BaseObject;

public class HotelCreditLimitBinding  extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 121276133485612557L;
	@Id
	private String bindingId;
	private String creditLimitId;//fk
	private String channelId;
	private String channelCode;
	private String hotelId;
	private String hotelCode;
	private BigDecimal totalRoomRev;
	private BigDecimal variable=new BigDecimal("0");
	public BigDecimal getVariable() {
		return variable;
	}
	public void setVariable(BigDecimal variable) {
		this.variable = variable;
	}
	public String getCreditLimitId() {
		return creditLimitId;
	}
	public void setCreditLimitId(String creditLimitId) {
		this.creditLimitId = creditLimitId;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public BigDecimal getTotalRoomRev() {
		return totalRoomRev;
	}
	public void setTotalRoomRev(BigDecimal totalRoomRev) {
		this.totalRoomRev = totalRoomRev;
	}
	public String getBindingId() {
		return bindingId;
	}
	public void setBindingId(String bindingId) {
		this.bindingId = bindingId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
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
}
