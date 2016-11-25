package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 房价模板
 * 
 * @author carr
 * 
 */
public class RatePlanTemplate extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4823715147780282367L;

	private String ratePlanTemplateId;     
	
	private String ratePlanTemplateCode;    

	
	public String getRatePlanTemplateId() {
		return ratePlanTemplateId;
	}

	public void setRatePlanTemplateId(String ratePlanTemplateId) {
		this.ratePlanTemplateId = ratePlanTemplateId;
	}

	public String getRatePlanTemplateCode() {
		return ratePlanTemplateCode;
	}

	public void setRatePlanTemplateCode(String ratePlanTemplateCode) {
		this.ratePlanTemplateCode = ratePlanTemplateCode;
	}

	@Override
	public String toString() {
		return "RatePlanTemplate [ratePlanTemplateId=" + ratePlanTemplateId
				+ ", ratePlanTemplateCode=" + ratePlanTemplateCode + "]";
	}

	
}
