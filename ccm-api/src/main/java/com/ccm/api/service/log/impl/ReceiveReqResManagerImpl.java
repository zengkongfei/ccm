/**
 * 
 */
package com.ccm.api.service.log.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.log.ReceiveReqResDao;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.log.ReceiveReqResLog;
import com.ccm.api.model.log.vo.OrderLogSearchResult;
import com.ccm.api.model.log.vo.SearchOrderLogCriteria;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.log.ReceiveReqResManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
@Service("receiveReqResManager")
public class ReceiveReqResManagerImpl extends GenericManagerImpl<ReceiveReqResLog, String> implements ReceiveReqResManager {

	private ReceiveReqResDao receiveReqResDao;

	@Autowired
	public ReceiveReqResManagerImpl(ReceiveReqResDao receiveReqResDao) {
		super(receiveReqResDao);
		this.receiveReqResDao = receiveReqResDao;
	}

	public List<ReceiveReqResLog> getReceiveReqResByObj(ReceiveReqResLog conf) {
		return receiveReqResDao.getReceiveReqResByObj(conf);
	}

	public OrderLogSearchResult searchList(SearchOrderLogCriteria criteria) {
		return receiveReqResDao.searchList(criteria);
	}

	public void saveOrUpdateReceiveLogOfOrder(ReceiveReqResLog receiveLog) {
		receiveLog.setCreatedBy(OXIConstant.creatorCode);
		receiveLog.setUpdatedBy(OXIConstant.creatorCode);

		ReceiveReqResLog obj = null;
		if (StringUtils.hasText(receiveLog.getReceivereqresId())) {
			try {
				obj = receiveReqResDao.get(receiveLog.getReceivereqresId());
			} catch (Exception e) {
			}
		}
		// 更新
		if (obj != null) {
			receiveReqResDao.save(receiveLog);
		}
		// 新增
		else {
			// 多线程新增时报主健冲突，所以用try catch
			try {
				// 新增
				receiveLog.setLastModifyTime(DateUtil.currentDateTime());
				receiveReqResDao.saveReceiveReqResLog(receiveLog);
			} catch (Exception e) {
				// 更新
				receiveLog.setLastModifyTime(DateUtil.currentDateTime());
				receiveReqResDao.save(receiveLog);
			}
		}
	}

	@Override
	public void saveOrUpdateReceiveReqRes(ReceiveReqResLog receiveReqResLog) {
		List<ReceiveReqResLog> resLogList = receiveReqResDao.getReceiveReqResByObj(receiveReqResLog);
		if (CommonUtil.isNotEmpty(resLogList)) {
			ReceiveReqResLog reqLog = resLogList.get(0);
			receiveReqResLog.setReceivereqresId(reqLog.getReceivereqresId());
		}
		save(receiveReqResLog);
	}

}
