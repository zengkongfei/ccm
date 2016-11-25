package com.ccm.api.log.service;

import java.util.List;
import java.util.Map;

import com.ccm.api.log.model.LogDetail;
import com.ccm.api.log.model.LogInfo;
import com.ccm.api.log.vo.LogInfoCriteria;
import com.ccm.api.log.vo.LogInfoResult;
import com.ccm.api.log.vo.LogVO;
import com.ccm.api.service.base.GenericManager;

public interface ILogService extends GenericManager<LogInfo, String> {
	/**
	 * 插入日志
	 * 
	 * @param logvo
	 */
	void insertLog(LogVO logvo);

	void insertLogAcation(LogInfo li, Map<String, String> keyValue, String primaryKeyName, String urlId);

	/**
	 * 查询日志信息
	 * 
	 * @param criteria
	 */
	LogInfoResult searchLogInfo(LogInfoCriteria criteria);

	/**
	 * 查询日志明细
	 * 
	 * @param logId
	 * @param language 
	 * @return
	 */
	List<LogDetail> searchLogDetails(String logId, String language);

	/**
	 * 根据表名，主键，主键值查询对象信息
	 * 
	 * @param logSearchvo
	 */
	Map<String, Object> searchObjectInfo(String tableName, String primaryKey, Object primaryKeyValue);
}
