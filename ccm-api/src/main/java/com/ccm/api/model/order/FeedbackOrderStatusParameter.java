package com.ccm.api.model.order;

import java.io.Serializable;

public class FeedbackOrderStatusParameter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8028593522571533273L;

	/**
	 * 合作方订单号,最长250个字符 
	 * 必填
	 */
	private String outOrderId;
	
	/**
	 * 酒店订单id (淘宝的订单ID号)
	 * 必填
	 */
	private Long orderId;
	
	/**
	 * 预订结果 S:成功 F:失败 
	 * 必填
	 */
	private String result;
	
	/**
	 * 失败原因,当result为failed时,此项为必填，最长200个字符
	 */
	private String failedReason;
	
	/**
	 * 在合作方退订时可能要用到的标识码，最长250个字符
	 * 可选
	 */
	private String refundCode;

	public String getOutOrderId() {
		return outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFailedReason() {
		return failedReason;
	}

	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}

	public String getRefundCode() {
		return refundCode;
	}

	public void setRefundCode(String refundCode) {
		this.refundCode = refundCode;
	}

	@Override
	public String toString() {
		return "FeedbackOrderStatusParameter [failedReason=" + failedReason
				+ ", orderId=" + orderId + ", outOrderId=" + outOrderId
				+ ", refundCode=" + refundCode + ", result=" + result + "]";
	}
	
}
