package com.ccm.api.model.ratePlan;

import java.math.BigDecimal;
import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 房价明细
 * @author carr
 *
 */
public class RateDetail extends BaseObject implements Cloneable{

	@Override
	public RateDetail clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (RateDetail)super.clone();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7669115039242396299L;
	
	private String rateDetailId;//序号           
	private String ratePlanId;//房价序号         
	private Date effectiveDate;//生效时间      
	private Date expireDate;//失效时间         
	private Boolean isApplyToMonday;//周一适用  
	private Boolean isApplyToTuesday;//周二适用   
	private Boolean isApplyToWednesday;//周三适用 
	private Boolean isApplyToThursday;//周四适用  
	private Boolean isApplyToFriday;//周五适用    
	private Boolean isApplyToSaturday;//周六适用  
	private Boolean isApplyToSunday;//周日适用    
	private BigDecimal extraBed;//加床（成人）  
	private BigDecimal extraBedChild;//加床（小孩）
	
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
	public BigDecimal getExtraBed() {
		return extraBed;
	}
	public void setExtraBed(BigDecimal extraBed) {
		this.extraBed = extraBed;
	}
	public BigDecimal getExtraBedChild() {
		return extraBedChild;
	}
	public void setExtraBedChild(BigDecimal extraBedChild) {
		this.extraBedChild = extraBedChild;
	}
	
	@Override
	public String toString() {
		return "RateDetail [rateDetailId=" + rateDetailId + ", ratePlanId="
				+ ratePlanId + ", effectiveDate=" + effectiveDate
				+ ", expireDate=" + expireDate + ", isApplyToMonday="
				+ isApplyToMonday + ", isApplyToTuesday=" + isApplyToTuesday
				+ ", isApplyToWednesday=" + isApplyToWednesday
				+ ", isApplyToThursday=" + isApplyToThursday
				+ ", isApplyToFriday=" + isApplyToFriday
				+ ", isApplyToSaturday=" + isApplyToSaturday
				+ ", isApplyToSunday=" + isApplyToSunday + ", extraBed="
				+ extraBed + ", extraBedChild=" + extraBedChild + "]";
	}
}
