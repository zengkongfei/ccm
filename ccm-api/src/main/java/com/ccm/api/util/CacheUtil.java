package com.ccm.api.util;

import java.util.HashMap;
import java.util.Map;

import com.ccm.api.model.cfg.SysCfg;


public class CacheUtil {
	
	public static String KEY_AREA = "AREA_"; // 地理位置
	public static String KEY_DIC = "DIC_"; //字典
	private static Long TIMEOUT = 600000L; //超时时间10分钟
	
	public static Long TIMEOUT_DAY = 24 * 60 *60L; //超时时间10分钟
	
	public static SysCfg sysCfg;
	
	//缓存时间
	private static Map <String, Long> TIME_MAP = new HashMap<String, Long>();
	private static Map <String, Object> CONTAINER_MAP = new HashMap<String, Object>();
	
	/**
	 * 加入缓存
	 * @param key
	 * @param object
	 */
	public static void put(String key, Object object)
	{
		CONTAINER_MAP.put(key, object);
	}
		
	/**
	 * 加入缓存, 有过期
	 * @param key
	 * @param object
	 */
	public static void putTimeOut(String key, Object object)
	{
		TIME_MAP.put(key, System.currentTimeMillis());
		CONTAINER_MAP.put(key, object);
	}
	
	/**
	 * 超时则清空缓存
	 * @param key
	 * @return
	 */
	public static Object get(String key)
	{
		Long cacheTime = TIME_MAP.get(key);
		Long now = System.currentTimeMillis();
		if(cacheTime != null && now - cacheTime > TIMEOUT){
			CONTAINER_MAP.remove(key);
			return null;
		}
		else{
			return CONTAINER_MAP.get(key);
		}
	}
	
	public static void setSysCfg(SysCfg sysCfg){
		CacheUtil.sysCfg = sysCfg;
	}
	
	public static SysCfg getSysCfg(){
		return sysCfg;
	}
	
}
