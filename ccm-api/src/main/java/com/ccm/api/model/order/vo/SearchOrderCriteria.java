/**
 * 
 */
package com.ccm.api.model.order.vo;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchCriteria;

/**
 * @author Justin Chen
 */
public class SearchOrderCriteria extends SearchCriteria {

	/**
	 * 
	 */

	private static final long serialVersionUID = 3681085466414249493L;
	private String guestName;
	private String name;// TB使用的姓名
	private String orderNo;
	private String cityName;
	private String orderStatus;// OrderStatus.STATUS_CANCELLED
	private String restype;// reservationStatusType

	private Date stayDate;// 在checkInDate和checkoutDate之间包含此天

	private Date checkinStart;
	private Date checkinEnd;
	private Date checkoutStart;
	private Date checkoutEnd;

	private String username;

	private String hotelId;
	private String hotelCode;

	private Date createStart;
	private Date createEnd;

	private String createStartTime;
	private String createEndTime;

	private String b2cUserMobile;
	private String b2cUserName;
	private String b2cUserTel;

	private String city;// 城市
	private String confirmStatus;// 确认状态
	private String paymentStatus;// 担保类型

	private String status;// 多个状态查询

	private String companyId;

	private List<String> hotelIdList;
	private List<String> masterIdList;
	private List<String> channelCodeList;
	private List<String> statusList; // 订单状态
	private List<String> chainCodeList;
	private List<String> downPmsMsgStatusList; // 订单下传消息状态
	private List<String> roomType; // 房型代码
	private List<String> ratePlan; // 房价代码

	private List<String> actionList;// 消息类型

	private String companyType;// 登录用户的公司类型

	private boolean excelFlag = false;// 导出标志

	private String b2cUserId;

	private String supplierName;// 供应商名称

	private String pmsId;//
	private String channelCode;// 渠道代码
	private String chainCode;// 集团代码

	private List<String> orderNos;

	private List<String> marketList;
	private String market;

	// 预订更新日期
	private Date lastModifyStart;
	private Date lastModifyEnd;

	private Date cancelTimeStart; // 订单取消时间
	private Date cancelTimeEnd; // 订单取消时间

	private String crsno;//渠道订单号
	
	public String getCrsno() {
		return crsno;
	}

	public void setCrsno(String crsno) {
		this.crsno = crsno;
	}

	public Date getCancelTimeStart() {
		return cancelTimeStart;
	}

	public void setCancelTimeStart(Date cancelTimeStart) {
		this.cancelTimeStart = cancelTimeStart;
	}

	public Date getCancelTimeEnd() {
		return cancelTimeEnd;
	}

	public void setCancelTimeEnd(Date cancelTimeEnd) {
		this.cancelTimeEnd = cancelTimeEnd;
	}

	public List<String> getRoomType() {
		return roomType;
	}

	public void setRoomType(List<String> roomType) {
		this.roomType = roomType;
	}

	public List<String> getRatePlan() {
		return ratePlan;
	}

	public void setRatePlan(List<String> ratePlan) {
		this.ratePlan = ratePlan;
	}

	public List<String> getMarketList() {
		return marketList;
	}

	public void setMarketList(List<String> marketList) {
		this.marketList = marketList;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public Date getLastModifyStart() {
		return lastModifyStart;
	}

	public void setLastModifyStart(Date lastModifyStart) {
		this.lastModifyStart = lastModifyStart;
	}

	public Date getLastModifyEnd() {
		return lastModifyEnd;
	}

	public void setLastModifyEnd(Date lastModifyEnd) {
		this.lastModifyEnd = lastModifyEnd;
	}

	public String getPmsId() {
		return pmsId;
	}

	public void setPmsId(String pmsId) {
		this.pmsId = pmsId;
	}

	public List<String> getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(List<String> orderNos) {
		this.orderNos = orderNos;
	}

	/**
	 * @return the guestName
	 */
	public String getGuestName() {
		return guestName;
	}

	/**
	 * @param guestName
	 *            the guestName to set
	 */
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo
	 *            the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus
	 *            the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}

	/**
	 * @return the stayDate
	 */
	public Date getStayDate() {
		return stayDate;
	}

	/**
	 * @param stayDate
	 *            the stayDate to set
	 */
	public void setStayDate(Date stayDate) {
		this.stayDate = stayDate;
	}

	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCheckinStart() {
		return checkinStart;
	}

	public void setCheckinStart(Date checkinStart) {
		this.checkinStart = checkinStart;
	}

	public Date getCheckinEnd() {
		return checkinEnd;
	}

	public void setCheckinEnd(Date checkinEnd) {
		this.checkinEnd = checkinEnd;
	}

	public Date getCheckoutStart() {
		return checkoutStart;
	}

	public void setCheckoutStart(Date checkoutStart) {
		this.checkoutStart = checkoutStart;
	}

	public Date getCheckoutEnd() {
		return checkoutEnd;
	}

	public void setCheckoutEnd(Date checkoutEnd) {
		this.checkoutEnd = checkoutEnd;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public Date getCreateStart() {

		return createStart;
	}

	public void setCreateStart(Date createStart) {
		this.createStart = createStart;
	}

	public Date getCreateEnd() {

		return createEnd;
	}

	public void setCreateEnd(Date createEnd) {
		this.createEnd = createEnd;
	}

	public String getB2cUserMobile() {
		return b2cUserMobile;
	}

	public void setB2cUserMobile(String b2cUserMobile) {
		this.b2cUserMobile = b2cUserMobile;
	}

	public String getB2cUserName() {
		return b2cUserName;
	}

	public void setB2cUserName(String b2cUserName) {
		this.b2cUserName = b2cUserName;
	}

	public String getB2cUserTel() {
		return b2cUserTel;
	}

	public void setB2cUserTel(String b2cUserTel) {
		this.b2cUserTel = b2cUserTel;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public List<String> getHotelIdList() {
		return hotelIdList;
	}

	public void setHotelIdList(List<String> hotelIdList) {
		this.hotelIdList = hotelIdList;
	}

	public List<String> getMasterIdList() {
		return masterIdList;
	}

	public void setMasterIdList(List<String> masterIdList) {
		this.masterIdList = masterIdList;
	}

	/**
	 * @return the companyType
	 */
	public String getCompanyType() {
		return companyType;
	}

	/**
	 * @param companyType
	 *            the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public boolean isExcelFlag() {
		return excelFlag;
	}

	public void setExcelFlag(boolean excelFlag) {
		this.excelFlag = excelFlag;
	}

	public void setB2cUserId(String b2cUserId) {
		this.b2cUserId = b2cUserId;
	}

	public String getB2cUserId() {
		return b2cUserId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getChannelCodeList() {
		return channelCodeList;
	}

	public void setChannelCodeList(List<String> channelCodeList) {
		this.channelCodeList = channelCodeList;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	/**
	 * @return the chainCodeList
	 */
	public List<String> getChainCodeList() {
		return chainCodeList;
	}

	/**
	 * @param chainCodeList
	 *            the chainCodeList to set
	 */
	public void setChainCodeList(List<String> chainCodeList) {
		this.chainCodeList = chainCodeList;
	}

	public List<String> getDownPmsMsgStatusList() {
		return downPmsMsgStatusList;
	}

	public void setDownPmsMsgStatusList(List<String> downPmsMsgStatusList) {
		this.downPmsMsgStatusList = downPmsMsgStatusList;
	}

	public List<String> getActionList() {
		return actionList;
	}

	public void setActionList(List<String> actionList) {
		this.actionList = actionList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchOrderCriteria [guestName=" + guestName + ", name=" + name + ", orderNo=" + orderNo + ", cityName=" + cityName + ", orderStatus=" + orderStatus + ", restype=" + restype + ", stayDate=" + stayDate + ", checkinStart=" + checkinStart + ", checkinEnd=" + checkinEnd + ", checkoutStart=" + checkoutStart + ", checkoutEnd=" + checkoutEnd + ", username=" + username + ", hotelId=" + hotelId + ", hotelCode=" + hotelCode + ", createStart=" + createStart + ", createEnd=" + createEnd + ", createStartTime=" + createStartTime + ", createEndTime=" + createEndTime + ", b2cUserMobile=" + b2cUserMobile + ", b2cUserName=" + b2cUserName + ", b2cUserTel=" + b2cUserTel + ", city=" + city + ", confirmStatus=" + confirmStatus + ", paymentStatus=" + paymentStatus + ", status=" + status
				+ ", companyId=" + companyId + ", hotelIdList=" + hotelIdList + ", masterIdList=" + masterIdList + ", channelCodeList=" + channelCodeList + ", statusList=" + statusList + ", chainCodeList=" + chainCodeList + ", downPmsMsgStatusList=" + downPmsMsgStatusList + ", roomType=" + roomType + ", ratePlan=" + ratePlan + ", actionList=" + actionList + ", companyType=" + companyType + ", excelFlag=" + excelFlag + ", b2cUserId=" + b2cUserId + ", supplierName=" + supplierName + ", pmsId=" + pmsId + ", channelCode=" + channelCode + ", chainCode=" + chainCode + ", orderNos=" + orderNos + ", marketList=" + marketList + ", market=" + market + ", lastModifyStart=" + lastModifyStart + ", lastModifyEnd=" + lastModifyEnd + ", cancelTimeStart=" + cancelTimeStart + ", cancelTimeEnd="
				+ cancelTimeEnd + "]";
	}

}
