package com.ccm.api.util;

public class NumberUtil {
	
	/**
	 * 比较两个float数字是否相等
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static boolean compareFloatEqual(float f1,float f2){
		
		
		if (Math.abs(f2 - f1) < 0.00000001) {
		  return true;
		}	
		return false;
	}
	 
}
