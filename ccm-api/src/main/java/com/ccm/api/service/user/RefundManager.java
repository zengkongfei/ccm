package com.ccm.api.service.user;

import com.ccm.api.model.user.Refund;
import com.ccm.api.model.user.criteria.RefundCriteria;
import com.ccm.api.model.user.vo.RefundSearchResult;
import com.ccm.api.service.base.GenericManager;

/**
 * 
 * @author Jenny
 * 
 */
public interface RefundManager extends GenericManager<Refund, String> {

	public RefundSearchResult searchUser(RefundCriteria criteria);

	/**
	 * 添加当前用户默认的退款原因
	 * 
	 * @param userId
	 */
	public void addSysDefaultRefund(String userId);

}
