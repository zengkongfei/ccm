/**
 * 
 */
package com.ccm.api.service.ota;

import org.opentravel.ota._2003._05.OTAHotelRatePlanRQ;
import org.opentravel.ota._2003._05.OTAHotelRatePlanRS;

/**
 * @author Jenny
 * 
 */
public interface HotelRatesManager {

	OTAHotelRatePlanRS buildHotelRatePlanRS(OTAHotelRatePlanRQ request);

}
