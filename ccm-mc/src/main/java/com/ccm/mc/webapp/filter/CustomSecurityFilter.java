package com.ccm.mc.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest
;import javax.servlet.ServletResponse;

import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
 /**    
 * ClassName:CustomFilter    
 * Function: TODO ADD FUNCTION    
*    
* @author   sux    
* @version  1.0, 2011-8-16    
* @since    ss31.0    
 */  
  public class CustomSecurityFilter extends AbstractSecurityInterceptor implements Filter{  
      
    private FilterInvocationSecurityMetadataSource securityMetadataSource;  
      
    @Override  
    public Class<? extends Object> getSecureObjectClass() {  
        return FilterInvocation.class;     
    }  
  
    @Override  
    public FilterInvocationSecurityMetadataSource obtainSecurityMetadataSource() {  
        return this.securityMetadataSource;     
    }  
  
    public void destroy() {  
    }  
  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,  
            ServletException {  
          FilterInvocation fi = new FilterInvocation(request, response, chain);  
          InterceptorStatusToken token = super.beforeInvocation(fi);  
          try{  
              fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
          }finally{  
              super.afterInvocation(token, null);  
          }  
    }  
  
    public void init(FilterConfig arg0) throws ServletException {  
    }  
  
    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {  
        return securityMetadataSource;  
    }  
  
    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {  
        this.securityMetadataSource = securityMetadataSource;  
    }  
} 
