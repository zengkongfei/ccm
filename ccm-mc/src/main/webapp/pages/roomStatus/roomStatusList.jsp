<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" 
	contentType="text/html;charset=utf-8"%>
<div class="CCMmainConter w1200">
<div style="display: none;" id="roomStatus_dataStr"></div>
  <div class="title_wp"> <fmt:message key="ccm.InventoryManagement"/>
    <div class="bt"> 
	    <a href="#SetSalesData" class="btn_2 blue ccm-popup-click"><fmt:message key="ccm.InventoryManagement.BatchAllotment"/></a> 
	    <a href="#SetBooking" class="btn_2 blue ccm-popup-click"><fmt:message key="ccm.InventoryManagement.BatchOB"/></a> 
	    <a href="#SetOB" class="btn_2 blue ccm-popup-click"><fmt:message key="ccm.InventoryManagement.BatchOBChannel"/></a> 
	    <a href="#RemoveSalesData" class="btn_2 blue ccm-popup-click"><fmt:message key="ccm.InventoryManagement.RemoveAllotment"/></a> 
	    <a href="#SetRateCodes" id="clickForSetRateCodes" style="display:none" class="btn_2 blue ccm-popup-click"><fmt:message key="ccm.InventoryManagement.OverbookingClass"/></a> 
    </div>
  </div>
  <!--设置销售数据-->
  <%@ include file="roomStatusSellForm.jsp"%>
  <%@ include file="roomStatusRemoveForm.jsp"%>
  <!--设置OverBooking-->
  <%@ include file="roomStatusOverBookingForm.jsp"%>
  <!--设置OB渠道分类-->
  <%@ include file="roomStatusOBChannelForm.jsp"%>
  <!-- setup rateCode for per day -->
  <%@ include file="roomStatusRateCodesForm.jsp"%>
  <div class="c_whitebg" > 
    <!--搜索项-->
    <div class="nm_box nopad">
      <div class="pdA18">
        <ul class="inq_wp frow">
          <li class="col3_3 mgT6">
            <div class="i_title"><span class="text"><fmt:message key="ccm.InventoryManagement.RoomTypes"/>：</span></div>
            <div class="i_input" style="position:relative;">
              <div class="moreoption" id="Two_click">
                <div class="opts">
                  <div class="text w360 typeCode" style="display: none;"></div>
                  <div class="text w360 typeCodeShow"><fmt:message key="common.select.plesesSelect"/></div>
                  <div class="text w360 typeName" style="display: none;"></div>
                </div>
              </div>
              <!--房型查看隐藏层-->
              <div id="Two_show" class="ft_layer abs" style="width:399px;">
                <div class=" n_overFlowY">
                  <div class="mgA6">
	                  <c:forEach items="${roomTypeList }" var="rl" varStatus="idx">
	                    <label class="checkbox">
	                      <input type="checkbox" id="roomType_${idx.index }" value="${rl.roomTypeCode }" name="roomTypeChk" >
	                      <span class="">
		                      <span class="span_roomTypeCode">${rl.roomTypeCode }</span>
		                      <span class="span_roomTypeName">${rl.roomTypeName } 
		                      	<c:if test="${!empty rl.pmsCode}">_${rl.pmsCode}</c:if>
		                      </span>
	                      </span> 
	                    </label>
	                  </c:forEach>
                  </div>
                </div>
                <div class="ft_ctr1">
                  <button type="button" class="btn_3 white selectAll" style="float:left;"><fmt:message key="common.select.selectAll"/></button>
                  <button type="button" class="btn_3 white reverseSel" style="float:left;"><fmt:message key="common.select.Unselect"/></button>
                  <button type="button" class="btn_3 green mgR6 confirmthis"><fmt:message key="common.button.OK"/></button>
                  <button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>
                </div>
              </div>
            </div>
            <div class="i_title"><span class="text"><fmt:message key="ccm.InventoryManagement.Channels"/>：</span></div>
            <div class="i_input" style="position:relative;">
              <div class="moreoption" id="Two_channel_click">
                <div class="opts">
                  <div class="text w360 typeCode" style="display: none;"></div>
                  <div class="text w360 typeName"><fmt:message key="common.select.plesesSelect"/></div>
                </div>
              </div>
              <!--渠道查看隐藏层-->
              <div id="Two_channel_show" class="ft_layer abs" style="width:399px;">
                <div class=" n_overFlowY">
                  <div class="mgA6">
	                  <c:forEach items="${cgAllList }" var="rl" varStatus="idx">
	                    <label class="checkbox">
	                      <input type="checkbox" id="channelCode_${idx.index }" value="${rl.channelId }" name="channelCodeChk" >
	                      <span class="">
		                      <span class="span_chanenlCode">${rl.channelCode }</span>
	                      </span>
	                    </label>
	                  </c:forEach>
                  </div>
                </div>
                <div class="ft_ctr1">
                  <button type="button" class="btn_3 white selectAll" style="float:left;"><fmt:message key="common.select.selectAll"/></button>
                  <button type="button" class="btn_3 white reverseSel" style="float:left;"><fmt:message key="common.select.Unselect"/></button>
                  <button type="button" class="btn_3 green mgR6 confirmthis"><fmt:message key="common.button.OK"/></button>
                  <button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>
                </div>
              </div>
            </div>
            
            <button type="button" class="btn_2 black mgL24 list_search"><fmt:message key="common.button.search"/></button>
            </li>
        </ul>
      </div>
      <div class="nm_ctr1"> 
	      <a href="javascript:;" class="month_link month_up"><fmt:message key="common.month.monthUp"/></a>
	      <a href="javascript:;" class="month_link month_current"><fmt:message key="common.month.monthCurrent"/></a>
	      <a href="javascript:;" class="month_link month_down"><fmt:message key="common.month.monthDown"/></a>
          <label class="checkbox inline mgL24">
          	<input type="checkbox" id="MonthCustomSwitch" value="option1" ><fmt:message key="common.month.UserDefined"/>
          </label>
        <span id="MonthCustom" style="display:none;">
        <select class="fxt c_year">
        </select>
        <span class="TB1 mgL6"><fmt:message key="time.year"/></span>
        <select class="fxt c_month">
          <option>01</option>
          <option>02</option>
          <option>03</option>
          <option>04</option>
          <option>05</option>
          <option>06</option>
          <option>07</option>
          <option>08</option>
          <option>09</option>
          <option>10</option>
          <option>11</option>
          <option>12</option>
        </select>
        <span class="TB1 mgL6"><fmt:message key="common.month.month"/></span> </span> </div>
    </div>
  </div>
  <!-- 日历列表位置 -->
</div>
 
<script>
$(document).ready(function() {
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
	$("#sell_fromDate").datepicker($.extend(dpconfig, {
		minDate:new Date(),
		onClose : function(v) {
			$("#sell_toDate").datepicker("option", "minDate", v);
		}
	}));
	$("#sell_toDate").datepicker($.extend(dpconfig, {
		minDate:new Date(),
		onClose : function(v) {
			$("#sell_fromDate").datepicker("option", "maxDate", v);
		}
	}));
	
	//remove 开始/结束时间
	$("#sell_fromDate2").datepicker($.extend(dpconfig, {
		minDate:new Date(),
		onClose : function(v) {
			$("#sell_toDate").datepicker("option", "minDate", v);
		}
	}));
	$("#sell_toDate2").datepicker($.extend(dpconfig, {
		minDate:new Date(),
		onClose : function(v) {
			$("#sell_fromDate").datepicker("option", "maxDate", v);
		}
	}));
	//ob 时间
	$("#ob_fromDate").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			$("#ob_toDate").datepicker("option", "minDate", v);
		}
	}));
	$("#ob_toDate").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			$("#ob_fromDate").datepicker("option", "maxDate", v);
		}
	}));
	
	var dataObject = {};
	
	RadioCheckedName('ccm_rdption1');
	RadioCheckedName('ccm_rdption2');
	
	$('#link_click').bind('click',function(){
		$('#link_show').slideDown();
	});
	$('#Two_click').bind('click',function(){
		$('#Two_show').slideDown();
	});
	$('#Two_channel_click').bind('click',function(){
		$('#Two_channel_show').slideDown();
	});
	$('#link_show .closethis').bind('click',function(){
		$('#link_show').slideUp();
	});
	$('#Two_show .closethis').bind('click',function(){
		$('#Two_show').slideUp();
	});
	$('#Two_channel_show .closethis').bind('click',function(){
		$('#Two_channel_show').slideUp();
	});
	//全选
	$("#Two_show .selectAll").bind('click',function(){
		var checklist = document.getElementsByName("roomTypeChk");
		for(var i=0;i<checklist.length;i++){
		      checklist[i].checked = true;
		} 
	});
	//反选
	$("#Two_show .reverseSel").bind('click',function(){
		
		var checklist = document.getElementsByName("roomTypeChk");
		for(var i=0;i<checklist.length;i++){
		     checklist[i].checked = !checklist[i].checked;
		} 
	});
	//全选
	$("#Two_channel_show .selectAll").bind('click',function(){
		var checklist = document.getElementsByName("channelCodeChk");
		for(var i=0;i<checklist.length;i++){
		      checklist[i].checked = true;
		} 
	});
	//反选
	$("#Two_channel_show .reverseSel").bind('click',function(){
		var checklist = document.getElementsByName("channelCodeChk");
		for(var i=0;i<checklist.length;i++){
		     checklist[i].checked = !checklist[i].checked;
		} 
	});
	
	//房型选择
	$('#Two_show .confirmthis').click(function(){
		var roomTypeCode='';
		var roomTypeName='';
		$('#Two_show input:checked').each(function(){ 
			roomTypeCode += $(this).val()+',';
		});
		$('#Two_show input:checked').next('span').each(function(){ 
			//roomTypeName += $(this).find("span.span_roomTypeCode").text() + ",";
			roomTypeName += $(this).find("span.span_roomTypeName").text()+",";
		});
		roomTypeCode = roomTypeCode.substr(0,roomTypeCode.lastIndexOf(','));
		roomTypeName = roomTypeName.substr(0,roomTypeName.lastIndexOf(','));
		$('#Two_click .typeCode').text(roomTypeCode);
		$('#Two_click .typeName').text(roomTypeName);
		$('#Two_click .typeCodeShow').text(roomTypeCode);
		$('#Two_show').hide();
	});
	
	//渠道选择
	$('#Two_channel_show .confirmthis').click(function(){
		var channelCode='';
		var channelIds='';
		$('#Two_channel_show input:checked').each(function(){ 
			channelIds += $(this).val()+',';
		});
		$('#Two_channel_show input:checked').next('span').each(function(){ 
			channelCode += $(this).find("span.span_chanenlCode").text()+",";
		});
		channelIds = channelIds.substr(0,channelIds.lastIndexOf(','));
		channelCode = channelCode.substr(0,channelCode.lastIndexOf(','));
		$('#Two_channel_click .typeCode').text(channelIds);
		$('#Two_channel_click .typeName').text(channelCode);
		$('#Two_channel_show').hide();
	});
	$("#MonthCustomSwitch").click(function(){
		var count=$(".c_year option").length;
	    for(var i=0;i<count;i++){
	  	  if(isNowYear($(".c_year").get(0).options[i].text))
	          {
	              $(".c_year").get(0).options[i].selected = true;
	              break;
	          }
	      }
	});
	
	//列表查询
	$('.list_search').click(function(){
		var year = "";
		var month = "";
		var roomTypeCodes = $('#Two_click .typeCode').text();
		var roomTypeNames = $('#Two_click .typeName').text();
		var channelCodes = $('#Two_channel_click .typeName').text();
		
		//根据选中房型初始化日历表格
		var table = '';
		if(roomTypeCodes==''){
			alert('<fmt:message key="ccm.InventoryManagement.SelectRoomType"/>！');
			return;
		}
		var channelCodeArray = channelCodes.split(",");
		if(channelCodes=='' || channelCodes =='<fmt:message key="common.select.plesesSelect"/>'){
			channelCodeArray=[];
		}
		var roomTypeCodesArray = roomTypeCodes.split(",");
		
		var arrayLength = roomTypeCodes.split(",").length;
		$('.roomstatus').remove();
		var table = createTable(roomTypeCodesArray,channelCodeArray);
		$('.c_whitebg').after(table);//追加table
		
		//判断自定义是否选中
		if($("#MonthCustomSwitch").is(':checked')){
			year =$(".c_year").find("option:selected").text();
			month =$(".c_month").find("option:selected").text();
		}
		
		//组装数据
		dataObject.roomTypeCodes = $('#Two_click .typeCode').text();
		dataObject.currentQueryDate = "";
		dataObject.queryFlag = "query";
		dataObject.year = year;
		dataObject.month = month;
		dataObject.channelIds=$('#Two_channel_click .typeCode').text();
		//设置隐藏提交数据
		var dataStr = JSON.stringify(dataObject);
		$("#roomStatus_dataStr").text(dataStr);
		//AJAX提交表单
		getCalendars(dataObject);
		
		//设置freesell
		$('.CCMmainConter table .td_freeSell').unbind("dblclick");
		$('.CCMmainConter table .td_freeSell').bind("dblclick",function(){
			freesellSet(this,1);
		});
		
		//设置td_overBooking
		$('.CCMmainConter table .td_overBooking').unbind("dblclick");
		$('.CCMmainConter table .td_overBooking').bind("dblclick",function(){
			overBookingSet(this);
		});
		
		//设置cutoffdays
		$('.CCMmainConter table .td_cutOffDays').unbind("dblclick");
		$('.CCMmainConter table .td_cutOffDays').bind("dblclick",function(){
			cutoffdaysSet(this,1);
		});
		
		//设置ratecodes
		$('.CCMmainConter table .td_rateCodes').unbind("dblclick");
		$('.CCMmainConter table .td_rateCodes').bind("dblclick",function(){
			showRateCodes(this,1);
		});
		
		//设置allotment
		$('.CCMmainConter table .td_allotment').unbind("dblclick");
		$('.CCMmainConter table .td_allotment').bind("dblclick",function(){
			allotmentSet(this,1);
		});
		
		//设置sendBlockCodeToPMS
		$('.CCMmainConter table .td_bcToPMS').unbind("dblclick");
		$('.CCMmainConter table .td_bcToPMS').bind("dblclick",function(){
			blockcodeToPMSSet(this,1);
		});
		
		var roomTypeCodes = $('#Two_click .typeCode').text().trim().split(',');
		var channelCode = $('#Two_channel_click .typeCode').text().trim();
		
	});
	
	//上月
	$('.month_up').click(function(){
		var months = $(".CCMmainConter .month:eq(0)").text();
		//组装数据
		dataObject.roomTypeCodes = $('#Two_click .typeCode').text();
		dataObject.queryFlag = "up";
		var y = '<fmt:message key="time.year"/>';//年
		var m = '<fmt:message key="common.month.month"/>';//月
		dataObject.currentQueryDate = months.replace(y,'t').replace(m,'t');
		dataObject.year = "";
		dataObject.month = "";
		dataObject.channelIds=$('#Two_channel_click .typeCode').text();
		
		if(dataObject.roomTypeCodes==''){
			alert('<fmt:message key="ccm.InventoryManagement.SelectRoomType"/>！');
			return;
		}
		
		//设置隐藏提交数据
		var dataStr = JSON.stringify(dataObject);
		$("#roomStatus_dataStr").text(dataStr);
		//AJAX提交表单
		getCalendars(dataObject);
	});
	
	//本月
	$('.month_current').click(function(){
		//组装数据
		dataObject.roomTypeCodes = $('#Two_click .typeCode').text();
		dataObject.currentQueryDate = "";
		dataObject.queryFlag = "current";
		dataObject.year = "";
		dataObject.month = "";
		dataObject.channelIds=$('#Two_channel_click .typeCode').text();
		
		if(dataObject.roomTypeCodes==''){
			alert('<fmt:message key="ccm.InventoryManagement.SelectRoomType"/>！');
			return;
		}
		//设置隐藏提交数据
		var dataStr = JSON.stringify(dataObject);
		$("#roomStatus_dataStr").text(dataStr);
		//AJAX提交表单
		getCalendars(dataObject);
	});
	
	//下月
	$('.month_down').click(function(){
		var months = $(".CCMmainConter .month:eq(0)").text();
		//组装数据
		dataObject.roomTypeCodes = $('#Two_click .typeCode').text();
		dataObject.queryFlag = "down";
		var y = '<fmt:message key="time.year"/>';//年
		var m = '<fmt:message key="common.month.month"/>';//月
		dataObject.currentQueryDate = months.replace(y,'t').replace(m,'t');
		dataObject.year = "";
		dataObject.month = "";
		dataObject.channelIds=$('#Two_channel_click .typeCode').text();
		
		if(dataObject.roomTypeCodes==''){
			alert('<fmt:message key="ccm.InventoryManagement.SelectRoomType"/>！');
			return;
		}
		//设置隐藏提交数据
		var dataStr = JSON.stringify(dataObject);
		$("#roomStatus_dataStr").text(dataStr);
		//AJAX提交表单
		getCalendars(dataObject);
	});
	
	//配置房量参数
	$('#link_show .confirmthis').click(function(){
		$('#link_show input').each(function(){
			var valChk = $(this).val();
			if($(this).is(':checked')){
				if(valChk == 'available'){
					$(".CCMmainConter table").find(".tr_available td:gt(0)").show();
				}else{
					$(".CCMmainConter table").find(".tr_"+valChk).show();
				}
			}else{
				if(valChk == 'available'){
					$(".CCMmainConter table").find(".tr_available td:gt(0)").hide();
				}else{
					$(".CCMmainConter table").find(".tr_"+valChk).hide();
				}
			}
		});

		$('#link_show').hide();
	});
	
	$('#roomRateCodesForm .rate_save').click(function(){
		//提交表单
		var data = $('#roomRateCodesForm').serialize(); 
		
		$.ajax({
	        type: "post",
	        url: "roomStatus_ajaxAllotmentSet.do",
	        data: data,
	        cache:false,
	        beforeSend : function() {
				$('#SetRateCodes .rate_save').addClass('no_ald');
				$('#SetRateCodes .rate_save').attr('disabled', 'disabled');
			},
	        success: function(data){
	        	//清空数据
	    		$("#roomRateCodesForm")[0].reset();
	    		queryCalendars();
	    		$.magnificPopup.close();
	        }
		});
		
	});
	
	//销售数据保存
	$('#roomStatusSellForm .sell_save').click(function(){
		var roomTypeCode='';
		var channelIds='';
		var weeks='';
		var fromDate = $('#sell_fromDate').val();
		var toDate = $('#sell_toDate').val();

		$('.sell_roomType input:checked').each(function(){ 
			roomTypeCode += $(this).val()+',';
		});
		$('.sell_channel input:checked').each(function(){ 
			channelIds += $(this).val()+',';
		});
		$('.sell_weeks input:checked').each(function(){ 
			weeks += $(this).val()+',';
		});
		roomTypeCode = roomTypeCode.substr(0,roomTypeCode.lastIndexOf(','));
		channelIds = channelIds.substr(0,channelIds.lastIndexOf(','));
		weeks = weeks.substr(0,weeks.lastIndexOf(','));
		$('#sell_roonTypeCodes').val(roomTypeCode);
		$('#sell_channelIds').val(channelIds);
		$('#sell_weeks').val(weeks);
		
		//判定输入项
		if(roomTypeCode==''){
			alert('<fmt:message key="ccm.InventoryManagement.error.roomTypeNull"/>！');
			return;
		}
		if(channelIds==''){
			alert('<fmt:message key="ccm.InventoryManagement.error.channelNull"/>！');
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
		
		if(!fucCheck($("#sell_allotment").val())){   
	        alert('<fmt:message key="ccm.InventoryManagement.errorroomsNumberErrorMessage"/>！');   
	        return;   
	    }
		
		var isPush = $("#sellPush").is(':checked');
		//提交表单
		getChosenRateCodeToBlock();
		var data = $('#roomStatusSellForm').serialize(); 
		if($("#sell_blockcode").val().trim().length>0){
			if(channelIds.indexOf(",")!=-1){
			alert('<fmt:message key="ccm.InventoryManagement.error.blockHasMoreChannelErrorMessage"/>！');
			return;
			}
		}
		$.ajax({
	        type: "post",
	        url: "roomStatus_ajaxSaveSell.do",
	        data: data+"&push="+isPush,
	        cache:false,
	        beforeSend : function() {
				$('#SetSalesData .sell_save').addClass('no_ald');
				$('#SetSalesData .sell_save').attr('disabled', 'disabled');
			},
	        success: function(data){
	        	//清空数据
	    		$("#roomStatusSellForm")[0].reset();
	    		queryCalendars();
	    		$.magnificPopup.close();
	        }
		});
	});
	
	//销售数据保存
	$('#roomStatusRemoveForm .sell_save').click(function(){
		var roomTypeCode='';
		var channelIds='';
		var weeks='';
		var fromDate = $('#sell_fromDate2').val();
		var toDate = $('#sell_toDate2').val();

		$('.sell_roomType2 input:checked').each(function(){ 
			roomTypeCode += $(this).val()+',';
		});
		$('.sell_channel2 input:checked').each(function(){ 
			channelIds += $(this).val()+',';
		});
		$('.sell_weeks2 input:checked').each(function(){ 
			weeks += $(this).val()+',';
		});
		roomTypeCode = roomTypeCode.substr(0,roomTypeCode.lastIndexOf(','));
		channelIds = channelIds.substr(0,channelIds.lastIndexOf(','));
		weeks = weeks.substr(0,weeks.lastIndexOf(','));
		$('#sell_roonTypeCodes2').val(roomTypeCode);
		$('#sell_channelIds2').val(channelIds);
		$('#sell_weeks2').val(weeks);
		
		//判定输入项
		if(roomTypeCode==''){
			alert('<fmt:message key="ccm.InventoryManagement.error.roomTypeNull"/>！');
			return;
		}
		if(channelIds==''){
			alert('<fmt:message key="ccm.InventoryManagement.error.channelNull"/>！');
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
		
		if(!fucCheck($("#sell_allotment").val())){   
	        alert('<fmt:message key="ccm.InventoryManagement.errorroomsNumberErrorMessage"/>！');   
	        return;   
	    }
		
		var isPush = $("#sellPush").is(':checked');
		//提交表单
		var data = $('#roomStatusRemoveForm').serialize(); 
		$.ajax({
	        type: "post",
	        url: "roomStatus_ajaxRemoveSell.do",
	        data: data+"&push="+isPush,
	        cache:false,
	        beforeSend : function() {
				$('#RemoveSalesData .sell_save').addClass('no_ald');
				$('#RemoveSalesData .sell_save').attr('disabled', 'disabled');
			},
	        success: function(data){
	        	//清空数据
	    		$("#roomStatusRemoveForm")[0].reset();
	    		queryCalendars();
	    		$.magnificPopup.close();
	        }
		});
	});
	
	//关闭销售数据页面
	$('.popup-close').bind('click',function(){
		queryCalendars();
	});
	
	var nowYear = (new Date()).getFullYear();
	var yearSelect = $('.c_year');
	
	var minYear = nowYear - 1;
    var maxYear = nowYear + 2;
	for(var i = minYear;i <= maxYear ; i++){
		yearSelect.append('<option value="'+i+'">'+i+'</option>');
	}
	
});

function queryCalendars(){
	var dataStr = $("#roomStatus_dataStr").text();
	if(dataStr !=""){
		var jsonData = JSON.parse(dataStr);
		if(jsonData.roomTypeCodes!=""){
			getCalendars(jsonData);
		}
	}
	
	$('.btn_2.green.mgR6').removeClass('no_ald');
	$('.btn_2.green.mgR6').removeAttr('disabled');
}
//AJAX获取房态日历
function getCalendars(datas){
	$.ajax({
        type: "get",
        dataType: "json",
        url: "roomStatus_ajaxGetCalendars.do",
        data: datas,
        cache:false,
        success: function(data){
        	showRsvtypeChannelList(data,datas);
        }
	});
	$(".rel").removeClass("rel");//删除底部样式
	
}
function showRsvtypeChannelList(jsonData,params){
	var roomTypeChannelMap=new Object();
	$(".CCMmainConter table .tr_available").find("td:gt(1):lt(31)").text("--");
	$(".CCMmainConter table .tr_unavailable").find("td:gt(0):lt(31)").text("--");
	$(".CCMmainConter table .tr_overBooking").find("td:gt(0):lt(31)").text("--");
	$(".CCMmainConter table .tr_totalOBSold").find("td:gt(0):lt(31)").text("--");
	$(".CCMmainConter table .tr_freeSell").find("td:gt(0):lt(31)").text("--");
	$(".CCMmainConter table .tr_freeSell").find("td:gt(0):lt(31)").removeClass("off_fs");
	$(".CCMmainConter table .tr_obSold").find("td:gt(0):lt(31)").text("--");
	
	$(".CCMmainConter table .tr_allotment").find("td:gt(0):lt(31)").text("--");
	$(".CCMmainConter table .tr_allotmentSold").find("td:gt(0):lt(31)").text("--");
	$(".CCMmainConter table .tr_cutOffDays").find("td:gt(0):lt(31)").text("--");
	$(".CCMmainConter table .tr_rateCodes").find("td:gt(0):lt(31)").text("--");
	$(".CCMmainConter table .tr_blockcode").find("td:gt(0):lt(31)").text("--");
	$(".CCMmainConter table .tr_bcToPMS").find("td:gt(0):lt(31)").text("--");
	$(".CCMmainConter table .tr_bcToPMS").find("td:gt(0):lt(31)").removeClass("off_fs");

	var roomTypeCodes = $('#Two_click .typeCode').text();
	var roomTypeNames = $('#Two_click .typeName').text();
	
	var channelCodes = $('#Two_channel_click .typeName').text();
	var roomTypeCodesArray = roomTypeCodes.split(",");
	var roomTypeNamesArray = roomTypeNames.split(",");
	var channelCodeArray = channelCodes.split(",");
	
	for(var i=0; i<roomTypeCodesArray.length; i++){
		var addBlockIndx=1;
		var roomTypeCode = roomTypeCodesArray[i];
		var roomTypeName = roomTypeNamesArray[i];
		var rsvtypeList = jsonData[roomTypeCode];

		//默认设置可买房量为 物理房量
		var availaList = jsonData[roomTypeCode+"_available"];
		if(availaList.length >0 && availaList[0].physicalRooms != null){
			var currentQueryDate = params.currentQueryDate;
			var year = params.year;
			var month = params.month;
			var queryFlag = params.queryFlag;
			
			//如果年月为空
			if(year == "" && month == ""){
				if(currentQueryDate != ""){
					year = currentQueryDate.substr(0,4);
					month = parseInt(currentQueryDate.substr(5,2));
				}else{
					var now = new Date();
					year = now.getFullYear();
					month = now.getMonth()+1;
				}
			}
			
			if(queryFlag == 'up' && month == 1){
				year = parseInt(year) - 1;
				month = 12;
			}else if(queryFlag == 'up'){
				month = parseInt(month) - 1;
			}else if(queryFlag == 'down' && month == 12){
				year = parseInt(year) + 1;
				month = 1;
			}else if(queryFlag == 'down'){
				month = parseInt(month) + 1;
			}
			
			var maxday = 31;
			if(month == 4 || month == 6 || month == 9 || month == 11){
				maxday = 30;
			}else if(month == 2){
				//闰年
				if(year%4==0&&(year%100!=0||year%400==0)){
					maxday = 29;
				}else{
					maxday = 28;
				}
			}
			
			var rtTab = $("#rtTab_"+roomTypeCode);
			var  rcData=$("#rcData_"+roomTypeCode);
			for(var j=0; j<channelCodeArray.length; j++){
				var clTrCls="clTr_"+roomTypeCode+"_"+channelCodeArray[j];
				var idVal = "#id_"+roomTypeCode+"_"+channelCodeArray[j];
				for(var n=2;n<99;n++){
					if(rcData.find("tr[class='"+clTrCls+" tr_allotment"+n+"']").length>0){
							rcData.find("tr[class='"+clTrCls+" tr_allotment"+n+"']").remove();
							rcData.find("tr[class='"+clTrCls+" tr_allotmentSold"+n+"']").remove();
							rcData.find("tr[class='"+clTrCls+" tr_cutOffDays"+n+"']").remove();
							rcData.find("tr[class='"+clTrCls+" tr_rateCodes"+n+"']").remove();
							rcData.find("tr[class='"+clTrCls+" tr_blockcode"+n+"']").remove();
							rcData.find("tr[class='"+clTrCls+" tr_bcToPMS"+n+"']").remove();
							$(""+idVal).attr("rowspan",parseInt($(""+idVal).attr("rowspan"))-6);
					}else{
						break;
					}
					
				}
			}
			
			for(var ii = 1 ; ii <= maxday ; ii ++ ){
				rtTab.find(".tr_available").find("td:eq("+(ii + 1)+")").text(availaList[ii-1].physicalRooms);
			}
			
			v_month = month+"";
			if(v_month.length < 2){
				v_month = "0"+v_month;
			}
			$("#rtDiv_"+roomTypeCode+" .month").text(year+'<fmt:message key="time.year"/>'+v_month+'<fmt:message key="common.month.month"/>'+' <fmt:message key="ccm.InventoryManagement.RoomTypes"/>:'+roomTypeCode+' '+roomTypeName);
			
			
		}
		
		for(var j=0;j<rsvtypeList.length;j++){
			var rtTab = $("#rtTab_"+roomTypeCode);
					
			var rsv = rsvtypeList[j];
			var date = rsv.date;
			var d = parseInt(date.substr(8));//天

			rtTab.find(".tr_available").find("td:eq("+(d+1)+")").text(rsv.available);
			rtTab.find(".tr_unavailable").find("td:eq("+(d)+")").text(rsv.unavailable);
			rtTab.find(".tr_overBooking").find("td:eq("+(d)+")").text(rsv.overBooking);
			rtTab.find(".tr_totalOBSold").find("td:eq("+(d)+")").text(rsv.totalOBSold);
			
			var rcList = rsv.rcList;
			rcList = rcList == null ? [] :rcList;
			for(var k=0;k<rcList.length;k++){
				var addBlockIndx=1;
				var rc = rcList[k];
				var channelCode = rc.channelCode;
				var clTrCls = ".clTr_"+roomTypeCode+"_"+channelCode;
				
				if(roomTypeChannelMap[clTrCls]==undefined||roomTypeChannelMap[clTrCls]==null){
					roomTypeChannelMap[clTrCls]=1;
					}
					addBlockIndx=roomTypeChannelMap[clTrCls];
				var idVal = "#id_"+roomTypeCode+"_"+channelCode;
				var freeSell = '<fmt:message key="ccm.InventoryManagement.Close"/>';
				if(rc.freeSell || rc.freeSell==null){
        			freeSell = '<fmt:message key="ccm.InventoryManagement.Open"/>';
        		}else{
        			rtTab.find(clTrCls+".tr_freeSell").find("td:eq("+(d)+")").addClass("off_fs");
        		}
				
				rtTab.find(clTrCls+".tr_freeSell").find("td:eq("+(d)+")").text(freeSell);
				rtTab.find(clTrCls+".tr_obSold").find("td:eq("+(d)+")").text(rc.obSold);
				
				rtTab.find(clTrCls+".tr_allotment").find("td:eq("+(d)+")").text(rc.allotment);
				rtTab.find(clTrCls+".tr_allotmentSold").find("td:eq("+(d)+")").text(rc.allotmentSold);
				rtTab.find(clTrCls+".tr_cutOffDays").find("td:eq("+(d)+")").text(rc.cutOffDays);
				
				if(rc.ratePlanCodes  != undefined){
					rtTab.find(clTrCls+".tr_rateCodes").find("td:eq("+(d)+")").text(rc.ratePlanCodes);
				}
				
				if(rc.hasBlock ==1){
					if(rc.rsvchanBlockList!=null){
						for(var m=0;m<rc.rsvchanBlockList.length;m++){
							if(m==0){
								rtTab.find(clTrCls+".tr_allotment").find("td:eq("+(d)+")").text(rc.rsvchanBlockList[m].blockNum);
								rtTab.find(clTrCls+".tr_allotmentSold").find("td:eq("+(d)+")").text(rc.rsvchanBlockList[m].blockSold);
								rtTab.find(clTrCls+".tr_cutOffDays").find("td:eq("+(d)+")").text(rc.rsvchanBlockList[m].cutOffDays);
								rtTab.find(clTrCls+".tr_blockcode").find("td:eq("+(d)+")").text(rc.rsvchanBlockList[m].blockCode);
								var isSendToPMS='<fmt:message key="ccm.InventoryManagement.Close"/>';
									if(rc.rsvchanBlockList[m].isSendToPMS || rc.rsvchanBlockList[m].isSendToPMS==null){
										isSendToPMS = '<fmt:message key="ccm.InventoryManagement.Open"/>';
					        		}else{
					        			rtTab.find(clTrCls+".tr_bcToPMS").find("td:eq("+(d)+")").addClass("off_fs");
					        		}
								rtTab.find(clTrCls+".tr_bcToPMS").find("td:eq("+(d)+")").text(isSendToPMS);
								
								if(rc.rsvchanBlockList[m].ratePlanCodes  != undefined){
									rtTab.find(clTrCls+".tr_rateCodes").find("td:eq("+(d)+")").text(rc.rsvchanBlockList[m].ratePlanCodes);
								}else{
									rtTab.find(clTrCls+".tr_rateCodes").find("td:eq("+(d)+")").text("");
								}
							}
							else{
								if((m+1)>addBlockIndx){
								addBlockIndx++;
								var td_allotmentN="";
								var td_allotmentSoldN="";
								var td_cutOffDaysN="";
								var td_ratecodesN="";
								var td_blockcodeN="";
								var td_bcToPMSN="";
								for(var z=1;z<=31;z++){
									td_allotmentN+='<td class="td_allotment'+addBlockIndx+'" style="cursor:pointer;">--</td>';
									td_allotmentSoldN+='<td class="td_allotmentSold'+addBlockIndx+'" style="cursor:pointer;">--</td>';
									td_cutOffDaysN+='<td class="td_cutOffDays'+addBlockIndx+'" style="cursor:pointer;">--</td>';
									td_ratecodesN+='<td class="td_rateCodes'+addBlockIndx+'" style="cursor:pointer;">--</td>';
									td_blockcodeN+='<td class="td_blockcode'+addBlockIndx+'" style="cursor:pointer;">--</td>';
									td_bcToPMSN+='<td class="td_bcToPMS'+addBlockIndx+'" style="cursor:pointer;">--</td>';
								}
								var blockcontent=
								'<tr   class="'+clTrCls.substring(1)+' tr_allotment'+addBlockIndx+'"><td title="<fmt:message key="ccm.InventoryManagement.AllotmentMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.Allotment"/></td>'+td_allotmentN
								+ '<td title="<fmt:message key="ccm.InventoryManagement.AllotmentMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.Allotment"/></td></tr>'
								+ '<tr class="'+clTrCls.substring(1)+' tr_allotmentSold'+addBlockIndx+'"><td title="<fmt:message key="ccm.InventoryManagement.AllotmentSoldMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.AllotmentSold"/></td>'+td_allotmentSoldN
								+ '<td title="<fmt:message key="ccm.InventoryManagement.AllotmentSoldMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.AllotmentSold"/></td></tr>'
								+ '<tr class="'+clTrCls.substring(1)+' tr_rateCodes'+addBlockIndx+'"><td title="<fmt:message key="ccm.InventoryManagement.ChannelAllotmentRateCodesMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelAllotmentRateCodes"/></td>'+td_ratecodesN
								+ '<td title="<fmt:message key="ccm.InventoryManagement.ChannelAllotmentRateCodesMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelAllotmentRateCodes"/></td></tr>'
								+ '<tr class="'+clTrCls.substring(1)+' tr_cutOffDays'+addBlockIndx+'"><td title="<fmt:message key="ccm.InventoryManagement.ChannelCutOffDaysMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelCutOffDays"/></td>'+td_cutOffDaysN
								+ '<td title="<fmt:message key="ccm.InventoryManagement.ChannelCutOffDaysMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelCutOffDays"/></td></tr>'
								+ '<tr class="'+clTrCls.substring(1)+' tr_blockcode'+addBlockIndx+'"><td title="<fmt:message key="ccm.InventoryManagement.ChannelBlockMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelBlockCode"/></td>'+td_blockcodeN
								+ '<td title="<fmt:message key="ccm.InventoryManagement.ChannelBlockMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelBlockCode"/></td></tr>'
								+ '<tr class="'+clTrCls.substring(1)+' tr_bcToPMS'+addBlockIndx+'"><td title=""><fmt:message key="ccm.InventoryManagement.ChannelBlockCodeToPMS"/></td>'+td_bcToPMSN
								+ '<td title=""><fmt:message key="ccm.InventoryManagement.ChannelBlockCodeToPMS"/></td></tr>';
								if(addBlockIndx==2){
									rtTab.find(clTrCls+".tr_bcToPMS").after(blockcontent);
									}else{
										var prePosition=addBlockIndx-1;
										rtTab.find(clTrCls+".tr_bcToPMS"+prePosition).after(blockcontent);
									}
								
								if(roomTypeChannelMap[clTrCls]<addBlockIndx){
									$(""+idVal).attr("rowspan",parseInt($(""+idVal).attr("rowspan"))+6);
									roomTypeChannelMap[clTrCls]=addBlockIndx;
									}
								}
								
								var blockCount=m+1;
								rtTab.find(clTrCls+".tr_allotment"+blockCount).find("td:eq("+(d)+")").text(rc.rsvchanBlockList[m].blockNum);
								rtTab.find(clTrCls+".tr_allotmentSold"+blockCount).find("td:eq("+(d)+")").text(rc.rsvchanBlockList[m].blockSold);
								rtTab.find(clTrCls+".tr_blockcode"+blockCount).find("td:eq("+(d)+")").text(rc.rsvchanBlockList[m].blockCode);
								rtTab.find(clTrCls+".tr_cutOffDays"+blockCount).find("td:eq("+(d)+")").text(rc.rsvchanBlockList[m].cutOffDays);
								var isSendToPMS='<fmt:message key="ccm.InventoryManagement.Close"/>';
								if(rc.rsvchanBlockList[m].isSendToPMS || rc.rsvchanBlockList[m].isSendToPMS==null){
									isSendToPMS = '<fmt:message key="ccm.InventoryManagement.Open"/>';
				        		}else{
				        			rtTab.find(clTrCls+".tr_bcToPMS"+blockCount).find("td:eq("+(d)+")").addClass("off_fs");
				        		}
								rtTab.find(clTrCls+".tr_bcToPMS"+blockCount).find("td:eq("+(d)+")").text(isSendToPMS);
								
								if(rc.rsvchanBlockList[m].ratePlanCodes  != undefined){
									rtTab.find(clTrCls+".tr_rateCodes"+blockCount).find("td:eq("+(d)+")").text(rc.rsvchanBlockList[m].ratePlanCodes);
								}else{
									rtTab.find(clTrCls+".tr_rateCodes"+blockCount).find("td:eq("+(d)+")").text("");
									}
								//设置cutoffdays2
								$(clTrCls+".tr_cutOffDays"+blockCount).find("td:eq("+(d)+")").bind("dblclick",function(){
									cutoffdaysSet(this,blockCount);
								});
								
								//设置ratecodes
								$(clTrCls+".tr_rateCodes"+blockCount).find("td:eq("+(d)+")").bind("dblclick",function(){
									showRateCodes(this,blockCount);
								});
								
								//设置allotment
								$(clTrCls+".tr_allotment"+blockCount).find("td:eq("+(d)+")").bind("dblclick",function(){
									allotmentSet(this,blockCount);
								});
								
								
								//设置sendBlockCodeToPMS
								$(clTrCls+".tr_bcToPMS"+blockCount).find("td:eq("+(d)+")").bind("dblclick",function(){
									blockcodeToPMSSet(this,blockCount);
								});
							}
						}
					}
				}
			}
			
		}
	}
	/*$("tbody").find("[class$='tr_unavailable']").find("td").each(function(i){
		if(i%2==0){
			$("tbody").find(".tr_unavailable").find("td:eq("+i+")").css("background","#f2f2f2");
			$("tbody").find(".tr_overBooking").find("td:eq("+i+")").css("background","#f2f2f2");
			$("tbody").find(".tr_totalOBSold").find("td:eq("+i+")").css("background","#f2f2f2");
			$("tbody").find(".tr_freeSell").find("td:eq("+i+")").css("background","#f2f2f2");
			$("tbody").find(".tr_obSold").find("td:eq("+i+")").css("background","#f2f2f2");
			$("tbody").find("[class*='tr_cutOffDays']").find("td:eq("+i+")").css("background","#f2f2f2");
			$("tbody").find("[class*='tr_allotmentSold']").find("td:eq("+i+")").css("background","#f2f2f2");
			$("tbody").find("[class*='tr_allotment']").find("td:eq("+i+")").css("background","#f2f2f2");
			$("tbody").find("[class*='tr_rateCodes']").find("td:eq("+i+")").css("background","#f2f2f2");
			$("tbody").find("[class*='tr_blockcode']").find("td:eq("+i+")").css("background","#f2f2f2");
			$("tbody").find("[class*='tr_bcToPMS']").find("td:eq("+i+")").css("background","#f2f2f2");
		}else{
			if(i>0){
				$("tbody").find(".tr_available").find("td:eq("+i+")").css("background","#f2f2f2");
			}
		}
	});
	$("tbody").find(".tr_available").find("td:last").css("background","#f2f2f2");*/
	
	$("tbody").find(".tr_available").find("td:gt(1)").css("background","#f2f2f2");
	$("tbody").find(".tr_unavailable").find("td:gt(0)").css("background","#f2f2f2");
	$("tbody").find(".tr_overBooking").find("td:gt(0)").css("background","#f2f2f2");
	$("tbody").find(".tr_totalOBSold").find("td:gt(0)").css("background","#f2f2f2");
	$("tbody").find(".tr_freeSell").find("td:gt(0)").css("background","#f2f2f2");
	$("tbody").find(".tr_obSold").find("td:gt(0)").css("background","#f2f2f2");
	$("tbody").find("[class*='tr_cutOffDays']").find("td:gt(0)").css("background","#f2f2f2");
	$("tbody").find("[class*='tr_allotmentSold']").find("td:gt(0)").css("background","#f2f2f2");
	$("tbody").find("[class*='tr_allotment']").find("td:gt(0)").css("background","#f2f2f2");
	$("tbody").find("[class*='tr_rateCodes']").find("td:gt(0)").css("background","#f2f2f2");
	$("tbody").find("[class*='tr_blockcode']").find("td:gt(0)").css("background","#f2f2f2");
	$("tbody").find("[class*='tr_bcToPMS']").find("td:gt(0)").css("background","#f2f2f2");
	
	for(var ii = 1 ; ii <= maxday ; ii ++ ){
		var nows = year + "-" + month + "-" + ii;
	
		if(typeof(nows)!="undefined" && nows!="" && isMorethanNow(nows)!='false' ){
			$("tbody").find(".tr_available").find("td:eq("+(ii + 1)+")").css("background","");
			$("tbody").find(".tr_unavailable").find("td:eq("+(ii)+")").css("background","");
			$("tbody").find(".tr_overBooking").find("td:eq("+(ii)+")").css("background","");
			$("tbody").find(".tr_totalOBSold").find("td:eq("+(ii)+")").css("background","");
			$("tbody").find(".tr_obSold").find("td:eq("+(ii)+")").css("background","");
			$("tbody").find(".tr_freeSell").find("td:eq("+(ii)+")").css("background","");
			
			$("tbody").find("[class*='tr_cutOffDays']").find("td:eq("+(ii)+")").css("background","");
			$("tbody").find("[class*='tr_allotmentSold']").find("td:eq("+(ii)+")").css("background","");
			$("tbody").find("[class*='tr_allotment']").find("td:eq("+(ii)+")").css("background","");
			$("tbody").find("[class*='tr_rateCodes']").find("td:eq("+(ii)+")").css("background","");
			$("tbody").find("[class*='tr_blockcode']").find("td:eq("+(ii)+")").css("background","");
			$("tbody").find("[class*='tr_bcToPMS']").find("td:eq("+(ii)+")").css("background","");
		}
	}
}
//创建table
function createTable(roomTypeNames,channelCodes){
	var th_31 = '';
	var td_available = '';
	var td_unavailable = '';
	var td_overBooking = '';
	var td_totalOBSold= '';
	
	for(var i=1;i<=31;i++){
		if(i<10){
			th_31 += '<th><span>'+"0"+i+'</span></th>';
		}else{
			th_31 += '<th><span>'+i+'</span></th>';
		}
	}
	th_31+='<th><span><fmt:message key="common.Value"/></span></th>';
	for(var i=1;i<=31;i++){
		td_available += '<td style="">--</td>';
		td_unavailable += '<td style="">--</td>';
		td_overBooking += '<td class="td_overBooking" style="cursor:pointer;">--</td>';
		td_totalOBSold += '<td style="">--</td>';
	}
	
	
	var table = '';
		for(var i=0;i < roomTypeNames.length ;i++){
			table += '<div class="roomstatus" id="rtDiv_'+roomTypeNames[i]+'" >'
			+ '<div class="month"><fmt:message key="ccm.InventoryManagement.fmtDate"/></div>'
			+ '<div class="bt_wp" style="position:relative;width:100%;overflow:auto;padding:0px; margin:0px;">'
			+ '<table class="ccm_table1" id="rtTab_'+roomTypeNames[i]+'">'
			+ '<thead>'
			+ '<tr><th class="w100"><span><fmt:message key="common.Object"/></span></th><th class="w120"><span><fmt:message key="common.Value"/></span></th>'+th_31+'</tr>'
			+ '</thead>'
			+ '<tbody id="rcData_'+roomTypeNames[i]+'">'
			+ '<tr class="tr_available"><td rowspan="4" class="table_roomType"><fmt:message key="ccm.InventoryManagement.Property"/></td><td title="<fmt:message key="ccm.InventoryManagement.AvailableMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.Available"/></td>'+td_available
			+ '<td title="<fmt:message key="ccm.InventoryManagement.AvailableMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.Available"/></td></tr>'
			+ '<tr class="tr_unavailable"><td title="<fmt:message key="ccm.InventoryManagement.SoldMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.Sold"/></td>'+td_unavailable
			+ '<td title="<fmt:message key="ccm.InventoryManagement.SoldMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.Sold"/></td></tr>'
			+ '<tr class="tr_overBooking"><td title="<fmt:message key="ccm.InventoryManagement.TotalOBMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.TotalOB"/></td>'+td_overBooking
			+ '<td title="<fmt:message key="ccm.InventoryManagement.TotalOBMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.TotalOB"/></td></tr>'
			+ '<tr class="tr_totalOBSold"><td title="<fmt:message key="ccm.InventoryManagement.TotalOBSoldMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.TotalOBSold"/></td>'+td_totalOBSold
			+ '<td title="<fmt:message key="ccm.InventoryManagement.TotalOBSoldMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.TotalOBSold"/></td></tr>'	///
			+ getChannelTrHtml(channelCodes,roomTypeNames[i])
			+ '</tbody>'
			+ '</table>'
			+ '</div>'
			+ '</div>';
		}
  return table;
}
function getChannelTrHtml(channelCodes,roomTypeCode){
	var td_freeSell = '';
	var td_allotment = '';
	var td_allotmentSold= '';
	var td_obSold= '';
	var td_cutOffDays='';
	var td_ratecodes='';
	var td_blockcode='';
	var td_bcToPMS='';
	
	for(var i=1;i<=31;i++){
		td_freeSell += '<td class="td_freeSell" style="cursor:pointer;">--</td>';
		td_allotment += '<td class="td_allotment" style="cursor:pointer;">--</td>';
		td_allotmentSold +='<td>--</td>';
		td_obSold +='<td>--</td>';
		td_cutOffDays+='<td class="td_cutOffDays" style="cursor:pointer;">--</td>';
		td_ratecodes+='<td class="td_rateCodes" style="cursor:pointer;">--</td>';
		
		td_blockcode+='<td class="td_blockcode" style="cursor:pointer;">--</td>';
		td_bcToPMS+='<td class="td_bcToPMS" style="cursor:pointer;">--</td>';
	}
	
	var mc_avai_channel = '';
	for(var i=0;i<channelCodes.length;i++){
		var idt="id_"+roomTypeCode+"_"+ channelCodes[i];
		mc_avai_channel += '<tr class="clTr_'+roomTypeCode+"_"+ channelCodes[i] +'"><td rowspan="9" name="channelAllList" id="'+idt+'" class="w100">'+ channelCodes[i] +'</td></tr>'
		+ '<tr class="clTr_'+roomTypeCode+"_"+ channelCodes[i] +' tr_freeSell"><td class="w120" title="<fmt:message key="ccm.InventoryManagement.FreeSellMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.FreeSell"/></td>'+td_freeSell
		+ '<td class="w120" title="<fmt:message key="ccm.InventoryManagement.FreeSellMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.FreeSell"/></td></tr>'
		+ '<tr class="clTr_'+roomTypeCode+"_"+ channelCodes[i] +' tr_obSold"><td title="<fmt:message key="ccm.InventoryManagement.ChannelOBSoldMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelOBSold"/></td>'+td_obSold			///原超卖房量
		+ '<td title="<fmt:message key="ccm.InventoryManagement.ChannelOBSoldMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelOBSold"/></td></tr>'
		+ '<tr class="clTr_'+roomTypeCode+"_"+ channelCodes[i] +' tr_allotment"><td title="<fmt:message key="ccm.InventoryManagement.AllotmentMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.Allotment"/></td>'+td_allotment
		+ '<td title="<fmt:message key="ccm.InventoryManagement.AllotmentMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.Allotment"/></td></tr>'	
		+ '<tr class="clTr_'+roomTypeCode+"_"+ channelCodes[i] +' tr_allotmentSold"><td title="<fmt:message key="ccm.InventoryManagement.AllotmentSoldMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.AllotmentSold"/></td>'+td_allotmentSold
		+ '<td title="<fmt:message key="ccm.InventoryManagement.AllotmentSoldMessageInfo"/>"><fmt:message key="ccm.InventoryManagement.AllotmentSold"/></td></tr>'	///
		+ '<tr class="clTr_'+roomTypeCode+"_"+ channelCodes[i] +' tr_rateCodes"><td title="<fmt:message key="ccm.InventoryManagement.ChannelAllotmentRateCodesMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelAllotmentRateCodes"/></td>'+td_ratecodes
		+ '<td title="<fmt:message key="ccm.InventoryManagement.ChannelAllotmentRateCodesMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelAllotmentRateCodes"/></td></tr>'
		+ '<tr class="clTr_'+roomTypeCode+"_"+ channelCodes[i] +' tr_cutOffDays"><td title="<fmt:message key="ccm.InventoryManagement.ChannelCutOffDaysMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelCutOffDays"/></td>'+td_cutOffDays
		+ '<td title="<fmt:message key="ccm.InventoryManagement.ChannelCutOffDaysMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelCutOffDays"/></td></tr>'					///
		+ '<tr class="clTr_'+roomTypeCode+"_"+ channelCodes[i] +' tr_blockcode"><td title="<fmt:message key="ccm.InventoryManagement.ChannelBlockMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelBlockCode"/></td>'+td_blockcode
		+ '<td title="<fmt:message key="ccm.InventoryManagement.ChannelBlockMsgInfo"/>"><fmt:message key="ccm.InventoryManagement.ChannelBlockCode"/></td></tr>'
		+ '<tr class="clTr_'+roomTypeCode+"_"+ channelCodes[i] +' tr_bcToPMS"><td title=""><fmt:message key="ccm.InventoryManagement.ChannelBlockCodeToPMS"/></td>'+td_bcToPMS
		+ '<td title=""><fmt:message key="ccm.InventoryManagement.ChannelBlockCodeToPMS"/></td></tr>';
		
	}
	return mc_avai_channel;
}

function showRateCodes(obj,num){
	var fmt_y = '<fmt:message key="time.year"/>';//年
	
	// 取日期
	var date = $(".CCMmainConter .month:eq(0)").text();
	//取日
	var y = date.substr(0, date.indexOf(fmt_y));
	var m = date.substr(date.indexOf(fmt_y) + 1, 2);
	d = $(obj).prevAll().length;
	if(!getAfterCurDate(d,date)){
		return false;
	}
	
	var maxday = 31;
	if(m == 4 || m == 6 || m == 9 || m == 11){
		maxday = 30;
	}else if(m == 2){
		//闰年
		if(y%4==0&&(y%100!=0||y%400==0)){
			maxday = 29;
		}else{
			maxday = 28;
		}
	}
	if(d > maxday){
		return false;
	}
		//取房型代码,渠道代码
		var clTrCls = $(obj).parent().attr('class');
		var roomTypeChannel = clTrCls.split(" ")[0];
		var roomTypeCode = roomTypeChannel.split("_")[1];
		var channelCode = roomTypeChannel.split("_")[2];
		//取日期
		var date = $(".CCMmainConter .month:eq(0)").text();
		var y = "";
		var m = "";
		var d = "";
		if(date=="<fmt:message key='ccm.InventoryManagement.fmtDate'/>"){
			return;
		}else{
			y = date.substr(0,date.indexOf(fmt_y));
			m = date.substr(date.indexOf(fmt_y)+1,2);
		}
		d = $(obj).prevAll().length;
		var existedBlockcode=null;
		if(num==1){
			existedBlockcode= ($("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_blockcode']").children().eq(d).text());
		}else{
			existedBlockcode= ($("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_blockcode"+num+"']").children().eq(d).text());
		}
		
		//提交数据
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: "roomStatus_ajaxRateCodeSet.do",
	        data: {"roomStatusVO.blockCode":existedBlockcode,"roomStatusVO.channelCode":channelCode,"roomStatusVO.type":roomTypeCode,"year":y,"month":m,"day":d},
	        cache:false,
	        success: function(data){
	        	if(data==''){//提交数据错误
	        		return;
	        	}else if(data=='fail'){//后台出错
	        		alert('<fmt:message key="ccm.InventoryManagement.error.rateCodesSetupFailure"/>!');
	        	}else{//正常返回对象
	        		for(var roomTypeCode in data){
	        			$("#rateCodeSetPerDate").html("");
	        			var rateCodes=data[roomTypeCode];
	        			for(i=0;i<rateCodes.length;i++){
	        				var rateCode=rateCodes[i];
	    				$("#rateCodeSetPerDate").append(
						"<label class='checkbox'> <input type='checkbox'  onclick='getChosenRateCodePerDate();' name='roomStatusVO.rateCodes'  value='"+rateCode+"'/><span class='checked'>"+rateCode+"</span></label>");		
	        			}
	        		}
	        		$("#roomTypeCodeSpan").text(roomTypeCode);
	        		$("#sell_channelcodeperdate").val(channelCode);
	        		$("#sell_blockcodeperdate").val(existedBlockcode);
	        		$("#sell_y").val(y);
	        		$("#sell_m").val(m);
	        		$("#sell_d").val(d);
	        		$("#sell_type").val(roomTypeCode);
	        		$("#clickForSetRateCodes").click();
	        	}
	        }
		});
		
}

//设置blockcodeToPMS
function blockcodeToPMSSet(obj,num){
	// 取日期
	var fmt_y = '<fmt:message key="time.year"/>';//年
	var date = $(".CCMmainConter .month:eq(0)").text();
	//取日
	var y = date.substr(0, date.indexOf(fmt_y));
	var m = date.substr(date.indexOf(fmt_y) + 1, 2);
	d = $(obj).prevAll().length;
	if(!getAfterCurDate(d,date)){
		return false;
	}
	
	var maxday = 31;
	if(m == 4 || m == 6 || m == 9 || m == 11){
		maxday = 30;
	}else if(m == 2){
		//闰年
		if(y%4==0&&(y%100!=0||y%400==0)){
			maxday = 29;
		}else{
			maxday = 28;
		}
	}
	if(d > maxday){
		return false;
	}
	
	var k='<fmt:message key="ccm.InventoryManagement.Open"/>';//开
	var g='<fmt:message key="ccm.InventoryManagement.Close"/>'//关
	var  blockcodeToPMS= $(obj).text();
	if(blockcodeToPMS==k){
		freesell = false;
	}else if(blockcodeToPMS==g){
		blockcodeToPMS = true;
	}else{
		blockcodeToPMS = false;
	}
	
	//取房型代码,渠道代码
	var clTrCls = $(obj).parent().attr('class');
	var roomTypeChannel = clTrCls.split(" ")[0];
	var roomTypeCode = roomTypeChannel.split("_")[1];
	var channelCode = roomTypeChannel.split("_")[2];
	var existedBlockcode=null;
	if(num==1){
		existedBlockcode= ($("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_blockcode']").children().eq(d).text());
	}else{
		existedBlockcode= ($("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_blockcode"+num+"']").children().eq(d).text());
	}
	
	if(blockcodeToPMS){
		$(obj).text(k);
		$(obj).removeClass("off_fs");
		console.log(k);
	}else{
		$(obj).text(g);
		$(obj).addClass("off_fs");
		console.log(g);
	}
	
	//提交数据
	$.ajax({
        type: "post",
        dataType: "json",
        url: "roomStatus_ajaxAllotmentSet.do",
        data: {"roomStatusVO.blockCode":existedBlockcode,"roomStatusVO.isSendToPMS":blockcodeToPMS,"roomStatusVO.channelCode":channelCode,"roomStatusVO.type":roomTypeCode,"year":y,"month":m,"day":d},
        cache:false,
        success: function(data){
        	if(data==''){//提交数据错误
        		return;
        	}else if(data=='fail'){//后台出错
        		alert('<fmt:message key="ccm.InventoryManagement.error.cutOffDaysSetupFailure"/>!');
        	}else{//正常返回对象
        		var blockcodeToPMS = g;
        		if(data.hasBlock==1){
        			for(var m=0;m<data.rsvchanBlockList.length;m++){
        				if(data.rsvchanBlockList[m].blockCode==existedBlockcode){
        					if(data.rsvchanBlockList[m].isSendToPMS || data.rsvchanBlockList[m].isSendToPMS==null){
        							blockcodeToPMS = k;
        							$(obj).removeClass("off_fs");
        						}else{
        							$(obj).addClass("off_fs");
        						}
        					$(obj).text(blockcodeToPMS);
        					}
						}	
        		}
        	}
        }
	});
}
//设置freesell
function freesellSet(obj,num){
	
	var fmt_y = '<fmt:message key="time.year"/>';//年
	var date = $(".CCMmainConter .month:eq(0)").text();
	
	var k='<fmt:message key="ccm.InventoryManagement.Open"/>';//开
	var g='<fmt:message key="ccm.InventoryManagement.Close"/>'//关
	//取freesell
	var freesell = $(obj).text();
	if(freesell==k){
		freesell = false;
	}else if(freesell==g){
		freesell = true;
	}else{
		freesell = false;
	}
	//取房型代码,渠道代码
	var clTrCls = $(obj).parent().attr('class');
	var roomTypeChannel = clTrCls.split(" ")[0];
	var roomTypeCode = roomTypeChannel.split("_")[1];
	var channelCode = roomTypeChannel.split("_")[2];
	
	//取日期
	var date = $(".CCMmainConter .month:eq(0)").text();
	
	var y = '<fmt:message key="time.year"/>';//年
	var m = '<fmt:message key="common.month.month"/>';//月
	var m = date.substr(date.indexOf(y) + 1, 2);
	//取日
	var y = date.substr(0, date.indexOf(y));
	d = $(obj).prevAll().length;
	if(!getAfterCurDate(d,date)){
		return false;
	}
	
	var maxday = 31;
	if(m == 4 || m == 6 || m == 9 || m == 11){
		maxday = 30;
	}else if(m == 2){
		//闰年
		if(y%4==0&&(y%100!=0||y%400==0)){
			maxday = 29;
		}else{
			maxday = 28;
		}
	}
	if(d > maxday){
		return false;
	}
	//取房型代码,渠道代码
	var clTrCls = $(obj).parent().attr('class');
	var roomTypeChannel = clTrCls.split(" ")[0];
	var roomTypeCode = roomTypeChannel.split("_")[1];
	var channelCode = roomTypeChannel.split("_")[2];
	//取日期
	var date = $(".CCMmainConter .month:eq(0)").text();
	var y = "";
	var m = "";
	var d = "";
	if(date=="<fmt:message key='ccm.InventoryManagement.fmtDate'/>"){
		return;
	}else{
		y = date.substr(0,date.indexOf(fmt_y));
		m = date.substr(date.indexOf(fmt_y)+1,2);
	}
	d = $(obj).prevAll().length;
	var existedBlockcode=null;
	if(num==1){
		existedBlockcode= ($("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_blockcode']").children().eq(d).text());
	}else{
		existedBlockcode= ($("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_blockcode"+num+"']").children().eq(d).text());
	}
	if(freesell){
		$(obj).text(k);
		$(obj).removeClass("off_fs");
		console.log(k);
	}else{
		$(obj).text(g);
		$(obj).addClass("off_fs");
		console.log(g);
	}
	//提交数据
	$.ajax({
        type: "post",
        dataType: "json",
        url: "roomStatus_ajaxFreeSellSet.do",
        data: {"roomStatusVO.blockCode":existedBlockcode,"roomStatusVO.freeSell":freesell,"roomStatusVO.channelCode":channelCode,"roomStatusVO.type":roomTypeCode,"year":y,"month":m,"day":d},
        cache:false,
        success: function(data){
        	if(data==''){//提交数据错误
        		return;
        	}else if(data=='fail'){//后台出错
        		$(obj).text(freesell ? g: k);
        		//alert("开/关房设置失败!");
        	}else{//正常返回对象
        		var freeSell = g;
				if(data.freeSell || data.freeSell==null){
        			freeSell = k;
        			$(obj).removeClass("off_fs");
        		}else{
        			$(obj).addClass("off_fs");
        		}
        		$(obj).text(freeSell);
        	}
        }
	});
}

//setup for cutoffdays
function cutoffdaysSet(obj,num){
var fmt_y = '<fmt:message key="time.year"/>';//年
	
	// 取日期
	var date = $(".CCMmainConter .month:eq(0)").text();
	//取日
	var y = date.substr(0, date.indexOf(fmt_y));
	var m = date.substr(date.indexOf(fmt_y) + 1, 2);
	d = $(obj).prevAll().length;
	if(!getAfterCurDate(d,date)){
		return false;
	}
	
	var maxday = 31;
	if(m == 4 || m == 6 || m == 9 || m == 11){
		maxday = 30;
	}else if(m == 2){
		//闰年
		if(y%4==0&&(y%100!=0||y%400==0)){
			maxday = 29;
		}else{
			maxday = 28;
		}
	}
	if(d > maxday){
		return false;
	}
	
	//鼠标双击时追加输入框并获得焦点
	var inputHtml = '<input type="text" class="w30">';
	var cutOffDays = $(obj).text();
	$(obj).text("");
	$(obj).append(inputHtml);
	$(obj).children("input").val(cutOffDays).focus();
	//输入框失去焦点时删除输入框并提交数据
	$(obj).children("input").blur(function(){
		//取保留房
		cutOffDays = $(this).val();
		if(cutOffDays=="" || cutOffDays=="--"){
			cutOffDays = 0;
		}
		if(!fucCheck(cutOffDays)){   
	        alert('<fmt:message key="ccm.InventoryManagement.error.numberErrorMessage"/>!');   
	        return;   
	    }
		$(this).remove();//删除输入框
		//取房型代码,渠道代码
		var clTrCls = $(obj).parent().attr('class');
		var roomTypeChannel = clTrCls.split(" ")[0];
		var roomTypeCode = roomTypeChannel.split("_")[1];
		var channelCode = roomTypeChannel.split("_")[2];
		//取日期
		var date = $(".CCMmainConter .month:eq(0)").text();
		var y = "";
		var m = "";
		var d = "";
		if(date=="<fmt:message key='ccm.InventoryManagement.fmtDate'/>"){
			return;
		}else{
			y = date.substr(0,date.indexOf(fmt_y));
			m = date.substr(date.indexOf(fmt_y)+1,2);
		}
		d = $(obj).prevAll().length;
		
		var existedBlockcode=null;
		if(num==1){
			existedBlockcode= ($("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_blockcode']").children().eq(d).text());
		}else{
			existedBlockcode= ($("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_blockcode"+num+"']").children().eq(d).text());
		}
		
		//提交数据
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: "roomStatus_ajaxAllotmentSet.do",
	        data: {"roomStatusVO.blockCode":existedBlockcode,"roomStatusVO.cutOffDays":cutOffDays,"roomStatusVO.channelCode":channelCode,"roomStatusVO.type":roomTypeCode,"year":y,"month":m,"day":d},
	        cache:false,
	        success: function(data){
	        	if(data==''){//提交数据错误
	        		return;
	        	}else if(data=='fail'){//后台出错
	        		alert('<fmt:message key="ccm.InventoryManagement.error.cutOffDaysSetupFailure"/>!');
	        	}else{//正常返回对象
	        		if(data.hasBlock==1){
	        			for(var m=0;m<data.rsvchanBlockList.length;m++){
	        				if(data.rsvchanBlockList[m].blockCode==existedBlockcode){
	        					$(obj).text(data.rsvchanBlockList[m].cutOffDays);
	        					}
							}	
	        		}else{
	        			$(obj).text(data.cutOffDays);
	        		}
	        	}
	        }
		});
	});
}
//设置allotment
function allotmentSet(obj,num){
	var fmt_y = '<fmt:message key="time.year"/>';//年
	// 取日期
	var date = $(".CCMmainConter .month:eq(0)").text();
	//取日
	var y = date.substr(0, date.indexOf(fmt_y));
	var m = date.substr(date.indexOf(fmt_y) + 1, 2);
	d = $(obj).prevAll().length;
	if(!getAfterCurDate(d,date)){
		return false;
	}
	
	var maxday = 31;
	if(m == 4 || m == 6 || m == 9 || m == 11){
		maxday = 30;
	}else if(m == 2){
		//闰年
		if(y%4==0&&(y%100!=0||y%400==0)){
			maxday = 29;
		}else{
			maxday = 28;
		}
	}
	if(d > maxday){
		return false;
	}
	
	//鼠标双击时追加输入框并获得焦点
	var inputHtml = '<input type="text" class="w30">';
	var allotment = $(obj).text();
	$(obj).text("");
	$(obj).append(inputHtml);
	$(obj).children("input").val(allotment).focus();
	//输入框失去焦点时删除输入框并提交数据
	$(obj).children("input").blur(function(){
		//取保留房
		allotment = $(this).val();
		if(allotment=="" || allotment=="--"){
			allotment = 0;
		}
		var allotSold=null;
		if(num==1){
			allotSold= $("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_allotmentSold']").children().eq(d).text();
		}else{
			allotSold= $("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_allotmentSold"+num+"']").children().eq(d).text();
		}
		
		if(allotSold=="" || allotSold=="--"){
			allotSold = 0;
		}
		if(allotment<allotSold){
			alert('<fmt:message key="ccm.InventoryManagement.error.AllotmentSoldErrorMessage"/>!');   
			return;
		}
		
		if(!fucCheck(allotment)){   
	        alert('<fmt:message key="ccm.InventoryManagement.error.numberErrorMessage"/>!');   
	        return;   
	    }
		$(this).remove();//删除输入框
		//取房型代码,渠道代码
		var clTrCls = $(obj).parent().attr('class');
		var roomTypeChannel = clTrCls.split(" ")[0];
		var roomTypeCode = roomTypeChannel.split("_")[1];
		var channelCode = roomTypeChannel.split("_")[2];
		//取日期
		var date = $(".CCMmainConter .month:eq(0)").text();
		var y = "";
		var m = "";
		var d = "";
		if(date=="<fmt:message key='ccm.InventoryManagement.fmtDate'/>"){
			return;
		}else{
			y = date.substr(0,date.indexOf(fmt_y));
			m = date.substr(date.indexOf(fmt_y)+1,2);
		}
		d = $(obj).prevAll().length;
		var existedBlockcode=null;
		if(num==1){
			existedBlockcode= ($("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_blockcode']").children().eq(d).text());
		}else{
			existedBlockcode= ($("tr[class='clTr_"+roomTypeCode+"_"+channelCode+" tr_blockcode"+num+"']").children().eq(d).text());
		}
		//提交数据
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: "roomStatus_ajaxAllotmentSet.do",
	        data: {"roomStatusVO.blockCode":existedBlockcode,"roomStatusVO.allotment":allotment,"roomStatusVO.channelCode":channelCode,"roomStatusVO.type":roomTypeCode,"year":y,"month":m,"day":d},
	        cache:false,
	        success: function(data){
	        	if(data==''){//提交数据错误
	        		return;
	        	}else if(data=='fail'){//后台出错
	        		alert('<fmt:message key="ccm.InventoryManagement.error.AllotmentSetupFailure"/>!');
	        	}else{//正常返回对象
	        		if(data.hasBlock==1){
	        			for(var m=0;m<data.rsvchanBlockList.length;m++){
	        				if(data.rsvchanBlockList[m].blockCode==existedBlockcode){
	        					$(obj).text(data.rsvchanBlockList[m].blockNum);
	        					}
							}	
	        		}else{
	        			$(obj).text(data.allotment);
	        		}
	        		
	        	}
	        }
		});
	});
}


//设置overBookingSet
function overBookingSet(obj){
	
	var fmt_y = '<fmt:message key="time.year"/>';//年
	// 取日期
	var date = $(".CCMmainConter .month:eq(0)").text();
	//取日
	var y = date.substr(0, date.indexOf(fmt_y));
	var m = date.substr(date.indexOf(fmt_y) + 1, 2);
	d = $(obj).prevAll().length;
	if(!getAfterCurDate(d,date)){
		return false;
	}
	
	var maxday = 31;
	if(m == 4 || m == 6 || m == 9 || m == 11){
		maxday = 30;
	}else if(m == 2){
		//闰年
		if(y%4==0&&(y%100!=0||y%400==0)){
			maxday = 29;
		}else{
			maxday = 28;
		}
	}
	if(d > maxday){
		return false;
	}
	
	//鼠标双击时追加输入框并获得焦点
	var inputHtml = '<input type="text" class="w30">';
	var overBooking = $(obj).text();
	$(obj).text("");
	$(obj).append(inputHtml);
	$(obj).children("input").val(overBooking).focus();
	//输入框失去焦点时删除输入框并提交数据
	$(obj).children("input").blur(function(){
		overBooking = $(this).val();
		if(overBooking=="" || overBooking=="--"){
			overBooking = 0;
		}
		if(!fucCheck(overBooking)){   
	        alert("<fmt:message key='ccm.InventoryManagement.error.numberErrorMessage'/>！");   
	        return;   
	    }
		$(this).remove();//删除输入框
		//取房型代码,渠道代码
		var tabId = $(obj).parent().parent().parent().attr("id");
		var roomTypeCode = tabId.split("_")[1];
		
		//取日期
		var date = $(".CCMmainConter .month:eq(0)").text();
		var y = "";
		var m = "";
		var d = "";
		if(date=="<fmt:message key='ccm.InventoryManagement.fmtDate'/>"){
			return;
		}else{
			y = date.substr(0,date.indexOf(fmt_y));
			m = date.substr(date.indexOf(fmt_y)+1,2);
		}
		d = $(obj).prevAll().length;
		//提交数据
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: "roomStatus_ajaxSetOverBooking.do",
	        data: {"roomStatusVO.overBooking":overBooking,"roomStatusVO.type":roomTypeCode,"year":y,"month":m,"day":d},
	        cache:false,
	        success: function(data){
	        	if(data==''){//提交数据错误
	        		return;
	        	}else if(data=='fail'){//后台出错
	        		alert("<fmt:message key='ccm.InventoryManagement.error.OverbookingSetupFailureMessage'/>!");
	        	}else{//正常返回对象
	        		$(obj).text(data.overBooking);
	        	}
	        }
		});
	});
}

function removeRateCodeCB(roomTypeCode){
	$("#rateCodeSet_"+roomTypeCode).html("");
}
//全选显示ratecode选项
function addBlockRateCodesConfigForAllRoomTypes(){
	
	$("li[name=rateCodeContainer]").each(function(){
		$(this).css("display","block");
	});
	var roomTypeArray=new Array();
	$("input[name=roomTypeCodeCB]").each(function(){
	        var roomTypeCode=$(this).val();
	        roomTypeArray.push(roomTypeCode);
	});
	getRateCode2(roomTypeArray);
}
//添加单个ratecode选项
function addBlockRateCodesConfigForOneRoomType(c,roomTypeCode){
	var eleId="#"+roomTypeCode+"_rateCodeContainer";
	if(c.checked==true){
		$(eleId).css("display","block");
	}else{
		$(eleId).css("display","none");
			$("input[name='"+roomTypeCode+"_rateCodeCB']:checkbox").each(function(){
				$(this).attr("checked", false);
				});
	}
	getRateCode2(getChosenRoomTypeArray2());
}
//反选ratecode选型
function inverseBlockRateCodesConfigForAllRoomTypes(){
	var roomTypeArray=new Array();
	$("input[name=roomTypeCodeCB]").each(function(){
	        var roomTypeCode=$(this).val();
	        if($(this).is(":checked")){
	        	//$(this).prop("checked", false);
	        	$("#"+roomTypeCode+"_rateCodeContainer").css("display","none");
	        		$("input[name='"+roomTypeCode+"_rateCodeCB']:checkbox").each(function(){
	    				$(this).attr("checked", false);
	    			});
	        }else{
	        	//$(this).prop("checked", true);
	        	$("#"+roomTypeCode+"_rateCodeContainer").css("display","block");
	        	roomTypeArray.push(roomTypeCode);
	        }
	});
	getRateCode2(roomTypeArray);
}

//将获取的ratecodes添加到room中
function addRateCodesToEachRoom(json){
	//json={"roomTypeCode1":["rateCode1","rateCode2"]};
	if(JSON.stringify(json)=='{}'){
		$("div[name='rateCodeSet']").html("");
	}else{
		for(var roomTypeCode in json){
			$("#rateCodeSet_"+roomTypeCode).html("");
			var rateCodes=json[roomTypeCode];
			for(i=0;i<rateCodes.length;i++){
				var rateCode=rateCodes[i];
				$("#rateCodeSet_"+roomTypeCode).append(
						"<label class='checkbox'> <input type='checkbox' name='"+roomTypeCode+"_rateCodeCB' id='"+roomTypeCode+"_"+rateCode+"' value='"+rateCode+"'/><span class='checked'>"+rateCode+"</span></label>");		
				}
			}
	}
}
//将获取的ratecodes添加到room中
function addRateCodesToEachRoom2(json){
	//json={"roomTypeCode1":["rateCode1","rateCode2"]};
	
		for(var roomTypeCode in json){
			$("#rateCodeSet_"+roomTypeCode).html("");
			var rateCodes=json[roomTypeCode];
			for(i=0;i<rateCodes.length;i++){
				var rateCode=rateCodes[i];
				$("#rateCodeSet_"+roomTypeCode).append(
						"<label class='checkbox'> <input type='checkbox' name='"+roomTypeCode+"_rateCodeCB' id='"+roomTypeCode+"_"+rateCode+"' value='"+rateCode+"'/><span class='checked'>"+rateCode+"</span></label>");		
				}
	}
}
//获得某一天的room中被选中的ratecode
function getChosenRateCodePerDate(){
	var roomTypeCode=$("#roomTypeCodeSpan").text();
	var jsonRateCodesWithBlock=new Object();
	$("input[name='setvo.rateCodes']:checked").each(function(){
		var rateCode=$(this).val();
		if( typeof jsonRateCodesWithBlock[roomTypeCode]==undefined||jsonRateCodesWithBlock[roomTypeCode]==null){
			jsonRateCodesWithBlock[roomTypeCode]=new Array();
			jsonRateCodesWithBlock[roomTypeCode].push(rateCode);
		}else{
			jsonRateCodesWithBlock[roomTypeCode].push(rateCode);
		}
	});
	//$("#sell_rateCodeperdate").val(JSON.stringify(jsonRateCodesWithBlock));
}
//获得每个room中被选中的ratecode
function getChosenRateCodeToBlock(){
	var jsonRateCodesWithBlock=new Object();
	$("input[name$='_rateCodeCB']:checked").each(function(){
		var idVal=$(this).attr("id");
		var roomTypeCodeArray=idVal.split("_");
		var roomTypeCode=roomTypeCodeArray[0];
		var rateCode=roomTypeCodeArray[1];
		if( typeof jsonRateCodesWithBlock[roomTypeCode]==undefined||jsonRateCodesWithBlock[roomTypeCode]==null){
			jsonRateCodesWithBlock[roomTypeCode]=new Array();
			jsonRateCodesWithBlock[roomTypeCode].push(rateCode);
		}else{
			jsonRateCodesWithBlock[roomTypeCode].push(rateCode);
		}
	});
	$("#sell_rateCodes").val(JSON.stringify(jsonRateCodesWithBlock));
	//json={"roomTypeCode1":["rateCode1","rateCode2"]};
}
function getChosenRoomTypeArray(){
	var roomTypeArray=new Array();
	$("input[name='roomTypeCodeCB']:checked").each(function(){
		roomTypeArray.push($(this).val());
	});
	return roomTypeArray;
}
function getChosenRoomTypeArray2(){
	var roomTypeArray=new Array();
	$("input[name='roomTypeCodeCB']:checked").each(function(){
		if($("input[name='"+$(this).val()+"_rateCodeCB']").length==0){
			roomTypeArray.push($(this).val());
		}
	});
	return roomTypeArray;
}

//从后台获得所有页面上房型的房价
function getRateCodes(){
	var chosenRoomTypeCodeList=getChosenRoomTypeArray();
	var sellWeeks="";
	$("input[name='weekCB']:checked").each(function(){
		sellWeeks+=$(this).val()+",";
	});
	sellWeeks=sellWeeks.substring(0,sellWeeks.length-1);
	if($("#sell_fromDate").val()==''||$("#sell_toDate").val()==''||chosenRoomTypeCodeList.length==0){
		return;
	}
	//提交数据
	$.ajax({
        type: "post",
        dataType: "json",
        url: "roomStatus_ajaxGetRateCodes.do",
        data: {"chosenRoomTypeCodeStr":JSON.stringify(chosenRoomTypeCodeList),"sellWeeks":sellWeeks,"startDate":$("#sell_fromDate").val(),"endDate":$("#sell_toDate").val(),"chosenRoomTypeCodeArray":JSON.stringify(chosenRoomTypeCodeList)},
        cache:false,
        success: function(data){
        	if(data==''){//提交数据错误
        		return;
        	}else if(data=='fail'){//后台出错
        		alert("<fmt:message key='ccm.InventoryManagement.error.AllotmentSetupFailure'/>!");
        	}else{//正常返回对象
        		addRateCodesToEachRoom(data);
        	}
        }
	});
}

function getRateCode2(arr){
	$("#sell_rateCodes").val("");
	var chosenRoomTypeCodeList=arr;
	var sellWeeks="";
	$("input[name='weekCB']:checked").each(function(){
		sellWeeks+=$(this).val()+",";
	});
	sellWeeks=sellWeeks.substring(0,sellWeeks.length-1);
	if($("#sell_fromDate").val()==''||$("#sell_toDate").val()==''||chosenRoomTypeCodeList.length==0){
		return;
	}
	//提交数据
	$.ajax({
        type: "post",
        dataType: "json",
        url: "roomStatus_ajaxGetRateCodes.do",
        data: {"chosenRoomTypeCodeStr":JSON.stringify(chosenRoomTypeCodeList),"sellWeeks":sellWeeks,"startDate":$("#sell_fromDate").val(),"endDate":$("#sell_toDate").val(),"chosenRoomTypeCodeArray":JSON.stringify(chosenRoomTypeCodeList)},
        cache:false,
        success: function(data){
        	if(data==''){//提交数据错误
        		return;
        	}else if(data=='fail'){//后台出错
        		alert("<fmt:message key='ccm.InventoryManagement.error.AllotmentSetupFailure'/>!");
        	}else{//正常返回对象
        		addRateCodesToEachRoom2(data);
        	}
        }
	});
}
</script>
<style type="text/css">
	td{ width:10px;}
</style>

