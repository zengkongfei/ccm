<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div id="chart_monitor"  style="height:500%"></div>


<script src="<c:url value='/js/main.js'/>"></script>
<script>

$(document).ready(function() {
	
	if('/main.do'==window.location.pathname){
		getPieData();
		setInterval("getPieData()",1000*60*5);
	}
	
});

function getPieData() {
	
	var green=0;
	var red=0;
	var isMonitor=false;

	var stopActive = new Array();
	
	$.ajax({
	  	 type:"POST",
	  	 async:false,
	     dataType : "json",
	  	 url:"/pmsLog_ajaxGetPieData.do",
		 success:function(data){
			 if(data.length > 0){	
				 
				  for(var i =0 ; i < data.length ; i++)
				  {
					  var pie = data[i];	  
					  green = pie.green;
					  red = pie.red;
					  isMonitor = pie.isMonitor;
					  stopActive[i]=green;
					  stopActive[i+i]=red;
				  }
				  
			  }
	     }
	});

	if(true==isMonitor){
		var dom = document.getElementById("chart_monitor");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;
		option = {
		    title : {
		        text: resources.hotelInterfaceDataGraph,
		        subtext: resources.hotelInterfaceDataGraphTimeTip,
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: [resources.hotelInterfaceDataGraphStop,resources.hotelInterfaceDataGraphActive]
		        
		    },	
		    series : [
		        {
		            name: resources.QuantityAndProportion,
		            type: 'pie',
		            radius : '75%',
		            center: ['50%', '60%'],
		            data:[
		                {value:red, name:resources.hotelInterfaceDataGraphStop,itemStyle:{normal:{color:'rgba(215, 44, 44, 0.9)'}}},
		                {value:green, name:resources.hotelInterfaceDataGraphActive,itemStyle:{normal:{color:'rgba(36, 168, 66, 0.7)'}}}
					],
		            itemStyle: {
		            	
		            	normal : {
	    					label : {
	    						show : true,
	    						position : 'top',
	    						formatter : "{b} {c} ({d}%)"
	    					}
	    				},
		    			
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(100, 255, 0, 1)'
		                }
		            },
		            label : {
						show : true,
						position : 'top',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					}
		        }
		    ]
		};
		;
		if (option && typeof option === "object") {
		    var startTime = +new Date();
		    myChart.setOption(option, true);
		    var endTime = +new Date();
		    var updateTime = endTime - startTime;
		}
		
		app.currentIndex = -1;

		app.timeTicket = setInterval(function() {
			var dataLen = option.series[0].data.length;
			// 取消之前高亮的图形
			myChart.dispatchAction({
				type : 'downplay',
				seriesIndex : 0,
				dataIndex : app.currentIndex
			});
			app.currentIndex = (app.currentIndex + 1) % dataLen;
			// 高亮当前图形
			myChart.dispatchAction({
				type : 'highlight',
				seriesIndex : 0,
				dataIndex : app.currentIndex
			});
			// 显示 tooltip
			myChart.dispatchAction({
				type : 'showTip',
				seriesIndex : 0,
				dataIndex : app.currentIndex
			});
		}, 3500);
		
	}else{
		$("#chart_monitor").hide();
	}
	
}

</script>



   
