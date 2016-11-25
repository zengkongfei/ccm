<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<div class="bt">
		</div>
		<fmt:message key="ccm.BookingDepositReport"/>
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
			
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.BasicConfiguration.ChainCode"/></span>
					</div>
					<div id="chains_show" class="i_input" style="position:relative;">
						<select id="sdc_chainId" name="sdc.chainIdList" class="fxt w120" multiple="multiple">
							<c:forEach items="${chainList}" var="rl">
								<option value="${rl.chainId}"
								 	${fn:contains(sdc.chainIdList, rl.chainId)?"selected":""}
								>${rl.chainCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<!-- 客户名称输入框 -->
				<li class="col3_1" id="profileNameInput">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ProfileList.ProfileName"/></span>
					</div>
					<div id="profileName_show" class="i_input" style="position:relative;">
						<input type="text" name="sdc.profileName"/>
					</div>
				</li>
			   <!-- Access Code -->
				<li class="col3_1" id="accessCodeInput">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.BookingDepositReport.AccessCode"/></span>
					</div>
					<div id="accessCode_show" class="i_input" style="position:relative;">
						<select id="sdc_accessCode" name="sdc.accessCodeList" class="fxt w120" multiple="multiple">
							<c:forEach items="${channelList}" var="channel">
								<option value="${channel.channelCode}"
								 	${fn:contains(sdc.accessCodeList, channel.channelCode)?"selected":""}
								>${channel.channelCode}</option>	
							</c:forEach>
						</select>
					</div>
				</li>
				<!-- 酒店代码 -->
				<li class="col3_1" id="hotelIdListClick">
					<div class="i_title">
						<span style="color:red">*</span><span class="text"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/></span>
					</div>
					<div id="hotelIdList_show" class="i_input" style="position:relative;">
						<select id="sdc_hotelId" name="sdc.hotelIdList" class="fxt w120" multiple="multiple">
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelId}"
								 	${fn:contains(sdc.hotelIdList, hotel.hotelId)?"selected":""}
								>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<!--IATA No输入框 -->
				<li class="col3_1" id="corpIATANoInput">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.BookingDepositReport.CorpIATANo"/></span>
					</div>
					<div id="corpIATANoInput_show" class="i_input" style="position:relative;">
						<input type="text" name="sdc.corpIATANo"/>
					</div>
				</li>
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12" onclick="javascript:searchData();"><fmt:message key="common.button.searchSelect"/>	</button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:exportData();"><fmt:message key="common.button.File"/></button>
			</div>
		</div>
	</div>
	</form>
	<div class="c_whitebg">
		<div class="bt_wp" >
			<form id="depositSearchForm" name="depositSearchForm" method="post" action="" style="position:relative;width:100%;overflow:auto;overflow-x:auto;">
				<display:table name="depositSearchResult.resultList" id="depositResultTable" 
					class="ccm_table1" requestURI="" pagesize="20" size="depositSearchResult.totalCount" 
					partialList="true"  form="depositSearchForm" >
				<display:column property="chainCode" sortable="true"  titleKey="ccm.BasicConfiguration.ChainCode" headerClass="sorted" />
				<display:column property="hotelCode" sortable="true"  titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
				<display:column property="name" sortable="true"  titleKey="ccm.ProfileList.ProfileName" headerClass="sorted" />
				<display:column property="accessCode" sortable="true"  titleKey="ccm.BookingDepositReport.AccessCode" headerClass="sorted" />
				<display:column property="corpIATANo" sortable="true"  titleKey="ccm.BookingDepositReport.CorpIATANo" headerClass="sorted" />
				<display:column property="originalCreditLimit" sortable="true"  titleKey="ccm.BookingDepositReport.OriginalCredit" headerClass="sorted" />
				<display:column property="minLimit" sortable="true"  titleKey="ccm.BookingDepositReport.MinCredit" headerClass="sorted" />
				<display:column property="childtotalRoomRev" sortable="true" titleKey="ccm.BookingDepositReport.childtotalRoomRev" headerClass="sorted" />
				<display:column property="totalRoomRev" sortable="true"  titleKey="ccm.BookingDepositReport.TotalRoomRev" headerClass="sorted" />
				<display:column property="income" sortable="true"  titleKey="ccm.BookingDepositReport.Income" headerClass="sorted" />
				<display:column property="balance" sortable="true"  titleKey="ccm.BookingDepositReport.Balance" headerClass="sorted" />
				</display:table>
			</form>
		</div>
	</div>

<!-- 内容区域 end-->
<script type="text/javascript">
	$("#searchForm").effectiveAndValidate({
		errorPlacement : function(error, element) {
			var errWrap = $('<span class=\"error\"></span>');
			error.appendTo(errWrap);
			if (element.is(":radio"))
				errWrap.appendTo(element.parent().parent());
			else if (element.is(":checkbox"))
				errWrap.appendTo(element.parent().parent());
			else if(element.next().is("span"))
				errWrap.appendTo(element.parent().parent());
			else
				errWrap.appendTo(element.parent());
		}
	
	});
	
	//查询
	function searchData() {
		
		//请选择酒店
		if($('#sdc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
	
		if($('#searchForm').valid()){
			document.searchForm.action = "/bookingDeposit_query.do";
			$('#searchForm').submit();
		}
	}

	//导出excel
	function exportData() {
	
		//请选择酒店
		if($('#sdc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		
		//如果通过校验
		if($('#searchForm').valid()){
			document.searchForm.action = "/bookingDeposit_export.do";
			document.searchForm.submit();
		}
	}

	$("#hotelIdListClick").click(function(){
		 $('#sdc_hotelId').multiselect('rebuild');
	});
	
	$(document).ready(function() {
		
		 //如果没有选中任何集团,则默认选中第一个集团
	    if($('#sdc_chainId option:selected').length==0){
	    	$("#sdc_chainId option:first").prop("selected", 'selected');
	    }
	    
		//动态加载酒店代码
		$('#chains_show').bind("change",function(){
			$('#sdc_hotelId').empty();
			showChainCode();
		});
		
		//集团代码初始化 
		$('#sdc_chainId').multiselect({
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
	
		//初始化
		$('#sdc_hotelId').multiselect({
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
		//渠道代码初始化
		$('#sdc_accessCode').multiselect({
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
		
		//如果是最开始跳入
		if('${isInit}' == '1'){
			/*
			//如果存在集团,默认为第一个集团
			if($("input[name='sdc.chainIdList']").length>0){
				var firstChk = $("input[name='sdc.chainIdList']").eq(0);
				firstChk.prop('checked',true);
				
				$('#chains_click .typeName').text(firstChk.attr('code'));
				$('#chains_click .typeCode').append(
						'<input type="text" name="sdc.chainIdList" value="'+firstChk.val()+'">');
			}
			*/
			//如果存在酒店,默认为所有酒店
			if($('#sdc_hotelId option').length>0){
				$('#sdc_hotelId').next().find('button').html('<fmt:message key="common.select.selectAll"/> <b class="caret"></b>');
				$('#sdc_hotelId option').attr('selected','selected');
				$('#sdc_hotelId').next().find('ul>li').find('input').prop('checked',true);
			}
		}
	});

	function showChainCode(){
		var chainCodeName='';
		$('#chains_show input:checked').next('span').each(function(){ 
			chainCodeName += $(this).find("span.span_chainCode").text()+",";
		});
		
		var chainIds = '';
		$('#chains_show input:checked').each(function(){ 
			$('#chains_click .typeCode').append(
					'<input type="text" name="sdc.chainIdList" value="'+$(this).val()+'">');
			chainIds += $(this).val() + ',';
		});
		$('#chains_click .typeName').text(chainCodeName.substr(0,chainCodeName.lastIndexOf(',')));
		chainIds = chainIds.substr(0,chainIds.lastIndexOf(','));
		
		$('#sdc_hotelId').next().find('button').html('<fmt:message key="common.select.plesesSelect"/> <b class="caret"></b>');
		$('option', $('#sdc_hotelId')).remove();
		
		if($('#chains_show input:checked').length >= 1){
			$.ajax({
			   	 type:"POST",
			   	 async:false,
			     dataType : "json",
			   	 url:"/orderProduct_ajaxGetHotels.do",
			   	 data:{"chainIds":chainIds},
				 success:function(data){
					  if(data.length > 0){
						  for(var i =0 ; i < data.length ; i++){
							  var hotel = data[i] ;
							  
							  //初始化
							  $('#sdc_hotelId').append(
										'<option value="'+hotel.hotelId+'" selected>'+hotel.hotelCode+'</option>');
							  
						  }
						  $('#sdc_hotelId').next().find('button').html('<fmt:message key="common.select.selectAll"/> <b class="caret"></b>');
						  $('#sdc_hotelId').multiselect('rebuild');
					  }
			     }
		    });
		}
		
	}
	
</script>
