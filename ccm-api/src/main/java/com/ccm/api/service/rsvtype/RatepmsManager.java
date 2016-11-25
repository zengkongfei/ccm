package com.ccm.api.service.rsvtype;

import java.util.List;

import com.ccm.api.model.rsvtype.Ratepms;
import com.ccm.api.service.base.GenericManager;


public interface RatepmsManager extends GenericManager<Ratepms, String> {
	
	/**
	 * 保存Ratepms
	 */
	public void addRatepms(Ratepms ratepms);
	
	/**
	 * 修改Ratepms状态
	 */
	public void updateRatepmsStatus(String ratepmsId);
	
	/**
	 * 查询所有状态为未处理的记录
	 */
	public List<Ratepms> getRatepms();
	
	/**
	 * PMS过来的数据解析到房价日历表中
	 */
	public void parseRatepms(Ratepms ratepms);
}
