package com.ccm.api.model.order;

import java.io.Serializable;
import java.util.Date;

public class CheckOrderParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5549165849480071246L;

	/**
	 * 酒店订单id (淘宝的订单ID号)
	 * 必填
	 */
	private Long orderId;
	
	/**
	 * 核实已入住或者未入住，true：已入住，false：未入住
	 * 必填
	 */
	private Boolean checked;
	
	/**
	 * 实际入住时间
	 * 可选
	 */
	private Date checkinDate;
	
	/**
	 * 实际离店时间
	 * 可选
	 */
	private Date checkOutDate;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	@Override
	public String toString() {
		return "CheckOrderParameter [checkOutDate=" + checkOutDate
				+ ", checked=" + checked + ", checkinDate=" + checkinDate
				+ ", orderId=" + orderId + "]";
	}
	
	
}
