package com.ccm.api.model.hotel.vo;

import java.util.List;

import com.ccm.api.model.hotel.CreditLimit;

public class CreditLimitVO extends CreditLimit {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7877390869091207239L;
	private String hotelCodes;
	private String hotelId;
	private List<String> hotelIds;

	public String getHotelCodes() {
		return hotelCodes;
	}

	public void setHotelCodes(String hotelCodes) {
		this.hotelCodes = hotelCodes;
	}


	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public List<String> getHotelIds() {
		return hotelIds;
	}

	public void setHotelIds(List<String> hotelIds) {
		this.hotelIds = hotelIds;
	}

	@Override
	public String toString() {
		return "CreditLimitVO [hotelCodes=" + hotelCodes + ", hotelId=" + hotelId + ", hotelIds=" + hotelIds + "]";
	}

	
	
}
