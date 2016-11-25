String.prototype.format = function() {
	if (arguments.length == 0)
		return this;
	for ( var s = this, i = 0; i < arguments.length; i++)
		s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
	return s;
};

function i18n_replace(str, arguments) {
	if (arguments.length == 0)
		return str;
	for ( var i = 0; i < arguments.length; i++)
		str = str.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
	return str;
};

// var arry = new Array();
// arry.push('<fmt:message key="ccm.Rates.DescriptionCorporate"/>');
// arry.push($(this).find('td:eq(0)').text());
// var str = '【{0}】多语言的第{1}项的语言种类未选择,请检查';
// alert(i18n_replace(str,arry))

var i18n_zh = {
	'DateError001' : '{0}不能为空',
	'DateError002' : '{0}填写的时间格式有误,格式',
	'DateError003' : '{0}填写的时间格式有误,长度小于8',
	'DateError004' : '{0}填写的月份有误,月份不能小于1',
	'DateError005' : '{0}填写的月份有误,月份不能大于12',
	'DateError006' : '{0}填写的日期有误,日期不能小于1',
	'DateError007' : '{0}填写的日期有误,平年的2月份日期不能大于28',
	'DateError008' : '{0}填写的日期有误,闰年的2月份日期不能大于29',
	'DateError009' : '{0}填写的日期有误,月份为4,6,9,11时,日期不能大于30',
	'DateError010' : '{0}填写的日期有误,月份为1,3,5,7,8,10,12时,日期不能大于31',
	'DateError011' : '{0}填写的小时有误,24小时制的小时数不能超过23',
	'DateError012' : '{0}填写的小时有误,12小时制的小时数不能超过11',
	'DateError013' : '{0}填写的分有误,分钟数不能超过59',
	'DateError014' : '{0}填写的秒有误,秒钟数不能超过59',
	'DateError015' : '{0}填写日期有误,不能大于当前日期',
	'DateError016' : '{0}填写日期有误,不能小于当前日期',
	'DateError017' : '{0}填写时间有误,不能大于当前时间',
	'DateError018' : '{0}填写时间有误,不能小于当前时间',
	'DateError019' : '{0}填写有误',
	'DateError020' : '{0}填写的时间格式有误,格式{1}',
	'upload001' : '组件加载中，请稍后...',
	'upload002' : '上传失败！',
	'upload003' : '上传成功！',
	'upload004' : '超出上传最大尺寸限制！',
	'upload005' : '待上传文件列表数量超限，不能选择！',
	'upload006' : '文件太大，不能选择！',
	'upload007' : '该文件大小为0，不能选择！',
	'upload008' : '该文件类型不可以上传！',
	'upload009' : '个文件待上传',
	'upload010' : '等待上传',
	'upload011' : '上传中...',
	'upload012' : '错误',
	'upload013' : '超出限制',
	'upload014' : '上传完成',
	'upload015' : '取消上传',
	'upload016' : '未知异常',
	'upload017' : '文件上传路径不存在或者路径错误!',
	'upload018' : '文件上传的路径不存在!',
	'upload019' : '准备上传',
	'upload020' : '删除',
	'numberErrorMessage' : '请输入有效数字',
	'nSelectedText' : '项选中',
	'hotelInterfaceDataGraph' : '酒店接口监控图',
	'hotelInterfaceDataGraphTimeTip' : '每5分钟刷新一次',
	'hotelInterfaceDataGraphStop' : '断开',
	'hotelInterfaceDataGraphActive' : '活跃',
	'QuantityAndProportion' : ' 酒店数   (所占比例) ',
	'CountofOrders' : '订单总数',
	'TotalAmountOfOrder' : '订单总金额',
	'TotalRoomNights' : '总晚房数',
	'PropertyChannelOrderChart' : '渠道订单统计图',
	'ChannelCode' : '渠道代码',
	'RoomNights' : '晚房数',
	'AmountOfOrder' : '订单总金额',
	'NoData' : '无数据',
	'SourcePresentData':'来源:当前酒店今日数据.',
	'ChannelTopFive':'渠道等待消息条数Top5',
	'HotelTopFive':'酒店等待消息条数Top5',
	'UnitsHundreds' : '单位:百条', 
	'ReminderSwitchChart' : '温馨提示:点击切换图表',
	'ReminderSwitchData' : '温馨提示:点击切换数据',
	'UnlockUser' : '解锁用户', 
	'ALLCODE' : '全选',
	'NoMoreThanTwo' : '最多选择两项',
	'BookingOrderOver' : '很抱歉，本次预定超过最大订单数量'
};
var i18n_en = {
	'DateError001' : 'The {0} was not null',
	'DateError002' : 'The time format input in the {0} was wrong and it should be:',
	'DateError003' : 'The time format input in the {0} was wrong and the length was less than 8. ',
	'DateError004' : 'The month input in the {0} was wrong and it could not be less than 1.',
	'DateError005' : 'The month input in the {0} was wrong and it could not be more than 12.',
	'DateError006' : 'The date input in the {0} was wrong and it could not be less than 1.',
	'DateError007' : 'The date input in the {0} was wrong and it could not be more than 28  in the February of nonleap year.',
	'DateError008' : 'The date input in the {0} was wrong and it could not be more than 29 in the February of leap year.',
	'DateError009' : 'The date input in the {0} was wrong and it could not be more than 30 when the month was April,June,September and November.',
	'DateError010' : 'The date input in the {0} was wrong and it could not be more than 31 when the month was January,March,May,July,August,October and December.',
	'DateError011' : 'The hour input in the {0} was wrong and it could not be more than 23 in the 24 hour system.',
	'DateError012' : 'The hour input in the {0} was wrong and it could not be more than 11 in the 12 hour system.',
	'DateError013' : 'The minute input in the {0} was wrong and it could not be more than 59.',
	'DateError014' : 'The second input in the {0} was wrong and it could not be more than 59.',
	'DateError015' : 'The date input in the {0} was wrong and it could not be more than the current date.',
	'DateError016' : 'The date input in the {0} was wrong and it could not be less than the current date.',
	'DateError017' : 'The time input in the {0} was wrong and it could not be more than the current time.',
	'DateError018' : 'The time input in the {0} was wrong and it could not be less than the current time.',
	'DateError019' : 'The input in the {0} was wrong.',
	'DateError020' : 'The time format input in the {0} was wrong and it should be:{1}',
	'upload001' : 'Component loading, please later...',
	'upload002' : 'Upload Failed',
	'upload003' : 'Upload Successful',
	'upload004' : 'Beyond the maximum size upload limits',
	'upload005' : 'The number of files to be uploaded is beyond the limits and cannot be selected.',
	'upload006' : 'The file is too large to be selected.',
	'upload007' : 'The file cannot be selected as the size is 0.',
	'upload008' : 'The file type cannot be uploaded.',
	'upload009' : 'files to be uploaded',
	'upload010' : 'Waiting to upload',
	'upload011' : 'Uploading...',
	'upload012' : 'Error',
	'upload013' : 'Beyond the limits',
	'upload014' : 'Upload Complete',
	'upload015' : 'Cancel Upload',
	'upload016' : 'Unknown Exception',
	'upload017' : 'The file upload path does not exist or the path is wrong.',
	'upload018' : 'The file upload path does not exist.',
	'upload019' : 'Ready to upload',
	'upload020' : 'Delete',
	'numberErrorMessage' : 'Please input the effecitve number',
	'nSelectedText' : '&nbsp;&nbsp;Selected',
	'hotelInterfaceDataGraph' : 'Interface Monitor Graph',
	'hotelInterfaceDataGraphTimeTip' : 'Refresh once every 5 minutes.',
	'hotelInterfaceDataGraphStop' : 'Stop',
	'hotelInterfaceDataGraphActive' : 'Active',
	'QuantityAndProportion' : 'Quantity (Proportion)',
	'CountofOrders' : 'Count of Orders',
	'TotalAmountOfOrder' : 'Total Amount of Order',
	'TotalRoomNights' : 'Total Room Nights',
	'PropertyChannelOrderChart' : 'Channel Order Chart',
	'ChannelCode' : 'ChannelCode',
	'RoomNights' : 'Room Nights',
	'AmountOfOrder' : 'Amount of Order',
	'NoData' : 'No Data',
	'SourcePresentData':'Source:Present-day data of current property.',
	'ChannelTopFive':'ChannelsADSPendingTop5',
	'HotelTopFive':'PropertyADSPendingTopFive',
	'UnitsHundreds' : 'Unit:Hundreds', 
	'ReminderSwitchChart' : 'Reminder:Click and Switch Chart',
	'ReminderSwitchData' : 'Reminder:Click and Switch Data',
	'UnlockUser' : 'Unlock this user', 
	'ALLCODE' : 'ALL',
	'NoMoreThanTwo' : 'Cannot Choose More Than Two',
	'BookingOrderOver' : 'We are sorry to inform you that the booking order is over the maximum order quantity'
};

var resources = null;
function initI18nJs(local) {
	if (local == 'en_US') {
		resources = i18n_en;
	} else if (local == 'zh_CN') {
		resources = i18n_zh;
	}
};

