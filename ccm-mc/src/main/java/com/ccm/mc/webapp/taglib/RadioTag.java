package com.ccm.mc.webapp.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccm.api.model.base.LabelValue;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.service.common.DictCodeManager;

public class RadioTag extends TagSupport {
	private static final long serialVersionUID = 5653520480841035698L;

	/**
	 * RADIO控件ID
	 */
	private String id;
	/**
	 * RADIO控件name
	 */
	private String name;
	/**
	 * RADIO控件style
	 */
	private String style;
	private String cssClass;
	/**
	 * RADIO控件onclick事件
	 */
	private String onclick;
	/**
	 * RADIO控件onchange事件
	 */
	private String onchange;
	/**
	 * RADIO控件显示列表
	 */
	private String list;
	/**
	 * RADIO控件默认值
	 */
	private String defaultValue = "0";
	/**
	 * RADIO控件:代码字典key（通过代码字典显示）
	 */
	private String dictKey;
	/**
	 * RADIO控件:排序键
	 */
	private String sortKey;
	/**
	 * RADIO控件:排序键
	 */
	private String firstText;

	private StringBuffer sb;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setList(String list) {
		this.list = list;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public void setFirstText(String firstText) {
		this.firstText = firstText;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * Process the start of this tag.
	 * 
	 * @return int status
	 * @exception JspException
	 *                if a JSP exception has occurred
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		sb = new StringBuffer();
		// 设置select的value和text
		fillDefaultValueObj();
		// 设置RADIO的value和text
		constractValue();

		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException io) {
			throw new JspException(io);
		}

		return super.doStartTag();
	}

	private void constractValue() {

		if (StringUtils.isNotEmpty(list)) {
			List<LabelValue> templist = null;
			if (pageContext.getRequest().getAttribute(list) == null) {
				if (pageContext.getSession().getAttribute(list) == null) {
					templist = null;
				} else {
					templist = (List) pageContext.getSession().getAttribute(list);
				}
			} else {
				templist = (List) pageContext.getRequest().getAttribute(list);
				pageContext.getSession().setAttribute(list, templist);
			}
			int i = 0;
			for (LabelValue labelValue : templist) {
				fillHeader();
				sb.append("value='");
				sb.append(labelValue.getValue());
				sb.append("' ");
				if (this.defaultValue != null && this.defaultValue.equals(labelValue.getValue())) {
					sb.append("checked");
				}
				sb.append("/>");

				sb.append(labelValue.getLabel());
				i++;
				if (i % 6 == 0) {
					sb.append("<br>");
				}
				fillFooter();
			}
		} else if (StringUtils.isNotEmpty(dictKey)) {
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
			DictCodeManager dictCodeManager = (DictCodeManager) ctx.getBean("dictCodeManager");

			Locale userLocale = pageContext.getRequest().getLocale();
			StringBuffer location = new StringBuffer();
			location.append(userLocale.getLanguage());
			if (StringUtils.isNotEmpty(userLocale.getCountry())) {
				location.append("_");
				location.append(userLocale.getCountry());
			}

			List<DictCode> codes = dictCodeManager.searchByDictName(dictKey);// /dictCodeManager.searchByDictName(dictKey,
			// sortKey);
			int i = 0;
			for (DictCode code : codes) {
				fillHeader();
				sb.append("value='");
				sb.append(code.getCodeNo());
				sb.append("' ");
				if (this.defaultValue != null && this.defaultValue.equals(code.getCodeNo())) {
					sb.append("checked");
				}
				sb.append("/>");

				sb.append(code.getCodeLabel());
				i++;
				if (i % 6 == 0) {
					sb.append("<br>");
				}
				fillFooter();
			}

		} else {
			// TODO
		}
	}

	private void fillHeader() {
		sb.append("<label><input type='radio' ");
		// 添加name属性
		if (this.name != null) {
			sb.append("name='" + this.name + "' ");
		}
		// 添加style属性
		if (this.style != null) {
			sb.append("style='" + this.style + "' ");
		}
		if (this.cssClass != null) {
			sb.append("class='" + this.cssClass + "' ");
		}
		// 添加onchange事件
		if (this.onchange != null) {
			sb.append("onchange='" + this.onchange + "' ");
		}
		// 添加onclick事件
		if (this.onclick != null) {
			sb.append("onclick='" + this.onclick + "' ");
		}
	}

	private void fillFooter() {
		sb.append("</label>");
	}

	private void fillDefaultValueObj() {
		if (null == this.name) {
			return;
		}
		Object currentValue = null;

		currentValue = this.pageContext.getRequest().getAttribute(this.name);
		if (null == currentValue) {
			currentValue = pageContext.getRequest().getParameter(this.name);
		}
		if (null != currentValue) {
			this.defaultValue = currentValue.toString();
		} else {
			// this.defaultValue = "";
		}

	}
}
