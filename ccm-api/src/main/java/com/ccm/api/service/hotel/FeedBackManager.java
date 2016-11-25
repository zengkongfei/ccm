/**
 * 
 */
package com.ccm.api.service.hotel;

import com.ccm.api.model.hotel.FeedBack;
import com.ccm.api.model.hotel.vo.FeedBackCriteria;
import com.ccm.api.model.hotel.vo.FeedBackResult;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface FeedBackManager extends GenericManager<FeedBack, String> {

	FeedBackResult searchList(FeedBackCriteria criteria);
}
