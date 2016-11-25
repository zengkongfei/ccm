package com.ccm.api.service.common;

/**
 * 
 * 批处理服务
 * 
 */
public interface BatchManager {

	/**
	 * 扫描所有未支付的订单: 批量取消没有付款的订单
	 */
	void updateOrder();

	void saveOrder2Pms();

	void sendEmail2HotelOfInterface();

}