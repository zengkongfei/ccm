package com.ccm.mc.webapp.action.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.log.model.LogDetail;
import com.ccm.api.log.service.ILogService;
import com.ccm.api.log.vo.LogInfoCriteria;
import com.ccm.api.log.vo.LogInfoResult;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.common.vo.MenuCriteria;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.vo.B2BUserVO;
import com.ccm.api.service.common.MenuManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.user.UserManager;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class LogAction extends BaseAction {

	private static final long serialVersionUID = 1750501830430477941L;

	private LogInfoResult logInfoResult = new LogInfoResult();
	private LogInfoCriteria criteria;
	private List<LogDetail> detailList;
	private String logId;// ID
	private String userId;// 操作员ID
	private String parentId;// 一级菜单ID
	private List<B2BUserVO> userList;
	private List<Menu> topMenuList;

	private List<Hotel> hotelList = new ArrayList<Hotel>();
	@Resource
	private HotelMCManager hotelMCManager;
	
	@Autowired
	private ILogService logService;
	@Autowired
	private UserManager userManager;
	@Autowired
	private MenuManager menuManager;
	@Resource
	private HotelManager hotelManager;

	/**
	 * 日志信息查询页面
	 * 
	 * @return
	 */
	public String list() throws IOException {
		B2BUser user = getCurLoginUser();
		if (user != null) {
			
			this.getAuthedHotels();
			this.getRequest().setAttribute("hotelList", hotelList);
			//String chainId = user.getHotelvo().getChainId();
			//this.getRequest().setAttribute("hotelList", hotelManager.getHotelByChainId(chainId));
			
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			
			//默认加载所有用户
			userList = userManager.getAllB2BUser();
			//this.getRequest().setAttribute("userList", userManager.getAllB2BUser());
			
			//默认加载所有一级菜单
			topMenuList = menuManager.getTopMenuList(language);
			//this.getRequest().setAttribute("topMenuList", topMenuList);
			
			if (null == criteria) {
				criteria = new LogInfoCriteria();
				return "list";
			}
			if (criteria.getOperateTimeBegin() == null || criteria.getOperateTimeEnd() == null) {
				saveMessage(getText("ccm.PMSMessageLog.ErrorMessage.PleaseStartTimeAndEndTime"));
				return "list";
			}

			if (criteria.getOperateTimeBegin().after(criteria.getOperateTimeEnd())) {
				saveMessage("开始时间不能大于结束时间");
				return "list";
			}
			int pageSize = this.getCurrentPageSize("logInfoList");
			int pageNo = this.getCurrentPageNo("logInfoList");
			criteria.setPageNum(pageNo);
			criteria.setPageSize(pageSize);
			criteria.setLanguage(language);
			
			logInfoResult = logService.searchLogInfo(criteria);
		}
		return "list";
	}

	/**
	 * ajax 根据登陆用户获取一级菜单
	 * 
	 * @throws IOException
	 */
	public void ajaxGetAllTopMenuByUserId() throws IOException {
		MenuCriteria menuCriteria=new MenuCriteria();
		String userIds = getRequest().getParameter("userIds");
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		if ( (null != userIds) && (!userIds.equals("")) ){
			String[] userIdsArr = userIds.split(",");
			menuCriteria.setLanguage(language);
			menuCriteria.setUserIds(Arrays.asList(userIdsArr));
			menuCriteria.setNeedPage(false);
			topMenuList=menuManager.getAuthedTopMenusByUserIds(menuCriteria);
		}
		Menu m = menuManager.getMenuById("10", language);
		topMenuList.add(m);
		JSONArray menuArr = new JSONArray();
		if (topMenuList != null && topMenuList.size() > 0) {
			for (Menu menu : topMenuList) {
				JSONObject menuJson = new JSONObject();
				menuJson.put("menuId", menu.getMenuId());
				menuJson.put("displayName", menu.getDisplayName());
				menuArr.add(menuJson);
			}
		}
		ajaxResponse(menuArr.toString());
	}

	/**
	 * ajax 根据登陆用户和一级菜单获取二级菜单
	 * 
	 * @throws IOException
	 */
	public void ajaxGetAllTwoMenuByUserId() throws IOException {
		MenuCriteria menuCriteria=new MenuCriteria();
		String parentIds = getRequest().getParameter("businessIds");
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		if (!parentIds.equals("")) {
			String[] pIdsArr = parentIds.split(",");
			menuCriteria.setLanguage(language);
			menuCriteria.setParentIds(Arrays.asList(pIdsArr));
			menuCriteria.setNeedPage(false);
			List<Menu> twoMenuList = menuManager.getTwoMenusByParentIds_i18n(menuCriteria);
			JSONArray menuArr = new JSONArray();
			if (twoMenuList != null && twoMenuList.size() > 0) {
				for (Menu menu : twoMenuList) {
					JSONObject menuJson = new JSONObject();
					menuJson.put("menuId", menu.getMenuId());
					menuJson.put("displayName", menu.getDisplayName());
					menuArr.add(menuJson);
				}
			}
			ajaxResponse(menuArr.toString());
		}
	}

	/**
	 * 查询日志明细信息
	 * 
	 * @return
	 */
	public String logDetailSearch() {
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		detailList = logService.searchLogDetails(logId,language);
		return "logDetailSearch";
	}

	public void ajaxCleanHashMap() {
		menuManager.cleanHashMap();
	}

	public LogInfoResult getLogInfoResult() {
		return logInfoResult;
	}

	public void setLogInfoResult(LogInfoResult logInfoResult) {
		this.logInfoResult = logInfoResult;
	}

	public LogInfoCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(LogInfoCriteria criteria) {
		this.criteria = criteria;
	}

	public List<LogDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<LogDetail> detailList) {
		this.detailList = detailList;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<B2BUserVO> getUserList() {
		return userList;
	}

	public void setUserList(List<B2BUserVO> userList) {
		this.userList = userList;
	}

	public List<Menu> getTopMenuList() {
		return topMenuList;
	}

	public void setTopMenuList(List<Menu> topMenuList) {
		this.topMenuList = topMenuList;
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
	}	
	
}
