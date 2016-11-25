package com.ccm.api.service.ratePlan.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.ratePlan.GuaranteePolicyDao;
import com.ccm.api.dao.ratePlan.RateGuaranteeRelationshipDao;
import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.RateGuaranteeRelationship;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.ratePlan.RateGuaranteeManager;
import com.ccm.api.util.DateUtil;

@Service("rateGuaranteeManager")
public class RateGuaranteeManagerImpl extends GenericManagerImpl<RateGuaranteeRelationship, String> implements RateGuaranteeManager {

	@Autowired
	private GuaranteePolicyDao guaranteePolicyDao;

	@Autowired
	private RateGuaranteeRelationshipDao rateGuaranteeRelationshipDao;

	/**
	 * switch下单时验证担保规则
	 */
	public boolean validGuaranteePolicy(String guaranteeCode, String ratePlanId, Date checkinDate) {

		GuaranteePolicy gp = guaranteePolicyDao.getGuaranteeByCode(guaranteeCode);

		return isEnableGuaranteePolicy(gp, ratePlanId, checkinDate);
	}

	public List<GuaranteePolicyVO> getEnabledGuaranteePolicy(String ratePlanId, String language, Date checkinDate) {
		List<GuaranteePolicyVO> gpVoList = guaranteePolicyDao.getGuaranteePolicyI18nByRatePlanId(ratePlanId, language);
		if (!gpVoList.isEmpty()) {
			List<GuaranteePolicyVO> result = new ArrayList<GuaranteePolicyVO>();
			List<String> guaranteeId = new ArrayList<String>();
			for (GuaranteePolicyVO gpVo : gpVoList) {
				if (!guaranteeId.contains(gpVo.getGuaranteeId()) && isEnableGuaranteePolicy(gpVo, ratePlanId, checkinDate)) {
					guaranteeId.add(gpVo.getGuaranteeId());
					result.add(gpVo);
				}
			}
			return result;
		}
		return gpVoList;
	}

	@SuppressWarnings("deprecation")
	private boolean isEnableGuaranteePolicy(GuaranteePolicy gp, String ratePlanId, Date checkinDate) {
		if (gp != null) {
			log.info(gp.getValidTime());

			Date cur = new Date();

			if (DateUtil.convertDateToString(cur).equals(DateUtil.convertDateToString(checkinDate))) {

				// 如果预订当天的房，则需先判断当前时间是否符合全局的有效时间
				try {
					Date curTime = DateUtil.convertStringToDate("HH:mm:ss", DateUtil.getDateTime("HH:mm:ss", cur));
					if (gp.getValidTime() != null && (curTime.after(gp.getValidTime()) && gp.getValidTime().getHours() > 0)) {
						log.info("nowDate after validTime");
						return false;
					}
				} catch (ParseException e) {
					log.warn(e);
				}
			}

			RateGuaranteeRelationship rgr = new RateGuaranteeRelationship();
			rgr.setRatePlanId(ratePlanId);
			rgr.setGuaranteeId(gp.getGuaranteeId());
			rgr.setEffectiveDate(checkinDate);
			// 如果预订当天之后的房，则只判断房价里的担保规则且用入住日期比较
			if (Calendar.MONDAY == DateUtil.getWeekday(checkinDate)) {
				rgr.setIsApplyToMonday(Boolean.TRUE);
			} else if (Calendar.TUESDAY == DateUtil.getWeekday(checkinDate)) {
				rgr.setIsApplyToTuesday(Boolean.TRUE);
			} else if (Calendar.WEDNESDAY == DateUtil.getWeekday(checkinDate)) {
				rgr.setIsApplyToWednesday(Boolean.TRUE);
			} else if (Calendar.THURSDAY == DateUtil.getWeekday(checkinDate)) {
				rgr.setIsApplyToThursday(Boolean.TRUE);
			} else if (Calendar.FRIDAY == DateUtil.getWeekday(checkinDate)) {
				rgr.setIsApplyToFriday(Boolean.TRUE);
			} else if (Calendar.SATURDAY == DateUtil.getWeekday(checkinDate)) {
				rgr.setIsApplyToSaturday(Boolean.TRUE);
			} else if (Calendar.SUNDAY == DateUtil.getWeekday(checkinDate)) {
				rgr.setIsApplyToSunday(Boolean.TRUE);
			}
			boolean isExist = rateGuaranteeRelationshipDao.getRateGuarRealByRatePlanIdGuaranteeId(rgr);
			if (isExist) {
				return true;
			}
		}
		return false;
	}

}
