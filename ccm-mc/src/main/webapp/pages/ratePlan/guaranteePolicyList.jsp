<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.GuaranteeRules.GuaranteeRulesList"/>
          <div class="bt"> 
          	<a href="/guarantee_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/guarantee_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow"><!--  
          		<li class="col3_1">
	                <div class="i_title"><span class="text">酒店：</span></div>
	                <div class="i_input">
	                  <s:select id="l_hotelId" name="criteria.hotelId" list="hotelList" listKey="hotelId" listValue="hotelName"
						headerKey="" headerValue="不限" cssClass="fxt w180">
					</s:select>
	                </div>
                </li>-->
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.GuaranteeRules.GuaranteeRulesCode"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_guaranteeCode" name="criteria.guaranteeCode" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.GuaranteeRules.GuaranteeRulesName"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_policyName" name="criteria.policyName" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.GuaranteeRules.GuaranteeType"/>：</span></div>
                <div class="i_input">
					<s:select id="l_guaranteeType" list="#request.guaranteeType" name="criteria.guaranteeType" cssClass="fxt w180" >
		            </s:select>
		            
		            
                </div>
              </li>
            </ul>
            <hr class="solided notopMargin">
            <div class="center">
	            <button type="submit" class="btn_2 black mgR12"><fmt:message key="common.button.searchSelect"/>	</button>
	            <button type="button" class="btn_2 white l_reset"><fmt:message key="common.button.Reset"/></button>
            </div>
          </div>
          </s:form>
          
          <!--列表样式-->
          <div class="bt_wp">
	          <display:table name="result.resultList" id="guaranteeList" class="ccm_table1" requestURI="" 
	        	pagesize="20"  size="result.totalCount" partialList="true">
		        <display:column property="guaranteeCode" titleKey="ccm.GuaranteeRules.GuaranteeRulesCode"/>
		        <display:column property="policyName" titleKey="ccm.GuaranteeRules.GuaranteeRulesName"/>
		        <display:column titleKey="ccm.GuaranteeRules.TimeoutProcessing">
		        	<c:choose>
		        		<c:when test="${guaranteeList.retributionType=='1' }">Res Automatically Cancelled</c:when>
		        		<c:when test="${guaranteeList.retributionType=='2' }">Res No Longer Guaranteed</c:when>
		        		<c:otherwise></c:otherwise>
		        	</c:choose>
		        </display:column>
		        <display:column titleKey="ccm.GuaranteeRules.GuaranteeType">
		        	<c:choose>
		        		<c:when test="${guaranteeList.guaranteeType=='1' }">GuaranteeRequired</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='2' }">None</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='3' }">CC/DC/Voucher</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='4' }">Profile</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='5' }">Deposit</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='6' }">PrePay</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='7' }">DepositRequired</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='8' }">CREDIT_CARD</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='9' }">CORPORATE</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='10' }">GUARANTEE</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='11' }">HOLD</c:when>
		        		<c:when test="${guaranteeList.guaranteeType=='12' }">TRAVEL_AGENT</c:when>
		        		<c:otherwise></c:otherwise>
		        	</c:choose>
		        </display:column>
		        <display:column property="holdTimeStr" titleKey="ccm.GuaranteeRules.ReservedTime"/>
		        <display:column property="validTimeStr" titleKey="ccm.GuaranteeRules.EffectiveTimeBefore"/>
		        <display:column property="description" titleKey="common.Description"/>
		        <display:column  titleKey="common.button.Activity">
		            <a href="/guarantee_toEdit.do?guaranteePolicyvo.guaranteeId=${guaranteeList.guaranteeId }&guaranteePolicyvo.guaranteeMId=${guaranteeList.guaranteeMId }" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" guaranteeId=${guaranteeList.guaranteeId } guaranteeMId=${guaranteeList.guaranteeMId } class="link del_ifself"><fmt:message key="common.button.delete"/></a>
		        </display:column>
		      </display:table>
          </div>
        </div>
<script>
$(document).ready(function() {
	// 重置
	$('.l_reset').click(function(){
		$('#l_guaranteeCode').val("");
		$('#l_policyName').val("");
		$('#l_guaranteeType').val("");
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var guaranteeId = $(this).attr('guaranteeId');
		var guaranteeMId = $(this).attr('guaranteeMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href="/guarantee_delete.do?guaranteePolicyvo.guaranteeId="+guaranteeId+"&guaranteePolicyvo.guaranteeMId="+guaranteeMId;
		}
	});
});
</script>
