<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="title_wp"><fmt:message key="ccm.ProfileList.EditProfile"/> </div>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form id="hotelForm" action="/custom_save.do" method="post">
	<appfuse:ccmToken name="token"></appfuse:ccmToken>
	<s:hidden name="custom.customId" />
	<div class="c_whitebg pdA12">
		<div class="mgB24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileType"/>：</span>
					</div>
					<div class="i_input">
						<s:select list="#request.profileType"  key="custom.type" cssClass="fxt w240 required"></s:select>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileName"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.name" cssClass="fxt w240 required" maxLength="250"></s:textfield>
					</div>
				</li>
				
   				<li>
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileBusiness"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.business" cssClass="fxt w240" maxlength="200"></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileMobile"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.mobile" cssClass="fxt w240" maxlength="200"></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileFax"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.fax" cssClass="fxt w240" maxlength="200"></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileAddress"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.address" cssClass="fxt w240" maxlength="200"></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileEmail"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.email" cssClass="fxt w240" maxlength="200"></s:textfield>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.BookingDepositReport.AccessCode"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.accessCode" cssClass="fxt w240 required"  maxlength="40"></s:textfield>
					</div>
				</li>	
			    <li class="col3_1">
					<div class="i_title">
						<span class="star"><fmt:message key="ccm.Channel.ChannelCode"/>：</span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="channelListId" name="custom.channelCode" class="fxt w120">
							<c:forEach items="${channelList}" var="channel">
								<option value="${channel.channelCode}"
								 	${fn:contains(custom.channelCode, channel.channelCode)?"selected":""}
								>${channel.channelCode}</option>			
							</c:forEach>
						</select>
					</div>	
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text">Corp/IATA No：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.corpIATANo" cssClass="fxt w240" maxlength="88"></s:textfield>
					</div>
				</li>	
				
				<li class="col3_1">
					<div class="i_title">
						<span class="text"></span><span class="text">ProfileID：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.profileID" cssClass="fxt w240 numOnly"  maxlength="20"></s:textfield>
					</div>
				</li>	
				
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Channel.DefaultGuarantee"/>：</span>
						
					</div>
					<div class="i_input" style="position:relative;">
						<select id="guaranteeListId" name="custom.defGuaranteeId" class="fxt w120">
							<option value=""><fmt:message key="common.select.plesesSelect"/></option>
							<c:forEach items="${hotelGuaranteeVOList}" var="hotelGuaranteeVO">
								<option value="${hotelGuaranteeVO.guaranteeId}"
			 					<c:if test="${hotelGuaranteeVO.guaranteeId == custom.defGuaranteeId}">selected="selected"</c:if>>
								${hotelGuaranteeVO.guaranteeCode}</option>			
							</c:forEach>
						</select>
					</div>	
				</li>
				
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Channel.DefaultCancel"/>：</span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="cancellationListId" name="custom.defCancelId" class="fxt w120">
							<option value=""><fmt:message key="common.select.plesesSelect"/></option>
							<c:forEach items="${hotelCancelVOList}" var="hotelCancelVO">
								<option value="${hotelCancelVO.cancelId}"
								<c:if test="${hotelCancelVO.cancelId == custom.defCancelId}">selected="selected"</c:if>>
								${hotelCancelVO.cancelPolicyCode}</option>			
							</c:forEach>
						</select>
					</div>	
				</li>
				
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.ProfileList.AutoDeposit"/>：</span>
					</div>
					<div class="i_input">
						<label class="checkbox inline"><s:checkbox key="custom.autoDeposit" onclick="autoDeposit(this);"/></label>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.ProfileList.TransactionCode"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.transactionCode" cssClass="fxt w240"  maxlength="50"></s:textfield>
					</div>
				</li>	
				<li class="col3_1">
					<div class="i_title">
						<span class=""></span><span class="text">TRACE DEPT：</span>
					</div>
					<div class="i_input">
						<s:textfield name="custom.traceDept" cssClass="fxt w240"  maxlength="50"></s:textfield>
					</div>
				</li>	
			</ul>
		</div>
		<hr class="dashed">
		<div class="listinputCtrl">
			<button type="button" class="btn_1 green mgR12 f_save"><fmt:message key="common.button.OK"/></button>
			<a class="btn_1 white" href="/custom_list.do"><fmt:message key="common.Return"/></a>
		</div>
	</div>
</s:form>
<script>

	$(document).ready(function() {
		$(".uneditable").css("background-color", "#ccccc");
		//默认不可用
		
		 //只能输入数字
		 $(".numOnly").keyup(function(){          
            $(this).val($(this).val().replace(/[^0-9]/g,''));    
        }).bind("paste",function(){  //CTR+V事件处理 
            $(this).val($(this).val().replace(/[^0-9]/g,''));     
        }).css("ime-mode", "disabled"); //CSS设置输入法不可用    
        
		 //只能输入数字和小数点  
		 $(".num").keyup(function(){          
             $(this).val($(this).val().replace(/[^0-9.]/g,''));    
         }).bind("paste",function(){  //CTR+V事件处理    
             $(this).val($(this).val().replace(/[^0-9.]/g,''));     
         }).css("ime-mode", "disabled"); //CSS设置输入法不可用    
         
         //只能输入数字和小数点，负号 
		 $(".neg").keyup(function(){          
             $(this).val($(this).val().replace(/[^0-9.-]/g,''));    
         }).bind("paste",function(){  //CTR+V事件处理    
             $(this).val($(this).val().replace(/[^0-9.-]/g,''));     
         }).css("ime-mode", "disabled"); //CSS设置输入法不可用    
		
		jQuery.extend(jQuery.validator.messages, {
			required : "<fmt:message key='common.RequiredField'/>"			
		});	
		$("#hotelForm").effectiveAndValidate( {
			rules : {
				'custom.corpIATANo' : {
					isLetterDigit : true
				},
				'custom.accessCode' : {
					isUpperLetterDigit : true
				}
			}
		});

		//保存
		$('.f_save').click(function() {
			
			//Access Code和Channel Code关联
			/*
			var accessCode = $("input[name='custom.accessCode']").val(); 
			var channelCode= $('#channelListId option:selected').val(); 
			if(!(accessCode==channelCode)){
				alert("<fmt:message key='ccm.ProfileList.ProfileConfirmTip2'/>!");
				return;
			}
			*/
			
			if($("#hotelForm_custom_transactionCode").val()!=""){
				$('#hotelForm_custom_autoDeposit').addClass("required");
			}else{
				$('#hotelForm_custom_autoDeposit').removeClass("required");
			}
			//验证表单
			if (!$("#hotelForm").valid()) {
				return;
			} else {
				$("#hotelForm").submit();
				//禁止重复提交
				$('.f_save').addClass('no_ald');
				$('.f_save').attr("disabled","disabled");	
			}
			
		});
	});
	
	//验证数字格式
	 function checkNum(number){
　         
		var reg1 = new RegExp("^(([0-9]+\.[0-9]*[0-9][0-9]*)|([0-9]*[0-9][0-9]*\.[0-9]+)|([0-9]*[0-9][0-9]*))$");//负浮点数
		var reg2 = new RegExp("^(-(([0-9]+\.[0-9]*[0-9][0-9]*)|([0-9]*[0-9][0-9]*\.[0-9]+)|([0-9]*[0-9][0-9]*)))$");//正浮点数 	
		if((!reg1.test(number)) && (!reg2.test(number)))
		{
			
			return false;
		}
	
		if(isNaN(parseFloat(number)))
		{
			
			return false;
		}
		//检查两个小数点
		if(number.replace('.','').indexOf('.')!=-1)
		{
			
			return false;
		}
		//检查不能超过两个小数,并且小数点后需要带一个数,小数点前必需要带一个数
		if(number.indexOf('.')!=-1  && ((number.length - number.lastIndexOf('.')) >3 || (number.indexOf('.')+1) == number.length || number.indexOf('.')==0)) 
		{
			
			return false;	
		}
		
		return true;
	}
	
	function autoDeposit(t){
		if($(t).is(':checked')){
			$("#hotelForm_custom_transactionCode").addClass("required");
		}else{ 
			$("#hotelForm_custom_transactionCode").removeClass("required");
		}
	}
	
</script>
