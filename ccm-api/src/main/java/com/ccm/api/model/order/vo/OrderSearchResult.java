/**
 * 
 */
package com.ccm.api.model.order.vo;

import java.util.ArrayList;
import java.util.List;

import com.ccm.api.model.base.criteria.SearchResult;
import com.ccm.api.model.order.Master;

/**
 * order 查询返回结果类
 */
public class OrderSearchResult extends SearchResult<Master> {
	private static final long serialVersionUID = -1977615185344318930L;

	private List<MasterVO> masterVOList = new ArrayList<MasterVO>();

	/**
	 * @return the masterVOList
	 */
	public List<MasterVO> getMasterVOList() {
		return masterVOList;
	}

	/**
	 * @param masterVOList
	 *            the masterVOList to set
	 */
	public void setMasterVOList(List<MasterVO> masterVOList) {
		this.masterVOList = masterVOList;
	}

}
