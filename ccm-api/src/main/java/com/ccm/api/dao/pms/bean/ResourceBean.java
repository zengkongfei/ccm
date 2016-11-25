package com.ccm.api.dao.pms.bean;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.pms.utils.DAOBean;

public class ResourceBean {
	/**
	 * 查询是否可以做预定
	 * @param params
	 * @return
	 * @throws Exception
	 */ 
	public boolean hasRoom(Map<String, Object> params) throws Exception {
		List<Map<String,Object>> mapList = getAvailableRoomCount(params);
		if(mapList == null || mapList.size() == 0)
			return false;
		for (Map<String, Object> map : mapList) {
			Long available = (Long) map.get("available");
			if(available == null || available == 0)
				return false;
		}
		return true;
	}
	
	/**
	 * 根据条件获取可用房间数
	 * @param params
	 * @return
	 * @throws Exception 
	 */ 
	public List<Map<String,Object>> getAvailableRoomCount(Map<String, Object> params) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT rsv.hotelid,rsv.date,rsv.type,rsv.rate,rsv.channel,max(rsv.type_count)-IF(rm.arr IS NULL,0,count(0)) AS available ");
		sql.append("FROM (SELECT r.hotelid, r.date, r.type, IFNULL(b.type_count,0) AS type_count, r.rate, r.channel ");
		sql.append(" FROM rsvtype r ");
		sql.append(" LEFT JOIN ( SELECT type, count(0) type_count FROM rmsta GROUP BY type) b ");
		sql.append(" ON r.type = b.type) AS rsv ");
		sql.append("LEFT JOIN (SELECT arr,dep,type FROM master WHERE sta='I' OR sta='R') AS rm ");
		sql.append("ON rsv.type=rm.type AND ((rsv.date >= rm.arr AND rsv.date < rm.dep) OR (rsv.date=rm.arr AND rm.arr=rm.dep)) ");
		sql.append("WHERE hotelid=? AND rsv.type=? AND rsv.date>=? AND rsv.date<=? AND rsv.channel=? ");
		sql.append("GROUP BY rsv.hotelid,rsv.date,rsv.type,rsv.rate,rsv.channel ");
		
		String hotelid = (String) params.get("hotelid");
		String type = (String) params.get("type");
		String arr = (String) params.get("arr");
		String dep = (String) params.get("dep");
		String channel = (String) params.get("channel");
		
		return DAOBean.getResultMapList(sql.toString(), hotelid, type, arr, dep, channel);
	}
}

