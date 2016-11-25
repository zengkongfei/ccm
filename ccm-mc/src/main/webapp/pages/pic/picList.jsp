<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<span id="picShow">
	<div class="div_upload_container">
		<span id="Span_Imgcon_Close" class="EIB div_imgcon_close"></span>
		<s:iterator value="pictureList">
			<div class="div_img" picId="<s:property value="picId"/>">
				<img src="<s:property value="pictureUrlContext"/><s:property value="url"/>" />
			</div>
		</s:iterator>
		<div class="clearboth"></div>
	</div> 
</span>
<script type="text/javascript">
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
	
</script>