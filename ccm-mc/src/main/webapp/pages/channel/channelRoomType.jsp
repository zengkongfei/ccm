<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<span class="changeFromRate">
<s:iterator value="#request.rtList" status="st">
	<label class="checkbox">
		<input type="checkbox" <s:if test="roomTypeIds.contains(roomTypeId)">checked="checked"</s:if> value="${roomTypeId}" name="roomTypeIds">
		<span class="">${roomTypeCode}&nbsp;${roomTypeName}
			<c:if test="${!empty pmsCode}">_${pmsCode}</c:if>
		</span>
	</label>
</s:iterator>
</span>