/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny Liao
 * 
 */
public enum ChannelCodeEnum {

    /***
     * TB
     */
	TAOBAO("TB");// 淘宝

	ChannelCodeEnum(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
