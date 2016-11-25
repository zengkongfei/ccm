package com.ccm.api.dao.master;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.order.MasterChangesLog;

public interface MasterChangesLogDao extends GenericDao<MasterChangesLog, String> {

	/**
	 * 根据订单号查询订单变更记录
	 * 
	 * @param crsno
	 * @return
	 */
	List<MasterChangesLog> getMasterChangesLogByMasterId(String masterId);

}
