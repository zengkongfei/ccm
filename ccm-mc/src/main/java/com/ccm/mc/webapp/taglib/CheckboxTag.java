package com.ccm.mc.webapp.taglib;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccm.api.model.base.LabelValue;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.service.common.DictCodeManager;

public class CheckboxTag extends TagSupport {
	private static final long serialVersionUID = 5653520480841035698L;

	/**
	 * Checkbox控件ID
	 */
	private String id;
	/**
	 * Checkbox控件name
	 */
	private String name;
	/**
	 * Checkbox控件style
	 */
	private String style;
	/**
	 * Checkbox控件onclick事件
	 */
	private String onclick;
	/**
	 * Checkbox控件onchange事件
	 */
	private String onchange;
	/**
	 * Checkbox控件显示列表
	 */
	private String list;
	/**
	 * Checkbox控件默认值
	 */
	private String defaultValue = "0";
	/**
	 * Checkbox控件:代码字典key（通过代码字典显示）
	 */
	private String dictKey;
	/**
	 * Checkbox控件:排序键
	 */
	private String sortKey;
	private String cssClass;
	private StringBuffer sb;

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) throws JspException {
		// 对EL表达式的支持   
//        this.name = ExpressionEvaluatorManager.evaluate("name", name, Object.class, this, pageContext).toString();   
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
		//设置select的value和text
        fillDefaultValueObj();
		// 设置Checkbox的value和text
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
			for (LabelValue labelValue : templist) {
				fillHeader();
				sb.append("value='");
				sb.append(labelValue.getValue());
				sb.append("' ");
				if (this.defaultValue != null && this.defaultValue.equals(labelValue.getValue())) {
					sb.append("checked");
				}
				sb.append("/>");

				fillFooter(labelValue.getLabel());
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
			int i=0;
			for (DictCode code : codes) {
				fillHeader();
				sb.append("value='");
				sb.append(code.getCodeNo());
				sb.append("' ");
				if (this.defaultValue != null){
					String[] defValues = defaultValue.split(",");
					if(this.defaultValue.trim().equals(code.getCodeNo().trim())) {
						sb.append("checked");
					}
					
					if(defValues.length > 1){
						for (String str : defValues) {
							if(str.trim().equals(code.getCodeNo().trim())){
								sb.append("checked");
								break;
							}
						}
					}
				}
				sb.append("/>");
				fillFooter(code.getCodeLabel());
				i++;
				if(i%6==0){
					sb.append("<br>");
				}
			}

		} else {
			// TODO
		}
	}

	private void fillHeader() {
		sb.append("<input type='checkbox' ");
		// 添加name属性
		if (this.name != null) {
			sb.append("name='" + this.name + "' ");
		}
		if (this.id != null) {
			sb.append("id='" + this.id + "' ");
		}
		// 添加style属性
		if (this.style != null) {
			sb.append("style='" + this.style + "' ");
		}
		if(this.cssClass != null){
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

	private void fillFooter(String label) {
		sb.append("<label>");
		sb.append(label);
		sb.append("</label>");
	}
	private void fillDefaultValueObj() {
			if (null == this.name) {
	            return;
	        }
	        Object currentValue = null;
	        	
	    	currentValue = this.pageContext.getRequest().getAttribute(this.name); 
	    	if(null == currentValue){
	    		currentValue = pageContext.getRequest().getParameter(this.name);
	    	}
	        if (null != currentValue) {
	            this.defaultValue = currentValue.toString();
	        } else {
	        	this.defaultValue = "";
	        }
    }
}
