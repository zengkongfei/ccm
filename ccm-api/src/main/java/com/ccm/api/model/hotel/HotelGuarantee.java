package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

public class HotelGuarantee extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6133425675920620334L;
	
	private String hotelGuaranteeId;
	private String hotelId;
	private String guaranteeId;
	private Integer sortNum;	//排序编号
	
	
	public String getHotelGuaranteeId() {
		return hotelGuaranteeId;
	}
	public void setHotelGuaranteeId(String hotelGuaranteeId) {
		this.hotelGuaranteeId = hotelGuaranteeId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getGuaranteeId() {
		return guaranteeId;
	}
	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	
}
