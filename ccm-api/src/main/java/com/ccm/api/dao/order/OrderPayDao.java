/**
 * 
 */
package com.ccm.api.dao.order;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.order.MasterPay;

/**
 * @author devin.deng
 * 
 */
public interface OrderPayDao extends GenericDao<MasterPay, String> {

	public MasterPay getOrderPayByOrderId(String orderId);

	public MasterPay getOrderPayByTranId(String tarnId);

}
