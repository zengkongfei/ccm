package com.ccm.api.model.rsvtype.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RedisRsvtype {

public RedisRsvtype(String hotelCode,String roomTypeCode,Date date,Integer qty){
		this.key=hotelCode+"|"+roomTypeCode+"|"+this.getMonthOfYear(date);
		this.hotelCode=hotelCode;
		this.roomTypeCode=roomTypeCode;
		this.qty=qty;
		this.date=this.getDate(date);
	}

public  String getDate(Date aDate) {
	SimpleDateFormat df;
	String returnValue = "";
	if (aDate != null) {
		df = new SimpleDateFormat("yyyy-MM-dd");
		returnValue = df.format(aDate);
	}

	return (returnValue);
}

public  String getMonthOfYear(Date aDate){
	SimpleDateFormat df;
	String returnValue = "";

	if (aDate != null) {
		df = new SimpleDateFormat("yyyy-MM");
		returnValue = df.format(aDate);
	}

	return (returnValue);
}
 public String getKey() {
	return key;
}

public String getHotelCode() {
	return hotelCode;
}

public String getDate() {
	return date;
}

public String getRoomTypeCode() {
	return roomTypeCode;
}

public Integer getQty() {
	return qty;
}

private String key;
private String hotelCode;
 private String date;
 private String roomTypeCode;
 private Integer qty;
}
