package com.ccm.api.model.bdp;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.util.ApacheHttpProxyClient;
import com.ccm.api.util.BdpDataUtil;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

public class BdpRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -12689300581376289L;
	/**
	 * 
	 */
	private String access_token;
	private String tb_id;
	private List<String>fields=new Vector<String>();
	private List<List<String>>values=new Vector<List<String>>();
	
	private BdpRecord(){
		super();
	}
	
	public String getCommitURL(String url){
		StringBuffer sb=new StringBuffer(url);
		sb.append("?");
		sb.append("access_token="+access_token);
		sb.append("&tb_id="+tb_id);
		return sb.toString();
	}
	public String getOperationURL(String url) {
		StringBuffer sb=new StringBuffer(url);
		sb.append("?");
		sb.append("access_token="+access_token);
		sb.append("&tb_id="+tb_id);
		sb.append("&fields=");
		
		String fieldsParam=JSONObject.toJSONString(fields);
		fieldsParam=URLEncoder.encode(fieldsParam);
		sb.append(fieldsParam);
		return sb.toString();
	}
	
	public List<List<String>> getValues() {
		return values;
	}
	public void setValues(List<List<String>> values) {
		this.values = values;
	}
	public String getValueJson(){
		return JSONObject.toJSONString(values);
	}
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getTb_id() {
		return tb_id;
	}
	public void setTb_id(String tb_id) {
		this.tb_id = tb_id;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	
	
	public static List<BdpRecord> buildRecords(List<?> msgList,String access_token,String tb_id){
		
		List<BdpRecord>bdpRecordList=new Vector<BdpRecord>();
		int num=100;
		List<List> splitList = CommonUtil.splitList(msgList, num);
		for(List partMsgList:splitList){
			BdpRecord newRecord=new BdpRecord();
			newRecord.setAccess_token(access_token);
			newRecord.setTb_id(tb_id);
			for(int indx=0;indx<partMsgList.size();indx++){
				Object ob=partMsgList.get(indx);
				List<String> valueList= new Vector<String>();
				for(Field field :ob.getClass().getDeclaredFields()){
					if(field.getName().equalsIgnoreCase("serialVersionUID")){
						continue;
					}
					field.setAccessible(true);
					try {
					if(indx==0){
						newRecord.getFields().add(field.getName());
					}
					if(Date.class.isAssignableFrom(field.getType())){
						if(field.get(ob)!=null){
							Date d=Date.class.cast(field.get(ob));
							valueList.add(DateUtil.convertDateTimeToString(d));
						}else
							valueList.add(field.get(ob)==null?"":field.get(ob).toString());
					}else if(field.getName().equalsIgnoreCase("msgDate")){
						valueList.add(field.get(ob)==null?"":field.get(ob).toString()+" 01:00:00");
					}
					else
						valueList.add(field.get(ob)==null?"":field.get(ob).toString());
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				newRecord.getValues().add(valueList);
			}
			bdpRecordList.add(newRecord);
		}
		return bdpRecordList;
	}
	
	
	public static void main(String ...args){
		List<Object>msgList=new Vector<Object>();
		DateUtil.getDate(DateUtil.currentDate());
		OxiApiDisconnectedMsg disconnectedMsg1 =new OxiApiDisconnectedMsg();
		disconnectedMsg1.setId("1");
		disconnectedMsg1.setHotelCode("TESTCCM");
		disconnectedMsg1.setMsgDate(DateUtil.getDate(DateUtil.currentDate()));
		disconnectedMsg1.setDisconnectedQuantum(new Long(1000000));
		disconnectedMsg1.setNumberOfTimes(12);
		OxiApiDisconnectedMsg disconnectedMsg2 =new OxiApiDisconnectedMsg();
		disconnectedMsg2.setId("2");
		disconnectedMsg2.setHotelCode("SIHJI");
		disconnectedMsg2.setMsgDate(DateUtil.getDate(DateUtil.currentDate()));
		disconnectedMsg2.setDisconnectedQuantum(new Long(500000));
		disconnectedMsg2.setNumberOfTimes(5);
		msgList.add(disconnectedMsg1);
		msgList.add(disconnectedMsg2);
		
		List<BdpRecord> records=BdpRecord.buildRecords(msgList,BdpDataUtil.ACCESS_TOKEN,BdpDataUtil.OXI_API_DISCONNECTED_MSG_ID);
		for(BdpRecord record:records){
			ApacheHttpProxyClient.postJson(record.getOperationURL(BdpDataUtil.URL_INSERT_DATA), JSONObject.toJSONString(record.getValues()),true).toJSONString();
			ApacheHttpProxyClient.get(record.getCommitURL(BdpDataUtil.URL_COMMIT_DATA),true);
		}
//		BdpRecords records=new BdpRecords();
//		records.setAccess_token("814484db2ec2811f3d01497e9de7c078");
//		records.setTb_id("tb_0e8724a9bc8449beaf5273a7a46d44eb");
//		records.getFields().add("id");
//		records.getFields().add("username");
//		records.getFields().add("age");
//		List<String> v1=new Vector<String>();
//		v1.add("1");
//		v1.add("luanjm");
//		v1.add("30");
//		List<String> v2=new Vector<String>();
//		v2.add("2");
//		v2.add("kaikai");
//		v2.add("31");
//		records.getValues().add(v1);
//		records.getValues().add(v2);
//		ApacheHttpProxyClient.postJson(records.getInsertURL(BdpDataUtil.URL_INSERT_DATA), JSONObject.toJSONString(records.getValues()),true).toJSONString();
//		ApacheHttpProxyClient.get(records.getCommitURL(BdpDataUtil.URL_COMMIT_DATA),true);
	}
}
