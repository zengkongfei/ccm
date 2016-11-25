/**
 * 
 */
package com.ccm.api.model.common;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny Liao
 * 
 */
public class Constant extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6698426448708054105L;

	private String id;

	private String name;

	private String value;

	private Integer flag;// 0-初始化状态,1-发布进行中,2-发布成功,3-发布失败,4-无绑定关系,5-根据酒店code查询不到酒店,99-flag值为1的时间大于当前时间30分钟.

	/**
	 * 
	 */
	public String getId() {
		return id;
	}

	public Boolean saveOrUpdate() {
		if (id != null && !id.trim().isEmpty()) {
			return false;// update
		} else {
			return true;// save
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Constant [id=" + id + ", name=" + name + ", value=" + value + ", flag=" + flag + "]";
	}

}
