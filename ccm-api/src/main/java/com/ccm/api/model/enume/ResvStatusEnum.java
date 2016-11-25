/**
 * 
 */
package com.ccm.api.model.enume;

/**
 * @author Jenny Liao
 * 
 */
public enum ResvStatusEnum {

	WAIT_BUYER_PAY("0"), // 未冻结/未付款 -> 等待买家付款。
	WAIT_SELLER_SEND_GOODS("1"), // 已冻结/已付款 -> 等待卖家发货 -> 等待卖家确认。
	TRADE_CLOSED("2"), // 已退款 -> 交易关闭。
	TRADE_FINISHED("3"), // 已转交易 -> 交易成功。
	TRADE_NO_CREATE_PAY("4"), // 没有创建支付宝交易。
	TRADE_CLOSED_BY_TAOBAO("5"), // 交易被淘宝关闭->卖家关闭交易或卖家核实未入住。
	TRADE_SUCCESS("6"), // 预定成功（等待卖家核实入住）
	OTHER("0");

	ResvStatusEnum(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static ResvStatusEnum fromValue(String name) {
		for (ResvStatusEnum rse : ResvStatusEnum.values()) {
			if (rse.name.equals(name)) {
				return rse;
			}
		}
		throw new IllegalArgumentException(name);
	}

}
