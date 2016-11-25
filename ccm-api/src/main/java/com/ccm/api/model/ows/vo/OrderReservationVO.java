package com.ccm.api.model.ows.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ccm.api.model.enume.PaymentTypeEnum;

/**
 * chinaonline预订接口对象
 */
public class OrderReservationVO {

	/**
	 * *创建订单时候传入字段***
	 */
	private String orderNo; // 渠道订单编号
	private String orderConfirmNo;// 发送给switch的渠道订单号
	private List<CheckInPersonVO> checkInPersonList;
	// private List<OrderDailyRateVO> dailyRateList;
	private String hotelId; // 酒店ID
	private String roomtypeId; // 房型代码
	private String priceDefId; // 房价代码
	private int roomNum; // 房间数量
	private Date checkInDate; // 入住时间
	private Date checkOutDate; // 离店时间
	private String comment; // 备注
	private int owsOrderCount; // 渠道订单的总房间数
	private int owsOrderIndex; // current index for the order;
	private Integer paymentType;
	/**
	 * *以下字段存数据库中获取,或修改时候传入**
	 */
	/**/
	// private String pingyingName; //姓名拼音
	private String hotelCode; // 酒店代码
	private String chainCode; // 集团代码
	private String roomtypeCode;// 房型代码
	private String rateCode; // 房价代码
	private int auditCount;// 客户成人数量
	// private int childCount;// 客户小孩数量
	private List<OrderDailyRateVO> roomNumList;

	private Date earliestArriveTime;
	private Date latestArriveTime;

	/**
	 * 修改时候传入
	 */
	private String resevationUniqueID; // chinaonline订单确认号,修改时候使用
	private String cancleCnName; // 取消人姓名
	public String cancelReason; // 取消原因

	private String haccnt;// OTA会员卡卡号

	private String receiveLogId;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomtypeId() {
		return roomtypeId;
	}

	public void setRoomtypeId(String roomtypeId) {
		this.roomtypeId = roomtypeId;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public void addCheckInPerson(CheckInPersonVO checkInPersonVO) {
		if (checkInPersonList == null) {
			checkInPersonList = new ArrayList<CheckInPersonVO>();
		}

		checkInPersonList.add(checkInPersonVO);
	}

	// public void addDailRate(OrderDailyRateVO orderDailyRateVO) {
	//
	// if (dailyRateList == null) {
	// dailyRateList = new ArrayList<OrderDailyRateVO>();
	// }
	//
	//
	// dailyRateList.add(orderDailyRateVO);
	// }

	public List<CheckInPersonVO> getCheckInPersonList() {
		return checkInPersonList;
	}

	public void setCheckInPersonList(List<CheckInPersonVO> checkInPersonList) {
		this.checkInPersonList = checkInPersonList;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPriceDefId() {
		return priceDefId;
	}

	public void setPriceDefId(String priceDefId) {
		this.priceDefId = priceDefId;
	}

	public String getResevationUniqueID() {
		return resevationUniqueID;
	}

	public void setResevationUniqueID(String resevationUniqueID) {
		this.resevationUniqueID = resevationUniqueID;
	}

	public String getCancleCnName() {
		return cancleCnName;
	}

	public void setCancleCnName(String cancleCnName) {
		this.cancleCnName = cancleCnName;
	}

	public List<OrderDailyRateVO> getRoomNumList() {
		return roomNumList;
	}

	public void setRoomNumList(List<OrderDailyRateVO> roomNumList) {
		this.roomNumList = roomNumList;
	}

	public int getOwsOrderCount() {
		return owsOrderCount;
	}

	public void setOwsOrderCount(int owsOrderCount) {
		this.owsOrderCount = owsOrderCount;
	}

	public int getOwsOrderIndex() {
		return owsOrderIndex;
	}

	public void setOwsOrderIndex(int owsOrderIndex) {
		this.owsOrderIndex = owsOrderIndex;
	}

	public String generateOwsOrderNo() {
		if (isOneOwsOrder()) {
			return this.getOrderNo();
		} else {
			return owsOrderNo(this.getOrderNo(), this.getOwsOrderIndex());
		}

	}

	public static String owsOrderNo(String orderNo, int index) {
		return orderNo + "R" + index;
	}

	public boolean isOneOwsOrder() {
		return this.getOwsOrderCount() == 1;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getRoomtypeCode() {
		return roomtypeCode;
	}

	public void setRoomtypeCode(String roomtypeCode) {
		this.roomtypeCode = roomtypeCode;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Date getEarliestArriveTime() {
		return earliestArriveTime;
	}

	public void setEarliestArriveTime(Date earliestArriveTime) {
		this.earliestArriveTime = earliestArriveTime;
	}

	public Date getLatestArriveTime() {
		return latestArriveTime;
	}

	public void setLatestArriveTime(Date latestArriveTime) {
		this.latestArriveTime = latestArriveTime;
	}

	public String getOrderConfirmNo() {
		return orderConfirmNo;
	}

	public void setOrderConfirmNo(String orderConfirmNo) {
		this.orderConfirmNo = orderConfirmNo;
	}

	public int getAuditCount() {
		return auditCount;
	}

	public void setAuditCount(int auditCount) {
		this.auditCount = auditCount;
	}

	public String getHaccnt() {
		return haccnt;
	}

	public void setHaccnt(String haccnt) {
		this.haccnt = haccnt;
	}

	public String getReceiveLogId() {
		return receiveLogId;
	}

	public void setReceiveLogId(String receiveLogId) {
		this.receiveLogId = receiveLogId;
	}

	public String getGuaranteeType() {
		if (PaymentTypeEnum.HOTELPAY.getId() == this.paymentType) {
			return "6PM";
		} else if (PaymentTypeEnum.PREPAID.getId() == this.paymentType) {
			return "PREPAID";
		} else if (PaymentTypeEnum.PRE.getId() == this.paymentType) {
			return "TAGTD";
		}
		return "6PM";
	}

}
