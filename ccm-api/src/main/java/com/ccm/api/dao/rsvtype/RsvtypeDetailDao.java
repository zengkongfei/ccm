package com.ccm.api.dao.rsvtype;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.rsvtype.RsvtypeDetail;

public interface RsvtypeDetailDao extends GenericDao<RsvtypeDetail, String> {
	
	/**
	 * 保存RsvtypeDetail
	 */
	public void addRsvtypeDetail(RsvtypeDetail rsd);
	
	/**
	 * 修改RsvtypeDetail
	 */
	public void updateRsvtypeDetail(RsvtypeDetail rsd);
	
	/**
	 * 查询RsvtypeDetail
	 */
	public RsvtypeDetail getRsvtypeDetail(RsvtypeDetail rsd);
}
