/**
 * 
 */
package com.ccm.api.model.log;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class SendReqResLog extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2372448250975857781L;

	private String sendreqresId;

	private String receivereqresId;

	private String interfaceId;// 接口ID

	private String hotelCode;// 酒店编码

	private String type;// 消息类型,参考MessageType

	private String originOrderId;// 接收到渠道的订单号
	private String originOrderConfirmId;// 真正发给外部系统的渠道订单号
	private String orderId;// pms订单号

	private String status;// 状态

	private String request;// 请求消息内容

	private String response;// 返回消息内容

	private String exception;// 异常错误

	public String getSendreqresId() {
		return sendreqresId;
	}

	public void setSendreqresId(String sendreqresId) {
		this.sendreqresId = sendreqresId;
	}

	public String getReceivereqresId() {
		return receivereqresId;
	}

	public void setReceivereqresId(String receivereqresId) {
		this.receivereqresId = receivereqresId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getOriginOrderId() {
		return originOrderId;
	}

	public void setOriginOrderId(String originOrderId) {
		this.originOrderId = originOrderId;
	}

	public String getOriginOrderConfirmId() {
		return originOrderConfirmId;
	}

	public void setOriginOrderConfirmId(String originOrderConfirmId) {
		this.originOrderConfirmId = originOrderConfirmId;
	}

}
