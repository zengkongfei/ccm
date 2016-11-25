package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

public class HotelCancel extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2474166304969961255L;
	
	private String hotelCancelId;
	private String hotelId;
	private String cancelId;
	private Integer sortNum;	//排序编号
	
	public String getHotelCancelId() {
		return hotelCancelId;
	}
	public void setHotelCancelId(String hotelCancelId) {
		this.hotelCancelId = hotelCancelId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getCancelId() {
		return cancelId;
	}
	public void setCancelId(String cancelId) {
		this.cancelId = cancelId;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	

}
