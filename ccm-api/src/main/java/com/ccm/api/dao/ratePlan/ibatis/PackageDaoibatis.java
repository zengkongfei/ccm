package com.ccm.api.dao.ratePlan.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.PackageDao;
import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.Package;
import com.ccm.api.model.ratePlan.PackageI18n;
import com.ccm.api.model.ratePlan.vo.PackageCriteria;
import com.ccm.api.model.ratePlan.vo.PackageVO;

@Repository("packageDao")
public class PackageDaoibatis extends GenericDaoiBatis<Package, String> implements PackageDao {

	public PackageDaoibatis() {
		super(Package.class);
	}

	@Override
	public Package addPackage(Package vo) {
		vo.setPackageId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addPackage", vo);
		return vo;
	}

	@Override
	public PackageI18n addPackageI18n(PackageI18n vo) {
		vo.setPackageMId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addPackageI18n", vo);
		return vo;
	}

	@Override
	public void updatePackage(Package vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updatePackage", vo);
	}

	@Override
	public void updatePackageI18n(PackageI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updatePackageI18n", vo);
	}

	@Override
	public void deletePackage(Package vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deletePackage", vo);
	}

	@Override
	public void deletePackageI18n(PackageI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deletePackageI18n", vo);
	}

	@Override
	public PackageVO getPackageByCode(String packageCode) {
		return this.getPackageByCode(packageCode, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public PackageVO getPackageByCode(String packageCode, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("packageCode", packageCode);
		params.put("language", language);
		return (PackageVO) getSqlMapClientTemplate().queryForObject("getPackageByCode", params);
	}

	@Override
	public PackageVO getPackageById(String packageId) {
		return this.getPackageById(packageId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public PackageVO getPackageById(String packageId, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("packageId", packageId);
		params.put("language", language);
		return (PackageVO) getSqlMapClientTemplate().queryForObject("getPackageById", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> searchPackage(PackageCriteria criteria) {
		// 如果没有知道语言种类
		if (criteria != null && StringUtils.isBlank(criteria.getLanguage())) {
			criteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchPackage", criteria);
	}

	@Override
	public Integer searchPackageCount(PackageCriteria criteria) {
		// 如果没有知道语言种类
		if (criteria != null && StringUtils.isBlank(criteria.getLanguage())) {
			criteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchPackageCount", criteria);
	}

	@Override
	public List<PackageVO> getRatePlanPackageByRatePlanId(List<String> ratePlanIds) {
		return this.getRatePlanPackageByRatePlanId(ratePlanIds, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getRatePlanPackageByRatePlanId(List<String> ratePlanIds, String language) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ratePlanIds", ratePlanIds);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getRatePlanPackageByRatePlanId", params);
	}

	@Override
	public List<PackageVO> getRoomTypePackageByRatePlanId(String rateDetailId) {
		return this.getRoomTypePackageByRatePlanId(rateDetailId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getRoomTypePackageByRatePlanId(String rateDetailId, String language) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("language", language);
		params.put("rateDetailId", rateDetailId);
		return getSqlMapClientTemplate().queryForList("getRoomTypePackageByRateDetailId", params);
	}

	@Override
	public void deletePackageI18nByPackageId(String packageId) {
		getSqlMapClientTemplate().update("deletePackageI18nByPackageId", packageId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageI18n> getPackageI18ns(String packageId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("packageId", packageId);
		return getSqlMapClientTemplate().queryForList("getPackageI18ns", params);
	}

	@Override
	public List<PackageVO> getAllPackages() {
		return this.getAllPackages(LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getAllPackages(String language) {
		return getSqlMapClientTemplate().queryForList("getAllPackages", language);
	}

	@SuppressWarnings("unchecked")
	public List<PackageVO> getDynamicPackagesByObj(DynamicPackage obj) {
		return getSqlMapClientTemplate().queryForList("getPackageByObj", obj);
	}

	@SuppressWarnings("unchecked")
	public List<PackageVO> getDynamicPackagesByObjLang(DynamicPackage obj, String language) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("hotelId", obj.getHotelId());
		param.put("channelId", obj.getChannelId());
		param.put("packageCode", obj.getPackageCode());
		param.put("language", language);
		return getSqlMapClientTemplate().queryForList("getPackageVoByObjLang", param);
	}
}
