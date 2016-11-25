<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

        <div class="title_wp"><fmt:message key="ccm.BrandList"/>
          <div class="bt"> 
          	<a href="/brand_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/>	</a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/brand_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
          		<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.BrandList.ChainName"/>：</span></div>
	                <div class="i_input">
					  <select id="l_chainId" Class="fxt w120" name="creteria.chainId">
					  	<option value=""> <fmt:message key="common.Unlimited"/></option>
					  	<c:forEach items="${chainvoList }" var="chainvo">
						  	<option value="${chainvo.chainId }" <c:if test="${chainvo.chainId==creteria.chainId }">selected="selected"</c:if>   > ${chainvo.chainName }</option>
					  	</c:forEach>
					  </select>
					  
	                </div>
                </li>
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.BrandList.BrandCode"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_brandCode" name="creteria.brandCode" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.BrandList.BrandName"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_brandName" name="creteria.brandName" cssClass="fxt w120"></s:textfield>
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
	          <display:table name="result.resultList" id="brandList" class="ccm_table1" requestURI="" 
	        	pagesize="20"  size="result.totalCount" partialList="true">
		        <display:column property="chainName" titleKey="ccm.BrandList.ChainName"/>
		        <display:column property="brandCode" titleKey="ccm.BrandList.BrandCode"/>
		        <display:column property="brandName" titleKey="ccm.BrandList.BrandName"/>
		        <display:column  titleKey="ccm.UserActivityLog.Action">
		            <a href="/brand_toEdit.do?brandvo.brandId=${brandList.brandId }&brandvo.brandMId=${brandList.brandMId }" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" brandId=${brandList.brandId } brandMId=${brandList.brandMId } class="link del_ifself"><fmt:message key="common.button.delete"/></a>
		        </display:column>
		      </display:table>
          </div>
        </div>

<script>
$(document).ready(function() {
	// 重置
	$('.l_reset').click(function(){
		$('#l_chainId').val("");
		$('#l_brandCode').val("");
		$('#l_brandName').val("");
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var brandId = $(this).attr('brandId');
		var brandMId = $(this).attr('brandMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href="/brand_delete.do?brandvo.brandId="+brandId+"&brandvo.brandMId="+brandMId;
		}
	});
});
</script>
