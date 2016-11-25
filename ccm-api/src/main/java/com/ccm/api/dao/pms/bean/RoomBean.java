package com.ccm.api.dao.pms.bean;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccm.api.dao.pms.form.ReservationForm;
import com.ccm.api.dao.pms.utils.DAOBean;
import com.ccm.api.dao.pms.utils.FoxSystemUtils;
import com.ccm.api.dao.pms.utils.SystemData;

public class RoomBean {
	
	public List<Map<String, Object>> getRoomList(String hotelid,Date sysDate,String roomtype) throws SQLException {
//		String sql = "select roomno,type,ocsta,people,bedno,feature,rate,sta from rmsta where hotelid=?";
		String sql = "select a.roomno,a.type,a.people,a.bedno,a.feature,a.sta,b.rate, "+
						"(SELECT b.roomTypeName FROM guestroom AS a,guestroom_i18n AS b  WHERE a.roomtypeCode=a.type AND a.guestRoomId=b.guestRoomId) AS typename "+
						"from rmsta as a, rsvtype as b "+
						"where a.hotelid=b.hotelid and a.type=b.type and a.hotelid=? and b.date=?";
		if(roomtype != null && !roomtype.isEmpty()){
			sql = sql + " and a.type='" + roomtype + "'"; 
		}
		List<Map<String,Object>> roomList = DAOBean.getResultMapList(sql, hotelid,formatData(sysDate));
		return roomList;
	}
	/**这个方法是给订单管理界面用的
	 * @param hotelid
	 * @param sysDate
	 * @param roomtype
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getMasterRoomList(SystemData systemData,String roomtype) throws SQLException {
		String sql = "SELECT  a.hotelid,d.hotelName,a.roomno,a.type,c.roomTypeName " +
				"FROM rmsta AS a RIGHT JOIN guestroom AS b ON a.type=b.roomtypeCode AND (ISNULL(b.delFlag) OR b.delFlag=0) " +
				"LEFT JOIN guestroom_i18n AS c ON b.guestRoomId=c.guestRoomId " +
				"LEFT JOIN hotel_i18n AS d ON d.hotelId=a.hotelid " + 
				"WHERE a.hotelid in (" + systemData.getHotelidSqlList() + ") ";
//				"WHERE a.hotelid=? ";
		if(roomtype != null && !roomtype.isEmpty()){
			sql = sql + " and a.type='" + roomtype + "'"; 
		}
		sql = sql + " ORDER BY a.hotelid,a.type,a.roomno";
//		List<Map<String,Object>> roomList = DAOBean.getResultMapList(sql, systemData.getHotelid());
		List<Map<String,Object>> roomList = DAOBean.getResultMapList(sql);
		return roomList;
	}
	
	public List<Map<String, Object>> getRoomDaily(String hotelid,String type, String arr, String dep) throws SQLException{
		//ssd
		String sql = "select date,(rate) as setrate,WEEKDAY(date) as wday from rsvtype where type=? and date >=? and date <?";// and hotelid=? ";
		List<Map<String,Object>> roomDailyList = DAOBean.getResultMapList(sql, type, arr, dep/*, hotelid*/);  //不要酒店了，因为房型代码是唯一的。
		return roomDailyList;
	}
	
	/**与上面的方法差别在于没有as setrate 而已
	 * @param hotelid
	 * @param type
	 * @param arr
	 * @param dep
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getRoomDailyByApi(String hotelid,String type, String arr, String dep) throws SQLException{
		String sql = "select date,rate,WEEKDAY(date) as wday from rsvtype where hotelid=? and type=? and date >=? and date <?";
		List<Map<String,Object>> roomDailyList = DAOBean.getResultMapList(sql, hotelid, type, arr, dep);
		return roomDailyList;
	}
	
	public void updateRoomSta(ReservationForm reservationForm, SystemData systemData) throws SQLException{
		String ocsta = "O";
		String sta = reservationForm.getSta();
		String hotelid = systemData.getHotelid();
		String roomno = reservationForm.getRoomNo();
		String sql = "update rmsta set ocsta=?,sta=? where hotelid=? and roomno=?";
		DAOBean.update(sql, ocsta, sta, hotelid, roomno);
	}
	
	private String formatData(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
}
