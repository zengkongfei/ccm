package com.ccm.api.service.wbe.channelBooking.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.namespace.QName;

import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.types.Time;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.net.chinaonline.webservices._switch._1_5_1.Reservation_wsdl.CheckInPolicy;
import cn.net.chinaonline.webservices._switch._1_5_1.Reservation_wsdl.CreateBookingRequest;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.ows.vo.OrderDailyRateVO;
import com.ccm.api.model.ows.vo.OrderReservationRetVO;
import com.ccm.api.model.wbe.WbeGuestVO;
import com.ccm.api.model.wbe.WbeOrderVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.wbe.channelBooking.ChannelBookIngReservationManager;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.PinyinUtil;
import com.micros.webservices.ReservationServiceLocator;
import com.micros.webservices.ReservationServiceSoapStub;
import com.micros.webservices.og._4_3.Common.Amount;
import com.micros.webservices.og._4_3.Common.CreditCard;
import com.micros.webservices.og._4_3.Common.Gender;
import com.micros.webservices.og._4_3.Common.IDPair;
import com.micros.webservices.og._4_3.Common.PersonName;
import com.micros.webservices.og._4_3.Common.Text;
import com.micros.webservices.og._4_3.Common.UniqueID;
import com.micros.webservices.og._4_3.Common.UniqueIDType;
import com.micros.webservices.og._4_3.Common.UserDefinedValue;
import com.micros.webservices.og._4_3.Core.EndPoint;
import com.micros.webservices.og._4_3.Core.OGHeader;
import com.micros.webservices.og._4_3.HotelCommon.AgeQualifyingCode;
import com.micros.webservices.og._4_3.HotelCommon.CancelTerm;
import com.micros.webservices.og._4_3.HotelCommon.CancelTermType;
import com.micros.webservices.og._4_3.HotelCommon.Guarantee;
import com.micros.webservices.og._4_3.HotelCommon.GuaranteeAccepted;
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
import com.micros.webservices.og._4_3.Name.NameAddress;
import com.micros.webservices.og._4_3.Name.NamePhone;
import com.micros.webservices.og._4_3.Name.Profile;
import com.micros.webservices.og._4_3.Reservation.HotelReservation;
import com.micros.webservices.og._4_3.Reservation.ResGuest;
import com.micros.webservices.ows._5_1.Reservation_wsdl.CancelBookingRequest;
import com.micros.webservices.ows._5_1.Reservation_wsdl.CancelBookingResponse;
import com.micros.webservices.ows._5_1.Reservation_wsdl.CreateBookingResponse;

@Repository("channelBookIngReservationManager")
public class ChannelBookIngReservationManagerImpl extends GenericManagerImpl<String, String> implements ChannelBookIngReservationManager {

	
	@Override
	public OrderReservationRetVO createBooking(WbeOrderVO wbeOrderVO) throws Exception{
		OrderReservationRetVO resRetVO = new OrderReservationRetVO();
		HotelReservation hotelReservation = new HotelReservation();
		CheckInPolicy checkInPolicy = new CheckInPolicy();
		ReservationServiceSoapStub binding = buildSwitchReservation(wbeOrderVO,hotelReservation,checkInPolicy);
		
		UniqueID uniqueID = new UniqueID();
		uniqueID.setType(UniqueIDType.EXTERNAL);
		
		//渠道订单号
		uniqueID.set_value(wbeOrderVO.getCrsno());
		
		UniqueID[] uniqueIDList = { uniqueID };
		
		hotelReservation.setUniqueIDList(uniqueIDList);			
		// 产生booking请求
		CreateBookingRequest createBookingRequest = new CreateBookingRequest();
		
		//渠道订单号
		//createBookingRequest.setChannelUniqueResID(wbeOrderVO.getCrsno());
		//setChannelUniqueResID(channelUniqueResID);
		//uniqueID.set_value(wbeOrderVO.getCrsno());
		
		createBookingRequest.setHotelReservation(hotelReservation);
		createBookingRequest.setCheckInPolicy(checkInPolicy);
		Object obj = JSONObject.toJSON(createBookingRequest);
		
		log.info("WBE UI createBooking createBookingRequest"+obj);
		
		CreateBookingResponse createBookingResponse = binding.createBooking(createBookingRequest);
		JSONObject objRes = (JSONObject) JSONObject.toJSON(createBookingResponse);
		String retFlag = createBookingResponse.getResult().getResultStatusFlag().getValue();
		resRetVO.setResultStatusFlag(retFlag);
		log.info("WBE UI createBooking createBookingResponse"+objRes.toJSONString());
		
		if ("SUCCESS".equals(retFlag)) {
			String pmsOrderId = createBookingResponse.getHotelReservation().getUniqueIDList()[0].get_value();
			resRetVO.setResevationUniqueID(pmsOrderId);
		} else if ("FAIL".equals(retFlag)) {
			String errorCode = createBookingResponse.getResult().getGDSError().getErrorCode();
			String errorText = createBookingResponse.getResult().getGDSError().get_value();
			resRetVO.setErrorCode(errorCode);
			resRetVO.setErrMsg(errorText);
		}
		
		return resRetVO;
	}

	@Override
	public OrderReservationRetVO cacelBooking(WbeOrderVO wbeOrderVO) throws Exception{
		OrderReservationRetVO resRetVO = new OrderReservationRetVO();
		String ows_reservation_wsdl = ProjectConfigConstant.ows_reservation_wsdl;
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

		hotelReference.setChainCode(wbeOrderVO.getChainCode());
		hotelReference.setHotelCode(wbeOrderVO.getHotelCode());
		cancelBookingRequest.setHotelReference(hotelReference);

		// 预订唯一号
		UniqueID confirmationNumber = new UniqueID();

		confirmationNumber.setType(UniqueIDType.INTERNAL);
		confirmationNumber.set_value(wbeOrderVO.getMasterId());

		cancelBookingRequest.setConfirmationNumber(confirmationNumber);

		cancelBookingRequest.setLastName(PinyinUtil.covertCnNameToPinyin(wbeOrderVO.getWbeGuestVO().getName2()));
		CancelTerm cancelTerm = new CancelTerm();


		cancelTerm.setCancelType(CancelTermType.fromString("Cancel"));

		Paragraph cancelReason = new Paragraph();
		cancelReason.setText(new Text(wbeOrderVO.getCancelReason()));
		cancelTerm.setCancelReason(cancelReason);
		cancelBookingRequest.setCancelTerm(cancelTerm);

		// 报文头信息
		OGHeader oGHeader = new OGHeader();
		oGHeader.setTransactionID(ows_transactionID);

		EndPoint origin = new EndPoint();
		origin.setEntityID(wbeOrderVO.getChannelCode());
		origin.setSystemType("WEB");
		oGHeader.setOrigin(origin);

		EndPoint destination = new EndPoint();
		destination.setEntityID("ORS");
		destination.setSystemType("ORS");
		oGHeader.setDestination(destination);

		SOAPHeaderElement el = new SOAPHeaderElement(new QName("http://webservices.micros.com/og/4.3/Core/", "OGHeader"), oGHeader);
		binding.setHeader(el);

		log.info("-----------------cacelBooking cancelBookingRequest="+cancelBookingRequest);
		
		CancelBookingResponse cancelBookingResponse = binding.cancelBooking(cancelBookingRequest);

		log.info("-----------------cacelBooking cancelBookingResponse="+cancelBookingResponse);
		
		Object objRes = JSONObject.toJSON(cancelBookingResponse);
		log.info("WBE UI cancel cancelBookingResponse"+objRes);

		String retFlag = cancelBookingResponse.getResult().getResultStatusFlag().getValue();
		resRetVO.setResultStatusFlag(retFlag);

		if ("SUCCESS".equals(retFlag)) {
			IDPair[] ids = cancelBookingResponse.getResult().getIDs();
			if(ids!=null){
				for(IDPair id :ids){
					resRetVO.setResevationUniqueID(id.getOperaId()+"");
					break;
				}
			}
		} else if ("FAIL".equals(retFlag)) {
			String errorCode = cancelBookingResponse.getResult().getGDSError().getErrorCode();
			String errorText = cancelBookingResponse.getResult().getGDSError().get_value();
			resRetVO.setErrorCode(errorCode);
			resRetVO.setErrMsg(errorText);
		}
		return resRetVO;
	}
	
	private ReservationServiceSoapStub buildSwitchReservation(WbeOrderVO wbeOrderVO,HotelReservation hotelReservation,CheckInPolicy checkInPolicy) throws Exception{
		// 酒店代码
		String hotelCode = wbeOrderVO.getHotelCode();
		// for loop roomstay
		RoomStay roomStay = new RoomStay();

		// for loop 循环房价
		RatePlan ratePlan = new RatePlan();
		// 获取房价代码
		String rateCode = wbeOrderVO.getRatePlanCode();
		
		
		ratePlan.setRatePlanCode(rateCode);
		ratePlan.setQualifyingIdType(wbeOrderVO.getAccessCodeType());
		ratePlan.setQualifyingIdValue(wbeOrderVO.getAccessCode());
		RatePlan[] RatePlans = { ratePlan };
		roomStay.setRatePlans(RatePlans);

		// 房型
		// 获取房型代码
		String roomTypeCode = wbeOrderVO.getRoomTypeCode();
		RoomType roomType = new RoomType();
		roomType.setRoomTypeCode(roomTypeCode);
		roomType.setNumberOfUnits(wbeOrderVO.getNumber());
		RoomType[] roomTypes = { roomType };
		roomStay.setRoomTypes(roomTypes);

		// for loop 循环房价,以改为备注形式
		RoomRate roomRate = new RoomRate();
		roomRate.setRoomTypeCode(roomTypeCode);
		roomRate.setRatePlanCode(rateCode);
		

		List<OrderDailyRateVO> dailRateList = wbeOrderVO.getDailRateList();
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
		guestCount.setCount(wbeOrderVO.getNumberOfUnits()); // 根据查询人数

		GuestCount guestCount2 = new GuestCount();
		guestCount2.setAgeQualifyingCode(AgeQualifyingCode.CHILD);
		guestCount2.setCount(0);

		GuestCount[] guestCounts = { guestCount, guestCount2 };
		roomStay.setGuestCounts(guestCounts);
		
		// 到店时间,离店时间
		Calendar checkInCal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
		// checkInCal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

		checkInCal.setTime(wbeOrderVO.getArr());
		checkInCal.add(Calendar.HOUR, 8);

		Calendar checkOutCal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
		// checkOutCal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

		checkOutCal.setTime(wbeOrderVO.getDep());
		checkOutCal.add(Calendar.HOUR, 8);

		TimeSpan timeSpan = new TimeSpan();
		timeSpan.setStartDate(checkInCal);

		timeSpan.setEndDate(checkOutCal);

		roomStay.setTimeSpan(timeSpan);
		
		// 担保类型
		Guarantee guarantee = new Guarantee();

		guarantee.setGuaranteeType(wbeOrderVO.getPayment());
		
		if(GuaranteeCode.CCGTD.equalsIgnoreCase(wbeOrderVO.getPayment())){
			GuaranteeAccepted guaranteeAccepted = new GuaranteeAccepted(); 
			CreditCard guaranteeCreditCard = new CreditCard(); 
			guaranteeCreditCard.setCardCode(wbeOrderVO.getCardCode()); 
			guaranteeCreditCard.setCardHolderName(wbeOrderVO.getCardHolderName()); 
			guaranteeCreditCard.setCardNumber(wbeOrderVO.getCardNumber()); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			guaranteeCreditCard.setExpirationDate(sdf.parse(wbeOrderVO.getExpirationDate()));
			guaranteeAccepted.setGuaranteeCreditCard(guaranteeCreditCard);
			GuaranteeAccepted[] guaranteesAccepted = { guaranteeAccepted };
			guarantee.setGuaranteesAccepted(guaranteesAccepted);
		}
		
		roomStay.setGuarantee(guarantee);
		
		// 入住酒店
		HotelReference hotelReference = new HotelReference();

		// 酒店信息
		String chainCode = wbeOrderVO.getChainCode();
		hotelReference.setChainCode(chainCode);
		hotelReference.setHotelCode(hotelCode);

		roomStay.setHotelReference(hotelReference);
		
		// 循环 备注
		ReservationComment comment = new ReservationComment();
		comment.setGuestViewable(false);
		comment.setCommentOriginatorCode("CRO");
		WbeGuestVO wbeGuestVO = wbeOrderVO.getWbeGuestVO();
		String commentStr = wbeGuestVO.getNeed();
		if (StringUtils.hasText(commentStr)) {
			commentStr = commentStr.replace("【", "").replace("】", "").replace("·", "");
		}
		StringBuffer ref = new StringBuffer();
		ref.append(commentStr);

		comment.setText(new Text(ref.toString()));
		ReservationComment[] comments = { comment };
		roomStay.setComments(comments);
		
		RoomStay[] roomStays = { roomStay };

		hotelReservation.setRoomStays(roomStays);

		addGuest(wbeOrderVO, hotelReservation);
		
//		if (StringUtils.hasText(orderResVo.getReceiptTitle())) {
//			NativeName name = new NativeName();
//			name.setLastName(orderResVo.getReceiptTitle());
//			BillHeader bh = new BillHeader();
//			bh.setName(name);
//			hotelReservation.setInvoice(bh);
//		}
		
//		/**
//		 *  1.    当且仅当“预付”类型的产品时，将阿里订单号赋值到SWITCH协议UDF字段的 “Payment Transaction”中
//			2.    当且仅当“预付”类型的产品时，将阿里订单中的“TotalPrice”节点值赋值到SWITCH协议的UDF字段的“Prepaid Amount”
//			3.    SWITCH协议的UDF字段的“Booking Source”默认赋值= CHANNEL CODE
//		 * */
//		String pt = wbeOrderVO.getPayment();
//		if(GuaranteeCode.TAGTD.equalsIgnoreCase(pt)||GuaranteeCode.TAGTD_GT.equalsIgnoreCase(pt)){
			UserDefinedValue udf = new UserDefinedValue();
			udf.setValueName("Create By");
			udf.setCharacterValue(wbeOrderVO.getCreateUDFBy());
//			
//			UserDefinedValue udfAmount = new UserDefinedValue();
//			udfAmount.setValueName("Prepaid Amount");
//			udfAmount.setNumericValue(totalPrice);
//			
//			UserDefinedValue udfSource = new UserDefinedValue();
//			udfSource.setValueName("Booking Source");
//			udfSource.setCharacterValue(wbeOrderVO.getChannelCode());
//			
			UserDefinedValue[] udfs = new UserDefinedValue[1];
			udfs[0] = udf;
//			udfs[1] = udfAmount;
//			udfs[2] = udfSource;
			hotelReservation.setUserDefinedValues(udfs);
//		}
		
		// 最早与最晚到店时间
		checkInPolicy.setEarliestTime(DateUtil.getTimeNow(wbeOrderVO.getWbeGuestVO().getArr()));
//		checkInPolicy.setLatestTime(DateUtil.getTimeNow(orderResVo.getLatestArriveTime()));

		String ows_timeout = ProjectConfigConstant.ows_timeout;
		String ows_transactionID = ProjectConfigConstant.ows_transactionID;
		
		// 报文头信息
		OGHeader oGHeader = new OGHeader();
		oGHeader.setTransactionID(ows_transactionID);

		EndPoint origin = new EndPoint();
		origin.setEntityID(wbeOrderVO.getChannelCode());
		origin.setSystemType("WEB");
		oGHeader.setOrigin(origin);

		EndPoint destination = new EndPoint();
		destination.setEntityID("ORS");
		destination.setSystemType("ORS");
		oGHeader.setDestination(destination);

		SOAPHeaderElement el = new SOAPHeaderElement(new QName("http://webservices.micros.com/og/4.3/Core/", "OGHeader"), oGHeader);
		// 建立调用stup
		ReservationServiceLocator wsLocator = new ReservationServiceLocator();
		wsLocator.setReservationServiceSoapEndpointAddress(ProjectConfigConstant.ows_reservation_wsdl);
		ReservationServiceSoapStub binding = (ReservationServiceSoapStub) wsLocator.getReservationServiceSoap();
		binding.setTimeout(Integer.parseInt(ows_timeout));
		binding.setHeader(el);
		return binding;
	}

	private void addGuest(WbeOrderVO wbeOrderVO, HotelReservation hotelReservation) {
		// 预订人信息
		ResGuest resGuest = new ResGuest();

		WbeGuestVO wbeGuestVO = wbeOrderVO.getWbeGuestVO();

		Profile profile = new Profile();
		// profile. 客人
		Customer customer = new Customer();

		customer.setGender(Gender.MALE);

		PersonName personName = new PersonName();

		String[] nameTitle = new String[] { "Mr." };
		personName.setNameTitle(nameTitle);
		String name1 = wbeGuestVO.getName1().replaceAll(" ", "");
		personName.setLastName(name1+" "+wbeGuestVO.getName2());
		if(StringUtils.hasLength(wbeGuestVO.getName3())){
			personName.setFirstName(wbeGuestVO.getName3());
		}else{
			personName.setFirstName(name1+","+wbeGuestVO.getName2());
		}

		customer.setPersonName(personName);
		profile.setCustomer(customer);

		// 客人联系方式
		NamePhone namePhone = new NamePhone();
		namePhone.setPhoneRole("PHONE");
		namePhone.setPhoneType("MOBILE");
		namePhone.setPhoneNumber(wbeGuestVO.getTel());

		NamePhone namePhone2 = new NamePhone();
		namePhone2.setPhoneRole("EMAIL");
		namePhone2.setPhoneType("EMAIL");
		namePhone2.setPhoneNumber("");

		NamePhone[] phones = { namePhone, namePhone2 };

		profile.setPhones(phones);

		// Memberships
//		NameMembership membership = new NameMembership();
//		membership.setMembershipNumber("");
//		NameMembership[] memberships = { membership };
//		profile.setMemberships(memberships);

		// NameAdress
		NameAddress na = new NameAddress();
		na.setAddressType("");
		na.setAddressLine(new String[] { "" });
		na.setCityName("");
		na.setStateProv("");
		na.setCountryCode("");
		na.setPostalCode("");
		NameAddress[] nas = { na };
		profile.setAddresses(nas);

//		 Booker
//		if (StringUtils.hasText(orderResVo.getBookerMemberName()) || StringUtils.hasText(orderResVo.getBookerMemberCardNo()) || StringUtils.hasText(orderResVo.getBookerMemberPhone())) {
//			BookerEntity booker = new BookerEntity();
//			booker.setName(orderResVo.getBookerMemberName());
//			booker.setMemberNumber(orderResVo.getBookerMemberCardNo());
//			booker.setMobile(orderResVo.getBookerMemberPhone());
//			resGuest.setBooker(booker);
//		}

		Profile[] profiles = { profile };
		resGuest.setProfiles(profiles);
		if(wbeGuestVO.getArr()!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(wbeGuestVO.getArr());
			Time time = new Time(calendar);
			resGuest.setArrivalTime(time);
		}

		ResGuest[] resGuests = { resGuest };

		hotelReservation.setResGuests(resGuests);
	}
	
}
