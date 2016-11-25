package com.ccm.api.model.enume;

import java.util.HashMap;
import java.util.Map;

public enum ActionEnum {

	ADD("ADD"),
	EDIT("EDIT"),
	CANCEL("CANCEL");

	ActionEnum(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private static Map<String, String> actionList;

	/***
	 * 供报表查询使用
	 */
	public static Map<String, String> getActionListMap() {
		if (actionList == null) {
			actionList = new HashMap<String, String>();
			
			actionList.put(ADD.value, ADD.value);
			actionList.put(EDIT.value, EDIT.value);
			actionList.put(CANCEL.value, CANCEL.value);
		
		}
		return actionList;
	}
	
}
