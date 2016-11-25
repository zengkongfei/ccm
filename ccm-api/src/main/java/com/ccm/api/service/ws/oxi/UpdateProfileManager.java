package com.ccm.api.service.ws.oxi;

import java.util.List;
import java.util.Map;

import _0._2.fidelio.profile.Profile;

public interface UpdateProfileManager {

	public void updateRateCode(Profile profile, String hotelCode,String message,List<String> rateList);
	
	public void savePmsMsg(Map<String, String> receive);
	
	public void runAutoPublishing(Profile profile, String propertyName,List<String> rateList);
	
}
