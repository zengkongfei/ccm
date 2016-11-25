/**
 * 
 */
package com.ccm.api.service.log.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.log.ReceivePmsLogDao;
import com.ccm.api.model.log.ReceivePmsLog;
import com.ccm.api.model.log.vo.ReceivePmsLogCriteria;
import com.ccm.api.model.log.vo.ReceivePmsLogResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.log.BdpMsgManager;
import com.ccm.api.service.log.ReceivePmsLogManager;
import com.ccm.api.util.CommonUtil;

/**
 * 接收PMS最后的热点请求时间
 * 
 * @author Jenny
 * 
 */
@Service("receivePmsLogManager")
public class ReceivePmsLogManagerImpl extends GenericManagerImpl<ReceivePmsLog, String> implements ReceivePmsLogManager {

	private ReceivePmsLogDao receivePmsLogDao;
	@Resource
	private BdpMsgManager bdpMsgManager;
	
	@Autowired
	public ReceivePmsLogManagerImpl(ReceivePmsLogDao receivePmsLogDao) {
		super(receivePmsLogDao);
		this.receivePmsLogDao = receivePmsLogDao;
	}

	public void saveOrupdateReceivePmsLog(ReceivePmsLog rpl) {
		try {
			List<ReceivePmsLog> dbObj = receivePmsLogDao.getReceivePmsLogByConf(rpl);
			if (dbObj != null && !dbObj.isEmpty()) {
				rpl.setReceivepmslogId(dbObj.get(0).getReceivepmslogId());
				bdpMsgManager.updateOxiApiDisconnectedMsg(rpl,dbObj.get(0));
			} else {
				rpl.setCreatedTime(new Date());
				bdpMsgManager.updateOxiApiDisconnectedMsg(rpl,null);
			}
			receivePmsLogDao.save(rpl);
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
	}

	public ReceivePmsLogResult searchList(ReceivePmsLogCriteria criteria) {
		return receivePmsLogDao.searchList(criteria);
	}

}
