package com.ccm.api.service.sms;

import java.util.Map;

import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.service.base.GenericManager;

/**
 * 
 * 短息服务类
 * 
 */
public interface SmsManager extends GenericManager<String, String> {

    /**
     * 短信发送
     * 
     * @param smsType
     *            短信类型
     * @param mobile
     *            发送短信号码
     * @param paramMap
     * @return
     */
    public String smsSend(String smsType, String mobile, Map paramMap);

    /**
     * 获取短信余额
     * 
     * @return
     */
    public String getEmayBalance();

    /**
     * 充值
     * 
     * @param cartSerialNo
     *            充值卡序列号
     * @param cardPassword
     *            充值卡序密码
     * @return
     */
    public String chargeUp(String cartSerialNo, String cardPassword);

    /**
     * 用于验证同一手机同一功能sec秒内发送次数限制
     * 
     * @param sms
     * @param sec
     * @return
     */
    public Integer countSmsByCondition(SmsSend sms, int sec);
    
    /**
     * 发送短信
     * @param smsType
     * @param mobile
     * @param paramMap
     * @param language
     * @param hotelCode
     * @return
     */
    public String smsSendI18n(String smsType, String mobile, Map paramMap,String language,String hotelCode);

}