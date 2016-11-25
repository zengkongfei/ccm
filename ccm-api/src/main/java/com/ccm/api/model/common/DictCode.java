/**
 * 
 */
package com.ccm.api.model.common;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny Liao
 * 
 */
public class DictCode extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6698426448708054105L;

	private String codeId;

	private String dictId;// Directory.id

	private String codeLabel;// 比如，已发布
	private String codeNo;// 比如, 01

	// 用于查询结果
	private String hotelId;

	/** 房价类别 */
	public static final String DICT_RPT = "rpt";

	/**
	 * 
	 */
	public String getId() {
		return codeId;
	}

	public Boolean saveOrUpdate() {
		if (codeId != null && !codeId.trim().isEmpty()) {
			return false;// update
		} else {
			return true;// save
		}
	}

	/**
	 * @return the codeId
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * @param codeId
	 *            the codeId to set
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
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
	 * @return the codeLabel
	 */
	public String getCodeLabel() {
		return codeLabel;
	}

	/**
	 * @param codeLabel
	 *            the codeLabel to set
	 */
	public void setCodeLabel(String codeLabel) {
		this.codeLabel = codeLabel;
	}

	/**
	 * @return the codeNo
	 */
	public String getCodeNo() {
		return codeNo;
	}

	/**
	 * @param codeNo
	 *            the codeNo to set
	 */
	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	@Override
	public String toString() {
		return "DictCode [codeId=" + codeId + ", dictId=" + dictId + ", codeLabel=" + codeLabel + ", codeNo=" + codeNo + ", hotelId=" + hotelId + "]";
	}

}
