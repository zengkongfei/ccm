/**
 * 
 */
package com.ccm.api.model.order.vo;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class WBEOrder extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -399039810552332286L;

	private String chainCode;
	private String hotelCode;
	private String language;

	private String roomTypeId;
	private String ratePlanCode;
	private Date arr;
	private Date dep;
	private Integer roomNumber;
	private Integer adult;
	private Integer child;
	private String corporateCode;
	private String promotionCode;
	private String masterId;
	private Boolean enableModifyRN = false;// 房间数是否可修改

	// 担保类型选择预付，显示CCM酒店列表里支付提醒内容
	private String payRemind;
	// 客户选择接机服务，在航班号旁显示CCM酒店列表里接机服务提醒内容
	private String pickUpService;

	private Boolean isAddressRequired = false; // 地址是否必输
	private Boolean isMobileRequired = false; // 手机是否必输
	private Boolean isEmailRequird = false; // email是否必输
	private Integer maxResCount = 3; // 最大预定数

	private String earliestTime;// 最早到达时间
	private String latestTime;// 最晚到达时间
	private String expirationDateY;
	private String expirationDateM;

	private String partner; // 支付宝中的合作身份者ID
	private String merchantid;// mpay合作身份者ID

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public Date getArr() {
		return arr;
	}

	public void setArr(Date arr) {
		this.arr = arr;
	}

	public Date getDep() {
		return dep;
	}

	public void setDep(Date dep) {
		this.dep = dep;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getAdult() {
		return adult;
	}

	public void setAdult(Integer adult) {
		this.adult = adult;
	}

	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}

	public String getCorporateCode() {
		return corporateCode;
	}

	public void setCorporateCode(String corporateCode) {
		this.corporateCode = corporateCode;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public Boolean getEnableModifyRN() {
		return enableModifyRN;
	}

	public void setEnableModifyRN(Boolean enableModifyRN) {
		this.enableModifyRN = enableModifyRN;
	}

	public String getPayRemind() {
		return payRemind;
	}

	public void setPayRemind(String payRemind) {
		this.payRemind = payRemind;
	}

	public String getPickUpService() {
		return pickUpService;
	}

	public void setPickUpService(String pickUpService) {
		this.pickUpService = pickUpService;
	}

	public Boolean getIsAddressRequired() {
		return isAddressRequired;
	}

	public void setIsAddressRequired(Boolean isAddressRequired) {
		this.isAddressRequired = isAddressRequired;
	}

	public Boolean getIsMobileRequired() {
		return isMobileRequired;
	}

	public void setIsMobileRequired(Boolean isMobileRequired) {
		this.isMobileRequired = isMobileRequired;
	}

	public Boolean getIsEmailRequird() {
		return isEmailRequird;
	}

	public void setIsEmailRequird(Boolean isEmailRequird) {
		this.isEmailRequird = isEmailRequird;
	}

	public Integer getMaxResCount() {
		return maxResCount;
	}

	public void setMaxResCount(Integer maxResCount) {
		this.maxResCount = maxResCount;
	}

	public String getExpirationDateY() {
		return expirationDateY;
	}

	public void setExpirationDateY(String expirationDateY) {
		this.expirationDateY = expirationDateY;
	}

	public String getExpirationDateM() {
		return expirationDateM;
	}

	public void setExpirationDateM(String expirationDateM) {
		this.expirationDateM = expirationDateM;
	}

	public String getEarliestTime() {
		return earliestTime;
	}

	public void setEarliestTime(String earliestTime) {
		this.earliestTime = earliestTime;
	}

	public String getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(String latestTime) {
		this.latestTime = latestTime;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	@Override
	public String toString() {
		return "WBEOrder [chainCode=" + chainCode + ", hotelCode=" + hotelCode + ", language=" + language + ", roomTypeId=" + roomTypeId + ", ratePlanCode=" + ratePlanCode + ", arr=" + arr + ", dep=" + dep + ", roomNumber=" + roomNumber + ", adult=" + adult + ", child=" + child + ", corporateCode=" + corporateCode + ", promotionCode=" + promotionCode + ", masterId=" + masterId + ", enableModifyRN=" + enableModifyRN + ", payRemind=" + payRemind + ", pickUpService=" + pickUpService + ", isAddressRequired=" + isAddressRequired + ", isMobileRequired=" + isMobileRequired + ", isEmailRequird=" + isEmailRequird + ", maxResCount=" + maxResCount + ", earliestTime=" + earliestTime + ", latestTime=" + latestTime + ", expirationDateY=" + expirationDateY + ", expirationDateM=" + expirationDateM
				+ ", partner=" + partner + ", merchantid=" + merchantid + "]";
	}

}
