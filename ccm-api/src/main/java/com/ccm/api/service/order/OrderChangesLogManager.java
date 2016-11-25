/**
 * 
 */
package com.ccm.api.service.order;

import java.util.List;

import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterChangesLog;
import com.ccm.api.model.order.MasterOrder;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny Liao 订单变更日志相关API
 */
public interface OrderChangesLogManager extends GenericManager<MasterChangesLog, String> {

	void saveMasterChangesLog(Master m, List<Master> mList);

	void saveMasterChangesLog(Object order);

	List<MasterChangesLog> getMasterChangesLogByOrderNo(String orderNo);
	
	public void updateMasterLog(Master m,MasterOrder mo);
}
