<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!doctype html>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!-- Consider adding an manifest.appcache: h5bp.com/d/Offline -->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><fmt:message key="title.ChinaonlineChannelManagement" />
</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width,initial-scale=1">
<script src="<c:url value='/js/chartUtils.js'/>${global_js_revision}"></script>
<script src="<c:url value='/js/main.js'/>"></script>


<script type="text/javascript" src="<c:url value='js/jHtmlArea-0.8.js'/>"></script>
<link rel="Stylesheet" type="text/css" href="<c:url value='css/jHtmlArea.css'/>" />

<script>

$(document).ready(function() {	 
		
	var passwordMassge = '${passwordMassge}';
	if(passwordMassge!=''&&passwordMassge!=undefined &&passwordMassge!=null&&passwordMassge.length!=0){
		alert(passwordMassge);
	}
	
if('/main.do'==window.location.pathname){
	
	var isAnnounce = '${sysCfg.isAnnounce}'+'';
	if('true'==isAnnounce){
		if(local=='zh_CN'){
			//折叠面板
			$(function() {
		    	$( "#accordionCn" ).accordion({
		     		 collapsible: true
		    	});
			});	
			//html代码域
			$("#announcementContentCn").htmlarea({
			    toolbar: false //去掉工具栏  
			});			
			$( "#accordionCn" ).show();
		}else if(local=='en_US'){
			//html代码域
			$("#announcementContentEn").htmlarea({
			    toolbar: false //去掉工具栏  
			});
			//折叠面板
			$(function() {
		    	$( "#accordionEn" ).accordion({
		     		 collapsible: true
		    	});
			});
			$( "#accordionEn" ).show();
		}
	}
	
	//用户角色为运营用户 （companyId=1）时，加载首页图表
	var companyId = '${companyId}';
	if(companyId==1){
		<%HttpSession s = request.getSession();%>
		var first ="<%=s.getAttribute("LoginActionForChart")%>";
		var t=0;
		if("LoginActionForChart"==first){
			t=3000;
			<%s.setAttribute("LoginActionForChart","123");%>
		}
		setTimeout(function(){
					//获取监控图数据并定时刷新
					getPieData();
					setInterval("getPieData()",1000*60*5);
					
					//获取排名前五的酒店以及房型
					//饼图
					getTopFiveHotelPie("pie","topFivePie");
					//柱状图
					getTopFiveHotel("bar","topFiveBar");
					
					//获取渠道订单数据
					getChannelOrderChartCountOfOrdersBar("CountOfOrders","countOfOrdersBar");
					getChannelOrderChartCountOfOrdersBar("TotalRoomNights","totalRoomNightsBar");
					getChannelOrderChartCountOfOrdersBar("TotalAmountOfOrders","totalAmountOfOrdersBar");

			},t);
	}
	
}
});
</script>

</head>
<body>  

<div class="banner-bottom">

		<div id="accordionCn" class="fxt w900" style="display: none">
		   <h1><fmt:message key="ccm.ccmSysCfg.SystemAnnouncement"/> </h1>
		   	<s:textarea id="announcementContentCn" name="sysCfg.announcementCn"
		   		spellcheck="false" readonly="true" disabled="true" cssClass="fxt w840 h110">
		   	</s:textarea>
		</div>
		
		<div id="accordionEn" class="fxt w900" style="display: none">
		   <h1><fmt:message key="ccm.ccmSysCfg.SystemAnnouncement"/> </h1>
		   	<s:textarea id="announcementContentEn" name="sysCfg.announcementEn"
		   		spellcheck="false" readonly="true" disabled="true" cssClass="fxt w840 h110">
		   	</s:textarea>
		</div>
			
		<div class="banner-bottom-grids">
			
				<div class="col-md-3 banner-bottom-grid-left">
					<div class="br-bm-gd-lt1">
						<div id="chart_monitor"  style="height:250%"></div>
					</div>
				</div>	
				<div class="col-md-3 banner-bottom-grid-left">
					<div class="br-bm-gd-lt br-bm-gd-lt2">
						<div id="topFivePie"  style="height:250%"></div>
					</div>
				</div>
				<div class="col-md-3 banner-bottom-grid-left">
					<div class="br-bm-gd-lt br-bm-gd-lt3">
						<div id="topFiveBar"  style="height:250%"></div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="banner-bottom-grids">
				<div class="col-md-3 banner-bottom-grid-left">
					<div class="br-bm-gd-lt br-bm-gd-lt4">
						<div id="countOfOrdersBar"  style="height:250%"></div>
					</div>
				</div>	
				<div class="col-md-3 banner-bottom-grid-left">
					<div class="br-bm-gd-lt br-bm-gd-lt5">
						<div id="totalRoomNightsBar"  style="height:250%"></div>
					</div>
				</div>
				<div class="col-md-3 banner-bottom-grid-left">
					<div class="br-bm-gd-lt br-bm-gd-lt6">
						<div id="totalAmountOfOrdersBar"  style="height:250%"></div>
					</div>
				</div>	
				<div class="clearfix"> </div>
			</div>
	</div>
</body>
</html>