<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="footer rel" id="footerDiv">
	<div class="w1200">
		<div class="cprts">
			<div>
			Copyright © 2016 Shiji Information Technology All rights reserved.<span class="mgL12"><fmt:message key="footer.ICPNo.09032152"/></span><span class="mgL12"><fmt:message key="footer.TechnicalSupportHotline"/>：4000 211 988×6×1</span>
			</div>
		    <div class="float-right rtlocation">
		    	<a class="lk" target="_blank" href="http://www.chinaonline.net.cn/cms/cn"><fmt:message key="footer.Chinaonline"/></a>
		        <a class="lk" target="_blank" href="http://www.chinaonline.net.cn/cms/cn/Contactus"><fmt:message key="footer.ContactUs"/></a>
		        <a class="lk" target="_blank" href="/privacyStatement.jsp"><fmt:message key="footer.PrivacyStatement"/></a>
		        <span class="mgL6">
					<a class="lag en current" title="Change to English" href="javascript:;" onclick="i18n('en_US');">Change to English</a>
			        <a class="lag cn current" title="切换到中文" href="javascript:;" onclick="i18n('zh_CN');">切换到中文</a>
				</span>
		    </div>
		</div>
	</div>
</div>
<s:if test='#attr.SPRING_SECURITY_CONTEXT.authentication.principal.companyId!="1" && (#attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.orderRemind || #attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.messRemind)'>
<!--弹出框-->
<div class="c_rel w1200" style="z-index: 999;">
	<div id="CCM_PopUpBox">
		<div class="w1200">
			<div class="ft_layer colorbg">
				<div class="mgA24">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<a href="/order_list.do?menuId=71&from=new">您有新的订单<span class="new">15</span>张，</a><a href="/order_list.do?menuId=71&from=modify">您有修改订单<span class="modify">5</span>张，</a><a href="/order_list.do?menuId=71&from=cancel">您有取消订单<span class="cancel">3</span>张，</a><a href="/feedBack_list.do?tmenuId=801&menuId=80&from=feedb">您有留言<span class="feedb">6</span>条。</a>
				</div>
			</div>
		</div>
	</div>
</div>

<!--音频-->
<div style="display: none;">
	<audio id="Paly1" preload="auto"> 
	<!--  <source src="http://www.w3school.com.cn/i/song.ogg" type="audio/ogg">--> 
	<source src="<c:url value='/js/audiojs/new.mp3'/>${global_js_revision}" type="audio/mpeg"> Your browser does not support the audio element. 
	</audio>
	<audio id="Paly2" preload="auto"> 
	<!--  <source src="http://www.w3school.com.cn/i/song.ogg" type="audio/ogg">--> 
	<source src="<c:url value='/js/audiojs/modify.mp3'/>${global_js_revision}" type="audio/mpeg"> Your browser does not support the audio element. 
	</audio>
	<audio id="Paly3" preload="auto"> 
	<!--  <source src="http://www.w3school.com.cn/i/song.ogg" type="audio/ogg">--> 
	<source src="<c:url value='/js/audiojs/cancel.mp3'/>${global_js_revision}" type="audio/mpeg"> Your browser does not support the audio element. 
	</audio>
	<audio id="Paly4" preload="auto"> 
	<!--  <source src="http://www.w3school.com.cn/i/song.ogg" type="audio/ogg">--> 
	<source src="<c:url value='/js/audiojs/leaveWord.mp3'/>${global_js_revision}" type="audio/mpeg"> Your browser does not support the audio element. 
	</audio>
</div>
<script src="<c:url value='/js/audiojs/audio.min.js'/>${global_js_revision}"></script>
<script>
	audiojs.events.ready(function() {
		var as = audiojs.createAll();
	});
</script>
<script>
	$(document).ready(function() {

		$('.ctr_room_status td.td_ia').mouseenter(function() {
			$p_left = $(this).position().left + 23;
			$p_top = $(this).position().top + 18;
			$compareW = $(window).width() / 2 + 355;
			if ($p_left > $compareW) {
				$('.room_status').css({
					left : $p_left - 258,
					top : $p_top
				});
				$('.room_status .off,.room_status .open').addClass('Right');
			} else {
				$('.room_status').css({
					left : $p_left,
					top : $p_top
				});
				$('.room_status .off,.room_status .open').removeClass('Right');
			}
			if ($(this).hasClass('off_room')) {
				$('.room_off').slideDown();
			} else {
				$('.room_open').stop(true, true).slideDown();
			}

		});
		$('.ctr_room_status td.td_ia').mouseleave(function() {
			$('.room_status').hide();
		});
		$('.ctr_room_status td.td_ia').bind('dblclick', function() {
			var bl_off = $(this).hasClass('off_room');
			if (bl_off) {
				$(this).removeClass('off_room');
				$(this).html('');
				$('.room_off').hide()
				$('.room_open').slideDown();

			} else {
				$(this).addClass('off_room');
				$(this).html('X');
				$('.room_open').hide();
				$('.room_off').slideDown();
			}

		});
		$('.ctr_room_status').mouseout(function() {
			$('.room_status').hide();
		})

		RadioCheckedName('ccm_rdption1');

		$('#One_click').bind('click', function() {
			$('#One_show').slideDown();
		});
		$('#Two_click').bind('click', function() {
			$('#Two_show').slideDown();
		});

		$('#One_show .closethis').bind('click', function() {
			$('#One_show').hide();
		});
		$('#Two_show .closethis').bind('click', function() {
			$('#Two_show').hide();
		});

		//$( ".ccm_table1" ).tooltip( "destroy" );

		remind();
		setInterval("remind()",120000);

		$('#CCM_PopUpBox .close').click(function(e) {
			$.ajax({
				url : '/remind_haveSeen.do',
				beforeSend : function() {

				},
				cache : false,
				dataType : "text",
				type : 'POST',
				complete : function(x, t) {
					if (t == 'error') {
						console.info("submit error");
					}
				},
				success : function(data) {
					data = eval("(" + data + ")");
					if (data.success == false) {
						console.info("fail");
					} else if (data.success == true) {
						e.stopPropagation();
						$('#CCM_PopUpBox').hide();
					} else {
						console.info("exception");
					}
				}
			});
		});

	});
	
	function remind(){
		/*弹出框*/
		$.ajax({
			url : '/remind_search.do',
			beforeSend : function() {

			},
			cache : false,
			dataType : "text",
			type : 'POST',
			complete : function(x, t) {
				if (t == 'error') {
					console.info("submit error");
				}
			},
			success : function(data) {
				data = eval("(" + data + ")");
				if (data.success == false) {
					console.info("fail");
				} else if (data.success == true) {
					$('#CCM_PopUpBox').effect("pulsate", "slow");
					if(data.count1 >0){
						var html = '<span class="new">'+data.count1+'</span>';
						var arry = new Array();
						arry.push(html);
						var str = '<fmt:message key="order.message.001"/>';
						$('span.new').parent().html(i18n_replace(str,arry));
					}else{
						$('span.new').parent().hide();
					}
					if(data.count2 >0){
						var html = '<span class="new">'+data.count2+'</span>';
						var arry = new Array();
						arry.push(html);
						var str = '<fmt:message key="order.message.002"/>';
						$('span.modify').parent().html(i18n_replace(str,arry));
					}else{
						$('span.modify').parent().hide();
					}
					if(data.count3 >0){
						var html = '<span class="new">'+data.count3+'</span>';
						var arry = new Array();
						arry.push(html);
						var str = '<fmt:message key="order.message.003"/>';
						$('span.cancel').parent().html(i18n_replace(str,arry));
					}else{
						$('span.cancel').parent().hide();
					}
					if(data.count4 >0){
						var html = '<span class="new">'+data.count4+'</span>';
						var arry = new Array();
						arry.push(html);
						var str = '<fmt:message key="order.message.004"/>';
						$('span.feedb').parent().html(i18n_replace(str,arry));
					}else{
						$('span.feedb').parent().hide();
					}
					if(data.count1 ==0 && data.count2 ==0 && data.count3 ==0 && data.count4 ==0){
						$('#CCM_PopUpBox').hide();
					}
					if(data.newSound >0){
						var Media = document.getElementById("Paly1");
						Media.play();
						$.sleep(2, function () {
							modifyCancelFeedb(data);
						});
					}else {
						modifyCancelFeedb(data);
					}
				} else {
					console.info("exception");
				}
			}
		});
	}
	
	function modifyCancelFeedb(data){
		if(data.modifySound >0){
			var Media = document.getElementById("Paly2");
			Media.play();
			$.sleep(2, function () {
				cancelFeedb(data);
			});
		}else{
			cancelFeedb(data);
		}
	}
	
	function cancelFeedb(data){
		if(data.cancelSound >0){
			var Media = document.getElementById("Paly3");
			Media.play();
			$.sleep(2, function () {
				if(data.feedbSound >0){
					var Media = document.getElementById("Paly4");
					Media.play();
				}
			});
		}else{
			if(data.feedbSound >0){
				var Media = document.getElementById("Paly4");
				Media.play();
			}
		}
	}
</script>
</s:if>

<script>

//获取浏览器语言环境 
function i18n(languega){
    var url = window.location.href;
    if(url.charAt(url.length-1)=="#"){
    	url = url.substring(0,url.length-1);
    }
    if(url.indexOf("?") > 0 ){
    	if(url.indexOf("&locale") > 0){
	    	window.location.href=url.split("&locale")[0]+"&locale="+languega+"&"+Math.random();
    	}else if(url.indexOf("?locale") > 0){
    		window.location.href=url.split("?locale")[0]+"?locale="+languega+"&"+Math.random();
    	}else{
    		window.location.href=url+"&locale="+languega+"&"+Math.random();
    	}
    }else{
	    window.location.href=url.split("?")[0]+"?locale="+languega+"&"+Math.random();
    }
}
</script>

<script>
	//动态添加 hotelIdFormHidden 
	$(document).ready(function() {
		
		/*重写jQuery的ajax */
		(function($){	
		    // 备份jquery的ajax方法 
		    var _ajax=$.ajax;  
		    // 重写jquery的ajax方法 
		    $.ajax=function(opt){  
		        // 备份opt中error和success方法 
		        var fn = {  
		            error:function(XMLHttpRequest, textStatus, errorThrown){},  
		            success:function(data, textStatus){}
		        }  
		        if(opt.error){  
		            fn.error=opt.error;  
		        }  
		        if(opt.success){  
		            fn.success=opt.success;  
		        }
		        
		        if(typeof(opt.data)=='object'){
		        	 opt.data.hotelIdFormHidden="${attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelId}";
		        }else {
		        	 opt.data = opt.data+"&hotelIdFormHidden=${attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelId}";
		        }
		          
		        // 扩展增强处理 
		        var _opt = $.extend(opt,{  
	 	        	//data: typeof(opt.data)=='object' ? opt.data.test="testhotelid" : opt.data+"&test=testhotelid",
		        	data: opt.data,
		            error:function(XMLHttpRequest, textStatus, errorThrown){  
		                // 错误方法增强处理     
		                fn.error(XMLHttpRequest, textStatus, errorThrown);  
		            },  
		            success:function(data, textStatus){  
		                // 成功回调方法增强处理    
		                fn.success(data, textStatus);   
		            }
		        });  
		       return _ajax(_opt);  
		    };  
		})(jQuery); 
		
		//在from中添加hotelIdFormHidden 
		$("form").append(function(n){
			var hotelIdFromSession = '${attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelId}';
			return "<input type='hidden' name='hotelIdFormHidden' value='"+hotelIdFromSession+"'/>";
		});
		
		var hotelIdFromSession = '${attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelId}';
		//为所有超链接添加 hotelIdFormHidden 
		setHotelIdForA(hotelIdFromSession);
		
		//setHotelIdForHref(window.location.href,hotelIdFromSession) 
		
	});
	//获取session内的hotelId添加到所有form表单内 type='hidden'
	
	//为所有超链接添加 hotelIdFormHidden 
	function setHotelIdForA(hotelIdFromSession){
		
		var aList = $("a");
		for(var i=0;i<aList.length;i++){
			var a = aList.eq(i);
			var href = $(a).attr("href");
			if(href=="#" || href == '' || href.indexOf("javascript:")>=0 ){
				//不做操作 
			}else{
				if(href.indexOf(".do")>0){
					if(href.indexOf("?")>0){
						
						href = href + "&hotelIdFormHidden="+hotelIdFromSession;
					}else{
						href = href + "?hotelIdFormHidden="+hotelIdFromSession;
					}
					$(a).attr("href",href);
				}
			}
		}
	}
	
	//window.location.href 
	function setHotelIdForHref(href){
		var hotelIdFromSession = '${attr.SPRING_SECURITY_CONTEXT.authentication.principal.hotelvo.hotelId}';
		if(href.indexOf(".do")>0){
			if(href.indexOf("?")>0){
				href = href + "&hotelIdFormHidden="+hotelIdFromSession;
			}else{
				href = href + "?hotelIdFormHidden="+hotelIdFromSession;
			}
		}
		return href;
	}
	
	
</script>
