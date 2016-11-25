/**
 * 
 */
package com.ccm.api.model.base;

import java.io.Serializable;

/**
 * @author Jenny Liao
 *
 */
public class SearchModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2169539668297860191L;

	private Integer pageSize;//每页大小 
	private Integer pageNum = 0;//当前页,从0开始
	private Integer totalCount = 0;//总共多少记录
	
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the pageNum
	 */
	public Integer getPageNum() {
		return pageNum;
	}
	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
}
