package com.ccm.mc.webapp.taglib;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义的分页标签
 * 
 * @jsp.tag name="simplePage" bodycontent="empty"
 */
public class SimplePageTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8818362530538280059L;

	// tag param
	private String totalSizeKey; // total num saved destination

	private String styleClass; // css style Name

	private String requestUrl = null;

	// pageVar and function
	private int total = 0; // total record

	private int pageSize = 10; // size of one page

	private int curPage = 1; // current page

	/**
	 * @param name
	 *            The name to set.
	 * 
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * @param name
	 *            The name to set.
	 * 
	 * @jsp.attribute required="true" rtexprvalue="true"
	 */
	public void setTotalSize(String totalSizeKey) {
		this.totalSizeKey = totalSizeKey;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	/**
	 * @param name
	 *            The name to set.
	 * 
	 * @jsp.attribute required="true" rtexprvalue="true"
	 */
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public int doStartTag() throws JspException {

		// 提取分页参数
		Integer totalStr = (Integer) pageContext.getRequest().getAttribute(totalSizeKey);
		String curPageStr = (String) pageContext.getRequest().getParameter("curPage");

		if (totalStr != null) {
			total = totalStr.intValue();
		}

		if (pageSize == 0) {
			pageSize = 10;
		}
		if (curPageStr != null) {
			curPage = Integer.parseInt(curPageStr);

		} else {
			curPage = 1;
		}

		// 请求URL及参数
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		String paramUrl = populatRequestUrl(request);
		String firstUrl = requestUrl + "?curPage=1&pageSize=" + pageSize + "&total=" + total + paramUrl;
		String preUrl = requestUrl + "?curPage=" + (curPage - 1) + "&pageSize=" + pageSize + "&total=" + total + paramUrl;
		String nextUrl = requestUrl + "?curPage=" + (curPage + 1) + "&pageSize=" + pageSize + "&total=" + total + paramUrl;
		String lastUrl = requestUrl + "?curPage=" + getTotalPage() + "&pageSize=" + pageSize + "&total=" + total + paramUrl;

		if (isFirst()) {
			firstUrl = "#";
			preUrl = "#";
		}

		if (isLast()) {
			nextUrl = "#";
			lastUrl = "#";
		}

		if (total > 0) {

			int num_display_entries = 4;
			int np = getTotalPage();
			int upper_limit = np - num_display_entries;
			int[] arr = new int[2];
			if (curPage < 4 || np < 5) {
				arr[0] = 1;
				if (np < 5) {
					arr[1] = np;
				} else {
					arr[1] = 4;
				}
			} else {
				int start = curPage > 1 ? Math.max(Math.min(curPage - 1, upper_limit), 1) : 1;
				int end = curPage > 2 ? Math.min(curPage + 2, np) : Math.min(num_display_entries, np);
				arr[0] = start;
				arr[1] = end;
			}

			StringBuffer sb = new StringBuffer();
			for (int i = arr[0]; i <= arr[1]; i++) {
				if (curPage == i) {
					sb.append(i).append("&nbsp;&nbsp;");
				} else {
					sb.append("<a href='");
					sb.append(requestUrl).append("?curPage=").append(i).append("&pageSize=").append(pageSize).append("&total=").append(total).append(paramUrl);
					sb.append("'>");
					sb.append(i);
					sb.append("</a>&nbsp;&nbsp;");
				}
			}

			String pageNav = "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0'><tr bgcolor='#D9D9D9'><td width='1%' height='28'></td>" + "<td width='38%' height='28' align='left'>共找到" + total + "条记录。</td><td width='60%' height='28' align='right'>[<a href='" + firstUrl + "'>首页</a>/<a href='" + preUrl + "'>上一页</a>]<strong>&nbsp;&nbsp;" + sb.toString() + "</strong>[<a href='" + nextUrl + "'>下一页</a>/<a href='" + lastUrl + "'>尾页</a>]&nbsp;&nbsp;跳转到第" + "<input name='jumpPageNo' id='jumpPageNo' size='1' type='text' style='width: 23px' value='" + curPage + "' onKeyDown='submitKeyClick();' />页" + "<input type='button' onClick='goToPage();' value='GO' />" + "</td><td width='1%'></td></tr></table>";

			/*
			 * String pageNav =
			 * "<table width='100%' border='0'><tr> <td width='100%' > \n" +
			 * "第"+getBeginRecordPos()+"-"+getEndRecordPos()+"条/共"+total+
			 * "条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n "+
			 * "第"+curPage+"页/共"+getTotalPage()+"页 \n"+
			 * "[<a href='"+firstUrl+"'>首页</a>&nbsp;<a href='"
			 * +preUrl+"'>上页</a>&nbsp;<a href='"
			 * +nextUrl+"'>下页</a>&nbsp;<a href='"+lastUrl+"'>尾页</a>]\n "+
			 * "到	<input name='curPage' type='text' id='curPage' size='2' maxlength='4' value='"
			 * +curPage+"' onblur='javascript:goPage(this.value)'>页 \n"+
			 * "每页<input name='pageSize' type='text' id='pageSize' size='2' maxlength='4' value='"
			 * +
			 * pageSize+"' onblur='javascript:changePageSize(this.value)'>条\n "+
			 * "</td></tr></table>  \n" ;
			 */

			String script = "<script type=\"text/javascript\" >\n" + "      function goToPage(){ \n" + "       var value=document.getElementById('jumpPageNo').value;\n" + "           if(value < 1 || value > " + getTotalPage() + ") {alert(\"页数必须在 1到 " + getTotalPage() + "之间 !\");return;} \n" + "		   var url = \"" + requestUrl + "?curPage=\"+value+\"&pageSize=" + pageSize + "&total=" + total + paramUrl + "\";\n" + "		    document.location.href=url;} \n" + "	     function submitKeyClick(){ \n" + "       var value=document.getElementById('pageSize').value;\n" + "          if(value < 1 || value >" + getTotalPage() + "){alert(\"页数必须在 1到 " + getTotalPage() + "之间 !\");return;} \n" + "		   var url =  \"" + requestUrl + "?curPage=" + curPage + "&pageSize=\"+value+\"&total=" + total + paramUrl
					+ "\";\n" + "		   document.location.href=url;}  </script> \n";

			try {
				if (pageNav != null) {
					JspWriter out = pageContext.getOut();
					out.print(pageNav + script);
				}
			} catch (Exception e) {
				throw new JspException(e);
			}
		}

		return EVAL_PAGE;
	}

	// 请求参数

	@SuppressWarnings("unchecked")
	private Map getRequestParam(ServletRequest request) {
		Enumeration names = request.getParameterNames();
		Map params = new HashMap();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String parameterValue = request.getParameter(name);
			params.put(name, parameterValue);
		}

		return params;
	}

	@SuppressWarnings("unchecked")
	private String populatRequestUrl(ServletRequest request) {
		String paramStr = "";
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String parameterValue = request.getParameter(name);

			if ("curPage".equals(name) || "pageSize".equals(name) || "total".equals(name)) {
			} else {
				paramStr = paramStr + "&" + name + "=" + parameterValue;
			}

		}

		return paramStr;
	}

	// 共多少页
	private int getTotalPage() {
		int mod = total % pageSize;
		if (mod == 0) {
			return (int) total / pageSize;
		} else {
			return (int) total / pageSize + 1;
		}
	}

	// // 开始条
	// private int getBeginRecordPos() {
	// return (curPage - 1) * pageSize + 1;
	// }
	//
	// // 结束条
	// private int getEndRecordPos() {
	// if (curPage == getTotalPage()) {
	// return total;
	// } else {
	// return curPage * pageSize;
	// }
	// }

	// 是否首页
	private boolean isFirst() {
		if (curPage == 1) {
			return true;
		} else {
			return false;
		}
	}

	// 是否尾页
	private boolean isLast() {
		if (curPage == getTotalPage()) {
			return true;
		} else {
			return false;
		}
	}

}