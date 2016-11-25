package com.ccm.api.model.bdp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import com.ccm.api.util.CommonUtil;

public class AriPushErrorMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7477590776044644654L;
	private String id;
	private String hotelCode;
	private String hotelName;
	private Integer qtr1;
	private Integer qtr2;
	private Integer qtr3;
	private Integer qtr4;
	private String msgDate;
	
	private AriPushErrorMsg(){
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public Integer getQtr1() {
		return qtr1;
	}
	public void setQtr1(Integer qtr1) {
		this.qtr1 = qtr1;
	}
	public Integer getQtr2() {
		return qtr2;
	}
	public void setQtr2(Integer qtr2) {
		this.qtr2 = qtr2;
	}
	public Integer getQtr3() {
		return qtr3;
	}
	public void setQtr3(Integer qtr3) {
		this.qtr3 = qtr3;
	}
	public Integer getQtr4() {
		return qtr4;
	}
	public void setQtr4(Integer qtr4) {
		this.qtr4 = qtr4;
	}
	public static List<AriPushErrorMsg> buildMsgList(List<AdsPushErrorCount> countList,String lastDateStr){
		List<AriPushErrorMsg> msgList=new Vector<AriPushErrorMsg>();
		Map<String,Map<Integer,Integer>> msgMap=new HashMap<String,Map<Integer,Integer>>();
		Map<String,String> hotelMap=new HashMap<String,String>();
		if(CommonUtil.isNotEmpty(countList)){
			for(AdsPushErrorCount countData:countList){
				hotelMap.put(countData.getHotelCode(), countData.getHotelName());
				if(msgMap.get(countData.getHotelCode())==null){
					msgMap.put(countData.getHotelCode(),new HashMap<Integer,Integer>());
					}
				Map<Integer,Integer>dataMap=msgMap.get(countData.getHotelCode());
				dataMap.put(countData.getQtr(),countData.getNumberOfTimes());
			}
		}
		
		for(String hotelCode:msgMap.keySet()){
			Map<Integer,Integer> dataMap=msgMap.get(hotelCode);
			AriPushErrorMsg ariPushErrorMsg=new AriPushErrorMsg();
			ariPushErrorMsg.setId(UUID.randomUUID().toString().replace("-", ""));
			ariPushErrorMsg.setMsgDate(lastDateStr);
			ariPushErrorMsg.setHotelCode(hotelCode);
			ariPushErrorMsg.setHotelName(hotelMap.get(hotelCode));
			if(dataMap.get(1)==null){
				ariPushErrorMsg.setQtr1(1);
			}else{
				ariPushErrorMsg.setQtr1(dataMap.get(1));
			}
			if(dataMap.get(2)==null){
				ariPushErrorMsg.setQtr2(1);
			}else{
				ariPushErrorMsg.setQtr2(dataMap.get(2));
			}
			if(dataMap.get(3)==null){
				ariPushErrorMsg.setQtr3(1);
			}else{
				ariPushErrorMsg.setQtr3(dataMap.get(3));
			}
			if(dataMap.get(4)==null){
				ariPushErrorMsg.setQtr4(1);
			}else{
				ariPushErrorMsg.setQtr4(dataMap.get(4));
			}
			msgList.add(ariPushErrorMsg);
		}
		
		return msgList;
	}
}
