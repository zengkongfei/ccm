package com.ccm.api.dao.sms;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.sms.SmsTemplate;


public interface SmsTemplateDao extends GenericDao<SmsTemplate, String> {
    
	/**
	 * 根据短信模板类型找模板
	 * @param hotelCode 酒店代码
	 * @param language 语言
	 * @param smsType  模板类型
	 * @return
	 */
	SmsTemplate searchBySmsType(String hotelCode,String language,String smsType);
	

}
