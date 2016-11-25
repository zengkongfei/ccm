<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="title_wp"><fmt:message key="ccm.UserActivityLog"/></div>
<div class="c_whitebg">
	<div class="bt_wp">
		<table class="ccm_table1" style="word-break:break-all;">
			<thead>
				<tr>
					<!-- <th width="4%"><span>类名</span>
					</th> -->
					<th width="30%"><span><fmt:message key="ccm.UserActivityLog.AttributeName"/></span>
					</th>
					<!-- <th width="4%"><span>业务主键</span>
					</th> -->
					<th width="40%"><span><fmt:message key="ccm.UserActivityLog.NewValue"/></span>
					</th>
					<th width="30%"><span><fmt:message key="ccm.UserActivityLog.OldValue"/></span>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${detailList}" var="detail">
					<c:if test='${detail.attributeName!="token" && detail.attributeName!="struts.token.name" && (detail.urlId!="7103" || (detail.urlId=="7103" && !(fn:startsWith(detail.attributeName,"order"))))}'>
					
					<c:if test='${(detail.urlId!="705" || (detail.urlId=="705" && (detail.attributeName ne "担保规则" && detail.attributeName ne "取消规则")))}'>
					<tr <c:if test="${detail.oldValue!=null && detail.newValue!=detail.oldValue}">style="color:red"</c:if>>
						<!-- <td>${detail.className}</td> -->
						<td>${detail.attributeName}</td>
						<!-- <td>${detail.primaryKey}</td> -->
						<td>${detail.newValue}</td>
						<td>${detail.oldValue}</td>
					</tr>
					</c:if>
					
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>