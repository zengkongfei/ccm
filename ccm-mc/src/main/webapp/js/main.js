/*!
    jQuery scrollTopTop v1.0 - 2013-03-15
    (c) 2013 Yang Zhao - geniuscarrier.com
    license: http://www.opensource.org/licenses/mit-license.php
*/
(function($) {
    $.fn.scrollToTop = function(options) {
        var config = {
            "speed" : 800
        };

        if (options) {
            $.extend(config, {
                "speed" : options
            });
        }

        return this.each(function() {

            var $this = $(this);

            $(window).scroll(function() {
                if ($(this).scrollTop() > 100) {
                    $this.fadeIn();
                } else {
                    $this.fadeOut();
                }
            });

            $this.click(function(e) {
                e.preventDefault();
                $("body, html").animate({
                    scrollTop : 0
                }, config.speed);
            });

        });
    };
})(jQuery);

;(function($){
	$(document).ready(function() {
		/*头部的js*/
		$('ul.hd_link li').bind('mouseenter',function(e){
		if($(this).hasClass('common')){
			e.stopPropagation();
			/*$('.commonFuction').show();*/
			/*$('.commonFuction').slideDown('fast');*/
			$('.commonFuction').effect( "slide", 200 );
			}
		else{
			$('.commonFuction').hide();	
			}
		});
	$('.commonFuction').bind('mouseleave',function(){
			$(this).hide()
		});
	$('.header').bind('mouseleave',function(){
			$('.commonFuction').hide()
		});	
		
	
	
		
	$('#searchHotel').bind('click',function(e){
		e.stopPropagation();
		$('.instantHotelList').show();
		});	
	
	
	
	$('.account').bind('click',function(e){
		e.stopPropagation();
		$('.accoutDetail').slideDown();
		});
	$(document).bind('click',function(){
		$('.accoutDetail').hide();
		});
	
	/*控制底部footer的代码*/
	function footerPositonCtr(){
		if($(window).height()>$(document.body).height()){
			$('.footer').addClass('rel');
		}
		else{
			$('.footer').removeClass('rel');
			}
	}
	footerPositonCtr();
	$(window).resize(function(){
		footerPositonCtr();	
		})
		
		/*弹出层*/
	bind_ccm_popup();
	$('.popup-close').bind('click',function(){
		$.magnificPopup.close();
		});
	/*自定义 切换*/
	$('#MonthCustomSwitch').bind('change',function(){
		var this_checked=$(this).is(':checked');
			if(this_checked){$('#MonthCustom').show();}
			else{$('#MonthCustom').hide();}
		
		})
	/*价格处理*/	
	bindSpanPrice();
		
		/*提示*/
		$('.ccm_table1').tooltip({
			track: true
		});
		
		/*显示滚动条*/
		var num_list=$('.lt_menu2 ul.mlist li').length;
		if(num_list>10){$('.lt_menu2 ul.mlist').addClass('overFlowY');}
		else{$('.lt_menu2 ul.mlist').removeClass('overFlowY');}
		
		/*回到顶部*/
		$("#toTop").scrollToTop(600);
		
		/*复选框控制*/
		$('input:checked').next().addClass('checked');
		$(':checkbox').change(function(){
			var this_checked=$(this).is(':checked');
			var hl_span=$(this).next();
			if(this_checked){hl_span.addClass('checked');}
			else{hl_span.removeClass('checked');}
			});
			
		$('.select_all').click(function(){
				$find=$(this).parents('.fm_bwp').find('input[type=checkbox]');
				$find.prop('checked','true')
				$find.next().addClass('checked');
			});
		$('.select_inverse').click(function(){
				$find=$(this).parents('.fm_bwp').find('label.checkbox');
				$find.each(function(){
					$c_input=$(this).children('input');
					$c_span=$(this).children('span');
					if($c_input.is(':checked')){
						$c_input.prop('checked','')
						$c_span.removeClass('checked');}
					else{	
						$c_input.prop('checked','true')
						$c_span.addClass('checked');}
					});
				
				
			});
		
		/*ft_layer*/	
		$('.select_all_layer').click(function(){
				$find=$(this).parents('.ft_layer').find('input[type=checkbox]');
				$find.prop('checked','true')
				$find.next().addClass('checked');
			});
		$('.select_inverse_layer').click(function(){
				$find=$(this).parents('.ft_layer').find('label.checkbox');
				$find.each(function(){
					$c_input=$(this).children('input');
					$c_span=$(this).children('span');
					if($c_input.is(':checked')){
						$c_input.prop('checked','')
						$c_span.removeClass('checked');}
					else{	
						$c_input.prop('checked','true')
						$c_span.addClass('checked');}
					});
				
				
			});
		
	});
	
})(jQuery);
function bind_ccm_popup(){
	$('.ccm-popup-click').magnificPopup({
	    type: 'inline',

	    fixedContentPos: false,
	    fixedBgPos: true,

	    overflowY: 'auto',
		  closeOnBgClick:false,

	    closeBtnInside: false,
	    preloader: false,
	    
	    midClick: true,
	    removalDelay: 300,
	    mainClass: 'my-mfp-zoom-in'
	  });
}


function bindSpanPrice(){
	/*价格处理*/	
	$('.ccm_table1 td span.price').hover(function(){
			$(this).addClass('hover');
			$(this).next().stop(true,true).slideDown();
		},
		function(){
			$(this).removeClass('hover');
			$(this).next().hide();
		}
	);
}
function unbindSpanPrice(){
	/*解绑*/	
	$('.ccm_table1 td span.price').unbind('mouseenter').unbind('mouseleave');
}
/*单选框控制*/
function RadioCheckedName(radioname){
	$('input[type=radio][name='+radioname+']').change(function(){
		$('input[type=radio][name='+radioname+']').next().removeClass('checked');
		var this_checked=$(this).is(':checked');
		var hl_span=$(this).next();
		if(this_checked){hl_span.addClass('checked');}
		else{hl_span.removeClass('checked');}
		});
}
Array.prototype.remove = function(from, to) {
    var rest = this.slice((to || from) + 1 || this.length);
    this.length = from < 0 ? this.length + from : from;
    return this.push.apply(this, rest);
};

//对Date的扩展，将 Date 转化为指定格式的String 
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.format = function(fmt) 
{ //author: meizz 
	var o = { 
	 "M+" : this.getMonth()+1,                 //月份 
	 "d+" : this.getDate(),                    //日 
	 "h+" : this.getHours(),                   //小时 
	 "m+" : this.getMinutes(),                 //分 
	 "s+" : this.getSeconds(),                 //秒 
	 "q+" : Math.floor((this.getMonth()+3)/3), //季度 
	 "S"  : this.getMilliseconds()             //毫秒 
	}; 
	if(/(y+)/.test(fmt)) 
	 fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	for(var k in o) 
	 if(new RegExp("("+ k +")").test(fmt)) 
	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
	return fmt; 
}

function clone(obj){
    if (obj === null){
        return null; 
    }
    var o = obj.constructor === Array ? [] : {};
    for(var i in obj){
        if(obj.hasOwnProperty(i)){
            o[i] = typeof obj[i] === "object" ? clone(obj[i]) : obj[i];
        }
    }
    return o;
}

(function ($) {
    var _sleeptimer;
    $.sleep = function (time2sleep, callback) {
        $.sleep._sleeptimer = time2sleep;
        $.sleep._cback = callback;
        $.sleep.timer = setInterval('$.sleep.count()', 1000);
    }
    $.extend($.sleep, {
        current_i: 1,
        _sleeptimer: 0,
        _cback: null,
        timer: null,
        count: function () {
            if ($.sleep.current_i >= $.sleep._sleeptimer) {
                clearInterval($.sleep.timer);
                $.sleep._cback.call(this);
            }
            $.sleep.current_i++;
        }
    });
})(jQuery);