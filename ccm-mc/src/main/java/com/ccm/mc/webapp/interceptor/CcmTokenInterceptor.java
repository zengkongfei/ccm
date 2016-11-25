/**
 * 
 */
package com.ccm.mc.webapp.interceptor;

import java.util.HashSet;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.TokenInterceptor;
import org.springframework.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 
 */
public class CcmTokenInterceptor  extends MethodFilterInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8954961691989004342L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

	Map session = ActionContext.getContext().getSession();
	ActionContext ac = invocation.getInvocationContext();
	HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
	String tokenTag = request.getParameter("ccm.tokens.token");//获取s:token name value
	if(StringUtils.hasLength(tokenTag)){
		synchronized (session) {
			HashSet<String> o = (HashSet<String>) session.get("ccm.tokens.token");
			if(o != null){
				if(o.contains(tokenTag)){
					o.remove(tokenTag);
					return invocation.invoke();
				}else{
					request.setAttribute("errMsg", "请勿重复提交");
					return TokenInterceptor.INVALID_TOKEN_CODE;
				}
			}
		}
		request.setAttribute("errMsg", "请勿重复提交");
		return TokenInterceptor.INVALID_TOKEN_CODE;
	}
		return invocation.invoke();
	}
}
