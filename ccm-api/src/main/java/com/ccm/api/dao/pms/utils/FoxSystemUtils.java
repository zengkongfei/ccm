package com.ccm.api.dao.pms.utils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ccm.api.model.hotel.vo.HotelVO;

public class FoxSystemUtils {
	
	public static String getSys_extraid(String cat, String hotelid) throws SQLException{
//		Object[] obj = DAOBean.getResult("select id from sys_extraid where cat=? and hotelid=?", cat, hotelid);
//		if(obj == null || obj.length == 0 || obj[0] == null){
//			int i = DAOBean.insert("INSERT INTO sys_extraid(hotelid,sign,cat,id,descript) VALUES(?,?,?,?,?)",hotelid,"2",cat,"2",cat);
//			return "1";
//		}else{
//			long id = Long.parseLong(obj[0].toString());
//			DAOBean.update("update sys_extraid set id=? where cat=? and hotelid=?",id + 1, cat, hotelid);
//			return id + "";
//		}
		return UUID.randomUUID().toString().replace("-","");
	}
	
	public static long getTbAccnt(String accnt, String hotelid) throws SQLException{
		Object[] obj = DAOBean.getResult("select crsno from master where accnt=? and hotelid=?", accnt, hotelid);
		if(obj == null || obj.length == 0 || obj[0] == null){
			return 0;
		}else{
			if(obj[0].toString().isEmpty()){
				return 0;
			}else{
				long id = Long.parseLong(obj[0].toString());
				return id;
			}
		}
	}
	
	public static Object[] getAccntByTB(String tid, String hotelid) throws SQLException{
		Object[] obj = DAOBean.getResult("select accnt from master where crsno=? and hotelid=?", tid, hotelid);
		if(obj == null || obj.length == 0 || obj[0] == null){
			return null;
		}else{
//			if(obj[0].toString().isEmpty()){
//				return "";
//			}else{
//				return obj[0].toString();
//			}
			return obj;
		}
	}
	
	public static String getName2ByAccnt(String accnt) throws SQLException {
		Object[] obj = DAOBean.getResult("select name2 from master where accnt=?", accnt);
		if (obj == null || obj.length == 0 || obj[0] == null) {
			return "";
		} else {
			if (obj[0].toString().isEmpty()) {
				return "";
			} else {
				return obj[0].toString();
			}
		}
	}
	
	public static String getHotelid(String userId) throws SQLException{
		Object[] obj = DAOBean.getResult("SELECT hotelId FROM userrole WHERE userId=?", userId);
		if(obj == null || obj.length == 0 || obj[0] == null){
			return "";
		}else{
			if(obj[0].toString().isEmpty()){
				return "";
			}else{
				return obj[0].toString();
			}
		}
	}
	
	public static List<Object[]> getHotelInfo(String userId) throws SQLException{
		List<Object[]> obj = DAOBean.getResultList("SELECT h.hotelId, h.hotelName FROM userrole as u,hotel_i18n as h WHERE u.hotelId=h.hotelId and u.userId=?  ORDER BY h.hotelName", userId);
		return obj;
	}
	
	public static List<Map<String, Object>> getHotelInfoToMap(String userId) throws SQLException{
		List<Map<String, Object>> obj = DAOBean.getResultMapList("SELECT h.hotelId, h.hotelName FROM userrole as u,hotel_i18n as h WHERE u.hotelId=h.hotelId and u.userId=?  ORDER BY h.hotelName", userId);
		return obj;
	}
	
	public static Date getMaxDateByRsvtype(String type, String hotelid) throws SQLException{
		Object[] obj = DAOBean.getResult("SELECT MAX(date) FROM rsvtype WHERE type=? AND hotelid=?", type, hotelid);
		if(obj == null || obj.length == 0 || obj[0] == null){
			return new Date();//返回一个当前时间把.
		}else{
			return (Date)obj[0];
		}
	}
	
	public static Object[] validateHasResv(String tbId, String hotelid) throws SQLException{
		Object[] obj = DAOBean.getResult("SELECT accnt FROM master WHERE hotelid=? AND crsno=?", hotelid, tbId);
		if(obj == null || obj.length == 0 || obj[0] == null){
			return null;
		}else{
			return obj;
		}
	}
	
	public static String arrayToStrList(String[] strArr){
		if(strArr == null || strArr.length < 1){
			return "";
		}else{
			String strList = "";
			for(String str : strArr){
				strList += ",'" + str + "'";
			}
			return strList.isEmpty()?"":strList.substring(1);
		}
	}
	public static String hotelVOToStrList(List<HotelVO> hotelids){
		if(hotelids == null || hotelids.size() < 1){
			return "";
		}else{
			String strList = "";
			for(HotelVO hotelVO : hotelids){
				strList += ",'" + hotelVO.getHotelId() + "'";
			}
			return strList.isEmpty()?"":strList.substring(1);
		}
	}
}
