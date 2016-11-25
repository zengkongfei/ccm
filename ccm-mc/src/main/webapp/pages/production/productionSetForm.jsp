<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<s:form id="productSetForm" action="product_ajaxBatchOnOffSet.do" method="post">
<s:hidden id="product_channelCodes" name="product.channelCode"/>
<s:hidden id="product_ratePlanCodes" name="product.ratePlanCode"/>
<s:hidden id="product_roomTypeCodes" name="product.roomTypeCode"/>
<s:hidden id="product_dataStr" name="dataStr"/>
<s:hidden id="product_weeks" name="product.weeks"/>
	<div class="pp_main">
		<div class="t_title"><fmt:message key="ccm.RestrictionsManagement.Restrictions"/></div>
		<div class="pdA24">
			<div class="mgB18">
				<div class="mgB6">
					<div class="fm_bwp inline w240">
						<div class="title yahei"><fmt:message key="ccm.InventoryManagement.Channels"/></div>
					</div>
					<div class="fm_bwp inline w240">
						<div class="title yahei"><fmt:message key="ccm.RestrictionsManagement.RateCodeDescription"/></div>
					</div>
					<div class="fm_bwp inline w240">
						<div class="title yahei"><fmt:message key="ccm.InventoryManagement.RoomTypes"/> </div>
					</div>
				</div>
				<div class="fm_bwp inline w240">
					<div class="sel_ele">
						<span id="form_channel_select_all" class="select_all"><fmt:message key="common.select.selectAll"/></span>
						<span id="form_channel_select_inverse" class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
					</div>
					<div class="fm_box form_channelCode">
						<c:forEach items="${channelList }" var="cl" varStatus="idx">
		                    <label class="checkbox">
		                      <input type="checkbox" id="${cl.channelId }" value="${cl.channelCode }" >
		                      <span class="">${cl.channelCode }</span> 
		                    </label>
		                </c:forEach>
					</div>
				</div>
				<div class="fm_bwp inline w240">
					<div class="sel_ele">
						<span id="form_ratePlan_select_all" class="select_all"><fmt:message key="common.select.selectAll"/></span>
						<span id="form_ratePlan_select_inverse" class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
					</div>
					<div class="fm_box form_ratePlanCode">
					</div>
				</div>
				<div class="fm_bwp inline w240">
					<div class="sel_ele">
						<span id="form_roomType_select_all" class="select_all"><fmt:message key="common.select.selectAll"/></span>
						<span id="form_roomType_select_inverse" class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
					</div>
					<div class="fm_box form_roomTypeCode">
					</div>
				</div>
			</div>
			<ul class="list_input">
				<li class="c_rel">
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="common.time.BeginDate"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="product_fromDate" cssClass="fxt w120" name="product.startDate" ></s:textfield>
					</div>
					<div class="date_abs form_weeks">
						<div class="dateweek">
							<div class="d_wp">
								<span><fmt:message key="common.week.sunday"/></span> 
								<input type="checkbox" value="1" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.monday"/></span> 
								<input type="checkbox" value="2" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.tuesday"/></span> 
								<input type="checkbox" value="3" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.wednesday"/></span> 
								<input type="checkbox" value="4" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.thursday"/></span> 
								<input type="checkbox" value="5" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.friday"/></span> 
								<input type="checkbox" value="6" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.saturday"/></span> 
								<input type="checkbox" value="7" checked>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="common.time.EndDate"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="product_toDate" cssClass="fxt w120" name="product.endDate" ></s:textfield>
					</div>
				</li>
			</ul>
			<hr class="dashed">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="ccm.RestrictionsManagement.RestrictionsSwitch"/>：</span>
					</div>
					<div class="i_input">
						<label class="radio inline"> 
							<input type="radio" name="product.onOff" value="1" checked>
							<span class=""><fmt:message key="ccm.InventoryManagement.Open"/></span>
						</label>
						<label class="radio inline"> 
							<input type="radio" name="product.onOff" value="0"> 
							<span class=""><fmt:message key="ccm.InventoryManagement.Close"/></span> 
						</label>
					</div>
				</li>
			</ul>
		</div>
		<div class="b_crl">
			<button type="button" class="btn_2 green mgR6 product_save"><fmt:message key="common.button.save"/></button>
			<button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
		</div>
	</div>
</s:form>
<script type="text/javascript" src="/js/main.js"></script>
<script>
$(document).ready(function(){
	channelSelectForm();//渠道全选/反选
	ratePlanSelectForm();//房价全选/反选
	roomTypeSelectForm();//房型全选/反选
	//动态获得所有房价
	$('.form_channelCode').click(function(){
		getAllRatePlanForm();
	});
	//动态获得所有房型
	$('.form_ratePlanCode').click(function(){
		getAllRoomTypeForm();
	});
	//日期显示
	var dpconfig = {
		dateFormat : "yy-mm-dd",
		dayNamesMin : [ 
		               '<fmt:message key="calendar.week.sunday"/>', 
		               '<fmt:message key="calendar.week.monday"/>', 
		               '<fmt:message key="calendar.week.tuesday"/>', 
		               '<fmt:message key="calendar.week.wednesday"/>', 
		               '<fmt:message key="calendar.week.thursday"/>', 
		               '<fmt:message key="calendar.week.friday"/>', 
		               '<fmt:message key="calendar.week.saturday"/>' 
		              ],
		showMonthAfterYear : true,
		yearSuffix : '<fmt:message key="time.year"/>',
		monthNames : [ 
		               '<fmt:message key="calendar.month.january"/>', 
		               '<fmt:message key="calendar.month.february"/>', 
		               '<fmt:message key="calendar.month.march"/>', 
		               '<fmt:message key="calendar.month.april"/>', 
		               '<fmt:message key="calendar.month.may"/>', 
		               '<fmt:message key="calendar.month.june"/>', 
		               '<fmt:message key="calendar.month.july"/>', 
		               '<fmt:message key="calendar.month.august"/>',
		               '<fmt:message key="calendar.month.september"/>', 
		               '<fmt:message key="calendar.month.october"/>', 
		               '<fmt:message key="calendar.month.november"/>', 
		               '<fmt:message key="calendar.month.december"/>' 
		              ]
	}
	//开始/结束时间
	$("#product_fromDate").datepicker($.extend(dpconfig, {
		minDate:new Date(),
		onClose : function(v) {
			$("#product_toDate").datepicker("option", "minDate", v);
		}
	}));
	$("#product_toDate").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			$("#product_fromDate").datepicker("option", "maxDate", v);
		}
	}));
	//单选按钮样式控制
	$('#productSetForm input[type=radio]').change(function(){
		$('#productSetForm input[type=radio]').next().removeClass('checked');
		var this_checked=$(this).is(':checked');
		var hl_span=$(this).next();
		if(this_checked){
			hl_span.addClass('checked');
		}else{
			hl_span.removeClass('checked');
		}
	});
	//批量设置开/关产品保存
	$('#productSetForm .product_save').click(function(){
		var channelCode='';
		var ratePlanCode='';
		var roomTypeCode='';
		var weeks='';
		var fromDate = $('#product_fromDate').val();
		var toDate = $('#product_toDate').val();
		$('.form_channelCode input:checked').each(function(){ 
			channelCode += $(this).val()+',';
		});
		$('.form_ratePlanCode input:checked').each(function(){ 
			ratePlanCode += $(this).val()+',';
		});
		$('.form_roomTypeCode input:checked').each(function(){ 
			roomTypeCode += $(this).val()+',';
		});
		$('.form_weeks input:checked').each(function(){ 
			weeks += $(this).val()+',';
		});
		channelCode = channelCode.substr(0,channelCode.lastIndexOf(','));
		ratePlanCode = ratePlanCode.substr(0,ratePlanCode.lastIndexOf(','));
		roomTypeCode = roomTypeCode.substr(0,roomTypeCode.lastIndexOf(','));
		weeks = weeks.substr(0,weeks.lastIndexOf(','));
		$('#product_channelCodes').val(channelCode);
		$('#product_ratePlanCodes').val(ratePlanCode);
		$('#product_roomTypeCodes').val(roomTypeCode);
		$('#product_weeks').val(weeks);
		
		//判定输入项
		if(channelCode==''){
			alert('<fmt:message key="ccm.InventoryManagement.error.channelNull"/>！');
			return;
		}
		if(ratePlanCode==''){
			alert('<fmt:message key="ccm.RestrictionsManagement.error.rateNull"/>！');
			return;
		}
		if(roomTypeCode==''){
			alert('<fmt:message key="ccm.InventoryManagement.error.roomTypeNull"/>！');
			return;
		}
		if(fromDate==''){
			alert('<fmt:message key="ccm.InventoryManagement.error.beginDateNull"/>！');
			return;
		}
		if(toDate==''){
			alert('<fmt:message key="ccm.InventoryManagement.error.endDateNull"/>！');
			return;
		}
		
		//校验开始时间
		var fromDateCode = validateYYYYMMDD(fromDate); 
		if(fromDateCode!='success'){
			alert(getErrorMsg(fromDateCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
			return;
		}else if(isMorethanNow(fromDate)=='false'){
			alert('开始时间不能小于当前日期');
			return;
		}
		
		//校验结束时间
		var toDateCode = validateYYYYMMDD(toDate); 
		if(toDateCode!='success'){
			alert(getErrorMsg(toDateCode,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-DD'));
			return;
		}else if(isMorethanNow(toDate)=='false'){
			alert('结束时间不能小于当前日期');
			return;
		}
		//提交表单
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: "product_ajaxBatchOnOffSet.do",
	        data: $('#productSetForm').serialize(),
	        async:false,
	        cache:false,
	        success: function(data){
	        	if(data=='fail'){
	        		alert("<fmt:message key='ccm.RestrictionsManagement.error.RestrictionsSetupFailure'/>!");
	        	}else{
	        		alert("<fmt:message key='ccm.RestrictionsManagement.error.SetupSuccess'/>!");
	        	}
	        	//清空数据
	    		resetForm(); 
	        }
		});
	});
	
	$('.popup-close').bind('click',function(){
		var dataStr = JSON.parse($("#product_dataStr").val());
		if(!dataStr.roomTypeCodes==""){
			//getCalendars(dataStr);
			var result = checkData();//验证提交数据
			if(result) getCalendars(getDataObject("query"));
		}
	});
});	

//重置表单
function resetForm(){
	$("#productSetForm")[0].reset(); //清空表单数据
	$('.form_ratePlanCode label').remove();//删除房价数据
	$('.form_roomTypeCode label').remove();//删除房型数据
	//设置渠道显示样式
	$('.form_channelCode span').each(function(){ 
		$(this).removeClass('checked');
	});
	//单选按钮样式控制
	$('#productSetForm input[type=radio]').next().removeClass('checked');
	$('#productSetForm input[type=radio]:eq(0)').next().addClass('checked');
}

//渠道点击事件，获得相关房价
function getAllRatePlanForm(){
	var channelIds = '';
	$('.form_channelCode input:checked').each(function(){ 
		channelIds += $(this).attr("id")+',';
	});
	$.ajax({
        type: "get",
        dataType: "json",
        url: "product_ajaxGetRatePlanList.do",
        data: {"channelIds":channelIds},
        async:false,
        cache:false,
        success: function(data){
        	var ratePlanHtml = '';
        	if(data.length>0){
        		for(var i=0;i<data.length;i++){
        			var rateplanName = data[i].ratePlanName == null ? '':data[i].ratePlanName;
        			ratePlanHtml += '<label class="checkbox">'
    		        	+'<input type="checkbox" id="'+data[i].ratePlanId+'" value="'+data[i].ratePlanCode+'" >'
    		        	+'<span class="">'+data[i].ratePlanCode+'&nbsp;'+rateplanName+'</span>'
    		        	+'</label>';
        		}
        	}
       		$('.form_ratePlanCode label').remove();
       		$('.form_roomTypeCode label').remove();
       		$('.form_ratePlanCode').append(ratePlanHtml);
        }
	});
}

//渠道点击事件，获得相关房型
function getAllRoomTypeForm(){
	var channelIds = '';
	var ratePlanIds = '';
	$('.form_channelCode input:checked').each(function(){ 
		channelIds += $(this).attr("id")+',';
	});
	$('.form_ratePlanCode input:checked').each(function(){ 
		ratePlanIds += $(this).attr("id")+',';
	});
	$.ajax({
        type: "get",
        dataType: "json",
        url: "product_ajaxGetRoomTypeList.do",
        data: {"channelIds":channelIds,"ratePlanIds":ratePlanIds},
        async:false,
        cache:false,
        success: function(data){
        	var roomTypeHtml = '';
        	if(data.length>0){
        		for(var i=0;i<data.length;i++){
        			var pmsCode = '';
        			if(!strIsNull(data[i].pmsCode)){
        				pmsCode += "_"+data[i].pmsCode;
        			}
        			roomTypeHtml += '<label class="checkbox">'
    		        	+'<input type="checkbox" id="'+data[i].roomTypeId+'" value="'+data[i].roomTypeCode+'" >'
    		        	+'<span class="">'+data[i].roomTypeCode+'&nbsp;'+data[i].roomTypeName+pmsCode+'</span>'
    		        	+'</label>';
        		}
        	}
       		$('.form_roomTypeCode label').remove();
       		$('.form_roomTypeCode').append(roomTypeHtml);
        }
	});
}

//渠道全选/反选
function channelSelectForm(){
	$('#form_channel_select_all').click(function(){
		$find=$(this).parents('.fm_bwp').find('input[type=checkbox]');
		$find.prop('checked','true')
		$find.next().addClass('checked');
		getAllRatePlanForm();//动态获得所有房价
	});
	$('#form_channel_select_inverse').click(function(){
		$find=$(this).parents('.fm_bwp').find('input[type=checkbox]');
		$find.each(function(){
			$c_input=$(this).children('input');
			$c_span=$(this).children('span');
			if($c_input.is(':checked')){
				$c_input.prop('checked',false)
				$c_span.removeClass('checked');}
			else{	
				$c_input.prop('checked','true')
				$c_span.addClass('checked');}
		});
		getAllRatePlanForm();//动态获得所有房价
	});
}

//房价全选/反选
function ratePlanSelectForm(){
	$('#form_ratePlan_select_all').click(function(){
		$find=$(this).parents('.fm_bwp').find('input[type=checkbox]');
		$find.prop('checked','true')
		$find.next().addClass('checked');
		getAllRoomTypeForm();//动态获得所有房价
	});
	$('#form_ratePlan_select_inverse').click(function(){
		$find=$(this).parents('.fm_bwp').find('input[type=checkbox]');
		$find.each(function(){
			$c_input=$(this).children('input');
			$c_span=$(this).children('span');
			if($c_input.is(':checked')){
				$c_input.prop('checked','')
				$c_span.removeClass('checked');}
			else{	
				$c_input.prop('checked','true')
				$c_span.addClass('checked');}
		});
		getAllRoomTypeForm();//动态获得所有房价
	});
	
	
}

//房型全选/反选
function roomTypeSelectForm(){
	$('#form_roomType_select_all').click(function(){
		$find=$(this).parents('.fm_bwp').find('input[type=checkbox]');
		$find.prop('checked','true')
		$find.next().addClass('checked');
	});
	$('#form_roomType_select_inverse').click(function(){
		$find=$(this).parents('.fm_bwp').find('input[type=checkbox]');
		$find.each(function(){
			$c_input=$(this).children('input');
			$c_span=$(this).children('span');
			if($c_input.is(':checked')){
				$c_input.prop('checked','')
				$c_span.removeClass('checked');}
			else{	
				$c_input.prop('checked','true')
				$c_span.addClass('checked');}
		});
	});
}
</script>