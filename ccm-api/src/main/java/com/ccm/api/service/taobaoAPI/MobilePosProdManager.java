package com.ccm.api.service.taobaoAPI;

import java.util.Map;

/**
 * 超级收款业务管理
 * 
 * @author Tata.Wang
 */
public interface MobilePosProdManager {

	Map<String, Object> doPosConfirm(String tradeNo, String amount) throws Exception;
	
	/**
	 * 处理付款返回结果
	 * 
	 * @param result
	 *            结果参数result
	 * @param timeout
	 *            结果参数is_timeout
	 * @return
	 */
	Map<String, Object> doPosResult(String result, String timeout);

}
