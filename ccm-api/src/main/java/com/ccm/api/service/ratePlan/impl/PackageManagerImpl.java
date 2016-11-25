package com.ccm.api.service.ratePlan.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.ratePlan.PackageDao;
import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.Package;
import com.ccm.api.model.ratePlan.PackageI18n;
import com.ccm.api.model.ratePlan.vo.PackageCriteria;
import com.ccm.api.model.ratePlan.vo.PackageResult;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.ratePlan.PackageManager;

@Service("packageManager")
public class PackageManagerImpl extends GenericManagerImpl<Package, String> implements PackageManager {

	@Autowired
	private PackageDao packageDao;

	@Autowired
	public PackageManagerImpl(PackageDao genericDao) {
		super(genericDao);
	}

	@Override
	public PackageVO savePackage(PackageVO vo) {
		Package vo1 = new Package();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = packageDao.addPackage(vo1);
		vo.setPackageId(vo1.getPackageId());

		if (vo.getPackageI18nList() == null || vo.getPackageI18nList().size() == 0) {
			List<PackageI18n> i18nList = new ArrayList<PackageI18n>();
			PackageI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setPackageI18nList(i18nList);
		}

		// 循环添加多语言数据
		for (PackageI18n packageI18n : vo.getPackageI18nList()) {
			PackageI18n i18n = new PackageI18n();
			i18n.setPackageId(vo.getPackageId());
			i18n.setLanguage(packageI18n.getLanguage());
			i18n.setPackageName(packageI18n.getPackageName());
			i18n.setDescription(packageI18n.getDescription());
			i18n.setMessage(packageI18n.getMessage());
			packageDao.addPackageI18n(i18n);
		}

		return vo;
	}

	@Override
	public void updatePackage(PackageVO vo) {
		Package vo1 = new Package();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		packageDao.updatePackage(vo1);

		if (vo.getPackageI18nList() != null) {

			packageDao.deletePackageI18nByPackageId(vo.getPackageId());
			// 循环添加多语言数据
			for (PackageI18n packageI18n : vo.getPackageI18nList()) {
				PackageI18n i18n = new PackageI18n();
				i18n.setPackageId(vo.getPackageId());
				i18n.setLanguage(packageI18n.getLanguage());
				i18n.setPackageName(packageI18n.getPackageName());
				i18n.setDescription(packageI18n.getDescription());
				i18n.setMessage(packageI18n.getMessage());
				packageDao.addPackageI18n(i18n);
			}
		} else {
			PackageI18n i18n = new PackageI18n();
			i18n.setPackageMId(vo.getPackageMId());
			i18n.setPackageId(vo.getPackageId());
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setPackageName(vo.getPackageName());
			i18n.setDescription(vo.getDescription());
			i18n.setMessage(vo.getMessage());
			packageDao.updatePackageI18n(i18n);
		}
	}

	@Override
	public void deletePackage(PackageVO vo) {
		Package vo1 = new Package();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		packageDao.deletePackage(vo1);

		packageDao.deletePackageI18nByPackageId(vo.getPackageId());
	}

	@Override
	public PackageVO getPackageByCode(String packageCode) {
		return packageDao.getPackageByCode(packageCode);
	}

	@Override
	public PackageVO getPackageById(String packageId) {
		return packageDao.getPackageById(packageId);
	}

	@Override
	public PackageResult searchPackage(PackageCriteria criteria) {
		PackageResult result = new PackageResult();
		List<PackageVO> voList = packageDao.searchPackage(criteria);
		Integer count = packageDao.searchPackageCount(criteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}

	@Override
	public PackageI18n getDefaultLanguageI18n(PackageVO vo) {
		// 创建多语言对象,并且设置值
		PackageI18n i18n = new PackageI18n();
		if(vo.getLanguage()==null){
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			i18n.setLanguage(vo.getLanguage());
		}
		i18n.setPackageName(vo.getPackageName());
		i18n.setDescription(vo.getDescription());
		i18n.setMessage(vo.getMessage());
		return i18n;
	}

	@Override
	public PackageVO getPackageById(String packageId, String language) {
		return packageDao.getPackageById(packageId, language);
	}

	@Override
	public void deletePackageI18nByPackageId(String packageId) {
		packageDao.deletePackageI18nByPackageId(packageId);
	}

	@Override
	public List<PackageI18n> getPackageI18ns(String packageId) {
		return packageDao.getPackageI18ns(packageId);
	}

	@Override
	public PackageVO getPackageByCode(String packageCode, String language) {
		return packageDao.getPackageByCode(packageCode, language);
	}

	@Override
	public List<PackageVO> getAllPackages() {
		return packageDao.getAllPackages();
	}

	@Override
	public List<PackageVO> getAllPackages(String language) {
		return packageDao.getAllPackages(language);
	}

	@Override
	public List<PackageVO> getPackageByRatePlan(List<String> ratePlanIds, String language) {
		if (StringUtils.hasLength(language)) {
			return packageDao.getRatePlanPackageByRatePlanId(ratePlanIds, language);
		} else {
			return packageDao.getRatePlanPackageByRatePlanId(ratePlanIds);
		}
	}

	public List<PackageVO> getRoomTypePackageByRatePlanId(String rateDetailId, String language) {
		if (StringUtils.hasLength(language)) {
			return packageDao.getRoomTypePackageByRatePlanId(rateDetailId, language);
		} else {
			return packageDao.getRoomTypePackageByRatePlanId(rateDetailId);
		}
	}

	public List<PackageVO> getDynamicPackagesByObj(DynamicPackage obj, String language) {
		if (language == null) {
			return packageDao.getDynamicPackagesByObj(obj);
		} else {
			return packageDao.getDynamicPackagesByObjLang(obj, language);
		}
	}
}
