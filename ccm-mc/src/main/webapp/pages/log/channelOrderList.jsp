<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<fmt:message key="ccm.report.ChannelOrderReport" />
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color: red">* </span> <fmt:message
								key="ccm.ReservationMonitorReport.PropertyCode" /> </span>
					</div>
					<div class="i_input">
						<s:textfield id="hotelCode" key="soc.hotelCode"
							cssClass="fxt w120"></s:textfield>
					</div></li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message
								key="ccm.ReservationMonitorReport.CreatedTime" /> </span>
					</div>
					<div class="i_input">
						<input id="changedDate" name="soc.changedDate"
							class="fxt w120 calendar"
							value="<fmt:formatDate value='${soc.changedDate }' 
						pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				
				<li class="col3_1"></li>
				
				<!-- 图表配置 -->
				<li class="col3_1" id="chartTypeLi" style="display: none">
					<div class="i_title">
						<span class="text"><fmt:message key="common.button.ChartType" /></span>
					</div>
					<div class="i_input" id="chartType_input">
						<select key="soc.chartType" class="fxt w120" name="soc.chartType" id="soc.chartType">
							<c:forEach var="m" items="${chartTypes}">
								<option value="${m }" <c:if test="${m==soc.chartType }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1" id="chartValueLi" style="display: none">
					<div class="i_title">
						<span class="text"><fmt:message key="common.button.ChartValue" /></span>
					</div>
					<div class="i_input" id="chartValue_input">
						<select key="soc.chartValue" class="fxt w120" name="soc.chartValue" id="soc.chartValue">
							<c:forEach var="m" items="${chartValues}">
								<option value="${m }" <c:if test="${m==soc.chartValue }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12"
					onclick="javascript:searchOrder();">
					<fmt:message key="common.button.searchSelect" />
				</button>
				<button type="button" class="btn_2 white mgR12"
					onclick="javascript:getPieData();">
					<fmt:message key="ccm.report.Chart" />
				</button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:exportOrder();"><fmt:message key="common.button.File"/></button>
			
			</div>
		</div>
	</div>
</form>
	<div id="show" class="ccm-popup width900 zoom-anim-dialog mfp-hide"
		style="height: 650px;"></div>
	<!-- 查询结果 -->
	<div class="c_whitebg">
		<div id="chart_monitor" style="height: 500%;"></div>

		<div id="chart_monitorNoData" style="display: none" align="center">
			<fmt:message key="ccm.report.Norecordfound" />.
		</div>
		
		<div class="bt_wp" id="bt_wp">
		<form id="displayForm" name="displayForm" method="post" action="">
			<display:table name="channelOrderResult.resultList" id="order"
				class="ccm_table1" requestURI="" pagesize="20" form="displayForm"
				size="channelOrderResult.totalCount" partialList="true">
				<display:column property="channelCode" sortable="true"
					titleKey="ccm.Channel.ChannelCode" headerClass="sorted" />
				<display:column property="hotelName" sortable="true"
					titleKey="ccm.ReservationMonitorReport.PropertyCode"
					headerClass="sorted" />
				<display:column property="countOfOrder" sortable="true"
					headerClass="sorted" titleKey="ccm.report.CountofOrders"></display:column>
				<display:column property="totalRoomNights" sortable="true"
					titleKey="ccm.report.RoomNights" headerClass="sorted" />
				<display:column property="totalAmountOfOrders" sortable="true"
					titleKey="ccm.report.AmountOfOrder" headerClass="sorted" />
			</display:table>
		</form>
		</div>
	</div>

<!-- 内容区域 end-->
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#chartValueLi").hide();
		$("#chartTypeLi").hide();
	});

	function getPieData() {
		
		$("#chartValueLi").show();
		$("#chartTypeLi").show();
		
		var chart = $('#chartType_input option:checked').val();
		var type = $('#chartValue_input option:checked').val();
		
		var channelCodes = new Array();
		var countOfOrders = new Array();
		var totalRoomNights = new Array();
		var totalAmountOfOrders = new Array();

		//默认选择酒店渠道订单总数
		var countOfOrder = resources.CountofOrders;
		var totalRoomNight = resources.TotalRoomNights;
		var totalAmountOfOrder = resources.TotalAmountOfOrder;
		var yAxisName = resources.CountofOrders;
		var titleText=resources.PropertyChannelOrderChart;
		var xAxisName=resources.ChannelCode;
		
		if ((type == '总晚房数')||(type == 'TotalRoomNights')) {
			countOfOrders = totalRoomNights;
			countOfOrder = totalRoomNight;
			yAxisName = resources.RoomNights;
		} else if ((type == '订单总金额')||(type == 'TotalAmountOfOrders')) {
			countOfOrders = totalAmountOfOrders;
			countOfOrder = totalAmountOfOrder;
			yAxisName = resources.AmountOfOrder;
		}

		//默认为柱形图
		var chartType = 'bar';
		var tooltipFormatter = "{b} : {c}";
		var xAxisShow = true;
		var yAxisShow = true;
		var dataZoomShow = true;
		var labelFormatter = " {b} : {c} ";
		
		//饼图
		if (("饼图" == chart)||("Pie" == chart)) {
			chartType = 'pie';
			tooltipFormatter = "{b} {c} ({d}%)";
			xAxisShow = false;
			yAxisShow = false;
			dataZoomShow = false;
			labelFormatter = "{b} {c} ({d}%)";
		}

		//根据查询条件，异步加载数据
		var hotelCode = $('#hotelCode').val();
		var changedDate = $('#changedDate').val();
		var url = "/channelOrder_ajaxGetchannelOrderPieData.do?hotelCode="
				+ hotelCode + "&changedDate=" + changedDate;
		$.ajax({
			type : "POST",
			async : false,
			dataType : "json",
			url : url,
			success : function(data) {

				if (data.length > 0) {
					
					$("#bt_wp").hide();
					$("#chart_monitorNoData").hide();
					
					$("#chart_monitor").show();
					
					
					for ( var i = 0; i < data.length; i++) {
						var pie = data[i];
						channelCodes[i] = pie.ChannelCode;
						countOfOrders[i] = pie.CountOfOrder;
						totalRoomNights[i] = pie.totalRoomNight;
						totalAmountOfOrders[i] = pie.totalAmountOfOrder;
					}
				}else{
					$("#chart_monitor").hide();

					$("#chart_monitorNoData").hide();
					
					$("#chartValueLi").hide();
					$("#chartTypeLi").hide();
					
					$("#chart_monitorNoData").show();
					$("#bt_wp").hide();
				}

			}
		});

		//组合数组：渠道代码以及其数据
		var seriesData = [];
		for ( var i = 0; i < channelCodes.length; i++) {
			var temp = {};
			temp.name = channelCodes[i];
			temp.value = countOfOrders[i];
			seriesData.push(temp);
		}

		var dom = document.getElementById("chart_monitor");
		var myChart = echarts.init(dom);

		var app = {};
		option = null;
		option = {
			//backgroundColor : 'rgba(83, 114, 102, 0.4)',
			title : {
				x : 'center',
				text : titleText,
				textStyle : {
					color : '#888',
				},
			 //subtext : 'Channel Order Report',
			//link: 'http://echarts.baidu.com/doc/example.html'
			},
			tooltip : {
				trigger : 'item',
				formatter : tooltipFormatter
			},
			toolbox : {
				show : true,
				feature : {
					dataView : {
						show : false,
						readOnly : false
					},
					restore : {
						show : false
					},
					saveAsImage : {
						show : false
					}
				}
			},
			calculable : true,
			grid : {
				borderWidth : 0,
				y : 80,
				y2 : 60
			},
			xAxis : [ {
				type : 'category',
				show : xAxisShow,
				data : channelCodes,
				name : xAxisName,
				nameGap : 16,
				nameTextStyle : {
					color : '#444',
					fontSize : 12
				},
				max : 31,
				splitLine : {
					show : false
				},
				axisLine : {
					lineStyle : {
						color : '#333'
					}
				},
				axisTick : {
					lineStyle : {
						color : '#333'
					}
				},
				axisLabel : {
					formatter : '{value}',
					textStyle : {
						color : '#333'
					}
				}
			} ],
			yAxis : [ {
				type : 'value',
				show : yAxisShow,
				name : yAxisName,
				nameLocation : 'end',
				nameGap : 20,
				nameTextStyle : {
					color : '#333',
					fontSize : 16
				},
				axisLine : {
					lineStyle : {
						color : '#333'
					}
				},
				axisTick : {
					lineStyle : {
						color : '#333'
					}
				},
				splitLine : {
					show : false
				},
				axisLabel : {
					// 使用函数模板，函数参数分别为刻度数值（类目），刻度的索引
					formatter : function(value, index) {
						value = value * 1;
						return value;
					},
					textStyle : {
						color : '#333'
					}
				}

			} ],
			dataZoom : [ {
				type : 'slider',
				show : dataZoomShow,
				start : 1,
				end : 100,
				handleSize : 8
			}, {
				type : 'inside',
				start : 1,
				show : dataZoomShow,
				end : 100
			}, {
				type : 'slider',
				show : dataZoomShow,
				yAxisIndex : 0,
				filterMode : 'empty',
				width : 10,
				height : '70%',
				handleSize : 8,
				showDataShadow : false,
				left : '93%'
			} ],
			series : [ {
				name : countOfOrder,
				type : chartType,
				itemStyle : {
					normal : {
						color : function(params) {
							var colorList = [ '#B7C334', '#FCCE90', '#E88C25',
									'#11727B', '#A7514B', '#FE8463', '#9BCA63',
									'#FAD860', '#F3A43B', '#69C0DD', '#D6674B',
									'#C5E579', '#F4E661', '#F2305A', '#44C0C0',
									'#C1232B', '#B5C334', '#FCCE10', '#E87C25',
									'#27727B', '#FE8463', '#9BCA63', '#FAD860',
									'#F3A43B', '#60C0DD', '#D7504B', '#C6E579',
									'#F4E001', '#F0805A', '#26C0C0', '#B7C634',
									'#FCCM90', '#N88C25', '#11027B', '#A7094B',
									'#F90463', '#HJCA63', '#EAD860', '#73A43B',
									'#F9C0DD', '#D5674B', '#C5E559', '#F4E561',
									'#F2355A', '#44C5C0', '#C1252B', '#B5C354',
									'#FCCE50', '#E85C25', '#27725B', '#FE8A63',
									'#9BCA6A', '#FAD86A', '#A3A43B', '#60C0AD',
									'#D75A4B', '#CAE579', '#FAE001', '#F08A5A',
									'#26CAC0' ];
							return colorList[params.dataIndex]
						},
						label : {
							show : true,
							position : 'top',
							formatter : labelFormatter
						}
					}
				},
				data : seriesData,
				markPoint : {
					tooltip : {
						trigger : 'item',
						backgroundColor : 'rgba(9,122,122,1)'
					}
				}
			} ]
		};
		if (option && typeof option === "object") {
			var startTime = +new Date();
			myChart.setOption(option, true);
			var endTime = +new Date();
			var updateTime = endTime - startTime;
			console.log("Time used:", updateTime);
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
		}, 2500);

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		// 处理点击事件并且条状到相应的百度搜索页面
		myChart.on('click', function(params) {
			//window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
			//alert("name="+params.name+","+"value="+params.value+","+"seriesName="+params.seriesName);
		});
		/*
		 所有的鼠标事件的参数 params 是一个包含点击图形的数据信息的对象，如下格式：
		 {
		 // 系列在传入的 option.series 中的 index
		 seriesIndex: number,
		 // 系列名称
		 seriesName: string,
		 // 数据名，类目名
		 name: string,
		 // 数据在传入的 data 数组中的 index
		 dataIndex: number,
		 // 传入的原始数据项
		 data: Object,
		 // 传入的数据值
		 value: number|Array
		 }
		 */

	}

	function searchOrder() {
		document.searchForm.action = "/channelOrder_list.do";
		$('#searchForm').submit();
	}

	 function exportOrder()
	 {
		//请选择酒店
		if($('#hotelCode').val().length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		document.searchForm.action = "/channelOrder_export.do";
		$('#searchForm').submit();
	 }
	 
	var dpconfig = {
		dateFormat : "yy-mm-dd",
		dayNamesMin : [ '<fmt:message key="calendar.week.sunday"/>',
				'<fmt:message key="calendar.week.monday"/>',
				'<fmt:message key="calendar.week.tuesday"/>',
				'<fmt:message key="calendar.week.wednesday"/>',
				'<fmt:message key="calendar.week.thursday"/>',
				'<fmt:message key="calendar.week.friday"/>',
				'<fmt:message key="calendar.week.saturday"/>' ],
		yearSuffix : '<fmt:message key="time.year"/>',
		monthNames : [ '<fmt:message key="calendar.month.january"/>',
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
				'<fmt:message key="calendar.month.december"/>' ],
		monthNamesShort : [ '<fmt:message key="calendar.month.january"/>',
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
				'<fmt:message key="calendar.month.december"/>' ],
		hourText : '<fmt:message key="time.Hour"/>',
		minuteText : '<fmt:message key="time.Minute"/>',
		timeText : '<fmt:message key="time.Time"/>',
		currentText : '<fmt:message key="time.Present"/>',
		closeText : '<fmt:message key="common.button.close"/>'
	}

	$("#changedDate").datepicker($.extend(dpconfig, {}));
</script>