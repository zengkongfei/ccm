package com.ccm.api.service.sms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.sms.SmsSendDao;
import com.ccm.api.model.order.vo.OrderEmailConfirmResult;
import com.ccm.api.model.rsvtype.vo.SearchOrderEmailConfirmCriteria;
import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.sms.SmsSendManager;

@Service("smsSendManager")
public class SmsSendManagerImpl extends GenericManagerImpl<SmsSend, String> implements SmsSendManager {
	@Autowired
	private SmsSendDao smsSendDao;

	@Override
	public OrderEmailConfirmResult searchHotelInterfaceLog(
			SearchOrderEmailConfirmCriteria criteria) {
		return smsSendDao.searchHotelInterfaceLog(criteria);
	}

	

}
