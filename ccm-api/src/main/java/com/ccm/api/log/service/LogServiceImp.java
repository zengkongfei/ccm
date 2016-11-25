package com.ccm.api.log.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.log.dao.ILogDetailDao;
import com.ccm.api.log.dao.ILogInfoDao;
import com.ccm.api.log.model.LogDetail;
import com.ccm.api.log.model.LogInfo;
import com.ccm.api.log.util.LogObjectConvert;
import com.ccm.api.log.vo.LogInfoCriteria;
import com.ccm.api.log.vo.LogInfoResult;
import com.ccm.api.log.vo.LogVO;
import com.ccm.api.model.common.ParamInterface;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.ParamInterfaceManager;

@Service("logService")
public class LogServiceImp extends GenericManagerImpl<LogInfo, String> implements ILogService {

	@Autowired
	private ILogInfoDao logInfoDao;
	@Autowired
	private ILogDetailDao logDetailDao;
	@Resource
	private ParamInterfaceManager paramInterfaceManager;

	private Map<String, List<LogDetail>> logDetailMap = new HashMap<String, List<LogDetail>>();

	@Override
	public void insertLog(LogVO logvo) {
		List<LogDetail> logDetailList = LogObjectConvert.logvoOfLogDetail(logvo);
		if (!logDetailList.isEmpty()) {
			// 保存日志信息
			LogInfo logInfo = logInfoDao.saveLogInfo(logvo);
			// 保存明细信息
			for (LogDetail logDetail : logDetailList) {
				logDetail.setLogId(logInfo.getLogId());
				logDetailDao.saveLogDetail(logDetail);
			}
		}
	}

	public void insertLogAcation(LogInfo li, Map<String, String> keyValue, String primaryKeyName, String urlId) {
		if (li != null) {
			// 保存日志信息
			// 组合主键
			StringBuffer newValue = new StringBuffer();
			if (primaryKeyName != null && primaryKeyName.indexOf(",") > 1) {
				String[] strs = primaryKeyName.split(",");
				for (int i = 0; i < strs.length; i++) {
					newValue.append(keyValue.get(strs[i]));
				}
				li.setPrimaryValue(newValue.toString());
			}
			LogInfo logInfo = logInfoDao.saveLogInfo(li);
			// 组合主键
			if (StringUtils.hasText(newValue.toString())) {
				LogDetail logDetail = new LogDetail();
				logDetail.setLogId(logInfo.getLogId());
				logDetail.setPrimaryKey(primaryKeyName);
				logDetail.setAttributeName(primaryKeyName);
				logDetail.setNewValue(newValue.toString());
				logDetail.setUrlId(urlId);
				logDetailDao.saveLogDetail(logDetail);
			}
			// 保存明细信息
			Iterator<String> iter = keyValue.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				LogDetail logDetail = new LogDetail();
				logDetail.setLogId(logInfo.getLogId());
				logDetail.setPrimaryKey(primaryKeyName);
				logDetail.setAttributeName(key);
				logDetail.setNewValue(keyValue.get(key));
				logDetail.setUrlId(urlId);

				ParamInterface pi = new ParamInterface();
				pi.setUrlId(logDetail.getUrlId());
				pi.setFieldName(logDetail.getAttributeName());
				ParamInterface piNew = paramInterfaceManager.getParamInterfaceByObj(pi);
				if (piNew != null) {
					// 根据外部表主键获取Code
					if (StringUtils.hasLength(piNew.getForeignTable())) {
						String codeName = paramInterfaceManager.getCode(piNew.getForeignTable(), logDetail.getNewValue());
						if (StringUtils.hasText(codeName)) {
							logDetail.setNewValue(codeName);
						}
					} else if (piNew.getDelFlag()) {
						logDetail.setNewValue("******");
					}
				}

				logDetailDao.saveLogDetail(logDetail);
			}
		}
	}

	@Override
	public LogInfoResult searchLogInfo(LogInfoCriteria criteria) {
		LogInfoResult result = new LogInfoResult();
		List<LogVO> voList = logInfoDao.searchLogInfo(criteria);
		Integer count = logInfoDao.searchLogInfoCount(criteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}

	@Override
	public List<LogDetail> searchLogDetails(String logId,String language) {
		List<LogDetail> ldList = logDetailDao.searchLogDetails(logId);
		if (logDetailMap.get(logId) != null) {
			// return logDetailMap.get(logId);
		}
		List<LogDetail> result = getLogDetailByLogId(ldList, logId,language);
		logDetailMap.put(logId, result);
		return result;
	}

	public List<LogDetail> getLogDetailByLogId(List<LogDetail> ldList, String logId,String language) {

		// 旧值显示
		LogInfo logI = logInfoDao.get(logId);
		Map<String, String> nameValue = new HashMap<String, String>();
		if (!"1".equals(logI.getOperateType()) && !"3".equals(logI.getOperateType())) {
			nameValue = logDetailDao.searchLogDetailPrev(logI, logI.getPrimaryValue());
		}

		for (LogDetail ld : ldList) {
			if (!StringUtils.hasLength(ld.getUrlId())) {
				break;
			}
			// 旧值显示
			ld.setOldValue(nameValue.get(ld.getAttributeName()));
			// 字段描述显示
			ParamInterface pi = new ParamInterface();
			pi.setUrlId(ld.getUrlId());
			pi.setFieldName(ld.getAttributeName());
			ParamInterface piNew = paramInterfaceManager.getParamInterfaceByObj(pi,language);
			if (piNew != null) {
				// 针对同一字段不同值
				if (StringUtils.hasLength(piNew.getFieldValue()) && !piNew.getFieldValue().equals("Id") && !piNew.getFieldValue().equals("Ids")) {
					pi.setFieldValue(ld.getNewValue());
					piNew = paramInterfaceManager.getParamInterfaceByObj(pi,language);
				}
				if (piNew == null) {
					continue;
				}
				ld.setAttributeName(piNew.getFieldDesc());
			}
		}
		return ldList;
	}

	@Override
	public Map<String, Object> searchObjectInfo(String tableName, String primaryKey, Object primaryKeyValue) {
		return logInfoDao.searchObjectInfo(tableName, primaryKey, primaryKeyValue);
	}
}
