package com.ccm.api.service.ratePlan.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.ratePlan.CancelPolicyDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.CancelPolicy;
import com.ccm.api.model.ratePlan.CancelPolicyI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyCriteria;
import com.ccm.api.model.ratePlan.vo.CancelPolicyResult;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.ratePlan.CancelPolicyManager;

@Service("cancelPolicyManager")
public class CancelPolicyManagerImpl extends GenericManagerImpl<CancelPolicy, String> implements CancelPolicyManager {

	@Autowired
	private CancelPolicyDao cancelPolicyDao;

	@Override
	public void saveCancelPolicy(CancelPolicyVO vo) {
		CancelPolicy vo1 = new CancelPolicy();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = cancelPolicyDao.addCancelPolicy(vo1);
		vo.setCancelId(vo1.getCancelId());

		if (vo.getCancelPolicyI18nList() == null || vo.getCancelPolicyI18nList().size() == 0) {
			List<CancelPolicyI18n> i18nList = new ArrayList<CancelPolicyI18n>();
			CancelPolicyI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setCancelPolicyI18nList(i18nList);
		}

		// 循环添加多语言数据
		for (CancelPolicyI18n cancelPolicyI18n : vo.getCancelPolicyI18nList()) {
			CancelPolicyI18n i18n = new CancelPolicyI18n();
			i18n.setCancelId(vo.getCancelId());
			i18n.setLanguage(cancelPolicyI18n.getLanguage());
			i18n.setPolicyName(cancelPolicyI18n.getPolicyName());
			i18n.setDescription(cancelPolicyI18n.getDescription());
			cancelPolicyDao.addCancelPolicyI18n(i18n);
		}
	}

	@Override
	public void updateCancelPolicy(CancelPolicyVO vo) {
		CancelPolicy vo1 = new CancelPolicy();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cancelPolicyDao.updateCancelPolicy(vo1);
		if (vo.getCancelPolicyI18nList() != null) {
			cancelPolicyDao.deleteCancelPolicyI18nByCancelId(vo.getCancelId());

			// 循环添加 取消规则多语言数据
			for (CancelPolicyI18n cancelPolicyI18n : vo.getCancelPolicyI18nList()) {
				CancelPolicyI18n i18n = new CancelPolicyI18n();
				i18n.setCancelId(vo.getCancelId());
				i18n.setLanguage(cancelPolicyI18n.getLanguage());
				i18n.setPolicyName(cancelPolicyI18n.getPolicyName());
				i18n.setDescription(cancelPolicyI18n.getDescription());
				cancelPolicyDao.addCancelPolicyI18n(i18n);
			}
		} else {
			CancelPolicyI18n i18n = new CancelPolicyI18n();
			i18n.setCancelMId(vo.getCancelId());
			i18n.setCancelId(vo.getCancelId());
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setPolicyName(vo.getPolicyName());
			i18n.setDescription(vo.getDescription());
			cancelPolicyDao.updateCancelPolicyI18n(i18n);
		}
	}

	@Override
	public void deleteCancelPolicy(CancelPolicyVO vo) {
		CancelPolicy vo1 = new CancelPolicy();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cancelPolicyDao.deleteCancelPolicy(vo1);

		cancelPolicyDao.deleteCancelPolicyI18nByCancelId(vo.getCancelId());
	}

	@Override
	public CancelPolicyVO getCancelPolicyByCode(String cancelPolicyCode) {
		return cancelPolicyDao.getCancelPolicyByCode(cancelPolicyCode);
	}

	@Override
	public CancelPolicyVO getCancelPolicyByCode(String cancelPolicyCode, String language) {
		return cancelPolicyDao.getCancelPolicyByCode(cancelPolicyCode, language);
	}

	@Override
	public CancelPolicyVO getCancelPolicyById(String cancelId) {
		return this.getCancelPolicyById(cancelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public CancelPolicyVO getCancelPolicyById(String cancelId, String language) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		CancelPolicyVO vo = cancelPolicyDao.getCancelPolicyById(cancelId, language);
		if (null != vo) {
			Date date = vo.getAbsoluteDeadline();
			if (date != null) {
				vo.setAbsoluteTime(sdf.format(date));
			}
		}
		return vo;
	}

	@Override
	public CancelPolicyResult searchCancelPolicy(CancelPolicyCriteria criteria) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		CancelPolicyResult result = new CancelPolicyResult();
		List<CancelPolicyVO> voList = cancelPolicyDao.searchCancelPolicy(criteria);
		if (!voList.isEmpty()) {
			for (CancelPolicyVO vo : voList) {
				Date date = vo.getAbsoluteDeadline();
				if (date != null) {
					vo.setAbsoluteTime(sdf.format(date));
				}
			}
		}
		Integer count = cancelPolicyDao.searchCancelPolicyCount(criteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}

	@Override
	public CancelPolicyI18n getDefaultLanguageI18n(CancelPolicyVO vo) {
		// 创建多语言对象,并且设置值
		CancelPolicyI18n i18n = new CancelPolicyI18n();
		if(vo.getLanguage()==null){
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			i18n.setLanguage(vo.getLanguage());
		}
		i18n.setPolicyName(vo.getPolicyName());
		i18n.setDescription(vo.getDescription());
		return i18n;
	}

	@Override
	public void deleteCancelPolicyI18nByCancelId(String cancelId) {
		cancelPolicyDao.deleteCancelPolicyI18nByCancelId(cancelId);
	}

	@Override
	public List<CancelPolicyI18n> getCancelPolicyI18ns(String cancelId) {
		return cancelPolicyDao.getCancelPolicyI18ns(cancelId);
	}

	@Override
	public List<CancelPolicyVO> getAllCancelPolicys() {
		return cancelPolicyDao.getAllCancelPolicys();
	}

	@Override
	public List<CancelPolicyVO> getAllCancelPolicys(String language) {
		return cancelPolicyDao.getAllCancelPolicys(language);
	}

	public List<CancelPolicy> getRateCancelByRatePlanId(String ratePlanId) {
		return cancelPolicyDao.getRateCancelByRatePlanId(ratePlanId);
	}

	public List<CancelPolicyVO> getRateCancelI18nByRatePlanId(String ratePlanId, String language) {
		List<CancelPolicyVO> cpvoList = cancelPolicyDao.getRateCancelI18nByRatePlanId(ratePlanId, language);
		if (cpvoList == null) {
			cpvoList = new ArrayList<CancelPolicyVO>();
		}
		return cpvoList;
	}

}
