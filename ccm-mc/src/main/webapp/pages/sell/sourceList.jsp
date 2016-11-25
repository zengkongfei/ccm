<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

        <div class="title_wp"><fmt:message key="ccm.Source.SourceList"/>
          <div class="bt"> 
          	<a href="/source_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/source_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.Source.SourceList"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_sourceCode" name="creteria.sourceCode" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="common.ValidTime"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_effectiveDate" cssClass="fxt w120" name="creteria.effectiveDate" ></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="common.InvalidTime"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_expireDate" cssClass="fxt w120" name="creteria.expireDate" ></s:textfield>
	                </div>
                </li>
            </ul>
            <hr class="solided notopMargin">
            <div class="center">
	            <button type="submit" class="btn_2 black mgR12" onclick="javascript:return query();"><fmt:message key="common.button.searchSelect"/></button>
	            <button type="button" class="btn_2 white l_reset"><fmt:message key="common.button.Reset"/></button>
            </div>
          </div>
          </s:form>
          
          <!--列表样式-->
          <div class="bt_wp">
	          <display:table name="result.resultList" id="sourceList" class="ccm_table1" requestURI="" 
	        	pagesize="20"  size="result.totalCount" partialList="true">
		        <display:column property="sourceCode"  titleKey="ccm.Source.SourceCode"/>
		        <display:column titleKey="common.ValidTime">
		        	<fmt:formatDate type="date" value="${sourceList.effectiveDate}" pattern="yyyy-MM-dd"/>
		        </display:column>
		        <display:column titleKey="common.InvalidTime">
		        	<fmt:formatDate type="date" value="${sourceList.expireDate}" pattern="yyyy-MM-dd"/>
		        </display:column>
		        <display:column property="description" titleKey="common.Description"/>
		        <display:column   titleKey="common.button.Activity">
		            <a href="/source_toEdit.do?sourcevo.sourceId=${sourceList.sourceId }&sourcevo.sourceMId=${sourceList.sourceMId }" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" sourceId=${sourceList.sourceId } sourceMId=${sourceList.sourceMId } class="link del_ifself"><fmt:message key="common.button.delete"/></a>
		        </display:column>
		      </display:table>
          </div>
        </div>

<script>
$(document).ready(function() {
	//日期显示
	var dpconfig = {
		showMonthAfterYear : true,
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
	//开始/结束时间
	$("#l_effectiveDate").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			$("#l_expireDate").datepicker("option", "minDate", v);
		}
	}));
	$("#l_expireDate").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			$("#l_effectiveDate").datepicker("option", "maxDate", v);
		}
	}));
	
	// 重置
	$('.l_reset').click(function(){
		$('#l_sourceCode').val("");
		$('#l_effectiveDate').val("");
		$('#l_expireDate').val("");
	});
	
	/*是否删除本条*/
	$('.del_ifself').bind('click',function(){
		var sourceId = $(this).attr('sourceId');
		var sourceMId = $(this).attr('sourceMId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href="/source_delete.do?sourcevo.sourceId="+sourceId+"&sourcevo.sourceMId="+sourceMId;
		}
	});
});

//查询
function query(){
	
	//生效时间
	var effectiveDate = $('#l_effectiveDate').val();
	if(!strIsNull(effectiveDate)){
		var effectiveDateCode = validateYYYYMMDD(effectiveDate);
		if(effectiveDateCode!='success'){
			alert(getErrorMsg(effectiveDateCode,'<fmt:message key="common.ValidTime"/>','yyyy-MM-DD'));
			return false;
		}
	}
	
	//失效时间
	var expireDate = $('#l_expireDate').val();
	if(!strIsNull(expireDate)){
		var expireDateCode = validateYYYYMMDD(expireDate);
		if(expireDateCode!='success'){
			alert(getErrorMsg(expireDateCode,'<fmt:message key="common.InvalidTime"/>','yyyy-MM-DD'));
			return false;
		}
	}
	return true;
}

</script>
