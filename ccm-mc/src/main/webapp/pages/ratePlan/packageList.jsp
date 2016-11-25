<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.PackageService.PackageServiceList"/>
          <div class="bt"> 
          	<a href="/package_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/package_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.PackageService.PackageServiceCode"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_packageCode" name="criteria.packageCode" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.PackageService.PackageServiceName"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_packageName" name="criteria.packageName" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PackageService.Calculation"/>：</span></div>
	                <div class="i_input">
						<%-- <s:select id="l_calculation" list="#{'1':'按每晚','2':'只算到达日','3':'只算离店日','4':'在一周内的某（几）天','5':'在一段时间内（几月几日~几月几日）'}" 
							name="criteria.calculation" cssClass="fxt w180" headerKey="" headerValue="请选择">
			            </s:select > --%>
			            <select id="l_calculation" name="criteria.calculation" class="fxt w180">
			            	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
			            	<c:forEach items="${calculationList }" var="calculation" varStatus="i">
			            	<option value="${i.index+1 }" <c:if test="${(i.index+1 )==criteria.calculation }">selected="selected"</c:if>   >${ calculation} </option>
			            	</c:forEach>
			            </select>
			            
	                </div>
	            </li>
	            <li class="col3_1">
	                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PackageService.CalculationRule"/>：</span></div>
	                <div class="i_input">
<%-- 						<s:select id="l_rule" list="#{'1':'固定价格','4':'每人','5':'每房间数'}" 
							name="criteria.rule" cssClass="fxt w120" headerKey="" headerValue="请选择">
			            </s:select> --%>
						<s:select id="l_rule" list="#request.rule" 
							name="criteria.rule" cssClass="fxt w120" >
			            </s:select>
	                </div>
	            </li>
	            <li class="col3_1">
	                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PackageService.CalculationType"/>：</span></div>
	                <div class="i_input">
						<%-- <s:select id="l_basicType" list="#{'1':'按百分比','2':'按具体值'}" 
							name="criteria.basicType" cssClass="fxt w120" headerKey="" headerValue="请选择">
			            </s:select> --%>
			            
			             <select id="l_basicType" name="criteria.basicType" class="fxt w120">
			            	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
			            	<c:forEach items="${basicTypeList }" var="basicType" varStatus="i">
			            	<option value="${i.index+1 }" <c:if test="${(i.index+1)==criteria.basicType }">selected="selected"</c:if>   >${ basicType} </option>
			            	</c:forEach>
			            </select>
			            
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
	          <display:table name="result.resultList" id="packageList" class="ccm_table1" requestURI="" 
	        	pagesize="20"  size="result.totalCount" partialList="true">
		        <display:column property="packageCode" titleKey="ccm.PackageService.PackageServiceCode"/>
		        <display:column property="packageName" titleKey="ccm.PackageService.PackageServiceName"/>
		        <display:column titleKey="ccm.PackageService.ExtraFeeorNot">
		        	<c:choose>
		        		<c:when test="${packageList.isExtraCharge }"><fmt:message key="common.Yes"/>	</c:when>
		        		<c:otherwise><fmt:message key="common.Not"/>	</c:otherwise>
		        	</c:choose>
		        </display:column>
		        <display:column titleKey="ccm.PackageService.Calculation">
		        	<c:choose>
		        		<c:when test="${packageList.calculation==1 }"><fmt:message key="ccm.PackageService.ByPerNight"/></c:when>
		        		<c:when test="${packageList.calculation==2 }"><fmt:message key="ccm.PackageService.OnlyCountArrival"/></c:when>
		        		<c:when test="${packageList.calculation==3 }"><fmt:message key="ccm.PackageService.OnlyCountDeparture"/></c:when>
		        		<c:when test="${packageList.calculation==4 }"><fmt:message key="ccm.PackageService.AFewDaysWeek"/></c:when>
		        		<c:when test="${packageList.calculation==5 }"><fmt:message key="ccm.PackageService.SeveralDays"/></c:when>
		        	</c:choose>
		        </display:column>
		        <display:column titleKey="ccm.PackageService.CalculationRule">
		        	<c:choose>
		        		<c:when test="${packageList.rule==1 }"><fmt:message key="ccm.PackageService.FixedAmount"/></c:when>
		        		<c:when test="${packageList.rule==4 }"><fmt:message key="ccm.PackageService.PerPerson"/></c:when>
		        		<c:when test="${packageList.rule==5 }"><fmt:message key="ccm.PackageService.PerRoom"/></c:when>
		        	</c:choose>
		        </display:column>
		        <display:column titleKey="ccm.PackageService.CalculationType">
		        	<c:choose>
		        		<c:when test="${packageList.basicType==1 }"><fmt:message key="ccm.PackageService.ByPercentage"/></c:when>
		        		<c:when test="${packageList.basicType==2 }"><fmt:message key="ccm.PackageService.ByConcreteValue"/></c:when>
		        	</c:choose>
		        </display:column>
				<display:column titleKey="ccm.PackageService.IsSvcOrTax">
		        	<c:choose>
		        		<c:when test="${packageList.issvcortax==null }"><fmt:message key="common.Not"/></c:when>
		        		<c:when test="${packageList.issvcortax==false }"><fmt:message key="common.Not"/></c:when>
		        		<c:when test="${packageList.issvcortax==true }"><fmt:message key="common.Yes"/></c:when>
		        	</c:choose>
		        </display:column>
		        <display:column  titleKey="common.button.Activity">
		            <a href="/package_toEdit.do?packagevo.packageId=${packageList.packageId }&packagevo.packageMId=${packageList.packageMId }" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" packageId=${packageList.packageId } packageMId=${packageList.packageMId } class="link del_ifself"><fmt:message key="common.button.delete"/></a>
		        </display:column>
		      </display:table>
          </div>
        </div>
<script>
$(document).ready(function() {
	// 重置
	$('.l_reset').click(function(){
		$('#l_packageCode').val("");
		$('#l_packageName').val("");
		$('#l_calculation').val("");
		$('#l_rule').val("");
		$('#l_basicType').val("");
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var packageId = $(this).attr('packageId');
		var packageMId = $(this).attr('packageMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href="/package_delete.do?packagevo.packageId="+packageId+"&packagevo.packageMId="+packageMId;
		}
	});
	
	/*$('#l_rule').change(function(){
		var dt=$("#l_basicType option[value='2']");
		if($(this).val()==1 && dt){
			dt.remove();
		}else if(strIsNull(dt.val())){
			$("#l_basicType option[value='1']").after("<option value='2'>按具体值</option>");
		}
	})*/
});
</script>
