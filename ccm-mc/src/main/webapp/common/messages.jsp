<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% if (request.getAttribute("struts.valueStack") != null) { %>
<%-- ActionError Messages - usually set in Actions --%>
<s:if test="hasActionErrors()">
    <div class="error" id="errorMessages">    
      <s:iterator value="actionErrors">
        <img src="<c:url value="/img/iconWarning.gif"/>"
            alt="<fmt:message key="icon.warning"/>" class="icon" />
        <s:property/><br />
      </s:iterator>
   </div>
</s:if>

<%-- FieldError Messages - usually set by validation rules --%>
<s:if test="hasFieldErrors()">
    <div class="error" id="errorMessages">    
      <s:iterator value="fieldErrors">
          <s:iterator value="value">
            <img src="<c:url value="/img/iconWarning.gif"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon" />
             <s:property/><br />
          </s:iterator>
      </s:iterator>
   </div>
</s:if>
<% } %>
<style>
.remindtip,
.warningtip{ border:#999 1px solid; border-color:#fff #999 #999 #fff; margin-bottom:10px;background-color:#fff; font-size:14px; padding:6px 24px;}
.remindtip{ background:#dff3fe url(../img/gradient-bg1.png) repeat-x 0 bottom;}
.warningtip{background:#f9f7e5 url(../img/gradient-bg2.png) repeat-x 0 bottom;}
.remindtip span,
.warningtip span{ display:inline-block;font-family: "Microsoft Yahei"; font-size:24px; letter-spacing:2px; line-height:40px; padding-left:48px; padding-right:12px; float:left;}
.remindtip span{ color:#06c; background:url(../img/icons-bg.png) no-repeat 0 3px;}
.warningtip span{color:#e57a3a;background:url(../img/icons-bg.png) no-repeat 0 -38px;}
.remindtip .tipcon,
.warningtip .tipcon{ margin-left:120px; line-height:22px; padding-top:10px;}
</style>
<%-- Success Messages --%>
<c:if test="${not empty messages}">
    
    <div class="remindtip"><span><fmt:message key="common.Reminder"/></span>
            <div class="tipcon">
           			<c:forEach var="msg" items="${messages}">
		            <c:out value="${msg}"/><br />
		        </c:forEach>
            </div>
            <div class="clearfix"></div>
     </div>
   
    <c:remove var="messages" scope="session"/>
</c:if>

<%-- Error Messages (on JSPs, not through Struts --%>
<c:if test="${not empty errors}">
    <div class="error" id="errorMessages">
        <c:forEach var="error" items="${errors}">
            <img src="<c:url value="/img/iconWarning.gif"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}"/><br />
        </c:forEach>
    </div>
    <c:remove var="errors" scope="session"/>
</c:if>