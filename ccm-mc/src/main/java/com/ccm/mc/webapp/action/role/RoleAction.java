package com.ccm.mc.webapp.action.role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import com.ccm.api.model.base.vo.MenuTreeVO;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.role.RoleCriteria;
import com.ccm.api.model.role.RoleSearchResult;
import com.ccm.api.model.user.AuthRole;
import com.ccm.api.model.user.Authority;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.RoleChannel;
import com.ccm.api.model.user.RoleMenu;
import com.ccm.api.model.user.UserRoleChannel;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.user.AuthManager;
import com.ccm.api.service.user.RoleManager;
import com.ccm.api.service.user.UserManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction {
	@Resource
	private RoleManager roleManager;
	@Resource
	private UserManager userManager;
	private RoleCriteria roleCriteria;
	private RoleSearchResult roleSearchResult;
	private String roleId;
	private final String returnList="list";
	private final String returnEdit="edit";
	private Role role;
	private String[] menuIds;
	private String[] channelIds;
	private List<Channel> channels;
	private Set<String> channelIdSet;
	private String roleCode;
	// 已有权限
	private List<String> authedRoleMap;
	// 所有可授权的菜单
	private Map<String, List<MenuTreeVO>> allAuthMenuMap;
	
	// 所有可授权的功能权限
	private List<Authority> allAuthority;
	
	//功能权限
	private String[] authIds;
	//某角色已有的所有权限
	private List<String> authList;
	@Resource
	private AuthManager authManager;
	
	@Resource
	private ChannelManager channelManager;
	public List<String> getAuthedRoleMap() {
		return authedRoleMap;
	}
	public void setAuthedRoleMap(List<String> authedRoleMap) {
		this.authedRoleMap = authedRoleMap;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public RoleCriteria getRoleCriteria() {
		return roleCriteria;
	}
	public void setRoleCriteria(RoleCriteria roleCriteria) {
		this.roleCriteria = roleCriteria;
	}
	public RoleSearchResult getRoleSearchResult() {
		return roleSearchResult;
	}
	public void setRoleSearchResult(RoleSearchResult roleSearchResult) {
		this.roleSearchResult = roleSearchResult;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -3781145449056954748L;
	public String list() {
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage() + "_" + locale.getCountry();

		int pageSize = this.getCurrentPageSize("role");
		int pageNo = this.getCurrentPageNo("role");
		if (roleCriteria == null) {
			roleCriteria = new RoleCriteria();
		}
		roleCriteria.setPageNum(pageNo);
		roleCriteria.setPageSize(pageSize);
		roleCriteria.setLanguage(language);

	

		roleSearchResult = roleManager.search(roleCriteria);

		this.getRequest().setAttribute("resultSize",
					roleSearchResult.getTotalCount());

		return returnList;
	
	}
	
	public String save(){
		String currentUserId=getCurLoginUser().getUserId();
		
		if(CommonUtil.isNotEmpty(roleId)){
			//modify
			role.setRoleId(roleId);
			role.setUpdatedBy(currentUserId);
			role.setLastModifyTime(new Date());
		}else{
			//new
			role.setDelFlag(false);
			role.setCreatedBy(currentUserId);
			role.setCreatedTime(new Date());
		}
		//save role
			role=roleManager.saveRole(role);
			roleId=role.getRoleId();
		//remove old menu data	
			roleManager.removeRoleMenuByRoleId(roleId);
			
		//保存角色功能权限
		AuthRole ar=new AuthRole();
		authList=new ArrayList<>();
		if(CommonUtil.isNotEmpty(authIds)){
			Collections.addAll(authList, authIds);
		}
		ar.setId(roleId);
		ar.setAuthIds(authList);
		authManager.saveAuthRole(ar);
			
		if(CommonUtil.isNotEmpty(menuIds)){
			for(String menuId:menuIds){
				RoleMenu roleMenu=new RoleMenu();
				roleMenu.setDelFlag(false);
				roleMenu.setCreatedBy(currentUserId);
				roleMenu.setCreatedTime(new Date());
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(menuId);
				//save new menu data	
				roleManager.addRoleMenu(roleMenu);
			}
		}
		// new coding
				List<RoleChannel> roleChannelList=new ArrayList<RoleChannel>(); 
				if(CommonUtil.isNotEmpty(channelIds)){
					for(String channelId:channelIds){
						RoleChannel rc=new RoleChannel();
						rc.setChannelId(channelId);
						rc.setRoleId(roleId);
						rc.setCreatedTime(new Date());
						rc.setCreatedBy(currentUserId);
						roleChannelList.add(rc);
					}
				}
				roleManager.setRoleOfRoleChannel(roleId,roleChannelList);
				
				List<String> userIdList=userManager.getUserIdByRoleId(roleId);
				if(CommonUtil.isNotEmpty(userIdList)){
					for(String userId:userIdList){
						List<UserRoleChannel> userRoleChannelList=new ArrayList<UserRoleChannel>();  
						if(CommonUtil.isNotEmpty(channelIds)){
							for(String channelId:channelIds){
								UserRoleChannel urc=new UserRoleChannel();
								urc.setUserId(userId);
								urc.setChannelId(channelId);
								urc.setRoleId(roleId);
								urc.setCreatedTime(new Date());
								urc.setCreatedBy(currentUserId);
								userRoleChannelList.add(urc);
								}
							}
						userManager.setRoleOfUserOfMcChannel(userId,roleId,userRoleChannelList);
						}
				}
		
		return list();
	}
	public String remove(){
		roleManager.remove(roleId);
		roleManager.removeRoleMenuByRoleId(roleId);
		
		//删除角色的同时删除角色功能权限
		authManager.removeAuthRole(roleId);
		
		return list();
	}
	
	public String edit(){
		if(CommonUtil.isNotEmpty(roleId)){
			role=roleManager.get(roleId);
		}
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage() + "_" + locale.getCountry();
		authedRoleMap= new ArrayList<String>();
		List<Menu> mList = roleManager.getMenuByRoleId(roleId, language);
		for (Menu m : mList) {
			authedRoleMap.add(m.getMenuId());
		}
		allAuthMenuMap=roleManager.getAllMenu(language);
		
		channels = channelManager.getAllChannel();
		channelIdSet=new HashSet<String>(roleManager.getChannelIdByRoleId(roleId));
		
		//获取所有权限
		List<Authority> auths=authManager.getAllAuth();
		if(null!=auths){
			allAuthority=auths;
		}
		if(CommonUtil.isNotEmpty(roleId)){
			AuthRole ar=authManager.getAuthRole(roleId);
			if(null!=ar){
				authList=ar.getAuthIds();
			}
					
		}
		
		return returnEdit;
	}
	
	public void exist(){
		Boolean isExisted=roleManager.isExistedRoleTemplateByRoleCode(roleCode);
		this.ajaxResponse(isExisted.toString());
	}
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public Map<String, List<MenuTreeVO>> getAllAuthMenuMap() {
		return allAuthMenuMap;
	}
	
	public void setAllAuthMenuMap(Map<String, List<MenuTreeVO>> allAuthMenuMap) {
		this.allAuthMenuMap = allAuthMenuMap;
	}
	
	public String[] getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}
	public List<Channel> getChannels() {
		return channels;
	}
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
	public Set<String> getChannelIdSet() {
		return channelIdSet;
	}
	public void setChannelIdSet(Set<String> channelIdSet) {
		this.channelIdSet = channelIdSet;
	}
	public String[] getChannelIds() {
		return channelIds;
	}
	public void setChannelIds(String[] channelIds) {
		this.channelIds = channelIds;
	}
	public List<Authority> getAllAuthority() {
		return allAuthority;
	}
	public void setAllAuthority(List<Authority> allAuthority) {
		this.allAuthority = allAuthority;
	}
	public String[] getAuthIds() {
		return authIds;
	}
	public void setAuthIds(String[] authIds) {
		this.authIds = authIds;
	}
	public List<String> getAuthList() {
		return authList;
	}
	public void setAuthList(List<String> authList) {
		this.authList = authList;
	}

}
