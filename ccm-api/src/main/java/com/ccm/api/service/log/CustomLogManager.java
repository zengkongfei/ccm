/**
 * 
 */
package com.ccm.api.service.log;

import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.CustomCreteria;
import com.ccm.api.model.hotel.vo.CustomResult;
import com.ccm.api.model.log.CustomLog;
import com.ccm.api.model.log.vo.CustomLogCreteria;
import com.ccm.api.model.log.vo.CustomLogResult;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Water
 * 
 */
public interface CustomLogManager extends GenericManager<CustomLog, String> {

	void saveCustomLog(CustomLog cl);
	/**
	 * 查询列表并可分页显示
	 * 
	 * @param creteria
	 * @return
	 */
	CustomLogResult searchCustomLog(CustomLogCreteria creteria);

	/*
	 * 根据customLogId查找
	 */
	public CustomLog searchCustomLogById(String customLogId);

}
