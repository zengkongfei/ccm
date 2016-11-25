package com.ccm.api.log.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.log.model.LogInfo;
import com.ccm.api.log.vo.LogInfoCriteria;
import com.ccm.api.log.vo.LogVO;
import com.ccm.api.model.constant.LanguageCode;

@Repository("logInfoDao")
public class LogInfoDaoiBatis extends GenericDaoiBatis<LogInfo, String> implements ILogInfoDao{

	public LogInfoDaoiBatis() {
		super(LogInfo.class);
	}

	@Override
	public LogInfo saveLogInfo(LogInfo logInfo) {
		logInfo.setLogId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("saveLogInfo", logInfo);
		return logInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogVO> searchLogInfo(LogInfoCriteria criteria) {
		//设定语言
		if(criteria!=null&&StringUtils.isBlank(criteria.getLanguage())){
			criteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchLogInfo", criteria);
	}
	
	@Override
	public Integer searchLogInfoCount(LogInfoCriteria criteria) {
		//设定语言
		if(criteria!=null&&StringUtils.isBlank(criteria.getLanguage())){
			criteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchLogInfoCount",criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> searchObjectInfo(String tableName,
			String primaryKey, Object primaryKeyValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
    	map.put("primaryKey", primaryKey);
    	map.put("primaryKeyValue", primaryKeyValue);
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("searchObjectInfo", map);
	}

}
