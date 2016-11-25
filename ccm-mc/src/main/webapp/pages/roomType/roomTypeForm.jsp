<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.RoomTypeList.RoomTypeCreation"/> </div>
        <s:form id="roomTypeForm" action="/roomType_save.do" method="post">
        <appfuse:ccmToken name="token"></appfuse:ccmToken>
        <s:hidden id="f_roomTypeId" name="roomTypevo.roomTypeId"/>
        <s:hidden id="f_roomTypeI18ns" name="f_roomTypeI18ns" />
        <s:hidden id="f_picId"	name="roomTypevo.picId" />
        <s:hidden id="f_hotelId" name="roomTypevo.hotelId" ></s:hidden>
        <div class="c_whitebg pdA12">
          <div class="mgB24">
            <ul class="list_input">
              <li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.RoomTypeList.RoomTypeTemplate"/>：</span></div>
                <div class="i_input">
               		<select id="f_roomTypeTemplateId" name="roomTypevo.roomTypeTemplateId" value="${roomTypevo.roomTypeTemplateId}"
                			class="fxt w180 " >
	                		<option value=""><fmt:message key="common.select.plesesSelect"/></option>
	                		<c:forEach items="${roomTypeTempList}" var="temp" >
	                  			<option value="${temp.roomTypeTemplateId}"  code="${temp.roomTypeTemplateCode}" ${temp.roomTypeTemplateId == roomTypevo.roomTypeTemplateId?"selected":""}>
		                  			${temp.roomTypeTemplateCode}
	                  			</option>
	                  		</c:forEach>
                	</select>
                	<c:if test="${not empty roomTypevo.roomTypeId}">
              			<input type="hidden" id="roomTypeTemplateId" name="roomTypevo.roomTypeTemplateId" value="${roomTypevo.roomTypeTemplateId}" />
        			</c:if>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.Channel.RoomTypeCode"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_roomTypeCode" name="roomTypevo.roomTypeCode" cssClass="fxt w150 required"  ></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.Channel.RoomName"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_roomTypeName" name="roomTypevo.roomTypeName" cssClass="fxt w150 required"></s:textfield>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_roomTypeName"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.RoomTypeTemplate.MaxOccupancy"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_maxOccupancy" name="roomTypevo.maxOccupancy" maxlength="4" cssClass="fxt w150 numOnly required" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"></s:textfield>
                </div>
              </li>
              <li id="ml_switch_roomTypeName" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty roomTypevo.roomTypeI18nList}">
							<c:forEach items="${roomTypevo.roomTypeI18nList}" var="roomTypeI18n" varStatus="vstatus"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == roomTypeI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" 
													value="${roomTypeI18n.roomTypeName}" />  
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_roomTypeName');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
							</c:forEach>
						</c:if>
						
						<tr id="mdl_switch_roomTypeName" style="display:none;">  
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
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_roomTypeName');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_roomTypeName')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
               <li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.RoomTypeList.RoomTypePicture"/>：</span>
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
			   <c:if test="${not empty roomTypevo.pictureList}">
			   		<li>
					<div class="i_title invisible">
						<span class="star"></span><span class="text">&nbsp;</span>
					</div>
					<div class="i_input ">
						<span id="picShow">
							<div class="div_upload_container">
								<span id="Span_Imgcon_Close" class="EIB div_imgcon_close"></span>
								<c:forEach items="${roomTypevo.pictureList}" var="picture">
									<div class="div_img" picId="${picture.picId}">
										<img src="${roomTypevo.roomPicUrl} ${picture.url}" />
									</div>
								</c:forEach>
								<div class="clearboth"></div>
							</div> 
						</span>
					</div>
			  		</li>
			   </c:if>
			 <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.RoomTypeList.Area"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_area" name="roomTypevo.area" cssClass="fxt w150"></s:textfield>40/10-20
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.RoomTypeList.BedSize"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_bed_size" name="roomTypevo.bed_size" cssClass="fxt w150 number"></s:textfield>1.8/1.5/1.2
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.RoomTypeList.BedType"/>：</span></div>
                <div class="i_input">
	                <%-- <s:select id="f_bedTypeCode" name="roomTypevo.bedTypeCode" list="dictCodeList" listKey="codeNo" listValue="codeLabel"
						headerKey="" headerValue="请选择" cssClass="fxt w180">
					</s:select> --%>
					<select Class="fxt w180" name="roomTypevo.bedTypeCode">
						<option value=""><fmt:message key="common.select.plesesSelect"/></option>
						<s:iterator value="#request.dictCodeList" var="dictCode">
							<option value="<s:property value="#dictCode.codeNo" />" <s:if test="#dictCode.codeNo==roomTypevo.bedTypeCode">selected="selected"</s:if> ><s:property value="#dictCode.codeLabel" /> </option>
						</s:iterator>
						
					</select>
					
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.RoomTypeList.PhysicalRooms"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_physicalRooms" name="roomTypevo.physicalRooms" cssClass="fxt w150 number"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.Rates.DisplaySequence"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_sortNum" name="roomTypevo.sortNum" cssClass="fxt w150 onlyNum" maxlength="6"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="common.Description"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_description" name="roomTypevo.description" cssClass="fxt w491 h150"></s:textarea>
                </div>
                <div class="i_input">
                 	&nbsp;<button type="button" class="btn_3 white mgR6 " id="switch_description" onclick="switchHtmlLanguage('switch_description','language.description');"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_description" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 550px;">
						<c:if test="${not empty roomTypevo.roomTypeI18nList}">
							<c:forEach items="${roomTypevo.roomTypeI18nList}" var="roomTypeI18n" varStatus="vstatus"> 
								<c:if test="${not empty roomTypeI18n.description }">
								<tr >
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == roomTypeI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="common.Description"/>:<textarea  class="fxt w491 h150" style="margin-top:5px;margin-bottom:5px;" 
												name="language.description" >${roomTypeI18n.description}</textarea>
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
								<fmt:message key="common.Description"/>:<textarea  class="fxt w491 h150" style="margin-top:5px;margin-bottom:5px;" 
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
<link href="<c:url value='/upload/css/uploadskin.css'/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value='/upload/css/upload.css'/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<c:url value='/upload/core/swfupload.js'/>${global_js_revision}"></script>
<script type="text/javascript" src="<c:url value='/upload/upload.js'/>${global_js_revision}"></script>
<script type="text/javascript" src="<c:url value='js/jHtmlArea-0.8.js'/>"></script>
<link rel="Stylesheet" type="text/css" href="<c:url value='css/jHtmlArea.css'/>" />
<script type="text/javascript" src="<c:url value='js/jHtmlArea.ColorPickerMenu-0.8.js'/>"></script>
<link rel="Stylesheet" type="text/css" href="<c:url value='css/jHtmlArea.ColorPickerMenu.css'/>" />
<style type="text/css">
     div.jHtmlArea { border: solid 1px #ccc; }
     
</style>
<script>
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
	
	//只能输入数字
	$(".numOnly").keyup(function() {
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	}).bind("paste", function() { //CTR+V事件处理 
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用    
	//设置房型编码不能编辑
	if(!strIsNull($('#f_roomTypeId').val())){
		$('#f_roomTypeTemplateId').attr("disabled", true);
		
	}
	$('#f_roomTypeCode').attr("readonly",true);
	
	$('#f_roomTypeTemplateId').change(function(){
		//获取选中的roomTypeTemplateId
		var roomTypeTemplateId = $(this).val();
		
		//设置内容
		var tempCode = $(this).find("option:selected").attr('code');
		$('#f_roomTypeCode').val(tempCode);

		//新增时验证
		$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"roomType_ajaxloadRoomTypeTemplateInfo.do",
	   	  data:{"roomTypeTemplateId":roomTypeTemplateId},
		  success:function(data){
			   //房型名称和描述
			   var moreTempName = $('#ml_switch_roomTypeName');
			   var modelTempName = $('#mdl_switch_roomTypeName');
			   var moreDescription = $('#ml_switch_description');
			   var modelDescription = $('#mdl_switch_description');
				  
			   //除模板行以外,移除其他行 
			   moreTempName.find('table>tbody').find("tr:not(:last)").each(function(){
					if($(this).attr('id') != 'mdl_switch_roomTypeName'){
						$(this).remove();
					}
			   });
				  
			   //除模板行以外,移除其他行 
			   moreDescription.find('table>tbody').find("tr:not(:last)").each(function(){
					if($(this).attr('id') != 'mdl_switch_description'){
						$(this).remove();
					}
			   });
			   
			  $('#f_roomTypeName').val('');
			  $('#f_description').text('');
				
			  if(strIsNull(data)){
				  return false;
			  }
			  var tempList = eval("("+data+")");
			  
			  for(var i=0;i<tempList.length;i++){
				  //如果是默认语言
				  if(tempList[i].isDefault == 'Yes'){
					  $('#f_roomTypeName').val(tempList[i].tempName);
					  $('#f_description').text(tempList[i].description);
				  }else{
					  //如果房型名不为空
					  if(!strIsNull(tempList[i].tempName)){
						  //设置包价名称
						  moreTempName.find('table>tbody').find("tr:last").before(
								  '<tr><td class="w20">1.</td>'+modelTempName.html()+'</tr>');
						  var tempNameTr = moreTempName.find('table>tbody').find("tr:last").prev();
						  var sel = tempNameTr.find("select[name='language.codeNo']");
						  var pn  = tempNameTr.find("input[name='language.name']");
						  sel.val(tempList[i].language);
						  pn.val(tempList[i].tempName);
					  }
					  
					  //如果描述不为空
					  if(!strIsNull(tempList[i].description)){
						  //设置描述
						  moreDescription.find('table>tbody').find("tr:last").before(
								  '<tr><td class="w20">1.</td>'+modelDescription.html()+'</tr>');
						  var descriptionTr = moreDescription.find('table>tbody').find("tr:last").prev();
						  var sel2 = descriptionTr.find("select[name='language.codeNo']");
						  var desc  = descriptionTr.find("textarea[name='language.description']");
						  sel2.val(tempList[i].language);
						  desc.text(tempList[i].description);
					  }
				  }
			  }
	       }
	    });	
		
	});
	
	$('.onlyNum').keydown(function(e){
		return filterNumInput(e,$(this).val());
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
	    postParams: {"bizId" : "${roomTypevo.picId}","bizType" : "2"},
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
	
	$("textarea[name='roomTypevo.description']").htmlarea({
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
        }),
        loaded: function() {
            
        }
    });
	
	
	$("#roomTypeForm").effectiveAndValidate( {
		rules : {
			'roomTypevo.maxOccupancy' : {
				required : true,
				range : [ 1,5 ]
			}
		},
		
		messages : {
		     'roomTypevo.maxOccupancy' : {
		    	 range: '<span class="infotext"><fmt:message key="ccm.RoomTypeTemplate.MaxOccupancy.errorMsg"/></span>'
		     }
		}
	});
	
	//保存
	$('.f_save').click(function(){
		//验证表单
		if(!$("#roomTypeForm").valid()){
			return;
		}
		
		//验证多语言并且重组数据 
		var flag = executeMoreLanguage();
		
		if(!flag){
			return;
		}
		$('.f_save').attr('disabled',"true");
		$('.f_save').addClass("black ");
		
		//修改
		if($('#f_roomTypeId').val().length>0){
			$("#roomTypeForm").submit();
			return;
		}
		
		//新增时验证房型代码
		$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"roomType_isRoomTypeCode.do",
	   	  data:{"roomTypevo.roomTypeCode":$('#f_roomTypeCode').val(),"roomTypevo.hotelId":$('#f_hotelId').val()},
	   	  dataType:"text",
		  success:function(data){
			  if("false" == data){
				 alert("<fmt:message key='ccm.error.013'/>");
		  	  }else{
		  		 $("#roomTypeForm").submit();
		  	  }
	       }
	    });
	});
});

function switchHtmlLanguage(switch_Id,c_name){
	var moreLanguageSwitch = $('#ml_'+switch_Id);
	var modelLanguage = $('#mdl_'+switch_Id);
	
	if(moreLanguageSwitch.is(':hidden')){
		moreLanguageSwitch.show();
		
		//如果仅剩一行记录
		if(moreLanguageSwitch.find('table>tbody>tr').length==2){
			var lastTr= moreLanguageSwitch.find('table>tbody').find("tr:last");
			lastTr.before('<tr><td class="w20">1.</td>'+modelLanguage.html()+'</tr>');
		
			var newTxtArea = lastTr.prev('tr').find('textarea[name="'+c_name+'"]');
			setHtmlArea(newTxtArea);
		}else{
			//循环遍历拼接多语言字符串
			moreLanguageSwitch.find('table>tbody>tr:not(:last)').each(function(){
				//不能加载多语言模型行
				if($(this).attr('id') != 'mdl_switch_description'){
					var description = $(this).find('textarea[name="language.description"]');
					setHtmlArea(description);
				}
			});
		}
	}else{
		moreLanguageSwitch.hide();
	}
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
	$('#ml_switch_roomTypeName').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_roomTypeName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var name = $(this).find('input[name="language.name"]').val();
			
			var selStr = ','+sel.val()+',';
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.RateValidityPeriodReport.RoomTypeName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【房型名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			
			//判断语言种类是否已重复
			if(tempNameCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.RateValidityPeriodReport.RoomTypeName"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【房型名称】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			
			//如果未填写名称 
			if(strIsNull(name)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.RateValidityPeriodReport.RoomTypeName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
				alert(i18n_replace(str,arry));
				//alert('【房型名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
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
				arry.push('<fmt:message key="ccm.RateValidityPeriodReport.RoomTypeName"/>');
				arry.push('<fmt:message key="common.Description"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeListError"/>';
				alert(i18n_replace(str,arry));
				//alert('【描述】多语言的语种:【'+sel.find("option:selected").text()+'】在【房型名称】多语言中未设置,请检查.');
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
			$('#f_roomTypeI18ns').val(moreLanguagesJson);
		}
	}

	return flag;
}


</script>
