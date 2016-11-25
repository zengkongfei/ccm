package com.ccm.api.model.hotel;

import java.math.BigDecimal;

import com.ccm.api.model.base.BaseObject;

/**
 * 交通方式
 * @author carr
 *
 */
public class Transportation extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 59656503993379691L;

	private String transportationId;//序号            
	private String relativePositionId;//酒店周边序号  
	private Integer transportationCode;//交通方式      
	private Integer chargeUnit;//计费单位              
	private Boolean isIncluded;//是否包含在房费中      
	private String description;//描述                 
	private String typicalTravelTime;//通常耗时       
	private String currencyCode;//货币单位            
	private BigDecimal amount;//价格                      
	private Integer existsCode;//存在代码    
	
	public String getTransportationId() {
		return transportationId;
	}
	public void setTransportationId(String transportationId) {
		this.transportationId = transportationId;
	}
	public String getRelativePositionId() {
		return relativePositionId;
	}
	public void setRelativePositionId(String relativePositionId) {
		this.relativePositionId = relativePositionId;
	}
	public Integer getTransportationCode() {
		return transportationCode;
	}
	public void setTransportationCode(Integer transportationCode) {
		this.transportationCode = transportationCode;
	}
	public Integer getChargeUnit() {
		return chargeUnit;
	}
	public void setChargeUnit(Integer chargeUnit) {
		this.chargeUnit = chargeUnit;
	}
	public Boolean getIsIncluded() {
		return isIncluded;
	}
	public void setIsIncluded(Boolean isIncluded) {
		this.isIncluded = isIncluded;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypicalTravelTime() {
		return typicalTravelTime;
	}
	public void setTypicalTravelTime(String typicalTravelTime) {
		this.typicalTravelTime = typicalTravelTime;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getExistsCode() {
		return existsCode;
	}
	public void setExistsCode(Integer existsCode) {
		this.existsCode = existsCode;
	}
	
	@Override
	public String toString() {
		return "Transportation [transportationId=" + transportationId
				+ ", relativePositionId=" + relativePositionId
				+ ", transportationCode=" + transportationCode
				+ ", chargeUnit=" + chargeUnit + ", isIncluded=" + isIncluded
				+ ", description=" + description + ", typicalTravelTime="
				+ typicalTravelTime + ", currencyCode=" + currencyCode
				+ ", amount=" + amount + ", existsCode=" + existsCode + "]";
	}
}
