package com.ccm.mc.webapp.action.log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.log.ChannelOrder;
import com.ccm.api.model.log.ReceivePmsLog;
import com.ccm.api.model.log.SendOTALog;
import com.ccm.api.model.log.vo.ChannelOrderCriteria;
import com.ccm.api.model.log.vo.ChannelOrderResult;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.UserRole;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.log.ChannelOrderManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.XMLUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;
import com.opensymphony.xwork2.ActionContext;

public class ChannelOrderAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7496651028675683929L;

	@Autowired
	private ChannelOrderManager channelOrderManager;

	@Autowired
	private HotelManager hotelManager;
	
	private ChannelOrderResult channelOrderResult=new ChannelOrderResult();
	
	// 查询条件
	private ChannelOrderCriteria soc=new ChannelOrderCriteria();
	
	private String hotelCode;
	private String changedDate;
	
	private List<String> getChannelCodeList(){
		//渠道用户渠道权限
		B2BUser user = getCurLoginUser();
		List<Channel> channels=null;
		List<String> channelCodeList=new ArrayList<String>();
		if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
			channels=user.getChannels();
			for (Channel c : channels) {
				channelCodeList.add(c.getChannelCode());
			}
		}
		return channelCodeList;
	}
	
	public String list() {
		
		Locale locale = ActionContext.getContext().getLocale();
		String lang=locale.getLanguage() + "_" + locale.getCountry();
		
		if("zh_CN".equals(lang)){
			//
			List<String> chartTypes = new ArrayList<String>();
			chartTypes.add("饼图");
			chartTypes.add("柱状图");
			this.getRequest().setAttribute("chartTypes",chartTypes);
			//
			List<String> chartValues = new ArrayList<String>();
			chartValues.add("订单总数");
			chartValues.add("总晚房数");
			chartValues.add("订单总金额");
			this.getRequest().setAttribute("chartValues",chartValues);
		}else{ // en_US
			//
			List<String> chartTypes = new ArrayList<String>();
			chartTypes.add("Bar");
			chartTypes.add("Pie");
			this.getRequest().setAttribute("chartTypes",chartTypes);
			//
			List<String> chartValues = new ArrayList<String>();
			chartValues.add("CountOfOrders");
			chartValues.add("TotalRoomNights");
			chartValues.add("TotalAmountOfOrders");
			this.getRequest().setAttribute("chartValues",chartValues);
		}
		
		
		// 分页
		int pageSize = this.getCurrentPageSize("order");
		int pageNo = this.getCurrentPageNo("order");
		soc.setPageNum(pageNo);
		soc.setPageSize(pageSize);
		
		soc.setLanguageCode(lang);
		
		String changedStr=DateUtil.getDate(soc.getChangedDate());
		soc.setChangedStr(changedStr);

		// 获取hotelId
		List<Hotel> hotels = hotelManager.getHotelByHotelCode(soc.getHotelCode());
		if(null!=hotels&&hotels.size()==1){
			String hotelId = hotels.get(0).getHotelId();
			soc.setHotelId(hotelId);
		}
		
		B2BUser user=getCurLoginUser();
		if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
			soc.setChannelCodeList(getChannelCodeList());
		}
		
		channelOrderResult=channelOrderManager.getChannelOrders(soc);

		this.getRequest().setAttribute("resultSize", channelOrderResult.getTotalCount());
		
		return "list";
	}
	
	/**
	 * ajax获取PieData
	 */
	public void ajaxGetchannelOrderPieData() {
		
		boolean is72=false;//是否有报表日志权限
		boolean isMonitor=false;
		B2BUser user=getCurLoginUser();	
		List<UserRole> userRoles=userManager.getUserRolesByUserId(user.getUserId());
		for (UserRole userRole : userRoles) {
			if("72".equals(userRole.getRoleId())||"1".equals(userRole.getRoleId())){
				is72=true;
			}
			if("7208".equals(userRole.getRoleId())||"1".equals(userRole.getRoleId())){
				isMonitor=true;
			}
		}
		if(is72&&isMonitor){
			Locale locale = ActionContext.getContext().getLocale();
			soc.setLanguageCode(locale.getLanguage() + "_" + locale.getCountry());
			
			//String changedStr=DateUtil.getDate(soc.getChangedDate());
			soc.setChangedStr(changedDate);

			//获取当前登录的酒店
			if("currentHotelForChart".equals(hotelCode)){
				if(null!=user.getHotelvo()){
					hotelCode=user.getHotelvo().getHotelCode();
				}
				
			}
			// 获取hotelId
			List<Hotel> hotels = hotelManager.getHotelByHotelCode(hotelCode);
			if(null!=hotels&&hotels.size()==1){
				String hotelId = hotels.get(0).getHotelId();
				soc.setHotelId(hotelId);
			}
			
			JSONArray dataArr = new JSONArray();
			
			if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
				soc.setChannelCodeList(getChannelCodeList());
			}
			
			channelOrderResult=channelOrderManager.getChannelOrders(soc);

			List<ChannelOrder> channelOrders=channelOrderResult.getResultList();
			for (ChannelOrder channelOrder : channelOrders) {
				JSONObject pieObj = new JSONObject();
				pieObj.put("ChannelCode", channelOrder.getChannelCode());
				pieObj.put("CountOfOrder", channelOrder.getCountOfOrder());
				pieObj.put("totalRoomNight", channelOrder.getTotalRoomNights());
				pieObj.put("totalAmountOfOrder", channelOrder.getTotalAmountOfOrders().toString());
				dataArr.add(pieObj);
			}

			ajaxResponse(dataArr.toString());
		}
	}
	
	private List<String[]> getExcelDataByObjList(List<ChannelOrder> channelOrderList) {
		List<String[]> dataList = new ArrayList<String[]>();
		
		if (channelOrderList != null && channelOrderList.size() > 0) {
			for (ChannelOrder channelOrder : channelOrderList) {
				String[] arr = new String[5];
				
				arr[0] = channelOrder.getChannelCode();
				arr[1] = channelOrder.getHotelName();
				arr[2] = channelOrder.getCountOfOrder().toString();
				arr[3] = channelOrder.getTotalRoomNights().toString();
				arr[4] = channelOrder.getTotalAmountOfOrders().toString();
			
				dataList.add(arr);
			}
		}
		return dataList;
	}
	
	public String export() throws IOException {

		// 设置导出参数
		soc.setNeedPage(Boolean.FALSE);
		
		try {
			// 查询数据
			Locale locale = ActionContext.getContext().getLocale();
			String lang=locale.getLanguage() + "_" + locale.getCountry();
			soc.setLanguageCode(lang);
			
			String changedStr=DateUtil.getDate(soc.getChangedDate());
			soc.setChangedStr(changedStr);

			// 获取hotelId
			List<Hotel> hotels = hotelManager.getHotelByHotelCode(soc.getHotelCode());
			if(null!=hotels&&hotels.size()==1){
				String hotelId = hotels.get(0).getHotelId();
				soc.setHotelId(hotelId);
			}
			
			B2BUser user=getCurLoginUser();
			if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
				soc.setChannelCodeList(getChannelCodeList());
			}
			
			channelOrderResult=channelOrderManager.getChannelOrders(soc);
			
			// excel表头
			String[] colName = { 
					getText("ccm.Channel.ChannelCode"),
					getText("ccm.ReservationMonitorReport.PropertyCode"),
					getText("ccm.report.CountofOrders"),
					getText("ccm.report.RoomNights"),		
					getText("ccm.report.AmountOfOrder") };
			
			// excel要导出的数据
			List<String[]> dataList = getExcelDataByObjList(channelOrderResult.getResultList());
			
			SXSSFWorkbook workbook = ExportUtil.createExcel2("ChannelOrderReport", colName, dataList);

			// 生成excel文件名
			String excelName = ExportUtil.createFileName(getText("Channel Order Report")); 
			// 设置导出的excel文件从页面中下载
			getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");

			// 输出流
			OutputStream os = getResponse().getOutputStream();

			workbook.write(os);
			os.flush();
			os.close();
			
		} catch (Exception e) {
			getResponse().getWriter().print(CommonUtil.getExceptionMsg(e, "export fail"));
			getResponse().getWriter().flush();
		}

		return null;
	}
	
	public HotelManager getHotelManager() {
		return hotelManager;
	}

	public void setHotelManager(HotelManager hotelManager) {
		this.hotelManager = hotelManager;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}


	public String getChangedDate() {
		return changedDate;
	}

	public void setChangedDate(String changedDate) {
		this.changedDate = changedDate;
	}

	public ChannelOrderManager getChannelOrderManager() {
		return channelOrderManager;
	}

	public void setChannelOrderManager(ChannelOrderManager channelOrderManager) {
		this.channelOrderManager = channelOrderManager;
	}

	public ChannelOrderResult getChannelOrderResult() {
		return channelOrderResult;
	}

	public void setChannelOrderResult(ChannelOrderResult channelOrderResult) {
		this.channelOrderResult = channelOrderResult;
	}

	public ChannelOrderCriteria getSoc() {
		return soc;
	}

	public void setSoc(ChannelOrderCriteria soc) {
		this.soc = soc;
	}

}