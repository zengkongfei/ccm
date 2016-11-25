/**
 * 
 */
package com.ccm.api.service.order;

import com.ccm.api.model.order.Master;
import com.ccm.oxi.reservation.ReservationActionType;
import com.ccm.oxi.reservation.ReservationAlerts;
import com.ccm.oxi.reservation.ReservationTraces;

/**
 * @author Jenny
 * 
 */
public interface OXIReservationNodesService {

	ReservationAlerts buildReservationAlerts(ReservationActionType reservationActionType, String alerts);

	ReservationTraces buildReservationTraces(String traceDept, String traces);

	void buildCancelFintrx(Master master);

	String buildFintrxMsg(Master master, String total, String beginMsgId);
}
