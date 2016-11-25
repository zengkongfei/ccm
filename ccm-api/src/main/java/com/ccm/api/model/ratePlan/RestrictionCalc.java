package com.ccm.api.model.ratePlan;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.BaseObject;

/**
 * 房价开关日历
 * 
 * @author carr
 * 
 */
public class RestrictionCalc extends BaseObject {

	private static final long serialVersionUID = -6010045796678035526L;

	private String restrictionCalcId;// 序号
	private String channelCode;// 渠道代码
	private String chainCode;// 集团代码
	private String hotelCode;// 酒店代码
	private String roomTypeCode;// 房型代码
	private String ratePlanCode;// 房价代码
	private Date date;// 日期
	private Integer onOff;// 房价开关
	private String startDate; // 供查询
	private String endDate;// 供查询

	private List<String> roomTypeCodeList;// 查询使用

	public String getRestrictionCalcId() {
		return restrictionCalcId;
	}

	public void setRestrictionCalcId(String restrictionCalcId) {
		this.restrictionCalcId = restrictionCalcId;
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

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getOnOff() {
		return onOff;
	}

	public void setOnOff(Integer onOff) {
		this.onOff = onOff;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<String> getRoomTypeCodeList() {
		return roomTypeCodeList;
	}

	public void setRoomTypeCodeList(List<String> roomTypeCodeList) {
		this.roomTypeCodeList = roomTypeCodeList;
	}

	@Override
	public String toString() {
		return "RestrictionCalc [restrictionCalcId=" + restrictionCalcId + ", channelCode=" + channelCode + ", chainCode=" + chainCode + ", hotelCode=" + hotelCode + ", roomTypeCode=" + roomTypeCode + ", ratePlanCode=" + ratePlanCode + ", date=" + date + ", onOff=" + onOff + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
