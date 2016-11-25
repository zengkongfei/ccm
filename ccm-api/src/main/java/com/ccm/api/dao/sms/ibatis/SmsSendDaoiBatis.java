/**
 * 
 */
package com.ccm.api.dao.sms.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.sms.SmsSendDao;
import com.ccm.api.model.constant.EmailType;
import com.ccm.api.model.order.OrderEmailConfirm;
import com.ccm.api.model.order.vo.OrderEmailConfirmResult;
import com.ccm.api.model.rsvtype.vo.SearchOrderEmailConfirmCriteria;
import com.ccm.api.model.sms.SmsSend;

@Repository("smsSendDao")
public class SmsSendDaoiBatis extends GenericDaoiBatis<SmsSend, String> implements SmsSendDao {

	public SmsSendDaoiBatis() {

		super(SmsSend.class);

	}

	@Override
	public int countSmsByMobile(String mobile) {
		return (Integer) getSqlMapClientTemplate().queryForObject("getSmsSendNumByMobile", mobile);
	}

	public Integer countSmsByCondition(SmsSend sms) {
		return (Integer) getSqlMapClientTemplate().queryForObject("getSmsSendNumByCondition", sms);
	}

	@SuppressWarnings("unchecked")
	public List<SmsSend> searchSmsSend(SmsSend sms) {
		return getSqlMapClientTemplate().queryForList("searchSmsSend", sms);
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderEmailConfirmResult searchOrderEmailConfirm(SearchOrderEmailConfirmCriteria criteria) {
		OrderEmailConfirmResult searchResult = new OrderEmailConfirmResult();

		// 导出excel标识
		if (criteria.isExcelFlag()) {
			List<OrderEmailConfirm> resultList = getSqlMapClientTemplate().queryForList("searchOrderEmailConfirm", criteria);
			this.setResultList(resultList);

			searchResult.setResultList(resultList);

		} else {
			// 查询一页数据
			List<OrderEmailConfirm> resultList = getSqlMapClientTemplate().queryForList("searchOrderEmailConfirm", criteria);
			this.setResultList(resultList);
			searchResult.setResultList(resultList);

			// 得到总条数
			Integer totalCount = (Integer) getSqlMapClientTemplate().queryForObject("searchOrderEmailConfirmCount", criteria);
			searchResult.setTotalCount(totalCount);
		}
		return searchResult;
	}

	@SuppressWarnings("unchecked")
	public OrderEmailConfirmResult searchHotelInterfaceEmail(SearchOrderEmailConfirmCriteria criteria) {

		OrderEmailConfirmResult searchResult = new OrderEmailConfirmResult();

		// 导出excel标识
		if (criteria.isExcelFlag()) {
			List<OrderEmailConfirm> resultList = getSqlMapClientTemplate().queryForList("searchHotelInterfaceEmail", criteria);
			this.setResultList(resultList);

			searchResult.setResultList(resultList);

		} else {
			// 查询一页数据
			List<OrderEmailConfirm> resultList = getSqlMapClientTemplate().queryForList("searchHotelInterfaceEmail", criteria);
			this.setResultList(resultList);
			searchResult.setResultList(resultList);

			// 得到总条数
			Integer totalCount = (Integer) getSqlMapClientTemplate().queryForObject("countHotelInterfaceEmail", criteria);
			searchResult.setTotalCount(totalCount);
		}
		return searchResult;

	}

	private void setResultList(List<OrderEmailConfirm> resultList) {
		if (resultList != null && resultList.size() > 0) {
			for (OrderEmailConfirm orderEmailConfirm : resultList) {
				String emails = orderEmailConfirm.getEmailAddress();
				orderEmailConfirm.setEmailAddress(emails.replace(",", ";"));
				orderEmailConfirm.setSendCount(emails.split(",").length);
			}
		}

	}

	@Override
	public void addSmsSend(SmsSend sms) {
		if(sms.getContentType()==null)
			sms.setContentType(EmailType.HotelInterface);
		getSqlMapClientTemplate().insert("addSmsSend", sms);
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderEmailConfirmResult searchHotelInterfaceLog(
			SearchOrderEmailConfirmCriteria criteria) {
		OrderEmailConfirmResult searchResult = new OrderEmailConfirmResult();
		if(criteria.isExcelFlag()){
			List<OrderEmailConfirm> resultList = getSqlMapClientTemplate().queryForList("searchHotelInterfaceLog", criteria);
			searchResult.setResultList(resultList);
		}else{
			// 查询一页数据
			List<OrderEmailConfirm> resultList = getSqlMapClientTemplate().queryForList("searchHotelInterfaceLog", criteria);
			searchResult.setResultList(resultList);
			
			// 得到总条数
			Integer totalCount = (Integer) getSqlMapClientTemplate().queryForObject("countHotelInterfaceLog", criteria);
			searchResult.setTotalCount(totalCount);
		}
		
		return searchResult;
	}

}
