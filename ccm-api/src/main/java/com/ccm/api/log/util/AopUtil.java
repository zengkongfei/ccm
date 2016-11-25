package com.ccm.api.log.util;

import com.ccm.api.model.base.BaseObject;

public class AopUtil {
	
	/**
	 * 根据参数获取参数名称
	 * @param parame
	 * @return
	 */
	public static String getParameName(Object parame){
		String parameName = "";
		parameName = parame.getClass().getName();
		parameName = parameName.substring(parameName.lastIndexOf(".")+1);
		return parameName;
	}
	
	/**
	 * 替换字符串中的大写字母为下划线加小写字母
	 * 如："A"==>"_a"
	 */
	public static String stringConvert(String str){
		String s = "";
		for (int i=0;i<str.length();i++) {
			if(str.charAt(i)>='A' && str.charAt(i)<='Z'){
				s = s+"_"+(char)(str.charAt(i)+32);
			}else{
				s = s+(char)(str.charAt(i));
			}
		}
		return s.substring(1);
	}
	
	/**
	 * 判断对象是否不需要监控
	 * @return
	 */
	public static boolean excludeTable(Object[] obj){
		if(obj.length!=1){//参数不为一个时
			return false;
		}
		if(!(obj[0] instanceof BaseObject)){//参数没有继承BaseObject
			return false;
		}
		
		String entityName = AopUtil.getParameName(obj[0]);//参数类型名称
		if("PriceCalc".equalsIgnoreCase(entityName)){//价格日历
			return false;
		}
		return true;
	}
}
