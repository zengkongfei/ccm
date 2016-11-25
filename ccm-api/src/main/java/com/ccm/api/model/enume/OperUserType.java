/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny
 * 
 */
public enum OperUserType {

	SWITCH, WBE, SYSTEM, PMS;

	public String value() {
		return name();
	}

	public static OperUserType fromValue(String v) {
		return valueOf(v);
	}

}
