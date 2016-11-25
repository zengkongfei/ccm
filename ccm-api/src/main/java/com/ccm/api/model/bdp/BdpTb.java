package com.ccm.api.model.bdp;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.util.ApacheHttpProxyClient;
import com.ccm.api.util.BdpDataUtil;

public class BdpTb implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4082735782734421296L;
	private List<String >uniq_key=new Vector<String>();
	private String ds_id;//DS_ID
	private String tb_id;//TB_ID
	private String name;//TABLE_NAME
	private List<Map<String,String>> schema=new Vector<Map<String,String>>();
	private String access_token;//token
	private BdpTb(){
		super();
	}
	public String getCreateInvokeURL(String url) {
		StringBuffer sb=new StringBuffer(url);
		sb.append("?");
		sb.append("access_token="+access_token);
		return sb.toString();
	}
	public String getTb_id() {
		return tb_id;
	}
	public void setTb_id(String tb_id) {
		this.tb_id = tb_id;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public List<String> getUniq_key() {
		return uniq_key;
	}
	public void setUniq_key(List<String> uniq_key) {
		this.uniq_key = uniq_key;
	}
	public String getDs_id() {
		return ds_id;
	}
	public void setDs_id(String ds_id) {
		this.ds_id = ds_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Map<String, String>> getSchema() {
		return schema;
	}
	public void setSchema(List<Map<String, String>> schema) {
		this.schema = schema;
	}
	public static BdpTb buildBdpTable(String access_token,String dataSourceId,Class c){
		BdpTb tb=new BdpTb();
		tb.setAccess_token(access_token);
		tb.setDs_id(dataSourceId);
		tb.setName(c.getSimpleName());
		tb.getUniq_key().add("id");
		for(Field field :c.getDeclaredFields()){
			if(field.getName().equalsIgnoreCase("serialVersionUID")){
				continue;
			}
			Map<String,String> fieldMap=new HashMap<String,String>();
			fieldMap.put("name",field.getName());
			if(Number.class.isAssignableFrom(field.getType())){
				fieldMap.put("type","number");
			}else
				fieldMap.put("type", field.getType().getSimpleName().toLowerCase());
			fieldMap.put("comment", "");
			tb.getSchema().add(fieldMap);
		}
		return tb;
	}
	
	public static StringBuffer buildNewField(String access_token,String tbId,String fieldName,String type){
		StringBuffer sb =new StringBuffer();
		sb.append("?access_token="+access_token);
		sb.append("&tb_id="+tbId);
		sb.append("&name="+fieldName);
		sb.append("&type="+type);
		sb.append("&uniq_index=0");
		return sb;
	}
	
	public static void createTable(Class entity){
		BdpTb tb=buildBdpTable(BdpDataUtil.ACCESS_TOKEN,BdpDataUtil.DS_ID,entity);
		ApacheHttpProxyClient.postJson(tb.getCreateInvokeURL(BdpDataUtil.URL_CREATE_TB), JSONObject.toJSONString(tb),true);
	}
	
	public static void addField(String tbId,String fieldName,String type){
		StringBuffer sb=buildNewField(BdpDataUtil.ACCESS_TOKEN,tbId,fieldName,type);
		ApacheHttpProxyClient.get(BdpDataUtil.URL_ADD_FIELD+sb.toString(),true);
	}
	
	public static void cleanData(String tb_id){
		ApacheHttpProxyClient.get(BdpDataUtil.URL_CLS_TB+"?"+"access_token="+BdpDataUtil.ACCESS_TOKEN+"&tb_id="+tb_id, true);
		ApacheHttpProxyClient.get(BdpDataUtil.URL_COMMIT_DATA+"?"+"access_token="+BdpDataUtil.ACCESS_TOKEN+"&tb_id="+tb_id,true);
	}
	
	public static void getTableInfo(){
		ApacheHttpProxyClient.get(BdpDataUtil.URL_GET_DS+"?access_token=814484db2ec2811f3d01497e9de7c078",true);
	}
	
	public static void updateTable(String tbId){
		List<String>tbIds=new ArrayList<String>();
		tbIds.add(tbId);
		ApacheHttpProxyClient.get(BdpDataUtil.URL_UPDATE_TABLE+buildUpdateTable(BdpDataUtil.ACCESS_TOKEN,tbIds).toString(),true);
	}
	
	public static void removeDataByConidtion(String tbId,String whereCriteria){
		ApacheHttpProxyClient.get(BdpDataUtil.URL_BULK_DELETE_DATA+bulkDelete(BdpDataUtil.ACCESS_TOKEN,tbId,whereCriteria).toString(),true);
		ApacheHttpProxyClient.get(BdpDataUtil.URL_COMMIT_DATA+"?"+"access_token="+BdpDataUtil.ACCESS_TOKEN+"&tb_id="+tbId,true);
	}
	
	public static StringBuffer buildUpdateTable(String access_token,List<String>tbIds){
		StringBuffer sb =new StringBuffer();
		sb.append("?access_token="+access_token);
		sb.append("&tb_ids=");
		String tbIdsParam=JSONObject.toJSONString(tbIds);
		tbIdsParam=URLEncoder.encode(tbIdsParam);
		sb.append(tbIdsParam);
		return sb;
	}
	
	public static StringBuffer bulkDelete(String access_token,String tbId,String whereCriteria){
		StringBuffer sb =new StringBuffer();
		sb.append("?access_token="+access_token);
		sb.append("&tb_id=");
		sb.append(tbId);
		sb.append("&where=");
		String param=URLEncoder.encode(whereCriteria);
		sb.append(param);
		return sb;
	}
}
