/**
 * 
 */
package com.ccm.api.dao.log;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.CustomCreteria;
import com.ccm.api.model.hotel.vo.CustomResult;
import com.ccm.api.model.log.CustomLog;
import com.ccm.api.model.log.vo.CustomLogCreteria;
import com.ccm.api.model.log.vo.CustomLogResult;

/**
 * @author Water
 * 
 */
public interface CustomLogDao extends GenericDao<CustomLog, String> {
	/**
	 * 保存
	 * @param cl
	 */
	void saveCustomLog(CustomLog cl); 
	/**
	 * 查询列表并可分页显示
	 * 
	 * @param creteria
	 * @return
	 */
	CustomLogResult searchCustomLog(CustomLogCreteria creteria);

	/*
	 * 根据customId查找
	 */
	CustomLog searchCustomLogById(String customLogId);
	/**
	 * 根据customId累加
	 * @param customId
	 */
}
