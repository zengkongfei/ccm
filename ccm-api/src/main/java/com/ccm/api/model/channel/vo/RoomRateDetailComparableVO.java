package com.ccm.api.model.channel.vo;

import java.io.Serializable;
import java.util.Date;

public class RoomRateDetailComparableVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8190587425827837312L;
	private String rateplanId;
	private String rateDetailid;
	private String roomRateid;
	private String roomtypeid;
	private Date effectiveDate;
	private Date expireDate;
	private Integer dateRangeType;
	private String dayRange;
	private String uncoveredDayRange;
	private Integer isDaysSame;
	public Integer getIsDaysSame() {
		return isDaysSame;
	}
	public void setIsDaysSame(Integer isDaysSame) {
		this.isDaysSame = isDaysSame;
	}
	public String getRateplanId() {
		return rateplanId;
	}
	public String getUncoveredDayRange() {
		return uncoveredDayRange;
	}
	public void setUncoveredDayRange(String uncoveredDayRange) {
		this.uncoveredDayRange = uncoveredDayRange;
	}
	public void setRateplanId(String rateplanId) {
		this.rateplanId = rateplanId;
	}
	public String getRateDetailid() {
		return rateDetailid;
	}
	public void setRateDetailid(String rateDetailid) {
		this.rateDetailid = rateDetailid;
	}
	public String getRoomRateid() {
		return roomRateid;
	}
	public void setRoomRateid(String roomRateid) {
		this.roomRateid = roomRateid;
	}
	public String getRoomtypeid() {
		return roomtypeid;
	}
	public void setRoomtypeid(String roomtypeid) {
		this.roomtypeid = roomtypeid;
	}
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
	public Integer getDateRangeType() {
		return dateRangeType;
	}
	public void setDateRangeType(Integer dateRangeType) {
		this.dateRangeType = dateRangeType;
	}
	public String getDayRange() {
		return dayRange;
	}
	public void setDayRange(String dayRange) {
		this.dayRange = dayRange;
	}

}
