<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.PropertyList"/>
          <div class="bt"> 
          	<a href="/hotel_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/hotel_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
          		<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.BasicConfiguration.ChainName"/>：</span></div>
	                <div class="i_input">
	                  <%-- <s:select id="l_chainId" name="creteria.chainId" list="chainvoList" listKey="chainId" listValue="chainName"
						headerKey="" headerValue="不限" cssClass="fxt w120" onchange="changeChain();">
					  </s:select> --%>
					  <select id="l_chainId" class="fxt w120" onchange="changeChain();" name="creteria.chainId">
					  	<option value=""><fmt:message key="common.Unlimited"/></option>
						  <c:forEach items="${chainvoList }" var="chainvo">
					  		<option value="${chainvo.chainId }"  <c:if test="${chainvo.chainId==creteria.chainId }">selected="selected"</c:if> >${chainvo.chainName }</option>
						  </c:forEach>
					  </select>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.BrandList.BrandName"/>：</span></div>
	                <div class="i_input">
					  <select id="l_brandId" name="creteria.brandId" brandId="${creteria.brandId}" class="fxt w120">
						<option value=""><fmt:message key="common.Unlimited"/></option>
					  </select>
	                </div>
                </li>
                
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_hotelCode" name="creteria.hotelCodeLike" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.UserActivityLog.PropertyName"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_hotelName" name="creteria.hotelName" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text">email：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_email" name="creteria.email" cssClass="fxt w120"></s:textfield>
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
	          <display:table name="result.resultList" id="hotelList" class="ccm_table1" requestURI="" 
	        	pagesize="20"  size="result.totalCount" partialList="true">
		        <display:column property="hotelCode" titleKey="ccm.ReservationMonitorReport.PropertyCode"/>
		        <display:column property="hotelName" titleKey="ccm.UserActivityLog.PropertyName"/>
		        <display:column property="chainName" titleKey="ccm.BasicConfiguration.ChainName"/>
		        <display:column property="brandName" titleKey="ccm.BrandList.BrandName"/>
		        <display:column titleKey="ccm.PropertyList.EstablishmentTime">
		        	<fmt:formatDate type="date" value="${hotelList.whenBuilt}" pattern="yyyy-MM-dd"/>
		        </display:column>
		        <display:column property="longitude" titleKey="ccm.LandmarkList.Longitude"/>
		        <display:column property="latitude" titleKey="ccm.LandmarkList.Latitude"/>
		        <display:column property="altitude" titleKey="ccm.PropertyList.Altitude"/>
		        <display:column  titleKey="common.button.Activity">
		            <a href="/hotel_toEdit.do?hotelvo.hotelId=${hotelList.hotelId }&hotelvo.hotelMId=${hotelList.hotelMId }" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <!--  <a href="javascript:;" hotelId=${hotelList.hotelId } hotelMId=${hotelList.hotelMId } class="link del_ifself">删除</a> -->
		        </display:column>
		      </display:table>
          </div>
        </div>
<script>
$(document).ready(function() {
	changeChain();//集团与品牌联动
	
	// 重置
	$('.l_reset').click(function(){
		$('#l_chainId').val("");
		$('#l_brandId').val("");
		$('#l_hotelCode').val("");
		$('#l_hotelName').val("");
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var hotelId = $(this).attr('hotelId');
		var hotelMId = $(this).attr('hotelMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href="/hotel_delete.do?hotelvo.hotelId="+hotelId+"&hotelvo.hotelMId="+hotelMId;
		}
	});
});

//集团与品牌联动
function changeChain(){
	 var chainId=$("#l_chainId").val();
	 if(chainId.length>0){
		 $.get("/brand_ajaxGetAllBrandByChainId.do?brandvo.chainId="+chainId,function(data){
			data = "<option value=''><fmt:message key="common.Unlimited"/></option>"+data;
			$("#l_brandId").html(data);
			var v=$("#l_brandId").attr("brandId");
		    if(v!=undefined&&v!='null'){
				var options = $("#l_brandId option");
   				$.each(options, function(){
   					$(this).prop("selected", $(this).val()==v);
   				});	
		    }
		 })
	 }else{
		 var o=$("#l_chainId").next();
		 $(o).html('<option value=""><fmt:message key="common.Unlimited"/></option>');
	 }
}
</script>
