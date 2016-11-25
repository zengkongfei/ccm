package com.ccm.api.service.email.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.email.MasterSendLogDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.hotel.HotelGuaranteeDao;
import com.ccm.api.dao.log.SendMsgLogDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.dao.sms.SmsSendDao;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.email.MasterSendLog;
import com.ccm.api.model.email.vo.EmailVO;
import com.ccm.api.model.email.vo.MasterSendLogVO;
import com.ccm.api.model.enume.OXIStatusEnum;
import com.ccm.api.model.hotel.HotelGuaranteeI18n;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterPackage;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.ratePlan.RatePlanI18n;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.email.MasterSendLogManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.util.AmountUtil;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.PropertiesUtil;

@Service("masterSendLogManager")
public class MasterSendLogManagerImpl extends GenericManagerImpl<MasterSendLog, String> implements MasterSendLogManager {

	private Log log = LogFactory.getLog("reservation");

	@Resource
	private MessageSource messageSource;
	@Resource
	private MasterSendLogDao masterSendLogDao;
	@Resource
	private SmsSendDao smsSendDao;
	@Resource
	private HotelDao hotelDao;
	@Resource
	private MasterDao masterDao;
	@Resource
	private SendMsgLogDao sendMsgLogDao;
	@Resource
	private HotelGuaranteeDao hotelGuaranteeDao;

	@Resource
	private EmailManager emailManager;
	@Resource
	private PictureManager pictureManager;
	@Resource
	private RateplanDao rateplanDao;
	@Resource
	private RoomTypeManager roomTypeManager;

	@Override
	public void saveMasterSendLog(MasterSendLog masterSendLog) {
		masterSendLogDao.saveMasterSendLog(masterSendLog);
	}

	@Override
	public MasterSendLog getMasterSendLogById(String masterSendLogId) {
		return masterSendLogDao.getMasterSendLogById(masterSendLogId);
	}

	@Override
	public MasterSendLog getMasterSendLogBySmsId(String smsSendId) {
		return masterSendLogDao.getMasterSendLogBySmsId(smsSendId);
	}

	/**
	 * OWS,WBE第一次发送邮件确认函
	 */
	public void recordSendEmail(String email, String hotelName, String masterId, String lang, String sta) {
		SmsSend semail = new SmsSend();
		semail.setSmsSendId(CommonUtil.generatePrimaryKey());
		semail.setBizId(masterId);
		semail.setSmsContent(email);
		semail.setSmsType(SmsType.SMS_TYPE_EMAIL);
		semail.setVerifyCode(lang);
		semail.setMobileNumber(sta);

		semail = sendEmail(semail, "&orderNo=" + semail.getBizId(), hotelName);
		smsSendDao.addSmsSend(semail);
	}

	/**
	 * 每5分执行一次:WBE订单发送未发送成功的邮件
	 */
	public void retrySendEmail() {
		SmsSend semail = new SmsSend();
		semail.setResultCode("0");
		semail.setSmsType(SmsType.SMS_TYPE_EMAIL);
		List<SmsSend> ssList = smsSendDao.searchSmsSend(semail);
		for (SmsSend ss : ssList) {
			String hotelName = "";
			String hotelId = masterDao.getHotelIdByMasterId(ss.getBizId());
			if (StringUtils.hasText(hotelId)) {
				HotelVO hvo = hotelDao.getHotelVoByHotelId(hotelId, ss.getVerifyCode());
				if (hvo != null) {
					hotelName = hvo.getHotelName();
				}
			}
			ss = sendEmail(ss, null, hotelName);
			smsSendDao.save(ss);
		}
	}

	/**
	 * 每1分执行一次:淘宝新建订单30分钟后，如PMS下传消息的“消息状态”为IGNORE，就不向酒店发送邮件确认函。
	 */
	public void sendEmailOfTBADD() {
		Date nAfter30Min = DateUtil.addMinutes(new Date(), -29);
		List<MasterSendLogVO> mslvoList = masterSendLogDao.getMasterSendLogByChannelStaDate("TAOBAO", OrderStatus.ADD, nAfter30Min);
		for (MasterSendLogVO mslvo : mslvoList) {
			SendMsgLog cond = new SendMsgLog();
			cond.setHotelCode(mslvo.getHotelCode());
			cond.setMessageType(MessageType.RESERVATION);
			cond.setAction(OrderStatus.ADD);
			cond.setOrderId(mslvo.getMasterId());
			String byStatus = "'" + OXIStatusEnum.SEND_RESULT_SUCCESS.getValue() + "','" + OXIStatusEnum.SRP_PROCESSED.getValue() + "','" + OXIStatusEnum.SEND_IGNORE.getValue() + "'";
			cond.setByStatus(byStatus);
			List<SendMsgLog> smlList = sendMsgLogDao.getSendMsgLogByCondit(cond);
			boolean isSendEmail = true;
			for (SendMsgLog sml : smlList) {
				if (OXIStatusEnum.SEND_RESULT_SUCCESS.getValue().equals(sml.getStatus()) || OXIStatusEnum.SRP_PROCESSED.getValue().equals(sml.getStatus())) {
					isSendEmail = true;
					break;
				} else if (OXIStatusEnum.SEND_IGNORE.getValue().equals(sml.getStatus())) {
					isSendEmail = false;
				}
			}
			if (isSendEmail) {
				// 获取订单确认函快照但未发送
				SmsSend ss = new SmsSend();
				ss.setSmsSendId(mslvo.getSmsSendId());
				ss.setMobileNumber(mslvo.getMobileNumber());
				ss.setSmsType(mslvo.getSmsType());
				ss.setSmsContent(mslvo.getSmsContent());
				ss.setBizId(mslvo.getMasterId());
				ss.setVerifyCode(mslvo.getVerifyCode());
				ss = sendEmail(ss, null, mslvo.getHotelName());
				smsSendDao.save(ss);
				// 未生成订单确认函快照
				// else {
				// String lang = LanguageCode.MAIN_LANGUAGE_CODE;
				// HotelVO hvo =
				// hotelDao.getHotelVoByHotelId(order.getHotelId(), lang);
				// String hotelName = "";
				// if (hvo != null) {
				// hotelName = hvo.getHotelName();
				// }
				// recordSendEmail(hvo.getEmail(), hotelName,
				// order.getMasterId(), lang, order.getSta());
				// }
			}
		}
	}

	/**
	 * 重发订单确认函（数据取自表:mastersendlog）
	 */
	public SmsSend reSendEmail(String email, MasterSendLog msl, String lang) {
		SmsSend semail = new SmsSend();
		semail.setSmsSendId(msl.getSmsSendId());
		semail.setBizId(msl.getMasterId());
		semail.setSmsContent(email);
		semail.setSmsType(SmsType.SMS_TYPE_EMAIL);
		semail.setVerifyCode(lang);
		semail.setMobileNumber(msl.getSta());
		semail = sendEmail(semail, null, msl.getHotelName());
		return semail;
	}

	/**
	 * 记录淘宝新建订单的邮件确认函但不发送
	 */
	public void recordSendEmailOfTBADD(String email, String masterId, String lang, String sta) {
		SmsSend semail = new SmsSend();
		semail.setSmsSendId(CommonUtil.generatePrimaryKey());
		semail.setBizId(masterId);
		semail.setSmsContent(email);
		semail.setSmsType(SmsType.SMS_TYPE_EMAIL);
		semail.setVerifyCode(lang);
		semail.setMobileNumber(sta);
		// 查询子订单
		Master order = masterDao.getMasterOrderByOrderId(masterId);
		if (order == null || "0".equals(order.getPcrec())) {
			semail.setResultCode("2");
			semail.setResultMsg("SORRY,NO SUCH RESERVATIONS FOUND!");
			smsSendDao.addSmsSend(semail);
			return;
		}
		saveSendEmailLog(order, semail.getSmsSendId(), lang);

		semail.setResultCode("3");
		semail.setResultMsg("30MinutesLaterSendEmail");
		semail.setSendTime(new Date());
		smsSendDao.addSmsSend(semail);
	}

	/**
	 * 保存订单确认函快照
	 */
	public MasterSendLog saveSendEmailLog(Master order, String smssendId, String lang) {
		String masterId = order.getMasterId();

		// 客人姓名取中文姓名，如无取英文姓名
		if (!StringUtils.hasText(order.getName4())) {
			order.setName4(order.getName2() + " " + order.getName());
		}
		try {
			List<MasterRate> priceDetail = masterDao.getMasterRate(masterId);
			order.setMrList(priceDetail);

			List<MasterPackage> mpDbList = masterDao.getMasterPackage(masterId);
			List<MasterPackage> mpList = new ArrayList<MasterPackage>();
			// 处理套餐金额为每种套餐的总价
			Map<String, Object> packId = new HashMap<String, Object>();
			Map<String, Object> staticpackId = new HashMap<String, Object>();
			for (MasterPackage mp : mpDbList) {
				if (mp.getIsDynamic()) {
					if (packId.containsKey(mp.getPackageId())) {
						MasterPackage mpSrc = (MasterPackage) packId.get(mp.getPackageId());
						mpSrc.setAmount(AmountUtil.add(mpSrc.getAmount(), mp.getAmount()));
					} else {
						packId.put(mp.getPackageId(), mp);
						mpList.add(mp);
					}
				} else {
					if (staticpackId.containsKey(mp.getPackageId())) {
						MasterPackage mpSrc = (MasterPackage) staticpackId.get(mp.getPackageId());
						mpSrc.setAmount(AmountUtil.add(mpSrc.getAmount(), mp.getAmount()));
					} else {
						staticpackId.put(mp.getPackageId(), mp);
						mpList.add(mp);
					}
				}
			}
			order.setMpList(mpList);
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		// 语言
		if (!StringUtils.hasText(lang)) {
			lang = LanguageCode.ZH_CN;
		}
		HotelVO hvo = hotelDao.getHotelVoByHotelId(order.getHotelId(), lang);
		// 获取酒店图片和读取目录
		List<Picture> logoPicList = pictureManager.getBizPictureList("1", hvo.getLogoPicId());
		if (logoPicList != null && logoPicList.size() > 0) {
			// 获取logo
			hvo.setLogoPic(logoPicList.get(0));
		}
		hvo.setPictureUrlFolder(PropertiesUtil.getProperty(ProjectConfigConstant.pictureUrlContext));
		// 如果房价描述为空
		if (!StringUtils.hasText(order.getRatePlanDesc())) {
			RatePlanI18n i18n = rateplanDao.getRatePlanI18nById(order.getRatePlanId(), lang);
			if (i18n != null) {
				order.setRatePlanDesc(StringUtils.hasText(i18n.getRatePlanName()) ? i18n.getRatePlanName() : i18n.getDescription());
			}
		}
		// 如果房型名称为空
		if (!StringUtils.hasText(order.getRoomTypeName())) {
			RoomTypeVO roomTypeVo = roomTypeManager.getRoomTypeById(order.getRoomTypeId(), lang);
			if (roomTypeVo != null) {
				order.setRoomTypeName(roomTypeVo.getRoomTypeName());
			}
		}
		// 保存快照
		return saveOrderEmailConfirm(smssendId, lang, null, masterId, order, hvo);
	}

	private SmsSend sendEmail(SmsSend ss, String param, String hotelName) {
		String resultCode = "0";
		try {
			String languageCode = ss.getVerifyCode();
			if (!StringUtils.hasText(languageCode) || languageCode.equals("null")) {
				ss.setVerifyCode(LanguageCode.ZH_CN);
			}
			String address = ProjectConfigConstant.wbeUrl + "order_emailConfirm.do?smsSendId=" + ss.getSmsSendId() + "&request_locale=" + languageCode;
			if (StringUtils.hasText(param)) {
				address = address + param;
			}
			URL url = new URL(address);// 网址
			URLConnection urlConnection = url.openConnection();// 打开一个连接
			InputStream inputStream = urlConnection.getInputStream();// 打开的连接读取的输入流。
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer sb = new StringBuffer();
			String line = bufferedReader.readLine();
			while (line != null) {
				sb.append(line);
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			String content = sb.toString().replace("\"/", "\"" + ProjectConfigConstant.wbeUrl);
			// 发送邮件
			log.info("*****预订开始发送邮件。。。");
			EmailVO emailv = new EmailVO();
			// Order Status
			String sta = ss.getMobileNumber();
			String code = "";
			if (OrderStatus.ADD.equals(sta) || OrderStatus.PAID.equals(sta)) {
				code = "R0175";
			} else if (OrderStatus.EDIT.equals(sta)) {
				code = "R0176";
			} else if (OrderStatus.CANCEL.equals(sta)) {
				code = "R0177";
			}
			String value = "";
			int indexOfUnderscore = languageCode.indexOf('_');
			String language = languageCode.substring(0, indexOfUnderscore);
			String country = languageCode.substring(indexOfUnderscore + 1);
			Locale l = new Locale(language, country);
			if (StringUtils.hasText(code)) {
				value = messageSource.getMessage(code, new Object[] {}, l);
			}
			// Email Subject
			emailv.setSubject(hotelName + "-" + value + " (" + messageSource.getMessage("R0178", new Object[] {}, l) + ":" + ss.getBizId() + ")");
			// Email Content
			emailv.setContent(content);
			emailv.setToArray(ss.getSmsContent().split(";"));
			emailManager.sendHtml(emailv);
			resultCode = "1";
			ss.setResultMsg("发送成功");
			log.info("*****邮件发送成功,收件人：" + ss.getSmsContent());
		} catch (Exception e) {
			String msg = CommonUtil.getExceptionMsg(e, new String[] { "ccm" });
			log.error(msg);
			if (msg.length() > 200) {
				msg = msg.substring(0, 200);
			}
			ss.setResultMsg(msg);

			if (e instanceof BizException && "sendEmailExcpetion".equals(((BizException) e).getErrKey())) {
				resultCode = "2";
			}
		}
		ss.setResultCode(resultCode);
		ss.setSendTime(new Date());
		return ss;
	}

	/**
	 * 保存订单邮件确认函的快照表
	 */
	private MasterSendLog saveOrderEmailConfirm(String smsSendId, String language, MasterSendLog masterSendLog, String orderNo, Master order, HotelVO hvo) {
		if (masterSendLog == null) {
			masterSendLog = new MasterSendLog();
		}

		// 支付类型(付款方式)/担保类型(guaranteeType)
		String payment = order.getPayment();
		if (GuaranteeCode.CCGTD.equals(payment)) {
			int indexOfUnderscore = language.indexOf('_');
			String languageA = language.substring(0, indexOfUnderscore);
			String country = language.substring(indexOfUnderscore + 1);
			Locale l = new Locale(languageA, country);
			masterSendLog.setPayment(messageSource.getMessage("CCGTD", new Object[] {}, l) + "(" + order.getCardCode() + ")");
		} else {
			HotelGuaranteeI18n hgi = hotelGuaranteeDao.getHotelGuaranteeIdAndPolicyName(order.getHotelId(), language, payment);
			if (hgi != null) {
				masterSendLog.setPayment(hgi.getPolicyName());
			}

		}

		// 手机（客户的）
		masterSendLog.setMobile(order.getMobile());
		// 信用卡类型
		masterSendLog.setCardCode(order.getCardCode());
		// 邮箱（客户的）
		masterSendLog.setMasterEmail(order.getEmail());
		// 地址（客户的）
		masterSendLog.setAddressLine(order.getAddressLine());
		// 房间数
		masterSendLog.setNumberOfUnits(order.getNumberOfUnits());

		masterSendLog.setMasterSendLogId(CommonUtil.generatePrimaryKey());
		masterSendLog.setSmsSendId(smsSendId);
		masterSendLog.setMasterId(orderNo);
		masterSendLog.setLanguage(language);
		masterSendLog.setHotelId(order.getHotelId());
		masterSendLog.setHotelCode(hvo.getHotelCode());
		masterSendLog.setHotelName(hvo.getHotelName());
		masterSendLog.setChannelCode(order.getChannel());
		masterSendLog.setTelephone(hvo.getTelephone());
		masterSendLog.setFax(order.getFax());
		masterSendLog.setPostCode(hvo.getPostCode());
		masterSendLog.setEmail(hvo.getEmail());
		masterSendLog.setAddress(hvo.getAddress());
		masterSendLog.setName4(order.getName4());
		masterSendLog.setArr(order.getArr());
		masterSendLog.setDep(order.getDep());
		masterSendLog.setSta(order.getSta());
		masterSendLog.setChanged(order.getChanged());
		masterSendLog.setRoomTypeName(order.getRoomTypeName());
		masterSendLog.setRatePlanDesc(order.getRatePlanDesc());
		masterSendLog.setGstno(order.getGstno());
		masterSendLog.setCharge(order.getCharge());
		masterSendLog.setSrqs(order.getSrqs());
		masterSendLog.setRef(order.getRef());
		masterSendLog.setCheckInTimeDesc(hvo.getCheckInTimeDesc());
		masterSendLog.setCheckOutTimeDesc(hvo.getCheckOutTimeDesc());
		masterSendLog.setCancelPolicyDesc(hvo.getCancelPolicyDesc());
		masterSendLog.setMrListJson(JSON.toJSONString(order.getMrList()));
		masterSendLog.setMpListJson(JSON.toJSONString(order.getMpList()));
		masterSendLog.setCreatedTime(DateUtil.currentDateTime());
		// 重新计算税后房价
		BigDecimal amountAfterTax = new BigDecimal(0);
		amountAfterTax = AmountUtil.add(amountAfterTax, order.getRmrate());
		for (MasterRate mr : order.getMrList()) {
			amountAfterTax = AmountUtil.add(amountAfterTax, mr.getSetrateAfterTax());
		}
		masterSendLog.setChargeAfterTax(amountAfterTax);
		// 设置logo
		if (hvo.getLogoPic() != null) {
			masterSendLog.setPictureUrlFolder(hvo.getPictureUrlFolder());
			masterSendLog.setLogoPicUrl(hvo.getLogoPic().getUrl());
		}

		if (StringUtils.hasText(smsSendId)) {
			saveMasterSendLog(masterSendLog); // 存表
		}
		return masterSendLog;
	}
}
