package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 服务
 * @author carr
 *
 */
public class Service extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6068797897632520110L;

	private String serviceId;//序号                               
	private String hotelId;//酒店序号                             
	private Integer serviceCode;//服务代码                         
	private Boolean isIncluded;//是否包含在房费里                  
	private Integer businessServiceCode;//商务服务代码             
	private Integer existsCode;//存在代码                          
	private Boolean isAvailableToAnyGuest;//是否为所有客人提供服务 
	private String inventoryId;//库存序号                         
	private Integer proximityCode;//所在位置                       
	private Integer mealPlanCode;//膳食计划                        
	private Integer quantity;//数量                                
	private Boolean isFeaturedService;//是否特色服务               
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public Integer getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(Integer serviceCode) {
		this.serviceCode = serviceCode;
	}
	public Boolean getIsIncluded() {
		return isIncluded;
	}
	public void setIsIncluded(Boolean isIncluded) {
		this.isIncluded = isIncluded;
	}
	public Integer getBusinessServiceCode() {
		return businessServiceCode;
	}
	public void setBusinessServiceCode(Integer businessServiceCode) {
		this.businessServiceCode = businessServiceCode;
	}
	public Integer getExistsCode() {
		return existsCode;
	}
	public void setExistsCode(Integer existsCode) {
		this.existsCode = existsCode;
	}
	public Boolean getIsAvailableToAnyGuest() {
		return isAvailableToAnyGuest;
	}
	public void setIsAvailableToAnyGuest(Boolean isAvailableToAnyGuest) {
		this.isAvailableToAnyGuest = isAvailableToAnyGuest;
	}
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public Integer getProximityCode() {
		return proximityCode;
	}
	public void setProximityCode(Integer proximityCode) {
		this.proximityCode = proximityCode;
	}
	public Integer getMealPlanCode() {
		return mealPlanCode;
	}
	public void setMealPlanCode(Integer mealPlanCode) {
		this.mealPlanCode = mealPlanCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Boolean getIsFeaturedService() {
		return isFeaturedService;
	}
	public void setIsFeaturedService(Boolean isFeaturedService) {
		this.isFeaturedService = isFeaturedService;
	}
	
	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", hotelId=" + hotelId
				+ ", serviceCode=" + serviceCode + ", isIncluded=" + isIncluded
				+ ", businessServiceCode=" + businessServiceCode
				+ ", existsCode=" + existsCode + ", isAvailableToAnyGuest="
				+ isAvailableToAnyGuest + ", inventoryId=" + inventoryId
				+ ", proximityCode=" + proximityCode + ", mealPlanCode="
				+ mealPlanCode + ", quantity=" + quantity
				+ ", isFeaturedService=" + isFeaturedService + "]";
	}
}
