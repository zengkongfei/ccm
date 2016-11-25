
package com.ccm.mc.webapp.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.Constants;
import com.ccm.api.model.base.criteria.SearchCriteria;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.base.MailEngine;
import com.ccm.api.service.user.RoleManager;
import com.ccm.api.service.user.UserManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.PropertiesUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Implementation of <strong>ActionSupport</strong> that contains convenience
 * methods for subclasses. For example, getting the current user and saving
 * messages/errors. This class is intended to be a base class for all Action
 * classes.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 3525445612504421307L;
	
	public String hotelIdFormHidden;//获取session内的hotelId添加到所有form表单内
	public String getHotelIdFormHidden() {
		return hotelIdFormHidden;
	}
	public void setHotelIdFormHidden(String hotelIdFormHidden) {
		this.hotelIdFormHidden = hotelIdFormHidden;
	}

	/**
	 * Constant for cancel result String
	 */
	public static final String CANCEL = "cancel";

	/**
	 * Transient log to prevent session synchronization issues - children can
	 * use instance for logging.
	 */
	public final transient Log log = LogFactory.getLog(getClass());
	public static final String CONTENT_TYPE_JSON = "application/json";
	/**
	 * The UserManager
	 */
	public UserManager userManager;

	/**
	 * The RoleManager
	 */
	public RoleManager roleManager;

	/**
	 * Indicator if the user clicked cancel
	 */
	public String cancel;

	/**
	 * Indicator for the page the user came from.
	 */
	public String from;

	/**
	 * Set to "delete" when a "delete" request parameter is passed in
	 */
	public String delete;

	/**
	 * Set to "save" when a "save" request parameter is passed in
	 */
	public String save;

	/**
	 * MailEngine for sending e-mail
	 */
	public MailEngine mailEngine;

	/**
	 * A message pre-populated with default data
	 */
	public SimpleMailMessage mailMessage;

	/**
	 * Velocity template to use for e-mailing
	 */
	public String templateName;

	/**
	 * Simple method that returns "cancel" result
	 * 
	 * @return "cancel"
	 */
	public String cancel() {
		return CANCEL;
	}

	/**
	 * Save the message in the session, appending if messages already exist
	 * 
	 * @param msg
	 *            the message to put in the session
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void saveMessage(String msg) {
		List messages = (List) getRequest().getSession().getAttribute("messages");
		if (messages == null) {
			messages = new ArrayList();
		}
		messages.add(msg);
		getRequest().getSession().setAttribute("messages", messages);
	}

	/**
	 * Convenience method to get the Configuration HashMap from the servlet
	 * context.
	 * 
	 * @return the user's populated form from the session
	 */
	@SuppressWarnings("rawtypes")
	public Map getConfiguration() {
		Map config = (HashMap) getSession().getServletContext().getAttribute(Constants.CONFIG);
		// so unit tests don't puke when nothing's been set
		if (config == null) {
			return new HashMap();
		}
		return config;
	}

	/**
	 * Convenience method to get the request
	 * 
	 * @return current request
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * Convenience method to get the response
	 * 
	 * @return current response
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * Convenience method to get the session. This will create a session if one
	 * doesn't exist.
	 * 
	 * @return the session from the request (request.getSession()).
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * Convenience method to send e-mail to users
	 * 
	 * @param user
	 *            the user to send to
	 * @param msg
	 *            the message to send
	 * @param url
	 *            the URL to the application (or where ever you'd like to send
	 *            them)
	 */
	public void sendUserMessage(B2BUser user, String msg, String url) {

		// mailMessage.setTo(user.getFullName() + "<" + user.getEmail() + ">");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		// TODO: figure out how to get bundle specified in struts.xml
		// model.put("bundle", getTexts());
		model.put("message", msg);
		model.put("applicationURL", url);
		mailEngine.sendMessage(mailMessage, templateName, model);
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public void setMailEngine(MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * Convenience method for setting a "from" parameter to indicate the
	 * previous page.
	 * 
	 * @param from
	 *            indicator for the originating page
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public void setSave(String save) {
		this.save = save;
	}

	// 项目配置属性
	protected Properties projectProperties;

	@Resource
	protected PropertiesUtil propertyConfigurer;

	@Resource
	public void setProjectProperties(Properties projectProperties) {
		this.projectProperties = projectProperties;
	}

	// 当前用户

	private B2BUser curLoginUser = null;

	protected B2BUser getCurLoginUser() {
		if (curLoginUser == null) {
			SecurityContext sc = SecurityContextHolder.getContext();
			Authentication auth = sc.getAuthentication();
			if (auth != null && auth.getPrincipal() instanceof B2BUser) {
				curLoginUser = (B2BUser) auth.getPrincipal();
			} else {
				SecurityContextImpl sci = (SecurityContextImpl) getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				if (sci != null) {
					curLoginUser = (B2BUser) sci.getAuthentication().getPrincipal();
				}
			}
		}
		return curLoginUser;
	}

	public void prepareSearchCriteria(SearchCriteria criteria, String tableId) {
		if (criteria != null) {
			int pageNum = this.getCurrentPageNo(tableId);
			criteria.setPageNum(pageNum);
			String sortBy = getSortBy(tableId);
			if (sortBy != null) {
				criteria.setSortBy(sortBy);
				criteria.setDesc(getOrder(tableId));
			}
		}
	}

	public String getSortBy(String tableId) {
		String key = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_SORT);
		if (StringUtils.isNotEmpty(getRequest().getParameter(key))) {
			String sortBy = getRequest().getParameter(key);
			return sortBy;
		}
		return null;
	}

	public String getOrder(String tableId) {
		String order = null;
		String key = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_ORDER);
		if (StringUtils.isNotEmpty(getRequest().getParameter(key))) {
			order = getRequest().getParameter(key);
		}
		if ("1".equals(order)) {
			return SearchCriteria.DESC;
		} else if ("2".equals(order)) {
			return SearchCriteria.ASC;
		} else {
			return SearchCriteria.DESC;
		}
	}

	// 取得当前页

	public int getCurrentPageNo(String tableId) {
		String pageId = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_PAGE);

		// 从请求中取得当前页，如不存在设置第一页为默认页
		int pageNumber = 1;
		if (StringUtils.isNotEmpty(getRequest().getParameter(pageId))) {
			pageNumber = Integer.parseInt(getRequest().getParameter(pageId));
		}
		return pageNumber;
	}

	public void ajaxResponse(String text) {
		try {
			getResponse().getWriter().print(text);
			getResponse().getWriter().flush();
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, new String[] { "mc" }));
		}
	}

	public void ajaxResponse(String text, String contentType){
		getResponse().setContentType(contentType);
		this.ajaxResponse(text);
	}

	public void ajaxResponse(AjaxResponse ajaxResponse) throws Exception {
		getResponse().getWriter().print(ajaxResponse.toString());
		getResponse().getWriter().flush();
	}

	// 取得每页多少条
	public int getCurrentPageSize(String tableId) {
		int pageNumber = Constants.PAGE_SIZE;
		return pageNumber;
	}

	public void writeMessage(boolean success, String message) {
		try {
			StringBuilder str = new StringBuilder();
			str.append("{success:").append(success).append(",").append("message:'").append(message).append("'}");
			getResponse().setContentType("text/plain; charset=utf-8");

			getResponse().getWriter().write(str.toString());
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
		}
	}

	public void writeMessage(Integer flag, String message) {
		try {
			StringBuilder str = new StringBuilder();
			str.append("{success:").append(flag).append(",").append("message:'").append(message).append("'}");
			getResponse().setContentType("text/plain; charset=utf-8");

			getResponse().getWriter().write(str.toString());
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
		}
	}

	/**
	 * ajax请求时输出返回信息并返回给客户端
	 * 
	 * @param info
	 * @return
	 * @throws IOException
	 */
	protected String ajaxReturn(Object info, String success) throws IOException {
		JSONObject jo = new JSONObject();
		jo.put("data", info);
		jo.put("success", success);
		getResponse().getWriter().print(jo.toString());
		getResponse().getWriter().flush();
		return null;
	}
}

