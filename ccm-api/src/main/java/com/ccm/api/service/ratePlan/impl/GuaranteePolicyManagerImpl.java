package com.ccm.api.service.ratePlan.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.dao.ratePlan.GuaranteePolicyDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.GuaranteePolicyI18n;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyCriteria;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyResult;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.ratePlan.GuaranteePolicyManager;
import com.ccm.api.util.DateUtil;

@Service("guaranteePolicyManager")
public class GuaranteePolicyManagerImpl extends GenericManagerImpl<GuaranteePolicy, String> implements GuaranteePolicyManager {

	@Autowired
	private GuaranteePolicyDao guaranteePolicyDao;
	@Autowired
	private HotelMCDao hotelMCDao;

	@Autowired
	public GuaranteePolicyManagerImpl(GuaranteePolicyDao genericDao) {
		super(genericDao);
	}

	@Override
	public void saveGuaranteePolicy(GuaranteePolicyVO vo) {
		GuaranteePolicy vo1 = new GuaranteePolicy();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = guaranteePolicyDao.addGuaranteePolicy(vo1);
		vo.setGuaranteeId(vo1.getGuaranteeId());

		if (vo.getGuaranteePolicyI18nList() == null || vo.getGuaranteePolicyI18nList().size() == 0) {
			List<GuaranteePolicyI18n> i18nList = new ArrayList<GuaranteePolicyI18n>();
			GuaranteePolicyI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setGuaranteePolicyI18nList(i18nList);
		}

		// 循环添加担保规则多语言数据
		for (GuaranteePolicyI18n guaranteePolicyI18n : vo.getGuaranteePolicyI18nList()) {
			GuaranteePolicyI18n i18n = new GuaranteePolicyI18n();
			i18n.setGuaranteeId(vo.getGuaranteeId());
			i18n.setLanguage(guaranteePolicyI18n.getLanguage());
			i18n.setPolicyName(guaranteePolicyI18n.getPolicyName());
			i18n.setDescription(guaranteePolicyI18n.getDescription());
			guaranteePolicyDao.addGuaranteePolicyI18n(i18n);
		}
	}

	@Override
	public void updateGuaranteePolicy(GuaranteePolicyVO vo) {
		GuaranteePolicy vo1 = new GuaranteePolicy();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		guaranteePolicyDao.updateGuaranteePolicy(vo1);

		if (vo.getGuaranteePolicyI18nList() != null) {

			guaranteePolicyDao.deleteGuaranteePolicyI18nByGuaranteeId(vo.getGuaranteeId());

			// 循环添加多语言数据
			for (GuaranteePolicyI18n guaranteePolicyI18n : vo.getGuaranteePolicyI18nList()) {
				GuaranteePolicyI18n i18n = new GuaranteePolicyI18n();
				i18n.setGuaranteeId(vo.getGuaranteeId());
				i18n.setLanguage(guaranteePolicyI18n.getLanguage());
				i18n.setPolicyName(guaranteePolicyI18n.getPolicyName());
				i18n.setDescription(guaranteePolicyI18n.getDescription());
				i18n.setUpdatedBy(SecurityHolder.getUserId());
				i18n.setLastModifyTime(new Date());
				guaranteePolicyDao.addGuaranteePolicyI18n(i18n);
			}
		} else {
			GuaranteePolicyI18n i18n = new GuaranteePolicyI18n();
			i18n.setGuaranteeMId(vo.getGuaranteeMId());
			i18n.setGuaranteeId(vo.getGuaranteeId());
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setPolicyName(vo.getPolicyName());
			i18n.setDescription(vo.getDescription());
			guaranteePolicyDao.updateGuaranteePolicyI18n(i18n);
		}
	}

	@Override
	public void deleteGuaranteePolicy(GuaranteePolicyVO vo) {
		GuaranteePolicy vo1 = new GuaranteePolicy();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		guaranteePolicyDao.deleteGuaranteePolicy(vo1);

		guaranteePolicyDao.deleteGuaranteePolicyI18nByGuaranteeId(vo.getGuaranteeId());
	}

	@Override
	public GuaranteePolicyVO getGuaranteePolicyByCode(String guaranteeCode) {
		return guaranteePolicyDao.getGuaranteePolicyByCode(guaranteeCode);
	}

	@Override
	public GuaranteePolicyVO getGuaranteePolicyById(String guaranteeId) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		GuaranteePolicyVO vo = guaranteePolicyDao.getGuaranteePolicyById(guaranteeId);
		if (null != vo) {
			Date holdTime = vo.getHoldTime();
			Date validTime = vo.getValidTime();
			if (holdTime != null) {
				vo.setHoldTimeStr(sdf.format(holdTime));
			}
			if (validTime != null) {
				vo.setValidTimeStr(DateUtil.getDateTime("HH:mm:ss", validTime));
			}
		}
		return vo;
	}

	@Override
	public GuaranteePolicyResult searchGuaranteePolicy(GuaranteePolicyCriteria criteria) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		GuaranteePolicyResult result = new GuaranteePolicyResult();
		List<GuaranteePolicyVO> voList = guaranteePolicyDao.searchGuaranteePolicy(criteria);
		if (!voList.isEmpty()) {
			for (GuaranteePolicyVO vo : voList) {
				if (null != vo.getHotelId() && !"".equals(vo.getHotelId())) {
					HotelVO hotel = hotelMCDao.getHotelByIdMC(vo.getHotelId());
					if (null != hotel) {
						vo.setHotelName(hotel.getHotelName());
					}
				}
				Date holdTime = vo.getHoldTime();
				Date validTime = vo.getValidTime();
				if (holdTime != null) {
					vo.setHoldTimeStr(sdf.format(holdTime));
				}
				if (validTime != null) {
					vo.setValidTimeStr(sdf.format(validTime));
				}
			}
		}

		Integer count = guaranteePolicyDao.searchGuaranteePolicyCount(criteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}

	@Override
	public GuaranteePolicyI18n getDefaultLanguageI18n(GuaranteePolicyVO vo) {
		// 创建多语言对象,并且设置值
		GuaranteePolicyI18n i18n = new GuaranteePolicyI18n();
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
	public GuaranteePolicyVO getGuaranteePolicyById(String guaranteeId, String language) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		GuaranteePolicyVO vo = guaranteePolicyDao.getGuaranteePolicyById(guaranteeId, language);
		if (null != vo) {
			Date holdTime = vo.getHoldTime();
			Date validTime = vo.getValidTime();
			if (holdTime != null) {
				vo.setHoldTimeStr(sdf.format(holdTime));
			}
			if (validTime != null) {
				vo.setValidTimeStr(DateUtil.getDateTime("HH:mm:ss", validTime));
			}
		}
		return vo;
	}

	@Override
	public void deleteGuaranteePolicyI18nByGuaranteeId(String guaranteeId) {
		guaranteePolicyDao.deleteGuaranteePolicyI18nByGuaranteeId(guaranteeId);
	}

	@Override
	public List<GuaranteePolicyI18n> getGuaranteePolicyI18ns(String guaranteeId) {
		return guaranteePolicyDao.getGuaranteePolicyI18ns(guaranteeId);
	}

	@Override
	public GuaranteePolicyVO getGuaranteePolicyByCode(String guaranteeCode, String language) {
		return guaranteePolicyDao.getGuaranteePolicyByCode(guaranteeCode, language);
	}

	@Override
	public List<GuaranteePolicyVO> getAllGuaranteePolicys() {
		return guaranteePolicyDao.getAllGuaranteePolicys();
	}

	@Override
	public List<GuaranteePolicyVO> getAllGuaranteePolicys(String language) {
		return guaranteePolicyDao.getAllGuaranteePolicys(language);
	}

	public List<GuaranteePolicyVO> getGuaranteePolicyByRatePlanId(String ratePlanId, String language) {
		return guaranteePolicyDao.getGuaranteePolicyI18nByRatePlanId(ratePlanId, language);
	}

}
