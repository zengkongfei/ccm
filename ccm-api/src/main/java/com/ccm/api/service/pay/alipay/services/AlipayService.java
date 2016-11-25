package com.ccm.api.service.pay.alipay.services;

import java.util.Map;

import com.ccm.api.model.order.Master;

/* *
 *类名：AlipayService
 *功能：支付宝各接口构造类
 *详细：构造支付宝各接口请求参数
 *版本：3.2
 *修改日期：2011-03-17
 *说明：
 */

public interface AlipayService {

	/**
	 * 构造即时到帐接口
	 * 
	 * @param sParaTemp
	 *            请求参数集合
	 * @return 表单提交HTML信息
	 */
	String create_direct_pay_by_user(Master orderInfo, Map<String, String> map);

}
