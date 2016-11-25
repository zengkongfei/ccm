package com.ccm.api.model.ws.vo.fetchCalendar;

import java.math.BigDecimal;
import java.util.Date;

public class FetchCalendarRSVO {
	private String resultStatusFlag;//结果状态
	private Date date;//日期
	private String roomTypeCode;//房型代码
	private String ratePlanCode;//房价代码
	private BigDecimal rate;//当日房费
	private BigDecimal additionalGuestAmount;//加床价格
	private Integer totalRooms;//房间总数
	private Integer totalAvailableRooms;//可用房量
	private String additionalGuestType;//客人类型
	
	public String getResultStatusFlag() {
		return resultStatusFlag;
	}
	public void setResultStatusFlag(String resultStatusFlag) {
		this.resultStatusFlag = resultStatusFlag;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getAdditionalGuestAmount() {
		return additionalGuestAmount;
	}
	public void setAdditionalGuestAmount(BigDecimal additionalGuestAmount) {
		this.additionalGuestAmount = additionalGuestAmount;
	}
	public Integer getTotalRooms() {
		return totalRooms;
	}
	public void setTotalRooms(Integer totalRooms) {
		this.totalRooms = totalRooms;
	}
	public Integer getTotalAvailableRooms() {
		return totalAvailableRooms;
	}
	public void setTotalAvailableRooms(Integer totalAvailableRooms) {
		this.totalAvailableRooms = totalAvailableRooms;
	}
	public String getAdditionalGuestType() {
		return additionalGuestType;
	}
	public void setAdditionalGuestType(String additionalGuestType) {
		this.additionalGuestType = additionalGuestType;
	}
	
	@Override
	public String toString() {
		return "FetchCalendarRSVO [resultStatusFlag=" + resultStatusFlag
				+ ", date=" + date + ", roomTypeCode=" + roomTypeCode
				+ ", ratePlanCode=" + ratePlanCode + ", rate=" + rate
				+ ", additionalGuestAmount=" + additionalGuestAmount
				+ ", totalRooms=" + totalRooms + ", totalAvailableRooms="
				+ totalAvailableRooms + ", additionalGuestType="
				+ additionalGuestType + "]";
	}
}
