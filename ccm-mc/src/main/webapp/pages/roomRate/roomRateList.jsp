<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="CCMmainConter w1200">
  <div class="title_wp"> 
    <!-- <div class="bt"> <a href="0HOTEL-2.html" class="btn_2 blue">新建</a> </div>--> 
    <fmt:message key="ccm.Rates.RatesManagment"/>
     </div>
  <!--左右两列-->
  <div class="ccm_2wp clearfix">
    <div class="ccm_left" style="position:relative;">
      <div class="menulayerMask"></div>
      <div class="lt_menu2">
        <ul class="mlist">
        <c:set value="${param.ratePlanId == null ? ratePlanId : param.ratePlanId}" var="ratePlanId"></c:set>
        <s:iterator value="rateMapList" var="rateMap" status="sta">
          <c:if test="${rateMap.disable == 'true' }">
          	<li><a class="disable" <c:if test="${ratePlanId == rateMap.ratePlanId }">class="selected"</c:if> 
          		href="roomRate_list.do?ratePlanId=${rateMap.ratePlanId}" 
          		><b>${rateMap.ratePlanCode }</b></a>
          		
          </c:if>
          <c:if test="${rateMap.disable != 'true'}">
          	<li><a <c:if test="${ratePlanId == rateMap.ratePlanId }">class="selected"</c:if> href="roomRate_list.do?ratePlanId=${rateMap.ratePlanId}" 
          		>
          		<b>${rateMap.ratePlanCode }</b>
          		
          		</a></li>
          </c:if >
        </s:iterator>
        </ul>
        <div class="newwp">
          <button type="button" class="btn_2 blue" onclick="location.href='roomRate_add.do'"><fmt:message key="ccm.Rates.NewRate"/></button>
          <%--<a href="#BatchEditCondition" class="ccm-popup-click">批量修改</a>--%> </div> 
      </div>
    </div>
    <div class="ccm_2col">
      <div class="ccm_right"> 
        <!--基本信息-->
        <div class="ratewp">
          <div class="edit_rate">
          <%----%>
          <a class="link ccm-popup-click" href="#BasicPriceInfo"><fmt:message key="common.button.edit"/></a></br>
          
<%--           <a class="link" href="javascript:delRatePlan('${ratePlanId}');">删除</a>  --%>
          </div>
          <ul class="textlist_wp frow">
            <li class="col3_1">
              <div class="i_title"><span class="text"><fmt:message key="ccm.Rates.RateCode"/>：</span></div>
              <div class="i_input" title="${ratePlanVO.rpi18n.ratePlanName }"> 
              		<b>
              		<c:if test="${fn:length(ratePlanVO.rpi18n.ratePlanName)<=14}">
              			${ratePlanVO.rpi18n.ratePlanName}
              		</c:if>
              		<c:if test="${fn:length(ratePlanVO.rpi18n.ratePlanName)>14 }">
              			${fn:substring(ratePlanVO.rpi18n.ratePlanName,0,14)}...
              		</c:if>
              		</b>
              </div>
            </li>
            <li class="col3_1">
              <div class="i_title"><span class="text"><fmt:message key="ccm.Rates.IsSuper"/>：</span></div>
              <div class="i_input">
              	<c:if test="${ratePlanVO.rp.isSuper}">
              		<fmt:message key="common.Yes"/>
              	</c:if>
              	<c:if test="${!ratePlanVO.rp.isSuper}">
              		<fmt:message key="common.Not"/>
              	</c:if>
              </div>
            </li>
            <li class="col3_1">
              <div class="i_title"><span class="text"><fmt:message key="ccm.Rates.RateCategory"/>：</span></div>
              <div class="i_input">${ratePlanVO.ratePlanType}</div>
            </li>
            <li class="col3_1">
              <div class="i_title"><span class="text"><fmt:message key="ccm.Rates.BeginDate"/>：</span></div>
              <div class="i_input" id="effectiveDateOfHeader"><fmt:formatDate value="${ratePlanVO.rp.effectiveDate }" pattern="yyyy-MM-dd"/></div>
            </li>
            <li class="col3_1">
              <div class="i_title"><span class="text"><fmt:message key="common.time.EndDate"/>：</span></div>
              <div class="i_input" id="expireDateOfHeader"> <fmt:formatDate value="${ratePlanVO.rp.expireDate }" pattern="yyyy-MM-dd"/></div>
            </li>
            <li class="col3_1">
              <div class="i_title"><span class="text"><fmt:message key="ccm.Rates.Market"/>：</span></div>
              <div class="i_input"> ${ratePlanVO.marketDescription } </div>
            </li>
            <li class="col3_1">
              <div class="i_title"><span class="text"><fmt:message key="ccm.Rates.SourceCode"/>：</span></div>
              <div class="i_input"> ${ratePlanVO.sourceDescription } </div>
            </li>
            <li class="col3_1">
              <div class="i_title"><span class="text"><fmt:message key="ccm.Rates.Negotiated"/>：</span></div>
              <div class="i_input">
              	<c:if test="${ratePlanVO.rp.isNegotiated }">
              		<fmt:message key="common.Yes"/>
              	</c:if>
              	<c:if test="${!ratePlanVO.rp.isNegotiated }">
              		<fmt:message key="common.Not"/>
              	</c:if>
              </div>
            </li>
            <li class="col3_1">
              <div class="i_title"><span class="text"><fmt:message key="ccm.Rates.Package"/>：</span></div>
              <div class="i_input">
              	<s:iterator value="ratePlanVO.ratePackageList" var="ratePackage" status="sta">
              		${sta.index!=0 ? ',' : ''}${ratePackage.packageCode}
              	</s:iterator>
              </div>
            </li>
            
            <!-- 房价描述ppages/ -->
            <li class="col3_1">
              <div class="i_title"><span class="text"><fmt:message key="ccm.Rates.DescriptionCorporate"/>：</span></div>
              <div class="i_input" style="padding-left:88px; float:none;line-height:20px;"> 
		             <div class="i_input" title="${ratePlanName }"> 
		              		<b>
		              		<c:if test="${fn:length(ratePlanName)<=14}">
		              			${ratePlanName}
		              		</c:if>
		              		<c:if test="${fn:length(ratePlanName)>14 }">
		              			${fn:substring(ratePlanName,0,14)}...
		              		</c:if>
		              		</b>
		             </div>
              </div>
            </li>
 
             <li class="col3_2">
              <div class="i_title"><span class="text"><fmt:message key="ccm.Rates.PriceValidate"/>：</span></div>
              <div class="i_input">
              	<c:if test="${ratePlanVO.rp.priceValidate}">
              		<fmt:message key="common.Yes"/>
              	</c:if>
              	<c:if test="${!ratePlanVO.rp.priceValidate}">
              		<fmt:message key="common.Not"/>
              	</c:if>
              </div>
            </li>
          </ul>
        </div>
        
        <c:if test="${ratePlanVO.rp.inheritRatePlanId == null or ratePlanVO.rp.inheritRatePlanId ==''}">
			<c:set value="false" var="isEdit"></c:set>
        </c:if>
        <c:if test="${ratePlanVO.rp.inheritRatePlanId != null && ratePlanVO.rp.inheritRatePlanId !=''}">
			<c:set value="true" var="isEdit"></c:set>
        </c:if>
        <!--价格内容-->
        <div class="ratetbwp">
        <%----%>
          <div class="ctl frow">
            <div class="float-right">
            	<!-- 新建房价-->
            	<a href="#AddNewPrice" id="newRateDetail" class="btn_2 blue  ccm-popup-click" onclick="showRateDetail(-1);"><fmt:message key="common.button.New"/></a>
            </div>
            <span class="TB5">
            <label class="radio inline">
              <input type="radio" name="ccm_rdption2" id="optionsRadios_rate1" onclick="filterDateRateDetailList(true);" value="true" checked>
              <span class="checked"><fmt:message key="ccm.Rates.ShowOnlyVaildTime"/></span> </label>
            <label class="radio inline">
              <input type="radio" name="ccm_rdption2" id="optionsRadios_rate2" onclick="filterDateRateDetailList(false);" value="true">
              <span class=""><fmt:message key="ccm.Rates.ShowAllRecords"/></span> </label>
            </span>
          </div>
             
          <div class="bt_wp">
            <table class="ccm_table1" id="normalTB">
              <thead>
                <tr>
                  <th class="w240 sortable sortAsc" onclick="sortBy('date');"><span><fmt:message key="ccm.Rates.Dates"/></span></th>
                  <th class="w180 sortable sortDes" onclick="sortBy('week');"><span><fmt:message key="ccm.Rates.Weeks"/></span></th>
                  <th><span><fmt:message key="ccm.InventoryManagement.RoomTypes"/></span></th>
                  <th class="sortable nosort w100" onclick="sortBy('price');"><span><fmt:message key="ccm.Rates.Amounts"/></span></th>
                  <th class="w120"><span><fmt:message key='common.button.Action'/>	</span></th>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
            <table class="ccm_table1" id="selectTB" style="display:none;">
              <thead>
                <tr>
                  <th class="w180 sortable sortAsc"><span><fmt:message key="ccm.Rates.Dates"/></span></th>
                  <th class="w180 sortable sortDes"><span><fmt:message key="ccm.Rates.Weeks"/></span></th>
                  <th><span><fmt:message key="ccm.InventoryManagement.RoomTypes"/></span></th>
                  <th class="sortable nosort w100"><span><fmt:message key="ccm.Rates.Amounts"/></span></th>
                  <th class="w100"><span><fmt:message key='common.button.Action'/></span></th>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
          </div>
          
          <!--批量修改-->
          <div id="BatchEditCondition" class="ccm-popup width750 zoom-anim-dialog mfp-hide">
            <div class="pp_main">
<%--              <div class="t_title">批量修改</div> --%>
              <div class="pdA24">
                <ul class="list_input">
                  <li>
                    <div class="i_title"><span class=""></span><span class="text">房价定义：</span></div>
                    <div class="i_input">
                      <div class="fm_bwp inline w240">
                        <div class="sel_ele"><span class="select_all"><fmt:message key="common.select.selectAll"/></span><span class="select_inverse"><fmt:message key="common.select.Unselect"/></span></div>
                        <div class="fm_box">
                          <label class="checkbox">
                            <input type="checkbox" id="cc" value="option1" checked="">
                            <span class="checked">CCFC 渠道门市价</span></label>
                          <label class="checkbox">
                            <input type="checkbox" id="dd" value="option2">
                            <span>CCFC 渠道门市价</span></label>
                          <label class="checkbox">
                            <input type="checkbox" id="ee" value="option3">
                            <span>CCFC 渠道门市价</span></label>
                          <label class="checkbox">
                            <input type="checkbox" id="ff" value="option2">
                            <span>CCFC 渠道门市价</span></label>
                          <label class="checkbox">
                            <input type="checkbox" id="gg" value="option3">
                            <span>CCFC 渠道门市价</span></label>
                          <label class="checkbox">
                            <input type="checkbox" id="hh" value="option2">
                            <span>CCFC 渠道门市价</span></label>
                          <label class="checkbox">
                            <input type="checkbox" id="ii" value="option3">
                            <span>CCFC 渠道门市价</span></label>
                          <label class="checkbox">
                            <input type="checkbox" id="jj" value="option2">
                            <span>CCFC 渠道门市价</span></label>
                          <label class="checkbox">
                            <input type="checkbox" id="kk" value="option3">
                            <span>CCFC 渠道门市价</span></label>
                          <label class="checkbox">
                            <input type="checkbox" id="ll" value="option2">
                            <span>CCFC 渠道门市价</span></label>
                          <label class="checkbox">
                            <input type="checkbox" id="mm" value="option3">
                            <span>CCFC 渠道门市价</span></label>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="i_title"><span class=""></span><span class="text">操作参数：</span></div>
                    <div class="i_input">
                      <select class="fxt w180">
                        <option>----</option>
                        <option>选项二</option>
                        <option>选项三</option>
                      </select>
                    </div>
                  </li>
                  <li>
                    <div class="i_title"><span class=""></span><span class="text">选择规则：</span></div>
                    <div class="i_input">
                      <select class="fxt w180">
                        <option>----</option>
                        <option>选项二</option>
                        <option>选项三</option>
                      </select>
                    </div>
                  </li>
                  <li class="c_rel">
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.BeginDate"/>：</span></div>
                    <div class="i_input">
                      <input class="fxt w120" type="text" value="" >
                    </div>
                    <div class="date_abs">
                      <div class="dateweek">
                        <div class="d_wp"> <span><fmt:message key="common.week.sunday"/></span>
                          <input type="checkbox" id="sunday_week" value="option1" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.monday"/>	</span>
                          <input type="checkbox" id="monday_week" value="option1" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.tuesday"/></span>
                          <input type="checkbox" id="tuesday_week" value="option1" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.wednesday"/></span>
                          <input type="checkbox" id="wenday_week" value="option1" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.thursday"/></span>
                          <input type="checkbox" id="thursday_week" value="option1" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.friday"/></span>
                          <input type="checkbox" id="friday_week" value="option1" checked="">
                        </div>
                        <div class="d_wp"> <span><fmt:message key="common.week.saturday"/></span>
                          <input type="checkbox" id="saturday_week" value="option1" checked="">
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.EndDate"/>	：</span></div>
                    <div class="i_input">
                      <input class="fxt w120" type="text" value="" >
                    </div>
                  </li>
                </ul>
              </div>
              <div class="b_crl">
                <button type="button" class="btn_2 green mgR6"><fmt:message key="common.button.save"/></button>
                <button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
              </div>
            </div>
          </div>
          
          <!--房价基本信息-->
          <div id="BasicPriceInfo" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
            <div class="pp_main">
              <div class="t_title"><fmt:message key='ccm.Rates.RateBaseInfo'/></div>
              <form action="/roomRate_edit.do" name="rateplanForm1" method="post" id="rateplanForm1">
              <s:hidden name="ratePlanVO.rp.ratePlanId" ></s:hidden>
              	<!--房价基本信息-->
         		<div class="pdA24">
                <div class="r_basic1">
                  <div class="r_basic2 mgR18">
                    <ul class="inq_wp">
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key='ccm.RestrictionsManagement.RateCode'/>：</span></div>
                        <div class="i_input">
                        	<s:textfield cssClass="fxt w120 required" name="ratePlanVO.rp.ratePlanCode"  readonly="true"></s:textfield>
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key='ccm.Rates.RateCategory'/>：</span></div>
                        <div class="i_input">
                        <s:select list="configMap['rpt']" name="ratePlanVO.rp.ratePlanType" listValue="codeNo+'-'+codeLabel" listKey="codeNo" cssClass="fxt w180"></s:select>
                        </div>
                      </li>
                    </ul>
                  </div>
                  <div class="r_basic2">
                    <ul class="inq_wp">
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key='ccm.Rates.BeginDate'/>：</span></div>
                        <div class="i_input">
                        <s:textfield name="ratePlanVO.rp.effectiveDate"  id="effectiveDate_rate" cssClass="fxt w120 required"></s:textfield>
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.EndDate"/>	：</span></div>
                        <div class="i_input">
                        <s:textfield name="ratePlanVO.rp.expireDate"  id="expireDate_rate" cssClass="fxt w120 required"></s:textfield>
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
                        	<input	class="fxt w240" name="ratePlanVO.rpi18n.description" readonly="readonly" 
                        		value="${ratePlanVO.rpi18n.description}"	type="hidden"/>
                        	<input	class="fxt w240" name="show_description" readonly="readonly" 
                        		value="${ratePlanName}"/>
                        	<s:hidden name="ratePlanVO.languageJsonArr"></s:hidden>
                        	&nbsp;<button type="button" class="btn_3 white mgR6 " id="switch_description"><fmt:message key="ccm.Rates.MultiLanguage"/></button>
                        	<div class="ft_layer basicNew DefaultLanguageNew" style="z-index:12;" id="ml_switch_defaultDesc">
                				<textarea  class="fxt w491 h150 " id="f_description"
											name="d_description" >${ratePlanVO.rpi18n.description}</textarea>
								<div class="ft_ctr1">
									<button type="button" class="btn_3 green mgR6"><fmt:message key="common.button.OK"/></button>
	        						<button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>	
								</div>
                			</div>
                        	<div class="ft_layer basicNew DescMoreLanguageNew" style="z-index:10;" id="ml_switch_description">
                				<table class="ccm_table2" >
                					<c:if test="${not empty ratePlanVO.ratePlanI18nList}">
										<c:forEach items="${ratePlanVO.ratePlanI18nList}" var="ratePlanI18n" varStatus="vstatus"> 
											<c:if test="${not empty ratePlanI18n.description }">
											<tr>
											    <td class="w20">${vstatus.index + 1}.</td>
												<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
															<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
															<c:forEach items="${configMap['languageList']}" var="lan" >
																<option value="${lan.codeNo}" ${lan.codeNo == ratePlanI18n.language?"selected":""}>${lan.codeLabel}</option>
															</c:forEach>
														</select> <br>
													<textarea  class="fxt w491 h150 " style="margin-top:5px;margin-bottom:5px;" 
															name="language.description" >${ratePlanI18n.description}</textarea>
												</td>
												<td class="w20">
													<div class="center">
														<a href="javascript:void[0];" onclick="deleteRow(this,'switch_description');" class="link_1 del_ifself">x</a>
													</div>
												</td>
											</tr>
											</c:if>	
										</c:forEach>
									</c:if>
									<tr id="mdl_switch_description" style="display:none;">  
										<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
											<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
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
										<td><a href="javascript:void[0];" class="link" onclick="addHtmlLanguage(this,'switch_description','language.description')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
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
				                      <input type="checkbox" id="roomType_${idx.index }" roomTypeId="${rl.roomTypeId}"
				                      	<c:if test="${fn:indexOf(ratePlanVO.rp.includeRoomType,rl.roomTypeCode)>-1 }">
				                      		checked="checked" onclick="checkRateRoomIsExists(this);"
				                      	</c:if>
				                      value="${rl.roomTypeCode }" >
				                      <span class="">${rl.roomTypeCode }&nbsp;${rl.roomTypeName }&nbsp;${rl.pmsCode }</span> 
				                    </label>
				                  </c:forEach>
                              </div>
                            </div>
                            <s:hidden name="ratePlanVO.rp.includeRoomType" id="roomTypeCode"></s:hidden>
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
                  				<div class="text w240 packageName">
                  				</div>
                            </div>
                          </div>
                          <div class="ft_layer abs packageDiv" id="packageDiv" style="width:399px;">
                            <div class=" n_overFlowY">
                              <div class="mgA6">
                              <c:forEach items="${configMap['packages']}" var="pk" varStatus="idxs">
                              		<label class="checkbox">
                              		<input type="checkbox" id="${pk.packageId }" 
	                              		  <c:forEach var="ratePackage" items="${ratePlanVO.ratePackageList }">
	                              		  	  <c:if test="${ratePackage.packageId == pk.packageId }">
						                      	checked="checked" 
						                      </c:if>
					                      </c:forEach>
				                      value="${pk.packageCode}" >
				                      <span class="">${pk.packageCode}&nbsp;${pk.packageName }</span>
				                    </label>
                               </c:forEach>
                              </div>
                            </div>
                            <s:hidden name="ratePlanVO.packageId"></s:hidden>
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
                  <div class="r_basic2">
                    <ul class="inq_wp">
                      <li>
                      	<div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.RateCode"/>：</span></div>
                        <div class="i_input">
                        	<s:textfield cssClass="fxt w240" name="ratePlanVO.rpi18n.ratePlanName"  maxlength="200"></s:textfield>
                        	&nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_ratePlanName"><fmt:message key="ccm.Rates.MultiLanguage"/></button>
                        	<div class="ft_layer basicNew NameMoreLanguageNew" style="z-index:10;" id="ml_switch_ratePlanName">
                				<table class="ccm_table2" style="width: 480px;">
                					<c:if test="${not empty ratePlanVO.ratePlanI18nList}">
										<c:forEach items="${ratePlanVO.ratePlanI18nList}" var="ratePlanI18n" varStatus="vstatus"> 
											<c:if test="${not empty ratePlanI18n.ratePlanName }">
											<tr>
											    <td class="w20">${vstatus.index + 1}.</td>
												<td><fmt:message key="common.Description"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
															<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
															<c:forEach items="${configMap['languageList']}" var="lan" >
																<option value="${lan.codeNo}" ${lan.codeNo == ratePlanI18n.language?"selected":""}>${lan.codeLabel}</option>
															</c:forEach>
														</select> &nbsp;&nbsp;
													<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" 
													value="${ratePlanI18n.ratePlanName}" />  
												</td>
												<td class="w20">
													<div class="center">
														<a href="javascript:void[0];" onclick="deleteRow(this,'switch_ratePlanName');" class="link_1 del_ifself">x</a>
													</div>
												</td>
											</tr>
											</c:if>	
										</c:forEach>
									</c:if>
									<tr id="mdl_switch_ratePlanName" style="display:none;">  
										<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
											<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
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
										<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_ratePlanName')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
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
                      <li style="margin-top:8px;">
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Market"/>：</span></div>
                        <div class="i_input">
                        <s:select cssClass="fxt" cssStyle="width:280px;" list="configMap['MarketVOList']" name="ratePlanVO.rp.marketCode" listKey="marketCode" listValue="marketCode+'-'+description">
                        </s:select>
                        
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Source"/>：</span></div>
                        <div class="i_input"> 
                         <s:select cssClass="fxt" cssStyle="width:280px;" list="configMap['sourceVOList']" name="ratePlanVO.rp.sourceCode" listKey="sourceCode" listValue="description">
                        </s:select>
                        </div>
                      </li>
              <%--        <li>
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
                       --%>
                    </ul>
                  </div>
                </div>
                <div class="r_basic1">
                  <div class="r_basic2 mgR18">
                    <ul class="inq_wp">
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.DisplaySequence"/>：</span></div>
                        <div class="i_input">
                          <s:textfield cssClass="fxt w60" name="ratePlanVO.rp.orderNum" maxLength="10"></s:textfield>
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Negotiated"/>：</span></div>
                        <div class="i_input">
                          <label class="radio inline">
                            <input type="radio" name="ratePlanVO.rp.isNegotiated" ${ratePlanVO.rp.isNegotiated ? 'checked="checked"' : '' } value="true" >
                            <span class=""><fmt:message key="common.Yes"/></span>
                          </label>
                          <label class="radio inline">
                            <input type="radio" name="ratePlanVO.rp.isNegotiated" ${ratePlanVO.rp.isNegotiated ? '' : 'checked="checked"' } value="false">
                            <span class=""><fmt:message key="common.Not"/></span>
                          </label>
                        </div>
                      </li>
                    </ul>
                  </div>
                  <div class="r_basic2">
                    <ul class="inq_wp">
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.Commission"/>%：</span></div>
                        <div class="i_input">
                        	<s:textfield cssClass="fxt w60" name="ratePlanVO.rp.commisionPercent"></s:textfield>
                        </div>
                      </li>
                      <li>
                        <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.ExternalControl"/>：</span></div>
                        <div class="i_input">
                          <label class="radio inline">
                            <input type="radio" name="ratePlanVO.rp.isExtraControl" ${ratePlanVO.rp.isExtraControl ? 'checked="checked"' : '' } id="optionsRadios_xy1" value="true">
                            <span class=""><fmt:message key="common.Yes"/></span> </label>
                          <label class="radio inline">
                            <input type="radio" name="ratePlanVO.rp.isExtraControl" ${ratePlanVO.rp.isExtraControl ? '' : 'checked="checked"' } id="optionsRadios_xy2" value="false">
                            <span class=""><fmt:message key="common.Not"/></span> </label>
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
                            <input type="radio" name="ratePlanVO.rp.isSuper" <s:if test="ratePlanVO.rp.isSuper==true"> checked="checked"</s:if>  value="true">
                            <span class=""><fmt:message key="common.Yes"/></span> </label>
                          <label class="radio inline">
                            <input type="radio" name="ratePlanVO.rp.isSuper" <s:if test="ratePlanVO.rp.isSuper==false"> checked="checked"</s:if> value="false">
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
                            <input type="radio" name="ratePlanVO.rp.priceValidate" <s:if test="ratePlanVO.rp.priceValidate==true"> checked="checked"</s:if>  value="true">
                            <span class=""><fmt:message key="common.Yes"/></span> </label>
                          <label class="radio inline">
                            <input type="radio" name="ratePlanVO.rp.priceValidate" <s:if test="ratePlanVO.rp.priceValidate==false"> checked="checked"</s:if> value="false">
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
								<div class="text w240 typeName"><fmt:message key="common.select.plesesSelect"/>	</div>
							</div>
							</div>
							<!--AccessCode查看隐藏层-->
							<div id="Two_show" class="ft_layer abs" style="width: 278px;">
								<div class=" n_overFlowY">
									<div class="mgA6">
										<c:forEach items="${configMap['accessCodeList']}" var="rl" varStatus="idx">
											<label class="checkbox"> <input type="checkbox" id="roomType_${idx.index }" value="${rl.customId}" name="ratePlanVO.customList" <s:if test="%{configMap['accessCodeSelList'].contains(#attr.rl.customId)}">checked="checked"</s:if>> <span class=""> <span class="span_roomTypeCode">${rl.accessCode} - ${rl.name}</span>  </span> </label>
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
									<s:textfield cssClass="fxt w240" name="ratePlanVO.rp.payment" maxlength="100"></s:textfield>
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
                 	<s:hidden name="ratePlanVO.cancelRuleJsonArr" id="cancelRuleJsonArr"></s:hidden>
                  	<div class="table2wp" id="CancelRule" style="display:none;">
                    	<div class="subtitle frow">
                        	<div class="float-right"><a href="javascript:;" class="btn_3 blue NR_click"><fmt:message key="common.button.New"/></a></div>
                        </div>
                        <div class="n_overFlowY">
                    	<table class="ccm_table2" id="cancelRuleTable">
                     <%--   	<tr>
                            	<td class="w180">2012-11-29 ~ 2014-12-31</td>
                            	<td class="w180">一，二，三，四，五，六，日</td>
                            	<td><a href="javascript:;" class="link NR_click">取消规则1XXX</a></td>
                                <td class="w20"><div class="center"><a href="javascript:;" class="link_1 del_ifself">x</a></div></td>
                            </tr>
                       --%> 	     
                        </table>
                        </div>
                    
                    </div>
                    
                 <!-- 可卖条件-->
                 <s:hidden name="ratePlanVO.rp.soldableCondition" id="soldableConditionJsonArr"></s:hidden>
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
                
              <s:hidden name="ratePlanVO.guaranteeJsonArr" id="guaranteeJsonArr"></s:hidden>
              <!--房价基本信息 新建 担保规则-->
          <div class="ft_layer basicNew GuaranteeRulesNew" style="width:600px;z-index:2000">
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
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.BeginDate"/>：</span></div>
                    <div class="i_input">
                      <!-- 担保规则 开始时间 -->
                      <input class="fxt w120" name="effectiveDate" id="effectiveDate_guarantee" type="text" value="" >
                    </div>
                    <div class="date_abs">
                      <div class="dateweek">
                        <div class="d_wp"> <span><fmt:message key="common.week.sunday"/></span>
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
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.EndDate"/>	：</span></div>
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
          <div class="ft_layer basicNew CancelRuleNew" style="width:600px;z-index:2000">
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
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.BeginDate"/>：</span></div>
                    <div class="i_input">
                      <input class="fxt w120" name="effectiveDate" id="effectiveDate_cancel" type="text" value="" >
                    </div>
                    <div class="date_abs">
                      <div class="dateweek">
                        <div class="d_wp"> <span><fmt:message key="common.week.sunday"/></span>
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
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.EndDate"/>	：</span></div>
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
                      <span class="typeinwp visuallyhidden">
                      <input class="fxt w60" type="text" value="" name="percent" title="">
                      %</span>
                      <label class="radio inline" style="padding-right:6px; padding-left:24px;">
                        <input type="radio" name="ccm_rdption4" id="BasicPriceNumber_New" value="option1">
                        <span class=""><fmt:message key='common.FixedAmount'/></span> </label>
                      <span class="typeinwp visuallyhidden">
                      <input class="fxt w60" type="text" value="" name="amount" title="">CNY</span> 
                        </div>
                    
                    </div>
                </li>
                
                <li>
                	<div class="l_side"><label class="checkbox inline"><span class=""><fmt:message key="ccm.Rates.LimitedPurchase"/></span></label></div>
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
         <c:if test="${configMap.showRefRateplan ==true }">    
             <div class="r_basic1">
                  <ul class="inq_wp">
                    <li>
                      <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.Rates.BaseRate"/>：</span></div>
                      <div class="i_input">
                      	<s:select list="noInheritRateMapList" cssClass="fxt w300"  
                      		id="BasicPriceChosen" value="%{ratePlanVO.rp.inheritRatePlanId}"  
                      		name="ratePlanVO.rp.inheritRatePlanId" listKey="ratePlanId" 
                      		listValue="ratePlanCode" headerKey="" headerValue="" ></s:select>
                      </div>
                      <span class="basicPriceMethod">
                      <label class="radio inline" style="padding-right:6px; padding-left:24px;">
                        <input type="radio" name="ccm_rdption3" id="BasicPricePercent" value="option1" ${ratePlanVO.rp.percent != null ? 'checked="checked"' : '' } >
                        <span class=""><fmt:message key='common.Percentage'/></span> </label>
                      	<span class="">
                      	<s:if test="ratePlanVO.rp.amount != null">
                      		<s:textfield name="ratePlanVO.rp.percent" cssClass="fxt w60" disabled="true"></s:textfield> 
                      	</s:if>
                        <s:else>
                        	<s:textfield name="ratePlanVO.rp.percent" cssClass="fxt w60"></s:textfield>
                        </s:else>
                      	%</span>
                      
                      <label class="radio inline" style="padding-right:6px; padding-left:24px;">
                        <input type="radio" name="ccm_rdption3" id="BasicPriceNumber" value="option1" ${ratePlanVO.rp.amount != null ? 'checked="checked"' : '' }>
                        <span class=""><fmt:message key='common.FixedAmount'/></span> </label>
                        <s:if test="ratePlanVO.rp.percent != null">
                        	<s:textfield name="ratePlanVO.rp.amount" cssClass="fxt w60" disabled="true"></s:textfield>
                        </s:if>
                        <s:else>
                        	<s:textfield name="ratePlanVO.rp.amount" cssClass="fxt w60"></s:textfield>
                        </s:else>
                     </span> </li>
                  </ul>
                </div>
          </c:if>   
             
              <div class="b_crl">
                <button type="button" onclick="submitForm();" id="btn_submitRateplan" class="btn_2 green mgR6"><fmt:message key="common.button.save"/></button>
                <button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
              </div>
            </div>
          </div>
           </form>
          <!--新建价格-->
          <div id="AddNewPrice" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
          <s:hidden name="rateDetailIdx"></s:hidden>
            <div class="pp_main">
              <div class="t_title"><fmt:message key="ccm.Rates.RateSetupEdit"/></div>
              <div class="pdA24 pdT12">
                
                <div class="p_title"><fmt:message key="ccm.Rates.RoomTypes"/></div>
                <div class="table2wp">
                <div class="n_overFlowY2">
                <table class="ccm_table2 roomRatePackage">
                			<tr>
                            	<th class="w20"><span class="p_ch1"><input type="checkbox" id="RoomTypeCheckAll" value="option1" ></span></th>
                            	<th class="w360"><fmt:message key="ccm.Rates.RoomTypesChoose"/></th>
                            	<th><fmt:message key="ccm.Rates.Packages"/></th>
                            </tr>
                            <s:iterator value="configMap['selRoomTypeList']" var="rt" status="sta">
		                   		<tr>
		                       	<td class="w20"><span class="p_ch1"><input type="checkbox" id="${rt.roomTypeId }" value="option1"></span></td>
		                       	<td class="w360">
		                       		<span class="room checked">${rt.roomTypeCode }&nbsp;${rt.roomTypeName }
		                       			<c:if test="${!empty rt.pmsCode}">_${rt.pmsCode}</c:if>
		                       		</span>
		                       	</td>
		                       	<td><a href="javascript:;" class="link BSP_click roomSelected">
		                       		<span id="roomPackageId_${sta.index}" class="checked"></span></a> <!-- 该房型的包价产品 -->
		                       		<a href="javascript:;" class="link BSP_click"><fmt:message key="common.button.add"/></a>
		                       	</td>
		                       </tr>
	                       </s:iterator>
                        </table>
                       </div>
                       </div>
                
                <!--包价产品-->
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
                      <input class="fxt w120" type="text" id="effectiveDate_rateDetail" value="" >
                    </div>
                    <div class="date_abs rateDetailWeek">
                      <div class="dateweek">
                        <div class="d_wp"> <span><fmt:message key="common.week.sunday"/></span>
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
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.EndDate"/>	：</span></div>
                    <div class="i_input">
                      <input class="fxt w120" type="text" id="expireDate_rateDetail" value="" >
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
                    <div class="i_input"><input id="2AdultValue" class="fxt w80" type="text" name="baseAmount" numberOfUnits="2" value="" ></div>
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
              	<!-- 调整价格 保存 -->
                <button type="button" class="btn_2 green mgR6" id="btn_saveRateDetail"><fmt:message key="common.button.save"/></button>
                <button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
              </div>
            </div>
          </div>
          
          
          
          <!-- 拆分日期 -->
          <form action="" name="splitRateDetailForm" id="splitRateDetailForm">
          <div id="splitRateDetailDiv" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
          <s:hidden name="splitRateDetailIdx"></s:hidden>
            <div class="pp_main">
              <div class="t_title"><fmt:message key="ccm.Rates.Split"/></div>
              <div class="pdA24 pdT12">
                <div class="p_title"><fmt:message key="ccm.Rates.Dates"/> </div>
                <ul class="list_input">
                  <li class="c_rel">
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.BeginDate"/>：</span></div>
                    <div class="i_input">
                      <input class="fxt w120 required" type="text" id="effectiveDate_splitRateDetail" value="" >
                    </div>
                    <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.time.EndDate"/>	：</span></div>
                    <div class="i_input">
                      <input class="fxt w120 required" type="text" id="expireDate_splitRateDetail" value="" >
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
                <button type="button" class="btn_2 green mgR6" onclick="saveSplitRateDetail();" id="btn_saveSplitRateDetail"><fmt:message key="common.button.save"/></button>
                <button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
              </div>
            </div>
          </div>
          </form>
        </div>
     	   <!--房价基本信息end-->
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="/pages/roomRate/rateModel.js"></script>
<script type="text/javascript" src="<c:url value='js/jHtmlArea-0.8.js'/>"></script>
<link rel="Stylesheet" type="text/css" href="<c:url value='css/jHtmlArea.css'/>" />
<script type="text/javascript" src="<c:url value='js/jHtmlArea.ColorPickerMenu-0.8.js'/>"></script>
<link rel="Stylesheet" type="text/css" href="<c:url value='css/jHtmlArea.ColorPickerMenu.css'/>" />
<script type="text/javascript">
function checkRateRoomIsExists(thi){
 	$.ajax({
        type: "post",
        dataType: "text",
        url: "/roomRate_checkRateRoomIsExists.do",
        data: "ratePlanId=${ratePlanId}&roomTypeId="+$(thi).attr("roomTypeId"),
        success: function(data){
        	if(data == 'true'){
        		alert('<fmt:message key="ccm.Rates.ErrorMessage.CanNotBeDeleted"/>！');
        		$(thi).prop("checked","true");
        	}
        }
	}); 
}


function hiddenOperate(){
	<c:if test="${isEdit}">
	//将新建按钮禁用
	$("#newRateDetail").remove();
	//将操作禁用
	$('#normalTB thead tr th:last-child,#normalTB tbody tr td:last-child').hide();
	</c:if>
}


//取消规则开始
var cancelRuleJson=[];
//担保规则开始
var guaranteeJson=[];
//房型房价
var rateDetailList=[];
//可卖条件
var soldableConditionJson=[];

function submitForm(){
	if (!$("#rateplanForm1").valid()) {
		return;
	}
	if(strIsNull($("input[type=checkbox][name='ratePlanVO.customList']:checked").val())){
		alert('<fmt:message key="ccm.Rates.message.AccessCode"/>');
		return;
	}
	if(guaranteeJson.length < 1){
		alert('<fmt:message key="ccm.Rates.ErrorMessage.GuaranteeType"/>');
		return;
	}
	var med ="";
	for(var i=0;i<soldableConditionJson.length;i++){
		if(soldableConditionJson[i].minEvenDay){
			med = soldableConditionJson[i].minEvenDay;
			break;
		}
	}
	if(med ==""){
		alert('<fmt:message key="ccm.Rates.message.MinimumStayThrough"/>');
		return;
	}
	
	if(!setLanguageJsonArr()){
		return;
	}
	$("#btn_submitRateplan").attr("disabled",true);
	$("#btn_submitRateplan").addClass("no_ald");
	$("#rateplanForm1").submit();
}

//设置房价房型
var roomTypeNameList='';
$('#roomTypeCodeDiv input:checked').each(function(){ 
	roomTypeNameList += $(this).val()+',';
});
$('.rommTypeClick .typeName').text(roomTypeNameList.substr(0,roomTypeNameList.lastIndexOf(',')));

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
	$('.rommTypeClick .typeName').text(roomTypeCode);
	$('#roomTypeCodeDiv').hide();
});

$('.rommTypeClick').bind('click',function(){
	$(this).next('#roomTypeCodeDiv').slideDown();
});

$('#roomTypeCodeDiv .closethis').bind('click',function(){
	$('#roomTypeCodeDiv').hide();
});
//设置已有房价打包
var packageNameList='';
$('#packageDiv input:checked').each(function(){ 
	packageNameList += $(this).val()+',';
});
$('.packageClick .packageName').text(packageNameList.substr(0,packageNameList.lastIndexOf(',')));

//房价包价产品
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
	$('#ratePlanVO_packageId').val(packageId);
	$('.packageClick .packageCode').text(packageCode);
	$('.packageClick .packageName').text(packageCode);
	$('#packageDiv').hide();
});

$('.packageClick').bind('click',function(){
	$(this).next('#packageDiv').slideDown();
});

$('#packageDiv .closethis').bind('click',function(){
	$('#packageDiv').hide();
});


function delRatePlan(ratePlanId){
	if(confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？")){
		window.location.href="/roomRate_delRatePlan.do?ratePlanId="+ratePlanId;
	}
}


var maxExpireDate="";
function delRateDetail(idx){
	if(confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？")){
		$.ajax({
	        type: "post",
	        dataType: "text",
	        url: "/roomRate_delRateDetail.do",
	        data: "rateDetailId="+rateDetailList[idx].rateDetailId+"&ratePlanId=${ratePlanVO.rp.ratePlanId}",
	        success: function(data){
	        	//刷新列表
	        	rateDetailList.remove(idx);
	    		showRateDetailList();
	    		maxExpireDate = getMaxDate();
	    		$("#expireDate_rate").datepicker("option", "minDate", maxExpireDate);
	        }
		});
	}
}

function getMaxDate(){
	var maxDate="";
	for(var i=0;i<rateDetailList.length;i++){
		var rateDetail = rateDetailList[i];
		var expireDate = rateDetail.expireDate;
		if(maxDate < expireDate){
			maxDate=expireDate;
		}
	}
	return maxDate;
}
function delCancelRuleOne(idx){
	if(confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？")){
		cancelRuleJson.remove(idx);
		showCancelRule();
	}
}
function editCancelRuleTr(idx,thi){
	var cancelRuleOneJson = (cancelRuleJson[idx]);
	$('.CancelRuleNew select[name="cancelId"]').attr("idx",idx);
	$('.CancelRuleNew select[name="cancelId"]').val(cancelRuleOneJson.cancelId);
	$('.CancelRuleNew input[name="effectiveDate"]').val(cancelRuleOneJson.effectiveDate);
	$('.CancelRuleNew input[name="expireDate"]').val(cancelRuleOneJson.expireDate);

	var l_ps=$(thi).position().left;
	var r_ps=$(window).width()-l_ps-45;
	var t_ps=$(thi).position().top+16;
	var t_ps2=$(thi).position().top+24;
	$('.CancelRuleNew').slideDown();
	if($(this).hasClass('blue')){
		$('.CancelRuleNew').css({left:'',right:r_ps,top:t_ps2});
	}
	else{
		$('.CancelRuleNew').css({left:l_ps,right:'',top:t_ps});
	}
	
	$('.CancelRuleNew input[type="checkbox"]').each(function(){
		var isApplyToWeek = $(this).attr("id");
		if(cancelRuleOneJson[isApplyToWeek]){
			$(this).prop("checked","true");
		}else{
			$(this).prop("checked","");
		}
	});
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
function showCancelRule(){
	$("#cancelRuleTable").empty();
	for(var i=0;i< cancelRuleJson.length;i++){
		var cancelRuleOneJson = (cancelRuleJson[i]);
		var cancelRuleTr = "";
		cancelRuleTr +='<tr><td class="w180">'+cancelRuleOneJson.effectiveDate +' ~ '+cancelRuleOneJson.expireDate+'</td>'
		cancelRuleTr +='<td class="w180">'+getWeekStr(cancelRuleOneJson) +'</td>'
		cancelRuleTr +='<td><a href="javascript:;" onclick="editCancelRuleTr('+i+',this);" class="link">'+cancelRuleOneJson.policyName+'</a></td>'
		cancelRuleTr +='<td class="w20"><div class="center"><a href="javascript:delCancelRuleOne('+i+');" class="link_1">x</a></div></td></tr>'
		$("#cancelRuleTable").append(cancelRuleTr);
	}
	
	$("#cancelRuleJsonArr").val(JSON.stringify(cancelRuleJson));
}
//取消规则结束
function delGuaranteeOne(idx){
	if(confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？")){
		guaranteeJson.remove(idx);
		showGuarantee();
	}
}
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

function editGuaranteeTr(idx,thi){
	var guaranteeOneJson = (guaranteeJson[idx]);
	$('.GuaranteeRulesNew select[name="guaranteeId"]').attr("idx",idx);
	$('.GuaranteeRulesNew select[name="guaranteeId"]').val(guaranteeOneJson.guaranteeId);
	$('.GuaranteeRulesNew input[name="effectiveDate"]').val(guaranteeOneJson.effectiveDate);
	$('.GuaranteeRulesNew input[name="expireDate"]').val(guaranteeOneJson.expireDate);

	var l_ps=$(thi).position().left;
	var r_ps=$(window).width()-l_ps-45;
	var t_ps=$(thi).position().top+16;
	var t_ps2=$(thi).position().top+24;
	$('.GuaranteeRulesNew').slideDown();
	if($(this).hasClass('blue')){
		$('.GuaranteeRulesNew').css({left:'',right:r_ps,top:t_ps2});
	}
	else{
		$('.GuaranteeRulesNew').css({left:l_ps,right:'',top:t_ps});
	}
	
	$('.GuaranteeRulesNew input[type="checkbox"]').each(function(){
		var isApplyToWeek = $(this).attr("id");
		if(guaranteeOneJson[isApplyToWeek]){
			$(this).prop("checked","true");
		}else{
			$(this).prop("checked","");
		}
	});
}
function showGuarantee(){
	$("#guaranteeTable").empty();
	for(var i=0;i< guaranteeJson.length;i++){
		var guaranteeOneJson = (guaranteeJson[i]);
		var guaranteeTr = "";
		guaranteeTr +='<tr><td class="w180">'+guaranteeOneJson.effectiveDate +' ~ '+guaranteeOneJson.expireDate+'</td>'
		guaranteeTr +='<td class="w180">'+getWeekStr(guaranteeOneJson) +'</td>'
		guaranteeTr +='<td><a href="javascript:;" onclick="editGuaranteeTr('+i+',this);" class="link">'+guaranteeOneJson.policyName+'</a></td>'
		guaranteeTr +='<td class="w20"><div class="center"><a href="javascript:delGuaranteeOne('+i+');" class="link_1">x</a></div></td></tr>'
		$("#guaranteeTable").append(guaranteeTr);
	}
	$("#guaranteeJsonArr").val(JSON.stringify(guaranteeJson));
}

//添加一项多语言
function addLanguage(t,switch_Id){
	addLanguageRow(t,switch_Id);
}

//移除一行语言
function deleteRow(t,switch_Id){
	deleteLanguageRow(t,switch_Id);
}

function setLanguageJsonArr(){
	//这是房价名称和描述的多语言项
	var languageTRS = $('#ml_switch_description').find('table>tbody>tr:not(:last)');
	var languageRTS = $('#ml_switch_ratePlanName').find('table>tbody>tr:not(:last)');

	//如果长度项都是1
	if(languageTRS.length==1&&languageRTS.length==1){
		$("input[name='ratePlanVO.languageJsonArr']").val('');
		$('.NameMoreLanguageNew').slideUp();
		$('.DescMoreLanguageNew').slideUp();
		return true;
	}
	
	var moreLanguagesJson = '';
	var tempNameCodes = '';
	var flag = true;
	
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
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',ratePlanName:'"+escapeAcutes(name)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;

	var tempDescCodes = '';
	
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
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
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
			//判断
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.Rates.RateCode"/>');
				arry.push('<fmt:message key="ccm.Rates.DescriptionCorporate"/>');
				arry.push('<fmt:message key="ccm.Rates.RateCode"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeListError"/>';
				alert(i18n_replace(str,arry));
				//alert('【房价名称】多语言与【房价描述】多语言列表不一致,【房价名称】多语言中不存在语种:'+sel.find("option:selected").text()+',请添加.');
				flag = false;
				return false;
			}
			//拼接语言种类
			tempDescCodes += ',' + sel.val() + ',';
			
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',description:'"+escapeAcutes(description)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	//验证 房价名称和房价描述要相匹配
	languageRTS.each(function(){
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_ratePlanName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var selStr = ','+sel.val()+',';
			//判断
			if(tempDescCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.Rates.RateCode"/>');
				arry.push('<fmt:message key="ccm.Rates.DescriptionCorporate"/>');
				arry.push('<fmt:message key="ccm.Rates.DescriptionCorporate"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeListError"/>';
				//alert('【房价名称】多语言与【房价描述】多语言列表不一致,【房价描述】多语言中不存在语种:'+sel.find("option:selected").text()+',请添加.');
				flag = false;
				return false;
			}
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	if(flag){
		//如果校验通过
		if(!strIsNull(moreLanguagesJson)){
			moreLanguagesJson = '['+moreLanguagesJson.substring(1)+']';
			$("input[name='ratePlanVO.languageJsonArr']").val(moreLanguagesJson);
			$('.NameMoreLanguageNew').slideUp();
			$('.DescMoreLanguageNew').slideUp();
		}
	}
	
	return flag;
}

//担保规则结束
$(document).ready(function() {
	var gstr = '<s:property escape="false" value="guaranteeJsonArr"/>'
	if(gstr != ""){//获取已存的担保规则
		guaranteeJson = convertStrToJson(gstr);
		showGuarantee();
	}
	
	var cstr = '<s:property escape="false" value="cancelRuleJsonArr"/>'
	if(gstr != ""){//获取已存的取消规则
		cancelRuleJson = convertStrToJson(cstr);
		showCancelRule();
	}
	var scstr = '<s:property escape="false" value="ratePlanVO.rp.soldableCondition"/>'
		if(scstr != ""){//获取已存的可卖条件
			soldableConditionJson = convertStrToJson(scstr);
			showSoldableCondition();
		}
	
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
		var effectiveDateCode = validateYYYYMMDD(effectiveDate);
		if(effectiveDateCode!='success'){
			alert(getErrorMsg(effectiveDateCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
			return false;
		}
		var expireDateCode = validateYYYYMMDD(expireDate);
		if(expireDateCode!='success'){
			alert(getErrorMsg(expireDateCode,'<fmt:message key="common.time.EndDate"/>	','yyyy-MM-DD'));
			return false;
		}
		var cancelRuleOne = convertStrToJson(cancelRuleOneStr);
		if(!validateObj(cancelRuleOne,'cancelId',cancelRuleJson,idx,true)){//忽略值比较
			alert('<fmt:message key="ccm.Rates.ErrorMessage.RuleAlreadyExisted"/>！');
			return;
		}
		if(idx >= 0){
			cancelRuleOne.rateCancelRelationshipId = cancelRuleJson[idx].rateCancelRelationshipId;
			cancelRuleJson.splice(idx,1,cancelRuleOne);
		}else{
			cancelRuleJson.push(cancelRuleOne);
		}
		showCancelRule();
		$(this).parents('.basicNew').hide();
	});
	
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
		
		
		var effectiveDateCode = validateYYYYMMDD(effectiveDate);
		if(effectiveDateCode!='success'){
			alert(getErrorMsg(effectiveDateCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
			return false;
		}
		var expireDateCode = validateYYYYMMDD(expireDate);
		if(expireDateCode!='success'){
			alert(getErrorMsg(expireDateCode,'<fmt:message key="common.time.EndDate"/>	','yyyy-MM-DD'));
			return false;
		}
		var guaranteeOne = convertStrToJson(guaranteeOneStr);
		if(!validateObj(guaranteeOne,'guaranteeId',guaranteeJson,idx,false)){
			alert('<fmt:message key="ccm.Rates.ErrorMessage.RuleAlreadyExisted"/>！');
			return;
		}
		
		if(idx >= 0){
			guaranteeOne.rateGuaranteeRelationshipId = guaranteeJson[idx].rateGuaranteeRelationshipId;
			guaranteeJson.splice(idx,1,guaranteeOne);
		}else{
			guaranteeJson.push(guaranteeOne);
		}
		showGuarantee();
		$(this).parents('.basicNew').hide();
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
		
		
		if(obj.lastMinutesBeginTime !='' && obj.lastMinutesEndTime=='' ){
			alert('<fmt:message key="ccm.InventoryManagement.error.endDateNull"/>！');
			return;
		}
		if(obj.lastMinutesBeginTime =='' && obj.lastMinutesEndTime!='' ){
			alert('<fmt:message key="ccm.InventoryManagement.error.beginDateNull"/>！');
			return;
		}
		if(obj.lastMinutesBeginTime !='' && obj.lastMinutesEndTime!=''){
			if(obj.lastMinutesPercent=='' && (obj.lastMinutesAmount== undefined || obj.lastMinutesAmount=='')){
				alert('<fmt:message key="ccm.Rates.ErrorMessage.PercentageAndFixedAmount"/>！');
				return;
			}
		}else{
			if(obj.lastMinutesPercent!=''|| (obj.lastMinutesAmount!= undefined && obj.lastMinutesAmount!='')){
				alert('<fmt:message key="ccm.InventoryManagement.error.beginDateNull"/>！');
				return;
			}
		}
		
		
		var idx =$('.SoldableConditionNew').attr("idx");
		if(idx >= 0){
			soldableConditionJson.splice(idx,1,obj);
		}else{
			soldableConditionJson.push(obj);
		}
// 		console.log(JSON.stringify(obj));
		showSoldableCondition();
		$(this).parents('.basicNew').hide();
	});
	
	$('#BasicPriceChosen').change(function(){
		if($('#BasicPriceChosen').val()==''){
			$("#ratePlanVO_rp_percent").val("");
			$("#ratePlanVO_rp_amount").val("");
		}
	});
	$('#BasicPricePercent,#BasicPriceNumber,#BasicPricePercent_New,#BasicPriceNumber_New').change(function(){
		if($("#BasicPricePercent").is(':checked')){
			$("#ratePlanVO_rp_amount").val("");
			$("#ratePlanVO_rp_amount").attr("disabled",true);
			$("#ratePlanVO_rp_percent").attr("disabled",false);
		}
		if($("#BasicPriceNumber").is(':checked')){
			$("#ratePlanVO_rp_percent").val("");
			$("#ratePlanVO_rp_amount").attr("disabled",false);
			$("#ratePlanVO_rp_percent").attr("disabled",true);
		}
		
		if($("#BasicPricePercent_New").is(':checked')){
			$(".SoldableConditionNew input[name='amount']").val("");
		}
		if($("#BasicPriceNumber_New").is(':checked')){
			$(".SoldableConditionNew input[name='percent']").val("");
		}
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
		$('.GuaranteeRulesNew select[name="guaranteeId"]')[0].selectedIndex=0;
		$('.GuaranteeRulesNew input[name="effectiveDate"]').val('');
		$('.GuaranteeRulesNew input[name="expireDate"]').val('');
		$('.GuaranteeRulesNew select[name="guaranteeId"]').attr("idx",-1);
		
		$("#effectiveDate_guarantee").datepicker("option", "minDate", new Date());
		$("#effectiveDate_guarantee").datepicker("option", "maxDate", maxExpireDate);
		$("#effectiveDate_guarantee").val(nowFormat());
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
		$('.CancelRuleNew select[name="cancelId"]')[0].selectedIndex=0;
		$('.CancelRuleNew input[name="effectiveDate"]').val('');
		$('.CancelRuleNew input[name="expireDate"]').val('');
		$('.CancelRuleNew select[name="cancelId"]').attr("idx",-1);
		
		$("#effectiveDate_cancel").datepicker("option", "minDate", new Date());
		$("#effectiveDate_cancel").datepicker("option", "maxDate", maxExpireDate);
	});
	
	$('.SC_click').bind('click',function(){
		$('.SoldableConditionNew').attr("idx",-1);
		var l_ps=$(this).position().left;
		var r_ps=873-l_ps;
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
	
	//展开房型打包div
	$('.BSP_click').bind('click',function(){
		var roomTypeId = $(this).parents('tr').find(':checkbox').attr("id");
		var idx = $(this).parents('tr')[0].rowIndex - 1;	 //获取当前行，从数组中填充值
		$('#roomPackageDiv #roomTypeId').val(roomTypeId);
		$('#roomType_idx').val(idx);
		
		$('#roomPackageDiv input[type="checkbox"]').each(function(){
			$(this).prop("checked","");		//点击清空包价产品
		});
		//var rateDetailIdx = $("#rateDetailIdx").val();
		var roomRate = roomRateList[idx];
		if(roomTypeId == roomRate.roomTypeId){
			$('#roomPackageDiv input[type="checkbox"]').each(function(){
				if(roomRate.roomPackageList != null){
					for(var j=0;j<roomRate.roomPackageList.length;j++){
						var pkId = roomRate.roomPackageList[j].packageId;
						if($(this).attr("id")==pkId){
							$(this).prop("checked","true");
						}
					}
				}
			});
		}
		
		var l_ps=$(this).position().left;
		var t_ps=$(this).position().top+16;
		$('.priceBagService').slideDown();
		$('.priceBagService').css({left:l_ps,right:'',top:t_ps});
		});
	$('.ccm_click2').bind('click',function(){
		$(this).next('.ccm_click2_show').slideDown();
		});
	
	$('.ccm_click2_show .closethis').bind('click',function(){
		$(this).parents('.ccm_click2_show').hide();
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
			              ],
			hourText:'<fmt:message key="time.Hour"/>',
			minuteText:'<fmt:message key="time.Minute"/>',
			timeText:'<fmt:message key="time.Time"/>',
			currentText:'<fmt:message key="time.Present"/>',
			closeText:'<fmt:message key="common.button.close"/>'
		}
	
	//开始/结束日期
	$("#effectiveDate_rate").datepicker($.extend(dpconfig,{
		minDate:new Date(),
		onClose : function(v) {
			$("#expireDate_rate").datepicker("option", "minDate", '<fmt:formatDate value="${ratePlanVO.maxDetailExpireDate}" pattern="yyyy-MM-dd"/>');
			
			$("#effectiveDate_guarantee").datepicker("option", "minDate", v);
			$("#effectiveDate_cancel").datepicker("option", "minDate", v);
			$("#effectiveDate_rateDetail").datepicker("option", "minDate", v);
			$("#effectiveDate_splitRateDetail").datepicker("option", "minDate", v);
		}
	}));
	maxExpireDate='<fmt:formatDate value="${ratePlanVO.maxDetailExpireDate}" pattern="yyyy-MM-dd"/>';
	$("#expireDate_rate").datepicker($.extend(dpconfig, {
		minDate:maxExpireDate,
		onClose : function(v) {
			$("#effectiveDate_rate").datepicker("option", "maxDate", v);
			
			$("#expireDate_guarantee").datepicker("option", "maxDate", v);
			$("#expireDate_cancel").datepicker("option", "maxDate", v);
			$("#expireDate_rateDetail").datepicker("option", "maxDate", v);
			$("#expireDate_splitRateDetail").datepicker("option", "maxDate", v);
		}
	}));
	
	//房价担保规则和取消规则时间控件
	$("#effectiveDate_guarantee").datepicker($.extend(dpconfig, {
		minDate:$("#effectiveDate_rate").val(),
		onClose : function(v) {
			
			$("#expireDate_guarantee").datepicker("option", "minDate", v);
		},
		beforeShow: function (input, inst) {
	         $.datepicker._pos = $.datepicker._findPos(input);
	         $.datepicker._pos[1] += input.offsetHeight-document.body.scrollTop;
	     }
	}));
	$("#expireDate_guarantee").datepicker($.extend(dpconfig, {
		maxDate:$("#expireDate_rate").val(),
		onClose : function(v) {
			$("#effectiveDate_guarantee").datepicker("option", "maxDate", v);
		},
		 beforeShow: function (input, inst) { 
	         $.datepicker._pos = $.datepicker._findPos(input);
	         $.datepicker._pos[1] +=input.offsetHeight-document.body.scrollTop;  
	     }
	}));
	$("#effectiveDate_cancel").datepicker( {
		minDate:$("#effectiveDate_rate").val(),
		onClose : function(v) {
			$("#expireDate_cancel").datepicker("option", "minDate", v);
		},
		beforeShow: function (input, inst) {
	         $.datepicker._pos = $.datepicker._findPos(input);
	         $.datepicker._pos[1] += input.offsetHeight-document.body.scrollTop;
	     }
	});
	$("#expireDate_cancel").datepicker($.extend(dpconfig, {
		maxDate:$("#expireDate_rate").val(),
		onClose : function(v) {
			$("#effectiveDate_cancel").datepicker("option", "maxDate", v);
		},
		beforeShow: function (input, inst) {
	         $.datepicker._pos = $.datepicker._findPos(input);
	         $.datepicker._pos[1] += input.offsetHeight-document.body.scrollTop;
	     }
	}));
	
	
	$("#effectiveDate_rateDetail").datepicker($.extend(dpconfig,{
		minDate:$("#effectiveDate_rate").val(),
		onClose : function(v) {
			v = addHRToStr(v);
			$("#effectiveDate_rateDetail").val(v);
			$("#expireDate_rateDetail").datepicker("option", "minDate", v);
		}
	}));
	$("#expireDate_rateDetail").datepicker($.extend(dpconfig, {
		maxDate:$("#expireDate_rate").val(),
		onClose : function(v) {
			v = addHRToStr(v);
			$("#expireDate_rateDetail").val(v);
			$("#effectiveDate_rateDetail").datepicker("option", "maxDate", v);
		}
	}));
	//房价拆分时间控件
	$("#effectiveDate_splitRateDetail").datepicker($.extend(dpconfig,{
		minDate:$("#effectiveDate_rate").val(),
		onClose : function(v) {
			$("#expireDate_splitRateDetail").datepicker("option", "minDate", v);
		},
		beforeShow: function (input, inst) {
	         $.datepicker._pos = $.datepicker._findPos(input);
	         $.datepicker._pos[1] += input.offsetHeight;
	     }
	}));
	$("#expireDate_splitRateDetail").datepicker($.extend(dpconfig,{
		maxDate:$("#expireDate_rate").val(),
		onClose : function(v) {
			$("#effectiveDate_splitRateDetail").datepicker("option", "maxDate", v);
		},
		beforeShow: function (input, inst) {
	         $.datepicker._pos = $.datepicker._findPos(input);
	         $.datepicker._pos[1] += input.offsetHeight;
	     }
	}));
	
	var start = $("#startDate");
	var end = $("#endDate");
	jtimepicker(start, end);
	start.bind('click',function(){
		$(document).scrollTop(1);
	});
	end.bind('click',function(){
		$(document).scrollTop(1);
	});
	
	$('.ui-datepicker-close').bind('click',function(){
		$('#ui-datepicker-div').hide();
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
		var checklist = document.getElementsByName("ratePlanVO.customList");
		for(var i=0;i<checklist.length;i++){
		      checklist[i].checked = !checklist[i].checked;
		}
		//$("input[name='ratePlanVO.customList']").attr("checked",false);
	});
	//AccessCode选择
	var roomTypeName='';
	$('#Two_show input:checked').next('span').each(function(){ 
		roomTypeName += $(this).find("span.span_roomTypeCode").text()+",";
	});
	if(roomTypeName!=''){
		$('#Two_click .typeName').text(roomTypeName.substr(0,roomTypeName.lastIndexOf(',')));
	}
	$('#Two_show .confirmthis').click(function(){
		showAccessCode();
		$('#Two_show').hide();
	});
	
	
	$('input[name="show_description"]').focus(function(){
		if($('.DescMoreLanguageNew').is(':hidden')){
			//设置为HTML编辑器
			setHtmlArea($('#f_description'));
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
		$('input[name="ratePlanVO.rpi18n.description"]').val($('#f_description').val());
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
			var name_input = $("input[name='ratePlanVO.rpi18n.ratePlanName']");
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
});

//AccessCode
function showAccessCode(){
	var roomTypeName='';
	$('#Two_show input:checked').next('span').each(function(){ 
		roomTypeName += $(this).find("span.span_roomTypeCode").text()+",";
	});
	$('#Two_click .typeName').text(roomTypeName.substr(0,roomTypeName.lastIndexOf(',')));
}

function getFirstPrice(rateAmountList){
	rateAmountList.sort(function (a,b)
	{
		return a.numberOfUnits - b.numberOfUnits;
	});
	for(var j=0;j<rateAmountList.length;j++){
		if(rateAmountList[j].baseAmount != ''){
			return rateAmountList[j].baseAmount;
		}
	}
	return '';
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

//设置为HTML编辑器
function setHtmlArea(textareaC){
	textareaC.htmlarea({
        toolbar: [
      			  ["html"],
                  ["bold", "italic", "underline", "strikethrough", "|", 
                   	"increaseFontSize","decreaseFontSize","forecolor"],
                  ["p", "h1", "h3", "h5"],
                  ["indent", "outdent"],
                  ["justifyleft", "justifycenter", "justifyright"],
                  ["orderedlist", "unorderedlist"],
                  ["link", "unlink", "horizontalrule"]
              ],
              toolbarText: $.extend({}, jHtmlArea.defaultOptions.toolbarText, {
                    "bold": "fett",
                    "italic": "kursiv",
                    "underline": "unterstreichen"
              })
    });
}

function addHtmlLanguage(t,switch_Id,c_name){
	var len=$(t).parent().parent().parent().children().length-1;
	if(len>=10){
		alert('不超过10条');
		return false;
	}
	
	$(t).parent().parent().before('<tr><td class="w20">'+len+'.</td>'+$('#mdl_'+switch_Id).html()+'</tr>');
	var newTxtArea = $(t).parent().parent().prev('tr').find('textarea[name="'+c_name+'"]');
	setHtmlArea(newTxtArea);
}
/***
 * ignoreCompareValue 是否忽略比较值
 */
function validateObj(objNew,objId,objList,idx,ignoreCompareValue){
	for(var i=0;i<objList.length;i++){
		var objOld = objList[i];
		if(i==idx){
			continue;
		}
		if(ignoreCompareValue || objOld[objId] == objNew[objId] ){
			if((objOld.effectiveDate <= objNew.effectiveDate && objOld.expireDate >= objNew.effectiveDate ) 
            		|| (objOld.effectiveDate <= objNew.expireDate && objOld.expireDate >= objNew.expireDate)
            		|| (objOld.effectiveDate > objNew.effectiveDate && objOld.expireDate < objNew.expireDate)){
                var weeks = getWeekStr(objOld).split(',');
                var weeksValidate = getWeekStr(objNew).split(',');
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
</script>
<jsp:include page="/pages/roomRate/rateDetail.jsp"></jsp:include>


