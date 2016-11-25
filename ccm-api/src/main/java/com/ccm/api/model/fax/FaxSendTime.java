package com.ccm.api.model.fax;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 传真时间
 * @author chay
 *
 */
public class FaxSendTime extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 582158730413100758L;
	private String faxSendTimeId;//PK
	private String hotelId;//酒店id
	private String faxNumber;//传真号码
	private Date beginTime;//开始时间  
	private Date endTime;//结束时间
	private Date time;//查询时，用time 作为时间参数
	public String getFaxSendTimeId() {
		return faxSendTimeId;
	}
	public void setFaxSendTimeId(String faxSendTimeId) {
		this.faxSendTimeId = faxSendTimeId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "FaxSendTime [faxSendTimeId=" + faxSendTimeId + ", hotelId="
				+ hotelId + ", faxNumber=" + faxNumber + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", time=" + time + "]";
	}
	
}
