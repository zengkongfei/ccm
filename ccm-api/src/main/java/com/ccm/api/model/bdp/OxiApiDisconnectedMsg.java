package com.ccm.api.model.bdp;

import java.io.Serializable;
import java.util.Date;

public class OxiApiDisconnectedMsg implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4371066615169286558L;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String id;
	private String hotelCode;
	private String hotelName;
	private Integer numberOfTimes;
	private Long disconnectedQuantum;
	private String msgDate;
	private String isMonitorHeartBeat;
	private Integer hour;
	private String channels;
	private String hotelId;
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	
	public String getChannels() {
		return channels;
	}
	public void setChannels(String channels) {
		this.channels = channels;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public String getIsMonitorHeartBeat() {
		return isMonitorHeartBeat;
	}
	public void setIsMonitorHeartBeat(String isMonitorHeartBeat) {
		this.isMonitorHeartBeat = isMonitorHeartBeat;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public Integer getNumberOfTimes() {
		return numberOfTimes;
	}
	public void setNumberOfTimes(Integer numberOfTimes) {
		this.numberOfTimes = numberOfTimes;
	}
	public Long getDisconnectedQuantum() {
		return disconnectedQuantum;
	}
	public void setDisconnectedQuantum(Long disconnectedQuantum) {
		this.disconnectedQuantum = disconnectedQuantum;
	}
	public String getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}
}
