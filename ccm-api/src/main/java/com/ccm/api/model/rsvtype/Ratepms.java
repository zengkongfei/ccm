package com.ccm.api.model.rsvtype;

import java.math.BigDecimal;
import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 房价日历明细表
 * @author carr
 *
 */
public class Ratepms extends BaseObject {
	
	private static final long serialVersionUID = -6729687789827902244L;
	
	private String ratepmsId;//	主键
	private String hotelCode;//	酒店代码
	private String rateCode;//	房价代码
	private Date startDate;//	开始日期
	private Date endDate;//	结束日期
	private String roomTypeList;//	房型代码列表
	private BigDecimal rate1;//	一人价（成人）
	private BigDecimal rate2;//	二人价（成人）
	private BigDecimal rate3;//	三人价（成人）
	private BigDecimal rate4;//	四人价（成人）
	private BigDecimal rate5;//	五人价（成人）
	private BigDecimal child1RateParentRoom;//	一人价（成人/小孩）
	private BigDecimal child2RateParentRoom;//	二人价（成人/小孩）
	private BigDecimal child3RateParentRoom;//	三人价（成人/小孩）
	private BigDecimal child1RateOwnRoom;//	一人价（小孩）
	private BigDecimal child2RateOwnRoom;//	二人价（小孩）
	private BigDecimal child3RateOwnRoom;//	三人价（小孩）
	private BigDecimal child4RateOwnRoom;//	四人价（小孩）
	private BigDecimal extraBed;//	扩展床价格
	private Boolean monday;//	周一
	private Boolean tuesday;//	周二
	private Boolean wednesday;//	周三
	private Boolean thursday;//	周四
	private Boolean friday;//	周五
	private Boolean saturday;//	周六
	private Boolean sunday;//	周日
	private Integer payingChildren;//	可住小孩数
	private Integer freeChildren;//	免住小孩数
	private Integer status;//	0:数据未处理；1：数据处理完成
	private String marketCode;// 市场代码
	
	public String getRatepmsId() {
		return ratepmsId;
	}
	public void setRatepmsId(String ratepmsId) {
		this.ratepmsId = ratepmsId;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
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
	public String getRoomTypeList() {
		return roomTypeList;
	}
	public void setRoomTypeList(String roomTypeList) {
		this.roomTypeList = roomTypeList;
	}
	public BigDecimal getRate1() {
		return rate1;
	}
	public void setRate1(BigDecimal rate1) {
		this.rate1 = rate1;
	}
	public BigDecimal getRate2() {
		return rate2;
	}
	public void setRate2(BigDecimal rate2) {
		this.rate2 = rate2;
	}
	public BigDecimal getRate3() {
		return rate3;
	}
	public void setRate3(BigDecimal rate3) {
		this.rate3 = rate3;
	}
	public BigDecimal getRate4() {
		return rate4;
	}
	public void setRate4(BigDecimal rate4) {
		this.rate4 = rate4;
	}
	public BigDecimal getRate5() {
		return rate5;
	}
	public void setRate5(BigDecimal rate5) {
		this.rate5 = rate5;
	}
	public BigDecimal getChild1RateParentRoom() {
		return child1RateParentRoom;
	}
	public void setChild1RateParentRoom(BigDecimal child1RateParentRoom) {
		this.child1RateParentRoom = child1RateParentRoom;
	}
	public BigDecimal getChild2RateParentRoom() {
		return child2RateParentRoom;
	}
	public void setChild2RateParentRoom(BigDecimal child2RateParentRoom) {
		this.child2RateParentRoom = child2RateParentRoom;
	}
	public BigDecimal getChild3RateParentRoom() {
		return child3RateParentRoom;
	}
	public void setChild3RateParentRoom(BigDecimal child3RateParentRoom) {
		this.child3RateParentRoom = child3RateParentRoom;
	}
	public BigDecimal getChild1RateOwnRoom() {
		return child1RateOwnRoom;
	}
	public void setChild1RateOwnRoom(BigDecimal child1RateOwnRoom) {
		this.child1RateOwnRoom = child1RateOwnRoom;
	}
	public BigDecimal getChild2RateOwnRoom() {
		return child2RateOwnRoom;
	}
	public void setChild2RateOwnRoom(BigDecimal child2RateOwnRoom) {
		this.child2RateOwnRoom = child2RateOwnRoom;
	}
	public BigDecimal getChild3RateOwnRoom() {
		return child3RateOwnRoom;
	}
	public void setChild3RateOwnRoom(BigDecimal child3RateOwnRoom) {
		this.child3RateOwnRoom = child3RateOwnRoom;
	}
	public BigDecimal getChild4RateOwnRoom() {
		return child4RateOwnRoom;
	}
	public void setChild4RateOwnRoom(BigDecimal child4RateOwnRoom) {
		this.child4RateOwnRoom = child4RateOwnRoom;
	}
	public BigDecimal getExtraBed() {
		return extraBed;
	}
	public void setExtraBed(BigDecimal extraBed) {
		this.extraBed = extraBed;
	}
	public Boolean getMonday() {
		return monday;
	}
	public void setMonday(Boolean monday) {
		this.monday = monday;
	}
	public Boolean getTuesday() {
		return tuesday;
	}
	public void setTuesday(Boolean tuesday) {
		this.tuesday = tuesday;
	}
	public Boolean getWednesday() {
		return wednesday;
	}
	public void setWednesday(Boolean wednesday) {
		this.wednesday = wednesday;
	}
	public Boolean getThursday() {
		return thursday;
	}
	public void setThursday(Boolean thursday) {
		this.thursday = thursday;
	}
	public Boolean getFriday() {
		return friday;
	}
	public void setFriday(Boolean friday) {
		this.friday = friday;
	}
	public Boolean getSaturday() {
		return saturday;
	}
	public void setSaturday(Boolean saturday) {
		this.saturday = saturday;
	}
	public Boolean getSunday() {
		return sunday;
	}
	public void setSunday(Boolean sunday) {
		this.sunday = sunday;
	}
	public Integer getPayingChildren() {
		return payingChildren;
	}
	public void setPayingChildren(Integer payingChildren) {
		this.payingChildren = payingChildren;
	}
	public Integer getFreeChildren() {
		return freeChildren;
	}
	public void setFreeChildren(Integer freeChildren) {
		this.freeChildren = freeChildren;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	
	@Override
	public String toString() {
		return "Ratepms [ratepmsId=" + ratepmsId + ", hotelCode=" + hotelCode
				+ ", rateCode=" + rateCode + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", roomTypeList=" + roomTypeList
				+ ", rate1=" + rate1 + ", rate2=" + rate2 + ", rate3=" + rate3
				+ ", rate4=" + rate4 + ", rate5=" + rate5
				+ ", child1RateParentRoom=" + child1RateParentRoom
				+ ", child2RateParentRoom=" + child2RateParentRoom
				+ ", child3RateParentRoom=" + child3RateParentRoom
				+ ", child1RateOwnRoom=" + child1RateOwnRoom
				+ ", child2RateOwnRoom=" + child2RateOwnRoom
				+ ", child3RateOwnRoom=" + child3RateOwnRoom
				+ ", child4RateOwnRoom=" + child4RateOwnRoom + ", extraBed="
				+ extraBed + ", monday=" + monday + ", tuesday=" + tuesday
				+ ", wednesday=" + wednesday + ", thursday=" + thursday
				+ ", friday=" + friday + ", saturday=" + saturday + ", sunday="
				+ sunday + ", payingChildren=" + payingChildren
				+ ", freeChildren=" + freeChildren + ", status=" + status
				+ ", marketCode=" + marketCode + "]";
	}
}
