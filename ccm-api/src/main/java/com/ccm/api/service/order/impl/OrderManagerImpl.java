/**
 * 
 */
package com.ccm.api.service.order.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.SecurityHolder;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.hotel.CustomDao;
import com.ccm.api.dao.hotel.HotelSwitchDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.dao.redis.RedisDao;
import com.ccm.api.dao.sms.SmsSendDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.constant.ChannelCode;
import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.OWSConstant;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.constant.RemindStatus;
import com.ccm.api.model.enume.CardCode;
import com.ccm.api.model.enume.OperUserType;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterCancel;
import com.ccm.api.model.order.MasterOrder;
import com.ccm.api.model.order.MasterPackage;
import com.ccm.api.model.order.MasterProfile;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.order.vo.OrderEmailConfirmResult;
import com.ccm.api.model.order.vo.OrderSearchResult;
import com.ccm.api.model.order.vo.SearchOrderCriteria;
import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.rsvtype.RsvChangeInfo;
import com.ccm.api.model.rsvtype.vo.SearchOrderEmailConfirmCriteria;
import com.ccm.api.service.availability.AvailabilityManager;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.AsyncManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.CreditLimitManager;
import com.ccm.api.service.order.OXIReservationNodesService;
import com.ccm.api.service.order.OrderChangesLogManager;
import com.ccm.api.service.order.OrderManager;
import com.ccm.api.service.order.ReservationService;
import com.ccm.api.service.ratePlan.GuaranteePolicyManager;
import com.ccm.api.service.ratePlan.PackageManager;
import com.ccm.api.service.ratePlan.RateGuaranteeManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.util.AmountUtil;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.IncrementService;
import com.ccm.api.util.PinyinUtil;
import com.ccm.api.util.RSAEncrypt;
import com.ccm.oxi.reservation.ReservationStatusType;

/**
 * 
 *
 * @author Jenny Liao
 * 
 */
@Service("orderManager")
public class OrderManagerImpl extends GenericManagerImpl<Master, String> implements OrderManager {

	private Log log = LogFactory.getLog("reservation");

	private Map<String, String> channelOrderId = new HashMap<String, String>();

	@Autowired
	private MasterDao masterDao;
	@Autowired
	private SmsSendDao smsSendDao;

	@Resource
	private PackageManager packageManager;
	@Resource
	private AvailabilityManager availabilityManager;
	@Resource
	private ReservationService reservationService;
	@Resource
	private RateGuaranteeManager rateGuaranteeManager;
	@Resource
	private RsvtypeChannelManager rsvtypeChannelManager;
	@Resource
	private DictCodeManager dictCodeManager;
	@Resource
	private ChannelManager channelManager;
	@Resource
	private GuaranteePolicyManager guaranteePolicyManager;
	@Resource
	private OrderChangesLogManager orderChangesLogManager;
	@Resource
	private OXIReservationNodesService oXIReservationNodesService;
	@Autowired
	private RateCustomRelationshipDao rateCustomRelationshipDao;
	@Autowired
	private HotelSwitchDao hotelSwitchDao;
	@Autowired
	private RedisDao redisDao;
	@Resource
	private CustomDao customDao;
	@Resource
	private CreditLimitManager creditLimitManager;
	@Resource
	private AsyncManager asyncManager;
	/**
	 * 保存或修改OWS订单
	 */
	public String saveOrUpdateMasterOrderRate(Master m, List<Master> mList) throws Exception {

		String orderId = m.getMasterId();
		int rooms = m.getNumberOfUnits();

		// 更新
		if (StringUtils.hasText(orderId)) {

			if (m.getNumberOfUnits().intValue() != m.getOnumberOfRooms() || m.getArr().compareTo(m.getOcheckinDate()) != 0 || m.getDep().compareTo(m.getOcheckoutDate()) != 0 || !m.getType().equals(m.getOtype()) || !m.getRatePlanCode().equals(m.getOratePlanCode()) || !m.getHotelCode().equals(m.getOhotelCode()) || !m.getChannel().equals(m.getOchannel())) {
				updateRsvtypeChannelRooms(m);
			}
			m.setOsta(RemindStatus.order_modify);
			dealRestype(m);
			masterDao.updateMaster(m);
			m.setLastModifyTime(new Date());
			masterDao.updateMasterOrder(m);
			saveMasterRate(m);
		}
		// 创建
		else {
			updateRsvtypeChannelRooms(m);

			// 生成的订单号是否使用渠道订单号加四位渠道代码
			Channel c = channelManager.get(m.getChannelId());
			boolean isChannelOrderId = false;
			if (StringUtils.hasText(m.getCrsno()) && c != null && c.getIsChannelOrderId() != null && c.getIsChannelOrderId()) {
				String channelCode = m.getChannel();
				if (channelCode.length() >= 3) {
					channelCode = channelCode.substring(0, 3);
				}
				orderId = m.getCrsno() + channelCode;
				orderId = getChannelOrderId(orderId);
				isChannelOrderId = true;
			} else {
				orderId = IncrementService.orderId();
				m.setResno(orderId);// 自增订单号
			}
			m.setMasterId(orderId);

			// 注释拆单逻辑
			// ccm拆单,主订单的pcrec为0
			if (mList != null && rooms > 1) {
				// 创建主订单
				m.setPcrec("0");
				m.setCharge(m.getCharge().multiply(new BigDecimal(rooms)));
				m.setSetrate(m.getSetrate().multiply(new BigDecimal(rooms)));
				m.setRmrate(m.getRmrate().multiply(new BigDecimal(rooms)));
				m.setStaticPackage(m.getStaticPackage().multiply(new BigDecimal(rooms)));
				m.setChanged(new Date());
				dealRestype(m);
				masterDao.saveMaster(m);
				masterDao.saveMasterOrder(m);
				saveMasterRate(m);
				// 创建子订单
				StringBuffer sb = new StringBuffer();
				sb.append(orderId);
				for (int i = 0; i < rooms; i++) {
					Master master = mList.get(i);
					master.setHaccnt(IncrementService.profileId());
					master.setPcrec(orderId);
					String subMasterId;
					if (isChannelOrderId) {
						subMasterId = orderId + (i + 1);
					} else {
						subMasterId = IncrementService.orderId();
						master.setResno(subMasterId);
					}
					master.setMasterId(subMasterId);
					master.setChanged(new Date());
					dealRestype(master);
					masterDao.saveMaster(master);
					master.setNumberOfUnits(1);
					masterDao.saveMasterOrder(master);
					saveMasterRate(master);
					sb.append(",").append(subMasterId);
				}
				orderChangesLogManager.saveMasterChangesLog(m, mList);
				return sb.toString();
			} else {
				m.setChanged(new Date());
				dealRestype(m);
				masterDao.saveMaster(m);
				masterDao.saveMasterOrder(m);
				saveMasterRate(m);
			}
		}
		orderChangesLogManager.saveMasterChangesLog(m);
		creditLimitManager.increaseTotalRoomRev(m);
		if(m.getSendOccupyFreeSellAvai()){
			asyncManager.sendAllotNotificationEmail(m.getHotelId(),m.getChannel(),m.getMasterId());
		}
		return orderId;
	}

	/**	 * 保存或修改WBE订单信息,返回总订单号
	 */
	public String saveOrUpdateWBEOrder(Master m, List<Master> mList, Master dbMaster) throws Exception {

		String orderId = m.getMasterId();
		int nowRoomNum = m.getNumberOfUnits().intValue();
		String result;

		// 更新
		if (StringUtils.hasText(orderId) && dbMaster != null) {
			// 更新原子订单信息
			m.setOcheckinDate(dbMaster.getArr());
			m.setOcheckoutDate(dbMaster.getDep());
			m.setOnumberOfRooms(dbMaster.getNumberOfUnits());
			m.setOtype(dbMaster.getType());
			m.setOchainCode(dbMaster.getChainCode());
			m.setOhotelCode(dbMaster.getHotelCode());
			m.setOchannel(dbMaster.getChannel());
			m.setOratePlanCode(dbMaster.getRatePlanCode());
			m.setOgstno(dbMaster.getGstno());
			m.setOchild(dbMaster.getChild());
			// 添加子订单时
			if ("0".equals(dbMaster.getPcrec())) {
				// 更新主订单总金额
				dbMaster.setCharge(dbMaster.getCharge().add(m.getCharge()));
				dbMaster.setSetrate(dbMaster.getSetrate().add(m.getSetrate()));
				dbMaster.setRmrate(dbMaster.getRmrate().add(m.getRmrate()));
				dbMaster.setStaticPackage(m.getStaticPackage().add(m.getStaticPackage()));
				dbMaster.setNumberOfUnits(dbMaster.getNumberOfUnits() + nowRoomNum);
				dbMaster.setSta(OrderStatus.EDIT);
				dbMaster.setOsta(RemindStatus.order_modify);
				dealRestype(dbMaster);
				masterDao.updateMaster(dbMaster);

				updateRsvtypeChannelRooms(m);
				orderChangesLogManager.saveMasterChangesLog(dbMaster);
				// 创建子订单
				for (int i = 0; i < nowRoomNum; i++) {
					// for (int i = 0; i < 1; i++) {
					Master master = mList.get(i);
					master.setHaccnt(IncrementService.profileId());
					String masterId = IncrementService.orderId();
					master.setMasterId(masterId);
					master.setPcrec(orderId);
					master.setResno(masterId);
					master.setChanged(new Date());
					dealRestype(master);
					masterDao.saveMaster(master);
					master.setNumberOfUnits(dbMaster.getNumberOfUnits() + nowRoomNum);
					masterDao.saveMasterOrder(master);
					saveMasterRate(master);
					saveMasterProfile(master, i);
					orderChangesLogManager.saveMasterChangesLog(master);
					master.setCardNumber(RSAEncrypt.decrypt(master.getCardNumber()));
					reservationService.buildWBEOrder(master);
				}
				result = orderId;
			}
			// 修改子订单
			else {
				// 更新主订单总金额
				Master pMasgter = getOrderByOrderNo(dbMaster.getPcrec());
				pMasgter.setCharge(pMasgter.getCharge().subtract(dbMaster.getCharge()).add(m.getCharge()));
				pMasgter.setSetrate(pMasgter.getSetrate().subtract(dbMaster.getSetrate()).add(m.getSetrate()));
				pMasgter.setRmrate(pMasgter.getRmrate().subtract(dbMaster.getRmrate()).add(m.getRmrate()));
				pMasgter.setStaticPackage(m.getStaticPackage().subtract(dbMaster.getStaticPackage()).add(m.getStaticPackage()));
				pMasgter.setSta(OrderStatus.EDIT);
				pMasgter.setOsta(RemindStatus.order_modify);
				dealRestype(pMasgter);
				masterDao.updateMaster(pMasgter);
				orderChangesLogManager.saveMasterChangesLog(pMasgter);
				// 更新子订单
				m.setRef(mList.get(0).getRef());
				m.setSta(OrderStatus.EDIT);
				m.setHaccnt(dbMaster.getHaccnt());
				m.setMasterId(dbMaster.getMasterId());
				m.setPcrec(dbMaster.getPcrec());
				m.setResno(dbMaster.getResno());
				m.setOsta(RemindStatus.order_modify);
				dealRestype(m);
				masterDao.updateMaster(m);
				m.setLastModifyTime(new Date());
				m.setUpdatedBy(dbMaster.getCby());
				masterDao.updateMasterOrder(m);
				saveMasterRate(m);
				saveMasterProfile(m, 0);
				orderChangesLogManager.saveMasterChangesLog(m);
				result = dbMaster.getPcrec();
				m.setCardNumber(RSAEncrypt.decrypt(m.getCardNumber()));
				reservationService.buildWBEOrder(m);
			}
		}
		// 创建
		else {
			updateRsvtypeChannelRooms(m);

			// 创建主订单
			orderId = IncrementService.orderId();
			m.setMasterId(orderId);
			m.setResno(orderId);// 主订单必填项
			m.setPcrec("0");// 主订单的pcrec为0
			m.setChanged(new Date());
			dealRestype(m);
			masterDao.saveMaster(m);
			masterDao.saveMasterOrder(m);
			saveMasterRate(m);
			saveMasterProfile(m, null);
			orderChangesLogManager.saveMasterChangesLog(m);
			// 创建子订单
			for (int i = 0; i < nowRoomNum; i++) {
				// for (int i = 0; i < 1; i++) {
				Master master = mList.get(i);
				master.setHaccnt(IncrementService.profileId());
				String masterId = IncrementService.orderId();
				master.setMasterId(masterId);
				master.setPcrec(orderId);
				master.setResno(masterId);
				master.setChanged(new Date());
				dealRestype(master);
				masterDao.saveMaster(master);
				master.setNumberOfUnits(1);
				masterDao.saveMasterOrder(master);
				saveMasterRate(master);
				saveMasterProfile(master, i);
				orderChangesLogManager.saveMasterChangesLog(master);
				master.setCardNumber(RSAEncrypt.decrypt(master.getCardNumber()));
				reservationService.buildWBEOrder(master);
			}
			result = orderId;
		}
		
		creditLimitManager.increaseTotalRoomRev(m);
		if(m.getSendOccupyFreeSellAvai()){
			asyncManager.sendAllotNotificationEmail(m.getHotelId(),m.getChannel(),m.getMasterId());
		}
		return result;

	}

	public void updateMasterDealRestype(Master m) {
		dealRestype(m);
		masterDao.updateMaster(m);
	}

	/**
	 * 取消订单,修改master,master_order表及加回房量
	 */
	public void cancelOrder(Master m) {
		m.setOsta(RemindStatus.order_cancel);
		dealRestype(m);
		/*
		 * 设置默认值
		 */
		m.setCancelPmsId(RemindStatus.order_cancel);
		masterDao.updateMaster(m);
		masterDao.cancelOrderById(m);
		rsvtypeChannelManager.cancelRsvtypeChannelAilable(m.getType(), m.getHotelCode(), m.getArr(), m.getDep(), m.getChannel(), m.getNumberOfUnits(), m.getRatePlanCode(), DateUtil.currentDate());
		creditLimitManager.increaseTotalRoomRev(m);
	}

	public void updateMasterOrderPmsId(MasterOrder mo) {
		String userName = SecurityHolder.getUserName();
		if (StringUtils.hasText(userName)) {
			mo.setUpdatedBy(userName);
		}
		masterDao.updateMasterOrderPmsId(mo);
		orderChangesLogManager.saveMasterChangesLog(mo);
	}

	/**
	 * WBE未支付订单二十五分钟后自动取消
	 */
	public void autoCancelNotPaidOrder() {

		// 超时取消
		List<String> notPayOrderList = masterDao.getMasterByOsta(null, null, GuaranteeCode.DUE);

		for (String orderNo : notPayOrderList) {
			log.info(this.getClass().getName() + " notPayOrder: " + orderNo);
			Master master = getOrderByOrderNo(orderNo);
			// 取消订单，修改状态
			if (!OrderStatus.CANCEL.equals(master.getSta())) {
				master.setSta(OrderStatus.CANCEL);
				master.setRestype(ReservationStatusType.CANCELED.name());
				master.setMasterId(master.getMasterId());
				master.setCancelTime(DateUtil.currentDateTime());
				master.setCancelType(OWSConstant.cancelType);
				master.setCancelReasonCode(OWSConstant.cancelReasonCode);
				master.setCancelRef("WBE Auto CancelRef");
				try {
					String operUser = OperUserType.SYSTEM.value();
					master.setUpdatedBy(operUser);
					master.setCancelBy(operUser);
					cancelOrder(master);
					orderChangesLogManager.saveMasterChangesLog(master);
					reservationService.buildWBEOrder(master);
				} catch (Exception e) {
					log.error(CommonUtil.getExceptionMsg(e, "ccm"));
				}
			}
		}
	}

	@Override
	public Master getOrderByOrderNo(String orderNo) {
		return masterDao.getMasterOrderByOrderId(orderNo);
	}

	public List<MasterRate> getMasterRateByOrderNo(String orderNo) {
		return masterDao.getMasterRate(orderNo);
	}

	public List<MasterPackage> getMasterPackageByOrderNo(String orderNo) {
		return masterDao.getMasterPackage(orderNo);
	}

	public List<MasterCancel> getMasterCancelByOrderNo(String orderNo) {
		return masterDao.getMasterCancel(orderNo);
	}

	public List<MasterProfile> getMasterProfile(String masterId) {
		return masterDao.getMasterProfile(masterId);
	}

	/**
	 * 
	 */
	public OrderSearchResult searchOrder(SearchOrderCriteria criteria) {
		return masterDao.searchList(criteria);
	}

	/**
	 * 验证房量
	 */
	public void verifyRoomNumbers(Master master) {
		if (!StringUtils.hasText(master.getMasterId()) || master.getNumberOfUnits().intValue() != master.getOnumberOfRooms() || master.getArr().compareTo(master.getOcheckinDate()) != 0 || master.getDep().compareTo(master.getOcheckoutDate()) != 0 || !master.getType().equals(master.getOtype()) || !master.getRatePlanCode().equals(master.getOratePlanCode()) || !master.getHotelCode().equals(master.getOhotelCode()) || !master.getChannel().equals(master.getOchannel())) {
			String channelCode = master.getChannel();
			String roomTypeCode = master.getType();
			String hotelCode = master.getHotelCode();
			String channelId = master.getChannelId();
			String rateplanCode = master.getRatePlanCode();
			Date checkinDate = master.getArr();
			Date checkoutDate = master.getDep();
			int numberOfRooms = master.getNumberOfUnits();
			log.info(" 本次订单下来的房间数：rooms = " + numberOfRooms);
			String oldRoomTypeCode = master.getOtype();
			Date oldCheckInDate = master.getOcheckinDate();
			Date oldCheckOutDate = master.getOcheckoutDate();
			int oldNumberOfRooms = master.getOnumberOfRooms() == null ? 0 : master.getOnumberOfRooms();
			Date orderCreatedDate = DateUtil.currentDate();
			// 需要将master里的值设置到此对象中，通过构造器创建
			RsvChangeInfo rsvChangeInfo = new RsvChangeInfo();
			rsvChangeInfo.setChannelId(channelId);
			rsvChangeInfo.setChannelCode(channelCode);
			rsvChangeInfo.setHotelCode(hotelCode);
			rsvChangeInfo.setOldRoomTypeCode(oldRoomTypeCode);
			rsvChangeInfo.setNewRoomTypeCode(roomTypeCode);
			rsvChangeInfo.setRateplanCode(rateplanCode);
			rsvChangeInfo.setOldCheckInDate(oldCheckInDate);
			rsvChangeInfo.setNewCheckInDate(checkinDate);
			rsvChangeInfo.setOldCheckOutDate(oldCheckOutDate);
			rsvChangeInfo.setNewCheckOutDate(checkoutDate);
			rsvChangeInfo.setOldRsvQty(oldNumberOfRooms);
			rsvChangeInfo.setNewRsvQty(numberOfRooms);
			rsvChangeInfo.setOrderCreatedDate(orderCreatedDate);
			rsvChangeInfo.setIsSupplierRateCode(master.getIsSupperRateCode());
			if (!isChannelRoomNumber(channelId)) {
				// 修改
				if (StringUtils.hasText(master.getMasterId())) {
					log.info("start to validate that the room qty of the modified order is available or not");
					// 验证房量
					boolean isAvai = rsvtypeChannelManager.validataRsvtypeChannelAilable(rsvChangeInfo);
					if (!isAvai) {
						log.info("验证酒店房型 modify ERROR..无房");
						throw new BizException("10");
					}
					log.info("end to validate");
				}
				// 创建
				else {
					log.info("start to validate that the room qty of the new order is available or not");
					// 验证房量
					boolean isAvai = rsvtypeChannelManager.validataRsvtypeChannelAilable(rsvChangeInfo);
					if (!isAvai) {
						log.info("验证酒店房型 create ERROR..无房");
						throw new BizException("10");
					}
					log.info("end to validate");
				}
			}
			log.info("验证OK..有房");
		}
	}

	/**
	 * 验证取消规则且返回扣款金额
	 */
	public BigDecimal validCancelPolicy(Master master, Date arr, BigDecimal roomPrice, BigDecimal staticPackage, int days) {
		Date now = new Date();
		now = DateUtil.getCleanDate(now);

		// 如果取消日期在入住日期之后,则不能取消的
		if (now.after(arr)) {
			throw new BizException("3");
		}
		// order is hardcancel 直接返回0
		if (master.getIsHardCancelOfMaster() == true) {
			return BigDecimal.ZERO;
		}

		List<BigDecimal> deductAmountList = new ArrayList<BigDecimal>();
		for (int dayIndex = 0; dayIndex < days; dayIndex++) {
			// 获取取消规则、校验、算出扣款金额
			Date cancelDate = DateUtil.addDays(DateUtil.getCleanDate(arr), dayIndex);

			MasterCancel mcObj = new MasterCancel();
			mcObj.setMasterId(master.getMasterId());
			mcObj.setCreatedTime(cancelDate);
			if (Calendar.MONDAY == DateUtil.getWeekday(cancelDate)) {
				mcObj.setIsApplyToMonday(Boolean.TRUE);
			} else if (Calendar.TUESDAY == DateUtil.getWeekday(cancelDate)) {
				mcObj.setIsApplyToTuesday(Boolean.TRUE);
			} else if (Calendar.WEDNESDAY == DateUtil.getWeekday(cancelDate)) {
				mcObj.setIsApplyToWednesday(Boolean.TRUE);
			} else if (Calendar.THURSDAY == DateUtil.getWeekday(cancelDate)) {
				mcObj.setIsApplyToThursday(Boolean.TRUE);
			} else if (Calendar.FRIDAY == DateUtil.getWeekday(cancelDate)) {
				mcObj.setIsApplyToFriday(Boolean.TRUE);
			} else if (Calendar.SATURDAY == DateUtil.getWeekday(cancelDate)) {
				mcObj.setIsApplyToSaturday(Boolean.TRUE);
			} else if (Calendar.SUNDAY == DateUtil.getWeekday(cancelDate)) {
				mcObj.setIsApplyToSunday(Boolean.TRUE);
			}
			List<MasterCancel> mcList = masterDao.getMasterCancelByObj(mcObj);
			if (mcList == null || mcList.isEmpty()) {
				continue;
			}

			MasterCancel mc = mcList.get(0);

			// 渠道取消规则勾选，如渠道取消订单，忽略房价取消规则，以确保取消订单生成
			if (mc.getChannelIsCancel() != null) {
				if (mc.getChannelIsCancel()) {
					return BigDecimal.ZERO;
				}
			}
			// if (master.getIsHardCancelOfHotel() == false) {
			// // 不能取消
			// if (mc.getIsNonRefundable() != null &&
			// mc.getIsNonRefundable()) {
			// throw new BizException("3");
			// }
			// }
			// // 如果酒店开关为开,IsNonRefundable 等于true
			// if (master.getIsHardCancelOfHotel() == true) {
			// // 不能取消
			// if (mc.getIsNonRefundable() != null &&
			// mc.getIsNonRefundable()) {
			// return master.getCharge();
			// }
			// }
			// 不能取消
			if (mc.getIsNonRefundable() != null && mc.getIsNonRefundable()) {
				// 如果酒店开关为开,IsNonRefundable 等于true
				if (master.getIsHardCancelOfHotel()) {
					return master.getCharge();
				} else {
					throw new BizException("3");
				}
			}

			// 绝对时间
			if (mc.getAbsoluteDeadline() != null) {
				// 在绝对时间之后，再判断是否扣款，若扣则可取消，否不可取消
				if (!new Date().before(mc.getAbsoluteDeadline())) {
					if (master.getIsHardCancelOfHotel() == true) {
						deductAmountList.add(deductBasic(mc, roomPrice, staticPackage, days));
					} else {
						throw new BizException("3");
					}
				}
			}
			// 相对时间,暂时只支持参考时间为入住时间
			else if (mc.getOffsetTimeUnit() != null && mc.getOffsetDropTime() != null && mc.getOffsetUnitMultiplier() != null) {
				Date dropTime = null;
				if (mc.getOffsetDropTime() == 1) {
					dropTime = cancelDate;
				}
				if (dropTime == null) {
					if (master.getIsHardCancelOfHotel() == true) {
						deductAmountList.add(BigDecimal.ZERO);
					} else {
						throw new BizException("3");
					}
				}
				Date dropTimeResult = null;
				// 获取截至当天几点
				int second2 = 0;
				if (mc.getOffsetCutTime() != null) {
					second2 = new BigDecimal(String.valueOf(mc.getOffsetCutTime())).multiply(new BigDecimal(3600)).intValue();
				}
				// 参照天,小时
				// days
				if (mc.getOffsetTimeUnit().intValue() == 1) {
					int second = new BigDecimal(mc.getOffsetUnitMultiplier()).multiply(new BigDecimal(86400)).intValue();
					second = second - second2;
					dropTimeResult = DateUtil.addSecond(DateUtil.setDateHHmmss(dropTime, 0, 0, 0), -second);
				}
				// hours
				else if (mc.getOffsetTimeUnit().intValue() == 2) {
					int second = new BigDecimal(mc.getOffsetUnitMultiplier()).multiply(new BigDecimal(3600)).intValue();
					second = second - second2;
					dropTimeResult = DateUtil.addSecond(DateUtil.setDateHHmmss(dropTime, 0, 0, 0), -second);
				}
				// 在相对时间之后，再判断是否扣款，若扣则可取消，否不可取消
				if (dropTimeResult != null && !new Date().before(dropTimeResult)) {
					if (master.getIsHardCancelOfHotel() == true) {
						deductAmountList.add(deductBasic(mc, roomPrice, staticPackage, days));
					} else {
						throw new BizException("3");
					}
				}
			}
		}
		if (deductAmountList.isEmpty() == false) {
			Collections.sort(deductAmountList);
			return deductAmountList.get(deductAmountList.size() - 1);
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 获取N间房的动态和1间房的静态包价
	 */
	public List<MasterPackage> getPackageInfo(Master master, List<PriceCalc> pcList) throws Exception {

		String language = master.getLang();
		// 取动态包价
		List<PackageVO> pvoDynamiclist = new ArrayList<PackageVO>();
		for (MasterPackage pe : master.getMpList()) {
			if (!pe.getIsDynamic() || pe.getQuantity() == null || pe.getQuantity() < 1 || pe.getRooms() < 1) {
				continue;
			}
			DynamicPackage dp = new DynamicPackage();
			dp.setHotelId(master.getHotelId());
			dp.setChannelId(master.getChannelId());
			dp.setPackageCode(pe.getPackageCode());
			List<PackageVO> dpList = packageManager.getDynamicPackagesByObj(dp, language);
			if (dpList != null) {
				for (PackageVO pvo : dpList) {
					pvo.setIsDynamic(true);
					pvo.setQuantity(pe.getQuantity());
					pvo.setRooms(pe.getRooms());
					pvoDynamiclist.add(pvo);
				}
			}
		}
		// 订单房价内容不变时，判断动态包价是否变更
		if (pcList == null && StringUtils.hasText(master.getMasterId())) {
			boolean newPackage = false;// 动态包价是否变更标志
			List<MasterPackage> mpListDB = getMasterPackageByOrderNo(master.getMasterId());
			List<MasterPackage> mpListDBDynamic = new ArrayList<MasterPackage>();
			for (MasterPackage mpDB : mpListDB) {
				if (mpDB.getIsDynamic()) {
					mpListDBDynamic.add(mpDB);
				}
			}
			if (mpListDBDynamic.size() != pvoDynamiclist.size()) {
				newPackage = true;
			} else {
				for (MasterPackage mpDB : mpListDBDynamic) {
					boolean a = false;
					for (MasterPackage mp : master.getMpList()) {
						if (mpDB.getPackageCode().equals(mp.getPackageCode()) && mpDB.getQuantity().intValue() == mp.getQuantity()) {
							a = true;
							break;
						}
					}
					if (!a) {
						newPackage = true;
						break;
					}
				}
			}
			// 动态包价变更了重新获取动态包价内容
			if (newPackage) {
				// 设置包价信息
				List<MasterPackage> mpAllList = new ArrayList<MasterPackage>();
				BigDecimal dynamicPackage = BigDecimal.ZERO;
				BigDecimal staticPakcage = BigDecimal.ZERO;
				for (MasterRate mr : master.getMrList()) {
					List<PackageVO> packagelist = countDynamicPackageAmount(pvoDynamiclist, master.getArr(), master.getDep(), master.getGstno(), mr.getDate());
					BigDecimal ratePackage = BigDecimal.ZERO;
					if (mpListDB.isEmpty()) {
						for (int n = 0; n < packagelist.size(); n++) {
							PackageVO pvo = packagelist.get(n);
							MasterPackage mp = new MasterPackage();
							mp.setAmount(new BigDecimal(pvo.getPackageAmount()));
							mp.setPackageId(pvo.getPackageId());
							mp.setPackageCode(pvo.getPackageCode());
							mp.setCreatedTime(new Date());
							mp.setCreatedBy(OXIConstant.user);
							mp.setDate(mr.getDate());
							mp.setIsDynamic(pvo.getIsDynamic());
							mp.setQuantity(pvo.getQuantity());
							mp.setRooms(pvo.getRooms());
							if (StringUtils.hasText(pvo.getDescription())) {
								mp.setPackageDesc(pvo.getDescription());
							} else {
								mp.setPackageDesc(pvo.getPackageName());
							}
							mp.setPackageName(pvo.getPackageName());
							dynamicPackage = AmountUtil.add(dynamicPackage, mp.getAmount().multiply(new BigDecimal(mp.getRooms())));
							ratePackage = AmountUtil.add(ratePackage, mp.getAmount());
							mr.getMpList().add(mp);
							mpAllList.add(mp);
						}
					} else {
						for (MasterPackage mp : mpListDB) {
							if (!mp.getIsDynamic()) {
								staticPakcage = AmountUtil.add(staticPakcage, mp.getAmount());
								ratePackage = AmountUtil.add(ratePackage, mp.getAmount());
								mr.getMpList().add(mp);
								mpAllList.add(mp);
							} else {
								for (int n = 0; n < packagelist.size(); n++) {
									PackageVO pvo = packagelist.get(n);
									mp.setAmount(new BigDecimal(pvo.getPackageAmount()));
									mp.setPackageId(pvo.getPackageId());
									mp.setPackageCode(pvo.getPackageCode());
									mp.setCreatedTime(new Date());
									mp.setCreatedBy(OXIConstant.user);
									mp.setDate(mp.getDate());
									mp.setIsDynamic(pvo.getIsDynamic());
									mp.setQuantity(pvo.getQuantity());
									mp.setRooms(pvo.getRooms());
									if (StringUtils.hasText(pvo.getDescription())) {
										mp.setPackageDesc(pvo.getDescription());
									} else {
										mp.setPackageDesc(pvo.getPackageName());
									}
									mp.setPackageName(pvo.getPackageName());
									dynamicPackage = AmountUtil.add(dynamicPackage, mp.getAmount().multiply(new BigDecimal(mp.getRooms())));
									ratePackage = AmountUtil.add(ratePackage, mp.getAmount());
									mr.getMpList().add(mp);
									mpAllList.add(mp);
								}
							}
						}
					}
					mr.setPackages(ratePackage);
				}
				master.setStaticPackage(staticPakcage);
				master.setRmrate(AmountUtil.add(staticPakcage, dynamicPackage));// 总共应收包价
				master.setCharge(AmountUtil.add(master.getSetrate(), master.getRmrate()));// 总共应收16
				return mpAllList;
			} else {
				// 设置房价里包价信息
				for (MasterRate mr : master.getMrList()) {
					for (MasterPackage mp : mpListDB) {
						if (mp.getDate().compareTo(mr.getDate()) == 0) {
							mr.getMpList().add(mp);
						}
					}
				}
				return mpListDB;
			}
		} else {
			// 取静态包价信息
			List<PackageVO> pvolist = new ArrayList<PackageVO>();
			List<String> rateplanIds = new ArrayList<String>();
			rateplanIds.add(master.getRatePlanId());
			pvolist = availabilityManager.getPackageVOs(rateplanIds, master.getArr(), master.getDep(), language);
			Map<String, List<PackageVO>> dateStatic = new HashMap<String, List<PackageVO>>();
			// 取房型房价包价
			if (pcList != null) {
				for (PriceCalc p : pcList) {
					List<PackageVO> rateRoomlist = new ArrayList<PackageVO>();
					rateRoomlist.addAll(pvolist);
					rateRoomlist.addAll(availabilityManager.getRoomTypePackageByRateDetailId(p.getRateDetailId(), language, master.getArr(), master.getDep()));
					List<PackageVO> rpAndRtlist = availabilityManager.getPackageVOsByRatePlanAndRoomType(rateRoomlist, master.getRatePlanId(), master.getRoomTypeId());
					dateStatic.put(p.getDate(), rpAndRtlist);
				}
			}
			// 设置包价信息
			List<MasterPackage> mpAllList = new ArrayList<MasterPackage>();
			BigDecimal dynamicPackage = BigDecimal.ZERO;
			BigDecimal staticPakcage = BigDecimal.ZERO;
			for (MasterRate mr : master.getMrList()) {
				PriceCalc p = new PriceCalc();
				p.setDate(DateUtil.convertDateToString(mr.getDate()));
				p.setAmount(mr.getSetrate());
				// 计算静态动态包价金额(人数/每房)
				List<PackageVO> packagelist = availabilityManager.countPackageAmount(dateStatic.get(p.getDate()), master.getArr(), master.getDep(), 1, master.getGstno(), p);
				// 计算动态包价金额(人数/每房)
				packagelist.addAll(countDynamicPackageAmount(pvoDynamiclist, master.getArr(), master.getDep(), master.getGstno(), mr.getDate()));
				BigDecimal ratePackage = BigDecimal.ZERO;
				for (int n = 0; n < packagelist.size(); n++) {
					PackageVO pvo = packagelist.get(n);
					MasterPackage mp = new MasterPackage();
					mp.setAmount(new BigDecimal(pvo.getPackageAmount()));
					mp.setPackageId(pvo.getPackageId());
					mp.setPackageCode(pvo.getPackageCode());
					mp.setCreatedTime(new Date());
					mp.setCreatedBy(OXIConstant.user);
					mp.setDate(mr.getDate());
					mp.setIsDynamic(pvo.getIsDynamic());
					mp.setQuantity(pvo.getQuantity());
					mp.setRooms(pvo.getRooms());
					if (StringUtils.hasText(pvo.getDescription())) {
						mp.setPackageDesc(pvo.getDescription());
					} else {
						mp.setPackageDesc(pvo.getPackageName());
					}
					mp.setPackageName(pvo.getPackageName());

					if (pvo.getIsDynamic()) {
						dynamicPackage = AmountUtil.add(dynamicPackage, mp.getAmount().multiply(new BigDecimal(mp.getRooms())));
					} else {
						staticPakcage = AmountUtil.add(staticPakcage, mp.getAmount());
					}
					ratePackage = AmountUtil.add(ratePackage, mp.getAmount());
					mr.getMpList().add(mp);
					mpAllList.add(mp);
				}
				mr.setPackages(ratePackage);

			}
			master.setStaticPackage(staticPakcage);
			master.setRmrate(AmountUtil.add(staticPakcage, dynamicPackage));// 总共应收包价
			master.setCharge(AmountUtil.add(master.getSetrate(), master.getRmrate()));// 总共应收16
			return mpAllList;
		}

	}

	public int getChildOrderNum(String masterId, String orderStatus) {
		return masterDao.getChildOrderNum(masterId, orderStatus);
	}

	public List<Master> getOrderChildByOrderId(String orderId) {
		return masterDao.getOrderChildByOrderId(orderId);
	}

	public Master getParentOrderById(String parentId) {
		return masterDao.getParentOrderById(parentId);
	}

	public List<Master> getMasterByCrsno(String channelOrderId) {
		return masterDao.getMasterByCrsno(channelOrderId);
	}

	public List<Master> getChildOrderByParentId(String parentId) {
		return masterDao.getChildOrderByParentId(parentId);
	}

	/**
	 * 验证信用卡信息
	 */
	public void getEnableGuaranteeType(Master master) throws Exception {
		if (master.getIsSupperRateCode()) {
			return;
		}
		String guaranteeType = master.getPayment();
		// 信用卡担保，预订时需校验信用卡信息
		if (GuaranteeCode.CCGTD.equals(guaranteeType)) {
			if (!StringUtils.hasText(master.getCardCode()) || !StringUtils.hasText(master.getCardHolderName()) || !StringUtils.hasText(master.getCardNumber()) || master.getExpirationDate() == null) {
				throw new BizException("31");
			}
			if (master.getExpirationDate().before(master.getDep())) {
				throw new BizException("17");
			}
			if (CardCode.AX.name().equals(master.getCardCode())) {
				Pattern p_script = Pattern.compile("3[4,7]{1}[0-9]{13}");
				Matcher m_script = p_script.matcher(master.getCardNumber());
				if (!m_script.find()) {
					throw new BizException("17");
				}
			} else if (CardCode.VA.name().equals(master.getCardCode())) {
				Pattern p = Pattern.compile("4([0-9]{12}|[0-9]{15})");
				Matcher m_script = p.matcher(master.getCardNumber());
				if (!m_script.find()) {
					throw new BizException("17");
				}
				long cc = Long.valueOf(master.getCardNumber()).longValue();
				if (master.getCardNumber().length() == 13) {
					if ((4000000000000L <= cc && cc <= 4905249999999L) || (4905300000000l <= cc && cc <= 4910999999999l) || (4911010000000l <= cc && cc <= 4927999999999l) || (4929000000000l <= cc && cc <= 4999999999999l)) {

					} else {
						throw new BizException("17");
					}
				} else if (master.getCardNumber().length() == 16) {
					if ((4000000000000000l <= cc && cc <= 4905249999999999l) || (4905300000000000l <= cc && cc <= 4910999999999999l) || (4911010000000000l <= cc && cc <= 4927999999999999l) || (4929000000000000l <= cc && cc <= 4999999999999999l)) {

					} else {
						throw new BizException("17");
					}
				} else {
					throw new BizException("17");
				}
			} else if (CardCode.MC.name().equals(master.getCardCode())) {
				Pattern p = Pattern.compile("5[1-5]{1}[0-9]{14}");
				Matcher m_script = p.matcher(master.getCardNumber());
				if (!m_script.find()) {
					throw new BizException("17");
				}
			} else if (CardCode.DC.name().equals(master.getCardCode())) {
				Pattern p = Pattern.compile("[30,36,38][0-9]{12}");
				Matcher m_script = p.matcher(master.getCardNumber());
				if (!m_script.find()) {
					throw new BizException("17");
				}
			} else if (CardCode.JC.name().equals(master.getCardCode())) {
				Pattern p = Pattern.compile("3[0-9]{15}");
				Matcher m_script = p.matcher(master.getCardNumber());
				if (!m_script.find()) {
					throw new BizException("17");
				}
				long cc = Long.valueOf(master.getCardNumber()).longValue();
				if ((3088000000000000l <= cc && cc <= 3094999999999999l) || (3096000000000000l <= cc && cc <= 3102999999999999l) || (3112000000000000l <= cc && cc <= 3120999999999999l) || (3158000000000000l <= cc && cc <= 3159999999999999l) || (3370000000000000l <= cc && cc <= 3349999999999999l) || (3528000000000000l <= cc && cc <= 3589999999999999l)) {

				} else {
					throw new BizException("17");
				}
			}
		}
		// 验证担保类型是否可用
		boolean isTrue = rateGuaranteeManager.validGuaranteePolicy(guaranteeType, master.getRatePlanId(), master.getArr());
		if (!isTrue) {
			Custom custom = customDao.searchCustomByHotelIdAndAccessCode(master.getHotelId(), master.getAccessCode());
			if (custom.getDefGuaranteeId() != null) {
				GuaranteePolicy guaranteePolicy = guaranteePolicyManager.get(custom.getDefGuaranteeId());
				if (guaranteePolicy.getGuaranteeCode().equalsIgnoreCase(guaranteeType)) {
					isTrue = true;
				}
			}
		}
		if (!isTrue) {
			Channel c = channelManager.get(master.getChannelId());
			if (c != null && c.getIsGuarantee() && StringUtils.hasLength(c.getGuaranteeId())) {
				try {
					GuaranteePolicy gp = guaranteePolicyManager.get(c.getGuaranteeId());
					master.setPayment(gp.getGuaranteeCode());
					return;
				} catch (Exception e) {
					log.info("isChannelGuaranteeDefault" + c.getGuaranteeId());
					throw new BizException("18");
				}
			}
			throw new BizException("18");
		}
	}

	public OrderSearchResult searchSummaryOrders(SearchOrderCriteria criteria) {
		return masterDao.searchSummaryOrders(criteria);
	}

	public OrderEmailConfirmResult searchOrderEmailConfirm(SearchOrderEmailConfirmCriteria criteria) {
		return smsSendDao.searchOrderEmailConfirm(criteria);
	}

	/**
	 * 转换渠道与CCM中预订状态的映射
	 * 
	 * @param master
	 */
	private void dealRestype(Master master) {
		log.info(master.getSta() + ",resType:" + master.getRestype());
		Map<String, String> codeMap = dictCodeManager.searchCodeMapByChannelHotel(OXIConstant.orderStatus, master.getChannelId(), null, true);
		String externalValue = codeMap.get(master.getSta());
		log.info("externalValue:" + externalValue);
		if (externalValue != null) {
			master.setRestype(externalValue);
		}
	}

	/**
	 * 添加快照明细
	 * 
	 * @param mrList
	 */
	private void saveMasterRate(Master master) {
		String orderId = master.getMasterId();
		if (master.getMrList() != null) {
			masterDao.deleteMasterRateByOrderId(orderId);
			for (MasterRate mr : master.getMrList()) {
				mr.setMasterId(orderId);
				masterDao.saveMasterRate(mr);
			}
		}
		if (master.getMpList() != null) {
			masterDao.deleteMasterPackageByOrderId(orderId);
			for (MasterPackage mp : master.getMpList()) {
				mp.setMasterId(orderId);
				mp.setMasterPackageId(CommonUtil.generatePrimaryKey());
				masterDao.saveMasterPackage(mp);
			}
		}
		if (master.getMcList() != null) {
			masterDao.deleteMasterCancelByOrderId(orderId);
			for (MasterCancel mc : master.getMcList()) {
				mc.setMasterId(orderId);
				mc.setMasterCancelId(CommonUtil.generatePrimaryKey());
				masterDao.saveMasterCancel(mc);
			}
		}
	}

	private void saveMasterProfile(Master master, Integer index) {
		String orderId = master.getMasterId();
		if (!master.getMprofile().isEmpty()) {
			log.info("start delete masterProfile&add:" + orderId);
			masterDao.deleteMasterProfileByOrderId(orderId);
			for (int i = 0; i < master.getMprofile().size(); i++) {
				// 客人姓,名是关联子订单时只需保存一次,因为子订单只有一间房所以只保存一个姓名
				if (index != null) {
					MasterProfile mp = master.getMprofile().get(index);
					saveMasterPrfile(mp, orderId, master.getLang(), i);
					break;
				} else {
					MasterProfile mp = master.getMprofile().get(i);
					saveMasterPrfile(mp, orderId, master.getLang(), i);
				}
			}
		}
	}

	private void saveMasterPrfile(MasterProfile mp, String orderId, String lang, int i) {
		mp.setMasterId(orderId);
		mp.setMasterProfileId(CommonUtil.generatePrimaryKey());
		if (LanguageCode.ZH_CN.equals(lang)) {
			mp.setChinaName(PinyinUtil.covertCnNameToPinyin(mp.getFirstName(), "") + PinyinUtil.covertCnNameToPinyin(mp.getLastName(), ""));
		}
		mp.setSex(CommonUtil.getSex(mp.getNameTitle()));
		mp.setNameTitle(mp.getNameTitle());
		if (i == 0) {
			mp.setCreatedTime(new Date());
		} else {
			mp.setCreatedTime(DateUtil.addSecond(new Date(), i));
		}
		mp.setCreatedBy(OXIConstant.user);
		masterDao.saveMasterProfile(mp);
	}

	private List<PackageVO> countDynamicPackageAmount(List<PackageVO> pvolist, Date startDate, Date endDate, Integer totalNumberOfGuests, Date date) throws Exception {

		endDate = new Date(endDate.getTime() - 1000 * 24 * 60 * 60);// 取前一天的日期
		if (totalNumberOfGuests == null) {
			totalNumberOfGuests = 0;
		}
		Calendar cal = Calendar.getInstance();
		int week = 0;
		cal.setTime(date);
		week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		for (PackageVO vo : pvolist) {
			BigDecimal amount = null;
			if (vo.getIsExtraCharge()) {
				if (vo.getCalculation() == 1) {// 每晚
					if (vo.getRule() == 1) {// 固定价格
						if (vo.getBasicType() == 2) {
							amount = new BigDecimal(vo.getAmount());
						}
					} else if (vo.getRule() == 4) {// 人数
						amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(totalNumberOfGuests));
					} else if (vo.getRule() == 5) {// 房间数
						amount = new BigDecimal(vo.getAmount());
					}
				} else if (vo.getCalculation() == 2) {// 到店日
					if (date.equals(startDate)) {
						if (vo.getRule() == 1) {// 固定价格
							if (vo.getBasicType() == 2) {
								amount = new BigDecimal(vo.getAmount());
							}
						} else if (vo.getRule() == 4) {// 人数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(totalNumberOfGuests));
						} else if (vo.getRule() == 5) {// 房间数
							amount = new BigDecimal(vo.getAmount());
						}
					}
				} else if (vo.getCalculation() == 3) {// 离店日
					if (date.equals(endDate)) {
						if (vo.getRule() == 1) {// 固定价格
							if (vo.getBasicType() == 2) {
								amount = new BigDecimal(vo.getAmount());
							}
						} else if (vo.getRule() == 4) {// 人数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(totalNumberOfGuests));
						} else if (vo.getRule() == 5) {// 房间数
							amount = new BigDecimal(vo.getAmount());
						}
					}
				} else if (vo.getCalculation() == 4) {// 星期
					if ((vo.getIsApplyToSunday() && week == 0) || (vo.getIsApplyToMonday() && week == 1) || (vo.getIsApplyToTuesday() && week == 2) || (vo.getIsApplyToWednesday() && week == 3) || (vo.getIsApplyToThursday() && week == 4) || (vo.getIsApplyToFriday() && week == 5) || (vo.getIsApplyToSaturday() && week == 6)) {
						if (vo.getRule() == 1) {// 固定价格
							if (vo.getBasicType() == 2) {
								amount = new BigDecimal(vo.getAmount());
							}
						} else if (vo.getRule() == 4) {// 人数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(totalNumberOfGuests));
						} else if (vo.getRule() == 5) {// 房间数
							amount = new BigDecimal(vo.getAmount());
						}
					}
				} else if (vo.getCalculation() == 5) {// 日期
					if ((vo.getBeginDate().before(date) || vo.getBeginDate().equals(date)) && (vo.getEndDate().after(date) || vo.getEndDate().equals(date))) {
						if (vo.getRule() == 1) {// 固定价格
							if (vo.getBasicType() == 2) {
								amount = new BigDecimal(vo.getAmount());
							}
						} else if (vo.getRule() == 4) {// 人数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(totalNumberOfGuests));
						} else if (vo.getRule() == 5) {// 房间数
							amount = new BigDecimal(vo.getAmount());
						}
					}
				}
			} else {
				amount = new BigDecimal("0");
			}
			if (amount != null && vo.getQuantity() != null && vo.getQuantity() > 0) {
				vo.setPackageAmount(amount.multiply(new BigDecimal(vo.getQuantity())).toString());
			} else {
				vo.setPackageAmount(null);
			}
		}
		List<PackageVO> list = new ArrayList<PackageVO>();
		for (PackageVO pvo : pvolist) {
			if (pvo.getPackageAmount() != null) {
				list.add(pvo);
			}
		}
		return list;

	}

	private synchronized String getChannelOrderId(String orderId) {
		if (channelOrderId.get(orderId) != null) {
			for (int i = 0; i < 26; i++) {
				String nowOrderId = channelOrderId.get(orderId) + (char) ("a".charAt(0) + i);
				if (channelOrderId.get(nowOrderId) == null) {
					channelOrderId.put(nowOrderId, nowOrderId);
					return nowOrderId;
				}
			}
		}
		channelOrderId.put(orderId, orderId);
		return orderId;
	}

	/**
	 * 渠道房量勾选时，如酒店无可卖房量，新建，修改订单也能生成
	 * 
	 * @param channelId
	 * @return
	 */
	private boolean isChannelRoomNumber(String channelId) {
		Channel c = channelManager.get(channelId);
		if (c != null && c.getIsRoomNumber() != null && c.getIsRoomNumber()) {
			log.info("channelOverRideIsRoomNumber");
			return true;
		}
		return false;
	}

	/**
	 * 渠道房量勾选时，如酒店无可卖房量，新建或修改订单也能生成，并将可卖数量存入渠道已售总房数
	 * 
	 * @param master
	 */
	private void updateRsvtypeChannelRooms(Master master) {
		String channelCode = master.getChannel();
		String channelId = master.getChannelId();
		String hotelCode = master.getHotelCode();
		String roomTypeCode = master.getType();
		String rateplanCode = master.getRatePlanCode();
		Date checkinDate = master.getArr();
		Date checkoutDate = master.getDep();
		int numberOfRooms = master.getNumberOfUnits();
		String oldRoomTypeCode = master.getOtype();
		Date oldCheckInDate = master.getOcheckinDate();
		Date oldCheckOutDate = master.getOcheckoutDate();
		Date orderCreatedDate = DateUtil.currentDate();
		int oldNumberOfRooms = master.getOnumberOfRooms() == null ? 0 : master.getOnumberOfRooms();
		try {
			// 需要将master里的值设置到此对象中，通过构造器创建
			RsvChangeInfo rsvChangeInfo = new RsvChangeInfo();
			rsvChangeInfo.setChannelId(channelId);
			rsvChangeInfo.setChannelCode(channelCode);
			rsvChangeInfo.setHotelCode(hotelCode);
			rsvChangeInfo.setOldRoomTypeCode(oldRoomTypeCode);
			rsvChangeInfo.setNewRoomTypeCode(roomTypeCode);
			rsvChangeInfo.setRateplanCode(rateplanCode);
			rsvChangeInfo.setOldCheckInDate(oldCheckInDate);
			rsvChangeInfo.setNewCheckInDate(checkinDate);
			rsvChangeInfo.setOldCheckOutDate(oldCheckOutDate);
			rsvChangeInfo.setNewCheckOutDate(checkoutDate);
			rsvChangeInfo.setOldRsvQty(oldNumberOfRooms);
			rsvChangeInfo.setNewRsvQty(numberOfRooms);
			rsvChangeInfo.setOrderCreatedDate(orderCreatedDate);
			// 获取房量
			boolean isAvai = rsvtypeChannelManager.validataRsvtypeChannelAilable(rsvChangeInfo);
			if (!isAvai && isChannelRoomNumber(channelId)) {
				rsvtypeChannelManager.updateRsvtypeChannelSold(roomTypeCode, hotelCode, checkinDate, checkoutDate, channelId, channelCode, numberOfRooms);
			} else {
				// 修改
				if (StringUtils.hasText(master.getMasterId())) {
					rsvtypeChannelManager.cancelRsvtypeChannelAilable(oldRoomTypeCode, hotelCode, oldCheckInDate, oldCheckOutDate, channelCode, oldNumberOfRooms, rateplanCode, orderCreatedDate);
					// 扣减新订单房量
					rsvtypeChannelManager.confirmRsvtypeChannelAilable(rsvChangeInfo);
				}
				// 新建
				else {
					rsvtypeChannelManager.confirmRsvtypeChannelAilable(rsvChangeInfo);
				}
				master.setSendOccupyFreeSellAvai(rsvChangeInfo.getSendOccupyFreeSellAvai());
			}
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
			throw new BizException("13");
		}
	}

	/**
	 * 在绝对和相对时间之后，再判断是否扣款，若扣则可取消，否不可取消
	 * 
	 * @param mc
	 * @param roomPrice
	 * @param staticPackage
	 * @param days
	 * @return
	 */
	private BigDecimal deductBasic(MasterCancel mc, BigDecimal roomPrice, BigDecimal staticPackage, int days) {
		// 计算扣款金额
		BigDecimal refundA = BigDecimal.ZERO;

		if (mc.getFeesInclusive() == null || !mc.getFeesInclusive() || mc.getBasisType() == null) {
			return refundA;
		}

		// 扣除首晚 firstlast
		if (mc.getFeesInclusive() != null && mc.getFeesInclusive() && mc.getBasisType() == 3) {
			MasterRate fmr = masterDao.getFirstMasterRateByOrderNo(mc.getMasterId());

			// PMS上传修改订单时会出现days为0
			if (days == 0) {
				return refundA;
			}
			// 含税
			else if (mc.getTaxInclusive() != null && mc.getTaxInclusive()) {
				// refundA=fmr.getSetrate().divide(roomPrice, 4,
				// BigDecimal.ROUND_HALF_UP).multiply(staticPackage);
				refundA = fmr.getSetrate();
			}
			// // 不含税,入住期间的均价=首日房价
			else {
				refundA = fmr.getSetrate();
			}
			return refundA;
		}

		// 按几个间夜扣款：入住期间的均价*扣款间夜=扣款金额
		if (mc.getNumberOfNights() != null) {
			// PMS上传修改订单时会出现days为0
			if (days == 0) {
				return refundA;
			}
			// 含税,入住期间的均价=(房价+静态包价)/夜数
			else if (mc.getTaxInclusive() != null && mc.getTaxInclusive()) {
				refundA = AmountUtil.add(roomPrice, staticPackage).divide(new BigDecimal(days), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(mc.getNumberOfNights()));
			}
			// 不含税,入住期间的均价=房价/夜数
			else {
				refundA = roomPrice.divide(new BigDecimal(days), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(mc.getNumberOfNights()));
			}
		}
		// 按百分比扣款：入住期间的总房价*百分比=扣款金额
		else if (mc.getPercent() != null) {
			// 含税,入住期间的总房价=房价+静态包价
			if (mc.getTaxInclusive() != null && mc.getTaxInclusive()) {
				refundA = AmountUtil.add(roomPrice, staticPackage).multiply(new BigDecimal(mc.getPercent())).multiply(new BigDecimal(0.01));
			}
			// 不含税,入住期间的总房价=房价
			else {
				refundA = roomPrice.multiply(new BigDecimal(mc.getPercent())).multiply(new BigDecimal(0.01));
			}
		}
		// 按固定金额扣款：固定金额=扣款金额
		else if (mc.getAmount() != null) {
			refundA = new BigDecimal(mc.getAmount());
		}
		return refundA;
	}

	@Override
	public void cancelOrderCCM(Master master) {
		// 取消订单，修改状态
		if (!OrderStatus.CANCEL.equals(master.getSta())) {
			HotelSwitch hotelSwitch = hotelSwitchDao.getByHotelId(master.getHotelId());
			// 酒店级的HARD CANCEL是否为TRUE，为TRUE时，取消一定成功
			if (hotelSwitch == null || !hotelSwitch.getIsHardCancel()) {
				try {
					this.validCancelPolicy(master, master.getArr(), master.getSetrate(), master.getStaticPackage(), master.getDays());
				} catch (BizException e) {
					throw new BizException("P0062");
				}
			}
			master.setSta(OrderStatus.CANCEL);
			master.setRestype(ReservationStatusType.CANCELED.name());
			master.setMasterId(master.getMasterId());
			master.setCancelTime(DateUtil.currentDateTime());
			master.setCancelType(OWSConstant.cancelType);
			master.setCancelReasonCode(OWSConstant.cancelReasonCode_CCM);
			// 总订单
			if ("0".equals(master.getPcrec())) {
				master.setCharge(BigDecimal.ZERO);
				master.setRmrate(BigDecimal.ZERO);
				master.setSetrate(BigDecimal.ZERO);
				master.setStaticPackage(BigDecimal.ZERO);
			}
			// 子订单
			else {
				Master mparent = this.getOrderByOrderNo(master.getPcrec());
				if (mparent != null) {
					mparent.setCharge(AmountUtil.reduce(mparent.getCharge(), master.getCharge()));
					mparent.setRmrate(AmountUtil.reduce(mparent.getRmrate(), master.getRmrate()));
					mparent.setSetrate(AmountUtil.reduce(mparent.getSetrate(), master.getSetrate()));
					mparent.setStaticPackage(AmountUtil.reduce(mparent.getStaticPackage(), master.getStaticPackage()));
					mparent.setUpdatedBy(master.getUpdatedBy());
					masterDao.updateMaster(mparent);
					orderChangesLogManager.saveMasterChangesLog(mparent);
				}
			}
			this.cancelOrder(master);
			orderChangesLogManager.saveMasterChangesLog(master);
			oXIReservationNodesService.buildCancelFintrx(master);
			reservationService.buildWBEOrder(master);
		}

	}

	@Override
	public List<MasterRate> getMasterRateByOrderNo(String masterId, String date) {
		return masterDao.getMasterRateByOrderNo(masterId, date);
	}

	@Override
	public boolean isCancel(Master order) {
		String channelCode = order.getChannel();
		boolean flag = false;
		if (ChannelCode.TAOBAO.equalsIgnoreCase(channelCode)) {
			// 淘宝渠道的预付单
			if (GuaranteeCode.TAGTD.equalsIgnoreCase(order.getPayment()) || GuaranteeCode.TAGTD_GT.equalsIgnoreCase(order.getPayment())) {
				flag = true;
			}
		} else {
			// 非淘宝渠道的预付单
			if (order.getPayment().equalsIgnoreCase(GuaranteeCode.DUE)) {
				flag = true;
			}
		}

		// flag=true 预付订单，预付单，才走下面的逻辑
		if (flag) {
			String hotelId = order.getHotelId();
			Custom c = new Custom();
			c.setHotelId(order.getHotelId());
			c.setAccessCode(order.getQualifyingIdValue());
			c.setType(order.getQualifyingIdType());
			Custom custom = rateCustomRelationshipDao.getCustomIdByRateCustom(c, order.getRatePlanId());
			if (custom.getBookingManagment()) {
				HotelSwitch hotelSwitch = hotelSwitchDao.getByHotelId(hotelId);
				/**
				 * 开启酒店配置hardcancel为true,未启用保证金，则酒店配置hardcancel为true无效
				 */
				// 开启酒店配置hardcancel为true，，取消订单无效，ccm订单暂无hardcancel取消方式
				if (hotelSwitch != null && hotelSwitch.getIsHardCancel()) {
					// 启用保证金
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void updateDeduct(Master master) {
		masterDao.updateDeduct(master);
	}

	/**
	 * 根据hotel获取相应的market
	 * 
	 * @param HotelIds
	 * @return
	 */
	@Override
	public List<String> getMarketsByHotelId(String hotelId) {
		return masterDao.getMarketsByHotelId(hotelId);
	}

	@Override
	public boolean isReservation(String key, String channelId) {
		// 1.渠道一分钟内下单次数限制
		// redis 中记录的次数
		Channel c = channelManager.get(channelId);
		if (c == null) {
			return false;
		}
		Map<String, Long> redis = redisDao.readLongValueFromMap(channelId);
		// 2.上已订单处理是否完毕
		Serializable s = redisDao.readObject(key);
		if (s != null) {
			boolean bool = (Boolean) s;
			if (bool) {
				return false;
			}
		}
		// redis没有记录
		if (redis == null || redis.isEmpty()) {
			return true;
		}
		Long number = redis.get(channelId);
		// redis 中记录的次数>渠道一分钟内下单次数限制
		if (number > c.getOrdernNumberOfTimes()) {
			return false;
		}

		return true;
	}

	@Override
	public void addReverationTime(String channelId) {
		Map<String, Long> s = redisDao.readLongValueFromMap(channelId);
		int a = 1;
		if (s != null && !s.isEmpty()) {
			redisDao.hincrebyForHash(channelId, channelId, a);
		} else {
			redisDao.hincrebyForHash(channelId, channelId, a, 60, TimeUnit.SECONDS);
		}

	}

	@Override
	public void deleReverationKey(String key) {
		redisDao.delete(key);
	}

	@Override
	public void addRedisDealOrder(String key, Boolean value, long time) {
		redisDao.save(key, value, time, TimeUnit.SECONDS);
	}

	@Override
	public void updateMaster(Master m,MasterOrder mo) {
		String userName = SecurityHolder.getUserName();
		if (StringUtils.hasText(userName)) {
			mo.setUpdatedBy(userName);
			m.setUpdatedBy(userName);
		}
		
		masterDao.updateMasterOrderPmsId(mo);
		//update Master表 by smasterId
		masterDao.updateMaster(m);
		orderChangesLogManager.saveMasterChangesLog(m);
	}
}
