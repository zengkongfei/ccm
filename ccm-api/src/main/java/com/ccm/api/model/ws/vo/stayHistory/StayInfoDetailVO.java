package com.ccm.api.model.ws.vo.stayHistory;

import java.util.Date;

public class StayInfoDetailVO {

	private String hotelCode;      //酒店Code
	private String confirmationNo; //订单确认号
	private String crsResID;	   //crsResID
	private String pmsResId;       //pms返回的Id
	private String status;         //订单状态
	private String guestFirstName; //客人名（英文或拼音）
	private String guestLastName;  //客人姓（英文或拼音）
	private Date   arrival;        //实际到店时间
	private Date   departure;      //实际离店时间
	private String roomType;       //房型代码
	private String RoomDesc;       //房型描述
	private String rateCode;       //房价编码
	private Float rate;           //客人实际入住的首日房价
	private String channel;        //渠道编码
	private Date resDate;        //订单最后修改时间
	private String roomNo;         //房间号
	private Integer guestID;       //客户号
	private String guestNativeName;  //客户本地名字
	private String remark;           //备注
	private Float roomRev;          //订单总额
	
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getConfirmationNo() {
		return confirmationNo;
	}
	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}
	public String getPmsResId() {
		return pmsResId;
	}
	public void setPmsResId(String pmsResId) {
		this.pmsResId = pmsResId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGuestFirstName() {
		return guestFirstName;
	}
	public void setGuestFirstName(String guestFirstName) {
		this.guestFirstName = guestFirstName;
	}
	public String getGuestLastName() {
		return guestLastName;
	}
	public void setGuestLastName(String guestLastName) {
		this.guestLastName = guestLastName;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomDesc() {
		return RoomDesc;
	}
	public void setRoomDesc(String roomDesc) {
		RoomDesc = roomDesc;
	}
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Date getResDate() {
		return resDate;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getCrsResID() {
		return crsResID;
	}
	public void setCrsResID(String crsResID) {
		this.crsResID = crsResID;
	}
	public Integer getGuestID() {
		return guestID;
	}
	public void setGuestID(Integer guestID) {
		this.guestID = guestID;
	}
	public String getGuestNativeName() {
		return guestNativeName;
	}
	public void setGuestNativeName(String guestNativeName) {
		this.guestNativeName = guestNativeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Float getRoomRev() {
		return roomRev;
	}
	public void setRoomRev(Float roomRev) {
		this.roomRev = roomRev;
	}
	
	
	
}
