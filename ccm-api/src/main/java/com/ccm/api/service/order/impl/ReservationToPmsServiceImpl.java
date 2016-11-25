/**
 * 
 */
package com.ccm.api.service.order.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ccm.api.model.constant.MessageType;
import com.ccm.api.service.order.AbstractReservationToPmsService;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.DateUtils;
import com.ccm.api.util.OwsOxiUtil;
import com.ccm.api.util.XMLUtil;
import com.ccm.oxi.profile.MfNegotiatedRates;
import com.ccm.oxi.profile.NegotiatedRate;
import com.ccm.oxi.reservation.MfShareAction;
import com.ccm.oxi.reservation.Rate;
import com.ccm.oxi.reservation.Reservation;
import com.ccm.oxi.reservation.RoomStay;

/**
 * @author Jenny
 * 
 */
@Service("reservationToPmsService")
public class ReservationToPmsServiceImpl extends AbstractReservationToPmsService {

	private Log log = LogFactory.getLog(ReservationToPmsServiceImpl.class);

	/**
	 * 渠道协议
	 */
	public MfNegotiatedRates buildMfNegotiatedRates(String hotelCode, String ratePlanCode, Date channelStart, Date channelEnd, String YYYY_MM_DD_HH_MM_SS) {
		MfNegotiatedRates mnr = new MfNegotiatedRates();
		NegotiatedRate oxiNR = new NegotiatedRate();
		oxiNR.setMfResort(hotelCode);
		oxiNR.setRateCode(ratePlanCode);
		channelStart = DateUtil.convertDateToDate(YYYY_MM_DD_HH_MM_SS, channelStart);
		oxiNR.setRateBeginDate(DateUtils.dateToXmlDate(channelStart));
		channelEnd = DateUtil.convertDateToDate(YYYY_MM_DD_HH_MM_SS, channelEnd);
		oxiNR.setRateEndDate(DateUtils.dateToXmlDate(channelEnd));
		mnr.getNegotiatedRate().add(oxiNR);
		return mnr;
	}

	/**
	 * 设置mfShareAction,mfReservationAction
	 */
	public void setMfAction(RoomStay roomStay, String mfReservationAction) {
		roomStay.setMfShareAction(MfShareAction.NA);
		roomStay.setMfReservationAction(mfReservationAction);
	}

	/**
	 * 设置大小，小孩数量
	 */
	public void setMfAdultsChildren(Rate rate, int adults, int children) {
		rate.setMfAdults(adults);
		rate.setMfChildren(children);
	}

	/**
	 * 生成发PMS的XML
	 */
	public String buildPmsXML(Reservation r, String msgId) throws Exception {
		String hotelCode = r.getHotelReference().getHotelCode();
		log.info(msgId);
		String xml = XMLUtil.JAXBParseToXmlNoSA(r, OwsOxiUtil.getReservationLabel(hotelCode, msgId, MessageType.RESERVATION));
		xml = xml.replace("xmlns:ns2", "xmlns");
		xml = xml.replace("<Profile", "<Profile xmlns=\"profile.fidelio.2.0\"");
		xml = xml.replace("<CreditCard", "<CreditCard xmlns=\"profile.fidelio.2.0\"");
		return xml;
	}

}
