package com.ccm.api.service.system;

import java.util.List;

import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.service.base.GenericManager;

public interface ShijiCareCaseManage extends GenericManager<ShijiCare, String>{
	/**
	 * 创建case
	 * @param sc
	 * @return
	 */
	ShijiCare newCase(ShijiCare sc);
	/**
	 * 关闭case
	 * @param shijicareId
	 * @param careId
	 */
	public Boolean closeCase(String shijicareId,String careId);
	
	/**
	 * 获取 未close 的case 
	 * @param shijiCare
	 * @return
	 */
	public List<ShijiCare> shijiCareList(ShijiCare shijiCare);
}
