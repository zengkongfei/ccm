package com.ccm.api.dao.user.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.user.RefundDao;
import com.ccm.api.model.user.Refund;
import com.ccm.api.model.user.criteria.RefundCriteria;
import com.ccm.api.model.user.vo.RefundSearchResult;

/**
 * 
 * @author Jenny
 * 
 */
@Repository("refundDao")
public class RefundDaoiBatis extends GenericDaoiBatis<Refund, String> implements RefundDao {

	/**
	 * Constructor to create a Generics-based version using Role as the entity
	 */
	public RefundDaoiBatis() {
		super(Refund.class);
	}

	@SuppressWarnings("unchecked")
	public RefundSearchResult searchUser(RefundCriteria criteria) {
		RefundSearchResult searchResult = new RefundSearchResult();

		// 系统计算该条件下总共多少条记录
		int count = (Integer) getSqlMapClientTemplate().queryForObject("searchRefundCount", criteria);
		searchResult.setTotalCount(count);

		List<Refund> list = getSqlMapClientTemplate().queryForList("searchRefunds", criteria);

		searchResult.setResultList(list);

		return searchResult;
	}

	public Integer countRefundByUserId(String userId) {
		return (Integer) getSqlMapClientTemplate().queryForObject("countRefundByUserId", userId);
	}

	public void batchInsertRefund(List<Refund> rList) {
		getSqlMapClientTemplate().insert("batchInsertRefund", rList);
	}
}
