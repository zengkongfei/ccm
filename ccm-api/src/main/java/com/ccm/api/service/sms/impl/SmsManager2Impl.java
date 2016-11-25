package com.ccm.api.service.sms.impl;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.dao.sms.SmsSendDao;
import com.ccm.api.dao.sms.SmsTemplateDao;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.model.sms.SmsTemplate;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.sms.SmsBaseUtil;
import com.ccm.api.service.sms.SmsManager2;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("smsManager2")
public class SmsManager2Impl extends GenericManagerImpl<String, String> implements SmsManager2{

	private final Log log = LogFactory.getLog(SmsManager2Impl.class);
	
	private SmsSendDao smsSendDao;

    private SmsTemplateDao smsTemplateDao;
    
    @Autowired
    private HotelMCDao hotelMcDao;

    @Autowired
    public void setSmsSendDao(SmsSendDao smsSendDao) {
        this.smsSendDao = smsSendDao;
    }

    @Autowired
    public void setSmsTemplateDao(SmsTemplateDao smsTemplateDao) {
        this.smsTemplateDao = smsTemplateDao;
    }
    
	@Override
	public String smsSend(String hotelCode, String lanuage, String smsType,
			String mobiles, Map<String, Object> paramMap)  {
		log.info("hotelCode:"+hotelCode+",language:"+lanuage+",smsType:"+smsType+",mobiles:"+mobiles);
		
		 //发送短信,返回结果信息
		String resultCode = "";
		String resultMsg = "";
		
		HotelVO hvo = hotelMcDao.getHotelByCodeMC2(hotelCode);
		String x_id = "";
		String x_pwd = "";
		
		if(hvo!=null){
			x_id = hvo.getSmsUserId();
			x_pwd = hvo.getSmsPassword();
		}
		
		log.info("x_id:"+x_id+",x_pwd:"+x_pwd);
		
		//手机用户名或者密码为空
		if(StringUtils.isBlank(x_id)||StringUtils.isBlank(x_pwd)){
			resultCode = "-14";
			log.info(SmsBaseUtil.resultMap.get(resultCode)+","+SmsBaseUtil.FAILURE);
			return SmsBaseUtil.FAILURE;
		}
		
		//获取发送短信的模板
		SmsTemplate smsTemplate = smsTemplateDao.searchBySmsType(hotelCode,lanuage,smsType);
		if(smsTemplate == null){
			return "noTemplate";
		}
		//将内容替换好
        String smsContent = evalContent(smsTemplate.getTemplateContent(), paramMap);
		
		try {
			resultCode = SmsBaseUtil.sendSms(mobiles, smsContent, x_id, x_pwd);
			resultMsg = SmsBaseUtil.resultMap.get(resultCode);
		} catch (UnsupportedEncodingException e) {
			resultCode = "-13";
			resultMsg = CommonUtil.getExceptionMsg(e);
			log.info("exception-msg:"+resultMsg);
			e.printStackTrace();
		}

		if(StringUtils.isBlank(resultMsg)){
			resultMsg = SmsBaseUtil.SUCCESS;
		}
		
		// 记录发送记录
        SmsSend smsSend = new SmsSend();
        smsSend.setMobileNumber(mobiles);
        smsSend.setSmsContent(smsContent);
        smsSend.setSmsType(smsType);
        smsSend.setSendTime(DateUtil.currentDateTime());
        smsSend.setResultCode(resultCode);
        smsSend.setResultMsg(resultMsg);
        smsSendDao.save(smsSend);
			
		return resultMsg;
	}

	/**
     * 替换为发送内容
     * 
     * @param templateContent
     * @param paramMap
     * @return
     */
	private String evalContent(String templateContent, Map<String, Object> paramMap) {
        String smsContent = null;
        try {

            VelocityEngine ve = new VelocityEngine();
            ve.init();

            VelocityContext context = new VelocityContext(paramMap);

            StringWriter writer = new StringWriter();

            ve.evaluate(context, writer, "", templateContent);
            smsContent = writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("smstemplate.eval.error", "短信模板替换错误.");

        }

        return smsContent;
    }

}
