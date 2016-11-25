<%@page import="org.springframework.util.StringUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	
$(document).ready(function() {
	
	/*是否删除本条 setHotelIdForHref */
	$('.del_ifself').bind('click',function(){
		var codeListMappingId = $(this).attr('codeListMappingId');
		var dictId = $(this).attr('dictId');
		var r=confirm("<fmt:message key='ccm.ChannelMapping.ErrorMessage.Delete'/>");
		if (r==true){
			window.location.href=setHotelIdForHref("/convert_delete.do?codeMap.codeListMappingId="+codeListMappingId+"&dictId="+dictId);
		}
	});
	
});

	function addNew(dictId){
		$('#AddNewElement').load('/convert_edit.do?dictId='+dictId);
	}
	
	function edit(codeMapId,dictId){
		$('#EditNewElement').load('/convert_edit.do?codeMap.codeListMappingId='+codeMapId+'&dictId='+dictId);
	}
	/*
	function delUser(url) {
		if (confirm("<fmt:message key='ccm.ChannelMapping.ErrorMessage.Delete'/>")) {
			location.href = url;
		}
	}
	*/
	function copyCodeMap(){
		$('#AddNewElement').load('/convert_showCurChainHotels.do');
	}
</script>
<%
	String tmenuId = request.getParameter("tmenuId");
	if (StringUtils.hasText(tmenuId)) {
		session.setAttribute("tmenuId", tmenuId);
	}
%>
<div class="CCMmainConter w1200">
	<div class="title_wp">
		<div class="bt">
			<s:if test='dictId!=null && dictId!=""'>
			<a href="#AddNewElement" onclick="javascript:addNew(${dictId});" class="btn_2 blue ccm-popup-click"><fmt:message key="common.button.New"/>	</a>
			</s:if>
			<s:else>
			<a href="#AddNewElement" onclick="javascript:copyCodeMap();" class="btn_2 blue ccm-popup-click"><fmt:message key='ccm.SupervisorOperator.Copy'/></a>
			</s:else>
		</div>
		<fmt:message key='ccm.ConversionCodes'/>
	</div>

	<!--左右两列-->
	<div class="ccm_2wp clearfix">
		<div class="ccm_left_r">
			<div class="lt_menu">
				<div class="title"><fmt:message key='ccm.ConversionCodes'/></div>
				<div class="n_overFlowY">
					<ul class="mlist"><s:set value="dictId" var="di"></s:set>
						<s:iterator value="#request.dictionary">
							<li><a href="/convert_list.do?tmenuId=<s:property value="#parameters.tmenuId[0]"/>&dictId=${dictId}" title="${dictName}" <s:if test="dictId==#di">class="selected"</s:if>><span>${remark}</span> </a></li>
						</s:iterator>
					</ul>
				</div>
			</div>
		</div>
		<div class="ccm_2col_r">
			<div class="ccm_right_r" style="min-height:513px;">
			<%@ include file="/common/messages.jsp"%>
				<!--表格-->

				<div class="bt_wp">
					<table class="ccm_table1" id="normalTB">
						<thead>
							<tr>
								<th><span>CCM Value</span></th>
								<th><span>PMS Value</span></th>
								<th class="w120"><span>PMS -> CCM</span></th>
								<th class="w120"><span>CCM -> PMS</span></th>
								<th class="w120"><fmt:message key='common.button.Activity'/></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="codeMapList">
								<tr>
									<td>${codeNo}</td>
									<td>${codeName}</td>
									<td>${external2ccm=='1'?'Y':'N'}</td>
									<td>${ccm2external=='1'?'Y':'N'}</td>
									<td>
									<a href="#EditNewElement" class="link ccm-popup-click" onclick="javascript:edit('${codeListMappingId}',${di})"><fmt:message key="common.button.edit"/></a> 
									<a href="javascript:;" codeListMappingId=${codeListMappingId} dictId=${di} class="link del_ifself"><fmt:message key="common.button.delete"/></a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>

				<!--编辑-->
				<div id="AddNewElement" class="ccm-popup width600 zoom-anim-dialog mfp-hide"></div>
				<div id="EditNewElement" class="ccm-popup width600 zoom-anim-dialog mfp-hide"></div>
			</div>
		</div>
	</div>
</div>