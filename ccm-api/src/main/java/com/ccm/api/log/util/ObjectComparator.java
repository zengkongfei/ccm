package com.ccm.api.log.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ObjectComparator {
	/**
	 * 比较两个map对象，返回有差别的entryList
	 * 如："rateCode=新值:rateCode=旧值"
	 * @param newMap
	 * @param oldMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String> mapComparator(Map<String, Object> newMap,Map<String, Object> oldMap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> list = new ArrayList<String>();
		String newKey = "";
		String oldKey = "";
		Object newVal = null;
		Object oldVal = null;
		List<Entry<String, Object>> newList = new ArrayList(newMap.entrySet());
		List<Entry<String, Object>> oldList = new ArrayList(oldMap.entrySet());
		if(newList.equals(oldList)){
			return null;
		}
		for(int i=0;i<newList.size();i++){
			newKey = newList.get(i).getKey();
			if(!"createdBy".equals(newKey) && !"createdTime".equals(newKey) && !"updatedBy".equals(newKey) && 
					!"lastModifyTime".equals(newKey) && !"delFlag".equals(newKey) && !"sessionKey".equals(newKey) && 
					!"class".equals(newKey)){
				newVal = newList.get(i).getValue();
				if(newVal instanceof Date){
					newVal = sdf.format(newVal);
				}
				if(null==newVal){
					newVal = "";
				}
				if(newVal.toString().equals("true")){
					newVal = 1;
				}
				if(newVal.toString().equals("false")){
					newVal = 0;
				}
				if(oldList.isEmpty()){
					list.add(newKey + "=" + newVal + "::" + newKey + "=" + "");
				}else{
					for(Entry<String, Object> en:oldList){
						oldKey = en.getKey();
						if(newKey.equals(oldKey)){
							oldVal = en.getValue();
							if(oldVal instanceof Date){
								oldVal = sdf.format(oldVal);
							}
							if(null==oldVal){
								oldVal = "";
							}
							if(oldVal.toString().equals("true")){
								oldVal = 1;
							}
							if(oldVal.toString().equals("false")){
								oldVal = 0;
							}
							//比较字符串，不相等添加到list中
							if(!newVal.toString().equals(oldVal.toString())){
								list.add(newKey + "=" + newVal + "::" + oldKey + "=" + oldVal);
							}
						}
					}
				}
			}
		}
		return list;
	}
}
