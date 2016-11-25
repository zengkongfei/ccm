/**
 * 
 */
package com.ccm.api.service.common;

import java.util.List;

/**
 * @author Jenny
 * 
 */
public interface RemindManager {

	List<String> getMasterByOsta(String osta, String hotelId);

	List<String> getFeedBackByStatus(String status, String hotelId);

	void havedSeenRemind(String hotelId);

	void updateHaveSeen(String hotelId, String type);

}
