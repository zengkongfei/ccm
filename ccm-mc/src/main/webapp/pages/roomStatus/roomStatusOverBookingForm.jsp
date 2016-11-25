<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--设置OverBooking-->
<div id="SetBooking"
	class="ccm-popup width750 zoom-anim-dialog mfp-hide">
	<form action="roomStatus_ajaxBatchSetOverBooking.do" id="obForm" method="post">
	<input type="hidden" id="ob_roonTypeCodes" name="setvo.roonTypeCodes"/>
	<input type="hidden" id="ob_weeks" name="setvo.weeks"/>
	<div class="pp_main">
		<div class="t_title"><fmt:message key="ccm.InventoryManagement.NumberOfOverbooking"/> </div>
		<div class="pdA24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.InventoryManagement.RoomTypes"/>：</span>
					</div>
					<div class="i_input">
						<div class="fm_bwp inline w240">
							<div class="sel_ele">
								<span class="select_all"><fmt:message key="common.select.selectAll"/></span><span class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
							</div>
							<div class="fm_box ob_roomType">
							<c:forEach items="${roomTypeList }" var="rl" varStatus="idx">
								<label class="checkbox">
									<input type="checkbox" id="cc_${rl.roomTypeCode }" value="${rl.roomTypeCode }">
									<span class="checked">${rl.roomTypeCode } ${rl.roomTypeName }
										<c:if test="${!empty rl.pmsCode}">_${rl.pmsCode}</c:if>
									</span>
								</label>
							</c:forEach>
							</div>
						</div>
					</div></li>
				<li class="c_rel">
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="common.time.BeginDate"/>：</span>
					</div>
					<div class="i_input">
						<input type="text" id="ob_fromDate" class="fxt w120" name="setvo.fromDate"></input>
					</div>
					<div class="date_abs">
						<div class="dateweek ob_weeks">
							<div class="d_wp">
								<span><fmt:message key="common.week.sunday"/></span> 
								<input type="checkbox" value="0" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.monday"/></span> 
								<input type="checkbox" value="1" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.tuesday"/></span> 
								<input type="checkbox" value="2" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.wednesday"/></span> 
								<input type="checkbox" value="3" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.thursday"/></span> 
								<input type="checkbox" value="4" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.friday"/></span> 
								<input type="checkbox" value="5" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.saturday"/></span> 
								<input type="checkbox" value="6" checked>
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
						<input type="text" id="ob_toDate" class="fxt w120" name="setvo.toDate" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.InventoryManagement.TotalOB"/>：</span>
					</div>
					<div class="i_input">
						<input class="fxt w30" type="text" id="overBooking" name="setvo.overBooking">
					</div></li>
			</ul>
		</div>
		<div class="b_crl">
			<label class="checkbox inline" id="label_obPush">
				<input type="checkbox" id="overBookingPush" checked="checked"><fmt:message key="ccm.InventoryManagement.Distribute"/>
			</label>
			<button type="button" class="btn_2 green mgR6 obBatchSaveBtn"><fmt:message key="common.button.save"/></button>
			<button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
		</div>
	</div>
	</form>
</div>

<script>
$(document).ready(function() {
//销售数据保存
$('#SetBooking .obBatchSaveBtn').click(function(){
	var roomTypeCode='';
	var weeks='';
	var fromDate = $('#ob_fromDate').val();
	var toDate = $('#ob_toDate').val();

	$('.ob_roomType input:checked').each(function(){ 
		roomTypeCode += $(this).val()+',';
	});
	$('#SetBooking .ob_weeks input:checked').each(function(){ 
		weeks += $(this).val()+',';
	});
	roomTypeCode = roomTypeCode.substr(0,roomTypeCode.lastIndexOf(','));
	weeks = weeks.substr(0,weeks.lastIndexOf(','));
	$('#ob_roonTypeCodes').val(roomTypeCode);
	$('#ob_weeks').val(weeks);
	var overBooking = $("#overBooking").val();
	//判定输入项
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
	if(overBooking==''){
		alert('<fmt:message key="ccm.InventoryManagement.error.overbookingErrorMessage"/>！');
		return;
	}
	if(!fucCheck(overBooking)){   
        alert('<fmt:message key="ccm.InventoryManagement.errorroomsNumberErrorMessage"/>！');   
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
	var data = $('#obForm').serialize();
	var isPush = $("#overBookingPush").is(':checked');
	
	$.ajax({
        type: "post",
        url: "roomStatus_ajaxBatchSetOverBooking.do",
        data: data+"&push="+isPush,
        cache:false,
        beforeSend : function() {
			$('#SetBooking .obBatchSaveBtn').addClass('no_ald');
			$('#SetBooking .obBatchSaveBtn').attr('disabled', 'disabled');
		},
        success: function(data){
        	//清空数据
    		$("#obForm")[0].reset();
    		queryCalendars();
    		$.magnificPopup.close();
        }
	});
	
});
//关闭销售数据页面
$('.popup-close').bind('click',function(){
	var dataStr = JSON.parse($("#roomStatus_dataStr").text());
	if(!dataStr.roomTypeCodes==""){
		getCalendars(dataStr);
	}
});
});
</script>