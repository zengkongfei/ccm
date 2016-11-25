<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<div class="title_wp">
	<div class="bt">
		<a href="#" class="btn_2 blue" onclick="addRow()"><fmt:message key="common.button.New"/></a>
	</div>
	<fmt:message key="ccm.RegionalCityManagement"/>
</div>
<div id="show" class="ccm-popup width900 zoom-anim-dialog mfp-hide"></div>

<div class="c_whitebg"> 
          <!--搜索项-->
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
                <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.RegionalCityManagement.State"/>：</span></div>
                <div class="i_input">
				  	<select class="fxt w120 city" id="country" parent="" child="province">
				  		<option value=""></option>
				  		<c:forEach items="${cityList }" var="city">
					  		<option value="${city.id }" code="${city.cityCode }">${city.cityName }</option>
				  		</c:forEach>
					</select>
                </div>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.RegionalCityManagement.Province"/>：</span></div>
                <div class="i_input">
				  	<select class="fxt w120 city" id="province" parent="country" child="city">
				  		<option value=""></option>
					</select>
                </div>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.RegionalCityManagement.City"/>：</span></div>
                <div class="i_input">
				  	<select class="fxt w120 city" id="city" parent="province">
				  		<option value=""></option>
					</select>
                </div>
                <!-- <div class="i_title"><span class="text"></span><span class="text">县/区：</span></div>
                <div class="i_input">
				  	<select class="fxt w120 city" id="area">
				  		<option>临川县</option>
				  		<option>乐安县</option>
					</select>
                </div> -->
              </li>
            </ul>
          </div>
          <div id="address"><fmt:message key="ccm.RegionalCityManagement.Location"/>：</div>
          
          <!--列表样式-->
          <div class="bt_wp">
	          	<table class="ccm_table1" width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table">
	          		<input value="0" type="hidden"  id="tableParentId"/>
	          		<input value="country" type="hidden"  id="tableName"/>
	          		<tr bgcolor="#D9D9D9"  >
	          			<th align="center"><fmt:message key="ccm.RegionalCityManagement.Name"/></th>
	          			<th align="center"><fmt:message key="ccm.RegionalCityManagement.Code"/></th>
	          			<th align="center"><fmt:message key='common.button.Activity'/>	</th>
	          		</tr>
	          		<tbody id="tbody">
		          		<c:forEach items="${cityList }" var="city">
			          		<tr>
			          			<input value="${city.id }" type="hidden" name="id"/>
			          			<td class="cityName">${city.cityName }</td>
			          			<td class="cityCode">${city.cityCode }</td>
			          			<td>
			          			<a href="javascript:;" class="link del_ifself" onclick="delRow(this);"><fmt:message key="common.button.delete"/>	</a>
			          			</td>
			          		</tr>
					  	</c:forEach>
	          		
	          		</tbody>
	          		
	          	</table>
          </div>
        </div>

<!-- 内容区域 end-->
<script type="text/javascript">

	$(document).ready(function() {
		$('#country').change(function(){
		});
		$('.city').change(function(){
			if($(this).val()!=null&&$(this).val()!=''){
				$("#tableParentId").val($(this).val());
				ajaxRequestData($(this).val(),"child",this);
			}else{
				var parent=$(this).attr("parent");
				if(parent!=null&&parent!=''){
					$("#tableParentId").val($("#"+parent).val());
					$("#tableName").val(parent);
					ajaxRequestData($("#"+parent).val(),"parentt",this);
				}else{
					$("#tableName").val("country");
					$("#tableParentId").val(0);
					ajaxRequestData(0,"parentt",this);
				}
			}
		})
		event();
	})

	//地址
	function getAddress(){
		var city = $(".city");
		var addr = '';
		for(var i=0;i<city.length;i++){
			var text = $(city).eq(i).find("option:selected").text();
			if(text!=null&&text!=''){
				addr=addr+text+"->";
			}
		}
		$("#address").text("<fmt:message key='ccm.RegionalCityManagement.Location'/>："+addr.substring(0,addr.length-2));
	}
	
	//改变下拉框
	function changeSelect(data){
		var parentId = $("#tableParentId").val();
		var selectId = $("#tableName").val();
		var json = eval(data);
		var html = '<option value=""></option>';
		for(var i = 0;i<json.length;i++){
			var selected='';
			if(parentId==json[i].id){
				selected = 'selected="selected"';
			}
			html = html+'<option value="'+json[i].id+'"'+selected+' code="'+json[i].cityCode+'">'+json[i].cityName+'</option>';
		}
		$("#"+selectId).html(html);
	}
	
	
	//清除子类所有的下拉框
	//obj 为该元素
	function cleanChildSelect(obj){
		var objId = $(obj).attr("id");
		var city = $(".city");
		var flag=false;
		for(var i=0;i<city.length;i++){
			if(flag){
				$(city).eq(i).html('');
			}else{
				if($(city).eq(i).attr("id")==objId){
					flag=true;
				}
			}
		}
	}
	
	
	//获取数据
	function ajaxRequestData(parentId,type,obj) {
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
				if(type=="child"){
					var child=$(obj).attr("child");
					$("#tableName").val(child);
					cleanChildSelect(obj);
					changeSelect(data);
				}else{
					cleanChildSelect(obj);
				}
				changTable(data);
				getAddress();
				event();
			}
		});
	}
	//改变表格数据
	function changTable(list){
		var html ='';
		for(var i = 0;i<list.length;i++){
			html=html+'<tr>'
				+'<input value="'+list[i].id+'" type="hidden" name="id"/>'
				+'<td class="cityName">'+list[i].cityName+'</td>'
				+'<td class="cityCode">'+list[i].cityCode+'</td>'
				+'<td>'
				+'<a href="javascript:;" class="link del_ifself" onclick="delRow(this);"><fmt:message key="common.button.delete"/>	</a>'
				+'</td></tr>';
		}
		$("#tbody").html(html);
	}
	//添加一行
	function addRow() {
		var html = '<tr><input  type="hidden" name="id"/></input>'
				+ '<td ><input name="cityName"></input></td>'
				+ '<td ><input name="cityCode"></input></td>'
				+ '<td>'
				+ '<a href="#" class="link mgR12" onclick="submit(this);"><fmt:message key="common.button.OK"/></a>'
				+ '<a href="#" class="link mgR12" onclick="removeRow(this);"><fmt:message key="common.button.Cancel"/></a>'
				+ '</td>' + '</tr>';
		$("#tbody").append(html);
		$("#tbody").find("tr:last-child").find("td").eq(0).find("input[name='cityName']").focus();
	}
	//确认
	function submit(obj) {
		var parentId = $("#tableParentId").val();
		var cityName = $(obj).parent().prev().prev().find("input[name='cityName']").val();
		var cityCode = $(obj).parent().prev().find("input[name='cityCode']").val();
		var tr = $(obj).parent().parent();
		
		$.ajax({
			type : "post",
			url : "city_ajaxCheckCityCode.do",
			data : {
				"cityCode" : cityCode
			},
			cache : false,
			dataType : 'text',
			beforeSend : function() {
			},
			success : function(data) {
				if(data=='true'){
					$.ajax({
						type : "post",
						url : "city_saveCity.do",
						data : {"city.cityCode" : cityCode,"city.cityName" : cityName,"city.parentId" : parentId},
						cache : false,
						dataType : 'json',
						success : function(data) {
							if(data.type=='success'){
								$(tr).find("input[name='id']").val(data.id);
								$(tr).find("td").eq(0).text(cityName).addClass("cityName");
								$(tr).find("td").eq(1).text(cityCode).addClass("cityCode");
								$(tr).find("td").eq(2).find("a").remove();
								$(tr).find("td").eq(2).append('<a href="javascript:;" class="link del_ifself" onclick="delRow(this);"><fmt:message key="common.button.delete"/></a>');
								changeSelect(data.cityList);
								event();
							}
						}
					});
				}else{
					alert("<fmt:message key='ccm.RegionalCityManagement.Code'/>："+cityCode+"<fmt:message key='ccm.RegionalCityManagement.error.003'/>");
					return false;
				}
			}
		});
	}

	//删除一行
	function removeRow(obj){
		$(obj).parent().parent().remove();
	}
	//删除一行
	function delRow(obj){
		var id = $(obj).parent().parent().find("input[name='id']").val();
		$.ajax({
			type : "post",
			url : "city_updateCity.do",
			data : {"city.id" : id,"city.delFlag" : true,"parentId":$("#tableParentId").val()},
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data.type=='success'){
					alert("<fmt:message key='ccm.RegionalCityManagement.DeletedSuccessfully'/>");
					$(obj).parent().parent().remove();
					changeSelect(data.cityList);
					event();
				}
			}
		});
	}
	
	//事件
	function event(){
		$('.cityName').dblclick(function(){
			var obj =$(this);
			$(obj).removeClass("cityName");
			var text = $(obj).text();
			$(obj).text("");
			var input ="<input value='"+text+"' class='input'></input>";
			$(obj).append(input);
			$(obj).find(".input").bind("mouseout",function(){
				var id = $(obj).parent().find("input[name='id']").val();
				var val = $(this).val();
				if(val==''){
					alert("<fmt:message key='ccm.RegionalCityManagement.error.001'/>");
					$(this).focus();
					return false;
				}
				$.ajax({
					type : "post",
					url : "city_updateCity.do",
					data : {"city.id" : id,"city.cityName" : val,"city.parentId":$("#tableParentId").val(),"parentId":$("#tableParentId").val()},
					cache : false,
					dataType : 'json',
					success : function(data) {
						if(data.type=='success'){
							$(obj).text(val);
							$(obj).addClass("cityName");
							changeSelect(data.cityList);
						}
					}
				});
			})
		});
		$('.cityCode').dblclick(function(){
			var obj =$(this);
			$(obj).removeClass("cityCode");
			var text = $(obj).text();
			$(obj).text("");
			var oldCityCode=text;
			var input ="<input value='"+text+"' class='input'></input>";
			$(obj).append(input);
			$(obj).find(".input").bind("mouseout",function(){
				var id = $(obj).parent().find("input[name='id']").val();
				var val = $(this).val();
				if(val==''){
					alert("<fmt:message key='ccm.RegionalCityManagement.error.002'/>");
					$(this).focus();
					return false;
				}else if(oldCityCode==val){
					$(obj).text(val);
					$(obj).addClass("cityCode");
				}else{
					$.ajax({
						type : "post",
						url : "city_ajaxCheckCityCode.do",
						data : {
							"cityCode" : val
						},
						cache : false,
						dataType : 'text',
						beforeSend : function() {
						},
						success : function(data) {
							if(data=='true'){
								$.ajax({
									type : "post",
									url : "city_updateCity.do",
									data : {"city.id" : id,"city.cityCode" : val,"city.parentId":$("#tableParentId").val(),"parentId":$("#tableParentId").val()},
									cache : false,
									dataType : 'json',
									success : function(data) {
										if(data.type=='success'){
											$(obj).text(val);
											$(obj).addClass("cityCode");
											changeSelect(data.cityList);
										}
									}
								});
							}else{
								alert("<fmt:message key='ccm.RegionalCityManagement.Code'/>："+val+"<fmt:message key='ccm.RegionalCityManagement.error.003'/>");
								return false;
							}
						}
					});
				}
			})
		});
	}
</script>