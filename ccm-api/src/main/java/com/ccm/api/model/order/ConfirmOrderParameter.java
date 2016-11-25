package com.ccm.api.model.order;

import java.io.Serializable;

/**
 * @author bruce
 * 订单确认或取消参数对象
 */
public class ConfirmOrderParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -825909968657706896L;

	/**
	 * 酒店订单id (淘宝的订单ID号)
	 * 必填
	 */
	private Long orderId;
	
	/**
	 * 操作类型，1：确认预订，2：取消订单 
	 * 必填
	 */
	private String operType;
	
	/**
	 * 取消订单时的取消原因，可选值：1,2,3,4； 1：无房，2：价格变动，3：买家原因，4：其它原因 
	 * 可选
	 */
	private String reasonType;
	
	/**
	 * 取消订单时的取消原因备注信息
	 * 可选
	 */
	private String reasonText;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getReasonType() {
		return reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	public String getReasonText() {
		return reasonText;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

	@Override
	public String toString() {
		return "CheckOrderParameter [operType=" + operType + ", orderId="
				+ orderId + ", reasonText=" + reasonText + ", reasonType="
				+ reasonType + "]";
	}
	
	
	
}
