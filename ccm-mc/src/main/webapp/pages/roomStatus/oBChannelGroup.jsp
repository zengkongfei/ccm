<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">

function OverbookingGroup() {
	var overbookingGroup = new Object();
	overbookingGroup.groupCode;// 分组code
	overbookingGroup.percent;	//该组百分比
	overbookingGroup.channelHotelList;	//渠道id
	return overbookingGroup;
}
function ChannelHotel(){
	var channelHotel = new Object();
	channelHotel.channelId;
	channelHotel.channelCode;
	return channelHotel;
}
function addChannel(groupCode){
	$('#unGroup input:checked').each(function(){ 
		var channelCode = $(this).val();
		var channel = $("#label_"+channelCode);
		$(this).trigger("click");
		channel.hide('fast').queue(function(next){
			$("#groupBox_"+groupCode).append(channel);
		        next();
		    }).show('fast');
	});
}
function removeChannel(groupCode){
	$('#groupBox_'+groupCode+' input:checked').each(function(){ 
		var channelCode = $(this).val();
		var channel = $("#label_"+channelCode);
		
		channel.hide('slow').queue(function(next){
			$("#unGroup").append(channel);
		        next();
		    }).show('slow');
	});
}
function saveChannelGroup(){
	var channelGroupArr = new Array();
	var obclass = $('.ob_wp');
	$('.ob_wp').each(function(){
		var groupCode = $(this).attr("id");
		var percent = $("#percent_"+groupCode).val();
		var groupId = $("#groupId_"+groupCode).val();
		var obGroup = OverbookingGroup();
		obGroup.groupCode=groupCode;
		obGroup.percent=percent;
		obGroup.groupId=groupId;
		obGroup.channelHotelList=new Array();
		$("#groupBox_"+groupCode+" input[type='checkbox']").each(function(){ 
			var channelHotel = ChannelHotel();
			channelHotel.channelId = $(this).attr("id");
			channelHotel.channelCode = $(this).val();
			obGroup.channelHotelList.push(channelHotel);
		});
		channelGroupArr.push(obGroup);
	});
	var channelGroupStr = JSON.stringify(channelGroupArr);	
	var isPush = $("#obPush").is(':checked');
	
	$.ajax({
		  type: "post",   
		  url: "roomStatus_ajaxSaveChannelGroup.do",   
		  data: "channelGroupStr="+channelGroupStr+"&push="+isPush,   
		  datatype: "json",
		  success:function(data){
// 		  	alert('操作成功！');
		  }
	});
	$.magnificPopup.close();
}
</script>