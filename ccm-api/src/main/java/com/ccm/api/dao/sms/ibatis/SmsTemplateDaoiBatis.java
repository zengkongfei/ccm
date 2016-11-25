/**
 * 
 */
package com.ccm.api.dao.sms.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.sms.SmsTemplateDao;
import com.ccm.api.model.sms.SmsTemplate;

@Repository("smsTemplateDao")
public class SmsTemplateDaoiBatis extends GenericDaoiBatis<SmsTemplate, String> implements SmsTemplateDao {
	public SmsTemplateDaoiBatis() {
		super(SmsTemplate.class);
	}

	/**
     * {@inheritDoc}
     */
	public SmsTemplate searchBySmsType(String hotelCode,String language,String smsType) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("hotelCode", hotelCode);	
		param.put("language", language);	
		param.put("smsType", smsType);	
		return 	(SmsTemplate)getSqlMapClientTemplate().queryForObject("searchSmsTemplate", param);
	}
	

}
