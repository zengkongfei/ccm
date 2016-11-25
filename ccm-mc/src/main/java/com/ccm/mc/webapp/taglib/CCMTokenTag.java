package com.ccm.mc.webapp.taglib;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * ccm 防止重复提交token， token存放session，粗放方式hashset
 * 
 * @author chay
 * 
 */
public class CCMTokenTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7793526184471957005L;
	private static final Random RANDOM = new Random();
	public static final String TOKEN_NAMESPACE = "ccm.tokens";
	Logger logger = LoggerFactory.getLogger(CCMTokenTag.class);
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		String token = buildToken();
		buildHtml(sb,token);
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return super.doStartTag();
	}

	/**
	 * 生成token,放入session 中的hashSet
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String buildToken() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String tokenSessionName = buildTokenSessionAttributeName(name);
		HashSet<String> o = null;
		Object obj = session.get(tokenSessionName);
		if(obj != null && obj instanceof Collection){
			o = new HashSet<String>((Collection<? extends String>) obj);
		}else{
			o = new HashSet<String>();
		}
		
		String token = generateGUID();
		o.add(token);
		session.put(tokenSessionName, o);
		return token;
	}

	/**
	 * 生成含有token 的 html
	 * @param sb
	 * @param token
	 */
	public void buildHtml(StringBuffer sb,String token) {
		String inputName = buildTokenSessionAttributeName(name);
		sb.append("<input type='hidden' name='"+inputName+"' value='"+token+"'/>");
	}

	public static String buildTokenSessionAttributeName(String tokenName) {
		return TOKEN_NAMESPACE + "." + tokenName;
	}

	/**
	 * 生成token
	 * 
	 * @return
	 */
	public static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}
}
