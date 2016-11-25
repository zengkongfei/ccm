package com.ccm.api.log.dao;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.log.model.LogInfo;
import com.ccm.api.log.vo.LogInfoCriteria;
import com.ccm.api.log.vo.LogVO;

public interface ILogInfoDao extends GenericDao<LogInfo, String>{
	/**
	 * 插入日志信息
	 * @param logInfo
	 */
	LogInfo saveLogInfo(LogInfo logInfo);
	
	/**
	 * 查询日志信息
	 * @param logSearchvo
	 */
	List<LogVO> searchLogInfo(LogInfoCriteria criteria);
	
	/**
	 * 查询日志信息总数
	 */
	Integer searchLogInfoCount(LogInfoCriteria criteria);
	
	/**
	 * 根据表名，主键，主键值查询对象信息
	 * @param logSearchvo
	 */
	Map<String,Object> searchObjectInfo(String tableName,String primaryKey,Object primaryKeyValue);
}
