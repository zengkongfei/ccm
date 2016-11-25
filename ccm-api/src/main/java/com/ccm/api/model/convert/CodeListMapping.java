/**
 * 
 */
package com.ccm.api.model.convert;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class CodeListMapping extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1138766395978838248L;

	private String codeListMappingId;

	private String channelId;

	private String hotelId;

	private String codeName;

	private String codeId;

	private String external2ccm;// 1-Y

	private String ccm2external;// 1-Y

	// 以下用于查询返回
	private String dictId;// 用于查询传参
	private String codeNo;// 用于查询映射列表
	private String dataTable;
	private String lableField;
	private String querySql;
	private String channelCode;

	public String getCodeListMappingId() {
		return codeListMappingId;
	}

	public void setCodeListMappingId(String codeListMappingId) {
		this.codeListMappingId = codeListMappingId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getExternal2ccm() {
		return external2ccm;
	}

	public void setExternal2ccm(String external2ccm) {
		this.external2ccm = external2ccm;
	}

	public String getCcm2external() {
		return ccm2external;
	}

	public void setCcm2external(String ccm2external) {
		this.ccm2external = ccm2external;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getDataTable() {
		return dataTable;
	}

	public void setDataTable(String dataTable) {
		this.dataTable = dataTable;
	}

	public String getLableField() {
		return lableField;
	}

	public void setLableField(String lableField) {
		this.lableField = lableField;
	}

	public String getQuerySql() {
		return querySql;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	@Override
	public String toString() {
		return "CodeListMapping [codeListMappingId=" + codeListMappingId + ", channelId=" + channelId + ", hotelId=" + hotelId + ", codeName=" + codeName + ", codeId=" + codeId + ", external2ccm=" + external2ccm + ", ccm2external=" + ccm2external + ", dictId=" + dictId + ", codeNo=" + codeNo + ", dataTable=" + dataTable + ", lableField=" + lableField + ", querySql=" + querySql + ", channelCode=" + channelCode + "]";
	}

}
