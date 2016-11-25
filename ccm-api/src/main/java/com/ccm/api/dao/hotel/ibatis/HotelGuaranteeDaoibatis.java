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
import com.ccm.api.dao.hotel.HotelGuaranteeDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.HotelGuarantee;
import com.ccm.api.model.hotel.HotelGuaranteeI18n;
import com.ccm.api.model.hotel.vo.HotelGuaranteeCreteria;
import com.ccm.api.model.hotel.vo.HotelGuaranteeVO;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;

@Repository("hotelGuaranteeDao")
public class HotelGuaranteeDaoibatis extends GenericDaoiBatis<HotelGuarantee, String> implements HotelGuaranteeDao{
	
	public HotelGuaranteeDaoibatis() {
		super(HotelGuarantee.class);
	}
	
	@Override
	public HotelGuarantee addHotelGuarantee(HotelGuarantee hotelGuarantee) {
		hotelGuarantee.setCreatedBy(SecurityHolder.getUserId());
		hotelGuarantee.setCreatedTime(new Date());
		hotelGuarantee.setHotelGuaranteeId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addHotelGuarantee",hotelGuarantee);
		return hotelGuarantee;
	}
	
	@Override
	public HotelGuaranteeI18n addHotelGuaranteeI18n(HotelGuaranteeI18n i18n) {
		i18n.setCreatedBy(SecurityHolder.getUserId());
		i18n.setCreatedTime(new Date());
		i18n.setHotelGuaranteeMId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addHotelGuaranteeI18n",i18n);
		return i18n;
	}
	
	@Override
	public void updaetHotelGuarantee(HotelGuarantee hotelGuarantee) {
		hotelGuarantee.setUpdatedBy(SecurityHolder.getUserId());
		hotelGuarantee.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updaetHotelGuarantee",hotelGuarantee);
	}

	@Override
	public void updateHotelGuaranteeI18n(HotelGuaranteeI18n hotelGuaranteeI18n) {
		hotelGuaranteeI18n.setUpdatedBy(SecurityHolder.getUserId());
		hotelGuaranteeI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateHotelGuaranteeI18n",hotelGuaranteeI18n);
	}

	@Override
	public void deleteHotelGuaranteeByHotelId(String hotelId) {
		getSqlMapClientTemplate().update("deleteHotelGuaranteeByHotelId", hotelId);
	}

	@Override
	public HotelGuaranteeVO getHotelGuaranteeById(String hotelGuaranteeId) {
		return this.getHotelGuaranteeById(hotelGuaranteeId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public HotelGuaranteeVO getHotelGuaranteeById(String hotelGuaranteeId,
			String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelGuaranteeId", hotelGuaranteeId);
		params.put("language", language);
		return (HotelGuaranteeVO) getSqlMapClientTemplate().queryForObject("getHotelGuaranteeById", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelGuaranteeVO> getHotelGuaranteeByObj(HotelGuaranteeVO vo) {
		return getSqlMapClientTemplate().queryForList("getHotelGuaranteeByObj",vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelGuaranteeVO> searchHotelGuarantee(
			HotelGuaranteeCreteria creteria) {
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchHotelGuarantee", creteria);
	}

	@Override
	public Integer searchHotelGuaranteecount(HotelGuaranteeCreteria creteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("countHotelGuarantee", creteria);
	}

	@Override
	public HotelGuaranteeVO getHotelGuaranteeByHotelId(String hotelId) {
		return (HotelGuaranteeVO) getSqlMapClientTemplate().queryForObject("getHotelGuaranteeByHotelId", hotelId);
	}
	
	@SuppressWarnings("unchecked")
	public List<HotelGuaranteeVO> getHotelGuaranteeCodeByHotelId(String hotelId) {
		return getSqlMapClientTemplate().queryForList("getHotelGuaranteeCodeByHotelId", hotelId);
	}

	@Override
	public void deleteHotelGuaranteeById(HotelGuaranteeVO vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteHotelGuaranteeById", vo);
	}

	@Override
	public List<GuaranteePolicyVO> getDontUseHotelGuarantee(String hotelId) {
		return this.getDontUseHotelGuarantee(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuaranteePolicyVO> getDontUseHotelGuarantee(String hotelId,
			String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getDontUseHotelGuarantee", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rateplan> getUseRateGuarantee(String hotelId, String guaranteeId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("guaranteeId", guaranteeId);
		return getSqlMapClientTemplate().queryForList("getUseRateGuarantee", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelGuaranteeI18n> getHotelGuaranteeI18ns(
			String hotelGuaranteeId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelGuaranteeId", hotelGuaranteeId);
		return getSqlMapClientTemplate().queryForList("getHotelGuaranteeI18ns", params);
	}

	@Override
	public void deleteHotelGuaranteeI18nById(String hotelGuaranteeId) {
		getSqlMapClientTemplate().update("deleteHotelGuaranteeI18nById", hotelGuaranteeId);
	}
	
	/**
	 * 获取酒店级别的担保规则名称
	 * @param hotelId
	 * @param language
	 * @param guaranteeCode
	 * @return
	 */
	@Override
	public HotelGuaranteeI18n getHotelGuaranteeIdAndPolicyName(String hotelId,
			String language, String guaranteeCode) {
		Map<String,String> params=new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		params.put("guaranteeCode", guaranteeCode);
		return (HotelGuaranteeI18n) getSqlMapClientTemplate().queryForObject("getHotelGuaranteeIdAndPolicyName", params);
	}

}
