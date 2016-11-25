package com.ccm.mc.webapp.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccm.api.model.common.DictCode;
import com.ccm.api.service.common.DictCodeManager;

public class SpanTag extends TagSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1096837127057241874L;
	/**
	 * 字典名称
	 */
	private String dictKey;
	/**
	 * 代码编号
	 */
	private String codeNo;
	

	public void setId(String id) {
		this.id = id;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}


	/**
	 * FUNCTION : Process the start of this tag.
	 * 
	 * @param :
	 * @return : int status
	 * @exception : JspException if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
		DictCodeManager dictCodeManager = (DictCodeManager) ctx.getBean("dictCodeManager");

		DictCode dictCode = dictCodeManager.searchByCodeNo(dictKey, codeNo);		

		try {
			if(dictCode!=null){
				pageContext.getOut().write(dictCode.getCodeLabel());
			}
			else{
				pageContext.getOut().write("");
			}
			
		} catch (IOException io) {
			throw new JspException(io);
		}

		return super.doStartTag();
	}
}
