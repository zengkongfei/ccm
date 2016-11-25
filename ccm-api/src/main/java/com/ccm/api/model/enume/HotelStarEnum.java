/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny Liao
 * 
 */
public enum HotelStarEnum {

	FIRST_STAR("NO_STAR", "201", "一星"), 
	TWO_STAR("TWO_STAR", "202", "二星"), 
	THREE_STAR("THREE_STAR", "203", "三星"), 
	FOUR_STAR("FOUR_STAR", "204", "四星"), 
	FIVE_STAR("FIVE_STAR", "205", "五星");

	HotelStarEnum(String name, String code, String cnName) {
		this.name = name;
		this.cnName = cnName;
		this.code = code;
	}

	public static HotelStarEnum fromCode(String code) {
		for (HotelStarEnum hotelStar : HotelStarEnum.values()) {
			if (hotelStar.getCode().equals(code)) {
				return hotelStar;
			}
		}
		return null;
	}

	public static HotelStarEnum fromName(String name) {
		for (HotelStarEnum hotelStar : HotelStarEnum.values()) {
			if (hotelStar.getName().equals(name)) {
				return hotelStar;
			}
		}
		return null;
	}

	private String name;
	private String cnName;
	private String code;

	public String getName() {
		return name;
	}

	public String getCnName() {
		return cnName;
	}

	public String getCode() {
		return code;
	}

}
