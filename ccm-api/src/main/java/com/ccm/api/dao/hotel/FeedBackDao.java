/**
 * 
 */
package com.ccm.api.dao.hotel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.FeedBack;
import com.ccm.api.model.hotel.vo.FeedBackCriteria;
import com.ccm.api.model.hotel.vo.FeedBackResult;

/**
 * @author Jenny
 * 
 */
public interface FeedBackDao extends GenericDao<FeedBack, String> {

	void updateStatusByFeedBackId(String status, List<String> feedBackIds);

	FeedBackResult searchList(FeedBackCriteria criteria);

	List<String> getFeedBackByStatus(String status, String hotelId);
}
