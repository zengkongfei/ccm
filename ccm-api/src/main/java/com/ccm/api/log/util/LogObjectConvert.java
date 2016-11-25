package com.ccm.api.log.util;

import java.util.ArrayList;
import java.util.List;

import com.ccm.api.log.model.LogDetail;
import com.ccm.api.log.vo.LogVO;

public class LogObjectConvert {
	
	/**
	 * LogVO转换为List<LogDetail>
	 * @param LogVO vo
	 * @return
	 */
	public static List<LogDetail> logvoOfLogDetail(LogVO vo){
		List<LogDetail> logDetailList = new ArrayList<LogDetail>();
		List<String> changeList = vo.getChangeList();
		//判断Change是否为空
		if(changeList != null && !changeList.isEmpty()){
			for(String str:changeList){
				LogDetail logDetail = new LogDetail();
				logDetail.setPrimaryKey(vo.getPrimaryKey());
				logDetail.setClassName(vo.getEntityName());
				logDetail.setAttributeName(str.substring(0, str.indexOf("=")));
				logDetail.setNewValue(str.substring(str.indexOf("=")+1, str.indexOf("::")));
				logDetail.setOldValue(str.substring(str.lastIndexOf("=")+1));
				logDetailList.add(logDetail);
			}
		}
		return logDetailList;
	}
}
