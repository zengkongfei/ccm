package com.ccm.api.model.rsvtype;


import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 *
 */
public class Usage extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8562870481144985585L;
	
	private Date resvDate;	//预定日期
	private String hotelCode;	//酒店代码
	private String channelCode;	//渠道代码
	private String roomType;	//房型代码

	private Integer available;	//自由销售房量    = 酒店可售房
	private Integer allotmentRemain; //保留房剩余房量  = 渠道保留房-渠道已售房
	private Integer availableSum;	//自由销售房量    = 酒店可售房
	private Integer allotmentSum;	//保留房销售房量  = 渠道保留房
	private Integer allotmentRemainSum; //保留房剩余房量  = 渠道保留房-渠道已售房


	private Integer allotment;//保留房		保留房销售房量 =渠道保留房
	private Integer allotmentSold;//已售保留房	渠道已售房
	private Integer freeSellSold;//已售自由销售房量结果
	
	public Integer getFreeSellSold() {
		return freeSellSold;
	}
	public void setFreeSellSold(Integer freeSellSold) {
		this.freeSellSold = freeSellSold;
	}
	public Integer getAvailableSum() {
		return availableSum;
	}
	public void setAvailableSum(Integer availableSum) {
		this.availableSum = availableSum;
	}
	public Integer getAllotmentSum() {
		return allotmentSum;
	}
	public void setAllotmentSum(Integer allotmentSum) {
		this.allotmentSum = allotmentSum;
	}
	public Integer getAllotmentRemainSum() {
		return allotmentRemainSum;
	}
	public void setAllotmentRemainSum(Integer allotmentRemainSum) {
		this.allotmentRemainSum = allotmentRemainSum;
	}
	public Date getResvDate() {
		return resvDate;
	}
	public void setResvDate(Date resvDate) {
		this.resvDate = resvDate;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Integer getAllotment() {
		return allotment;
	}
	public void setAllotment(Integer allotment) {
		this.allotment = allotment;
	}
	public Integer getAllotmentSold() {
		return allotmentSold;
	}
	public void setAllotmentSold(Integer allotmentSold) {
		this.allotmentSold = allotmentSold;
	}
	public Integer getAllotmentRemain() {
		return allotmentRemain;
	}
	public void setAllotmentRemain(Integer allotmentRemain) {
		this.allotmentRemain = allotmentRemain;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Usage [resvDate=" + resvDate + ", hotelCode=" + hotelCode
				+ ", channelCode=" + channelCode + ", roomType=" + roomType
				+ ", allotment=" + allotment + ", allotmentSold="
				+ allotmentSold + ", freeSellSold=" + freeSellSold + "]";
	}
	
}
