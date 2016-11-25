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
import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.model.city.City;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.HotelI18n;
import com.ccm.api.model.hotel.vo.HotelCreteria;
import com.ccm.api.model.hotel.vo.HotelVO;

@Repository("hotelMCDao")
public class HotelMCDaoibatis extends GenericDaoiBatis<Hotel, String> implements HotelMCDao {

	public HotelMCDaoibatis() {
		super(Hotel.class);
	}

	@Override
	public Hotel addHotelMC(Hotel vo) {
		vo.setHotelId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addHotelMC",vo);
		return vo;
	}

	@Override
	public HotelI18n addHotelI18nMC(HotelI18n vo) {
		vo.setHotelMId(UUID.randomUUID().toString().replace("-", ""));
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addHotelI18nMC",vo);
		return vo;
	}

	@Override
	public void updateHotelMC(Hotel vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateHotelMC",vo);
	}

	@Override
	public void updateHotelI18nMC(HotelI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateHotelI18nMC",vo);
	}

	@Override
	public void deleteHotelMC(Hotel vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteHotelMC",vo);
	}

	@Override
	public void deleteHotelI18nMC(HotelI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteHotelI18nMC",vo);
	}

	@Override
	public HotelVO getHotelByCodeMC(HotelVO vo) {
		if(vo!=null&&StringUtils.isBlank(vo.getLanguageCode())){
			vo.setLanguageCode(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (HotelVO) getSqlMapClientTemplate().queryForObject("getHotelByCodeMC",vo);
	}
	
	@Override
	public HotelVO getHotelByCodeMC2(String hotelCode) {
		return this.getHotelByCodeMC2(hotelCode, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public HotelVO getHotelByCodeMC2(String hotelCode,String languageCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelCode", hotelCode);
		params.put("languageCode", languageCode);
		return (HotelVO) getSqlMapClientTemplate().queryForObject("getHotelByCodeMC2",params);
	}
	
	@Override
	public Hotel getHotelByCodeMC3(String chainCode, String hotelCode){
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelCode", hotelCode);
		params.put("chainCode", chainCode);
		return (Hotel) getSqlMapClientTemplate().queryForObject("getHotelByCodeMC3", params);
	}

	@Override
	public HotelVO getHotelByIdMC(String hotelId) {
		return this.getHotelByIdMC(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public HotelVO getHotelByIdMC(String hotelId,String languageCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("languageCode", languageCode);
		return (HotelVO) getSqlMapClientTemplate().queryForObject("getHotelByIdMC",params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelVO> searchHotelMC(HotelCreteria creteria) {
		//如果没有设置酒店语言
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguageCode())){
			creteria.setLanguageCode(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchHotelMC",creteria);
	}

	@Override
	public Integer searchHotelCountMC(HotelCreteria creteria) {
		//如果没有设置酒店语言
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguageCode())){
			creteria.setLanguageCode(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchHotelCountMC",creteria);
	}

	
	@Override
	public List<HotelVO> getAllHotelMC() {
		return this.getAllHotelMC(LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelVO> getAllHotelMC(String languageCode) {
		return getSqlMapClientTemplate().queryForList("getAllHotelMC",languageCode);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> loadPrivinces(){
		return getSqlMapClientTemplate().queryForList("loadPrivinces");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> loadCitysByPrivince(String privinceCode){
		return getSqlMapClientTemplate().queryForList("loadCitysByPrivince",privinceCode);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelVO> loadHotelsByCity(String city,String language){
		Map<String, String> params = new HashMap<String, String>();
		params.put("city", city);
		params.put("languageCode", language);
		return getSqlMapClientTemplate().queryForList("loadHotelsByCity",params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> getUserHotelByChainAndUserId(Map<String, Object> params){
		return getSqlMapClientTemplate().queryForList("getUserHotelByChainAndUserId", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> getUserHotelByChainCodeAndUserId(Map<String, Object> params) {
		return getSqlMapClientTemplate().queryForList("getUserHotelByChainCodeAndUserId", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelVO> getHotelByChannelCode(String chainCode,String channel, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("channel", channel);
		params.put("chainCode", chainCode);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getHotelByChannelCode",params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getPrivinces(String language) {
		//如果没有设置酒店语言
		if(language==null){
			language=LanguageCode.MAIN_LANGUAGE_CODE;
		}
		return getSqlMapClientTemplate().queryForList("getPrivinces",language);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCitysByPrivince(String privinceCode,String language) {
		//如果没有设置酒店语言
		if(language==null){
			language=LanguageCode.MAIN_LANGUAGE_CODE;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("privinceCode", privinceCode);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getCitysByPrivince",params);
	}

	@Override
	public HotelVO gethotelRemindInfo(String hotelId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("languageCode", LanguageCode.MAIN_LANGUAGE_CODE);
		return (HotelVO) getSqlMapClientTemplate().queryForObject("gethotelRemindInfo",params);
	}

	@Override
	public void updateHotelPMSHeartBeat(Hotel hotel) {
		getSqlMapClientTemplate().update("updateHotelPMSHeartBeat",hotel);
	}

	@Override
	public List<Hotel> getAllHoteleRemind() {
		return getSqlMapClientTemplate().queryForList("getAllHoteleRemind");
	}

}
