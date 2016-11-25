/**
 * 
 */
package com.ccm.api.service.common.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.hotel.FeedBackDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.model.constant.RemindStatus;
import com.ccm.api.service.common.RemindManager;

/**
 * @author Jenny
 * 
 */
@Service("remindManager")
public class RemindManagerImpl implements RemindManager {

	private Log log = LogFactory.getLog(RemindManagerImpl.class);

	@Resource
	private MasterDao masterDao;

	@Resource
	private FeedBackDao feedBackDao;

	public List<String> getMasterByOsta(String osta, String hotelId) {
		return masterDao.getMasterByOsta(osta, hotelId, null);
	}

	public List<String> getFeedBackByStatus(String status, String hotelId) {
		return feedBackDao.getFeedBackByStatus(status, hotelId);
	}

	@SuppressWarnings("unchecked")
	public void updateHaveSeen(String hotelId, String type) {
		if (!StringUtils.hasLength(type) || !StringUtils.hasLength(hotelId)) {
			log.info("hotelId:" + hotelId + "type:" + type);
			return;
		}
		HttpSession session = SecurityHolder.getSession();
		if ("new".equals(type)) {
			if (session.getAttribute(hotelId + type) != null) {
				List<String> masterIds = (List<String>) session.getAttribute(hotelId + type);
				masterDao.updateOstaByMasterIds(RemindStatus.haveseen_new, masterIds);
				session.removeAttribute(hotelId + type);
			}
		} else if ("modify".equals(type)) {
			if (session.getAttribute(hotelId + type) != null) {
				List<String> masterIds = (List<String>) session.getAttribute(hotelId + type);
				masterDao.updateOstaByMasterIds(RemindStatus.haveseen_modify, masterIds);
				session.removeAttribute(hotelId + type);
			}
		} else if ("cancel".equals(type)) {
			if (session.getAttribute(hotelId + type) != null) {
				List<String> masterIds = (List<String>) session.getAttribute(hotelId + type);
				masterDao.updateOstaByMasterIds(RemindStatus.haveseen_cancel, masterIds);
				session.removeAttribute(hotelId + type);
			}
		} else if ("feedb".equals(type)) {
			if (session.getAttribute(hotelId + type) != null) {
				List<String> feedBackIds = (List<String>) session.getAttribute(hotelId + "feedb");
				feedBackDao.updateStatusByFeedBackId(RemindStatus.haveseen_feedback, feedBackIds);
				session.removeAttribute(hotelId + type);
			}
		}
	}

	public void havedSeenRemind(String hotelId) {
		log.info("hotelId:" + hotelId);
		updateHaveSeen(hotelId, "new");
		updateHaveSeen(hotelId, "modify");
		updateHaveSeen(hotelId, "cancel");
		updateHaveSeen(hotelId, "feedb");
	}

}
