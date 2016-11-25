package com.ccm.api.log.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.log.model.LogDetail;
import com.ccm.api.log.model.LogInfo;

@Repository("logDetailsDao")
public class LogDetailDaoiBatis extends GenericDaoiBatis<LogDetail, String> implements ILogDetailDao {

	public LogDetailDaoiBatis() {
		super(LogDetail.class);
	}

	@Override
	public LogDetail saveLogDetail(LogDetail logDetail) {
		logDetail.setLogDetailId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("saveLogDetail", logDetail);
		return logDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogDetail> searchLogDetails(String logId) {
		return getSqlMapClientTemplate().queryForList("searchLogDetails", logId);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> searchLogDetailPrev(LogInfo info, String primaryValue) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelId", info.getHotelId());
		param.put("businessId", info.getBusinessId());
		param.put("functionId", info.getFunctionId());
		param.put("primaryValue", primaryValue);
		param.put("operateTime", info.getOperateTime());
		return getSqlMapClientTemplate().queryForMap("searchLogDetailPrev", param, "attributeName", "newValue");
	}

}
