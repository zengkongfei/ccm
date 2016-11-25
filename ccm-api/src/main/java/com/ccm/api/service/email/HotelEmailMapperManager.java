package com.ccm.api.service.email;

import com.ccm.api.model.email.HotelEmailMapper;
import com.ccm.api.service.base.GenericManager;

public interface HotelEmailMapperManager extends GenericManager<HotelEmailMapper, String> {

	String getEmailByChainAndHotelCode(String chainCode, String hotelCode);

	String sendEmail2HotelOff(String hotelCode, String hotelName, String email);

}
