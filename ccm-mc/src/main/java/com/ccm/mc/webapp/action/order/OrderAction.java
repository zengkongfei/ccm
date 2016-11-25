package com.ccm.mc.webapp.action.order;


import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.SecurityHolder;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterChangesLog;
import com.ccm.api.model.order.MasterOrder;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.order.vo.OrderSearchResult;
import com.ccm.api.model.order.vo.SearchOrderCriteria;
import com.ccm.api.model.user.AuthRole;
import com.ccm.api.model.user.AuthUser;
import com.ccm.api.model.user.Authority;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.common.RemindManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.hotel.HotelSwitchManager;
import com.ccm.api.service.order.OrderChangesLogManager;
import com.ccm.api.service.order.OrderManager;
import com.ccm.api.service.order.ReservationService;
import com.ccm.api.service.system.PushManage;
import com.ccm.api.service.user.AuthManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;

/**
 * @author Jenny
 * 
 */
public class OrderAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2153377910584706563L;

	private Log log = LogFactory.getLog(OrderAction.class);

	private String orderNo;
	private Master order;
	private List<Channel> channelList=null;
	private String companyId;
	// 查询条件
	private SearchOrderCriteria soc = new SearchOrderCriteria();

	// 返回结果
	private OrderSearchResult orderSearchResult = new OrderSearchResult();
	private List<MasterRate> priceDetail;

	// 订单状态
	private Map<String, String> orderStatus;
	
	private HashMap<String,Object> configMap = new HashMap<String, Object>();

	@Autowired
	private OrderManager orderManager;
	@Resource
	private HotelManager hotelManager;
	@Resource
	private HotelMCManager hotelMCManager;
	@Resource
	private RemindManager remindManager;
	@Resource
	private ReservationService reservationService;
	
	@Autowired
	private HotelSwitchManager hotelSwitchManager;
	@Resource
	private OrderChangesLogManager orderChangesLogManager;
	
	@Autowired
	private PushManage pushManage;
	
	@Resource
	private DictCodeManager dictCodeManager;
	
	private String status;
	//状态
	private List<String> restypeList;
	
	//功能权限
	@Resource
	private AuthManager authManager;
	private boolean orderAuth=false;
	

	//为现有所有运营和酒店用户增加订单编辑权限
	/*
	public String updateAuth(){
			log.info("updateAuth Begin");
			
			Authority authorityOrder=new Authority();
			authorityOrder.setId("orderauth97");
			authorityOrder.setAuthNameEN("Reservation Edit");
			authorityOrder.setAuthNameCN("订单编辑");
			authorityOrder.setMenu("订单");
			authManager.save(authorityOrder);
			
			log.info("save Authority over!");
			
			
			AuthRole authRoleOrder=new AuthRole();
			authRoleOrder.setId("55e62c4b460a4c4993021d6b4f474045");
			List<String> authIdsOrder=new ArrayList<>();
			authIdsOrder.add("orderauth97");
			authRoleOrder.setAuthIds(authIdsOrder);
			authManager.saveAuthRole(authRoleOrder);
			
			log.info("save AuthRole over!");
			
			
			List<B2BUser>  userList=userManager.getAll();
			for (B2BUser b2bUser : userList) {
				if(CompanyType.PLAT_COMPANY_ID.equals(b2bUser.getCompanyId())
						||CompanyType.HOTEL.equals(b2bUser.getCompanyId())){
					AuthUser authUserOrder=new AuthUser();
					authUserOrder.setId(b2bUser.getUserId());
					List<String> roleIds=new ArrayList<>();
					roleIds.add("55e62c4b460a4c4993021d6b4f474045");
					authUserOrder.setRoleIds(roleIds);
					List<String> authIds=new ArrayList<>();
					authIds.add("orderauth97");
					authUserOrder.setAuthIds(authIds);
					authManager.saveAuthUser(authUserOrder);
				}
			}
			
			log.info("save AuthUser over!");
			
			log.info("updateAuth end");
			
			return "authover";
			
	}
	*/
	private void init(){
		
		B2BUser user= getCurLoginUser();
		if(null!=user&&null!=user.getUserId()){
			List<String> orderAuthList=authManager.getUserAuth(user.getUserId());
			for (String string : orderAuthList) {
				if(string.equalsIgnoreCase("orderauth97")
						||"1".equals(user.getUserId())||"2".equals(user.getUserId())){
					orderAuth=true;
				}
			}
		}
		
		//状态
		restypeList=new ArrayList<String>();
		restypeList.add("RESERVED");
		//restypeList.add("MODIFTY");
		restypeList.add("CHECKIN");
		restypeList.add("CHECKOUT");
		restypeList.add("NOSHOW");
		restypeList.add("CANCEL");
		this.getRequest().setAttribute("restypeList", restypeList);
	}
	/**
	 * 订单列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list() {
		try {
			B2BUser user = getCurLoginUser();
			companyId=user.getCompanyId();
			channelList=user.getChannels();
			// 列出当前用户选择的酒店
			if (user.getHotelvo() == null || !StringUtils.hasText(user.getHotelvo().getHotelId())) {
				log.info("noSelectHotel");
				return "list";
			}
			String hotelId = user.getHotelvo().getHotelId();

			// 分页
			int pageSize = this.getCurrentPageSize("order");
			int pageNo = this.getCurrentPageNo("order");
			soc.setPageNum(pageNo);
			soc.setPageSize(pageSize);

			// 是从订单提醒过来的则更新状态
			if (StringUtils.hasLength(from)) {
				List<String> masterIds = (List<String>) getSession().getAttribute(hotelId + from);
				if (masterIds != null) {
					soc.setMasterIdList(masterIds);
					soc.setHotelId(hotelId);
				}
				remindManager.updateHaveSeen(hotelId, from);
			}

			if (StringUtils.hasText(soc.getHotelId())) {
				orderSearchResult = orderManager.searchOrder(soc);
			}
			// 进入订单页面，默认查询条件为空，无需显示任何内容
			soc.setHotelId(hotelId);
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		this.getRequest().setAttribute("currentTime", DateUtil.getCleanDate(new Date()));
		this.getRequest().setAttribute("resultSize", orderSearchResult.getTotalCount());
		return "list";

	}
	
	private List<String[]> getExcelDataByObjList(List<Master> orderList) {
		List<String[]> dataList = new ArrayList<String[]>();
		
		if (orderList != null && orderList.size() > 0) {
			for (Master master : orderList) {
				String[] arr = new String[11];
				// arr[] = master.get
				arr[0] = master.getMasterId();
				
				arr[1] = (null==master.getName2()?"":master.getName2())
						+(null==master.getName()?"":master.getName())
						+ (null==master.getName4()?"":master.getName4());
				
				arr[2] = DateUtil.getDate(master.getArr());
				arr[3] = DateUtil.getDate(master.getDep());
				arr[4] = DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", master.getChanged());
				arr[5] = DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", master.getCancelTime());
				arr[6] = master.getRestype();
				arr[7] = master.getRatePlanCode();
				arr[8] = master.getType();
				arr[9] = master.getQualifyingIdType().equals("TRAVEL_AGENT")?master.getQualifyingIdValue():"";
				arr[10] = master.getQualifyingIdType().equals("CORPORATE")?master.getQualifyingIdValue():"";
				
				dataList.add(arr);
			}
		}
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	public String export() throws IOException {
		//渠道用户渠道权限
		B2BUser user = getCurLoginUser();
		List<Channel> channels=null;
		List<String> channelCodeList=new ArrayList<String>();
		Boolean needChannel=false;
		if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
			needChannel=true;
			channels=user.getChannels();
			for (Channel c : channels) {
				channelCodeList.add(c.getChannelCode());
			}
		}
		// 设置导出参数
		soc.setNeedPage(Boolean.FALSE);
		try {
			// 查询数据
			// 列出当前用户选择的酒店
			if (user.getHotelvo() == null || !StringUtils.hasText(user.getHotelvo().getHotelId())) {
				log.info("noSelectHotel");
				return "list";
			}
			String hotelId = user.getHotelvo().getHotelId();
			// 是从订单提醒过来的则更新状态
			if (StringUtils.hasLength(from)) {
				List<String> masterIds = (List<String>) getSession().getAttribute(hotelId + from);
				if (masterIds != null) {
					soc.setMasterIdList(masterIds);
					soc.setHotelId(hotelId);
				}
				remindManager.updateHaveSeen(hotelId, from);
			}

			if (StringUtils.hasText(soc.getHotelId())) {
				//渠道用户渠道权限
				if(needChannel){
					soc.setChannelCodeList(channelCodeList);
				}
				orderSearchResult = orderManager.searchOrder(soc);
				// excel表头
				String[] colName = { 
						getText("ccm.ReservationsManagment.CRSNo"),
						getText("ccm.ReservationsManagment.GuestName"),
						getText("ccm.ReservationsManagment.ArrivalDate"),
						getText("ccm.ReservationsManagment.DepartureDate"),		
						getText("ccm.ReservationsManagment.ReservationCreatedDate"),
						getText("ccm.report.CacellationDate"),
						getText("ccm.ReservationsManagment.Status"),
						getText("ccm.RestrictionsManagement.RateCode"),
						getText("ccm.ReservationsManagment.RoomType"),
						getText("ccm.ReservationsManagment.TravelAgentSource"),		
						getText("ccm.ReservationsManagment.GroupCompany")
				};
				
				// excel要导出的数据
				List<String[]> dataList = getExcelDataByObjList(orderSearchResult.getResultList());
				SXSSFWorkbook workbook = ExportUtil.createExcel2("ReservationsList", colName, dataList);
				// 生成excel文件名
				String excelName = ExportUtil.createFileName(getText("ReservationsList")); 
				// 设置导出的excel文件从页面中下载
				getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
				getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");
				// 输出流
				OutputStream os = getResponse().getOutputStream();
				workbook.write(os);
				os.flush();
				os.close();			
			}
			// 进入订单页面，默认查询条件为空，无需显示任何内容
			soc.setHotelId(hotelId);
		} catch (Exception e) {
			getResponse().getWriter().print(CommonUtil.getExceptionMsg(e, "export fail"));
			getResponse().getWriter().flush();
		}
		return null;
	}
	

	/**
	 * 订单详情
	 */
	public String detail() {
		init();
		if (StringUtils.hasText(orderNo)) {
			findOrderInfo();

		} else {
			order = new Master();
		}

		return "detail";
	}

	/**
	 * 打印订单详情
	 * 
	 * @return
	 */
	public String printDetail() {
		if (StringUtils.hasText(orderNo)) {
			findOrderInfo();
		} else {
			order = new Master();
		}

		return "detailPrint";
	}

	/**
	 * 预订监控报表查询
	 * 
	 * @return
	 */
	public String reportList() {
		//渠道用户渠道权限
		B2BUser user = getCurLoginUser();
		List<Channel> channels=null;
		List<String> channelCodeList=new ArrayList<String>();
		Boolean needChannel=false;
		if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
			needChannel=true;
			channels=user.getChannels();
			for (Channel c : channels) {
				channelCodeList.add(c.getChannelCode());
			}
		}	
		
		// 分页
		int pageSize = this.getCurrentPageSize("order");
		int pageNo = this.getCurrentPageNo("order");
		soc.setPageNum(pageNo);
		soc.setPageSize(pageSize);

		if (soc.getCreateStart() != null) {
			soc.setExcelFlag(true);
			
			List<String> hList = soc.getHotelIdList();
			if(CommonUtil.isNotEmpty(hList)){
				String str = hList.get(0);
				if("-ALLCODE-".equals(str)){
					soc.setHotelIdList(null);
				}
			}
			//渠道用户渠道权限
			if(needChannel){
				soc.setChannelCodeList(channelCodeList);
			}
			
			orderSearchResult = orderManager.searchOrder(soc);
		} else {
			// 设置初始化标识
			getRequest().setAttribute("isInit", "1");
		}

		this.getRequest().setAttribute("resultSize", orderSearchResult.getTotalCount());

		List<Hotel> hotelList = null;
		// 如果是运营人员
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hotelList = hotelManager.getAll();
		} else {
			hotelList = hotelMCManager.getUserHotelByChainAndUserId(user.getUserId());
		}

		this.getRequest().setAttribute("hotelList", hotelList);

		orderStatus = OrderStatus.getOrderProductStatusMap();

		return "report";
	}

	/**
	 * 重新同步订单到PMS
	 * 
	 * @return
	 */
	public String resync() {
		try {
			Master master = orderManager.getOrderByOrderNo(orderNo);
			if (OrderStatus.ADD.equals(master.getSta())) {
				master.setSta(OrderStatus.EDIT);
			}
			reservationService.buildReservation(master);
			ajaxResponse("true");
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
			ajaxResponse("false");
		}
		return null;
	}

	public String save() {
		B2BUser user= getCurLoginUser();
	
		if(null!=user&&null!=user.getUserId()){
			List<String> orderAuthList=authManager.getUserAuth(user.getUserId());
			for (String string : orderAuthList) {
				if(string.equalsIgnoreCase("orderauth97")
						||"1".equals(user.getUserId())||"2".equals(user.getUserId())
					){
					orderAuth=true;
				}
			}
		}
		if(orderAuth){
			try {
				MasterOrder mo = new MasterOrder();
				//masterId 中央预定号,pmsId 酒店预定号
				mo.setMasterId(order.getMasterId());
				mo.setPmsId(order.getPmsId());
				
				Master m = orderManager.getOrderByOrderNo(order.getMasterId());//参数orderNo即masterId
				m.setRestype(order.getRestype());
				m.setRoomno(order.getRoomno());
				m.setPmsId(order.getPmsId());
				m.setMasterId(order.getMasterId());
				
				//更改sta与restype
				Map<String, String> codeMap = dictCodeManager.searchCodeMapByChannelHotel(OXIConstant.orderStatus, m.getChannelId(), null, false);
				String externalValue = codeMap.get(m.getRestype());
				if (externalValue != null) {
					m.setSta(externalValue);
				}else{
					m.setSta(m.getRestype());
				}
				
				/*
				 * 更改订单信息并记录更改日志状态   
				 * 酒店预定号   master_order  pmsId    
				 * 状态              master        restype
				 * 房号              master        roomno     
				 */
				//orderManager.updateMasterOrderPmsId(mo);
				orderManager.updateMaster(m,mo);
				
				//记录STAY HISTORY LOG
				m.setIsMC(true);
				pushManage.stayHistoryNotification(m);
				
				ajaxResponse("true");
			} catch (Exception e) {
				log.error(CommonUtil.getExceptionMsg(e, "ccm"));
				ajaxResponse("false");
			}
		}
		return null;
	}

	/**
	 * 订单详情及打印页的公共部分
	 */
	private void findOrderInfo() {

		order = orderManager.getOrderByOrderNo(orderNo);
		
		//信用卡是否掩码
		String cardNumber = order.getCardNumber();
		if(StringUtils.hasText(cardNumber)&&cardNumber.length()>4){
			B2BUser user = getCurLoginUser();
			HotelVO hotelVO=user.getHotelvo();
			String hotelId=hotelVO.getHotelId();
			HotelSwitch hotelSwitch=hotelSwitchManager.getByHotelId(hotelId);
			StringBuffer x = new StringBuffer();
			if(hotelSwitch!=null&&hotelSwitch.getIsMask()!=null && hotelSwitch.getIsMask()){
				for(int i=0;i<cardNumber.length()-4;i++){
					x.append("X");
				}
				order.setCardNumber(cardNumber.substring(0, 4)+x.toString());
			}
		}

		try {
			priceDetail = orderManager.getMasterRateByOrderNo(orderNo);
			getRequest().setAttribute("packageDetail", orderManager.getMasterPackageByOrderNo(orderNo));
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public String changesLog() {
		if (StringUtils.hasText(orderNo)) {
			List<MasterChangesLog> masterChangesLogList = orderChangesLogManager.getMasterChangesLogByOrderNo(orderNo);
			getRequest().setAttribute("masterChangesLogList", masterChangesLogList);
		}
		return "changesLog";
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public SearchOrderCriteria getSoc() {
		return soc;
	}

	public void setSoc(SearchOrderCriteria soc) {
		this.soc = soc;
	}

	public Master getOrder() {
		return order;
	}

	public void setOrder(Master order) {
		this.order = order;
	}

	public OrderSearchResult getOrderSearchResult() {
		return orderSearchResult;
	}

	public void setOrderSearchResult(OrderSearchResult orderSearchResult) {
		this.orderSearchResult = orderSearchResult;
	}

	public List<MasterRate> getPriceDetail() {
		return priceDetail;
	}

	public void setPriceDetail(List<MasterRate> priceDetail) {
		this.priceDetail = priceDetail;
	}

	public Map<String, String> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Map<String, String> orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public HashMap<String, Object> getConfigMap() {
        return configMap;
    }
    public void setConfigMap(HashMap<String, Object> configMap) {
        this.configMap = configMap;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}
	
	public String getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public List<String> getRestypeList() {
		return restypeList;
	}
	
	public void setRestypeList(List<String> restypeList) {
		this.restypeList = restypeList;
	}
	public boolean isOrderAuth() {
		return orderAuth;
	}
	public void setOrderAuth(boolean orderAuth) {
		this.orderAuth = orderAuth;
	}
	
	
}