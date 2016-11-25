<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="CCMmainConter w1200">
	<div class="title_wp"><fmt:message key="ccm.RestrictionsManagement"/>
		<div class="bt">
			<a href="#BatchSetSwitch" onclick="javascript:toSetOnOff();" class="btn_2 blue ccm-popup-click"><fmt:message key="ccm.RestrictionsManagement.BatchOpenClose"/></a>
		</div>
	</div>
	<!--批量设置开/关产品-->
	<div id="BatchSetSwitch" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
		<%@ include file="productionSetForm.jsp"%>
	</div>
	<div class="c_whitebg">
		<!--搜索项-->
		<div class="nm_box nopad">
			<div class="pdA18">
				<div class="pd_mg_bt">
					<button type="button" class="btn_2 black list_search"><fmt:message key="ccm.RestrictionsManagement.RestrictionsActivity"/></button>
				</div>
				<div class="mgB6">
					<div class="fm_bwp inline w300">
						<div class="title yahei"><fmt:message key="ccm.InventoryManagement.Channels"/></div>
					</div>
					<div class="fm_bwp inline w300">
						<div class="title yahei"><fmt:message key="ccm.RestrictionsManagement.RateCodeDescription"/></div>
					</div>
					<div class="fm_bwp inline w300">
						<div class="title yahei"><fmt:message key="ccm.InventoryManagement.RoomTypes"/></div>
					</div>
				</div>
				<div class="fm_bwp inline w300">
					<div class="sel_ele">
						<span id="list_channel_select_all" class="select_all"><fmt:message key="common.select.selectAll"/></span>
						<span id="list_channel_select_inverse" class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
					</div>
					<div class="fm_box list_channelCode">
						<c:forEach items="${channelList }" var="cl" varStatus="idx">
		                    <label class="checkbox">
		                      <input type="checkbox" id="${cl.channelId }" code="${cl.channelCode }" value="${cl.channelCode }|||${cl.channelCode }" >
		                      <span class="">${cl.channelCode } </span> 
		                    </label>
		                </c:forEach>
					</div>
				</div>
				<div class="fm_bwp inline w300">
					<div class="sel_ele">
						<span id="list_ratePlan_select_all" class="select_all"><fmt:message key="common.select.selectAll"/></span>
						<span id="list_ratePlan_select_inverse" class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
					</div>
					<div class="fm_box list_ratePlanCode">
					</div>
				</div>
				<div class="fm_bwp inline w300">
					<div class="sel_ele">
						<span id="list_roomType_select_all" class="select_all"><fmt:message key="common.select.selectAll"/></span>
						<span id="list_roomType_select_inverse" class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
					</div>
					<div class="fm_box list_roomTypeCode">
					</div>
				</div>
			</div>
			<div class="nm_ctr1">
				<a href="javascript:;" class="month_link month_up"><fmt:message key="common.month.monthUp"/></a>
				<a href="javascript:;" class="month_link month_current"><fmt:message key="common.month.monthCurrent"/></a>
				<a href="javascript:;" class="month_link month_down"><fmt:message key="common.month.monthDown"/></a> 
				<label class="checkbox inline mgL24">
				<input type="checkbox" id="MonthCustomSwitch" value="option1"><fmt:message key="common.month.UserDefined"/></label>
				<input type="hidden" id="lastQueryDate">
				<span id="MonthCustom" style="display: none;"> 
					<select class="fxt c_year">
						<option>2013</option>
						<option>2014</option>
						<option>2015</option>
						<option>2016</option>
						<option>2017</option>
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
					<span class="TB1 mgL6"><fmt:message key="common.month.month"/></span>
				</span>
			</div>
		</div>
	</div>
	<!--提示层-->
	<%@ include file="productionStatus.jsp"%>
</div>
<script>
$(document).ready(function(){
	channelSelect();//渠道全选/反选
	ratePlanSelect();//房价全选/反选
	roomTypeSelect();//房型全选/反选
	//动态获得所有房价
	$('.list_channelCode').click(function(){
		getAllRatePlan();
	});
	//动态获得所有房型
	$('.list_ratePlanCode').click(function(){
		getAllRoomType();
	});
	//列表查询
	$('.list_search').click(function(){
		var result = checkData();//验证提交数据
		if(result) getCalendars(getDataObject("query"));//AJAX提交表单
	});
	//上月
	$('.month_up').click(function(){
		var result = checkData();//验证提交数据
		if(result) getCalendars(getDataObject("up"));//AJAX提交表单
	});
	//本月
	$('.month_current').click(function(){
		var result = checkData();//验证提交数据
		if(result) getCalendars(getDataObject("current"));//AJAX提交表单
	});
	//下月
	$('.month_down').click(function(){
		var result = checkData();//验证提交数据
		if(result) getCalendars(getDataObject("next"));//AJAX提交表单
	});
	$("#MonthCustomSwitch").click(function(){
		var count=$(".c_year option").length;
	    for(var i=0;i<count;i++){
	  	  	if(isNowYear($(".c_year").get(0).options[i].text)){
	             $(".c_year").get(0).options[i].selected = true;
	             break;
	      	}
	    }
	});
});

//获取并设置日期
function getQueryDate(queryType){
	var queryDate = "";//返回时间
	var currDate = null;//当前时间
	var month = "";//当前月份
	var y = "yyyy";//返回年
    var m = "MM";//返回月
	if(queryType=="query"){//直接查询
		//判断自定义是否选中
		if($("#MonthCustomSwitch").is(':checked')){
			y =$(".c_year").find("option:selected").text();
			m =$(".c_month").find("option:selected").text();
		}else{
			//取当前日期
			currDate = new Date();
			y = currDate.getFullYear();
		    m = currDate.getMonth()+1;
		    if(m<10){
		    	m = "0" + m;
		    }
		}
		queryDate = y+'<fmt:message key="time.year"/>'+m+'<fmt:message key="common.month.month"/>'; 
	}else{
		//自定义日期隐藏
		$("#MonthCustomSwitch").attr('checked',false);
		$("#MonthCustom").hide();
		if(queryType=="current"){//查询当月
			//取当前日期
			currDate = new Date();
			y = currDate.getFullYear();
		    m = currDate.getMonth()+1;
		}else if(queryType=="up"){//查询上月
			//取当前查询月份
			month = $("#lastQueryDate").val();
			if(month=='yyyy<fmt:message key="time.year"/>MM<fmt:message key="common.month.month"/>' || typeof(month) == "undefined"){
				month = '';
			}
			if(month==''){
				currDate = new Date();
			}else{
				y = month.substr(0,month.indexOf('<fmt:message key="time.year"/>'));
				m = month.substr(month.indexOf('<fmt:message key="time.year"/>')+1,2);
				currDate = new Date(y,(m-1),1);//月份介于 0 到 11 之间
			}
			currDate.setMonth(currDate.getMonth() -1);
			y = currDate.getFullYear();
		    m = currDate.getMonth()+1;
		}else if(queryType=="next"){//查询下月
			//取当前查询月份
			month = $("#lastQueryDate").val();
			if(month=='yyyy<fmt:message key="time.year"/>MM<fmt:message key="common.month.month"/>' || typeof(month) == "undefined"){
				month = '';
			}
			if(month==''){
				currDate = new Date();
			}else{
				y = month.substr(0,month.indexOf("<fmt:message key='time.year'/>"));
				m = month.substr(month.indexOf("<fmt:message key='time.year'/>")+1,2);
				currDate = new Date(y,(m-1),1);//月份介于 0 到 11 之间
			}
			currDate.setMonth(currDate.getMonth() +1);
			y = currDate.getFullYear();
		    m = currDate.getMonth()+1;
		}
		if(m<10){
	    	m = "0" + m;
	    }
	    queryDate = y+"<fmt:message key='time.year'/>"+m+"<fmt:message key='common.month.month'/>"; 
	}
	//设置table日期
	$(".CCMmainConter .month").text(queryDate);
	$("#lastQueryDate").val(queryDate);
	return queryDate;
}
//获取提交数据
function getDataObject(queryType){
	dataObject = {};
	var channelCodes = "";
    var ratePlanCodes = "";
    var roomTypeCodes = "";
  	//获取checkbox
	$('.list_channelCode input:checked').each(function(){ 
		channelCodes += $(this).attr("code")+',';
	});
	$('.list_ratePlanCode input:checked').each(function(){ 
		ratePlanCodes += $(this).attr("code")+',';
	});
	$('.list_roomTypeCode input:checked').each(function(){ 
		roomTypeCodes += $(this).attr("code")+',';
	});
	//组装数据
	dataObject.channelCodes = channelCodes;
	dataObject.ratePlanCodes = ratePlanCodes;
	dataObject.roomTypeCodes = roomTypeCodes;
	dataObject.queryDate = getQueryDate(queryType);
	return dataObject;
}

var channelNames='';
var ratePlanNames='';
var roomTypeNames='';

//验证提交数据并显示table
function checkData(){
	//获取checkbox
	$('.list_channelCode input:checked').each(function(){ 
		channelNames += $(this).val()+',';
	});
	$('.list_ratePlanCode input:checked').each(function(){ 
		ratePlanNames += $(this).val()+',';
	});
	$('.list_roomTypeCode input:checked').each(function(){ 
		roomTypeNames += $(this).val()+',';
	});
	channelNames = channelNames.substr(0,channelNames.lastIndexOf(','));
	ratePlanNames = ratePlanNames.substr(0,ratePlanNames.lastIndexOf(','));
	roomTypeNames = roomTypeNames.substr(0,roomTypeNames.lastIndexOf(','));
	//验证checkbox必须选择
	if(channelNames == ""){
		alert('<fmt:message key="ccm.RestrictionsManagement.error.channelNull"/>！');
		return false;
	}
	if(ratePlanNames == ""){
		alert('<fmt:message key="ccm.RestrictionsManagement.error.rateCodeNull"/>！');
		return false;
	}
	if(roomTypeNames == ""){
		alert('<fmt:message key="ccm.RestrictionsManagement.error.roomTypeNull"/>！');
		return false;
	}
	return true;
}

//AJAX获取房态日历
function getCalendars(datas){
	var date = "";
	var d = "";//天
	
	$('.pd_mg_list').remove();//删除所有table
	$(".rel").removeClass("rel");//删除底部样式
	
	$.ajax({
        type: "post",
        dataType: "json",
        url: "product_ajaxGetCalendars.do",
        data: datas,
        async:false,
        cache:false,
        success: function(data){
        	var tables = '';
        	for(var i=0;i<data.length;i++){
        		for(var j=0;j<data[i].length;j++){
        			tables += createTable(data,data[i][j].length,i,j);
        		}
        	}
        	
        	$('.c_whitebg').after(tables);//追加table
        	$('.room_off').hide()
			//$('.room_open').slideDown();
        	
        	tableEvent();//绑定table操作事件
        }
	});
	
	
}

//创建table
function createTable(data,roomTypeLength,idx1,idx2){
	var th_31 = '';
	//显示th
	for(var i=1;i<=31;i++){
		if(i<10){
			th_31 += '<th><span>'+"0"+i+'</span></th>';
		}else{
			th_31 += '<th><span>'+i+'</span></th>';
		}
	}

	//显示table
	var tbody_roomType = '';
	tbody_roomType = '<tbody>'
		+ '<tr><td rowspan="'+roomTypeLength+'"><span class="span_ratePlanCode">'
		+ data[idx1][idx2][0][0].ratePlanCode+'</span><br>'
		+ '<span class="span_ratePlanName">'+getRatePlanName(data[idx1][idx2][0][0].ratePlanCode)+'</span></td>'
		+ '<td title="'+getroomTypeName(data[idx1][idx2][0][0].roomTypeCode)+'"><span>'+data[idx1][idx2][0][0].roomTypeCode+'</span></td>'
		+ getTdRoomType(data[idx1][idx2][0],1)+'</tr>';
	
	if(roomTypeLength>1){	
		for(var i=1;i<roomTypeLength;i++){
			tbody_roomType += '<tr ><td title="'+getroomTypeName(data[idx1][idx2][i][0].roomTypeCode)+'"><span>'+data[idx1][idx2][i][0].roomTypeCode+'</span></td>'+getTdRoomType(data[idx1][idx2][i],0)+'</tr>';
		}
	}
	tbody_roomType += '</tbody>';
	
	//显示pd_mg_list
	var table = '<div class="pd_mg_list">'
  		+ '<div class="c_m frow">'
  		+ '<div class="channelimg">'+data[idx1][idx2][0][0].channelCode+'</div>'
  		+ '<div class="month">'+$("#lastQueryDate").val()+'</div>'
  		+ '</div>'
  		+ '<div class="bt_wp">'
  		+ '<table class="ccm_table1 ctr_room_status">'
  		+ '<thead>'
		+ '<tr><th class="w100"><span><fmt:message key="ccm.RestrictionsManagement.RateCode"/></span></th><th class="w100"><span><fmt:message key="ccm.InventoryManagement.RoomTypes"/></span></th>'+th_31+'</tr>'
		+ '</thead>'
		+ tbody_roomType
		+ '</table>'
		+ '</div>'
		+ '</div>';
  return table;
}

//获得TD字符串
function getTdRoomType(rows,align){
	var td_roomType = '';
	for(var i=0;i<31;i++){
		//是否有效
		var isUse = false;
		if(rows.length>i){
			isUse = true;
		}
		if(isUse){
			//设置类样式
			var classStr = "";
			var tdHtml = "";
			if(rows[i].onOff != '1'){
				classStr = "off_room";
				tdHtml = "X";
			}
			
			var date = rows[i].date;
	   		var d = parseInt(date.substr(8));//天
	   		if(typeof(date)!="undefined" && date!="" && isMorethanNow(date)!='false' ){
	   			td_roomType += '<td class="td_ia '+classStr+'" '
	   				+'channelCode="'+rows[i].channelCode+'" '
	   				+'chainCode="'+rows[i].chainCode+'" '
	   				+'hotelCode="'+rows[i].hotelCode+'" '
	   				+'roomTypeCode="'+rows[i].roomTypeCode+'" '
	   				+'roomTypeName="'+getroomTypeName(rows[i].roomTypeCode)+'" '
	   				+'ratePlanCode="'+rows[i].ratePlanCode+'" '
	   				+'ratePlanName="'+getRatePlanName(rows[i].ratePlanCode)+'" '
	   				+'onOff="'+rows[i].onOff+'" '
	   				+'date="'+rows[i].date+'">'+tdHtml+'</td>';
	   		}else{
	   			td_roomType += '<td class="td_ia '+classStr+'" style="background: #f2f2f2;cursor:text;"'
	   				+'date="'+rows[i].date+'" '
	   				+'onOff="'+rows[i].onOff+'" '
	   				+'outTime="1">'+tdHtml+'</td>';
	   		}
		}else{
			td_roomType += '<td class="td_ia" style="background: #f2f2f2;cursor:text;"'
   				+'date="" '
   				+'onOff="" '
   				+'outTime="1"></td>';
		}
	}
	
	return td_roomType;
}

//table操作事件
function tableEvent(){
	//悬浮窗口显示
	$('.ctr_room_status td.td_ia').mouseenter(function() {
		//取值
		var channelCode = $(this).attr("channelCode");
		var ratePlanCode = $(this).attr("ratePlanCode");
		var ratePlanName = $(this).attr("ratePlanName");
		var roomTypeCode = $(this).attr("roomTypeCode");
		var roomTypeName = $(this).attr("roomTypeName");
		var onOff = $(this).attr("onOff");
		var date = $(this).attr("date");
		var outTime = $(this).attr("outTime"); //是否过时 ,1表示过时
		
		//如果开关为空不显示
		if(onOff!='' && outTime!='1'){
			channelCode = '<fmt:message key="ccm.InventoryManagement.Channels"/>：'+channelCode;
			roomTypeCode = '<fmt:message key="ccm.InventoryManagement.RoomTypes"/>：'+roomTypeCode+" "+roomTypeName;
			ratePlanCode = '<fmt:message key="ccm.RestrictionsManagement.Rate"/>：'+ratePlanCode+" "+ratePlanName;
			var y = date.substr(0,4);
			var m = date.substr(5,2);
			var d = date.substr(8,2);
			date = '<fmt:message key="common.date"/>：'+y+'<fmt:message key="time.year"/>'+m+'<fmt:message key="common.month.month"/>'+d+'<fmt:message key="time.day"/>';
			$p_left = $(this).position().left + 23;
			$p_top = $(this).position().top + 18;
			$compareW = $(window).width() / 2 + 355;
			if ($p_left > $compareW) {
				$('.room_status').css({
					left : $p_left - 258,
					top : $p_top
				});
				$('.room_status .off,.room_status .open').addClass('Right');
			} else {
				$('.room_status').css({
					left : $p_left,
					top : $p_top
				});
				$('.room_status .off,.room_status .open').removeClass('Right');
			}
			//设置悬浮窗口
			if(onOff=='1'){
				$('.room_open .channel').text(channelCode);
				$('.room_open .roomType').text(roomTypeCode);
				$('.room_open .ratePlan').text(ratePlanCode);
				$('.room_open .date').text(date);
				$('.room_open').stop(true, true).slideDown();
			}else if(onOff=='0'){
				$('.room_off .channel').text(channelCode);
				$('.room_off .roomType').text(roomTypeCode);
				$('.room_off .ratePlan').text(ratePlanCode);
				$('.room_off .date').text(date);
				$('.room_off').slideDown();
			}
		}
	});
	
	//悬浮窗口隐藏
	$('.ctr_room_status td.td_ia').mouseleave(function() {
		$('.room_status').hide();
	});
	
	//开/关产品设置
	$('.ctr_room_status td.td_ia').bind('dblclick', function() {
		var obj = this;
		var datas = null;
		
		// 取日期
		var date = $(".CCMmainConter .month:eq(0)").text();
		
		//取第几行
		var tr = $(this).parents("tr").prevAll().length;
		//取日
		d = $(this).prevAll().length-1;
		if(tr>0){
			d += 1;
		}
		if(!getAfterCurDate(d,date)){
			return;
		}
		
		//取数
		var channelCode = $(obj).attr("channelCode");
		var chainCode = $(obj).attr("chainCode");
		var hotelCode = $(obj).attr("hotelCode");
		var roomTypeCode = $(obj).attr("roomTypeCode");
		var ratePlanCode = $(obj).attr("ratePlanCode");
		var onOff = $(obj).attr("onOff");
		var date = $(obj).attr("date");
		//验证是否提交
		if(channelCode==''&&chainCode==''&&hotelCode==''&&roomTypeCode==''&&ratePlanCode==''){
			return;
		}else if(channelCode==''||chainCode==''||hotelCode==''||roomTypeCode==''||ratePlanCode==''){
			alert("<fmt:message key='ccm.RestrictionsManagement.error.submitError'/>！");
			return;
		}
		//设置提交数据
		if(onOff=='0'){
			onOff = '1';
		}else{
			onOff = '0';
		}
		datas = {"product.channelCode":channelCode,
				"product.chainCode":chainCode,
				"product.hotelCode":hotelCode,
				"product.roomTypeCode":roomTypeCode,
				"product.ratePlanCode":ratePlanCode,
				"product.onOff":onOff,
				"product.date":date
			};
		//提交
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: "product_ajaxOnOffSet.do",
	        data: datas,
	        async:false,
	        cache:false,
	        success: function(data){
	        	if(data=='fail'){
	        		alert("<fmt:message key='ccm.RestrictionsManagement.error.RestrictionSetupFailure'/>!");
	        	}else{
	        		if(onOff=='1'){
	        			$(obj).removeClass('off_room');
	        			$(obj).html('');
	        			$(obj).attr('onOff','1');
	        			$('.room_off').hide()
	        			$('.room_open').slideDown();
	        		}else if(onOff=='0'){
	        			$(obj).addClass('off_room');
	        			$(obj).html('X');
	        			$(obj).attr('onOff','0');
	        			$('.room_open').hide();
	        			$('.room_off').slideDown();
	        		}
	        	}
	        }
		});
	});
}

//渠道点击事件，获得相关房价
function getAllRatePlan(){
	var channelIds = '';
	$('.list_channelCode input:checked').each(function(){ 
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
    		        	+'<input type="checkbox" id="'+data[i].ratePlanId+'" code="'+data[i].ratePlanCode
    		        	+'" value="'+data[i].ratePlanCode+'|||'+rateplanName+'" ><span class="">'
    		        	+data[i].ratePlanCode+'&nbsp;'+rateplanName+'</span></label>';
        		}
        	}
       		$('.list_ratePlanCode label').remove();
       		$('.list_roomTypeCode label').remove();
       		$('.list_ratePlanCode').append(ratePlanHtml);
        }
	});
}

//渠道点击事件，获得相关房型
function getAllRoomType(){
	var channelIds = '';
	var ratePlanIds = '';
	$('.list_channelCode input:checked').each(function(){ 
		channelIds += $(this).attr("id")+',';
	});
	$('.list_ratePlanCode input:checked').each(function(){ 
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
        			var roomTypeName = data[i].roomTypeName == null ? '':data[i].roomTypeName;
        			if(!strIsNull(data[i].pmsCode)){
        				roomTypeName += "_"+data[i].pmsCode;
        			}
        			roomTypeHtml += '<label class="checkbox">'
    		        	+'<input type="checkbox" id="'+data[i].roomTypeId+'" code="'
    		        	+data[i].roomTypeCode+'" value="'+data[i].roomTypeCode+'|||'+roomTypeName
    		        	+'" ><span class="">'+data[i].roomTypeCode+'&nbsp;'+roomTypeName
    		        	+'</span></label>';
        		}
        	}
       		$('.list_roomTypeCode label').remove();
       		$('.list_roomTypeCode').append(roomTypeHtml);
        }
	});
}

//渠道全选/反选
function channelSelect(){
	$('#list_channel_select_all').click(function(){
		$find=$(this).parents('.fm_bwp').find('input[type=checkbox]');
		$find.prop('checked','true')
		$find.next().addClass('checked');
		getAllRatePlan();//动态获得所有房价
	});
	$('#list_channel_select_inverse').click(function(){
		$find=$(this).parents('.fm_bwp').find('label.checkbox');
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
		getAllRatePlan();//动态获得所有房价
	});
}

//房价全选/反选
function ratePlanSelect(){
	$('#list_ratePlan_select_all').click(function(){
		$find=$(this).parents('.fm_bwp').find('input[type=checkbox]');
		$find.prop('checked','true')
		$find.next().addClass('checked');
		getAllRoomType();//动态获得所有房价
	});
	$('#list_ratePlan_select_inverse').click(function(){
		$find=$(this).parents('.fm_bwp').find('label.checkbox');
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
		getAllRoomType();//动态获得所有房价
	});
}

//房型全选/反选
function roomTypeSelect(){
	$('#list_roomType_select_all').click(function(){
		$find=$(this).parents('.fm_bwp').find('input[type=checkbox]');
		$find.prop('checked','true')
		$find.next().addClass('checked');
	});
	$('#list_roomType_select_inverse').click(function(){
		$find=$(this).parents('.fm_bwp').find('label.checkbox');
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

//批量设置开关房页面显示
function toSetOnOff(){
	var dataStr = JSON.stringify(dataObject);
	$('#BatchSetSwitch').load('product_toSetOnOff.do?dataStr='+dataStr);
}

//根据房价Code取房价名称
function getRatePlanName(code){
	var ratePlanNames = "";
	var rpcode = "";
	var rpname = "";
	$('.list_ratePlanCode input:checked').each(function(){ 
		ratePlanNames += $(this).val()+',';
	});
	
	var ratePlanNameArray = ratePlanNames.split(",");
	var arrayLength = ratePlanNameArray.length;
	for(var i=0;i<arrayLength;i++){
		rpcode = ratePlanNameArray[i].substr(0,ratePlanNameArray[i].indexOf("|||"));
		rpname = ratePlanNameArray[i].substr(ratePlanNameArray[i].indexOf("|||")+3);
		if(rpcode==code){
			return rpname;
		}
	}
	return "";
}

//根据房型Code取房型名称
function getroomTypeName(code){
	var roomTypeNames = "";
	var rtcode = "";
	var rtname = "";
	$('.list_roomTypeCode input:checked').each(function(){ 
		roomTypeNames += $(this).val()+',';
	});
	
	var roomTypeNameArray = roomTypeNames.split(",");
	var arrayLength = roomTypeNameArray.length;
	for(var i=0;i<arrayLength;i++){
		rtcode = roomTypeNameArray[i].substr(0,roomTypeNameArray[i].indexOf("|||"));
		rtname = roomTypeNameArray[i].substr(roomTypeNameArray[i].indexOf("|||")+3);
		if(rtcode==code){
			return rtname;
		}
	}
	return "";
}
</script>