<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="CCMmainConter w1200">
  <div class="title_wp"> 
    <!-- <div class="bt"> <a href="0HOTEL-2.html" class="btn_2 blue">新建</a> </div>--> 
    <fmt:message key="ccm.Rates.RatesManagment"/> </div>
  
  <!--左右两列-->
  <div class="ccm_2wp clearfix">
    <div class="ccm_left" style="position:relative;">
      <div class="menulayerMask"></div>
      <div class="lt_menu2">
        <ul class="mlist">
        </ul>
        <div class="newwp">
          <button type="button" class="btn_2 blue"><fmt:message key="ccm.Rates.RateSetupNew"/></button> </div>
      </div>
    </div>
   
<div class="ccm_2col">
      <div class="ccm_right"> 

        <div class="step_wp">
            <ul class="step3">
            	<li><span>1.</span><fmt:message key="ccm.Rates.RateHeader"/></li>
                <li class="active"><span>2.</span><fmt:message key="ccm.Rates.RateDetail"/></li>
                <li class="noline"><span>3.</span><fmt:message key="ccm.Rates.OK"/></li>
            </ul>
            </div>
       
       <!--价格内容-->
        <div class="ratetbwp">
          <div class="ctl frow">
            <div class="float-right"><a href="#AddNewPrice" class="btn_2 blue  ccm-popup-click" id="newRateDetail" onclick="showRateDetail(-1);"><fmt:message key='common.button.New'/></a></div>
            <span class="TB5">
            <label class="radio inline">
              <input type="radio" name="ccm_rdption2" id="optionsRadios_rate1" onclick="filterDateRateDetailList(true);" value="true" checked>
              <span class="checked"><fmt:message key='ccm.Rates.ShowOnlyVaildTime'/></span> </label>
            <label class="radio inline">
              <input type="radio" name="ccm_rdption2" id="optionsRadios_rate2" onclick="filterDateRateDetailList(false);" value="true">
              <span class=""><fmt:message key='ccm.Rates.ShowAllRecords'/></span> </label>
            </span>
           </div>
          <div class="bt_wp">
            <table class="ccm_table1" id="normalTB">
              <thead>
                <tr>
                  <th class="w180 sortable sortAsc" onclick="sortBy('date');"><span><fmt:message key='ccm.Rates.Dates'/></span></th>
                  <th class="w180 sortable sortDes" onclick="sortBy('week');"><span><fmt:message key='ccm.Rates.Weeks'/></span></th>
                  <th><span><fmt:message key='ccm.InventoryManagement.RoomTypes'/></span></th>
                  <th class="sortable nosort w100" onclick="sortBy('price');"><span><fmt:message key='ccm.Rates.Amounts'/></span></th>
                  <th class="w120"><span><fmt:message key='common.button.Activity'/></span></th>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
            <%--
            <table class="ccm_table1" id="selectTB" style="display:none;">
              <thead>
                <tr>
                  <th class="w180 sortable sortAsc"><span><fmt:message key='ccm.Rates.Dates'/></span></th>
                  <th class="w180 sortable sortDes"><span><fmt:message key='ccm.Rates.Weeks'/></span></th>
                  <th><span><fmt:message key='ccm.InventoryManagement.RoomTypes'/></span></th>
                  <th class="sortable nosort w100"><span><fmt:message key='ccm.Rates.Amounts'/></span></th>
                  <th class="w100"><span><fmt:message key='common.button.Activity'/></span></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>2014-01-01 ~ 2014-12-31</td>
                  <td><div class="toleft"><span>一，二，三，四，五，六，日</span></div></td>
                  <td><div class="toleft"><a class="focus_room" href="#">DXF行政大床房</a>，<a class="link_1" href="#">DXF行政大床房</a>，<a class="link_1" href="#">TF1行政套房</a>，<a class="link_1" href="#">XCF行政双床房</a></div></td>
                  <td><div class="toright c_rel"><span class="price">678.00</span>
                      <div class="ft_layer p_hover">
                        <p><fmt:message key="ccm.Rates.1Adult"/>：678.00 元</p>
                        <p><fmt:message key="ccm.Rates.2Adult"/>：678.00 元</p>
                        <p><fmt:message key="ccm.Rates.3Adult"/>：678.00 元</p>
                      </div>
                    </div></td>
                  <td><a href="#AddNewPrice" class="link mgR12  ccm-popup-click">修改</a><a href="javascript:;" class="link del_ifself">删</a></td>
                </tr>
              </tbody>
            </table>
             --%>
          </div>      
          
             <!--新建价格-->
          <div id="AddNewPrice" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
          <s:hidden name="rateDetailIdx"></s:hidden>
            <div class="pp_main">
              <div class="t_title"><fmt:message key='ccm.Rates.RateSetupEdit'/></div>
              <div class="pdA24 pdT12">
                
                <div class="p_title"><fmt:message key='ccm.Rates.RoomTypes'/></div>
                <div class="table2wp">
                <div class="n_overFlowY2">
                <table class="ccm_table2 roomRatePackage" id="roomPackageTab">
           			<tr>
                       	<th class="w20"><span class="p_ch1"><input type="checkbox" id="RoomTypeCheckAll" value="option1" ></span></th>
                       	<th class="w360"><fmt:message key='ccm.Rates.RoomTypesChoose'/></th>
                       	<th><fmt:message key='ccm.Rates.Packages'/></th>
                       </tr>
                       <s:iterator value="configMap['roomTypeList']" var="rt" status="sta">
                   		<tr>
                       	<td class="w20"><span class="p_ch1"><input type="checkbox" id="${rt.roomTypeId }" value="option1"></span></td>
                       	<td class="w360">
                       		<span class="room checked">${rt.roomTypeCode }&nbsp;${rt.roomTypeName }
                       			<c:if test="${!empty rt.pmsCode}">_${rt.pmsCode}</c:if>
                       		</span>
                       	</td>
                       	<td><a href="javascript:;" class="link  BSP_click roomSelected">
                       		<span id="roomPackageId_${sta.index}" class="checked"></span></a> <!-- 该房型的打包服务 -->
                       		<a href="javascript:;" class="link BSP_click"><fmt:message key='common.button.add'/></a>
                       	</td>
                       </tr>
                       </s:iterator>
                   </table>
                 </div>
                 </div>
                
                <!--打包服务-->
                <div class="ft_layer priceBagService" id="roomPackageDiv" style="width:399px;">
                            <div class="n_overFlowY">
                              <div class="mgA6">
                              <c:forEach items="${configMap['packages']}" var="pk" varStatus="idxs">
                              		<label class="checkbox">
				                      <input type="checkbox" id="${pk.packageId}" value="${pk.packageCode }" >
				                      <span class="">${pk.packageCode}&nbsp;${pk.packageName }</span>
				                    </label>
                               </c:forEach>
                              </div>
                              <input type="hidden" id="roomTypeId"/>
                              <input type="hidden" id="roomType_idx"/>
                            </div>
                            <div class="ft_ctr1">
                              <button type="button" class="btn_3 green confirmthis"><fmt:message key="common.button.OK"/></button>
                              <button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>
                            </div>
                          </div>
                
                <div class="p_title"><fmt:message key="ccm.Rates.Dates"/></div>
                
                <ul class="list_input">
                  
                  <li class="c_rel">
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.BeginDate"/>：</span></div>
                    <div class="i_input">
                    <input class="fxt w120" name="effectiveDate" id="effectiveDate_rateDetail" type="text" value="" >
                    </div>
                    <div class="date_abs">
                      <div class="dateweek">
                        <div class="d_wp"> <span><fmt:message key="common.week.sunday"/></span>
                          <input type="checkbox" id="isApplyToSunday" value=value="true" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.monday"/></span>
                          <input type="checkbox" id="isApplyToMonday" value="true" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.tuesday"/></span>
                          <input type="checkbox" id="isApplyToTuesday" value="true" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.wednesday"/></span>
                          <input type="checkbox" id="isApplyToWednesday" value="true" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.thursday"/></span>
                          <input type="checkbox" id="isApplyToThursday" value="true" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.friday"/></span>
                          <input type="checkbox" id="isApplyToFriday" value="true" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.saturday"/></span>
                          <input type="checkbox" id="isApplyToSaturday" value="true" checked=""/>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.EndDate"/>：</span></div>
                    <div class="i_input">
                      <input class="fxt w120" type="text" name="rp.expireDate"  id="expireDate_rateDetail" value="" title="">
                    </div>
                  </li>
                </ul>
                
                <div class="p_title"><fmt:message key="ccm.Rates.PriceSetting"/></div>
                <div class="editprice_dt">
                <ul class="list_input">
                  <li>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.1Adult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" id="1" name="baseAmount" numberOfUnits="1" type="text" value="" > </div> 
                    <div class="i_title narrow"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.2Adult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" name="baseAmount" numberOfUnits="2" value="" ></div>
                  </li>
                    </ul>
                     
                     <hr class="dashed" style=" margin-left:100px;">
                     <ul class="list_input">
                  <li>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.3Adult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" value="" name="baseAmount" numberOfUnits="3"> </div> 
                    <div class="i_title narrow"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.4Adult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" value="" name="baseAmount" numberOfUnits="4"></div>
                    <div class="i_title narrow"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.5Adult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" value="" name="baseAmount" numberOfUnits="5"></div>
                  </li>
                  <li>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.ExtraAdult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" value="" name="extraBed" id="extraBed" > </div>
                    <div class="i_title narrow"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Children"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" value="" name="extraBedChild" id="extraBedChild"> </div>
                  </li>
                </ul>
                </div>
              </div>
              <div class="b_crl">
                <button type="button" id="btn_saveRateDetail" class="btn_2 green mgR6"><fmt:message key="common.button.save"/>	</button>
                <button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
              </div>
            </div>
          </div>
       
      </div>
      <!-- 上一步下一步控制--> 
     <div class="mgT24">
      <hr class="dashed">
     </div>
      <div class="toright">
         <div class="float-left"> <a href="/roomRate_cancel.do?rp.ratePlanCode=${rp.ratePlanCode}" class="btn_1 red"><fmt:message key='ccm.Rates.Close'/></a></div>
          <a href="javascript:history.back();" class="btn_1 green mgR6"><fmt:message key="common.Back"/>	</a> <a href="javascript:rateDetailForm.submit();" class="btn_1 green"><fmt:message key='ccm.Rates.OKAndNext'/></a>
      </div>
    </div>
  </div>
</div>
</div>

<!-- 拆分日期 -->
          <form action="" name="splitRateDetailForm" id="splitRateDetailForm">
          <div id="splitRateDetailDiv" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
          <s:hidden name="splitRateDetailIdx"></s:hidden>
            <div class="pp_main">
              <div class="t_title"><fmt:message key="ccm.Rates.Split"/>	</div>
              <div class="pdA24 pdT12">
                <div class="p_title"><fmt:message key="ccm.Rates.Dates"/></div>
                <ul class="list_input">
                  <li class="c_rel">
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.BeginDate"/>：</span></div>
                    <div class="i_input">
                      <input class="fxt w120 required" type="text" id="effectiveDate_splitRateDetail"  value="" >
                    </div>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.EndDate"/>：</span></div>
                    <div class="i_input">
                      <input class="fxt w120 required" type="text" id="expireDate_splitRateDetail"  value="" >
                    </div>
                  </li>
                </ul>
                
                <div class="p_title"><fmt:message key="ccm.Rates.PriceSetting"/></div>
                <div class="splitRateDetailPrice">
                <ul class="list_input">
                  <li>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.1Adult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" id="1" name="baseAmount" numberOfUnits="1" type="text" value="" > </div> 
                    <div class="i_title narrow"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.2Adult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" name="baseAmount" numberOfUnits="2" value="" ></div>
                  </li>
                </ul>
                     <hr class="dashed" style=" margin-left:100px;">
                     <ul class="list_input">
                  <li>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.3Adult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" value="" name="baseAmount" numberOfUnits="3"> </div> 
                    <div class="i_title narrow"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.4Adult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" value="" name="baseAmount" numberOfUnits="4"></div>
                    <div class="i_title narrow"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.5Adult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" value="" name="baseAmount" numberOfUnits="5"></div>
                  </li>
                  <li>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.ExtraAdult"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" value="" name="extraBed" id="extraBed" > </div>
                    <div class="i_title narrow"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Children"/>：</span></div>
                    <div class="i_input"><input class="fxt w80" type="text" value="" name="extraBedChild" id="extraBedChild"> </div>
                  </li>
                </ul>
                </div>
              </div>
              <div class="b_crl">
                <button type="button" class="btn_2 green mgR6" onclick="saveSplitRateDetail();" id="btn_saveSplitRateDetail"><fmt:message key="common.button.save"/>	</button>
                <button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
              </div>
            </div>
          </div>
          </form>
<form action="/roomRate_finish.do" method="post" name="rateDetailForm">
	<s:hidden name="rateDetailListStr"></s:hidden>
	<s:hidden name="rp.ratePlanCode"></s:hidden>
</form>
<c:if test="${rp.inheritRatePlanId == null or rp.inheritRatePlanId ==''}">
	<c:set value="false" var="isEdit"></c:set>
</c:if>
<c:if test="${rp.inheritRatePlanId != null && rp.inheritRatePlanId !=''}">
	<c:set value="true" var="isEdit"></c:set>
</c:if>

<script type="text/javascript" src="/pages/roomRate/rateModel.js"></script>
<script type="text/javascript">

var Adult='';
var Delete='';
var edit='';
var Split='';

function hiddenOperate(){
	<c:if test="${isEdit}">
	//将新建按钮禁用
	$("#newRateDetail").remove();
	//将操作禁用
	$('#normalTB thead tr th:last-child,#normalTB tbody tr td:last-child').hide();
	</c:if>
}

//房价明细
var rateDetailList = new Array();
//房型打包服务
$(document).ready(function() {
//日期显示
var dpconfig = {
	dateFormat : "yy-mm-dd",
	dayNamesMin : [ 
	               '<fmt:message key="calendar.week.sunday"/>', 
	               '<fmt:message key="calendar.week.monday"/>', 
	               '<fmt:message key="calendar.week.tuesday"/>', 
	               '<fmt:message key="calendar.week.wednesday"/>', 
	               '<fmt:message key="calendar.week.thursday"/>', 
	               '<fmt:message key="calendar.week.friday"/>', 
	               '<fmt:message key="calendar.week.saturday"/>' 
	              ],
	              yearSuffix : '<fmt:message key="time.year"/>',
	showMonthAfterYear : true,
	minDate:'<fmt:formatDate value="${ratePlan.rp.effectiveDate}" pattern="yyyy-MM-dd"/>',
	maxDate:'<fmt:formatDate value="${ratePlan.rp.expireDate}" pattern="yyyy-MM-dd"/>',
	monthNames : [ 
	               '<fmt:message key="calendar.month.january"/>', 
	               '<fmt:message key="calendar.month.february"/>', 
	               '<fmt:message key="calendar.month.march"/>', 
	               '<fmt:message key="calendar.month.april"/>', 
	               '<fmt:message key="calendar.month.may"/>', 
	               '<fmt:message key="calendar.month.june"/>', 
	               '<fmt:message key="calendar.month.july"/>', 
	               '<fmt:message key="calendar.month.august"/>',
	               '<fmt:message key="calendar.month.september"/>', 
	               '<fmt:message key="calendar.month.october"/>', 
	               '<fmt:message key="calendar.month.november"/>', 
	               '<fmt:message key="calendar.month.december"/>' 
	              ]
}
	var rateDetailListStr = '<s:property escape="false" value="rateDetailListStr"/>';
	if(rateDetailListStr != ""){
		rateDetailList = convertStrToJson(rateDetailListStr);
		showRateDetailList();
	}
	
	
	var roomRateList = new Array();
	<s:iterator value="configMap['roomTypeList']" var="rt" status="sta">
		var roomRate = new RoomRate();
		roomRate.roomTypeId = "${rt.roomTypeId}";
		roomRate.roomTypeName = "${rt.roomTypeName}";  
		roomRate.maxOccupancy = "${rt.maxOccupancy}";//最多入住人数
		roomRateList.push(roomRate);
	</s:iterator>
	//设置房型打包服务
	$('#roomPackageDiv .confirmthis').click(function(){
		var packageCode='';
		var packageName='';
		var idx = $('#roomType_idx').val();
		
		var roomRate = null;
		for(var i=0;i<roomRateList.length;i++){
			var roomTypeId = $("#roomTypeId").val();
			if(roomTypeId == roomRateList[i].roomTypeId){
				roomRate = roomRateList[i];
				break;
			}
		}
		var roomPackageList = new Array();
		$('#roomPackageDiv input:checked').each(function(){
			packageCode += $(this).val()+',';
			var roomPackage = new RoomPackage();
			roomPackage.packageId = $(this).attr("id");
			roomPackageList.push(roomPackage);
		});
		//roomRate.roomPackageList.push(roomPackage);
		roomRate.roomPackageList = roomPackageList;
		
		//alert(JSON.stringify(roomRate));
		//roomRateList.push(roomRate);
		//roomRateList.splice(idx,1,roomRate);
		
		$('#roomPackageDiv input:checked').next('span').each(function(){ 
			packageName += $(this).text()+',';
		});
		packageCode = packageCode.substr(0,packageCode.lastIndexOf(','));
		packageName = packageName.substr(0,packageName.lastIndexOf(','));
		
		
		$("#roomPackageId_"+idx).text(packageName);
		if(packageName != ""){
			$("#roomPackageId_"+idx).parent().next().hide();
		}else{
			$("#roomPackageId_"+idx).parent().next().show();
		}
		
		$('.packageClick .packageCode').text(packageCode);
		$('.packageClick .packageName').text(packageName);
		$('#roomPackageDiv').hide();
	});
	//打包服务弹出层
	$('.BSP_click').bind('click',function(){
		var roomTypeId = $(this).parents('tr').find(':checkbox').attr("id");
		var idx = $(this).parents('tr')[0].rowIndex - 1;	 //获取当前行，从数组中填充值
		$('#roomPackageDiv #roomTypeId').val(roomTypeId);
		$('#roomType_idx').val(idx);
		
		$('#roomPackageDiv input[type="checkbox"]').each(function(){ 
			$(this).prop("checked","");		//点击清空打包服务
		});
		var rrList = roomRateList[idx];
		if(rrList.roomPackageList != null){
			for(var i=0;i<rrList.roomPackageList.length;i++){
				$('#roomPackageDiv input[type="checkbox"]').each(function(){ 
					if($(this).attr("id") == rrList.roomPackageList[i].packageId){
						$(this).prop("checked","true");
					}
				});
			}
		}
		var l_ps=$(this).position().left;
		var t_ps=$(this).position().top+16;
		$('.priceBagService').slideDown();
		$('.priceBagService').css({left:l_ps,right:'',top:t_ps});
		});
	
	$('#btn_saveRateDetail').bind('click',function(){
		
		var rateDetail = new RateDetail();
		rateDetail.effectiveDate=$("#effectiveDate_rateDetail").val();
		rateDetail.expireDate=$("#expireDate_rateDetail").val();
		$('.date_abs input:checked').each(function(){
			var isApplyToWeek = $(this).attr("id");
			rateDetail[isApplyToWeek]='true';
		});
		rateDetail.extraBed=$("#extraBed").val();
		rateDetail.extraBedChild=$("#extraBedChild").val();
		var rateAmountList = new Array();
		$('.editprice_dt input[name="baseAmount"]').each(function(){
			var baseAmount = $(this).val();
			if(baseAmount != ''){
				var rateAmount = new RateAmount();
				var number = $(this).attr("numberOfUnits");
				rateAmount.numberOfUnits = number;
				rateAmount.baseAmount = baseAmount;
				rateAmountList.push(rateAmount);
			}
		});
		rateDetail.rateAmountList=rateAmountList;
		
		var checkedRoomRateList = new Array();
		var moreThanTwo=false;
		for(var i=0;i<roomRateList.length;i++){
			var isChecked = $("#"+roomRateList[i].roomTypeId).prop("checked");
			if(isChecked == true){
				checkedRoomRateList.push(roomRateList[i]);
				//判断是否包含最多人是否超过1
				var maxOccup=roomRateList[i].maxOccupancy;
				if(maxOccup!=null && maxOccup>1){
					moreThanTwo=true;
				}
			}
		}
		if(moreThanTwo){
			//判断是否包含二人价格
			var TwoAdultValue=$("#2AdultValue").val();
			if((TwoAdultValue==null )||(TwoAdultValue=='')||(TwoAdultValue=='null')||(TwoAdultValue.length<1) ){
				var isT=confirm('<fmt:message key="ccm.Rates.Message.2AdultConfirm"/>?');
				if(isT){
					return false;
				}
			}
			
		}
		
		if(checkedRoomRateList.length ==0){
			alert("<fmt:message key='ccm.Rates.ErrorMessage.SelectRoomType'/>！");
			return;
		}
		if(rateDetail.effectiveDate == '' || rateDetail.expireDate==''){
			alert("<fmt:message key='ccm.InventoryManagement.error.beginDateNull'/>！");
			return;
		}
		
		//校验起始时间和结束时间
		//开始时间
		var effectiveDateCode = validateYYYYMMDD(rateDetail.effectiveDate);
		if(effectiveDateCode!='success'){
			alert(getErrorMsg(effectiveDateCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
			return false;
		}else if(isMorethanNow(rateDetail.effectiveDate)=='false'){
			alert('开始时间不能小于当前时间');
			return false;
		}
		
		var expireDateCode = validateYYYYMMDD(rateDetail.expireDate);
		if(expireDateCode!='success'){
			alert(getErrorMsg(expireDateCode,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-DD'));
			return false;
		}else if(isMorethanNow(rateDetail.expireDate)=='false'){
			alert('结束时间不能小于当前时间');
			return false;
		}
		
		if(rateDetail.effectiveDate > rateDetail.expireDate){
			alert("结束时间不能小于开始时间！");
			return;
		}
		if(getWeekStr(rateDetail) ==''){
			alert("<fmt:message key='ccm.Rates.ErrorMessage.SelectAWeekAtLeast'/>！");
			return;
		}
		var baseAmount1 = $('.editprice_dt input[name="baseAmount"][numberofunits="1"]').val();
		if(baseAmount1 == ''){
			alert('<fmt:message key="ccm.Rates.ErrorMessage.1AdultIsRequired"/>！');
			return;
		}
		
		for(var i=0;i<rateAmountList.length;i++){
			var number = rateAmountList[i].numberOfUnits;
			if(number != i+1){
				alert('<fmt:message key="ccm.Rates.ErrorMessage.ContinuousAdultNumberRate"/>！');
				return;
			}
		}
		
		rateDetail.roomRateList=clone(checkedRoomRateList);
		var rateDetailIdx = $("#rateDetailIdx").val();
		
		if(validateRateDetail(clone(rateDetail),rateDetailIdx)){
			if(rateDetailIdx > -1){
				rateDetailList.splice(rateDetailIdx,1,clone(rateDetail));
			}else{
				rateDetailList.push(clone(rateDetail));
			}
			
			//$("#btn_saveRateDetail").addClass("no_ald");
			$.magnificPopup.close();
			//刷新列表
			showRateDetailListAndSetVal();
		}else{
			alert("<fmt:message key='ccm.Rates.ErrorMessage.PeriodAlreadyExisted'/>！");
		}
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true)
			  {
			  alert("<fmt:message key='ccm.Rates.ErrorMessage.IsDeleted'/>!");
			  }
			else
			  {
			  
			  }
		});
	
	$('.ccm_table1 a.link_1').bind('click',function(){
		$('#selectTB').show();
		$('#normalTB').hide();
		});
	$('.ccm_table1 a.focus_room').bind('click',function(){
		$('#selectTB').hide();
		$('#normalTB').show();
		});
		
	$('#BasicPriceChosen').change(function(){
		$('.basicPriceMethod').removeClass('visuallyhidden');
		});
	$('#BasicPricePercent,#BasicPriceNumber,#BasicPricePercent_New,#BasicPriceNumber_New').change(function(){
		$('.typeinwp').addClass('visuallyhidden');
		if($(this).is(':checked')){$(this).parent('label').next('.typeinwp').removeClass('visuallyhidden');}
		});
	$('.bothside .l_side :checkbox').bind('change',function(){
		if($(this).is(':checked')){
			$(this).parents('.l_side').next().show();
			}
		else{
			$(this).parents('.l_side').next().hide();
			}
		
		})
	$('#soldAddMothod_1').bind('click',function(){
		$(this).addClass('formula');
		$('#soldAddMothod_2').removeClass('formula');
		$('#soldNew_normal').show();
		$('#soldNew_formula').hide();
		
		});
	$('#soldAddMothod_2').bind('click',function(){
		$(this).addClass('formula');
		$('#soldAddMothod_1').removeClass('formula');
		$('#soldNew_normal').hide();
		$('#soldNew_formula').show();
		});
	
	$('.GR_click').bind('click',function(){
		var l_ps=$(this).position().left;
		var r_ps=873-l_ps;
		var t_ps=$(this).position().top+16;
		var t_ps2=$(this).position().top+24;
		$('.GuaranteeRulesNew').slideDown();
		if($(this).hasClass('blue')){
			$('.GuaranteeRulesNew').css({left:'',right:r_ps,top:t_ps2});
			}
		else{
			$('.GuaranteeRulesNew').css({left:l_ps,right:'',top:t_ps});
		}
		});
	$('.NR_click').bind('click',function(){
		var l_ps=$(this).position().left;
		var r_ps=873-l_ps;
		var t_ps=$(this).position().top+16;
		var t_ps2=$(this).position().top+24;
		$('.CancelRuleNew').slideDown();
		if($(this).hasClass('blue')){
			$('.CancelRuleNew').css({left:'',right:r_ps,top:t_ps2});
			}
		else{
			$('.CancelRuleNew').css({left:l_ps,right:'',top:t_ps});
		}
		
		});
	$('.SC_click').bind('click',function(){
		var l_ps=$(this).position().left;
		var r_ps=873-l_ps;
		var t_ps=$(this).position().top+16;
		var t_ps2=$(this).position().top+24;
		$('.SoldableConditonNew').slideDown();
		if($(this).hasClass('blue')){
			$('.SoldableConditonNew').css({left:'',right:r_ps,top:t_ps2});
		}
		else{
			$('.SoldableConditonNew').css({left:l_ps,right:'',top:t_ps});
		}
		});
	
	$('.nav li').bind('click',function(){
		$('.nav li').removeClass('active');
		$('#GuaranteeRules,#CancelRule,#SoldableConditon').hide()
		$(this).addClass('active');
		var li_1=$('.nav li:first-child').hasClass('active')
		var li_3=$('.nav li:last-child').hasClass('active')
		if(li_1){$('#GuaranteeRules').show()}
		else if(li_3){$('#SoldableConditon').show()}
		else{$('#CancelRule').show()}
		});
			
	RadioCheckedName('ccm_rdption1');
	RadioCheckedName('ccm_rdption2');
	RadioCheckedName('ccm_rdption3');
	RadioCheckedName('ccm_rdption4');
	
	
	$('#RoomTypeCheckAll').change(function(){
		var this_check=$(this).parents('.ccm_table2').find(':checkbox');
		var this_span=$(this).parents('.ccm_table2').find('td span.room');
		var this_link=$(this).parents('.ccm_table2').find('td .BSP_click');
		if($(this).is(':checked')){
			this_check.prop('checked',true);
			this_span.addClass('checked');
			this_link.show();
		}
		else{
			this_check.prop('checked',false);
			this_span.removeClass('checked');
			this_link.hide();
			}
		});
	
	$('.ccm_table2 td :checkbox').change(function(){
		var this_span=$(this).parents('tr').find('span.room');
		var this_link=$(this).parents('tr').find('.BSP_click');
		if($(this).is(':checked')){
			this_span.addClass('checked');
			this_link.removeClass('roomSelected').show();
		}
		else{
			this_span.removeClass('checked');
			this_link.hide();
			}
		});
	
	$('.ccm_click2').bind('click',function(){
		$(this).next('.ccm_click2_show').slideDown();
	});
	
	$('.ccm_click2_show .closethis').bind('click',function(){
		$(this).parents('.ccm_click2_show').hide();
	});
	$('.basicNew .closethis').bind('click',function(){
		$(this).parents('.basicNew').hide();
	});
	$('.priceBagService .closethis').bind('click',function(){
		$(this).parents('.priceBagService').hide();
	});
	
	//开始/<fmt:message key="common.time.EndDate"/>
	$("#effectiveDate_rateDetail").datepicker($.extend(dpconfig, {
		minDate : new Date(),
		onClose : function(v) {
			v = addHRToStr(v);
			$("#effectiveDate_rateDetail").val(v);
			//$("#expireDate_rateDetail").datepicker("option", "minDate", v);
		}
	}));
	$("#expireDate_rateDetail").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			v = addHRToStr(v);
			$("#expireDate_rateDetail").val(v);
			//$("#effectiveDate_rateDetail").datepicker("option", "maxDate",v);
		}
	}));
	$("#effectiveDate_splitRateDetail").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			v = addHRToStr(v);
			$("#effectiveDate_splitRateDetail").val(v);
			$("#expireDate_splitRateDetail").datepicker("option", "minDate", v);
		}
	}));
	$("#expireDate_splitRateDetail").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			v = addHRToStr(v);
			$("#expireDate_splitRateDetail").val(v);
			$("#effectiveDate_splitRateDetail").datepicker("option", "maxDate", v);
		}
	}));
});


function showRateDetailListAndSetVal(){
	$("#rateDetailListStr").val(JSON.stringify(rateDetailList));
	showRateDetailList();
}

function delRateDetail(idx){
	if(confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？")){
		rateDetailList.remove(idx);
		showRateDetailListAndSetVal();
	}
}
function getFirstPrice(rateAmountList){
	for(var j=0;j<rateAmountList.length;j++){
		if(rateAmountList[j].baseAmount != ''){
			return rateAmountList[j].baseAmount;
		}
	}
	return '';
}

function showRateAmount(thi){
	if(($(thi).attr("class").indexOf('hover')) == -1){
		$(thi).addClass('hover');
		$(thi).next().stop(true,true).slideDown();
	}else{
		$(thi).removeClass('hover');
		$(thi).next().hide();
	}
}
function showRateDetail(rateDetailIdx){
	$("#effectiveDate_rateDetail").val("");
	$("#expireDate_rateDetail").val("");
	//清空房型
	$('.roomRatePackage input:checked').each(function(){
		$(this).trigger("click");
	});
	//默认选中星期
	$('.dateweek input[type="checkbox"]').each(function(){
		$(this).prop("checked","true");
	});
	//清空价格
	$('.editprice_dt input[type="text"]').each(function(){
		$(this).val("");
	});
	$("#rateDetailIdx").val(rateDetailIdx);
	if(rateDetailIdx > -1){
		var rateDetail = rateDetailList[rateDetailIdx];
		for(var i=0;i<rateDetail.roomRateList.length;i++){
			var roomRate = rateDetail.roomRateList[i];
			//设置已有的房型选中
			$(".roomRatePackage #"+roomRate.roomTypeId).trigger("click");
			
			//设置已有的打包到临时 roomRateList
			//for(var k=0;k<roomRateList.length;k++){
			//	if(roomRate.roomTypeId == roomRateList[k].roomTypeId){
			//		roomRateList[k].roomRateId = roomRate.roomRateId;
			//		roomRateList[k].roomPackageList = roomRate.roomPackageList;
			//	}
			//}
			
			var idx = $(".roomRatePackage #"+roomRate.roomTypeId).parents('tr')[0].rowIndex - 1;	 //获取当前行
			//设置已有的打包名字
			var packageName="";
			$('#roomPackageDiv input[type="checkbox"]').each(function(){ 
				if(roomRate.roomPackageList != null){
					for(var j=0;j<roomRate.roomPackageList.length;j++){
						var pkId = roomRate.roomPackageList[j].packageId;
						if($(this).attr("id")==pkId){
							packageName += ","+$(this).next('span').text();
						}
					}
				}
			});
			$("#roomPackageId_"+idx).text(packageName.substr(1));
		}
		
		$("#effectiveDate_rateDetail").val(rateDetail.effectiveDate);
		$("#expireDate_rateDetail").val(rateDetail.expireDate);
		$('.dateweek input[type="checkbox"]').each(function(){
			var isApplyToWeek = $(this).attr("id");
			if(rateDetail[isApplyToWeek]){
				$(this).prop("checked","true");
			}else{
				$(this).prop("checked","");
			}
		});
		$("#extraBed").val(rateDetail.extraBed);
		$("#extraBedChild").val(rateDetail.extraBedChild);
		
		for(var i=0;i<rateDetail.rateAmountList.length;i++){
			var rateAmount = rateDetail.rateAmountList[i];
			var rateAmountInput = $('.editprice_dt input[numberOfUnits="'+rateAmount.numberOfUnits+'"]');
			rateAmountInput.val(rateAmount.baseAmount);
			rateAmountInput.attr("rateAmountId",rateAmount.rateAmountId);
		}
	}
}

function validateRateDetail(rateDetailVali,idx){
	for(var i=0;i<rateDetailList.length;i++){
		var rateDetail = rateDetailList[i];
		if(i==idx){
			continue;
		}
		var roomRatelist = rateDetail.roomRateList;
		var roomRatelistVali = rateDetailVali.roomRateList;
		var isFindRoomType=false;
		for(var z=0;z<roomRatelist.length;z++){
			for(var j=0;j<roomRatelistVali.length;j++){
        		if(roomRatelist[z].roomTypeId == roomRatelistVali[j].roomTypeId){
        			isFindRoomType = true;
                    break;
        		}
        	}
        }
        
        if(isFindRoomType){
            if((rateDetail.effectiveDate <= rateDetailVali.effectiveDate && rateDetail.expireDate >= rateDetailVali.effectiveDate ) 
            		|| (rateDetail.effectiveDate <= rateDetailVali.expireDate && rateDetail.expireDate >= rateDetailVali.expireDate)
            		|| (rateDetail.effectiveDate > rateDetailVali.effectiveDate && rateDetail.expireDate < rateDetailVali.expireDate)){
                var weeks = getWeekStr(rateDetail).split(',');
                var weeksValidate = getWeekStr(rateDetailVali).split(',');
                for(var z=0;z<weeks.length;z++){
                	for(var j=0;j<weeksValidate.length;j++){
                        if(weeks[z] == weeksValidate[j]){
                            return false;
                        }
                    }
                }
            }
        }
	}
	return true;
}
function saveSplitRateDetail(){
	var idx = $("#splitRateDetailIdx").val();
	var rateDetail = rateDetailList[idx];
	var splitStart = $("#effectiveDate_splitRateDetail").val();
	var splitEnd = $("#expireDate_splitRateDetail").val();
	var extraBed=$("#extraBed").val();
	var extraBedChild=$("#extraBedChild").val();
	var rateAmountList = new Array();
	$('.splitRateDetailPrice input[name="baseAmount"]').each(function(){
		var baseAmount = $(this).val();
		if(baseAmount != ''){
			var rateAmount = new RateAmount();
			var number = $(this).attr("numberOfUnits");
			var rateAmountId = $(this).attr("rateAmountId");
			rateAmount.numberOfUnits = number;
			rateAmount.baseAmount = baseAmount;
			rateAmount.rateAmountId = rateAmountId;
			rateAmountList.push(rateAmount);
		}
	});
	
	var start = rateDetail.effectiveDate;
	var end =  rateDetail.expireDate;
	
	if(splitStart == '' || splitEnd==''){
		alert("<fmt:message key='ccm.InventoryManagement.error.beginDateNull'/>！");
		return;
	}
	
	//校验起始时间和结束时间
	var splitStartCode = validateYYYYMMDD(splitStart);
	if(splitStartCode!='success'){
		alert(getErrorMsg(splitStartCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
		return false;
	}else if(isMorethanNow(splitStart)=='false'){
		alert('开始时间不能小于当前时间');
		return false;
	}
	
	var splitEndCode = validateYYYYMMDD(splitEnd);
	if(splitEndCode!='success'){
		alert(getErrorMsg(splitEndCode,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-DD'));
		return false;
	}else if(isMorethanNow(splitEnd)=='false'){
		alert('结束时间不能小于当前时间');
		return false;
	}
	
	if((splitStart < start) || (splitEnd > end)){
		var arry = new Array();
		arry.push(rateDetail.effectiveDate);
		arry.push(rateDetail.expireDate);
		var str = '<fmt:message key="ccm.Rates.ErrorMessage.StartDateAndEndDate"/>';
		alert(i18n_replace(str,arry));
		//alert('开始、结束日期必须在'+rateDetail.effectiveDate+'~'+rateDetail.expireDate+'内！');
		return;
	}
	if(rateAmountList.length == 0){
		alert("<fmt:message key='ccm.Rates.ErrorMessage.SetUpARateAtLeast'/>！");
		return;
	}
	var rateDetail1 = null;
	var rateDetail2 = null;
	var rateDetail3 = null;
	if(splitStart != start){
		var rateDetail1 = clone(rateDetail);
		var d1 = new Date(splitStart.replace(/-/g,"/"));
		d1 = d1.setDate(d1.getDate()-1);		//减1天
		d1 = new Date(d1).format("yyyy-MM-dd");
		rateDetail1.expireDate = d1;	
		
		var rateDetail2 = clone(rateDetail);
		rateDetail2.rateDetailId=null;
		rateDetail2.effectiveDate = splitStart;
		rateDetail2.expireDate = splitEnd;
		rateDetail2.rateAmountList=rateAmountList;
		rateDetail2.extraBed=extraBed;
		rateDetail2.extraBedChild=extraBedChild;
		
		for(var i=0;i<rateDetail2.rateAmountList.length;i++){
			rateDetail2.rateAmountList[i].rateAmountId=null;
		}
		for(var i=0;i<rateDetail2.roomRateList.length;i++){
			rateDetail2.roomRateList[i].roomRateId=null;
		}
	}else{
		var rateDetail1 = clone(rateDetail);
		rateDetail1.expireDate = splitEnd;
		rateDetail1.rateAmountList=rateAmountList;
		rateDetail1.extraBed=extraBed;
		rateDetail1.extraBedChild=extraBedChild;
	}
	
	if(splitEnd != end){
		var rateDetail3 = clone(rateDetail);
		rateDetail3.rateDetailId=null;
		var d2 = new Date(splitEnd.replace(/-/g,"/"));
		d2 = d2.setDate(d2.getDate()+1);			//加1天
		d2 = new Date(d2).format("yyyy-MM-dd");
		rateDetail3.effectiveDate = d2;
		rateDetail3.expireDate = end;
		for(var i=0;i<rateDetail3.rateAmountList.length;i++){
			rateDetail3.rateAmountList[i].rateAmountId=null;
		}
		for(var i=0;i<rateDetail3.roomRateList.length;i++){
			rateDetail3.roomRateList[i].roomRateId=null;
		}
	}

	rateDetailList.splice(idx,1,rateDetail1);
	if(rateDetail2 != null){
		rateDetailList.push(rateDetail2);
	}
	if(rateDetail3 != null){
		rateDetailList.push(rateDetail3);
	}
	
	
	
	$.magnificPopup.close();
	//刷新列表
	showRateDetailListAndSetVal();
}
function splitRateDetail(idx){
	$("#effectiveDate_splitRateDetail").val("");
	$("#expireDate_splitRateDetail").val("");
	//清空价格
	$('.splitRateDetailPrice input[type="text"]').each(function(){
		$(this).val("");
	});
	$("#splitRateDetailIdx").val(idx);
	var rateDetail = rateDetailList[idx];
//	$("#expireDate_splitRateDetail").datepicker("option", "minDate", rateDetail.expireDate);
//	$("#effectiveDate_splitRateDetail").datepicker("option", "maxDate", rateDetail.effectiveDate);
}

function convertStrToJson(str){
	return eval("("+str+")");
}
function getWeekStr(oneJson){
	var weekStr = "";
	if(oneJson.isApplyToMonday){
		weekStr = ",<fmt:message key='common.weeks.monday'/>";
	}
	if(oneJson.isApplyToTuesday){
		weekStr += ",<fmt:message key='common.weeks.tuesday'/>";
	}
	if(oneJson.isApplyToWednesday){
		weekStr += ",<fmt:message key='common.weeks.wednesday'/>";
	}
	if(oneJson.isApplyToThursday){
		weekStr += ",<fmt:message key='common.weeks.thursday'/>";
	}
	if(oneJson.isApplyToFriday){
		weekStr += ",<fmt:message key='common.weeks.friday'/>";
	}
	if(oneJson.isApplyToSaturday){
		weekStr += ",<fmt:message key='common.weeks.saturday'/>";
	}
	if(oneJson.isApplyToSunday){
		weekStr += ",<fmt:message key='common.weeks.sunday'/>";
	}
	return weekStr.substr(1);;
}
</script>

   