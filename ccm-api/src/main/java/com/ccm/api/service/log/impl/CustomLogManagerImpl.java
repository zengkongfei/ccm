/**
 * 
 */
package com.ccm.api.service.log.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.log.CustomLogDao;
import com.ccm.api.model.log.CustomLog;
import com.ccm.api.model.log.vo.CustomLogCreteria;
import com.ccm.api.model.log.vo.CustomLogResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;

import com.ccm.api.service.log.CustomLogManager;

/**
 * @author Water
 * 
 */
@Service("customLogManager")
public class CustomLogManagerImpl extends GenericManagerImpl<CustomLog, String> implements CustomLogManager {

	private CustomLogDao customLogDao;

	@Autowired
	public CustomLogManagerImpl(CustomLogDao customLogDao) {
		super(customLogDao);
		this.customLogDao = customLogDao;
	}

	@Override
	public void saveCustomLog(CustomLog cl) {
		customLogDao.saveCustomLog(cl);
	}

	@Override
	public CustomLogResult searchCustomLog(CustomLogCreteria creteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomLog searchCustomLogById(String customLogId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
