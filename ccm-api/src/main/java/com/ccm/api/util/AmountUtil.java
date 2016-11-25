/**
 * 
 */
package com.ccm.api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author Jenny
 * 
 */
public class AmountUtil {

	public static BigDecimal add(BigDecimal srcA, BigDecimal desA) {
		if (srcA != null) {
			if (desA != null) {
				return srcA.add(desA).setScale(4, BigDecimal.ROUND_HALF_UP);
			} else {
				return srcA.setScale(4, BigDecimal.ROUND_HALF_UP);
			}
		} else {
			if (desA != null) {
				return desA.setScale(4, BigDecimal.ROUND_HALF_UP);
			} else {
				return BigDecimal.ZERO;
			}
		}
	}

	public static BigDecimal reduce(BigDecimal srcA, BigDecimal desA) {
		if (srcA == null) {
			return new BigDecimal(0).subtract(desA);
		} else {
			return srcA.subtract(desA);
		}
	}

	public static BigDecimal convert2HalfUp(BigDecimal amount) {
		if (amount == null) {
			return null;
		}
		amount = amount.add(new BigDecimal(0.000001));
		DecimalFormat df = new DecimalFormat("#.##");
		amount = new BigDecimal(df.format(amount));
		return amount;
	}

	public static BigDecimal convert2ByFloorMode(BigDecimal amount){
		if (amount == null) {
			return null;
		}
		DecimalFormat formater = new DecimalFormat();
		formater.setMaximumFractionDigits(2);
		formater.setGroupingSize(0);
		formater.setRoundingMode(RoundingMode.FLOOR);
		amount =new BigDecimal(formater.format(amount));
		return amount;
	}
}
