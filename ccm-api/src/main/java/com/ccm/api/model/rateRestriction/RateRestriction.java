package com.ccm.api.model.rateRestriction;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;


public class RateRestriction extends BaseObject {

	private static final long serialVersionUID = 3633281551200236629L;
	
	private String rateRestrictionId;
	private String hotelCode;
	private String rateCode;
	private Integer restrictionType;//限制类型 1:S_OPEN;2:S_CLOSE
	private Date beginDate;
	private Date endDate;
	private Boolean Sun;
	private Boolean Mon;
	private Boolean Tue;
	private Boolean Wed;
	private Boolean Thu;
	private Boolean Fri;
	private Boolean Sat;
	
	public String getRateRestrictionId() {
		return rateRestrictionId;
	}
	public void setRateRestrictionId(String rateRestrictionId) {
		this.rateRestrictionId = rateRestrictionId;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	public Integer getRestrictionType() {
		return restrictionType;
	}
	public void setRestrictionType(Integer restrictionType) {
		this.restrictionType = restrictionType;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Boolean getSun() {
		return Sun;
	}
	public void setSun(Boolean sun) {
		Sun = sun;
	}
	public Boolean getMon() {
		return Mon;
	}
	public void setMon(Boolean mon) {
		Mon = mon;
	}
	public Boolean getTue() {
		return Tue;
	}
	public void setTue(Boolean tue) {
		Tue = tue;
	}
	public Boolean getWed() {
		return Wed;
	}
	public void setWed(Boolean wed) {
		Wed = wed;
	}
	public Boolean getThu() {
		return Thu;
	}
	public void setThu(Boolean thu) {
		Thu = thu;
	}
	public Boolean getFri() {
		return Fri;
	}
	public void setFri(Boolean fri) {
		Fri = fri;
	}
	public Boolean getSat() {
		return Sat;
	}
	public void setSat(Boolean sat) {
		Sat = sat;
	}
	
	@Override
	public String toString() {
		return "RateRestriction [rateRestrictionId=" + rateRestrictionId
				+ ", hotelCode=" + hotelCode + ", rateCode=" + rateCode
				+ ", restrictionType=" + restrictionType + ", beginDate="
				+ beginDate + ", endDate=" + endDate + ", Sun=" + Sun
				+ ", Mon=" + Mon + ", Tue=" + Tue + ", Wed=" + Wed + ", Thu="
				+ Thu + ", Fri=" + Fri + ", Sat=" + Sat + "]";
	}
}
