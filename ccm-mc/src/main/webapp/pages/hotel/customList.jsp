<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="title_wp">
	<fmt:message key="ccm.ProfileList"/>
	<div class="bt">
		<a href="/custom_edit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a>
	</div>
</div>
<div class="c_whitebg">

	<!--搜索项-->
	<s:form action="/custom_list.do" method="post">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ProfileList.ProfileName"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield name="creteria.name" cssClass="fxt w120"></s:textfield>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ProfileList.ProfileType"/>：</span>
					</div>
					<div class="i_input">
						<s:select list="#request.profileType"  key="creteria.type" cssClass="fxt w160 required"></s:select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.BookingDepositReport.AccessCode"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="l_hotelName" name="creteria.accessCode" cssClass="fxt w120"></s:textfield>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text">Corp/IATA No：</span>
					</div>
					<div class="i_input">
						<s:textfield id="l_hotelCode" name="creteria.corpIATANo" cssClass="fxt w120"></s:textfield>
					</div>
				</li>
				
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button type="submit" class="btn_2 black mgR12"><fmt:message key="common.button.searchSelect"/></button>
				<button type="button" class="btn_2 white l_reset" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
			</div>
		</div>
	</s:form>

	<!--列表样式-->
	<div class="bt_wp">
		<display:table name="result.resultList" id="hotelList" class="ccm_table1" requestURI="" pagesize="20" size="result.totalCount" partialList="true">
			<display:column property="name"  titleKey="ccm.ProfileList.ProfileName" style="width: 400px;word-break: break-all;word-wrap: break-word;"/>
			<display:column property="type"  titleKey="ccm.ProfileList.ProfileType" />
			<display:column property="corpIATANo" title="Corp/IATA No" />
			<display:column property="accessCode" titleKey="ccm.BookingDepositReport.AccessCode" />
			<display:column titleKey="ccm.UserActivityLog.Action">
				<a href="/custom_edit.do?customId=${hotelList.customId}" class="link mgR12"><fmt:message key="common.button.edit"/>	</a>
				<a href="javascript:;" onclick="delRecord('${hotelList.customId}')" class="link del_ifself"><fmt:message key="common.button.delete"/></a>
			</display:column>
		</display:table>
	</div>
</div>
<script>
	$(document).ready(function() {

		// 重置
		$('.l_reset').click(function() {
			$('#l_chainId').val("");
			$('#l_brandId').val("");
			$('#l_hotelCode').val("");
			$('#l_hotelName').val("");
		});

	});
	
	/*是否删除本条*/
	function delRecord(customId){
		var r = confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r == true) {
			location.href =setHotelIdForHref("/custom_delete.do?customId=" + customId);
		}
	}

</script>
