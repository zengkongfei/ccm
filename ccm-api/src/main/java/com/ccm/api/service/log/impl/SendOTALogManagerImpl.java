package com.ccm.api.service.log.impl;


import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.log.SendOTALogDao;
import com.ccm.api.model.log.SendOTALog;
import com.ccm.api.model.log.vo.SendOTALogCriteria;
import com.ccm.api.model.log.vo.SendOTALogResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.log.SendOTALogManager;

@Service("sendOTALogManager")
public class SendOTALogManagerImpl extends GenericManagerImpl<SendOTALog, String>  implements SendOTALogManager{

	private SendOTALogDao sendOTALogDao;

	private final Log log1 = LogFactory.getLog(SendOTALogManagerImpl.class);
	@Autowired
	public SendOTALogManagerImpl(SendOTALogDao sendOTALogDao) {
		super(sendOTALogDao);
		this.sendOTALogDao = sendOTALogDao;
	}
	
	
	@Override
	public SendOTALog findOTALogById(String id) {
		// TODO Auto-generated method stub
		return sendOTALogDao.findSendOTALogById(id);
	}


	@Override
	public void addOTAlog(SendOTALog sendOTALog) {
		// TODO Auto-generated method stub
		sendOTALogDao.createSendOTALog(sendOTALog);
	}

	@Override
	public void modifyOTAlog(SendOTALog sendOTALog) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateStatusForOTALog(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		sendOTALogDao.updateStatusForSendOTALog(paramMap);
	}


	@Override
	public SendOTALogResult searchList(SendOTALogCriteria criteria) {
		// TODO Auto-generated method stub
		return sendOTALogDao.searchList(criteria);
	}
	public SendOTALog getOTALogById(String hotelCode,String sendOTALogId){
		return sendOTALogDao.getOTALogById(hotelCode, sendOTALogId);
	}
}
