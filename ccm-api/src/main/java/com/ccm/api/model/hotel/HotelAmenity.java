/**
 * 
 */
package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class HotelAmenity extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5237662257145622120L;
	private String hotelAmenityId;
	private String hotelId;
	private String codeNo;

	public String getHotelAmenityId() {
		return hotelAmenityId;
	}

	public void setHotelAmenityId(String hotelAmenityId) {
		this.hotelAmenityId = hotelAmenityId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	@Override
	public String toString() {
		return "HotelAmenity [hotelAmenityId=" + hotelAmenityId + ", hotelId="
				+ hotelId + ", codeNo=" + codeNo + "]";
	}

}
