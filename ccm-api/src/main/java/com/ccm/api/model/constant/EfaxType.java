package com.ccm.api.model.constant;

import com.ccm.api.util.PropertiesUtil;

public class EfaxType {

	public static String email_pic_dir = PropertiesUtil.getProperty(ProjectConfigConstant.pictureUploadPath);
	public static String email_pic_path = email_pic_dir + "/chinaonlineblack.jpg";
	public static String email_subject = "CHINAonline畅联直连预订接口断开提醒";
	public static String email_content_view1 = "TO: ";
	public static String email_content_view5 = "为避免在接口恢复后产生重复订单，请把以上预订号录入在贵酒店系统的“预订号/订号/其他参考号/CRS NO./中央预订号”处";
	public static String email_content_view2 = "Fax: ";
	public static String email_content_view3 = "Date: ";
	public static String email_content_view4 = PropertiesUtil.getProperty(ProjectConfigConstant.pictureUrlContext) + "/chinaonline.jpg";
	public static String email_content_view6 = "请将此确认回传至：010-59320666<br>或回复酒店确认号邮件至Email：<br>col-ccmts.list@shijinet.com.cn";
	public static String email_content_view7 = "Email：chinaonlinets@qq.com";
	public static String email_content_view8 = "time:";
	public static String email_content_view10 = "预订号:";
	public static String email_content_view9 = "Reservation No";
	public static String email_content_view11 = "Guest Name";
	public static String email_content_view12 = "客人姓名:";
	public static String email_content_view14 = "Check-in Date";
	public static String email_content_view15 = "入住日期: ";
	public static String email_content_view19 = "离店日期:";
	public static String email_content_view16 = "Room type";
	public static String email_content_view17 = "房间类型:";
	public static String email_content_view18 = "房间数量:";
	public static String email_content_view20 = "Check-out Date";
	public static String email_content_view21 = "Number of Room";
	public static String email_content_view22 = "Remarks";
	public static String email_content_view23 = "备注:";
	public static String email_content_view24 = "酒店确认人签字:";
	public static String email_content_view25 = "此通知仅作为酒店备查，客人订单详细信息请登录畅联系统查询，如客人订单发生改变，以接口下传或畅联系统中内容为准。<br>西软信用住用户：在录入阿里信用住(不包括现付和预付)订单时，请必须录入以下信息：1.预订类型：后付预订2.渠道：畅连预订3.来源：畅联预定4.预订号：在附加信息/网络/预订号：填写CCM中央预订号15位+TAOBAO，避免客人离店后酒店收不到费用<br>";
	public static String email_content_view26 = "订单信息来源：CHINAonline畅联<br>";
	public static String email_content_view27 = "请将此确认回传至畅联传真： 010-59320666 或回复酒店确认号邮件至Email： col-ccmts.list@shijinet.com.cn<br>";
	public static String email_content_view28 = "联系电话： 13601391511";
	public static String email_content_view30 = "Best Regards! 请速回复确认";
	public static String email_content_view31 = "Room Rate";
	public static String email_content_view32 = "房价:";
	public static String email_content_view33 = "付款方式:";
	
	public static String email_content_send = "<html><head></head><body>" + email_content_view1;
	
}
