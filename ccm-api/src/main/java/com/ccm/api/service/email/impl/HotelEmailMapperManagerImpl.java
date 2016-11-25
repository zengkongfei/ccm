package com.ccm.api.service.email.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.email.HotelEmailMapperDao;
import com.ccm.api.dao.sms.SmsSendDao;
import com.ccm.api.model.constant.EmailType;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.email.HotelEmailMapper;
import com.ccm.api.model.email.vo.EmailVO;
import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.email.HotelEmailMapperManager;
import com.ccm.api.util.CommonUtil;

@Service("hotelEmailMapperManager")
public class HotelEmailMapperManagerImpl extends GenericManagerImpl<HotelEmailMapper, String> implements HotelEmailMapperManager {

	@Resource
	private SmsSendDao smsSendDao;

	@Resource
	private EmailManager emailManager;
	@Resource
	private HotelEmailMapperDao hotelEmailMapperDao;

	@Override
	public String getEmailByChainAndHotelCode(String chainCode, String hotelCode) {
		HotelEmailMapper hem = new HotelEmailMapper();
		hem.setChainCode(chainCode);
		hem.setHotelCode(hotelCode);
		List<HotelEmailMapper> hemList = hotelEmailMapperDao.searchHotelEmailMapper(hem);
		if (CommonUtil.isNotEmpty(hemList)) {
			return hemList.get(0).getEmail();
		}
		return null;
	}

	public String sendEmail2HotelOff(String hotelCode, String hotelName, String email) {
		SmsSend semail = new SmsSend();
		semail.setSmsSendId(CommonUtil.generatePrimaryKey());
		semail.setBizId(hotelCode);
		semail.setSmsContent(email);
		semail.setSmsType(SmsType.SMS_TYPE_EMAIL);
		semail.setVerifyCode(LanguageCode.MAIN_LANGUAGE_CODE);
		semail.setMobileNumber(hotelName);
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

			emailManager.sendHtmlWithImage(EmailType.email_pic_path, emailVO);
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
}
