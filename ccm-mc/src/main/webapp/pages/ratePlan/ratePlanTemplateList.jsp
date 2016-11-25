<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.RatePlanTemplate.RatePlanTemplateList"/>
          <div class="bt"> 
          	<a href="/ratePlanTemplate_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/ratePlanTemplate_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.RatePlanTemplate.RatePlanTemplateCode"/>:</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_ratePlanTemplateCode" name="creteria.ratePlanTemplateCode" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.RatePlanTemplate.RatePlanTemplateName"/>:</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_ratePlanTemplateName" name="creteria.ratePlanTemplateName" cssClass="fxt w120"></s:textfield>
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
	          <display:table name="result.resultList" id="ratePlanTemp" class="ccm_table1" requestURI="" 
	        	pagesize="20"  size="result.totalCount" partialList="true">
		        <display:column property="ratePlanTemplateCode" titleKey="ccm.RatePlanTemplate.RatePlanTemplateCode"/>
		        <display:column property="ratePlanTemplateName" titleKey="ccm.RatePlanTemplate.RatePlanTemplateName"/>
		        <display:column property="description" titleKey="common.Introduction"/>
		        <display:column  titleKey="common.button.Activity">
		            <a href="/ratePlanTemplate_toEdit.do?ratePlanTemplateVo.ratePlanTemplateId=${ratePlanTemp.ratePlanTemplateId }&ratePlanTemplateVo.ratePlanTemplateMId=${ratePlanTemp.ratePlanTemplateMId }" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" ratePlanTemplateId=${ratePlanTemp.ratePlanTemplateId } ratePlanTemplateMId=${ratePlanTemp.ratePlanTemplateMId} class="link del_ifself"><fmt:message key="common.button.delete"/></a>
		        </display:column>
		      </display:table>
          </div>
        </div>
<script>
$(document).ready(function() {
	// 重置
	$('.l_reset').click(function(){
		
		$('#l_ratePlanTemplateCode').val("");
		$('#l_ratePlanTemplateName').val("");
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var ratePlanTemplateId = $(this).attr('ratePlanTemplateId');
		var ratePlanTemplateMId = $(this).attr('ratePlanTemplateMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>	？");
		if (r==true){
			window.location.href="/ratePlanTemplate_delete.do?ratePlanTemplateVo.ratePlanTemplateId="+ratePlanTemplateId+"&ratePlanTemplateVo.ratePlanTemplateMId="+ratePlanTemplateMId;
		}
	});
});
</script>
