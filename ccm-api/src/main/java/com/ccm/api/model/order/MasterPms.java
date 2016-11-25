package com.ccm.api.model.order;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 无pmsid的订单
 * @author chay
 *
 */
public class MasterPms extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 401257053488061020L;
	private String masterId;//订单id
	private String hotelId;//酒店id
	private String channelId;//酒店id
	private String sta;//订单状态
	private int number;//轮询次数
	private Date arr;
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getArr() {
		return arr;
	}
	public void setArr(Date arr) {
		this.arr = arr;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	@Override
	public String toString() {
		return "MasterPms [masterId=" + masterId + ", hotelId=" + hotelId + ", channelId=" + channelId + ", sta=" + sta + ", number=" + number + ", arr=" + arr + "]";
	}
	
	
}
