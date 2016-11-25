/**
 * 
 */
package com.ccm.api.util;

/**
 * @author Jenny Liao
 *
 */
public class NumberUtils {

	public static Boolean equals(Integer i1, Integer i2) {
		
		if (i1 == null && i2 == null) {
			return true;
		} 
		
		if ((i1 == null && i2 != null) || (i1 != null && i2 == null)) {
			return false;
		}
		
		if (i1.equals(i2)) {
			return true;
		}
		
		return false;
	}
	
	public static Boolean equals(Double i1, Double i2) {
		
		if (i1 == null && i2 == null) {
			return true;
		} 
		
		if ((i1 == null && i2 != null) || (i1 != null && i2 == null)) {
			return false;
		}
		
		if (i1.equals(i2)) {
			return true;
		}
		
		return false;
	}
	
}
