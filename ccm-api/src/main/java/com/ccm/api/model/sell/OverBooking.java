package com.ccm.api.model.sell;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 超订数据
 * @author carr
 *
 */
public class OverBooking extends BaseObject {

	private static final long serialVersionUID = -6207100812361506327L;

	private String overBookingId;//序号         
	private Date fromDate;//开始时间          
	private Date toDate;//结束时间            
	private Boolean isApplyToMonday;//周一适用   
	private Boolean isApplyToTuesday;//周二适用  
	private Boolean isApplyToWednesday;//周三适用
	private Boolean isApplyToThursday;//周四适用 
	private Boolean isApplyToFriday;//周五适用   
	private Boolean isApplyToSaturday;//周六适用 
	private Boolean isApplyToSunday;//周日适用   
	private Date effectiveTime;//生效时间     
	private Date expireTime;//失效时间        
	private Integer allotment;//超订数量         
	
	public String getOverBookingId() {
		return overBookingId;
	}
	public void setOverBookingId(String overBookingId) {
		this.overBookingId = overBookingId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Boolean getIsApplyToMonday() {
		return isApplyToMonday;
	}
	public void setIsApplyToMonday(Boolean isApplyToMonday) {
		this.isApplyToMonday = isApplyToMonday;
	}
	public Boolean getIsApplyToTuesday() {
		return isApplyToTuesday;
	}
	public void setIsApplyToTuesday(Boolean isApplyToTuesday) {
		this.isApplyToTuesday = isApplyToTuesday;
	}
	public Boolean getIsApplyToWednesday() {
		return isApplyToWednesday;
	}
	public void setIsApplyToWednesday(Boolean isApplyToWednesday) {
		this.isApplyToWednesday = isApplyToWednesday;
	}
	public Boolean getIsApplyToThursday() {
		return isApplyToThursday;
	}
	public void setIsApplyToThursday(Boolean isApplyToThursday) {
		this.isApplyToThursday = isApplyToThursday;
	}
	public Boolean getIsApplyToFriday() {
		return isApplyToFriday;
	}
	public void setIsApplyToFriday(Boolean isApplyToFriday) {
		this.isApplyToFriday = isApplyToFriday;
	}
	public Boolean getIsApplyToSaturday() {
		return isApplyToSaturday;
	}
	public void setIsApplyToSaturday(Boolean isApplyToSaturday) {
		this.isApplyToSaturday = isApplyToSaturday;
	}
	public Boolean getIsApplyToSunday() {
		return isApplyToSunday;
	}
	public void setIsApplyToSunday(Boolean isApplyToSunday) {
		this.isApplyToSunday = isApplyToSunday;
	}
	public Date getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Integer getAllotment() {
		return allotment;
	}
	public void setAllotment(Integer allotment) {
		this.allotment = allotment;
	}
	
	@Override
	public String toString() {
		return "OverBooking [overBookingId=" + overBookingId + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", isApplyToMonday="
				+ isApplyToMonday + ", isApplyToTuesday=" + isApplyToTuesday
				+ ", isApplyToWednesday=" + isApplyToWednesday
				+ ", isApplyToThursday=" + isApplyToThursday
				+ ", isApplyToFriday=" + isApplyToFriday
				+ ", isApplyToSaturday=" + isApplyToSaturday
				+ ", isApplyToSunday=" + isApplyToSunday + ", effectiveTime="
				+ effectiveTime + ", expireTime=" + expireTime + ", allotment="
				+ allotment + "]";
	}
}
