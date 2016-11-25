/**
 * 
 */
package com.ccm.api.dao.hotel.ibatis;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.HotelSwitchDao;
import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.hotel.vo.HotelSwitchCriteria;
import com.ccm.api.model.hotel.vo.HotelSwitchResult;
import com.ccm.api.util.CommonUtil;

/**
 * @author Water
 * 
 */
@Repository("hotelSwitchDao")
public class HotelSwitchDaoibatis extends GenericDaoiBatis<HotelSwitch, String> implements HotelSwitchDao {
	public HotelSwitchDaoibatis() {
		super(HotelSwitch.class);
	}

	@Override
	public void addHotelSwitch(HotelSwitch hotelSwitch) {
		
		if(null!=hotelSwitch && hotelSwitch.getHsId()==null){
			hotelSwitch.setHsId(UUID.randomUUID().toString().replace("-", ""));
		}
		getSqlMapClientTemplate().insert("addHotelSwitch", hotelSwitch);
	}

	@Override
	public void updateHotelSwitch(HotelSwitch hotelSwitch) {
		getSqlMapClientTemplate().update("updateHotelSwitch", hotelSwitch);
	}
	@Override
	public void updateByHotelId(HotelSwitch hotelSwitch) {
		getSqlMapClientTemplate().update("updateByHotelId", hotelSwitch);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HotelSwitchResult getHotelSwitch(HotelSwitchCriteria criteria) {
		HotelSwitchResult searchResult=new HotelSwitchResult();
		
		int totalCount=(Integer) getSqlMapClientTemplate().queryForObject("countHotelSwitch", criteria);
		searchResult.setTotalCount(totalCount);
		List<HotelSwitch> resultList=getSqlMapClientTemplate().queryForList("getHotelSwitch", criteria);
		searchResult.setResultList(resultList);
		
		return searchResult;
	}

	@Override
	public HotelSwitch getByChainAndHotel(String chainCode,String hotelCode) {
		HotelSwitchCriteria criteria = new HotelSwitchCriteria();
		criteria.setChainCode(chainCode);
		criteria.setHotelCode(hotelCode);
		List<HotelSwitch> resultList=getSqlMapClientTemplate().queryForList("getHotelSwitch", criteria);
		if(CommonUtil.isNotEmpty(resultList)){
			return resultList.get(0);
		}
		return null;
	}
	
	@Override
	public HotelSwitch getByHotelId(String hotelId) {
		return (HotelSwitch) getSqlMapClientTemplate().queryForObject("getByHotelId", hotelId);
	}

}
