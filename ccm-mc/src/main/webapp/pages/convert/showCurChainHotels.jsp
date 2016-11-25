<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="pp_main">
	<div class="t_title">复制</div>
	<s:form id="codeMapForm" action="/convert_copyCodeMap.do" method="post">
	<appfuse:ccmToken name="token"></appfuse:ccmToken>
		<div class="pdA24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class=""></span><span class="text">酒店代码：</span>
					</div>
					<div class="i_input" style="position:relative;">
						<select class="fxt w180 required" name="hotelId" id="hotelId">
							<c:forEach items="${hotelList}" var="hotel">
								<c:if test="${hotelId!=hotel.hotelId}">
								<option value="${hotel.hotelId}">${hotel.hotelCode}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</li>
			</ul>
		</div>
		<div class="b_crl">
			<button type="button" class="btn_2 green mgR6" onclick="dosubmit();">保存</button>
			<button type="button" class="btn_2 white popup-close">关闭</button>
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
				'hotelId' : {
					required : true,
				}
			},
			messages : {
				'hotelId' : {
					required : '<span class="infotext">请选择</span>'
				}
			}

		});
		if ($("#codeMapForm").valid()) {
			$("#codeMapForm").submit();
		}

	}
</script>