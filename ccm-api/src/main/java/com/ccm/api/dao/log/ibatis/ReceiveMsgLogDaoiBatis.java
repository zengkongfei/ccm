/**
 * 
 */
package com.ccm.api.dao.log.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.log.ReceiveMsgLogDao;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.log.vo.UpDownPmsLogCriteria;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;
import com.ccm.api.util.CommonUtil;

/**
 * @author Jenny
 * 
 */
@Repository("receiveMsgLogDaoiBatis")
public class ReceiveMsgLogDaoiBatis extends GenericDaoiBatis<ReceiveMsgLog, String> implements ReceiveMsgLogDao {

	public ReceiveMsgLogDaoiBatis() {
		super(ReceiveMsgLog.class);
	}

	public void createReceiveMsgLogTable(String hotelCode) {
		getSqlMapClientTemplate().insert("createReceiveMsgLogTable", getTableName(hotelCode));
	}
	
	public void updateReceiveMsgLogStatus(ReceiveMsgLog receiveMsgLog){
		getSqlMapClientTemplate().update("updateReceiveMsgLogStatus", receiveMsgLog);
	}
	

	@Override
	public ReceiveMsgLog addReceiveMsgLog(ReceiveMsgLog receiveMsgLog) {
		receiveMsgLog.setCreatedBy(SecurityHolder.getUserId());
		receiveMsgLog.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addReceiveMsgLog", receiveMsgLog);
		return receiveMsgLog;
	}

	@SuppressWarnings("unchecked")
	public List<ReceiveMsgLog> getRMLByHotelCodeMsgId(ReceiveMsgLog obj) {
		return getSqlMapClientTemplate().queryForList("getReceiveMsgLogByOne", obj);
	}

	@Override
	public ReceiveMsgLog getReceiveMsgLog(String receiveMsgLogId, String hotelCode) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("receiveMsgLogId", receiveMsgLogId);
		param.put("hotelCode", hotelCode);
		return (ReceiveMsgLog) getSqlMapClientTemplate().queryForObject("getReceiveMsgLog", param);
	}

	@SuppressWarnings("unchecked")
	public UpDownPmsLogResult searchList(UpDownPmsLogCriteria criteria) {

		criteria.setTableName(getTableName(criteria.getHotelCode()));

		UpDownPmsLogResult searchResult = new UpDownPmsLogResult();
		int count = (Integer) getSqlMapClientTemplate().queryForObject("countReceiveMsgLog", criteria);
		searchResult.setTotalCount(count);
		
		List<ReceiveMsgLog> list = getSqlMapClientTemplate().queryForList("searchReceiveMsgLog", criteria);
		searchResult.setUpMsgLogList(list);

		return searchResult;
	}

	private String getTableName(String hotelCode) {
		return "receivemsglog";
	}
	
	/**
	 * 根据日期与酒店编号删除receivemsglog表的数据并返回影响记录的条数
	 * @param date
	 * @param hotelcode
	 * @return
	 */
	@Override
	public int deleteReceiveMsgLogByCreatedTimeAndHotelCode(String createdTime, String hotelcode) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("createdTime",createdTime);
		params.put("hotelcode", hotelcode);
		                                                                           
		return getSqlMapClientTemplate().delete("deleteReceiveMsgLogByCreatedTimeAndHotelCode", params);
	}
	
	@Override
	public ReceiveMsgLog getPendingReceiveMsgLog(ReceiveMsgLog conditionMsgLog){
		List<ReceiveMsgLog> resultMsgLogList=getSqlMapClientTemplate().queryForList("getPendingReceiveMsgLog",conditionMsgLog);
		if(CommonUtil.isNotEmpty(resultMsgLogList)){
			return resultMsgLogList.get(0);
		}else{
			return null;
		}
	}
}
