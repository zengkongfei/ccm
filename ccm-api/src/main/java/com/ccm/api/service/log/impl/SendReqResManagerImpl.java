/**
 * 
 */
package com.ccm.api.service.log.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.log.SendReqResDao;
import com.ccm.api.model.log.SendReqResLog;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.log.SendReqResManager;

/**
 * @author Jenny
 * 
 */
@Service("sendReqResManager")
public class SendReqResManagerImpl extends GenericManagerImpl<SendReqResLog, String> implements SendReqResManager {

	private SendReqResDao sendReqResDao;

	@Autowired
	public SendReqResManagerImpl(SendReqResDao sendReqResDao) {
		super(sendReqResDao);
		this.sendReqResDao = sendReqResDao;
	}

	public List<SendReqResLog> searchSendReqRes(SendReqResLog cond) {
		return sendReqResDao.searchSendReqRes(cond);
	}

}
