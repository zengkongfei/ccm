/**
 * 
 */
package com.ccm.api.service.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.hotel.CustomDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.log.SendMsgLogDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.dao.rsvtype.RsvchanBlockDao;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.constant.OWSConstant;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.constant.Sex;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.enume.OXIStatusEnum;
import com.ccm.api.model.enume.ProfileType;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterPackage;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.service.common.AsyncManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.email.MasterSendLogManager;
import com.ccm.api.service.log.SendMsgLogManager;
import com.ccm.api.service.order.AbstractReservationToPmsService;
import com.ccm.api.service.order.OXIReservationNodesService;
import com.ccm.api.service.order.ReservationService;
import com.ccm.api.service.sms.SmsManager2;
import com.ccm.api.util.AmountUtil;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.DateUtils;
import com.ccm.api.util.IncrementService;
import com.ccm.api.util.RSAEncrypt;
import com.ccm.oxi.profile.AddressType;
import com.ccm.oxi.profile.CreditCard;
import com.ccm.oxi.profile.ElectronicAddress;
import com.ccm.oxi.profile.ElectronicAddressType;
import com.ccm.oxi.profile.ElectronicAddresses;
import com.ccm.oxi.profile.IndividualName;
import com.ccm.oxi.profile.MfNationalName;
import com.ccm.oxi.profile.MfNegotiatedRates;
import com.ccm.oxi.profile.PhoneNumber;
import com.ccm.oxi.profile.PhoneNumberType;
import com.ccm.oxi.profile.PhoneNumbers;
import com.ccm.oxi.profile.PostalAddress;
import com.ccm.oxi.profile.PostalAddresses;
import com.ccm.oxi.profile.Profile;
import com.ccm.oxi.reservation.Amount;
import com.ccm.oxi.reservation.ArrivalTransport;
import com.ccm.oxi.reservation.Describe;
import com.ccm.oxi.reservation.GuaranteeInfo;
import com.ccm.oxi.reservation.GuaranteeType;
import com.ccm.oxi.reservation.GuestCount;
import com.ccm.oxi.reservation.GuestCounts;
import com.ccm.oxi.reservation.HotelReference;
import com.ccm.oxi.reservation.InHouseTimeSpan;
import com.ccm.oxi.reservation.MfAmount;
import com.ccm.oxi.reservation.MfImage;
import com.ccm.oxi.reservation.MfReservationAction;
import com.ccm.oxi.reservation.PaymentDue;
import com.ccm.oxi.reservation.PaymentInstruction;
import com.ccm.oxi.reservation.PaymentInstructions;
import com.ccm.oxi.reservation.PaymentMethodType;
import com.ccm.oxi.reservation.Price;
import com.ccm.oxi.reservation.Rate;
import com.ccm.oxi.reservation.RateBasisTimeUnitType;
import com.ccm.oxi.reservation.RatePlan;
import com.ccm.oxi.reservation.RatePlans;
import com.ccm.oxi.reservation.Rates;
import com.ccm.oxi.reservation.ResComment;
import com.ccm.oxi.reservation.ResComments;
import com.ccm.oxi.reservation.ResCreditCard;
import com.ccm.oxi.reservation.ResCreditCards;
import com.ccm.oxi.reservation.ResGuest;
import com.ccm.oxi.reservation.ResGuests;
import com.ccm.oxi.reservation.ResProfile;
import com.ccm.oxi.reservation.ResProfiles;
import com.ccm.oxi.reservation.Reservation;
import com.ccm.oxi.reservation.ReservationActionType;
import com.ccm.oxi.reservation.ReservationStatusType;
import com.ccm.oxi.reservation.RoomStay;
import com.ccm.oxi.reservation.RoomStays;
import com.ccm.oxi.reservation.SelectedMembership;
import com.ccm.oxi.reservation.SelectedMemberships;
import com.ccm.oxi.reservation.ServicePricingType;
import com.ccm.oxi.reservation.Services;
import com.ccm.oxi.reservation.StayDateRange;
import com.ccm.oxi.reservation.TimeSpan;
import com.ccm.oxi.reservation.TimeUnitType;
import com.ccm.oxi.reservation.Udfs;
import com.ccm.oxi.udf.Udf;
import com.ccm.oxi.udfdefinition.UdfDefinition;
import com.ccm.reservation.AgeQualifyingCode;

/**
 * @author Jenny
 * 
 */
@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

	private Log log = LogFactory.getLog("reservation");

	private static final String HH_MM_SS = "　HH:mm:ss";

	private Map<String, Object> pmsVersion = new HashMap<String, Object>();

	@Autowired
	private MasterDao masterDao;
	@Resource
	private HotelDao hotelDao;
	@Resource
	private SendMsgLogDao sendMsgLogDao;

	@Autowired
	private SendMsgLogManager sendMsgLogManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	@Resource
	private MasterSendLogManager masterSendLogManager;
	@Resource
	private SmsManager2 smsManager2;
	@Resource
	private AsyncManager asyncManager;
	@Resource
	private OXIReservationNodesService oXIReservationNodesService;
	@Autowired
	private RsvchanBlockDao rsvchanBlockDao;
	@Autowired
	private CustomDao customDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccm.oxi.reservation.ReservationService#createOrder(com.ccm.oxi.
	 * reservation.Reservation)
	 */
	public void createOrder(String confirmId) throws Exception {

		log.info("start deal ccm createOrder" + confirmId);

		List<Master> orderList = masterDao.getOrderChildByOrderId(confirmId);
		if (orderList == null || orderList.isEmpty()) {
			Master order = masterDao.getMasterOrderByOrderId(confirmId);
			if (order == null) {
				log.warn("createOrderNum1:" + confirmId + "isNoExist");
				return;
			}
			buildPmsReservation(order);
		} else {
			for (Master order : orderList) {
				order.setCardNumber(RSAEncrypt.decrypt(order.getCardNumber()));
				buildPmsReservation(order);
			}
		}

		log.info("end deal ccm createOrder" + confirmId);

	}

	/**
	 * 接收渠道修改订单，构建PMS规定的XML格式，通过HTTP发送
	 */
	public void changeOrder(String confirmId) throws Exception {

		Master order = masterDao.getMasterOrderByOrderId(confirmId);
		if (order == null) {
			log.warn("changeOrder:" + confirmId + "isNoExist");
			return;
		}
		buildPmsReservation(order);

	}

	/**
	 * 取消订单，构建PMS规定的XML格式
	 */
	public void cancelOrder(String confirmId) throws Exception {

		Master order = masterDao.getMasterOrderByOrderId(confirmId);
		if (order == null) {
			log.warn("cancelOrder:" + confirmId + "isNoExist");
			return;
		}
		oXIReservationNodesService.buildCancelFintrx(order);
		buildPmsReservation(order);

	}

	@SuppressWarnings("deprecation")
	@Async
	public void buildWBEOrder(Master master) {
		if (master.getSta().equalsIgnoreCase(OrderStatus.PAID)) {
			master.setSta(MfReservationAction.EDIT.name());
		}
		buildReservation(master);

		String language = master.getLang();
		if (!StringUtils.hasText(language)) {
			language = LanguageCode.MAIN_LANGUAGE_CODE;
		}
		HotelVO h = hotelDao.getHotelVoByHotelId(master.getHotelId(), language);
		String hotelName = "";

		// 发送短信
		try {
			String tel = "";
			if (h != null) {
				tel = h.getTelephone();
				hotelName = h.getHotelName();
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userName", master.getName2() + master.getName());
			param.put("hotelName", hotelName);
			param.put("roomName", master.getRoomTypeName());
			param.put("roomNum", master.getNumberOfUnits());
			MasterRate mr = master.getMrList().get(0);
			param.put("price", AmountUtil.add(mr.getSetrate(), mr.getPackages()));
			param.put("startDate_month", master.getArr().getMonth());
			param.put("startDate_day", master.getArr().getDay());
			param.put("endDate_month", master.getDep().getMonth());
			param.put("endDate_day", master.getDep().getDay());
			param.put("reservationNumber", master.getMasterId());
			param.put("telephone", tel);
			if (OrderStatus.ADD.equalsIgnoreCase(master.getSta())) {
				smsManager2.smsSend(master.getHotelCode(), language, SmsType.SMS_TYPE_NEWRESERVATION, master.getMobile(), param);
			} else if (OrderStatus.EDIT.equalsIgnoreCase(master.getSta())) {
				smsManager2.smsSend(master.getHotelCode(), language, SmsType.SMS_TYPE_EDITRESERVATION, master.getMobile(), param);
			} else if (OrderStatus.CANCEL.equalsIgnoreCase(master.getSta())) {
				smsManager2.smsSend(master.getHotelCode(), language, SmsType.SMS_TYPE_CANCELRESERVATION, master.getMobile(), param);
			}
		} catch (Exception e) {
			log.error("WEBOrder sendMobile fail......" + master.getMobile());
			log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
		}

		// 发送Email
		if (StringUtils.hasText(master.getEmail())) {
			if (!GuaranteeCode.DUE.equals(master.getPayment())) {
				masterSendLogManager.recordSendEmail(master.getEmail(), hotelName, master.getMasterId(), language, master.getSta());
			}
		}
		if (h != null && StringUtils.hasText(h.getEmail())) {
			masterSendLogManager.recordSendEmail(h.getEmail(), hotelName, master.getMasterId(), language, master.getSta());
		}

	}

	/**
	 * 从数据查询订单构建OXI的XML
	 * 1.预订类型;2.集团与酒店编码;3.预订号;4.订单的渠道编码,可选项;5.预订时间范围的数量,可选项;6.oxi订单创建时间
	 * ;7.入住与离店时间
	 * ;8.客人数量;9.备注;10.信用卡信息;11.客户行程;12.个人档案;13.房型;14.房间数量;15.订单修改时间;16.取消原因.
	 * 
	 * @param order
	 * @return
	 */
	public void buildReservation(Master order) {

		Reservation r = new Reservation();

		// 酒店代码
		String hotelCode = order.getHotelCode();
		String hotelId = order.getHotelId();

		// 1.预订类型:下传PMS的hardcancel订单，OXI节点的订单状态还是按cancel下传，并加入cancel的数据转换
		String sta = order.getSta();
		if (OrderStatus.HARDCANCEL.equals(sta)) {
			sta = OrderStatus.CANCEL;
		}
		String mfReservationAction = getCodeMapByValue(OXIConstant.orderStatus, hotelId, sta, null);
		r.setMfReservationAction(mfReservationAction);

		// 3.预订号-本地系统的
		r.setConfirmationID(order.getMasterId());

		// 3.预订号-PMS的
		r.setReservationID(order.getPmsId());

		// 2.集团与酒店编码
		HotelReference oxiHR = new HotelReference();
		// Not currently used.
		oxiHR.setChainCode(order.getChainCode());
		oxiHR.setHotelCode(hotelCode);// 必填项1
		r.setHotelReference(oxiHR);

		// 4.订单的渠道编码,可选项,Not currently mapped into Opera
		r.setReservationOriginatorCode(OXIConstant.creatorCode);

		// 6.oxi订单创建时间
		String dateFormat = order.getDateFormat();
		if (!StringUtils.hasText(dateFormat)) {
			dateFormat = "yyyy-MM-dd";
		}
		String YYYY_MM_DD_HH_MM_SS = dateFormat + HH_MM_SS;
		r.setOriginalBookingDate(DateUtils.dateToXmlDate(DateUtil.convertDateToDate(YYYY_MM_DD_HH_MM_SS, order.getChanged())));

		// 5.预订时间范围数量,可选项,一般只有一个开始与结束时间，所以固定为1
		r.setMfLegNumbers("1");

		// 7. 入住与离店时间
		Date arr = DateUtil.convertDateToDate(dateFormat, order.getArr());
		Date dep = DateUtil.convertDateToDate(dateFormat, order.getDep());
		int nights = DateUtil.dateDiff(arr, dep);
		StayDateRange sdr = new StayDateRange();
		sdr.setTimeUnitType(TimeUnitType.DAY);
		sdr.setStartTime(DateUtils.dateToXmlDate(arr));// 必填项3
		sdr.setNumberOfTimeUnits(nights);// 必填项4
		r.setStayDateRange(sdr);

		// 8.客人数量
		GuestCounts gcs = new GuestCounts();
		int adults = order.getGstno().intValue() - order.getChild().intValue();
		int children = order.getChild().intValue();
		GuestCount oxiGC = new GuestCount();
		oxiGC.setAgeQualifyingCode(AgeQualifyingCode.ADULT.value());// 必填项5
		oxiGC.setMfCount(adults);// 必填项6
		gcs.getGuestCount().add(oxiGC);
		GuestCount oxiGC2 = new GuestCount();
		oxiGC2.setAgeQualifyingCode(AgeQualifyingCode.CHILD.value());// 必填项5
		oxiGC2.setMfCount(children);// 必填项6
		gcs.getGuestCount().add(oxiGC2);
		r.setGuestCounts(gcs);

		// 9.备注
		ResComment oxiRC = new ResComment();
		oxiRC.setResCommentRPH(0l);
		oxiRC.setCommentOriginatorCode(OXIConstant.creatorCode);
		oxiRC.setGuestViewable(1);
		oxiRC.setMfcommentDate(DateUtils.dateToXmlDate(new Date()));
		StringBuffer comment = new StringBuffer();
		if (StringUtils.hasText(order.getPaymentRemark())) {
			comment.append(order.getPaymentRemark()).append("/");
		}
		if (StringUtils.hasText(order.getRef())) {
			comment.append(order.getRef());
		}
		oxiRC.setComment(comment.toString());

		// commentType
		String mfcommentType = getCodeMapByValue(OXIConstant.commentType, hotelId, OXIConstant.mfCommentType, null);
		oxiRC.setMfcommentType(mfcommentType);

		ReservationActionType reservationActionType = ReservationActionType.NA;
		if (MfReservationAction.ADD.name().equals(sta)) {
			reservationActionType = ReservationActionType.NEW;
		} else if (MfReservationAction.EDIT.name().equals(sta) || MfReservationAction.CANCEL.name().equals(sta)) {
			reservationActionType = ReservationActionType.CHANGE;
		} else if (MfReservationAction.SYNCH.name().equals(sta)) {
			reservationActionType = ReservationActionType.SYNC;
		} else if (MfReservationAction.DELETE.name().equals(sta)) {
			reservationActionType = ReservationActionType.DELETE;
		}
		oxiRC.setReservationActionType(reservationActionType);

		ResComments rcs = new ResComments();
		rcs.getResComment().add(oxiRC);
		r.setResComments(rcs);

		// 支付方式
		String paymentMethod = getCodeMapByValue(OXIConstant.paymentMethod, hotelId, order.getCardCode(), OXIConstant.mfPaymentMethod);

		// 10.信用卡信息
		if (StringUtils.hasText(order.getCardNumber())) {
			CreditCard oxiCC = new CreditCard();

			oxiCC.setCreditCardCode(paymentMethod);
			oxiCC.setCreditCardNumber(getDiffCardNumber(order));
			Date expireDate = DateUtil.convertDateToDate(dateFormat, order.getExpirationDate());
			oxiCC.setCreditCardExpire(DateUtils.dateToXmlDate(expireDate));
			oxiCC.setCreditCardHolderName(order.getCardHolderName());
			oxiCC.setMfPrimaryYN("Y");
			ResCreditCard rcc = new ResCreditCard();
			rcc.setResCreditCardRPH(0l);
			rcc.setReservationActionType(reservationActionType);
			rcc.setCreditCard(oxiCC);
			ResCreditCards rccs = new ResCreditCards();
			rccs.getResCreditCard().add(rcc);
			r.setResCreditCards(rccs);

		}

		// 11.客户行程
		ResGuest oxiRG = new ResGuest();
		oxiRG.setResGuestRPH(0l);
		oxiRG.setProfileRPHs("0,1");
		// 最早到店时间
		if (order.getEarliestTime() != null) {
			Date earliestTime = DateUtil.convertDateToDate(dateFormat, order.getEarliestTime());
			oxiRG.setArrivalTime(DateUtils.dateToXmlDate(earliestTime));
		} else {
			oxiRG.setArrivalTime(sdr.getStartTime());
		}
		// 最晚离店时间
		if (order.getLastTime() != null) {
			Date departTime = DateUtil.convertDateToDate(dateFormat, order.getLastTime());
			oxiRG.setDepartureTime(DateUtils.dateToXmlDate(departTime));
		} else {
			oxiRG.setDepartureTime(DateUtils.dateToXmlDate(dep));
		}
		oxiRG.setConfirmationID(r.getConfirmationID());
		oxiRG.setMfConfirmationLegNo(1);
		oxiRG.setReservationID(order.getPmsId());
		oxiRG.setReservationActionType(reservationActionType);
		InHouseTimeSpan itsp = new InHouseTimeSpan();
		itsp.setStartTime(sdr.getStartTime());
		itsp.setNumberOfTimeUnits(nights);
		oxiRG.setInHouseTimeSpan(itsp);

		// ArrivalTransport
		ArrivalTransport ats = new ArrivalTransport();
		if (order.getTransportID() != null) {
			ats.setTransportID(order.getTransportID());
		}
		if (order.getTransportTime() != null) {
			ats.setTransportTime(DateUtils.dateToXmlDate(order.getTransportTime()));
		}
		oxiRG.setArrivalTransport(ats);

		ResGuests rgs = new ResGuests();
		rgs.getResGuest().add(oxiRG);
		r.setResGuests(rgs);

		/**
		 * profile start
		 */
		// 12.个人档案
		ResProfiles oxiRP = new ResProfiles();
		Profile oxiP = new Profile();

		// Profile Type
		String profileType = getCodeMapByValue(OXIConstant.profileType, hotelId, ProfileType.GUEST.name(), null);
		oxiP.setProfileType(profileType);

		// 性别 Gender
		String sex = getCodeMapByValue(OXIConstant.gender, hotelId, order.getSex(), Sex.MALE);
		oxiP.setGender(sex);

		oxiP.setProfileID(order.getHaccnt());
		oxiP.setCreatorCode(OXIConstant.creatorCode);
		oxiP.setCreatedDate(DateUtils.dateToXmlDate(new Date()));// 必填项14
		oxiP.setLastUpdaterCode("");// 必填项15
		oxiP.setLastUpdated(null);// 必填项16

		// 客户姓名
		IndividualName in = new IndividualName();
		if (order.getName2() != null) {
			in.setNameSur(order.getName2());// lastName姓必填项
		}
		if (order.getName() != null) {
			in.setNameFirst(order.getName());// 名必填项
		}

		// Title
		String title = getCodeMapByValue(OXIConstant.title, hotelId, order.getNameTitle(), OXIConstant.nameTitle);
		in.setNameTitle(title);
		in.setNamePrefix(title);

		// Language Code
		String languageCode = getCodeMapByValue(OXIConstant.languageCode, hotelId, order.getLang(), LanguageCode.EN_US);
		in.setNameOrdered(languageCode);

		oxiP.setIndividualName(in);
		oxiP.setGenericName(in.getNameSur());
		oxiP.setPrimaryLanguageID(languageCode);

		// 客户Email
		if (StringUtils.hasText(order.getEmail())) {
			ElectronicAddresses eas = new ElectronicAddresses();
			ElectronicAddress ea = new ElectronicAddress();
			String electronicAddressType = getCodeMapByValue(OXIConstant.phoneType, hotelId, ElectronicAddressType.EMAIL.name(), null);
			ea.setElectronicAddressType(electronicAddressType);
			ea.setEAddress(order.getEmail());
			eas.getElectronicAddress().add(ea);
			oxiP.setElectronicAddresses(eas);
		}

		// 客户邮编地址
		PostalAddresses paes = new PostalAddresses();
		PostalAddress pa = new PostalAddress();

		// addressType
		String addressType = getCodeMapByValue(OXIConstant.addressType, hotelId, order.getAddressType(), AddressType.BUSINESS.name());
		pa.setAddressType(addressType);

		pa.setAddress1(order.getAddressLine());
		pa.setStateCode(order.getStateProv());
		pa.setCity(order.getCityName());

		// Country Code
		String countryCode = getCodeMapByValue(OXIConstant.country, hotelId, order.getCountryCode(), OWSConstant.countryCode_CCM);
		pa.setCountryCode(countryCode);

		pa.setPostalCode(order.getPostCode());
		pa.setMfPrimaryYN("Y");
		paes.getPostalAddress().add(pa);
		oxiP.setPostalAddresses(paes);

		// 客户联系电话
		PhoneNumbers pns = new PhoneNumbers();
		if (StringUtils.hasText(order.getMobile())) {
			PhoneNumber oxiPN = new PhoneNumber();
			String phoneNumberType = getCodeMapByValue(OXIConstant.phoneType, hotelId, PhoneNumberType.MOBILE.name(), null);
			oxiPN.setPhoneNumberType(phoneNumberType);
			oxiPN.setPhoneNumber(order.getMobile());
			pns.getPhoneNumber().add(oxiPN);
		}
		if (StringUtils.hasText(order.getBusiness())) {
			PhoneNumber oxiPN = new PhoneNumber();
			String phoneNumberType = getCodeMapByValue(OXIConstant.phoneType, hotelId, PhoneNumberType.BUSINESS.name(), null);
			oxiPN.setPhoneNumberType(phoneNumberType);
			oxiPN.setPhoneNumber(order.getBusiness());
			pns.getPhoneNumber().add(oxiPN);
		}
		if (StringUtils.hasText(order.getHome())) {
			PhoneNumber oxiPN = new PhoneNumber();
			String phoneNumberType = getCodeMapByValue(OXIConstant.phoneType, hotelId, PhoneNumberType.HOME.name(), null);
			oxiPN.setPhoneNumberType(phoneNumberType);
			oxiPN.setPhoneNumber(order.getHome());
			pns.getPhoneNumber().add(oxiPN);
		}
		if (StringUtils.hasText(order.getFax())) {
			PhoneNumber oxiPN = new PhoneNumber();
			String phoneNumberType = getCodeMapByValue(OXIConstant.phoneType, hotelId, PhoneNumberType.BUSINESSFAX.name(), null);
			oxiPN.setPhoneNumberType(phoneNumberType);
			oxiPN.setPhoneNumber(order.getFax());
			pns.getPhoneNumber().add(oxiPN);
		}
		oxiP.setPhoneNumbers(pns);

		oxiP.setMfResort(hotelCode);// 酒店代码，必填项
		oxiP.setMfAllowEMail("YES");

		if (order.getName4() != null) {
			// 中文名
			MfNationalName mnn = new MfNationalName();
			mnn.setNameLast(order.getName4());// 姓名
			oxiP.setMfNationalName(mnn);
		}

		ResProfile rpf = new ResProfile();
		rpf.setResProfileRPH(0l);
		rpf.setProfile(oxiP);
		oxiRP.getResProfile().add(rpf);

		// 渠道协议价start
		Profile oxiP2 = new Profile();

		// profileType-TRAVEL_AGENT
		String profileTypeT = getCodeMapByValue(OXIConstant.profileType, hotelId, order.getQualifyingIdType(), ProfileType.TRAVEL_AGENT.name());
		oxiP2.setProfileType(profileTypeT);

		// 性别
		oxiP2.setGender(oxiP.getGender());

		// 房价代码
		String ratePlanCode = order.getRatePlanCode();
		ratePlanCode = getCodeMapByValue(OXIConstant.ratePlanCode, hotelId, ratePlanCode, null);

		// 房型代码
		String roomTypeCode = order.getType();
		roomTypeCode = getCodeMapByValue(OXIConstant.roomTypeCode, hotelId, roomTypeCode, null);

		// profileId
		oxiP2.setProfileID(getDiffProfileID(order));
		// 创建修改者和时间
		oxiP2.setCreatorCode("");
		oxiP2.setCreatedDate(null);
		oxiP2.setLastUpdaterCode("");
		oxiP2.setLastUpdated(null);
		oxiP2.getPreferredRatePlanCode().add(ratePlanCode);
		// 客户名称
		String customName = order.getCustomName();
		oxiP2.setGenericName(customName);
		IndividualName in2 = new IndividualName();
		in2.setNameSur(customName);
		oxiP2.setIndividualName(in2);
		// 酒店代码
		oxiP2.setMfResort(hotelCode);
		// 渠道代码
		if (StringUtils.hasText(order.getMfNameCode())) {
			oxiP2.setMfNameCode(order.getMfNameCode());
		}
		// 获取PMS版本
		String pmsType = order.getPmsType();
		if (pmsVersion.get(pmsType) == null) {
			pmsType = OXIConstant.pmsTypeDefault;
		}
		AbstractReservationToPmsService pmsService = (AbstractReservationToPmsService) pmsVersion.get(pmsType);
		// 渠道协议
		MfNegotiatedRates mnr = pmsService.buildMfNegotiatedRates(hotelCode, ratePlanCode, order.getChannelRateStart(), order.getChannelRateEnd(), YYYY_MM_DD_HH_MM_SS);
		oxiP2.setMfNegotiatedRates(mnr);

		ResProfile rpf2 = new ResProfile();
		rpf2.setResProfileRPH(1l);
		rpf2.setProfile(oxiP2);
		oxiRP.getResProfile().add(rpf2);
		// 渠道协议价end

		r.setResProfiles(oxiRP);
		/**
		 * profile end
		 */

		// 13.房型
		RoomStay oxiRS = new RoomStay();
		// 设置blockCode
		String blockCode = getBlockCode(order.getType(), order);
		if (blockCode != null && !blockCode.isEmpty()) {
			oxiRS.setInventoryBlockCode(blockCode);
		}

		// reservationStatusType:下传PMS的hardcancel订单，OXI节点的订单状态还是按cancel下传，并加入cancel的数据转换
		String restype = order.getRestype();
		if (OrderStatus.HARDCANCEL.equals(restype)) {
			restype = ReservationStatusType.CANCELED.name();
		}
		String reservationStatusType = getCodeMapByValue(OXIConstant.reservationStatusType, hotelId, restype, null);
		oxiRS.setReservationStatusType(reservationStatusType);// 必填项7

		oxiRS.setReservationActionType(reservationActionType);
		// 设置mfShareAction,mfReservationAction
		pmsService.setMfAction(oxiRS, mfReservationAction);

		oxiRS.setRoomStayRPH(0l);
		oxiRS.setResGuestRPHs("0");
		oxiRS.setResCommentRPHs("0");
		oxiRS.setSelectedMembershipRPHs("0");

		// 市场代码
		String mfMarketCode = getCodeMapByValue(OXIConstant.marketCode, hotelId, order.getMarket(), OXIConstant.mfMarketCode);
		oxiRS.setMarketSegmentCode(mfMarketCode);

		// 13.房型-房型编码
		oxiRS.setRoomInventoryCode(roomTypeCode);// 必填项8

		// 13.房型-入住时间
		TimeSpan oxiTS = new TimeSpan();
		oxiTS.setTimeUnitType(TimeUnitType.DAY);
		oxiTS.setStartTime(sdr.getStartTime());// 必填项9
		oxiTS.setNumberOfTimeUnits(nights);
		oxiRS.setTimeSpan(oxiTS);
		// 13.房型-客人数量
		oxiRS.setGuestCounts(gcs);
		// 13.房型-房价明细
		RatePlan rp = new RatePlan();
		double totalAmount = 0;

		rp.setRatePlanRPH(0l);

		// 13.房型-房价明细-房价编码
		rp.setRatePlanCode(ratePlanCode);

		// 13.房型-房价明细-入住时间
		rp.setTimeSpan(oxiTS);// 必填项10

		// 来源代码
		String mfSourceCode = getCodeMapByValue(OXIConstant.sourceCode, hotelId, order.getSource(), OXIConstant.mfSourceCode);
		rp.setMfsourceCode(mfSourceCode);
		rp.setMfMarketCode(mfMarketCode);

		// 13.房型-房价明细-明细
		Rates rates = new Rates();

		List<MasterRate> rateList = masterDao.getMasterRate(order.getMasterId());

		// Curency Code
		String currencyCode = getCodeMapByValue(OXIConstant.currency, hotelId, order.getCurrencyCode(), OWSConstant.currencyCode);

		for (int j = 0; j < rateList.size(); j++) {

			MasterRate mr = rateList.get(j);
			BigDecimal rmrate = mr.getRmrate();
			if(rmrate==null){
				rmrate = new BigDecimal("0");
			}
			if(order.getIsDiscount()==null || !order.getIsDiscount()){
				rmrate = new BigDecimal("0");
			}
			
			Amount oxiA = new Amount();
			oxiA.setValueNum(mr.getSetrate());
			oxiA.setCurrencyCode(currencyCode);

			Rate oxiRate = new Rate();
			oxiRate.setRateRPH(Long.valueOf(j));
			oxiRate.setAmount(oxiA);
			oxiRate.setRateBasisUnits(1);
			oxiRate.setRateBasisTimeUnitType(RateBasisTimeUnitType.DAY);

			TimeSpan oxiTSR = new TimeSpan();
			oxiTSR.setTimeUnitType(TimeUnitType.DAY);
			oxiTSR.setStartTime(DateUtils.dateToXmlDate(mr.getDate()));// 必填项9
			oxiTSR.setNumberOfTimeUnits(1);
			oxiRate.setTimeSpan(oxiTSR);
			// 设置大小，小孩数量
			pmsService.setMfAdultsChildren(oxiRate, adults, children);

			oxiRate.setMfMarketCode(mfMarketCode);
			oxiRate.setMfsourceCode(mfSourceCode);
			oxiRate.setReservationActionType(oxiRG.getReservationActionType());
			rates.getRate().add(oxiRate);
			totalAmount = totalAmount + oxiA.getValueNum().doubleValue();
		}
		BigDecimal totalA = new BigDecimal(totalAmount).setScale(4, BigDecimal.ROUND_HALF_UP);

		rp.setRates(rates);
		RatePlans rps = new RatePlans();
		rps.getRatePlan().add(rp);
		oxiRS.setRatePlans(rps);
		// 13.房型-担保信息
		GuaranteeInfo gi = new GuaranteeInfo();
		gi.setGuaranteeType(GuaranteeType.NA);

		// guarantee Type
		String mfGuaranteeType = getCodeMapByValue(OXIConstant.guaranteeType, hotelId, order.getPayment(), OXIConstant.mfGuaranteeType);
		gi.setMfGuaranteeType(mfGuaranteeType);// 必填项11

		oxiRS.setGuaranteeInfo(gi);
		// 13.房型-支付方式
		PaymentInstruction pi = new PaymentInstruction();
		if (GuaranteeCode.CCGTD.equals(order.getPayment())) {
			pi.setPaymentMethodType(PaymentMethodType.CREDITCARD);
		} else {
			pi.setPaymentMethodType(PaymentMethodType.NA);
		}
		pi.setMfPaymentMethod(paymentMethod);// 必填项12
		pi.setResCreditCardRPH(0l);
		PaymentDue pd = new PaymentDue();
		Amount a = new Amount();
		a.setCurrencyCode(order.getCurrencyCode());
		a.setValueNum(totalA);
		pd.setAmount(a);
		pi.setPaymentDue(pd);
		PaymentInstructions pis = new PaymentInstructions();
		pis.getPaymentInstruction().add(pi);
		oxiRS.setPaymentInstructions(pis);
		// 13.房型-渠道编码,非必填项

		// 发送PMS的channelCode
		String mfchannelCode = getCodeMapByValue(OXIConstant.channelCode, hotelId, OXIConstant.mfChannelCode, OXIConstant.mfChannelCode);
		oxiRS.setMfchannelCode(mfchannelCode);

		oxiRS.setMfsourceCode(mfSourceCode);// 必填项13

		oxiRS.setMfcomplementaryCode("");
		oxiRS.setMfconfidentialRate(0);

		// 取消使用
		// oxiRS.setCancelPenalties(value);

		if (StringUtils.hasText(order.getAlerts())) {
			oxiRS.setReservationAlerts(oXIReservationNodesService.buildReservationAlerts(reservationActionType, order.getAlerts()));
		}
		if (StringUtils.hasText(order.getTraces())) {
			oxiRS.setReservationTraces(oXIReservationNodesService.buildReservationTraces(order.getTraceDept(), order.getTraces()));
		}

		// 房型构建完成
		RoomStays rss = new RoomStays();
		rss.getRoomStay().add(oxiRS);
		r.setRoomStays(rss);

		// SelectedMemberships
		SelectedMembership sm = new SelectedMembership();
		sm.setReservationActionType(reservationActionType);
		sm.setSelectedMembershipRPH(0l);
		sm.setProgramCode("PC");
		sm.setAccountID(order.getMembershipNumber());
		MfAmount ma = new MfAmount();
		ma.setCurrencyCode(currencyCode);
		sm.setMfAmount(ma);
		sm.setMfmembershipCategory("BASIC");
		SelectedMemberships sms = new SelectedMemberships();
		sms.getSelectedMembership().add(sm);
		r.setSelectedMemberships(sms);

		// 包价
		List<MasterPackage> mpList = masterDao.getMasterPackage(order.getMasterId());
		Services services = new Services();
		if (mpList != null) {
			for (int i = 0; i < mpList.size(); i++) {
				MasterPackage mp = mpList.get(i);
				if (mp.getIsDynamic()) {
					com.ccm.oxi.reservation.Service service = new com.ccm.oxi.reservation.Service();
					service.setReservationActionType(reservationActionType);
					service.setServicePricingType(ServicePricingType.NA);
					service.setReservationStatusType(ReservationStatusType.NA);
					service.setServiceRPH(Long.valueOf(i));
					String serviceInventoryCode = getCodeMapByValue(OXIConstant.packageCode, hotelId, mp.getPackageCode(), null);
					service.setServiceInventoryCode(serviceInventoryCode);
					TimeSpan ts = new TimeSpan();
					ts.setTimeUnitType(TimeUnitType.DAY);
					ts.setStartTime(DateUtils.dateToXmlDate(mp.getDate()));// 必填项9
					ts.setNumberOfTimeUnits(1);
					service.setTimeSpan(ts);
					Price price = new Price();
					price.setCurrencyCode(currencyCode);
					price.setValueNum(mp.getAmount());
					service.setPrice(price);
					service.setQuantity(mp.getQuantity());
					services.getService().add(service);
				}
			}
		}
		r.setServices(services);

		// 14.房间数量
		int numberOfUnits = order.getNumberOfUnits();
		MfImage mi = new MfImage();
		mi.setNumRooms(numberOfUnits);

		// mfImage-Describe
		Describe d = new Describe();
		d.setInsertUser(OXIConstant.user);
		d.setUpdateUser(OXIConstant.user);
		String guaranteeName = "";
		DictCode dc = dictCodeManager.searchByCodeNo(DictName.guaranteeCode, order.getPayment());
		if (dc != null) {
			guaranteeName = dc.getCodeLabel();
		}
		d.setGuarantee(order.getPayment() + ": " + guaranteeName);
		mi.setDescribe(d);

		r.setMfImage(mi);

		// 15.订单修改时间
		Date updateTime = DateUtil.convertDateToDate(YYYY_MM_DD_HH_MM_SS, order.getLastModifyTime());
		r.setMfupdateDate(DateUtils.dateToXmlDate(updateTime));

		// 16.取消原因
		r.setCancellationID("");
		r.setCancelOriginatorCode(order.getCancelReasonCode());
		Date cancelTime = DateUtil.convertDateToDate(YYYY_MM_DD_HH_MM_SS, order.getCancelTime());
		r.setCancellationDate(DateUtils.dateToXmlDate(cancelTime));
		r.setMfcancellationComment(order.getCancelRef());

		r.setResCommentRPHs("0");
		r.setResProfileRPHs(oxiRG.getProfileRPHs());
		r.setMfupdateDate(null);
		r.setMfcomplementary(0);

		// 17.Udfs
		Udfs udfs = new Udfs();
		if (StringUtils.hasText(order.getInvoiceTitle())) {
			UdfDefinition udef = new UdfDefinition();
			udef.setPmsTableName(OXIConstant.pmsTableName);
			String pmsValue = getCodeMapByValue(OXIConstant.pmsColumnName, order.getHotelId(), OXIConstant.mfPmsColumnName, null);
			udef.setPmsColumnName(pmsValue);
			Udf udf = new Udf();
			udf.setUdfDefinition(udef);
			udf.setUdfValue(order.getInvoiceTitle());
			udfs.getUdf().add(udf);
		}
		if (CommonUtil.isNotEmpty(order.getPaymentTransaction())) {
			String pmsValue = getCodeMapByDictNameAndCodeNo(OXIConstant.pmsColumnName, order.getHotelId(), OXIConstant.paymentTransaction);
			if (CommonUtil.isNotEmpty(pmsValue)) {
				UdfDefinition udef = new UdfDefinition();
				udef.setPmsTableName(OXIConstant.pmsTableName);
				udef.setPmsColumnName(pmsValue);
				Udf udf = new Udf();
				udf.setUdfDefinition(udef);
				udf.setUdfValue(order.getPaymentTransaction());
				udfs.getUdf().add(udf);
			}
		}
		if (null != order.getPrepaidAmount()) {
			String pmsValue = getCodeMapByDictNameAndCodeNo(OXIConstant.pmsColumnName, order.getHotelId(), OXIConstant.prepaidAmount);
			if (CommonUtil.isNotEmpty(pmsValue)) {
				UdfDefinition udef = new UdfDefinition();
				udef.setPmsTableName(OXIConstant.pmsTableName);
				udef.setPmsColumnName(pmsValue);
				Udf udf = new Udf();
				udf.setUdfDefinition(udef);
				udf.setUdfValue(order.getPrepaidAmount() + "");
				udfs.getUdf().add(udf);
			}
		}
		if (CommonUtil.isNotEmpty(order.getBookingSource())) {
			String pmsValue = getCodeMapByDictNameAndCodeNo(OXIConstant.pmsColumnName, order.getHotelId(), OXIConstant.bookingSource);
			if (CommonUtil.isNotEmpty(pmsValue)) {
				UdfDefinition udef = new UdfDefinition();
				udef.setPmsTableName(OXIConstant.pmsTableName);
				udef.setPmsColumnName(pmsValue);
				Udf udf = new Udf();
				udf.setUdfDefinition(udef);
				udf.setUdfValue(order.getBookingSource());
				udfs.getUdf().add(udf);
			}
		}
		if (CommonUtil.isNotEmpty(udfs.getUdf())) {
			r.setUdfs(udfs);
		}

		// 转换为XML且保存到订单队列
		saveOrderOxi(r, pmsService, sta, order.getMsgStatus2Pms());

	}

	private void buildPmsReservation(Master order) throws Exception {
		// hardcancel订单，且新建订单下传PMS的消息状态为READY，将hardcancel订单和新建订单的下传PMS的消息状态更新为IGNORE,PMS发起的get请求，针对消息状态是IGNORE的消息将不再给酒店发送。
		// hardcancel订单，如新建订单下传PMS的消息状态为RESULT_SUCCESS或PROCESSED，该hardcancel订单还是要往酒店下传，消息状态也不用更新为IGNORE。
		String msgStatus2Pms = null;
		if (OrderStatus.HARDCANCEL.equals(order.getSta())) {
			SendMsgLog cond = new SendMsgLog();
			cond.setHotelCode(order.getHotelCode());
			cond.setMessageType(MessageType.RESERVATION);
			cond.setAction(OrderStatus.ADD);
			cond.setOrderId(order.getMasterId());
			String byStatus = "'" + OXIStatusEnum.SEND_RESULT_SUCCESS.getValue() + "','" + OXIStatusEnum.SRP_PROCESSED.getValue() + "','" + OXIStatusEnum.SEND_READY.getValue() + "'";
			cond.setByStatus(byStatus);
			List<SendMsgLog> smlList = sendMsgLogDao.getSendMsgLogByCondit(cond);
			List<SendMsgLog> addSmlList = new ArrayList<SendMsgLog>();
			for (SendMsgLog sml : smlList) {
				if (OXIStatusEnum.SEND_RESULT_SUCCESS.getValue().equals(sml.getStatus()) || OXIStatusEnum.SRP_PROCESSED.getValue().equals(sml.getStatus())) {
					msgStatus2Pms = OXIStatusEnum.SEND_READY.getValue();
					break;
				} else if (OXIStatusEnum.SEND_READY.getValue().equals(sml.getStatus())) {
					msgStatus2Pms = OXIStatusEnum.SEND_IGNORE.getValue();
					addSmlList.add(sml);
				}
			}
			if (msgStatus2Pms != null && OXIStatusEnum.SEND_IGNORE.getValue().equals(msgStatus2Pms)) {
				for (SendMsgLog sml : addSmlList) {
					sml.setStatus(msgStatus2Pms);
					sml.setLastModifyTime(new Date());
					sendMsgLogManager.saveSendMsgLogUpdateOrder(sml);
				}
			}
			order.setMsgStatus2Pms(msgStatus2Pms);
		}
		// 构建PMS订单信息
		buildReservation(order);
		// PMS下传消息的“消息状态”为IGNORE，就不向酒店发送邮件确认函。
		if (msgStatus2Pms == null || !OXIStatusEnum.SEND_IGNORE.getValue().equals(msgStatus2Pms)) {
			// 发送邮件给酒店
			asyncManager.owsOrder2pmsSendEmail(order);
		}
	}

	/**
	 * 保存订单到队列表等GET再返回给PMS
	 */
	private void saveOrderOxi(Reservation r, AbstractReservationToPmsService pmsService, String sta, String msgStatus2Pms) {
		String hotelCode = r.getHotelReference().getHotelCode();
		String msgId = IncrementService.msgId(hotelCode);
		Date date = DateUtil.currentDateTime();
		String xml;
		try {
			xml = pmsService.buildPmsXML(r, msgId);
		} catch (Exception e) {
			xml = "buildPmsXmlFail";
			log.error("buildPmsXmlFail-" + hotelCode + msgId);
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		SendMsgLog oxi = new SendMsgLog();
		oxi.setInterfaceId(OXIConstant.Interface);
		oxi.setHotelCode(hotelCode);
		oxi.setMessage(xml);
		oxi.setMessageType(MessageType.RESERVATION);
		oxi.setOrderId(r.getConfirmationID());
		oxi.setAction(sta);
		if (!StringUtils.hasText(msgStatus2Pms)) {
			msgStatus2Pms = OXIStatusEnum.SEND_READY.getValue();
		}
		oxi.setStatus(msgStatus2Pms);
		oxi.setMsgId(msgId);
		oxi.setCreatedTime(date);
		sendMsgLogManager.saveSendMsgLogUpdateOrder(oxi);

	}

	/**
	 * 1.如果value无值，则置value=defaultVale; 2.获取value映射值，若没有，则取value.
	 * 
	 * @param dictName
	 * @param hotelId
	 * @param value
	 *            :数据库中保存的值
	 * @param defaultValue
	 * @return
	 */
	private String getCodeMapByValue(String dictName, String hotelId, String value, String defaultValue) {
		log.info("ccmValue:" + value + " defalutValue" + defaultValue);
		if (!StringUtils.hasText(value)) {
			value = defaultValue;
		}
		Map<String, String> codeMap = dictCodeManager.searchCodeMapByHotelId(dictName, hotelId, true);
		String pmsValue = codeMap.get(value);
		if (StringUtils.hasText(pmsValue)) {
			log.info("ccm2pmsMap:" + pmsValue);
			return pmsValue;
		} else {
			log.info("result:" + value);
			return value;
		}
	}

	private String getCodeMapByDictNameAndCodeNo(String dictName, String hotelId, String codeNo) {
		Map<String, String> codeMap = dictCodeManager.searchCodeMapByHotelId(dictName, hotelId, true);
		String pmsValue = null;
		if (CommonUtil.isNotEmpty(codeMap)) {
			pmsValue = codeMap.get(codeNo);
		}
		return pmsValue;
	}

	public Map<String, Object> getPmsVersion() {
		return pmsVersion;
	}

	public void setPmsVersion(Map<String, Object> pmsVersion) {
		this.pmsVersion = pmsVersion;
	}

	/**
	 * 获取 BlockCode
	 * 
	 * @param roomTypeCode
	 *            ：转换后的roomTypeCode
	 * @param order
	 * @return
	 */
	private String getBlockCode(String roomTypeCode, Master order) {
		List<Date> dateList = DateUtil.getDayList(order.getArr(), DateUtil.addDays(order.getDep(), -1));
		List<String> dateStrList = new ArrayList<String>();
		for (Date d : dateList) {
			dateStrList.add(DateUtil.convertDateToString(d));
		}
		List<RsvchanBlock> rsvchanBlockList = rsvchanBlockDao.getIsSendToPMSRsvchanBlock(order.getHotelCode(), order.getChannel(), dateStrList, roomTypeCode);
		Set<String> blockSet = new HashSet<String>();
		for (Date d : dateList) {
			String blockCode = null;
			inner: for (int i = 0; i < rsvchanBlockList.size(); i++) {
				// 使用block code
				if (rsvchanBlockList.get(i).getDate().equals(d)) {
					if (rsvchanBlockList.get(i).getRatePlanCodes() == null) {
						blockCode = rsvchanBlockList.get(i).getBlockCode();
						break inner;
					} else {
						// 使用block code
						if (rsvchanBlockList.get(i).getRatePlanCodes().contains(order.getRatePlanCode())) {
							blockCode = rsvchanBlockList.get(i).getBlockCode();
							break inner;
						}
					}
				}
			}
			if (blockCode == null) {
				return null;
			} else {
				blockSet.add(blockCode);
			}
		}
		if (blockSet.size() == 1) {
			List<String> list = new ArrayList<String>(blockSet);
			return list.get(0);
		} else {
			return null;
		}
	}

	@Async
	public void asyncCreateOrder(String str) throws Exception {
		this.createOrder(str);
	}

	/**
	 * 对于信用卡卡号要根据pms类型，做不同的处理 非oprea酒店，只传前4位，后面都改成X opera酒店，传完整的号码
	 * 
	 * @param m
	 * @return
	 */
	private String getDiffCardNumber(Master m) {
		String cardNumber = m.getCardNumber();
		if (StringUtils.hasLength(cardNumber)) {
			Hotel hotel = hotelDao.getHotel(m.getHotelId());
			if (!"OPERA".equalsIgnoreCase(hotel.getPmsType())) {
				String first = cardNumber.substring(0, 4);
				StringBuffer sb = new StringBuffer();
				if (cardNumber.length() > 4) {
					for (int i = 0; i < cardNumber.length() - 4; i++) {
						sb.append("X");
					}
				}
				return first + sb.toString();
			}
		}
		return cardNumber;
	}

	/**
	 * CCM下传PMS订单时，当CCM客户列表中的PROFILE ID 有值， 且这个PROFILE TYPE是TRAVEL AGENT时，
	 * 下传CCM的XML中，相应TRAVEL AGENT PROFILE的PROFILE ID 需要使用该值。
	 * 
	 * @param m
	 * @return
	 */
	private String getDiffProfileID(Master m) {
		String qualifyingIdType = m.getQualifyingIdType();
		String QualifyingIdValue = m.getQualifyingIdValue();
		Custom custom = customDao.searchCustomByHotelIdAndAccessCode(m.getHotelId(), QualifyingIdValue);
		if (custom != null) {
			if (custom.getType() != null && qualifyingIdType.equalsIgnoreCase(custom.getType()) && custom.getType().equalsIgnoreCase(ProfileType.TRAVEL_AGENT.name()) && StringUtils.hasLength(custom.getProfileID())) {
				return custom.getProfileID();
			}
		}
		return m.getChannelRateId();
	}

}
