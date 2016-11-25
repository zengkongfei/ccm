package com.ccm.mc.webapp.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AreaLabelTag extends TagSupport {
	private static final long serialVersionUID = 4756177930333686830L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 字典名称
	 */
	private String dictKey;
	/**
	 * 代码编号
	 */
	private String codeNo;
	/**
	 * 式样
	 */
	private String style;

	private StringBuffer sb;

	public void setId(String id) {
		this.id = id;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * FUNCTION : Process the start of this tag.
	 * 
	 * @param :
	 * @return : int status
	 * @exception : JspException if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {
		sb = new StringBuffer("<label ");
		// 添加ID
		if (this.id != null) {
			sb.append("id='" + this.id + "' ");
		}
		// 添加式样
		if (this.style != null) {
			sb.append("style='" + this.style + "' ");
		}
		sb.append(">");

		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());

		// 设置select的value和text
		sb.append("</label>");

		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException io) {
			throw new JspException(io);
		}

		return super.doStartTag();
	}
}
