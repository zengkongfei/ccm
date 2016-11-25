<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/messages.jsp"%>
<s:form id="channelConfigForm" action="" method="post">
<appfuse:ccmToken name="token"></appfuse:ccmToken>
	<s:hidden  id="channelIdHidden" key="channel.channelId"></s:hidden>
	<s:hidden  id="channelCodeHidden" key="channel.channelCode"></s:hidden>
	<s:hidden id="channelHotelConfigIdHidden" name="channelHotelConfig.channelHotelConfigId"></s:hidden>
	<s:hidden id="channelIdHiddenConfigHidden" name="channelHotelConfig.channelId"></s:hidden>
	<s:hidden id="channelHotelConfigHidden" name="channelHotelConfig.channelHotelConfig"></s:hidden>
	<s:hidden id="channelRoomTypeConfigHidden" name="channelHotelConfig.channelRoomTypeConfig"></s:hidden>
	<s:hidden id="channelRateConfigHidden" name="channelHotelConfig.channelRateConfig"></s:hidden>
	<s:hidden id="pushMethodHidden" name="channelHotelConfig.pushMethod"></s:hidden>
	<s:hidden id="ariSwitchHidden" name="channelHotelConfig.ariSwitch"></s:hidden>
	
	<div class="c_whitebg pdA12">	
	  <hr class="dashed">
	  <div class="banner-bottom-grids">
		<div class="col-md-3 banner-bottom-grid-left">
			<div class="br-bm-gd-lt1">
			<div class="title_wp"><fmt:message key="ccm.channelHotelConfig.ChannelHotelConfig"/></div>
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.BasicConfiguration.ChainCode"/>：</span>
					</div>
					<div class="i_input">
						<span id="chainCode" class="checkbox inline"></span>
					</div>
				</li>
				<%-- <li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/>：</span>
					</div>
					<div class="i_input">
						<span id="hotelCode" class="checkbox inline"></span>
					</div>
				</li> --%>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.UserActivityLog.PropertyName"/>：</span>
					</div>
					<div class="i_input">
						<span id="hotelName" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text">Country Code：</span>
					</div>
					<div class="i_input">
						<span id="countryCode" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text">City Code：</span>
					</div>
					<div class="i_input">
						<span id="city" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PropertyList.PropertyLocation"/>：</span>
					</div>
					<div class="i_input">
						<span id="cityName" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PropertyList.PropertyAddress"/>：</span>
					</div>
					<div class="i_input">
						<span id="address" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.PostalCode"/>：</span>
					</div>
					<div class="i_input">
						<span id="postCode" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PropertyList.Tel"/>：</span>
					</div>
					<div class="i_input">
						<span id="telephone" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PropertyList.Fax"/>：</span>
					</div>
					<div class="i_input">
						<span id="fax" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text">email：</span>
					</div>
					<div class="i_input">
						<span id="email" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PropertyList.TaobaoShopName"/>：</span>
					</div>
					<div class="i_input">
						<span id="tbShopName" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Language"/>：</span>
					</div>
					<div class="i_input">
						<span id="languageCode" class="checkbox inline"></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.channelHotelConfig.province"/>：</span>
					</div>
					<div class="i_input">
						<span id="province" class="checkbox inline"></span>
					</div>
				</li>
			</ul>		
			</div>
		</div>	
		<div class="col-md-3 banner-bottom-grid-left">
			<div class="br-bm-gd-lt br-bm-gd-lt2">	
			<div class="title_wp"><fmt:message key="ccm.channelHotelConfig.RoomTyppeConfig"/></div>
			<ul class="list_input">
			 		<%-- <li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.Channel.RoomTypeCode"/>：</span>
						</div>
						<div class="i_input">
							<span id="roomTypeCode" class="checkbox inline"></span>
						</div>
					</li> --%>
					<li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.RoomTypeTemplate.MaxOccupancy"/>：</span>
						</div>
						<div class="i_input">
							<span id="maxOccupancy" class="checkbox inline"></span>
						</div>
					</li>
					<li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.RateValidityPeriodReport.RoomTypeName"/>：</span>
						</div>
						<div class="i_input">
							<span id="roomTypeName" class="checkbox inline"></span>
						</div>
					</li>
					<li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.RoomTypeList.BedType"/>：</span>
						</div>
						<div class="i_input">
							<span id="bedTypeName" class="checkbox inline"></span>
						</div>
					</li>
					<li>
						<div class="i_title">
							<span class="text"><fmt:message key="common.Description"/>：</span>
						</div>
						<div class="i_input">
							<span id="description" class="checkbox inline"></span>
						</div>
					</li>
				</ul>
			</div>	
			
			<div class="br-bm-gd-lt br-bm-gd-lt2">	
			<div class="title_wp"><fmt:message key="ccm.channelHotelConfig.RateConfig"/></div>
			<ul class="list_input">
			 		<%-- <li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.otalog.RatePlanCode"/>：</span>
						</div>
						<div class="i_input">
							<span id="ratePlanCode" class="checkbox inline"></span>
						</div>
					</li> --%>
					<li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.Rates.RateCode"/>：</span>
						</div>
						<div class="i_input">
							<span id="ratePlanName" class="checkbox inline"></span>
						</div>
					</li>
					<li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.channelHotelConfig.guaranteeJsonArr"/>：</span>
						</div>
						<div class="i_input">
							<span id="guaranteeJsonArr" class="checkbox inline"></span>
						</div>
					</li>
					<li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.channelHotelConfig.productStatusType"/>：</span>
						</div>
						<div class="i_input">
							<span id="productStatusType" class="checkbox inline"></span>
						</div>
					</li>
				</ul>
			</div>	
			
		</div>
		<div class="col-md-3 banner-bottom-grid-left">
				<div class="br-bm-gd-lt br-bm-gd-lt2">
				<div class="title_wp"><fmt:message key="ccm.channelHotelConfig.PushMethodConfig"/></div>
				<ul class="list_input">
			 		<li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.channelHotelConfig.PushAlone"/>：</span>
						</div>
						<div class="i_input">
							<span id="pushMethod" class="checkbox inline">
								 <input checked="checked" id="pushAlone" type="radio" name="pushMethodRadio"/> 
							</span>
						</div>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.channelHotelConfig.PushCombined"/>：</span>
						</div>
						<div class="i_input">
							<span id="pushMethod" class="checkbox inline">
								 <input id="pushCombined" type="radio" name="pushMethodRadio"/> 
							</span>
						</div>
					</li>
				</ul>
				
				<div class="title_wp"><fmt:message key="ccm.channelHotelConfig.JointwisdomConfig"/></div>
				<ul class="list_input">
			 		<li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.channelHotelConfig.isJointwisdom"/>：</span>
						</div>
						<div class="i_input">
							<span id="isJointwisdom" class="checkbox inline">
								<s:checkbox key="channel.isJointwisdom"/>
							</span>
						</div>
					</li>
				</ul>
			</div>
			
			<div class="br-bm-gd-lt br-bm-gd-lt2">
			<div class="title_wp"><fmt:message key="ccm.channelHotelConfig.ARISwitch"/></div>
			<ul class="list_input">
		 		<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.channelHotelConfig.ARISwitch"/>：</span>
					</div>
					<div class="i_input">
						<span id="ariSwitch" class="checkbox inline">
							 <input id="ariSwitchId" type="checkbox" name="ariSwitch"/>
						</span>
					</div>
				</li>
			</ul>
			</div>
		 </div>
		<div class="clearfix"> </div>
		</div>
	</div>
		<hr class="dashed">
		<div class="listinputCtrl">
			<button type="button" class="btn_1 green mgR12 f_save" onclick="dosubmit();"><fmt:message key="common.button.OK"/></button>
			<a class="btn_1 white" href="/channel_list.do?menuId=${sessionScope.menuId}"><fmt:message key="common.Return"/></a>
		</div>
	</div>
</s:form>

<script>
	$(document).ready(function() {
		showCfg();
	});
	
	function dosubmit() {
		
		if ($("#channelConfigForm").valid()) {
			editCfg();
			document.channelConfigForm.action = '/channel_saveConfig.do';
			document.channelConfigForm.submit();
			//禁止重复提交
			 $('.f_save').addClass('no_ald');
			 $('.f_save').attr("disabled","disabled");	
		}

	}
	
	//提交前修改数据
	function editCfg(){
		/*酒店配置
		'address':'true','chainCode':'true','city':'true','cityName':'true','countryCode':'true',
		'email':'true','fax':'true','hotelCode':'true','hotelName':'true','languageCode':'true',
		'postCode':'true','tbShopName':'true','telephone':'true'*/
		var channelHotelConfig=''
		+($('#addressChk').is(':checked')?"'address':'true',":'')
	    +($('#chainCodeChk').is(':checked')?"'chainCode':'true',":'')
	    +($('#cityChk').is(':checked')?"'city':'true',":'')
	    +($('#cityNameChk').is(':checked')?"'cityName':'true',":'')
	    +($('#countryCodeChk').is(':checked')?"'countryCode':'true',":'')
	    +($('#emailChk').is(':checked')?"'email':'true',":'')
	    +($('#faxChk').is(':checked')?"'fax':'true',":'')
	    +($('#hotelCodeChk').is(':checked')?"'hotelCode':'true',":'')
	    +($('#hotelNameChk').is(':checked')?"'hotelName':'true',":'')
	    +($('#languageCodeChk').is(':checked')?"'languageCode':'true',":'')
	    +($('#postCodeChk').is(':checked')?"'postCode':'true',":'')
	    +($('#provinceChk').is(':checked')?"'provinceStr':'true',":'')
	    +($('#tbShopNameChk').is(':checked')?"'tbShopName':'true',":'')
	    +($('#telephoneChk').is(':checked')?"'telephone':'true',":'')
		;
		//去掉末尾的逗号
		if(channelHotelConfig.charAt(channelHotelConfig.length-1)==","){
			channelHotelConfig=channelHotelConfig.substring(0,channelHotelConfig.length-1);
		}
		if(channelHotelConfig!=null && channelHotelConfig.length>1){
			channelHotelConfig='{'+channelHotelConfig+'}';
		}
		$('#channelHotelConfigHidden').val(channelHotelConfig);
		
		//房型配置 
		/*
		'bedTypeName':'true','description':'true','maxOccupancy':'true','roomTypeCode':'true','roomTypeName':'true'"		
		*/
		var channelRoomTypeConfig=''
			+($('#bedTypeNameChk').is(':checked')?"'bedTypeName':'true',":'')
		    +($('#descriptionChk').is(':checked')?"'description':'true',":'')
		    +($('#maxOccupancyChk').is(':checked')?"'maxOccupancy':'true',":'')
		    +($('#roomTypeCodeChk').is(':checked')?"'roomTypeCode':'true',":'')
		    +($('#roomTypeNameChk').is(':checked')?"'roomTypeName':'true',":'')
			;
			//去掉末尾的逗号
			if(channelRoomTypeConfig.charAt(channelRoomTypeConfig.length-1)==","){
				channelRoomTypeConfig=channelRoomTypeConfig.substring(0,channelRoomTypeConfig.length-1);
			}
			if(channelRoomTypeConfig!=null && channelRoomTypeConfig.length>1){
				channelRoomTypeConfig='{'+channelRoomTypeConfig+'}';
			}
			$('#channelRoomTypeConfigHidden').val(channelRoomTypeConfig);
		//房价配置
		/*
		channelRateConfigHidden
		guaranteeJsonArr;// 担保规则json字符串
		ratePlanCode;// 房价代码
		ratePlanName;// 房价名称
		*/
		var channelRateConfig=''
			+($('#ratePlanCodeChk').is(':checked')?"'ratePlanCode':'true',":'')
		    +($('#ratePlanNameChk').is(':checked')?"'ratePlanName':'true',":'')
		    +($('#guaranteeJsonArrChk').is(':checked')?"'guaranteeJsonArr':'true',":'')
		    +($('#productStatusTypeChk').is(':checked')?"'productStatusType':'true',":'')
		    
			;
			//去掉末尾的逗号
			if(channelRateConfig.charAt(channelRateConfig.length-1)==","){
				channelRateConfig=channelRateConfig.substring(0,channelRateConfig.length-1);
			}
			
			if(channelRateConfig!=null && channelRateConfig.length>1){
				channelRateConfig='{'+channelRateConfig+'}';
			}
			$('#channelRateConfigHidden').val(channelRateConfig);
			
		//推送方法配置  pushAlone pushCombined pushMethodHidden
		if($('#pushAlone').is(':checked')){
			$('#pushMethodHidden').val(1);
		}else{
			$('#pushMethodHidden').val(2);
		}

		if($('#ariSwitchId').is(':checked')){
			$('#ariSwitchHidden').val(true);
		}else{
			$('#ariSwitchHidden').val(false);
		}
		
	}
	//加载页面
	 function showCfg(){
	
		var hotelConfig=$('#channelHotelConfigHidden').val();
		var hotelConfigJson;
		if(hotelConfig.charAt(hotelConfig.length-1)=="}"){
			hotelConfigJson=hotelConfig;
		}else{
			hotelConfigJson="{"+hotelConfig+"}";
		}
		
		var hotelcfgObj=eval('('+hotelConfigJson+')');
		
		$('#province').append("<input id='provinceChk' type='checkbox' "+
				(hotelcfgObj.provinceStr=="true"?" checked='checked'":"")
		+">"); 
		
		/* $('#hotelCode').append("<input id='hotelCodeChk' type='checkbox' "+
		(hotelcfgObj.hotelCode=="true"?" checked='checked'":"")
		+">");  */
		
		$('#cityName').append("<input id='cityNameChk' type='checkbox' "+
		(hotelcfgObj.cityName=="true"?"checked":"")
		+">");  
		
		$('#postCode').append("<input id='postCodeChk' type='checkbox' "+
		(hotelcfgObj.postCode=="true"?" checked='checked'":"")
		+">"); 
		
		$('#city').append("<input id='cityChk' type='checkbox' "+
		(hotelcfgObj.city=="true"?"checked":"")
		+">");  
		
		$('#countryCode').append("<input id='countryCodeChk' type='checkbox' "+
		(hotelcfgObj.countryCode=="true"?"checked":"")
		+">");  
		
		$('#telephone').append("<input id='telephoneChk' type='checkbox' "+
		(hotelcfgObj.telephone=="true"?"checked":"")
		+">");  
		
		$('#fax').append("<input id='faxChk' type='checkbox' "+
		(hotelcfgObj.fax=="true"?"checked":"")
		+">");  
		
		$('#email').append("<input id='emailChk' type='checkbox' "+
		(hotelcfgObj.email=="true"?"checked":"")
		+">");  

		$('#tbShopName').append("<input id='tbShopNameChk' type='checkbox' "+
		(hotelcfgObj.tbShopName=="true"?"checked":"")
		+">");  
		
		$('#chainCode').append("<input id='chainCodeChk' type='checkbox' "+
		(hotelcfgObj.chainCode=="true"?"checked":"")
		+">");  
		
		$('#languageCode').append("<input id='languageCodeChk' type='checkbox' "+
		(hotelcfgObj.languageCode=="true"?"checked":"")
		+">");  
		
		$('#hotelName').append("<input id='hotelNameChk' type='checkbox' "+
		(hotelcfgObj.hotelName=="true"?"checked":"")
		+">");  
		
		$('#address').append("<input id='addressChk' type='checkbox' "+
		(hotelcfgObj.address=="true"?"checked":"")
		+">");  
		
		var roomConfig=$('#channelRoomTypeConfigHidden').val();
		
		var roomConfigJson;
		if(roomConfig.charAt(roomConfig.length-1)=="}"){
			roomConfigJson=roomConfig;
		}else{
			roomConfigJson="{"+roomConfig+"}";
		}
		
		var roomcfgObj=eval('('+roomConfigJson+')');
		
	/* 	$('#roomTypeCode').append("<input id='roomTypeCodeChk' type='checkbox' "+
		(roomcfgObj.roomTypeCode=="true"?"checked":"")
		+">");   */
		
		$('#maxOccupancy').append("<input id='maxOccupancyChk' type='checkbox' "+
		(roomcfgObj.maxOccupancy=="true"?"checked":"")
		+">");  
		
		$('#roomTypeName').append("<input id='roomTypeNameChk' type='checkbox' "+
		(roomcfgObj.roomTypeName=="true"?"checked":"")
		+">");  
		
		$('#bedTypeName').append("<input id='bedTypeNameChk' type='checkbox' "+
		(roomcfgObj.bedTypeName=="true"?"checked":"")
		+">");  
		
		$('#description').append("<input id='descriptionChk' type='checkbox' "+
		(roomcfgObj.description=="true"?"checked":"")
		+">");  
		
		//房价配置
		var rateConfig=$('#channelRateConfigHidden').val();
		
		var rateConfigJson;
		if(rateConfig.charAt(rateConfig.length-1)=="}"){
			rateConfigJson=rateConfig;
		}else{
			rateConfigJson="{"+rateConfig+"}";
		}
		
		var ratecfgObj=eval('('+rateConfigJson+')');
		
		/* $('#ratePlanCode').append("<input id='ratePlanCodeChk' type='checkbox' "+
		(ratecfgObj.ratePlanCode=="true"?"checked":"")
		+">");   */
		
		$('#ratePlanName').append("<input id='ratePlanNameChk' type='checkbox' "+
		(ratecfgObj.ratePlanName=="true"?"checked":"")
		+">");  
		
		$('#guaranteeJsonArr').append("<input id='guaranteeJsonArrChk' type='checkbox' "+
		(ratecfgObj.guaranteeJsonArr=="true"?"checked":"")
		+">");  
		
		$('#productStatusType').append("<input id='productStatusTypeChk' type='checkbox' "+
				(ratecfgObj.productStatusType=="true"?"checked":"")
		+">");  
		
		var pushConfig=$('#pushMethodHidden').val();
		if(pushConfig==2||pushConfig=='2'){
			$("#pushAlone").removeAttr("checked");
			$("#pushCombined").attr("checked","checked");
		}
		
		var ariSwitch=$('#ariSwitchHidden').val();
		if(ariSwitch==true||ariSwitch=='true'){
			$("#ariSwitchId").attr("checked","checked");
		}
		
	}
	
</script>