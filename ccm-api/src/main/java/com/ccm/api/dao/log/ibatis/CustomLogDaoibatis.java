/**
 * 
 */
package com.ccm.api.dao.log.ibatis;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.log.CustomLogDao;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.CustomResult;
import com.ccm.api.model.log.CustomLog;
import com.ccm.api.model.log.vo.CustomLogCreteria;
import com.ccm.api.model.log.vo.CustomLogResult;

/**
 * @author Water
 * 
 */
@Repository("customLogDao")
public class CustomLogDaoibatis extends GenericDaoiBatis<CustomLog, String> implements CustomLogDao {

	public CustomLogDaoibatis() {
		super(CustomLog.class);
	}

	/**
	 * 查询列表并可分页显示
	 */
	@Override
	public CustomLogResult searchCustomLog(CustomLogCreteria creteria) {
		return null;
	}
	/*
	 * 根据customId查找
	 */
	@Override
	public CustomLog searchCustomLogById(String customLogId) {
		return null;
	}

	@Override
	public void saveCustomLog(CustomLog cl) {                               
		getSqlMapClientTemplate().insert("addCustomLog", cl);
	}

}
