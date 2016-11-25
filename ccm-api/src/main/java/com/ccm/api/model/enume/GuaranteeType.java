/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * queryRate接口使用的担保类型
 * 
 * @author Jenny
 * 
 */
public enum GuaranteeType {

	GUARANTEEREQUIRED(1), // 需要担保
	NONE(2), // 无担保
	CC_DC_VOUCHER(3), // 信用卡
	PROFILE(4), //
	DEPOSIT(5), // 公司担保
	PREPAY(6), // 预付
	DEPOSITREQUIRED(7), // 需要押金

	CREDIT_CARD(8), //
	CORPORATE(9), //
	GUARANTEE(10), //
	HOLD(11), //
	TRAVEL_AGENT(12);//

	GuaranteeType(Integer value) {
		this.value = value;
	}

	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public static String fromValue(Integer v) {
		for (GuaranteeType c : GuaranteeType.values()) {
			if (c.value.intValue() == v.intValue()) {
				return c.name();
			}
		}
		return null;
	}

}
