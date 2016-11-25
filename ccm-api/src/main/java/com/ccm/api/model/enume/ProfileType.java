/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny
 * 
 */
public enum ProfileType {

	CORPORATE, TRAVEL_AGENT, GUEST, PROMOTION;

	public String value() {
		return name();
	}

	public static ProfileType fromValue(String v) {
		return valueOf(v);
	}

}
