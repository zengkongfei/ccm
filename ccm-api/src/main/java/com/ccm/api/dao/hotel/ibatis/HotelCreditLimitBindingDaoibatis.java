package com.ccm.api.dao.hotel.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.HotelCreditLimitBindingDao;
import com.ccm.api.model.hotel.HotelCreditLimitBinding;
@Repository("hotelCreditLimitBindingDao")
public class HotelCreditLimitBindingDaoibatis extends GenericDaoiBatis<HotelCreditLimitBinding,String>  implements HotelCreditLimitBindingDao{

	public HotelCreditLimitBindingDaoibatis() {
		super(HotelCreditLimitBinding.class);
	}
	
	@Override
	public void addHotelCreditLimitBinding(HotelCreditLimitBinding hotelCreditLimitBinding){
		getSqlMapClientTemplate().insert("addHotelCreditLimitBinding", hotelCreditLimitBinding);
	}

	@Override
	public Integer removeBindingHotel(String hotelId,String channelId) {
		// TODO Auto-generated method stub
		Map<String,String>paramMap =new HashMap<String,String>();
		paramMap.put("hotelId", hotelId);
		paramMap.put("channelId", channelId);
		return getSqlMapClientTemplate().update("removeBindingHotel", paramMap);
	}
	
	@Override
	public Integer removeBindingHotels(String creditLimitId) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("removeBindingHotels", creditLimitId);
	}
	
	@Override
	public List<HotelCreditLimitBinding> findHotelsCreditLimit(String creditLimitId){
		// TODO Auto-generated method stub
		return  getSqlMapClientTemplate().queryForList("findHotelsCreditLimit", creditLimitId);
		}

	@Override
	public void updateTotalRoomRev(HotelCreditLimitBinding hotelCreditLimitBinding){
		 getSqlMapClientTemplate().update("updateTotalRoomRevOfHotelCreditLimit",hotelCreditLimitBinding);
		
	}
	
	@Override
	public HotelCreditLimitBinding checkExisted(String channelId, String hotelId) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("channelId", channelId);
		map.put("hotelId", hotelId);
		return (HotelCreditLimitBinding) getSqlMapClientTemplate().queryForObject("checkExisted",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findHotelIds(String creditLimitId) {
		return getSqlMapClientTemplate().queryForList("findHotelIds",creditLimitId);
	}

}
