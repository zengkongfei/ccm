<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<link href="/css/site_co.css" rel="stylesheet" media="screen">
<jsp:include page="/pages/user/userType.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {

		$("#departmentChosen").find("option").each(function() {
			if ($(this).val() == '${b2bUserCriteria.dept}') {
				$(this).attr("selected", true);
				showTitle();
			}
		});

		$("#positionChosen").find("option").each(function() {
			if ($(this).val() == '${b2bUserCriteria.title}') {
				$(this).attr("selected", true);
			}
		});

		$('#departmentChosen').bind('change', function() {
			showTitle();
		});

		$("a.nob_searchbt").click(function() {
			$('input.formnormal').val('');
			$("select.formnormal").find("option:selected").each(function() {
				if ($(this).parent().get(0).selectedIndex != 0) {
					$(this).parent().get(0).selectedIndex = 0;
				}
			});
			$("select.formnormal").find("option:selected").each(function() {
				if ($(this).parent().get(0).selectedIndex != 0) {
					$(this).parent().get(0).selectedIndex = 0;
				}
			});
		})

	});

	function showTitle() {
		var dp_1 = new Array('', '总经理', '助理');
		var dp_2 = new Array('', '高级总监', '总监', '大区经理', '高级销售经理', '销售经理',
				'房控专员', '销售支持');
		var dp_3 = new Array('', '总监', '运营经理', '运营专员', '客服经理', '客服专员', '结算专员');
		var dp_4 = new Array('', '总监', '媒体与拓展经理', '市场主管', '市场策划', 'seo经理',
				'seo专员');
		var dp_5 = new Array('', '总监', '副总监', 'ued经理', '产品经理', '产品助理', '交互设计师',
				'视觉设计师', '前端工程师');
		var dp_6 = new Array('', '总监', '资深开发工程师', '开发工程师', '资深开发主管', '开发主管',
				'开发经理', '测试工程师');
		var dp_7 = new Array('', '总编', '副总编', '主编', '编辑');
		var dp_8 = new Array('', '财务经理', '财务专员');

		var $posit = $('#positionChosen');
		var $i = $('#departmentChosen option:selected').val();
		function ShowPosion(v) {
			for (i = 0; i < v.length; i++) {
				$posit.append("<option value="+v[i]+">" + v[i] + "</option>");
			}
		}
		$posit = $('#positionChosen').empty();
		switch ($i) {
		case '总经办':
			ShowPosion(dp_1);
			break;
		case '销售部':
			ShowPosion(dp_2);
			break;
		case '运营部':
			ShowPosion(dp_3);
			break;
		case '市场部':
			ShowPosion(dp_4);
			break;
		case '产品部':
			ShowPosion(dp_5);
			break;
		case '技术部':
			ShowPosion(dp_6);
			break;
		case '编辑部':
			ShowPosion(dp_7);
			break;
		case '财务部':
			ShowPosion(dp_8);
			break;
		}
	}

	function doSearch() {
		document.searchForm.action = '<c:url value="/${prefix}_list.do?from=${param.from}"/>';
		document.searchForm.submit();
	}

	function delUser(url) {
		if (confirm("<fmt:message key='ccm.SupervisorOperator.deleteMessage'/>")) {
			location.href = url;
		}
	}
	
	function unlockUser(url) {
		if (confirm(resources.UnlockUser+"?")) {
			location.href = url;
		}
	}
	
</script>
<%@ include file="/common/messages.jsp"%>
<div class="title_wp">
	<div class="bt">
		<a href="/${prefix}_edit.do?from=${param.from}" class="btn_2 blue"><fmt:message key="common.button.New"/></a>
	</div>
	<s:if test="from==4">
		<fmt:message key="ccm.channelUser.channelUser"/>
	</s:if>
	<s:if test="from==2">
		<fmt:message key="ccm.HotelUser"/>
	</s:if>
	<s:elseif test="#attr.SPRING_SECURITY_CONTEXT.authentication.principal.companyId.equals(\"4\")||#attr.SPRING_SECURITY_CONTEXT.authentication.principal.companyId.equals(\"2\")">
		用户管理
	</s:elseif>
	<s:else>
	<fmt:message key="ccm.SupervisorOperator"/>
	</s:else>
</div>
<div class="c_whitebg">
	<s:form id="searchForm" action="" method="post">
		<!--搜索项-->
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="login.Username"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="b2bUserCriteria.username" size="30" maxlength="120" cssClass="fxt w120" />
					</div>
				</li>

				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.SupervisorOperator.Name"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="b2bUserCriteria.name" size="30" maxlength="120" cssClass="fxt w120" />
					</div>
				</li>
				<s:if test="from!=2 && from!=4 ">
					<li class="col3_1">
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.SupervisorOperator.MobilePhone"/></span>
						</div>
						<div class="i_input">
							<s:textfield key="b2bUserCriteria.mobile" size="30" maxlength="120" cssClass="fxt w120" />
						</div>
					</li>
					<li class="col3_1">
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.SupervisorOperator.Department"/></span>
						</div>
						<div class="i_input">
							<s:textfield key="b2bUserCriteria.dept" cssClass="fxt w120" />
						</div>
					</li>
					<li class="col3_1">
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.SupervisorOperator.Title"/></span>
						</div>
						<div class="i_input">
							<s:textfield key="b2bUserCriteria.title" cssClass="fxt w120" />
						</div>
					</li>
				</s:if>
			</ul>

			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12" onclick="javascript:doSearch();"><fmt:message key="common.button.searchSelect"/>	</button>
				<button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
			</div>
		</div>
	</s:form>
	<%
		int i = 1;
	%>
	<div class="bt_wp">
		<display:table name="b2bUserSearchResult.resultList" id="b2buser" class="ccm_table1" requestURI="/${prefix}_list.do" pagesize="20" size="b2bUserSearchResult.totalCount" partialList="true">

			<display:column property="username" sortable="true" titleKey="login.Username" headerClass="sorted"/>
			<display:column property="name" sortable="true" titleKey="ccm.SupervisorOperator.Name" headerClass="sorted"/>
			<display:column property="dept" sortable="true" titleKey="ccm.SupervisorOperator.Department" headerClass="sorted"/>
			<s:if test="from!=2 && from!=4">
				<!-- 运营平台  -->
				<display:column property="title" sortable="true" titleKey="ccm.SupervisorOperator.Title" headerClass="sorted"/>
				<display:column property="telNo" sortable="true" titleKey="ccm.PropertyList.Tel" headerClass="sorted"/>
				<display:column property="mobile" sortable="true" titleKey="ccm.SupervisorOperator.MobilePhone" headerClass="sorted"/>
				<display:column property="qq" sortable="true" title="QQ" headerClass="sorted"/>
			</s:if>
				<display:column property="email" sortable="true" titleKey="common.Email" headerClass="sorted"/>
			<s:else>
				<display:column property="telNo" sortable="true" titleKey="ccm.HotelUser.ContactWay" headerClass="sorted"/>
			</s:else>
			<display:column titleKey="common.button.Activity">
				<c:if test="${b2bUser.userId != b2buser.userId}">
					<a href="/${prefix}_edit.do?userId=${b2buser.userId}&from=${param.from}"><fmt:message key="ccm.SupervisorOperator.AuthorityManagement"/></a>
				</c:if>
			 	
				&nbsp;&nbsp;<a href="/${prefix}_editPwd.do?userId=${b2buser.userId}&from=${param.from}"><fmt:message key="ccm.SupervisorOperator.Message.002"/></a>

				<c:if test="${b2bUser.userId != b2buser.userId}">
			        &nbsp;&nbsp;<a onclick="delUser('/${prefix}_delete.do?userId=${b2buser.userId}&b2bUser.username=${b2buser.username}&from=${param.from}')" href="javascript:"><fmt:message key="common.button.delete"/></a>
				</c:if>
				
				<c:if test="${(b2bUser.userId != b2buser.userId)&&(b2buser.islocak==true)&&(b2buser.isAdmin==true)}">
			        &nbsp;&nbsp;
			        <a style="color:red" onclick="unlockUser('/${prefix}_unlock.do?userId=${b2buser.userId}&b2bUser.username=${b2buser.username}&from=${param.from}')" href="javascript:">
			        <fmt:message key="ccm.password.Unlock"/>
			        </a>
				</c:if>
				
			</display:column>
		</display:table>
	</div>
</div>