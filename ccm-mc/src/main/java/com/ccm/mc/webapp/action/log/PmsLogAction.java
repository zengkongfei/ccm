package com.ccm.mc.webapp.action.log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.log.ReceivePmsLog;
import com.ccm.api.model.log.vo.ReceivePmsLogCriteria;
import com.ccm.api.model.log.vo.ReceivePmsLogResult;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.UserRole;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.log.ReceivePmsLogManager;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Jenny
 * 
 */
public class PmsLogAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2153377910584706563L;

	private Log log = LogFactory.getLog(PmsLogAction.class);

	// 查询条件
	private ReceivePmsLogCriteria soc = new ReceivePmsLogCriteria();

	// 返回结果
	private ReceivePmsLogResult receivePmsLogResult = new ReceivePmsLogResult();
	//private List<HotelVO> hotelList = new ArrayList<HotelVO>();
	private List<Hotel> hotelList = new ArrayList<Hotel>();
	@Resource
	private HotelMCManager hotelMCManager;
	
	@Autowired
	private ReceivePmsLogManager receivePmsLogManager;
	@Autowired
	private HotelManager hotelManager;
	
	/**
	 * 订单日志列表
	 * 
	 * @return
	 */
	public String list() {
		
		this.getRequest().setAttribute("isMonitor", isMonitor());
	
		HashMap<String, String> logStatus = new HashMap<String, String>();
		logStatus.put(null, getText("common.All"));
		logStatus.put("on", getText("common.Active"));
		logStatus.put("off", getText("common.Stop"));
		this.getRequest().setAttribute("logStatus",logStatus);
	
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//获取所有直连的酒店
		//hotelList= hotelManager.getAllDirectPmsHotel(language, true);
		this.getAuthedHotels();
		
		
		if (soc.getHoteCodes() == null) {
			return "list";
		}
		
		soc.setLanguageCode(language);
		// 分页
		int pageSize = this.getCurrentPageSize("pmslog");
		int pageNo = this.getCurrentPageNo("pmslog");
		soc.setPageNum(pageNo);
		soc.setPageSize(pageSize);
		
		receivePmsLogResult = receivePmsLogManager.searchList(soc);
		
		this.getRequest().setAttribute("resultSize", receivePmsLogResult.getTotalCount());
		
		return "list";

	}

	public String pie() {		
		
		return "pie";
	}
	/**
	 * 是否开启监控图
	 * @return
	 */
    public boolean isMonitor(){
    	boolean is10=false;//是否为运营人员
		boolean is72=false;//是否有报表日志权限
		boolean is95=false;//是否有监控日志权限
		boolean isMonitor=false;
		
		//监控日志 roleId=95	
		B2BUser user=getCurLoginUser();	

		List<UserRole> userRoles=userManager.getUserRolesByUserId(user.getUserId());
		/*
		for (UserRole userRole : userRoles) {
			if("10".equals(userRole.getRoleId())||"1".equals(userRole.getRoleId())){
				is10=true;
			}
		}
		*/
		if("1".equals(user.getCompanyId())){
			is10=true;
			for (UserRole userRole : userRoles) {
				if("72".equals(userRole.getRoleId())||"1".equals(userRole.getRoleId())){
					is72=true;
				}
			}
			if(is72){
				for (UserRole userRole : userRoles) {
					if( "95".equals(userRole.getRoleId())||"1".equals(userRole.getRoleId()) ){
						is95=true;
					}
				}
			}
		}

		if(is95&&is10&&is72){
			isMonitor=true;
		}
		
		return isMonitor;
    }
	/**
	 * ajax获取PieData
	 */
	public void ajaxGetPieData() {
		
		JSONArray dataArr = new JSONArray();
		
		if(isMonitor()){
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			List<String> hoteCodes=new ArrayList<String>();
			List<HotelVO> hvos= hotelManager.getAllDirectPmsHotel(language, true);
			for (HotelVO h : hvos) {
				hoteCodes.add(h.getHotelCode());
			}
			soc.setHoteCodes(hoteCodes);
			soc.setNeedPage(false);
			soc.setLanguageCode(language);
			
			receivePmsLogResult = receivePmsLogManager.searchList(soc);
			
			List<ReceivePmsLog> receivePmsLogs=receivePmsLogResult.getResultList();
			int red=0;
			int green=0;
			for (ReceivePmsLog receivePmsLog : receivePmsLogs) {
				String status=receivePmsLog.getStatusResult();
				if("on".equals(status)){
					green++;
				}else if("off".equals(status)){
					red++;
				}
			}

			JSONObject pieObj = new JSONObject();
			pieObj.put("red", red);
			pieObj.put("green", green);
			pieObj.put("isMonitor", isMonitor());
			
			dataArr.add(pieObj);
		}
		
		ajaxResponse(dataArr.toString());
	}
	
	public String export() {
		HashMap<String, String> logStatus = new HashMap<String, String>();
		logStatus.put(null, getText("common.All"));
		logStatus.put("on", getText("common.Active"));
		logStatus.put("off", getText("common.Stop"));
		this.getRequest().setAttribute("logStatus",logStatus);
		
		return "export";
	}

	public String getExportFileName() {
		
		try {
			return new String(ExportUtil.createFileName(getText("ccm.PropertyInterfaceMonitor")).getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ExportUtil.createFileName("hotelMonitorLog");
	}

	public InputStream getExcelFile() throws Exception {
		
		try {
			if (soc.getHoteCodes() != null) {
				soc.setNeedPage(false);
				Locale locale = ActionContext.getContext().getLocale();
				String language = locale.getLanguage()+"_"+locale.getCountry();
				soc.setLanguageCode(language);
				receivePmsLogResult = receivePmsLogManager.searchList(soc);
			}
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(getText("ccm.PropertyInterfaceMonitor"));
			// 创建表头
			HSSFRow titleRow = sheet.createRow(0);
			HSSFCell cell = titleRow.createCell(0);
			int i = 0;
			HSSFRow headerRow = sheet.createRow(0);
			
			String[] colName = { getText("ccm.ReservationMonitorReport.PropertyCode"),
					getText("ccm.UserActivityLog.PropertyName"),
					getText("ccm.hotel.MonitorPMSHeartBeat"),
					getText("ccm.PMSMessageLog.PMSType"),
					getText("ccm.ReservationsManagment.Status"),
					getText("ccm.PropertyInterfaceMonitor.UploadMessageTimeInterval"),
					getText("ccm.PropertyList.CreditOnlineHotel"),
					getText("ccm.PropertyList.DisplacementInterface")};
			for (String name : colName) {
				cell = headerRow.createCell(i++);
				cell.setCellValue(name);
			}

			int rowIndex = 1;

			for (int n = 0; n < receivePmsLogResult.getResultList().size(); n++) {

				HSSFRow valueRow = sheet.createRow(rowIndex);
				i = 0;

				ReceivePmsLog rLog = receivePmsLogResult.getResultList().get(n);
		
				cell = valueRow.createCell(i++);
				cell.setCellValue(rLog.getHotelCode());
				
				cell = valueRow.createCell(i++);
				cell.setCellValue(rLog.getHotelName());
				
				cell = valueRow.createCell(i++);
				if(rLog.getIsPMSHeartBeat()){
					cell.setCellValue(getText("common.Yes"));
				}else{
					cell.setCellValue(getText("common.Not"));
				}

				cell = valueRow.createCell(i++);
				cell.setCellValue(rLog.getPmsType());

				cell = valueRow.createCell(i++);
				if ("on".equals(rLog.getStatusResult())) {
					cell.setCellValue(getText("common.Active"));
				} else if ("off".equals(rLog.getStatusResult())) {
					cell.setCellValue(getText("common.Stop"));
				} else {
					cell.setCellValue("-");
				}

				cell = valueRow.createCell(i++);
				cell.setCellValue(rLog.getSpaceSec());
				
				//上线信用住”
				cell = valueRow.createCell(i++);
				if ("1".equals(rLog.getIsCreditOnlineHotel())) {
					cell.setCellValue(getText("common.Yes"));
				} else {
					cell.setCellValue(getText("common.Not"));
				}

				//“置换机”
				cell = valueRow.createCell(i++);
				if ("1".equals(rLog.getIsDisplacementInterface())) {
					cell.setCellValue(getText("common.Yes"));
				}  else {
					cell.setCellValue(getText("common.Not"));
				}
			
				
				rowIndex++;

			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				workbook.write(baos);
			} catch (IOException e) {
				e.printStackTrace();
			}

			byte[] ba = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			return bais;

		} catch (Exception e) {
			log.info(e);
		}
		return null;
	}

	public ReceivePmsLogCriteria getSoc() {
		return soc;
	}

	public void setSoc(ReceivePmsLogCriteria soc) {
		this.soc = soc;
	}

	public ReceivePmsLogResult getReceivePmsLogResult() {
		return receivePmsLogResult;
	}

	public void setReceivePmsLogResult(ReceivePmsLogResult receivePmsLogResult) {
		this.receivePmsLogResult = receivePmsLogResult;
	}
	
	public List<Hotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}
	//只能看到其由权限的酒店代码，且改酒店是直连的(isDirectPms=true)
	private void getAuthedHotels() {
		B2BUser user = getCurLoginUser();
		//获取当前用户有权限的chain
		List<ChainVO> chainVos=user.getChainVOs();
		List<String> chainIdList=new ArrayList<String>();
		for (ChainVO v : chainVos) {
			chainIdList.add(v.getChainId());
		}
		// 如果存在集团
		if (chainIdList != null && chainIdList.size() > 0) {
			for (String chainId : chainIdList) {
				// 如果是运营人员
				if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
					hotelList.addAll(hotelManager.getHotelByChainId(chainId));
				} else {
					hotelList.addAll(hotelMCManager.getUserHotelByChainAndUserId(chainId, user.getUserId()));
				}
			}
		}
		//去除非直连的
		int size=hotelList.size();
		List<Hotel> hToRemove=new ArrayList<Hotel>();
		for (int i=0;i<size;i++) {
			Hotel h = hotelList.get(i);
			if(!h.getIsDirectPms()){	
				hToRemove.add(h);
			}
		}
		hotelList.removeAll(hToRemove);
	}	
}
