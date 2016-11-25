package com.ccm.mc.webapp.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.Constants;
import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.user.EmployeeDao;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.Employee;
import com.ccm.api.service.common.MenuManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.service.hotel.HotelManager;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class I18nInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2067359440557725136L;

	@Resource
	private MenuManager menuManager;
	@Autowired
	private HotelManager hotelManager;
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private ChainManager chainManager;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		B2BUser curLoginUser = null;// 取登陆用户信息
		try {
			curLoginUser = SecurityHolder.getB2BUser();
		} catch (Exception e1) {
		}
		if (null != curLoginUser) {
			HttpServletRequest request = ServletActionContext.getRequest();
			String url = request.getRequestURI();
			if (url.indexOf("/") > -1 && url.length() > 1) {
				url = url.substring(1);
			}
			String locale = request.getParameter("locale");//url请求的语言环境
			String menuId = request.getParameter("menuId");//一级菜单
			String tmenuId = request.getParameter("tmenuId");//二级菜单
			Locale localeStruts = invocation.getInvocationContext().getLocale();// 语言环境
			String language = localeStruts.getLanguage()+"_"+localeStruts.getCountry();
			HttpSession session = request.getSession();
			//更换语言环境
			if(locale!=null){
				Locale preferredLocale = new Locale(localeStruts.getLanguage(), localeStruts.getCountry());
				session.setAttribute(Constants.PREFERRED_LOCALE_KEY, preferredLocale);
				Config.set(session, Config.FMT_LOCALE, preferredLocale);
				
				updateUserHotel(curLoginUser,language);
				
				List<Menu>  menuList = null;
				List<Menu>  menuListGetSession = (List<Menu>) session.getAttribute("TopMenu"+locale + curLoginUser.getUserId());
				String sessMenuId = "TopMenu" + curLoginUser.getUserId();
				if(menuListGetSession!=null){
					session.setAttribute(sessMenuId, menuListGetSession);
				}else{
					//更新一级菜单
					menuList = menuManager.getAuthedTopMenu(curLoginUser,language);
					session.setAttribute("TopMenu"+locale + curLoginUser.getUserId(), menuList);
					session.setAttribute(sessMenuId, menuList);
				}
				
				
				/**
				 * 当前处于二级菜单时，更新二级菜单
				 */
				//更新二级菜单
				
				//只展示二级菜单
				if(StringUtils.hasLength(menuId)){
					String sessMenuSubId = "SubMenu" + curLoginUser.getUserId() + "ParentId" + menuId+language;
					if(session.getAttribute(sessMenuSubId)!=null){//session中存在该二级菜单
						return invocation.invoke();
					}else{
						// 取得有权限的菜单列表
						Menu m = menuManager.getMenuById(menuId, language);
						if (m != null) {
							session.setAttribute("menuName" +language+ curLoginUser.getUserId() + "ParentId" + menuId, m.getMenuName());
							menuList = menuManager.getAuthedTwoMenus(menuId, curLoginUser.getUserId(),language);
							
							session.setAttribute(sessMenuSubId, menuList);
							return invocation.invoke();
						}
					}
				}
				//展示二级菜单中的内容  url中有tmenuId参数
				if(StringUtils.hasLength(tmenuId)){
					// 取得有权限的菜单列表
					Menu m = menuManager.getMenuById(tmenuId, language);
					
					if (m != null) {
						String sessMenuSubId = "SubMenu" + curLoginUser.getUserId() + "ParentId" + m.getParentId()+language;
						if(session.getAttribute(sessMenuSubId)!=null){//session中存在该二级菜单
							return invocation.invoke();
						}else{
							session.setAttribute("menuName" +language+ curLoginUser.getUserId() + "ParentId" + m.getParentId(), m.getMenuName());
							menuList = menuManager.getAuthedTwoMenus(m.getParentId(), curLoginUser.getUserId(),language);
							
							session.setAttribute(sessMenuSubId, menuList);
							return invocation.invoke();
						}
					}
				}
				//展示二级菜单中的内容  url中没有tmenuId参数
				if(!StringUtils.hasLength(menuId)&&!StringUtils.hasLength(tmenuId)){
					//通过url获取该菜单
					Menu m = menuManager.getMenuByUrl(url,language);
					if(m!=null&&m.getParentId()!=null){
						String parentId = m.getParentId();
						String sessMenuSubId = "SubMenu" + curLoginUser.getUserId() + "ParentId" +parentId+language;
						if(session.getAttribute(sessMenuSubId)!=null){//session中存在该二级菜单
							return invocation.invoke();
						}else{
							session.setAttribute("menuName" +language+ curLoginUser.getUserId() + "ParentId" + m.getParentId(), m.getMenuName());
							//通过url获取二级菜单
							menuList = menuManager.getAuthedTwoMenusByUrl(curLoginUser.getUserId(), url,locale);
							
							session.setAttribute(sessMenuSubId, menuList);
							return invocation.invoke();
						}
					}
				}
			}
		}
		return  invocation.invoke();
	}
	
	/**
	 * 切换语言时，更换用户的酒店语言信息
	 * @param user
	 */
	private void updateUserHotel(B2BUser user,String language){
//		if(user.getHotelvo()!=null){
//			return ;
//		}
		if (StringUtils.hasText(user.getEmployeeId())) {
//			Employee employee = employeeDao.get(user.getEmployeeId());
			Employee employee = employeeDao.getEmployee(user.getEmployeeId(), language);
			user.setEmployee(employee);
		}

		List<HotelVO> hlist = null;
		// 运营人员
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hlist = hotelManager.getAllHotels(language);
		} else {
			hlist = hotelManager.getHotelInfoChainByUserId(user.getUserId(),language);
		}
		
		if (hlist != null && !hlist.isEmpty()) {
			//用户关联的所有集团
			List<ChainVO> chainVos = new ArrayList<ChainVO>();
			for (HotelVO hotel : hlist) {
				boolean flag = true; //表示不存在chainVos中
				for (ChainVO chainVO : chainVos) {
					if(chainVO.getChainId().equals(hotel.getChainId())){
						chainVO.getHotelVos().add(hotel);
						flag = false;
						break;
					}
				}
				
				//如果不存在chainVos中
				if(flag){
					ChainVO chainVo = chainManager.getChainById(hotel.getChainId(),language);
					if(chainVo != null){
						chainVo.getHotelVos().add(hotel);
						chainVos.add(chainVo);
					}
				}
			}
			
			//集团排序
			Collections.sort(chainVos, new Comparator<ChainVO>() {
				@Override
				public int compare(ChainVO o1, ChainVO o2) {
					return o1.getChainCode().compareTo(o2.getChainCode());
				}
			});
			
			//集团下的酒店排序.
			for (ChainVO chainVO : chainVos) {
				Collections.sort(chainVO.getHotelVos(), new Comparator<HotelVO>() {
					@Override
					public int compare(HotelVO o1, HotelVO o2) {
						return o1.getHotelCode().compareTo(o2.getHotelCode());
					}
				});
			}
			
			//设置集团集团列表
			user.setChainVOs(chainVos);
			
			//设置当前酒店和集团
			ChainVO firstChain = chainVos.get(0);
			HotelVO firstHotel = firstChain.getHotelVos().get(0);
			firstHotel.setChainCode(firstChain.getChainCode());
			
			user.setHotelvo(firstHotel);
			user.setHotelVOs(hlist);
		}
	}
}
