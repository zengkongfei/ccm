/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny
 * 
 */
public enum PaymentTypeEnum {
	PRE("预付", 1), HOTELPAY("前台面付", 5), PREPAID("支付宝后付费", 6),VOUCHER("VOUCHER", 7);

	PaymentTypeEnum(String name, Integer id) {
		this.name = name;
		this.id = id;
	}

	private String name;
	private Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static PaymentTypeEnum fromId(String id) {
		for (PaymentTypeEnum paymentType : PaymentTypeEnum.values()) {
			if (paymentType.getId().equals(id)) {
				return paymentType;
			}
		}
		return null;
	}
}