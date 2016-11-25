/**
 * 
 */
package com.ccm.api.service.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.constant.RemindStatus;
import com.ccm.api.model.constant.Sex;
import com.ccm.api.model.enume.OXIStatusEnum;
import com.ccm.api.model.enume.OperUserType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterOrder;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.log.ReceiveMsgLogManager;
import com.ccm.api.service.log.SendMsgLogManager;
import com.ccm.api.service.order.OXIReservationNodesService;
import com.ccm.api.service.order.OrderChangesLogManager;
import com.ccm.api.service.order.ReservationFromPmsService;
import com.ccm.api.service.order.ReservationService;
import com.ccm.api.service.system.Push2ChannelManager;
import com.ccm.api.service.system.PushManage;
import com.ccm.api.util.AmountUtil;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.DateUtils;
import com.ccm.api.util.IncrementService;
import com.ccm.api.util.OwsOxiUtil;
import com.ccm.api.util.RSAEncrypt;
import com.ccm.api.util.XMLUtil;
import com.ccm.oxi.profile.CreditCard;
import com.ccm.oxi.profile.Document;
import com.ccm.oxi.profile.Profile;
import com.ccm.oxi.reservation.GuaranteeInfo;
import com.ccm.oxi.reservation.GuestCount;
import com.ccm.oxi.reservation.GuestCounts;
import com.ccm.oxi.reservation.PaymentInstructions;
import com.ccm.oxi.reservation.Rate;
import com.ccm.oxi.reservation.RatePlans;
import com.ccm.oxi.reservation.Rates;
import com.ccm.oxi.reservation.ResComments;
import com.ccm.oxi.reservation.ResCreditCards;
import com.ccm.oxi.reservation.ResProfiles;
import com.ccm.oxi.reservation.Reservation;
import com.ccm.oxi.reservation.RoomStays;
import com.ccm.oxi.reservation.StayDateRange;
import com.ccm.oxi.result.RESULT;
import com.ccm.oxi.result.ResultId;
import com.ccm.oxi.result.ResultIds;
import com.ccm.oxi.result.ResultMsgType;
import com.ccm.oxi.result.Success;
import com.ccm.oxi.udf.Udf;
import com.ccm.reservation.AgeQualifyingCode;
import com.ccm.reservation.ReservationStatusType;

/**
 * @author Jenny
 * 
 */
@Service("reservationFromPmsService")
public class ReservationFromPmsServiceImpl implements ReservationFromPmsService {

	private Log log = LogFactory.getLog("reservation");

	@Resource
	private MasterDao masterDao;
	@Resource
	private HotelDao hotelDao;

	@Autowired
	private SendMsgLogManager sendMsgLogManager;

	@Autowired
	private ReceiveMsgLogManager receiveMsgLogManager;

	@Autowired
	private DictCodeManager dictCodeManager;

	@Resource
	private RateplanDao rateplanDao;
	@Resource
	private RoomTypeDao roomTypeDao;
	@Autowired
	private PushManage pushManage;
	@Resource
	private Push2ChannelManager push2ChannelManager;
	@Resource
	private OrderChangesLogManager orderChangesLogManager;
	@Resource
	private ReservationService reservationService;
	@Resource
	private OXIReservationNodesService oXIReservationNodesService;

	/**
	 * 接收PMS POST过来的XML格式的Reservation来修改系统中的订单 字段如下：
	 * 
	 * sta,arr,dep,gstno,child,ref,name ,name2,nameTitle,name4,sex,restype,type ,roomno,ratePlanCode,masterRate,charge ,payment,cardCode,cardHolderName,cardNumber,expirationDate,updateBy
	 */
	public void pmsChangeOrder(Map<String, String> mapReq) throws Exception {
		log.info("pmsChangeOrder ...");
		// 针对西软PMS无命名空间的情况
		String xml = mapReq.get("Message");
		if (!StringUtils.hasText(xml)) {
			saveReserveResult(mapReq, Success.FAIL, null, "XmlIsEmpty", null);
			return;
		}
		if (xml.contains("xmlns=")) {
			xml = xml.replace("xmlns=", "xmlns:ns2=");
			log.info("no namespace");
		} else {
			log.info("has namespace");
		}

		Reservation r = (Reservation) XMLUtil.JAXBParseToBean(xml, Reservation.class, mapReq.get("charsetName"));
		String reservationId = r.getReservationID();
		String hotelCode = mapReq.get("propertyName");

		if (!StringUtils.hasText(reservationId) || !StringUtils.hasText(hotelCode)) {
			saveReserveResult(mapReq, Success.FAIL, r.getConfirmationID(), "REQUIRED,update fail!", reservationId);
			return;
		}

		// 获取订单
		Master order = null;
		List<Master> mList = masterDao.getMasterOrderByPmsIdHotelCode(reservationId, hotelCode);
		if (mList != null && mList.size() > 0) {
			if (mList.size() == 1) {
				order = mList.get(0);
				order.setCardNumber(RSAEncrypt.decrypt(order.getCardNumber()));
			} else {
				saveReserveResult(mapReq, Success.FAIL, r.getConfirmationID(), "HotelPmsIdDuplicate,update fail!", reservationId);
				return;
			}
		}
		if (order == null) {
			saveReserveResult(mapReq, Success.FAIL, r.getConfirmationID(), "ConfirmationID ISNOTEXIST,update fail!", reservationId);
			return;
		}
		String confirmId = order.getMasterId();
		String hotelId = order.getHotelId();

		// 订单状态
		String oldOrderSta = order.getSta();
		String newOrderStatus = getPms2CcmCodeMap(OXIConstant.orderStatus, hotelId, r.getMfReservationAction());
		if (newOrderStatus == null) {
			saveReserveResult(mapReq, Success.FAIL, confirmId, "MfReservationAction WRONG,update fail!", reservationId);
			return;
		}
		if ((OrderStatus.CANCEL.equals(oldOrderSta) || OrderStatus.HARDCANCEL.equals(oldOrderSta)) && (OrderStatus.EDIT.equals(newOrderStatus) || OrderStatus.CANCEL.equals(newOrderStatus) || OrderStatus.CHECKIN.equals(newOrderStatus) || OrderStatus.CHECKOUT.equals(newOrderStatus) || OrderStatus.NOSHOW.equals(newOrderStatus))) {
			saveReserveResult(mapReq, Success.FAIL, confirmId, "ReservationAction=\"CANCEL\"，can't update", reservationId);
			return;
		}
		order.setSta(newOrderStatus);
		log.info("order Status： " + oldOrderSta + " ==> " + newOrderStatus);
		// 房间数量
		if (r.getMfImage() != null && r.getMfImage().getNumRooms() != null && r.getMfImage().getNumRooms().intValue() != order.getNumberOfUnits().intValue()) {
			saveReserveResult(mapReq, Success.FAIL, confirmId, "MfImage NumRooms Can not be modified!", reservationId);
			return;
		}
		// 更新原订单信息
		order.setOcheckinDate(order.getArr());
		order.setOcheckoutDate(order.getDep());
		order.setOnumberOfRooms(order.getNumberOfUnits());
		order.setOtype(order.getType());
		order.setOchainCode(order.getChainCode());
		order.setOhotelCode(order.getHotelCode());
		order.setOchannel(order.getChannel());
		order.setOratePlanCode(order.getRatePlanCode());
		order.setOgstno(order.getGstno());
		order.setOchild(order.getChild());
		// 入住离店时间
		StayDateRange sdr = r.getStayDateRange();
		if (sdr != null && sdr.getStartTime() != null) {
			order.setArr(DateUtil.getCleanDate(DateUtils.xmlDate2Date(sdr.getStartTime())));
			order.setDep(DateUtil.addDays(order.getArr(), sdr.getNumberOfTimeUnits()));
		}
		// 成人小孩数量
		GuestCounts gcs = r.getGuestCounts();
		if (gcs != null) {
			Integer adult = null;
			int child = 0;
			for (GuestCount gc : gcs.getGuestCount()) {
				if (AgeQualifyingCode.ADULT.value().equals(gc.getAgeQualifyingCode())) {
					adult = gc.getMfCount();
				} else if (AgeQualifyingCode.CHILD.value().equals(gc.getAgeQualifyingCode())) {
					child = gc.getMfCount();
				}
			}
			if (adult != null) {
				order.setGstno(adult + child);
				order.setChild(child);
			}
		}
		// 备注
		ResComments rcs = r.getResComments();
		if (rcs != null && rcs.getResComment().size() > 0 && StringUtils.hasText(rcs.getResComment().get(0).getComment())) {
			order.setRef(rcs.getResComment().get(0).getComment());
		}

		Success resultFlag = Success.SUCCESS;
		String resultMsg = "update success!";
		String docType = null;
		String docNumber = null;

		// 个人信息
		ResProfiles rps = r.getResProfiles();
		if (rps != null && rps.getResProfile().size() > 0) {
			Profile p = rps.getResProfile().get(0).getProfile();
			if (p != null) {
				if (p.getIndividualName() != null) {
					order.setName(p.getIndividualName().getNameFirst());
					order.setName2(p.getIndividualName().getNameSur());
					order.setNameTitle(p.getIndividualName().getNameTitle());
					String zhName = "";
					if (p.getMfNationalName() != null) {
						zhName = p.getMfNationalName().getNameLast();
					}
					order.setName4(zhName);
				}
				// 性别 如酒店上传预订，OXI客史节点缺少gender，赋予默认值0
				String sex = getPms2CcmCodeMap(OXIConstant.gender, hotelId, p.getGender());
				if (sex == null) {
					sex = Sex.MALE;
				}
				order.setSex(sex);
				// 身份证
				if (p.getDocuments() != null) {
					Hotel h = null;
					for (Document document : p.getDocuments().getDocument()) {
						docType = document.getDocumentCode();
						docNumber = document.getDocNumber();
						try {
							if (StringUtils.hasText(docType) && StringUtils.hasText(docNumber) && !docNumber.equals(order.getIdent())) {
								// 控制只查询一次酒店表
								if (h == null) {
									h = hotelDao.getHotel(order.getHotelId(), "isUpdateIdent");
									if (h == null) {
										break;
									}
								}
								if (h.getIsUpdateIdent() != null && h.getIsUpdateIdent()) {
									int len = docNumber.length();
									if (len > 6) {
										docNumber = docNumber.substring(len - 6);
									}
									order.setIdcls(docType);
									order.setIdent(docNumber);
									push2ChannelManager.pushReservation(order);
								}
							}
						} catch (Exception e) {
							log.error(CommonUtil.getExceptionMsg(e, "ccm"));
						}
					}
				}
			}
		}
		// 房型信息
		RoomStays rss = r.getRoomStays();
		if (rss != null && rss.getRoomStay().size() > 0) {
			// 预订类型状态，若先查询渠道与CCM映射，没有则查询PMS与CCM的映射，没有则取PMS上传的值
			log.info(order.getSta() + ",ResType:" + order.getRestype());
			Map<String, String> codeMap = dictCodeManager.searchCodeMapByChannelHotel(OXIConstant.orderStatus, order.getChannelId(), null, true);
			String externalValue = codeMap.get(order.getSta());
			log.info("externalValue:" + externalValue);
			if (externalValue != null) {
				order.setRestype(externalValue);
			} else {
				String resType = getPms2CcmCodeMap(OXIConstant.reservationStatusType, hotelId, rss.getRoomStay().get(0).getReservationStatusType());
				if (resType != null) {
					order.setRestype(resType);
				} else {
					saveReserveResult(mapReq, Success.FAIL, confirmId, "ReservationStatusType WRONG,update fail!", reservationId);
					return;
				}
			}
			// 房型代码:根据PMS的值从转换代码查映射值，若找不到再从房型表中查找，若还是找不到则设置默认值
			String pmsRoomTypeCode = rss.getRoomStay().get(0).getRoomInventoryCode();
			if (StringUtils.hasText(pmsRoomTypeCode)) {
				String type = getPms2CcmCodeMapP(OXIConstant.roomTypeCode, hotelId, pmsRoomTypeCode);
				String roomTypeId = null;
				if (type == null) {
					RoomType rt = new RoomType();
					rt.setHotelId(order.getHotelId());
					rt.setRoomTypeCode(pmsRoomTypeCode);
					rt = roomTypeDao.getRoomTypeByObj(rt);
					if (rt == null) {
						type = OXIConstant.roomTypeCodeDefault;
					} else {
						type = pmsRoomTypeCode;
						roomTypeId = rt.getRoomTypeId();

					}
				}
				if (roomTypeId == null) {
					RoomType rt = new RoomType();
					rt.setHotelId(order.getHotelId());
					rt.setRoomTypeCode(type);
					rt = roomTypeDao.getRoomTypeByObj(rt);
					if (rt != null) {
						log.info("changeMasterRoomTypeId");
						order.setRoomTypeId(roomTypeId);
					}
				} else {
					log.info("changeMasterRoomTypeId");
					order.setRoomTypeId(roomTypeId);
				}
				order.setType(type);
			}
			// 房间号
			String roomId = rss.getRoomStay().get(0).getRoomID();
			if (StringUtils.hasText(roomId)) {
				order.setRoomno(roomId);
			}
			// 房价代码:根据PMS的值从转换代码查映射值，若找不到再从房价表中查找，若还是找不到则设置默认值
			RatePlans ratePs = rss.getRoomStay().get(0).getRatePlans();
			if (ratePs != null && ratePs.getRatePlan() != null && ratePs.getRatePlan().size() > 0) {
				// 房价代码
				String pmsRatePlanCode = ratePs.getRatePlan().get(0).getRatePlanCode();
				if (StringUtils.hasText(pmsRatePlanCode)) {
					String ratePlanCode = getPms2CcmCodeMapP(OXIConstant.ratePlanCode, hotelId, pmsRatePlanCode);
					String ratePlanId = null;
					if (ratePlanCode == null) {
						Rateplan rate = new Rateplan();
						rate.setHotelId(hotelId);
						rate.setRatePlanCode(pmsRatePlanCode);
						Rateplan rateP = rateplanDao.getRatePlanByObj(rate);
						if (rateP == null) {
							ratePlanCode = OXIConstant.ratePlanCodeDefault;
						} else {
							ratePlanCode = pmsRatePlanCode;
							ratePlanId = rateP.getRatePlanId();
						}
					}
					if (ratePlanId == null) {
						Rateplan rate = new Rateplan();
						rate.setHotelId(hotelId);
						rate.setRatePlanCode(ratePlanCode);
						Rateplan rateP = rateplanDao.getRatePlanByObj(rate);
						if (rateP != null) {
							order.setRatePlanId(rateP.getRatePlanId());
						}
					} else {
						order.setRatePlanId(ratePlanId);
					}
					order.setRatePlanCode(ratePlanCode);
				}
				// 每日房价详情与总房价
				Rates rates = ratePs.getRatePlan().get(0).getRates();
				if (rates != null && rates.getRate().size() > 0) {
					BigDecimal total = BigDecimal.ZERO;
					List<MasterRate> mrList = new ArrayList<MasterRate>();
					Map<String, MasterRate> mrMap = new HashMap<String, MasterRate>();
					for (Rate rate : rates.getRate()) {

						XMLGregorianCalendar arr = rate.getTimeSpan().getStartTime();
						if (arr == null) {
							continue;
						}

						Date date = DateUtils.xmlDate2Date(arr);
						total = getPmsDistinctMasterRate(total, mrMap, mrList, hotelId, confirmId, date, rate.getAmount().getValueNum());

						int len = rate.getTimeSpan().getNumberOfTimeUnits().intValue();
						if (len > 1) {
							for (int i = 1; i < len; i++) {
								total = getPmsDistinctMasterRate(total, mrMap, mrList, hotelId, confirmId, DateUtil.addDays(date, i), rate.getAmount().getValueNum());
							}
						}
					}
					order.setMrList(mrList);
					if (!total.equals(BigDecimal.ZERO)) {
						order.setCharge(AmountUtil.add(AmountUtil.reduce(order.getCharge(), order.getSetrate()), total));
						order.setSetrate(total);
					}
				}
			}
			// 担保类型
			GuaranteeInfo gi = rss.getRoomStay().get(0).getGuaranteeInfo();
			if (gi != null) {
				String payment = getPms2CcmCodeMap(OXIConstant.guaranteeType, hotelId, gi.getMfGuaranteeType());
				if (payment != null) {
					order.setPayment(payment);
				} else {
					resultFlag = Success.FAIL;
					resultMsg = "MfGuaranteeType WRONG!";
				}
			}
			// 支付方式
			PaymentInstructions pis = rss.getRoomStay().get(0).getPaymentInstructions();
			if (pis != null && pis.getPaymentInstruction().size() > 0) {
				String cardCode = getPms2CcmCodeMap(OXIConstant.paymentMethod, hotelId, pis.getPaymentInstruction().get(0).getMfPaymentMethod());
				if (cardCode != null) {
					order.setCardCode(cardCode);
				} else {
					resultFlag = Success.FAIL;
					resultMsg = "MfPaymentMethod WRONG!";
				}
			}
		} else {
			saveReserveResult(mapReq, Success.FAIL, confirmId, "ReservationStatusType WRONG,update fail!", reservationId);
			return;
		}
		if (OrderStatus.ADD.equals(newOrderStatus) || OrderStatus.EDIT.equals(newOrderStatus)) {
			// 信用卡
			ResCreditCards rccs = r.getResCreditCards();
			if (rccs != null && rccs.getResCreditCard().size() > 0) {
				CreditCard cc = rccs.getResCreditCard().get(0).getCreditCard();
				if (cc != null) {
					String cardCode = getPms2CcmCodeMap(OXIConstant.paymentMethod, hotelId, cc.getCreditCardCode());
					if (cardCode != null) {
						order.setCardCode(cardCode);
					} else {
						resultFlag = Success.FAIL;
						resultMsg = "CreditCardCode WRONG!";
					}

					order.setCardHolderName(cc.getCreditCardHolderName());
					order.setCardNumber(cc.getCreditCardNumber());
					if (cc.getCreditCardExpire() != null) {
						order.setExpirationDate(DateUtils.xmlDate2Date(cc.getCreditCardExpire()));
					}
				}
			}
		}
		// Udfs pmsColumnName数据转换不准确，无需更新UDF内容，但可更新订单其他内容，
		// 并给出respone：FAIL:can't update UDF
		if (r.getUdfs() != null) {
			for (Udf udf : r.getUdfs().getUdf()) {
				if (udf.getUdfDefinition() != null && StringUtils.hasText(udf.getUdfDefinition().getPmsColumnName())) {
					String pmsColumnName = getPms2CcmCodeMap(OXIConstant.pmsColumnName, hotelId, udf.getUdfDefinition().getPmsColumnName());
					if (pmsColumnName != null) {
						order.setInvoiceTitle(udf.getUdfValue());
					} else {
						resultFlag = Success.FAIL;
						resultMsg = "can't update UDF!";
					}
				}
			}
		}
		// 修改人
		String operUser = r.getReservationOriginatorCode();
		if (!StringUtils.hasText(operUser)) {
			operUser = OperUserType.PMS.value();
		}
		order.setUpdatedBy(operUser);
		try {
			// 更新master,master_order,master_rate三张表
			order.setOsta(RemindStatus.order_modify);
			masterDao.updateMaster(order);
			order.setLastModifyTime(new Date());
			masterDao.updateMasterOrder(order);
			// 若房价明细为空则不更新
			if (order.getMrList() != null && !order.getMrList().isEmpty()) {
				String orderId = order.getMasterId();
				masterDao.deleteMasterRateByOrderId(orderId);
				for (MasterRate mr : order.getMrList()) {
					mr.setMasterId(orderId);
					masterDao.saveMasterRate(mr);
				}
			}
			orderChangesLogManager.saveMasterChangesLog(order);
			log.info("stayHistoryNotification start1...");
			if (!oldOrderSta.equals(newOrderStatus)) {
				log.info("stayHistoryNotification start2...");
				pushManage.stayHistoryNotification(order);
				log.info("stayHistoryNotification end...");
			}
		} catch (Exception e) {
			resultFlag = Success.FAIL;
			resultMsg = e.getMessage();
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		saveReserveResult(mapReq, resultFlag, confirmId, resultMsg, reservationId);
	}

	/**
	 * 处理PMS POST过来的XML格式的Result
	 * 
	 * @throws Exception
	 */
	public void dealExtSysResult(String hotelCode, String transactionId, String message, String charsetName) throws Exception {

		ReceiveMsgLog rml = new ReceiveMsgLog();
		rml.setReceiveMsgLogId(CommonUtil.generatePrimaryKey());
		rml.setInterfaceId(OXIConstant.Interface);
		rml.setHotelCode(hotelCode);
		rml.setMessageType(MessageType.RESULT);
		rml.setBeginMsgId(transactionId);

		// 通过msgId查询系统推送的订单日志
		SendMsgLog cond = new SendMsgLog();
		cond.setMsgId(transactionId);
		cond.setHotelCode(hotelCode);
		SendMsgLog sml = sendMsgLogManager.getOrderQueueByCondition(cond);

		// 处理订单PMS订单号
		Success status = dealReservationResultOfPms(message, charsetName, rml, sml);

		if (sml != null) {
			// 根据PMS返回更新系统推送的订单日志的状态
			if (status.equals(Success.SUCCESS) || status.equals(Success.SUCCESSINFO)) {
				sml.setStatus(OXIStatusEnum.SEND_RESULT_SUCCESS.getValue());
			} else if (status.equals(Success.REQUEUE)) {
				sml.setStatus(OXIStatusEnum.SEND_REQUEUED.getValue());
			} else {
				sml.setStatus(OXIStatusEnum.SEND_RESULT_FAIL.getValue());
			}
			sendMsgLogManager.saveLogRemoveOrderQueue(sml);
		}

		rml.setStatus(status.name());
		rml.setProcessStatus(OXIStatusEnum.SRP_PROCESSED.getValue());
		rml.setProcessTime(DateUtil.currentDateTime());
		rml.setMessage(message);

		// 保存接收订单日志
		receiveMsgLogManager.addReceiveMsgLog(rml);
	}

	private Success dealReservationResultOfPms(String message, String charsetName, ReceiveMsgLog rml, SendMsgLog sml) throws Exception {
		Success status = Success.FAIL;
		if (!StringUtils.hasText(message)) {
			return status;
		}
		message = message.replaceAll("result.fidelio.[2-9].0", "result.fidelio.1.0");
		RESULT r = (RESULT) XMLUtil.JAXBParseToBean(message, RESULT.class, charsetName);
		if (r == null) {
			return status;
		}

		String action = null;
		String sendMsgMasterId = null;
		if (sml != null) {
			action = sml.getAction();
			sendMsgMasterId = sml.getOrderId();
			log.info("srcMasterId:" + sendMsgMasterId + " srcOrderSendMsgLogAction:" + action);
		}
		if (r.getSuccess() != null) {
			status = r.getSuccess();
			// 订单下传取消订单请求失败且该订单有“入帐信息”时，生成一条UPDATE
			// RESERVATION消息，消息中带有ALERTS类型的备注，内容为”Channel Cancelled Booking,
			// Need Refund XXX." Refund后的金额根据取消规则计算是”全额“或”全额-罚金“
			if (status.value().equals(Success.FAIL.value()) && OrderStatus.CANCEL.equals(action) && StringUtils.hasText(sendMsgMasterId)) {
				Master master = masterDao.getMasterOrderByOrderId(sendMsgMasterId);
				// 有入帐信息
				if (master != null && StringUtils.hasText(master.getAccountMsgId())) {
					// 生成UPDATE RESERVATION消息
					master.setSta(OrderStatus.EDIT);
					master.setRestype(ReservationStatusType.RESERVED.value());
					if (master.getDeduct() == null) {
						master.setDeduct(BigDecimal.ZERO);
					}
					master.setAlerts("此订单在阿里平台已取消，第一晚房费已扣计入罚金，第一晚之后的房费已自动退款，如需入住，第一晚无须再次收费。");
					if (StringUtils.hasText(master.getTraceDept())) {
						master.setTraces("CANCEL FAILED DUE TO DEPOSIT NEED TO BALANCE, PLEASE CHECK IF NEED TO CHARGE CANCELLLATION FEE");
					}
					reservationService.buildReservation(master);
				}
			}
		}
		rml.setExtMsgId(r.getRequestMsgId());

		ResultIds rs = r.getResultIds();
		if (rs == null) {
			return status;
		}

		for (ResultId ri : rs.getResultId()) {
			if (ri.getResultMsgType() == null || !ResultMsgType.RESERVATION.name().equals(ri.getResultMsgType().name())) {
				continue;
			}
			rml.setExtOrderId(ri.getPmsId());
			if (StringUtils.hasText(ri.getCrsId()) && StringUtils.hasText(rml.getExtOrderId())) {
				String masterId = ri.getCrsId();
				if (masterId.endsWith(".1")) {
					masterId = masterId.substring(0, masterId.lastIndexOf(".1"));
				}
				log.info("save reservation pmsId:" + rml.getExtOrderId() + " masterId:" + masterId);
				if (status.value().equals(Success.SUCCESS.value()) && OrderStatus.CANCEL.equals(action)) {
					// 订单取消成功，修改master表中的cancelPmsId
					String cancelPmsId = ri.getPmsId();
					masterDao.updateCancelPmsId(masterId, cancelPmsId);
				}
				if (action == null || OrderStatus.ADD.equals(action)) {

					// 如果PMS返回的是添加订单的结果则添加PMSID
					Master master = masterDao.getMasterByOrderId(masterId);
					if (master == null) {
						continue;
					}

					/*
					 * 渠道新建订单成功并收到PMS上传订单号后， 根据订单所带客户信息中是否 ”AUTO DEPOSIT“为"TRUE”时，生成FINTRX消息，
					 * 
					 * 按订单房费总额及客户列表中的“入帐代码”生成OXI消息 （注意一个订单只能生成一次入帐信息, 在订单表中，加一个FIELD，记录生成的入信息的消息号，有消息号的订单不再重复生成入帐消息）
					 */
					if (master.getSta().equalsIgnoreCase(OrderStatus.HARDCANCEL)==false && master.getSta().equalsIgnoreCase(OrderStatus.CANCEL)==false && master.getAutoDeposit() != null && master.getAutoDeposit() && !StringUtils.hasText(master.getAccountMsgId())) {
						// 生成FINTRX消息
						master.setHotelCode(rml.getHotelCode());
						master.setPmsId(rml.getExtOrderId());
						String msgId = oXIReservationNodesService.buildFintrxMsg(master, master.getCharge() + "", rml.getBeginMsgId());
						// 记录生成的入帐消息号
						master.setAccountMsgId(msgId);
						masterDao.updateAccountMsgId(master);
					}
					// 保存PMS订单号
					MasterOrder mo = new MasterOrder();
					mo.setMasterId(masterId);
					mo.setPmsId(rml.getExtOrderId());
					mo.setUpdatedBy(OperUserType.PMS.value());
					masterDao.updateMasterOrderPmsId(mo);
					orderChangesLogManager.saveMasterChangesLog(mo);
				}
			}
		}
		return status;
	}

	/**
	 * 保存订单到队列表等GET再返回给PMS
	 * 
	 * @param msgId
	 * @param hotelCode
	 * @param status
	 * @param linkedMsgId
	 * @param confirmId
	 * @throws Exception
	 */
	private void saveReserveResult(Map<String, String> mapReq, Success status, String confirmId, String resultMsg, String reservationId) throws Exception {
		String hotelCode = mapReq.get("propertyName");
		String linkedMsgId = mapReq.get("transactionId");
		RESULT result = new RESULT();
		result.setResortId(hotelCode);
		result.setSuccess(status);
		result.setResultMessage(resultMsg);
		result.setTimeStamp(DateUtils.dateToXmlDate(new Date()));
		ResultId resultId = new ResultId();
		resultId.setResultMsgType(ResultMsgType.RESERVATION);
		resultId.setCrsId(confirmId);
		resultId.setPmsId(reservationId);
		resultId.setLegNumbers("1");
		ResultIds resultIds = new ResultIds();
		resultIds.getResultId().add(resultId);
		result.setResultIds(resultIds);
		String message = XMLUtil.JAXBParseToXmlNoSA(result, OwsOxiUtil.getReservationLabel(hotelCode, linkedMsgId, MessageType.RESULT));

		SendMsgLog log = new SendMsgLog();
		log.setInterfaceId(OXIConstant.Interface);
		log.setHotelCode(hotelCode);
		log.setMessageType(MessageType.RESULT);
		log.setMsgId(IncrementService.msgId(hotelCode));
		log.setStatus(OXIStatusEnum.SEND_READY.getValue());
		log.setMessage(message);
		log.setLinkedMsgId(linkedMsgId);
		log.setAction(MessageType.RESERVATION);
		log.setOrderId(confirmId);
		sendMsgLogManager.saveSendMsgLogUpdateOrder(log);

		ReceiveMsgLog rml = new ReceiveMsgLog();
		rml.setReceiveMsgLogId(mapReq.get("receiveMsgLogId"));
		rml.setHotelCode(hotelCode);
		rml.setExtOrderId(reservationId);// PMS的订单号
		rml.setStatus(status.name());
		rml.setUpdatedBy(OXIConstant.creatorCode);
		receiveMsgLogManager.updateReceiveMsgLog(rml);

	}

	/**
	 * 根据pmsValue获取转换代码映射的值,若找不到则返回null
	 * 
	 * @param dictName
	 * @param hotelId
	 * @param pmsValue
	 * @return
	 */
	private String getPms2CcmCodeMapP(String dictName, String hotelId, String pmsValue) {
		Map<String, String> pms2ccmMap = dictCodeManager.searchCodeMapByHotelId(dictName, hotelId, false);
		String ccmValue = pms2ccmMap.get(pmsValue);
		log.info(pmsValue + "pms-ccm" + ccmValue);
		if (ccmValue != null) {
			return ccmValue;
		}
		return null;

	}

	/**
	 * 根据pmsValue获取转换代码映射的值，若找不到则从系统中找，找不到返回null
	 * 
	 * @param dictName
	 * @param hotelId
	 * @param pmsValue
	 * @return
	 */
	private String getPms2CcmCodeMap(String dictName, String hotelId, String pmsValue) {
		log.info(pmsValue);
		if (StringUtils.hasText(pmsValue)) {
			Map<String, String> pms2ccmMap = dictCodeManager.searchCodeMapByHotelId(dictName, hotelId, false);
			String ccmValue = pms2ccmMap.get(pmsValue);
			log.info(ccmValue);
			if (ccmValue != null) {
				return ccmValue;
			}
			if (dictCodeManager.searchByCodeNo(dictName, pmsValue) != null) {
				return pmsValue;
			}
		}
		return null;

	}

	private BigDecimal getPmsDistinctMasterRate(BigDecimal total, Map<String, MasterRate> mrMap, List<MasterRate> mrList, String hotelId, String masterId, Date date, BigDecimal setrate) {

		String dateStr = DateUtil.getDate(date);
		String key = masterId + dateStr;
		if (mrMap.get(key) == null) {
			List<MasterRate> mrDBList = masterDao.getMasterRate(masterId);
			for (MasterRate mr : mrDBList) {
				if (dateStr.equals(DateUtil.getDate(mr.getDate()))) {
					return buildMasterRate(key, mr, total, mrMap, mrList, hotelId, masterId, date, setrate);
				}
			}
			MasterRate mr = new MasterRate();
			return buildMasterRate(key, mr, total, mrMap, mrList, hotelId, masterId, date, setrate);
		} else {
			MasterRate rmSrc = mrMap.get(key);
			total = AmountUtil.add(AmountUtil.reduce(total, rmSrc.getSetrate()), setrate);
			rmSrc.setSetrate(setrate);
			log.info("distinctMaterRate:" + key + "&setRate:" + setrate + "&total:" + total);
		}
		return total;
	}

	private BigDecimal buildMasterRate(String key, MasterRate mr, BigDecimal total, Map<String, MasterRate> mrMap, List<MasterRate> mrList, String hotelId, String masterId, Date date, BigDecimal setrate) {
		mr.setHotelId(hotelId);
		mr.setMasterId(masterId);
		mr.setDate(date);
		mr.setSetrate(setrate);
		mr.setCby(OXIConstant.changeCode);
		mr.setChanged(new Date());
		mrList.add(mr);
		mrMap.put(key, mr);
		total = AmountUtil.add(total, mr.getSetrate());
		log.info("materRate:" + key + "&setRate:" + setrate + "&total:" + total);
		return total;
	}
}
