/**
 * 
 */
package com.ccm.api.util;

import java.util.concurrent.atomic.AtomicInteger;

import com.ccm.api.model.enume.OXIStatusEnum;

/**
 * @author Jenny
 * 
 */
public class OwsOxiUtil {

	public static String getReservationLabel(String hotelCode, String msgId,String messageType) {
		// <?Label SSMCL|RESERVATION|646855|NEW?>
		StringBuffer sb = new StringBuffer("<?Label ");
		sb.append(hotelCode);
		sb.append("|");
		sb.append(messageType);
		sb.append("|");
		sb.append(msgId);
		sb.append("|");
		sb.append(OXIStatusEnum.NEW);
		sb.append("?>");
		return sb.toString();
	}

	private static AtomicInteger counter = new AtomicInteger(0);

	/**
	 * 长生消息id
	 */
	public static Long getAtomicCounter18() {
		if (counter.get() > 999999) {
			counter.set(1);
		}
		long time = System.currentTimeMillis();
		long returnValue = time * 100 + counter.incrementAndGet();
		return returnValue;
	}

	public static void main(String[] args) {
		System.out.println("'" + OXIStatusEnum.SEND_READY + "','" + OXIStatusEnum.SEND_REQUEUED + "'");
		System.out.println(getAtomicCounter18());
	}
}
