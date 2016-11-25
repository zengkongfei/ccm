/**
 * 
 */
package com.ccm.api.model.hotel.vo;

import java.util.ArrayList;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchResult;
import com.ccm.api.model.hotel.HotelSwitch;

/**
 * 查询返回结果类
 */
public class HotelSwitchResult extends SearchResult<HotelSwitch> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2715281306793415590L;
	
	private List<HotelSwitch> hotelSwitchList = new ArrayList<HotelSwitch>();

	public List<HotelSwitch> getHotelSwitchList() {
		return hotelSwitchList;
	}

	public void setHotelSwitchList(List<HotelSwitch> hotelSwitchList) {
		this.hotelSwitchList = hotelSwitchList;
	}

	
}
