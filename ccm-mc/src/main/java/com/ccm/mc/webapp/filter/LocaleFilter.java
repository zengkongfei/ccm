package com.ccm.mc.webapp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ccm.api.Constants;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.util.SpringContextUtil;

/**
 * Filter to wrap request with a request including user preferred locale.
 */
public class LocaleFilter extends OncePerRequestFilter {

	private static  HotelManager hotelManager = null;
	
	
    /**
     * This method looks for a "locale" request parameter. If it finds one, it sets it as the preferred locale
     * and also configures it to work with JSTL.
     * 
     * @param request the current request
     * @param response the current response
     * @param chain the chain
     * @throws IOException when something goes wrong
     * @throws ServletException when a communication failure happens
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain chain)
            throws IOException, ServletException {

        String locale = request.getParameter("locale");
        Locale preferredLocale = null;
        
        if (locale != null) {
            int indexOfUnderscore = locale.indexOf('_');
            if (indexOfUnderscore != -1) {
                String language = locale.substring(0, indexOfUnderscore);
                String country = locale.substring(indexOfUnderscore + 1);
                preferredLocale = new Locale(language, country);
            } else {
                preferredLocale = new Locale(locale);
            }
        }
        
        HttpSession session = request.getSession(false);//If create is false and the request has no valid HttpSession, this method returns null. 
        String hotelIdFormHidden = request.getParameter("hotelIdFormHidden");
        if(null!=session){
        	SecurityContextImpl sci = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if(null!=sci){
            	Authentication authentication = sci.getAuthentication();
            	 if(null!=authentication){
            		 Object obj=authentication.getPrincipal();
            		 if(null!=obj){
            	       	B2BUser curLoginUser = (B2BUser) obj;
                     	if(null!=curLoginUser){
                     		HotelVO hvo=curLoginUser.getHotelvo();
                            if(hvo!=null){
                            	 String hotelIdFromSession = hvo.getHotelId();
                                 if(null!=hotelIdFormHidden&&null!=hotelIdFromSession){
                              	   if(!hotelIdFormHidden.equalsIgnoreCase(hotelIdFromSession)){
                              		    if(hotelManager==null){
                              		    	hotelManager=SpringContextUtil.getBean(HotelManager.class);
                              		    }
	                              		Locale localeNew = (Locale) session.getAttribute(Constants.PREFERRED_LOCALE_KEY);
	                             		String language = null;
	                             		if(null!=localeNew){
	                             			language = localeNew.getLanguage()+"_"+localeNew.getCountry();
	                             		}
	                             			
	                             		if(!hotelIdFormHidden.equalsIgnoreCase(hvo.getHotelId())){
	                             			HotelVO hvoNew = null;
	                             			if(null!=language){
	                             				language="zh_CN";
	                             			}
	                             			hvoNew = hotelManager.getHotelI18nChainByHotelId(hotelIdFormHidden,language);
	                             			if(null!=hvoNew){
	                             				curLoginUser.setHotelvo(hvoNew);
	                             				List<HotelVO> hotelVOs=new ArrayList<HotelVO>();
	                             				hotelVOs.add(hvoNew);
	                             				curLoginUser.setHotelVOs(hotelVOs);
	                             			}
	                             		}
                                    }
                                 }
                            }
                     	}
            		 }
            	 }
            	
            }
        }
        
    	if (session != null) {
            if (preferredLocale == null) {
                preferredLocale = (Locale) session.getAttribute(Constants.PREFERRED_LOCALE_KEY);
                if(null!=preferredLocale){
                	Config.set(session, Config.FMT_LOCALE, preferredLocale);
                }
            } else {
            	if(null!=preferredLocale){
            		 session.setAttribute(Constants.PREFERRED_LOCALE_KEY, preferredLocale);
            		 Config.set(session, Config.FMT_LOCALE, preferredLocale);
            	}
            }

            if (preferredLocale != null && !(request instanceof LocaleRequestWrapper)) {
                request = new LocaleRequestWrapper(request, preferredLocale);
                LocaleContextHolder.setLocale(preferredLocale);
            }
        }
        //验证请求关键字
        this.checkParam(request, response);
        
        String theme = request.getParameter("theme");
        if (theme != null && request.isUserInRole(Constants.ADMIN_ROLE)) {
            Map<String, Object> config = (Map) getServletContext().getAttribute(Constants.CONFIG);
            config.put(Constants.CSS_THEME, theme);
        }

        chain.doFilter(request, response);

        // Reset thread-bound LocaleContext.
        LocaleContextHolder.setLocaleContext(null);
    }
    
    private void checkParam(HttpServletRequest req, HttpServletResponse response) throws IOException{
    	// 获得所有请求参数名
		Enumeration params = req.getParameterNames();
		String reg = "(\\b(delete|update|drop|exec|truncate|insert)\\b)";// 过滤掉的sql关键字
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

		while (params.hasMoreElements()) {
			String name = params.nextElement().toString();
			String[] value = req.getParameterValues(name);

			for (int i = 0; i < value.length; i++) {
				String str = value[i].toLowerCase();
				if (sqlPattern.matcher(str).find()) {

					response.sendRedirect("/error.jsp");
					throw new BizException("request error!");
				}
			}
		}
   }

	public HotelManager getHotelManager() {
		return hotelManager;
	}
	@SuppressWarnings("static-access")
	public void setHotelManager(HotelManager hotelManager) {
		this.hotelManager = hotelManager;
	}
  
}
