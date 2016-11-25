package com.ccm.api.model.wbe;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * 
 * @author chay.huang
 *
 */
public class WbeGuestVO extends BaseObject  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2126323339633740905L;
	private String name1;//姓 （拼音）
	private String name2;//名(拼音)
	private String name3;//中文
	private String tel;//联系电话
	private Date arr;//预计到店时间
	private String need;//特殊需求
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getArr() {
		return arr;
	}
	public void setArr(Date arr) {
		this.arr = arr;
	}
	public String getNeed() {
		return need;
	}
	public void setNeed(String need) {
		this.need = need;
	}
	@Override
	public String toString() {
		return "WbeGuestVo [name1=" + name1 + ", name2=" + name2 + ", name3="
				+ name3 + ", tel=" + tel + ", arr=" + arr + ", need=" + need
				+ "]";
	}
	
}
