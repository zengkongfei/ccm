package com.ccm.mc.webapp.action.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.ratePlan.RoomStatusAndProductSwitch;
import com.ccm.api.model.ratePlan.vo.RoomStatusAndProductSwitchCriteria;
import com.ccm.api.model.ratePlan.vo.RoomStatusAndProductSwitchResult;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.ratePlan.RestrictionCalcManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;

public class RoomStatusAndProductSwitchAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6162364451964926118L;

	private Log log = LogFactory.getLog(RoomStatusAndProductSwitchAction.class);
	
	// 查询条件
	private RoomStatusAndProductSwitchCriteria rspsc = new RoomStatusAndProductSwitchCriteria();
	
	//返回结果
	private RoomStatusAndProductSwitchResult switchSearchResult = new RoomStatusAndProductSwitchResult();
	
	@Resource
	private HotelManager hotelManager;
	
	@Resource
	private HotelMCManager hotelMCManager;
	
	@Resource
	private RoomTypeManager roomTypeManager;
	
	@Resource
	private RatePlanManager rateplanManager;
	
	@Resource
	private RestrictionCalcManager restrictionCalcManager;
	
	//酒店列表
	private List<Hotel> hotelList;
	
	//房型列表
	List<RoomType> roomTypeList;
	
	//房价列表
	List<Rateplan> rateplanList;
	
	//数据类型
	private Map<String, String> dataTypeMap = new HashMap<String, String>();
	
	/**
	 * 初始化
	 * @return
	 */
	public String list(){
		//初始化查询条件
		B2BUser user = getCurLoginUser();
		//如果是运营人员
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hotelList = hotelManager.getAll();
		}else{
			hotelList = hotelMCManager.getUserHotelByChainAndUserId(user.getUserId());
		}
		this.setDataTypeMap();
		
		//设置初始化标识
		getRequest().setAttribute("isInit", "1");
		
		return "list";
	}

	/**
	 * 查询
	 * @return
	 */
	public String query(){
		// 分页
		int pageSize = this.getCurrentPageSize("roomStatusAndProductSwitchs");
		int pageNo = this.getCurrentPageNo("roomStatusAndProductSwitchs");
		rspsc.setPageNum(pageNo);
		rspsc.setPageSize(pageSize);
		
		//设置查询参数
		rspsc.setExcelFlag(Boolean.FALSE);
		log.info(rspsc.toString());
		
		if(StringUtils.isNotBlank(rspsc.getType())){
			if("1".equals(rspsc.getType())){
				rspsc.setIncludeRoomStatus(Boolean.TRUE);
			}else if("2".equals(rspsc.getType())){
				rspsc.setIncludeProduct(Boolean.TRUE);
			}else{
				rspsc.setIncludeRoomStatus(Boolean.TRUE);
				rspsc.setIncludeProduct(Boolean.TRUE);
				rspsc.setIsUnion(Boolean.TRUE);
			}
		}else{
			rspsc.setIncludeRoomStatus(Boolean.TRUE);
			rspsc.setIncludeProduct(Boolean.TRUE);
			rspsc.setIsUnion(Boolean.TRUE);
		}
		
		switchSearchResult = restrictionCalcManager.searchRoomStatusAndProductSwitchs(rspsc);
		if(switchSearchResult.getResultList() != null 
				&&switchSearchResult.getResultList().size() > 0){
			this.setDataTypeMap();
			for (RoomStatusAndProductSwitch switchs : switchSearchResult.getResultList()) {
				switchs.setType(dataTypeMap.get(switchs.getType()));
			}
		}
		
		//初始化查询条件
		B2BUser user = getCurLoginUser();
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hotelList = hotelManager.getAll();
		}else{
			hotelList = hotelMCManager.getUserHotelByChainAndUserId(user.getUserId());
		}
		this.setDataTypeMap();
		
		
		//如果酒店id不为空
		if(rspsc.getHotelCodes() != null && rspsc.getHotelCodes().size() == 1){
			String hotelCode = rspsc.getHotelCodes().get(0);
			List<Hotel> hotels = hotelManager.getHotelByHotelCode(hotelCode);
			if(hotels!=null && hotels.size() > 0){
				roomTypeList = roomTypeManager.getRoomTypeByHotelId(hotels.get(0).getHotelId());
				rateplanList = rateplanManager.getRatePlanByHotelId(hotels.get(0).getHotelId());
			}
		}
		
		return "list";
	}
	
	/**
	 * 导出
	 * @return
	 * @throws IOException 
	 */
	public String export() throws IOException{
		//导出excel标识
		rspsc.setExcelFlag(Boolean.TRUE);
		
		if(StringUtils.isNotBlank(rspsc.getType())){
			if("1".equals(rspsc.getType())){
				rspsc.setIncludeRoomStatus(Boolean.TRUE);
			}else if("2".equals(rspsc.getType())){
				rspsc.setIncludeProduct(Boolean.TRUE);
			}else{
				rspsc.setIncludeRoomStatus(Boolean.TRUE);
				rspsc.setIncludeProduct(Boolean.TRUE);
				rspsc.setIsUnion(Boolean.TRUE);
			}
		}else{
			rspsc.setIncludeRoomStatus(Boolean.TRUE);
			rspsc.setIncludeProduct(Boolean.TRUE);
			rspsc.setIsUnion(Boolean.TRUE);
		}
		
		try{
			switchSearchResult = restrictionCalcManager.searchRoomStatusAndProductSwitchs(rspsc);
			
			// excel表头
			String[] colName = { getText("ccm.Channel.ChannelCode"),
					getText("ccm.ReservationMonitorReport.PropertyCode"),
					getText("ccm.PropertyRoomOccupancyReport.TrainingFacilitationServicesSupervisor"),
					getText("ccm.Channel.RoomTypeCode"),
					getText("ccm.RestrictionsManagement.RateCode"),
					getText("common.type"),
					getText("common.date") };
			
			// excel要导出的数据
			List<String[]> dataList = this.getExcelDataBySwitchList(switchSearchResult.getResultList());
			
			SXSSFWorkbook workbook = ExportUtil.createExcel2("roomStatusAndProductSwitchList", colName, dataList);
			
			// 生成excel文件名
			String excelName = ExportUtil.createFileName(getText("ccm.RoomInventoryandRestrictionsReport"));
			// 设置导出的excel文件从页面中下载
			getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");
			// getResponse().setContentType("application/vnd.ms-excel;charset=gb2312");
	
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

	private List<String[]> getExcelDataBySwitchList(List<RoomStatusAndProductSwitch> resultList) {
		List<String[]> dataList = new ArrayList<String[]>();
		
		//如果订单结果不为空
		if(resultList!=null && resultList.size()>0){
			this.setDataTypeMap();
			
			for (RoomStatusAndProductSwitch roomStatusAndProduct : resultList) {
				String[] arr = new String[7];
				arr[0] = roomStatusAndProduct.getChannelCode();
				arr[1] = roomStatusAndProduct.getHotelCode();
				arr[2] = roomStatusAndProduct.getSpecialist();
				arr[3] = roomStatusAndProduct.getRoomTypeCode();
				arr[4] = roomStatusAndProduct.getRatePlanCode();
				arr[5] = dataTypeMap.get(roomStatusAndProduct.getType());
				arr[6] = DateUtil.convertDateToString(roomStatusAndProduct.getDate());
				
				dataList.add(arr);
			}
		}
		return dataList;
	}
	
	/**
	 * ajax获取房型信息
	 */
	public void ajaxGetRoomType(){
		String hotelCode = getRequest().getParameter("hotelCode");
		List<Hotel> hotels = hotelManager.getHotelByHotelCode(hotelCode);
		if(hotels!=null && hotels.size() > 0){
			List<RoomType> roomTypeList = roomTypeManager.getRoomTypeByHotelId(hotels.get(0).getHotelId());
			
			JSONArray roomArr = new JSONArray();
			if(roomTypeList != null && roomTypeList.size() > 0){
				for (RoomType roomTypeVO : roomTypeList) {
					JSONObject roomJson = new JSONObject();
					roomJson.put("roomTypeCode", roomTypeVO.getRoomTypeCode());
					roomJson.put("roomTypeId", roomTypeVO.getRoomTypeId());
					roomArr.add(roomJson);
				}
			}
			ajaxResponse(roomArr.toString());
		}
	}
	
	/**
	 * ajax获取房价信息
	 */
	public void ajaxGetRateplan(){
		String hotelCode = getRequest().getParameter("hotelCode");
		List<Hotel> hotels = hotelManager.getHotelByHotelCode(hotelCode);
		if(hotels!=null && hotels.size() > 0){
			List<Rateplan> rateplanList = rateplanManager.getRatePlanByHotelId(hotels.get(0).getHotelId());
			
			JSONArray rateArr = new JSONArray();
			if(rateplanList != null && rateplanList.size() > 0){
				for (Rateplan rateplan : rateplanList) {
					JSONObject rateJson = new JSONObject();
					rateJson.put("ratePlanCode", rateplan.getRatePlanCode());
					rateJson.put("ratePlanId", rateplan.getRatePlanId());
					rateArr.add(rateJson);
				}
			}
			ajaxResponse(rateArr.toString());
		}
	}
	
	/**
	 * 设置类型
	 */
	private void setDataTypeMap(){
		dataTypeMap.put("1", getText("common.RoomInventory"));
		dataTypeMap.put("2", getText("common.Restrictions"));
	}

	public List<Hotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	public RoomStatusAndProductSwitchCriteria getRspsc() {
		return rspsc;
	}

	public void setRspsc(RoomStatusAndProductSwitchCriteria rspsc) {
		this.rspsc = rspsc;
	}

	public RoomStatusAndProductSwitchResult getSwitchSearchResult() {
		return switchSearchResult;
	}

	public void setSwitchSearchResult(
			RoomStatusAndProductSwitchResult switchSearchResult) {
		this.switchSearchResult = switchSearchResult;
	}

	public Map<String, String> getDataTypeMap() {
		return dataTypeMap;
	}

	public void setDataTypeMap(Map<String, String> dataTypeMap) {
		this.dataTypeMap = dataTypeMap;
	}

	public List<RoomType> getRoomTypeList() {
		return roomTypeList;
	}

	public void setRoomTypeList(List<RoomType> roomTypeList) {
		this.roomTypeList = roomTypeList;
	}

	public List<Rateplan> getRateplanList() {
		return rateplanList;
	}

	public void setRateplanList(List<Rateplan> rateplanList) {
		this.rateplanList = rateplanList;
	}
	
}
