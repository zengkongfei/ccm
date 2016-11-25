<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
 <div class="title_wp"><fmt:message key="ccm.CancellationRules.List"/>
          <div class="bt"> 
          	<a href="javascript:addData();" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          <!--搜索项-->
          <s:form action="/hotelCancel_list.do" method="post">
          <div class="nm_box ">
          	<ul class="inq_wp frow">
                <li class="col3_2">
	                <div class="i_title ">
		                <span class="text"><fmt:message key="ccm.CancellationRules.CancellationRulesCode"/>：
			                <s:hidden id="i_cancelIds" name="creteria.cancelIds"/>
			                <s:hidden name="creteria.cancelPolicyCodes" value="creteria.cancelPolicyCodes"></s:hidden> 
		                </span>
	                </div>
	                <div class="i_input cancels" style="position:relative;">
	                	<div class="moreoption">
							<div class="opts">
								<div class="text typeName" style="width: 350px;">
								<c:if test="${empty creteria.cancelPolicyCodes}">
									<fmt:message key="common.select.plesesSelect"/>
								</c:if>
								<c:if test="${!empty creteria.cancelPolicyCodes}">
									${creteria.cancelPolicyCodes}
								</c:if>
								</div>
							</div>
						</div>
						<div class="ft_layer abs ddds" style="width: 388px;">
							<div class=" n_overFlowY">
								<div class="mgA6">
									<c:forEach items="${requestScope.cancelPolicyList}" var="rl">
										<label class="checkbox"> 
											<input type="checkbox" value="${rl.cancelId}" name="creteria.packageIds" 
												<c:if test="${fn:contains(creteria.cancelIds,rl.cancelId)}">checked="checked" </c:if> > 
												<span class=""> 
													<span class="span_cancelPolicyCode">${rl.cancelPolicyCode }</span> 
													<span class="span_policyName">${rl.policyName }</span> 
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
			<display:table name="result.resultList" id="hotelCancelList" class="ccm_table1" requestURI="" pagesize="20" size="result.totalCount" partialList="true">
				<display:column property="cancelPolicyCode" sortable="true" titleKey="ccm.CancellationRules.CancellationRulesCode" headerClass="sorted" />
				<display:column property="policyName" sortable="true" titleKey="ccm.CancellationRules.CancellationRulesName" headerClass="sorted" />
				<display:column property="description" sortable="true" titleKey="common.Description" headerClass="sorted" />
				<display:column property="createdTime" sortable="true" titleKey="ccm.ReservationMonitorReport.CreatedTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted"/>
				<display:column titleKey="ccm.UserActivityLog.Action">
					<a href="/hotelCancel_toEdit.do?hotelCancelVo.hotelCancelId=${hotelCancelList.hotelCancelId}" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" hotelCancelId=${hotelCancelList.hotelCancelId} class="link del_ifself"><fmt:message key="common.button.delete"/></a>
				</display:column>
			</display:table>
	 	  </div>
      </div>

<script>
$(document).ready(function() {
	
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
		$('.cancels input:checked').each(function(){ 
			codes += $(this).val()+',';
		});
		$('.cancels input:checked').next('span').each(function(){ 
			names += $(this).find('.span_cancelPolicyCode').text()+',';
		});
		codes = codes.substr(0,codes.lastIndexOf(','));
		names = names.substr(0,names.lastIndexOf(','));
		
		$("input[name='creteria.cancelIds']").val(codes);
		$('.cancels .typeName').text(names);
		$("input[name='creteria.cancelPolicyCodes']").val(names);
		$(this).parent().parent().slideUp();
	});
});

function addData(){
	$.ajax({
		type:"POST",
	   	async:false,
	   	url:"hotelCancel_isNotHaveCancel.do",
	   	success:function(data){
	   		if("false" == data){
	   			alert('<fmt:message key="ccm.error.025"/>');
	   		}else{
	   			location.href = setHotelIdForHref('/hotelCancel_toEdit.do');
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
   	  url:"hotelCancel_validateDeleteCancel.do",
   	  data:{"hotelCancelVo.hotelCancelId":pid},
	  success:function(data){
		  var obj = eval("("+data+")");
		  if(obj.result = 'success'){
			  var r = confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
			  if (r == true) {
				  location.href = '/hotelCancel_delete.do?hotelCancelVo.hotelCancelId='+pid;
			  }
		  }else if(obj.result = 'fail'){
			  var code = obj.code;
			  var pcode = obj.pcode;
			  var message = "";
			  if(obj.type == "rateplan"){
				  var arry = new Array();
					arry.push(pcode);
					arry.push(code);
					var str = '<fmt:message key="ccm.error.024"/>';
					message = i18n_replace(str,arry);
				  //message = "取消规则【"+pcode+"】在当前酒店中已被{房价}代码【"+code+"】引用,不允许删除";
			  }
			  alert(message);
		  }
       }
    });	
}
*/

/*是否删除本条 setHotelIdForHref */
$('.del_ifself').bind('click',function(){
	var pid = $(this).attr('hotelCancelId');
	//删除时验证
	$.ajax({
   	  type:"POST",
   	  async:false,
   	  url:"hotelCancel_validateDeleteCancel.do",
   	  data:{"hotelCancelVo.hotelCancelId":pid},
	  success:function(data){
		  var obj = eval("("+data+")");
		  if(obj.result = 'success'){
			  var r = confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
			  if (r == true) {
				  location.href = setHotelIdForHref('/hotelCancel_delete.do?hotelCancelVo.hotelCancelId='+pid);
			  }
		  }else if(obj.result = 'fail'){
			  var code = obj.code;
			  var pcode = obj.pcode;
			  var message = "";
			  if(obj.type == "rateplan"){
				  var arry = new Array();
					arry.push(pcode);
					arry.push(code);
					var str = '<fmt:message key="ccm.error.024"/>';
					message = i18n_replace(str,arry);
				  //message = "取消规则【"+pcode+"】在当前酒店中已被{房价}代码【"+code+"】引用,不允许删除";
			  }
			  alert(message);
		  }
       }
    });	
	
});

//查询
function query(){
	return true;
}
</script>