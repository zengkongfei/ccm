<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="pp_main" style="height: 395px">
    <div>	
    	<div align="center">
    		<br/>${title}
    	</div>
    	<div align="left">
    		<br/>
			${content1}${hotelName}${content5}
			<br/><br/>
		</div>
		<div align="left">
    		<br/>${content2}<br/>
    	</div>
    	<div align="left">
			<br/>${content6}<br/>
		</div>
		<div align="left">
			<br/>${content7}${content8}<br/>
		</div>
		<div align="left">
			<br/>${content9}${content10}<br/>
		</div>
		<div align="left">
			<br/>${content11}${content12}<br/>
		</div>
		<div align="left">
			<br/>${content13}${content14}<br/>
		</div>
		<div align="left">
			<br/>${content15}${content16}<br/>
		</div>
		<div align="left">
			<br/>${content17}${content18}<br/>
		</div>
    	<div align="left">
			<br/>${content3}<br/>
		</div>
		<div align="left">
			<br/><img width="748px;" height="130px;" alt="" src="${content4}">
		</div>
		<div class="b_crl" style="text-align: center;">
			<button type="button" class="btn_2 white popup-close">关闭</button>
		</div>
	</div>
	
</div>
<script src="<c:url value='/js/main.js'/>"></script>