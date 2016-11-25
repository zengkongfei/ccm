/**
 * 
 */
package com.ccm.api.dao.hotel.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.Hoteli18nDao;
import com.ccm.api.model.hotel.HotelI18n;

/**
 * @author Jenny
 * 
 */
@Repository("hoteli18nDao")
public class Hoteli18nDaoibatis extends GenericDaoiBatis<HotelI18n, String> implements Hoteli18nDao {

	public Hoteli18nDaoibatis() {
		super(HotelI18n.class);
	}

	@SuppressWarnings("unchecked")
	/**
	 * 根据酒店ID,语言（语言不为空时）查询酒店多语言表
	 */
	public List<HotelI18n> getHoteli18nByObj(HotelI18n hi) {
		return getSqlMapClientTemplate().queryForList("getHotelI18ns", hi);
	}
	
	/**
	 * 删除多语言中的数据
	 * @param hotelId
	 */
	public void deleteHotelI18nByHotelId(String hotelId) {
		getSqlMapClientTemplate().update("deleteHotelI18nByHotelId",hotelId);
	}

}
