<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
 <div class="title_wp"><fmt:message key="ccm.DynamicPackageList"/>
          <div class="bt"> 
          	<a href="/dynamicPackage_edit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/dynamicPackage_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.Channel.ChannelCode"/>：</span></div>
	                <div class="i_input" style="position:relative;width: 100px;">
	                	<div class="moreoption">
							<div class="opts">
								<div class="text typeName" style="width: 178px;"><fmt:message key="common.select.plesesSelect"/></div>
							</div>
						</div>
						<!--房型查看隐藏层-->
						<div class="ft_layer abs ddds" style="width: 216px;">
							<div class=" n_overFlowY">
								<div class="mgA6">
									<c:forEach items="${requestScope.channelList}" var="rl">
										<label class="checkbox"> <input type="checkbox" value="${rl.channelId}" name="creteria.channelIds" <s:if test="creteria.channelIds.contains(#attr.rl.channelId)">checked="checked"</s:if>> <span class=""> <span class="span_roomTypeCode">${rl.channelCode }</span>  </span> </label>
									</c:forEach>
								</div>
							</div>
							<div class="ft_ctr1">
								<button type="button" class="btn_3 white selectAll" style="float: left;"><fmt:message key="common.select.selectAll"/></button>
								<button type="button" class="btn_3 white reverseSel" style="float:left;"><fmt:message key="common.select.Unselect"/></button>
								<button type="button" class="btn_3 green mgR6 confirmthis"><fmt:message key="common.button.OK"/></button>
								<button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>
							</div>
						</div>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.DynamicPackageList.PackageCode"/>：</span></div>
	                <div class="i_input" style="position:relative;width: 100px;">
	                	<div class="moreoption">
							<div class="opts">
								<div class="text typeName" style="width: 178px;"><fmt:message key="common.select.plesesSelect"/></div>
							</div>
						</div>
						<!--房型查看隐藏层-->
						<div class="ft_layer abs ddds" style="width: 216px;">
							<div class=" n_overFlowY">
								<div class="mgA6">
									<c:forEach items="${requestScope.packageList}" var="rl">
										<label class="checkbox"> <input type="checkbox" value="${rl.packageId}" name="creteria.packageIds" <s:if test="creteria.packageIds.contains(#attr.rl.packageId)">checked="checked"</s:if>> <span class=""> <span class="span_roomTypeCode">${rl.packageCode }</span>  </span> </label>
									</c:forEach>
								</div>
							</div>
							<div class="ft_ctr1">
								<button type="button" class="btn_3 white selectAll" style="float: left;"><fmt:message key="common.select.selectAll"/></button>
								<button type="button" class="btn_3 white reverseSel" style="float:left;"><fmt:message key="common.select.Unselect"/></button>
								<button type="button" class="btn_3 green mgR6 confirmthis"><fmt:message key="common.button.OK"/></button>
								<button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>
							</div>
						</div>
	                </div>
                </li>
            </ul>
            <hr class="solided notopMargin">
            <div class="center">
	            <button type="submit" class="btn_2 black mgR12" onclick="javascript:return query();"><fmt:message key="common.button.searchSelect"/></button>
	            <button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
            </div>
          </div>
          </s:form>
          
          <!--列表样式-->
          <div class="bt_wp">
		<display:table name="result.resultList" id="channel" class="ccm_table1" requestURI="" pagesize="20" size="result.totalCount" partialList="true">

				<display:column property="channelCode" sortable="true" titleKey="ccm.Channel.ChannelCode" headerClass="sorted"/>
				<display:column property="packageCode" sortable="true"  titleKey="ccm.DynamicPackageList.PackageCode" headerClass="sorted"/>
				<display:column property="createdTime" sortable="true"  titleKey="ccm.ReservationMonitorReport.CreatedTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted"/>
				<display:column titleKey="ccm.UserActivityLog.Action">
					<a href="/dynamicPackage_edit.do?dp.dynamicPackageId=${channel.dynamicPackageId}" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" dynamicPackageId=${channel.dynamicPackageId} class="link del_ifself"><fmt:message key="common.button.delete"/></a>
				</display:column>
			</display:table>
	 </div>
        </div>

<script>
$(document).ready(function() {
	
	/*是否删除本条 setHotelIdForHref */
	$('.del_ifself').bind('click',function(){
		var dynamicPackageId = $(this).attr('dynamicPackageId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href=setHotelIdForHref("/dynamicPackage_delete.do?dp.dynamicPackageId="+dynamicPackageId);
		}
	});
	
	$('.moreoption').bind('click',function(){
		$(this).next().slideDown();
	});
	$('.closethis').bind('click',function(){
		$(this).parent().parent().hide();
	});
	//全选
	$(".selectAll").bind('click',function(){
		var checklist = $(this).parent().prev().children().children().children();
		for(var i=0;i<checklist.length;i++){
		 	checklist[i].checked = true;
		}
	});
	//反选
	$(".reverseSel").bind('click',function(){
		var checklist = $(this).parent().prev().children().children().children();
		for(var i=0;i<checklist.length;i++){
			checklist[i].checked = !checklist[i].checked;
		}
	});
	//选择
	$('.ddds').each(function(){ 
		var roomTypeName='';
		$(this).children().children().children().children('input:checked').next('span').each(function(){ 
			roomTypeName += $(this).find("span.span_roomTypeCode").text()+",";
		});
		if(roomTypeName!=''){
			$(this).prev().children().children('.typeName').text(roomTypeName.substr(0,roomTypeName.lastIndexOf(',')));
		}
	});
	$('.confirmthis').click(function(){
		showHotelCode(this);
		$(this).parent().parent().hide();
	});
});
function showHotelCode(t){
	var roomTypeName='';
	$(t).parent().prev().children().children().children('input:checked').next('span').each(function(){ 
		roomTypeName += $(this).find("span.span_roomTypeCode").text()+",";
	});
	$(t).parent().parent().prev().children().children('.typeName').text(roomTypeName.substr(0,roomTypeName.lastIndexOf(',')));
}
/*是否删除本条*/
/*
function delUser(url) {
	var r = confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
	if (r == true) {
		location.href = url;
	}
}
*/
//查询
function query(){
	return true;
}
</script>