package com.ccm.api.log.dao;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.log.model.LogDetail;
import com.ccm.api.log.model.LogInfo;

public interface ILogDetailDao extends GenericDao<LogDetail, String> {
	/**
	 * 插入日志明细
	 * 
	 * @param logDetail
	 */
	LogDetail saveLogDetail(LogDetail logDetail);

	/**
	 * 查询日志明细
	 * 
	 * @param logSearchvo
	 */
	List<LogDetail> searchLogDetails(String logId);

	Map<String, String> searchLogDetailPrev(LogInfo info, String primaryValue);
}
