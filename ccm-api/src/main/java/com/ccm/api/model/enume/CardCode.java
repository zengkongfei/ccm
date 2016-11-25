/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny
 * 
 */
public enum CardCode {

	AX, //
	VA, //
	MC, //
	DC, //
	JC;//

	public String value() {
		return name();
	}

	public static CardCode fromValue(String v) {
		return valueOf(v);
	}

}
