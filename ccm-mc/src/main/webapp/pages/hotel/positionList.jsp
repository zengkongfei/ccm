<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.LandmarkList"/>
          <div class="bt"> 
          	<a href="/position_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/position_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.LandmarkList.LandmarkName"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_name" name="creteria.name" cssClass="fxt w120"></s:textfield>
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
	          <display:table name="result.resultList" id="positionList" class="ccm_table1" requestURI="" 
	        	pagesize="20" size="result.totalCount" partialList="true">
		        <display:column property="name" titleKey="ccm.LandmarkList.LandmarkName"/>
		        <display:column property="longitude"  titleKey="ccm.LandmarkList.Longitude"/>
		        <display:column property="latitude"  titleKey="ccm.LandmarkList.Latitude"/>
		        <display:column property="description"  titleKey="common.Description"/>
		        <display:column   titleKey="ccm.UserActivityLog.Action">
		            <a href="/position_toEdit.do?positionvo.positionId=${positionList.positionId }&positionvo.relativePositionMId=${positionList.relativePositionMId }" class="link mgR12"><fmt:message key="common.button.edit"/>	</a>
		            <a href="javascript:;" positionId=${positionList.positionId } relativePositionMId=${positionList.relativePositionMId } class="link del_ifself"><fmt:message key="common.button.delete"/></a>
		        </display:column>
		      </display:table>
          </div>
        </div>
<script>
$(document).ready(function() {
	// 重置
	$('.l_reset').click(function(){
		$('#l_name').val("");
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var positionId = $(this).attr('positionId');
		var relativePositionMId = $(this).attr('relativePositionMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href="/position_delete.do?positionvo.positionId="+positionId+"&positionvo.relativePositionMId="+relativePositionMId;
		}
	});
});
</script>
