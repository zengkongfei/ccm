<%@ include file="/common/taglibs.jsp"%>

<!-- normalize.css -->
<link href="/css/normalize.css" rel="stylesheet" media="screen">
<!--jQuery ui-->
<link href="/css/jquery-ui/jquery-ui.css" rel="stylesheet" media="screen">
<!--main CSS for this site-->
<link href="/css/main.css${global_js_revision}" rel="stylesheet" media="screen">
<link href="/css/site_co.css${global_js_revision}" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/jquery-ui-timepicker-addon.css">

<link rel="stylesheet" href="/css/bootstrap-3.2.0.min.css">
<link rel="stylesheet" href="/css/bootstrap-multiselect.css">

<!-- jQuery -->
<script src="<c:url value='js/jquery-1.10.2.min.js'/>${global_js_revision}"></script>

<!--jQuery ui-->
<script src="<c:url value='/css/jquery-ui/jquery-ui.js'/>${global_js_revision}"></script>
<!--插件-->
<script src="<c:url value='/js/plugins.js'/>${global_js_revision}"></script>
<script src="<c:url value='/js/echarts.min.js'/>${global_js_revision}"></script>


<!-- 主要的自定义js -->
<script src="<c:url value='/js/main.js'/>${global_js_revision}"></script>
<script src="<c:url value='/js/i18n.js'/>${global_js_revision}"></script>
<script type="text/javascript">
var local = "${locale}";
initI18nJs(local);
</script>

<script type="text/javascript" src="<c:url value='/js/effective-validate-min.js'/>${global_js_revision}"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.min.js'/>${global_js_revision}"></script>
<script type="text/javascript" src="<c:url value='/js/messages_cn.js'/>${global_js_revision}"></script>

<script type="text/javascript" src="<c:url value='/js/json2.js'/>${global_js_revision}"></script>

<script type="text/javascript" src="<c:url value='/js/plugin_div_modal.js'/>${global_js_revision}"></script>
<script type="text/javascript" src="js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="/js/datepicker.js"></script>
<script type="text/javascript" src="js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="/js/jquery-ui-timepicker-zh-CN.js"></script>

<script type="text/javascript" src="js/bootstrap-3.2.0.min.js"></script>
<script type="text/javascript" src="js/prettify.js"></script>
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>

<!-- 插件 bootsrap -->
<!-- 弹出框 -->
<script type="text/javascript" src="js/bootstrap/bootstrap-tooltip.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap-popover.js"></script>


<script src="<c:url value='/js/common.js'/>${global_js_revision}"></script>
<script src="<c:url value='/js/dateUtils.js'/>${global_js_revision}"></script>

<script src="<c:url value='/js/global.js'/>${global_js_revision}"></script>
<!--
<script src="<c:url value='/js/easyscroll.js'/>"></script>
-->