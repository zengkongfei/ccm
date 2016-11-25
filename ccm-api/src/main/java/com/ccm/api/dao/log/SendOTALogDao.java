package com.ccm.api.dao.log;

import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.log.SendOTALog;
import com.ccm.api.model.log.vo.SendOTALogCriteria;
import com.ccm.api.model.log.vo.SendOTALogResult;

public interface SendOTALogDao extends GenericDao<SendOTALog, String> {
	void modfiySendOTALog(SendOTALog sendOTALog);
	void createSendOTALog(SendOTALog sendOTALog);
	void updateStatusForSendOTALog(Map <String,Object> paramMap);
	SendOTALog findSendOTALogById(String id); 
	SendOTALogResult searchList(SendOTALogCriteria criteria);
	SendOTALog getOTALogById(String hotelCode,String sendOTALogId);
}
