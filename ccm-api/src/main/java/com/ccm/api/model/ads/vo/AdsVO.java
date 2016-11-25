
package com.ccm.api.model.ads.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * 直连- ads 房价VO
 *
 */
public class AdsVO implements Serializable {

	
	private static final long serialVersionUID = 4437405822426690422L;

	private String chainCode;  // 集团代码
	
	private String hotelCode;  // 酒店代码
	
    private  Set<AdsAvailablilityVO> availVOSet;
    
    private  Set<AdsRateUpdateVO> rateUpdateVOSet;
    
    public void addAvailablilityVO(AdsAvailablilityVO availablilityVO){
    	if(availVOSet== null){
    		availVOSet = new HashSet<AdsAvailablilityVO>();
		}
    	availVOSet.add(availablilityVO);
    }
    
    public void addAdsRateUpdateVO(AdsRateUpdateVO rateUpdateVO){
    	if(rateUpdateVOSet== null){
    		rateUpdateVOSet = new HashSet<AdsRateUpdateVO>();
		}
    	rateUpdateVOSet.add(rateUpdateVO);
    }

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public Set<AdsAvailablilityVO> getAvailVOSet() {
		return availVOSet;
	}

	public void setAvailVOSet(Set<AdsAvailablilityVO> availVOSet) {
		this.availVOSet = availVOSet;
	}

	public Set<AdsRateUpdateVO> getRateUpdateVOSet() {
		return rateUpdateVOSet;
	}

	public void setRateUpdateVOSet(Set<AdsRateUpdateVO> rateUpdateVOSet) {
		this.rateUpdateVOSet = rateUpdateVOSet;
	}

	
    
	
}
