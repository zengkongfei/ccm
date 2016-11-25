package com.ccm.mc.webapp.action.order;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.fax.vo.FaxSendCriteria;
import com.ccm.api.model.fax.vo.FaxSendSearchResult;
import com.ccm.api.model.fax.vo.FaxSendVO;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.fax.FaxSendManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;

/**
 * 订单监控失败日志
 * @author hyh
 *
 */
public class OrderFailAction extends BaseAction {
	
	private Log log = LogFactory.getLog(OrderFailAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2234268199138817990L;
	
	// 查询条件
	private FaxSendCriteria soc = new FaxSendCriteria();
	// 返回结果
	private FaxSendSearchResult result = new FaxSendSearchResult();
	
	@Autowired
	private FaxSendManager faxSendManager;
	@Autowired
	private HotelManager hotelManager;
	@Autowired
	private HotelMCManager hotelMCManager;
	
	
	/**
	 * 查询列表
	 */
	public String list(){
		if(soc.getBeginDate()!=null){
			// 分页
			int pageSize = this.getCurrentPageSize("orderFail");
			int pageNo = this.getCurrentPageNo("orderFail");
			soc.setPageNum(pageNo);
			soc.setPageSize(pageSize);
			result = faxSendManager.searchFaxSendVO(soc);
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
			soc.setExcelFlag(true);
			soc.setNeedPage(false); 
			result = faxSendManager.searchFaxSendVO(soc);

			String[] colName = { getText("ccm.ReservationMonitorReport.PropertyCode"), 
					getText("ccm.ReservationsManagment.CRSNo"), 
					getText("ccm.ReservationsManagment.AltId"), 
//					getText("ccm.orderFail.CancellationNumber"), 
					getText("ccm.orderFail.FaxResult"), 
					getText("ccm.orderFail.FaxFailureTime")};
			// excel要导出的数据
			List<String[]> dataList = getExcelDataByObjList(result.getResultList());
			SXSSFWorkbook workbook = ExportUtil.createExcel2("ReservationsList", colName, dataList);
			// 生成excel文件名
			String excelName = ExportUtil.createFileName(getText("ccm.orderFail.ReservationMonitorFailureLog")); 
			// 设置导出的excel文件从页面中下载
			getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");
			// 输出流
			OutputStream os = getResponse().getOutputStream();
			workbook.write(os);
			os.flush();
			os.close();
		}catch (Exception e) {
			log.error(e);
		}	
	}
	
	private List<String[]> getExcelDataByObjList(List<FaxSendVO> list) {
		List<String[]> dataList = new ArrayList<String[]>();
		
		if (list != null && list.size() > 0) {
			for (FaxSendVO faxSend : list) {
				String[] arr = new String[11];
				// arr[] = master.get
				arr[0] = faxSend.getHotelCode();
				
				arr[1] = faxSend.getBizId();
				
				arr[2] = faxSend.getPmsId();
				arr[3] = faxSend.getFaxStatus();
//				arr[3] = faxSend.getCancelId();
				arr[4] = DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", faxSend.getSendTime());
				dataList.add(arr);
			}
		}
		return dataList;
	}

	public FaxSendCriteria getSoc() {
		return soc;
	}

	public void setSoc(FaxSendCriteria soc) {
		this.soc = soc;
	}

	public FaxSendSearchResult getResult() {
		return result;
	}

	public void setResult(FaxSendSearchResult result) {
		this.result = result;
	}
	
	
	
}
