<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<link href="/css/site_co.css" rel="stylesheet" media="screen">
<%@ include file="/common/messages.jsp"%>

<div class="title_wp">
	<fmt:message key="ccm.ccmSysCfg.SysCfg" />
</div>
<div class="c_whitebg">
	<s:form id="searchForm" action="/ccmSysCfg_saveSuccess.do" method="post">
		<appfuse:ccmToken name="token"></appfuse:ccmToken>
		<!--搜索项-->
		<div class="nm_box">
			<ul class="inq_wp frow">
				<hr class="dashed" />
				<li>
					<div class="i_input">
						<span class="text"><fmt:message key="ccm.ccmSysCfg.CfgParam" />:</span>
						<s:textfield name="sysCfg.pendingMsgCount" key="sysCfg.pendingMsgCount" maxlength="8" cssClass="numOnly fxt w120" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
					</div>
				</li>
				<hr class="dashed" />
				<li>
					<div class="i_input">
						<span class="text"><fmt:message key="ccm.ccmSysCfg.MonitorTheHotelInterface" />：</span>
						<label class="checkbox inline"> <s:checkbox id="f_isInterfaceListener" name="sysCfg.isInterfaceListener" /> </label>
					</div>
				</li>
				<hr class="dashed" />
				<li>
					<div class="i_input">
						<span class="text"><fmt:message key="ccm.sysListener.MonitorTimeOfBookingOrder" />:</span>
						<s:textfield name="sysCfg.interfaceListener" key="sysCfg.interfaceListener" maxlength="8" cssClass="numOnly fxt w120" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
						s
					</div>
				</li>
				<hr class="dashed" />
				<li>
					<div class="i_input">
						<span class="text"><fmt:message key="ccm.ccmSysCfg.MonitorTheReservation" />：</span>
						<label class="checkbox inline"> <s:checkbox id="f_isMasterListener" name="sysCfg.isMasterLister" /> </label>
					</div>
				</li>
				<hr class="dashed" />
				<li>
					<div class="i_input">
						<span class="text"><fmt:message key="ccm.sysListener.MonitorTimeofHotelPMSInterface" />:</span>
						<s:textfield name="sysCfg.masterListener" key="sysCfg.masterListener" maxlength="8" cssClass="numOnly fxt w120" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
						s
					</div>
				</li>
				<hr class="dashed" />
				<li>
					<div class="i_input">
						<span class="text"><fmt:message key="ccm.ccmSysCfg.Bookingorderoperationtime" />:</span>
						<s:textfield name="sysCfg.masterDealTime" key="sysCfg.masterDealTime"  maxlength="8" cssClass="numOnly fxt w120" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
						m
					</div>
				</li>
				<hr class="dashed" />
				<li>
					<div class="i_input">
						<span class="text"><fmt:message key="ccm.ccmSysCfg.DailySMSAlertAbsoluteTime" />:</span>
						<s:textfield name="sysCfg.timeArr" key="sysCfg.timeArr" id="timeArr"  maxlength="30" cssClass="fxt w120" />
						<span class="error"><label for="createStart" generated="true" class="error"><fmt:message key="ccm.ccmSysCfg.DailySMSAlertAbsoluteT" /></label></span>
					</div>
				</li>
				
			</ul>
				
				<hr class="dashed"/>
				<li>
					<div class="i_input">
					<span class="text"><fmt:message key="ccm.ccmSysCfg.IsAnnounce"/>:</span>
						<label class="checkbox inline">
                    		<s:checkbox name="sysCfg.isAnnounce"/>
                  		</label>
					</div>
				</li>
				
				<hr class="dashed"/>
				<li>
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ccmSysCfg.SystemAnnouncement"/></span>
					</div>
					<div class="i_input">
					
						<div id="accordion">
						  <h3><fmt:message key="ccm.ccmSysCfg.ChineseAnnouncement"/></h3>
						  <div>
						   	<s:textarea name="sysCfg.announcementCn" id="announcement_cn" cssClass="announcement fxt w491 h150"></s:textarea>
						  </div>
						  <h3><fmt:message key="ccm.ccmSysCfg.EnglishAnnouncement"/></h3>
						  <div>
						   	<s:textarea name="sysCfg.announcementEn" id="announcement_en" cssClass="announcement fxt w491 h150"></s:textarea>
						  </div>
						</div>
					</div>
				</li>
			</ul>
			<hr class="solided notopMargin">
			<hr class="solided notopMargin">
			<div class="center">
				<div class="listinputCtrl">
					<button type="button" class="btn_1 green mgR12 f_save">
						<fmt:message key="ccm.hotelSwitch.SaveModify" />
					</button>
					<a class="btn_1 white" href="/ccmSysCfg_list.do"><fmt:message key="ccm.hotelSwitch.Refresh" /> </a>
				</div>
			</div>
		</div>
	</s:form>
</div>

<link href="<c:url value='/upload/css/uploadskin.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/upload/css/upload.css'/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<c:url value='/upload/core/swfupload.js'/>${global_js_revision}"></script>
<script type="text/javascript" src="<c:url value='/upload/upload.js'/>${global_js_revision}"></script>
<script type="text/javascript" src="<c:url value='js/jHtmlArea-0.8.js'/>"></script>
<link rel="Stylesheet" type="text/css" href="<c:url value='css/jHtmlArea.css'/>" />
<script type="text/javascript" src="<c:url value='js/jHtmlArea.ColorPickerMenu-0.8.js'/>"></script>
<link rel="Stylesheet" type="text/css" href="<c:url value='css/jHtmlArea.ColorPickerMenu.css'/>" />

<script type="text/javascript">
	$(document).ready(function() {

	});

	//只能输入数字
	$(".numOnly").keyup(function() {
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	}).bind("paste", function() { //CTR+V事件处理 
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用    

	$(function() {
		  $( "#accordion" ).accordion({
		     //collapsible: true
		  });
	});
	
	//System announcement 系统公告
	$(".announcement").htmlarea({  
	});
	
   //只能输入数字
   $(".numOnly").keyup(function(){          
       $(this).val($(this).val().replace(/[^0-9]/g,''));    
   }).bind("paste",function(){  //CTR+V事件处理 
       $(this).val($(this).val().replace(/[^0-9]/g,''));     
   }).css("ime-mode", "disabled"); //CSS设置输入法不可用    
   
	//保存
	$('.f_save').click(function() {

		//保存时跳出确认框
		con = confirm("<fmt:message key="ccm.hotelSwitch.Confirm"/>?");
		if (!con) {
			return;
		}
		
		
		var timeArr = $("#timeArr").val().trim();
		if (timeArr.length > 0) {
			try {
				var timeStrArr = timeArr.split(";");
				for (var i = 0; i < timeStrArr.length; i++) {
					var hours = timeStrArr[i].split(":")[0];
					var minute = timeStrArr[i].split(":")[1];
					console.log(parseInt(hours));
					console.log(parseInt(minute));
					if (parseInt(hours) >= 0
							&& parseInt(hours) <= 23
							&& parseInt(minute) >= 0
							&& parseInt(minute) <= 59) {
						continue;
					}else{
						alert("<fmt:message key='ccm.ccmSysCfg.DailySMSAlertAbsoluteTime.error.msg' />");
						return;
					}
				}
			} catch (e) {
				alert(e.name + ": " + e.message);
				return;
			}
		}

		//验证表单
		if (!$("#searchForm").valid()) {
			return;
		} else {

			$("#searchForm").submit();
			//禁止重复提交
			$('.f_save').addClass('no_ald');
			$('.f_save').attr("disabled", "disabled");
		}

	});
</script>