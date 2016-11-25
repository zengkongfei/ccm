package com.ccm.api.dao.pms.bean;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ccm.api.dao.pms.utils.DAOBean;

public class StatisticsBean {
	public List<Map<String, Object>> getStatisticsData(int type, String fromDate, String toDate, String hotelid, boolean isMany) throws SQLException{
		String cond = "";
		if(type == 3){
			cond = "month(a.dt)";
		}else{
			cond = "a.dt";
		}
		String sql = "select " + cond + " as dt,sum(IFNULL(m.rn,0)) as rsum, sum(mr.setrate) as rateSum from " +
				"(select r.date as dt from rsvtype as r group by r.date) as a " +
				"left join " +
				"(select 1 as rn,arr,dep,charge,accnt,hotelid from master where sta<>'X') as m " +
				"on " +
				"((a.dt>=m.arr AND a.dt<m.dep) or (a.dt = m.arr AND m.arr=m.dep)) " +
				"left join master_rate as mr " +
				"on " +
				"mr.date=a.dt and mr.accnt=m.accnt and mr.hotelid=m.hotelid " +
				"where a.dt>=? and a.dt<=?";
		if(!isMany){
			sql = sql + " and m.hotelid='" + hotelid + "' ";
		}else{
			sql = sql + " and m.hotelid in (" + hotelid + ") ";
		}
		sql = sql + " group by " + cond + "  order by a.dt";
		return DAOBean.getResultMapList(sql, fromDate, toDate);
	}
	
	public int getMaxRoomNum(String hotelid) throws SQLException{
		String sql = "select count(0) as rm_count from rmsta where hotelid in (" + hotelid + ")";
		Object[] obj = DAOBean.getResult(sql);
		
		if(obj == null || obj.length == 0 || obj[0] == null){
			return 0;
		}else{
			if(obj[0].toString().isEmpty()){
				return 0;
			}else{
				return Integer.parseInt(obj[0].toString());
			}
		}
	}
}
