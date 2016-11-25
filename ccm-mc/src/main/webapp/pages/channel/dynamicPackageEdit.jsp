
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="title_wp"><fmt:message key="ccm.DynamicPackageList.EditDynamicPackage"/></div>
<%@ include file="/common/messages.jsp"%>
<s:form id="channelForm" action="" method="post">
<appfuse:ccmToken name="token"></appfuse:ccmToken>
	<s:hidden key="dp.dynamicPackageId"></s:hidden>
	<div class="c_whitebg pdA12">
		<div class="mgB24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.Channel.ChannelCode"/>：</span>
					</div>
					<div class="i_input">
						<select Class="fxt w120 required" name="dp.channelId">
							<option><fmt:message key="common.select.plesesSelect"/>	</option>
							<c:forEach items="${channelList }" var="channel">
								<option value="${channel.channelId }" <c:if test="${dp.channelId==channel.channelId }">selected="selected"</c:if>  >${channel.channelCode }</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.DynamicPackageList.PackageCode"/>：</span>
					</div>
					<div class="i_input">
						<select Class="fxt w120 required" name="dp.packageId">
							<option><fmt:message key="common.select.plesesSelect"/>	</option>
							<s:iterator value="#request.packageList" var="package">
								<option value="<s:property value="#package.packageId" /> " <s:if test="#package.packageId==dp.packageId">selected="selected"</s:if> ><s:property value="#package.packageCode" /> </option>
							</s:iterator>
						</select>
						
					</div>
				</li>
			</ul>
		</div>
		<hr class="dashed">
		<div class="listinputCtrl">
			<button type="button" class="btn_1 green mgR12 f_save" onclick="dosubmit()"><fmt:message key="common.button.OK"/></button>
			<a class="btn_1 white" href="/dynamicPackage_list.do?menuId=${sessionScope.menuId}"><fmt:message key="common.Return"/>	</a>
		</div>
	</div>
</s:form>

<script>
	$(document).ready(function() {
		
	});

	function dosubmit(url) {
		$("#channelForm").effectiveAndValidate({

			rules : {

				'dp.channelCode' : {
					required : true
				},
				'dp.packageCode' : {
					required : true
				}
			},

			messages : {
				'dp.channelCode' : {
					required : '<span class="infotext">请输入渠道代码</span>'
				},
				'dp.packageCode' : {
					required : '<span class="infotext">请输入包价代码</span>'
				}
			},
			errorPlacement : function(error, element) {
				var errWrap = $('<span class=\"error\"></span>');
				error.appendTo(errWrap);
				if (element.is(":radio"))
					errWrap.appendTo(element.parent().parent());
				else if (element.is(":checkbox"))
					errWrap.appendTo(element.parent().parent());
				else if(element.next().is("span"))
					errWrap.appendTo(element.parent().parent());
				else
					errWrap.appendTo(element.parent());
			}

		});
		
		if ($("#channelForm").valid()) {
			document.channelForm.action = '/dynamicPackage_save.do';
			document.channelForm.submit();
			//禁止重复提交
			$('.f_save').addClass('no_ald');
			$('.f_save').attr("disabled","disabled");	
		}
	}
</script>