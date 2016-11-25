/**
 * 
 */
package com.ccm.api.service.hotel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.hotel.FeedBackDao;
import com.ccm.api.model.hotel.FeedBack;
import com.ccm.api.model.hotel.vo.FeedBackCriteria;
import com.ccm.api.model.hotel.vo.FeedBackResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.FeedBackManager;

/**
 * @author Jenny
 * 
 */
@Service("feedBackManager")
public class FeedBackManagerImpl extends GenericManagerImpl<FeedBack, String> implements FeedBackManager {

	private FeedBackDao feedBackDao;

	@Autowired
	public FeedBackManagerImpl(FeedBackDao feedBackDao) {
		super(feedBackDao);
		this.feedBackDao = feedBackDao;
	}

	public FeedBackResult searchList(FeedBackCriteria criteria) {
		return feedBackDao.searchList(criteria);
	}

}
