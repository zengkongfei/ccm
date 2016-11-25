package com.ccm.api.model.log;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.ccm.api.model.base.BaseObject;
/**
 * 删除操作的日志记录
 * @author Water
 *
 */
public class DeleteLog extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2925753542069557978L;
	@Id
	private String id;
	private String de_table;  //所删除的表名
	private int de_number;    //所影响的记录条数
	private Date de_time;	 //删除操作的时间
	
	private String hotelCode; //所操作的hotelCode
	
	//getters and setters for private fileds
	
	
	public String getDe_table() {
		return de_table;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDe_table(String de_table) {
		this.de_table = de_table;
	}
	public int getDe_number() {
		return de_number;
	}
	public void setDe_number(int de_number) {
		this.de_number = de_number;
	}
	public Date getDe_time() {
		return de_time;
	}
	public void setDe_time(Date de_time) {
		this.de_time = de_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//构造器
	public DeleteLog() {
	}
	public DeleteLog(String de_table, int de_number, Date de_time,
			String hotelCode) {
		this.de_table = de_table;
		this.de_number = de_number;
		this.de_time = de_time;
		this.hotelCode = hotelCode;
	} 
	public DeleteLog(String de_table, int de_number, Date de_time) {
		this.de_table = de_table;
		this.de_number = de_number;
		this.de_time = de_time;
	} 
}
