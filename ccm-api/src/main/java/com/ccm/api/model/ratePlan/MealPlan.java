package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 膳食计划
 * @author carr
 *
 */
public class MealPlan extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4780787419368778597L;
	
	private String mealPlanId;//序号          
	private String ratePlanId;//房价序号      
	private Integer mealPlanCode;//膳食计划代码
	
	public String getMealPlanId() {
		return mealPlanId;
	}
	public void setMealPlanId(String mealPlanId) {
		this.mealPlanId = mealPlanId;
	}
	public String getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public Integer getMealPlanCode() {
		return mealPlanCode;
	}
	public void setMealPlanCode(Integer mealPlanCode) {
		this.mealPlanCode = mealPlanCode;
	}
	
	@Override
	public String toString() {
		return "MealPlan [mealPlanId=" + mealPlanId + ", ratePlanId="
				+ ratePlanId + ", mealPlanCode=" + mealPlanCode + "]";
	}
}
