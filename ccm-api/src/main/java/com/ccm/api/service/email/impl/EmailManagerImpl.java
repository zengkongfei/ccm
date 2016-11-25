/**
 * 
 */
package com.ccm.api.service.email.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.sms.SmsSendDao;
import com.ccm.api.dao.sms.SmsTemplateDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.constant.EmailType;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.email.vo.EmailVO;
import com.ccm.api.model.hotel.CreditLimit;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.model.sms.SmsTemplate;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.util.CommonUtil;


/**
 * @author Jenny Liao
 * 
 */
@Service("emailManager")
public class EmailManagerImpl implements EmailManager {

	private transient static Logger log = Logger.getLogger(EmailManagerImpl.class);
	public static final String MsgLimitNotifEmail="MsgLimitNotifEmail";
	public static final String MiniLimitNotifEmail="MiniLimitNotifEmail";
	private JavaMailSender mailSender;// spring配置中定义
	private VelocityEngine velocityEngine;// spring配置中定义
	private SimpleMailMessage simpleMailMessage;// spring配置中定义
	private JavaMailSender mailSenderError;// spring配置中定义
	private SimpleMailMessage simpleMailMessageError;// spring配置中定义

	@Resource
	private SmsSendDao smsSendDao;
	@Resource
	private HotelDao hotelDao;
	@Resource
	private SmsTemplateDao smsTemplateDao;
	@Resource
	private ChannelDao channelDao;
	// 是否需要身份验证
	private boolean validate = false;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 * @param simpleMailMessage
	 *            the simpleMailMessage to set
	 */
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	/**
	 * @param mailSenderError
	 *            the mailSenderError to set
	 */
	public void setMailSenderError(JavaMailSender mailSenderError) {
		this.mailSenderError = mailSenderError;
	}

	/**
	 * @param simpleMailMessageError
	 *            the simpleMailMessageError to set
	 */
	public void setSimpleMailMessageError(SimpleMailMessage simpleMailMessageError) {
		this.simpleMailMessageError = simpleMailMessageError;
	}

	/**
	 * @return the validate
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * @param validate
	 *            the validate to set
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**************************************************
     * 
     */
	/**
	 * 发送模板邮件
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void sendWithTemplate(Map model, EmailVO emailVO) {
		simpleMailMessage.setTo(emailVO.getTo()); // 接收人
		simpleMailMessage.setFrom(simpleMailMessage.getFrom()); // 发送人,从配置文件中取得
		simpleMailMessage.setSubject(emailVO.getSubject());
		String result = null;
		try {
			result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailVO.getTemplateName(), "UTF-8", model);
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
		}
		simpleMailMessage.setText(result);
		mailSender.send(simpleMailMessage);
	}

	/**
	 * 发送普通Html邮件 ,根据html模板
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	@Override
	public void sendHtmlWithTemplate(Map model, EmailVO emailVO) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		try {
			String to = emailVO.getTo();
			if (to.indexOf(",") > 0) {
				// 多个收件人之间用,分隔
				InternetAddress[] recipients = new InternetAddress().parse(to);
				mimeMessage.setRecipients(Message.RecipientType.TO, recipients);
			} else {
				messageHelper.setTo(to);
			}
			messageHelper.setFrom(simpleMailMessage.getFrom());
			messageHelper.setSubject(emailVO.getSubject());
			String result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailVO.getTemplateName(), "UTF-8", model);
			messageHelper.setText(result, true);
		} catch (MessagingException e) {
			log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
		} catch (VelocityException e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
			e.printStackTrace();
		}
		mailSender.send(mimeMessage);
	}

	/**
	 * 发送普通文本邮件
	 * 
	 */
	public void sendText(EmailVO emailVO) {
		try {
		if (emailVO.getTo() != null) {
			simpleMailMessage.setTo(emailVO.getTo()); // 接收人
		} else if (emailVO.getToArray() != null) {
			simpleMailMessage.setTo(emailVO.getToArray()); // 接收人
		}
		simpleMailMessage.setFrom(simpleMailMessage.getFrom()); // 发送人,从配置文件中取得
		simpleMailMessage.setSubject(emailVO.getSubject());
		simpleMailMessage.setText(emailVO.getContent());
		mailSender.send(simpleMailMessage);
		}catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
	}

	/**
	 * 监控DAO,Service服务并发送普通文本邮件
	 * 
	 */
	public void sendExceptionText(EmailVO emailVO) {
		try {
			if (emailVO.getTo() != null) {
				simpleMailMessageError.setTo(emailVO.getTo()); // 接收人
			} else if (emailVO.getToArray() != null) {
				simpleMailMessageError.setTo(emailVO.getToArray()); // 接收人
			}
			simpleMailMessageError.setFrom(simpleMailMessageError.getFrom()); // 发送人,从配置文件中取得
			simpleMailMessageError.setSubject(emailVO.getSubject());
			simpleMailMessageError.setText(emailVO.getContent());
			mailSenderError.send(simpleMailMessageError);
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
	}

	/**
	 * 发送普通Html邮件
	 * 
	 */
	public void sendHtml(EmailVO emailVO) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			if (emailVO.getTo() != null) {
				messageHelper.setTo(emailVO.getTo()); // 接收人
			} else if (emailVO.getToArray() != null) {
				messageHelper.setTo(emailVO.getToArray()); // 接收人
			}
			messageHelper.setFrom(simpleMailMessage.getFrom());
			messageHelper.setSubject(emailVO.getSubject());
			messageHelper.setText(emailVO.getContent(), true);
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
			throw new BizException("sendEmailExcpetion", "", e);
		}
	}

	/**
	 * 发送普通带一张图片的Html邮件
	 * 
	 */
	public void sendHtmlWithImage(String imagePath, EmailVO emailVO) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			if (emailVO.getTo() != null) {
				messageHelper.setTo(emailVO.getTo()); // 接收人
			} else if (emailVO.getToArray() != null) {
				messageHelper.setTo(emailVO.getToArray()); // 接收人
			}
			messageHelper.setFrom(simpleMailMessage.getFrom());
			messageHelper.setSubject(emailVO.getSubject());
			messageHelper.setText(emailVO.getContent(), true);
			// Content="<html><head></head><body><img src=\"cid:image\"/></body></html>";
			// 图片必须这样子：<img src='cid:image'/>
			FileSystemResource img = new FileSystemResource(new File(imagePath));
			messageHelper.addInline("image", img);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
			throw new BizException("sendEmailExcpetion", "", e);
		}
	}

	/**
	 * 发送普通带附件的Html邮件
	 * 
	 */
	public void sendHtmlWithAttachment(String filePath, EmailVO emailVO) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setTo(emailVO.getTo());
			messageHelper.setFrom(simpleMailMessage.getFrom());
			messageHelper.setSubject(emailVO.getSubject());
			messageHelper.setText(emailVO.getContent(), true);
			FileSystemResource file = new FileSystemResource(new File(filePath));
			messageHelper.addAttachment(file.getFilename(), file);
		} catch (MessagingException e) {
			log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
		}
		mailSender.send(mimeMessage);
	}

	public String sendEmail2HotelOff(String hotelCode, String hotelName,String email,String source) {
		SmsSend semail = new SmsSend();
		semail.setSmsSendId(CommonUtil.generatePrimaryKey());
		semail.setBizId(hotelCode);
		semail.setSmsContent(email);
		semail.setSmsType(SmsType.SMS_TYPE_EMAIL);
		semail.setVerifyCode(LanguageCode.MAIN_LANGUAGE_CODE);
		semail.setMobileNumber(hotelName);
		semail.setSource(source);
		String resultCode = "0";
		try {
			EmailVO emailVO = new EmailVO();
			emailVO.setToArray(email.split(";"));

			emailVO.setContent(EmailType.email_content_send + hotelName + EmailType.email_content_send2);
			emailVO.setSubject(EmailType.email_subject);

			File emailPic = new File(EmailType.email_pic_dir);
			File emailPicFile = new File(EmailType.email_pic_path);
			if (!emailPic.exists() || !emailPicFile.exists()) {
				emailPic.mkdirs();
				OutputStream os = new FileOutputStream(emailPicFile);
				// 返回读取指定资源的输入流
				InputStream is = this.getClass().getResourceAsStream("/email/chinaonline.jpg");
				int byteCount = 0;
				byte[] bytes = new byte[1024];
				while ((byteCount = is.read(bytes, 0, 1024)) != -1) {
					os.write(bytes, 0, byteCount);
				}
				/** 清空缓存，关闭文件输出流 */
				is.close();
				os.close();
			}

			this.sendHtmlWithImage(EmailType.email_pic_path, emailVO);
			semail.setResultMsg("发送成功");
			resultCode = "1";

		} catch (Exception e) {
			String msg = CommonUtil.getExceptionMsg(e, new String[] { "ccm" });
			log.error(msg);
			if (msg.length() > 200) {
				msg = msg.substring(0, 200);
			}
			semail.setResultMsg(msg);

			if (e instanceof BizException && "sendEmailExcpetion".equals(((BizException) e).getErrKey())) {
				resultCode = "2";
			}
		}
		semail.setResultCode(resultCode);
		semail.setSendTime(new Date());
		smsSendDao.addSmsSend(semail);
		return resultCode;
	}

	@Override
	public String sendForgetPasswordEmail(String email, String url, String username) {
		SmsSend semail = new SmsSend();
		semail.setSmsSendId(CommonUtil.generatePrimaryKey());
		semail.setSmsContent("发送用户：" + username + "重置密码邮件,重置密码链接为：" + url);
		semail.setSmsType(SmsType.SMS_TYPE_FORGETPASSWORD_EMAIL);
		semail.setVerifyCode(LanguageCode.MAIN_LANGUAGE_CODE);
		String resultCode = "0";
		try {
			EmailVO emailVO = new EmailVO();
			emailVO.setToArray(email.split(";"));

			emailVO.setContent(EmailType.email_subject_forgetPassword_view1 + url + EmailType.email_subject_forgetPassword_view2 + url + EmailType.email_subject_forgetPassword_view3);
			emailVO.setSubject(EmailType.email_subject_forgetPassword);

			File emailPic = new File(EmailType.email_pic_dir);
			File emailPicFile = new File(EmailType.email_pic_path);
			if (!emailPic.exists() || !emailPicFile.exists()) {
				emailPic.mkdirs();
				OutputStream os = new FileOutputStream(emailPicFile);
				// 返回读取指定资源的输入流
				InputStream is = this.getClass().getResourceAsStream("/email/chinaonline.jpg");
				int byteCount = 0;
				byte[] bytes = new byte[1024];
				while ((byteCount = is.read(bytes, 0, 1024)) != -1) {
					os.write(bytes, 0, byteCount);
				}
				/** 清空缓存，关闭文件输出流 */
				is.close();
				os.close();
			}

			this.sendHtmlWithImage(EmailType.email_pic_path, emailVO);
			semail.setResultMsg("发送成功");
			resultCode = "1";

		} catch (Exception e) {
			String msg = CommonUtil.getExceptionMsg(e, new String[] { "ccm" });
			log.error(msg);
			if (msg.length() > 200) {
				msg = msg.substring(0, 200);
			}
			semail.setResultMsg(msg);

			if (e instanceof BizException && "sendEmailExcpetion".equals(((BizException) e).getErrKey())) {
				resultCode = "2";
			}
			// 邮箱地址不通
			else if (e instanceof MailSendException && e.getMessage().contains("Invalid Addresses")) {
				resultCode = "4";
			}
		}
		semail.setResultCode(resultCode);
		semail.setSendTime(new Date());
		smsSendDao.addSmsSend(semail);
		return resultCode;
	}
	@Override
	public void sendAllotNotificationEmail(String hotelId,String channelCode,String orderId){
		log.info("begin sendAllotNotificationEmail");
		HotelVO h=hotelDao.getHotelI18nChainByHotelId(hotelId);
		SmsTemplate smstmp=smsTemplateDao.searchBySmsType(h.getHotelCode(), "zh_CN","AllotNotifEmail");
		Channel channel=channelDao.getChannelByChannelCode(channelCode);
		if(CommonUtil.isNotEmpty(h)&&CommonUtil.isNotEmpty(channel)&&CommonUtil.isNotEmpty(smstmp)){
			if(CommonUtil.isNotEmpty(h.getAllotNotificationEmail())&&CommonUtil.isNotEmpty(channel.getAllotNotificationEmail())){
				EmailVO emailVO = new EmailVO();
				List<String>emailList=new ArrayList<String>();
				convertEmailArrayFromStringField(h.getAllotNotificationEmail(),emailList);
				convertEmailArrayFromStringField(channel.getAllotNotificationEmail(),emailList);
				String[]emailArray=new String[emailList.size()];
				emailVO.setToArray(emailList.toArray(emailArray));
				emailVO.setSubject(smstmp.getTemplateDesc().replace("${channelCode}",channelCode));
				emailVO.setContent(smstmp.getTemplateContent().replace("${orderNo}", orderId));
				log.info("begin to invoke sendText");
				Exception exp=null;
				try{
					sendHtml(emailVO);
				}catch(Exception ex){
					exp=ex;
				}finally{
					creditLimitlogForsmsSend(EmailType.AllotmentNotice,channelCode,h.getHotelCode(),h.getHotelName(),h.getAllotNotificationEmail()+","+channel.getAllotNotificationEmail(),emailVO.getSubject(),emailVO.getContent(),exp);
				}
				log.info("end to invoke sendText");
			}
			
		}
	}
	
	@Override
	public String resendHtmlEmail(String emailType,String addresses,String hotelCode,String hotelName,String channelCode,String subject,String content){
		log.info("begin sendCreditLimitEmail");
				EmailVO emailVO = new EmailVO();
				List<String>emailList=new ArrayList<String>();
				convertEmailArrayFromStringField(addresses,emailList);
				String[]emailArray=new String[emailList.size()];
				emailVO.setToArray(emailList.toArray(emailArray));
				emailVO.setSubject(subject);
				emailVO.setContent(content);
				log.info("begin to invoke sendText");
				Exception exp=null;
				try{
					sendHtml(emailVO);
				}catch(Exception ex){
					exp=ex;
					return "2";
				}finally{
					creditLimitlogForsmsSend(emailType,channelCode,hotelCode,hotelName,addresses,subject,content,exp);
				}
				log.info("end to invoke sendText");
				return "1";
	}
	
	@Override
	public void sendCreditLimitEmail(String hotelId,String channelCode,String balance,String typename,CreditLimit cl){
		log.info("begin sendCreditLimitEmail");
		HotelVO h=hotelDao.getHotelI18nChainByHotelId(hotelId);
		SmsTemplate smstmp=smsTemplateDao.searchBySmsType(h.getHotelCode(), "zh_CN",typename);
		if(CommonUtil.isNotEmpty(h)&&CommonUtil.isNotEmpty(smstmp)){
			if(CommonUtil.isNotEmpty(cl.getHotelEmail())&&CommonUtil.isNotEmpty(cl.getChannelEmail())){
				EmailVO emailVO = new EmailVO();
				List<String>emailList=new ArrayList<String>();
				convertEmailArrayFromStringField(cl.getHotelEmail(),emailList);
				convertEmailArrayFromStringField(cl.getChannelEmail(),emailList);
				String[]emailArray=new String[emailList.size()];
				emailVO.setToArray(emailList.toArray(emailArray));
				emailVO.setSubject(smstmp.getTemplateDesc().replace("${channelCode}",channelCode));
				emailVO.setContent(smstmp.getTemplateContent().replace("${balance}", balance).replace("${limit}",cl.getMinLimitPct()!=null?cl.getMinLimitPct().toString()+"%":cl.getMinLimit().toString()));
				log.info("begin to invoke sendText");
				Exception exp=null;
				try{
					sendHtml(emailVO);
				}catch(Exception ex){
					exp=ex;
				}finally{
					creditLimitlogForsmsSend(EmailType.CreditLimitNotice,channelCode,h.getHotelCode(),h.getHotelName(),cl.getHotelEmail()+","+cl.getChannelEmail(),emailVO.getSubject(),emailVO.getContent(),exp);
				}
				
				log.info("end to invoke sendText");
			}
			
		}
	}
	
	private void convertEmailArrayFromStringField(String emails,List<String>emailList){
		if(emails.contains(";")){
			emailList.addAll(Arrays.asList(emails.split(";")));
		}else if(emails.contains(",")){
			emailList.addAll(Arrays.asList(emails.split(",")));
		}else{
			emailList.add(emails);
		}
	}
	
	@Override	
	public void creditLimitlogForsmsSend(String emailType,String channelCode,String hotelCode,String hotelName,String Addresses,String title,String content,Exception ex){
		SmsSend semail = new SmsSend();
		semail.setContentType(emailType);
		semail.setSource(channelCode);
		semail.setTitle(title);
		semail.setSmsSendId(CommonUtil.generatePrimaryKey());
		semail.setBizId(hotelCode);
		semail.setSmsContent(Addresses);
		semail.setBodycontent(content);
		semail.setSmsType(SmsType.SMS_TYPE_EMAIL);
		semail.setVerifyCode(LanguageCode.MAIN_LANGUAGE_CODE);
		semail.setMobileNumber(hotelName);
		String resultCode="1";
		if(ex==null){
			semail.setResultMsg("发送成功");
		}else{
			String msg = CommonUtil.getExceptionMsg(ex, new String[] { "ccm" });
			log.error(msg);
			if (msg.length() > 200) {
				msg = msg.substring(0, 200);
			}
			if (ex instanceof BizException && "sendEmailExcpetion".equals(((BizException) ex).getErrKey())) {
				resultCode = "2";
			}
			// 邮箱地址不通
			else if (ex instanceof MailSendException && ex.getMessage().contains("Invalid Addresses")) {
				resultCode = "4";
			}
			semail.setResultMsg(msg);
		}
		semail.setResultCode(resultCode);
		semail.setSendTime(new Date());
		smsSendDao.addSmsSend(semail);
	}
}
