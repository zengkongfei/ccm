package com.ccm.api.model.email;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.order.MasterPackage;
import com.ccm.api.model.order.MasterRate;

public class MasterSendLog extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3307545473907872093L;
	
	private String masterSendLogId;
	private String smsSendId;
	private String masterId;
	private String language;
	private String hotelId;
	private String hotelCode;
	private String hotelName;
	private String name4;
	private String telephone;
	private String fax;
	private String postCode;
	private String email;
	private String address;
	private String sta;
	private Date changed;
	private Date arr;
	private Date dep;
	private String roomTypeName;
	private String ratePlanDesc;
	private Integer gstno;
	private String mrListJson;
	private String mpListJson;
	private BigDecimal charge;
	private BigDecimal chargeAfterTax;
	private String srqs;
	private String ref;
	private String checkInTimeDesc;
	private String checkOutTimeDesc;
	private String cancelPolicyDesc;
	private String pictureUrlFolder;
	private String logoPicUrl;
	private String channelCode;
	private String currencyCode;// 币种代码
	
	// 支付类型(付款方式),担保类型guaranteeType
	private String payment;
	//信用卡类型  
	private String cardCode;
	
	//master mobile
	private String mobile;

	//邮箱（客户的）	
	private String masterEmail;
	//地址（客户的） 
	private String addressLine;
	//房间数
	private Integer numberOfUnits;
	
	
	private List<MasterRate> mrList = new ArrayList<MasterRate>();// 房价明细
	private List<MasterPackage> mpList = new ArrayList<MasterPackage>();// 包价明细
	
	
	public BigDecimal getChargeAfterTax() {
		return chargeAfterTax;
	}
	public void setChargeAfterTax(BigDecimal chargeAfterTax) {
		this.chargeAfterTax = chargeAfterTax;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getMasterEmail() {
		return masterEmail;
	}
	public void setMasterEmail(String masterEmail) {
		this.masterEmail = masterEmail;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getMasterSendLogId() {
		return masterSendLogId;
	}
	public void setMasterSendLogId(String masterSendLogId) {
		this.masterSendLogId = masterSendLogId;
	}
	public String getSmsSendId() {
		return smsSendId;
	}
	public void setSmsSendId(String smsSendId) {
		this.smsSendId = smsSendId;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getName4() {
		return name4;
	}
	public void setName4(String name4) {
		this.name4 = name4;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public Date getChanged() {
		return changed;
	}
	public void setChanged(Date changed) {
		this.changed = changed;
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
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	public String getRatePlanDesc() {
		return ratePlanDesc;
	}
	public void setRatePlanDesc(String ratePlanDesc) {
		this.ratePlanDesc = ratePlanDesc;
	}
	public Integer getGstno() {
		return gstno;
	}
	public void setGstno(Integer gstno) {
		this.gstno = gstno;
	}
	public String getMrListJson() {
		return mrListJson;
	}
	public void setMrListJson(String mrListJson) {
		this.mrListJson = mrListJson;
	}
	public String getMpListJson() {
		return mpListJson;
	}
	public void setMpListJson(String mpListJson) {
		this.mpListJson = mpListJson;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public String getSrqs() {
		return srqs;
	}
	public void setSrqs(String srqs) {
		this.srqs = srqs;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getCheckInTimeDesc() {
		return checkInTimeDesc;
	}
	public void setCheckInTimeDesc(String checkInTimeDesc) {
		this.checkInTimeDesc = checkInTimeDesc;
	}
	public String getCheckOutTimeDesc() {
		return checkOutTimeDesc;
	}
	public void setCheckOutTimeDesc(String checkOutTimeDesc) {
		this.checkOutTimeDesc = checkOutTimeDesc;
	}
	public String getCancelPolicyDesc() {
		return cancelPolicyDesc;
	}
	public void setCancelPolicyDesc(String cancelPolicyDesc) {
		this.cancelPolicyDesc = cancelPolicyDesc;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public List<MasterRate> getMrList() {
		return mrList;
	}
	public void setMrList(List<MasterRate> mrList) {
		this.mrList = mrList;
	}
	public List<MasterPackage> getMpList() {
		return mpList;
	}
	public void setMpList(List<MasterPackage> mpList) {
		this.mpList = mpList;
	}
	public String getPictureUrlFolder() {
		return pictureUrlFolder;
	}
	public void setPictureUrlFolder(String pictureUrlFolder) {
		this.pictureUrlFolder = pictureUrlFolder;
	}
	public String getLogoPicUrl() {
		return logoPicUrl;
	}
	public void setLogoPicUrl(String logoPicUrl) {
		this.logoPicUrl = logoPicUrl;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	

}
