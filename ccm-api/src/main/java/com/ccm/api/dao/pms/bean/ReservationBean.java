package com.ccm.api.dao.pms.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccm.api.dao.pms.form.ReservationForm;
import com.ccm.api.dao.pms.utils.DAOBean;
import com.ccm.api.dao.pms.utils.DateUtils;
import com.ccm.api.dao.pms.utils.SystemData;

public class ReservationBean {

	private String SELECTFIELD = " accnt,name,hotelid,sex,mobile,arr,dep,ref,idcls,ident,gstno," + "payment,credit,deposit,charge,cby,changed,setrate,roomno,sta,channel,type,crsno,name2,ressta," + "(SELECT b.roomTypeName FROM guestroom AS a,guestroom_i18n AS b  WHERE a.roomtypeCode=type AND a.guestRoomId=b.guestRoomId) AS typename ";

	/**
	 * 修改预订的大量信息
	 * 
	 * @param reservationForm
	 * @param systemData
	 * @throws SQLException
	 */
	public void updateResv(ReservationForm reservationForm, SystemData systemData) throws SQLException {
		String masterId = reservationForm.getAccnt();
		List<Object> paramsList = getResvDataList(reservationForm, systemData, masterId, false);
		updateResevation(paramsList);
	}

	/**
	 * 新建一条预订
	 * 
	 * @param reservationForm
	 * @param systemData
	 * @return
	 * @throws SQLException
	 */
	public String insertResv(ReservationForm reservationForm, SystemData systemData) throws SQLException {
		String masterId = reservationForm.getAccnt();
		List<Object> paramsList = getResvDataList(reservationForm, systemData, masterId, true);

		insertReservation(paramsList);
		return masterId;
	}

	/**
	 * 生成预订的 每日房价详情
	 * 
	 * @param reservationForm
	 * @param systemData
	 * @param isNew
	 * @throws SQLException
	 */
	public void saveRoomDaily(ReservationForm reservationForm, SystemData systemData, boolean isNew) throws SQLException {
		if (reservationForm.getRateList().isEmpty()) {
			return;
		}
		String[] roomRateList = reservationForm.getRateList().split(",");
		Object[][] dailyArr = new Object[roomRateList.length][];

		for (int i = 0; i < roomRateList.length; i++) {
			String[] data = roomRateList[i].split(":");
			List<Object> list = new ArrayList<Object>();
			list.add(systemData.getHotelid());
			list.add(reservationForm.getAccnt());
			list.add(data[0]);
			list.add(reservationForm.getRoomPrice());
			list.add(Double.parseDouble(data[1].toString()));
			list.add(systemData.getUserName());
			list.add(new Date());

			dailyArr[i] = list.toArray();
		}

		if (!isNew) {
			deleteDaily(reservationForm, systemData);
		}
		insertDailyBatch(dailyArr);
	}

	/**
	 * 获取预订每日房价详情的列表
	 * 
	 * @param accnt
	 * @param hotelid
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getRoomRateList(String accnt, String hotelid) throws SQLException {
		String sql;
		sql = "select date,setrate,WEEKDAY(date) as wday from master_rate where accnt=? " +
		// "and hotelid=? " + //accnt是唯一的，不需要hotelid了
				"order by date ";
		List<Map<String, Object>> rateList = DAOBean.getResultMapList(sql, accnt);
		return rateList;
	}

	/**
	 * 根据房间号查找预订（可能需要废弃或者修改）
	 * 
	 * @param room
	 * @param hotelid
	 * @return
	 * @throws SQLException
	 */
	@Deprecated
	public Map<String, Object> getMasterMapByRoomNo(String room, String hotelid) throws SQLException {
		Map<String, Object> masterMap = new HashMap<String, Object>();
		String sql = "select " + SELECTFIELD + // accnt,name,sex,mobile,arr,dep,ref,idcls,ident,gstno,payment,credit,deposit,charge,cby,changed,setrate
				"from master where roomno=? and hotelid=?";
		masterMap = DAOBean.getResultMap(sql, room, hotelid);
		return masterMap;
	}

	/**
	 * 根据预订号获取预订信息
	 * 
	 * @param accnt
	 * @param hotelid
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getMasterMapByAccnt(String accnt, String hotelid) throws SQLException {
		Map<String, Object> masterMap = new HashMap<String, Object>();
		String sql = "select " + SELECTFIELD// accnt,name,sex,mobile,arr,dep,ref,idcls,ident,gstno,payment,credit,deposit,charge,cby,changed,setrate,roomno,sta
				+ "from master where accnt=?";// and
												// hotelid=?";//只要accnt就行了，这个是全局唯一的。
		masterMap = DAOBean.getResultMap(sql, accnt);
		return masterMap;
	}

	/**
	 * 根据预订号获取预订的某些字段
	 * 
	 * @param accnt
	 * @param hotelid
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getMasterMapFiledByAccnt(String fileds, String accnt, String hotelid) throws SQLException {
		Map<String, Object> masterMap = new HashMap<String, Object>();
		String sql = "select " + fileds + " from master where accnt=?";// and
																		// hotelid=?";
																		// 酒店不用了，accnt是唯一的。
		masterMap = DAOBean.getResultMap(sql, accnt/* , hotelid */);
		return masterMap;
	}

	/**
	 * 修改预订状态
	 * 
	 * @param accnt
	 * @param hotelid
	 * @param sta
	 * @throws SQLException
	 */
	public void updateResevationSta(String accnt, String hotelid, String sta) throws SQLException {
		String sql = "UPDATE master SET sta=? WHERE accnt=?";// AND hotelid=?
																// ";//酒店ID不用了，accnt是全服务器唯一的。
		DAOBean.update(sql, sta, /* hotelid, */accnt);
	}

	/**
	 * 更新预订的实际方法
	 * 
	 * @param paramsList
	 * @throws SQLException
	 */
	public void updateResevation(List<Object> paramsList) throws SQLException {
		String sql = "UPDATE master SET sta=?,osta=?,name=?,sex=?,idcls=?,ident=?,mobile=?," + "arr=?,dep=?,gstno=?,roomno=?,type=?,rmrate=?,setrate=?,master=?,charge=?,credit=?,payment=?,channel=?," + "ref=?,deposit=?,name2=? WHERE hotelid=? AND accnt=?";
		DAOBean.update(sql, paramsList.toArray());
	}

	/**
	 * 新建预订的实际方法
	 * 
	 * @param paramsList
	 * @throws SQLException
	 */
	public void insertReservation(List<Object> paramsList) throws SQLException {
		String sql = "INSERT INTO master(sta,osta,name,sex,idcls,ident,mobile," + "arr,dep,gstno,roomno,type,rmrate,setrate,master,charge,credit,payment," + "channel,ref,deposit,name2,hotelid,accnt,cby,changed,crsno,pcrec,ressta,haccnt) " + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DAOBean.insert(sql, paramsList.toArray());
	}

	/**
	 * 批量插入每日房价详情
	 * 
	 * @param dailyArr
	 * @throws SQLException
	 */
	private void insertDailyBatch(Object[][] dailyArr) throws SQLException {
		String sql = "INSERT INTO master_rate(hotelid,accnt,date,rmrate,setrate,cby,changed) " + "VALUES(?,?,?,?,?,?,?)";
		DAOBean.insertBatch(sql, dailyArr);
	}

	/**
	 * 删除每日房价详情
	 * 
	 * @param reservationForm
	 * @param systemData
	 * @throws SQLException
	 */
	public void deleteDaily(ReservationForm reservationForm, SystemData systemData) throws SQLException {
		String delSql = "DELETE FROM master_rate WHERE accnt=?";// and
																// hotelid=?";//酒店ID不用了。
		DAOBean.delete(delSql, reservationForm.getAccnt()/*
														 * ,systemData.getHotelid
														 * ()
														 */);
	}

	/**
	 * 按一个日期去取相关的预订
	 * 
	 * @param hotelid
	 * @param sysDate
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getMasterListByDate(String hotelids, String fromDate, String toDate, String insta, String roomType) throws SQLException {
		String sql = "";
		if (insta.isEmpty()) {
			sql = "hotelid in (" + hotelids + ") and arr<=? and  ?<=dep";
		} else {
			sql = "hotelid in (" + hotelids + ") and arr<=? and  ?<=dep and sta in (" + insta + ")";
		}
		// String today = DateUtils.format(new Date());
		// sql =
		// "hotelid=? and ((arr<=? and  ?<=dep and sta='I') or (arr>=? and sta='R'))";
		// hotelid='H1001' and ((arr<='2013-07-21' and '2013-06-21'<=dep and
		// sta='I') or (arr>='2013-06-21' and sta='R'))

		if (roomType != null && !roomType.isEmpty()) {
			sql = sql + " and type='" + roomType + "'";
		}

		List<Map<String, Object>> masterMap = new ArrayList<Map<String, Object>>();

		if (toDate == null || toDate.isEmpty()) { // 如果木有todate 那就两个都一样
			masterMap = getMasterList(sql, fromDate, fromDate/* ,today */);
		} else { // 如果有todate， 那就arr<=todate, dep>=fromdate
			masterMap = getMasterList(sql, toDate, fromDate/* ,today */);
		}

		return masterMap;
	}

	/**
	 * 按照传入的条件取预订列表
	 * 
	 * @param hotelid
	 * @param sysDate
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getMasterList(String condition, Object... params) throws SQLException {
		String sql = "select " + SELECTFIELD + "from master where " + condition + " order by arr";

		List<Map<String, Object>> masterMap = new ArrayList<Map<String, Object>>();
		masterMap = DAOBean.getResultMapList(sql, params);
		return masterMap;
	}

	/**
	 * 按房间号验证在一定日期范围内是否有交集的预订
	 * 
	 * @param roomNo
	 * @param hotelid
	 * @param from
	 * @param to
	 * @param accnt
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> validateHaveResv(String roomNo, String hotelid, String from, String to, String accnt) throws SQLException {
		Map<String, Object> map = null;
		Date fromdate = DateUtils.parse(from, "yyyy-MM-dd");
		Date todate = DateUtils.parse(to, "yyyy-MM-dd");
		String sql = "SELECT accnt,name,arr,dep from master " + "where roomno=? and hotelid=? and " + "((dep>? and arr<?) OR (dep=arr and dep=?) OR (?=? and arr=?)) " + "and sta not in('O','X')";
		if (accnt != null && !accnt.isEmpty()) {
			sql = sql + " and accnt<>?";
			map = DAOBean.getResultMap(sql, roomNo, hotelid, from, to, from, from, to, from, accnt);
		} else {
			map = DAOBean.getResultMap(sql, roomNo, hotelid, from, to, from, from, to, from);
		}
		return map;
	}

	/**
	 * 更新预订的某些字段 最后两个参数是, accnt
	 * 
	 * @param filed
	 * @param price
	 * @param accnt
	 * @param hotelid
	 * @throws SQLException
	 */
	public void updateResvByFiled(String filed, Object... params) throws SQLException {
		String sql = "update `master` set " + filed + " where accnt=?";// and
																		// hotelid=?";不需要酒店了。
		DAOBean.update(sql, params);
	}

	public List<Map<String, Object>> getSourceList(SystemData systemData) throws SQLException {
		String sql = "select code,source from bookchannel where (delFlag IS NULL OR delFlag =0) and userId=?";
		if (systemData.getCompanyid().equals("1")) {
			return DAOBean.getResultMapList(sql, systemData.getUserid());
		} else {
			return DAOBean.getResultMapList(sql, systemData.getCompanyid());
		}
	}

	/**
	 * 生成插入和修改预订时的paramsList
	 * 
	 * @param reservationForm
	 * @param systemData
	 * @param masterID
	 * @param isNew
	 * @return
	 */
	public List<Object> getResvDataList(ReservationForm reservationForm, SystemData systemData, String masterID, boolean isNew) {
		String hotelid = systemData.getHotelid();
		String masterId = masterID;

		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(reservationForm.getSta());
		paramsList.add(null); // osta
		paramsList.add(reservationForm.getName());
		paramsList.add(reservationForm.getSex());
		paramsList.add(reservationForm.getCardType());
		paramsList.add(reservationForm.getCardNum());
		paramsList.add(reservationForm.getPhone());
		paramsList.add(reservationForm.getArrDate());
		paramsList.add(reservationForm.getDepDate());
		paramsList.add(reservationForm.getPepSum());
		paramsList.add(reservationForm.getRoomNo());
		paramsList.add(reservationForm.getType());
		paramsList.add(reservationForm.getRoomPrice());
		paramsList.add(null); // setrate
		paramsList.add(masterId);
		paramsList.add(reservationForm.getCharge());
		paramsList.add(reservationForm.getAlready());
		paramsList.add(reservationForm.getPayType());
		paramsList.add(reservationForm.getChannel());
		paramsList.add(reservationForm.getRemark());
		paramsList.add(reservationForm.getDeposit());
		paramsList.add(reservationForm.getName2());
		
		paramsList.add(hotelid); // 顾及到修改
		paramsList.add(masterId);
		if (isNew) {
			paramsList.add(systemData.getUserName());
			paramsList.add(new Date());
			paramsList.add(reservationForm.getTbAccnt());
			paramsList.add(reservationForm.getPcrec());
			paramsList.add(reservationForm.getRessta());
			paramsList.add(reservationForm.getHaccnt());
		}

		return paramsList;
	}

	public List<Map<String, Object>> getCancelOption(SystemData systemData) throws SQLException {
		String sql = "SELECT refundId,reason FROM refund WHERE userId=?";
		if (systemData.getCompanyid().equals("1")) {
			return DAOBean.getResultMapList(sql, systemData.getUserid());
		} else {
			return DAOBean.getResultMapList(sql, systemData.getCompanyid());
		}
	}
}
