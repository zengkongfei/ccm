/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.messages, {
	required : "必填字段",
	remote : "请修正该字段",
	email : "请输入正确格式的电子邮件",
	url : "请输入合法的网址",
	date : "请输入合法的日期",
	dateISO : "请输入合法的日期 (ISO).",
	number : "请输入合法的数字",
	digits : "只能输入整数",
	creditcard : "请输入合法的信用卡号",
	equalTo : "请再次输入相同的值",
	accept : "请输入拥有合法后缀名的字符串",
	maxlength : jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
	minlength : jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
	rangelength : jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
	range : jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max : jQuery.validator.format("请输入一个最大为 {0} 的值"),
	min : jQuery.validator.format("请输入一个最小为 {0} 的值")
});

jQuery.validator.addMethod("isTel", function(value, element, param) {
	var tel = /^\d{3,6}#\d{3,4}#\d{7,20}$/; // 电话号码格式0086#010#12345678
	return this.optional(element) || (tel.test(value));
}, "请正确填写您的电话号码");

// 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element, param) {
	var length = value.length;
	var mobile = /^1[358]\d{9}$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

jQuery.validator.addMethod("isDateCustom", function(value, element, param) {
	var v;
	if ($.browser.msie) {
		var date = value.split("-");
		if (date.length == 3) {
			v = new Date(parseInt(date[0]), parseInt(date[1]) - 1,
					parseInt(date[2]));
		}
	} else {
		v = new Date(value);
	}
	return this.optional(element) || !/Invalid|NaN/.test(v);
}, "请正确填写日期");
// 只允许输入英文字母、数字
jQuery.validator.addMethod("isLetterDigit", function(value, element, param) {
	var tel = /^[a-zA-Z0-9]+$/;
	return this.optional(element) || (tel.test(value));
}, "只支持英文字母和数字");
// 只允许输入英文字母、数字,英文字母必须大写
jQuery.validator.addMethod("isUpperLetterDigit",function(value, element, param) {
	var tel = /^[A-Z0-9]+$/;
	return this.optional(element) || (tel.test(value));
}, "只支持大写英文字母和数字");