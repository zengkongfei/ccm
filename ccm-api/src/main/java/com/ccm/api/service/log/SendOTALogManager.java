package com.ccm.api.service.log;

import java.util.Map;

import com.ccm.api.model.log.SendOTALog;
import com.ccm.api.model.log.vo.SendOTALogCriteria;
import com.ccm.api.model.log.vo.SendOTALogResult;
import com.ccm.api.service.base.GenericManager;

public interface SendOTALogManager  extends GenericManager<SendOTALog, String> {
	 void addOTAlog(SendOTALog sendOTALog);
	 void modifyOTAlog(SendOTALog sendOTALog);
	 void updateStatusForOTALog(Map<String,Object> paramMap);
	 SendOTALog findOTALogById(String id);
	 SendOTALogResult searchList(SendOTALogCriteria criteria);
	 SendOTALog getOTALogById(String hotelCode,String sendOTALogId);
}
