/**
 * 
 */
package com.ccm.mc.webapp.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.cglib.core.Local;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.ccm.api.SecurityHolder;
import com.ccm.api.log.model.LogInfo;
import com.ccm.api.log.service.ILogService;
import com.ccm.api.model.common.vo.MenuVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.MenuManager;
import com.ccm.api.service.common.ParamInterfaceManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author Jenny
 * 
 */
public class LogActionInterceptor implements Interceptor {

	/**
     * 
     */
	private static final long serialVersionUID = -4931429260724171166L;
	Logger logger = LoggerFactory.getLogger(LogActionInterceptor.class);

	@Resource
	private ILogService logService;
	@Resource
	private MenuManager menuManager;
	@Resource
	private ParamInterfaceManager paramInterfaceManager;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	/**
	 * 记录日志信息 监测action层的所有方法
	 * 
	 * @param jp
	 */
	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation invocation) throws Exception {
		B2BUser curLoginUser = null;// 取登陆用户信息
		try {
			curLoginUser = SecurityHolder.getB2BUser();
		} catch (Exception e1) {
		}
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		// 如果新增/修改/删除并且登陆对象不为空时切入
		if (null != curLoginUser) {
			String methodName = invocation.getProxy().getMethod();// 获取目标方法签名 ;
			try {

				HttpServletRequest request = ServletActionContext.getRequest();
				String url = request.getRequestURI();
				if (url.indexOf("/") > -1 && url.length() > 1) {
					url = url.substring(1);
				}
				MenuVO mvo = menuManager.findRecordLogMenuByUrl(url,language);
				if (mvo == null || !StringUtils.hasLength(mvo.getMenuId())) {
					return response(invocation);
				}
				String topMenu = mvo.getPmenuId();// 一级菜单
				String twoMenu = mvo.getMenuId();// 二级菜单

				if (!StringUtils.hasLength(topMenu) && !StringUtils.hasLength(twoMenu)) {
					return response(invocation);
				}

				if (!StringUtils.hasLength(topMenu)) {
					topMenu = twoMenu;
					twoMenu = "";
				}

				HotelVO hotelvo = curLoginUser.getHotelvo();// 取酒店信息

				String operateType = "0";// 取操作类型
				if (methodName.indexOf("save") == 0 || methodName.indexOf("add") == 0) {
					operateType = "1";
				} else if (methodName.indexOf("update") == 0) {
					operateType = "2";
				} else if (methodName.indexOf("delete") == 0 || methodName.indexOf("remove") == 0) {
					operateType = "3";
				} else {
					operateType = mvo.getMenuUrlName();
				}

				LogInfo logInfo = new LogInfo();
				logInfo.setOperaterId(curLoginUser.getUserId());
				logInfo.setHotelId(hotelvo.getHotelId());
				logInfo.setLastLoginTime(curLoginUser.getLastLoginTime());
				logInfo.setOperateTime(new Date());
				logInfo.setBusinessId(topMenu);
				logInfo.setFunctionId(twoMenu);
				logInfo.setOperateType(operateType);

				Map<String, String> keyValue = new HashMap<String, String>();
				Map map = request.getParameterMap();
				Set keSet = map.entrySet();

				// 获取修改时的主键
				String primaryKeyName = paramInterfaceManager.getPrimaryKeyByCond(mvo.getUrlId());

				for (Iterator itr = keSet.iterator(); itr.hasNext();) {
					Map.Entry me = (Map.Entry) itr.next();
					Object ok = me.getKey();
					Object ov = me.getValue();
					String[] value = new String[1];
					if (ov instanceof String[]) {
						value = (String[]) ov;
					} else {
						value[0] = ov.toString();
					}

					for (int k = 0; k < value.length; k++) {
						if (ok.equals("_")) {
							continue;
						}
						//复选框
						Boolean checkbox=true;
						if(((String) ok).startsWith("__checkbox_")){
							ok=((String) ok).replace("__checkbox_","");
							if((ok.equals("_"))||(((String) ok).length()<1)||map.containsKey(ok)){
								continue;
							}
							checkbox=false;
						}
						
						String key = (String) ok;
						
						if(!checkbox){
							keyValue.put(key, checkbox.toString());
						}else if (keyValue.containsKey(key)) {
							keyValue.put(key, keyValue.get(key) + "," + value[k]);
						}else {
							keyValue.put(key, value[k]);
						}

						if (StringUtils.hasLength(primaryKeyName) && primaryKeyName.equals(key) && StringUtils.hasLength(value[k])) {
							logInfo.setPrimaryValue(value[k]);
							if (operateType.equals("1")) {
								logInfo.setOperateType("2");
							}
						}
					}
				}

				logService.insertLogAcation(logInfo, keyValue, primaryKeyName, mvo.getUrlId());

			} catch (Exception e) {
				logger.error(methodName + "!!!!!!Action保存日志出错:" + e.getMessage());
			}
		}
		return response(invocation);
	}

	private String response(ActionInvocation invocation) throws Exception {
		// invocation.invoke()是放行的意思，等action中的方法执行完成后，会再次返回过来，actionResultString为action中返回的值，如success
		logger.info("******start invoke action method******");
		String actionResultString = invocation.invoke();
		logger.info("******end invoke action method******");
		return actionResultString;
	}
}
