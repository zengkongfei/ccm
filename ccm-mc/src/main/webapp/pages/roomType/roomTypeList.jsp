<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.RoomTypeList"/>
          <div class="bt"> 
          	<a href="javascript:addData();" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/roomType_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
          		<!--  
          		<li class="col3_1">
	                <div class="i_title"><span class="text">酒店：</span></div>
	                <div class="i_input">
	                  <s:select id="l_hotelId" name="creteria.hotelId" list="hotelList" listKey="hotelId" listValue="hotelCode"
						headerKey="" headerValue="不限" cssClass="fxt w120">
					</s:select>
	                </div>
                </li>
                -->
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.Channel.RoomTypeCode"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_roomTypeCode" name="creteria.roomTypeCode" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.Channel.RoomName"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_roomTypeName" name="creteria.roomTypeName" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
            </ul>
            <hr class="solided notopMargin">
            <div class="center">
	            <button type="submit" class="btn_2 black mgR12"><fmt:message key="common.button.searchSelect"/></button>
	            <button type="button" class="btn_2 white l_reset"><fmt:message key="common.button.Reset"/></button>
            </div>
          </div>
          </s:form>
          
          <!--列表样式-->
          <div class="bt_wp">
	          <display:table name="result.resultList" id="roomTypeList" class="ccm_table1" requestURI="" 
	        	pagesize="20"  size="result.totalCount" partialList="true">roo
		        <display:column property="roomTypeCode"  titleKey="ccm.Channel.RoomTypeCode"/>
		        <display:column property="roomTypeName" titleKey="ccm.Channel.RoomName"/>
		        <display:column property="physicalRooms" titleKey="ccm.RoomTypeList.PhysicalRooms"/>
		        <display:column  titleKey="common.button.Activity">
		            <a href="/roomType_toEdit.do?roomTypevo.roomTypeId=${roomTypeList.roomTypeId }&roomTypevo.roomTypeMId=${roomTypeList.roomTypeMId }" class="link mgR12"><fmt:message key="common.button.edit"/>	</a>
		           <%--  <a href="javascript:;" roomTypeId=${roomTypeList.roomTypeId } roomTypeMId=${roomTypeList.roomTypeMId } class="link del_ifself"><fmt:message key="common.button.delete"/></a> --%>
		        </display:column>
		      </display:table>
          </div>
        </div>
<script>
$(document).ready(function() {
	// 重置
	$('.l_reset').click(function(){
		$('#l_hotelId').val("");
		$('#l_roomTypeCode').val("");
		$('#l_roomTypeName').val("");
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var roomTypeId = $(this).attr('roomTypeId');
		var roomTypeMId = $(this).attr('roomTypeMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href="/roomType_delete.do?roomTypevo.roomTypeId="+roomTypeId+"&roomTypevo.roomTypeMId="+roomTypeMId;
		}
	});
});

function addData(){
	$.ajax({
		type:"POST",
	   	async:false,
	   	url:"roomType_isNotHaveRoomTypeTemplate.do",
	   	success:function(data){
	   		if("false" == data){
	   			alert('<fmt:message key="ccm.error.012"/>');
	   		}else{
	   			location.href = '/roomType_toEdit.do';
	   		}
	   	}
	});
}
</script>
