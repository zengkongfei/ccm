/**
 * 
 */
package com.ccm.api.model.enume;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jenny Liao
 * 
 */
public enum OXIStatusEnum {

	/**
	 * sendmsglog表中status
	 */
	SEND_READY("READY"), // 1
	SEND_REQUEUED("REQUEUED"), // 2
	SEND_RESULT_FAIL("RESULT_FAIL"), // 3
	SEND_FAIL("FAIL"), // 4
	SEND_RESULT_SUCCESS("RESULT_SUCCESS"), // 5
	SEND_IGNORE("IGNORE"), //

	// sendmsglog表status和receiveMsgLog表的processStatus 6
	SRP_PROCESSED("PROCESSED"),

	PROCESSING("PROCESSING"),

	// 系统中创建XML时使用
	SUCCESS("SUCCESS"),
	// New non-result message.
	NEW("NEW"),
	// Only for result messages. OXI could not process original message received
	// from the external system.
	FAILED("FAILED"),
	// Only for profile messages. User deleted the profile in Opera.
	DELETE("DELETE");

	OXIStatusEnum(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private static Map<String, String> downPmsMsgStatus;

	/***
	 * 供报表查询使用
	 */
	public static Map<String, String> getOrderProductDownPmsMsgStatusMap() {
		if (downPmsMsgStatus == null) {
			downPmsMsgStatus = new HashMap<String, String>();
			downPmsMsgStatus.put(SEND_READY.value, SEND_READY.value);
			downPmsMsgStatus.put(SRP_PROCESSED.value, SRP_PROCESSED.value);
			downPmsMsgStatus.put(SEND_RESULT_FAIL.value, SEND_RESULT_FAIL.value);
			downPmsMsgStatus.put(SEND_RESULT_SUCCESS.value, SEND_RESULT_SUCCESS.value);
			downPmsMsgStatus.put(SEND_REQUEUED.value, SEND_REQUEUED.value);
			downPmsMsgStatus.put(SEND_IGNORE.value, SEND_IGNORE.value);
		}
		return downPmsMsgStatus;
	}
}
