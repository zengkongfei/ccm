package com.ccm.api.service.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.hotel.HotelSwitchDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.dao.ratePlan.CancelPolicyDao;
import com.ccm.api.dao.ratePlan.GuaranteePolicyDao;
import com.ccm.api.dao.ratePlan.RateCancelRelationshipDao;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.OWSConstant;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.constant.WBEConstant;
import com.ccm.api.model.enume.OperUserType;
import com.ccm.api.model.enume.ProfileType;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterCancel;
import com.ccm.api.model.order.MasterPackage;
import com.ccm.api.model.order.MasterProfile;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.order.vo.WBEOrder;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RateCancelRelationship;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.service.hotel.CreditLimitManager;
import com.ccm.api.service.hotel.CustomManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.order.OXIReservationNodesService;
import com.ccm.api.service.order.OrderBusinessVerifyManager;
import com.ccm.api.service.order.OrderChangesLogManager;
import com.ccm.api.service.order.OrderManager;
import com.ccm.api.service.order.ReservationService;
import com.ccm.api.service.order.WBEOrderManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.util.AmountUtil;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.PinyinUtil;
import com.ccm.oxi.reservation.ReservationStatusType;

@Service("wBEOrderManager")
public class WBEOrderManagerImpl extends GenericManagerImpl<Master, String> implements WBEOrderManager {

	private Log log = LogFactory.getLog(WBEOrderManagerImpl.class);

	@Resource
	private RateCustomRelationshipDao rateCustomRelationshipDao;
	@Resource
	private GuaranteePolicyDao guaranteePolicyDao;
	@Resource
	private RateCancelRelationshipDao rateCancelRelationshipDao;
	@Resource
	private MasterDao masterDao;

	@Resource
	private ChainManager chainManager;
	@Resource
	private HotelManager hotelManager;
	@Resource
	private OrderManager orderManager;
	@Resource
	private RatePlanManager ratePlanManager;
	@Resource
	private RoomTypeManager roomTypeManager;
	@Resource
	private ChannelgoodsManager channelgoodsManager;
	@Resource
	private OrderBusinessVerifyManager orderBusinessVerifyManager;
	@Resource
	private ReservationService reservationService;
	@Resource
	private CustomManager customManager;
	@Resource
	private OrderChangesLogManager orderChangesLogManager;
	@Resource
	private OXIReservationNodesService oXIReservationNodesService;
	@Autowired
	private HotelSwitchDao hotelSwitchDao;
	@Resource
	private CancelPolicyDao cancelPolicyDao;
	@Resource
	private CreditLimitManager creditLimitManager;
	public String createOrder(WBEOrder order, Master master, String ref) throws Exception {
		if (master.getMprofile().isEmpty()) {
			return null;
		}

		master.setSta(OrderStatus.ADD);
		master.setRestype(ReservationStatusType.RESERVED.name());

		Master dbMaster = null;
		try {
			dbMaster = buildOrder(order, master);
		} catch (BizException e) {
			throw e;
		} catch (Exception e) {
			log.warn(CommonUtil.getExceptionMsg(e, "ccm"));
			throw new BizException("P0063");
		}

		if (dbMaster != null && master.getIsSupperRateCode() == false) {
			// 修改子订单
			if (!"0".equals(dbMaster.getPcrec())) {
				// 信用卡担保订单不能修改担保类型
				if (dbMaster.getPayment().equals(GuaranteeCode.CCGTD) && !dbMaster.getPayment().equalsIgnoreCase(master.getPayment())) {
					throw new BizException("W28");
				}
			}
			// 预付订单不可以修改及取消订单
			if (GuaranteeCode.DUE.equalsIgnoreCase(dbMaster.getPayment()) && !dbMaster.getPayment().equalsIgnoreCase(master.getPayment())) {
				throw new BizException("UNABLE_CANCEL_OR_MODIF");
			}
			// 非预付订单不成修改为预付订单
			if (!GuaranteeCode.DUE.equalsIgnoreCase(dbMaster.getPayment()) && GuaranteeCode.DUE.equalsIgnoreCase(master.getPayment())) {
				throw new BizException("W40");
			}
		}

		if (order.getIsAddressRequired() && !StringUtils.hasText(master.getAddressLine())) {
			throw new BizException("C0008");
		}
		if (order.getIsEmailRequird() && !StringUtils.hasText(master.getEmail())) {
			throw new BizException("C0003");
		}
		if (order.getIsMobileRequired() && !StringUtils.hasText(master.getMobile())) {
			throw new BizException("C0029");
		}
		if (order.getMaxResCount() < master.getNumberOfUnits()) {
			throw new BizException("G05");
		}

		// 设置担保信息
		if (StringUtils.hasText(order.getExpirationDateY()) && StringUtils.hasText(order.getExpirationDateM())) {
			Date expirationDate = DateUtil.getMonthLastDayByYM(Integer.valueOf(order.getExpirationDateY()), Integer.valueOf(order.getExpirationDateM()));
			master.setExpirationDate(expirationDate);
		}
		getValidGuaranteeType(master);

		if (CommonUtil.getStringByteLength(master.getSrqs()) > 500) {
			throw new BizException("G07");
		}
		master.setRef(master.getSrqs());

		if (master.getMprofile().size() == 0) {
			throw new BizException("C0027");
		}
		MasterProfile profile = master.getMprofile().get(0);

		if (LanguageCode.ZH_CN.equalsIgnoreCase(master.getLang())) {
			master.setName(PinyinUtil.covertCnNameToPinyin(profile.getFirstName(), ""));// firstName3
			master.setName2(PinyinUtil.covertCnNameToPinyin(profile.getLastName(), ""));// lastName28
			master.setName4(profile.getLastName() + profile.getFirstName());// 中文名
		} else {
			master.setName(profile.getFirstName());// firstName 3
			master.setName2(profile.getLastName());// lastName 28
		}

		master.setNameTitle(profile.getNameTitle());
		master.setSex(CommonUtil.getSex(profile.getNameTitle()));// 性别4

		// 客户联系地址
		master.setAddressLine(master.getAddressLine());
		master.setPostCode(master.getPostCode());

		// 设置手机号码7
		master.setMobile(master.getMobile());
		master.setHome(master.getHome());
		master.setBusiness(master.getBusiness());
		master.setFax(master.getFax());
		master.setEmail(master.getEmail());

		List<Master> mList = new ArrayList<Master>();
		for (int i = 0; i < master.getNumberOfUnits(); i++) {
			// for (int i = 0; i < 1; i++) {
			// 多房处理
			Master masterN = new Master();
			BeanUtils.copyProperties(master, masterN);

			MasterProfile profile2 = master.getMprofile().get(i);
			if (i > 0) {
				String firstName = profile2.getFirstName();
				if (!StringUtils.hasText(firstName) || profile.getFirstName().equals(firstName)) {
					firstName = profile.getFirstName();
				}
				String lastName = profile2.getLastName();
				if (!StringUtils.hasText(lastName) || profile.getLastName().equals(lastName)) {
					lastName = profile.getLastName() + i;
				}
				if (LanguageCode.ZH_CN.equalsIgnoreCase(master.getLang())) {
					masterN.setName(PinyinUtil.covertCnNameToPinyin(firstName, ""));// firstName3
					masterN.setName2(PinyinUtil.covertCnNameToPinyin(lastName, ""));// lastName28
					masterN.setName4(lastName + firstName);// 中文名
				} else {
					masterN.setName(firstName);// firstName 3
					masterN.setName2(lastName);// lastName 28
				}
				masterN.setNameTitle(profile2.getNameTitle());
				masterN.setSex(CommonUtil.getSex(profile2.getNameTitle()));
			}
			masterN.setRef(ref);

			// 处理子订单1房
			List<MasterRate> mrList = new ArrayList<MasterRate>();
			List<MasterPackage> mpList = new ArrayList<MasterPackage>();
			BigDecimal dynamicPackage = BigDecimal.ZERO;
			BigDecimal staticPakcage = BigDecimal.ZERO;
			for (MasterRate mrO : masterN.getMrList()) {
				MasterRate mr = new MasterRate();
				BeanUtils.copyProperties(mrO, mr);
				BigDecimal ratePackage = BigDecimal.ZERO;
				for (int n = 0; n < mr.getMpList().size(); n++) {
					MasterPackage mp = new MasterPackage();
					BeanUtils.copyProperties(mr.getMpList().get(n), mp);
					if (mp.getIsDynamic()) {
						if (mp.getRooms() - i > 0) {
							ratePackage = AmountUtil.add(ratePackage, mp.getAmount());
							dynamicPackage = AmountUtil.add(dynamicPackage, mp.getAmount());
						} else {
							continue;
						}
					} else {
						ratePackage = AmountUtil.add(ratePackage, mp.getAmount());
						staticPakcage = AmountUtil.add(staticPakcage, mp.getAmount());
					}
					mp.setRooms(1);
					mpList.add(mp);
				}
				mr.setPackages(ratePackage);
				mrList.add(mr);
			}
			masterN.setStaticPackage(staticPakcage);
			masterN.setRmrate(AmountUtil.add(staticPakcage, dynamicPackage));// 总共应收包价
			masterN.setCharge(AmountUtil.add(masterN.getSetrate().divide(new BigDecimal(master.getNumberOfUnits())), masterN.getRmrate()));// 总共应收16
			masterN.setMpList(mpList);
			masterN.setMrList(mrList);

			mList.add(masterN);
		}

		// -----------------重复订单、渠道下单频率-----------start-----------------------
		// redis 中，每分钟处理订单的次数+1
		orderManager.addReverationTime(mList.get(0).getChannelId());
		// firstName 3
		String firstName = mList.get(0).getName();
		// lastName 28
		String lastName = mList.get(0).getName2();
		// 中文名
		String nationName = mList.get(0).getName4();

		String key = mList.get(0).getChannelId() + firstName + lastName + nationName + mList.get(0).getType() + DateUtil.convertDateToString(mList.get(0).getArr());
		Boolean bool = orderManager.isReservation(key, mList.get(0).getChannelId());
		// 开始处理订单
		orderManager.addRedisDealOrder(key, true, 60);
		if (!bool) {
			orderManager.deleReverationKey(key);
			throw new BizException("20");
		}
		// -----------------重复订单、渠道下单频率------------end----------------------
		String orderId = orderManager.saveOrUpdateWBEOrder(master, mList, dbMaster);
		// 订单处理结束
		orderManager.deleReverationKey(key);
		return orderId;
	}

	public Master buildOrder(WBEOrder order, Master master) {

		master.setChannel(WBEConstant.CHANNEL_CODE);

		Master dbMaster = null;
		order.setEnableModifyRN(true);

		// 修改订单的情况
		if (StringUtils.hasText(master.getMasterId())) {
			dbMaster = orderManager.getOrderByOrderNo(master.getMasterId());
			if (dbMaster == null) {
				throw new BizException("R0090");
			}
			// 修改子订单
			if (!"0".equals(dbMaster.getPcrec())) {
				order.setEnableModifyRN(false);
				if (order.getRoomNumber() != 1) {
					throw new BizException("W27");
				}
			}
			// 更新原订单信息
			master.setOcheckinDate(dbMaster.getArr());
			master.setOcheckoutDate(dbMaster.getDep());
			master.setOnumberOfRooms(dbMaster.getNumberOfUnits());
			master.setOtype(dbMaster.getType());
			master.setOchainCode(dbMaster.getChainCode());
			master.setOhotelCode(dbMaster.getHotelCode());
			master.setOchannel(dbMaster.getChannel());
			master.setOratePlanCode(dbMaster.getRatePlanCode());
			master.setOgstno(dbMaster.getGstno());
			master.setOchild(dbMaster.getChild());
			master.setStaticPackage(dbMaster.getStaticPackage());// 静态包价之和
			master.setRmrate(dbMaster.getRmrate());// 总共应收包价
			master.setSetrate(dbMaster.getSetrate());// 房价之和
			master.setCharge(dbMaster.getCharge());// 总共应收16
			master.setChanged(dbMaster.getChanged());// 用于记录changesLog里预订创建时间一致
		}

		master.setChainCode(order.getChainCode());
		master.setHotelCode(order.getHotelCode());
		master.setLang(order.getLanguage());// 语言代码

		ChainVO cvo = chainManager.getChainByCode(order.getChainCode(), order.getLanguage());
		if (cvo == null) {
			throw new BizException("INVALID_CHAINCODE");
		}

		HotelVO hvo = hotelManager.getHotelI18nByCode(order.getHotelCode(), cvo.getChainId(), order.getLanguage());
		if (hvo == null) {
			throw new BizException("INVALID_PROPERTY");
		}
		master.setPmsType(hvo.getPmsType());
		master.setDateFormat(hvo.getDateFormat());
		order.setPayRemind(hvo.getPayRemind());
		order.setPickUpService(hvo.getPickUpService());
		order.setIsAddressRequired(hvo.getIsAddressRequired());
		order.setIsEmailRequird(hvo.getIsEmailRequird());
		order.setIsMobileRequired(hvo.getIsMobileRequired());
		order.setMaxResCount(hvo.getMaxResCount());
		order.setPartner(hvo.getPartner());
		order.setMerchantid(hvo.getMerchant_tid());

		RoomTypeVO rtvo = roomTypeManager.getRoomTypeById(order.getRoomTypeId(), order.getLanguage());
		if (rtvo == null) {
			throw new BizException("R0167");
		}
		//超出最多入住人数
		if(rtvo.getMaxOccupancy()!=null && rtvo.getMaxOccupancy()>0 && rtvo.getMaxOccupancy()<order.getAdult()){
			throw new BizException("30");
		}
		
		String roomTypeCode = rtvo.getRoomTypeCode();
		master.setType(roomTypeCode);
		master.setRoomTypeName(rtvo.getRoomTypeName());

		RatePlanVO rpvo = ratePlanManager.getRatePlanI18nByCodeHotelId(order.getRatePlanCode(), hvo.getHotelId(), order.getLanguage());
		if (rpvo == null) {
			throw new BizException("R0168");
		}
		String ratePlanId = rpvo.getRp().getRatePlanId();
		master.setRatePlanId(ratePlanId);
		master.setRatePlanCode(order.getRatePlanCode());
		master.setMarket(rpvo.getRp().getMarketCode());// 市场代码
		master.setSource(rpvo.getRp().getSourceCode());// 来源代码
		// 房价描述
		if (rpvo.getRpi18n() != null) {
			if (StringUtils.hasText(rpvo.getRpi18n().getRatePlanName())) {
				master.setRatePlanDesc(rpvo.getRpi18n().getRatePlanName());
			} else {
				master.setRatePlanDesc(rpvo.getRpi18n().getDescription());
			}
		}

		// 验证AccessCode
		String type = null;
		String accessCode = order.getCorporateCode();
		if (StringUtils.hasLength(accessCode)) {
			type = ProfileType.CORPORATE.name();
		} else if (StringUtils.hasLength(order.getPromotionCode())) {
			accessCode = order.getPromotionCode();
			type = ProfileType.PROMOTION.name();
		}
		master.setAccessCode(accessCode);
		Custom c = new Custom();
		c.setHotelId(hvo.getHotelId());
		c.setAccessCode(accessCode);
		c.setType(type);
		Custom custom = rateCustomRelationshipDao.getCustomIdByRateCustom(c, ratePlanId);
		if (custom != null) {
			master.setCustomName(custom.getName());
			master.setMfNameCode(custom.getCorpIATANo());
		} else {
			throw new BizException("INVALID_CORPORATE_PROMOTION_CODE");
		}
		master.setQualifyingIdType(type);
		master.setQualifyingIdValue(accessCode);
		master.setAutoDeposit(custom.getAutoDeposit());
		master.setTransactionCode(custom.getTransactionCode());
		master.setTraceDept(custom.getTraceDept());

		master.setGstno(order.getAdult());// 入住人数10

		// 渠道房价绑定关系
		String profileID = "";
		Date rateBeginDate = null;
		Date rateEndDate = null;
		String channelId = null;
		Boolean isCancel = null;
		try {
			ChannelGoodsVO cgvo = new ChannelGoodsVO();
			cgvo.setRoomTypeCode(roomTypeCode);
			cgvo.setRatePlanCode(order.getRatePlanCode());
			cgvo.setHotelId(hvo.getHotelId());
			cgvo.setChannelCode(master.getChannel());
			cgvo.setExpireDate(order.getDep());
			List<ChannelGoodsVO> crpList = channelgoodsManager.getEnabledChannelGoods(cgvo);
			if (crpList != null && !crpList.isEmpty()) {
				ChannelGoodsVO crp = crpList.get(0);
				profileID = String.valueOf(crp.getChannelProfileId());
				rateBeginDate = crp.getEffectiveDate();
				rateEndDate = crp.getExpireDate();
				channelId = crp.getChannelId();
				isCancel = crp.getIsCancel();
			}
		} catch (Exception e) {
			log.warn("getChannelRateGoods is Exception" + e);
			throw new BizException("P0063");
		}
		if (channelId == null) {
			throw new BizException("G05");
		}
		master.setChannelRateId(profileID);
		master.setChannelRateStart(rateBeginDate);
		master.setChannelRateEnd(rateEndDate);
		master.setChannelId(channelId);

		// 取消规则
		List<RateCancelRelationship> rcrList = rateCancelRelationshipDao.getRateCancelPolicyI18nByRatePlanId(hvo.getHotelId(), ratePlanId, order.getLanguage());
		List<MasterCancel> mcList = new ArrayList<MasterCancel>();
		for (RateCancelRelationship rcr : rcrList) {
			MasterCancel mc = new MasterCancel();
			BeanUtils.copyProperties(rcr, mc);
			mc.setChannelIsCancel(isCancel);
			mc.setCancelCode(rcr.getCancelPolicyCode());
			mc.setCancelId(rcr.getCancelId());
			mc.setCreatedBy(OXIConstant.user);
			mc.setCreatedTime(new Date());
			mc.setOffsetCutTime(rcr.getOffsetCutTime());
			// 获取酒店级别的取消规则
			if (StringUtils.hasText(rcr.getDescription())) {
				mc.setCancelDesc(rcr.getDescription());
			} else {
				mc.setCancelDesc(rcr.getPolicyName());
			}
			mcList.add(mc);
		}
		if (mcList.size() == 0) {
			if (custom.getDefCancelId() != null) {
				CancelPolicyVO cp = cancelPolicyDao.getCancelPolicyById(custom.getDefCancelId(), order.getLanguage());
				MasterCancel mc = new MasterCancel();
				BeanUtils.copyProperties(cp, mc);
				mc.setEffectiveDate(rpvo.getRp().getEffectiveDate());
				mc.setExpireDate(rpvo.getRp().getExpireDate());
				mc.setIsApplyToSunday(true);
				mc.setIsApplyToMonday(true);
				mc.setIsApplyToTuesday(true);
				mc.setIsApplyToWednesday(true);
				mc.setIsApplyToThursday(true);
				mc.setIsApplyToFriday(true);
				mc.setIsApplyToSaturday(true);
				mc.setChannelIsCancel(isCancel);
				mc.setCancelCode(cp.getCancelPolicyCode());
				mc.setCancelId(cp.getCancelId());
				mc.setCreatedBy(OXIConstant.user);
				mc.setCreatedTime(new Date());
				mc.setOffsetCutTime(cp.getOffsetCutTime());
				if (StringUtils.hasText(cp.getDescription())) {
					mc.setCancelDesc(cp.getDescription());
				} else {
					mc.setCancelDesc(cp.getPolicyName());
				}
				mcList.add(mc);
			}
		}
		master.setMcList(mcList);

		// 验证房量
		try {
			master.setArr(order.getArr());// 入住日期8
			master.setDep(order.getDep());// 离店日期9
			master.setNumberOfUnits(order.getRoomNumber());
			master.setHotelId(hvo.getHotelId());// 酒店ID22
			master.setRoomTypeId(order.getRoomTypeId());
			master.setChannel(master.getChannel());
			if (rpvo.getRp().getIsSuper() == true) {
				master.setIsSupperRateCode(true);
			}
			orderManager.verifyRoomNumbers(master);
		} catch (Exception e) {
			throw new BizException("P0092");
		}

		// 创建者（记录订单变更记录需求时由渠道传值改为WBE）24
		String operUser = OperUserType.WBE.value();
		master.setCby(operUser);
		master.setUpdatedBy(operUser);
		master.setCreateBY(operUser);

		// 验证房价是否可用
		PriceCalc pc = new PriceCalc();
		pc.setChainCode(order.getChainCode());
		pc.setChannelCode(master.getChannel());
		pc.setHotelCode(order.getHotelCode());
		pc.setNumberOfUnits(order.getAdult());
		pc.setRatePlanCode(order.getRatePlanCode());
		pc.setRoomTypeCode(roomTypeCode);
		pc.setStartDate(DateUtil.convertDateTimeToString(order.getArr()));
		pc.setEndDate(DateUtil.convertDateTimeToString(order.getDep()));
		pc.setHotelId(hvo.getHotelId());
		pc.setChannelId(channelId);
		log.info("pc=" + pc);
		log.info("master=" + master);
		orderBusinessVerifyManager.verifyRoomRateWbe(pc, master);

		// 预付订单
		log.info("master=" + master);
		if (master.getPayment() != null) {
			if (master.getPayment().equalsIgnoreCase(GuaranteeCode.DUE)) {
				// 订单Credit Mgmt逻辑
				if (!creditLimitManager.validCreditLimitForOrder(master)) {
					throw new BizException("22");
				}
			}
		}
		return dbMaster;

	}

	public void cancelOrder(Master master) {
		// 取消订单，修改状态
		if (!OrderStatus.CANCEL.equals(master.getSta())) {
			HotelSwitch hotelSwitch = hotelSwitchDao.getByHotelId(master.getHotelId());
			// 酒店级的HARD CANCEL是否为TRUE，为TRUE时，取消一定成功
			if (hotelSwitch == null || !hotelSwitch.getIsHardCancel()) {
				try {
					orderManager.validCancelPolicy(master, master.getArr(), master.getSetrate(), master.getStaticPackage(), master.getDays());
				} catch (BizException e) {
					throw new BizException("P0062");
				}
			}
			master.setSta(OrderStatus.CANCEL);
			master.setRestype(ReservationStatusType.CANCELED.name());
			master.setMasterId(master.getMasterId());
			master.setCancelTime(DateUtil.currentDateTime());
			master.setCancelType(OWSConstant.cancelType);
			master.setCancelReasonCode(OWSConstant.cancelReasonCode);
			master.setCancelRef("WBE User CancelRef");
			String operUser = OperUserType.WBE.value();
			master.setUpdatedBy(operUser);
			master.setCancelBy(operUser);
			// 总订单
			if ("0".equals(master.getPcrec())) {
				master.setCharge(BigDecimal.ZERO);
				master.setRmrate(BigDecimal.ZERO);
				master.setSetrate(BigDecimal.ZERO);
				master.setStaticPackage(BigDecimal.ZERO);
			}
			// 子订单
			else {
				Master mparent = orderManager.getOrderByOrderNo(master.getPcrec());
				if (mparent != null) {
					mparent.setCharge(AmountUtil.reduce(mparent.getCharge(), master.getCharge()));
					mparent.setRmrate(AmountUtil.reduce(mparent.getRmrate(), master.getRmrate()));
					mparent.setSetrate(AmountUtil.reduce(mparent.getSetrate(), master.getSetrate()));
					mparent.setStaticPackage(AmountUtil.reduce(mparent.getStaticPackage(), master.getStaticPackage()));
					mparent.setUpdatedBy(operUser);
					masterDao.updateMaster(mparent);
					orderChangesLogManager.saveMasterChangesLog(mparent);
				}
			}
			orderManager.cancelOrder(master);
			orderChangesLogManager.saveMasterChangesLog(master);
			oXIReservationNodesService.buildCancelFintrx(master);
			reservationService.buildWBEOrder(master);
		}
	}

	private void getValidGuaranteeType(Master master) {

		String guaranteeType = master.getPayment();
		if (guaranteeType != null) {
			try {
				orderManager.getEnableGuaranteeType(master);
			} catch (BizException e) {
				if (("18").equals(e.getErrKey())) {
					throw new BizException("G03");
				} else {
					throw new BizException("P0060");
				}

			} catch (Exception e) {
				log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
				throw new BizException("P0063");
			}

			GuaranteePolicyVO gpvo = guaranteePolicyDao.getGuaranteePolicyByCode(guaranteeType, master.getLang());
			if (gpvo != null) {
				master.setGuaranteeDesc(gpvo.getDescription());
			}
		}
	}

}
