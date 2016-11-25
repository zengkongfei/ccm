package com.ccm.api.service.hotel.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.hotel.BrandDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Brand;
import com.ccm.api.model.hotel.BrandI18n;
import com.ccm.api.model.hotel.vo.BrandCreteria;
import com.ccm.api.model.hotel.vo.BrandResult;
import com.ccm.api.model.hotel.vo.BrandVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.BrandManager;

@Service("brandManager")
public class BrandManagerImpl extends GenericManagerImpl<Brand, String> implements BrandManager {
	
	@Autowired
	private BrandDao brandDao;

	@Override
	public void saveBrand(BrandVO vo) {
		Brand vo1 = new Brand();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = brandDao.addBrand(vo1);
		vo.setBrandId(vo1.getBrandId());
		
		if(vo.getBrandI18nList()==null||vo.getBrandI18nList().size()==0){
			List<BrandI18n> i18nList = new ArrayList<BrandI18n>();
			BrandI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setBrandI18nList(i18nList);
		}
		
		//循环添加多语言数据
		for (BrandI18n brandI18n : vo.getBrandI18nList()) {
			BrandI18n i18n = new BrandI18n();
			i18n.setBrandId(vo.getBrandId());
			i18n.setLanguage(brandI18n.getLanguage());
			i18n.setBrandName(brandI18n.getBrandName());
			brandDao.addBrandI18n(i18n);
		}
	}

	@Override
	public void updateBrand(BrandVO vo) {
		Brand vo1 = new Brand();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		brandDao.updateBrand(vo1);
		
		
		if(vo.getBrandI18nList()!=null){
			
			//批量删除多语言记录
			brandDao.deleteBrandI18nByBrandId(vo.getBrandId());
			
			//循环添加多语言数据
			for (BrandI18n brandI18n : vo.getBrandI18nList()) {
				BrandI18n i18n = new BrandI18n();
				i18n.setBrandId(vo.getBrandId());
				i18n.setLanguage(brandI18n.getLanguage());
				i18n.setBrandName(brandI18n.getBrandName());
				brandDao.addBrandI18n(i18n);
			}
		}else{
			BrandI18n i18n = new BrandI18n();
			i18n.setBrandMId(vo.getBrandMId());
			i18n.setBrandId(vo.getBrandId());
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setBrandName(vo.getBrandName());
			brandDao.updateBrandI18n(i18n);
		}

	}

	@Override
	public void deleteBrand(BrandVO vo) {
		Brand vo1 = new Brand();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		brandDao.deleteBrand(vo1);
		brandDao.deleteBrandI18nByBrandId(vo.getBrandId());
	}

	@Override
	public BrandVO getBrandByCode(String brandCode) {
		return brandDao.getBrandByCode(brandCode);
	}
	
	@Override
	public BrandVO getBrandByCode(String brandCode, String language) {
		return brandDao.getBrandByCode(brandCode, language);
	}

	@Override
	public BrandVO getBrandById(String brandId) {
		return brandDao.getBrandById(brandId);
	}

	@Override
	public BrandResult searchBrand(BrandCreteria creteria) {
		BrandResult result = new BrandResult();
		List<BrandVO> voList = brandDao.searchBrand(creteria);
		Integer count = brandDao.searchBrandCount(creteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}
	
	@Override
	public List<BrandVO> getAllBrandByChainId(String chainId) {
		return brandDao.getAllBrandByChainId(chainId);
	}
	
	@Override
	public List<BrandVO> getAllBrandByChainId(String chainId,String language) {
		return brandDao.getAllBrandByChainId(chainId,language);
	}
	
	@Override
	public BrandI18n getDefaultLanguageI18n(BrandVO vo) {
		BrandI18n brandI18n = new BrandI18n();
		if(vo.getLanguage()==null){
			brandI18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			brandI18n.setLanguage(vo.getLanguage());
		}
		brandI18n.setBrandName(vo.getBrandName());
		return brandI18n;
	}

	@Override
	public BrandVO getBrandById(String brandId, String language) {
		return brandDao.getBrandById(brandId, language);
	}

	@Override
	public void deleteBrandI18nByBrandId(String brandId) {
		brandDao.deleteBrandI18nByBrandId(brandId);
	}

	@Override
	public List<BrandI18n> getBrandI18ns(String brandId) {
		return brandDao.getBrandI18ns(brandId);
	}

	
}
