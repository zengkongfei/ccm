package com.ccm.api.model.ratePlan;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 取消规则
 * 
 * @author carr
 * 
 */
public class CancelPolicy extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1635637336004553060L;

	public static final int OFFSETTIMEUNIT_DAYS = 1;// 每天
	public static final int OFFSETTIMEUNIT_HOURS = 2;// 每小时

	public static final int OFFSETDROPTIME_BEFOREARRIVAL = 1;// 到达前
	public static final int OFFSETDROPTIME_AFTERBOOKING = 2;// 预订后
	public static final int OFFSETDROPTIME_AFTERCONFIRMATION = 3;// 确认后

	public static final int BASISTYPE_FULLSTAY = 1;// 按照全部扣
	public static final int BASISTYPE_NIGHTS = 2;// 按照间夜扣
	public static final int BASISTYPE_FIRSTLAST = 3;// 按照首晚扣

	private String cancelId;// 序号
	private String cancelPolicyCode;// 取消代码
	private Boolean isNonRefundable;// 不能取消
	private Date absoluteDeadline;// 可以取消的最后期限(绝对时间)
	private Integer offsetTimeUnit;// 可以取消的最后期限(相对时间)单位
	private Double offsetUnitMultiplier;// 可以取消的最后期限(相对时间)值
	private Integer offsetDropTime;// 可以取消的最后期限(相对时间)参照时点
	private Boolean taxInclusive;// 是否含税
	private Boolean feesInclusive;// 是否含其他收费
	private Integer basisType;// 扣款基数
	private Integer numberOfNights;// 按几个间夜计算扣款
	private Double percent;// 按百分比扣款
	private Double amount;// 按固定金额扣款

	private Integer cancelType;// 取消类型，TB使用
	private Integer offsetCutTime;//可以取消的当天几点截至
	
	public Integer getOffsetCutTime() {
		return offsetCutTime;
	}

	public void setOffsetCutTime(Integer offsetCutTime) {
		this.offsetCutTime = offsetCutTime;
	}

	public String getCancelId() {
		return cancelId;
	}

	public void setCancelId(String cancelId) {
		this.cancelId = cancelId;
	}

	public String getCancelPolicyCode() {
		return cancelPolicyCode;
	}

	public void setCancelPolicyCode(String cancelPolicyCode) {
		this.cancelPolicyCode = cancelPolicyCode;
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

	public Double getOffsetUnitMultiplier() {
		return offsetUnitMultiplier;
	}

	public void setOffsetUnitMultiplier(Double offsetUnitMultiplier) {
		this.offsetUnitMultiplier = offsetUnitMultiplier;
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

	public Integer getCancelType() {
		return cancelType;
	}

	public void setCancelType(Integer cancelType) {
		this.cancelType = cancelType;
	}

	@Override
	public String toString() {
		return "CancelPolicy [cancelId=" + cancelId + ", cancelPolicyCode=" + cancelPolicyCode + ", isNonRefundable=" + isNonRefundable + ", absoluteDeadline=" + absoluteDeadline + ", offsetTimeUnit=" + offsetTimeUnit + ", offsetUnitMultiplier=" + offsetUnitMultiplier + ", offsetDropTime=" + offsetDropTime + ", taxInclusive=" + taxInclusive + ", feesInclusive=" + feesInclusive + ", basisType=" + basisType + ", numberOfNights=" + numberOfNights + ", percent=" + percent + ", amount=" + amount + ", cancelType=" + cancelType + "]";
	}

}
