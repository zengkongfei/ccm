package com.ccm.mc.webapp.action.log;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.order.OrderEmailConfirm;
import com.ccm.api.model.order.vo.OrderEmailConfirmResult;
import com.ccm.api.model.rsvtype.vo.SearchOrderEmailConfirmCriteria;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.sms.SmsSendManager;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;

public class SmsSendAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6062830277680636799L;
	private Log log = LogFactory.getLog(SmsSendAction.class);
	
	private SearchOrderEmailConfirmCriteria soc = new SearchOrderEmailConfirmCriteria();
	private OrderEmailConfirmResult result = new OrderEmailConfirmResult();
	
	@Autowired
	private SmsSendManager smsSendManager;
	@Autowired
	private HotelManager hotelManager;
	@Autowired
	private HotelMCManager hotelMCManager;
	
	public String list(){
		Map<String, String> statusDictMap = new HashMap<String, String>();
		statusDictMap.put("", getText("common.select.selectAll"));
		statusDictMap.put("email", getText("ccm.InterfaceAutomaticMonitoringLog.Email"));
		statusDictMap.put("SMSREMIND", getText("ccm.InterfaceAutomaticMonitoringLog.SMS"));
		this.getRequest().setAttribute("statusDictMap", statusDictMap);
		B2BUser user = getCurLoginUser();
		List<Hotel> hotelList = null;
		// 如果是运营人员
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hotelList = hotelManager.getAll();
		} else {
			hotelList = hotelMCManager.getUserHotelByChainAndUserId(user.getUserId());
		}
		
		this.getRequest().setAttribute("hotelList", hotelList);
		
		if(soc.getHotelCodes()==null ||soc.getHotelCodes().isEmpty()||soc.getStartDate()==null||soc.getEndDate()==null){
			return "list";
		}
		
		int pageSize = this.getCurrentPageSize("smsSendLog");
		int pageNo = this.getCurrentPageNo("smsSendLog");
		soc.setPageNum(pageNo);
		soc.setPageSize(pageSize);
		if(StringUtils.hasText(soc.getEmailType()) && soc.getEmailType().equalsIgnoreCase("email")){
			soc.setSource(SmsType.SMS_SOURCE_EMAIL_INGERFACE);
		}
		result = smsSendManager.searchHotelInterfaceLog(soc);
		
		
		return "list";
	}
	
	public void export(){
		try {
			soc.setExcelFlag(true);
			soc.setNeedPage(false);
			soc.setSource(SmsType.SMS_SOURCE_EMAIL_INGERFACE);
			result = smsSendManager.searchHotelInterfaceLog(soc);
			
			String[] colName = { getText("ccm.ReservationMonitorReport.PropertyCode"), 
					getText("ccm.HotelUser.ContactWay"), 
					getText("ccm.InterfaceAutomaticMonitoringLog.SendingType"), 
					getText("ccm.InterfaceAutomaticMonitoringLog.SendingTime"), 
					getText("ccm.ReservationEmailConfirmationLetter.SendingStatus")};
			// excel要导出的数据
			List<String[]> dataList = getExcelDataByObjList(result.getResultList());
			SXSSFWorkbook workbook = ExportUtil.createExcel2("ReservationsList", colName, dataList);
			// 生成excel文件名
			String excelName = ExportUtil.createFileName(getText("ccm.InterfaceAutomaticMonitoringLog")); 
			// 设置导出的excel文件从页面中下载
			getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");
			// 输出流
			OutputStream os = getResponse().getOutputStream();
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private List<String[]> getExcelDataByObjList(List<OrderEmailConfirm> list) {
		List<String[]> dataList = new ArrayList<String[]>();
		
		if (list != null && list.size() > 0) {
			for (OrderEmailConfirm orderEmailConfirm : list) {
				String[] arr = new String[11];
				arr[0] = orderEmailConfirm.getHotelCode();
				arr[1] = orderEmailConfirm.getEmailAddress();
				arr[2] = orderEmailConfirm.getSmsType();
				arr[3] = DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", orderEmailConfirm.getSendTime());
				arr[4] = orderEmailConfirm.getSta();
				dataList.add(arr);
			}
		}
		return dataList;
	}

	
	
	public SearchOrderEmailConfirmCriteria getSoc() {
		return soc;
	}

	public void setSoc(SearchOrderEmailConfirmCriteria soc) {
		this.soc = soc;
	}

	public OrderEmailConfirmResult getResult() {
		return result;
	}

	public void setResult(OrderEmailConfirmResult result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "SmsSendAction [log=" + log + ", soc=" + soc + ", result="
				+ result + "]";
	}

	
	
	
}
