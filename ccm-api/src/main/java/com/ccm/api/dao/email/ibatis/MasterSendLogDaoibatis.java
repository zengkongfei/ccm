package com.ccm.api.dao.email.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.email.MasterSendLogDao;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.email.MasterSendLog;
import com.ccm.api.model.email.vo.MasterSendLogVO;

@Repository("MasterSendLogDao")
public class MasterSendLogDaoibatis extends GenericDaoiBatis<MasterSendLog, String> implements MasterSendLogDao {

	public MasterSendLogDaoibatis() {
		super(MasterSendLog.class);
	}

	@Override
	public void saveMasterSendLog(MasterSendLog masterSendLog) {
		getSqlMapClientTemplate().insert("addMasterSendLog", masterSendLog);
	}

	@Override
	public MasterSendLog getMasterSendLogById(String masterSendLogId) {
		return (MasterSendLog) getSqlMapClientTemplate().queryForObject("getMasterSendLogById", masterSendLogId);
	}

	@Override
	public MasterSendLog getMasterSendLogBySmsId(String smsSendId) {
		return (MasterSendLog) getSqlMapClientTemplate().queryForObject("getMasterSendLogBySmsId", smsSendId);
	}

	@SuppressWarnings("unchecked")
	public List<MasterSendLogVO> getMasterSendLogByChannelStaDate(String channel, String sta, Date orderCreatedTime) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("channel", channel);
		param.put("sta", sta);
		param.put("changed", orderCreatedTime);
		param.put("resultCode", "3");
		param.put("smsType", SmsType.SMS_TYPE_EMAIL);
		return getSqlMapClientTemplate().queryForList("getMasterSendLogByChannelStaDate", param);
	}

}
