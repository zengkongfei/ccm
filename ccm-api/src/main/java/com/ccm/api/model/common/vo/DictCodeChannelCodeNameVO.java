/**
 * 
 */
package com.ccm.api.model.common.vo;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny Liao
 * 
 */
public class DictCodeChannelCodeNameVO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6698426448708054105L;

	private String codeLabel;// 显示的中文名称

	private String codeNo;// 保存到数据库的值

	private String codeName;// 渠道定义的字段名称

	public String getCodeLabel() {
		return codeLabel;
	}

	public void setCodeLabel(String codeLabel) {
		this.codeLabel = codeLabel;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

}
