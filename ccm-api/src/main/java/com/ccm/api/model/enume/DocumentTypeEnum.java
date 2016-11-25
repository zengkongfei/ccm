/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny
 * 
 */
public enum DocumentTypeEnum {

	IDCARD("5"), // 0:身份证
	PASSPORT("2"), // 1: 护照

	POLICECARD("6"), // 警官证
	SOLDIERCARD("3");// 3:士兵证

	DocumentTypeEnum(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}