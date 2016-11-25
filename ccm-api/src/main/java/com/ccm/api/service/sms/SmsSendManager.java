package com.ccm.api.service.sms;

import com.ccm.api.model.order.vo.OrderEmailConfirmResult;
import com.ccm.api.model.rsvtype.vo.SearchOrderEmailConfirmCriteria;
import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.service.base.GenericManager;

public interface SmsSendManager extends GenericManager<SmsSend, String>{
	/**
	 * 监控日志
	 * 自动监控记录发送的email和sms
	 * @param criteria
	 * @return
	 */
	OrderEmailConfirmResult searchHotelInterfaceLog(SearchOrderEmailConfirmCriteria criteria);
}
