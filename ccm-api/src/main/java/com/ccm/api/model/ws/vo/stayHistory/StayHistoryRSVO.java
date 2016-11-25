package com.ccm.api.model.ws.vo.stayHistory;

import java.util.ArrayList;
import java.util.List;

public class StayHistoryRSVO {

	private String hotelCode;	//酒店代码
	private List<StayInfoDetailVO> stayInfoDetailList; //订单详情列表
	
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public List<StayInfoDetailVO> getStayInfoDetailList() {
		if(stayInfoDetailList == null){
			stayInfoDetailList = new ArrayList<StayInfoDetailVO>();
		}
		return stayInfoDetailList;
	}
	public void setStayInfoDetailList(List<StayInfoDetailVO> stayInfoDetailList) {
		this.stayInfoDetailList = stayInfoDetailList;
	}

}
