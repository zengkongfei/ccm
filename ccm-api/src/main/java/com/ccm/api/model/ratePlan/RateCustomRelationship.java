package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 房价担保
 * 
 * @author Jenny
 * 
 */
public class RateCustomRelationship extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1281181084227502987L;

	private String rateCustomRelationshipId;// 序号
	private String ratePlanId;// 房价序号
	private String customId;// 客户序号

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public String getRateCustomRelationshipId() {
		return rateCustomRelationshipId;
	}

	public void setRateCustomRelationshipId(String rateCustomRelationshipId) {
		this.rateCustomRelationshipId = rateCustomRelationshipId;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	@Override
	public String toString() {
		return "RateCustomRelationship [rateCustomRelationshipId=" + rateCustomRelationshipId + ", ratePlanId=" + ratePlanId + ", customId=" + customId + "]";
	}

}
