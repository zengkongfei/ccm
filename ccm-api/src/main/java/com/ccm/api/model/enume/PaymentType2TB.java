/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny
 * 
 */
public enum PaymentType2TB {

	PRE("TAGTD", 1), HOTELPAY("6PM", 5),HOTELPAY_GT("6PM_GT", 5), PREPAID("PREPAID", 6),PREPAID_GT("PREPAID_GT", 6)
	,VOUCHER("VOUCHER", 7),VOUCHER_GT("VOUCHER_GT", 7);

	PaymentType2TB(String name, Integer id) {
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

	public static Integer getIdFromName(String name) {
		for (PaymentType2TB paymentType : PaymentType2TB.values()) {
			if (paymentType.getName().equals(name)) {
				return paymentType.getId();
			}
		}
		return null;
	}
}