package com.ccm.api.model.wbe;

import com.ccm.api.model.base.BaseObject;

public class EffectiveGuarrntee extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 506404407321067289L;
	private String ratePlanId;//房价序号
	private String guaranteeId;//担保序号
	private String policyName;//担保代码
	public String getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public String getGuaranteeId() {
		return guaranteeId;
	}
	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
}
