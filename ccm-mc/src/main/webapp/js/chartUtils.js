//返回 yyyy-MM-dd格式的当前日期字符串
function nowFormat(){
	// 获取当前日期
	var now = new Date();
	year = now.getFullYear();    // 获取完整的年份(4位,1970-????)
	month = now.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
	day = now.getDate();       // 获取当前日(1-31)
	if (month < 10) {
		month = '0' + month;
	}
	return year + '-' + month + '-' + day;
}

//去除数组内重复的元素
function unique(arr) {
	var result = [], hash = {};
	for ( var i = 0, elem; (elem = arr[i]) != null; i++) {
		if (!hash[elem]) {
			result.push(elem);
			hash[elem] = true;
		}
	}
	return result;
}

// 组合数组：x轴及其对应y轴数据
function makeSeriesData(arrX, arrY) {
	var seriesData = [];
	for ( var i = 0; i < arrX.length; i++) {
		var temp = {};
		temp.name = arrX[i];
		temp.value = arrY[i];
		seriesData.push(temp);
	}
	return seriesData;
}
// 记录日志
function chartsLog(option, myChart) {
	if (option && typeof option === "object") {
		var startTime = +new Date();
		myChart.setOption(option, true);
		var endTime = +new Date();
		var updateTime = endTime - startTime;
		// console.log("Time used:", updateTime);
	}
}
// 自动循环显示toolTip。参数time是一个数字，表示多久循环一个模块
function displayTool(app, myChart, time) {

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
	}, time);

}

/* 特定图表 */
// 渠道订单 Bar
function getChannelOrderChartCountOfOrdersBar(type,id) {
	// 根据查询条件，异步加载数据
	var hotelCode = 'currentHotelForChart';
	var changedDate = nowFormat();
	var channelCodes = new Array();
	var countOfOrders = new Array();
	var totalRoomNights = new Array();
	var totalAmountOfOrders = new Array();

	// 默认选择酒店渠道订单总数
	var countOfOrder = resources.CountofOrders;
	var totalRoomNight = resources.TotalRoomNights;
	var totalAmountOfOrder = resources.TotalAmountOfOrder;
	var yAxisName = resources.CountofOrders;
	var titleText = resources.PropertyChannelOrderChart;
	var xAxisName = resources.ChannelCode;

	if ((type == '总晚房数') || (type == 'TotalRoomNights')) {
		countOfOrders = totalRoomNights;
		countOfOrder = totalRoomNight;
		yAxisName = resources.RoomNights;
	} else if ((type == '订单总金额') || (type == 'TotalAmountOfOrders')) {
		countOfOrders = totalAmountOfOrders;
		countOfOrder = totalAmountOfOrder;
		yAxisName = resources.AmountOfOrder;
	}

	// 柱形图 参数
	var chartType = 'bar';
	var tooltipFormatter = "{a}<br/>{b}:{c}";
	var xAxisShow = true;
	var yAxisShow = true;
	var dataZoomShow = true;
	var labelFormatter = " {b} : {c} ";

	/* 测试 假数据 */
	/*
	 hotelCode='SHTSH'; 
	 changedDate='2016-02-26';
	*/
	
	var url = "/channelOrder_ajaxGetchannelOrderPieData.do?hotelCode="
			+ hotelCode + "&changedDate=" + changedDate;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : url,
		success : function(data) {
			if (data.length > 0) {
				for ( var i = 0; i < data.length; i++) {
					var pie = data[i];
					channelCodes[i] = pie.ChannelCode;
					countOfOrders[i] = pie.CountOfOrder;
					totalRoomNights[i] = pie.totalRoomNight;
					totalAmountOfOrders[i] = pie.totalAmountOfOrder;
				}
			} else {
				channelCodes[0] = resources.NoData;
				$('#' + id).hide();
			}
			
			//begin------------------------
			if (channelCodes.length < 1) {
				$('#' + id).hide();
			}
			// Top10
			var topFive = new Array();
			for ( var i = 0; i < channelCodes.length; i++) {
				var c = new Object();
				c.count = countOfOrders[i];
				c.code = channelCodes[i];
				topFive[i] = c;
			}
			// 根据count对topFive进行降序排序
			topFive.sort(function(a, b) {
				return b.count - a.count;
			});
			// 截取前10
			topFive = topFive.slice(0, 10);
			var x = new Array();
			var y = new Array();
			for ( var i = 0; i < topFive.length; i++) {
				x[i] = topFive[i].code;
				y[i] = topFive[i].count;
			}
			// 组合数组：渠道代码以及其数据
			seriesData = makeSeriesData(x, y);

			var dom = document.getElementById(id);
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
				title : {
					x : 'left',
					text : titleText + ':' + yAxisName,
					subtext : resources.SourcePresentData + 'Top10',
					textStyle : {
						color : '#555',
						fontSize : 14
					},
					link : '/channelOrder_list.do?tmenuId=7208'
				},
				tooltip : {
					trigger : 'item',
					formatter : tooltipFormatter
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data : [ resources.hotelInterfaceDataGraphStop,
							resources.hotelInterfaceDataGraphActive ]

				},
				calculable : true,
				grid : {
					borderWidth : 0,
					y : 80,
					y2 : 60
				},
				xAxis : [ {
					type : 'category',
					axisLabel : {
						interval : 0,
						rotate : 50,
						margin : 2,
						textStyle : {
							color : '#222'
						}
					},
					data : x,
					axisLine : {
						lineStyle : {
							color : '#333',
							show : false
						}
					},
					axisTick : {
						lineStyle : {
							color : '#333'
						}
					},
					show : xAxisShow,
					itemStyle : {
						normal : {
							label : {
								position : 'inner',
								formatter : function(params) {
									return (params.percent - 0) + '%';
								},
								textStyle : {
									color : '#000'
								}
							},
							labelLine : {
								show : false
							}
						}
					}
				} ],
				yAxis : [ {
					type : 'value',
					show : yAxisShow,
					name : '            ' + yAxisName,
					nameLocation : 'end',
					nameGap : 20,
					nameTextStyle : {
						color : '#333',
						fontSize : 6
					}
				} ],
				series : [ {
					name : resources.ReminderSwitchChart,
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
								show : false,
								position : 'inner',
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
			displayTool(app, myChart, 4000);
			myChart.setOption(option);
			myChart.on('click', function(params) {
				// 跳转到饼图
				getChannelOrderChartCountOfOrdersPie(type,id);
			});
			//end  ------------------------
			
		}
	});
	
}

// 渠道订单Pie
function getChannelOrderChartCountOfOrdersPie(type,id) {
	var hotelCode = 'currentHotelForChart';
	var changedDate = nowFormat();
	var channelCodes = new Array();
	var countOfOrders = new Array();
	var totalRoomNights = new Array();
	var totalAmountOfOrders = new Array();

	// 默认选择酒店渠道订单总数
	var countOfOrder = resources.CountofOrders;
	var totalRoomNight = resources.TotalRoomNights;
	var totalAmountOfOrder = resources.TotalAmountOfOrder;
	var yAxisName = resources.CountofOrders;
	var titleText = resources.PropertyChannelOrderChart;
	var xAxisName = resources.ChannelCode;

	if ((type == '总晚房数') || (type == 'TotalRoomNights')) {
		countOfOrders = totalRoomNights;
		countOfOrder = totalRoomNight;
		yAxisName = resources.RoomNights;
	} else if ((type == '订单总金额') || (type == 'TotalAmountOfOrders')) {
		countOfOrders = totalAmountOfOrders;
		countOfOrder = totalAmountOfOrder;
		yAxisName = resources.AmountOfOrder;
	}
	// 饼图 参数
	var chartType = 'pie';
	var tooltipFormatter = "{b} {c} ({d}%)";
	var xAxisShow = false;
	var yAxisShow = false;
	var dataZoomShow = false;
	var labelFormatter = "{b}";

	/* 测试 假数据 */
	/*
	hotelCode='SHTSH';	 
	changedDate='2016-02-26';
	*/
	
	var url = "/channelOrder_ajaxGetchannelOrderPieData.do?hotelCode="
			+ hotelCode + "&changedDate=" + changedDate;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : url,
		success : function(data) {
			if (data.length > 0) {
				for ( var i = 0; i < data.length; i++) {
					var pie = data[i];
					channelCodes[i] = pie.ChannelCode;
					countOfOrders[i] = pie.CountOfOrder;
					totalRoomNights[i] = pie.totalRoomNight;
					totalAmountOfOrders[i] = pie.totalAmountOfOrder;
				}
			} else {
				channelCodes[0] = resources.NoData;
				$('#' + id).hide();
			}
			
			//begin------------------------
			if (channelCodes.length < 1) {
				$('#' + id).hide();
			}
			// Top5
			var topFive = new Array();
			for ( var i = 0; i < channelCodes.length; i++) {
				var c = new Object();
				c.count = countOfOrders[i];
				c.code = channelCodes[i];
				topFive[i] = c;
			}
			// 根据count对topFive进行降序排序
			topFive.sort(function(a, b) {
				return b.count - a.count;
			});
			// 截取前五
			topFive = topFive.slice(0, 5);
			var x = new Array();
			var y = new Array();
			for ( var i = 0; i < topFive.length; i++) {
				x[i] = topFive[i].code;
				y[i] = topFive[i].count;
			}
			// 组合数组：渠道代码以及其数据
			seriesData = makeSeriesData(x, y);

			var dom = document.getElementById(id);
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
				title : {
					text : titleText,
					subtext : yAxisName + 'Top5',
					x : 'right',
					textStyle : {
						color : '#555',
						fontSize : 14
					},
					link : '/pmsLog_list.do?tmenuId=95'

				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data : x
				},
				series : [ {
					name : resources.ReminderSwitchChart,
					type : 'pie',
					radius : '75%',
					center : [ '50%', '60%' ],
					data : seriesData,
					itemStyle : {
						normal : {
							label : {
								show : false,
								position : 'inner',
								formatter : "{b} {c}({d}%)",
							},
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
							}
						},
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(100, 255, 0, 1)'
						}
					},
					label : {
						show : true,
						position : 'top',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					}
				} ]
			};
			// 记录log
			chartsLog(option, myChart);
			displayTool(app, myChart, 4000);
			myChart.setOption(option);
			myChart.on('click', function(params) {
				getChannelOrderChartCountOfOrdersBar(type,id);
			});
			//end  ------------------------
			
		}
	});
	
}

// 酒店接口监控图 饼图
function getPieData() {
	var green = 0;
	var red = 0;
	var isMonitor = false;
	var stopActive = new Array();
	
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "/pmsLog_ajaxGetPieData.do",
		success : function(data) {
			if (data.length > 0) {
				for ( var i = 0; i < data.length; i++) {
					var pie = data[i];
					green = pie.green;
					red = pie.red;
					isMonitor = pie.isMonitor;
					stopActive[i] = green;
					stopActive[i + i] = red;
				}
			} else {
				$("#chart_monitor").hide();
			}
			//begin------------------------
			if (true == isMonitor) {
				var dom = document.getElementById("chart_monitor");
				var myChart = echarts.init(dom);
				var app = {};
				option = null;
				option = {
					title : {
						text : resources.hotelInterfaceDataGraph,
						subtext : resources.hotelInterfaceDataGraphTimeTip,
						x : 'center',
						textStyle : {
							color : '#555',
							fontSize : 14
						},
						link : '/pmsLog_list.do?tmenuId=95'

					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						orient : 'vertical',
						left : 'left',
						data : [ resources.hotelInterfaceDataGraphStop,
								resources.hotelInterfaceDataGraphActive ]

					},
					series : [ {
						name : resources.QuantityAndProportion,
						type : 'pie',
						radius : '75%',
						center : [ '50%', '60%' ],
						data : [ {
							value : red,
							name : resources.hotelInterfaceDataGraphStop,
							itemStyle : {
								normal : {
									color : 'rgba(215, 44, 44, 0.9)'
								}
							}
						}, {
							value : green,
							name : resources.hotelInterfaceDataGraphActive,
							itemStyle : {
								normal : {
									color : 'rgba(36, 168, 66, 0.7)'
								}
							}
						} ],
						itemStyle : {

							normal : {
								label : {
									show : true,
									position : 'inner',
									formatter : "{b} {c}({d}%)"
								}
							},
							emphasis : {
								shadowBlur : 10,
								shadowOffsetX : 0,
								shadowColor : 'rgba(100, 255, 0, 1)'
							}
						},

						label : {
							show : true,
							position : 'top',
							formatter : "{a} <br/>{b} : {c} ({d}%)"
						}
					} ]
				};
				chartsLog(option, myChart);
				displayTool(app, myChart, 2000);
			} else {
				$("#chart_monitor").hide();
			}
			/*点击红色部分，弹窗显示所有断开酒店*/
			myChart.setOption(option);
			myChart.on('click', function(params) {
				//alert('run pie 12');
			});
			//end  ------------------------
		}
	});
}

// 获取该酒店排名前五的渠道 Bar
function getTopFiveChannel(hotelCode, counts, chartType, id) {
	/* 获取并处理数据 */
	// alert("run getTopFiveChannel:"+new Date());
	// alert("hotelCode="+hotelCode+" counts="+counts.length);
	var channels = new Array();
	var channelCounts = new Array();
	var topFive = new Array();
	var n = 0;
	for ( var i = 0; i < counts.length; i++) {
		var roomMsg = counts[i];
		if (roomMsg.hotelCode == hotelCode) {
			channels[n] = roomMsg.channelCode;
			n++;
		}
	}
	// 去除重复
	channels = unique(channels);
	for ( var i = 0; i < channels.length; i++) {
		var temp = 0;
		var channel = channels[i];
		var top = new Object();
		top.channel = channel;
		for ( var j = 0; j < counts.length; j++) {
			var roomMsg = counts[j];
			if ((roomMsg.channelCode == channel)
					& (roomMsg.hotelCode == hotelCode)) {
				temp += roomMsg.count;
			}
		}
		channelCounts[i] = temp;
		top.count = temp;
		topFive[i] = top;
	}
	// 根据count对topFive进行降序排序
	topFive.sort(function(a, b) {
		return b.count - a.count;
	});
	// 截取数组前五个元素
	topFive = topFive.slice(0, 5);
	var xChannels = new Array();
	var yCounts = new Array();
	for ( var i = 0; i < topFive.length; i++) {
		xChannels[i] = topFive[i].channel;
		yCounts[i] = topFive[i].count / 100;
	}
	seriesData = makeSeriesData(xChannels, yCounts);

	// 柱形图：控件参数
	var tooltipFormatter = "{a}<br/>{b}:{c}";
	var xAxisShow = true;
	var yAxisShow = true;
	var dataZoomShow = true;
	var labelFormatter = " {b} : {c} ";

	var dom = document.getElementById(id);
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	option = {
		title : {
			x : 'left',
			text : resources.ChannelTopFive + '—' + hotelCode,
			textStyle : {
				color : '#555',
				fontSize : 14
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : tooltipFormatter
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			axisLabel : {
				interval : 0,
				rotate : 30,
				margin : 2,
				textStyle : {
					color : '#222'
				}
			},
			data : xChannels,
			axisLine : {
				lineStyle : {
					color : '#333',
					show : false
				}
			},
			axisTick : {
				lineStyle : {
					color : '#333'
				}
			},
			show : xAxisShow,
		} ],
		yAxis : [ {
			type : 'value',
			show : yAxisShow,
			name : '            ' + resources.UnitsHundreds,
			nameLocation : 'end',
			nameGap : 20,
			nameTextStyle : {
				color : '#333',
				fontSize : 6
			}
		} ],
		series : [ {
			name : resources.ReminderSwitchData,
			type : chartType,
			itemStyle : {
				normal : {
					color : function(params) {
						var colorList = [ '#73A43B', '#F9C0DD', '#D5674B',
								'#F4E561', '#F2355A', '#44C5C0', '#C1252B',
								'#C5E559' ];
						return colorList[params.dataIndex]
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
	displayTool(app, myChart, 4000);
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	myChart.on('click', function(params) {
		// 获取排名前五的酒店以及房型
		getTopFiveHotel("bar", "topFiveBar");
	});
}

// 获取排名前五的酒店 柱状图
function getTopFiveHotel(chartType, id) {
	// alert("run getTopFiveHotel:"+new Date());
	var hotels = new Array();
	var counts = new Array();
	var topFive = new Array();
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "/adsBeq_msgCount.do",
		success : function(data) {
			// console.log("data:", data);
			if (data.length > 0) {
				for ( var i = 0; i < data.length; i++) {
					var tf = data[i];
					hotels[i] = tf.hotelCode;
					var roomMsg = new Object();
					roomMsg.channelCode = tf.channelCode;
					roomMsg.hotelCode = tf.hotelCode;
					roomMsg.chainCode = tf.chainCode;
					roomMsg.roomTypeCode = tf.roomTypeCode;
					roomMsg.count = tf.count;
					counts[i] = roomMsg;
				}
			} else {
				counts[0] = resources.NoData;
				$('#' + id).hide();
			}
			
			//begin-------------------
			if (hotels.length < 1) {
				$('#' + id).hide();
			}
			// 去除重复的酒店代码
			hotels = unique(hotels);
			// 获取所有酒店的统计数据（htoelCode,hc）
			for ( var i = 0; i < hotels.length; i++) {
				var hc = 0;
				var msg = new Object();
				msg.hotelCode = hotels[i];
				for ( var j = 0; j < counts.length; j++) {
					var roomMsg = counts[j];
					if (hotels[i] == roomMsg.hotelCode) {
						hc += roomMsg.count;
					}
				}
				msg.hc = hc;
				topFive[i] = msg;
			}
			// 根据hc对topFive进行降序排序
			topFive.sort(function(a, b) {
				return b.hc - a.hc;
			});
			// 截取数组前五个元素
			topFive = topFive.slice(0, 5);
			var hotelCodes = new Array();
			var hotelCounts = new Array();
			// var subCodes=new Array();
			for ( var i = 0; i < topFive.length; i++) {
				hotelCodes[i] = topFive[i].hotelCode;
				// subCodes[i] = hotelCodes[i].substr(0, 3)+'..';
				topFive[i].hc = topFive[i].hc / 100;
				;
				hotelCounts[i] = topFive[i].hc;
			}
			seriesData = makeSeriesData(hotelCodes, hotelCounts);

			// 控件参数
			var tooltipFormatter = "{a}<br/>{b}:{c}";
			var xAxisShow = true;
			var yAxisShow = true;
			var dataZoomShow = true;
			var labelFormatter = " {b} : {c} ";

			var dom = document.getElementById(id);
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
				title : {
					x : 'left',
					text : resources.HotelTopFive,
					textStyle : {
						color : '#555',
						fontSize : 14
					}
				},
				tooltip : {
					trigger : 'item',
					formatter : tooltipFormatter
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					axisLabel : {
						interval : 0,
						rotate : 30,
						margin : 2,
						textStyle : {
							color : '#222'
						}
					},
					data : hotelCodes,
					axisLine : {
						lineStyle : {
							color : '#333',
							show : false
						}
					},
					axisTick : {
						lineStyle : {
							color : '#333'
						}
					},
					show : xAxisShow,
				} ],
				yAxis : [ {
					type : 'value',
					show : yAxisShow,
					name : '            ' + resources.UnitsHundreds,
					nameLocation : 'end',
					nameGap : 20,
					nameTextStyle : {
						color : '#333',
						fontSize : 6
					}
				} ],
				series : [ {
					name : resources.ReminderSwitchData + "",
					type : chartType,
					itemStyle : {
						normal : {
							color : function(params) {
								var colorList = [ '#73A43B', '#F9C0DD', '#D5674B',
										'#F4E561', '#F2355A', '#44C5C0', '#C1252B',
										'#C5E559' ];
								return colorList[params.dataIndex]
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
			displayTool(app, myChart, 4000);
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
			myChart.on('click', function(params) {
				getTopFiveChannel(params.name, counts, chartType, id)
			});
			//end  -------------------
			
		}
	});
	
}

// 获取该酒店排名前五的渠道 Pie
function getTopFiveChannelPie(hotelCode, counts, chartType, id) {
	// alert("run getTopFiveChannel:"+new Date());
	// alert("hotelCode="+hotelCode+" counts="+counts.length);
	var channels = new Array();
	var channelCounts = new Array();
	var topFive = new Array();
	var n = 0;
	for ( var i = 0; i < counts.length; i++) {
		var roomMsg = counts[i];
		if (roomMsg.hotelCode == hotelCode) {
			channels[n] = roomMsg.channelCode;
			n++;
		}
	}
	// 去除重复
	channels = unique(channels);
	for ( var i = 0; i < channels.length; i++) {
		var temp = 0;
		var channel = channels[i];
		var top = new Object();
		top.channel = channel;

		for ( var j = 0; j < counts.length; j++) {
			var roomMsg = counts[j];
			if ((roomMsg.channelCode == channel)
					& (roomMsg.hotelCode == hotelCode)) {
				temp += roomMsg.count;
			}
		}
		channelCounts[i] = temp;
		top.count = temp;
		topFive[i] = top;
	}
	// 根据count对topFive进行降序排序
	topFive.sort(function(a, b) {
		return b.count - a.count;
	});
	// 截取数组前五个元素
	topFive = topFive.slice(0, 5);
	var xChannels = new Array();
	var yCounts = new Array();
	for ( var i = 0; i < topFive.length; i++) {
		xChannels[i] = topFive[i].channel;
		yCounts[i] = topFive[i].count / 100;
	}
	seriesData = makeSeriesData(xChannels, yCounts);

	// 参数
	var tooltipFormatter = "{b} {c} {d}%";
	var xAxisShow = false;
	var yAxisShow = false;
	var dataZoomShow = false;
	var labelFormatter = " {b} : {c} ";

	var dom = document.getElementById(id);
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	option = {
		title : {
			text : resources.ChannelTopFive,
			subtext : '——' + hotelCode,
			x : 'right',
			textStyle : {
				color : '#555',
				fontSize : 14
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			left : 'left',
			data : xChannels

		},
		series : [ {
			name : resources.ReminderSwitchData,
			type : 'pie',
			radius : '75%',
			center : [ '50%', '60%' ],
			data : seriesData,
			itemStyle : {
				normal : {
					label : {
						show : false,
						position : 'inner',
						formatter : "{b} {c}({d}%)"
					},
					color : function(params) {
						var colorList = [ '#73A43B', '#F9C0DD', '#D5674B',
								'#F4E561', '#F2355A', '#44C5C0', '#C1252B',
								'#C5E559' ];
						return colorList[params.dataIndex]
					}
				},
				emphasis : {
					shadowBlur : 10,
					shadowOffsetX : 0,
					shadowColor : 'rgba(100, 255, 0, 1)'
				}
			},
			label : {
				show : true,
				position : 'top',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			}
		} ]
	};

	displayTool(app, myChart, 4000);
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	myChart.on('click', function(params) {
		// 点击跳转到获取排名前五的酒店
		getTopFiveHotelPie("pie", "topFivePie");
	});
}
// 获取排名前五的酒店Pie
function getTopFiveHotelPie(chartType, id) {
	// alert("run getTopFiveHotel:"+new Date());
	var hotels = new Array();
	var counts = new Array();
	var topFive = new Array();
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "/adsBeq_msgCount.do",
		success : function(data) {
			if (data.length > 0) {

				for ( var i = 0; i < data.length; i++) {
					var tf = data[i];
					hotels[i] = tf.hotelCode;
					var roomMsg = new Object();
					roomMsg.channelCode = tf.channelCode;
					roomMsg.hotelCode = tf.hotelCode;
					roomMsg.chainCode = tf.chainCode;
					roomMsg.roomTypeCode = tf.roomTypeCode;
					roomMsg.count = tf.count;
					counts[i] = roomMsg;
				}
			} else {
				counts[0] = resources.NoData;
			}
			
			//begin------------------------
			if (hotels.length < 1) {
				$('#' + id).hide();
			}
			// 去除重复的酒店代码
			hotels = unique(hotels);
			// 获取所有酒店的统计数据（htoelCode,hc）
			for ( var i = 0; i < hotels.length; i++) {
				var hc = 0;
				var msg = new Object();
				msg.hotelCode = hotels[i];
				for ( var j = 0; j < counts.length; j++) {
					var roomMsg = counts[j];
					if (hotels[i] == roomMsg.hotelCode) {
						hc += roomMsg.count;
					}
				}
				msg.hc = hc;
				topFive[i] = msg;
			}
			// 根据hc对topFive进行降序排序
			topFive.sort(function(a, b) {
				return b.hc - a.hc;
			});
			// 截取数组前五个元素
			topFive = topFive.slice(0, 5);
			var hotelCodes = new Array();
			var hotelCounts = new Array();
			// var subCodes=new Array();
			for ( var i = 0; i < topFive.length; i++) {
				hotelCodes[i] = topFive[i].hotelCode;
				// subCodes[i] = hotelCodes[i].substr(0, 3)+'..';
				topFive[i].hc = topFive[i].hc / 100;
				;
				hotelCounts[i] = topFive[i].hc;
			}
			seriesData = makeSeriesData(hotelCodes, hotelCounts);

			var tooltipFormatter = "{b} {c} {d}%";
			var xAxisShow = false;
			var yAxisShow = false;
			var dataZoomShow = false;
			var labelFormatter = "{b} {c} ({d}%)";

			var dom = document.getElementById(id);
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
				title : {
					text : resources.HotelTopFive,
					x : 'right',
					textStyle : {
						color : '#555',
						fontSize : 14
					}
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data : hotelCodes

				},
				series : [ {
					name : resources.ReminderSwitchData,
					type : 'pie',
					radius : '75%',
					center : [ '50%', '60%' ],
					data : seriesData,
					itemStyle : {
						normal : {
							label : {
								show : false,
								position : 'inner',
								formatter : "{b} {c}({d}%)"
							},
							color : function(params) {
								var colorList = [ '#73A43B', '#F9C0DD', '#D5674B',
										'#F4E561', '#F2355A', '#44C5C0', '#C1252B',
										'#C5E559' ];
								return colorList[params.dataIndex]
							}
						},
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(100, 255, 0, 1)'
						}
					},
					label : {
						show : true,
						position : 'top',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					}
				} ]
			};
			displayTool(app, myChart, 4000);
			myChart.setOption(option);
			myChart.on('click', function(params) {
				getTopFiveChannelPie(params.name, counts, chartType, id)
			});
			//end  ------------------------	
		}
	});
	
}

//begin------------------------
//end  ------------------------
