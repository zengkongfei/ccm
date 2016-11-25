package com.ccm.api.model.ratePlan;

import java.util.Date;

/**
 * 房价取消
 * 
 * @author carr
 * 
 */
public class RateCancelRelationship extends CancelPolicy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4252402635212651965L;

	private String rateCancelRelationshipId;// 序号
	private String ratePlanId;// 房价序号
	private String cancelId;// 取消规则序号
	private Date effectiveDate;// 生效时间
	private Date expireDate;// 失效时间
	private String policyName;// 取消名称
	private String description;// 描述
	private Boolean isApplyToMonday;// 周一适用
	private Boolean isApplyToTuesday;// 周二适用
	private Boolean isApplyToWednesday;// 周三适用
	private Boolean isApplyToThursday;// 周四适用
	private Boolean isApplyToFriday;// 周五适用
	private Boolean isApplyToSaturday;// 周六适用
	private Boolean isApplyToSunday;// 周日适用

	public String getRateCancelRelationshipId() {
		return rateCancelRelationshipId;
	}

	public void setRateCancelRelationshipId(String rateCancelRelationshipId) {
		this.rateCancelRelationshipId = rateCancelRelationshipId;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public String getCancelId() {
		return cancelId;
	}

	public void setCancelId(String cancelId) {
		this.cancelId = cancelId;
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

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RateCancelRelationship [rateCancelRelationshipId=" + rateCancelRelationshipId + ", ratePlanId=" + ratePlanId + ", cancelId=" + cancelId + ", effectiveDate=" + effectiveDate + ", expireDate=" + expireDate + ", policyName=" + policyName + ", description=" + description + ", isApplyToMonday=" + isApplyToMonday + ", isApplyToTuesday=" + isApplyToTuesday + ", isApplyToWednesday=" + isApplyToWednesday + ", isApplyToThursday=" + isApplyToThursday + ", isApplyToFriday=" + isApplyToFriday + ", isApplyToSaturday=" + isApplyToSaturday + ", isApplyToSunday=" + isApplyToSunday + "]";
	}

}
