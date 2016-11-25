package com.ccm.api.service.fax;

import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FaxManagerImpl implements FaxManager {
	private final Log log = LogFactory.getLog(FaxManagerImpl.class);
	private String fax_username;
	private String fax_password;
	private String fax_url;
	private String fax_format;
	private String fax_deskey;

	public String getFax_username() {
		return fax_username;
	}

	public void setFax_username(String fax_username) {
		this.fax_username = fax_username;
	}

	public String getFax_password() {
		return fax_password;
	}

	public void setFax_password(String fax_password) {
		this.fax_password = fax_password;
	}

	public String getFax_url() {
		return fax_url;
	}

	public void setFax_url(String fax_url) {
		this.fax_url = fax_url;
	}

	public String getFax_format() {
		return fax_format;
	}

	public void setFax_format(String fax_format) {
		this.fax_format = fax_format;
	}

	public String getFax_deskey() {
		return fax_deskey;
	}

	public void setFax_deskey(String fax_deskey) {
		this.fax_deskey = fax_deskey;
	}

	@Override
	public String sendFax(String faxNumber,List<String> filelist,String msgid) {
//		String posturl = "http://api.gfax.cn:8088"+"/sendfax/" + "json";  
//		FaxUtil allcom = new FaxUtil("chinaonline@gfax.cn", "chinaOnline2016", FaxUtil.MD5,"f6439bd3"); 
	    String posturl = fax_url+"/sendfax/" + fax_format;  
	    FaxUtil allcom = new FaxUtil(fax_username, fax_password, FaxUtil.MD5,fax_deskey); 
	  
	    Map<String, String> datamap = new Hashtable<String, String>();  
	    datamap.put("subject", faxNumber);
	    datamap.put("precision", "photo_low");  
	    
	    Locale.setDefault(new Locale("zh", "CN"));
	    datamap.put("msgid", msgid);  
	  
	    String result = allcom.sendFax(posturl, datamap, filelist); 
		
		return result;
	}

	@Override
	public String queryMoney() {
//		FaxUtil allcom = new FaxUtil("chinaonline@gfax.cn", "chinaOnline2016", FaxUtil.MD5,"f6439bd3");
//		String url = "http://api.gfax.cn:8088"+"/querymoney/" + "json";
		FaxUtil allcom = new FaxUtil(fax_username, fax_password, FaxUtil.MD5,fax_deskey);
		String url = fax_url+"/querymoney/" + fax_format;
		String result = allcom.getHttp(url);
		return result;
	}

	@Override
	public String queryTask(String msgid) {
		FaxUtil allcom = new FaxUtil(fax_username, fax_password, FaxUtil.MD5,fax_deskey);
		String url = fax_url+"/querytask/" + msgid + "/"+ fax_format;
//		FaxUtil allcom = new FaxUtil("chinaonline@gfax.cn", "chinaOnline2016", FaxUtil.MD5,"f6439bd3");
//		String url = "http://api.gfax.cn:8088"+"/querytask/" + msgid + "/"+ "json";
		String result = allcom.getHttp(url);
		return result;
	}

	@Override
	public String queryLog() {
		FaxUtil allcom = new FaxUtil(fax_username, fax_password, FaxUtil.MD5,fax_deskey); 
	    String url = fax_url+"/querymoneylog/" + fax_format;  
	    String result = allcom.getHttp(url);  
		return result;
	}

}
