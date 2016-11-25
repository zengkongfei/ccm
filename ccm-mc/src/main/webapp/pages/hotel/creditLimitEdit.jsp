<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="title_wp"><fmt:message key="ccm.creditlimit.setup"/></div>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form id="hotelForm" action="/creditLimit_save.do" method="post">

	<appfuse:ccmToken name="token"></appfuse:ccmToken>
	<div class="c_whitebg pdA12">
		<input type="hidden" name="creditLimit.channelCode" value="${creditLimit.channelCode }" id="channelCode">
		<input type="hidden" id="hotelIds" name="hotelIds" value="${creditLimit.hotelIds}">
		<input type="hidden" id="hotelCodes" name="hotelCodes" value="${hoteCodes}">
		<input type="hidden" id="creditLimitId" name="creditLimit.creditLimitId" value="${creditLimit.creditLimitId}">
		<div class="mgB24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.creditlimit.groupname"/>：</span>
					</div>
					<div class="i_input">
						<input name="creditLimit.groupName" value="${creditLimit.groupName }" class="fxt w240" maxlength="50">
					</div>
				</li>
			
			    <li class="col3_1">
					<div class="i_title">
						<span class="star"><fmt:message key="ccm.Channel.ChannelCode"/>：</span>
					</div>
					<div class="i_input " style="position:relative;">
						<select name="creditLimit.channelId" id="channelId"  class="fxt w120 required">
							<option value=""><fmt:message key="common.select.plesesSelect"/></option>
							<c:forEach items="${channelList }" var="channel">
								<option value="${channel.channelId }" <c:if test="${ creditLimit.channelId==channel.channelId}">selected</c:if>>${channel.channelCode }</option>
							</c:forEach>
						</select>
					</div>	
				</li>
				 <li class="col3_1">
					<div class="i_title">
						<span class="star"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/>：</span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="soc_hotelId" style="display: none;" class="fxt w120 required" multiple="multiple" >
							<c:forEach items="${hotelList}" var="hotel">
									<option value="${hotel.hotelId}"
									 	${fn:contains(creditLimit.hotelIds, hotel.hotelId)?"selected":""}
									>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
						</select>
					</div>	
				</li>
				
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.creditlimit.CHANNELEMAIL"/>：</span>
					</div>
					<div class="i_input">
						<input  name="creditLimit.channelEmail" value="${creditLimit.channelEmail }" class="fxt w240 required" maxlength="100">
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.creditlimit.HOTELEMAIL"/>：</span>
					</div>
					<div class="i_input">
						<input name="creditLimit.hotelEmail" value="${creditLimit.hotelEmail }" class="fxt w240 required" maxlength="200">
					</div>
				</li>
				
				
				<li class="numLi">
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileOriginalCreditLimit"/>：</span>
					</div>
					<div class="i_input">
						<input name="creditLimit.originalCreditLimit" value="${creditLimit.originalCreditLimit }" class="fxt w240 num ro wr required" maxlength="19">
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title">
						<span class="star"><fmt:message key="common.type"/>：</span>
					</div>
					<div class="i_input " style="position:relative;">
						<input type="radio" name="creditLimit.limitType" onChange="chooseLimitType(this.value);" value="0"
						<c:if test="${creditLimit.minLimitPct ==null}"> checked="true"</c:if>>
						<fmt:message key="common.FixedAmount"/></>
						<input type="radio" name="creditLimit.limitType" onChange="chooseLimitType(this.value);" value="1"
						<c:if test="${creditLimit.minLimitPct !=null}"> checked="true"</c:if>>
						<fmt:message key="common.Percentage"/></>
					</div>	
				</li>
				
				<li class="numLi">
					<div class="i_title">
						<span class="star"></span><span class="text" id="type_minCredit">
							<c:if test="${creditLimit.minLimitPct ==null}"><fmt:message key="ccm.BookingDepositReport.MinCredit"/>：</c:if>
							<c:if test="${creditLimit.minLimitPct !=null}"><fmt:message key="ccm.BookingDepositReport.MinCreditPct"/>：</c:if>
						</span>
					</div>
					<div class="i_input">
						<input name="creditLimit.minLimit" value="${creditLimit.minLimit }" class="fxt w240 neg ro wr num required" maxlength="19"
						<c:if test="${creditLimit.minLimitPct !=null}"> type="hidden"</c:if>>
						
						<input name="creditLimit.minLimitPct" value="${creditLimit.minLimitPct}" class="fxt w30 num ro wr required" maxlength="2"
						<c:if test="${creditLimit.minLimitPct ==null}"> type="hidden"</c:if>>
					</div>
				</li>
				<li class="numLi">
					<div class="i_title">
						<span class="text"></span><span class="text" id="type_emailCredit">
							<c:if test="${creditLimit.minLimitPct ==null}"><fmt:message key="ccm.creditlimit.LIMITAMOUNTALERT"/>：</c:if>
							<c:if test="${creditLimit.minLimitPct !=null}"><fmt:message key="ccm.BookingDepositReport.EmailCreditPct"/>：</c:if>
						</span>
					</div>
					<div class="i_input">
						<input name="creditLimit.emailLimit" value="${creditLimit.emailLimit }" class="fxt w240 neg ro wr num" maxlength="19"
						<c:if test="${creditLimit.minLimitPct !=null}"> type="hidden"</c:if>>
						
						<input name="creditLimit.emailLimitPct" value="${creditLimit.emailLimitPct}" class="fxt w30 num ro wr" maxlength="2"
						<c:if test="${creditLimit.minLimitPct ==null}"> type="hidden"</c:if>>
					</div>
				</li>
				<li class="numLi">
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.BookingDepositReport.TotalRoomRev"/>：</span>
					</div>
					<div class="i_input">
						<input name="creditLimit.totalRoomRev" value="${creditLimit.totalRoomRev }" class="fxt w240 num uneditable" style="background-color: rgb(204, 204, 204);" maxlength="19" readonly="true">
					</div>
				</li>
				<li class="numLi">
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileBalance"/>：</span>
					</div>
					<div class="i_input">
						<input name="" value="${creditLimit.originalCreditLimit- creditLimit.minLimit+creditLimit.income-creditLimit.totalRoomRev}" class="fxt w240 num uneditable" maxlength="19" style="background-color: rgb(204, 204, 204);" readonly="true">
					</div>
				</li>
				<li class="numLi">
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileIncome"/>：</span>
					</div>
					<div class="i_input">
						<input name="creditLimit.income" value="${creditLimit.income }" class="fxt w240 num uneditable" vlass="fxt w240 num uneditable" style="background-color: rgb(204, 204, 204);" maxlength="19" readonly="true">
					</div>
				</li>
				<li class="numLi">
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.ProfileList.ProfileReceivable"/>：</span>
					</div>
					<div class="i_input">
						<input name="creditLimit.variable" value="${creditLimit.variable }" class="fxt w240 neg ro num_ng  <c:if test="${empty creditLimit.creditLimitId}">uneditable</c:if>  " maxlength="19" <c:if test="${empty creditLimit.creditLimitId}">readonly="true" style="background-color: rgb(204, 204, 204);"</c:if>>
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
	   $('#soc_hotelId').parent().append(headHtml);
	   setTimeout(function () { 
		 //初始化
		  $('#soc_hotelId').multiselect({
			dropRight: true,
	        enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:192,
			buttonWidth: '194px',
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>',
	    });
	   }, 50);
		$('#soc_hotelId').next().remove();
	});



	$(document).ready(function() {
        
		 //只能输入数字和小数点  
		 $(".num").keyup(function(){          
             $(this).val($(this).val().replace(/[^0-9.]/g,''));    
         }).bind("paste",function(){  //CTR+V事件处理    
             $(this).val($(this).val().replace(/[^0-9.]/g,''));     
         }).css("ime-mode", "disabled"); //CSS设置输入法不可用    
         
       //只能输入数字和小数点  
		 $(".num_ng").keyup(function(){          
             $(this).val($(this).val().replace(/[^0-9.-]/g,''));    
         }).bind("paste",function(){  //CTR+V事件处理    
             $(this).val($(this).val().replace(/[^0-9.-]/g,''));     
         }).css("ime-mode", "disabled"); //CSS设置输入法不可用    
		
		jQuery.extend(jQuery.validator.messages, {
			required : "<fmt:message key='common.RequiredField'/>"			
		});	

		//保存
		$('.f_save').click(function() {
			var hotelIds = $("#soc_hotelId").val()
			if(hotelIds==null || hotelIds=='null'){
				alert("<fmt:message key='ccm.ReservationMonitorReport.PleaseSelectTheHotel'/>")
				return;
			}
			
			if(!$("#hotelForm").valid()){
				return;
			}
			var channelCode = $("#channelId").find("option:selected").text();
			var hoteCodes = $("#soc_hotelId").next().find("button").attr("title");
			$("#channelCode").val(channelCode);
			$("#hotelIds").val(hotelIds);
			$("#hotelCodes").val(hoteCodes);
			
			check();
			
		});
	});
	
	function chooseLimitType(value){
			if(value==0){
				$($("input[name='creditLimit.minLimit']").get(0)).attr("type","text");
				$($("input[name='creditLimit.emailLimit']").get(0)).attr("type","text");
				$($("input[name='creditLimit.minLimitPct']").get(0)).attr("type","hidden");
				$($("input[name='creditLimit.emailLimitPct']").get(0)).attr("type","hidden");
				$("#type_minCredit").html("<fmt:message key='ccm.BookingDepositReport.MinCredit'/>：");
				$("#type_emailCredit").html("<fmt:message key='ccm.creditlimit.LIMITAMOUNTALERT'/>：");
			}else{
				$($("input[name='creditLimit.minLimit']").get(0)).attr("type","hidden");
				$($("input[name='creditLimit.emailLimit']").get(0)).attr("type","hidden");
				$($("input[name='creditLimit.minLimitPct']").get(0)).attr("type","text");
				$($("input[name='creditLimit.emailLimitPct']").get(0)).attr("type","text");
				$("#type_minCredit").html("<fmt:message key='ccm.BookingDepositReport.MinCreditPct'/>：");
				$("#type_emailCredit").html("<fmt:message key='ccm.BookingDepositReport.EmailCreditPct'/>：");
			}
	}
	
	function calPct(){
		var limitType=$("input:radio[name='creditLimit.limitType']:checked").val();
		if(limitType==1){
			$($("input[name='creditLimit.minLimitPct']").get(0)).addClass("required");
			var amount=$("input[name='creditLimit.originalCreditLimit']").get(0).value;
			var  min_limit_pct=$("input[name='creditLimit.minLimitPct']").get(0).value;
			if(min_limit_pct!=null&&amount!=null){
				var cal_min_limit=min_limit_pct*0.01*amount;
				$("input[name='creditLimit.minLimit']").get(0).value=cal_min_limit.toFixed(2);
			}
			var  email_limit_pct=$("input[name='creditLimit.emailLimitPct']").get(0).value;
			if(email_limit_pct!=null&&amount!=null){
				var cal_email_limit=email_limit_pct*0.01*amount;
				$("input[name='creditLimit.emailLimit']").get(0).value=cal_email_limit.toFixed(2);
			}
		}else{
			$($("input[name='creditLimit.minLimitPct']").get(0)).removeClass("required");
			$("input[name='creditLimit.minLimitPct']").get(0).value=null;
			$("input[name='creditLimit.emailLimitPct']").get(0).value=null;
		}
	}
	function submitForm(){
		calPct();
		
		$.ajax({
			url :'/creditLimit_save.do',
			cache : false,
			dataType : "text",
			type : 'POST',
			data:$("#hotelForm").serialize(),
			success : function(data) {
				alert(data);
				if(data=='success'){
					window.location.href = '/creditLimit_edit.do?creditLimitId=${creditLimit.creditLimitId }';
				}
			}
		});
	}
	
	function check(){
		$.ajax({
			url :'/creditLimit_checkBind.do',
			cache : false,
			dataType : "text",
			type : 'POST',
			data:{"creditLimitId":$("#creditLimitId").val(),"channelId":$("#channelId").val(),"channelCode":$("#channelCode").val(),"hotelIds":$("#hotelIds").val(),"hotelCodes":$("#hotelCodes").val()},
			success : function(data) {
				if(data=='true'){
					submitForm();
				}else{
					alert(data);
				}
			}
		});
	}
	
	
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
	
</script>
