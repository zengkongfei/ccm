package com.ccm.api.dao.hotel.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.BrandDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Brand;
import com.ccm.api.model.hotel.BrandI18n;
import com.ccm.api.model.hotel.vo.BrandCreteria;
import com.ccm.api.model.hotel.vo.BrandVO;

@Repository("brandDao")
public class BrandDaoibatis extends GenericDaoiBatis<Brand, String> implements BrandDao {

	public BrandDaoibatis() {
		super(Brand.class);
	}

	@Override
	public Brand addBrand(Brand vo) {
		vo.setBrandId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addBrand",vo);
		return vo;
	}

	@Override
	public BrandI18n addBrandI18n(BrandI18n vo) {
		vo.setBrandMId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addBrandI18n",vo);
		return vo;
	}

	@Override
	public void updateBrand(Brand vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateBrand",vo);
	}
	
	@Override
	public void updateBrandI18n(BrandI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateBrandI18n",vo);
	}

	@Override
	public void deleteBrand(Brand vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteBrand",vo);
	}

	@Override
	public void deleteBrandI18n(BrandI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteBrandI18n",vo);
	}

	@Override
	public BrandVO getBrandByCode(String brandCode) {
		return this.getBrandByCode(brandCode, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public BrandVO getBrandByCode(String brandCode,String language) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("brandCode", brandCode);
		params.put("language", language);
		return (BrandVO) getSqlMapClientTemplate().queryForObject("getBrandByCode",params);
	}

	@Override
	public BrandVO getBrandById(String brandId) {
		return this.getBrandById(brandId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public BrandVO getBrandById(String brandId,String language) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("brandId", brandId);
		params.put("language", language);
		return (BrandVO) getSqlMapClientTemplate().queryForObject("getBrandById",params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BrandVO> searchBrand(BrandCreteria creteria) {
		//如果没有知道语言种类
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchBrand",creteria);
	}

	@Override
	public Integer searchBrandCount(BrandCreteria creteria) {
		//如果没有知道语言种类
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchBrandCount",creteria);
	}
	
	@Override
	public List<BrandVO> getAllBrandByChainId(String chainId) {
		return this.getAllBrandByChainId(chainId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BrandVO> getAllBrandByChainId(String chainId,String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chainId", chainId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getAllBrandByChainId",params);
	}
	
	@Override
	public void deleteBrandI18nByBrandId(String brandId){
		getSqlMapClientTemplate().update("deleteBrandI18nByBrandId",brandId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BrandI18n> getBrandI18ns(String brandId){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("brandId", brandId);
		return getSqlMapClientTemplate().queryForList("getBrandI18ns",params);
	}
}
