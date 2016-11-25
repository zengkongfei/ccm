/**
 * 
 */
package com.ccm.api.service.order;

import com.ccm.api.model.order.Master;

/**
 * 通过OWS协议传递过来的订单服务
 * 
 * @author Jenny
 * 
 */
public interface ReservationService {

	/**
	 * 创建订单，构建PMS规定的XML格式
	 * 
	 * @param confirmId
	 * @throws Exception
	 */
	public void createOrder(String confirmId) throws Exception;

	/**
	 * 修改订单，构建PMS规定的XML格式
	 * 
	 * @param confirmId
	 * @throws Exception
	 */
	public void changeOrder(String confirmId) throws Exception;

	/**
	 * 取消订单，构建PMS规定的XML格式
	 * 
	 * @param confirmId
	 * @throws Exception
	 */
	public void cancelOrder(String confirmId) throws Exception;

	void buildWBEOrder(Master master);

	void buildReservation(Master order);

	public void asyncCreateOrder(String str) throws Exception;

}
