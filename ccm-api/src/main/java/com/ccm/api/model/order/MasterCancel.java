/**
 * 
 */
package com.ccm.api.model.order;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class MasterCancel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3275515521859307549L;
	private String masterCancelId;
	private String masterId;
	private String cancelId;
	private String cancelCode;
	private String cancelDesc;// WBE使用，酒店级别，先取取消规则描述，若没有则取名称

	// 全局取消规则快照
	private Boolean isNonRefundable = Boolean.FALSE;// 不能取消
	private Date absoluteDeadline;// 可以取消的最后期限(绝对时间)
	private Integer offsetDropTime;// 可以取消的最后期限(相对时间)参照时点
	private Integer offsetTimeUnit;// 可以取消的最后期限(相对时间)天
	private Double offsetUnitMultiplier;// 可以取消的最后期限(相对时间)时分
	private Boolean taxInclusive = Boolean.FALSE;// 是否含税
	private Boolean feesInclusive = Boolean.FALSE;// 是否扣款
	private Integer basisType;// 扣款基数
	private Integer numberOfNights;// 按几个间夜计算扣款
	private Double percent;// 按百分比扣款
	private Double amount;// 按固定金额扣款
	private Integer offsetCutTime;//可以取消的当天几点截至
	
	private Boolean channelIsCancel;// 是否勾选渠道表里取消规则

	private Date effectiveDate;// 生效时间
	private Date expireDate;// 失效时间
	private Boolean isApplyToMonday;// 周一适用
	private Boolean isApplyToTuesday;// 周二适用
	private Boolean isApplyToWednesday;// 周三适用
	private Boolean isApplyToThursday;// 周四适用
	private Boolean isApplyToFriday;// 周五适用
	private Boolean isApplyToSaturday;// 周六适用
	private Boolean isApplyToSunday;// 周日适用

	public Integer getOffsetCutTime() {
		return offsetCutTime;
	}

	public void setOffsetCutTime(Integer offsetCutTime) {
		this.offsetCutTime = offsetCutTime;
	}

	public String getMasterCancelId() {
		return masterCancelId;
	}

	public void setMasterCancelId(String masterCancelId) {
		this.masterCancelId = masterCancelId;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getCancelId() {
		return cancelId;
	}

	public void setCancelId(String cancelId) {
		this.cancelId = cancelId;
	}

	public String getCancelCode() {
		return cancelCode;
	}

	public void setCancelCode(String cancelCode) {
		this.cancelCode = cancelCode;
	}

	public String getCancelDesc() {
		return cancelDesc;
	}

	public void setCancelDesc(String cancelDesc) {
		this.cancelDesc = cancelDesc;
	}

	public Boolean getIsNonRefundable() {
		return isNonRefundable;
	}

	public void setIsNonRefundable(Boolean isNonRefundable) {
		this.isNonRefundable = isNonRefundable;
	}

	public Date getAbsoluteDeadline() {
		return absoluteDeadline;
	}

	public void setAbsoluteDeadline(Date absoluteDeadline) {
		this.absoluteDeadline = absoluteDeadline;
	}

	public Integer getOffsetTimeUnit() {
		return offsetTimeUnit;
	}

	public void setOffsetTimeUnit(Integer offsetTimeUnit) {
		this.offsetTimeUnit = offsetTimeUnit;
	}

	public Integer getOffsetDropTime() {
		return offsetDropTime;
	}

	public void setOffsetDropTime(Integer offsetDropTime) {
		this.offsetDropTime = offsetDropTime;
	}

	public Boolean getTaxInclusive() {
		return taxInclusive;
	}

	public void setTaxInclusive(Boolean taxInclusive) {
		this.taxInclusive = taxInclusive;
	}

	public Boolean getFeesInclusive() {
		return feesInclusive;
	}

	public void setFeesInclusive(Boolean feesInclusive) {
		this.feesInclusive = feesInclusive;
	}

	public Double getOffsetUnitMultiplier() {
		return offsetUnitMultiplier;
	}

	public void setOffsetUnitMultiplier(Double offsetUnitMultiplier) {
		this.offsetUnitMultiplier = offsetUnitMultiplier;
	}

	public Integer getBasisType() {
		return basisType;
	}

	public void setBasisType(Integer basisType) {
		this.basisType = basisType;
	}

	public Integer getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(Integer numberOfNights) {
		this.numberOfNights = numberOfNights;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Boolean getChannelIsCancel() {
		return channelIsCancel;
	}

	public void setChannelIsCancel(Boolean channelIsCancel) {
		this.channelIsCancel = channelIsCancel;
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

	@Override
	public String toString() {
		return "MasterCancel [masterCancelId=" + masterCancelId + ", masterId=" + masterId + ", cancelId=" + cancelId + ", cancelCode=" + cancelCode + ", cancelDesc=" + cancelDesc + ", isNonRefundable=" + isNonRefundable + ", absoluteDeadline=" + absoluteDeadline + ", offsetDropTime=" + offsetDropTime + ", offsetTimeUnit=" + offsetTimeUnit + ", offsetUnitMultiplier=" + offsetUnitMultiplier + ", taxInclusive=" + taxInclusive + ", feesInclusive=" + feesInclusive + ", basisType=" + basisType + ", numberOfNights=" + numberOfNights + ", percent=" + percent + ", amount=" + amount + ", channelIsCancel=" + channelIsCancel + ", effectiveDate=" + effectiveDate + ", expireDate=" + expireDate + ", isApplyToMonday=" + isApplyToMonday + ", isApplyToTuesday=" + isApplyToTuesday + ", isApplyToWednesday="
				+ isApplyToWednesday + ", isApplyToThursday=" + isApplyToThursday + ", isApplyToFriday=" + isApplyToFriday + ", isApplyToSaturday=" + isApplyToSaturday + ", isApplyToSunday=" + isApplyToSunday + "]";
	}

}
