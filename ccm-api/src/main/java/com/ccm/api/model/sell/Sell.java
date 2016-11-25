package com.ccm.api.model.sell;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 销售数据
 * @author carr
 *
 */
public class Sell extends BaseObject {

	private static final long serialVersionUID = 1610017011084325494L;

	private String roomSellId;//序号            
	private Date fromDate;//开始时间          
	private Date toDate;//结束时间            
	private Boolean isApplyToMonday;//周一适用   
	private Boolean isApplyToTuesday;//周二适用  
	private Boolean isApplyToWednesday;//周三适用
	private Boolean isApplyToThursday;//周四适用 
	private Boolean isApplyToFriday;//周五适用   
	private Boolean isApplyToSaturday;//周六适用 
	private Boolean isApplyToSunday;//周日适用   
	private Boolean effectiveTime;//生效时间     
	private Date expireTime;//失效时间        
	private Date allotment;//保留房           
	private Integer freeSell;//freesell开关      
	
	public String getRoomSellId() {
		return roomSellId;
	}
	public void setRoomSellId(String roomSellId) {
		this.roomSellId = roomSellId;
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
	public Boolean getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(Boolean effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Date getAllotment() {
		return allotment;
	}
	public void setAllotment(Date allotment) {
		this.allotment = allotment;
	}
	public Integer getFreeSell() {
		return freeSell;
	}
	public void setFreeSell(Integer freeSell) {
		this.freeSell = freeSell;
	}
	
	@Override
	public String toString() {
		return "Sell [roomSellId=" + roomSellId + ", isApplyToMonday="
				+ isApplyToMonday + ", isApplyToTuesday=" + isApplyToTuesday
				+ ", isApplyToWednesday=" + isApplyToWednesday
				+ ", isApplyToThursday=" + isApplyToThursday
				+ ", isApplyToFriday=" + isApplyToFriday
				+ ", isApplyToSaturday=" + isApplyToSaturday
				+ ", isApplyToSunday=" + isApplyToSunday + ", effectiveTime="
				+ effectiveTime + ", freeSell=" + freeSell + "]";
	}
}
