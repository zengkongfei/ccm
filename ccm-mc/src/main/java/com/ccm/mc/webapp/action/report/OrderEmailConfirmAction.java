package com.ccm.mc.webapp.action.report;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.ccm.api.dao.sms.SmsSendDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.EmailType;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.email.MasterSendLog;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.order.MasterPackage;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.order.OrderEmailConfirm;
import com.ccm.api.model.order.vo.OrderEmailConfirmResult;
import com.ccm.api.model.rsvtype.vo.SearchOrderEmailConfirmCriteria;
import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.email.HotelEmailMapperManager;
import com.ccm.api.service.email.MasterSendLogManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.order.OrderManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;
import com.ccm.mc.webapp.util.WordUtil;

@SuppressWarnings("restriction")
public class OrderEmailConfirmAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8698271903014503954L;

	private Log log = LogFactory.getLog(OrderEmailConfirmAction.class);

	// 查询条件
	private SearchOrderEmailConfirmCriteria soecc = new SearchOrderEmailConfirmCriteria();

	// 返回结果
	private OrderEmailConfirmResult orderEmailConfirmResult = new OrderEmailConfirmResult();
	@Resource
	private EmailManager emailManager;
	
	@Resource
	private HotelManager hotelManager;

	@Resource
	private HotelMCManager hotelMCManager;

	@Resource
	private ChannelManager channelManager;

	@Resource
	private OrderManager orderManager;

	@Resource
	private MasterSendLogManager masterSendLogManager;
	@Resource
	private HotelEmailMapperManager hotelEmailMapperManager;

	@Resource
	private SmsSendDao smsSendDao;

	// 酒店列表
	private List<Hotel> hotelList;

	// 渠道列表
	private List<Channel> channelList;

	// 订单状态
	private Map<String, String> orderStatus;

	private MasterSendLog order = new MasterSendLog();

	private HotelVO hvo = new HotelVO();

	static Map<String, String> statusDictMap = new HashMap<String, String>();

	static {
		statusDictMap.put("CHECKIN_ZH_CN", "已入住");
		statusDictMap.put("CHECKOUT_ZH_CN", "已离店");
		statusDictMap.put("ADD_ZH_CN", "新建");
		statusDictMap.put("CANCEL_ZH_CN", "已取消");
		statusDictMap.put("EDIT_ZH_CN", "已修改");
		statusDictMap.put("NOSHOW_ZH_CN", "应到未到");
		statusDictMap.put("PAID_ZH_CN", "支付成功");

		statusDictMap.put("CHECKIN_EN_US", "CHECKIN");
		statusDictMap.put("CHECKOUT_EN_US", "CHECKOUT");
		statusDictMap.put("ADD_EN_US", "Booking");
		statusDictMap.put("CANCEL_EN_US", "Cancellation");
		statusDictMap.put("EDIT_EN_US", "Modification");
		statusDictMap.put("NOSHOW_EN_US", "NO SHOW");
		statusDictMap.put("PAID_EN_US", "Paid,Pay to complete");

		statusDictMap.put("CHECKIN_JA_JP", "CHECKIN");
		statusDictMap.put("CHECKOUT_JA_JP", "CHECKOUT");
		statusDictMap.put("ADD_JA_JP", "予約");
		statusDictMap.put("CANCEL_JA_JP", "Cancellation");
		statusDictMap.put("EDIT_JA_JP", "修正");
		statusDictMap.put("NOSHOW_JA_JP", "NO SHOW");
		statusDictMap.put("PAID_JA_JP", "支払われた,完了するために支払う");

	}

	/**
	 * 初始化
	 * 
	 * @return
	 */
	public String list() {
		HashMap<String, String> emailType = new HashMap<String, String>();
		//emailType.put(null, getText("common.select.plesesSelect"));
		emailType.put(EmailType.Order, getText("ccm.ReservationEmailConfirmationLetter"));
		emailType.put(EmailType.HotelInterface, getText("ccm.HotelInterfaceReminderLetter"));
		emailType.put(EmailType.CreditLimitNotice, getText("ccm.CreditLimitNoticeLetter"));
		emailType.put(EmailType.AllotmentNotice, getText("ccm.AllotmentNoticeLetter"));
		this.getRequest().setAttribute("emailType", emailType);
		

		// 初始化查询条件
		B2BUser user = getCurLoginUser();
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hotelList = hotelManager.getAll();
		} else {
			hotelList = hotelMCManager.getUserHotelByChainAndUserId(user.getUserId());
		}

		//渠道用户渠道权限		
		if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
			channelList=user.getChannels();
		}else{
			channelList = channelManager.getAll();
		}
						
				
		orderStatus = OrderStatus.getOrderProductStatusMap();

		// 设置初始化标识
		getRequest().setAttribute("isInit", "1");
		return "list";
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public String query() {

		soecc.setEndDate(DateUtil.setDateHHmmss(soecc.getEndDate(), 23, 59, 59));

		HashMap<String, String> emailType = new HashMap<String, String>();
		emailType.put(null, getText("common.select.plesesSelect"));
		emailType.put(EmailType.Order, getText("ccm.ReservationEmailConfirmationLetter"));
		emailType.put(EmailType.HotelInterface, getText("ccm.HotelInterfaceReminderLetter"));
		emailType.put(EmailType.CreditLimitNotice, getText("ccm.CreditLimitNoticeLetter"));
		emailType.put(EmailType.AllotmentNotice, getText("ccm.AllotmentNoticeLetter"));
		
		this.getRequest().setAttribute("emailType", emailType);
		// 分页
		int pageSize = this.getCurrentPageSize("orderEmailConfirm");
		int pageNo = this.getCurrentPageNo("orderEmailConfirm");
		soecc.setPageNum(pageNo);
		soecc.setPageSize(pageSize);

		// 设置查询参数
		soecc.setExcelFlag(Boolean.FALSE);
		if (EmailType.Order.equals(soecc.getEmailType())) {
			orderEmailConfirmResult = orderManager.searchOrderEmailConfirm(soecc);
			orderStatus = OrderStatus.getOrderProductStatusMap();
		} else if (EmailType.HotelInterface.equals(soecc.getEmailType())
				||EmailType.CreditLimitNotice.equals(soecc.getEmailType())
				||EmailType.AllotmentNotice.equals(soecc.getEmailType())) {
			orderEmailConfirmResult = smsSendDao.searchHotelInterfaceEmail(soecc);
		}

		if (orderEmailConfirmResult.getResultList() != null && orderEmailConfirmResult.getResultList().size() > 0) {
			for (OrderEmailConfirm orderEmail : orderEmailConfirmResult.getResultList()) {
				if ("1".equals(orderEmail.getResultCode())) {
					orderEmail.setResultCode(getText("common.Yes"));
				} else {
					orderEmail.setResultCode(getText("common.Not"));
				}
			}
		}
		// 初始化查询条件
		B2BUser user = getCurLoginUser();
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hotelList = hotelManager.getAll();
		} else {
			hotelList = hotelMCManager.getUserHotelByChainAndUserId(user.getUserId());
		}

		emailType = new HashMap<String, String>();
		//emailType.put(null, getText("common.select.plesesSelect"));
		emailType.put(EmailType.Order, getText("ccm.ReservationEmailConfirmationLetter"));
		emailType.put(EmailType.HotelInterface, getText("ccm.HotelInterfaceReminderLetter"));
		emailType.put(EmailType.CreditLimitNotice, getText("ccm.CreditLimitNoticeLetter"));
		emailType.put(EmailType.AllotmentNotice, getText("ccm.AllotmentNoticeLetter"));
		this.getRequest().setAttribute("emailType", emailType);
		
		if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
			channelList=user.getChannels();
		}else{
			channelList = channelManager.getAll();
		}
		
		orderStatus = OrderStatus.getOrderProductStatusMap();
		
		return "list";
	}

	/**
	 * 导出
	 * 
	 * @return
	 * @throws IOException
	 */
	public String export() throws IOException {
		// 导出excel标识
		soecc.setExcelFlag(Boolean.TRUE);
		soecc.setNeedPage(Boolean.FALSE);

		soecc.setEndDate(DateUtil.setDateHHmmss(soecc.getEndDate(), 23, 59, 59));

		try {
			SXSSFWorkbook workbook = null;
			String excelName = null;
			if (EmailType.Order.equals(soecc.getEmailType())) {
				orderEmailConfirmResult = orderManager.searchOrderEmailConfirm(soecc);
				// excel表头
				String[] colName = { getText("ccm.Channel.ChannelCode"), getText("ccm.ReservationMonitorReport.PropertyCode"), getText("ccm.ReservationEmailConfirmationLetter.ConfirmationNo"), getText("ccm.ReservationMonitorReport.ReservationStatus"), getText("ccm.ReservationEmailConfirmationLetter.EmailSendingDate"), getText("ccm.ReservationEmailConfirmationLetter.EmailAddress"), getText("ccm.ReservationEmailConfirmationLetter.SendingStatus"), getText("ccm.ReservationEmailConfirmationLetter.SendNumber") };
				// excel要导出的数据
				List<String[]> dataList = this.getExcelDataBySendList(orderEmailConfirmResult.getResultList());

				workbook = ExportUtil.createExcel2("orderEmailConfirmList", colName, dataList);

				// 生成excel文件名
				excelName = ExportUtil.createFileName(getText("ccm.ReservationEmailConfirmationLetter"));
			} else if (EmailType.HotelInterface.equals(soecc.getEmailType())
					||EmailType.CreditLimitNotice.equals(soecc.getEmailType())
					||EmailType.AllotmentNotice.equals(soecc.getEmailType())) {
				orderEmailConfirmResult = smsSendDao.searchHotelInterfaceEmail(soecc);
				// excel表头
				String[] colName = { getText("ccm.Channel.ChannelCode"), getText("ccm.ReservationEmailConfirmationLetter.EmailSendingDate"), getText("ccm.ReservationEmailConfirmationLetter.EmailAddress"), getText("ccm.ReservationEmailConfirmationLetter.SendingStatus"), getText("ccm.ReservationEmailConfirmationLetter.SendNumber") };
				// excel要导出的数据
				List<String[]> dataList = this.getExcelDataBySendHotelInterfaceList(orderEmailConfirmResult.getResultList());

				workbook = ExportUtil.createExcel2("orderEmailConfirmList", colName, dataList);

				// 生成excel文件名
				excelName = ExportUtil.createFileName(getText("ccm.HotelInterfaceReminderLetter"));
			}

			// 设置导出的excel文件从页面中下载
			getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");
			// getResponse().setContentType("application/vnd.ms-excel;charset=gb2312");

			// 输出流
			OutputStream os = getResponse().getOutputStream();

			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			getResponse().getWriter().print(CommonUtil.getExceptionMsg(e, "export fail"));
			getResponse().getWriter().flush();
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}

		return null;
	}

	private List<String[]> getExcelDataBySendList(List<OrderEmailConfirm> resultList) {
		List<String[]> dataList = new ArrayList<String[]>();

		// 如果订单结果不为空
		if (resultList != null && resultList.size() > 0) {
			for (OrderEmailConfirm orderEmail : resultList) {
				String[] arr = new String[8];
				arr[0] = orderEmail.getChannelCode();
				arr[1] = orderEmail.getHotelCode();
				arr[2] = orderEmail.getMasterId();
				arr[3] = orderEmail.getSta();
				arr[4] = DateUtil.convertDateTimeToString(orderEmail.getSendTime());
				arr[5] = orderEmail.getEmailAddress();
				arr[6] = "1".equals(orderEmail.getResultCode()) ? getText("common.Yes") : getText("common.Not");
				arr[7] = orderEmail.getSendCount() + "";
				dataList.add(arr);
			}
		}
		return dataList;
	}

	private List<String[]> getExcelDataBySendHotelInterfaceList(List<OrderEmailConfirm> resultList) {
		List<String[]> dataList = new ArrayList<String[]>();

		// 如果订单结果不为空
		if (resultList != null && resultList.size() > 0) {
			for (OrderEmailConfirm orderEmail : resultList) {
				String[] arr = new String[8];
				arr[1] = orderEmail.getHotelCode();
				arr[4] = DateUtil.convertDateTimeToString(orderEmail.getSendTime());
				arr[5] = orderEmail.getEmailAddress();
				arr[6] = "1".equals(orderEmail.getResultCode()) ? "是" : "否";
				arr[7] = orderEmail.getSendCount() + "";
				dataList.add(arr);
			}
		}
		return dataList;
	}

	/**
	 * 获取订单确认函详情
	 * 
	 * @return
	 */
	public String ajaxDetail() {
		String smsSendId = getRequest().getParameter("smsSendId");
		SmsSend smsSend = smsSendDao.get(smsSendId);
		// 酒店接口提醒函
		if (EmailType.HotelInterface.equals(soecc.getEmailType())) {
			getRequest().setAttribute("title", EmailType.email_subject);
			getRequest().setAttribute("content1", EmailType.email_content_view1);
			if (smsSend != null) {
				getRequest().setAttribute("hotelName", smsSend.getMobileNumber());
			} else {
				getRequest().setAttribute("hotelName", "");
			}
			getRequest().setAttribute("content5", EmailType.email_content_view5);
			getRequest().setAttribute("content2", EmailType.email_content_view2);
			getRequest().setAttribute("content3", EmailType.email_content_view3);
			getRequest().setAttribute("content4", EmailType.email_content_view4);
			getRequest().setAttribute("content6", EmailType.email_content_view6);
			getRequest().setAttribute("content7", EmailType.email_content_view7);
			getRequest().setAttribute("content8", EmailType.email_content_view8);
			getRequest().setAttribute("content9", EmailType.email_content_view9);
			getRequest().setAttribute("content10", EmailType.email_content_view10);
			getRequest().setAttribute("content11", EmailType.email_content_view11);
			getRequest().setAttribute("content12", EmailType.email_content_view12);
			getRequest().setAttribute("content13", EmailType.email_content_view13);
			getRequest().setAttribute("content14", EmailType.email_content_view14);
			getRequest().setAttribute("content15", EmailType.email_content_view15);
			getRequest().setAttribute("content16", EmailType.email_content_view16);
			getRequest().setAttribute("content17", EmailType.email_content_view17);
			getRequest().setAttribute("content18", EmailType.email_content_view18);
			return "detailHotelInterface";
		}else if (EmailType.CreditLimitNotice.equals(soecc.getEmailType())
				||EmailType.AllotmentNotice.equals(soecc.getEmailType())){
			getRequest().setAttribute("content",smsSend.getBodycontent());
			return "emailNotice";
		}
		// 获取masterSendLog对象
		order = masterSendLogManager.getMasterSendLogBySmsId(smsSendId);
		if (order == null) {
			return "detailError";
		}

		String language = StringUtils.isBlank(order.getLanguage()) ? "ZH_CN" : order.getLanguage();
		String sk = order.getSta() + "_" + language;
		sk = sk.replaceAll("\\s*|\t|\r|\n", "").toUpperCase();
		order.setSta(statusDictMap.get(sk));

		// 转换订单rate
		if (StringUtils.isNotBlank(order.getMrListJson())) {
			try {
				List<MasterRate> mrList = JSON.parseArray(order.getMrListJson(), MasterRate.class);
				order.setMrList(mrList);
			} catch (Exception e) {
			}
		}

		// 转换订单package
		if (StringUtils.isNotBlank(order.getMpListJson())) {
			try {
				List<MasterPackage> mpList = JSON.parseArray(order.getMpListJson(), MasterPackage.class);
				order.setMpList(mpList);
			} catch (Exception e) {
			}
		}

		if (LanguageCode.EN_US.equalsIgnoreCase(order.getLanguage())) {
			return "detailEn";
		} else if (LanguageCode.JA_JP.equalsIgnoreCase(order.getLanguage())) {
			return "detailJa";
		} else {
			return "detailZh";
		}
	}

	/**
	 * 重新发送订单确认函
	 * 
	 * @throws IOException
	 */
	public void ajaxReSend() throws IOException {
		String smsSendId = getRequest().getParameter("smsSendId");
		// 获取smsSend对象
		SmsSend smsSend = smsSendDao.get(smsSendId);
		if (smsSend == null) {
			ajaxResponse("smsSendIsNull");
			return;
		}
		// 酒店接口提醒函
		if (EmailType.HotelInterface.equals(soecc.getEmailType())) {
			// 发送Email
			if (StringUtils.isNotBlank(smsSend.getSmsContent())) {
				String result = hotelEmailMapperManager.sendEmail2HotelOff(smsSend.getBizId(), smsSend.getMobileNumber(), smsSend.getSmsContent());
				if ("1".equals(result)) {
					ajaxResponse("SUCCESS");
					return;
				} else {
					ajaxResponse(result);
					return;
				}
			}
			ajaxResponse("SendFail");
			return;
		}else if(EmailType.CreditLimitNotice.equals(soecc.getEmailType())
				||EmailType.AllotmentNotice.equals(soecc.getEmailType())){
			String result =emailManager.resendHtmlEmail(soecc.getEmailType(),smsSend.getSmsContent(),smsSend.getBizId(),smsSend.getMobileNumber(), 
					smsSend.getSource(), smsSend.getTitle(), smsSend.getBodycontent());
			if ("1".equals(result)) {
				ajaxResponse("SUCCESS");
				return;
			} else {
				ajaxResponse("Send failed");
				return;
			}
		}

		MasterSendLog msl = masterSendLogManager.getMasterSendLogBySmsId(smsSendId);
		if (msl == null) {
			ajaxResponse("mslIsNull");
			return;
		}

		// 发送Email
		if (StringUtils.isNotBlank(smsSend.getSmsContent())) {
			String language = StringUtils.isBlank(msl.getLanguage()) ? LanguageCode.MAIN_LANGUAGE_CODE : msl.getLanguage();
			SmsSend result = masterSendLogManager.reSendEmail(smsSend.getSmsContent(), msl, language);

			// 保存重发记录
			String smsSendId2 = CommonUtil.generatePrimaryKey();
			result.setSmsSendId(smsSendId2);
			result.setCreatedTime(DateUtil.currentDateTime());
			result.setSendTime(DateUtil.currentDateTime());
			smsSendDao.addSmsSend(result);

			msl.setMasterSendLogId(CommonUtil.generatePrimaryKey());
			msl.setSmsSendId(smsSendId2);
			msl.setCreatedTime(DateUtil.currentDateTime());
			masterSendLogManager.saveMasterSendLog(msl);

			if ("1".equals(result.getResultCode())) {
				ajaxResponse("SUCCESS");
				return;
			} else {
				ajaxResponse(result.getResultMsg());
				return;
			}
		}

		ajaxResponse("SendFail");
		return;
	}

	/**
	 * ajax验证是否可以导出word
	 */
	public void ajaxIsWord() {
		// 订单邮件确认函
		if (EmailType.Order.equals(soecc.getEmailType())) {
			String smsSendId = getRequest().getParameter("smsSendId");
			MasterSendLog msl = masterSendLogManager.getMasterSendLogBySmsId(smsSendId);
			if (msl == null) {
				ajaxResponse("mslIsNull");
				return;
			}
		}

		ajaxResponse("SUCCESS");
	}

	/**
	 * word导出订单确认函
	 */
	public void ajaxWordExport() {
		String smsSendId = getRequest().getParameter("smsSendId");
		// 酒店接口提醒函
		if (EmailType.HotelInterface.equals(soecc.getEmailType())) {
			SmsSend smsSend = smsSendDao.get(smsSendId);
			// 设置数据
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", EmailType.email_subject);
			map.put("content1", EmailType.email_content_view1);
			if (smsSend != null) {
				map.put("hotelName", smsSend.getMobileNumber());
			} else {
				map.put("hotelName", "");
			}
			map.put("content5", EmailType.email_content_view5);
			map.put("content2", EmailType.email_content_view2);
			map.put("content3", EmailType.email_content_view3);
			map.put("content4", getImageStr());
			map.put("content6", EmailType.email_content_view6);
			map.put("content7", EmailType.email_content_view7);
			map.put("content8", EmailType.email_content_view8);
			map.put("content9", EmailType.email_content_view9);
			map.put("content10", EmailType.email_content_view10);
			map.put("content11", EmailType.email_content_view11);
			map.put("content12", EmailType.email_content_view12);
			map.put("content13", EmailType.email_content_view13);
			map.put("content14", EmailType.email_content_view14);
			map.put("content15", EmailType.email_content_view15);
			map.put("content16", EmailType.email_content_view16);
			map.put("content17", EmailType.email_content_view17);
			map.put("content18", EmailType.email_content_view18);

			WordUtil handler = new WordUtil();
			Writer out;

			String template = "hotelOffInterface.xml";
			try {
				String wordName = ExportUtil.createFileName("酒店接口提醒函 ");
				getResponse().setContentType("application/vnd.ms-word;charset=UTF-8");
				getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(wordName.getBytes(), "ISO8859-1") + ".doc" + "\"");
				// 输出流
				out = new OutputStreamWriter(getResponse().getOutputStream(), "UTF-8");
				handler.write("/word", template, map, out);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		MasterSendLog msl = masterSendLogManager.getMasterSendLogBySmsId(smsSendId);
		// 设置数据
		Map<String, String> map = new HashMap<String, String>();

		map.put("payment", msl.getPayment());
		map.put("mobile", msl.getMobile());
		map.put("masterEmail", msl.getMasterEmail());
		map.put("addressLine", msl.getAddressLine());
		map.put("numberOfUnits", msl.getNumberOfUnits().toString());

		map.put("masterId", msl.getMasterId());
		map.put("hotelName", msl.getHotelName());
		map.put("changed", DateUtil.convertDateTimeToString(msl.getChanged()));
		map.put("name4", msl.getName4());
		map.put("telephone", msl.getTelephone());
		map.put("fax", msl.getFax());
		map.put("channel", msl.getChannelCode());
		map.put("email", msl.getEmail());
		map.put("arr", DateUtil.convertDateToString(msl.getArr()));
		map.put("dep", DateUtil.convertDateToString(msl.getDep()));
		map.put("roomTypeName", msl.getRoomTypeName());
		map.put("ratePlanDesc", msl.getRatePlanDesc());
		map.put("gstno", msl.getGstno() + "");
		map.put("charge", msl.getCurrencyCode()+" " + msl.getCharge());
		map.put("srqs", msl.getSrqs());
		if (StringUtils.isNotBlank(msl.getRef()) && StringUtils.isNotBlank(msl.getSrqs())) {
			map.put("ref", msl.getRef().replace(msl.getSrqs(), ""));
		}
		map.put("checkInTimeDesc", msl.getCheckInTimeDesc());
		map.put("checkOutTimeDesc", msl.getCheckOutTimeDesc());
		map.put("cancelPolicyDesc", msl.getCancelPolicyDesc());
		map.put("address", msl.getAddress());
		map.put("postCode", msl.getPostCode());

		String language = StringUtils.isBlank(msl.getLanguage()) ? "ZH_CN" : msl.getLanguage();
		String sk = msl.getSta() + "_" + language;
		sk = sk.replaceAll("\\s*|\t|\r|\n", "").toUpperCase();
		map.put("sta", statusDictMap.get(sk));

		// 如果日房价不为空
		if (StringUtils.isNotBlank(msl.getMrListJson())) {
			List<MasterRate> mrList = JSON.parseArray(msl.getMrListJson(), MasterRate.class);
			if (mrList != null && mrList.size() > 0) {
				String mrListString = "";
				for (MasterRate masterRate : mrList) {
					mrListString += DateUtil.convertDateToString(masterRate.getDate()) + ": "+msl.getCurrencyCode() + masterRate.getSetrate() + " ";
				}
				map.put("mrListString", mrListString);
			}
		}

		// 如果套餐不为空
		if (StringUtils.isNotBlank(msl.getMpListJson())) {
			List<MasterPackage> mpList = JSON.parseArray(msl.getMpListJson(), MasterPackage.class);
			if (mpList != null && mpList.size() > 0) {
				String mpListString = "";
				String ndynamicMpListString = "";
				for (int i = 0; i < mpList.size(); i++) {
					MasterPackage masterPackage = mpList.get(i);
					if (masterPackage.getIsDynamic()) {
						if (LanguageCode.ZH_CN.equalsIgnoreCase(language)) {
							mpListString += ";" + masterPackage.getPackageName() + "份:共" + masterPackage.getAmount() + "元";
						} else {
							mpListString += ";" + masterPackage.getPackageName() + ":Total" + masterPackage.getAmount() + msl.getCurrencyCode();
						}
					} else {
						if (LanguageCode.ZH_CN.equalsIgnoreCase(language)) {
							ndynamicMpListString += ";" + masterPackage.getPackageName() + "份:共" + masterPackage.getAmount() + "元";
						} else {
							ndynamicMpListString += ";" + masterPackage.getPackageName() + ":Total" + masterPackage.getAmount() + "元";
						}
					}
				}

				if (StringUtils.isNotBlank(mpListString)) {
					map.put("mpListString", mpListString.substring(1));
				}

				if (StringUtils.isNotBlank(ndynamicMpListString)) {
					map.put("ndynamicMpListString", ndynamicMpListString.substring(1));
				}
			}
		}

		WordUtil handler = new WordUtil();
		Writer out;

		String template = "masterConfirmation-zh_CN.xml";
		if (LanguageCode.EN_US.equalsIgnoreCase(language)) {
			template = "masterConfirmation-en_US.xml";
		} else if (LanguageCode.JA_JP.equalsIgnoreCase(language)) {
			template = "masterConfirmation-ja_JP.xml";
		}
		try {
			// out = new OutputStreamWriter(new FileOutputStream(
			// "E:\\Study\\测试文件\\testModel.doc"), "UTF-8");//生成文件的保存路径

			String wordName = ExportUtil.createFileName(getText("ccm.ReservationEmailConfirmationLetter") + msl.getMasterId());
			getResponse().setContentType("application/vnd.ms-word;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(wordName.getBytes(), "ISO8859-1") + ".doc" + "\"");
			// 输出流
			out = new OutputStreamWriter(getResponse().getOutputStream(), "UTF-8");
			handler.write("/word", template, map, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getImageStr() {
		String imgFile = EmailType.email_pic_path;
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	public SearchOrderEmailConfirmCriteria getSoecc() {
		return soecc;
	}

	public void setSoecc(SearchOrderEmailConfirmCriteria soecc) {
		this.soecc = soecc;
	}

	public OrderEmailConfirmResult getOrderEmailConfirmResult() {
		return orderEmailConfirmResult;
	}

	public void setOrderEmailConfirmResult(OrderEmailConfirmResult orderEmailConfirmResult) {
		this.orderEmailConfirmResult = orderEmailConfirmResult;
	}

	public List<Hotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	public Map<String, String> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Map<String, String> orderStatus) {
		this.orderStatus = orderStatus;
	}

	public MasterSendLog getOrder() {
		return order;
	}

	public void setOrder(MasterSendLog order) {
		this.order = order;
	}

	public HotelVO getHvo() {
		return hvo;
	}

	public void setHvo(HotelVO hvo) {
		this.hvo = hvo;
	}

}
