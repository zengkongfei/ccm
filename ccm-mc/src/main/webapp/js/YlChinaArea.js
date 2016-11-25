(function($) { 
    $.fn.jChinaArea = function(o) {
		o = $.extend({
			
			province:null,
			city:null,
			county:null,
			valueEleName:null
			
			
    }, o || {});
        var wrap=$(this);
		var sel=$("select",wrap);
		var sProvince=sel.eq(0);
		var sCity=sel.eq(1);
		var sCounty=sel.eq(2);
		var loc	= new Location();
        
		sProvince.empty();
		var sPoption	= '<option value=""></option>';
		sProvince.append(sPoption);
		sCity.empty();
		sCounty.empty();
		
		loc.fillOption(sProvince , '0',o.province);
		loc.fillOption(sCity , '0,'+sProvince.val(),o.city);
		loc.fillOption(sCounty , '0,' + sProvince.val() + ',' + sCity.val(),o.county);
		
		writeInput();
		
		sProvince.change(function() {
				sCity.empty();
				sCity.append(sPoption);
				loc.fillOption(sCity , '0,'+sProvince.val());
				sCounty.empty();
				loc.fillOption(sCounty , '0,' + sProvince.val() + ',' + sCity.val());
				writeInput();
				
	   })
	
		sCity.change(function() {
			sCounty.empty();
			var option	= '<option value=""></option>';
			sCounty.append(option);
			loc.fillOption(sCounty , '0,' + sProvince.val() + ',' + sCity.val());
			writeInput();
		})
		sCounty.change(function(){
			writeInput();
		})
	
		function writeInput(){
			$('#'+o.valueEleName).val($(":selected",sProvince).text()+"-"+$(":selected",sCity).text()+"-"+$(":selected",sCounty).text()) ;
			$('#'+o.valueEleName).html(loc.findValue('0',o.province)+"&nbsp;"+loc.findValue('0,'+o.province,o.city)+"&nbsp;"+loc.findValue('0,'+o.province+ ',' + o.city,o.county));
		}
	};
		
})(jQuery);
