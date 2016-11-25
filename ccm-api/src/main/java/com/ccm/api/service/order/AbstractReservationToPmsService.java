/**
 * 
 */
package com.ccm.api.service.order;

import java.util.Date;

import com.ccm.oxi.profile.MfNegotiatedRates;
import com.ccm.oxi.reservation.Rate;
import com.ccm.oxi.reservation.Reservation;
import com.ccm.oxi.reservation.RoomStay;

/**
 * @author Jenny
 * 
 */
public abstract class AbstractReservationToPmsService {

	/**
	 * 渠道协议
	 * 
	 * @param hotelCode
	 * @param ratePlanCode
	 * @param channelStart
	 * @param channelEnd
	 * @param dateFormat
	 * @return
	 */
	public abstract MfNegotiatedRates buildMfNegotiatedRates(String hotelCode, String ratePlanCode, Date channelStart, Date channelEnd, String dateFormat);

	/**
	 * 设置mfShareAction,mfReservationAction
	 * 
	 * @param roomStay
	 * @param mfReservationAction
	 */
	public abstract void setMfAction(RoomStay roomStay, String mfReservationAction);

	/**
	 * 设置大小，小孩数量
	 * 
	 * @param rate
	 * @param adults
	 * @param children
	 */
	public abstract void setMfAdultsChildren(Rate rate, int adults, int children);

	/**
	 * 生成发PMS的XML
	 * 
	 * @param r
	 * @param msgId
	 * @return
	 * @throws Exception
	 */
	public abstract String buildPmsXML(Reservation r, String msgId) throws Exception;

}
