/**
 * 
 */
package com.ccm.api.dao.hotel.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.HotelAmenity;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.util.CommonUtil;

/**
 * @author Jenny
 * 
 */
@Repository("hotelDao")
public class HotelDaoibatis extends GenericDaoiBatis<Hotel, String> implements HotelDao {

	public HotelDaoibatis() {
		super(Hotel.class);
	}

	/**
	 * 保存酒店服务设施
	 */
	@Deprecated
	public void saveHotelAmenity(HotelAmenity ha) {
		ha.setHotelAmenityId(CommonUtil.generatePrimaryKey());
		getSqlMapClientTemplate().insert("addHotelAmenity", ha);
	}

	/**
	 * 查询酒店信息(hotelId,chainId,hotelCode,orderRemind,messRemind,cityName, telephone ,email,currencyCode,tbShopName,hotelName,languageCode,address,chainCode)
	 */
	public HotelVO getHotelI18nChainByHotelId(String hotelId) {
		return this.getHotelI18nChainByHotelId(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	/**
	 * 查询酒店信息(hotelId,chainId,hotelCode,orderRemind,messRemind,cityName, telephone ,email,currencyCode,tbShopName,hotelName,languageCode,address,chainCode)
	 */
	public HotelVO getHotelI18nChainByHotelId(String hotelId, String languageCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelId", hotelId);
		param.put("languageCode", languageCode);
		return (HotelVO) getSqlMapClientTemplate().queryForObject("getHotelI18nChainByHotelId", param);
	}
	
	/**
	 * 查询酒店信息(hotelId,chainId,hotelCode,cityName,telephone,fax,email,
	 * currencyCode,tbShopName,postCode,city,countryCode,hotelPushUrl,hotelName,
	 * languageCode,address,chainCode,allotNotificationEmail,privinceName)
	 */
	public HotelVO getPushHotelInfoByHotelId(String hotelId, String languageCode){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelId", hotelId);
		param.put("languageCode", languageCode);
		return (HotelVO) getSqlMapClientTemplate().queryForObject("getPushHotelInfoByHotelId", param);
	}

	@SuppressWarnings("unchecked")
	public List<HotelVO> getAllDirectPmsHotel(String languageCode, Boolean isDirectPms) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("languageCode", languageCode);
		param.put("isDirectPms", isDirectPms);
		return getSqlMapClientTemplate().queryForList("getAllDirectPmsHotel", param);
	}

	@SuppressWarnings("unchecked")
	public List<String> getHotelCodesByHotelIds(List<String> hotelIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelIdList", hotelIds);
		return getSqlMapClientTemplate().queryForList("getHotelCodesByHotelIds", param);
	}

	/**
	 * 根据用户ID获取酒店
	 */
	@SuppressWarnings("unchecked")
	public List<HotelVO> getHotelInfoChainByUserId(String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("languageCode", LanguageCode.MAIN_LANGUAGE_CODE);
		return getSqlMapClientTemplate().queryForList("getHotelInfoChainByUserId", params);
	}

	/**
	 * 根据用户ID获取酒店
	 */
	@SuppressWarnings("unchecked")
	public List<HotelVO> getHotelInfoChainByUserId(String userId, String language) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("languageCode", language);
		return getSqlMapClientTemplate().queryForList("getHotelInfoChainByUserId", params);
	}

	/**
	 * 根据酒店ID获取酒店已存在的服务设施
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Map<String, String> getHotelAmenity(String hotelId) {
		return getSqlMapClientTemplate().queryForMap("getHotelAmenity", hotelId, "codeNo", "hotelAmenityId");
	}

	@Deprecated
	public String getSessionKeyByHotelId(String hotelId) {
		return (String) getSqlMapClientTemplate().queryForObject("getSessionKeyByHotelId", hotelId);
	}

	@Override
	public HotelVO getHotelVoByHotelId(String hotelId) {
		return this.getHotelVoByHotelId(hotelId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public HotelVO getHotelVoByHotelId(String hotelId, String languageCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("languageCode", languageCode);
		return (HotelVO) getSqlMapClientTemplate().queryForObject("getHotelVoByHotelId", params);
	}

	/**
	 * 根据hotelCode查询酒店
	 */
	@SuppressWarnings("unchecked")
	public List<Hotel> getHotelByHotelCode(String hotelCode) {
		return getSqlMapClientTemplate().queryForList("getHotelByHotelCode", hotelCode);
	}

	/**
	 * 只根据hotelCode查询
	 */
	@SuppressWarnings("unchecked")
	public List<Hotel> getHotelAllByHotelCode(String hotelCode) {
		return getSqlMapClientTemplate().queryForList("getHotelAllByHotelCode", hotelCode);
	}

	@SuppressWarnings("unchecked")
	public List<Hotel> getHotelByChainId(String chainId) {
		return getSqlMapClientTemplate().queryForList("getHotelByChainId", chainId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Hotel> getHotelByChainCode(String chainCode) {
		return getSqlMapClientTemplate().queryForList("getHotelByChainCode", chainCode);
	}

	public List<HotelVO> getHotelI18nByCode(String hotelCode, String chainId) {
		return this.getHotelI18nByCode(hotelCode, chainId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	public List<HotelVO> getHotelI18nByCode(String hotelCode, String chainId, String languageCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelCode", hotelCode);
		params.put("chainId", chainId);
		params.put("languageCode", languageCode);
		return getSqlMapClientTemplate().queryForList("getHotelI18nByCode", params);
	}

	/**
	 * 删除服务设施,此处是逻辑删除，即修改删除标志
	 */
	@Deprecated
	public void deleteHotelAmenity(String hotelAmenityId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("hotelAmenityId", hotelAmenityId);
		param.put("delFlag", "1");
		getSqlMapClientTemplate().update("updateHotelAmenity", param);

	}

	@Deprecated
	public void updateHotelStatus(String hotelId, String status) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("hotelId", hotelId);
		param.put("status", status);
		getSqlMapClientTemplate().update("updateHotelStatus", param);

	}

	/**
	 * 设置酒店为启动或停用
	 * 
	 * @param hotelId
	 * @param delFlag
	 */
	public void updateHotelDelFlag(String hotelId, String delFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelId", hotelId);
		param.put("delFlag", delFlag);
		param.put("lastModifyTime", new Date());
		param.put("updatedBy", SecurityHolder.getUserId());
		getSqlMapClientTemplate().update("updateHotelDelFlag", param);

	}

	@Deprecated
	public boolean cleanCCMData(String hotelId) {
		getSqlMapClientTemplate().queryForObject("cleanCCMData", hotelId);
		log.info("###### cleanCCMData  hotelId:" + hotelId);
		return true;
	}

	/**
	 * 根据酒店ID查询酒店与集团的ID和代码及币种
	 * 
	 * @param hotelId
	 * @return
	 */
	public HotelVO getHotelChainByHotelId(String hotelId) {
		return (HotelVO) getSqlMapClientTemplate().queryForObject("getHotelChainByHotelId", hotelId);
	}

	public Hotel getHotel(String hotelId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("hotelId", hotelId);
		return (Hotel) getSqlMapClientTemplate().queryForObject("getHotelOfHotelId", param);
	}

	public Hotel getHotel(String hotelId, String selectFields) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("hotelId", hotelId);
		param.put("selectFields", selectFields);
		return (Hotel) getSqlMapClientTemplate().queryForObject("getHotelFieldsOfHotelId", param);
	}

	@Deprecated
	public Hotel getHotelOfHid(String hid) {
		return (Hotel) getSqlMapClientTemplate().queryForObject("getHotelOfHid", hid);
	}

	@Override
	public Hotel getHotelContainDel(String hotelId) {
		return (Hotel) getSqlMapClientTemplate().queryForObject("getHotelContainDel", hotelId);
	}

	public List<HotelVO> getAllHotels() {
		return this.getAllHotels(LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@SuppressWarnings("unchecked")
	public List<HotelVO> getAllHotels(String languageCode) {
		return getSqlMapClientTemplate().queryForList("getAllHotels", languageCode);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> getBdpHotel(){
		return getSqlMapClientTemplate().queryForList("getBdpHotel");
	}
}
