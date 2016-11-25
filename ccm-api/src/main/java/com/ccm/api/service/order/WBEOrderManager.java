/**
 * 
 */
package com.ccm.api.service.order;

import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.vo.WBEOrder;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface WBEOrderManager extends GenericManager<Master, String> {

	String createOrder(WBEOrder order, Master master, String ref) throws Exception;

	Master buildOrder(WBEOrder order, Master master);

	void cancelOrder(Master master);

}
