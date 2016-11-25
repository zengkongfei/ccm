/**
 * 
 */
package com.ccm.api.dao.order.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.order.OrderPayDao;
import com.ccm.api.model.constant.PaymentStatus;
import com.ccm.api.model.order.MasterPay;

/**
 * @author devin.deng
 * 
 */
@Repository("orderPayDao")
public class OrderPayDaoiBatis extends GenericDaoiBatis<MasterPay, String> implements OrderPayDao {

	public OrderPayDaoiBatis() {
		super(MasterPay.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public MasterPay getOrderPayByOrderId(String orderId) {

		List<MasterPay> payList = getSqlMapClientTemplate().queryForList("getOrderPayByOrderId", orderId);
		if (payList != null && payList.size() > 0) {
			for (MasterPay op : payList) {
				if (PaymentStatus.PAY.equals(op.getStatus())) {
					return op;
				}
			}
			return payList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MasterPay getOrderPayByTranId(String tarnId) {
		List<MasterPay> payList = getSqlMapClientTemplate().queryForList("getOrderPayByTranId", tarnId);
		if (payList != null && payList.size() > 0) {
			for (MasterPay op : payList) {
				if (PaymentStatus.PAY.equals(op.getStatus())) {
					return op;
				}
			}
			return payList.get(0);
		}
		return null;
	}

}
