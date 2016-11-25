package com.ccm.api.model.ratePlan.vo;

import java.util.Date;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class RateDetailVOCriteria extends SearchCriteria{

	private static final long serialVersionUID = 1608387678052245124L;
	private String chainCode;// 集团代码
	private String hotelCode;// 酒店代码
	private String ratePlanCode;// 房价代码
	private String rateDetailId;//序号        
	private Date effectiveDate;//生效时间      
	private Date expireDate;//失效时间     
	private Date startDate;//对应createdTime
    private Date endDate;//对应createdTime
    private String uuidSign;
    private String rateType;
    private String customerId;
    
    private String[] status;
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	public String getRateDetailId() {
		return rateDetailId;
	}
	public void setRateDetailId(String rateDetailId) {
		this.rateDetailId = rateDetailId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String... status) {
		this.status = status;
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
	public String getUuidSign() {
		return uuidSign;
	}
	public void setUuidSign(String uuidSign) {
		this.uuidSign = uuidSign;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
