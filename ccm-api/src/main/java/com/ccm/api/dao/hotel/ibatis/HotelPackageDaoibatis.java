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
import com.ccm.api.dao.hotel.HotelPackageDao;
import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.HotelPackage;
import com.ccm.api.model.hotel.HotelPackageI18n;
import com.ccm.api.model.hotel.vo.HotelPackageCreteria;
import com.ccm.api.model.hotel.vo.HotelPackageVO;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.roomType.RoomType;

@Repository("hotelPackageDao")
public class HotelPackageDaoibatis extends GenericDaoiBatis<HotelPackage, String> implements HotelPackageDao{

	public HotelPackageDaoibatis(){
		super(HotelPackage.class);
	}
	
	@Override
	public HotelPackage addHotelPackage(HotelPackage hotelPackage) {
		hotelPackage.setCreatedBy(SecurityHolder.getUserId());
		hotelPackage.setCreatedTime(new Date());
		hotelPackage.setHotelPackageId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addHotelPackage",hotelPackage);
		return hotelPackage;
	}
	
	@Override
	public HotelPackageI18n addHotelPackageI18n(HotelPackageI18n i18n) {
		i18n.setCreatedBy(SecurityHolder.getUserId());
		i18n.setCreatedTime(new Date());
		i18n.setHotelPackageMId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addHotelPackageI18n",i18n);
		return i18n;
	}
	
	@Override
	public void updaetHotelPackage(HotelPackage hotelPackage) {
		hotelPackage.setUpdatedBy(SecurityHolder.getUserId());
		hotelPackage.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updaetHotelPackage",hotelPackage);
	}
	
	@Override
	public void updateHotelPackageI18n(HotelPackageI18n hotelPackageI18n) {
		hotelPackageI18n.setUpdatedBy(SecurityHolder.getUserId());
		hotelPackageI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateHotelPackageI18n",hotelPackageI18n);
	}
	
	@Override
	public void deleteHotelPackageById(HotelPackageVO vo ){
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteHotelPackageById", vo);
	}

	@Override
	public void deleteHotelPackageByHotelId(String hotelId) {
		getSqlMapClientTemplate().update("deleteHotelPackageByHotelId", hotelId);
	}
	
	@Override
	public void deleteHotelPackageI18nById(String hotelPackageId) {
		getSqlMapClientTemplate().update("deleteHotelPackageI18nById", hotelPackageId);
	}

	@Override
	public HotelPackageVO getHotelPackageById(String hotelPackageId) {
		return this.getHotelPackageById(hotelPackageId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public HotelPackageVO getHotelPackageById(String hotelPackageId,String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelPackageId", hotelPackageId);
		params.put("language", language);
		return (HotelPackageVO) getSqlMapClientTemplate().queryForObject("getHotelPackageById", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelPackageVO> getHotelPackageByObj(HotelPackageVO vo) {
		if(vo!=null&&StringUtils.isBlank(vo.getLanguage())){
			vo.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("getHotelPackageByObj", vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelPackageVO> searchHotelPackage(HotelPackageCreteria creteria) {
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchHotelPackage", creteria);
	}

	@Override
	public Integer searchHotelPackagecount(HotelPackageCreteria creteria) {
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("countHotelPackage", creteria);
	}

	@Override
	public HotelPackageVO getHotelPackageByHotelId(String hotelId) {
		return this.getHotelPackageByHotelId(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public HotelPackageVO getHotelPackageByHotelId(String hotelId,String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		return (HotelPackageVO) getSqlMapClientTemplate().queryForObject("getHotelPackageByHotelId", params);
	}
	
	@Override
	public List<PackageVO> getDontUseHotelPackage(String hotelId){
		return this.getDontUseHotelPackage(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getDontUseHotelPackage(String hotelId,String language){
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("language", language);
		return getSqlMapClientTemplate().queryForList("getDontUseHotelPackage", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Rateplan> getUseRatePackage(String hotelId,String packageId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("packageId", packageId);
		return getSqlMapClientTemplate().queryForList("getUseRatePackage", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomType> getUseRoomPackage(String hotelId,String packageId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("packageId", packageId);
		return getSqlMapClientTemplate().queryForList("getUseRoomPackage", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DynamicPackage> getUseDynamicPackage(String hotelId,String packageId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("packageId", packageId);
		return getSqlMapClientTemplate().queryForList("getUseDynamicPackage", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelPackageI18n> getHotelPackageI18ns(String hotelPackageId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelPackageId", hotelPackageId);
		return getSqlMapClientTemplate().queryForList("getHotelPackageI18ns", params);
	}
	
}
