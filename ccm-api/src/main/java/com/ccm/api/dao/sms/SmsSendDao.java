package com.ccm.api.dao.sms;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.order.vo.OrderEmailConfirmResult;
import com.ccm.api.model.rsvtype.vo.SearchOrderEmailConfirmCriteria;
import com.ccm.api.model.sms.SmsSend;

public interface SmsSendDao extends GenericDao<SmsSend, String> {

	public void addSmsSend(SmsSend sms);

	// 查询手机当天发送的短信总数
	public int countSmsByMobile(String mobile);

	// 用于验证同一手机同一功能sec秒内发送次数限制
	public Integer countSmsByCondition(SmsSend sms);

	List<SmsSend> searchSmsSend(SmsSend sms);

	OrderEmailConfirmResult searchOrderEmailConfirm(SearchOrderEmailConfirmCriteria criteria);

	OrderEmailConfirmResult searchHotelInterfaceEmail(SearchOrderEmailConfirmCriteria criteria);
	
	/**
	 * 监控日志
	 * 自动监控记录发送的email和sms
	 * @param criteria
	 * @return
	 */
	OrderEmailConfirmResult searchHotelInterfaceLog(SearchOrderEmailConfirmCriteria criteria);

}
