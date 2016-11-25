/**
 * 
 */
package com.ccm.api.service.order;

import java.util.Map;

import com.ccm.api.model.order.MasterPay;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny.Liao
 */
public interface OrderPayManager extends GenericManager<MasterPay, String> {

	// 一般的CRUD操作 和 查询 操作在GenericService里面已经有了

	public MasterPay getOrderPayByOrderId(String orderId);

	public Map<String, String> completePayCallback(MasterPay op);

}
