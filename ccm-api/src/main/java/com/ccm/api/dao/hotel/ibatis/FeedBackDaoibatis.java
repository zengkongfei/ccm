/**
 * 
 */
package com.ccm.api.dao.hotel.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.FeedBackDao;
import com.ccm.api.model.hotel.FeedBack;
import com.ccm.api.model.hotel.vo.FeedBackCriteria;
import com.ccm.api.model.hotel.vo.FeedBackResult;

/**
 * @author Jenny
 * 
 */
@Repository("feedBackDao")
public class FeedBackDaoibatis extends GenericDaoiBatis<FeedBack, String> implements FeedBackDao {

	public FeedBackDaoibatis() {
		super(FeedBack.class);
	}

	public void updateStatusByFeedBackId(String status, List<String> feedBackIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("feedBackIds", feedBackIds);
		getSqlMapClientTemplate().update("updateStatusByFeedBackId", map);
	}

	@SuppressWarnings("unchecked")
	public FeedBackResult searchList(FeedBackCriteria criteria) {
		FeedBackResult searchResult = new FeedBackResult();

		int count = (Integer) getSqlMapClientTemplate().queryForObject("countFeedBack", criteria);
		searchResult.setTotalCount(count);

		//List<FeedBack> list = getSqlMapClientTemplate().queryForList("searchFeedBack", criteria, criteria.getStart(), criteria.getPageSize());
		List<FeedBack> list = getSqlMapClientTemplate().queryForList("searchFeedBack", criteria);
		searchResult.setResultList(list);

		return searchResult;
	}

	@SuppressWarnings("unchecked")
	public List<String> getFeedBackByStatus(String status, String hotelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("hotelId", hotelId);
		return getSqlMapClientTemplate().queryForList("getFeedBackByStatus", map);
	}

}
