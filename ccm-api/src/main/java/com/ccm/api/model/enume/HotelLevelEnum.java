/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny Liao
 * 
 */
public enum HotelLevelEnum {

	APARTMENT("NO_STAR", "A", "客栈公寓"), 
	ECONOMIC("TWO_STAR", "B", "经济连锁"), 
	TWO_STAR("THREE_STAR", "C", "二星级/以下"), 
	THREE_STAR("FOUR_STAR", "D", "三星级/舒适"), 
	FOUR_STAR("FIVE_STAR", "E", "四星级/高档"), 
	FIVE_STAR("FIVE_STAR", "F", "五星级/豪华");

	HotelLevelEnum(String name, String code, String cnName) {
		this.name = name;
		this.cnName = cnName;
		this.code = code;
	}

	public static HotelLevelEnum fromCode(String code) {
		for (HotelLevelEnum hotelStar : HotelLevelEnum.values()) {
			if (hotelStar.getCode().equals(code)) {
				return hotelStar;
			}
		}
		return null;
	}

	public static HotelLevelEnum fromName(String name) {
		for (HotelLevelEnum hotelStar : HotelLevelEnum.values()) {
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
