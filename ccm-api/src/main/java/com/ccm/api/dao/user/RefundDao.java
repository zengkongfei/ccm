package com.ccm.api.dao.user;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.user.Refund;
import com.ccm.api.model.user.criteria.RefundCriteria;
import com.ccm.api.model.user.vo.RefundSearchResult;

/**
 * 
 * @author Jenny
 * 
 */
public interface RefundDao extends GenericDao<Refund, String> {

	public RefundSearchResult searchUser(RefundCriteria criteria);

	public Integer countRefundByUserId(String userId);

	public void batchInsertRefund(List<Refund> rList);

}
