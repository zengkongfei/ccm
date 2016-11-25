<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.CancellationRules.CancellationRulesList"/>
          <div class="bt"> 
          	<a href="/cancel_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/cancel_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.CancellationRules.CancellationRulesCode"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_cancelPolicyCode" name="criteria.cancelPolicyCode" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.CancellationRules.CancellationRulesName"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_policyName" name="criteria.policyName" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
            </ul>
            <hr class="solided notopMargin">
            <div class="center">
	            <button type="submit" class="btn_2 black mgR12"><fmt:message key="common.button.searchSelect"/></button>
	            <button type="button" class="btn_2 white l_reset"><fmt:message key="common.button.Reset"/></button>
            </div>
          </div>
          </s:form>
          
          <!--列表样式-->
          <div class="bt_wp">
	          <display:table name="result.resultList" id="cancelList" class="ccm_table1" requestURI="" 
	        	pagesize="20"  size="result.totalCount" partialList="true">
		        <display:column property="cancelPolicyCode" titleKey="ccm.CancellationRules.RulesCode"/>
		        <display:column property="policyName" titleKey="ccm.CancellationRules.RulesName"/>
		        <display:column titleKey="ccm.CancellationRules.Noncancelable">
		        	<c:choose>
		        		<c:when test="${cancelList.isNonRefundable }"><fmt:message key="common.Yes"/></c:when>
		        		<c:otherwise><fmt:message key="common.Not"/></c:otherwise>
		        	</c:choose>
		        </display:column>
		        <display:column titleKey="ccm.CancellationRules.DeductedAmountorNot">
		        	<c:choose>
		        		<c:when test="${cancelList.feesInclusive }"><fmt:message key="common.Yes"/></c:when>
		        		<c:otherwise><fmt:message key="common.Not"/></c:otherwise>
		        	</c:choose>
		        </display:column>
		        <display:column property="basisType" titleKey="ccm.CancellationRules.DeductedAmountBaseNumber"/>
		        <display:column property="numberOfNights" titleKey="ccm.CancellationRules.DeductedAmountByNights"/>
		        <display:column property="percent" titleKey="ccm.CancellationRules.DeductedAmountByPercentage"/>
		        <display:column property="amount" titleKey="ccm.CancellationRules.DeductedAmountByFixedAmount"/>
		        <display:column titleKey="ccm.CancellationRules.TaxInclusive">
		        	<c:choose>
		        		<c:when test="${cancelList.taxInclusive }"><fmt:message key="common.Yes"/></c:when>
		        		<c:otherwise><fmt:message key="common.Not"/></c:otherwise>
		        	</c:choose>
		        </display:column>
		        <display:column property="description" titleKey="common.Description"/>
		        <display:column  titleKey="common.button.Activity">
		            <a href="/cancel_toEdit.do?cancelPolicyvo.cancelId=${cancelList.cancelId }&cancelPolicyvo.cancelMId=${cancelList.cancelMId }" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" cancelId=${cancelList.cancelId } cancelMId=${cancelList.cancelMId } class="link del_ifself"><fmt:message key="common.button.delete"/></a>
		        </display:column>
		      </display:table>
          </div>
        </div>
<script>
$(document).ready(function() {
	
	// 重置
	$('.l_reset').click(function(){
		$('#l_cancelPolicyCode').val("");
		$('#l_policyName').val("");
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var cancelId = $(this).attr('cancelId');
		var cancelMId = $(this).attr('cancelMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href="/cancel_delete.do?cancelPolicyvo.cancelId="+cancelId+"&cancelPolicyvo.cancelMId="+cancelMId;
		}
	});
});
</script>
