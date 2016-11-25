<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="CCMmainConter w1200">
  <div class="title_wp"> 
    <!-- <div class="bt"> <a href="0HOTEL-2.html" class="btn_2 blue"><fmt:message key="common.button.New"/></a> </div>--> 
    <fmt:message key="ccm.Rates.RatesManagment"/></div>
  
  <!--左右两列-->
  <div class="ccm_2wp clearfix">
    <div class="ccm_left" style="position:relative;">
      <div class="menulayerMask"></div>
      <div class="lt_menu2">
        <ul class="mlist">
        </ul>
        <div class="newwp">
          <button type="button" class="btn_2 blue"><fmt:message key="ccm.Rates.RateSetupNew"/></button>
          <a href="#BatchEditCondition" class="ccm-popup-click"></a> </div>
      </div>
    </div>
   
<div class="ccm_2col">
      <div class="ccm_right"> 

        <div class="step_wp">
            <ul class="step3">
            	<li class="active"><span>1.</span><fmt:message key="ccm.Rates.RateHeader"/></li>
                <li><span>2.</span><fmt:message key="ccm.Rates.RateDetail"/></li>
                <li class="noline"><span>3.</span><fmt:message key="ccm.Rates.OK"/></li>
            </ul>
            </div>
       <form action="/roomRate_next.do" id="rateForm" method="post">
       <appfuse:ccmToken name="token"></appfuse:ccmToken>
        <!--房价基本信息-->
         <div class="pdA24">
                <div class="r_basic1">
                  <div class="r_basic2 mgR18">
                    <ul class="inq_wp">
                      <li>
                        <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.RestrictionsManagement.RateCode"/>：</span></div>
                        <div class="i_input">
                        	<select id="f_ratePlanTemplateCode" class="fxt w180 required" name="rp.ratePlanCode">
                        		<option value=""><fmt:message key="common.select.plesesSelect"/></option>
                        		<c:forEach var="ratePlanTemplate" items="${configMap['ratePlanTempList']}" >
                        			<option value="${ratePlanTemplate.ratePlanTemplateCode}"> ${ratePlanTemplate.ratePlanTemplateCode}</option>
                        		</c:forEach>
                        	</select>
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.RateCategory"/>：</span></div>
                        <div class="i_input">
                        	<s:select list="configMap['rpt']" name="rp.ratePlanType" listValue="codeNo+'-'+codeLabel" listKey="codeNo" cssClass="fxt w180">
                        	</s:select>
                        </div>
                      </li>
                    </ul>
                  </div>
                  <div class="r_basic2">
                    <ul class="inq_wp">
                      <li>
                        <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.Rates.BeginDate"/>：</span></div>
                        <div class="i_input">
                          <input class="fxt w120 required"   name="rp.effectiveDate" type="text" id="effectiveDate_rate" value="" title="">
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="common.time.EndDate"/>：</span></div>
                        <div class="i_input">
                          <input class="fxt w120 required" type="text" name="rp.expireDate" id="expireDate_rate" value="" title="">
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <div class="r_basic1">
                  <div class="r_basic2 mgR18">
                    <ul class="inq_wp">
                      <li>
                      	<div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.DescriptionCorporate"/>：</span></div>
                        <div class="i_input">
                        	<input	class="fxt w240" name="rpi18n.description"	readonly="readonly" type="hidden"/>
                        	<input	class="fxt w240" name="show_description" readonly="readonly" 
                        		value="${ratePlanName}"/>
                        	<s:hidden name="languageJsonArr"></s:hidden>
                        	&nbsp;<button type="button" class="btn_3 white mgR6 " id="switch_description"><fmt:message key="ccm.Rates.MultiLanguage"/></button>
                        	<div class="ft_layer basicNew DefaultLanguageNew" style="z-index:12;" id="ml_switch_defaultDesc">
                				<textarea  class="fxt w491 h150 " id="f_description"
											name="d_description" >${ratePlanI18n.description}</textarea>
								<div class="ft_ctr1">
									<button type="button" class="btn_3 green mgR6"><fmt:message key="common.button.OK"/></button>
	        						<button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>	
								</div>
                			</div>
                        	<div class="ft_layer basicNew DescMoreLanguageNew" style="z-index:10;" id="ml_switch_description">
                				<table class="ccm_table2" >
									<tr id="mdl_switch_description" style="display:none;">  
										<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
											<option value=""><fmt:message key="common.select.plesesSelect"/></option>
											<c:forEach items="${configMap['languageList']}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
											</c:forEach>
										</select> <br>
										<textarea  class="fxt w491 h150" style="margin-top:5px;margin-bottom:5px;" 
													name="language.description" ></textarea>
										</td>
										<td class="w20">
											<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_description');" class="link_1 del_ifself">x</a>
											</div>
										</td>		
									</tr>		
									<tr>
										<td class="w20">&nbsp;</td>
										<td><a href="javascript:void[0];" class="link" onclick="addHtmlLanguage(this,'switch_description','language.description')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/>	</span>
										</td>
										<td class="w20">&nbsp;</td>
									</tr>
								</table>
								<div class="ft_ctr1" >
									<button type="button" class="btn_3 green mgR6"><fmt:message key="common.button.OK"/></button>
	        						<button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>	
								</div>
                			</div>
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.InventoryManagement.RoomTypes"/>：</span></div>
                        <div class="i_input c_rel" style="z-index:4"> <!--1 S-->
                          <div class="moreoption rommTypeClick">
                            <div class="opts">
                            	<div class="text w240 typeCode" style="display: none;"></div>
                  				<div class="text w240 typeName"><fmt:message key="ccm.Rates.AllRoomType"/></div>
                            </div>
                          </div>
                          <!-- 房型 -->
                          <div class="ft_layer abs" id="roomTypeCodeDiv" style="width:399px;">
                            <div class=" n_overFlowY">
                              <div class="mgA6">
                                <c:forEach items="${configMap['roomTypeList'] }" var="rl" varStatus="idx">
				                    <label class="checkbox">
				                      <input type="checkbox" checked="checked" id="roomType_${idx.index }" value="${rl.roomTypeCode }" >
				                      <span class="">${rl.roomTypeCode }&nbsp;${rl.roomTypeName }</span> 
				                    </label>
				                  </c:forEach>
                              </div>
                            </div>
                            <s:hidden name="rp.includeRoomType" id="roomTypeCode"></s:hidden>
                            <div class="ft_ctr1">
                              <button type="button" class="btn_3 green confirmthis"><fmt:message key="common.button.OK"/></button>
                              <button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>
                            </div>
                          </div>
                          <!--1 E--> 
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Package"/>：</span></div>
                        <div class="i_input c_rel" style="z-index:3"><!--1 S-->
                          <div class="moreoption packageClick">
                            <div class="opts">
                              <div class="text w240 packageCode" style="display: none;"></div>
                  				<div class="text w240 packageName"></div>
                            </div>
                          </div>
                          <div class="ft_layer abs packageDiv" id="packageDiv" style="width:399px;">
                            <div class=" n_overFlowY">
                              <div class="mgA6">
                              <c:forEach items="${configMap['packages']}" var="pk" varStatus="idxs">
                              		<label class="checkbox">
				                      <input type="checkbox" id="${pk.packageId }" value="${pk.packageCode}" >
				                      <span class="">${pk.packageCode}&nbsp;${pk.packageName }</span>
				                    </label>
                               </c:forEach>
                              </div>
                            </div>
                            <s:hidden name="packageId"></s:hidden>
                            <div class="ft_ctr1">
                              <button type="button" class="btn_3 green confirmthis"><fmt:message key="common.button.OK"/></button>
                              <button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>
                            </div>
                          </div>
                          <!--1 E--> 
                        </div>
                      </li>
                    </ul>
                  </div>
                  <div class="r_basic2 ">
                    <ul class="inq_wp ">
                      <li>
                      	<div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.RateCode"/>：</span></div>
                        <div class="i_input">
                        	<s:textfield cssClass="fxt w240" name="rpi18n.ratePlanName"></s:textfield>
                        	&nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_ratePlanName"><fmt:message key="ccm.Rates.MultiLanguage"/></button>
                        	<div class="ft_layer basicNew NameMoreLanguageNew" style="z-index:10;" id="ml_switch_ratePlanName">
                				<table class="ccm_table2" style="width: 480px;">
									<tr id="mdl_switch_ratePlanName" style="display:none;">  
										<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
											<option value=""><fmt:message key="common.select.plesesSelect"/></option>
											<c:forEach items="${configMap['languageList']}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
											</c:forEach>
										</select> &nbsp;&nbsp;
										<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" />
										</td>
										<td class="w20">
											<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_ratePlanName');" class="link_1 del_ifself">x</a>
											</div>
										</td>		
									</tr>		
									<tr>
										<td class="w20">&nbsp;</td>
										<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_ratePlanName')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/>	</span>
										</td>
										<td class="w20">&nbsp;</td>
									</tr>
								</table>
								<div class="ft_ctr1">
									<button type="button" class="btn_3 green mgR6"><fmt:message key="common.button.OK"/></button>
	        						<button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>	
								</div>
                			</div>
                        </div>
                      </li>
                      <li style="margin-top:15px;">
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Market"/>：</span></div>
                        <div class="i_input">
                        <s:select cssClass="fxt" cssStyle="width:280px;" list="configMap['MarketVOList']" name="rp.marketCode" listKey="marketCode" listValue="marketCode+'-'+description">
                        </s:select>
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Source"/>：</span></div>
                        <div class="i_input"> 
                         <s:select cssClass="fxt" cssStyle="width:280px;" list="configMap['sourceVOList']" name="rp.sourceCode" listKey="sourceCode" listValue="description">
                        </s:select>
                        </div>
                      </li>
             <%--         <li>
                        <div class="i_title"><span class=""></span><span class="text">服务费：</span></div>
                        <div class="i_input">
                          <select class="fxt" style="width:280px;">
                          	<option></option>
                            <option>含服务费</option>
                            <option selected="selected">不含服务费</option>
                            <option>含服务费，须扣除</option>
                          </select>
                        </div>
                      </li>
                  --%>   </ul>
                  </div>
                </div>
                <div class="r_basic1">
                  <div class="r_basic2 mgR18">
                    <ul class="inq_wp">
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.DisplaySequence"/>：</span></div>
                        <div class="i_input">
                        	<s:textfield cssClass="fxt w60" name="rp.orderNum"></s:textfield>
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Negotiated"/>：</span></div>
                        <div class="i_input">
                          <label class="radio inline">
                            <input type="radio" name="rp.isNegotiated"  value="true" >
                            <span class=""><fmt:message key="common.Yes"/>	</span> </label>
                          <label class="radio inline">
                            <input type="radio" name="rp.isNegotiated"  value="false">
                            <span class=""><fmt:message key="common.Not"/>	</span> </label>
                        </div>
                      </li>
                    </ul>
                  </div>
                  <div class="r_basic2">
                    <ul class="inq_wp">
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Commission"/>	%：</span></div>
                        <div class="i_input">
                        	<s:textfield cssClass="fxt w60" name="rp.commissionCode"></s:textfield>
                        </div>
                        <%--
                        <div class="i_title"><span class=""></span><span class="text">佣金代码：</span></div>
                        <div class="i_input">
                        	<s:textfield cssClass="fxt w60" name="rp.commisionPercent"></s:textfield>
                        </div>
                         --%>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.ExternalControl"/>：</span></div>
                        <div class="i_input">
                          <label class="radio inline">
                            <input type="radio" name="rp.isExtraControl" id="optionsRadios_xy1" value="true">
                            <span class=""><fmt:message key="common.Yes"/>	</span> </label>
                          <label class="radio inline">
                            <input type="radio" name="rp.isExtraControl" id="optionsRadios_xy2" value="false">
                            <span class=""><fmt:message key="common.Not"/>	</span> </label>
                        </div>
                      </li>
                    </ul>
                  </div>
                  
                <div class="r_basic2 mgR18">
                   <ul class="inq_wp">
					<li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.IsSuper"/>：</span></div>
                        <div class="i_input">
                          <label class="radio inline">
                            <input type="radio" name="rp.isSuper" <s:if test="rp.isSuper==true"> checked="checked"</s:if>  value="true">
                            <span class=""><fmt:message key="common.Yes"/></span> </label>
                          <label class="radio inline">
                            <input type="radio" name="rp.isSuper" <s:if test="rp.isSuper==false"> checked="checked"</s:if> value="false">
                            <span class=""><fmt:message key="common.Not"/></span> </label>
                        </div>
                      </li>
                    </ul>
                  </div>
                  
                  <div class="r_basic2">
                   <ul class="inq_wp">
					<li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.PriceValidate"/>：</span></div>
                        <div class="i_input">
                          <label class="radio inline">
                            <input type="radio" name="rp.priceValidate" <s:if test="rp.priceValidate==true"> checked="checked"</s:if>  value="true">
                            <span class=""><fmt:message key="common.Yes"/></span> </label>
                          <label class="radio inline">
                            <input type="radio" name="rp.priceValidate" <s:if test="rp.priceValidate==false"> checked="checked"</s:if> value="false">
                            <span class=""><fmt:message key="common.Not"/></span> </label>
                        </div>
                      </li>
                    </ul>
                  </div>
                  
                <div class="r_basic2 mgR18">
                    <ul class="inq_wp">
                      <li>
                        <div class="i_title"><span class=""></span><span class="text">AccessCode：</span></div>
                        <div class="i_input" style="position:relative;">
                        	<div class="moreoption" id="Two_click">
							<div class="opts">
								<div class="text w240 typeName"><fmt:message key="common.select.plesesSelect"/></div>
							</div>
							</div>
							<!--AccessCode查看隐藏层-->
							<div id="Two_show" class="ft_layer abs" style="width: 278px;">
								<div class=" n_overFlowY">
									<div class="mgA6">
										<c:forEach items="${configMap['accessCodeList']}" var="rl" varStatus="idx">
											<label class="checkbox"> <input type="checkbox" id="roomType_${idx.index }" value="${rl.customId}" name="ratePlanVO.customList"> <span class=""> <span class="span_roomTypeCode">${rl.accessCode}</span>  </span> </label>
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
                  </div>
					<!-- 付款方式 -->
					<div class="r_basic2">
						<ul class="inq_wp">
							<li>
								<div class="i_title">
									<span class=""></span><span class="text"><fmt:message key='ccm.Rates.Payment' />：</span>
								</div>
								<div class="i_input">
									<s:textfield cssClass="fxt w240" name="rp.payment" maxlength="100"></s:textfield>
								</div>
							</li>
						</ul>
					</div>
				</div>
                
                <div class="r_basic1">
                  <div class="nav_wp" style="margin-bottom:6px;">
                        <ul class="nav nav-tabs" style="margin-bottom:0;">
                              <li class="active"><a href="#"><fmt:message key="ccm.Rates.GuaranteeType"/></a></li>
                              <li><a href="#"><fmt:message key="ccm.Rates.CancellationType"/></a></li>
                              <li><a href="#"> <fmt:message key="ccm.Rates.SellControls"/></a></li>
                        </ul>
                  </div>
                  <div class="pdL6 pdR6">
                 <!-- 担保规则-->
                  	<div class="table2wp" id="GuaranteeRules">
                    	<div class="subtitle frow">
                        	<div class="float-right"><a href="javascript:;" class="btn_3 blue GR_click"><fmt:message key="common.button.New"/></a></div>
                        </div>
                        <div class="n_overFlowY">
                    	<table class="ccm_table2" id="guaranteeTable">
                        </table>
                        </div>
                    </div>
                    
                 <!-- 取消规则-->
                 	<s:hidden name="cancelRuleJsonArr"></s:hidden>
                  	<div class="table2wp" id="CancelRule" style="display:none;">
                    	<div class="subtitle frow">
                        	<div class="float-right"><a href="javascript:;" class="btn_3 blue NR_click"><fmt:message key="common.button.New"/></a></div>
                        </div>
                        <div class="n_overFlowY">
                    	<table class="ccm_table2" id="cancelRuleTable">
                        </table>
                        </div>
                    
                    </div>
                    
                 <!-- 可卖条件-->
                 
                 <!-- 可卖条件-->
                 <s:hidden name="rp.soldableCondition" id="soldableConditionJsonArr"></s:hidden>
                  	<div class="table2wp" id="SoldableCondition" style="display:none;">
                    	<div class="subtitle frow">
                        	<div class="float-right"><a href="javascript:;" class="btn_3 blue SC_click"><fmt:message key="common.button.New"/></a></div>
                            <span class="TB3"><fmt:message key="ccm.Rates.MeetOneConditions"/></span>
                        </div>
                        <div class="n_overFlowY">
                    	<table class="ccm_table2" id="soldableConditionTab">
                        </table>
                        </div>
                    </div>
                  </div>
                </div>
                 
<%--                  <s:hidden name="rp.soldableCondition" id="soldableConditionJsonArr"></s:hidden> --%>
<!--                   	<div class="table2wp" id="SoldableCondition" style="display:none;"> -->
<!--                     	<div class="subtitle frow"> -->
<!--                         	<div class="float-right"><a href="javascript:;" class="btn_3 blue SC_click"><fmt:message key="common.button.New"/></a></div> -->
<!--                             <span class="TB3"><fmt:message key="ccm.Rates.MeetOneConditions"/></span> -->
<!--                         </div> -->
<!--                         <div class="n_overFlowY"> -->
<!--                     	<table class="ccm_table2" id="soldableConditionTab"> -->
<!--                         </table> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 </div> -->
              <s:hidden name="guaranteeJsonArr"></s:hidden>
              <!--房价基本信息 新建 担保规则-->
          <div class="ft_layer basicNew GuaranteeRulesNew" style="width:600px;">
          <div class="s_title"><fmt:message key="ccm.Rates.GuaranteeType"/></div>
          	<div class="pdA24">
                <ul class="list_input">
                  <li>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.GuaranteeType"/>：</span></div>
                    <div class="i_input">
                    <s:select cssClass="fxt w180" list="configMap['guaranteePolicyVOList']" name="guaranteeId" listKey="guaranteeId" listValue="guaranteeCode">
                    </s:select>
                    </div>
                  </li>
                  <li class="c_rel">
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.BeginDate"/>	：</span></div>
                    <div class="i_input">
                      <input class="fxt w120" name="effectiveDate" id="effectiveDate_guarantee" type="text" value="" >
                    </div>
                    <div class="date_abs">
                      <div class="dateweek">
                        <div class="d_wp"> <span><fmt:message key="common.week.sunday"/>	</span>
                          <input type="checkbox" id="isApplyToSunday" value="true" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.monday"/>	</span>
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
                      <input class="fxt w120" name="expireDate" id="expireDate_guarantee" type="text" value="" >
                    </div>
                  </li>
                </ul>
              </div>
        <div class="ft_ctr1">
	        <button type="button" class="btn_3 green mgR6"><fmt:message key="common.button.OK"/></button>
	        <button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>
        </div>
        </div>
          
          <!--房价基本信息 新建 取消规则-->
          <div class="ft_layer basicNew CancelRuleNew" style="width:600px;">
          <div class="s_title"><fmt:message key="ccm.Rates.CancellationType"/></div>
          	<div class="pdA24">
                <ul class="list_input">
                  <li>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.CancellationType"/>：</span></div>
                    <div class="i_input">
                      <s:select cssClass="fxt w180" list="configMap['cancelPolicyVOList']" name="cancelId" listKey="cancelId" listValue="cancelPolicyCode">
                    </s:select>
                    </div>
                  </li>
                  <li class="c_rel">
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.BeginDate"/>	：</span></div>
                    <div class="i_input">
                      <input class="fxt w120" name="effectiveDate" id="effectiveDate_cancel" type="text" value="" >
                    </div>
                    <div class="date_abs">
                      <div class="dateweek">
                        <div class="d_wp"> <span><fmt:message key="common.week.sunday"/>	</span>
                          <input type="checkbox" id="isApplyToSunday" value="true" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.monday"/>	</span>
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
                      <input class="fxt w120" name="expireDate" id="expireDate_cancel" type="text" value="" >
                    </div>
                  </li>
                </ul>
              </div>
        <div class="ft_ctr1"><button type="button" class="btn_3 green mgR6"><fmt:message key="common.button.OK"/></button><button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button></div>
        </div>
          
          <!--房价基本信息 新建 可卖条件-->
          <div class="ft_layer basicNew SoldableConditionNew" style="width:600px;z-index:2000;">
          <div class="s_title">
          <div class="float-right">
<!--          <a id="soldAddMothod_1" href="javascript:;" class="link mgR12  formula">常规</a>
           <a id="soldAddMothod_2" href="javascript:;" class="link ">公式计算器</a> -->
          </div>
          <fmt:message key="ccm.Rates.SellControls"/></div>
          	<div class="pdA24">
                <div id="soldNew_normal">
                <ul class="bothside">
                <li>
                	<div class="l_side"><label class="checkbox inline"><span class=""><fmt:message key='ccm.Rates.MinimumStayThrough'/></span></label></div>
                    <div style="float: left;"><input class="fxt w30" name="minEvenDay" type="text" value="" > <fmt:message key="common.Day"/></div>
                    <div class="l_side"><label class="checkbox inline"><span class="">&nbsp;&nbsp;<fmt:message key='ccm.Rates.MaximumStayThrough'/></span></label></div>
                    <div style="float: left;"><input class="fxt w30" name="maxEvenDay" type="text" value="" > <fmt:message key="common.Day"/></div>
                </li>
                <li>
                	<div class="l_side"><label class="checkbox inline"><span class=""><fmt:message key='ccm.Rates.MinimumAdvanceBooking'/></span></label></div>
                    <div style="float: left;"><input class="fxt w30" name="minBeforehandDay" type="text" value="" > <fmt:message key="common.Day"/></div>
                    <div class="l_side"><label class="checkbox inline"><span class="">&nbsp;&nbsp;<fmt:message key='ccm.Rates.MaximumAdvanceBooking'/></span></label></div>
                    <div style="float: left;"><input class="fxt w30" name="maxBeforehandDay" type="text" value="" > <fmt:message key="common.Day"/></div>
                </li>
                <li>
                	<div class="l_side"><label class="checkbox inline"><span class=""><fmt:message key='ccm.Rates.StartEndDate'/></span></label></div>
                    <div style="float: left;">
                    	<div class="bk">
                    		<input class="fxt w40 calendar removehasDatepicker" readonly="readonly" name="startDate" type="text" value="" id="startDate"> 
                    		— <input class="fxt w40 calendar removehasDatepicker" readonly="readonly" name="endDate" type="text" value="" id="endDate">
                    	</div>
                    </div>
                </li>
                <%--
                <li>
                	<div class="l_side"><label class="checkbox inline"><span class=""><fmt:message key='ccm.Rates.FlashSale'/></span></label></div>
                    <div style="float: left;">
                    	<div class="bk"><input class="fxt w120 calendar" type="text" value="" name="beginTime" id="beginTime2" >
                    	— <input class="fxt w120 calendar" type="text" value="" name="endTime" id="endTime2"  ></div>
                        <div class="bk">

                      <label class="radio inline" style="padding-right:6px;">
                        <input type="radio" name="ccm_rdption4" id="BasicPricePercent_New" value="option1">
                        <span class=""><fmt:message key='common.Percentage'/></span> </label>
                      <span class="typeinwp visuallyhidden" id="lab_rp_percent">
                      <input class="fxt w60" type="text" value="" name="percent" title="">
                      %</span>
                      <label class="radio inline" style="padding-right:6px; padding-left:24px;">
                        <input type="radio" name="ccm_rdption4" id="BasicPriceNumber_New" value="option1">
                        <span class=""><fmt:message key='common.FixedAmount'/></span> </label>
                      <span class="typeinwp visuallyhidden" id="lab_rp_amount">
                      <input class="fxt w60" type="text" value="" name="amount" title=""></span> 
                        </div>
                    
                    </div>
                </li> 
                <li>
                	<div class="l_side"><label class="checkbox inline"><span class=""><fmt:message key='ccm.Rates.LimitedPurchase'/></span></label></div>
                    <div style="float: left;"><input class="fxt w30" type="text" name="limitBuy" value="" > <fmt:message key='ccm.Rates.Room'/>
                    <input name="soldNum" type="hidden"></div>
<!--                 <li> -->
<!--                 	<div class="l_side"><label class="checkbox inline"><span class="">预订时间</label></div> -->
<!--                     <div style="float: left;"><select class="fxt w80" name="bookTime"><option value=""></option><option value="18">18</option> <option  value="19">19</option><option value="20">20</option></select>点之前</div> -->
<!--                 </li> -->
				</li>
	--%>
                </ul>
               </div> 
                <div id="soldNew_formula" style="display:none;">
                <textarea rows="8" class="fxt w480"></textarea>
                </div>
              </div>
        <div class="ft_ctr1"><button type="button" class="btn_3 green mgR6"><fmt:message key="common.button.OK"/></button><button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button></div>
        </div>
        
              <div class="r_basic1">
                  <ul class="inq_wp">
                    <li>
                      <div class="i_title"><span class=""></span><span class="text"><fmt:message key='ccm.Rates.BaseRate'/>：</span></div>
                      <div class="i_input">
                      <s:select list="noInheritRateMapList" cssClass="fxt w300"  id="BasicPriceChosen" 
                      	value=""  name="rp.inheritRatePlanId" listKey="ratePlanId" 
                      	listValue="ratePlanCode" headerKey="" headerValue="" ></s:select>
                      </div>
                      <span class="basicPriceMethod">
                      <label class="radio inline" style="padding-right:6px; padding-left:24px;">
                        <input type="radio" name="ccm_rdption3" id="BasicPricePercent" value="option1">
                        <span class=""><fmt:message key='common.Percentage'/></span> </label>
                      	<span class="">
                      		<s:textfield name="rp.percent" cssClass="fxt w60" disabled="true"></s:textfield> 
                      	%</span>
                      <label class="radio inline" style="padding-right:6px; padding-left:24px;">
                        <input type="radio" name="ccm_rdption3" id="BasicPriceNumber" value="option1" ${rp.amount != null ? 'checked="checked"' : '' }>
                        <span class=""><fmt:message key='common.FixedAmount'/></span> </label>
                        	<s:textfield name="rp.amount" cssClass="fxt w60" disabled="true"></s:textfield>
                     </span> </li>
                  </ul>
                </div>
              
              </form>
     <!-- 上一步下一步控制--> 
     <div class="mgT24">
      <hr class="dashed">
     </div>
      <div class="toright">
         <div class="float-left"><a href="/roomRate_cancel.do" class="btn_1 red"><fmt:message key='ccm.Rates.Close'/></a></div>
          <a href="javascript:subForm();" class="btn_1 green" id="oKAndNext"><fmt:message key='ccm.Rates.OKAndNext'/></a>
      </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="<c:url value='js/jHtmlArea-0.8.js'/>"></script>
<link rel="Stylesheet" type="text/css" href="<c:url value='css/jHtmlArea.css'/>" />
<script type="text/javascript" src="<c:url value='js/jHtmlArea.ColorPickerMenu-0.8.js'/>"></script>
<link rel="Stylesheet" type="text/css" href="<c:url value='css/jHtmlArea.ColorPickerMenu.css'/>" />
<script type="text/javascript" src="/pages/roomRate/addRoomRate.js"></script>
<script type="text/javascript" src="/pages/roomRate/rateModel.js"></script>
<script type="text/javascript">

$('#oKAndNext').bind('click',function(){
	effectiveDateRate=$("#effectiveDate_rate").val();
	expireDateRate=$("#expireDate_rate").val();
	
	var effectiveDateCode = validateYYYYMMDDLine(effectiveDateRate);
	if(effectiveDateCode!='success'){
		alert(getErrorMsg(effectiveDateCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
		return false;
	}
	
	var expireDateCode = validateYYYYMMDDLine(expireDateRate);
	if(expireDateCode!='success'){
		alert(getErrorMsg(expireDateCode,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-DD'));
		return false;
	}
});

//可卖条件
var soldableConditionJson=[];
var MinimumStayThrough= '';
var rateCodeAlreadyExistedInTheHotel= '';
var accessCode= '';
var deleteMessage= '';
var multiLanguageType= '';
var rateCode= '';
var multiLanguageName= '';
var multiLanguageTypeRepeated= '';
var descriptionCorporate= '';
var multiLanguageDescription= '';
var monday= '';
var tuesday= '';
var wednesday= '';
var thursday= '';
var friday= '';
var saturday= '';
var sunday= '';
$(document).ready(function() {
	MinimumStayThrough = '<fmt:message key="ccm.Rates.message.MinimumStayThrough"/>'
	rateCodeAlreadyExistedInTheHotel = '<fmt:message key="ccm.Rates.ErrorMessage.RateCodeAlreadyExistedInTheHotel"/>'
	accessCode = '<fmt:message key="ccm.Rates.message.AccessCode"/>';
	deleteMessage = '<fmt:message key="ccm.Channel.message.DeleteMessage"/>';
	multiLanguageType = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
	rateCode='<fmt:message key="ccm.Rates.RateCode"/>';
	multiLanguageName =	'<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
	multiLanguageTypeRepeated = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
	descriptionCorporate = '<fmt:message key="ccm.Rates.DescriptionCorporate"/>';
	multiLanguageDescription='<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageDescription"/>';
	multiLanguageDescription = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageDescription"/>'
	monday="<fmt:message key='common.weeks.monday'/>";
	tuesday="<fmt:message key='common.weeks.tuesday'/>";
	wednesday="<fmt:message key='common.weeks.wednesday'/>";
	thursday="<fmt:message key='common.weeks.thursday'/>";
	friday="<fmt:message key='common.weeks.friday'/>";
	saturday="<fmt:message key='common.weeks.saturday'/>";
	sunday="<fmt:message key='common.weeks.sunday'/>";
	
	
	
	
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
			              ],
			monthNamesShort:[
							'<fmt:message key="month.january"/>', 
							'<fmt:message key="month.february"/>', 
							'<fmt:message key="month.march"/>', 
							'<fmt:message key="month.april"/>', 
							'<fmt:message key="month.may"/>', 
							'<fmt:message key="month.june"/>', 
							'<fmt:message key="month.july"/>', 
							'<fmt:message key="month.august"/>',
							'<fmt:message key="month.september"/>', 
							'<fmt:message key="month.october"/>', 
							'<fmt:message key="month.november"/>', 
							'<fmt:message key="month.december"/>' 
			              ],
			hourText:'<fmt:message key="time.Hour"/>',
			minuteText:'<fmt:message key="time.Minute"/>',
			timeText:'<fmt:message key="time.Time"/>',
			currentText:'<fmt:message key="time.Present"/>',
			closeText:'<fmt:message key="common.button.close"/>'
		}
	
	jQuery.extend(jQuery.validator.messages, {
		required : "<fmt:message key='common.RequiredField'/>"
	});
	
	var start = $("#startDate");
	var end = $("#endDate");
	jtimepicker(start, end)
	
	$('.CancelRuleNew .mgR6').bind('click',function(){
		var isApplyToWeek = "";
		$('.CancelRuleNew input:checked').each(function(){
			 isApplyToWeek += ",'"+$(this).attr("id")+"':'true'";
		});
		var idx = $('.CancelRuleNew select[name="cancelId"]').attr("idx");
		var cancelId = $('.CancelRuleNew select[name="cancelId"]').val();
		var policyName = $('.CancelRuleNew select[name="cancelId"]').find("option:selected").text();
		var effectiveDate = $('.CancelRuleNew input[name="effectiveDate"]').val();
		var expireDate = $('.CancelRuleNew input[name="expireDate"]').val();
		var cancelRuleOneStr = "{'cancelId':'"+cancelId+"','policyName':'"+policyName.trim()+"','effectiveDate':'"+effectiveDate+"','expireDate':'"+expireDate+"'"+isApplyToWeek+"}";
		
		if(idx >= 0){
			cancelRuleJson.splice(idx,1,convertStrToJson(cancelRuleOneStr));
		}else{
			cancelRuleJson.push(convertStrToJson(cancelRuleOneStr));
		}
		showCancelRule();
		$(this).parents('.basicNew').slideUp();
	});
	
	//var gstr = '[{"delFlag":false,"effectiveDate":"2013-12-25","expireDate":"2013-12-26","guaranteeId":"6181ce6abc9d4e1880bc1eb3db8a0e57","isApplyToFriday":true,"isApplyToMonday":true,"isApplyToSaturday":true,"isApplyToSunday":true,"isApplyToThursday":true,"isApplyToTuesday":true,"isApplyToWednesday":true,"policyName":"测试担保规则"},{"delFlag":false,"effectiveDate":"2013-12-26","expireDate":"2013-12-27","guaranteeId":"d06eb1356d6347ac9fd0c77febc06ede","isApplyToFriday":true,"isApplyToMonday":true,"isApplyToSaturday":true,"isApplyToThursday":true,"isApplyToWednesday":true,"policyName":"测试担保规则2"}]';
	var gstr = '${guaranteeJsonArr}'
	if(gstr != ""){//获取已存的担保规则
		guaranteeJson = convertStrToJson(gstr);
		showGuarantee();
	}
	
	$('.GuaranteeRulesNew .mgR6').bind('click',function(){
		var isApplyToWeek = "";
		$('.GuaranteeRulesNew input:checked').each(function(){
			 isApplyToWeek += ",'"+$(this).attr("id")+"':'true'";
		});
		var idx = $('.GuaranteeRulesNew select[name="guaranteeId"]').attr("idx");
		var guaranteeId = $('.GuaranteeRulesNew select[name="guaranteeId"]').val();
		var policyName = $('.GuaranteeRulesNew select[name="guaranteeId"]').find("option:selected").text();
		var effectiveDate = $('.GuaranteeRulesNew input[name="effectiveDate"]').val();
		var expireDate = $('.GuaranteeRulesNew input[name="expireDate"]').val();
		var guaranteeOneStr = "{'guaranteeId':'"+guaranteeId+"','policyName':'"+policyName.trim()+"','effectiveDate':'"+effectiveDate+"','expireDate':'"+expireDate+"'"+isApplyToWeek+"}";
		
		if(idx >= 0){
			guaranteeJson.splice(idx,1,convertStrToJson(guaranteeOneStr));
		}else{
			guaranteeJson.push(convertStrToJson(guaranteeOneStr));
		}
		showGuarantee();
		$(this).parents('.basicNew').slideUp();
	});
	
	//房型选择
	$('#roomTypeCodeDiv .confirmthis').click(function(){
		var roomTypeCode='';
		var roomTypeName='';
		$('#roomTypeCodeDiv input:checked').each(function(){ 
			roomTypeCode += $(this).val()+',';
		});
		$('#roomTypeCodeDiv input:checked').next('span').each(function(){ 
			roomTypeName += $(this).text()+',';
		});
		roomTypeCode = roomTypeCode.substr(0,roomTypeCode.lastIndexOf(','));
		roomTypeName = roomTypeName.substr(0,roomTypeName.lastIndexOf(','));
		
		$('#roomTypeCode').val(roomTypeCode);
		$('.rommTypeClick .typeCode').text(roomTypeCode);
		$('.rommTypeClick .typeName').text(roomTypeName);
		$('#roomTypeCodeDiv').hide();
	});
	
	$('.rommTypeClick').bind('click',function(){
		$(this).next('#roomTypeCodeDiv').slideDown();
	});
	
	$('#roomTypeCodeDiv .closethis').bind('click',function(){
		$('#roomTypeCodeDiv').hide();
	});
	
	//打包服务
	$('#packageDiv .confirmthis').click(function(){
		var packageId = '';
		var packageCode='';
		var packageName='';
		$('#packageDiv input:checked').each(function(){ 
			packageCode += $(this).val()+',';
			packageId += $(this).attr("id")+',';
		});
		$('#packageDiv input:checked').next('span').each(function(){ 
			packageName += $(this).text()+',';
		});
		packageCode = packageCode.substr(0,packageCode.lastIndexOf(','));
		packageName = packageName.substr(0,packageName.lastIndexOf(','));
		packageId = packageId.substr(0,packageId.lastIndexOf(','));
		$('#packageId').val(packageId);
		$('.packageClick .packageCode').text(packageCode);
		$('.packageClick .packageName').text(packageName);
		$('#packageDiv').hide();
	});
	
	$('.packageClick').bind('click',function(){
		$(this).next('#packageDiv').slideDown();
	});
	
	$('#packageDiv .closethis').bind('click',function(){
		$('#packageDiv').hide();
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
		if($('#BasicPriceChosen').val()==''){
			$("#rp_percent").val("");
			$("#rp_amount").val("");
			$("#basicPriceMethod").hide();
		}else{
			$("#basicPriceMethod").show();
		}
	});
	$('#BasicPricePercent,#BasicPriceNumber,#BasicPricePercent_New,#BasicPriceNumber_New').change(function(){
		if($("#BasicPricePercent").is(':checked')){
			$("#rp_amount").val("");
			$("#rp_amount").attr("disabled",true);
			$("#rp_percent").attr("disabled",false);
		}
		if($("#BasicPriceNumber").is(':checked')){
			$("#rp_percent").val("");
			$("#rp_amount").attr("disabled",false);
			$("#rp_percent").attr("disabled",true);
		}
		if($("#BasicPricePercent_New").is(':checked')){
			$('.SoldableConditionNew input[name="percent"]').val("");
			$("#lab_rp_percent").removeClass('visuallyhidden');
			$("#lab_rp_amount").addClass('visuallyhidden');
		}
		if($("#BasicPriceNumber_New").is(':checked')){
			$('.SoldableConditionNew input[name="amount"]').val("");
			$("#lab_rp_amount").removeClass('visuallyhidden');
			$("#lab_rp_percent").addClass('visuallyhidden');
		}
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
	//新建  显示担保规则div
	$('.GR_click').bind('click',function(){
		$('.GuaranteeRulesNew input[type="checkbox"]').each(function(){
			$(this).prop("checked","true");
		});
		$('.GuaranteeRulesNew select[name="guaranteeId"]').attr("idx",-1);
		$('.GuaranteeRulesNew select[name="guaranteeId"]').val("");
		$('.GuaranteeRulesNew input[name="effectiveDate"]').val("");
		$('.GuaranteeRulesNew input[name="expireDate"]').val("");
		
		var l_ps=$(this).position().left;
		var r_ps=$(window).width()-l_ps-45;
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
	//新建  显示取消规则div
	$('.NR_click').bind('click',function(){
		$('.CancelRuleNew input[type="checkbox"]').each(function(){
			$(this).prop("checked","true");
		});
		$('.CancelRuleNew select[name="cancelId"]').attr("idx",-1);
		$('.CancelRuleNew select[name="cancelId"]').val("");
		$('.CancelRuleNew input[name="effectiveDate"]').val("");
		$('.CancelRuleNew input[name="expireDate"]').val("");
		
		var l_ps=$(this).position().left;
		var r_ps=$(window).width()-l_ps-45;
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
		$('.SoldableConditionNew').attr("idx",-1);
		var l_ps=$(this).position().left;
		var r_ps=$(window).width()-l_ps-45;
		var t_ps=$(this).position().top+16;
		var t_ps2=$(this).position().top+24;
		$('.SoldableConditionNew').slideDown();
		if($(this).hasClass('blue')){
			$('.SoldableConditionNew').css({left:'',right:r_ps,top:t_ps2});
		}
		else{
			$('.SoldableConditionNew').css({left:l_ps,right:'',top:t_ps});
		}
		$('.SoldableConditionNew input').each(function(){
			$(this).val("");
		});
		
		$('.SoldableConditionNew input[name="minEvenDay"]').val("1");
// 		$('.SoldableConditionNew select[name="bookTime"]')[0].selectedIndex=0;
		$("#BasicPricePercent_New").prop("checked","");
		$("#BasicPriceNumber_New").prop("checked","");
	});
	
	$('.nav li').bind('click',function(){
		$('.nav li').removeClass('active');
		$('#GuaranteeRules,#CancelRule,#SoldableCondition').hide()
		$(this).addClass('active');
		var li_1=$('.nav li:first-child').hasClass('active')
		var li_3=$('.nav li:last-child').hasClass('active')
		if(li_1){$('#GuaranteeRules').show()}
		else if(li_3){$('#SoldableCondition').show()}
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
	$('.BSP_click').bind('click',function(){
		var l_ps=$(this).position().left;
		var t_ps=$(this).position().top+16;
		$('.priceBagService').slideDown();
		$('.priceBagService').css({left:l_ps,right:'',top:t_ps});
		});
	$('.basicNew .closethis').bind('click',function(){
		$(this).parents('.basicNew').slideUp();
		});
	
	
	$('.priceBagService .closethis').bind('click',function(){
		$(this).parents('.priceBagService').hide();
		});
	
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
			              ],
			monthNamesShort:[
							'<fmt:message key="month.january"/>', 
							'<fmt:message key="month.february"/>', 
							'<fmt:message key="month.march"/>', 
							'<fmt:message key="month.april"/>', 
							'<fmt:message key="month.may"/>', 
							'<fmt:message key="month.june"/>', 
							'<fmt:message key="month.july"/>', 
							'<fmt:message key="month.august"/>',
							'<fmt:message key="month.september"/>', 
							'<fmt:message key="month.october"/>', 
							'<fmt:message key="month.november"/>', 
							'<fmt:message key="month.december"/>' 
			              ]
		}
	
	
	
	//开始/结束日期
	$("#effectiveDate_rate").datepicker($.extend(dpconfig,{
		minDate:new Date(),
		onClose : function(v) {
			v = addHRToStr(v);
			$("#effectiveDate_rate").val(v);
			$("#expireDate_rate").datepicker("option", "minDate", v);
			
			$("#effectiveDate_guarantee").datepicker("option", "minDate", v);
			$("#effectiveDate_cancel").datepicker("option", "minDate", v);
			$("#effectiveDate_rateDetail").datepicker("option", "minDate", v);
			$("#effectiveDate_splitRateDetail").datepicker("option", "minDate", v);
		}
	}));
	$("#expireDate_rate").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			v = addHRToStr(v);
			$("#expireDate_rate").val(v);
			$("#effectiveDate_rate").datepicker("option", "maxDate", v);
			
			$("#expireDate_guarantee").datepicker("option", "maxDate", v);
			$("#expireDate_cancel").datepicker("option", "maxDate", v);
			$("#expireDate_rateDetail").datepicker("option", "maxDate", v);
			$("#expireDate_splitRateDetail").datepicker("option", "maxDate", v);
		}
	}));
	$("#effectiveDate_guarantee").datepicker( $.extend(dpconfig,{
		minDate:new Date(),
		onClose : function(v) {
			$("#expireDate_guarantee").datepicker("option", "minDate", v);
		}
	}));
	$("#expireDate_guarantee").datepicker($.extend(dpconfig, {
		maxDate:$("#expireDate_rate").val(),
		onClose : function(v) {
			$("#effectiveDate_guarantee").datepicker("option", "maxDate", v);
		}
	}));
	$("#effectiveDate_cancel").datepicker( $.extend(dpconfig,{
		minDate:new Date(),
		onClose : function(v) {
			$("#expireDate_cancel").datepicker("option", "minDate", v);
		}
	}));
	$("#expireDate_cancel").datepicker($.extend(dpconfig, {
		maxDate:$("#expireDate_rate").val(),
		onClose : function(v) {
			$("#effectiveDate_cancel").datepicker("option", "maxDate", v);
		}
	}));
	
	
	$('.hasDatepicker').blur(function(){
		validateDateFormart($(this));
	});
	
	//AccessCode
	$('#Two_click').bind('click',function(){
		$('#Two_show').slideDown();
	});
	
	$('#Two_show .closethis').bind('click',function(){
		$('#Two_show').hide();
	});
	
	//全选
	$("#Two_show .selectAll").bind('click',function(){
		var checklist = document.getElementsByName("ratePlanVO.customList");
		for(var i=0;i<checklist.length;i++){
		      checklist[i].checked = true;
		}
	});
	//反选
	$("#Two_show .reverseSel").bind('click',function(){
		$("input[name='ratePlanVO.customList']").attr("checked",false);
	});
	//AccessCode选择
	$('#Two_show .confirmthis').click(function(){
		showAccessCode();
		$('#Two_show').hide();
	});
	
	
	$('#f_ratePlanTemplateCode').change(function(){
		//获取选中的ratePlanTemplateCode
		var ratePlanTemplateCode = $(this).val();
		
		//新增时验证
		$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"roomRate_ajaxloadRateTempInfo.do",
	   	  data:{"ratePlanTempCode":ratePlanTemplateCode},
		  success:function(data){
			  //打包名称
			  var moreRatePlanName = $('#ml_switch_ratePlanName');
			  var modelRatePlanName = $('#mdl_switch_ratePlanName');
			  var moreDescription = $('#ml_switch_description');
			  var modelDescription = $('#mdl_switch_description');
				  
			  //除模板行以外,移除其他行 
			  moreRatePlanName.find('table>tbody').find("tr:not(:last)").each(function(){
				  if($(this).attr('id') != 'mdl_switch_ratePlanName'){
					  $(this).remove();
				  }
			  });
				  
			  //除模板行以外,移除其他行 
			  moreDescription.find('table>tbody').find("tr:not(:last)").each(function(){
				  if($(this).attr('id') != 'mdl_switch_description'){
					  $(this).remove();
				  }
			  });
			  
			  $('input[name="rpi18n.ratePlanName"]').val('');
			  $('input[name="rpi18n.description"]').val('');
			  $('input[name="show_description"]').val('');
				
			  if(strIsNull(data)){
				  return false;
			  }
			  var tempList = eval("("+data+")");

			  for(var i=0;i<tempList.length;i++){
				  //如果是默认语言
				  if(tempList[i].isDefault == 'Yes'){
					  $('input[name="rpi18n.ratePlanName"]').val(tempList[i].ratePlanName);
					  $('input[name="rpi18n.description"]').val(tempList[i].description);
					  
					  $('#f_description').htmlarea('html',tempList[i].description);
					  $('input[name="show_description"]').val($('#f_description').htmlarea('toString'));
				  }else{
					  //如果包名不为空
					  if(!strIsNull(tempList[i].ratePlanName)){
						  //设置包价名称
						  moreRatePlanName.find('table>tbody').find("tr:last").before(
								  '<tr><td class="w20">1.</td>'+modelRatePlanName.html()+'</tr>');
						  var ratePlanNameTr = moreRatePlanName.find('table>tbody').find("tr:last").prev();
						  var sel = ratePlanNameTr.find("select[name='language.codeNo']");
						  var pn  = ratePlanNameTr.find("input[name='language.name']");
						  sel.val(tempList[i].language);
						  pn.val(tempList[i].ratePlanName);
					  }
					  
					  //如果描述不为空
					  if(!strIsNull(tempList[i].description)){
						  //设置描述
						  moreDescription.find('table>tbody').find("tr:last").before(
								  '<tr><td class="w20">1.</td>'+modelDescription.html()+'</tr>');
						  var descriptionTr = moreDescription.find('table>tbody').find("tr:last").prev();
						  var sel2 = descriptionTr.find("select[name='language.codeNo']");
						  var desc  = descriptionTr.find("textarea[name='language.description']");
						  desc.text(tempList[i].description);
						  sel2.val(tempList[i].language);
						  setHtmlArea(desc);
					  }
				  }
			  }
	       }
	    });	
	});
	
	//设置为HTML编辑器
	setHtmlArea($('#f_description'));
	
	$('input[name="show_description"]').focus(function(){
		if($('.DescMoreLanguageNew').is(':hidden')){
			$('.DefaultLanguageNew').slideDown();
		}
	});
	
	$('.DescMoreLanguageNew .mgR6').bind('click',function(){
		var languageTRS = $('#ml_switch_description').find('table>tbody>tr:not(:last)');
		
		//如果长度项都是1
		if(languageTRS.length==1){
			$('.DescMoreLanguageNew').slideUp();
			return true;
		}
		
		var tempDescCodes = '';
		var flag = true;
		//循环遍历拼接多语言字符串
		languageTRS.each(function(){
			//不能加载多语言模型行
			if($(this).attr('id') != 'mdl_switch_description'){
				var sel = $(this).find('select[name="language.codeNo"]');
				var description = $(this).find('textarea[name="language.description"]').val();
				var selStr = ','+sel.val()+',';
				
				//如果未选择语言类型
				if(strIsNull(sel.val())){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.Rates.DescriptionCorporate"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
					alert(i18n_replace(str,arry));
					//alert('【房价描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
					flag = false;
					return false;
				}
				
				//如果未选择语言类型
				if(strIsNull(description)){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.Rates.DescriptionCorporate"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageDescription"/>';
					alert(i18n_replace(str,arry));
					//alert('【房价描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的描述未填写,请检查'); 
					flag = false;
					return false;
				}
				
				//判断语言种类是否已重复
				if(tempDescCodes.indexOf(selStr)>=0){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.Rates.DescriptionCorporate"/>');
					arry.push(sel.find("option:selected").text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
					alert(i18n_replace(str,arry));
					//alert('【房价描述】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
					flag = false;
					return false;
				}
				
				//拼接语言种类
				tempDescCodes += ',' + sel.val() + ',';
			}
		});
		
		//校验不通过,返回
		if(!flag)return flag;
		
		$('.DescMoreLanguageNew').slideUp();
	});
	
	$('.NameMoreLanguageNew .mgR6').bind('click',function(){
		var tempNameCodes = '';
		var flag = true;
		var languageRTS = $('#ml_switch_ratePlanName').find('table>tbody>tr:not(:last)');
		
		//循环遍历拼接多语言字符串
		languageRTS.each(function(){
			//不能加载多语言模型行
			if($(this).attr('id') != 'mdl_switch_ratePlanName'){
				var sel = $(this).find('select[name="language.codeNo"]');
				var name = $(this).find('input[name="language.name"]').val();
				var selStr = ','+sel.val()+',';
				
				//如果未选择语言类型
				if(strIsNull(sel.val())){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.Rates.RateCode"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
					alert(i18n_replace(str,arry));
					//alert('【房价名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
					flag = false;
					return false;
				}
				
				//如果未选择语言类型
				if(strIsNull(name)){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.Rates.RateCode"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
					alert(i18n_replace(str,arry));
					//alert('【房价名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查'); 
					flag = false;
					return false;
				}
				
				//判断语言种类是否已重复
				if(tempNameCodes.indexOf(selStr)>=0){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.Rates.RateCode"/>');
					arry.push(sel.find("option:selected").text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
					alert(i18n_replace(str,arry));
					//alert('【房价名称】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
					flag = false;
					return false;
				}
				
				//拼接语言种类
				tempNameCodes += ',' + sel.val() + ',';
			}
		});
		
		//校验不通过,返回
		if(!flag)return flag;
		
		$('.NameMoreLanguageNew').slideUp();
	});
	
	$('.DefaultLanguageNew .mgR6').click(function(){
		$('input[name="rpi18n.description"]').val($('#f_description').val());
		$('input[name="show_description"]').val($('#f_description').htmlarea('toString'));
		$('.DefaultLanguageNew').slideUp();
	});
	
	$('.DefaultLanguageNew .closethis').click(function(){
		$('.DefaultLanguageNew').slideUp();
	});
	
	//多语言按钮点击事件
	$('#switch_ratePlanName').click(function(){
		//如果多语言面板隐藏了
		if($(".NameMoreLanguageNew").is(':hidden')){
			var name_input = $("input[name='rpi18n.ratePlanName']");
			var modelLanguage = $('#mdl_switch_ratePlanName');
			//如果仅剩一行记录
			if($(".NameMoreLanguageNew").find('table>tbody>tr').length==2){
				$(".NameMoreLanguageNew").find('table>tbody').find("tr:last").before('<tr><td class="w20">1.</td>'+modelLanguage.html()+'</tr>');
			}
			
			//将多语言面板放到描述下方
			$(".NameMoreLanguageNew").css("left",name_input.position().left);
	        $(".NameMoreLanguageNew").css("top",name_input.position().top+name_input.outerHeight());
	        $('.NameMoreLanguageNew').slideDown();
		}else{
			$('.NameMoreLanguageNew').slideUp();
		}
	});
	
	//多语言按钮点击事件
	$('#switch_description').click(function(){
		if($('.DefaultLanguageNew').is(':hidden')){

			//如果多语言面板隐藏了
			if($(".DescMoreLanguageNew").is(':hidden')){
				var desc_input = $("input[name='show_description']");
				var modelLanguage = $('#mdl_switch_description');
				//如果仅剩一行记录
				if($(".DescMoreLanguageNew").find('table>tbody>tr').length==2){
					var lastTr= $(".DescMoreLanguageNew").find('table>tbody').find("tr:last");
					lastTr.before('<tr><td class="w20">1.</td>'+modelLanguage.html()+'</tr>');
				
					var newTxtArea = lastTr.prev('tr').find('textarea[name="language.description"]');
					setHtmlArea(newTxtArea);
				}else{
					//循环遍历拼接多语言字符串
					$(".DescMoreLanguageNew").find('table>tbody>tr:not(:last)').each(function(){
						//不能加载多语言模型行
						if($(this).attr('id') != 'mdl_switch_description'){
							var description = $(this).find('textarea[name="language.description"]');
							setHtmlArea(description);
						}
					});
				}
				
				//将多语言面板放到描述下方
				$(".DescMoreLanguageNew").css("left",desc_input.position().left);
		        $(".DescMoreLanguageNew").css("top",desc_input.position().top+desc_input.outerHeight());
		        $('.DescMoreLanguageNew').slideDown();
			}else{
				$('.DescMoreLanguageNew').slideUp();
			}
		}
	});
	
	//可卖条件
	$('.SoldableConditionNew .mgR6').bind('click',function(){
		var conditionStr = "";
		$('.SoldableConditionNew input[type="text"]').each(function(){
			conditionStr += $(this).val();
		});
		if(conditionStr == ""){
			alert("<fmt:message key='ccm.Rates.ErrorMessage.ValueAtLeast'/>!");
			return;
		}
		
		var obj = soldAbleCondition();
		obj.maxEvenDay = $('.SoldableConditionNew input[name="maxEvenDay"]').val();
		obj.minEvenDay = $('.SoldableConditionNew input[name="minEvenDay"]').val();
		obj.maxBeforehandDay = $('.SoldableConditionNew input[name="maxBeforehandDay"]').val();
		obj.minBeforehandDay = $('.SoldableConditionNew input[name="minBeforehandDay"]').val();
		obj.startDate = $('.SoldableConditionNew input[name="startDate"]').val();
		obj.endDate = $('.SoldableConditionNew input[name="endDate"]').val();
		obj.lastMinutesBeginTime = $('.SoldableConditionNew input[name="beginTime"]').val();
		obj.lastMinutesEndTime = $('.SoldableConditionNew input[name="endTime"]').val();
		obj.lastMinutesPercent = $('.SoldableConditionNew input[name="percent"]').val();
		obj.lastMinutesAmount = $('.SoldableConditionNew input[name="amount"]').val();
		obj.limitBuy = $('.SoldableConditionNew input[name="limitBuy"]').val();
// 		obj.bookTime = $('.SoldableConditionNew select[name="bookTime"]').val();
		obj.soldNum = $('.SoldableConditionNew input[name="soldNum"]').val();
		
		if(obj.startDate !='' && obj.endDate=='' ){
			alert('<fmt:message key="ccm.InventoryManagement.error.endDateNull"/>！');
			return;
		}
		if(obj.startDate =='' && obj.endDate!='' ){
			alert('<fmt:message key="ccm.InventoryManagement.error.beginDateNull"/>！');
			return;
		}
		
		if(obj.lastMinutesBeginTime !='' && obj.lastMinutesEndTime=='' ){
			alert('<fmt:message key="ccm.InventoryManagement.error.endDateNull"/>！');
			return;
		}
		if(obj.lastMinutesBeginTime =='' && obj.lastMinutesEndTime!='' ){
			alert('<fmt:message key="ccm.InventoryManagement.error.beginDateNull"/>！');
			return;
		}
		if(obj.lastMinutesBeginTime !='' && obj.lastMinutesEndTime!=''){
			if(obj.lastMinutesPercent=='' && obj.lastMinutesAmount==''){
				alert('<fmt:message key="ccm.Rates.ErrorMessage.PercentageAndFixedAmount"/>！');
				return;
			}
		}else{
			if(obj.lastMinutesPercent!=''||obj.lastMinutesAmount!=''){
				alert('fmt:message key="ccm.InventoryManagement.error.beginDateNull"/>！');
				return;
			}
		}
		
		var idx =$('.SoldableConditionNew').attr("idx");
		if(idx >= 0){
			soldableConditionJson.splice(idx,1,obj);
		}else{
			soldableConditionJson.push(obj);
		}
		console.log(JSON.stringify(obj));
		showSoldableCondition();
		$(this).parents('.basicNew').hide();
	});
	
	
});
function showSoldableCondition(){
	$("#soldableConditionTab").empty();
	for(var i=0;i< soldableConditionJson.length;i++){
		var scj = soldableConditionJson[i];
		var tr = "";
		tr +='<tr><td class="w20">'+(i+1)+'</td>';
		tr +='<td ><a href="javascript:;" onclick="editSoldableConditionTr('+i+',this);">';
		if(scj.minEvenDay){
			tr += "<fmt:message key='ccm.Rates.MinimumStayThrough'/>"+scj.minEvenDay+"<fmt:message key='common.Day'/>";
		}
		if(scj.maxEvenDay){
			tr += " <fmt:message key='ccm.Rates.MaximumStayThrough'/>"+scj.maxEvenDay+"<fmt:message key='common.Day'/>";
		}
		if(scj.minBeforehandDay){
			tr += " <fmt:message key='ccm.Rates.MinimumAdvanceBooking'/>"+scj.minBeforehandDay+"<fmt:message key='common.Day'/>";
		}
		if(scj.maxBeforehandDay){
			tr += " <fmt:message key='ccm.Rates.MaximumAdvanceBooking'/>"+scj.maxBeforehandDay+"<fmt:message key='common.Day'/>";
		}
		if(scj.startDate){
			tr += " <fmt:message key='ccm.Rates.StartEndDate'/>"+scj.startDate +' ~ '+scj.endDate 
		}
		if(scj.lastMinutesBeginTime){
			tr += " <fmt:message key='ccm.Rates.FlashSale'/>"+scj.lastMinutesBeginTime +' ~ '+scj.lastMinutesEndTime 
			if(scj.lastMinutesPercent != undefined && scj.lastMinutesPercent != ''){
				tr+=" <fmt:message key='ccm.RestrictionsManagement.Rate'/>"+scj.lastMinutesPercent+"%";
			}
			if(scj.lastMinutesAmount != undefined && scj.lastMinutesAmount != ''){
				tr+=" <fmt:message key='ccm.RestrictionsManagement.Rate'/>"+scj.lastMinutesAmount;
			}
		}
		if(scj.limitBuy){
			tr += " <fmt:message key='ccm.Rates.LimitedPurchase'/>"+scj.limitBuy+"<fmt:message key='ccm.Rates.Room'/>";
		}
// 		if(scj.bookTime){
// 			tr += " 预订时间"+scj.bookTime+"点之前";
// 		}
		tr +='</a></td><td><div class="center"><a href="javascript:delSoldableCondition('+i+');" class="link_1">x</a></div></td></tr>';
		$("#soldableConditionTab").append(tr);
	}
	$("#soldableConditionJsonArr").val(JSON.stringify(soldableConditionJson));
}
//取消规则结束
function delSoldableCondition(idx){
	if(confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？")){
		soldableConditionJson.remove(idx);
		showSoldableCondition();
	}
}
function editSoldableConditionTr(idx,thi){
	$('.SoldableConditionNew input').each(function(){
		$(this).val("");
	});
// 	$('.SoldableConditionNew select[name="bookTime"]')[0].selectedIndex=0;
	$('.SoldableConditionNew input[name="percent"]').parent('span').addClass('visuallyhidden');
	$('.SoldableConditionNew input[name="amount"]').parent('span').addClass('visuallyhidden');
	$("#BasicPricePercent_New").prop("checked","");
	$("#BasicPriceNumber_New").prop("checked","");
	
	var obj = soldableConditionJson[idx];
	$(".SoldableConditionNew").attr("idx",idx);
	$('.SoldableConditionNew input[name="maxEvenDay"]').val(obj.maxEvenDay);
	$('.SoldableConditionNew input[name="minEvenDay"]').val(obj.minEvenDay);
	$('.SoldableConditionNew input[name="maxBeforehandDay"]').val(obj.maxBeforehandDay);
	$('.SoldableConditionNew input[name="minBeforehandDay"]').val(obj.minBeforehandDay);
	$('.SoldableConditionNew input[name="startDate"]').val(obj.startDate);
	$('.SoldableConditionNew input[name="endDate"]').val(obj.endDate);
	$('.SoldableConditionNew input[name="beginTime"]').val(obj.lastMinutesBeginTime);
	$('.SoldableConditionNew input[name="endTime"]').val(obj.lastMinutesEndTime);
	
	
	if(obj.lastMinutesPercent != undefined && obj.lastMinutesPercent != ''){
		$('.SoldableConditionNew input[name="percent"]').parent('span').removeClass('visuallyhidden');
		$('.SoldableConditionNew input[name="percent"]').val(obj.lastMinutesPercent);
		$("#BasicPricePercent_New").prop("checked","true");
	}else if(obj.lastMinutesAmount != undefined && obj.lastMinutesAmount != ''){
		$('.SoldableConditionNew input[name="amount"]').parent('span').removeClass('visuallyhidden');
		$('.SoldableConditionNew input[name="amount"]').val(obj.lastMinutesAmount);
		$("#BasicPriceNumber_New").prop("checked","true");
	}
	
	$('.SoldableConditionNew input[name="limitBuy"]').val(obj.limitBuy);
// 	$('.SoldableConditionNew select[name="bookTime"]').val(obj.bookTime);
	$('.SoldableConditionNew input[name="soldNum"]').val(obj.soldNum);
	
	var l_ps=$(thi).position().left;
	var r_ps=873-l_ps;
	var t_ps=$(thi).position().top+16;
	var t_ps2=$(thi).position().top+24;
	$('.SoldableConditionNew').slideDown();
	if($(this).hasClass('blue')){
		$('.SoldableConditionNew').css({left:'',right:r_ps,top:t_ps2});
	}
	else{
		$('.SoldableConditionNew').css({left:l_ps,right:'',top:t_ps});
	}
}
</script>
   