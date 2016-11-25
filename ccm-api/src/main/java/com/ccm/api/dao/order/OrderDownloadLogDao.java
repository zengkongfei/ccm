package com.ccm.api.dao.order;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.order.OrderDownloadLog;

public interface OrderDownloadLogDao extends GenericDao<OrderDownloadLog, String> {

	/**
	 * 根据用户ID求订单最后下载时间
	 * @param log
	 * @return
	 */
	OrderDownloadLog getOrderDownloadLogByUserId(OrderDownloadLog log);

	/**
	 * 修改最后刷新时间
	 * @param log
	 * @return
	 */
	OrderDownloadLog update(OrderDownloadLog log);
}
