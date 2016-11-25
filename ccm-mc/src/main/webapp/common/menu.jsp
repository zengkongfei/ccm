<%@page import="org.springframework.util.StringUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<a id="toTop" href="#top"></a>
<div class="header">
	<div class="w1200 c_rel">
		<div class="account">
			<a href="javascript:;">
				<s:if test="#attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo!=null">
					<span>
						<security:authentication property="principal.hotelvo.chainCode"></security:authentication>-
						<security:authentication property="principal.hotelvo.hotelCode"></security:authentication> 
					</span>
					<c:choose>
						<c:when test="${fn:length(SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelName) > 12}">
							${fn:substring(SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelName, 0, 12)}. . .
						</c:when>
						<c:otherwise>
							${fn:substring(SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelName, 0, 15)}
						</c:otherwise>
					</c:choose>
				</s:if>
				<s:else><fmt:message key="ccm.AccountManagement"/></s:else>
				<span></span>
			</a>
		</div>
		<ul class="hd_link frow">
			<li class="logo"><a id="logoA" href=""><img title="回到首页" src="/img/ccm_logo2.png"> </a>
			</li>
			<!--  <li class="common"><a href="#">常用功能</a></li>-->
			<%
				String menuId = request.getParameter("menuId");
				if (StringUtils.hasText(menuId)) {
					session.setAttribute("menuId", menuId);
				}
			%>
			<s:set var="topMenu" value="#session['TopMenu'+#attr.SPRING_SECURITY_CONTEXT.authentication.principal.userId]"></s:set>
			<s:iterator value="#topMenu" id='menuTop'>
				<li class="liter"><a href="<s:property value="#menuTop.url"/>?menuId=<s:property value="#menuTop.menuId"/>" <s:if test="#menuTop.menuId==#session.menuId">class="selected"</s:if>><s:property value='#menuTop.displayName' /> </a>
				</li>
			</s:iterator>
		</ul>
		<s:if test="#attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelVOs!=null && #attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelVOs.size()>0">
			<div class="ft_layer accoutDetail IS">
				<s:if test="#attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo!=null">
					<p class="hotel">
					<s:if test="#attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelName!=null">
						<security:authentication property="principal.hotelvo.hotelName"></security:authentication>
					</s:if>
					<s:if test="#attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelName==null">
					</s:if>
					&nbsp;
					</p>
				</s:if>
				<p class="post">${SPRING_SECURITY_CONTEXT.authentication.principal.employee.name}(${attr.SPRING_SECURITY_CONTEXT.authentication.principal.employee.title})</p>
				<!-- <p class="search">
					<input id="searchHotel" class="fxt w180" type="text" value="" title="">
				</p>-->
				<div style="padding-left:34px; padding-bottom:12px; margin-top:-10px;width:194px;" >
					<s:textfield key="principal.hotelCode" cssClass="fxt w180" maxlength="30"/>
				</div>
				<div class="instantHotelList" style="display: block;">
					<div class="ins_hotel" style="width: 194px;height:210px;overflow-y:auto;">
						<c:forEach var="chainVo" items="${SPRING_SECURITY_CONTEXT.authentication.principal.chainVOs}">
							<span class="group narrow">${chainVo.chainCode}</span> 
							<span class="sub">
							<c:forEach var="hotelVo" items="${chainVo.hotelVos}">
								<a href="/menu_menuList.do?hotelId=${hotelVo.hotelId}">${hotelVo.hotelCode}</a>
							</c:forEach>
							</span>
						</c:forEach>
					</div>
				</div>
				<div class="ft_ctr1">
					<span class="float-left TB5 mgL6"><a class="ft_link" href="/user_manager.do"><fmt:message key="ccm.AccountSettings"/></a> </span>
					<button type="button" class="btn_3 blue" onclick="javascript:logout();"><fmt:message key="ccm.SignOut"/></button>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="ft_layer accoutDetail">
				<p class="hotel"><security:authentication property="principal.username"></security:authentication></p>
				<p class="post">${SPRING_SECURITY_CONTEXT.authentication.principal.employee.name}(${attr.SPRING_SECURITY_CONTEXT.authentication.principal.employee.title})</p>
				<div class="ft_ctr1">
					<span class="float-left TB5 mgL6"><a class="ft_link" href="/user_manager.do"><fmt:message key="ccm.AccountSettings"/></a> </span>
					<button type="button" class="btn_3 blue" onclick="javascript:logout();"><fmt:message key="ccm.SignOut"/></button>
				</div>
			</div>
		</s:else>
		<div class="ft_layer commonFuction">
			<ul class="cmlist frow">
				<li><a href="#"><img src="img/temp_common_icon.png"> <span>快捷方式</span> </a>
				</li>
				<li><a href="#"><img src="img/temp_common_icon.png"> <span>快捷方式</span> </a>
				</li>
				<li><a href="#"><img src="img/temp_common_icon.png"> <span>快捷方式</span> </a>
				</li>
				<li><a href="#"><img src="img/temp_common_icon.png"> <span>快捷方式</span> </a>
				</li>
				<li><a href="#"><img src="img/temp_common_icon.png"> <span>快捷方式</span> </a>
				</li>
				<li><a href="#"><img src="img/temp_common_icon.png"> <span>快捷方式</span> </a>
				</li>
				<li><a href="#"><img src="img/temp_common_icon.png"> <span>快捷方式</span> </a>
				</li>
				<li><a href="#"><img src="img/temp_common_icon.png"> <span>快捷方式</span> </a>
				</li>
				<li><a href="#"><img src="img/temp_common_icon.png"> <span>快捷方式</span> </a>
				</li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">

//var sessionHotelId = '${attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelId}';
$('#logoA').attr('href','http://'+window.location.host+'/main.do'); 

$(document).ready(function(){
	var local = "${locale}";
	//var menu_css = $("ul").find(".hd_link").find("li").find("a");
	var menu_css = $("ul.hd_link li a");
	if(local=='en_US'){
		$(menu_css).css("padding","0 5px");
		$(menu_css).css("font-size","8px");
		$(".header").find(".logo").find("img").attr("src","/img/ccm_logo2-en.png");
	}else if(local=='zh_CN'){
		$(menu_css).css("padding","0 10px");
		$(menu_css).css("font-size","10px");
		$(".header").find(".logo").find("img").attr("src","/img/ccm_logo2.png");
	}

	
})

	function logout() {
		window.location.href = "logout.jsp";
	}

	//取消选择酒店黑块时,隐藏的效果
	$(".ins_hotel").click(function(e){
		//取消文本框获取焦点时出现的事件冒泡
		$(".header").click(function(event){
			event.stopPropagation();
		});
	});
	
	//光标进入酒店代码的输入文本框时,不进行隐藏
	$("input[name='principal.hotelCode']").focus(function(e){
		//取消文本框获取焦点时出现的事件冒泡
		$(".header").click(function(event){
			event.stopPropagation();
		});
	});
	
	//筛选需要显示的集团酒店数(对应的如果集团下有酒店则显示集团和酒店,否则就隐藏)
	$("input[name='principal.hotelCode']").keyup(function(e){
		var code = $(this).val();
		if(strIsNull(code)){
			//$(".IS .instantHotelList .ins_hotel a").show();
			
			$('.group').removeClass('expand').addClass('narrow');
			$('.group').next('.sub').children().hide();
			$('.group').show();
			return;
		}

		//隐藏所有的group元素
		$('.group').hide();
		
		//对所有子节点酒店项进行筛选
		$(".IS .instantHotelList .ins_hotel a").each(function(index,element){
			var linkUp = $(this).text().toUpperCase();
			var codeUp = code.toUpperCase();
			
			if(linkUp.indexOf(codeUp)>=0){
				//如果没有显示,则先显示出来
				if(!$(this).parent().prev('.group').is(':visible')){
					$(this).parent().prev('.group').removeClass('narrow').addClass('expand');
					$(this).parent().prev('.group').show();
				}
				
				$(this).show();
			}else{
				$(this).hide();
			}
		});
	});
	
	
	$('.sub a').hide();
	$('.group').click(function(){
		if(! $(this).next('.sub').children('a').is(':visible')){
			$(this).removeClass('narrow').addClass('expand');
			$(this).next('.sub').children().show();
		}else{
			$(this).removeClass('expand').addClass('narrow');
			$(this).next('.sub').children().hide();
		}
	});

	
	
</script>