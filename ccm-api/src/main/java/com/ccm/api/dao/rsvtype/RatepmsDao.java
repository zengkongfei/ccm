package com.ccm.api.dao.rsvtype;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.rsvtype.Ratepms;

public interface RatepmsDao extends GenericDao<Ratepms, String> {

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
}
