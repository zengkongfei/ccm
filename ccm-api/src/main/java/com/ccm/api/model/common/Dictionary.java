/**
 * 
 */
package com.ccm.api.model.common;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny Liao
 * 
 */
public class Dictionary extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7556291517670146453L;

	private String dictId;

	private String dictName;
	private String dictType;// 01-默认值，02-ccm&pms映射字典
	private String remark;

	// TODO: to decide if continue to use it
	private String dataTable;
	private String lableFeild;
	private String valueField;
	private String querySql;

	/**
	 * 
	 */
	public String getId() {
		return dictId;
	}

	public Boolean saveOrUpdate() {
		if (dictId != null && !dictId.trim().isEmpty()) {
			return false;// update
		} else {
			return true;// save
		}
	}

	/**
	 * @return the dictId
	 */
	public String getDictId() {
		return dictId;
	}

	/**
	 * @param dictId
	 *            the dictId to set
	 */
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	/**
	 * @return the dictName
	 */
	public String getDictName() {
		return dictName;
	}

	/**
	 * @param dictName
	 *            the dictName to set
	 */
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	/**
	 * @return the dictType
	 */
	public String getDictType() {
		return dictType;
	}

	/**
	 * @param dictType
	 *            the dictType to set
	 */
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the dataTable
	 */
	public String getDataTable() {
		return dataTable;
	}

	/**
	 * @param dataTable
	 *            the dataTable to set
	 */
	public void setDataTable(String dataTable) {
		this.dataTable = dataTable;
	}

	public String getLableFeild() {
		return lableFeild;
	}

	public void setLableFeild(String lableFeild) {
		this.lableFeild = lableFeild;
	}

	/**
	 * @return the valueField
	 */
	public String getValueField() {
		return valueField;
	}

	/**
	 * @param valueField
	 *            the valueField to set
	 */
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	/**
	 * @return the querySql
	 */
	public String getQuerySql() {
		return querySql;
	}

	/**
	 * @param querySql
	 *            the querySql to set
	 */
	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

}
