<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="pp_main">
    <div style="height:500px;overflow-y:scroll;">
   	 <pre>
    	<s:property value="#request.sendOTALog.message"/>
   	 </pre>
	</div>
	<div class="b_crl" style="text-align: center;">
		<button type="button" class="btn_2 white" onclick="doExport();"><fmt:message key="common.button.File"/></button>
		<button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
	</div>
</div>
<script src="<c:url value='/js/main.js'/>"></script>
<script>
function doExport() 
{
    if ($('#searchForm :input[name="b2CUserCriteria.isVerified"]').val() == "null") 
    {
        $('#searchForm :input[name="b2CUserCriteria.isVerified"]').attr("disabled", "disabled");
    }
    document.searchForm.action = '/sendOTALog_export.do?sendOTALogId=${sendOTALogId}&hotelCode=${requestScope.sendOTALog.hotelCode}';
    document.searchForm.submit();
}
</script>
