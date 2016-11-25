package com.ccm.api.model.hotel.vo;

import com.ccm.api.model.hotel.Custom;

public class CustomReportRecord extends Custom {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6020583151307703878L;
	private String hotelCode;
	private String chainCode;
	private String childtotalRoomRev;

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

	public String getChildtotalRoomRev() {
		return childtotalRoomRev;
	}

	public void setChildtotalRoomRev(String childtotalRoomRev) {
		this.childtotalRoomRev = childtotalRoomRev;
	}
	
}
