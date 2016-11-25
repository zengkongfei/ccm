<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="title_wp"><fmt:message key="ccm.PackageService.EditPropertyPackage"/></div>
<%@ include file="/common/messages.jsp"%>
<s:form id="hotelPackageForm" action="/hotelPackage_save.do" method="post">
<appfuse:ccmToken name="token"></appfuse:ccmToken>
<s:hidden id="f_hotelPackageId" name="hotelPackageVo.hotelPackageId"/>
<s:hidden id="f_hotelPackageI18ns" name="f_hotelPackageI18ns" />
	<div class="c_whitebg pdA12">
		<div class="mgB24">
			<ul class="list_input">
				<li>
					<div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.DynamicPackageList.PackageCode"/>：
						<c:if test="${not empty hotelPackageVo.hotelPackageId}">
              				<s:hidden id="i_packageId" name="hotelPackageVo.packageId"/> 
        				</c:if>
	                	</span>
	                </div>
	                <div class="i_input">
	                 	<select id="f_packageId" name="hotelPackageVo.packageId" class="fxt w390" >
							<option value=""><fmt:message key="common.select.plesesSelect"/></option>
							<c:forEach items="${requestScope.packageList}" var="rl" >
								<option value="${rl.packageId}" ${rl.packageId == hotelPackageVo.packageId?"selected":""}>
									<c:out value="${rl.packageCode}"></c:out>&nbsp;&nbsp;
									<c:out value="${rl.packageName}"></c:out>
								</option>
							</c:forEach>
						</select>						
	                </div>
				</li>
				<li>
	                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PackageService.PackageName"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="f_packageName" name="hotelPackageVo.packageName" cssClass="fxt w240 required"></s:textfield>
	                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_packageName"><fmt:message key="common.MultipleLanguagesSetup"/></button>
	                </div>
              	</li>
              	<li id="ml_switch_packageName" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelPackageVo.hotelPackageI18nList}">
							<c:forEach items="${hotelPackageVo.hotelPackageI18nList}" var="hotelPackageI18n" varStatus="vstatus"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelPackageI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" 
													value="${hotelPackageI18n.packageName}" />  
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_packageName');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_packageName" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select>&nbsp;&nbsp;
								<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name"/> <br> 
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_packageName');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_packageName')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.Rates.DisplaySequence"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_sortNum" name="hotelPackageVo.sortNum" cssClass="fxt w150 onlyNum" maxlength="6"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="common.Description"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_description" name="hotelPackageVo.description" cssClass="fxt w360 h80"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_description"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_description" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty hotelPackageVo.hotelPackageI18nList}">
							<c:forEach items="${hotelPackageVo.hotelPackageI18nList}" var="hotelPackageI18n" varStatus="vstatus"> 
								<c:if test="${not empty hotelPackageI18n.description }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == hotelPackageI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="common.Description"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.description" >${hotelPackageI18n.description}</textarea>
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
								<fmt:message key="common.Description"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
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
			</ul>
		</div>
		<hr class="dashed">
		<div class="listinputCtrl">
			<button type="button" class="btn_1 green mgR12 f_save" onclick="dosubmit()"><fmt:message key="common.button.OK"/></button>
			<a class="btn_1 white" href="/hotelPackage_list.do?menuId=${sessionScope.menuId}"><fmt:message key="common.Return"/></a>
		</div>
	</div>
</s:form>

<script>
	$(document).ready(function() {
		//设置房型编码不能编辑
		if(null!=$('#f_hotelPackageId').val() && ''!=$('#f_hotelPackageId').val()){
			$('#f_packageId').attr("disabled", true);
		}
		
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
		
		$('.packages .confirmthis').click(function(){
			setPackages();
			$(this).parent().parent().slideUp();
		});
		
		$('.onlyNum').keydown(function(e){
			return filterNumInput(e,$(this).val());
		});
		
		$('#f_packageId').change(function(){
			//获取选中的packageId
			var packageId = $(this).val();
			
			//新增时验证
			$.ajax({
		   	  type:"POST",
		   	  async:false,
		   	  url:"hotelPackage_ajaxloadPackageInfo.do",
		   	  data:{"packageId":packageId},
			  success:function(data){
				  
				  //打包名称
				  var morePackageName = $('#ml_switch_packageName');
				  var modelPackageName = $('#mdl_switch_packageName');
					  
				  var moreDescription = $('#ml_switch_description');
				  var modelDescription = $('#mdl_switch_description');
					  
				  //除模板行以外,移除其他行 
				  morePackageName.find('table>tbody').find("tr:not(:last)").each(function(){
					  if($(this).attr('id') != 'mdl_switch_packageName'){
						  $(this).remove();
					  }
				  });
					  
				  //除模板行以外,移除其他行 
				  moreDescription.find('table>tbody').find("tr:not(:last)").each(function(){
					  if($(this).attr('id') != 'mdl_switch_description'){
						  $(this).remove();
					  }
				  });
				  
				  $('#f_packageName').val('');
				  $('#f_description').text('');
					
				  if(strIsNull(data)){
					  return false;
				  }
				  var packageList = eval("("+data+")");

				  for(var i=0;i<packageList.length;i++){
					  //如果是默认语言
					  if(packageList[i].isDefault == 'Yes'){
						  $('#f_packageName').val(packageList[i].packageName);
						  $('#f_description').text(packageList[i].description);
					  }else{
						  //如果包名不为空
						  if(!strIsNull(packageList[i].packageName)){
							//设置包价名称
							  morePackageName.find('table>tbody').find("tr:last").before(
									  '<tr><td class="w20">1.</td>'+modelPackageName.html()+'</tr>');
							  var packageNameTr = morePackageName.find('table>tbody').find("tr:last").prev();
							  var sel = packageNameTr.find("select[name='language.codeNo']");
							  var pn  = packageNameTr.find("input[name='language.name']");
							  sel.val(packageList[i].language);
							  pn.val(packageList[i].packageName);
						  }
						  
						  //如果描述不为空
						  if(!strIsNull(packageList[i].description)){
							  //设置描述
							  moreDescription.find('table>tbody').find("tr:last").before(
									  '<tr><td class="w20">1.</td>'+modelDescription.html()+'</tr>');
							  var descriptionTr = moreDescription.find('table>tbody').find("tr:last").prev();
							  var sel2 = descriptionTr.find("select[name='language.codeNo']");
							  var desc  = descriptionTr.find("textarea[name='language.description']");
							  sel2.val(packageList[i].language);
							  desc.text(packageList[i].description);
						  }
					  }
				  }
		       }
		    });	
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
	});
	
	function dosubmit(url) {
		if(strIsNull($('#f_hotelPackageId').val())&&strIsNull($('#f_packageId').val())){
			alert('<fmt:message key="ccm.error.018"/>');
			return false;
		}
		
		//验证表单
		if(!$("#hotelPackageForm").valid()){
			return false;
		}
		
		//验证多语言并且重组数据 
		var flag = executeMoreLanguage();
		
		if(!flag){
			return;
		}
		
		//如果为空就进行修改 
		if(strIsNull($('#f_hotelPackageId').val())){
			var packageId = $('#f_packageId').val();
			
			//新增时验证
			$.ajax({
		   	  type:"POST",
		   	  async:false,
		   	  url:"hotelPackage_isHotelPackage.do",
		   	  data:{"hotelPackageVo.packageId":packageId},
			  success:function(data){
				  if("true" == data){
					  $("#hotelPackageForm").submit();
			  	  }else if(data.indexOf("false")>=0){
			  		var packageCode = data.substring(6);
			  		var arry = new Array();
					arry.push(packageCode);
					var str = '<fmt:message key="ccm.error.019"/>';
					alert(i18n_replace(str,arry));
			  		// alert("当前酒店已经绑定了包价【"+packageCode+"】,不允许再次绑定.");
			  	  }
		       }
		    });	
		}else{
			$("#hotelPackageForm").submit();
			//禁止重复提交
			$('.f_save').addClass('no_ald');
			$('.f_save').attr("disabled","disabled");
		}
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
		var codes = '';
		var flag = true;
		
		var tempNameCodes = '';
		//循环遍历拼接多语言字符串
		$('#ml_switch_packageName').find('table>tbody>tr:not(:last)').each(function(){
			
			//不能加载多语言模型行
			if($(this).attr('id') != 'mdl_switch_packageName'){
				var sel = $(this).find('select[name="language.codeNo"]');
				var name = $(this).find('input[name="language.name"]').val();
				
				var selStr = ','+sel.val()+',';
				//如果未选择语言类型
				if(strIsNull(sel.val())){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.PackageService.PackageName"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
					alert(i18n_replace(str,arry));
					//alert('【包价名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
					flag = false;
					return false;
				}
				
				//判断语言种类是否已重复
				if(tempNameCodes.indexOf(selStr)>=0){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.PackageService.PackageName"/>');
					arry.push(sel.find("option:selected").text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
					alert(i18n_replace(str,arry));
					//alert('【包价名称】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
					flag = false;
					return false;
				}
				
				//如果未填写名称 
				if(strIsNull(name)){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.PackageService.PackageName"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
					alert(i18n_replace(str,arry));
					//alert('【包价名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
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
					arry.push('<fmt:message key="common.Description"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
					alert(i18n_replace(str,arry));
					//alert('【描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
					flag = false;
					return false;
				}
				
				//判断语言种类是否已重复
				if(tempDescCodes.indexOf(selStr)>=0){
					var arry = new Array();
					arry.push('<fmt:message key="common.Description"/>');
					arry.push(sel.find("option:selected").text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
					alert(i18n_replace(str,arry));
					//alert('【描述】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
					flag = false;
					return false;
				}
				
				//如果不在模板名称多语言范围内 
				if(tempNameCodes.indexOf(selStr)<0){
					var arry = new Array();
					arry.push('<fmt:message key="common.Description"/>');
					arry.push(sel.find("option:selected").text());
					arry.push('<fmt:message key="ccm.PackageService.PackageName"/>');
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
					alert(i18n_replace(str,arry));
					//alert('【描述】多语言的语种:【'+sel.find("option:selected").text()+'】在【包价名称】多语言中未设置,请检查.');
					flag = false;
					return false;
				}

				//拼接语言种类
				tempDescCodes += selStr;
				
				moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',description:'"+escapeAcutes(description)+"'}";
			}
		});

		//如果校验通过
		if(flag){
			//如果不为空,拼接成json
			if(!strIsNull(moreLanguagesJson)){
				moreLanguagesJson = '['+moreLanguagesJson.substring(1)+']';
				$('#f_hotelPackageI18ns').val(moreLanguagesJson);
			}
		}

		return flag;
	}
</script>