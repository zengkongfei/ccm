package com.ccm.mc.webapp.action.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.rsvtype.Usage;
import com.ccm.api.model.rsvtype.vo.UsageCriteria;
import com.ccm.api.model.rsvtype.vo.UsageResult;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.rsvtype.UsageManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.action.order.OrderAction;
import com.ccm.mc.webapp.util.ExportUtil;

public class UsageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4833882394317032221L;

	private Log log = LogFactory.getLog(OrderAction.class);

	// 查询条件
	private UsageCriteria uc = new UsageCriteria();
	// 返回结果
	private UsageResult usageResult = new UsageResult();

	@Autowired
	private UsageManager usageManager;
	@Autowired
	private ChannelManager channelManager;
	@Resource
	private HotelManager hotelManager;
	@Resource
	private HotelMCManager hotelMCManager;
	@Resource
	private RoomTypeManager roomTypeManager;
	
	// 渠道列表
	private List<Channel> channelList = new ArrayList<Channel>();
	// 酒店列表
	private List<HotelVO> hotelList = new ArrayList<HotelVO>();

	private List<String> hotelIdList; // 酒店
	private List<String> channelIdList; // 渠道
	private List<String> roomTypeList; // 房型

	/**
	 * 
	 * @return
	 */
	public String list() {

		// 分页
		int pageSize = this.getCurrentPageSize("orderProductDetail");
		int pageNo = this.getCurrentPageNo("orderProductDetail");
		uc.setPageNum(pageNo);
		uc.setPageSize(pageSize);
		uc.setNeedPage(Boolean.TRUE);

		this.setInitCondition();
		if((null==uc.getHotelIdList())||(uc.getHotelIdList().isEmpty())||(uc.getHotelIdList().size()>2)){
			return "list";
		}
		if((null==uc.getResvDateBegin())||(null==uc.getResvDateEnd())){
			return "list";
		}
		Integer diff=DateUtil.dateDiff(uc.getResvDateBegin(), uc.getResvDateEnd());
		if((diff==null)||(diff>31)||(diff<0)){
			return "list";
		}
		usageResult = usageManager.getUsages(uc);
	
		return "list";
	}

	public String export() {
		
		if((null==uc.getHotelIdList())||(uc.getHotelIdList().isEmpty())){
			return "list";
		}
		if((null==uc.getResvDateBegin())||(null==uc.getResvDateEnd())){
			return "list";
		}
		Integer diff=DateUtil.dateDiff(uc.getResvDateBegin(), uc.getResvDateEnd());
		if((diff==null)||(diff>31)||(diff<0)){
			return "list";
		}
		
		uc.setNeedPage(Boolean.FALSE);
		try {
				usageResult = usageManager.getUsages(uc);
				// excel表头
				String[] colName = { 
						getText("ccm.usagereports.ResvDate"),
						getText("ccm.Channel.ChannelCode"),		
						getText("ccm.ReservationMonitorReport.PropertyCode"),
						getText("ccm.ReservationsManagment.RoomType"),
						getText("ccm.usagereports.allotment"),
						getText("ccm.usagereports.soldAllotment"),
						getText("ccm.usagereports.freeSell")
				};
				// excel要导出的数据
				List<String[]> dataList = getExcelDataByObjList(usageResult.getResultList());
				/*
				String[] tail = new String[6];
				tail[0]=getText("ccm.usagereports.soldAllotment")+getText("ccm.usagereports.Sum");
				tail[1]=usageResult.getAllotmentSum()==null?"":usageResult.getAllotmentSum().toString();
				tail[2]=getText("ccm.usagereports.allotment")+getText("ccm.usagereports.Sum");
				tail[3]=usageResult.getAllotmentRemainSum()==null?"":usageResult.getAllotmentRemainSum().toString();
				tail[4]=getText("ccm.usagereports.freeSell")+getText("ccm.usagereports.Sum");
				tail[5]=usageResult.getAvailableSum()==null?"":usageResult.getAvailableSum().toString();
			
				String[] tailValue = new String[7];
				tailValue[0]="";
				tailValue[1]="";
				tailValue[2]="";
				tailValue[3]="";
				tailValue[4]=tail[0]+": "+tail[1];
				tailValue[5]=tail[2]+": "+tail[3];
				tailValue[6]=tail[4]+": "+tail[5];
				*/
				//SXSSFWorkbook workbook = ExportUtil.createExcelTail("AllotmentUsageAndFree-sellInventoryUsageReport", colName, dataList,tailValue);
				
				SXSSFWorkbook workbook = ExportUtil.createExcel2("AllotmentUsageAndFree-sellInventoryUsageReport", colName, dataList);
				// 生成excel文件名
				String excelName = ExportUtil.createFileName(getText("ccm.usagereports.AllotmentUsageAnd")); 
				// 设置导出的excel文件从页面中下载
				getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
				getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");
				// 输出流
				OutputStream os = getResponse().getOutputStream();
				workbook.write(os);
				os.flush();
				os.close();			
		} catch (Exception e) {
			try {
				getResponse().getWriter().flush();
				getResponse().getWriter().print(CommonUtil.getExceptionMsg(e, "export fail"));
			} catch (IOException e1) {
				log.info("AllotmentUsageAndFree-sellInventoryUsageReport export fail");
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	private List<String[]> getExcelDataByObjList(List<Usage> usageList) {
		List<String[]> dataList = new ArrayList<String[]>();
		if (usageList != null && usageList.size() > 0) {
			for (Usage usage : usageList) {
				String[] arr = new String[11];
				arr[0]=DateUtil.getDateTime("yyyy-MM-dd", usage.getResvDate());
				arr[1]=usage.getChannelCode();
				arr[2]=usage.getHotelCode();
				arr[3]=usage.getRoomType();

				arr[4]=usage.getAllotment()==null?"":usage.getAllotment().toString();
				
				arr[5]=usage.getAllotmentSold()==null?"":usage.getAllotmentSold().toString();
				
				arr[6]=usage.getFreeSellSold()==null?"":usage.getFreeSellSold().toString();
				
				dataList.add(arr);
			}
		}
		return dataList;
	}
	
	/**
	 * 根据酒店id,ajax获取房型代码
	 */
	public void ajaxGetRoomType() {
		String hotelIds = getRequest().getParameter("hotelIds");
		if (StringUtils.hasText(hotelIds)) 
		{
			//组合酒店id的List
			String[] hotelArr = hotelIds.split(",");
			List<String> hotelIdList=new ArrayList<String>();
			Collections.addAll(hotelIdList, hotelArr);
		    //根据酒店id的List获取所有房型，并取出房型代码
			List<String> roomTypes=new ArrayList<String>();
			List<RoomType> types=roomTypeManager.getRoomTypeByHotelIdList(hotelIdList);
			for (RoomType roomType : types) {
				roomTypes.add(roomType.getRoomTypeCode());
			}
			//去掉重复的房型代码
			HashSet<String> mSets = new HashSet<String>(roomTypes); 
			roomTypes.clear(); 
			roomTypes.addAll(mSets);
			roomTypes.remove(null);
			//返回房型代码
			this.getRequest().setAttribute("roomTypes",roomTypes);
			JSONArray rateCodeArr = new JSONArray();
			if (roomTypes != null && roomTypes.size() > 0) {
				for (String r : roomTypes) {
					JSONObject rJson = new JSONObject();
					rJson.put("roomTypes", r);
					rateCodeArr.add(rJson);
				}
			}
			ajaxResponse(rateCodeArr.toString());
		}
	}
	
	// 初始化查询条件
	private void setInitCondition() {
		
		hotelList = getCurLoginUser().getHotelVOs();
	    channelList = getCurLoginUser().getChannels();
		getRequest().setAttribute("channelList",channelList);
		getRequest().setAttribute("hotelList", hotelList);

		// 设置初始化标识
		getRequest().setAttribute("isInit", "1");
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public UsageCriteria getUc() {
		return uc;
	}

	public void setUc(UsageCriteria uc) {
		this.uc = uc;
	}

	public UsageResult getUsageResult() {
		return usageResult;
	}

	public void setUsageResult(UsageResult usageResult) {
		this.usageResult = usageResult;
	}

	public UsageManager getUsageManager() {
		return usageManager;
	}

	public void setUsageManager(UsageManager usageManager) {
		this.usageManager = usageManager;
	}

	public ChannelManager getChannelManager() {
		return channelManager;
	}

	public void setChannelManager(ChannelManager channelManager) {
		this.channelManager = channelManager;
	}

	public HotelManager getHotelManager() {
		return hotelManager;
	}

	public void setHotelManager(HotelManager hotelManager) {
		this.hotelManager = hotelManager;
	}

	public HotelMCManager getHotelMCManager() {
		return hotelMCManager;
	}

	public void setHotelMCManager(HotelMCManager hotelMCManager) {
		this.hotelMCManager = hotelMCManager;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	public RoomTypeManager getRoomTypeManager() {
		return roomTypeManager;
	}

	public void setRoomTypeManager(RoomTypeManager roomTypeManager) {
		this.roomTypeManager = roomTypeManager;
	}

	public List<HotelVO> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<HotelVO> hotelList) {
		this.hotelList = hotelList;
	}

	public List<String> getHotelIdList() {
		return hotelIdList;
	}

	public void setHotelIdList(List<String> hotelIdList) {
		this.hotelIdList = hotelIdList;
	}

	public List<String> getChannelIdList() {
		return channelIdList;
	}

	public void setChannelIdList(List<String> channelIdList) {
		this.channelIdList = channelIdList;
	}

	public List<String> getRoomTypeList() {
		return roomTypeList;
	}

	public void setRoomTypeList(List<String> roomTypeList) {
		this.roomTypeList = roomTypeList;
	}

}
