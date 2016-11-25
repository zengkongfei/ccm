/**
 * 
 */
package com.ccm.api.service.pay.mpay;

import com.ccm.api.model.order.Master;

/**
 * @author Jenny
 * 
 */
public interface MpayManager {

	String buildPayInfo(Master orderInfo);

}
