/**
 * 
 */
package com.ccm.mc.webapp.action.hotel;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.ccm.api.model.hotel.FeedBack;
import com.ccm.api.model.hotel.vo.FeedBackCriteria;
import com.ccm.api.model.hotel.vo.FeedBackResult;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.RemindManager;
import com.ccm.api.service.hotel.FeedBackManager;
import com.ccm.mc.webapp.action.base.BaseAction;

/**
 * @author Jenny
 * 
 */
public class FeedBackAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2805588778880057840L;

	private Log log = LogFactory.getLog(FeedBackAction.class);

	@Resource
	private FeedBackManager feedBackManager;
	@Resource
	private RemindManager remindManager;

	private FeedBackCriteria criteria = new FeedBackCriteria();
	private String feedBackId;

	private FeedBackResult result = new FeedBackResult();
	private FeedBack fb = new FeedBack();

	/**
	 * 列表显示
	 */
	@SuppressWarnings("unchecked")
	public String list() {

		B2BUser user = getCurLoginUser();
		if (user == null) {
			log.info("user is not");
			return "index";
		}
		String hotelId = user.getHotelvo().getHotelId();

		int pageNo = this.getCurrentPageNo("feedBack");
		criteria.setPageNum(pageNo);
		criteria.setHotelId(hotelId);

		if (StringUtils.hasLength(from)) {
			List<String> feedBackIds = (List<String>) getSession().getAttribute(hotelId + from);
			if (feedBackIds != null) {
				criteria.setFeedBackIds(feedBackIds);
			}
			remindManager.updateHaveSeen(hotelId, from);
		}

		result = feedBackManager.searchList(criteria);

		return "list";
	}

	/**
	 * 查看
	 */
	public String view() {
		if (StringUtils.hasText(feedBackId)) {
			fb = feedBackManager.get(feedBackId);
		}
		return "view";
	}

	public FeedBackCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(FeedBackCriteria criteria) {
		this.criteria = criteria;
	}

	public String getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(String feedBackId) {
		this.feedBackId = feedBackId;
	}

	public FeedBackResult getResult() {
		return result;
	}

	public void setResult(FeedBackResult result) {
		this.result = result;
	}

	public FeedBack getFb() {
		return fb;
	}

	public void setFb(FeedBack fb) {
		this.fb = fb;
	}

}
