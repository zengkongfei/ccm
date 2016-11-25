/**
 * 
 */
package com.ccm.api.model.hotel;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class Hotel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1175646623758773888L;

	private String hotelId;// 序号
	private String countryCode;
	private Long privinceCode; // 省份编码
	private Long areaCode;// 区域（县级市）编码
	private Long city;// 城市编码
	private Integer star;
	private String level;
	private String orientation;
	private String postCode;
	private Date openingTime;
	private Date decorateTime;
	private Integer storeys; // 楼层数
	private Integer rooms; // 房间数
	private Integer beds; // 床数
	private Date addTime;
	private Date delTime;
	private Integer status;
	private Integer cityCenterDistance;
	private Integer railwayDistance;
	private Integer airportDistance;
	private Boolean domestic = Boolean.FALSE;// 是否国内酒店。0:国内;1:国外
	private String position_type;// 坐标类型,G-Google,B-百度, A-高德, M-Mapbar,L-灵图
	private String bizId;// 酒店图片ID
	private String houseOverbook;
	private String officialWebsite;// 酒店官网
	/*** 一下为新表字段 */
	private String chainId;// 集团序号
	private String brandId;// 品牌序号
	private String hotelCode;// 酒店代码
	private Date whenBuilt;// 建造时间
	private Integer hotelStatusCode;// 酒店状态
	private Boolean isDaylightSaving;// 是否有夏令时
	private String longitude;// 经度
	private String latitude;// 纬度
	private String altitude;// 海拔
	private String pmsType;
	private String email;// email
	private String telephone;// 电话
	private String currencyCode; // 货币类型
	private String fax; // 传真
	private Date checkInTime; // checkInTime
	private Date checkOutTime; // checkOutTime;
	private Date effectiveDate; // 生效时间
	private Date expireDate; // 失效时间
	private Boolean isNegotiate; // 是否为协议酒店
	private Boolean isDirectPms = Boolean.TRUE;// 与PMS直连
	private Boolean isUpdateIdent = Boolean.TRUE;// PMS上传订单信息时是否更新身份证
	private String homePage; // 酒店主页
	private String reservationPage; // 在线预订页面
	private String dateFormat; // 日期格式
	private String paymentMethod; // 支付方式

	private String smsUserId; // 短信发送的用户名
	private String smsPassword; // 短信发送的密码
	private String partner; // 支付宝中的合作身份者ID
	private String checkCode; // 交易安全检验码
	private String sellerEmail; // 签约支付宝账号或卖家收款支付宝帐户
	private String merchantid;// mpay合作身份者ID
	private String merchant_tid;// mpay的Terminal ID
	private String securekey;// mpay的安全检验码
	private String displayMode; // 酒店显示方式
	private String logoPicId; // logo图片ID

	private Boolean isAddressRequired; // 地址是否必输
	private Boolean isMobileRequired; // 手机是否必输
	private Boolean isEmailRequird; // email是否必输
	private Integer maxResCount; // 最大预定数

	private Boolean orderRemind;// 订单提醒
	private Boolean messRemind;// 留言提醒

	private Boolean isSupportChinese; // 酒店预订系统可支持的语种
	private Boolean isSupportEnglish; // 酒店预订系统可支持的语种
	private Boolean isSupportJapanese; // 酒店预订系统可支持的语种

	private String specialist; // 酒店维护专员

	private String cityName;
	private String tbShopName;// 淘宝店铺名称
	private String hotelPushUrl;// 静态数据推送地址
	private Boolean isCreditOnlineHotel;// 是否为“上线信用住”
	private Boolean isDisplacementInterface;// 是否为“置换机”
	private String defCancelId;
	private String defGuaranteeId;
	// 酒店样式css文件
	private String cssFileId;

	private String remindEmail;// 提醒邮件
	private String remindEfax;// 提醒传真
	private Boolean isPMSHeartBeat;// 是否监控PMS心跳
	private String sms;// 手机号
	private Date effectiveTime;// 发动短信提醒的有效时间 格式为时分秒
	private Date expireTime;// 发动短信提醒的失效时间 格式为时分秒
	private Boolean isMasterListener = Boolean.FALSE;// 是否监控订单

	private String allotNotificationEmail;

	private String pmsAccount;//PMS使用者账号
	public String getPmsAccount() {
		return pmsAccount;
	}
	public void setPmsAccount(String pmsAccount) {
		this.pmsAccount = pmsAccount;
	}

	public String getAllotNotificationEmail() {
		return allotNotificationEmail;
	}

	public void setAllotNotificationEmail(String allotNotificationEmail) {
		this.allotNotificationEmail = allotNotificationEmail;
	}

	public String getCssFileId() {
		return cssFileId;
	}

	public void setCssFileId(String cssFileId) {
		this.cssFileId = cssFileId;
	}

	public String getDefCancelId() {
		return defCancelId;
	}

	public void setDefCancelId(String defCancelId) {
		this.defCancelId = defCancelId;
	}

	public String getDefGuaranteeId() {
		return defGuaranteeId;
	}

	public void setDefGuaranteeId(String defGuaranteeId) {
		this.defGuaranteeId = defGuaranteeId;
	}

	public Boolean getIsCreditOnlineHotel() {
		return isCreditOnlineHotel;
	}

	public void setIsCreditOnlineHotel(Boolean isCreditOnlineHotel) {
		this.isCreditOnlineHotel = isCreditOnlineHotel;
	}

	public Boolean getIsDisplacementInterface() {
		return isDisplacementInterface;
	}

	public void setIsDisplacementInterface(Boolean isDisplacementInterface) {
		this.isDisplacementInterface = isDisplacementInterface;
	}

	public String getOfficialWebsite() {
		return officialWebsite;
	}

	public void setOfficialWebsite(String officialWebsite) {
		this.officialWebsite = officialWebsite;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Long getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	public Date getDecorateTime() {
		return decorateTime;
	}

	public void setDecorateTime(Date decorateTime) {
		this.decorateTime = decorateTime;
	}

	public Integer getStoreys() {
		return storeys;
	}

	public void setStoreys(Integer storeys) {
		this.storeys = storeys;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCityCenterDistance() {
		return cityCenterDistance;
	}

	public void setCityCenterDistance(Integer cityCenterDistance) {
		this.cityCenterDistance = cityCenterDistance;
	}

	public Integer getRailwayDistance() {
		return railwayDistance;
	}

	public void setRailwayDistance(Integer railwayDistance) {
		this.railwayDistance = railwayDistance;
	}

	public Integer getAirportDistance() {
		return airportDistance;
	}

	public void setAirportDistance(Integer airportDistance) {
		this.airportDistance = airportDistance;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public Date getWhenBuilt() {
		return whenBuilt;
	}

	public void setWhenBuilt(Date whenBuilt) {
		this.whenBuilt = whenBuilt;
	}

	public Integer getHotelStatusCode() {
		return hotelStatusCode;
	}

	public void setHotelStatusCode(Integer hotelStatusCode) {
		this.hotelStatusCode = hotelStatusCode;
	}

	public Boolean getIsDaylightSaving() {
		return isDaylightSaving;
	}

	public void setIsDaylightSaving(Boolean isDaylightSaving) {
		this.isDaylightSaving = isDaylightSaving;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getPmsType() {
		return pmsType;
	}

	public void setPmsType(String pmsType) {
		this.pmsType = pmsType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getDomestic() {
		return domestic;
	}

	public void setDomestic(Boolean domestic) {
		this.domestic = domestic;
	}

	public String getPosition_type() {
		return position_type;
	}

	public void setPosition_type(String position_type) {
		this.position_type = position_type;
	}

	public String getHouseOverbook() {
		return houseOverbook;
	}

	public void setHouseOverbook(String houseOverbook) {
		this.houseOverbook = houseOverbook;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Integer getBeds() {
		return beds;
	}

	public void setBeds(Integer beds) {
		this.beds = beds;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
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

	public Boolean getIsNegotiate() {
		return isNegotiate;
	}

	public void setIsNegotiate(Boolean isNegotiate) {
		this.isNegotiate = isNegotiate;
	}

	public Boolean getIsDirectPms() {
		return isDirectPms;
	}

	public void setIsDirectPms(Boolean isDirectPms) {
		this.isDirectPms = isDirectPms;
	}

	/**
	 * @return the isUpdateIdent
	 */
	public Boolean getIsUpdateIdent() {
		return isUpdateIdent;
	}

	/**
	 * @param isUpdateIdent
	 *            the isUpdateIdent to set
	 */
	public void setIsUpdateIdent(Boolean isUpdateIdent) {
		this.isUpdateIdent = isUpdateIdent;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getReservationPage() {
		return reservationPage;
	}

	public void setReservationPage(String reservationPage) {
		this.reservationPage = reservationPage;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Long getPrivinceCode() {
		return privinceCode;
	}

	public void setPrivinceCode(Long privinceCode) {
		this.privinceCode = privinceCode;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getSmsUserId() {
		return smsUserId;
	}

	public void setSmsUserId(String smsUserId) {
		this.smsUserId = smsUserId;
	}

	public String getSmsPassword() {
		return smsPassword;
	}

	public void setSmsPassword(String smsPassword) {
		this.smsPassword = smsPassword;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getMerchant_tid() {
		return merchant_tid;
	}

	public void setMerchant_tid(String merchant_tid) {
		this.merchant_tid = merchant_tid;
	}

	public String getSecurekey() {
		return securekey;
	}

	public void setSecurekey(String securekey) {
		this.securekey = securekey;
	}

	public String getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}

	public String getLogoPicId() {
		return logoPicId;
	}

	public void setLogoPicId(String logoPicId) {
		this.logoPicId = logoPicId;
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

	public Boolean getOrderRemind() {
		return orderRemind;
	}

	public void setOrderRemind(Boolean orderRemind) {
		this.orderRemind = orderRemind;
	}

	public Boolean getMessRemind() {
		return messRemind;
	}

	public void setMessRemind(Boolean messRemind) {
		this.messRemind = messRemind;
	}

	public Boolean getIsSupportChinese() {
		return isSupportChinese;
	}

	public void setIsSupportChinese(Boolean isSupportChinese) {
		this.isSupportChinese = isSupportChinese;
	}

	public Boolean getIsSupportEnglish() {
		return isSupportEnglish;
	}

	public void setIsSupportEnglish(Boolean isSupportEnglish) {
		this.isSupportEnglish = isSupportEnglish;
	}

	public Boolean getIsSupportJapanese() {
		return isSupportJapanese;
	}

	public void setIsSupportJapanese(Boolean isSupportJapanese) {
		this.isSupportJapanese = isSupportJapanese;
	}

	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getTbShopName() {
		return tbShopName;
	}

	public void setTbShopName(String tbShopName) {
		this.tbShopName = tbShopName;
	}

	public String getHotelPushUrl() {
		return hotelPushUrl;
	}

	public void setHotelPushUrl(String hotelPushUrl) {
		this.hotelPushUrl = hotelPushUrl;
	}

	public String getRemindEmail() {
		return remindEmail;
	}

	public void setRemindEmail(String remindEmail) {
		this.remindEmail = remindEmail;
	}

	public String getRemindEfax() {
		return remindEfax;
	}

	public void setRemindEfax(String remindEfax) {
		this.remindEfax = remindEfax;
	}

	public Boolean getIsPMSHeartBeat() {
		return isPMSHeartBeat;
	}

	public void setIsPMSHeartBeat(Boolean isPMSHeartBeat) {
		this.isPMSHeartBeat = isPMSHeartBeat;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Boolean getIsMasterListener() {
		return isMasterListener;
	}

	public void setIsMasterListener(Boolean isMasterListener) {
		this.isMasterListener = isMasterListener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", countryCode=" + countryCode + ", privinceCode=" + privinceCode + ", areaCode=" + areaCode + ", city=" + city + ", star=" + star + ", level=" + level + ", orientation=" + orientation + ", postCode=" + postCode + ", openingTime=" + openingTime + ", decorateTime=" + decorateTime + ", storeys=" + storeys + ", rooms=" + rooms + ", beds=" + beds + ", addTime=" + addTime + ", delTime=" + delTime + ", status=" + status + ", cityCenterDistance=" + cityCenterDistance + ", railwayDistance=" + railwayDistance + ", airportDistance=" + airportDistance + ", domestic=" + domestic + ", position_type=" + position_type + ", bizId=" + bizId + ", houseOverbook=" + houseOverbook + ", officialWebsite=" + officialWebsite + ", chainId=" + chainId + ", brandId="
				+ brandId + ", hotelCode=" + hotelCode + ", whenBuilt=" + whenBuilt + ", hotelStatusCode=" + hotelStatusCode + ", isDaylightSaving=" + isDaylightSaving + ", longitude=" + longitude + ", latitude=" + latitude + ", altitude=" + altitude + ", pmsType=" + pmsType + ", email=" + email + ", telephone=" + telephone + ", currencyCode=" + currencyCode + ", fax=" + fax + ", checkInTime=" + checkInTime + ", checkOutTime=" + checkOutTime + ", effectiveDate=" + effectiveDate + ", expireDate=" + expireDate + ", isNegotiate=" + isNegotiate + ", isDirectPms=" + isDirectPms + ", isUpdateIdent=" + isUpdateIdent + ", homePage=" + homePage + ", reservationPage=" + reservationPage + ", dateFormat=" + dateFormat + ", paymentMethod=" + paymentMethod + ", smsUserId=" + smsUserId
				+ ", smsPassword=" + smsPassword + ", partner=" + partner + ", checkCode=" + checkCode + ", sellerEmail=" + sellerEmail + ", merchantid=" + merchantid + ", merchant_tid=" + merchant_tid + ", securekey=" + securekey + ", displayMode=" + displayMode + ", logoPicId=" + logoPicId + ", isAddressRequired=" + isAddressRequired + ", isMobileRequired=" + isMobileRequired + ", isEmailRequird=" + isEmailRequird + ", maxResCount=" + maxResCount + ", orderRemind=" + orderRemind + ", messRemind=" + messRemind + ", isSupportChinese=" + isSupportChinese + ", isSupportEnglish=" + isSupportEnglish + ", isSupportJapanese=" + isSupportJapanese + ", specialist=" + specialist + ", cityName=" + cityName + ", tbShopName=" + tbShopName + ", hotelPushUrl=" + hotelPushUrl + ", isCreditOnlineHotel="
				+ isCreditOnlineHotel + ", isDisplacementInterface=" + isDisplacementInterface + ", defCancelId=" + defCancelId + ", defGuaranteeId=" + defGuaranteeId + ", cssFileId=" + cssFileId + ", remindEmail=" + remindEmail + ", remindEfax=" + remindEfax + ", isPMSHeartBeat=" + isPMSHeartBeat + ", sms=" + sms + ", effectiveTime=" + effectiveTime + ", expireTime=" + expireTime + ", isMasterListener=" + isMasterListener + ", allotNotificationEmail=" + allotNotificationEmail + "]";
	}

}
