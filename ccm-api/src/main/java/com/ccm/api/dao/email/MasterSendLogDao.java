package com.ccm.api.dao.email;

import java.util.Date;
import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.email.MasterSendLog;
import com.ccm.api.model.email.vo.MasterSendLogVO;

public interface MasterSendLogDao extends GenericDao<MasterSendLog,String>{

	public void saveMasterSendLog(MasterSendLog masterSendLog);

	public MasterSendLog getMasterSendLogById(String masterSendLogId);

	public MasterSendLog getMasterSendLogBySmsId(String smsSendId);

	List<MasterSendLogVO> getMasterSendLogByChannelStaDate(String channel, String sta, Date orderCreatedTime);

}
