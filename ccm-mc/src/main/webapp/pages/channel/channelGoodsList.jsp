<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	//链接到众荟
	function linkToJointwisdom(){
		var hotelCode=$('#hotelCode').val();
		var pmsAccount=$('#pmsAccount').val();
		var JointwisdomURL=$('#JointwisdomURL').val();
		if(hotelCode==null||hotelCode=='null'){
			hotelCode='';
		}
		if(pmsAccount==null||pmsAccount=='null'){
			pmsAccount='';
		}
		//测试 http://124.127.242.67/AutoMappingProject?hotelGroupCode=CCM
		//window.open('http://124.127.242.67/AutoMappingProject?hotelGroupCode=CCM&hotelCode='+hotelCode+'&hotelAccount='+pmsAccount);
		//生产 http://54.222.212.179/AutoMapingWeb?hotelGroupCode=CCM
		//window.open('http://54.222.212.179/AutoMapingWeb?hotelGroupCode=CCM&hotelCode='+hotelCode+'&hotelAccount='+pmsAccount);
		window.open(JointwisdomURL+'&hotelCode='+hotelCode+'&hotelAccount='+pmsAccount);
	}
	//手动推送 
	function handSend(){
		$('#handSend').load('/adsBeq_handSend.do');
	}

	function addNew() {
		$('#ChannelPublishing').load('/channelGoods_edit.do');
	}
	//编辑
	function edit(channelGoodsId, status) {
		if(status != 1){
			alert('<fmt:message key="ccm.error.009"/>');
			return false;
		}
		$('#ChannelPublishing').load('/channelGoods_edit.do?channelGoodsId=' + channelGoodsId);
	}
	
	var flag = '';
	function search2(){
		flag = 'search';
	}
	
	function doExport(){
		flag = 'export';
	}
	
	$(document).ready(function() {
		
		//判断是否显示众荟链接
		var showJointwisdom=$('#showJointwisdom').val();
		if(true==showJointwisdom || 'true'==showJointwisdom){
			$('#jointwisdom').show();
		}else{
			$('#jointwisdom').hide();
		}

		//渠道代码初始化
		$('#chList_channelCode').multiselect({
			dropRight: true,
            enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:200,
			buttonWidth: '202px',
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
        });
		
		/*是否删除本条 setHotelIdForHref */
		$('.del_ifself').bind('click',function(){
			var channelGoodsId = $(this).attr('channelGoodsId');
			var status = $(this).attr('status');
			if(status != 1){
				alert('<fmt:message key="ccm.error.009"/>');
				return false;
			}
			var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
			if (r==true){
				window.location.href=setHotelIdForHref("/channelGoods_delete.do?channelGoodsId="+channelGoodsId);
			}
		});
		
		$('#publishBtn').click(function() {
			$(this).addClass('no_ald');
			$('#publishInfo').hide();
			$.ajax({
				  type: "POST",
				  url: '/channelGoods_publish.do',
				  beforeSend : function() {
				  },
				  success: function(data){
					  data = eval("(" + data + ")");
					  if(true == data.success){
						  alert('<fmt:message key="ccm.Channel.message.NoPublish2Enable"/>');
						  location.reload();
					  }else{
						  alert(data.message);
						  $(this).removeClass('no_ald');
						  $('#publishInfo').show();
					  }
				  }
			});
		});
		if ($('#publishInfo').length > 0) {
			$('#publishBtn').removeClass('no_ald');
			$('#publishBtn').removeAttr('disabled');
		} else {
			$('#publishBtn').addClass('no_ald');
			$('#publishBtn').attr('disabled', 'disabled');
		}
		
		$('#Two_click').bind('click',function(){
			$('#Two_show').slideDown();
		});
		
		$('#link_show .closethis').bind('click',function(){
			$('#link_show').hide();
		});
		$('#Two_show .closethis').bind('click',function(){
			$('#Two_show').slideUp();
		});
		
		//房型选择
		$('#Two_show .confirmthis').click(function(){
			var channelIds='';
			var channelCodes = '';
		
			$('#Two_show input:checked').each(function(){ 
				channelIds += $(this).val()+',';
			});
			$('#Two_show input:checked').next('span').each(function(){ 
				channelCodes += $(this).find("span.span_channelCode").text()+",";
			});
			
			channelIds = channelIds.substr(0,channelIds.lastIndexOf(','));
			channelCodes = channelCodes.substr(0,channelCodes.lastIndexOf(','));
			
			$('#Two_click .typeName').text(channelCodes);
			$('#chIds').val(channelIds);
			$('#chCodes').val(channelCodes);
			$('#Two_show').hide();
		});
		
		//全选
		$("#Two_show .selectAll").bind('click',function(){
			var checklist = document.getElementsByName("channelChk");
			for(var i=0;i<checklist.length;i++){
			     checklist[i].checked = true;
			} 
		});
		
		//反选
		$("#Two_show .reverseSel").bind('click',function(){
			var checklist = document.getElementsByName("channelChk");
			for(var i=0;i<checklist.length;i++){
			     checklist[i].checked = !checklist[i].checked;
			} 
		});
		
		$('.list_search').click(function(){
			document.cgListForm.action = '/channelGoods_search.do';
			document.cgListForm.submit();
		});
		
		$('.list_export').click(function(){
			if (window.confirm('<fmt:message key="ccm.Channel.message.File"/>?')) {
				document.cgListForm.action = '/channelGoods_export.do';
				document.cgListForm.submit();
			}
		});
		<s:if test="#session.enableOper!=null && !#session.enableOper">
			setInterval("location.reload()",5000);
		</s:if>
	
		//悬浮窗口显示
		$('.bt_wp span.roomtype').mouseenter(function() {
			$p_left = $(this).position().left + 23;
			$p_top = $(this).position().top + 18;
			$compareW = $(window).width() / 2 + 355;
			if ($p_left > $compareW) {
				$('.channel_goods').css({
					left : $p_left - 258,
					top : $p_top
				});
				$('.channel_goods .info').addClass('Right');
			} else {
				$('.channel_goods').css({
					left : $p_left,
					top : $p_top
				});
				$('.channel_goods .info').removeClass('Right');
			}

			var roomtypeName = $(this).attr('roomTypeName');
			var pmsCode = $(this).attr('pmsCode');
			$('.channel_goods .info').html(
					'<strong><fmt:message key="ccm.Channel.RoomName"/>:</strong>'+roomtypeName+"&nbsp;"+"<br/><strong><fmt:message key='ccm.Channel.PMSRoomCode'/>:</strong>"+pmsCode);
			$('.channel_goods').stop(true, true).slideDown();
		});
		
		//悬浮窗口隐藏
		$('.bt_wp span.roomtype').mouseleave(function() {
			$('.channel_goods .info').html('');
			$('.channel_goods').hide();
		});
	});
</script>
<%@ include file="/common/messages.jsp"%>
<!--搜索项-->
<s:hidden id="showJointwisdom" name="showJointwisdom"></s:hidden>
<s:hidden id="hotelCode" name="hotelCode"></s:hidden>
<s:hidden id="pmsAccount" name="pmsAccount"></s:hidden>
<input id="JointwisdomURL" value="${JointwisdomURL }" type="hidden"/>

<div class="CCMmainConter w1200">
	<div class="title_wp">
		<div class="bt">
			<s:if test="#session.channelGoods">
				<div id="publishInfo" class="alert alert-block inline">
					<strong><fmt:message key="ccm.Channel.message.Attention"/>!</strong> <fmt:message key="ccm.Channel.message.AttentionMessage"/>！
				</div>
			</s:if>
			<span class="mgL12"><button id="publishBtn" type="button" class="btn_1 red"><fmt:message key="common.button.Distribute"/></button> </span>
		</div>
		<span class="TB3 mgR6"><fmt:message key="ccm.Channel.ChannelDistribution"/></span> <a href="#ChannelPublishing" onclick="javascript:addNew();" class="btn_2 black ccm-popup-click"><fmt:message key="ccm.Channel.BindChannel"/></a>
		<a href="#handSend" onclick="javascript:handSend();" class="btn_2 black ccm-popup-click"><fmt:message key="ccm.Channel.ManuallyPush"/></a>
		<a href="#" id="jointwisdom" onclick="javascript:linkToJointwisdom();" class="btn_2 black mgL24"><fmt:message key="ccm.channelHotelConfig.isJointwisdom"/></a>
	</div>
	
    <s:form id="cgListForm" name="cgListForm" method="post" action="" >
        <div class="c_whitebg">
			<div class="nm_box">
         		<ul class="inq_wp frow">
               		<li class="col3_1">
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.InventoryManagement.Channels"/> ：</span>
						</div>
						<div id="channels_show" class="i_input" style="position:relative;">
							<select id="chList_channelCode" name="cgv.channelIds" class="fxt w120" multiple="multiple">
								<c:forEach items="${chList}" var="channel">	
									<option value="${channel.channelId}"
									${fn:contains(cgv.channelIds, channel.channelId)?"selected":""}
									>${channel.channelCode}</option>
								</c:forEach>
							</select>
						</div>
					</li>
					<li class="col3_1">
		            	<button type="button" class="btn_2 black mgL24 list_search" ><fmt:message key="common.button.searchSelect"/></button>
		            	<button type="button" class="btn_2 black mgL24 list_export" ><fmt:message key="common.button.Export"/>	</button>
		          	</li>    
				</ul>
		     </div>
		   </div>        
		</s:form>

	<!--渠道绑定-->
	<div id="ChannelPublishing" class="ccm-popup width900 zoom-anim-dialog mfp-hide"></div>
	<!--消息推送-->
	<div id="handSend" class="ccm-popup zoom-anim-dialog mfp-hide" style="width: 970px;height: 175px"></div>
	<!--列表-->
	<div class="bt_wp">
		<display:table name="cgList" id="cg" class="ccm_table1" requestURI="" defaultsort="1">
			<display:column property="channelCode" sortable="true" titleKey="ccm.InventoryManagement.Channels"  headerClass="sortable sorted" />
			<display:column property="ratePlanCode" sortable="true" titleKey="ccm.RestrictionsManagement.RateCodeDescription" headerClass="sortable sorted" />
			<display:column titleKey="ccm.InventoryManagement.RoomTypes" headerClass="w480">
				<div class="toleft">
					<c:forEach var="rtl" items="${cg.roomTypeVoList}" varStatus="st">
						<c:if test="${st.index>0}">，</c:if>
						<span class="roomtype" roomTypeName="${rtl.roomTypeName}" pmsCode="${rtl.pmsCode}" style="cursor:pointer;">${rtl.roomTypeCode}</span>
					</c:forEach>
				</div>
			</display:column>
			<display:column property="effectiveDate" sortable="true" titleKey="ccm.Channel.ActivatedOn" format="{0,date,yyyy-MM-dd HH:mm}" headerClass="sortable sorted" />
			<display:column sortable="true" titleKey="ccm.Channel.Status" headerClass="sortable sorted">
				<c:choose>
					<c:when test="${cg.status=='1'}"><span class="cl_red"><b><fmt:message key="common.button.close"/></b></span></c:when>
					<c:when test="${cg.status=='3'}"><span><fmt:message key="ccm.Channel.Distributed"/></span></c:when>
					<c:when test="${cg.status=='4'}"><span class="cl_yellow"><fmt:message key="ccm.Channel.Distributed.Not"/></span></c:when>
					<c:when test="${cg.status=='5'}"><span><fmt:message key="ccm.Channel.Distributed.Ing"/></span></c:when>
					<c:when test="${cg.status=='6'}"><span><fmt:message key="ccm.Channel.Distributed.Failure"/></span></c:when>
					<c:otherwise>
						<fmt:message key="ccm.Channel.Distributed.NotDistribute"/>
					</c:otherwise>
				</c:choose>
				
			</display:column>
			<display:column sortable="true" titleKey="ccm.Channel.BindChannel" headerClass="sortable sorted">
				<c:choose>
					<c:when test="${cg.isBind==false}"><span class="cl_red"><b><fmt:message key="ccm.Channel.Bind.Pending"/></b></span></c:when>
					<c:otherwise>
						<fmt:message key="ccm.Channel.Bind.Success"/>
					</c:otherwise>
				</c:choose>
				
			</display:column>
			<display:column titleKey="common.button.Action">
				<s:if test='#attr.cg.status=="1"'>
					<a href="/channelGoods_onOff.do?channelGoodsId=${cg.channelgoodsId}&onOff=0" class="link mgR12"><fmt:message key="ccm.Channel.Open"/></a>
					<a href="#ChannelPublishing" class="link mgR12 ccm-popup-click" onclick="edit('${cg.channelgoodsId}','1')"><fmt:message key="common.button.update"/></a>
				</s:if>
				<s:else>
					<a href="/channelGoods_onOff.do?channelGoodsId=${cg.channelgoodsId}&onOff=1" class="link mgR12"><fmt:message key="common.button.close"/></a>
					<a href="#ChannelPublishing" class="link mgR12" onclick="edit('${cg.channelgoodsId}','2')"><fmt:message key="common.button.update"/></a>
				</s:else>
				<a href="javascript:;" channelgoodsId=${cg.channelgoodsId} status=${cg.status} class="link del_ifself"><fmt:message key="common.button.delete"/></a>
			</display:column>
		</display:table>
		<span><fmt:message key="ccm.Channel.promptMsg"/></span>
	</div>
</div>
<div class="ft_layer channel_goods room_info">
	<div class="info">
	</div>
</div>