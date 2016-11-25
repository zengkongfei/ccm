package com.ccm.api.dao.log.ibatis;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.log.SendOTALogDao;
import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.log.SendOTALog;
import com.ccm.api.model.log.vo.SendOTALogCriteria;
import com.ccm.api.model.log.vo.SendOTALogResult;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;
@Repository("sendOTALogDaoiBatis")
public class SendOTALogDaoiBatis extends GenericDaoiBatis<SendOTALog, String> implements SendOTALogDao{

	public SendOTALogDaoiBatis() {
		super(SendOTALog.class);
	}

	@Override
	public void modfiySendOTALog(SendOTALog sendOTALog) {
		// TODO Auto-generated method stub
	}

	@Override
	public void createSendOTALog(SendOTALog sendOTALog) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("addOTALog",sendOTALog);
	}

	@Override
	public void updateStatusForSendOTALog(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("updateStatusForOTALog",paramMap);
	}

	@Override
	public SendOTALog findSendOTALogById(String id) {
		// TODO Auto-generated method stub
		return (SendOTALog)getSqlMapClientTemplate().queryForObject("findOTALog",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SendOTALogResult searchList(SendOTALogCriteria criteria) {

		SendOTALogResult searchResult = new SendOTALogResult();
		int count = (Integer) getSqlMapClientTemplate().queryForObject("countSendOTALog", criteria);
		searchResult.setTotalCount(count);

		List<SendOTALog> list = getSqlMapClientTemplate().queryForList("searchSendOTALog", criteria);
		searchResult.setResultList(list);

		return searchResult;
	}

	@Override
	public SendOTALog getOTALogById(String hotelCode,String sendOTALogId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("sendOTALogId", sendOTALogId);
		param.put("hotelCode", hotelCode);
		return (SendOTALog) getSqlMapClientTemplate().queryForObject("getOTALog", param);
		
	}

}
