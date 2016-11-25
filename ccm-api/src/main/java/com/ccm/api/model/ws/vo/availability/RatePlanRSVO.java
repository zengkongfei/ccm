package com.ccm.api.model.ws.vo.availability;


public class RatePlanRSVO {
	private String ratePlanCode;//房价代码
	private String ratePlanDescription;//房价描述
	private String refundpolicyinfo;//退订规则
	
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	public String getRatePlanDescription() {
		return ratePlanDescription;
	}
	public void setRatePlanDescription(String ratePlanDescription) {
		this.ratePlanDescription = ratePlanDescription;
	}
	public String getRefundpolicyinfo() {
		return refundpolicyinfo;
	}
	public void setRefundpolicyinfo(String refundpolicyinfo) {
		this.refundpolicyinfo = refundpolicyinfo;
	}
	
	@Override
	public String toString() {
		return "RatePlanRSVO [ratePlanCode=" + ratePlanCode
				+ ", ratePlanDescription=" + ratePlanDescription
				+ ", refundpolicyinfo=" + refundpolicyinfo + "]";
	}
}
