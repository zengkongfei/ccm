package com.ccm.api.service.sms.impl;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.emay.sdk.client.api.Client;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.sms.SmsSendDao;
import com.ccm.api.dao.sms.SmsTemplateDao;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.model.sms.SmsTemplate;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.sms.SmsManager;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.PropertiesUtil;

@Service("smsManager")
public class SmsManagerImpl extends GenericManagerImpl<String, String> implements SmsManager {

    private SmsSendDao smsSendDao;

    private SmsTemplateDao smsTemplateDao;

    @Autowired
    public void setSmsSendDao(SmsSendDao smsSendDao) {
        this.smsSendDao = smsSendDao;
    }

    @Autowired
    public void setSmsTemplateDao(SmsTemplateDao smsTemplateDao) {
        this.smsTemplateDao = smsTemplateDao;
    }

    private Client client = null;

    public Client getClient() {
        if (client == null) {
            try {
            	String sms_softwareSerialNo = PropertiesUtil.getProperty(ProjectConfigConstant.sms_softwareSerialNo);
//                String sms_softwareSerialNo = projectProperties.getProperty(ProjectConfigConstant.sms_softwareSerialNo);

            	String sms_key = PropertiesUtil.getProperty(ProjectConfigConstant.sms_key);
//                String sms_key = projectProperties.getProperty(ProjectConfigConstant.sms_key);

                client = new Client(sms_softwareSerialNo, sms_key);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException("sms.init.error", "短信发送初始化错误.");
            }
        }
        return client;
    }

    /**
     * {@inheritDoc}
     */

    public String smsSend(String smsType, String mobile, Map paramMap) {

        // 根据短信类型查找短信模板
        String resultMess = null;
        try {

            if (smsSendDao.countSmsByMobile(mobile) >= 20) {
                log.info("用户：" + mobile + "smsNum is over 20");
                return "overCount";
            }

            SmsTemplate smsTemplate = smsTemplateDao.searchBySmsType("","",smsType);

            String smsContent = evalContent(smsTemplate.getTemplateContent(), paramMap);
            mobile = mobile.trim();
            // 发送短信
            int result = getClient().sendSMS(new String[] { mobile }, smsContent, "", 5);// 带扩展码
            resultMess = String.valueOf(result);

            String resultMsg = null;
            if (result == 0) {
                resultMsg = "短信发送成功";
            } else if (result == -1) {
                resultMsg = "发送信息失败（短信内容长度越界";
            } else if (result == 17) {
                resultMsg = "发送信息失败（未激活序列号或序列号和KEY值不对，或账户没有余额等）";
            } else if (result == 101) {
                resultMsg = "客户端网络故障";
            } else if (result == 305) {
                resultMsg = "服务器端返回错误，错误的返回值（返回值不是数字字符串）";
            } else if (result == 307) {
                resultMsg = "目标电话号码不符合规则，电话号码必须是以0、1开头";
            } else if (result == 997) {
                resultMsg = "平台返回找不到超时的短信，该信息是否成功无法确定";
            } else if (result == 303) {
                resultMsg = "由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定";
            } else if (result == -128) {
                resultMsg = "账户余额不足";
            }else if(result == -9020){
            	 resultMsg = "号码格式错误";
            }

            // 记录发送记录
            SmsSend smsSend = new SmsSend();

            smsSend.setMobileNumber(mobile);
            smsSend.setSmsContent(smsContent);
            smsSend.setSmsType(smsType);
            smsSend.setSendTime(DateUtil.currentDateTime());
            smsSend.setResultCode("" + result);
            smsSend.setResultMsg(resultMsg);
            smsSendDao.save(smsSend);

        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("smsSend.error", "短信发送发生错误.");
        }

        return resultMess;

    }

    /**
     * 替换为发送内容
     * 
     * @param templateContent
     * @param paramMap
     * @return
     */
	private String evalContent(String templateContent, Map paramMap) {
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

    /**
     * {@inheritDoc}
     */
    public String getEmayBalance() {

        try {

            String balance = getClient().getBalance()+"";

            return balance;

        } catch (Exception e) {

            e.printStackTrace();
            throw new BizException("smsSend.error", "获取emay短信账号余额发生错误.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String chargeUp(String cartSerialNo, String cardPassword) {

        String resultMsg = null;
        try {

            int result = getClient().chargeUp(cartSerialNo, cardPassword);

            if (result == 0) {
                resultMsg = "充值成功";
            } else if (result == 13) {
                resultMsg = "充值失败";
            } else if (result == 303) {
                resultMsg = "客户端网络超时或者网络故障";
            } else if (result == 305) {
                resultMsg = "服务器端返回错误，错误的返回值（返回值不是数字字符串";
            } else if (result == 999) {
                resultMsg = "操作频繁";
            }

        } catch (Exception e) {

            e.printStackTrace();
            throw new BizException("smsChargeUp.error", "短信账号充值发生错误.");
        }
        return resultMsg;
    }

    public Integer countSmsByCondition(SmsSend sms, int sec) {
        sms.setSec(sec);
        return smsSendDao.countSmsByCondition(sms);
    }
    
    
    /**
     * {@inheritDoc}
     */

    public String smsSendI18n(String smsType, String mobile, Map paramMap,String language,String hotelCode) {

        // 根据短信类型查找短信模板
        String resultMess = null;
        try {

//            if (smsSendDao.countSmsByMobile(mobile) >= 20) {
//                log.info("用户：" + mobile + "smsNum is over 20");
//                return "overCount";
//            }

            SmsTemplate smsTemplate = smsTemplateDao.searchBySmsType("",language,smsType);

            String smsContent = evalContent(smsTemplate.getTemplateContent(), paramMap);
            mobile = mobile.trim();
            // 发送短信
            int result = getClient().sendSMS(new String[] { mobile }, smsContent, "", 5);// 带扩展码
            resultMess = String.valueOf(result);

            String resultMsg = null;
            if (result == 0) {
                resultMsg = "短信发送成功";
            } else if (result == -1) {
                resultMsg = "发送信息失败（短信内容长度越界";
            } else if (result == 17) {
                resultMsg = "发送信息失败（未激活序列号或序列号和KEY值不对，或账户没有余额等）";
            } else if (result == 101) {
                resultMsg = "客户端网络故障";
            } else if (result == 305) {
                resultMsg = "服务器端返回错误，错误的返回值（返回值不是数字字符串）";
            } else if (result == 307) {
                resultMsg = "目标电话号码不符合规则，电话号码必须是以0、1开头";
            } else if (result == 997) {
                resultMsg = "平台返回找不到超时的短信，该信息是否成功无法确定";
            } else if (result == 303) {
                resultMsg = "由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定";
            } else if (result == -128) {
                resultMsg = "账户余额不足";
            }else if(result == -1100){
            	 resultMsg = "发送失败";
            }else if(result == -9020){
            	 resultMsg = "号码格式错误";
            }

            // 记录发送记录
            SmsSend smsSend = new SmsSend();

            smsSend.setMobileNumber(mobile);
            smsSend.setSmsContent(smsContent);
            smsSend.setSmsType(smsType);
            smsSend.setSendTime(DateUtil.currentDateTime());
            smsSend.setResultCode("" + result);
            smsSend.setResultMsg(resultMsg);
            smsSend.setVerifyCode(language);
            smsSend.setBizId(hotelCode);
            smsSendDao.save(smsSend);

        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("smsSend.error", "短信发送发生错误.");
        }

        return resultMess;

    }

}
