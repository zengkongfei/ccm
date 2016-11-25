<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<div class="title_wp">
	<fmt:message key="ccm.hotelSwitch.CCMControlButton" />
</div>
<div class="c_whitebg">
	<!--展示编辑项-->
	<s:form id="hotelSwitchForm" action="/hotelSwitch_save.do" method="post">
		<appfuse:ccmToken name="token"></appfuse:ccmToken>
		<div class="nm_box">
			<ul class="inq_wp frow">
				<!-- HardCancel -->
				<hr class="dashed">
				<li>
					<div class="i_title" style="width: 200px;">
						<span class="text">
							<fmt:message key="ccm.hotelSwitch.HardCancel" />：
						</span>
					</div>
					<div class="i_input">
						<s:if test="hotelSwitch.isHardCancel">
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isHardCancel" id="hoptionsRadios1" value="true" checked="checked" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isHardCancel" id="hoptionsRadios2" value="false" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:if>
						<s:else>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isHardCancel" id="hoptionsRadios1" value="true" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isHardCancel" id="hoptionsRadios2" value="false" checked="checked" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:else>
					</div>
				</li>
				<!-- Generates -->
				<hr class="dashed">
				<li>
					<div class="i_title" style="width: 200px;">
						<span class="text">
							<fmt:message key="ccm.hotelSwitch.Generates" />：
						</span>
					</div>
					<div class="i_input">
						<s:if test="hotelSwitch.isGenerates">
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isGenerates" id="goptionsRadios1" value="true" checked="checked" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isGenerates" id="goptionsRadios2" value="false" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:if>
						<s:else>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isGenerates" id="goptionsRadios1" value="true" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isGenerates" id="goptionsRadios2" value="false" checked="checked" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:else>
					</div>
				</li>
				<!-- UploadRateHeader -->
				<hr class="dashed">
				<li>
					<div class="i_title" style="width: 200px;">
						<span class="text">
							<fmt:message key="ccm.hotelSwitch.UploadRateHeader" />：
						</span>
					</div>
					<div class="i_input">
						<s:if test="hotelSwitch.isUploadRateHeader">
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isUploadRateHeader" id="uoptionsRadios1" value="true" checked="checked" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isUploadRateHeader" id="uoptionsRadios2" value="false" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:if>
						<s:else>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isUploadRateHeader" id="uoptionsRadios1" value="true" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isUploadRateHeader" id="uoptionsRadios2" value="false" checked="checked" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:else>
					</div>
				</li>
				<!-- isAcceptRavr -->
				<hr class="dashed">
				<li>
					<div class="i_title" style="width: 200px;">
						<span class="text">
							<fmt:message key="ccm.hotelSwitch.isAcceptRavr" />：
						</span>
					</div>
					<div class="i_input">
						<s:if test="hotelSwitch.isAcceptRavr">
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isAcceptRavr" id="hoptionsRadios1" value="true" checked="checked" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isAcceptRavr" id="hoptionsRadios2" value="false" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:if>
						<s:else>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isAcceptRavr" id="hoptionsRadios1" value="true" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isAcceptRavr" id="hoptionsRadios2" value="false" checked="checked" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:else>
					</div>
				</li>
				<!-- isAcceptRavl -->
				<hr class="dashed">
				<li>
					<div class="i_title" style="width: 200px;">
						<span class="text">
							<fmt:message key="ccm.hotelSwitch.isAcceptRavl" />：
						</span>
					</div>
					<div class="i_input">
						<s:if test="hotelSwitch.isAcceptRavl">
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isAcceptRavl" id="hoptionsRadios1" value="true" checked="checked" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isAcceptRavl" id="hoptionsRadios2" value="false" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:if>
						<s:else>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isAcceptRavl" id="hoptionsRadios1" value="true" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isAcceptRavl" id="hoptionsRadios2" value="false" checked="checked" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:else>
					</div>
				</li>
				
				<!-- isMask -->
				<hr class="dashed">
				<li>
					<div class="i_title" style="width: 200px;">
						<span class="text">
							<fmt:message key="ccm.hotelSwitch.isMask" />：
						</span>
					</div>
					<div class="i_input">
						<s:if test="hotelSwitch.isMask">
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isMask"  value="true" checked="checked" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isMask"  value="false" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:if>
						<s:else>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isMask"  value="true" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isMask"  value="false" checked="checked" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:else>
					</div>
				</li>
				
				<!-- isMonitorADSPending -->
				<hr class="dashed">
				<li>
					<div class="i_title" style="width: 200px;">
						<span class="text">
							<fmt:message key="ccm.hotelSwitch.isMonitorADSPending" />：
						</span>
					</div>
					<div class="i_input">
						<s:if test="hotelSwitch.isMonitorADSPending">
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isMonitorADSPending"  value="true" checked="checked" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isMonitorADSPending"  value="false" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:if>
						<s:else>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isMonitorADSPending"  value="true" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isMonitorADSPending"  value="false" checked="checked" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:else>
					</div>
				</li>
				
				<hr class="dashed">
				<li>
					<div class="i_title" style="width: 200px;">
						<span class="text">
							<fmt:message key="ccm.hotelSwitch.isDISCOUNTRATE" /> 
						</span>
					</div>
					<div class="i_input">
						<s:if test="hotelSwitch.isDiscount">
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isDiscount"  value="true" checked="checked" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isDiscount"  value="false" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:if>
						<s:else>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isDiscount"  value="true" class="chRadio"> 
								<span class="">
									<fmt:message key="common.Yes" /> 
								</span> 
							</label>
							<label class="radio inline"> 
								<input type="radio" name="hotelSwitch.isDiscount"  value="false" checked="checked" class="chRadio"> 
								<span class=""> 
									<fmt:message key="common.Not" /> 
								</span> 
							</label>
						</s:else>
					</div>
				</li>
				
			</ul>
			<hr class="dashed">
			<div class="listinputCtrl">
				<button type="button" class="btn_1 green mgR12 f_save">
					<fmt:message key="ccm.hotelSwitch.SaveModify" />
				</button>
				<a class="btn_1 white" href="/hotelSwitch_list.do"><fmt:message key="ccm.hotelSwitch.Refresh"/></a>
			</div>
		</div>
	</s:form>
</div>

<script>
	$(document).ready(function() {

	});
	//保存
	$('.f_save').click(function() {
		var isHardCancel = $("input[name='hotelSwitch.isHardCancel']:checked").val(); 
		//保存时跳出确认框
		con=confirm("<fmt:message key="ccm.hotelSwitch.Confirm"/>?");
		if(!con){
			return;
		}
		//验证表单
		if (!$("#hotelSwitchForm").valid()) {
			return;
		} else {
			$("#hotelSwitchForm").submit();
			//禁止重复提交
			$('.f_save').addClass('no_ald');
			$('.f_save').attr("disabled","disabled");	
		}
		
	});

</script>
