/**
 * 
 */
package com.ccm.api.dao.log.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.log.SendMsgLogDao;
import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.log.vo.UpDownPmsLogCriteria;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;

/**
 * @author Jenny
 * 
 */
@Repository("sendMsgLogDaoiBatis")
public class SendMsgLogDaoiBatis extends GenericDaoiBatis<SendMsgLog, String> implements SendMsgLogDao {

	private Map<String, Long> msgIdMap = new HashMap<String, Long>();

	public SendMsgLogDaoiBatis() {
		super(SendMsgLog.class);
	}

	public void createSendMsgLogTable(String hotelCode) {
		getSqlMapClientTemplate().insert("createSendMsgLogTable", getTableName(hotelCode));
	}

	@SuppressWarnings("unchecked")
	public List<SendMsgLog> getSendMsgLogByCondit(SendMsgLog condition) {
		return getSqlMapClientTemplate().queryForList("getSendMsgLogByCondit", condition);
	}

	public synchronized String getMsgId(String hotelCode) {
		Long result = 3l;
		String key = "msgId" + hotelCode;
		if (msgIdMap.get(key) != null) {
			result = msgIdMap.get(key);
		} else {
			Object o = getSqlMapClientTemplate().queryForObject("getMaxMsgId", hotelCode);
			if (o != null) {
				result = Long.valueOf((String) o);
			}
		}
		result = result + 1;
		msgIdMap.put(key, result);
		return String.valueOf(result);
	}

	public SendMsgLog getSendMsgLogById(String sendMsgLogId, String hotelCode) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("sendMsgLogId", sendMsgLogId);
		param.put("hotelCode", hotelCode);
		return (SendMsgLog) getSqlMapClientTemplate().queryForObject("getSendMsgLog", param);
	}

	@SuppressWarnings("unchecked")
	public UpDownPmsLogResult searchList(UpDownPmsLogCriteria criteria) {

		criteria.setTableName(getTableName(criteria.getHotelCode()));

		UpDownPmsLogResult searchResult = new UpDownPmsLogResult();
		int count = (Integer) getSqlMapClientTemplate().queryForObject("countSendMsgLog", criteria);
		searchResult.setTotalCount(count);

		List<SendMsgLog> list = getSqlMapClientTemplate().queryForList("searchSendMsgLog", criteria);
		searchResult.setResultList(list);

		return searchResult;
	}

	private String getTableName(String hotelCode) {
		return "sendmsglog";
	}

	/**
	 * 根据日期与酒店编号删除sendmsglog 表的数据并返回影响记录的条数
	 * 
	 * @param date
	 * @param hotelcode
	 * @return
	 */
	@Override
	public int deleteSendMsgLogByCreatedTimeAndHotelCode(String createdTime, String hotelcode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("createdTime", createdTime);
		params.put("hotelcode", hotelcode);
		// 物理删除
		return getSqlMapClientTemplate().delete("deleteSendMsgLogByCreatedTimeAndHotelCode", params);
	}

	/**
	 * 查询分库日志表必须传入酒店代码
	 */
	@SuppressWarnings("unchecked")
	public UpDownPmsLogResult searchListNoCount(UpDownPmsLogCriteria criteria) {
		UpDownPmsLogResult searchResult = new UpDownPmsLogResult();
		if ((criteria.getHotelCodeList() != null && !criteria.getHotelCodeList().isEmpty()) || StringUtils.hasText(criteria.getHotelCode())) {
			criteria.setTableName(getTableName(criteria.getHotelCode()));
			List<SendMsgLog> list = getSqlMapClientTemplate().queryForList("searchSendMsgLogNoCount", criteria);
			searchResult.setResultList(list);
			return searchResult;
		}
		return searchResult;
	}

}
