package com.ccm.api.model.constant;

import com.ccm.api.util.PropertiesUtil;

public class EmailType {

	public final static String Order = "orderEmail";// 订单邮件确认函
	public final static String HotelInterface = "hotelEmail";// 酒店接口提醒函
	public final static String CreditLimitNotice="creditLimitEmail";//保证金邮件提醒
	public final static String AllotmentNotice="allotmentEmail";//保留房邮件提醒
	public static String email_pic_dir = PropertiesUtil.getProperty(ProjectConfigConstant.pictureUploadPath);
	public static String email_pic_path = email_pic_dir + "/chinaonline.jpg";
	public static String email_subject = "CHINAonline畅联直连预订接口断开提醒";
	public static String email_content_view1 = "尊敬的酒店用户";
	public static String email_content_view5 = "：";
	public static String email_content_view2 = "贵酒店直连预订接口目前处于断开状态，为避免影响订单、房量、房价传输，而造成的酒店损失和投诉。请立即联系IT经理或项目负责人恢复接口运行，非常感谢您的配合！";
	public static String email_content_view3 = "直连预订接口维护热线：13601391511（此邮件为系统的自动触发提醒，请勿回复，谢谢）";
	public static String email_content_view4 = PropertiesUtil.getProperty(ProjectConfigConstant.pictureUrlContext) + "/chinaonline.jpg";
	public static String email_content_view6 = "恢复接口操作详见网盘地址：";
	public static String email_content_view7 = "西软接口链接: ";
	public static String email_content_view8 = "http://pan.baidu.com/s/1o6w3Qu2";
	public static String email_content_view9 = "泰能接口链接: ";
	public static String email_content_view10 = "http://pan.baidu.com/s/1mgne8MO";
	public static String email_content_view11 = "千里马接口链接: ";
	public static String email_content_view12 = "http://pan.baidu.com/s/1hqqA7jA";
	public static String email_content_view13 = "Sinfonia接口链接: ";
	public static String email_content_view14 = "http://pan.baidu.com/s/1v617w";
	public static String email_content_view15 = "Opera接口链接: ";
	public static String email_content_view16 = "http://pan.baidu.com/s/1fryt4";
	public static String email_content_view17 = "Fidelio接口链接: ";
	public static String email_content_view18 = "http://pan.baidu.com/s/1ntpeuW5";

	public static String email_content_send = "<html><head></head><body>" + email_content_view1;
	public static String email_content_send2 = email_content_view5 + "<br>" + email_content_view2 + "<br>" + email_content_view6 + "<br>" + email_content_view7 + email_content_view8 + "<br>" + email_content_view9 + email_content_view10 + "<br>" + email_content_view11 + email_content_view12 + "<br>" + email_content_view13 + email_content_view14 + "<br>" + email_content_view15 + email_content_view16 + "<br>" + email_content_view17 + email_content_view18 + "<br>" + email_content_view3 + "<br><img src=cid:image/></body></html>";

	
	public static String email_subject_forgetPassword="您的密码将会被重置";
	public static String email_subject_forgetPassword_view1="请点击以下链接即可直接到CCM账户管理来直接修改密码，有效期30分钟。链接为：<a href='";
	public static String email_subject_forgetPassword_view2="'>";
	public static String email_subject_forgetPassword_view3="</a>";
}
