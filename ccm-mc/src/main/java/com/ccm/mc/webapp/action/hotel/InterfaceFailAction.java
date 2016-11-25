package com.ccm.mc.webapp.action.hotel;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.model.shijicare.vo.ShijicareCriteria;
import com.ccm.api.model.shijicare.vo.ShijicareSearchResult;
import com.ccm.api.model.shijicare.vo.ShijicareVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.shijicare.ShijiCareManager;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;

/**
 * 接口监控失败日志
 * @author hyh
 *
 */
public class InterfaceFailAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3777075360254472543L;
	private Log log = LogFactory.getLog(InterfaceFailAction.class);
	
	private ShijicareCriteria soc = new ShijicareCriteria();
	private ShijicareSearchResult result = new ShijicareSearchResult();
	
	
	@Autowired
	private HotelManager hotelManager;
	@Autowired
	private HotelMCManager hotelMCManager;
	@Autowired
	private ShijiCareManager shijiCareManager;
	
	
	/**
	 * 查询列表
	 */
	public String list(){
		ShijiCare sc = new ShijiCare();
		HashMap<String, String> logStatus = new HashMap<String, String>();
		logStatus.put(null, getText("common.All"));
		logStatus.put(sc.getProblemAssignTo(ShijiCare.PROBLEMTYPE_ADS_PENDINGMSG), "消息堵塞");
		logStatus.put(sc.getProblemAssignTo(ShijiCare.PROBLEMTYPE_PMSINTEFACE), "接口");
		logStatus.put(sc.getProblemAssignTo(ShijiCare.PROBLEMTYPE_PMS_ORDER), "订单");
		this.getRequest().setAttribute("logStatus",logStatus);
		if(soc.getBeginDate()!=null){
			int pageSize = this.getCurrentPageSize("shijicareLog");
			int pageNo = this.getCurrentPageNo("shijicareLog");
			soc.setPageNum(pageNo);
			soc.setPageSize(pageSize);
			result = shijiCareManager.searchShijiCareVO(soc);
		}
		
		
		B2BUser user = getCurLoginUser();
		List<Hotel> hotelList = null;
		// 如果是运营人员
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hotelList = hotelManager.getAll();
		} else {
			hotelList = hotelMCManager.getUserHotelByChainAndUserId(user.getUserId());
		}

		this.getRequest().setAttribute("hotelList", hotelList);
		
		return "list";
	}
	
	/**
	 * 导出
	 */
	public void export(){
		try {
			if(soc.getBeginDate()!=null){
				soc.setExcelFlag(true);
				soc.setNeedPage(false);
				result = shijiCareManager.searchShijiCareVO(soc);
				
				String[] colName = { getText("ccm.ReservationMonitorReport.PropertyCode"), 
						getText("ccm.PropertyInterfaceMonitor.logStatus"), 
						getText("ccm.PropertyInterfaceMonitor.CaseStatus"), 
						"CASE ID", 
						getText("ccm.MessagePushLog.ExceptionReason"), 
						getText("ccm.orderFail.FailureTimeCaseSetup")};
				// excel要导出的数据
				List<String[]> dataList = getExcelDataByObjList(result.getResultList());
				SXSSFWorkbook workbook = ExportUtil.createExcel2("ReservationsList", colName, dataList);
				// 生成excel文件名
				String excelName = ExportUtil.createFileName(getText("ccm.orderFail.InterfaceMonitorFailureLog")); 
				// 设置导出的excel文件从页面中下载
				getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
				getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");
				// 输出流
				OutputStream os = getResponse().getOutputStream();
				workbook.write(os);
				os.flush();
				os.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	private List<String[]> getExcelDataByObjList(List<ShijicareVO> list) {
List<String[]> dataList = new ArrayList<String[]>();
		
		if (list != null && list.size() > 0) {
			for (ShijicareVO faxSend : list) {
				String[] arr = new String[11];
				arr[0] = faxSend.getHotelCode();
				String assignTo  = faxSend.getAssignTo();
				if(assignTo.equalsIgnoreCase("tsi")){
					arr[1] = getText("ccm.PropertyInterfaceMonitor.Interface");
				}else if(assignTo.equalsIgnoreCase("tsr")){
					arr[1] = getText("ccm.PropertyInterfaceMonitor.Reservation");
				}else if(assignTo.equalsIgnoreCase("tsm")){
					arr[1] = getText("ccm.PropertyInterfaceMonitor.MessageJam");
				}
				
				String status = faxSend.getStatus();
				Boolean isClosed = faxSend.getIsclose();
				String closeCode = faxSend.getCloseCode();
				
				if("SUCCESS".equalsIgnoreCase(status) && !isClosed){//新建
					arr[2] = getText("ccm.PropertyInterfaceMonitor.Created");
				}else if("FAIL".equalsIgnoreCase(status)){//新建失败
					arr[2] = getText("ccm.PropertyInterfaceMonitor.CreateFailed");
				}else if("SUCCESS".equalsIgnoreCase(status) && isClosed && "1".equalsIgnoreCase(closeCode)){//关闭成功
					arr[2] = getText("ccm.PropertyInterfaceMonitor.Closed");
				}else if("SUCCESS".equalsIgnoreCase(status) && isClosed && !"1".equalsIgnoreCase(closeCode)){//关闭失败
					arr[2] = getText("ccm.PropertyInterfaceMonitor.CloseFailed");
				}
				
				if("SUCCESS".equalsIgnoreCase(status)){
					arr[3] = faxSend.getResultMsg();
				}
			
				//新建失败的异常原因
				if("FAIL".equalsIgnoreCase(status)){
					arr[4] = faxSend.getResultMsg();
				}
				//关闭失败的异常原因
				if("SUCCESS".equalsIgnoreCase(status) && isClosed && !"1".equalsIgnoreCase(closeCode)){//关闭失败
					arr[4] = faxSend.getCloseDesp();
				}
				
				arr[5] = DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", faxSend.getCreatedTime());
				dataList.add(arr);
			}
		}
		return dataList;
	}

	public ShijicareCriteria getSoc() {
		return soc;
	}

	public void setSoc(ShijicareCriteria soc) {
		this.soc = soc;
	}

	public ShijicareSearchResult getResult() {
		return result;
	}

	public void setResult(ShijicareSearchResult result) {
		this.result = result;
	}
	
	
}
