<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.RoomTypeTemplate.RoomTypeTemplateList"/>
          <div class="bt"> 
          	<a href="/roomTypeTemplate_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/roomTypeTemplate_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.RoomTypeTemplate.RoomTypeTemplateCode"/>:</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_roomTypeTemplateCode" name="creteria.roomTypeTemplateCode" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.RoomTypeTemplate.RoomTypeTemplateName"/>:</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_roomTypeTemplateName" name="creteria.roomTypeTemplateName" cssClass="fxt w120"></s:textfield>
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
	          <display:table name="result.resultList" id="roomTypeTemp" class="ccm_table1" requestURI="" 
	        	pagesize="20"  size="result.totalCount" partialList="true">
		        <display:column property="roomTypeTemplateCode" titleKey="ccm.RoomTypeTemplate.RoomTypeTemplateCode"/>
		        <display:column property="roomTypeTemplateName" titleKey="ccm.RoomTypeTemplate.RoomTypeTemplateName"/>
		        <display:column property="description" titleKey="common.Description"/>
		        <display:column  titleKey="common.button.Activity">
		            <a href="/roomTypeTemplate_toEdit.do?roomTypeTemplateVo.roomTypeTemplateId=${roomTypeTemp.roomTypeTemplateId }&roomTypeTemplateVo.roomTypeTemplateMId=${roomTypeTemp.roomTypeTemplateMId }" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" roomTypeTemplateId=${roomTypeTemp.roomTypeTemplateId } roomTypeTemplateMId=${roomTypeTemp.roomTypeTemplateMId} class="link del_ifself"><fmt:message key="common.button.delete"/></a>
		        </display:column>
		      </display:table>
          </div>
        </div>
<script>
$(document).ready(function() {
	// 重置
	$('.l_reset').click(function(){
		
		$('#l_roomTypeTemplateCode').val("");
		$('#l_roomTypeTemplateName').val("");
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var roomTypeTemplateId = $(this).attr('roomTypeTemplateId');
		var roomTypeTemplateMId = $(this).attr('roomTypeTemplateMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href="/roomTypeTemplate_delete.do?roomTypeTemplateVo.roomTypeTemplateId="+roomTypeTemplateId+"&roomTypeTemplateVo.roomTypeTemplateMId="+roomTypeTemplateMId;
		}
	});
});
</script>
