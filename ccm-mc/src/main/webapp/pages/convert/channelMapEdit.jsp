<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="pp_main">
	<div class="t_title"><fmt:message key="ccm.ChannelMapping.Edit"/></div>
	<s:form id="codeMapForm" action="/cMap_save.do" method="post">
		<appfuse:ccmToken name="token"></appfuse:ccmToken>
		<s:hidden key="codeMap.codeListMappingId"></s:hidden>
		<s:hidden key="dictId"></s:hidden>
		<s:hidden key="codeMap.codeNo"></s:hidden><!-- 记录日志 -->
		<div class="pdA24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class=""></span><span class="text">ChannelCode：</span>
					</div>
					<div class="i_input">
						<select class="fxt w180 required" name="codeMap.channelId">
							<s:iterator value="#request.channelList">
								<option value="${channelId}" <s:if test="channelId==codeMap.channelId">selected="selected"</s:if>>
									${channelCode}
								</option>
							</s:iterator>
						</select>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text">CCM Code：</span>
					</div>
					<div class="i_input">
						<select class="fxt w180 required" name="codeMap.codeId" id="codeMapCodeId">
							<s:iterator value="#request.dictCode">
								<option value="${codeId}" <s:if test="codeId==codeMap.codeId">selected="selected"</s:if>>
									${codeNo}
									<s:if test='codeLabel!=null'>－${codeLabel}</s:if>
								</option>
							</s:iterator>
						</select>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text">Channnel Value：</span>
					</div>
					<div class="i_input">
						<input class="fxt w120 required" type="text" value="${codeMap.codeName}" name="codeMap.codeName" style="width: 166px;">
					</div>
				</li>
			</ul>
		</div>
		<div class="b_crl">
			<button type="button" class="btn_2 green mgR6 f_save" onclick="dosubmit();"><fmt:message key="common.button.save"/>	</button>
			<button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
		</div>
	</s:form>
</div>
<script src="<c:url value='/css/jquery-ui/jquery-ui.js'/>${global_js_revision}"></script>
<script src="<c:url value='/js/main.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/effective-validate-min.js'/>${global_js_revision}"></script>

<script type="text/javascript">
	function dosubmit() {
		$("#codeMapForm").effectiveAndValidate({

			rules : {

				'codeMap.codeId' : {
					required : true,
				},
				'codeMap.codeName' : {
					required : true,
				}
			},

			messages : {
				'codeMap.codeId' : {
					required : '<span class="infotext"><fmt:message key="common.select.plesesSelect"/>	</span>'
				},
				'codeMap.codeName' : {
					required : '<span class="infotext"><fmt:message key="ccm.error.031"/></span>'
				}
			}

		});
		if ($("#codeMapForm").valid()) {
			$('#codeMapForm_codeMap_codeNo').val($('#codeMapCodeId option:selected').text());
			$("#codeMapForm").submit();
			//禁止重复提交
			 $('.f_save').addClass('no_ald');
			 $('.f_save').attr("disabled","disabled");
		}

	}
</script>