/**
 * 
 */
package com.ccm.api.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.ratePlan.CancelPolicyDao;
import com.ccm.api.dao.ratePlan.GuaranteePolicyDao;
import com.ccm.api.dao.ratePlan.RateCancelRelationshipDao;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.dao.ratePlan.RateGuaranteeRelationshipDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.enume.PaymentType2TB;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.RateCancelRelationship;
import com.ccm.api.model.ratePlan.RateCustomRelationship;
import com.ccm.api.model.ratePlan.RateGuaranteeRelationship;
import com.ccm.api.model.ratePlan.RatePackage;
import com.ccm.api.model.ratePlan.SoldableCondition;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.model.rest.AreaInfo;
import com.ccm.api.model.rest.CancelPolicy;
import com.ccm.api.model.rest.ContactInfo;
import com.ccm.api.model.rest.Guarantee;
import com.ccm.api.model.rest.HotelInfo;
import com.ccm.api.model.rest.HotelName;
import com.ccm.api.model.rest.Name;
import com.ccm.api.model.rest.RatePlan;
import com.ccm.api.model.rest.RoomType;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.ratePlan.RatePackageManager;
import com.ccm.api.service.system.AbstractPush2ChannelProtocolService;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
@Service("push2ChannelProtocolGeneralService")
public class Push2ChannelProtocolGeneralServiceImpl extends AbstractPush2ChannelProtocolService {

	@Resource
	private GuaranteePolicyDao guaranteePolicyDao;
	@Resource
	private RateCancelRelationshipDao rateCancelRelationshipDao;

	@Resource
	private RatePackageManager ratePackageManager;
	@Resource
	private RateCustomRelationshipDao rateCustomRelationshipDao;
	@Resource
	private CancelPolicyDao cancelPolicyDao;
	@Resource
	private RateGuaranteeRelationshipDao rateGuaranteeRelationshipDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccm.api.service.system.AbstractPush2ChannelProtocolService#buildRateOnOff
	 * ()
	 */
	@Override
	public Object buildRateOnOff(String chainCode, String hotelCode, String ratePlanCode, String status) {
		RatePlan rp = new RatePlan();
		// status
		if (ChannelGoodsStatus.OFF.equals(status)) {
			rp.setStatus(2);
		} else {
			rp.setStatus(1);
		}
		rp.setRateCode(ratePlanCode);
		rp.setHotelCode(hotelCode);
		rp.setChainCode(chainCode);
		return rp;
	}

	@Override
	public Object buildHotelMsg(HotelVO hvo) throws Exception {// 构建hotelInfo
		HotelInfo hInfo = new HotelInfo();
		// hotelName
		HotelName hn = new HotelName();
		hn.setValue(hvo.getHotelName());
		hn.setLanguageCode(hvo.getLanguageCode());
		hInfo.setHotelName(hn);
		// areaInfo
		AreaInfo ai = new AreaInfo();
		ai.setCityName(hvo.getCityName());
		ai.setAddress(hvo.getAddress());
		hInfo.setAreaInfo(ai);
		// contactInfo
		ContactInfo ci = new ContactInfo();
		ci.setPhone(hvo.getTelephone());
		// 多个email
		if (StringUtils.hasText(hvo.getEmail())) {
			String emailArr[] = hvo.getEmail().split(";");
			for (String email : emailArr) {
				ci.getEmail().add(email);
			}
		}
		hInfo.setContactInfo(ci);
		// other
		hInfo.setHotelCode(hvo.getHotelCode());
		hInfo.setCurrencyCode(hvo.getCurrencyCode());
		hInfo.setChainCode(hvo.getChainCode());
		hInfo.setSeller(hvo.getTbShopName());
		return hInfo;
	}

	@Override
	public Object buildRoomMsg(String chainCode, RoomTypeVO rtvo, String channelId) {
		// 构建RoomType
		RoomType rt = new RoomType();
		// roomTypeName
		Name n = new Name();
		n.setValue(rtvo.getRoomTypeName());
		n.setLanguage(rtvo.getLanguage());
		rt.setName(n);
		// other
		rt.setHotelCode(rtvo.getHotelCode());
		rt.setRoomTypeCode(rtvo.getRoomTypeCode());
		rt.setChainCode(chainCode);
		return rt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object buildRateMsg(RatePlanVO rpvo, String status, String language, String channelId) throws Exception {
		RatePlan rp = new RatePlan();
		RatePlan.Name name = new RatePlan.Name();
		if (rpvo.getRpi18n() != null) {
			name.setValue(rpvo.getRpi18n().getRatePlanName());
		}
		name.setLanguageCode(language);
		rp.setName(name);
		// rp.setMembership("");
		List<SoldableCondition> scList = rpvo.getRp().getSCList();
		if (CommonUtil.isNotEmpty(scList)) {
			for (SoldableCondition soldableCondition : scList) {
				if (soldableCondition.getMinEvenDay() != null) {
					rp.setMinLOS(soldableCondition.getMinEvenDay());
				}
				if (soldableCondition.getMaxEvenDay() != null) {
					rp.setMaxLOS(soldableCondition.getMaxEvenDay());
				}
				if (soldableCondition.getMinBeforehandDay() != null) {
					rp.setMinAdv(soldableCondition.getMinBeforehandDay());
				}
				if (soldableCondition.getMaxBeforehandDay() != null) {
					rp.setMaxAdv(soldableCondition.getMaxBeforehandDay());
				}
			}
		}
		// CCM的早餐在RATE HEADER中，判断两个CODE， 一个BB1，表示含 1个早餐，另一个是BKF50，表示含2个早餐
		List<RatePackage> rpList = ratePackageManager.getRatePackageByRatePlanId(rpvo.getRp().getRatePlanId());
		if (rpList != null) {
			for (RatePackage packag : rpList) {
				if ("BB1".equalsIgnoreCase(packag.getPackageCode())) {
					rp.setBreakfast(1);
					break;
				} else if ("BKF50".equalsIgnoreCase(packag.getPackageCode())) {
					rp.setBreakfast(2);
					break;
				} else if ("BKF".equalsIgnoreCase(packag.getPackageCode())) {
					rp.setBreakfast(-1);
					break;
				}
			}
		}
		// paymentType
		Custom custom = new Custom();
		List<Custom> customList = rateCustomRelationshipDao.getCustomByRatePlanId(rpvo.getRp().getRatePlanId());
		for (Custom customRec : customList) {
			if (CommonUtil.isNotEmpty(custom.getDefGuaranteeId()))
				break;
			if (CommonUtil.isNotEmpty(customRec.getChannelId())) {
				if (customRec.getChannelId().equalsIgnoreCase(channelId)) {
					RateCustomRelationship rateCustomRelationship = rateCustomRelationshipDao.getCustomByRateCustom(customRec, rpvo.getRp().getRatePlanCode());
					if (CommonUtil.isNotEmpty(rateCustomRelationship)) {
						if (CommonUtil.isNotEmpty(customRec.getDefGuaranteeId())) {
							custom.setDefGuaranteeId(customRec.getDefGuaranteeId());
							custom.setDefCancelId(customRec.getDefCancelId());
						}
					}
				}
			}
		}

		// rateGuaranteeManager
		RateGuaranteeRelationship rgr = new RateGuaranteeRelationship();
		rgr.setRatePlanId(rpvo.getRp().getRatePlanId());
		rgr.setEffectiveDate(DateUtil.currentDate());
		List<GuaranteePolicy> gpList = rateGuaranteeRelationshipDao.getEffectiveRateGuaranteeByRatePlanId(rgr);
		// valid expired date of gp
		GuaranteePolicy gp = null;
		// add default guarantee policy
		if (gpList == null)
			gpList = new ArrayList<GuaranteePolicy>();
		if (CommonUtil.isEmpty(gpList) && custom.getDefGuaranteeId() != null) {
			GuaranteePolicy defGP = guaranteePolicyDao.getGuaranteePolicyById(custom.getDefGuaranteeId(), language);
			if (defGP != null)
				gpList.add(defGP);
		}
		if (CommonUtil.isNotEmpty(gpList)) {
			gp = gpList.get(0);
			Integer pt = PaymentType2TB.getIdFromName(gp.getGuaranteeCode());
			if (pt != null) {
				rp.setPaymentType(pt);
			}
		}
		if (gp == null) {
			throw new BizException("没有可用的担保规则！");
		}
		// guaranteeType,cancelPolicy
		Map<String, Object> map = dealCancelPolicyAndGuarantee(rpvo.getRp(), custom.getDefCancelId(), language, gp);
		Object guarantee = map.get("guaranteeList");
		if (guarantee != null) {
			rp.setGuarantee((List<Guarantee>) guarantee);
		}

		Object cancelPolicy = map.get("cancelPolicyList");
		if (cancelPolicy != null) {
			rp.setCancelPolicy((List<CancelPolicy>) cancelPolicy);
		}

		// status
		if (ChannelGoodsStatus.OFF.equals(status)) {
			rp.setStatus(2);
		} else {
			rp.setStatus(1);
		}
		rp.setRateCode(rpvo.getRp().getRatePlanCode());
		rp.setHotelCode(rpvo.getRp().getHotelCode());
		rp.setChainCode(rpvo.getChainCode());
		return rp;
	}

	private Map<String, Object> dealCancelPolicyAndGuarantee(Rateplan rp, String defaultCancelId, String language, GuaranteePolicy gp) throws Exception {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CancelPolicy> cpListXml = new ArrayList<CancelPolicy>();
		List<Guarantee> gtListXml = new ArrayList<Guarantee>();
		// Date day180 = DateUtil.addDays(DateUtil.currentDate(), 179);

		List<RateCancelRelationship> cancelRuleList = rateCancelRelationshipDao.getRateCancelPolicyByRatePlanId(rp.getRatePlanId());
		if (cancelRuleList == null)
			cancelRuleList = new ArrayList<RateCancelRelationship>();
		if (cancelRuleList.isEmpty() && CommonUtil.isNotEmpty(defaultCancelId)) {
			CancelPolicyVO cp = cancelPolicyDao.getCancelPolicyById(defaultCancelId, language);
			if (CommonUtil.isNotEmpty(cp)) {
				RateCancelRelationship rr = new RateCancelRelationship();
				BeanUtils.copyProperties(cp, rr);
				rr.setEffectiveDate(rp.getEffectiveDate());
				rr.setExpireDate(rp.getExpireDate());
				rr.setIsApplyToSunday(true);
				rr.setIsApplyToMonday(true);
				rr.setIsApplyToTuesday(true);
				rr.setIsApplyToWednesday(true);
				rr.setIsApplyToThursday(true);
				rr.setIsApplyToFriday(true);
				rr.setIsApplyToSaturday(true);
				rr.setCancelId(cp.getCancelId());
				rr.setCancelPolicyCode(cp.getCancelPolicyCode());
				rr.setCreatedBy(OXIConstant.user);
				rr.setCreatedTime(new Date());
				rr.setDescription(cp.getDescription());
				rr.setPolicyName(cp.getPolicyName());
				cancelRuleList.add(rr);
			}
		}
		// 无取消规则
		if (cancelRuleList == null || cancelRuleList.isEmpty()) {
			CancelPolicy cp = new CancelPolicy();
			cp.setType(1);
			cp.setPolicyInfo("{\"cancelPolicyType\":1}");
			cpListXml.add(cp);
			resMap.put("cancelPolicyList", cpListXml);
			return resMap;
		}

		for (RateCancelRelationship rc : cancelRuleList) {
			if (rc.getEffectiveDate().before(DateUtil.currentDate())) {
				rc.setEffectiveDate(DateUtil.currentDate());
			}
			// if (rc.getExpireDate().after(day180)) {
			// rc.setExpireDate(day180);
			// }
			// 获取日期区间段
			Map<String, String> dateBlockMap = getDateBlock(DateUtil.getWeekDay(rc.getEffectiveDate(), rc.getExpireDate(), getWeek(rc)));
			for (Entry<String, String> e : dateBlockMap.entrySet()) {
				String startDate = e.getKey();
				String endDate = e.getValue();
				CancelPolicy cp = dealCancelPolicy(rc, startDate, endDate);
				cpListXml.add(cp);
			}
		}
		CancelPolicy defaultCp = new CancelPolicy();
		if ("6PM,6PM_GT,PREPAID,PREPAID_GT,VOUCHER".indexOf(gp.getGuaranteeCode()) > -1) {
			defaultCp.setType(1);
			defaultCp.setPolicyInfo("{\"cancelPolicyType\":1}");
		} else if ("TAGTD".equals(gp.getGuaranteeCode())) {
			defaultCp.setType(2);
			defaultCp.setPolicyInfo("{\"cancelPolicyType\":2}");
		}
		cpListXml.add(defaultCp);
		resMap.put("cancelPolicyList", cpListXml);

		for (CancelPolicy cancelPolicy : cpListXml) {
			Guarantee gt = getTbGuaranteeByCancelPolicy(cancelPolicy);
			if (gt != null) {
				gtListXml.add(gt);
			}
		}
		resMap.put("guaranteeList", gtListXml);
		return resMap;
	}

	private CancelPolicy dealCancelPolicy(RateCancelRelationship rc, String startDate, String endDate) {
		CancelPolicy cp = new CancelPolicy();
		cp.setStartDate(startDate);
		cp.setEndDate(endDate);
		cp.setPercent(rc.getPercent());
		cp.setBasisType(rc.getBasisType());

		Map<String, Object> policyInfo = new HashMap<String, Object>();
		if (rc.getOffsetDropTime() != null && rc.getOffsetDropTime() == 1) {//
			if (rc.getBasisType() != null) {
				if (rc.getBasisType() == 1) {// FULLSTAY
					if (rc.getPercent() != null) {
						if (rc.getPercent().intValue() == 100) {
							cp.setType(5);
						} else {
							cp.setType(4);
						}
					}
				} else if (rc.getBasisType() == 2 || rc.getBasisType() == 3) {// NIGHTS
					cp.setType(6);
				}
			}

			if (rc.getOffsetTimeUnit() != null && rc.getOffsetUnitMultiplier() != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				String hours = "";
				if (rc.getOffsetTimeUnit() == 1) {// days
					hours = rc.getOffsetUnitMultiplier().intValue() * 24 + "";
				} else if (rc.getOffsetTimeUnit() == 2) {// hours
					hours = rc.getOffsetUnitMultiplier().intValue() + "";
				}
				if (cp.getType() != null) {
					if (cp.getType() == 5) {
						if (CommonUtil.isNotEmpty(hours)) {
							map.put("timeBefore", Integer.parseInt(hours));
						}
					} else if (cp.getType() == 6) {
						if (CommonUtil.isNotEmpty(rc.getNumberOfNights())) {
							map.put(hours, rc.getNumberOfNights().intValue());
						}
					} else if (cp.getType() == 4) {
						if (CommonUtil.isNotEmpty(rc.getPercent())) {
							map.put(hours, rc.getPercent().floatValue());
						}
					}
					policyInfo.put("cancelPolicyType", cp.getType());
				}
				if (CommonUtil.isNotEmpty(hours)) {
					cp.setBeforeHour(Integer.parseInt(hours));
				}
				if (CommonUtil.isNotEmpty(map)) {
					policyInfo.put("policyInfo", map);
				}
				if (CommonUtil.isNotEmpty(policyInfo)) {
					cp.setPolicyInfo(JSONObject.toJSONString(policyInfo));
				}
			}
		} else {
			if (CommonUtil.isNotEmpty(rc.getIsNonRefundable()) && rc.getIsNonRefundable()) {
				cp.setType(2);
				policyInfo.put("cancelPolicyType", cp.getType());
				cp.setPolicyInfo(JSONObject.toJSONString(policyInfo));
			}
		}
		return cp;
	}

	private Guarantee getTbGuaranteeByCancelPolicy(CancelPolicy cancelPolicy) {
		Guarantee gt = new Guarantee();

		Integer cancelType = cancelPolicy.getType();
		if (cancelType != null) {
			switch (cancelType) {
			case 5:
				gt.setType("2");
				break;
			case 6:
				gt.setType("1");
				break;
			case 4:
				gt.setType("2");
				break;
			case 2:
				gt.setType("4");
				break;
			}
		}

		if (cancelPolicy.getBeforeHour() != null) {
			if (cancelPolicy.getBeforeHour() < 24) {
				gt.setStartTime(24 - cancelPolicy.getBeforeHour() + "");
			} else if (cancelPolicy.getBeforeHour() >= 24) {
				gt.setStartTime(null);
				gt.setType("3");
				if (cancelPolicy.getBasisType() != null && cancelPolicy.getPercent() != null) {
					if (cancelPolicy.getBasisType() == 1 && cancelPolicy.getPercent().intValue() == 100) {
						gt.setType("4");
					}
				}
			}

			if ("24".equals(gt.getStartTime())) {
				gt.setStartTime("00");
			}
		}
		gt.setStartDate(cancelPolicy.getStartDate());
		gt.setEndDate(cancelPolicy.getEndDate());

		if (cancelType != null && cancelType != 1) {
			return gt;
		}
		return null;
	}

	@Override
	public String buildHotelProductMsg(Map<String, Object> map) {
		return null;
	}

}
