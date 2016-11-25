<%@page import="org.springframework.util.StringUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		/*是否删除本条 setHotelIdForHref */
		$('.del_ifself').bind('click',function(){
			
			var codeListMappingId = $(this).attr('codeListMappingId');
			var dictId = $(this).attr('dictId');
			var channelId = $(this).attr('channelId');
			
			var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>?");
			if (r==true){
				window.location.href=setHotelIdForHref(
						"/cMap_delete.do?codeMap.codeListMappingId="+codeListMappingId+"&dictId="+dictId+"&codeMap.channelId="+channelId);
			}
		});
		
	});
	
	function addNew(dictId){
		$('#AddNewElement').load('/cMap_edit.do?dictId='+dictId);
	}
	
	function edit(codeMapId,dictId){
		$('#EditNewElement').load('/cMap_edit.do?codeMap.codeListMappingId='+codeMapId+'&dictId='+dictId);
	}
	/*
	function delUser(url) {
		if (confirm("<fmt:message key='ccm.ChannelMapping.ErrorMessage.Delete'/>。")) {
			location.href = url;
		}
	}
	*/
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
			<a href="#AddNewElement" onclick="javascript:addNew(${dictId});" class="btn_2 blue ccm-popup-click"><fmt:message key="common.button.New"/></a>
		</div>
		<fmt:message key="ccm.ChannelMapping"/>
	</div>

	<!--左右两列-->
	<div class="ccm_2wp clearfix">
		<div class="ccm_left_r">
			<div class="lt_menu">
				<div class="title"><fmt:message key="ccm.ChannelMapping"/></div>
				<div class="n_overFlowY">
					<ul class="mlist"><s:set value="dictId" var="di"></s:set>
						<s:iterator value="#request.dictionary">
							<li><a href="/cMap_list.do?tmenuId=<s:property value="#parameters.tmenuId[0]"/>&dictId=${dictId}" title="${dictName}" <s:if test="dictId==#di">class="selected"</s:if>><span>${remark}</span> </a></li>
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
								<th><span><fmt:message key="ccm.ChannelMapping.Channel"/></span></th>
								<th><span>CCM Value</span></th>
								<th><span>Channel Value</span></th>
								<th class="w120"><fmt:message key="ccm.UserActivityLog.Action"/></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="codeMapList">
								<tr>
									<td>${channelCode}</td>
									<td>${codeNo}</td>
									<td>${codeName}</td>
									<td>
									<a href="#EditNewElement" class="link ccm-popup-click" onclick="javascript:edit('${codeListMappingId}',${di})"><fmt:message key="common.button.edit"/></a> 
									<a href="javascript:;" codeListMappingId=${codeListMappingId} dictId=${di} channelId=${channelId}
									 class="link del_ifself"><fmt:message key="common.button.delete"/></a>
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