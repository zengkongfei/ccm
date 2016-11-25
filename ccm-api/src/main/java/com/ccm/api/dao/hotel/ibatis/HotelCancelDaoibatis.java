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
import com.ccm.api.dao.hotel.HotelCancelDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.HotelCancel;
import com.ccm.api.model.hotel.HotelCancelI18n;
import com.ccm.api.model.hotel.vo.HotelCancelCreteria;
import com.ccm.api.model.hotel.vo.HotelCancelVO;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;

@Repository("hotelCancelDao")
public class HotelCancelDaoibatis extends GenericDaoiBatis<HotelCancel, String> implements HotelCancelDao{

	public HotelCancelDaoibatis(){
		super(HotelCancel.class);
	}
	
	@Override
	public HotelCancel addHotelCancel(HotelCancel hotelCancel) {
		hotelCancel.setCreatedBy(SecurityHolder.getUserId());
		hotelCancel.setCreatedTime(new Date());
		hotelCancel.setHotelCancelId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addHotelCancel",hotelCancel);
		return hotelCancel;
	}
	
	@Override
	public HotelCancelI18n addHotelCancelI18n(HotelCancelI18n i18n) {
		i18n.setCreatedBy(SecurityHolder.getUserId());
		i18n.setCreatedTime(new Date());
		i18n.setHotelCancelMId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addHotelCancelI18n",i18n);
		return i18n;
	}

	@Override
	public void updaetHotelCancel(HotelCancel hotelGuarantee) {
		hotelGuarantee.setUpdatedBy(SecurityHolder.getUserId());
		hotelGuarantee.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updaetHotelCancel",hotelGuarantee);
	}

	@Override
	public void updateHotelCancelI18n(HotelCancelI18n hotelCancelI18n) {
		hotelCancelI18n.setUpdatedBy(SecurityHolder.getUserId());
		hotelCancelI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateHotelCancelI18n",hotelCancelI18n);
	}

	@Override
	public void deleteHotelCancelByHotelId(String hotelId) {
		getSqlMapClientTemplate().update("deleteHotelCancelByHotelId", hotelId);
	}

	@Override
	public HotelCancelVO getHotelCancelById(String hotelCancelId) {
		return this.getHotelCancelById(hotelCancelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public HotelCancelVO getHotelCancelById(String hotelCancelId,
			String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelCancelId", hotelCancelId);
		params.put("language", language);
		return (HotelCancelVO) getSqlMapClientTemplate().queryForObject("getHotelCancelById", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelCancelVO> getHotelCancelByObj(HotelCancelVO vo) {
		return getSqlMapClientTemplate().queryForList("getHotelCancelByObj",vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelCancelVO> searchHotelCancel(HotelCancelCreteria creteria) {
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchHotelCancel", creteria);
	}

	@Override
	public Integer searchHotelCancelcount(HotelCancelCreteria creteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("countHotelCancel", creteria);
	}

	@Override
	public HotelCancelVO getHotelCancelByHotelId(String hotelId) {
		return (HotelCancelVO) getSqlMapClientTemplate().queryForObject("getHotelCancelByHotelId", hotelId);
	}

	@Override
	public void deleteHotelCancelById(HotelCancelVO vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteHotelCancelById", vo);
	}

	@Override
	public List<CancelPolicyVO> getDontUseHotelCancel(String hotelId) {
		return this.getDontUseHotelCancel(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CancelPolicyVO> getDontUseHotelCancel(String hotelId,
			String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getDontUseHotelCancel", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rateplan> getUseRateCancel(String hotelId, String cancelId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("cancelId", cancelId);
		return getSqlMapClientTemplate().queryForList("getUseRateCancel", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelCancelI18n> getHotelCancelI18ns(String hotelCancelId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelCancelId", hotelCancelId);
		return getSqlMapClientTemplate().queryForList("getHotelCancelI18ns", params);
	}

	@Override
	public void deleteHotelCancelI18nById(String hotelCancelId) {
		getSqlMapClientTemplate().update("deleteHotelCancelI18nById", hotelCancelId);
	}

}
