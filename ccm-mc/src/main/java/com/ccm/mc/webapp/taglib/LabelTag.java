package com.ccm.mc.webapp.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccm.api.model.common.DictCode;
import com.ccm.api.service.common.DictCodeManager;

public class LabelTag extends TagSupport {
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
	@SuppressWarnings("unchecked")
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
		DictCodeManager dictCodeManager = (DictCodeManager) ctx.getBean("dictCodeManager");

		DictCode dictCode = dictCodeManager.searchByCodeNo(dictKey, codeNo);

		if (codeNo.indexOf(",") > 0) {

			List<DictCode> codes = dictCodeManager.searchByDictName(dictKey);

			Map codeStr = new HashMap();
			if (codes != null && codes.size() > 0) {
				for (int i = 0; i < codes.size(); i++) {
					DictCode dc = codes.get(i);
					codeStr.put(dc.getCodeNo(), dc.getCodeLabel());
				}

			}

			String[] codeNoStr = codeNo.split(",");
			for (int i = 0; i < codeNoStr.length; i++) {
				String value = (String) codeStr.get(codeNoStr[i].trim());
				if (value != null) {
					if (i == 0) {
						sb.append(value);
					} else {
						sb.append("&nbsp;").append(value);
					}
				}
			}

		} else {

			if (dictCode != null) {
				Locale userLocale = pageContext.getRequest().getLocale();
				StringBuffer location = new StringBuffer();
				location.append(userLocale.getLanguage());
				if (StringUtils.isNotEmpty(userLocale.getCountry())) {
					location.append("_");
					location.append(userLocale.getCountry());
				}
				sb.append(dictCode.getCodeLabel());

			}
		}

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
