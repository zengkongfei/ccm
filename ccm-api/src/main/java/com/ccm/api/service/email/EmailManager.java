/**
 * 
 */
package com.ccm.api.service.email;

import java.util.Map;

import com.ccm.api.model.email.vo.EmailVO;
import com.ccm.api.model.hotel.CreditLimit;

/**
 * @author Jenny Liao
 *
 */
public interface EmailManager {

	@SuppressWarnings("rawtypes")
	public void sendWithTemplate(Map model, EmailVO emailVO);
	
	public void sendText(EmailVO emailVO);
	
	void sendExceptionText(EmailVO emailVO);
	
	public void sendHtml(EmailVO emailVO);
	
	public void sendHtmlWithImage(String imagePath, EmailVO emailVO);
	
	public void sendHtmlWithAttachment(String filePath, EmailVO emailVO);

    void sendHtmlWithTemplate(Map model, EmailVO emailVO);
    
    public String sendEmail2HotelOff(String hotelCode, String hotelName, String email,String source);
    /**
     * 忘记密码，发送重置密码连接
     */
    public String sendForgetPasswordEmail(String email,String url,String username);

	void sendAllotNotificationEmail(String hotelCode,String channelCode,String orderId);

	void sendCreditLimitEmail(String hotelId,String channelCode,String balance,String typename,CreditLimit cl);

	void creditLimitlogForsmsSend(String emailType,String channelCode,String hotelCode, String hotelName,
			String Addresses,String title, String content, Exception ex);

	String resendHtmlEmail(String emailType, String addresses, String hotelCode,
			String hotelName, String channelCode, String subject, String content);
	
}
