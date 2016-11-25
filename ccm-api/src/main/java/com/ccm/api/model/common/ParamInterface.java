/**
 * 
 */
package com.ccm.api.model.common;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class ParamInterface extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3626534887743881534L;

	private String paramInterfaceId;

	private String urlId;

	private String fieldName;

	private String fieldValue;

	private String fieldDesc;

	private String foreignTable;

	public String getParamInterfaceId() {
		return paramInterfaceId;
	}

	public void setParamInterfaceId(String paramInterfaceId) {
		this.paramInterfaceId = paramInterfaceId;
	}

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getForeignTable() {
		return foreignTable;
	}

	public void setForeignTable(String foreignTable) {
		this.foreignTable = foreignTable;
	}

	@Override
	public String toString() {
		return "ParamInterface [paramInterfaceId=" + paramInterfaceId + ", urlId=" + urlId + ", fieldName=" + fieldName + ", fieldValue=" + fieldValue + ", fieldDesc=" + fieldDesc + ", foreignTable=" + foreignTable + "]";
	}

}
