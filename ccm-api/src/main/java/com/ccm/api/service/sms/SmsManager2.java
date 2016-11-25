package com.ccm.api.service.sms;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.ccm.api.service.base.GenericManager;

/**
 * 
 * 最新版本短息服务类
 * 
 */
public interface SmsManager2 extends GenericManager<String, String> {

	
	
    /**
     * 短信发送
     * @param hotelCode 酒店代码
     * @param lanuage 语言编码(zh_CN,en_US)
     * @param mobiles 要发送的电话号码 多个中间用逗号(,)隔开
     * @param smsType 短信类型
     * @param mobile 发送短信号码
     * @param paramMap 内容中的参数
     * 新建预订:
     * 嘉华大酒店预订确认：尊敬的${userName}贵宾，预订${hotelName}${roomName}${roomNum}间（${price}元/间/夜），
     * ${startDate_month}月${startDate_day}日至${endDate_month}月${endDate_day}日，
     * 预订号:${reservationNumber}，酒店总机号码:${telephone}.
     * 
     * Confirmation from Regal Palace Hotel:Dear Mr./Mrs ${userName}, ${roomName} 
     * check in ${startDate_day}/${startDate_month}，Check out ${endDate_day}/${endDate_month}，
     * RMB ${price}net/room/night。Confirmation No: ${reservationNumber} Tel:${telephone}
     * 
     * 修改预订:
     * 嘉华大酒店预订确认：尊敬的${userName}贵宾，您的预订已做更改，${roomName}${roomNum}间（${price}元/间/夜），
     * ${startDate_month}月${startDate_day}日至${endDate_month}月${endDate_day}日，
     * 预订号:${reservationNumber}，酒店联系号码: ${telephone}
     * 
     * Confirmation from Regal Palace Hotel: Dear ${userName} ,Your Reservation 
     * had changed already. ${roomName} check in ${startDate_day}/${startDate_month}，
     * Check out ${endDate_day}/${endDate_month}，RMB ${price}net/room/night。
     * Confirmation No.: ${reservationNumber} Tel:${telephone}
     * 
     * 取消预订:
     * 嘉华大酒店预订确认：尊敬的${userName}贵宾，您的预订（预订号：${reservationNumber}）已取消，
     * 期待您的再次关注，酒店联系号码:${telephone}.
     * 
     * Confirmation from Regal Palace Hotel: Dear ${userName} ,Your Reservation
     * (Confirmation No .: ${reservationNumber}) had cancelled already. 
     * Thank you for your attention. Tel: ${telephone}
     * 
     * @return
     * @throws UnsupportedEncodingException 
     */
    public String smsSend(String hotelCode,String lanuage,String smsType, String mobiles, Map<String, Object> paramMap) ;
    


}