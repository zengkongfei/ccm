<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.PropertyList.PropertyEstablishment"/> </div>
        <s:form id="hotelForm" action="/hotel_save.do" method="post">
        <appfuse:ccmToken name="token"></appfuse:ccmToken>
        <s:hidden id="f_hotelId" name="hotelvo.hotelId"/>
        <s:hidden id="f_hotelMId" name="hotelvo.hotelMId"/>
        <s:hidden id="f_hotelI18ns" name="f_hotelI18ns" />
        <s:hidden id="f_picId" name="hotelvo.picId" />
        <s:hidden id="f_logoPicId" name="hotelvo.logoPicId" />
        <s:hidden id="f_cssFileId" name="hotelvo.cssFileId" />
        <div class="c_whitebg pdA12">
          <div class="mgB24">
            <ul class="list_input">
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_hotelCode" name="hotelvo.hotelCode" cssClass="fxt w120 required" maxlength="16"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.UserActivityLog.PropertyName"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_hotelName" name="hotelvo.hotelName" cssClass="fxt w240 required" maxlength="128"></s:textfield>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_hotelName"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_hotelName" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:138px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" 
													value="${hotelI18n.hotelName}" maxlength="128"/> 
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_hotelName');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_hotelName" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:138px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select>&nbsp;&nbsp;
								<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" maxlength="128"/> 
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_hotelName');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_hotelName')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.PropertyList.PropertyNameUsedBefore"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_hotelUsedName" name="hotelvo.hotelUsedName" cssClass="fxt w240" maxlength="128"></s:textfield>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_usedName"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_usedName" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus">
								<c:if test="${not empty hotelI18n.hotelUsedName }"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:138px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key='ccm.error.051'/>:<input type="text" class="fxt w180  " style="margin-top:5px;margin-bottom:5px;" name="language.userdName"
													value="${hotelI18n.hotelUsedName}" maxlength="128" />  
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_usedName');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
								</c:if>
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_usedName" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:138px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select>&nbsp;&nbsp;
								<fmt:message key='ccm.error.051'/>:<input type="text" class="fxt w180  " style="margin-top:5px;margin-bottom:5px;" name="language.userdName" maxlength="128"/>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_usedName');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_usedName')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.PropertyList.PropertyAbbreviation"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_hotelShortName" name="hotelvo.hotelShortName" cssClass="fxt w240" maxlength="64"></s:textfield>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_shortName"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_shortName" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<c:if test="${not empty hotelI18n.hotelShortName }"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:138px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key='ccm.error.052'/>:<input type="text" class="fxt w180" style="margin-top:5px;margin-bottom:5px;" name="language.shortName"
													value="${hotelI18n.hotelShortName}" maxlength="64"/>
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_shortName');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
								</c:if>
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_shortName" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:138px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select>&nbsp;&nbsp;
								<fmt:message key='ccm.error.052'/>:<input type="text" class="fxt w180" style="margin-top:5px;margin-bottom:5px;" name="language.shortName" maxlength="64"/>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_shortName');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_shortName')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.hotel.PMSAccount"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_pmsAccount" name="hotelvo.pmsAccount" cssClass="fxt w120" maxlength="50"></s:textfield>
                </div>
              </li>
              <li>
              	<div class="i_title"><span class="text"><fmt:message key="ccm.PropertyList.PropertyLogo"/>：${hotlvo.logoPic }</span></div>
                <div class="i_input">
                	<input id="logoPic" type="file" size="50" name="file" class="w275"
                		<c:if test="${not empty hotelvo.logoPic}">style="display:none;"</c:if>>
                	<button class="btn_2 black" id="buttonUpload" onclick="return logoFileUpload();"
                		<c:if test="${not empty hotelvo.logoPic}">style="display:none;"</c:if>><fmt:message key="common.Upload"/></button>
                	<button class="btn_2 black" id="buttonDelete" onclick="return logoFileDelete();"
                		<c:if test="${empty hotelvo.logoPic}">style="display:none;"</c:if>><fmt:message key="common.button.delete"/></button>
               		<br><br>
               		<img id="logoImage" name="logoImage" src="
               			<c:if test="${not empty hotelvo.logoPic}">${hotelvo.pictureUrlFolder}${hotelvo.logoPic.url}</c:if>" 
               			width="80" height="80"  <c:if test="${empty hotelvo.logoPic}">style="display:none;"</c:if>>
                </div>
              </li>
              <!-- css文件 -->
               <li>
              	<div class="i_title"><span class="text"><fmt:message key="ccm.hotel.css.HotelCSS"/>：</span></div>
                <div class="i_input">
                	<input id="cssFile" type="file" size="50" name="file" class="w275"
                		<c:if test="${not empty hotelvo.cssFileId}">style="display:none;"</c:if>>
                	<button class="btn_2 black" id="buttonUploadCSS" onclick="return cssFileUpload();"
                		<c:if test="${not empty hotelvo.cssFileId}">style="display:none;"</c:if>><fmt:message key="common.Upload"/></button>
                	<button class="btn_2 black" id="buttonDeleteCSS" onclick="return cssFileDelete();"
                		<c:if test="${empty hotelvo.cssFileId}">style="display:none;"</c:if>><fmt:message key="common.button.delete"/></button>
               		<br><br>
               		<a href="${ hotelvo.cssUrl}">${ hotelvo.cssName}</a>
               				<br><br>
               		<span class="star">
               		<fmt:message key="ccm.hotel.css.HotelCSSTip"/>.
               		</span>
               		<br><br>
                </div>
              </li>
              
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.ReservationMonitorReport.OfficialWebsite"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_officialWebsite" name="hotelvo.officialWebsite" cssClass="fxt w360" maxlength="450"></s:textfield>
                </div>
              </li>
              
              <li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PropertyList.PropertyPhotos"/>：</span>
					</div>
					<div class="i_input w480">
						<div class="div_upload_photo">
							<div id="Div_Files_Area">
								<div id="Div_Toolbar" class="div_upload_toolbara">
									<span id="Span_Add_Img" class="div_upload_img"> <span id="swfuploadplaceholder"></span> </span> <span id="Span_Ul_Img" class="span_ul_img"></span> <span id="Span_Tip_HasFile"><fmt:message key="ccm.upload.AtleastOne"/></span>
								</div>
							</div>
							<div class="div_upload_mc" id="Div_Upload_Area"></div>
							<div class="div_upload_tips" id="Div_Upload_Tip">
								<span class="span_left">0<fmt:message key="upload.009"/></span> <span class="span_right"><fmt:message key="ccm.upload.AllCancelled"/></span>
								<div style="clear: both;"></div>
							</div>
						</div>
					</div>
			   </li>
			   <c:if test="${not empty hotelvo.pictureList}">
			   		<li>
					<div class="i_title invisible">
						<span class="star"></span><span class="text">&nbsp;</span>
					</div>
					<div class="i_input ">
						<span id="picShow">
							<div class="div_upload_container">
								<span id="Span_Imgcon_Close" class="EIB div_imgcon_close"></span>
								<c:forEach items="${hotelvo.pictureList}" var="picture">
									<div class="div_img" picId="${picture.picId}">
										<img src="${hotelvo.pictureUrlFolder} ${picture.url}" />
									</div>
								</c:forEach>
								<div class="clearboth"></div>
							</div> 
						</span>
					</div>
			  		</li>
			   </c:if>
              <li class="col3_1">
               <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.BasicConfiguration.ChainName"/>：</span></div>
               <div class="i_input">
                 <%-- <s:select id="f_chainId" name="hotelvo.chainId" list="chainvoList" listKey="chainId" listValue="chainName"
					headerKey="" headerValue="请选择" cssClass="fxt w240 required" onchange="changeChain();">
			  	 </s:select> --%>
			  	 
			  	 <select id="f_chainId" name="hotelvo.chainId" class="fxt w240 required" onchange="changeChain();">
			  	 	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
			  	 	<c:forEach items="${chainvoList }" var="chainvo">
			  	 		<option value="${chainvo.chainId }"  <c:if test="${chainvo.chainId==hotelvo.chainId }">selected="selected"</c:if> >${chainvo.chainName }</option>
			  	 	</c:forEach>
			  	 </select>
			  	 
			  	 
               </div>
              </li>
              <li class="col3_1">
               <div class="i_title"><span class="text"><fmt:message key="ccm.BrandList.BrandName"/>：</span></div>
               <div class="i_input">
	             <select id="f_brandId" name="hotelvo.brandId" brandId="${hotelvo.brandId}" class="fxt w240 ">
					<option value=""><fmt:message key="common.select.plesesSelect"/></option>
				 </select>
               </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.EstablishmentTime"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="InputDateStart" name="hotelvo.whenBuilt" cssClass="fxt w120" />
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PropertyList.OverseasorNot"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_domestic" name="hotelvo.domestic" />
                  </label>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.PropertyLocation"/>：</span></div>
                <div class="i_input" id="chianDiv">
				  	<select class="fxt w120" id="f_countryCode" name='hotelvo.countryCode'>
							<!-- <option value="China">中国</option> -->
							<c:forEach items="${countryList }" var="city">
								<option value="${city.cityCode }">${city.cityName }</option>
							</c:forEach>
					</select>
					<span class="ChinaArea" >
						<select id='f_privince' name='hotelvo.privinceCode' class='fxt required w120'>
							<option value=""></option>
							<c:forEach items="${privinceList }" var="city">
								<option value="${city.cityCode }" attrId="${city.id }" <c:if test="${city.cityCode==hotelvo.privinceCode }">selected="selected"</c:if>  >${city.cityName }</option>
							</c:forEach>
							
						</select> 
						<select id='f_city' name='hotelvo.city' class='fxt required w120'}>
							<c:forEach items="${cityList }" var="city">
								<option value="${city.cityCode }" attrId="${city.id }" <c:if test="${city.cityCode==hotelvo.city }">selected="selected"</c:if>  >${city.cityName }</option>
							</c:forEach>
						</select> 
						<select id='f_areaCode' name='hotelvo.areaCode' class='fxt w120'>
							<c:forEach items="${areaCodeList }" var="city">
								<option value="${city.cityCode }" attrId="${city.id }" <c:if test="${city.cityCode==hotelvo.areaCode }">selected="selected"</c:if>  >${city.cityName }</option>
							</c:forEach>
						</select>
						<input type="hidden" name="hotelvo.cityName" id="cityNameId">
					</span>
                </div>
                <!-- 
	                <div class="i_input" id="foreignDiv">
					  	国家代码:<s:textfield id="ff_countryCode" name="hotelvo.countryCode" cssClass="fxt w120"></s:textfield>
	                </div>
                 -->
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.PropertyAddress"/>：</span></div>
                <div class="i_input">
                 	<s:textfield id="f_address" name="hotelvo.address" cssClass="fxt w360 required" maxlength="200"></s:textfield>
                	&nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_address"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_address" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:138px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="common.Address"/>:<input type="text" class="fxt w360  " style="margin-top:5px;margin-bottom:5px;" name="language.address"
													value="${hotelI18n.address}" maxlength="200" /> 
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_address');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_address" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:138px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
								<fmt:message key="common.Address"/>:<input type="text" class="fxt w360 " style="margin-top:5px;margin-bottom:5px;" name="language.address" maxlength="200"/>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_address');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_address')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.PropertyList.BusinessZone"/>：</span></div>
                <div class="i_input">
                 	<s:textfield id="f_business" name="hotelvo.business" cssClass="fxt w360" maxlength="40"></s:textfield>
                 	&nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_business"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_business" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<c:if test="${not empty hotelI18n.business }"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td>&nbsp;&nbsp;&nbsp;<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:138px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="ccm.PropertyList.BusinessZone"/>:<input type="text" class="fxt " style="margin-top:5px;width:348px;margin-bottom:5px;" name="language.business"
													value="${hotelI18n.business}"  maxlength="40" /> 
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_business');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
								</c:if>
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_business" style="display:none;">  
							<td>&nbsp;&nbsp;&nbsp;<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:138px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
									<fmt:message key="ccm.PropertyList.BusinessZone"/>:<input type="text" class="fxt " style="margin-top:5px;margin-bottom:5px;width:348px;" name="language.business" maxlength="40" />
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_business');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_business')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text">email：</span></div>
                <div class="i_input">
                  <s:textfield id="f_email" name="hotelvo.email" cssClass="fxt w390 required" maxlength="400" ></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="common.PostalCode"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_postCode" name="hotelvo.postCode" cssClass="fxt w240 required" maxlength="50"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.Tel"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_telephone" name="hotelvo.telephone" cssClass="fxt w240 required" maxlength="20"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.Fax"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_fax" name="hotelvo.fax" cssClass="fxt w240 required" maxlength="50"></s:textfield>
                </div>
              </li>
               <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.sysListener.MonitorOfBookingOrder"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_isMasterListener" name="hotelvo.isMasterListener"/>
                  </label>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.hotel.BookingReminderEFAXNo"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="remindEfax" name="hotelvo.remindEfax" cssClass="fxt w240 " maxlength="50"></s:textfield>
                  &nbsp;<a href="#efaxDetail" class="link mgR12 ccm-popup-click" ><button type="button">..</button></a>
                  
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.hotel.MonitorPMSHeartBeat"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_isPMSHeartBeat" name="hotelvo.isPMSHeartBeat"/>
                  </label>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.hotel.BookingReminderEmail"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="remindEmail" name="hotelvo.remindEmail" cssClass="fxt w240 " maxlength="50"></s:textfield>
                </div>
              </li>
               <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.hotel.MoblieforPMSIAlert"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="sms" name="hotelvo.sms" cssClass="fxt w240 " maxlength="11"></s:textfield>
                </div>
              </li>
               <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.hotel.SMSAlertSendingRange"/>：</span></div>
                <div class="i_input">
                  <input id="effectiveTime" name="hotelvo.effectiveTime" cssClass="fxt w88 " maxlength="50" value="<fmt:formatDate value="${hotelvo.effectiveTime}" type="both" pattern="HH:mm"/>" />
                </div>
                <div class="i_input">
                  -<input id="expireTime" name="hotelvo.expireTime" cssClass="fxt w88 " maxlength="50" value="<fmt:formatDate value="${hotelvo.expireTime}" type="both" pattern="HH:mm"/>" />
                </div>
              </li>
             
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.TotalFloors"/>：</span></div>
                <div class="i_input">
                 	<s:textfield id="f_storeys" name="hotelvo.storeys" cssClass="fxt w80 onlyNum" maxlength="9"></s:textfield>&nbsp;&nbsp;&nbsp;&nbsp;
                 	 <fmt:message key="ccm.PropertyList.TotalRooms"/>:<s:textfield id="f_rooms" name="hotelvo.rooms" cssClass="fxt w80 onlyNum" maxlength="9"></s:textfield>&nbsp;&nbsp;&nbsp;&nbsp;
                 	 <fmt:message key="ccm.PropertyList.TotalBeds"/>:<s:textfield id="f_beds" name="hotelvo.beds" cssClass="fxt w80 onlyNum" maxlength="9"></s:textfield>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.CheckInTime"/>：</span></div>
                <div class="i_input">
                  <input id="checkInTime" name="hotelvo.checkInTime" value="<s:date name="hotelvo.checkInTime" format="HH:mm"/>" class="fxt w120 required" />
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.CheckOutTime"/>：</span></div>
                <div class="i_input">
                  <input id="checkOutTime" name="hotelvo.checkOutTime" value="<s:date name="hotelvo.checkOutTime" format="HH:mm"/>" class="fxt w120 required" />
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.ValidTime"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="effectiveDate" name="hotelvo.effectiveDate" cssClass="fxt w120 required" />
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.InvalidTime"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="expireDate" name="hotelvo.expireDate" cssClass="fxt w120 required" />
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PropertyList.DaylightSavingTimeorNot"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_isDaylightSaving" name="hotelvo.isDaylightSaving"/>
                  </label>
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PropertyList.AgreementHotelorNot"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_isNegotiate" name="hotelvo.isNegotiate"/>
                  </label>
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PropertyList.DirectlyConnectPMS"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_isDirectPms" name="hotelvo.isDirectPms"/>
                  </label>
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PropertyList.IsUpdateIdent"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_isUpdateIdent" name="hotelvo.isUpdateIdent"/>
                  </label>
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PropertyList.CreditOnlineHotel"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_isCreditOnlineHotel" name="hotelvo.isCreditOnlineHotel"/>
                  </label>
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PropertyList.DisplacementInterface"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_isDisplacementInterface" name="hotelvo.isDisplacementInterface"/>
                  </label>
                </div>
              </li>
              
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.PropertyType"/>：</span></div>
                <div class="i_input">
	                 <%-- <s:select id="f_level" name="hotelvo.level" list="hotelTypeList" listKey="codeNo" listValue="codeLabel"
						headerKey="" headerValue="请选择" cssClass="fxt w240 required" >
				  	 </s:select> --%>
				  	 
			  	 <select id="f_level" name="hotelvo.level" class="fxt w240 required" >
			  	 	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
			  	 	<c:forEach items="${hotelTypeList }" var="hotelType">
			  	 		<option value="${hotelType.codeNo }"  <c:if test="${hotelType.codeNo==hotelvo.level }">selected="selected"</c:if> >${hotelType.codeLabel }</option>
			  	 	</c:forEach>
			  	 </select>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.PropertyLevel"/>：</span></div>
                <div class="i_input">
	                 <%-- <s:select id="f_star" name="hotelvo.star" list="hotelStarList" listKey="codeNo" listValue="codeLabel"
						headerKey="" headerValue="请选择" cssClass="fxt w240 required" >
				  	 </s:select> --%>
				  	 <select id="f_star" name="hotelvo.star" class="fxt w240 required">
				  	 	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
				  	 	<c:forEach items="${hotelStarList }" var="hotelStar">
				  	 		<option value="${hotelStar.codeNo }"  <c:if test="${hotelStar.codeNo==hotelvo.star }">selected="selected"</c:if> >${hotelStar.codeLabel }</option>
				  	 	</c:forEach>
				  	 </select>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.PMSType"/>：</span></div>
                <div class="i_input">
	                 <%-- <s:select id="f_pmsType" name="hotelvo.pmsType" list="pmsTypeList" listKey="codeNo" listValue="codeLabel"
						headerKey="" headerValue="请选择" cssClass="fxt w240 required" >
				  	 </s:select> --%>
				  	 
				  	 <select id="f_pmsType" name="hotelvo.pmsType" class="fxt w240 required">
				  	 	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
				  	 	<c:forEach items="${pmsTypeList }" var="pmsType">
				  	 		<option value="${pmsType.codeNo }"  <c:if test="${pmsType.codeNo==hotelvo.pmsType }">selected="selected"</c:if> >${pmsType.codeLabel }</option>
				  	 	</c:forEach>
				  	 </select>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.CurrencyType"/>：</span></div>
                <div class="i_input">
	                 <%-- <s:select id="f_currencyCode" name="hotelvo.currencyCode" list="currencyCodeList" listKey="codeNo" listValue="codeLabel"
						headerKey="" headerValue="请选择" cssClass="fxt w240 required" >
				  	 </s:select> --%>
				  	 <select id="f_currencyCode" name="hotelvo.currencyCode" class="fxt w240 required">
				  	 	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
				  	 	<c:forEach items="${currencyCodeList }" var="currencyCode">
				  	 		<option value="${currencyCode.codeNo }"  <c:if test="${currencyCode.codeNo==hotelvo.currencyCode }">selected="selected"</c:if> >${currencyCode.codeLabel }</option>
				  	 	</c:forEach>
				  	 </select>
				  	 
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PropertyList.CoordinateType"/>：</span></div>
                <div class="i_input">
	                 <%-- <s:select id="f_positionType" name="hotelvo.position_type" list="positionTypeList" listKey="codeNo" listValue="codeLabel"
						headerKey="" headerValue="请选择" cssClass="fxt w240 " >
				  	 </s:select> --%>
				  	 <select id="f_positionType" name="hotelvo.position_type" class="fxt w240">
				  	 	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
				  	 	<c:forEach items="${positionTypeList }" var="positionType">
				  	 		<option value="${positionType.codeNo }"  <c:if test="${positionType.codeNo==hotelvo.position_type }">selected="selected"</c:if> >${positionType.codeLabel }</option>
				  	 	</c:forEach>
				  	 </select>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.DateFormat"/>：</span></div>
                <div class="i_input">
	                 <%-- <s:select id="f_dateFormat" name="hotelvo.dateFormat" list="dateFormatList" listKey="codeNo" listValue="codeLabel"
						headerKey="" headerValue="请选择" cssClass="fxt w240 required" >
				  	 </s:select> --%>
				  	 <select id="f_dateFormat" name="hotelvo.dateFormat" class="fxt w240 required">
				  	 	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
				  	 	<c:forEach items="${dateFormatList }" var="dateFormat">
				  	 		<option value="${dateFormat.codeNo }"  <c:if test="${dateFormat.codeNo==hotelvo.dateFormat }">selected="selected"</c:if> >${dateFormat.codeLabel }</option>
				  	 	</c:forEach>
				  	 </select>
				  	 
                </div>
              </li>
              <li>
                	<div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.AvailablePaymentType"/>：
                	<s:hidden id="f_paymentMethod" name="hotelvo.paymentMethod"/> </span></div>
	            	<div class="i_input paymentMethod" style="position:relative;">
	                	<div class="moreoption">
							<div class="opts">
								<div class="text typeName " style="width: 200px;"><fmt:message key="common.select.plesesSelect"/></div>
							</div>
						</div>
						<!--房型查看隐藏层-->
						<div class="ft_layer abs ddds" style="width: 238px;">
							<div class=" n_overFlowY">
								<div class="mgA6">
									<c:forEach items="${paymentMethodList}" var="payM">
										<label class="checkbox"> <input type="checkbox" value="${payM.codeNo}" name="hotelvo.paylabel" 
											<c:if test="${fn:contains(hotelvo.paymentMethod,payM.codeNo)}">checked="checked" </c:if> >							
											<span class=""> <span class="span_roomTypeCode">${payM.codeLabel}</span>  </span> </label>
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
              <!-- 默认取消规则 -->
              <%--<li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PropertyList.defCancelId"/>：</span></div>
                <div class="i_input">
                	<s:select name="hotelvo.defCancelId" cssClass="w120" list="hotelCancelList"
                	listKey="cancelId" listValue="cancelPolicyCode"  headerKey="" headerValue=""></s:select>
                </div>
              </li>
              <!-- 默认担保规则 -->
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PropertyList.defGuaranteeId"/>：</span></div>
                <div class="i_input">
				  	 <select id="f_defGuaranteeId" name="hotelvo.defGuaranteeId">
 				  	 <fmt:message key="common.select.plesesSelect"/> 
				  	 	<option value=""></option>
				  	 	<c:forEach items="${hotelGuaranteeList }" var="hg">
				  	 		<option value="${hg.guaranteeId }"  <c:if test="${hg.guaranteeId==hotelvo.defGuaranteeId }">selected="selected"</c:if> >
				  	 		${hg.guaranteeCode }
				  	 		</option>
				  	 	</c:forEach>
				  	 </select>
                </div>
              </li>--%>
              
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.LandmarkList.Longitude"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_longitude" name="hotelvo.longitude" cssClass="fxt w120" maxlength="15"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.LandmarkList.Latitude"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_latitude" name="hotelvo.latitude" cssClass="fxt w120" maxlength="15"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.Altitude"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_altitude" name="hotelvo.altitude" cssClass="fxt w120" maxlength="15"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.SMSUserName"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_smsUserId" name="hotelvo.smsUserId" cssClass="fxt w180" maxlength="30"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.SMSPassword"/>：</span></div>
                <div class="i_input">
                	<s:password id="f_smsPassword" name="hotelvo.smsPassword" cssClass="fxt w180" maxlength="30"></s:password>
                </div>
              </li>
             <li>
				<div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.ThirdPartyPayment"/>：</span></div>
                <div class="i_input">
                	<ul>
					<li><fmt:message key="ccm.PropertyList.Alipay"></fmt:message>&nbsp;<label class="radio inline"><input type="checkbox" id="f_alipay" <c:if test="${hotelvo.partner!=null && hotelvo.partner!=''}">checked="checked"</c:if> onchange="checkboxThirdPay();"/></label></li>
					<div id="alipayUl">
					<li>
						<div class="thirdPay">
							<span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.CooperativeIdentityID"/>：</span>
						</div>
						<div class="i_input">
							<input type="text" id="f_partner" name="hotelvo.partner" value="${hotelvo.partner}" class="fxt w180 onlyNum" maxlength="16"/>
						</div>
					</li>
					<li>
						<div class="thirdPay">
							<span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.TradingSafetyInspectionCode"></fmt:message>：</span>
						</div>
						<div class="i_input">
							<input type="text" id="f_checkCode" name="hotelvo.checkCode" value="${hotelvo.checkCode}" class="fxt w180" maxlength="100"/>
						</div>
					</li>
					<li>
						<div class="thirdPay">
							<span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.SigningAlipayaccount"></fmt:message>：</span>
						</div>
						<div class="i_input">
							<input type="text" id="f_sellerEmail" name="hotelvo.sellerEmail" value="${hotelvo.sellerEmail}" class="fxt w180" maxlength="100"/>
						</div>
					</li>
					</div>
					<li>Mpay&nbsp;<label class="radio inline"><input type="checkbox" id="f_mpay" <c:if test="${hotelvo.merchantid!=null && hotelvo.merchantid!=''}">checked="checked"</c:if> onchange="checkboxThirdPay();"/></label></li>
					<div id="mpayUl">
					<li>
						<div class="thirdPay">
							<span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.MerchantNumber"></fmt:message>：</span>
						</div>
						<div class="i_input">
							<input type="text" id="f_merchantid" name="hotelvo.merchantid" value="${hotelvo.merchantid}" class="fxt w180" maxlength="32"/>
						</div>
					</li>
					<li>
						<div class="thirdPay">
							<span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.MerchantPassword"></fmt:message>：</span>
						</div>
						<div class="i_input">
							<input type="text" id="f_merchant_tid" name="hotelvo.merchant_tid" value="${hotelvo.merchant_tid}" class="fxt w180	" maxlength="32"/>
						</div>
					</li>
					<li>
						<div class="thirdPay">
							<span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.MerchantSecurityCheckCode"></fmt:message>：</span>
						</div>
						<div class="i_input">
							<input type="text" id="f_securekey" name="hotelvo.securekey" value="${hotelvo.securekey}" class="fxt w180" maxlength="50"/>
						</div>
					</li>
					</div>
					</ul>
				</div>
			</li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.TaobaoShopName"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_tbShopName" name="hotelvo.tbShopName" cssClass="fxt w180" maxlength="30"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.HotelPushUrl"/>：</span></div>
                <div class="i_input">
                  http://<s:textfield id="f_hotelPushUrl" name="hotelvo.hotelPushUrl" cssClass="fxt w180"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.PropertyDisplayMode"/>：</span></div>
                <div class="i_input">
                  	<label class="radio inline"> 
                  		<input type="radio" name="hotelvo.displayMode" id="optionsRadios1" value="1" 
                  			<s:if test="hotelvo.displayMode==1">checked="checked"</s:if>
                  			<s:if test="hotelvo.displayMode==null">checked="checked"</s:if>> 
                  		<span class=""><fmt:message key="ccm.PropertyList.ByRoomMode"/></span> 
                  	</label> 
                  	<label class="radio inline"> 
                  		<input type="radio" name="hotelvo.displayMode" id="optionsRadios2" value="2" 
                  			<s:if test="hotelvo.displayMode==2">checked="checked"</s:if>> 
                  		<span class=""><fmt:message key="ccm.PropertyList.ByRateMode"/></span> 
                  	</label>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.GuestReservationRequiredSelection"/>：</span></div>
                <div class="i_input">
                	<div class="i_input">      
	                  <label class="checkbox inline">
	                     <s:checkbox id="f_isMobileRequired" name="hotelvo.isMobileRequired" onchange="customRequired('mobile');"/>
	                     <span class=""><fmt:message key="ccm.PropertyList.MobilePhoneRequired"/></span>
	                  </label>
	                  <label class="checkbox inline">
	                     <s:checkbox id="f_isEmailRequird" name="hotelvo.isEmailRequird" onchange="customRequired('email');"/>
	                     <span class=""><fmt:message key="ccm.PropertyList.EmailRequired"/></span>
	                  </label>
	                  <label class="checkbox inline">
	                     <s:checkbox id="f_isAddressRequired" name="hotelvo.isAddressRequired"/>
	                     <span class=""><fmt:message key="ccm.PropertyList.AddressRequired"/></span>
	                  </label>
	                </div>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.SupportedLanguagesForPropertyReservationSystem"/>：</span></div>
                <div class="i_input">
                	<div class="i_input">      
	                  <label class="checkbox inline">
	                     <s:checkbox id="f_isSupportChinese" name="hotelvo.isSupportChinese" onchange="supportChineseRequired();" value="true"/>
	                     <span class=""><fmt:message key="common.Chinese"/></span>
	                  </label>
	                  <label class="checkbox inline">
	                     <s:checkbox id="f_isSupportEnglish" name="hotelvo.isSupportEnglish" />
	                     <span class=""><fmt:message key="common.English"/></span>
	                  </label>
	                  <label class="checkbox inline">
	                     <s:checkbox id="f_isSupportJapanese" name="hotelvo.isSupportJapanese"/>
	                     <span class=""><fmt:message key="common.Japanese"/></span>
	                  </label>
	                </div>
                </div>
              </li>
				<li>
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.PackageService.Alert"/>：</span>
					</div>
					<div class="i_input">
						<div class="i_input">
							<label class="checkbox inline"> <s:checkbox name="hotelvo.orderRemind" /> <span class=""><fmt:message key="ccm.PropertyList.ReservationAlert"/></span> </label> 
							<label class="checkbox inline"> <s:checkbox name="hotelvo.messRemind" /> <span class=""><fmt:message key="ccm.PropertyList.MessageAlert"/></span> </label> 
						</div>
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class="text"></span><span class="text"><fmt:message key="ccm.Channel.allotNotificationEmail"/>：</span>
					</div>
					<div class="i_input">
						<div class="i_input">
							<s:textfield id="allotNotificationEmail" cssClass="fxt w180" key="hotelvo.allotNotificationEmail"></s:textfield>
						</div>
					</div>
				</li>
				
				<li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.MaxAvailableNumberofReservation"/>：</span></div>
                <div class="i_input">
	                 <select id="f_maxResCount" name="hotelvo.maxResCount" class="fxt w120 ">
	                 	<option value="1"
	                 		<c:if test="${hotelvo.maxResCount == 1 }"> selected="selected"</c:if>
	                 		>1</option>
	                 	<option value="2"
	                 		<c:if test="${hotelvo.maxResCount == 2 }"> selected="selected"</c:if>
	                 		>2</option>
	                 	<option value="3" 
	                 		<c:if test="${empty hotelvo.maxResCount}"> selected="selected"</c:if>
	                 		<c:if test="${hotelvo.maxResCount == 3 }"> selected="selected"</c:if>
	                 		>3</option>
	                 	<option value="4"
	                 		<c:if test="${hotelvo.maxResCount == 4 }"> selected="selected"</c:if>
	                 		>4</option>
	                 	<option value="5"
	                 		<c:if test="${hotelvo.maxResCount == 5 }"> selected="selected"</c:if>
	                 		>5</option>
	                 	<option value="6"
	                 		<c:if test="${hotelvo.maxResCount == 6 }"> selected="selected"</c:if>
	                 		>6</option>
	                 	<option value="7"
	                 		<c:if test="${hotelvo.maxResCount == 7 }"> selected="selected"</c:if>
	                 		>7</option>
	                 	<option value="8"
	                 		<c:if test="${hotelvo.maxResCount == 8 }"> selected="selected"</c:if>
	                 		>8</option>
	                 	<option value="9"
	                 		<c:if test="${hotelvo.maxResCount == 9 }"> selected="selected"</c:if>
	                 		>9</option>
	                 	<option value="10"
	                 		<c:if test="${hotelvo.maxResCount == 10 }"> selected="selected"</c:if>
	                 		>10</option>
	                 </select>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.HotelWebsite"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_homePage" name="hotelvo.homePage" cssClass="fxt w360" maxlength="1000"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.OnlineReservationHomePage"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_reservationPage" name="hotelvo.reservationPage" cssClass="fxt w360" maxlength="1000"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.PropertyRevenueSupport"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_specialist" name="hotelvo.specialist" cssClass="fxt w120" maxlength="35"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.PropertyIntroduction"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_description" name="hotelvo.description" cssClass="fxt w360 h80 required" ></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_description"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_description" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<c:if test="${not empty hotelI18n.description }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key='ccm.error.054'/>:<textarea  class="fxt w360 h80 " style="margin-top:5px;margin-bottom:5px;" 
												name="language.description" >${hotelI18n.description}</textarea>
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
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
								<fmt:message key='ccm.error.054'/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
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
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_description')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.WelcomeWords"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_salutatory" name="hotelvo.salutatory" cssClass="fxt w360 h80 "  maxlength="2000"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_salutatory"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_salutatory" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<c:if test="${not empty hotelI18n.salutatory }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td>&nbsp;&nbsp;&nbsp;<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="ccm.PropertyList.WelcomeWords"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.salutatory"  maxlength="2000">${hotelI18n.salutatory}</textarea>
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_salutatory');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>
								</c:if>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_salutatory" style="display:none;">  
							<td>&nbsp;&nbsp;&nbsp;<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
								<fmt:message key="ccm.PropertyList.WelcomeWords"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.salutatory"  maxlength="2000"></textarea>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_salutatory');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_salutatory')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.CheckInTimeDescription"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_checkInTimeDesc" name="hotelvo.checkInTimeDesc" cssClass="fxt w360 h80 required"  maxlength="1000"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_checkInTimeDesc"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_checkInTimeDesc" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<c:if test="${not empty hotelI18n.checkInTimeDesc }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td>&nbsp;&nbsp;&nbsp;<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="ccm.PropertyList.CheckInTimeDescription"/>:<textarea  class="fxt h80" style="margin-top:5px;margin-bottom:5px;width:330px;" 
												name="language.checkInTimeDesc" maxlength="1000">${hotelI18n.checkInTimeDesc}</textarea>
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_checkInTimeDesc');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>
								</c:if>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_checkInTimeDesc" style="display:none;">  
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" 
									onchange="setContent(this,'checkInTimeDesc');"
									>
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
								<fmt:message key="ccm.PropertyList.CheckInTimeDescription"/>:<textarea  class="fxt h80" style="margin-top:5px;margin-bottom:5px;width:330px;" 
												name="language.checkInTimeDesc" maxlength="1000"></textarea>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_checkInTimeDesc');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_checkInTimeDesc')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.CheckOutTimeDescription"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_checkOutTimeDesc" name="hotelvo.checkOutTimeDesc" cssClass="fxt w360 h80 required"  maxlength="1000"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_checkOutTimeDesc"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_checkOutTimeDesc" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<c:if test="${not empty hotelI18n.checkOutTimeDesc }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									            <fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="ccm.PropertyList.CheckOutTimeDescription"/>:<textarea  class="fxt h80" style="margin-top:5px;margin-bottom:5px;width:330px;" 
												name="language.checkOutTimeDesc" maxlength="1000">${hotelI18n.checkOutTimeDesc}</textarea>
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_checkOutTimeDesc');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>
								</c:if>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_checkOutTimeDesc" style="display:none;">  
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" 
									onchange="setContent(this,'checkOutTimeDesc');"
									>
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
								<fmt:message key="ccm.PropertyList.CheckOutTimeDescription"/>:<textarea  class="fxt h80" style="margin-top:5px;margin-bottom:5px;width:330px;" 
												name="language.checkOutTimeDesc" maxlength="1000"></textarea>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_checkOutTimeDesc');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_checkOutTimeDesc')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PropertyList.CancellationPolicy"/>:</span></div>
                <div class="i_input">
                  <s:textarea id="f_cancelPolicyDesc" name="hotelvo.cancelPolicyDesc" cssClass="fxt w360 h80 required"  maxlength="1000"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_cancelPolicyDesc"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_cancelPolicyDesc" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<c:if test="${not empty hotelI18n.cancelPolicyDesc }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="ccm.PropertyList.CancellationPolicy"/>:<textarea  class="fxt h80" style="margin-top:5px;margin-bottom:5px;width:350px;" 
												name="language.cancelPolicyDesc" maxlength="1000">${hotelI18n.cancelPolicyDesc}</textarea>
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_cancelPolicyDesc');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>
								</c:if>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_cancelPolicyDesc" style="display:none;">  
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" 
									onchange="setContent(this,'cancelPolicyDesc');"
									>
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
								<fmt:message key="ccm.PropertyList.CancellationPolicy"/>:<textarea  class="fxt h80" style="margin-top:5px;margin-bottom:5px;width:350px;" 
												name="language.cancelPolicyDesc" maxlength="1000"></textarea>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_cancelPolicyDesc');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_cancelPolicyDesc')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.PaymentAlert"/>:</span></div>
                <div class="i_input">
                  <s:textarea id="f_payRemind" name="hotelvo.payRemind" cssClass="fxt w360 h80"  maxlength="200"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_payRemind"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_payRemind" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<c:if test="${not empty hotelI18n.payRemind }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="ccm.PropertyList.PaymentAlert"/>:<textarea  class="fxt h80" style="margin-top:5px;margin-bottom:5px;width:350px;" 
												name="language.payRemind" maxlength="200">${hotelI18n.payRemind}</textarea>
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_payRemind');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>
								</c:if>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_payRemind" style="display:none;">  
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
								<fmt:message key="ccm.PropertyList.PaymentAlert"/>:<textarea  class="fxt h80" style="margin-top:5px;margin-bottom:5px;width:350px;" 
												name="language.payRemind" maxlength="200"></textarea>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_payRemind');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_payRemind')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PropertyList.PickupServiceAlert"/>:</span></div>
                <div class="i_input">
                  <s:textarea id="f_pickUpService" name="hotelvo.pickUpService" cssClass="fxt w360 h80"  maxlength="200"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_pickUpService"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_pickUpService" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelvo.hotelI18nList}">
							<c:forEach items="${hotelvo.hotelI18nList}" var="hotelI18n" varStatus="vstatus"> 
								<c:if test="${not empty hotelI18n.pickUpService }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="ccm.PropertyList.PickupServiceAlert"/>:<textarea  class="fxt h80" style="margin-top:5px;margin-bottom:5px;width:330px;" 
												name="language.pickUpService" maxlength="200">${hotelI18n.pickUpService}</textarea>
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_pickUpService');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>
								</c:if>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_pickUpService" style="display:none;">  
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
								<fmt:message key="ccm.PropertyList.PickupServiceAlert"/>:<textarea  class="fxt h80" style="margin-top:5px;margin-bottom:5px;width:330px;" 
												name="language.pickUpService" maxlength="200"></textarea>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_pickUpService');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_pickUpService')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
            </ul>
          </div>
          <hr class="dashed">
          <div class="listinputCtrl">
            <button type="button" class="btn_1 green mgR12 f_save"><fmt:message key="common.button.OK"/></button>
            <a class="btn_1 white" href="javascript:history.go(-1);"><fmt:message key="common.Return"/></a>
          </div>
        </div>
        </s:form>
<!--房价明细-->
	<div id="efaxDetail" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
		<div class="c_whitebg pdA12">
		<form action="" id="efaxDetailFrom" >
			<div class="mgB24">
				<ul class="list_input">
					<li>
						<div class="i_title"></div>
						<div class="i_input">
							<fmt:message key="ccm.PropertyList.faxDesp"/>
						</div>
					</li>
					<c:if test="${ empty faxSendTimeList }">
						<li maxIndex="0">
							<input type="hidden" name="faxSendTimeList[0].faxSendTimeId" ">
							<input type="hidden" name="faxSendTimeList[0].hotelId" value="${hotelvo.hotelId }">
							<div class="i_title">
								<input type="text" value="+" readonly="readonly" class="fxt" onclick="addefaxrow(this,'${hotelvo.hotelId }');" style="width: 10px">
								<fmt:message key="ccm.PropertyList.FaxNumber"/>
							</div>
							<div class="i_input">
								<input type="text" name="faxSendTimeList[0].faxNumber" maxlength="32"   class="fxt w80">
								 <fmt:message key="ccm.PropertyList.ReceivedTime"/>
								<input type="text" name="faxSendTimeList[0].beginTime" maxlength="9" readonly="readonly"  class="fxt w80  efaxbeginTime">
								&nbsp;&nbsp;  -&nbsp; &nbsp; 
								<input type="text" name="faxSendTimeList[0].endTime" maxlength="9" readonly="readonly" class="fxt w80 efaxendTime">
								<input type="text" value="-" readonly="readonly" onclick="removeefaxrow(this);" class="fxt" style="width: 10px">
							</div>
						</li>
					</c:if>
					<c:forEach items="${faxSendTimeList }" var="faxSendTime" varStatus="i">
						<li maxIndex="${ i.index}">
							<input type="hidden" name="faxSendTimeList[${ i.index}].faxSendTimeId" value="${faxSendTime.faxSendTimeId }">
							<input type="hidden" name="faxSendTimeList[${ i.index}].hotelId" value="${faxSendTime.hotelId }">
							<div class="i_title">
								<input type="text" value="+" readonly="readonly" class="fxt" onclick="addefaxrow(this,'${hotelvo.hotelId }');" style="width: 10px">
								<fmt:message key="ccm.PropertyList.FaxNumber"/>
							</div>
							<div class="i_input">
								<input type="text" name="faxSendTimeList[${ i.index}].faxNumber" maxlength="32" value="${faxSendTime.faxNumber }"  class="fxt w80">
								 <fmt:message key="ccm.PropertyList.ReceivedTime"/>
								<input type="text" name="faxSendTimeList[${ i.index}].beginTime" maxlength="9" readonly="readonly" value="<fmt:formatDate value="${faxSendTime.beginTime}" type="both" pattern="HH:mm"/>"  class="fxt w80  efaxbeginTime">
								&nbsp;&nbsp;  -&nbsp; &nbsp; 
								<input type="text" name="faxSendTimeList[${ i.index}].endTime" maxlength="9" readonly="readonly" value="<fmt:formatDate value="${faxSendTime.endTime}" type="both" pattern="HH:mm"/>"  class="fxt w80 efaxendTime">
								<input type="text" value="-" readonly="readonly" onclick="removeefaxrow(this);" class="fxt" style="width: 10px">
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</form>
		<div class="b_crl" style="text-align: center;">
			<button type="button" class="btn_2 white" onclick="saveEfaxDetail();"><fmt:message key="common.button.save"/></button>
			<button type="button" class="btn_2 white popup-close" ><fmt:message key="common.button.close"/></button>
		</div>
		</div>
	</div>
<script type="text/javascript" src="<c:url value='/js/location.js'/>"   ></script>
<script type="text/javascript" src="<c:url value='/js/YlChinaArea.js'/>" ></script>
<link href="<c:url value='/upload/css/uploadskin.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/upload/css/upload.css'/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<c:url value='/js/ajaxfileupload.js'/>${global_js_revision}"></script>
<script type="text/javascript" src="<c:url value='/upload/core/swfupload.js'/>${global_js_revision}"></script>
<script type="text/javascript" src="<c:url value='/upload/upload.js'/>${global_js_revision}"></script>
<script>

function saveEfaxDetail(){
	$.ajax({
		type : "post",
		url : "hotel_editEfaxDetail.do",
		data : $("#efaxDetailFrom").serialize(),
		cache : false,
		dataType : 'text',
		beforeSend : function() {
		},
		success : function(data) {
			console.log(data);
			if(data=='success'){
				$.magnificPopup.close();
			}else{
				alert(data);
			}
		}
	});
}

function addefaxrow(obj,hotelId){
	var ul = $(obj).parents("ul");
	var i = $(ul).children().length;
	var lastLi = $(ul).children().eq(i-1);
	var index = parseInt($(lastLi).attr("maxIndex"))+1;
	var html = ''
		+'<li maxIndex="'+index+'">'
		+'<input type="hidden" name="faxSendTimeList['+index+'].faxSendTimeId" value="">'
		+'<input type="hidden" name="faxSendTimeList['+index+'].hotelId" value="'+hotelId+'">'
		+'<div class="i_title">'
		+'<input type="text" value="+" readonly="readonly" class="fxt" onclick="addefaxrow(this,'+"'"+hotelId+"'"+');" style="width: 10px">'
		+'<fmt:message key="ccm.PropertyList.FaxNumber"/>'
		+'</div>'
		+'<div class="i_input">'
		+'<input type="text" name="faxSendTimeList['+index+'].faxNumber" maxlength="32" value=""  class="fxt w80">'
		+'<fmt:message key="ccm.PropertyList.ReceivedTime"/>'
		+'<input type="text" name="faxSendTimeList['+index+'].beginTime" maxlength="9" readonly="readonly" value=""  class="fxt w80  efaxbeginTime">'
		+'&nbsp;&nbsp;  -&nbsp; &nbsp; '
		+'<input type="text" name="faxSendTimeList['+index+'].endTime" maxlength="9" readonly="readonly" value=""  class="fxt w80 efaxendTime"> '
		+'<input type="text" value="-" readonly="readonly" onclick="removeefaxrow(this);" class="fxt" style="width: 10px"> '
		+'</div>'
		+'</li>';
		
	$(ul).append(html);
	initDateCfg();
}
function removeefaxrow(obj){
	var ul = $(obj).parents("ul");
	var number = $(ul).children().length;
	if(number>2){
		var li = $(obj).parents("li");
		$(li).remove();
	}
}


//获取数据
function ajaxRequestData(parentId,selectId) {
	$.ajax({
		type : "post",
		url : "city_getCityList.do",
		data : {
			"parentId" : parentId
		},
		cache : false,
		dataType : 'json',
		beforeSend : function() {
		},
		success : function(data) {
			var html = '<option value=""></option>';
			for(var i = 0;i<data.length;i++){
				var selected='';
				if(parentId==data[i].id){
					selected = 'selected="selected"';
				}
				html = html+'<option value="'+data[i].cityCode+'"'+selected+' attrid="'+data[i].id+'">'+data[i].cityName+'</option>';
			}
			$("#"+selectId).html(html);
		}
	});
}

function initDateCfg(){
	//日期显示
	var dpconfig = {
		dateFormat : "yy-mm-dd",
		showMonthAfterYear : true,
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
	
	$("#InputDateStart").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			$("#InputDateEnd").datepicker("option", "minDate", v);
		},
	}));
	
	$('#effectiveTime').timepicker($.extend(dpconfig, {
		showSecond:false,timeFormat: 'HH:mm',
	}));
	$('#expireTime').timepicker($.extend(dpconfig, {
		showSecond:false,timeFormat: 'HH:mm'
	}));
	
	$('.efaxbeginTime').datetimepicker($.extend(dpconfig, {
		showSecond:false,timeFormat: 'HH:mm',
		onSelect : function(dateText) {
			var time = $(this).val();
			var time = new Date(showDateTime(time));
			$(this).next().timepicker('option', "minDateTime",time);
		}
	}));
	$('.efaxendTime').timepicker($.extend(dpconfig, {
		timeFormat: 'HH:mm',
	}));

	$("#checkInTime").timepicker();
	
	$("#checkOutTime").timepicker();
	
	$("#effectiveDate").datepicker($.extend(dpconfig, {
		
	}));
	
	$("#expireDate").datepicker($.extend(dpconfig, {
		minDate:new Date(),
		onClose : function(v) {
			$("#effectiveDate").datepicker("option", "maxDate", v);
		}
	}));
}


$(document).ready(function() {
	var local = "${locale}";
	if(local=='en_US'){
		$("#Span_Add_Img").removeClass().addClass("div_upload_img_en");
		$("#Span_Ul_Img").removeClass().addClass("span_ul_img_en");
	}else if(local=='zh_CN'){
		$("#Span_Add_Img").removeClass().addClass("div_upload_img");
		$("#Span_Ul_Img").removeClass().addClass("span_ul_img");
	}
	initClass(local);
	
	initDateCfg();
	$("#f_privince").change(function(){
		var parentId=$(this).find("option:selected").attr("attrid");
		$("#f_city").html('<option value=""></option>');
		$("#f_areaCode").html('<option value=""></option>');
		if(parentId!=undefined&&parentId!='undefined'){
			ajaxRequestData(parentId,'f_city');
		}
	})
	$("#f_city").change(function(){
		$("#f_areaCode").html('<option value=""></option>');
		var parentId=$(this).find("option:selected").attr("attrid");
		if(parentId!=undefined&&parentId!='undefined'){
			ajaxRequestData(parentId,'f_areaCode');
		}
	})
	
	
	jQuery.extend(jQuery.validator.messages, {
		required : "<fmt:message key='common.RequiredField'/>"			
	});	
	
	
	changeChain();//集团与品牌联动
	
	//设置酒店编码不能编辑
	if(null!=$('#f_hotelId').val() && ''!=$('#f_hotelId').val()){
		$('#f_hotelCode').attr("readonly", true);
		
		var paymentMethodNames ='';
		$('.paymentMethod input:checked').next('span').each(function(){ 
			paymentMethodNames += $(this).text()+',';
		});
		paymentMethodNames = paymentMethodNames.substr(0,paymentMethodNames.lastIndexOf(','));
		$('.paymentMethod .typeName').text(paymentMethodNames);
		
	}else{
		$('#f_isDirectPms').attr("checked",'true');
		$('#f_isUpdateIdent').attr("checked",'true');
		//默认手机必填
		document.getElementById('f_isMobileRequired').checked = true;
	}
	
	/* $('.ChinaArea').jChinaArea({
		province : '${hotelvo.privinceCode}',
		city : '${hotelvo.city}',
		county : '${hotelvo.areaCode}',
		valueEleName : 'address'
	}); */
	
	$('.onlyNum').keydown(function(e){
		return filterNumInput(e,$(this).val());
	});
	
	$('.moreoption').bind('click',function(){
		$(this).next().slideDown();
	});
	$('.closethis').bind('click',function(){
		$(this).parent().parent().slideUp();
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
	//可接受支付方式的确认按钮点击事件
	$('.paymentMethod .confirmthis').click(function(){
		var paymentMethodCodes='';
		var paymentMethodNames ='';
		$('.paymentMethod input:checked').each(function(){ 
			paymentMethodCodes += $(this).val()+',';
		});
		$('.paymentMethod input:checked').next('span').each(function(){ 
			paymentMethodNames += $(this).text()+',';
		});
		paymentMethodCodes = paymentMethodCodes.substr(0,paymentMethodCodes.lastIndexOf(','));
		paymentMethodNames = paymentMethodNames.substr(0,paymentMethodNames.lastIndexOf(','));
		
		$("input[name='hotelvo.paymentMethod']").val(paymentMethodCodes);
		//$('.paymentMethod .typeCode').text(roomTypeCode);
		$('.paymentMethod .typeName').text(paymentMethodNames);
		
		$(this).parent().parent().slideUp();
	});
	
	$('.moreLanguageSwitch').click(function(){
		var moreLanguageSwitch = $('#ml_'+$(this).attr('id'));
		var modelLanguage = $('#mdl_'+$(this).attr('id'));
		
		if(moreLanguageSwitch.is(':hidden')){
			moreLanguageSwitch.show();
			
			//如果仅剩一行记录
			if(moreLanguageSwitch.find('table>tbody>tr').length==2){
				moreLanguageSwitch.find('table>tbody').find("tr:last").before('<tr><td class="w20">1.</td>'+modelLanguage.html()+'</tr>');
			}
		}else{
			moreLanguageSwitch.hide();
		}
	});
	
	var tar=$("#Div_Upload_Area");
	tar.vinSWFUpload({
		width:$(".div_upload_photo").width(),
		heigth:300,
	    filePostName:"file",
	    postParams: {"bizId" : "${hotelvo.picId}","bizType" : "1"},
	    maxSize:"5",
	    fileLimit:10,
		sizeUnit:"M",
		uploadURL:"pictureUpload.do;jsessionid=<%=session.getId()%>",
		customComplete : function() {v = 1;}
	});
	
	$("#Span_Ul_Img").hide();
	$("#Span_Tip_HasFile").hide();
	$("#Div_Upload_Area").hide();
	$("#Div_Upload_Tip").hide();
	
	$(".div_upload_container .div_img").mouseenter(function(){
		var picId =  $(this).attr("picId");
		$("#Span_Imgcon_Close").data("tar",$(this)).appendTo($(this)).click(function(){
			var t=this;
			$.get("picture_ajaxDelete.do?picId="+picId, {}, 
				function(data,textStatus) {
					$(t).appendTo($(".div_upload_container")).data("tar").remove();
				}
			);
			
		}).show();
	}).mouseleave(function(){
		$("#Span_Imgcon_Close").unbind("click").hide();
	});
	
	//保存
	$('.f_save').click(function(){
		
		//验证表单
		if(!$("#hotelForm").valid()){
			return;
		}
		
		//验证可接受支付方式
		if(strIsNull($('#f_paymentMethod').val())){
			alert('<fmt:message key="ccm.error.055"/>');
			return;
		}
		
		//验证酒店官网
		if(!strIsNull(   $('#f_officialWebsite').val()  )){
			
			var officialWebsite=$('#f_officialWebsite').val();
			
			var officialWebsiteReg='^((https|http|ftp|rtsp|mms)?://)([\s|\S]*)';
			
			var re=new RegExp(officialWebsiteReg);
			
			if(!re.test(officialWebsite)){
				alert('<fmt:message key="ccm.error.OfficialWebsite"/>');
				return;
			}
			
		}
		
		//校验邮箱
		var emailStr = $("#f_email").val();
		var emailArr = emailStr.split(";");
		for(var i = 0 ;i < emailArr.length ; i++ ){
			var email = emailArr[i];
			var emailReg = /[\w\.\-]+@([\w\-]+\.)+[\w\-]+/g;
			var emailRs = email.replace(emailReg,'');
			
			if(emailRs!=''){
				var arry = new Array();
				arry.push(email);
				var str = '<fmt:message key="ccm.error.056"/>';
				alert(i18n_replace(str,arry));
				//alert('email【'+email+'】格式错误,如果是多个邮箱,中间请用分号(;)隔开,请检查!');
				return;
			}
		}
		//提醒校验邮箱
		var emailStr = $("#remindEmail").val();
		var emailArr = emailStr.split(";");
		for(var i = 0 ;i < emailArr.length ; i++ ){
			var email = emailArr[i];
			var emailReg = /[\w\.\-]+@([\w\-]+\.)+[\w\-]+/g;
			var emailRs = email.replace(emailReg,'');
			
			if(emailRs!=''){
				var arry = new Array();
				arry.push(email);
				var str = '<fmt:message key="ccm.error.056"/>';
				alert(i18n_replace(str,arry));
				//alert('email【'+email+'】格式错误,如果是多个邮箱,中间请用分号(;)隔开,请检查!');
				return;
			}
		}

		//校验创建时间
		var dateStart = $("#InputDateStart").val();
		if(!strIsNull(dateStart)){
			var dateStartCode = validateYYYYMMDD(dateStart);
			if(dateStartCode!='success'){
				alert(getErrorMsg(dateStartCode,'<fmt:message key="ccm.PropertyList.EstablishmentTime"/>','yyyy-MM-DD'));
				return ;
			}
		}
		
		var checkInTime = $("#checkInTime").val();
		if(!strIsNull(checkInTime)){
			var checkInTimeCode = validateHHmm(checkInTime);
			if(checkInTimeCode!='success'){
				alert(getErrorMsg(checkInTimeCode,'<fmt:message key="ccm.PropertyList.CheckInTime"/>','HH:mm'));
				return ;
			}
		}
		
		var checkOutTime = $("#checkOutTime").val();
		if(!strIsNull(checkOutTime)){
			var checkOutTimeCode = validateHHmm(checkOutTime);
			if(checkOutTimeCode!='success'){
				alert(getErrorMsg(checkOutTimeCode,'<fmt:message key="ccm.PropertyList.CheckOutTime"/>','HH:mm'));
				return ;
			}
		}
		
		var effectiveDate = $("#effectiveDate").val();
		if(!strIsNull(effectiveDate)){
			var effectiveDateCode = validateYYYYMMDD(effectiveDate);
			if(effectiveDateCode!='success'){
				alert(getErrorMsg(effectiveDateCode,'<fmt:message key="ccm.PropertyList.ValidTime"/>','yyyy-MM-DD'));
				return ;
			}
		}
		
		var expireDate = $("#expireDate").val();
		if(!strIsNull(expireDate)){
			var expireDateCode = validateYYYYMMDD(expireDate);
			if(expireDateCode!='success'){
				alert(getErrorMsg(expireDateCode,'<fmt:message key="ccm.PropertyList.InvalidTime"/>','yyyy-MM-DD'));
				return ;
			}else if(isMorethanNow(expireDate)=='false'){
				alert('失效时间不能小于当前时间');
				return ;
			}
		}
		
		//校验 合作身份者ID，以2088开头由16位纯数字组成的字符串
		var partner = $("#f_partner").val();
		if(!strIsNull(partner)&&partner.indexOf("2088")!=0){
			alert("<fmt:message key='ccm.error.057'/>");
			return;
		}
		
		var checkCode = $("#f_checkCode").val();
		var sellerEmail = $('#f_sellerEmail').val();
		if(!strIsNull(partner)||!strIsNull(checkCode)||!strIsNull(sellerEmail)){
			if(strIsNull(partner)||strIsNull(checkCode)||strIsNull(sellerEmail)){
				alert('<fmt:message key="ccm.error.058"/>');
				return;
			}
		}

		//验证多语言并且重组数据 
		var flag = executeMoreLanguage();
		
		if(!flag){
			return;
		}

		$('#cityNameId').val($('#f_city').find("option:selected").text());
		
		//修改
		if($('#f_hotelId').val().length>0){
			$("#hotelForm").submit();
			//禁止重复提交
			 $('.f_save').addClass('no_ald');
			 $('.f_save').attr("disabled","disabled");
			return;
		}
		//新增时验证酒店代码
		$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  dataType : "text",
	   	  url:"hotel_isHotelCode.do",
	   	  data:{"hotelvo.hotelCode":$('#f_hotelCode').val(),"hotelvo.chainId":$('#f_chainId').val()},
		  success:function(data){
			  if("false" == data){
				 alert("<fmt:message key='ccm.error.059'/>");
		  	  }else{
		  		 $("#hotelForm").submit();
					//禁止重复提交
				 $('.f_save').addClass('no_ald');
				 $('.f_save').attr("disabled","disabled");
		  	  }
	       }
	    });
	});
});

function logoFileUpload(){
	
	var filePath= $('#logoPic').val();
	if(filePath.length<4){
		alert("<fmt:message key='ccm.error.060'/>");
		return false;
	}
	
	var fn = filePath.substring(filePath.lastIndexOf('.')).toLowerCase();
	if(fn !='.jpg' && fn != '.jpeg' && fn != '.png' && fn != '.gif'){	
		alert("<fmt:message key='ccm.error.060'/>");
		return false;
	}
	
	//ajax上传图片
	$.ajaxFileUpload({
		url:"pictureUpload.do?bizId=${hotelvo.logoPicId}&bizType=1&uploadType=normal&jsessionid=<%=session.getId()%>",
		secureuri:false,
		fileElementId:'logoPic',
		success: function (data){
			//新增时验证
			$.ajax({
				type:"POST",
				async:false,
			   	url:"hotel_updateLogPic.do",
			   	data:{"hotelvo.hotelId":"${hotelvo.hotelId}","hotelvo.logoPicId":"${hotelvo.logoPicId}"},
				success:function(data){
					  if(!strIsNull(data)){
						  var logoPic = eval("("+data+")");
						  
						  $('#logoImage').attr("src","${hotelvo.pictureUrlFolder}"+logoPic.url);
						  //显示图片
						  $('#logoImage').show();
						  $('#buttonDelete').show();
						  $('#logoPic').val('');
						  $('#logoPic').hide();
						  $('#buttonUpload').hide();
					  }
			    }   	  
			});	
		},
		error: function (data, status, e)
		{
			alert(data);
		}
	});
	return false;
}

function logoFileDelete(){
	
	//新增时验证
	$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"hotel_deleteLogPic.do",
	   	  data:{"hotelvo.logoPicId":"${hotelvo.logoPicId}"},
		  success:function(data){
			  if(data == 'success'){
				  //隐藏图片显示框
				  $('#logoImage').hide();
				  $('#buttonDelete').hide();
				  $('#logoPic').val('');
				  $('#logoPic').show();
				  $('#buttonUpload').show();
			  }
	      }
    });		
	
	return false;
}

//css文件上传与删除
function cssFileUpload(){
	var filePath= $('#cssFile').val();
	if(filePath.length<4){
		alert("<fmt:message key='ccm.hotel.css.010'/>");
		return false;
	}
	//验证文件格式
	var fn = filePath.substring(filePath.lastIndexOf('.')).toLowerCase();
	if(fn !='.css'){	
		alert("<fmt:message key='ccm.hotel.css.010'/>");
		return false;
	}
	
	//ajax上传
	$.ajaxFileUpload({
		url:"textfileUpload.do?hotelId=${hotelvo.hotelId}",
		secureuri:false,
		fileElementId:'cssFile',
		success: function (data){
			$.ajax({
				type:"POST",
				async:false,
				url:"hotel_updateCssFile.do",
				data:{"hotelvo.hotelId":"${hotelvo.hotelId}","hotelvo.cssFileId":"${hotelvo.cssFileId}"},
				success:function(data){
					  if(!strIsNull(data)){
						  var logoPic = eval("("+data+")");
						  $('#buttonDeleteCSS').show();
						  $('#cssFile').val('');
						  $('#cssFile').hide();
						  $('#buttonUploadCSS').hide();
					  }
				}
			});
		}
	});	
	
	return false;
}
	
function cssFileDelete(){
	$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"hotel_deleteCssFile.do",
	      data:{"hotelvo.hotelId":"${hotelvo.hotelId}"},
		  success:function(data){
			  if(data == 'success'){
				  $('#buttonDeleteCSS').hide();
				  $('#cssFile').val('');
				  $('#cssFile').show();
				  $('#buttonUploadCSS').show();
			  }
	      }
    });		
	
	return false;
}

//集团与品牌联动
function changeChain(){
	 var chainId=$("#f_chainId").val();
	 if(chainId.length>0){
		 $.get("/brand_ajaxGetAllBrandByChainId.do?brandvo.chainId="+chainId,function(data){
			data = "<option value=''><fmt:message key='common.select.plesesSelect'/></option>"+data;
			$("#f_brandId").html(data);
			var v=$("#f_brandId").attr("brandId");
		    if(v!=undefined&&v!='null'){
				var options = $("#f_brandId option");
   				$.each(options, function(){
   					$(this).prop("selected", $(this).val()==v);
   				});	
		    }
		 })
	 }else{
		 var o=$("#f_chainId").next();
		 $(o).html('<option value=""><fmt:message key="common.select.plesesSelect"/></option>');
	 }
}

/**
 * 控制客人预订必填项选择
 */
function customRequired(reqType){
	var mobileChk = document.getElementById('f_isMobileRequired');
	var emailChk = document.getElementById('f_isEmailRequird');
	if(!mobileChk.checked&&!emailChk.checked){
		
		alert('<fmt:message key='ccm.error.061'/>');
		if(reqType=='mobile'){
			mobileChk.checked = true;
		}else if(reqType=='email'){
			emailChk.checked = true;
		}
	}

}

/**
 * 必须支持中文
 */
function supportChineseRequired(){
	var chineseChk = document.getElementById('f_isSupportChinese');
	if(!chineseChk.checked){
		alert('<fmt:message key='ccm.error.062'/>');
		chineseChk.checked = true;
	}
}

function setContent(languageSel,descType){
	//新增时验证
	$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"hotel_ajaxloadDefaultDesc.do",
	   	  data:{"language":languageSel.value,"descType":descType},
		  success:function(data){
			  var ta = $(languageSel).parent().find('textarea');
			  ta.val(data);
		  }
	});
}

//添加一项多语言
function addLanguage(t,switch_Id){
	addLanguageRow(t,switch_Id);
}

//移除一行语言
function deleteRow(t,switch_Id){
	deleteLanguageRow(t,switch_Id);
}

function executeMoreLanguage(){
	
	//拼接 多语言Json格式: 
	var moreLanguagesJson = '';
	var flag = true;
	
	var tempNameCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_hotelName').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_hotelName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var name = $(this).find('input[name="language.name"]').val();
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempNameCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店名称】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果未填写名称 
			if(strIsNull(name)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
				flag = false;
				return false;
			}
				
			//拼接语言种类
			tempNameCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',name:'"+escapeAcutes(name)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempUsedCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_usedName').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_usedName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var userdName = $(this).find('input[name="language.userdName"]').val();
			var selStr = ','+sel.val()+',';
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyNameUsedBefore"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店曾用名】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempUsedCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyNameUsedBefore"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店曾用名】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyNameUsedBefore"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店曾用名】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}
				
			//拼接语言种类
			tempUsedCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',userdName:'"+escapeAcutes(userdName)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempShortCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_shortName').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_shortName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var shortName = $(this).find('input[name="language.shortName"]').val();
			var selStr = ','+sel.val()+',';
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyAbbreviation"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店简称】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempShortCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyAbbreviation"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店简称】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyAbbreviation"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店简称】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}
				
			//拼接语言种类
			tempShortCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',shortName:'"+escapeAcutes(shortName)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempAddressCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_address').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_address'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var address = $(this).find('input[name="language.address"]').val();
			var selStr = ','+sel.val()+',';
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyAddress"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店地址】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempAddressCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyAddress"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店地址】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyAddress"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店地址】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}
			
			//如果未填写地址
			if(strIsNull(address)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyAddress"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageDescription"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店地址】多语言的第'+$(this).find('td:eq(0)').text()+'项的地址未填写,请检查.');
				flag = false;
				return false;
			}
			
			//拼接语言种类
			tempAddressCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',address:'"+escapeAcutes(address)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	//验证 酒店名称和地址要相匹配
	$('#ml_switch_hotelName').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_hotelName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var selStr = ','+sel.val()+',';
			
			//判断
			if(tempAddressCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyAddress"/>');
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				arry.push('<fmt:message key="ccm.PropertyList.PropertyAddress"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeListError"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店地址】多语言与【酒店名称】多语言列表不一致,【酒店地址】多语言中不存在语种:'+sel.find("option:selected").text()+',请添加.');
				flag = false;
				return false;
			}
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;

	var tempBusinessCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_business').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_business'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var business = $(this).find('input[name="language.business"]').val();
			var selStr = ','+sel.val()+',';
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.BusinessZone"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【商业区】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempBusinessCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.BusinessZone"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【商业区】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.BusinessZone"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【商业区】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}
				
			//拼接语言种类
			tempBusinessCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',business:'"+escapeAcutes(business)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempDescCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_description').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_description'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var description = $(this).find('textarea[name="language.description"]').val();
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyIntroduction"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店概况】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempDescCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyIntroduction"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店概况】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyIntroduction"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店概况】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}
			
			//如果未填写地址
			if(strIsNull(description)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PropertyIntroduction"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageDescription"/>';
				alert(i18n_replace(str,arry));
				//alert('【酒店概况】多语言的第'+$(this).find('td:eq(0)').text()+'项的概况未填写,请检查.');
				flag = false;
				return false;
			}

			//拼接语言种类
			tempDescCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',description:'"+escapeAcutes(description)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempSaluCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_salutatory').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_salutatory'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var salutatory = $(this).find('textarea[name="language.salutatory"]').val();
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.WelcomeWords"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【欢迎词】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempSaluCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.WelcomeWords"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【欢迎词】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.WelcomeWords"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【欢迎词】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}

			//拼接语言种类
			tempSaluCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',salutatory:'"+escapeAcutes(salutatory)+"'}";
		}
	});
	
	var tempCheckInCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_checkInTimeDesc').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_checkInTimeDesc'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var checkInTimeDesc = $(this).find('textarea[name="language.checkInTimeDesc"]').val();
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CheckInTimeDescription"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【入住时间描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempCheckInCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CheckInTimeDescription"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【入住时间描述】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CheckInTimeDescription"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【入住时间描述】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}

			//如果未填写地址
			if(strIsNull(checkInTimeDesc)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CheckInTimeDescription"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageDescription"/>';
				alert(i18n_replace(str,arry));
				//alert('【入住时间描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的入住时间描述未填写,请检查.');
				flag = false;
				return false;
			}
			//拼接语言种类
			tempCheckInCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',checkInTimeDesc:'"+escapeAcutes(checkInTimeDesc)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempCheckOutCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_checkOutTimeDesc').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_checkOutTimeDesc'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var checkOutTimeDesc = $(this).find('textarea[name="language.checkOutTimeDesc"]').val();
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CheckOutTimeDescription"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【退房时间描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempCheckOutCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CheckOutTimeDescription"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【退房时间描述】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CheckOutTimeDescription"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【退房时间描述】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}
			//如果未填写地址
			if(strIsNull(checkOutTimeDesc)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CheckOutTimeDescription"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageDescription"/>';
				alert(i18n_replace(str,arry));
				//alert('【退房时间描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的退房时间描述未填写,请检查.');
				flag = false;
				return false;
			}

			//拼接语言种类
			tempCheckOutCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',checkOutTimeDesc:'"+escapeAcutes(checkOutTimeDesc)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempcancelPolicyCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_cancelPolicyDesc').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_cancelPolicyDesc'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var cancelPolicyDesc = $(this).find('textarea[name="language.cancelPolicyDesc"]').val();
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				arry.push('<fmt:message key="ccm.PropertyList.CancellationPolicy"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【取消政策】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempcancelPolicyCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CancellationPolicy"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【取消政策】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CancellationPolicy"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【取消政策】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}
			//如果未填写地址
			if(strIsNull(cancelPolicyDesc)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.CancellationPolicy"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageDescription"/>';
				alert(i18n_replace(str,arry));
				//alert('【取消政策】多语言的第'+$(this).find('td:eq(0)').text()+'项的取消政策未填写,请检查.');
				flag = false;
				return false;
			}
			//拼接语言种类
			tempcancelPolicyCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',cancelPolicyDesc:'"+escapeAcutes(cancelPolicyDesc)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempPayRemindCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_payRemind').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_payRemind'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var payRemind = $(this).find('textarea[name="language.payRemind"]').val();
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				arry.push('<fmt:message key="ccm.PropertyList.PaymentAlert"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【支付提醒】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempPayRemindCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PaymentAlert"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【支付提醒】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PaymentAlert"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【支付提醒】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}

			//拼接语言种类
			tempPayRemindCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',payRemind:'"+escapeAcutes(payRemind)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempPickUpServiceCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_pickUpService').find('table>tbody>tr:not(:last)').each(function(){		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_pickUpService'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var pickUpService = $(this).find('textarea[name="language.pickUpService"]').val();
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				arry.push('<fmt:message key="ccm.PropertyList.PickupServiceAlert"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【接机服务提醒】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			//判断语言种类是否已重复
			if(tempPickUpServiceCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PickupServiceAlert"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【接机服务提醒】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PropertyList.PickupServiceAlert"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.UserActivityLog.PropertyName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【接机服务提醒】多语言的语种:【'+sel.find("option:selected").text()+'】在【酒店名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}

			//拼接语言种类
			tempPickUpServiceCodes += selStr;
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',pickUpService:'"+escapeAcutes(pickUpService)+"'}";
		}
	});

	//如果校验通过
	if(flag){
		//如果不为空,拼接成json
		if(!strIsNull(moreLanguagesJson)){
			moreLanguagesJson = '['+moreLanguagesJson.substring(1)+']';
			$('#f_hotelI18ns').val(moreLanguagesJson);
		}
	}
	return flag;
}
//第三方支付
function checkboxThirdPay(){
	if ($('#f_alipay').is(":checked")) {
        $('#alipayUl').show();
        $('#f_partner').addClass("required");
        $('#f_checkCode').addClass("required");
        $('#f_sellerEmail').addClass("required");
    }else{
    	$('#alipayUl').hide();
    	$('#f_partner').removeClass("required");
        $('#f_checkCode').removeClass("required");
        $('#f_sellerEmail').removeClass("required");
        $('#f_partner').val("");
        $('#f_checkCode').val("");
        $('#f_sellerEmail').val("");
    }
	if ($('#f_mpay').is(":checked")) {
		$('#mpayUl').show();
		$('#f_merchantid').addClass("required");
        $('#f_merchant_tid').addClass("required");
        $('#f_securekey').addClass("required");
    }else{
    	$('#mpayUl').hide();
    	$('#f_merchantid').removeClass("required");
        $('#f_merchant_tid').removeClass("required");
        $('#f_securekey').removeClass("required");
        $('#f_merchantid').val("");
        $('#f_merchant_tid').val("");
        $('#f_securekey').val("");
    }
}
</script>
