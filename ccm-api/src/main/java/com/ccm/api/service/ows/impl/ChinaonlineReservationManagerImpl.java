package com.ccm.api.service.ows.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.xml.namespace.QName;

import org.apache.axis.message.SOAPHeaderElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.compass.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.chinaonline.webservices._switch._1_5_1.Reservation_wsdl.CheckInPolicy;
import cn.net.chinaonline.webservices._switch._1_5_1.Reservation_wsdl.CreateBookingRequest;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.aop.ExceptionPointcut;
import com.ccm.api.model.availability.AvailabilityReqVo;
import com.ccm.api.model.availability.AvailabilityResVo;
import com.ccm.api.model.availability.req.AvailRequestSegment;
import com.ccm.api.model.availability.req.AvailabilityRequest;
import com.ccm.api.model.availability.req.Body;
import com.ccm.api.model.availability.req.Criterion;
import com.ccm.api.model.availability.req.Destination;
import com.ccm.api.model.availability.req.Envelope;
import com.ccm.api.model.availability.req.Header;
import com.ccm.api.model.availability.req.HotelRef;
import com.ccm.api.model.availability.req.HotelSearchCriteria;
import com.ccm.api.model.availability.req.Origin;
import com.ccm.api.model.availability.req.RatePlanCandidate;
import com.ccm.api.model.availability.req.RatePlanCandidates;
import com.ccm.api.model.availability.req.StayDateRange;
import com.ccm.api.model.availability.res.AvailabilityResponse;
import com.ccm.api.model.availability.res.RoomRates;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.enume.PaymentTypeEnum;
import com.ccm.api.model.enume.ProfileType;
import com.ccm.api.model.log.SendReqResLog;
import com.ccm.api.model.ows.vo.CheckInPersonVO;
import com.ccm.api.model.ows.vo.OrderDailyRateVO;
import com.ccm.api.model.ows.vo.OrderReservationRetVO;
import com.ccm.api.model.ows.vo.OrderReservationVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.log.SendReqResManager;
import com.ccm.api.service.ows.ChinaonlineReservationManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.PinyinUtil;
import com.ccm.api.util.PushDataUtil;
import com.ccm.api.util.XMLUtil;
import com.micros.webservices.ReservationServiceLocator;
import com.micros.webservices.ReservationServiceSoapStub;
import com.micros.webservices.og._4_3.Common.Amount;
import com.micros.webservices.og._4_3.Common.Gender;
import com.micros.webservices.og._4_3.Common.PersonName;
import com.micros.webservices.og._4_3.Common.Text;
import com.micros.webservices.og._4_3.Common.UniqueID;
import com.micros.webservices.og._4_3.Common.UniqueIDType;
import com.micros.webservices.og._4_3.Core.EndPoint;
import com.micros.webservices.og._4_3.Core.OGHeader;
import com.micros.webservices.og._4_3.HotelCommon.AgeQualifyingCode;
import com.micros.webservices.og._4_3.HotelCommon.CancelTerm;
import com.micros.webservices.og._4_3.HotelCommon.CancelTermType;
import com.micros.webservices.og._4_3.HotelCommon.Guarantee;
import com.micros.webservices.og._4_3.HotelCommon.GuestCount;
import com.micros.webservices.og._4_3.HotelCommon.HotelReference;
import com.micros.webservices.og._4_3.HotelCommon.Paragraph;
import com.micros.webservices.og._4_3.HotelCommon.Rate;
import com.micros.webservices.og._4_3.HotelCommon.RatePlan;
import com.micros.webservices.og._4_3.HotelCommon.ReservationComment;
import com.micros.webservices.og._4_3.HotelCommon.RoomRate;
import com.micros.webservices.og._4_3.HotelCommon.RoomStay;
import com.micros.webservices.og._4_3.HotelCommon.RoomType;
import com.micros.webservices.og._4_3.HotelCommon.TimeSpan;
import com.micros.webservices.og._4_3.Name.Customer;
import com.micros.webservices.og._4_3.Name.NameMembership;
import com.micros.webservices.og._4_3.Name.NamePhone;
import com.micros.webservices.og._4_3.Name.Profile;
import com.micros.webservices.og._4_3.Reservation.HotelReservation;
import com.micros.webservices.og._4_3.Reservation.ResGuest;
import com.micros.webservices.ows._5_1.Reservation_wsdl.CancelBookingRequest;
import com.micros.webservices.ows._5_1.Reservation_wsdl.CancelBookingResponse;
import com.micros.webservices.ows._5_1.Reservation_wsdl.CreateBookingResponse;

@Service("chinaonlineReservationManager")
public class ChinaonlineReservationManagerImpl extends GenericManagerImpl<String, String> implements ChinaonlineReservationManager {

	private Log onlineLog = LogFactory.getLog("online");

	@Autowired
	private SendReqResManager sendReqResManager;

	/**
	 * {@inheritDoc}
	 */
	@ExceptionPointcut
	public OrderReservationRetVO createBooking(OrderReservationVO orderResVo) {
		OrderReservationRetVO resRetVO = new OrderReservationRetVO();

		// 酒店代码
		String hotelCode = orderResVo.getHotelCode();

		// 日志
		SendReqResLog reqRes = new SendReqResLog();
		reqRes.setReceivereqresId(orderResVo.getReceiveLogId());
		reqRes.setType(MessageType.Create);
		reqRes.setHotelCode(hotelCode);

		// 渠道订单号
		String channelOrderNo = orderResVo.generateOwsOrderNo();
		reqRes.setOriginOrderConfirmId(channelOrderNo);
		reqRes.setOriginOrderId(orderResVo.getOrderNo());

		try {
			String ows_reservation_wsdl = ProjectConfigConstant.ows_reservation_wsdl;
			String ows_channnelCode = ProjectConfigConstant.ows_channnelCode;
			String ows_timeout = ProjectConfigConstant.ows_timeout;
			String ows_transactionID = ProjectConfigConstant.ows_transactionID;

			// 建立调用stup
			ReservationServiceLocator wsLocator = new ReservationServiceLocator();

			wsLocator.setReservationServiceSoapEndpointAddress(ows_reservation_wsdl);

			// ReservationServiceSoap1Stub binding =
			// (ReservationServiceSoap1Stub)new
			// ReservationServiceLocator().getReservationServiceSoap1();
			ReservationServiceSoapStub binding = (ReservationServiceSoapStub) wsLocator.getReservationServiceSoap();

			binding.setTimeout(Integer.parseInt(ows_timeout));

			// 产生booking请求
			CreateBookingRequest createBookingRequest = new CreateBookingRequest();

			// 酒店预订信息
			HotelReservation hotelReservation = new HotelReservation();

			// creakbook时候为external的lsk订单号
			UniqueID uniqueID = new UniqueID();
			uniqueID.setType(UniqueIDType.EXTERNAL);
			uniqueID.set_value(channelOrderNo);
			UniqueID[] uniqueIDList = { uniqueID };
			hotelReservation.setUniqueIDList(uniqueIDList);

			// for loop roomstay
			RoomStay roomStay = new RoomStay();

			// for loop 循环房价
			RatePlan ratePlan = new RatePlan();
			// 获取房价代码
			String rateCode = orderResVo.getRateCode();
			ratePlan.setRatePlanCode(rateCode);
			ratePlan.setQualifyingIdType(ProfileType.TRAVEL_AGENT.name());
			ratePlan.setQualifyingIdValue(ows_channnelCode);
			RatePlan[] RatePlans = { ratePlan };
			roomStay.setRatePlans(RatePlans);

			// 房型
			// 获取房型代码
			String roomTypeCode = orderResVo.getRoomtypeCode();
			RoomType roomType = new RoomType();
			roomType.setRoomTypeCode(roomTypeCode);
			roomType.setNumberOfUnits(orderResVo.getRoomNum());
			RoomType[] roomTypes = { roomType };
			roomStay.setRoomTypes(roomTypes);

			// for loop 循环房价,以改为备注形式
			RoomRate roomRate = new RoomRate();
			roomRate.setRoomTypeCode(roomTypeCode);
			roomRate.setRatePlanCode(rateCode);
			orderResVo.getRoomNumList();

			List<OrderDailyRateVO> dailRateList = orderResVo.getRoomNumList();
			// 具体每间价格
			StringBuffer sb = new StringBuffer();
			double totalPrice = 0;
			int len = dailRateList.size();
			Rate[] rates = new Rate[len];
			for (int i = 0; i < dailRateList.size(); i++) {
				OrderDailyRateVO dailRateVo = dailRateList.get(i);
				sb.append(DateUtil.convertDateToString(dailRateVo.getPriceDate()));
				sb.append("/").append(dailRateVo.getPrice());
				sb.append(" ");
				Rate rate = new Rate();
				Amount amount = new Amount();
				amount.set_value(dailRateVo.getPrice());
				rate.setBase(amount);
				rate.setEffectiveDate(dailRateVo.getPriceDate());
				rates[i] = rate;

				totalPrice += dailRateVo.getPrice();
			}
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.000");
			sb.append(" 费用总计 ").append(df.format(totalPrice));

			roomRate.setRates(rates);
			RoomRate[] roomRates = { roomRate };
			roomStay.setRoomRates(roomRates);

			// 入住人数量
			GuestCount guestCount = new GuestCount();
			guestCount.setAgeQualifyingCode(AgeQualifyingCode.ADULT);
			guestCount.setCount(orderResVo.getAuditCount()); // 根据查询人数

			GuestCount guestCount2 = new GuestCount();
			guestCount2.setAgeQualifyingCode(AgeQualifyingCode.CHILD);
			guestCount2.setCount(0);

			GuestCount[] guestCounts = { guestCount, guestCount2 };
			roomStay.setGuestCounts(guestCounts);

			// 到店时间,离店时间
			Calendar checkInCal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
			// checkInCal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

			checkInCal.setTime(orderResVo.getCheckInDate());
			checkInCal.add(Calendar.HOUR, 13);

			Calendar checkOutCal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
			// checkOutCal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

			checkOutCal.setTime(orderResVo.getCheckOutDate());
			checkOutCal.add(Calendar.HOUR, 13);

			TimeSpan timeSpan = new TimeSpan();
			timeSpan.setStartDate(checkInCal);

			timeSpan.setEndDate(checkOutCal);

			roomStay.setTimeSpan(timeSpan);

			// 担保类型
			Guarantee guarantee = new Guarantee();

			guarantee.setGuaranteeType(orderResVo.getGuaranteeType());
			/*
			 * GuaranteeAccepted[] guaranteesAccepted = null;
			 * 
			 * GuaranteeAccepted guaranteeAccepted = new GuaranteeAccepted();
			 * CreditCard guaranteeCreditCard = new CreditCard();
			 * 
			 * guaranteeCreditCard.setCardCode("XX");
			 * guaranteeCreditCard.setCardHolderName("william");
			 * guaranteeCreditCard.setCardNumber("3722222222");
			 * guaranteeCreditCard.setExpirationDate(new Date());
			 */
			roomStay.setGuarantee(guarantee);

			// 入住酒店
			HotelReference hotelReference = new HotelReference();

			// 酒店信息
			String chainCode = orderResVo.getChainCode();
			hotelReference.setChainCode(chainCode);
			hotelReference.setHotelCode(hotelCode);

			roomStay.setHotelReference(hotelReference);

			// 循环 备注
			ReservationComment comment = new ReservationComment();
			comment.setGuestViewable(false);
			comment.setCommentOriginatorCode("CRO");
			String commentStr = orderResVo.getComment();
			if (!StringUtils.hasText(commentStr)) {
				commentStr = "TAOBAO ORDER";
			}
			StringBuffer ref = new StringBuffer();
			ref.append(commentStr);
			ref.append(" 渠道订单ID ");
			ref.append(channelOrderNo);

			ref.append(" 日房价 ").append(sb);

			ref.append(" 支付方式 ");
			if (orderResVo.getPaymentType().intValue() == PaymentTypeEnum.HOTELPAY.getId()) {
				ref.append("前台现付");
			} else if (orderResVo.getPaymentType() == PaymentTypeEnum.PRE.getId()) {
				ref.append("房费由渠道支付");
			} else if (orderResVo.getPaymentType() == PaymentTypeEnum.PREPAID.getId()) {
				ref.append("支付宝后付费");
			}

			comment.setText(new Text(ref.toString()));
			ReservationComment[] comments = { comment };
			roomStay.setComments(comments);

			RoomStay[] roomStays = { roomStay };

			hotelReservation.setRoomStays(roomStays);

			addGuest(orderResVo, hotelReservation);

			createBookingRequest.setHotelReservation(hotelReservation);

			// 最早与最晚到店时间
			CheckInPolicy checkInPolicy = new CheckInPolicy();
			checkInPolicy.setEarliestTime(DateUtil.getTimeNow(orderResVo.getEarliestArriveTime()));
			checkInPolicy.setLatestTime(DateUtil.getTimeNow(orderResVo.getLatestArriveTime()));
			createBookingRequest.setCheckInPolicy(checkInPolicy);

			// 订单号
			createBookingRequest.setChannelUniqueResID(channelOrderNo);

			// 报文头信息
			OGHeader oGHeader = new OGHeader();
			oGHeader.setTransactionID(ows_transactionID);

			EndPoint origin = new EndPoint();
			origin.setEntityID(ows_channnelCode);
			origin.setSystemType("WEB");
			oGHeader.setOrigin(origin);

			EndPoint destination = new EndPoint();
			destination.setEntityID("ORS");
			destination.setSystemType("ORS");
			oGHeader.setDestination(destination);

			SOAPHeaderElement el = new SOAPHeaderElement(new QName("http://webservices.micros.com/og/4.3/Core/", "OGHeader"), oGHeader);
			binding.setHeader(el);

			Object obj = JSONObject.toJSON(createBookingRequest);
			reqRes.setRequest(obj.toString());
			onlineLog.debug("createBookingRequest:" + obj);
			reqRes.setCreatedTime(new Date());
			CreateBookingResponse createBookingResponse = binding.createBooking(createBookingRequest);

			Object objRes = JSONObject.toJSON(createBookingResponse);
			reqRes.setResponse(objRes.toString());
			onlineLog.debug("createBookingResponse:" + objRes);

			String retFlag = createBookingResponse.getResult().getResultStatusFlag().getValue();
			resRetVO.setResultStatusFlag(retFlag);
			reqRes.setStatus(retFlag);

			if ("SUCCESS".equals(retFlag)) {
				String pmsOrderId = createBookingResponse.getHotelReservation().getUniqueIDList()[0].get_value();
				reqRes.setOrderId(pmsOrderId);
				resRetVO.setResevationUniqueID(pmsOrderId);
				onlineLog.debug("畅连订单创建成功！" + pmsOrderId);
			} else if ("FAIL".equals(retFlag)) {
				String errorCode = createBookingResponse.getResult().getGDSError().getErrorCode();
				String errorText = createBookingResponse.getResult().getGDSError().get_value();
				resRetVO.setErrorCode(errorCode);
				resRetVO.setErrMsg(errorText);
				reqRes.setResponse(reqRes.getResponse() + errorText);
				onlineLog.error("畅连订单创建失败！  ResevationUniqueID：" + orderResVo.getResevationUniqueID() + resRetVO.getErrMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			reqRes.setException(e.toString());
			resRetVO.setResultStatusFlag("ERR");
			resRetVO.setErrMsg(e.getMessage());
			onlineLog.error("畅连订单创建失败！  ResevationUniqueID：" + orderResVo.getResevationUniqueID() + " OrderNo:" + channelOrderNo + " ExceptionMsg:" + CommonUtil.getExceptionMsg(e, "ccm"));
		}
		saveSendReqResLog(reqRes);
		return resRetVO;
	}

	/**
	 * {@inheritDoc}
	 */
	public OrderReservationRetVO modifyBooking(OrderReservationVO orderResVo) {
		/*
		 * OrderReservationRetVO resRetVO = new OrderReservationRetVO(); try {
		 * String ows_reservation_wsdl =
		 * ProjectConfigConstant.ows_reservation_wsdl; String ows_channnelCode =
		 * ProjectConfigConstant.ows_channnelCode; String ows_timeout =
		 * ProjectConfigConstant.ows_timeout; String ows_transactionID =
		 * ProjectConfigConstant.ows_transactionID;
		 * 
		 * //建立调用stup ReservationServiceLocator wsLocator = new
		 * ReservationServiceLocator();
		 * wsLocator.setReservationServiceSoapEndpointAddress
		 * (ows_reservation_wsdl); //ReservationServiceSoap1Stub binding =
		 * (ReservationServiceSoap1Stub)new
		 * ReservationServiceLocator().getReservationServiceSoap1();
		 * ReservationServiceSoapStub binding = (ReservationServiceSoapStub)
		 * wsLocator.getReservationServiceSoap();
		 * binding.setTimeout(Integer.parseInt(ows_timeout)); // 产生booking请求
		 * ModifyBookingRequest modifyBookingRequest = new
		 * ModifyBookingRequest();
		 * 
		 * //酒店预订信息 HotelReservation hotelReservation = new HotelReservation();
		 * //预订唯一号 UniqueID uniqueID = new UniqueID();
		 * uniqueID.setType(UniqueIDType.INTERNAL);
		 * uniqueID.set_value(orderResVo.getResevationUniqueID()); UniqueID[]
		 * uniqueIDList = {uniqueID};
		 * hotelReservation.setUniqueIDList(uniqueIDList);
		 * 
		 * //for loop roomstay RoomStay roomStay = new RoomStay();
		 * 
		 * //for loop 循环房价 RatePlan ratePlan = new RatePlan();
		 * 
		 * //获取房价代码 ratePlan.setRatePlanCode(orderResVo.getRateCode());
		 * ratePlan.setQualifyingIdType(ProfileType.TRAVEL_AGENT.name());
		 * ratePlan.setQualifyingIdValue(ows_channnelCode); RatePlan[] RatePlans
		 * = {ratePlan}; roomStay.setRatePlans(RatePlans);
		 * 
		 * //for loop 循环房型 RoomType roomType = new RoomType(); //获取房型代码
		 * roomType.setRoomTypeCode(orderResVo.getRoomtypeCode());
		 * roomType.setNumberOfUnits(orderResVo.getRoomNum());
		 * 
		 * RoomType[] roomTypes = {roomType};
		 * 
		 * roomStay.setRoomTypes(roomTypes);
		 * 
		 * //for loop 循环房价,房型
		 * 
		 * RoomRate roomRate = new RoomRate();
		 * roomRate.setRoomTypeCode(orderResVo.getRoomtypeCode());
		 * roomRate.setRatePlanCode(orderResVo.getRateCode());
		 * 
		 * RoomRate[] roomRates = {roomRate};
		 * 
		 * roomStay.setRoomRates(roomRates);
		 * 
		 * 
		 * 
		 * //入住人数量 GuestCount guestCount = new GuestCount();
		 * guestCount.setAgeQualifyingCode(AgeQualifyingCode.ADULT);
		 * guestCount.setCount(1);
		 * 
		 * GuestCount guestCount2 = new GuestCount();
		 * guestCount2.setAgeQualifyingCode(AgeQualifyingCode.CHILD);
		 * guestCount2.setCount(0);
		 * 
		 * 
		 * GuestCount[] guestCounts = {guestCount, guestCount2};
		 * roomStay.setGuestCounts(guestCounts);
		 * 
		 * 
		 * 
		 * //到店时间,离店时间 Calendar checkInCal = new GregorianCalendar();
		 * 
		 * checkInCal.setTime(orderResVo.getCheckInDate());
		 * 
		 * 
		 * Calendar checkOutCal = new GregorianCalendar();
		 * 
		 * checkOutCal.setTime(orderResVo.getCheckOutDate());
		 * 
		 * 
		 * 
		 * TimeSpan timeSpan = new TimeSpan();
		 * timeSpan.setStartDate(checkInCal);
		 * 
		 * timeSpan.setEndDate(checkOutCal);
		 * 
		 * roomStay.setTimeSpan(timeSpan);
		 * 
		 * 
		 * //担保类型 Guarantee guarantee = new Guarantee();
		 * 
		 * guarantee.setGuaranteeType("DUE"); GuaranteeAccepted[]
		 * guaranteesAccepted = null;
		 * 
		 * GuaranteeAccepted guaranteeAccepted = new GuaranteeAccepted();
		 * CreditCard guaranteeCreditCard = new CreditCard();
		 * 
		 * guaranteeCreditCard.setCardCode("XX");
		 * guaranteeCreditCard.setCardHolderName("william");
		 * guaranteeCreditCard.setCardNumber("3722222222");
		 * guaranteeCreditCard.setExpirationDate(new Date());
		 * 
		 * roomStay.setGuarantee(guarantee);
		 * 
		 * //入住酒店 HotelReference hotelReference = new HotelReference();
		 * 
		 * hotelReference.setChainCode(orderResVo.getChainCode());
		 * roomStay.setHotelReference(hotelReference);
		 * 
		 * //循环 备注 ReservationComment comment = new ReservationComment();
		 * comment.setGuestViewable(false);
		 * comment.setCommentOriginatorCode("CRO"); comment.setText(new
		 * Text(orderResVo.getComment())); ReservationComment[] comments =
		 * {comment}; roomStay.setComments(comments);
		 * 
		 * RoomStay[] roomStays = {roomStay};
		 * 
		 * hotelReservation.setRoomStays(roomStays);
		 * 
		 * //预订人信息 ResGuest resGuest = new ResGuest();
		 * 
		 * Profile[] profiles = new
		 * Profile[orderResVo.getCheckInPersonList().size()]; int i = 0; for
		 * (CheckInPersonVO checkInPerson : orderResVo.getCheckInPersonList()) {
		 * 
		 * 
		 * Profile profile = new Profile(); //profile. 客人 Customer customer =
		 * new Customer();
		 * 
		 * customer.setGender(Gender.MALE);
		 * 
		 * PersonName personName = new PersonName();
		 * 
		 * String[] nameTitle = new String[]{"Mr."};
		 * personName.setNameTitle(nameTitle);
		 * personName.setFirstName(checkInPerson.getCnName());
		 * personName.setLastName
		 * (PinyinUtil.covertCnNameToPinyin(checkInPerson.getCnName()));
		 * 
		 * customer.setPersonName(personName);
		 * 
		 * profile.setCustomer(customer);
		 * 
		 * 
		 * NameAddress nameAddress = new NameAddress(); String[] addressLine =
		 * new String[]{"大连信命路石头记22好"}; nameAddress.setAddressLine(addressLine);
		 * nameAddress.setStateProv("辽宁"); nameAddress.setCityName("大连市");
		 * nameAddress.setCountryCode("CN");
		 * nameAddress.setPostalCode("100035");
		 * 
		 * NameAddress[] addresses = {nameAddress};
		 * 
		 * profile.setAddresses(addresses);
		 * 
		 * 
		 * //客人联系方式 NamePhone namePhone = new NamePhone();
		 * namePhone.setPhoneRole("PHONE"); namePhone.setPhoneType("MOBILE");
		 * namePhone.setPhoneNumber(checkInPerson.getMobile());
		 * 
		 * NamePhone namePhone2 = new NamePhone();
		 * namePhone2.setPhoneRole("EMAIL"); namePhone2.setPhoneType("EMAIL");
		 * namePhone2.setPhoneNumber(checkInPerson.getEmail());
		 * 
		 * NamePhone[] phones = {namePhone, namePhone2};
		 * 
		 * profile.setPhones(phones); profiles[i] = profile; i++; }
		 * 
		 * resGuest.setProfiles(profiles); ResGuest[] resGuests = {resGuest};
		 * hotelReservation.setResGuests(resGuests);
		 * modifyBookingRequest.setHotelReservation(hotelReservation);
		 * 
		 * //chinaonline入住政策 CheckInPolicy checkInPolicy = new CheckInPolicy();
		 * checkInPolicy.setEarliestTime("14:00");
		 * checkInPolicy.setLatestTime("18:00");
		 * modifyBookingRequest.setCheckInPolicy(checkInPolicy);
		 * 
		 * //订单号 //
		 * modifyBookingRequest.setChannelUniqueResID(orderResVo.getOrderNo());
		 * 
		 * // 报文头信息 OGHeader oGHeader = new OGHeader();
		 * oGHeader.setTransactionID(ows_transactionID);
		 * 
		 * EndPoint origin = new EndPoint();
		 * origin.setEntityID(ows_channnelCode); origin.setSystemType("WEB");
		 * oGHeader.setOrigin(origin);
		 * 
		 * EndPoint destination = new EndPoint();
		 * destination.setEntityID("ORS"); destination.setSystemType("ORS");
		 * oGHeader.setDestination(destination);
		 * 
		 * SOAPHeaderElement el = new SOAPHeaderElement(new
		 * QName("http://webservices.micros.com/og/4.3/Core/", "OGHeader"),
		 * oGHeader); binding.setHeader(el); ModifyBookingResponse
		 * modifyBookingResponse = binding.modifyBooking(modifyBookingRequest);
		 * onlineLog
		 * .info("*****modifyBookingRequest:"+JSONObject.toJSON(modifyBookingRequest
		 * )); onlineLog.info("*****modifyBookingResponse:"+JSONObject.toJSON(
		 * modifyBookingResponse)); String retFlag =
		 * modifyBookingResponse.getResult().getResultStatusFlag().getValue();
		 * 
		 * resRetVO.setResultStatusFlag(retFlag);
		 * 
		 * if ("SUCCESS".equals(retFlag)) {
		 * resRetVO.setResevationUniqueID(modifyBookingResponse
		 * .getHotelReservation().getUniqueIDList()[0].get_value()); } else if
		 * ("FAIL".equals(retFlag)) { String errorCode =
		 * modifyBookingResponse.getResult().getGDSError().getErrorCode();
		 * String errorText =
		 * modifyBookingResponse.getResult().getGDSError().get_value();
		 * resRetVO.setErrMsg("调用ows返回错误信息：" + errorCode + "(" + errorText +
		 * ")"); onlineLog.error("畅连订单修改失败！  ResevationUniqueID："+orderResVo.
		 * getResevationUniqueID()+ resRetVO.getErrMsg()); } } catch (Exception
		 * e) { resRetVO.setResultStatusFlag("ERR");
		 * resRetVO.setErrMsg(CommonUtil.getExceptionMsg(e,"ccm")); }
		 */
		return null;// resRetVO;
	}

	/**
	 * {@inheritDoc}
	 */
	@ExceptionPointcut
	public OrderReservationRetVO cacelBooking(OrderReservationVO orderResVo) {
		OrderReservationRetVO resRetVO = new OrderReservationRetVO();

		// pms订单号
		String pmsOrderId = orderResVo.getResevationUniqueID();
		// 日志
		SendReqResLog reqRes = new SendReqResLog();
		reqRes.setReceivereqresId(orderResVo.getReceiveLogId());
		reqRes.setType(MessageType.Cancel);
		reqRes.setHotelCode(orderResVo.getHotelCode());
		reqRes.setOrderId(pmsOrderId);
		reqRes.setOriginOrderId(orderResVo.getOrderNo());
		reqRes.setOriginOrderConfirmId(orderResVo.getOrderConfirmNo());

		try {
			String ows_reservation_wsdl = ProjectConfigConstant.ows_reservation_wsdl;
			String ows_channnelCode = ProjectConfigConstant.ows_channnelCode;
			String ows_timeout = ProjectConfigConstant.ows_timeout;
			String ows_transactionID = ProjectConfigConstant.ows_transactionID;

			// 建立调用stup
			ReservationServiceLocator wsLocator = new ReservationServiceLocator();
			wsLocator.setReservationServiceSoapEndpointAddress(ows_reservation_wsdl);
			// ReservationServiceSoap1Stub binding =
			// (ReservationServiceSoap1Stub)new
			// ReservationServiceLocator().getReservationServiceSoap1();
			ReservationServiceSoapStub binding = (ReservationServiceSoapStub) wsLocator.getReservationServiceSoap();
			binding.setTimeout(Integer.parseInt(ows_timeout));

			// 产生booking请求
			CancelBookingRequest cancelBookingRequest = new CancelBookingRequest();

			// 入住酒店
			HotelReference hotelReference = new HotelReference();

			hotelReference.setChainCode(orderResVo.getChainCode());
			hotelReference.setHotelCode(orderResVo.getHotelCode());
			cancelBookingRequest.setHotelReference(hotelReference);

			// 预订唯一号
			UniqueID confirmationNumber = new UniqueID();

			confirmationNumber.setType(UniqueIDType.INTERNAL);
			confirmationNumber.set_value(pmsOrderId);

			cancelBookingRequest.setConfirmationNumber(confirmationNumber);

			cancelBookingRequest.setLastName(PinyinUtil.covertCnNameToPinyin(orderResVo.getCancleCnName()));
			CancelTerm cancelTerm = new CancelTerm();

			CancelTermType cancelType = CancelTermType.fromString("Cancel");

			cancelTerm.setCancelType(cancelType);

			Paragraph cancelReason = new Paragraph();
			cancelReason.setText(new Text(orderResVo.getCancelReason()));
			cancelTerm.setCancelReason(cancelReason);
			cancelBookingRequest.setCancelTerm(cancelTerm);

			// 报文头信息
			OGHeader oGHeader = new OGHeader();
			oGHeader.setTransactionID(ows_transactionID);

			EndPoint origin = new EndPoint();
			origin.setEntityID(ows_channnelCode);
			origin.setSystemType("WEB");
			oGHeader.setOrigin(origin);

			EndPoint destination = new EndPoint();
			destination.setEntityID("ORS");
			destination.setSystemType("ORS");
			oGHeader.setDestination(destination);

			SOAPHeaderElement el = new SOAPHeaderElement(new QName("http://webservices.micros.com/og/4.3/Core/", "OGHeader"), oGHeader);
			binding.setHeader(el);

			Object objReq = JSONObject.toJSON(cancelBookingRequest);
			onlineLog.debug("*****cancelBookingRequest:" + objReq);
			reqRes.setRequest(objReq.toString());
			reqRes.setCreatedTime(new Date());
			CancelBookingResponse cancelBookingResponse = binding.cancelBooking(cancelBookingRequest);

			Object objRes = JSONObject.toJSON(cancelBookingResponse);
			onlineLog.debug("*****cancelBookingResponse:" + objRes);
			reqRes.setResponse(objRes.toString());

			String retFlag = cancelBookingResponse.getResult().getResultStatusFlag().getValue();
			resRetVO.setResultStatusFlag(retFlag);
			reqRes.setStatus(retFlag);

			if ("SUCCESS".equals(retFlag)) {
				// resRetVO.setResevationUniqueID(cancelBookingResponse.getCancellationNumber().get_value());
				onlineLog.info("畅连订单取消成功！ ResevationUniqueID：" + pmsOrderId);
			} else if ("FAIL".equals(retFlag)) {
				String errorCode = cancelBookingResponse.getResult().getGDSError().getErrorCode();
				String errorText = cancelBookingResponse.getResult().getGDSError().get_value();
				resRetVO.setErrorCode(errorCode);
				resRetVO.setErrMsg(errorText);
				onlineLog.error("畅连订单取消失败！  ResevationUniqueID：" + pmsOrderId + resRetVO.getErrMsg());
			}
		} catch (Exception e) {
			reqRes.setException(e.toString());
			resRetVO.setResultStatusFlag("ERR");
			resRetVO.setErrMsg(CommonUtil.getExceptionMsg(e, "ccm"));
			onlineLog.error("畅连订单取消失败！  ResevationUniqueID：" + pmsOrderId + resRetVO.getErrMsg() + "\n ExceptionMsg：" + CommonUtil.getExceptionMsg(e, "ccm"));
		}
		saveSendReqResLog(reqRes);
		return resRetVO;
	}

	private void addGuest(OrderReservationVO orderResVo, HotelReservation hotelReservation) {
		// 预订人信息
		ResGuest resGuest = new ResGuest();

		CheckInPersonVO checkInPerson = orderResVo.getCheckInPersonList().get(0);

		Profile profile = new Profile();
		// profile. 客人
		Customer customer = new Customer();

		customer.setGender(Gender.MALE);

		PersonName personName = new PersonName();

		String[] nameTitle = new String[] { "Mr." };
		personName.setNameTitle(nameTitle);
		personName.setFirstName(checkInPerson.getCnName());
		personName.setLastName(PinyinUtil.covertCnNameToPinyin(checkInPerson.getCnName()));

		customer.setPersonName(personName);
		profile.setCustomer(customer);

		// 客人联系方式
		NamePhone namePhone = new NamePhone();
		namePhone.setPhoneRole("PHONE");
		namePhone.setPhoneType("MOBILE");
		namePhone.setPhoneNumber(checkInPerson.getMobile());

		NamePhone namePhone2 = new NamePhone();
		namePhone2.setPhoneRole("EMAIL");
		namePhone2.setPhoneType("EMAIL");
		namePhone2.setPhoneNumber(checkInPerson.getEmail());

		NamePhone[] phones = { namePhone, namePhone2 };

		profile.setPhones(phones);

		// Memberships
		NameMembership membership = new NameMembership();
		membership.setMembershipNumber(orderResVo.getHaccnt());
		NameMembership[] memberships = { membership };
		profile.setMemberships(memberships);

		Profile[] profiles = { profile };
		resGuest.setProfiles(profiles);

		ResGuest[] resGuests = { resGuest };

		hotelReservation.setResGuests(resGuests);
	}

	@Override
	public AvailabilityResVo generalAvailability(AvailabilityReqVo avaiVo) {
		AvailabilityResVo avaiResVo = null;
		try {
			String ows_reservation_wsdl = ProjectConfigConstant.generalAvailability_wsdl;// "http://203.81.25.50:8000/Col_switch_ws/Availability.asmx";//ProjectConfigConstant.ows_reservation_wsdl;
			String ows_channnelCode = ProjectConfigConstant.ows_channnelCode;
			// String ows_timeout = ProjectConfigConstant.ows_timeout;
			String ows_transactionID = ProjectConfigConstant.ows_transactionID;

			AvailabilityRequest avaiReq = new AvailabilityRequest();
			avaiReq.setSummaryOnly(true);
			AvailRequestSegment availRequestSegment = new AvailRequestSegment();
			availRequestSegment.setAvailReqType("Room");
			availRequestSegment.setNumberOfRooms(avaiVo.getNumberOfRooms()); // 房间数
			// availRequestSegment.setRoomOccupancy(new BigInteger("1")); //占用
			availRequestSegment.setTotalNumberOfGuests(avaiVo.getTotalNumberOfGuests()); // 人数，每间房的入住人数

			RatePlanCandidate rpc = new RatePlanCandidate();
			rpc.setRatePlanCode("ALL");
			rpc.setQualifyingIdType("TRAVEL");
			rpc.setQualifyingIdValue(ows_channnelCode);
			RatePlanCandidates rpcs = new RatePlanCandidates();
			rpcs.setRatePlanCandidate(rpc);
			availRequestSegment.setRatePlanCandidates(rpcs);

			StayDateRange sdr = new StayDateRange();
			sdr.setStartDate(avaiVo.getStartDate());
			sdr.setEndDate(avaiVo.getEndDate());
			availRequestSegment.setStayDateRange(sdr);

			HotelSearchCriteria hsc = new HotelSearchCriteria();
			Criterion c = new Criterion();
			HotelRef hrf = new HotelRef();
			hrf.setChainCode(avaiVo.getChainCode());
			hrf.setHotelCode(avaiVo.getHotelCode());
			c.setHotelRef(hrf);
			hsc.setCriterion(c);
			availRequestSegment.setHotelSearchCriteria(hsc);
			avaiReq.setAvailRequestSegment(availRequestSegment);

			Envelope e = new Envelope();
			Header header = new Header();
			com.ccm.api.model.availability.req.OGHeader ogh = new com.ccm.api.model.availability.req.OGHeader();
			ogh.setTransactionID(ows_transactionID);
			Origin orgin = new Origin();
			orgin.setEntityID(ows_channnelCode);
			orgin.setSystemType("WEB");
			ogh.setOrigin(orgin);

			Destination dest = new Destination();
			dest.setEntityID("ORS");
			dest.setSystemType("ORS");
			ogh.setDestination(dest);
			header.setOGHeader(ogh);
			e.setHeader(header);

			Body body = new Body();
			body.setAvailabilityRequest(avaiReq);
			e.setBody(body);
			String xmlStr = XMLUtil.JAXBParseToXml(e);
			String res = PushDataUtil.postData(ows_reservation_wsdl, xmlStr);
			Object obj = XMLUtil.JAXBParseToBean(res, com.ccm.api.model.availability.res.Envelope.class, null);
			com.ccm.api.model.availability.res.Envelope rese = (com.ccm.api.model.availability.res.Envelope) obj;

			avaiResVo = new AvailabilityResVo();
			AvailabilityResponse avaiRes = rese.getBody().getAvailabilityResponse();
			String resultStatusFlag = avaiRes.getResult().getResultStatusFlag(); // “SUCCESS”：查询成功与否的标志。
			RoomRates roomRates = avaiRes.getAvailResponseSegments().getAvailResponseSegment().getRoomStayList().getRoomStay().getRoomRates();
			List<com.ccm.api.model.availability.res.RoomRate> roomRateList = roomRates.getRoomRate();
			avaiResVo.setResultStatusFlag(resultStatusFlag);
			avaiResVo.setRoomRateList(roomRateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return avaiResVo;
	}

	private void saveSendReqResLog(SendReqResLog reqRes) {
		reqRes.setInterfaceId(ProjectConfigConstant.ows_channnelCode);
		if (reqRes.getStatus() == null) {
			reqRes.setStatus(OrderReservationRetVO.RESULT_STATUS_FAIL);
		}
		reqRes.setCreatedBy(OXIConstant.creatorCode);
		reqRes.setLastModifyTime(new Date());
		sendReqResManager.save(reqRes);
	}

}
