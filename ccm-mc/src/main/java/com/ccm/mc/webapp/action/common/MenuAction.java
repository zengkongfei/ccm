package com.ccm.mc.webapp.action.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.model.cfg.SysCfg;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.UserPassword;
import com.ccm.api.service.cfg.SysCfgManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.common.MenuManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.user.UserManager;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 菜单类
 */
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = -9208910183310010569L;

	private String menuId;
	private String hotelId;

	private List<Menu> menuList;

	private List<HotelVO> hotelList;

	private MenuManager menuManager;

	@Autowired
	private HotelManager hotelManager;

	@Autowired
	private ChainManager chainManager;
	
	@Autowired private ChannelManager channelManager;
	
	@Autowired
	private UserManager userManager;

	@Autowired
	private SysCfgManager sysCfgManager;

	private SysCfg sysCfg;
	
	public void setMenuManager(MenuManager menuManager) {
		this.menuManager = menuManager;
	}

	/**
	 * 获取所有酒店
	 */
	public String main() {

		B2BUser user = getCurLoginUser();
		
		HttpSession session = getSession();
		
		//用户角色companyId
		if (user != null && user.getCompany()!=null) {
			session.setAttribute("companyId", user.getCompany().getCompanyId());
		}
		
		//获取系统公告
		List<SysCfg> all=sysCfgManager.getAll();
		if(null!=all&&all.size()>0){
			sysCfg=all.get(0);
		}
		
		if(user.getHotelvo()!=null){
			return SUCCESS;
		}

		List<HotelVO> hlist = null;
		List<Channel> channels = null;
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		// 运营人员
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hlist = hotelManager.getAllHotels(language);
			channels = channelManager.getAllChannel();
		} else {
			hlist = hotelManager.getHotelInfoChainByUserId(user.getUserId(),language);
			if(user.getIshotelBlackList()==true){
				List <HotelVO> allHotelList=hotelManager.getAllHotels(language);
				Map <String,HotelVO>hotelMap=new HashMap<String,HotelVO>();
				for(HotelVO hotelVO:allHotelList){
					hotelMap.put(hotelVO.getHotelId(),hotelVO);
				}
				for(HotelVO hotelVO:hlist){
					hotelMap.remove(hotelVO.getHotelId());
				}
				hlist=new ArrayList<HotelVO>(hotelMap.values());
			}
			
			if(CompanyType.CHANNEL.equals(user.getCompanyId())){
				channels = channelManager.getChannelInfoChainByUserId(user.getUserId());
			}else{
				channels = channelManager.getAllChannel();
			}
		}
		user.setChannels(channels);
		
		
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
					ChainVO chainVo = chainManager.getChainById(hotel.getChainId());
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
		return menuList();
	}

	/**
	 * 一级菜单列表
	 * 
	 * @return
	 */
	public String menuList() {

		B2BUser user = this.getCurLoginUser();
		HttpSession session = getSession();
		UserPassword userPassword = userManager.getUserPasswordInfo(user.getUserId());
		String passwordMassge = (String) session.getAttribute("passwordMassge");
		if(passwordMassge!=null){
			session.removeAttribute("passwordMassge");
		}
		if(userPassword!=null){
			Date passwordlastModifyTime = userPassword.getPasswordlastModifyTime();
			Date day90 = DateUtil.addDays(passwordlastModifyTime, 90);
			//上一次修改密码是90天之前，不展示任何主菜单
			if(day90.before(new Date())){
				session.setAttribute("passwordMassge", getText("ccm.password.message1"));
				return SUCCESS;
			}
			if(!userPassword.getPasswordIsInit()){
				session.setAttribute("passwordMassge", getText("ccm.password.message2"));
				return SUCCESS;
			}
			//85天，用户在密码过期前5天开始即使用85天开始登陆系统将自动提示“请尽快修改密码”
			Date day85 = DateUtil.addDays(passwordlastModifyTime, 85);
			if(day85.before(new Date())){
				session.setAttribute("passwordMassge", getText("ccm.password.message3"));
			}
		}

		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		if (StringUtils.hasText(hotelId)) {
			HotelVO hvo = hotelManager.getHotelI18nChainByHotelId(hotelId,language);
			user.setHotelvo(hvo);
		}

		String sessMenuId = "TopMenu" + user.getUserId();
		
		String sessMenuIdLangeuage = "TopMenu"+locale.getLanguage()+"_"+locale.getCountry() + user.getUserId();

		if (session.getAttribute(sessMenuId) == null) {

			// 取得有权限的菜单列表
			menuList = menuManager.getAuthedTopMenu(user,language);

			session.setAttribute(sessMenuId, menuList);
			session.setAttribute(sessMenuIdLangeuage, menuList);
		}

		return SUCCESS;
	}

	/**
	 * 二级菜单列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String twoList() {
		HttpSession session = getSession();
		String passwordMassge = (String) session.getAttribute("passwordMassge");
		if(passwordMassge!=null){
			session.removeAttribute("passwordMassge");
		}
		if (!StringUtils.hasText(menuId)) {
			return "twolist";
		}

		B2BUser user = this.getCurLoginUser();

		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		String sessMenuSubId = "SubMenu" + user.getUserId() + "ParentId" + menuId+language;
		
		if (session.getAttribute(sessMenuSubId) == null) 
		{
			// 取得有权限的菜单列表
			Menu m = menuManager.getMenuById(menuId, language);
			if (m != null) {
				session.setAttribute("menuName" +language+ user.getUserId() + "ParentId" + menuId, m.getMenuName());
				menuList = menuManager.getAuthedTwoMenus(menuId, user.getUserId(),language);				
				session.setAttribute(sessMenuSubId, menuList);
			}
		}
		
		//为 报表/日志 增加序号 zh_CN en_US
		String zh_CN_No=(String) session.getAttribute("zh_CN_No");
		String en_US_No=(String) session.getAttribute("en_US_No");
		List<Menu> menuList=(List<Menu>) session.getAttribute(sessMenuSubId);
		if( (session.getAttribute(sessMenuSubId) != null) && ("72".equals(menuId)) ){
			
			if( (!"zh_CN_No".equals(zh_CN_No)) && "zh_CN".equals(language)){
				int i=1;
				for (Menu m: menuList) {
					m.setDisplayName((i++)+" "+m.getDisplayName());
				}
				session.setAttribute("zh_CN_No", "zh_CN_No");
			}else if( (!"en_US_No".equals(en_US_No)) && ("en_US".equals(language)) ){
				int j=1;
				for (Menu m: menuList) {
					m.setDisplayName((j++)+" "+m.getDisplayName());
				}
				session.setAttribute("en_US_No", "en_US_No");
			}
		}

		return SUCCESS;
	}

	public String hotels() {
		try {
			return ajaxReturn(hotelManager.getAllHotels(), "OK");
		} catch (IOException e) {
			log.info(e);
			return null;
		}
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<HotelVO> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<HotelVO> hotelList) {
		this.hotelList = hotelList;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public SysCfg getSysCfg() {
		return sysCfg;
	}

	public void setSysCfg(SysCfg sysCfg) {
		this.sysCfg = sysCfg;
	}

}
