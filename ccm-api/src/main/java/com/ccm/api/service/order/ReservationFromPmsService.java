/**
 * 
 */
package com.ccm.api.service.order;

import java.util.Map;

/**
 * 通过OWS协议传递过来的订单服务
 * 
 * @author Jenny
 * 
 */
public interface ReservationFromPmsService {

	/**
	 * 接收PMS POST过来的XML格式的Reservation来修改系统中的订单
	 * 
	 * @param mapReq
	 * @throws Exception
	 */
	public void pmsChangeOrder(Map<String, String> mapReq) throws Exception;

	/**
	 * 处理PMS POST过来的XML格式的Result
	 * 
	 * @param hotelCode
	 * @param transactionId
	 * @param message
	 * @param charsetName
	 * @throws Exception
	 */
	public void dealExtSysResult(String hotelCode, String transactionId, String message, String charsetName) throws Exception;

}
