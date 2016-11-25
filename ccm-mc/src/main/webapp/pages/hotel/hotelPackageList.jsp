<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
 <div class="title_wp"><fmt:message key="ccm.PackageService.PropertyPackageList"/>
          <div class="bt"> 
          	<a href="javascript:addData();" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          <!--搜索项-->
          <s:form action="/hotelPackage_list.do" method="post">
          <div class="nm_box "> 
          	<ul class="inq_wp frow">
                <li class="col3_2">
	                <div class="i_title "><span class="text"><fmt:message key="ccm.DynamicPackageList.PackageCode"/>：
	                <s:hidden id="i_packageIds" name="creteria.packageIds"/>
	                <s:hidden name="creteria.packageCodes" value="creteria.packageCodes"></s:hidden> </span></div>
	                <div class="i_input packages" style="position:relative;">
	                	<div class="moreoption">
							<div class="opts">
								<div class="text typeName" style="width: 350px;">
									<c:if test="${empty creteria.packageCodes}">
										<fmt:message key="common.select.plesesSelect"/>
									</c:if>
									<c:if test="${!empty creteria.packageCodes}">
										${creteria.packageCodes}
									</c:if>
								</div>
							</div>
						</div>
						<div class="ft_layer abs ddds" style="width: 388px;">
							<div class=" n_overFlowY">
								<div class="mgA6">
									<c:forEach items="${requestScope.packageList}" var="rl">
										<label class="checkbox"> 
											<input type="checkbox" value="${rl.packageId}" name="creteria.packageIds" 
												<c:if test="${fn:contains(creteria.packageIds,rl.packageId)}">checked="checked" </c:if> > 
												<span class=""> 
													<span class="span_packageCode">${rl.packageCode }</span> 
													<span class="span_packageName">${rl.packageName }</span> 
												</span> 
										</label>
									</c:forEach>
								</div>
							</div>
							<div class="ft_ctr1">
								<button type="button" class="btn_3 white selectAll" style="float: left;"><fmt:message key="common.select.selectAll"/></button>
								<button type="button" class="btn_3 white reverseSel" style="float:left;"><fmt:message key="common.select.Unselect"/></button>
								<button type="button" class="btn_3 green mgR6 confirmthis"><fmt:message key="common.button.OK"/></button>
								<button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>
							</div>
						</div>
	                </div>
                </li>
            </ul>
            <hr class="solided notopMargin">
            <div class="center">
	            <button type="submit" class="btn_2 black mgR12" onclick="javascript:return query();"><fmt:message key="common.button.searchSelect"/></button>
	            <button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
            </div>
          </div>
          </s:form>
          
          <!--列表样式-->
          <div class="bt_wp">
			<display:table name="result.resultList" id="hotelPackageList" class="ccm_table1" requestURI="" pagesize="20" size="result.totalCount" partialList="true">
				<display:column property="packageCode" sortable="true" titleKey="ccm.DynamicPackageList.PackageCode" headerClass="sorted" />
				<display:column property="packageName" sortable="true" titleKey="ccm.PackageService.PackageName" headerClass="sorted" />
				<display:column property="description" sortable="true" titleKey="common.Description" headerClass="sorted" />
				<display:column property="createdTime" sortable="true" titleKey="ccm.ReservationMonitorReport.CreatedTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted"/>
				<display:column titleKey="common.button.Activity">
					<a href="/hotelPackage_toEdit.do?hotelPackageVo.hotelPackageId=${hotelPackageList.hotelPackageId}" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" hotelPackageId=${hotelPackageList.hotelPackageId} class="link del_ifself"><fmt:message key="common.button.delete"/></a>
				</display:column>
			</display:table>
	 	  </div>
      </div>

<script>
$(document).ready(function() {
	
	/*是否删除本条 setHotelIdForHref */
	$('.del_ifself').bind('click',function(){
		var pid = $(this).attr('hotelPackageId');
		
		//删除时验证
		$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"hotelPackage_validateDeletePackage.do",
	   	  data:{"hotelPackageVo.hotelPackageId":pid},
		  success:function(data){
			  var obj = eval("("+data+")");
			  if (obj.result == 'success') {
				  var r = confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
				  if (r == true) {
					  location.href = setHotelIdForHref('/hotelPackage_delete.do?hotelPackageVo.hotelPackageId='+pid);
				  }
			  }else if (obj.result == 'fail') {
				  var code = obj.code;
				  var pcode = obj.pcode;
				  var message = "";
				  if(obj.type == "rateplan"){
					  var arry = new Array();
						arry.push(pcode);
						arry.push(code);
						var str = '<fmt:message key="ccm.error.015"/>';
						message = i18n_replace(str,arry)
					 // message = "包价【"+pcode+"】在当前酒店中已被{房价}代码【"+code+"】引用,不允许删除";
				  }else if(obj.type == "roomtype"){
					  var arry = new Array();
						arry.push(pcode);
						arry.push(code);
						var str = '<fmt:message key="ccm.error.016"/>';
						message = i18n_replace(str,arry)
					 // message = "包价【"+pcode+"】在当前酒店中已被{房型}代码【"+code+"】引用,不允许删除";
				  }else if(obj.type == "dynamic"){
					  var arry = new Array();
						arry.push(pcode);
						arry.push(code);
						var str = '<fmt:message key="ccm.error.017"/>';
						message = i18n_replace(str,arry)
					//  message = "包价【"+pcode+"】在当前酒店的{动态打包}列表中已被引用,不允许删除";
				  }
				  alert(message);
			  }
	       }
	    });	
		
	});
	
	$('.moreoption').bind('click',function(){
		$(this).next().slideDown();
	});
	$('.closethis').bind('click',function(){
		$(this).parent().parent().slideUp();
	});
	//全选
	$(".selectAll").bind('click',function(){
		var checklist = $(this).parent().prev().children().children().children();
		for(var i=0;i<checklist.length;i++){
		 	checklist[i].checked = true;
		}
	});
	//反选
	$(".reverseSel").bind('click',function(){
		var checklist = $(this).parent().prev().children().children().children();
		for(var i=0;i<checklist.length;i++){
			checklist[i].checked = !checklist[i].checked;
		}
	});
	
	$('.confirmthis').click(function(){
		var codes='';
		var names ='';
		$('.packages input:checked').each(function(){ 
			codes += $(this).val()+',';
		});
		$('.packages input:checked').next('span').each(function(){ 
			names += $(this).find('.span_packageCode').text()+',';
		});
		codes = codes.substr(0,codes.lastIndexOf(','));
		names = names.substr(0,names.lastIndexOf(','));
		
		$("input[name='creteria.packageIds']").val(codes);
		$('.packages .typeName').text(names);
		$("input[name='creteria.packageCodes']").val(names);
		$(this).parent().parent().slideUp();
	});
});

function addData(){
	$.ajax({
		type:"POST",
	   	async:false,
	   	url:"hotelPackage_isNotHavePackage.do",
	   	success:function(data){
	   		if("false" == data){
	   			alert('<fmt:message key="ccm.error.084"/>');
	   		}else{
	   			location.href = '/hotelPackage_toEdit.do';
	   		}
	   	}
	});
}

/*是否删除本条*/
/*
function delUser(pid) {
	//删除时验证
	$.ajax({
   	  type:"POST",
   	  async:false,
   	  url:"hotelPackage_validateDeletePackage.do",
   	  data:{"hotelPackageVo.hotelPackageId":pid},
	  success:function(data){
		  var obj = eval("("+data+")");
		  if (obj.result == 'success') {
			  var r = confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
			  if (r == true) {
				  location.href = '/hotelPackage_delete.do?hotelPackageVo.hotelPackageId='+pid;
			  }
		  }else if (obj.result == 'fail') {
			  var code = obj.code;
			  var pcode = obj.pcode;
			  var message = "";
			  if(obj.type == "rateplan"){
				  var arry = new Array();
					arry.push(pcode);
					arry.push(code);
					var str = '<fmt:message key="ccm.error.015"/>';
					message = i18n_replace(str,arry)
				 // message = "包价【"+pcode+"】在当前酒店中已被{房价}代码【"+code+"】引用,不允许删除";
			  }else if(obj.type == "roomtype"){
				  var arry = new Array();
					arry.push(pcode);
					arry.push(code);
					var str = '<fmt:message key="ccm.error.016"/>';
					message = i18n_replace(str,arry)
				 // message = "包价【"+pcode+"】在当前酒店中已被{房型}代码【"+code+"】引用,不允许删除";
			  }else if(obj.type == "dynamic"){
				  var arry = new Array();
					arry.push(pcode);
					arry.push(code);
					var str = '<fmt:message key="ccm.error.017"/>';
					message = i18n_replace(str,arry)
				//  message = "包价【"+pcode+"】在当前酒店的{动态打包}列表中已被引用,不允许删除";
			  }
			  alert(message);
		  }
       }
    });	

}
*/

//查询
function query(){
	return true;
}
</script>