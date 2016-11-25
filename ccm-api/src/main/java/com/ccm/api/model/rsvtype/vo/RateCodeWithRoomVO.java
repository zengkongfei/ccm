package com.ccm.api.model.rsvtype.vo;


import java.util.Date;

import com.ccm.api.model.base.BaseObject;

public class RateCodeWithRoomVO  extends BaseObject  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ratePlanCode;
	private String roomTypeCode;
	private String ratePlanId;
	private String rateDetailId;
	private String inheritRatePlanId;
	private Date effectiveDate;
	private Date expireDate;
	
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getInheritRatePlanId() {
		return inheritRatePlanId;
	}
	public void setInheritRatePlanId(String inheritRatePlanId) {
		this.inheritRatePlanId = inheritRatePlanId;
	}
	public String getRateDetailId() {
		return rateDetailId;
	}
	public void setRateDetailId(String rateDetailId) {
		this.rateDetailId = rateDetailId;
	}
	public String getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	
}
