/**
 * 
 */
package com.ccm.api.model.order;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class MasterOrder extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7023514470858707654L;

	private String masterId;// 订单号
	private String pmsId = "";// PMS订单号
	private String hotelCode;// 酒店编码
	private Integer numberOfUnits;// 房间数量
	private String nameTitle;// 称谓编码:Mr.-先生,Ms.-女士.OXI中namePrefix
	private String transactionID;// 渠道身份参数
	private String chainCode;// 集团编码
	private String ratePlanCode;// 房价编码
	private String qualifyingIdType;// 订单来源类型,目前为旅行社或公司
	private String qualifyingIdValue;// 旅行社或公司值
	private Integer child = 0;// 小孩数量
	private String cardCode;// 信用卡类型
	private String cardHolderName;// 持卡人姓名
	private String cardNumber;// 信用卡号
	private Date expirationDate;// 有效期
	private String addressType;// 地址类型，参考AddressType
	private String addressLine;// 通讯地址
	private String cityName;// 城市
	private String stateProv;// 省份
	private String countryCode;// 国家编码
	private String postCode;// 邮政编码
	private String business;// 商务电话
	private String home;// 家庭电话
	private String fax;// 传真
	private String email;// 邮箱
	private Date earliestTime;// 最早到店时间
	private Date lastTime;// 最晚到店时间
	private String cancelBy;
	private Date cancelTime; // 订单取消时间
	private String cancelType;
	private String cancelReasonCode;
	private String cancelRef;
	private String pmsType;

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getPmsId() {
		return pmsId;
	}

	public void setPmsId(String pmsId) {
		this.pmsId = pmsId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}

	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public String getNameTitle() {
		return nameTitle;
	}

	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public String getQualifyingIdType() {
		return qualifyingIdType;
	}

	public void setQualifyingIdType(String qualifyingIdType) {
		this.qualifyingIdType = qualifyingIdType;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateProv() {
		return stateProv;
	}

	public void setStateProv(String stateProv) {
		this.stateProv = stateProv;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Date getEarliestTime() {
		return earliestTime;
	}

	public void setEarliestTime(Date earliestTime) {
		this.earliestTime = earliestTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}

	public String getCancelBy() {
		return cancelBy;
	}

	public void setCancelBy(String cancelBy) {
		this.cancelBy = cancelBy;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getCancelType() {
		return cancelType;
	}

	public void setCancelType(String cancelType) {
		this.cancelType = cancelType;
	}

	public String getCancelReasonCode() {
		return cancelReasonCode;
	}

	public void setCancelReasonCode(String cancelReasonCode) {
		this.cancelReasonCode = cancelReasonCode;
	}

	public String getCancelRef() {
		return cancelRef;
	}

	public void setCancelRef(String cancelRef) {
		this.cancelRef = cancelRef;
	}

	public String getPmsType() {
		return pmsType;
	}

	public void setPmsType(String pmsType) {
		this.pmsType = pmsType;
	}

	public String getQualifyingIdValue() {
		return qualifyingIdValue;
	}

	public void setQualifyingIdValue(String qualifyingIdValue) {
		this.qualifyingIdValue = qualifyingIdValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MasterOrder [masterId=" + masterId + ", pmsId=" + pmsId + ", hotelCode=" + hotelCode + ", numberOfUnits=" + numberOfUnits + ", nameTitle=" + nameTitle + ", transactionID=" + transactionID + ", chainCode=" + chainCode + ", ratePlanCode=" + ratePlanCode + ", qualifyingIdType=" + qualifyingIdType + ", qualifyingIdValue=" + qualifyingIdValue + ", child=" + child + ", cardCode=" + cardCode + ", cardHolderName=" + cardHolderName + ", cardNumber=" + cardNumber + ", expirationDate=" + expirationDate + ", addressType=" + addressType + ", addressLine=" + addressLine + ", cityName=" + cityName + ", stateProv=" + stateProv + ", countryCode=" + countryCode + ", postCode=" + postCode + ", business=" + business + ", home=" + home + ", fax=" + fax + ", email=" + email + ", earliestTime="
				+ earliestTime + ", lastTime=" + lastTime + ", cancelBy=" + cancelBy + ", cancelTime=" + cancelTime + ", cancelType=" + cancelType + ", cancelReasonCode=" + cancelReasonCode + ", cancelRef=" + cancelRef + ", pmsType=" + pmsType + "]";
	}

}
