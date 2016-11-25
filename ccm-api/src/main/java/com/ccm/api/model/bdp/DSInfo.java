package com.ccm.api.model.bdp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DSInfo implements Serializable{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -988918592268564755L;
	private String dsName="BDP";
	private String createTbURL="https://open.bdp.cn/api/tb/create";
	private String createDataURL="https://open.bdp.cn/api/data/insert";
	private String modifyDataURL="https://open.bdp.cn/api/data/update";
	private String getTbURL="https://open.bdp.cn/api/tb/list";
	private String getDsURL="https://open.bdp.cn/api/ds/list";
	private String commitDataURL="https://open.bdp.cn/api/tb/commit";
	private String accessToken="814484db2ec2811f3d01497e9de7c078";
	private String dsId="ds_e6cd49f3258f4e32a6c12b9ed4c3a52a";
	private String cleanTbURL="https://open.bdp.cn/api/tb/clean";
	private Map <String,String> tbIdMap=new HashMap <String,String>();
	public Map<String, String> getTbIdMap() {
		return tbIdMap;
	}
	public void setTbIdMap(Map<String, String> tbIdMap) {
		this.tbIdMap = tbIdMap;
	}
	public String getDsName() {
		return dsName;
	}
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}
	public String getCreateTbURL() {
		return createTbURL;
	}
	public void setCreateTbURL(String createTbURL) {
		this.createTbURL = createTbURL;
	}
	public String getCreateDataURL() {
		return createDataURL;
	}
	public void setCreateDataURL(String createDataURL) {
		this.createDataURL = createDataURL;
	}
	public String getGetTbURL() {
		return getTbURL;
	}
	public void setGetTbURL(String getTbURL) {
		this.getTbURL = getTbURL;
	}
	public String getGetDsURL() {
		return getDsURL;
	}
	public void setGetDsURL(String getDsURL) {
		this.getDsURL = getDsURL;
	}
	public String getCommitDataURL() {
		return commitDataURL;
	}
	public void setCommitDataURL(String commitDataURL) {
		this.commitDataURL = commitDataURL;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getDsId() {
		return dsId;
	}
	public void setDsId(String dsId) {
		this.dsId = dsId;
	}
	public String getCleanTbURL() {
		return cleanTbURL;
	}
	public void setCleanTbURL(String cleanTbURL) {
		this.cleanTbURL = cleanTbURL;
	}
	public String getModifyDataURL() {
		return modifyDataURL;
	}
	public void setModifyDataURL(String modifyDataURL) {
		this.modifyDataURL = modifyDataURL;
	}
}
