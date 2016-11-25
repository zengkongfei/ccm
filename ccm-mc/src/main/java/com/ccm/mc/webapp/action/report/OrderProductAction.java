package com.ccm.mc.webapp.action.report;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.log.SendMsgLogDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.enume.ActionEnum;
import com.ccm.api.model.enume.OXIStatusEnum;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.log.vo.UpDownPmsLogCriteria;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.order.vo.MasterVO;
import com.ccm.api.model.order.vo.OrderSearchResult;
import com.ccm.api.model.order.vo.SearchOrderCriteria;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.order.OrderManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.action.order.OrderAction;
import com.ccm.mc.webapp.util.ExportUtil;

public class OrderProductAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4064336455249822517L;

	private Log log = LogFactory.getLog(OrderAction.class);

	// 查询条件
	private SearchOrderCriteria soc = new SearchOrderCriteria();

	// 返回结果
	private OrderSearchResult orderSearchResult = new OrderSearchResult();

	@Autowired
	private OrderManager orderManager;
	@Resource
	private HotelManager hotelManager;
	@Resource
	private ChannelManager channelManager;
	@Resource
	private ChainManager chainManager;
	@Resource
	private HotelMCManager hotelMCManager;
	@Resource
	private RatePlanManager ratePlanManager;
	@Resource
	private RoomTypeManager roomTypeManager;
	@Resource
	private SendMsgLogDao sendMsgLogDao;
	@Resource
	private HotelDao hotelDao;
	
	// 渠道列表
	List<Channel> channelList;
	// 酒店列表
	private List<Hotel> hotelList;
	// 订单状态
	private Map<String, String> orderStatus;
	// 集团列表
	private List<Chain> chainList;
	//市场列表
	private List<String> marketList;

	/**
	 * 预订产量详情报表 - 初始化
	 * 
	 * @return
	 */
	public String detailList() {

		this.setInitCondition();
		getRequest().setAttribute("downPmsMsgStatus", OXIStatusEnum.getOrderProductDownPmsMsgStatusMap());
		//消息类型
		getRequest().setAttribute("actionList", ActionEnum.getActionListMap());
		return "detailList";
	}

	/**
	 * 预订产量详情报表 - 查询
	 * 
	 * @return
	 */
	public String detailQuery() {	
		
		// 分页
		int pageSize = this.getCurrentPageSize("orderProductDetail");
		int pageNo = this.getCurrentPageNo("orderProductDetail");
		soc.setPageNum(pageNo);
		soc.setPageSize(pageSize);
		
		// 设置查询参数
		soc.setExcelFlag(Boolean.FALSE);
		soc.setNeedPage(Boolean.TRUE);

		Date checkinEnd = soc.getCheckinEnd();
		if (soc.getCheckinEnd() != null) {
			soc.setCheckinEnd(DateUtil.addSecond(checkinEnd, -1));
		}

		Date checkoutEnd = soc.getCheckoutEnd();
		if (soc.getCheckoutEnd() != null) {
			soc.setCheckoutEnd(DateUtil.addSecond(checkoutEnd, -1));
		}

		Date createEnd = soc.getCreateEnd();
		if (soc.getCreateEnd() != null) {
			soc.setCreateEnd(DateUtil.addSecond(createEnd, -1));
		}
		//查询分表
		if((soc.getActionList()!=null || soc.getDownPmsMsgStatusList()!=null)&&(soc.getHotelIdList().size()<=10))
		{
			List<String> hotelCodes = hotelDao.getHotelCodesByHotelIds(soc.getHotelIdList());
			
			UpDownPmsLogCriteria upDownPmsLogCriteria=new UpDownPmsLogCriteria();
			upDownPmsLogCriteria.setHotelCodeList(hotelCodes);
			upDownPmsLogCriteria.setOrderId(soc.getOrderNo());
			upDownPmsLogCriteria.setMessageType(MessageType.RESERVATION);
			upDownPmsLogCriteria.setActions(soc.getActionList());
			upDownPmsLogCriteria.setDownPmsMsgStatusList(soc.getDownPmsMsgStatusList());
				
			UpDownPmsLogResult upDownPmsLogResult=sendMsgLogDao.searchListNoCount(upDownPmsLogCriteria);
			List<SendMsgLog> sendMsgLogList=upDownPmsLogResult.getResultList();
			
			if(sendMsgLogList!=null&&sendMsgLogList.size()>0)
			{
				List<String> orderNos=new ArrayList<String>(); 
				for (SendMsgLog sendMsgLog : sendMsgLogList) 
				{
					if(!orderNos.contains(sendMsgLog.getOrderId())){
						orderNos.add(sendMsgLog.getOrderId());				
					}
				}	
				
				soc.setOrderNos(orderNos);
				soc.setNeedPage(false);
				orderSearchResult = orderManager.searchOrder(soc);
		
				List<Master> masterList=orderSearchResult.getResultList();
				List<Master> masterListResult=new ArrayList<Master>();
				
				for (Master master : masterList) 
				{			
					for (SendMsgLog sendMsgLog : sendMsgLogList) 
					{
						if(master.getMasterId().equals(sendMsgLog.getOrderId()))
						{
							Master masterTemp=new Master();
							
							BeanUtils.copyProperties(master, masterTemp);
							masterTemp.setAction(sendMsgLog.getAction());
							masterTemp.setDownPmsMsgStatus(sendMsgLog.getStatus());
							masterListResult.add(masterTemp);
						}
					}
					
				}
				
				orderSearchResult.setTotalCount(masterListResult.size());
				//分页
				/*
				//如果大于20条则进行分页
				if(masterListResult.size()>20){
					
					int pageNoForList = this.getCurrentPageNo("orderProductDetail");
					int pageSizeForList = this.getCurrentPageSize("orderProductDetail");
					int skipResults =( pageNoForList - 1) * pageSizeForList;
					masterListResult.subList(skipResults, skipResults+pageSizeForList);
				}
				*/
				
				//根据创建时间排序
				Collections.sort(masterListResult,new Comparator<Master>() {
					 /**
				     * 如果m1小于m2,返回一个负数;如果m1大于m2，返回一个正数;如果他们相等，则返回0;
				     */
				    @Override
				    public int compare(Master m1, Master m2) {   
				        //降序排序
				        if(m1.getChanged().before(m2.getChanged())) 
				        	return 1;
				        return -1;
				    }
				});
				
				
				orderSearchResult.setResultList(masterListResult);
				
			}
			
		}
		else
		{
			orderSearchResult = orderManager.searchOrder(soc);
		}
		
		// 设置首日价
		if (orderSearchResult.getResultList() != null && orderSearchResult.getResultList().size() > 0) {
			// 循环设置首日价
			for (Master master : orderSearchResult.getResultList()) {
				BigDecimal firstDateRate = this.getFirstDateRate(master.getMasterId());
				BigDecimal charge=master.getCharge();
				BigDecimal deduct=master.getDeduct();
				if (firstDateRate != null) {
					master.setFirstDateRate(firstDateRate.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if(charge!=null){
					master.setCharge(charge.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if(deduct!=null){
					master.setDeduct(deduct.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}
		}

		this.setQueryCondition();

		soc.setCheckinEnd(checkinEnd);
		soc.setCheckoutEnd(checkoutEnd);
		soc.setCreateEnd(createEnd);
		
		getRequest().setAttribute("downPmsMsgStatus", OXIStatusEnum.getOrderProductDownPmsMsgStatusMap());
		//消息类型
		getRequest().setAttribute("actionList", ActionEnum.getActionListMap());

		return "detailList";
	}

	/**
	 * 预订产量汇总报表 - 初始化
	 * 
	 * @return
	 */
	public String collectList() {
		this.setInitCondition();
		return "collectList";
	}

	/**
	 * 预订产量汇总报表 - 查询
	 * 
	 * @return
	 */
	public String collectQuery() {

		// 分页
		int pageSize = this.getCurrentPageSize("orderProductCollect");
		int pageNo = this.getCurrentPageNo("orderProductCollect");
		soc.setPageNum(pageNo);
		soc.setPageSize(pageSize);

		// 设置查询参数
		soc.setExcelFlag(Boolean.FALSE);
		soc.setNeedPage(Boolean.TRUE);

		Date checkinEnd = soc.getCheckinEnd();
		if (soc.getCheckinEnd() != null) {
			soc.setCheckinEnd(DateUtil.addSecond(checkinEnd, -1));
		}

		Date checkoutEnd = soc.getCheckoutEnd();
		if (soc.getCheckoutEnd() != null) {
			soc.setCheckoutEnd(DateUtil.addSecond(checkoutEnd, -1));
		}

		Date createEnd = soc.getCreateEnd();
		if (soc.getCreateEnd() != null) {
			soc.setCreateEnd(DateUtil.addSecond(createEnd, -1));
		}

		Date lastModifyEnd = soc.getLastModifyEnd();
		if (soc.getLastModifyEnd() != null) {
			soc.setLastModifyEnd(DateUtil.addSecond(lastModifyEnd, -1));
		}

		orderSearchResult = orderManager.searchSummaryOrders(soc);

		// numberOfUnits 房间总数

		this.setQueryCondition();

		soc.setCheckinEnd(checkinEnd);
		soc.setCheckoutEnd(checkoutEnd);
		soc.setCreateEnd(createEnd);
		soc.setLastModifyEnd(lastModifyEnd);

		return "collectList";
	}

	/**
	 * 预订产量详情报表 - 导出
	 * 
	 * @return
	 * @throws IOException
	 */
	public String detailExport() throws IOException {
		try {

			// 设置导出参数
			soc.setExcelFlag(Boolean.FALSE);
			soc.setNeedPage(Boolean.FALSE);
			
			
			Date checkinEnd = soc.getCheckinEnd();
			if (soc.getCheckinEnd() != null) {
				soc.setCheckinEnd(DateUtil.addSecond(checkinEnd, -1));
			}

			Date checkoutEnd = soc.getCheckoutEnd();
			if (soc.getCheckoutEnd() != null) {
				soc.setCheckoutEnd(DateUtil.addSecond(checkoutEnd, -1));
			}

			Date createEnd = soc.getCreateEnd();
			if (soc.getCreateEnd() != null) {
				soc.setCreateEnd(DateUtil.addSecond(createEnd, -1));
			}

			//查询分表
			if((soc.getActionList()!=null || soc.getDownPmsMsgStatusList()!=null)&&(soc.getHotelIdList().size()<=10))
			{
				List<String> hotelCodes = hotelDao.getHotelCodesByHotelIds(soc.getHotelIdList());
				
				UpDownPmsLogCriteria upDownPmsLogCriteria=new UpDownPmsLogCriteria();
				upDownPmsLogCriteria.setHotelCodeList(hotelCodes);
				upDownPmsLogCriteria.setOrderId(soc.getOrderNo());
				upDownPmsLogCriteria.setMessageType(MessageType.RESERVATION);
				upDownPmsLogCriteria.setActions(soc.getActionList());
				upDownPmsLogCriteria.setDownPmsMsgStatusList(soc.getDownPmsMsgStatusList());
					
				UpDownPmsLogResult upDownPmsLogResult=sendMsgLogDao.searchListNoCount(upDownPmsLogCriteria);
				List<SendMsgLog> sendMsgLogList=upDownPmsLogResult.getResultList();

				if(sendMsgLogList!=null&&sendMsgLogList.size()>0)
				{
					List<String> orderNos=new ArrayList<String>(); 
					for (SendMsgLog sendMsgLog : sendMsgLogList) 
					{
						if(!orderNos.contains(sendMsgLog.getOrderId())){
							orderNos.add(sendMsgLog.getOrderId());				
						}
					}	
					
					soc.setOrderNos(orderNos);
					soc.setNeedPage(false);
					orderSearchResult = orderManager.searchOrder(soc);
			
					List<Master> masterList=orderSearchResult.getResultList();
					List<Master> masterListResult=new ArrayList<Master>();
					
					for (Master master : masterList) 
					{			
						for (SendMsgLog sendMsgLog : sendMsgLogList) 
						{
							if(master.getMasterId().equals(sendMsgLog.getOrderId()))
							{
								Master masterTemp=new Master();
								
								BeanUtils.copyProperties(master, masterTemp);
								masterTemp.setAction(sendMsgLog.getAction());
								masterTemp.setDownPmsMsgStatus(sendMsgLog.getStatus());
								masterListResult.add(masterTemp);
							}
						}
						
					}
					
					//根据创建时间排序
					Collections.sort(masterListResult,new Comparator<Master>() {
						 /**
					     * 如果m1小于m2,返回一个负数;如果m1大于m2，返回一个正数;如果他们相等，则返回0;
					     */
					    @Override
					    public int compare(Master m1, Master m2) {   
					        //降序排序
					        if(m1.getChanged().before(m2.getChanged())) 
					        	return 1;
					        return -1;
					    }
					});
					orderSearchResult.setResultList(masterListResult);
				}
			}
			else
			{
				orderSearchResult = orderManager.searchOrder(soc);
			}
			
			// 设置首日价等
			if (orderSearchResult.getResultList() != null && orderSearchResult.getResultList().size() > 0) {
				// 循环设置首日价
				for (Master master : orderSearchResult.getResultList()) {
					BigDecimal firstDateRate = this.getFirstDateRate(master.getMasterId());
					BigDecimal charge=master.getCharge();
					BigDecimal deduct=master.getDeduct();
					if (firstDateRate != null) {
						master.setFirstDateRate(firstDateRate.setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					if(charge!=null){
						master.setCharge(charge.setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					if(deduct!=null){
						master.setDeduct(deduct.setScale(2, BigDecimal.ROUND_HALF_UP));
					}
				}
			}

			// excel表头
			String[] colName = {getText("ccm.Channel.ChannelCode"),
					getText("ccm.ReservationMonitorReport.PropertyCode"), 
					getText("ccm.report.MarketCode"),
					getText("ccm.ReservationsManagment.CRSNo"),
					//渠道订单号
					getText("ccm.Channel.ChannelReservationNumber"),
					
					getText("ccm.report.PMSAltID"),
					getText("ccm.ReservationsManagment.EnglishSurname"),
					getText("ccm.ReservationsManagment.EnglishName"),
					getText("ccm.ReservationsManagment.ChineseName"),
					getText("ccm.ReservationsManagment.ArrivalDate"),
					getText("ccm.ReservationsManagment.DepartureDate"),
					getText("ccm.ReservationsManagment.ReservationCreatedDate"),
					getText("ccm.ReservationUpdateDate"),
					getText("ccm.report.CacellationDate"),
					getText("ccm.ReservationProductionDetailReport.Nights"),
					getText("ccm.ReservationProductionDetailReport.Rooms"),
					getText("ccm.RestrictionsManagement.RateCode"),
					getText("ccm.Channel.RoomTypeCode"),
					getText("ccm.ReservationProductionDetailReport.1stDayRoomRate"),			
					getText("ccm.ReservationProductionDetailReport.DeductedCXLFee"),
					getText("ccm.ReservationProductionDetailReport.TotalRoomFee"),
					getText("ccm.GuaranteeRules.GuaranteeType"),
					getText("ccm.Channel.MessageType"),
					getText("ccm.ReservationMonitorReport.ReservationStatus"),
					getText("ccm.ReservationDownloadMessageStatus"),
					getText("ccm.reports.CREATEBY")};
			
			// excel要导出的数据
			List<String[]> dataList = this.getExcelDataByMaseterList(orderSearchResult.getResultList());
			SXSSFWorkbook workbook = ExportUtil.createExcel2("orderProductDetailList", colName, dataList);

			// 生成excel文件名
			String excelName = ExportUtil.createFileName(getText("ccm.ReservationProductionDetailReport"));
			// 设置导出的excel文件从页面中下载
			getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");
			// getResponse().setContentType("application/vnd.ms-excel;charset=gb2312");

			// 输出流
			OutputStream os = getResponse().getOutputStream();

			workbook.write(os);
			os.flush();
			os.close();

			soc.setCheckinEnd(checkinEnd);
			soc.setCheckoutEnd(checkoutEnd);
			soc.setCreateEnd(createEnd);

		} catch (Exception e) {
			getResponse().getWriter().print(CommonUtil.getExceptionMsg(e, "export fail"));
			getResponse().getWriter().flush();
		}
		return null;
	}

	/**
	 * 预订产量汇总报表 - 导出
	 * 
	 * @return
	 * @throws IOException
	 */
	public String collectExport() throws IOException {
		// 设置导出参数
		soc.setExcelFlag(Boolean.FALSE);
		soc.setNeedPage(Boolean.FALSE);
		Date checkinEnd = soc.getCheckinEnd();
		if (soc.getCheckinEnd() != null) {
			soc.setCheckinEnd(DateUtil.addSecond(checkinEnd, -1));
		}

		Date checkoutEnd = soc.getCheckoutEnd();
		if (soc.getCheckoutEnd() != null) {
			soc.setCheckoutEnd(DateUtil.addSecond(checkoutEnd, -1));
		}

		Date createEnd = soc.getCreateEnd();
		if (soc.getCreateEnd() != null) {
			soc.setCreateEnd(DateUtil.addSecond(createEnd, -1));
		}

		Date lastModifyEnd = soc.getLastModifyEnd();
		if (soc.getLastModifyEnd() != null) {
			soc.setLastModifyEnd(DateUtil.addSecond(lastModifyEnd, -1));
		}

		try {
			// 查询数据
			orderSearchResult = orderManager.searchSummaryOrders(soc);

			// excel表头
			String[] colName = { getText("ccm.Channel.ChannelCode"), getText("ccm.ReservationMonitorReport.ReservationStatus"), getText("ccm.ReservationMonitorReport.PropertyCode"), getText("com.ReservationMonitorReport.TotalNumberOfReservations"), getText("ccm.ReservationProductionSummaryReport.TotalNoofRooms"), getText("ccm.ReservationProductionSummaryReport.TotalNoofNights"), getText("com.ReservationMonitorReport.TotalAmount"), getText("com.ReservationMonitorReport.AverageRates"), getText("com.ReservationMonitorReport.LengthOfStay"), };

			// excel要导出的数据
			List<String[]> dataList = this.getExcelDataByObjList(orderSearchResult.getMasterVOList());

			SXSSFWorkbook workbook = ExportUtil.createExcel2("orderProductCollectList", colName, dataList);

			// 生成excel文件名
			String excelName = ExportUtil.createFileName(getText("Reservation Production Summary Report"));
			// 设置导出的excel文件从页面中下载
			getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");
			// getResponse().setContentType("application/vnd.ms-excel;charset=gb2312");

			// 输出流
			OutputStream os = getResponse().getOutputStream();

			workbook.write(os);
			os.flush();
			os.close();

			soc.setCheckinEnd(checkinEnd);
			soc.setCheckoutEnd(checkoutEnd);
			soc.setCreateEnd(createEnd);
			soc.setLastModifyEnd(lastModifyEnd);
		} catch (Exception e) {
			getResponse().getWriter().print(CommonUtil.getExceptionMsg(e, "export fail"));
			getResponse().getWriter().flush();
		}

		return null;
	}

	private void setInitCondition() {
		// 初始化查询条件
		chainList = chainManager.getAllChain();
		
		//渠道用户渠道权限
		B2BUser user = getCurLoginUser();
		if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
			channelList=user.getChannels();
		}else{
			channelList = channelManager.getAll();
		}
				
		orderStatus = this.getStatusMap();
		// 设置初始化标识
		getRequest().setAttribute("isInit", "1");
	}

	private void setQueryCondition() {
		// 初始化查询条件
		chainList = chainManager.getAllChain();
		
		//channelList = channelManager.getAll();
		//渠道用户渠道权限
		B2BUser user = getCurLoginUser();
		if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
			channelList=user.getChannels();
		}else{
			channelList = channelManager.getAll();
		}
		
		orderStatus = this.getStatusMap();
		// 如果存在选中集团的情况
		if (soc.getChainCodeList() != null && soc.getChainCodeList().size() > 0) {
			hotelList = new ArrayList<Hotel>();
			for (String chainCode : soc.getChainCodeList()) {
				// 如果是运营人员
				if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
					hotelList.addAll(hotelManager.getHotelByChainCode(chainCode));
				} else {
					hotelList.addAll(hotelMCManager.getUserHotelByChainCodeAndUserId(chainCode, user.getUserId()));
				}
			}
		}
	}

	/**
	 * ajax获取酒店列表
	 */
	public void ajaxGetHotels() {
		String chainCodes = getRequest().getParameter("chainCodes");
		if (StringUtils.isNotBlank(chainCodes)) {
			String[] chainArr = chainCodes.split(",");
			List<Hotel> hotels = new ArrayList<Hotel>();
			B2BUser user = getCurLoginUser();

			for (String chainCode : chainArr) {
				// 如果是运营人员
				if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
					hotels.addAll(hotelManager.getHotelByChainCode(chainCode));
				} else {
					hotels.addAll(hotelMCManager.getUserHotelByChainCodeAndUserId(chainCode, user.getUserId()));
				}
			}
			
			JSONArray hotelArr = new JSONArray();
			if (hotels != null && hotels.size() > 0) {
				for (Hotel hotel : hotels) {
					JSONObject hotelJson = new JSONObject();
					hotelJson.put("hotelCode", hotel.getHotelCode());
					hotelJson.put("hotelId", hotel.getHotelId());
					hotelArr.add(hotelJson);
				}
			}
			ajaxResponse(hotelArr.toString());
		
		}
	}

	/**
	 * ajax获取市场列表
	 */
	public void ajaxGetMarkets() {
		String hotelIds = getRequest().getParameter("hotelIds");

		if (StringUtils.isNotBlank(hotelIds)) 
		{
			//根据酒店代码可以查到其对应的所有房价代码，然后每个房价对应一个市场代码，就可以获取所有的市场代码
			String[] hotelArr = hotelIds.split(",");
			List<String> hotelIdList=new ArrayList<String>();
			Collections.addAll(hotelIdList, hotelArr);
	
			List<String> market=new ArrayList<String>();
			List<Rateplan> rates=ratePlanManager.getRatePlanByHotelIdList(hotelIdList);
			for (Rateplan rateplan : rates) {
				market.add(rateplan.getMarketCode());
			}
			/*
			for (String h : hotelArr) {
				List<Rateplan> rates=ratePlanManager.getRatePlanByHotelId(h);
				for (Rateplan rateplan : rates) {
					market.add(rateplan.getMarketCode());
				}
			}
			*/
			
			//去掉重复的市场代码
			HashSet<String> mSets = new HashSet<String>(market); 
			market.clear(); 
			market.addAll(mSets);
			market.remove(null);
			
			JSONArray marketArr = new JSONArray();
			if (market != null && market.size() > 0) {
				for (String m : market) {
					JSONObject mJson = new JSONObject();
					mJson.put("market", m);
					marketArr.add(mJson);
				}
			}
			ajaxResponse(marketArr.toString());
			
		}
	}
	/**
	 * ajax获取房价代码
	 */
	public void ajaxGetRatePlan() {
		String hotelIds = getRequest().getParameter("hotelIds");
		if (StringUtils.isNotBlank(hotelIds)) 
		{
			//根据酒店代码可以查到其对应的所有房价代码
			String[] hotelArr = hotelIds.split(",");
			List<String> hotelIdList=new ArrayList<String>();
			Collections.addAll(hotelIdList, hotelArr);
			
			List<String> rateCode=new ArrayList<String>();
			
			List<Rateplan> rates=ratePlanManager.getRatePlanByHotelIdList(hotelIdList);
			for (Rateplan rateplan : rates) {
				rateCode.add(rateplan.getRatePlanCode());
			}
			/*
			for (String h : hotelArr) {
				//market.addAll(orderManager.getMarketsByHotelId(h));
				List<Rateplan> rates=ratePlanManager.getRatePlanByHotelId(h);
				for (Rateplan rateplan : rates) {
					rateCode.add(rateplan.getRatePlanCode());
				}
			}
			*/
			//去掉重复的房价代码
			HashSet<String> mSets = new HashSet<String>(rateCode); 
			rateCode.clear(); 
			rateCode.addAll(mSets);
			rateCode.remove(null);
			this.getRequest().setAttribute("rateCode",rateCode);
			JSONArray rateCodeArr = new JSONArray();
			if (rateCode != null && rateCode.size() > 0) {
				for (String r : rateCode) {
					JSONObject rJson = new JSONObject();
					rJson.put("rateCode", r);
					rateCodeArr.add(rJson);
				}
			}
			ajaxResponse(rateCodeArr.toString());	
		}
	}
	
	/**
	 * ajax获取房型代码
	 */
	public void ajaxGetRoomType() {
		String hotelIds = getRequest().getParameter("hotelIds");
		if (StringUtils.isNotBlank(hotelIds)) 
		{
			String[] hotelArr = hotelIds.split(",");
			List<String> hotelIdList=new ArrayList<String>();
			Collections.addAll(hotelIdList, hotelArr);
			
			List<String> roomTypes=new ArrayList<String>();
			List<RoomType> types=roomTypeManager.getRoomTypeByHotelIdList(hotelIdList);
			for (RoomType roomType : types) {
				roomTypes.add(roomType.getRoomTypeCode());
			}
			/*
			for (String h : hotelArr) {
				List<RoomType> types=roomTypeManager.getRoomTypeByHotelId(h);
				for (RoomType roomType : types) {
					roomTypes.add(roomType.getRoomTypeCode());
				}
			}
			*/
			//去掉重复的
			HashSet<String> mSets = new HashSet<String>(roomTypes); 
			roomTypes.clear(); 
			roomTypes.addAll(mSets);
			roomTypes.remove(null);
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
	
	private List<String[]> getExcelDataByMaseterList(List<Master> masterList) {
		List<String[]> dataList = new ArrayList<String[]>();

		// 如果订单结果不为空
		if (masterList != null && masterList.size() > 0) {
			for (Master master : masterList) {

				String[] arr = new String[26];
				arr[0] = master.getChannel();
				arr[1] = master.getHotelCode();
				arr[2] = master.getMarket();
				arr[3] = master.getMasterId();
				//渠道订单号
				arr[4] = master.getCrsno();
				
				arr[5] = master.getPmsId();
				arr[6] = master.getName2();
				arr[7] = master.getName();
				arr[8] = master.getName4();
				arr[9] = DateUtil.convertDateToString(master.getArr());
				arr[10] = DateUtil.convertDateToString(master.getDep());
				arr[11] = DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", master.getChanged());
				arr[12] =DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", master.getLastModifyTime());//预定更新日期
				arr[13] =DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", master.getCancelTime());//订单取消日期
				arr[14] = master.getDays() + "";
				arr[15]= master.getNumberOfUnits() + "";
				arr[16] = master.getRatePlanCode();
				arr[17] = master.getType();
				
				// 首日价
				arr[18]  = "";
				if (master.getFirstDateRate() != null) {
					arr[18]  = master.getFirstDateRate().setScale(2, BigDecimal.ROUND_HALF_UP) + "";
				}
				
				arr[19]  = "";
				if (master.getDeduct() != null) {
					arr[19]  = master.getDeduct().setScale(2, BigDecimal.ROUND_HALF_UP) + "";
				}
				
				arr[20]  = "";
				if (master.getCharge() != null) {
					arr[20]  = master.getCharge().setScale(2, BigDecimal.ROUND_HALF_UP) + "";
				}
				
				arr[21] = master.getPayment();
				//消息类型
				arr[22] = master.getAction();
				arr[23] = master.getSta();
				arr[24] = master.getDownPmsMsgStatus();
				arr[25] = master.getCreateBY();
				dataList.add(arr);
			}
		}
		return dataList;
	}

	private List<String[]> getExcelDataByObjList(List<MasterVO> masterList) {
		List<String[]> dataList = new ArrayList<String[]>();

		// 如果订单结果不为空 String[] colName = { "渠道代码", "预订状态", "酒店代码","订单总数","房间总数", "间夜总数","总收入","平均房价","平均入住时长" };
		if (masterList != null && masterList.size() > 0) {
			for (MasterVO master : masterList) {
				String[] arr = new String[14];
				arr[0] = master.getChannel();
				arr[1] = master.getSta();
				arr[2] = master.getHotelCode();
				arr[3] = master.getNumberOfOrders() + "";
				arr[4] = master.getNumberOfUnits() + "";
				arr[5] = master.getSumArrDays() + "";
				arr[6] = master.getTotalCharge() + "";
				arr[7] = master.getAvgCharge() + "";
				arr[8] = ((master.getAvgArrDays() == null) ? 0 : (master.getAvgArrDays())) + "";
				dataList.add(arr);
			}
		}
		return dataList;
	}

	/**
	 * 获取首日价
	 * 
	 * @return
	 */
	private BigDecimal getFirstDateRate(String masterId) {
		// 设置价格列表
		List<MasterRate> mrList = orderManager.getMasterRateByOrderNo(masterId);
		if (mrList != null && mrList.size() > 0) {
			BigDecimal totalRate = new BigDecimal(0.00);
			// 基础房价
			if (mrList.get(0).getSetrate() != null) {
				totalRate = totalRate.add(mrList.get(0).getSetrate());
			}
			// 当日包价
			if (mrList.get(0).getPackages() != null) {
				totalRate = totalRate.add(mrList.get(0).getPackages());
			}
			return totalRate;
		}
		return null;
	}

	private Map<String, String> getStatusMap() {
		return OrderStatus.getOrderProductStatusMap();
	}

	public SearchOrderCriteria getSoc() {
		return soc;
	}

	public void setSoc(SearchOrderCriteria soc) {
		this.soc = soc;
	}

	public OrderSearchResult getOrderSearchResult() {
		return orderSearchResult;
	}

	public void setOrderSearchResult(OrderSearchResult orderSearchResult) {
		this.orderSearchResult = orderSearchResult;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	public List<Hotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	public Map<String, String> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Map<String, String> orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<Chain> getChainList() {
		return chainList;
	}

	public void setChainList(List<Chain> chainList) {
		this.chainList = chainList;
	}

	public List<String> getMarketList() {
		return marketList;
	}

	public void setMarketList(List<String> marketList) {
		this.marketList = marketList;
	}

}
