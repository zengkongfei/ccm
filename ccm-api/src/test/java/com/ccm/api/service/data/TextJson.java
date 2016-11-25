package com.ccm.api.service.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;


public class TextJson {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String chainI18ns2 = "[{codeNo:'en_GB',name:'template one'},{codeNo:'en_US',name:'template one2'},{codeNo:'en_GB',description:'asdfasdfsdfasf'}]";
		
		JSONArray arry = JSONArray.parseArray(chainI18ns2);
		Iterator<Object> iter = arry.iterator();
		List<Map<String, String>> i18nMapList = new ArrayList<Map<String,String>>();
		
		while (iter.hasNext()) {

			Map<String,String> map = (Map<String, String>) iter.next();
			
			//是否需要存在新的语言
			boolean flag = true;
			
			for (Map<String, String> i18nMap : i18nMapList) {
				if(i18nMap.get("codeNo").equals(map.get("codeNo"))){
					i18nMap.putAll(map);
					flag = false;
					break;
				}
			}
			
			if(flag){
				i18nMapList.add(map);
			}

		}
		
		for (Map<String, String> i18nMap : i18nMapList) {
			System.out.println(i18nMap.get("codeNo"));
			System.out.println(i18nMap.get("name"));
			System.out.println(i18nMap.get("description"));
		}
		
	}
}
