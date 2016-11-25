<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.BasicConfiguration.ChainList"/>
          <div class="bt"> 
          	<a href="/chain_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/chain_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.BasicConfiguration.ChainCode"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_chainCode" name="creteria.chainCode" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.BasicConfiguration.ChainName"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_chainName" name="creteria.chainName" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
            </ul>
            <hr class="solided notopMargin">
            <div class="center">
	            <button type="submit" class="btn_2 black mgR12"><fmt:message key="common.button.Reset"/></button>
	            <button type="button" class="btn_2 white l_reset"><fmt:message key="common.button.searchSelect"/></button>
            </div>
          </div>
          </s:form>
          
          <!--列表样式-->
          <div class="bt_wp">
	          <display:table name="result.resultList" id="chainList" class="ccm_table1" requestURI="" 
	        	pagesize="20"  size="result.totalCount" partialList="true">
		        <display:column property="chainCode"  titleKey="ccm.BasicConfiguration.ChainCode"/>
		        <display:column property="chainName"  titleKey="ccm.BasicConfiguration.ChainName"/>
		        <display:column property="description"  titleKey="ccm.BasicConfiguration.ChainIntroduction"/>
		        <display:column  titleKey="common.button.Activity">
		            <a href="/chain_toEdit.do?chainvo.chainId=${chainList.chainId }&chainvo.chainMId=${chainList.chainMId }" class="link mgR12"><fmt:message key="common.button.edit"/>	</a>
		            <a href="javascript:;" chainId=${chainList.chainId } chainMId=${chainList.chainMId } class="link del_ifself"><fmt:message key="common.button.delete"/></a>
		        </display:column>
		      </display:table>
          </div>
        </div>

<script>
$(document).ready(function() {
	// 重置
	$('.l_reset').click(function(){
		$('#l_chainCode').val("");
		$('#l_chainName').val("");
	});
	
	/*是否删除本条 setHotelIdForHref */
	$('.del_ifself').bind('click',function(){
		var chainId = $(this).attr('chainId');
		var chainMId = $(this).attr('chainMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			//window.location.href="/chain_delete.do?chainvo.chainId="+chainId+"&chainvo.chainMId="+chainMId;
			window.location.href=setHotelIdForHref("/chain_delete.do?chainvo.chainId="+chainId+"&chainvo.chainMId="+chainMId);
		}
	});
});
</script>
