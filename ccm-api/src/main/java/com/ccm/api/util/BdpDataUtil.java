package com.ccm.api.util;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.bdp.BdpTb;
public class BdpDataUtil {
	public static  String URL_CREATE_TB="https://open.bdp.cn/api/tb/create";
	public static  String URL_INSERT_DATA="https://open.bdp.cn/api/data/insert";
	public static  String URL_UPDATE_DATA="https://open.bdp.cn/api/data/update";
	public static  String URL_GET_TB="https://open.bdp.cn/api/tb/list";
	public static  String URL_GET_DS="https://open.bdp.cn/api/ds/list";
	public static  String URL_COMMIT_DATA="https://open.bdp.cn/api/tb/commit";
	public static  String ACCESS_TOKEN="814484db2ec2811f3d01497e9de7c078";
	public static  String DS_ID="ds_e6cd49f3258f4e32a6c12b9ed4c3a52a";
	public static  String OXI_API_DISCONNECTED_MSG_ID="tb_36a7d0c6b8e94d0dafc996e3c681682e";
	public static  String ADS_PUSH_ERROR_MSG_ID="tb_c29264e6e4a74dea9072463f9ab7cfc3";
	public static  String CCM_OWS_RESERVATION_ID="tb_d7f5c641d87c42a082e1cb3397ae391c";
	public static  String URL_CLS_TB="https://open.bdp.cn/api/tb/clean";
	public static  String URL_ADD_FIELD="https://open.bdp.cn/api/field/add";
	public static  String URL_UPDATE_TABLE= "https://open.bdp.cn/api/tb/update";
	public static  String URL_BULK_DELETE_DATA="https://open.bdp.cn/api/data/bulkdelete";
	public static void main(String ...args){
		BdpTb.updateTable(BdpDataUtil.ADS_PUSH_ERROR_MSG_ID);
//		BdpTb tb=new BdpTb();
//		tb.setAccess_token("814484db2ec2811f3d01497e9de7c078");
//		tb.getUniq_key().add("id");
//		tb.setDs_id("ds_e6cd49f3258f4e32a6c12b9ed4c3a52a");
//		tb.setName("ljm_testuser");
		/*
		Map<String,String> field1Map =new HashMap<String,String>();
		field1Map.put("name", "id");
		field1Map.put("type", "string");
		field1Map.put("comment", "");
		
		Map<String,String> field2Map =new HashMap<String,String>();
		field2Map.put("name", "username");
		field2Map.put("type", "string");
		field2Map.put("comment", "");
		
		Map<String,String> field3Map =new HashMap<String,String>();
		field3Map.put("name", "age");
		field3Map.put("type", "number");
		field3Map.put("comment", "");
		*/
//		tb.getSchema().add(field1Map);
//		tb.getSchema().add(field2Map);
//		tb.getSchema().add(field3Map);
//		System.out.println(JSONObject.toJSONString(tb));
		//get TB info
//		ApacheHttpProxyClient.get(URL_GET_TB+"?access_token=814484db2ec2811f3d01497e9de7c078&ds_id=ds_e6cd49f3258f4e32a6c12b9ed4c3a52a",true);
		//get DS info
//		ApacheHttpProxyClient.get(URL_GET_DS+"?access_token=814484db2ec2811f3d01497e9de7c078",true);
		
		//create table
//		HttpProxyClient.postJson(tb.getInvokeURL(URL_CREATE_TB), JSONObject.toJSONString(tb),true);
	}
	
}
