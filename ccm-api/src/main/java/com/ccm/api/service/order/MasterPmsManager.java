package com.ccm.api.service.order;

import java.util.List;

import com.ccm.api.model.fax.FaxSend;
import com.ccm.api.model.order.Master;

public interface MasterPmsManager {
	/**
	 * 批处理需要提醒的订单
	 * @param mpList
	 */
	void batchMasterPms(List<Master> masterList);
	
	/**
	 * 删除上一次需要提醒但是这一次轮询已经拥有pmsId的订单，master_pms表数据
	 */
	void deleteMasterPms();
	/**
	 * 提醒酒店
	 */
	void remindHotel();

	/**
	 * fax 补偿
	 */
	void remindHotel2();

	/**
	 * 重发
	 * @param list
	 */
	void reissueFax(List<FaxSend> list);

}
