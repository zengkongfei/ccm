package com.ccm.api.dao.hotel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.HotelCreditLimitBinding;

public interface HotelCreditLimitBindingDao extends GenericDao<HotelCreditLimitBinding, String>{
	

	void addHotelCreditLimitBinding(HotelCreditLimitBinding hotelCreditLimitBinding);

	List<HotelCreditLimitBinding> findHotelsCreditLimit(String creditLimitId);

	Integer removeBindingHotel(String hotelId,String channelId);
	
	Integer removeBindingHotels(String creditLimitId);

	void updateTotalRoomRev(HotelCreditLimitBinding hotelCreditLimitBinding);

	HotelCreditLimitBinding checkExisted(String channelId, String hotelId);
	
	/**
	 * 酒店id集合
	 * @param creditLimitId
	 * @return
	 */
	List<String> findHotelIds(String creditLimitId);
}
