/**
 * 
 */
package com.ccm.api.model.order;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny Liao 订单支付
 */
public class MasterPay extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2475380860617933519L;

	private String orderPayId;
	private String masterId;

	private String gateway;
	private String trancationId;
	private Double cost;
	private String status;// ????

	// TODO: 理一下
	private Date paymentTime;// when to pay
	private String bank;// ???
	private String alipayEmail;// ???

	private Date createDate;

	/**
	 * 
	 */
	public String getId() {
		return orderPayId;
	}

	public Boolean saveOrUpdate() {
		if (orderPayId != null && !orderPayId.trim().isEmpty()) {
			return false;// update
		} else {
			return true;// save
		}
	}

	/**
	 * @return the orderPayId
	 */
	public String getOrderPayId() {
		return orderPayId;
	}

	/**
	 * @param orderPayId
	 *            the orderPayId to set
	 */
	public void setOrderPayId(String orderPayId) {
		this.orderPayId = orderPayId;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	/**
	 * @return the gateway
	 */
	public String getGateway() {
		return gateway;
	}

	/**
	 * @param gateway
	 *            the gateway to set
	 */
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getTrancationId() {
		return trancationId;
	}

	public void setTrancationId(String trancationId) {
		this.trancationId = trancationId;
	}

	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the paymentTime
	 */
	public Date getPaymentTime() {
		return paymentTime;
	}

	/**
	 * @param paymentTime
	 *            the paymentTime to set
	 */
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAlipayEmail() {
		return alipayEmail;
	}

	public void setAlipayEmail(String alipayEmail) {
		this.alipayEmail = alipayEmail;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
