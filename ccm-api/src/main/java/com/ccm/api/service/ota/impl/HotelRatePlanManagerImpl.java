/**
 * 
 */
package com.ccm.api.service.ota.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opentravel.ota._2003._05.AmountPercentType;
import org.opentravel.ota._2003._05.BookingRulesType.BookingRule;
import org.opentravel.ota._2003._05.CancelPenaltiesType;
import org.opentravel.ota._2003._05.CancelPenaltyType;
import org.opentravel.ota._2003._05.CancelPenaltyType.Deadline;
import org.opentravel.ota._2003._05.FormattedTextTextType;
import org.opentravel.ota._2003._05.HotelRatePlanType.BookingRules;
import org.opentravel.ota._2003._05.HotelRatePlanType.Rates.Rate;
import org.opentravel.ota._2003._05.ObjectFactory;
import org.opentravel.ota._2003._05.ParagraphType;
import org.opentravel.ota._2003._05.RateUploadType.MealsIncluded;
import org.opentravel.ota._2003._05.RequiredPaymentsType;
import org.opentravel.ota._2003._05.RequiredPaymentsType.GuaranteePayment;
import org.opentravel.ota._2003._05.RequiredPaymentsType.GuaranteePayment.AmountPercent;
import org.opentravel.ota._2003._05.SellableProductsType;
import org.opentravel.ota._2003._05.SellableProductsType.SellableProduct;
import org.opentravel.ota._2003._05.TPAExtensionsType;
import org.opentravel.ota._2003._05.TimeUnitType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.ccm.api.dao.ratePlan.CancelPolicyDao;
import com.ccm.api.dao.ratePlan.GuaranteePolicyDao;
import com.ccm.api.dao.ratePlan.RateCancelRelationshipDao;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.dao.ratePlan.RateGuaranteeRelationshipDao;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.enume.PaymentType2TB;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.RateCancelRelationship;
import com.ccm.api.model.ratePlan.RateCustomRelationship;
import com.ccm.api.model.ratePlan.RateGuaranteeRelationship;
import com.ccm.api.model.ratePlan.RatePackage;
import com.ccm.api.model.ratePlan.SoldableCondition;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.service.ota.HotelRatePlanManager;
import com.ccm.api.service.ratePlan.RatePackageManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.DateUtils;
import com.ccm.api.util.XMLUtil;

/**
 * @author Jenny
 * 
 */
@Service("hotelRatePlanManager")
public class HotelRatePlanManagerImpl implements HotelRatePlanManager {

	private Log log = LogFactory.getLog(HotelRatePlanManagerImpl.class);

	@Resource
	private GuaranteePolicyDao guaranteePolicyDao;
	@Resource
	private RatePackageManager ratePackageManager;
	@Resource
	private RateCustomRelationshipDao rateCustomRelationshipDao;
	@Resource
	private RateGuaranteeRelationshipDao rateGuaranteeRelationshipDao;
	@Resource
	private RateCancelRelationshipDao rateCancelRelationshipDao;
	@Resource
	private CancelPolicyDao cancelPolicyDao;

	public BookingRules buildBookingRules(List<SoldableCondition> scList, SellableProductsType sellableProducts, Rate rate) throws Exception {
		// BookingRules
		BookingRules bookingRules = new BookingRules();
		Integer minLos = null;
		Integer maxLos = null;
		if (CommonUtil.isNotEmpty(scList)) {
			for (SoldableCondition soldableCondition : scList) {
				// BookingRule
				BookingRule bookingRule = new BookingRule();
				if (soldableCondition.getMinEvenDay() != null) {
					minLos = soldableCondition.getMinEvenDay();
				}
				if (soldableCondition.getMaxEvenDay() != null) {
					maxLos = soldableCondition.getMaxEvenDay();
				}
				DatatypeFactory datatypeFactory = null;
				if (soldableCondition.getMinBeforehandDay() != null) {
					datatypeFactory = DatatypeFactory.newInstance();
					Duration durationMin = datatypeFactory.newDurationDayTime(true, soldableCondition.getMinBeforehandDay(), 0, 0, 0);
					String durationMinStr = durationMin.toString();
					durationMinStr = durationMinStr.substring(0, durationMinStr.indexOf("T"));
					bookingRule.setMinAdvancedBookingOffset(datatypeFactory.newDuration(durationMinStr));
				}
				if (soldableCondition.getMaxBeforehandDay() != null) {
					if (datatypeFactory == null) {
						datatypeFactory = DatatypeFactory.newInstance();
					}
					Duration durationMax = datatypeFactory.newDurationDayTime(true, soldableCondition.getMaxBeforehandDay(), 0, 0, 0);
					String durationMaxStr = durationMax.toString();
					durationMaxStr = durationMaxStr.substring(0, durationMaxStr.indexOf("T"));
					bookingRule.setMaxAdvancedBookingOffset(datatypeFactory.newDuration(durationMaxStr));
				}
				bookingRules.getBookingRule().add(bookingRule);
				// SellableProducts
				String start = soldableCondition.getStartDate();
				String end = soldableCondition.getEndDate();
				if (StringUtils.hasText(start) || StringUtils.hasText(end)) {
					SellableProduct sellableProduct = new SellableProduct();
					sellableProduct.setStart(start);
					sellableProduct.setEnd(end);
					sellableProducts.getSellableProduct().add(sellableProduct);
				}
			}
		}

		if (minLos != null) {
			rate.setMinLOS(String.valueOf(minLos));
		}
		if (maxLos != null) {
			rate.setMaxLOS(String.valueOf(maxLos));
		}

		return bookingRules;
	}

	public ParagraphType buildDescription(String ratePlanName, String language) {
		FormattedTextTextType formatText = new FormattedTextTextType();
		formatText.setLanguage(language);
		formatText.setValue(ratePlanName);
		JAXBElement<FormattedTextTextType> text = (new ObjectFactory()).createParagraphTypeText(formatText);
		ParagraphType paragraphType = new ParagraphType();
		paragraphType.setName("RatePlanName");
		paragraphType.getTextOrImageOrURL().add(text);
		return paragraphType;
	}

	@SuppressWarnings("unchecked")
	public TPAExtensionsType buildTPAExtensions(String rateCode, String ratePlanId, String channelId, String language, Rate rate, GuaranteePolicy gp) throws Exception {

		StringBuffer TPAExtension = new StringBuffer();
		TPAExtension.append("<TPA_Extension RatePlanCode=\"").append(rateCode).append("\">");

		// paymentType
		Custom custom = new Custom();
		List<Custom> customList = rateCustomRelationshipDao.getCustomByRatePlanId(ratePlanId);
		for (Custom customRec : customList) {
			if (CommonUtil.isNotEmpty(custom.getDefGuaranteeId()))
				break;
			if (CommonUtil.isNotEmpty(customRec.getChannelId())) {
				if (customRec.getChannelId().equalsIgnoreCase(channelId)) {
					RateCustomRelationship rateCustomRelationship = rateCustomRelationshipDao.getCustomByRateCustom(customRec, rateCode);
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
		rgr.setRatePlanId(ratePlanId);
		rgr.setEffectiveDate(DateUtil.currentDate());
		List<GuaranteePolicy> gpList = rateGuaranteeRelationshipDao.getEffectiveRateGuaranteeByRatePlanId(rgr);
		// add default guarantee policy
		if (gpList == null)
			gpList = new ArrayList<GuaranteePolicy>();
		if (CommonUtil.isNotEmpty(custom.getDefGuaranteeId())) {
			GuaranteePolicy defGP = guaranteePolicyDao.getGuaranteePolicyById(custom.getDefGuaranteeId(), language);
			if (defGP != null) {
				gpList.add(defGP);
			}
		}
		String guaranteeCode = null;
		if (CommonUtil.isNotEmpty(gpList)) {
			BeanUtils.copyProperties(gpList.get(0), gp);
			guaranteeCode = gp.getGuaranteeCode();
			Integer pt = PaymentType2TB.getIdFromName(guaranteeCode);
			if (pt != null) {
				TPAExtension.append("<Extension Name=\"Payment\"><Item Key=\"Type\" Value=\"").append(pt).append("\"/></Extension>");
			}
		}
		// CCM的早餐在RATE HEADER中，判断两个CODE， 一个BB1，表示含 1个早餐，另一个是BKF50，表示含2个早餐
		Integer breakfast = null;
		List<RatePackage> rpList = ratePackageManager.getRatePackageByRatePlanId(ratePlanId);
		if (rpList != null) {
			for (RatePackage packag : rpList) {
				if ("BB1".equalsIgnoreCase(packag.getPackageCode())) {
					breakfast = 1;
					break;
				} else if ("BKF50".equalsIgnoreCase(packag.getPackageCode())) {
					breakfast = 2;
					break;
				} else if ("BKF".equalsIgnoreCase(packag.getPackageCode())) {
					breakfast = -1;
					break;
				}
			}
		}
		if (breakfast != null) {
			MealsIncluded mealsIncluded = new MealsIncluded();
			mealsIncluded.setBreakfast(true);
			rate.setMealsIncluded(mealsIncluded);
			TPAExtension.append("<Extension Name=\"Meal\"><Item Key=\"Breakfast\" Value=\"").append(breakfast).append("\"/></Extension>");
		}

		TPAExtension.append("</TPA_Extension>");
		// TPA_Extensions
		Element element = XMLUtil.parse(XMLUtil.getXmlDocument(TPAExtension.toString())).getDocumentElement();
		TPAExtensionsType TPAExtensionsType = new TPAExtensionsType();
		TPAExtensionsType.getAny().add(element);

		// guaranteeType,cancelPolicy
		Map<String, Object> map = dealCancelPolicyAndGuarantee(ratePlanId, custom.getDefCancelId(), language, guaranteeCode);
		Object guarantee = map.get("guaranteeList");
		if (guarantee != null) {
			RequiredPaymentsType requiredPaymentsType = new RequiredPaymentsType();
			requiredPaymentsType.getGuaranteePayment().addAll((List<GuaranteePayment>) guarantee);
			rate.setPaymentPolicies(requiredPaymentsType);
		}

		Object cancelPolicy = map.get("cancelPolicyList");
		if (cancelPolicy != null) {
			CancelPenaltiesType cancelPenaltiesType = new CancelPenaltiesType();
			cancelPenaltiesType.getCancelPenalty().addAll((List<CancelPenaltyType>) cancelPolicy);
			rate.setCancelPolicies(cancelPenaltiesType);
		}

		return TPAExtensionsType;
	}

	private Map<String, Object> dealCancelPolicyAndGuarantee(String ratePlanId, String defaultCancelId, String language, String guaranteeCode) throws Exception {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CancelPenaltyType> cpListXml = new ArrayList<CancelPenaltyType>();
		List<GuaranteePayment> gtListXml = new ArrayList<GuaranteePayment>();
		// Date day180 = DateUtil.addDays(DateUtil.currentDate(), 179);

		List<RateCancelRelationship> cancelRuleList = rateCancelRelationshipDao.getRateCancelPolicyByRatePlanId(ratePlanId);
		if (cancelRuleList == null)
			cancelRuleList = new ArrayList<RateCancelRelationship>();
		if (CommonUtil.isNotEmpty(defaultCancelId)) {
			CancelPolicyVO cp = cancelPolicyDao.getCancelPolicyById(defaultCancelId, language);
			if (CommonUtil.isNotEmpty(cp)) {
				RateCancelRelationship rr = new RateCancelRelationship();
				BeanUtils.copyProperties(cp, rr);
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
			CancelPenaltyType cp = getCancelTypeOne();
			cpListXml.add(cp);
			resMap.put("cancelPolicyList", cpListXml);
			return resMap;
		}

		for (RateCancelRelationship rc : cancelRuleList) {
			if (CommonUtil.isNotEmpty(rc.getEffectiveDate())) {
				if (rc.getEffectiveDate().before(DateUtil.currentDate())) {
					rc.setEffectiveDate(DateUtil.currentDate());
				}
			}
			// if (rc.getExpireDate().after(day180)) {
			// rc.setExpireDate(day180);
			// }
			// convert cancel policy to OTA object
			CancelPenaltyType cp = dealCancelPolicy(rc);
			cpListXml.add(cp);
		}
		if (CommonUtil.isEmpty(defaultCancelId) && StringUtils.hasText(guaranteeCode)) {
			if ("6PM,6PM_GT,PREPAID,PREPAID_GT,VOUCHER".indexOf(guaranteeCode) > -1) {
				CancelPenaltyType defaultCp = getCancelTypeOne();
				cpListXml.add(defaultCp);
			} else if ("TAGTD".equals(guaranteeCode)) {
				CancelPenaltyType defaultCp = new CancelPenaltyType();
				defaultCp = getCancelTypeTwo(defaultCp);
				cpListXml.add(defaultCp);
			}
		}
		resMap.put("cancelPolicyList", cpListXml);

		for (CancelPenaltyType cancelPolicy : cpListXml) {
			GuaranteePayment gt = getTbGuaranteeByCancelPolicy(cancelPolicy);
			if (gt != null) {
				gtListXml.add(gt);
			}
		}
		resMap.put("guaranteeList", gtListXml);
		return resMap;
	}

	private CancelPenaltyType dealCancelPolicy(RateCancelRelationship rc) {
		CancelPenaltyType cp = new CancelPenaltyType();
		if (CommonUtil.isNotEmpty(rc.getEffectiveDate())) {
			cp.setStart(DateUtil.getDate(rc.getEffectiveDate()));
		}
		if (CommonUtil.isNotEmpty(rc.getExpireDate())) {
			cp.setEnd(DateUtil.getDate(rc.getExpireDate()));
		}
		cp.setPercent(rc.getPercent());
		cp.setBasisType(rc.getBasisType());

		if (BooleanUtils.isTrue(rc.getIsApplyToSunday())) {
			cp.setSun(true);
		}
		if (BooleanUtils.isTrue(rc.getIsApplyToMonday())) {
			cp.setMon(true);
		}
		if (BooleanUtils.isTrue(rc.getIsApplyToTuesday())) {
			cp.setTue(true);
		}
		if (BooleanUtils.isTrue(rc.getIsApplyToWednesday())) {
			cp.setWeds(true);
		}
		if (BooleanUtils.isTrue(rc.getIsApplyToThursday())) {
			cp.setThur(true);
		}
		if (BooleanUtils.isTrue(rc.getIsApplyToFriday())) {
			cp.setFri(true);
		}
		if (BooleanUtils.isTrue(rc.getIsApplyToSaturday())) {
			cp.setSat(true);
		}

		Integer cancelType = null;
		if (rc.getOffsetDropTime() != null && rc.getOffsetDropTime() == 1) {//
			if (rc.getBasisType() != null) {
				if (rc.getBasisType() == 1) {// FULLSTAY
					if (rc.getPercent() != null) {
						if (rc.getPercent().intValue() == 100) {
							cancelType = 5;
						} else {
							cancelType = 4;
						}
					}
				} else if (rc.getBasisType() == 2 || rc.getBasisType() == 3) {// NIGHTS
					cancelType = 6;
				}
			}
			if (cancelType == null) {
				return cp;
			}
			if (rc.getOffsetTimeUnit() != null && rc.getOffsetUnitMultiplier() != null) {
				if (rc.getOffsetTimeUnit() == 1) {// days
					Integer days = rc.getOffsetUnitMultiplier().intValue();
					Deadline deadline = new Deadline();
					deadline.setOffsetDropTime("BeforeArrival");
					deadline.setOffsetTimeUnit(TimeUnitType.DAY);
					deadline.setOffsetUnitMultiplier(days);
					deadline.setAbsoluteDeadline(getOffsetCutTime(rc.getOffsetCutTime()));
					log.info(deadline.getAbsoluteDeadline());
					cp.setDeadline(deadline);
				} else if (rc.getOffsetTimeUnit() == 2) {// hours
					Integer hours = rc.getOffsetUnitMultiplier().intValue();
					Deadline deadline = new Deadline();
					deadline.setOffsetDropTime("BeforeArrival");
					deadline.setOffsetTimeUnit(TimeUnitType.HOUR);
					deadline.setOffsetUnitMultiplier(hours);
					deadline.setAbsoluteDeadline(getOffsetCutTime(rc.getOffsetCutTime()));
					log.info(deadline.getAbsoluteDeadline());
					cp.setDeadline(deadline);
				}
			}
			if (cancelType == 5) {
				AmountPercentType amountPercentType = new AmountPercentType();
				amountPercentType.setAmount(BigDecimal.ZERO);
				cp.setAmountPercent(amountPercentType);
				FormattedTextTextType t = new FormattedTextTextType();
				t.setValue("5-从24点往前推多少小时可退");
				JAXBElement<FormattedTextTextType> j = (new ObjectFactory()).createParagraphTypeText(t);
				j.setValue(t);
				ParagraphType paragraphType = new ParagraphType();
				paragraphType.getTextOrImageOrURL().add(j);
				cp.getPenaltyDescription().add(paragraphType);
			} else if (cancelType == 6) {
				if (CommonUtil.isNotEmpty(rc.getNumberOfNights())) {
					AmountPercentType amountPercentType = new AmountPercentType();
					amountPercentType.setBasisType("Nights");
					amountPercentType.setNmbrOfNights(BigInteger.valueOf(rc.getNumberOfNights()));
					cp.setAmountPercent(amountPercentType);
				}
				FormattedTextTextType t = new FormattedTextTextType();
				t.setValue("6-从入住日24点往前推，至少提前小时数扣取首晚房费");
				JAXBElement<FormattedTextTextType> j = (new ObjectFactory()).createParagraphTypeText(t);
				j.setValue(t);
				ParagraphType paragraphType = new ParagraphType();
				paragraphType.getTextOrImageOrURL().add(j);
				cp.getPenaltyDescription().add(paragraphType);
			} else if (cancelType == 4) {
				if (CommonUtil.isNotEmpty(rc.getPercent())) {
					AmountPercentType amountPercentType = new AmountPercentType();
					amountPercentType.setBasisType("FullStay");
					amountPercentType.setPercent(new BigDecimal(rc.getPercent()));
					cp.setAmountPercent(amountPercentType);
				}
				FormattedTextTextType t = new FormattedTextTextType();
				t.setValue("4-从入住当天24点往前推X小时前取消收取Y%手续费");
				JAXBElement<FormattedTextTextType> j = (new ObjectFactory()).createParagraphTypeText(t);
				j.setValue(t);
				ParagraphType paragraphType = new ParagraphType();
				paragraphType.getTextOrImageOrURL().add(j);
				cp.getPenaltyDescription().add(paragraphType);
			}
		} else {
			if (CommonUtil.isNotEmpty(rc.getIsNonRefundable()) && rc.getIsNonRefundable()) {
				cp = getCancelTypeTwo(cp);
			}
		}
		return cp;
	}

	private GuaranteePayment getTbGuaranteeByCancelPolicy(CancelPenaltyType cancelPolicy) {
		GuaranteePayment gt = new GuaranteePayment();

		Integer cancelType = null;
		List<ParagraphType> penaltyDescList = cancelPolicy.getPenaltyDescription();
		if (!penaltyDescList.isEmpty()) {
			String cancelTypeStr = ((FormattedTextTextType) penaltyDescList.get(0).getTextOrImageOrURL().get(0).getValue()).getValue();
			if (cancelTypeStr != null) {
				cancelType = Integer.valueOf(cancelTypeStr.substring(0, 1));
				if (cancelType != null) {
					switch (cancelType) {
					case 4:
					case 5:
						gt.setType("2");
						gt.setGuaranteeType("GuaranteeRequired");
						AmountPercent amountPercent0 = new AmountPercent();
						amountPercent0.setBasisType("FullStay");
						amountPercent0.setPercent(new BigDecimal(100));
						gt.setAmountPercent(amountPercent0);
						FormattedTextTextType t0 = new FormattedTextTextType();
						t0.setValue("2 峰时全额担保");
						JAXBElement<FormattedTextTextType> j0 = (new ObjectFactory()).createParagraphTypeText(t0);
						j0.setValue(t0);
						ParagraphType paragraphType0 = new ParagraphType();
						paragraphType0.getTextOrImageOrURL().add(j0);
						gt.getDescription().add(paragraphType0);
						break;
					case 6:
						gt.setType("1");
						gt.setGuaranteeType("GuaranteeRequired");
						AmountPercent amountPercent2 = new AmountPercent();
						amountPercent2.setBasisType("FirstLast");
						amountPercent2.setNmbrOfNights(BigInteger.ONE);
						gt.setAmountPercent(amountPercent2);
						FormattedTextTextType t2 = new FormattedTextTextType();
						t2.setValue("1 峰时首晚担保");
						JAXBElement<FormattedTextTextType> j2 = (new ObjectFactory()).createParagraphTypeText(t2);
						j2.setValue(t2);
						ParagraphType paragraphType2 = new ParagraphType();
						paragraphType2.getTextOrImageOrURL().add(j2);
						gt.getDescription().add(paragraphType2);
						break;
					case 2:
						gt.setType("4");
						gt.setGuaranteeType("GuaranteeRequired");
						AmountPercent amountPercent4 = new AmountPercent();
						amountPercent4.setBasisType("FullStay");
						amountPercent4.setPercent(new BigDecimal(100));
						gt.setAmountPercent(amountPercent4);
						FormattedTextTextType t4 = new FormattedTextTextType();
						t4.setValue("4 全天全额担保");
						JAXBElement<FormattedTextTextType> j4 = (new ObjectFactory()).createParagraphTypeText(t4);
						j4.setValue(t4);
						ParagraphType paragraphType4 = new ParagraphType();
						paragraphType4.getTextOrImageOrURL().add(j4);
						gt.getDescription().add(paragraphType4);
						break;
					}
				}
			}
		}

		if (cancelPolicy.getDeadline() != null && cancelPolicy.getDeadline().getOffsetTimeUnit().value().equals(TimeUnitType.HOUR.value()) && cancelPolicy.getDeadline().getOffsetUnitMultiplier() != null) {
			if (cancelPolicy.getDeadline().getOffsetUnitMultiplier() < 24) {
				int startTime = 24 - cancelPolicy.getDeadline().getOffsetUnitMultiplier();
				if ("24".equals(String.valueOf(startTime))) {
					try {
						gt.setHoldTime(DateUtils.dateToXmlDate(DateUtil.convertStringToDate("HH:mm:ss", "00:00:00")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					try {
						gt.setHoldTime(DateUtils.dateToXmlDate(DateUtil.convertStringToDate("HH:mm:ss", startTime + ":00:00")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			} else if (cancelPolicy.getDeadline().getOffsetUnitMultiplier() >= 24) {
				gt.setType("3");
				gt.setGuaranteeType("GuaranteeRequired");
				AmountPercent amountPercent5 = new AmountPercent();
				amountPercent5.setBasisType("FirstLast");
				amountPercent5.setNmbrOfNights(BigInteger.ONE);
				gt.setAmountPercent(amountPercent5);
				FormattedTextTextType t5 = new FormattedTextTextType();
				t5.setValue("3 全天首晚担保");
				JAXBElement<FormattedTextTextType> j5 = (new ObjectFactory()).createParagraphTypeText(t5);
				j5.setValue(t5);
				ParagraphType paragraphType5 = new ParagraphType();
				paragraphType5.getTextOrImageOrURL().add(j5);
				if (gt.getDescription().size() > 0) {
					gt.getDescription().set(0, paragraphType5);
				} else {
					gt.getDescription().add(paragraphType5);
				}

				if (cancelPolicy.getBasisType() != null && cancelPolicy.getPercent() != null) {
					if (cancelPolicy.getBasisType() == 1 && cancelPolicy.getPercent().intValue() == 100) {
						gt.setType("4");
						t5.setValue("4 全天全额担保");
					}
				}
			}
		}
		gt.setStart(cancelPolicy.getStart());
		gt.setEnd(cancelPolicy.getEnd());
		gt.setSun(cancelPolicy.isSun());
		gt.setMon(cancelPolicy.isMon());
		gt.setTue(cancelPolicy.isTue());
		gt.setWeds(cancelPolicy.isWeds());
		gt.setThur(cancelPolicy.isThur());
		gt.setFri(cancelPolicy.isFri());
		gt.setSat(cancelPolicy.isSat());

		if (cancelType != null && cancelType != 1) {
			return gt;
		}
		return null;
	}

	private CancelPenaltyType getCancelTypeOne() {
		CancelPenaltyType cp = new CancelPenaltyType();
		AmountPercentType amountPercentType = new AmountPercentType();
		amountPercentType.setFeesInclusive(true);
		amountPercentType.setTaxInclusive(true);
		amountPercentType.setAmount(BigDecimal.ZERO);
		cp.setAmountPercent(amountPercentType);
		FormattedTextTextType t = new FormattedTextTextType();
		t.setValue("1-任意退");
		JAXBElement<FormattedTextTextType> j = (new ObjectFactory()).createParagraphTypeText(t);
		j.setValue(t);
		ParagraphType paragraphType = new ParagraphType();
		paragraphType.getTextOrImageOrURL().add(j);
		cp.getPenaltyDescription().add(paragraphType);
		return cp;
	}

	private CancelPenaltyType getCancelTypeTwo(CancelPenaltyType cp) {
		cp.setNoCancelInd(true);
		FormattedTextTextType t = new FormattedTextTextType();
		t.setValue("2-不可取消");
		JAXBElement<FormattedTextTextType> j = (new ObjectFactory()).createParagraphTypeText(t);
		j.setValue(t);
		ParagraphType paragraphType = new ParagraphType();
		paragraphType.getTextOrImageOrURL().add(j);
		cp.getPenaltyDescription().add(paragraphType);
		return cp;
	}

	private String getOffsetCutTime(Integer offsetCutTime) {
		String result = null;
		if (offsetCutTime != null) {
			int cutTime = offsetCutTime.intValue();
			if (cutTime < 0 || cutTime > 23) {
				return result;
			}
			if (cutTime >= 0 && cutTime < 10) {
				return "0" + cutTime + ":00:00";
			} else {
				return cutTime + ":00:00";
			}
		}
		return result;
	}
}
