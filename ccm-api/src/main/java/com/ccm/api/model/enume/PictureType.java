/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny Liao
 * 
 */
public enum PictureType {

	// 1 酒店图片,2 房型图片,3 商家图片
	HOTEL("pic", "199", "1"), 
	ROOM_TYPE("roomPic", "299", "2"), 
	COMPANY("pictures", "399", "3"), 
	OTHER("otherPic", "499", "4");

	PictureType(String filepath, String value, String name) {
		this.filepath = filepath;
		this.value = value;
		this.name = name;
	}

	public static PictureType findValue(String name) {
		for (PictureType value : PictureType.values()) {
			if (value.getName().equals(name)) {
				return value;
			}
		}
		return null;
	}

	private String filepath;
	private String value;
	private String name;

	public String getName() {
		return name;
	}

	public String getFilepath() {
		return filepath;
	}

	public String getValue() {
		return value;
	}

}
