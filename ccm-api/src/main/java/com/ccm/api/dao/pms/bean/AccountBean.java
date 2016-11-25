package com.ccm.api.dao.pms.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccm.api.dao.pms.form.AccountForm;
import com.ccm.api.dao.pms.utils.DAOBean;
import com.ccm.api.dao.pms.utils.SystemData;

public class AccountBean {
	
	/**插入一条账目
	 * @param accnt
	 * @param hotelid
	 * @param paramsList
	 * @return
	 * @throws SQLException
	 */ 
	public int insertAccount(String accnt, String hotelid, List<Object> paramsList) throws SQLException{
		Object[] obj = getMaxNumber(accnt, hotelid);
		int lastnumb = 0;
		if(obj != null && obj.length != 0){
			if(obj[0] != null){
				lastnumb = Integer.parseInt(obj[0].toString());
			}
		}
		lastnumb++;
		paramsList.add(lastnumb);
		String sql = "INSERT INTO account(hotelid,accnt,bdate,date,pccode,charge,credit,roomno,ref,empno,ref1,number) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		return DAOBean.insert(sql, paramsList.toArray());
	}
	
	/**批量插入多条账目
	 * @param accnt
	 * @param hotelid
	 * @param params
	 * @return
	 * @throws SQLException
	 */ 
	public int[] insertAccountBatch(String accnt, String hotelid, Object[][] params) throws SQLException{
		if(params == null || params.length == 0){
			return null;
		}
		Object[] obj = getMaxNumber(accnt, hotelid);
		int lastnumb = 0;
		if(obj != null && obj.length != 0){
			if(obj[0] != null){
				lastnumb = Integer.parseInt(obj[0].toString());
			}
		}
		
		for(int i=0; i<params.length; i++){
			lastnumb++;
			params[i][10] = lastnumb;
		}
		String sql = "INSERT INTO account(hotelid,accnt,bdate,date,pccode,charge,credit,roomno,ref,empno,number) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		return DAOBean.insertBatch(sql, params);
	}

	/**获取账单当前的最大流水号
	 * @param accnt
	 * @param hotelid
	 * @return
	 * @throws SQLException
	 */ 
	public Object[] getMaxNumber(String accnt, String hotelid)throws SQLException {
		Object[] obj = DAOBean.getResult("select max(number) from account where accnt=? and hotelid=?", accnt, hotelid);
		return obj;
	}
	
	/**插入一条支付账目
	 * @param accnt
	 * @param pccode
	 * @param price
	 * @param roomno
	 * @param systemData
	 * @throws SQLException
	 */ 
	public void insertPayAuccnt(String accnt,  String payType, double price, String roomno, SystemData systemData) throws SQLException{
		String[] strArr = getPccodeByType(payType);
		String pccode = strArr[0];
		String ref = strArr[1];

		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(systemData.getHotelid());
		paramsList.add(accnt);
		paramsList.add(null);//bdate
		paramsList.add(new Date());
		paramsList.add(pccode);
		if(pccode.equals("1000")){ //如果是房费，入到charge ，押金和支付入到credit
			paramsList.add(price);
			paramsList.add(0.00d);
		}else{
			paramsList.add(0.00d);
			paramsList.add(price);
		}
		paramsList.add(roomno);//roomno
		paramsList.add(ref);//ref
		paramsList.add(systemData.getUserName());//empno
		paramsList.add("");//超级支付的单号
		
		insertAccount(accnt, systemData.getHotelid(), paramsList);
	}
	
	public void insertPayAuccntToAliPay(AccountForm accountForm,String roomno, SystemData systemData) throws SQLException{
		String[] strArr = getPccodeByType(accountForm.getPayType());
		String pccode = strArr[0];
		String ref = strArr[1];

		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(systemData.getHotelid());
		paramsList.add(accountForm.getAccnt());
		paramsList.add(null);//bdate
		paramsList.add(new Date());
		paramsList.add(pccode);
		if(pccode.equals("1000")){ //如果是房费，入到charge ，押金和支付入到credit
			paramsList.add(accountForm.getPrice());
			paramsList.add(0.00d);
		}else{
			paramsList.add(0.00d);
			paramsList.add(accountForm.getPrice());
		}
		paramsList.add(roomno);//roomno
		paramsList.add(ref);//ref
		paramsList.add(systemData.getUserName());//empno
		paramsList.add(accountForm.getAliPayNo());//超级支付的单号
		insertAccount(accountForm.getAccnt(), systemData.getHotelid(), paramsList);
	}
	
	/**将每日房价入到账目库
	 * @param accnt
	 * @param roomNo
	 * @param systemData
	 * @return
	 * @throws SQLException
	 */ 
	public Double dailyToAccount(String accnt, String roomNo, SystemData systemData) throws SQLException{
		ReservationBean reservationBean = new ReservationBean();
		List<Map<String, Object>> rateList = reservationBean.getRoomRateList(accnt, systemData.getHotelid());
		Double sum = new Double("0.00");

		Object[][] params = new Object[rateList.size()][11];
		for(int i=0; i<rateList.size(); i++){
			Map<String, Object> map = rateList.get(i);
			params[i][0] = systemData.getHotelid();
			params[i][1] = accnt;
			params[i][2] = map.get("date");
			params[i][3] = new Date();
			params[i][4] = "1000";
			params[i][5] = map.get("setrate");
			params[i][6] = 0.00d;
			params[i][7] = roomNo;
			params[i][8] = "房费";
			params[i][9] = systemData.getUserName();
			sum = sum + Double.parseDouble(map.get("setrate").toString());
		}
		insertAccountBatch(accnt, systemData.getHotelid(), params);
		return sum;
	}
	
	public List<Map<String, Object>> getAccountDetaiById(String accnt,SystemData systemData) throws SQLException{
		String sql = "select accnt,date,pccode,charge,credit,empno,ref from account where accnt=? and hotelid=? order by pccode";
		return DAOBean.getResultMapList(sql, accnt, systemData.getHotelid());
	}
	
	public List<Map<String, Object>> getAccountByCrsno(String hotelid, String crsno) throws SQLException{
		String sql = "select accnt,name,roomno,charge,credit,deposit,sta from master where hotelid=? and crsno=? ";
		return DAOBean.getResultMapList(sql, hotelid, crsno);
	}
	
	private String[] getPccodeByType(String type){
		//CA现金、CR刷卡、AR合作伙伴收款、CL挂账收款。	
		if(type.equals("CA")){
			return new String[]{"9000","现金"};
		}else if(type.equals("DP")){
			return new String[]{"9001","押金"};
		}else if(type.equals("CR")){
			return new String[]{"9002","信用卡"};
		}else if(type.equals("FF")){
			return new String[]{"1000","房费"};
		}else{
			return new String[]{"9000","现金"};
		}
	}
}
