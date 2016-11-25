package com.ccm.api.service.fax;

import java.util.List;

public interface FaxManager {
	/**
	 * 发送传真
	 * @return
	 */
	public String sendFax(String faxNumber,List<String> filelist,String msgid);
	/**
	 * 查询余额
	 * @return
	 */
	public String queryMoney();
	
	/**
	 * 查询传真任务  
	 * @param msgid
	 * @return
	 */
	public String queryTask(String msgid);
	
	/**
	 * 查询资金日志 
	 * @return
	 */
	public String queryLog();
	
}
