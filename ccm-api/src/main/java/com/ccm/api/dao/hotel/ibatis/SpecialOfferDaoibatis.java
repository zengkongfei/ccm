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
import com.ccm.api.dao.hotel.SpecialOfferDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.SpecialOffer;
import com.ccm.api.model.hotel.SpecialOfferI18n;
import com.ccm.api.model.hotel.vo.SpecialOfferCreteria;
import com.ccm.api.model.hotel.vo.SpecialOfferVO;

@Repository("specialOfferDao")
public class SpecialOfferDaoibatis extends GenericDaoiBatis<SpecialOffer, String> implements SpecialOfferDao{

	public SpecialOfferDaoibatis(){
		super(SpecialOffer.class);
	}
	
	@Override
	public SpecialOffer addSpecialOffer(SpecialOffer specialOffer) {
		specialOffer.setCreatedBy(SecurityHolder.getUserId());
		specialOffer.setCreatedTime(new Date());
		specialOffer.setSpecialOfferId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addSpecialOffer",specialOffer);
		return specialOffer;
	}

	@Override
	public SpecialOfferI18n addSpecialOfferI18n(SpecialOfferI18n specialOfferI18n) {
		specialOfferI18n.setCreatedBy(SecurityHolder.getUserId());
		specialOfferI18n.setCreatedTime(new Date());
		specialOfferI18n.setSpecialOfferMId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addSpecialOfferI18n",specialOfferI18n);
		return specialOfferI18n;
	}

	@Override
	public void updateSpecialOffer(SpecialOffer specialOffer) {
		specialOffer.setUpdatedBy(SecurityHolder.getUserId());
		specialOffer.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateSpecialOffer",specialOffer);
	}

	@Override
	public void updateSpecialOfferI18n(SpecialOfferI18n specialOfferI18n) {
		specialOfferI18n.setUpdatedBy(SecurityHolder.getUserId());
		specialOfferI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateSpecialOfferI18n",specialOfferI18n);
	}

	@Override
	public void deleteSpecialOffer(SpecialOffer specialOffer) {
		specialOffer.setUpdatedBy(SecurityHolder.getUserId());
		specialOffer.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteSpecialOffer",specialOffer);
	}
	
	@Override
	public void deleteSpecialOfferI18n(SpecialOfferI18n specialOfferI18n) {
		specialOfferI18n.setUpdatedBy(SecurityHolder.getUserId());
		specialOfferI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteSpecialOfferI18n",specialOfferI18n);
	}

	@Override
	public void deleteSpecialOfferI18nBySpecialOfferId(String specialOfferId) {
		getSqlMapClientTemplate().update("deleteSpecialOfferI18nBySpecialOfferId",specialOfferId);
		
	}

	@Override
	public SpecialOffer getSpecialOffer(String specialOfferId) {
		return (SpecialOffer) getSqlMapClientTemplate().queryForObject("getSpecialOffer", specialOfferId);
	}

	@Override
	public SpecialOfferVO getSpecialOfferById(String specialOfferId) {
		return this.getSpecialOfferById(specialOfferId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public SpecialOfferVO getSpecialOfferById(String specialOfferId,String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("specialOfferId", specialOfferId);
		params.put("language", language);
		return (SpecialOfferVO) getSqlMapClientTemplate().queryForObject("getSpecialOfferById", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpecialOfferVO> searchSpecialOffer(SpecialOfferCreteria creteria) {
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchSpecialOffer", creteria);
	}

	@Override
	public Integer searchSpecialOfferCount(SpecialOfferCreteria creteria) {
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchSpecialOfferCount", creteria);
	}

	@Override
	public List<SpecialOfferVO> getAllSpecialOfferByHotelId(String hotelId) {
		return this.getAllSpecialOfferByHotelId(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpecialOfferVO> getAllSpecialOfferByHotelId(String hotelId,String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getAllSpecialOfferByHotelId",params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpecialOfferI18n> getSpecialOfferI18ns(String specialOfferId) {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("specialOfferId", specialOfferId);
		return getSqlMapClientTemplate().queryForList("getSpecialOfferI18ns",params);
	}

	

}
