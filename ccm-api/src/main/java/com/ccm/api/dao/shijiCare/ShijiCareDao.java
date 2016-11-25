package com.ccm.api.dao.shijiCare;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.model.shijicare.vo.ShijicareCriteria;
import com.ccm.api.model.shijicare.vo.ShijicareVO;

public interface ShijiCareDao extends GenericDao<ShijiCare, String> {
	public Integer getCount(ShijicareCriteria sc);
	public List<ShijicareVO> getList(ShijicareCriteria sc);
	public List<ShijicareVO> getExcelList(ShijicareCriteria sc);
	
	/**
	 * 获取shiji care 一个月前，切未关闭的
	 * @param shijiCare
	 * @return
	 */
	public List<ShijiCare> getShijiCareList(ShijiCare shijiCare);
	
	/**
	 * 关闭case
	 * @param shijiCare
	 */
	public void closeShijicareBycareId(ShijiCare shijiCare);
	
	/**
	 * 获取 case 
	 * @param shijiCare
	 * @return
	 */
	public List<ShijiCare> shijiCareList(ShijiCare shijiCare);
	
	public void updateShjicareSendSms(ShijiCare shijiCare);
}
