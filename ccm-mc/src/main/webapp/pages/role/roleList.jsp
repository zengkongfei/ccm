<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<link href="/css/site_co.css" rel="stylesheet" media="screen">
<script type="text/javascript">
	$(document).ready(function() {

	});

	
	function doSearch() {
		document.searchForm.action = '<c:url value="/role_list.do"/>';
		document.searchForm.submit();
	}

	function delRole(url) {
		if (confirm("<fmt:message key='ccm.SupervisorOperator.deleteMessage'/>")) {
			location.href = url;
		}
	}
	
</script>
<%@ include file="/common/messages.jsp"%>

<!-- title div & new function -->
<div class="title_wp">
	<div class="bt">
		<a href="/role_edit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a>
	</div>
	
	<fmt:message key="ccm.Role.roleAccess"/>
	
</div>


<div class="c_whitebg">
	<s:form id="searchForm" action="" method="post">
		<!--搜索项-->
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Role.roleCode"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="roleCriteria.roleCode" size="30" maxlength="120" cssClass="fxt w120" />
					</div>
				</li>

				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Role.roleName"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="roleCriteria.roleName" size="30" maxlength="120" cssClass="fxt w120" />
					</div>
				</li>
			</ul>

			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12" onclick="javascript:doSearch();"><fmt:message key="common.button.searchSelect"/>	</button>
				<button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
			</div>
		</div>
	</s:form>

	<div class="bt_wp">
		<display:table name="roleSearchResult.resultList" id="role" class="ccm_table1" requestURI="/role_list.do" pagesize="20" size="roleSearchResult.totalCount" partialList="true">

			<display:column property="name" sortable="true" titleKey="ccm.Role.roleCode" headerClass="sorted"/>
			<display:column property="cnName" sortable="true" titleKey="ccm.Role.roleName" headerClass="sorted"/>
			<display:column property="description" sortable="true" titleKey="ccm.Role.roleDesc" headerClass="sorted"/>
			
			<display:column titleKey="common.button.Activity">
					<a href="/role_edit.do?roleId=${role.roleId}"><fmt:message key="common.button.edit"/></a>
					&nbsp;&nbsp;
					<a onclick="delRole('/role_remove.do?roleId=${role.roleId}')" href="javascript:"><fmt:message key="common.button.delete"/></a>
			</display:column>
		</display:table>
	</div>
</div>