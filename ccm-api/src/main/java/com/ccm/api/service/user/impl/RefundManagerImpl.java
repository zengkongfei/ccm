package com.ccm.api.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.user.RefundDao;
import com.ccm.api.model.user.Refund;
import com.ccm.api.model.user.criteria.RefundCriteria;
import com.ccm.api.model.user.vo.RefundSearchResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.user.RefundManager;
import com.ccm.api.util.CommonUtil;

/**
 * 
 * @author Jenny
 * 
 */
@Service("refundManager")
public class RefundManagerImpl extends GenericManagerImpl<Refund, String> implements RefundManager {

	private RefundDao refundDao;

	@Autowired
	public RefundManagerImpl(RefundDao refundDao) {
		super(refundDao);
		this.refundDao = refundDao;
	}

	public RefundSearchResult searchUser(RefundCriteria criteria) {
		return refundDao.searchUser(criteria);
	}

	/**
	 * 添加当前用户默认的退款原因
	 */
	public void addSysDefaultRefund(String userId) {

		Integer crfd = refundDao.countRefundByUserId(userId);

		if (crfd == null || crfd.intValue() == 0) {

			List<Refund> rfdList = new ArrayList<Refund>();

			Refund r = new Refund();
			r.setRefundId(CommonUtil.generatePrimaryKey());
			r.setUserId(userId);
			r.setReason("未及时付款");
			rfdList.add(r);

			r = new Refund();
			r.setRefundId(CommonUtil.generatePrimaryKey());
			r.setUserId(userId);
			r.setReason("卖家联系不上");
			rfdList.add(r);

			r = new Refund();
			r.setRefundId(CommonUtil.generatePrimaryKey());
			r.setUserId(userId);
			r.setReason("谢绝还价");
			rfdList.add(r);

			r = new Refund();
			r.setRefundId(CommonUtil.generatePrimaryKey());
			r.setUserId(userId);
			r.setReason("商品瑕疵");
			rfdList.add(r);

			r = new Refund();
			r.setRefundId(CommonUtil.generatePrimaryKey());
			r.setUserId(userId);
			r.setReason("协商不一致");
			rfdList.add(r);

			r = new Refund();
			r.setRefundId(CommonUtil.generatePrimaryKey());
			r.setUserId(userId);
			r.setReason("买家不想买");
			rfdList.add(r);

			r = new Refund();
			r.setRefundId(CommonUtil.generatePrimaryKey());
			r.setUserId(userId);
			r.setReason("与买家协商一致");
			rfdList.add(r);

			refundDao.batchInsertRefund(rfdList);
		}

	}
}