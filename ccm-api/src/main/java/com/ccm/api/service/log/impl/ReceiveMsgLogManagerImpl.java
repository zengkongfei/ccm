/**
 * 
 */
package com.ccm.api.service.log.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.log.ReceiveMsgLogDao;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.log.vo.UpDownPmsLogCriteria;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.AsyncManager;
import com.ccm.api.service.log.ReceiveMsgLogManager;

/**
 * @author Jenny
 * 
 */
@Service("receiveMsgLogManager")
public class ReceiveMsgLogManagerImpl extends GenericManagerImpl<ReceiveMsgLog, String> implements ReceiveMsgLogManager {

	private ReceiveMsgLogDao receiveMsgLogDao;

	@Autowired
	public ReceiveMsgLogManagerImpl(ReceiveMsgLogDao receiveMsgLogDao) {
		super(receiveMsgLogDao);
		this.receiveMsgLogDao = receiveMsgLogDao;
	}

	@Resource
	private AsyncManager asyncManagerImpl;

	@Override
	public ReceiveMsgLog addReceiveMsgLog(ReceiveMsgLog receiveMsgLog) {
		receiveMsgLog = receiveMsgLogDao.addReceiveMsgLog(receiveMsgLog);
		asyncManagerImpl.changeReceivePmsLog(receiveMsgLog);
		return receiveMsgLog;
	}

	public void updateReceiveMsgLog(ReceiveMsgLog receiveMsgLog) {
		receiveMsgLogDao.save(receiveMsgLog);
	}

	public ReceiveMsgLog getRMLByHotelCodeMsgId(String hotelCode, String msgId) {
		ReceiveMsgLog rml = new ReceiveMsgLog();
		rml.setHotelCode(hotelCode);
		rml.setBeginMsgId(msgId);
		List<ReceiveMsgLog> rmlList = receiveMsgLogDao.getRMLByHotelCodeMsgId(rml);
		if (rmlList != null && !rmlList.isEmpty()) {
			return rmlList.get(0);
		}
		return null;
	}

	@Override
	public ReceiveMsgLog getReceiveMsgLog(String receiveMsgLogId, String hotelCode) {
		return receiveMsgLogDao.getReceiveMsgLog(receiveMsgLogId, hotelCode);
	}

	public UpDownPmsLogResult searchList(UpDownPmsLogCriteria criteria) {
		return receiveMsgLogDao.searchList(criteria);
	}

	@Override
	public void updateReceiveMsgLogStatus(ReceiveMsgLog receiveMsgLog) {
		receiveMsgLogDao.updateReceiveMsgLogStatus(receiveMsgLog);
	}

}
