package com.ccm.api.model.bdp;
import java.util.List;

import com.ccm.api.model.base.BaseObject;

public class AdsPushErrorCount extends BaseObject{
	private String hotelCode;
	private String hotelName;
	private Integer qtr;
	private Integer numberOfTimes;
	private String lastDate;
	private List<String>hotelCodeList;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1534128768683715971L;
	
	public List<String> getHotelCodeList() {
		return hotelCodeList;
	}
	public void setHotelCodeList(List<String> hotelCodeList) {
		this.hotelCodeList = hotelCodeList;
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
	public Integer getQtr() {
		return qtr;
	}
	public void setQtr(Integer qtr) {
		this.qtr = qtr;
	}
	public Integer getNumberOfTimes() {
		return numberOfTimes;
	}
	public void setNumberOfTimes(Integer numberOfTimes) {
		this.numberOfTimes = numberOfTimes;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
}
