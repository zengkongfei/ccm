package com.ccm.api.util.efax;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.StringUtils;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.constant.EfaxType;
import com.ccm.api.service.fax.FaxUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Test {
	static String format = "json";
	static String userid = "chinaonline@gfax.cn";// 账号必须加上@gfax.cn,否则失败
	static String pass = "chinaOnline2016";
	static String deskey = "f6439bd3";

	public static void main(String[] args) {
		Test t = new Test();
		t.sendFax();
	}
	
	@SuppressWarnings("restriction")
	public String image2byte(String path) {
		File file = new File(path);
		byte[] data = null;

		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(path);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder(); 
		return encoder.encode(data);
	}

	// 查询传真任务
	public static void queryTask() {
		FaxUtil allcom = new FaxUtil(userid, pass, FaxUtil.MD5);
		String msgid = "interface-chay@gfax.cn-1458892496038";
		String url = "http://api.gfax.cn:8088/querytask/" + msgid + "/"+ format;
		String result = allcom.getHttp(url);
		JSONObject json = JSONObject.parseObject(result);
		System.out.println(json.get("status"));
	}

	/**
	 * 查余额
	 */
	public static void queryMoney() {
		FaxUtil allcom = new FaxUtil(userid, pass, FaxUtil.MD5);
		String url = "http://api.gfax.cn:8088/querymoney/" + format;
		String result = allcom.getHttp(url);
		System.out.println(result);
	}
	
	
	public static void sendFaxStr(){
		String msgid = "interface-" + userid + "-" + System.currentTimeMillis();
		String posturl = "http://api.gfax.cn:8088/sendfax/" + format;
		FaxUtil allcom = new FaxUtil(userid, pass, FaxUtil.MD5,deskey);

		Map<String, String> datamap = new Hashtable<String, String>();
		datamap.put("subject", "02151010001");
		datamap.put("body", "test传真"); 
    	datamap.put("msgid", msgid);
		String result = allcom.sendFax(posturl, datamap, null);
		JSONObject json = JSONObject.parseObject(result);
		System.out.println(result);
		System.out.println(json.get("result"));
	}

	private static void html2pdf(Map<String,String> map,String filePath){
		try {

			// Step 1—Create a Document.
			Document document = new Document();
			PdfWriter.getInstance(document,new FileOutputStream(filePath));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  // 设置字体

		   	PdfWriter pw = PdfWriter.getInstance(document, baos);

			// Step 3—Open the Document.
			document.open();
			
			// 添加字体
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfChinese, 12, Font.NORMAL);


			// 顶部空格
			PdfPTable img_temp = new PdfPTable(4);
			img_temp.setTotalWidth(400);
			img_temp.setLockedWidth(true);
			
			PdfPCell img_p_temp = new PdfPCell();
			img_p_temp.setFixedHeight(25);
			img_p_temp.setBorderColor(BaseColor.WHITE);
			img_p_temp.setHorizontalAlignment(Element.ALIGN_CENTER);
			img_temp.addCell(img_p_temp);
			img_p_temp.setColspan(4);
			document.add(img_temp);
			
			// 图片
			PdfPTable img = new PdfPTable(4);
			img.setTotalWidth(400);
			img.setWidths(new float[]{100f, 100f, 100f,100f});
			img.setLockedWidth(true);
			PdfPCell img_p1 = new PdfPCell();
			img_p1.setFixedHeight(50);
			img_p1.setBorderColor(BaseColor.WHITE);
			img_p1.setHorizontalAlignment(Element.ALIGN_CENTER);
			img_p1.setColspan(2);
			img_p1.setTop(20.00f);
			img.addCell(img_p1);
			
			PdfPCell img_p = new PdfPCell();
			img_p.setFixedHeight(25);
			img_p.setBorderColor(BaseColor.WHITE);
			img_p.setHorizontalAlignment(Element.ALIGN_CENTER);
			img_p.setColspan(2);
			img_p.setTop(20.00f);
			Image image =Image.getInstance("c:/chinaonline.jpg");
			image.setScaleToFitHeight(true);
			image.scalePercent(110f);
			img_p.setImage(image);
			img.addCell(img_p);
			document.add(img);
			
			// TO
			PdfPTable to = new PdfPTable(4);
			to.setTotalWidth(400);
			to.setWidths(new float[]{100f, 100f, 100f,100f});
			to.setLockedWidth(true);
			
			PdfPCell c1_0 = new PdfPCell(new Paragraph("TO:", font));
			c1_0.setFixedHeight(25);
			c1_0.setBorderColor(BaseColor.WHITE);
			c1_0.setHorizontalAlignment(Element.ALIGN_LEFT);
			to.addCell(c1_0);

			PdfPCell c2_1 = new PdfPCell(new Paragraph(map.get("tel"), font));
			c2_1.setFixedHeight(25);
			c2_1.setBorderColor(BaseColor.WHITE);
			c2_1.setHorizontalAlignment(Element.ALIGN_LEFT);
			to.addCell(c2_1);
			PdfPCell c1_2 = new PdfPCell(new Paragraph("", font));
			c1_2.setFixedHeight(25);
			c1_2.setBorderColor(BaseColor.WHITE);
			c1_2.setHorizontalAlignment(Element.ALIGN_CENTER);
			to.addCell(c1_2);
			PdfPCell c1_3 = new PdfPCell(new Paragraph("", font));
			c1_3.setFixedHeight(25);
			c1_3.setBorderColor(BaseColor.WHITE);
			c1_3.setHorizontalAlignment(Element.ALIGN_CENTER);
			to.addCell(c1_3);
			document.add(to);
			
			// 空格
			PdfPTable temp = new PdfPTable(4);
			temp.setTotalWidth(400);
			temp.setWidths(new float[]{100f, 100f, 100f,100f});
			temp.setLockedWidth(true);
			
			PdfPCell temp1 = new PdfPCell(new Paragraph("", font));
			temp1.setFixedHeight(25);
			temp1.setBorderColor(BaseColor.WHITE);
			temp1.setHorizontalAlignment(Element.ALIGN_CENTER);
			temp.addCell(temp1);

			PdfPCell temp2 = new PdfPCell(new Paragraph("", font));
			temp2.setFixedHeight(25);
			temp2.setBorderColor(BaseColor.WHITE);
			temp2.setHorizontalAlignment(Element.ALIGN_CENTER);
			temp.addCell(temp2);
			
			PdfPCell temp3 = new PdfPCell(new Paragraph("请将此确认回传至：010-59320666", font));
			temp3.setFixedHeight(25);
			temp3.setBorderColor(BaseColor.WHITE);
			temp3.setHorizontalAlignment(Element.ALIGN_LEFT);
			temp.addCell(temp3);
			PdfPCell temp4 = new PdfPCell(new Paragraph("", font));
			temp4.setFixedHeight(25);
			temp4.setBorderColor(BaseColor.WHITE);
			temp4.setHorizontalAlignment(Element.ALIGN_CENTER);
			temp.addCell(temp4);
			document.add(temp);
			// FAX
			PdfPTable fax = new PdfPTable(4);
			fax.setTotalWidth(400);
			fax.setWidths(new float[]{100f, 100f, 100f,100f});
			fax.setLockedWidth(true);
			
			PdfPCell fax1 = new PdfPCell(new Paragraph("FAX:", font));
			fax1.setFixedHeight(25);
			fax1.setRight(10.0f);
			fax1.setBorderColor(BaseColor.WHITE);
			fax1.setHorizontalAlignment(Element.ALIGN_LEFT);
			fax.addCell(fax1);

			PdfPCell fax2 = new PdfPCell(new Paragraph(map.get("fax"), font));
			fax2.setFixedHeight(25);
			fax2.setBorderColor(BaseColor.WHITE);
			fax2.setHorizontalAlignment(Element.ALIGN_LEFT);
			fax.addCell(fax2);
			
			PdfPCell fax3 = new PdfPCell(new Paragraph("或回复酒店确认号邮件至Email：", font));
			fax3.setFixedHeight(25);
			fax3.setBorderColor(BaseColor.WHITE);
			fax3.setHorizontalAlignment(Element.ALIGN_LEFT);
			fax.addCell(fax3);
			PdfPCell fax4 = new PdfPCell(new Paragraph("", font));
			fax4.setFixedHeight(25);
			fax4.setBorderColor(BaseColor.WHITE);
			fax4.setHorizontalAlignment(Element.ALIGN_CENTER);
			fax.addCell(fax4);
			document.add(fax);
			
			// DATE
			PdfPTable date = new PdfPTable(4);
			date.setTotalWidth(400);
			date.setWidths(new float[]{100f, 100f, 100f,100f});
			date.setLockedWidth(true);
			
			PdfPCell date1 = new PdfPCell(new Paragraph("DATE:", font));
			date1.setFixedHeight(25);
			date1.setBorderColor(BaseColor.WHITE);
			date1.setHorizontalAlignment(Element.ALIGN_LEFT);
			date.addCell(date1);

			PdfPCell date2 = new PdfPCell(new Paragraph(map.get("date"), font));
			date2.setFixedHeight(25);
			date2.setBorderColor(BaseColor.WHITE);
			date2.setHorizontalAlignment(Element.ALIGN_LEFT);
			date.addCell(date2);
			
			PdfPCell date3 = new PdfPCell(new Paragraph("col-ccmts.list@shijinet.com.cn", font));
			date3.setFixedHeight(25);
			date3.setBorderColor(BaseColor.WHITE);
			date3.setHorizontalAlignment(Element.ALIGN_LEFT);
			date3.setColspan(2);
			date.addCell(date3);
			
			document.add(date);
			
			// 空格
			document.add(img_p_temp);
			
			// Reservation No
			PdfPTable reservation = new PdfPTable(4);
			reservation.setTotalWidth(400);
			reservation.setWidths(new float[]{200f, 100f, 50f,50f});
			reservation.setLockedWidth(true);
			
			PdfPCell reservation1 = new PdfPCell(new Paragraph("Reservation No:", font));
			reservation1.setFixedHeight(25);
			reservation1.setBorderColor(BaseColor.WHITE);
			reservation1.setHorizontalAlignment(Element.ALIGN_LEFT);
			reservation.addCell(reservation1);
			PdfPCell reservation2 = new PdfPCell(new Paragraph("", font));
			reservation2.setFixedHeight(25);
			reservation2.setBorderColor(BaseColor.WHITE);
			reservation2.setHorizontalAlignment(Element.ALIGN_LEFT);
			reservation2.setColspan(3);
			reservation.addCell(reservation2);
			document.add(reservation);
			
			// 预订号
			PdfPTable reservationCN = new PdfPTable(4);
			reservationCN.setTotalWidth(400);
			reservationCN.setWidths(new float[]{100f, 100f, 100f,100f});
			reservationCN.setLockedWidth(true);
			
			PdfPCell reservationCN1 = new PdfPCell(new Paragraph("预订号:", font));
			reservationCN1.setFixedHeight(25);
			reservationCN1.setBorderColor(BaseColor.WHITE);
			reservationCN1.setHorizontalAlignment(Element.ALIGN_LEFT);
			reservationCN.addCell(reservationCN1);
			
			PdfPCell reservationCN2 = new PdfPCell(new Paragraph(map.get("reservationNo"), font));
			reservationCN2.setFixedHeight(25);
			reservationCN2.setBorderColor(BaseColor.WHITE);
			reservationCN2.setHorizontalAlignment(Element.ALIGN_LEFT);
			reservationCN2.setColspan(3);
			reservationCN.addCell(reservationCN2);
			document.add(reservationCN);
			// 描述
			PdfPTable reservationdesc = new PdfPTable(4);
			reservationdesc.setTotalWidth(400);
			reservationdesc.setWidths(new float[]{50f, 100f, 100f,150f});
			reservationdesc.setLockedWidth(true);
			
			PdfPCell reservationdesc1 = new PdfPCell(new Paragraph("为避免在接口恢复后产生重复订单，请把以上预订号录入在贵酒店系统的“预订号/订号/其他参考号/CRS NO./中央预订号”处", font));
			reservationdesc1.setFixedHeight(50);
			reservationdesc1.setBorderColor(BaseColor.WHITE);
			reservationdesc1.setHorizontalAlignment(Element.ALIGN_LEFT);
			reservationdesc1.setColspan(4);
			reservationdesc.addCell(reservationdesc1);
			document.add(reservationdesc);
			// 空格
			document.add(img_p_temp);
			
			// Guest Name
			PdfPTable guestNameEN = new PdfPTable(4);
			guestNameEN.setTotalWidth(400);
			guestNameEN.setWidths(new float[]{100f, 100f, 100f,100f});
			guestNameEN.setLockedWidth(true);
			
			PdfPCell guestNameEN1 = new PdfPCell(new Paragraph("Guest Name:", font));
			guestNameEN1.setFixedHeight(25);
			guestNameEN1.setBorderColor(BaseColor.WHITE);
			guestNameEN1.setHorizontalAlignment(Element.ALIGN_LEFT);
			guestNameEN.addCell(guestNameEN1);
			
			PdfPCell guestNameEN2 = new PdfPCell(new Paragraph(map.get("guestNameEn"), font));
			guestNameEN2.setFixedHeight(25);
			guestNameEN2.setBorderColor(BaseColor.WHITE);
			guestNameEN2.setHorizontalAlignment(Element.ALIGN_LEFT);
			guestNameEN2.setColspan(3);
			guestNameEN.addCell(guestNameEN2);
			document.add(guestNameEN);
			
			// 客人姓名
			PdfPTable guestNameCN = new PdfPTable(4);
			guestNameCN.setTotalWidth(400);
			guestNameCN.setWidths(new float[]{100f, 100f, 100f,100f});
			guestNameCN.setLockedWidth(true);
			
			PdfPCell guestNameCN1 = new PdfPCell(new Paragraph("客人姓名:", font));
			guestNameCN1.setFixedHeight(25);
			guestNameCN1.setBorderColor(BaseColor.WHITE);
			guestNameCN1.setHorizontalAlignment(Element.ALIGN_LEFT);
			guestNameCN.addCell(guestNameCN1);
			
			PdfPCell guestNameCN2 = new PdfPCell(new Paragraph(map.get("guestName"), font));
			guestNameCN2.setFixedHeight(25);
			guestNameCN2.setBorderColor(BaseColor.WHITE);
			guestNameCN2.setHorizontalAlignment(Element.ALIGN_LEFT);
			guestNameCN2.setColspan(3);
			guestNameCN.addCell(guestNameCN2);
			document.add(guestNameCN);
			// 空格
			document.add(img_p_temp);
			
			// Check-in Date Check-out Date
			PdfPTable check = new PdfPTable(4);
			check.setTotalWidth(400);
			check.setWidths(new float[]{100f, 100f, 100f,100f});
			check.setLockedWidth(true);
			
			PdfPCell checkin = new PdfPCell(new Paragraph("Check-in Date", font));
			checkin.setFixedHeight(25);
			checkin.setBorderColor(BaseColor.WHITE);
			checkin.setHorizontalAlignment(Element.ALIGN_LEFT);
			check.addCell(checkin);
			
			PdfPCell checkout = new PdfPCell(new Paragraph("Check-out Date", font));
			checkout.setFixedHeight(25);
			checkout.setBorderColor(BaseColor.WHITE);
			checkout.setHorizontalAlignment(Element.ALIGN_LEFT);
			check.addCell(checkout);
			
			document.add(check);
			
			// 入住离店日期
			PdfPTable check_EN = new PdfPTable(4);
			check_EN.setTotalWidth(400);
			check_EN.setWidths(new float[]{100f, 100f, 100f,100f});
			check_EN.setLockedWidth(true);
			
			PdfPCell check_EN_in = new PdfPCell(new Paragraph("入住日期:", font));
			check_EN_in.setFixedHeight(25);
			check_EN_in.setBorderColor(BaseColor.WHITE);
			check_EN_in.setHorizontalAlignment(Element.ALIGN_LEFT);
			check_EN.addCell(check_EN_in);
			
			PdfPCell check_EN_in_date = new PdfPCell(new Paragraph(map.get("checkInDate"), font));
			check_EN_in_date.setFixedHeight(25);
			check_EN_in_date.setBorderColor(BaseColor.WHITE);
			check_EN_in_date.setHorizontalAlignment(Element.ALIGN_LEFT);
			check_EN.addCell(check_EN_in_date);
			
			PdfPCell check_EN_out = new PdfPCell(new Paragraph("离店日期:", font));
			check_EN_out.setFixedHeight(25);
			check_EN_out.setBorderColor(BaseColor.WHITE);
			check_EN_out.setHorizontalAlignment(Element.ALIGN_LEFT);
			check_EN.addCell(check_EN_out);
			
			PdfPCell check_EN_out_date = new PdfPCell(new Paragraph(map.get("checkOutDate"), font));
			check_EN_out_date.setFixedHeight(25);
			check_EN_out_date.setBorderColor(BaseColor.WHITE);
			check_EN_out_date.setHorizontalAlignment(Element.ALIGN_LEFT);
			check_EN.addCell(check_EN_out_date);
			
			document.add(check_EN);
			// 空格
			document.add(img_p_temp);
			
			// Room type  Number of Room
			PdfPTable room = new PdfPTable(4);
			room.setTotalWidth(400);
			room.setWidths(new float[]{100f, 100f, 100f,100f});
			room.setLockedWidth(true);
			
			PdfPCell roomType = new PdfPCell(new Paragraph("Room type", font));
			roomType.setFixedHeight(25);
			roomType.setBorderColor(BaseColor.WHITE);
			roomType.setHorizontalAlignment(Element.ALIGN_LEFT);
			roomType.setColspan(2);
			room.addCell(roomType);
			
			PdfPCell numberofRoom = new PdfPCell(new Paragraph("Number of Room", font));
			numberofRoom.setFixedHeight(25);
			numberofRoom.setBorderColor(BaseColor.WHITE);
			numberofRoom.setHorizontalAlignment(Element.ALIGN_LEFT);
			numberofRoom.setColspan(2);
			room.addCell(numberofRoom);
			document.add(room);
			
			// 房间类型  房间数量
			PdfPTable roomType_CN = new PdfPTable(4);
			roomType_CN.setTotalWidth(400);
			roomType_CN.setWidths(new float[]{100f, 100f, 100f,100f});
			roomType_CN.setLockedWidth(true);
			
			PdfPCell roomType_CN1 = new PdfPCell(new Paragraph("房间类型：", font));
			roomType_CN1.setFixedHeight(25);
			roomType_CN1.setBorderColor(BaseColor.WHITE);
			roomType_CN1.setHorizontalAlignment(Element.ALIGN_LEFT);
			roomType_CN.addCell(roomType_CN1);
			
			PdfPCell roomType_CN2 = new PdfPCell(new Paragraph(map.get("roomType"), font));
			roomType_CN2.setFixedHeight(25);
			roomType_CN2.setBorderColor(BaseColor.WHITE);
			roomType_CN2.setHorizontalAlignment(Element.ALIGN_LEFT);
			roomType_CN.addCell(roomType_CN2);
			
			PdfPCell numberofRoom_EN1 = new PdfPCell(new Paragraph("房间数量：", font));
			numberofRoom_EN1.setFixedHeight(25);
			numberofRoom_EN1.setBorderColor(BaseColor.WHITE);
			numberofRoom_EN1.setHorizontalAlignment(Element.ALIGN_LEFT);
			roomType_CN.addCell(numberofRoom_EN1);
			
			PdfPCell numberofRoom_EN2 = new PdfPCell(new Paragraph(map.get("numberOfRoom"), font));
			numberofRoom_EN2.setFixedHeight(25);
			numberofRoom_EN2.setBorderColor(BaseColor.WHITE);
			numberofRoom_EN2.setHorizontalAlignment(Element.ALIGN_LEFT);
			roomType_CN.addCell(numberofRoom_EN2);
			
			document.add(roomType_CN);
			// 空格
			document.add(img_p_temp);
			
			
			// Room Rate  pay
			PdfPTable roomRate = new PdfPTable(4);
			roomRate.setTotalWidth(400);
			roomRate.setWidths(new float[]{100f, 100f, 100f,100f});
			roomRate.setLockedWidth(true);
			
			PdfPCell roomRateEn1 = new PdfPCell(new Paragraph("Room Rate", font));
			roomRateEn1.setFixedHeight(25);
			roomRateEn1.setBorderColor(BaseColor.WHITE);
			roomRateEn1.setHorizontalAlignment(Element.ALIGN_LEFT);
			roomRateEn1.setColspan(4);
			roomRate.addCell(roomRateEn1);
			
			document.add(roomRate);
			
			// Room Rate  pay
			PdfPTable roomRate_CN = new PdfPTable(4);
			roomRate_CN.setTotalWidth(400);
			roomRate_CN.setWidths(new float[]{100f, 100f, 100f,100f});
			roomRate_CN.setLockedWidth(true);
			
			PdfPCell roomRate_CN1 = new PdfPCell(new Paragraph("房价:", font));
			roomRate_CN1.setFixedHeight(25);
			roomRate_CN1.setBorderColor(BaseColor.WHITE);
			roomRate_CN1.setHorizontalAlignment(Element.ALIGN_LEFT);
			roomRate_CN.addCell(roomRate_CN1);
			
			PdfPCell roomRate_CN2 = new PdfPCell(new Paragraph(map.get("roomRate"), font));
			roomRate_CN2.setFixedHeight(25);
			roomRate_CN2.setBorderColor(BaseColor.WHITE);
			roomRate_CN2.setHorizontalAlignment(Element.ALIGN_LEFT);
			roomRate_CN.addCell(roomRate_CN2);
			
			PdfPCell roomRate_CN3 = new PdfPCell(new Paragraph("付款方式：", font));
			roomRate_CN3.setFixedHeight(25);
			roomRate_CN3.setBorderColor(BaseColor.WHITE);
			roomRate_CN3.setHorizontalAlignment(Element.ALIGN_LEFT);
			roomRate_CN.addCell(roomRate_CN3);
			
			PdfPCell roomRate_CN4 = new PdfPCell(new Paragraph(map.get("payMent"), font));
			roomRate_CN4.setFixedHeight(25);
			roomRate_CN4.setBorderColor(BaseColor.WHITE);
			roomRate_CN4.setHorizontalAlignment(Element.ALIGN_LEFT);
			roomRate_CN.addCell(roomRate_CN4);
			
			document.add(roomRate_CN);
			// 空格
			document.add(img_p_temp);
			
			
			// 备注
			PdfPTable remarks_EN = new PdfPTable(4);
			remarks_EN.setTotalWidth(400);
			remarks_EN.setWidths(new float[]{100f, 100f, 100f,100f});
			remarks_EN.setLockedWidth(true);
			
			PdfPCell remarks_EN1 = new PdfPCell(new Paragraph("Remarks", font));
			remarks_EN1.setFixedHeight(25);
			remarks_EN1.setBorderColor(BaseColor.WHITE);
			remarks_EN1.setHorizontalAlignment(Element.ALIGN_LEFT);
			remarks_EN1.setColspan(4);
			remarks_EN.addCell(remarks_EN1);
			document.add(remarks_EN);
			
			// 备注
			PdfPTable remarks_CN = new PdfPTable(4);
			remarks_CN.setTotalWidth(400);
			remarks_CN.setWidths(new float[]{100f, 100f, 100f,100f});
			remarks_CN.setLockedWidth(true);
			
			PdfPCell remarks_CN1 = new PdfPCell(new Paragraph("备注："+map.get("channelCode")+"客人", font));
			remarks_CN1.setFixedHeight(25);
			remarks_CN1.setBorderColor(BaseColor.WHITE);
			remarks_CN1.setHorizontalAlignment(Element.ALIGN_LEFT);
			remarks_CN1.setColspan(4);
			remarks_CN.addCell(remarks_CN1);
			document.add(remarks_CN);
			
			String remarks = map.get("remarks");
			if(StringUtils.hasText(remarks)){
				// 预定备注
				PdfPTable remarks_bookIng = new PdfPTable(4);
				remarks_bookIng.setTotalWidth(400);
				remarks_bookIng.setWidths(new float[]{100f, 100f, 100f,100f});
				remarks_bookIng.setLockedWidth(true);
				
				PdfPCell remarks_bookIng1 = new PdfPCell(new Paragraph(remarks, font));
				remarks_bookIng1.setFixedHeight(60);
				remarks_bookIng1.setBorderColor(BaseColor.WHITE);
				remarks_bookIng1.setHorizontalAlignment(Element.ALIGN_LEFT);
				remarks_bookIng1.setColspan(4);
				remarks_bookIng.addCell(remarks_bookIng1);
				document.add(remarks_bookIng);
				
			}
			
			// 酒店确认人签字：
			PdfPTable confirm = new PdfPTable(4);
			confirm.setTotalWidth(400);
			confirm.setWidths(new float[]{100f, 100f, 100f,100f});
			confirm.setLockedWidth(true);
			
			PdfPCell confirm1 = new PdfPCell(new Paragraph("酒店确认人签字：", font));
			confirm1.setFixedHeight(25);
			confirm1.setBorderColor(BaseColor.WHITE);
			confirm1.setHorizontalAlignment(Element.ALIGN_LEFT);
			confirm.addCell(confirm1);
			
			PdfPCell confirm2 = new PdfPCell(new Paragraph("", font));
			confirm2.setUseBorderPadding(true);
			confirm2.setFixedHeight(25);
			confirm2.setBorderColor(BaseColor.WHITE);
			confirm2.setHorizontalAlignment(Element.ALIGN_LEFT);
			confirm2.setColspan(2);
			confirm2.setBorderWidthBottom(2f);
			confirm.addCell(confirm2);
			
			PdfPCell confirm3 = new PdfPCell(new Paragraph("", font));
			confirm3.setFixedHeight(25);
			confirm3.setBorderColor(BaseColor.WHITE);
			confirm3.setHorizontalAlignment(Element.ALIGN_LEFT);
			confirm.addCell(confirm3);
			document.add(confirm);
			
			// 说明1
			PdfPTable message1 = new PdfPTable(4);
			message1.setTotalWidth(400);
			message1.setWidths(new float[]{100f, 100f, 100f,100f});
			message1.setLockedWidth(true);
			
			PdfPCell message1_1 = new PdfPCell(new Paragraph("此通知仅作为酒店备查，客人订单详细信息请登录畅联系统查询，如客人订单发生改变，以接口下传或畅联系统中内容为准。", font));
			message1_1.setFixedHeight(40);
			message1_1.setBorderColor(BaseColor.WHITE);
			message1_1.setHorizontalAlignment(Element.ALIGN_LEFT);
			message1_1.setColspan(4);
			message1.addCell(message1_1);
			document.add(message1);
			
			// 说明2
			PdfPTable message2 = new PdfPTable(4);
			message2.setTotalWidth(400);
			message2.setWidths(new float[]{100f, 100f, 100f,100f});
			message2.setLockedWidth(true);
			
			PdfPCell message2_1 = new PdfPCell(new Paragraph("西软信用住用户：在录入阿里信用住(不包括现付和预付)订单时，请必须录入以下信息：1.预订类型：后付预订2.渠道：畅连预订3.来源：畅联预定4.预订号：在附加信息/网络/预订号：填写CCM中央预订号15位+TAOBAO，避免客人离店后酒店收不到费用", font));
			message2_1.setFixedHeight(50);
			message2_1.setBorderColor(BaseColor.WHITE);
			message2_1.setHorizontalAlignment(Element.ALIGN_LEFT);
			message2_1.setColspan(4);
			message2.addCell(message2_1);
			document.add(message2);
			
			// 说明3
			PdfPTable message3 = new PdfPTable(4);
			message3.setTotalWidth(400);
			message3.setWidths(new float[]{100f, 100f, 100f,100f});
			message3.setLockedWidth(true);
			
			PdfPCell message3_1 = new PdfPCell(new Paragraph("订单信息来源：CHINAonline畅联", font));
			message3_1.setFixedHeight(25);
			message3_1.setBorderColor(BaseColor.WHITE);
			message3_1.setHorizontalAlignment(Element.ALIGN_LEFT);
			message3_1.setColspan(4);
			message3.addCell(message3_1);
			document.add(message3);
			
			// 说明4
			PdfPTable message4 = new PdfPTable(4);
			message4.setTotalWidth(400);
			message4.setWidths(new float[]{100f, 100f, 100f,100f});
			message4.setLockedWidth(true);
			
			PdfPCell message4_1 = new PdfPCell(new Paragraph("请将此确认回传至畅联传真： 010-59320666 或回复酒店确认号邮件至Email： col-ccmts.list@shijinet.com.cn", font));
			message4_1.setFixedHeight(40);
			message4_1.setBorderColor(BaseColor.WHITE);
			message4_1.setHorizontalAlignment(Element.ALIGN_LEFT);
			message4_1.setColspan(4);
			message4.addCell(message4_1);
			document.add(message4);
			
			// 说明5
			PdfPTable message5 = new PdfPTable(4);
			message5.setTotalWidth(400);
			message5.setWidths(new float[]{100f, 100f, 100f,100f});
			message5.setLockedWidth(true);
			
			PdfPCell message5_1 = new PdfPCell(new Paragraph("联系电话： 13601391511", font));
			message5_1.setFixedHeight(25);
			message5_1.setBorderColor(BaseColor.WHITE);
			message5_1.setHorizontalAlignment(Element.ALIGN_LEFT);
			message5_1.setColspan(4);
			message5.addCell(message5_1);
			document.add(message5);
			
			// 空格
			document.add(img_p_temp);
			
			// 说明5
			PdfPTable message6 = new PdfPTable(4);
			message6.setTotalWidth(400);
			message6.setWidths(new float[]{100f, 100f, 100f,100f});
			message6.setLockedWidth(true);
			
			PdfPCell message6_1 = new PdfPCell(new Paragraph("Best Regards! 请尽快回传确认", font));
			message6_1.setFixedHeight(25);
			message6_1.setBorderColor(BaseColor.WHITE);
			message6_1.setHorizontalAlignment(Element.ALIGN_CENTER);
			message6_1.setColspan(4);
			message6.addCell(message6_1);
			document.add(message6);
			
			document.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	
	/**
	 * -1:传真提交失败 0:传真提交成功 -3:身份认证失败
	 */
	public static void sendFax() {
		String msgid = "interface-" + userid + "-" + System.currentTimeMillis();
		String posturl = "http://api.gfax.cn:8088/sendfax/" + format;
		FaxUtil allcom = new FaxUtil(userid, pass, FaxUtil.MD5,deskey);

		Map<String, String> datamap = new Hashtable<String, String>();
		datamap.put("subject", "021-20826806");
//		datamap.put("subject", "021-20826806");
		datamap.put("precision", "photo_low");

		Map<String, String> map = new HashMap<String, String>();
		map.put("tel", "测试");
		map.put("fax", "021-20826806");
		map.put("date", "测试");
		map.put("reservationNo", "测试");
		map.put("guestName", "测试");
		map.put("guestNameEn", "c.c");
		map.put("checkInDate", "测试");
		map.put("checkOutDate", "2016-11-12");
		map.put("roomType", "测试");
		map.put("numberOfRoom", 3 + "");
		map.put("remarks", "测试");
		map.put("roomRate", "test");
		map.put("payMent", "pay");
		map.put("channelCode", "测试");
	    Locale.setDefault(new Locale("zh","CN")); 
//	    String htmlstr = getContent(map); 
	    List<String> filelist = new ArrayList<String>();
	    String path = "c:/";
    	String name1 = "temp1231.pdf";
	    html2pdf(map,path+name1);
	    filelist.add(path+name1);
//	    for(int i=0;i<20;i++){
//	    	
//	    	String path = "c:/";
//	    	String name = "temp123"+i+".png";
//	    	HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
//	    	imageGenerator.loadHtml(htmlstr);
//	    	imageGenerator.getBufferedImage();
//	    	Dimension dimension = new Dimension(400, 400);
//	    	imageGenerator.setSize(dimension);
//	    	imageGenerator.saveAsImage(path+name);
//	    	
////		String content_send = getContent(map);*
//	    	
////		datamap.put("body", content_send);
//	    	datamap.put("msgid", msgid);
//	    	
//	    	filelist.add(path+name);
//		}

	String result = allcom.sendFax(posturl, datamap, filelist);
//		File file = new File(path+name1);
//		if(file.isFile()){
//			file.delete();
//		}
	JSONObject json = JSONObject.parseObject(result);
	System.out.println(result);
	System.out.println(json.get("result"));
	}
	
	public static String getContent(Map<String, String> map) {
		String content_send="";
			content_send = "<html ><head><meta charset='UTF-8'> </head><body><table style='width:520;font-size:12px;border-spacing:0px;margin:0px;'>"
					+"<tr><td colspan='4'></td><td colspan='2'><img width='160' height='50' src='file:///'></img></td></tr>"
					+"<tr><td width='80'>"+EfaxType.email_content_view1+"</td><td width='80' align='left' colspan='4'>"+map.get("tel")+"</td></tr>"
					+"<tr><td></td><td></td><td rowspan='3' colspan='4'>"+EfaxType.email_content_view6+"</td><td></td><td></td></tr>"
					+"<tr><td >"+EfaxType.email_content_view2+"</td><td>"+map.get("fax")+"</td></tr>"
					+"<tr><td>Date</td><td >"+map.get("date")+"</td></tr>"
					+"<tr><td colspan='6'><hr width='100%' size='1'/></td></tr>"
					+"<tr><td colspan='6'>"+EfaxType.email_content_view9+"</td></tr>"
					+"<tr><td >"+EfaxType.email_content_view10+"</td><td colspan='4' align='left'>"+map.get("reservationNo")+"</td></tr>"
					+"<tr><td colspan='6'>"+EfaxType.email_content_view5+"</td></tr>"
					+"<tr><td>"+EfaxType.email_content_view11+"</td><td colspan='5'>"+map.get("guestNameEn")+"</td></tr>"
					+"<tr><td>"+EfaxType.email_content_view12+"</td><td colspan='5'>"+map.get("guestName")+"</td></tr>"
					+"<tr><td>"+EfaxType.email_content_view14+"</td><td colspan='2'></td><td colspan='2'>"+EfaxType.email_content_view20+"</td><td></td></tr>"
					+"<tr><td>"+EfaxType.email_content_view15+"</td><td colspan='2' align='left'>"+map.get("checkInDate")+"</td><td>"+EfaxType.email_content_view19+"</td><td colspan='2' align='left'>"+map.get("checkOutDate")+"</td></tr>"
					+"<tr><td>"+EfaxType.email_content_view16+"</td><td colspan='2'></td><td colspan='2'>"+EfaxType.email_content_view21+"</td><td ></td></tr>"
					+"<tr><td>"+EfaxType.email_content_view17+"</td><td colspan='2'>"+map.get("roomType")+"</td><td>"+EfaxType.email_content_view18+"</td><td colspan='2' align='left'>"+map.get("numberOfRoom")+"</td></tr>"
					+"<tr><td>"+EfaxType.email_content_view31+"</td><td colspan='5'></td></tr>"
					+"<tr><td>"+EfaxType.email_content_view32+"</td><td colspan='2'>"+map.get("roomRate")+"</td><td>"+EfaxType.email_content_view33+"</td><td colspan='2' align='left'>"+map.get("payMent")+"</td></tr>"
					+"<tr><td>"+EfaxType.email_content_view22+"</td><td colspan='5'></td></tr>"
					+"<tr><td>"+EfaxType.email_content_view23+"</td><td colspan='5'>"+map.get("channelCode")+"</td></tr>"
					+"<tr><td colspan='6'>"+map.get("remarks")+"</td></tr>"
					+"<tr><td width='120' colspan='2'>"+EfaxType.email_content_view24+"</td><td colspan='2'><hr align='left' /></td><td></td><td></td></tr>"
					+"<tr><td colspan='6'>"+EfaxType.email_content_view25+EfaxType.email_content_view26+EfaxType.email_content_view27+EfaxType.email_content_view28+"</td></tr>"
					+"<tr><td colspan='6'><hr width='100%' size='1'/></td></tr>"
					+"<tr><td colspan='6' align='center'>"+EfaxType.email_content_view30+"</td></tr>"
					+"</table></body></html>";
		return content_send;
	}
	
}
