package com.ccm.api.dao.order.ibatis;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.order.OrderDownloadLogDao;
import com.ccm.api.model.order.OrderDownloadLog;

@Repository("orderDownloadLogDao")
public class OrderDownloadLogDaoImpl extends GenericDaoiBatis<OrderDownloadLog, String> implements OrderDownloadLogDao {

	public OrderDownloadLogDaoImpl() {
		super(OrderDownloadLog.class);
	}

	@Override
	public OrderDownloadLog getOrderDownloadLogByUserId(OrderDownloadLog log){
		return (OrderDownloadLog)getSqlMapClientTemplate().queryForObject("getOrderDownloadLogByUserId", log);
	}

	@Override
	public OrderDownloadLog update(OrderDownloadLog log) {
		getSqlMapClientTemplate().update("updateOrderDownloadLog", log);
		return log;
	}
	
}
