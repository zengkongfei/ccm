package com.ccm.api.dao.sms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.order.vo.OrderEmailConfirmResult;
import com.ccm.api.model.rsvtype.vo.SearchOrderEmailConfirmCriteria;

public class SmsSendDaoTest extends BaseDaoTestCase{
	
	@Autowired
	private SmsSendDao dao;
	
	@Test
	public void searchHotelInterfaceLogTest(){
		SearchOrderEmailConfirmCriteria criteria = new SearchOrderEmailConfirmCriteria();
		criteria.setPageNum(1);
		criteria.setPageSize(10);
		criteria.setExcelFlag(true);
		criteria.setNeedPage(false);
		criteria.setSource(SmsType.SMS_SOURCE_EMAIL_INGERFACE);
		OrderEmailConfirmResult searchResult = dao.searchHotelInterfaceLog(criteria);
		System.out.println(searchResult.getResultList());
		System.out.println(searchResult.getTotalCount());
	}
}
