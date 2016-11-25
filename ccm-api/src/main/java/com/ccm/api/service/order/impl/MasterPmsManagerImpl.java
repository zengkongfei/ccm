package com.ccm.api.service.order.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.fax.FaxSendDao;
import com.ccm.api.dao.fax.FaxSendTimeDao;
import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.dao.master.MasterPmsDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.constant.EfaxType;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.constant.RemindStatus;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.fax.FaxSend;
import com.ccm.api.model.fax.FaxSendTime;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterPms;
import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.service.fax.FaxManager;
import com.ccm.api.service.order.MasterPmsManager;
import com.ccm.api.service.system.ShijiCareCaseManage;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
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

@Service("masterPmsManager")
public class MasterPmsManagerImpl implements MasterPmsManager {
	private final Log log = LogFactory.getLog(MasterPmsManagerImpl.class);

	@Autowired
	private MasterDao masterDao;
	@Autowired
	private HotelMCDao hotelMCDao;
	@Autowired
	private FaxManager faxManager;
	@Autowired
	private FaxSendDao faxSendDao;
	@Autowired
	private ShijiCareCaseManage shijiCareCaseManage;
	@Autowired
	private MasterPmsDao masterPmsDao;
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private FaxSendTimeDao faxSendTimeDao;
	
	@Override
	public void batchMasterPms(List<Master> masterList) {
		try {
			List<MasterPms> mpList_add = new ArrayList<MasterPms>();
			List<MasterPms> mpList_update = new ArrayList<MasterPms>();
			Map<String,MasterPms> map = new HashMap<String, MasterPms>();
			List<MasterPms> dbmpList =  masterDao.getAllMasterPms();
			
			for(MasterPms mp :dbmpList){
				map.put(mp.getMasterId(), mp);
			}
			Map<String,HotelVO> hotelMap = new HashMap<String, HotelVO>();
			Map<String,Channel> channelMap = new HashMap<String, Channel>();
			Date createdTime = new Date();
			Date creataTime = DateUtil.convertDateToDate("HH:mm:ss",createdTime);
			// 处理需要提醒的订单
			for(Master m :masterList){
				String hotelId = m.getHotelId();
				HotelVO hotel = hotelMap.get(hotelId);
				if(hotel==null){
					hotel = hotelMCDao.gethotelRemindInfo(hotelId);
				}
				if(hotel==null){
					continue;
				}
				hotelMap.put(hotelId, hotel);
				
				Channel channel =channelMap.get(m.getChannelId());
				if(channel==null){
					channel = channelDao.getChannel(m.getChannelId());
				}
				if(channel ==null){
					continue;
				}
				
				channelMap.put(m.getChannelId(), channel);
				// 排除监控时间  开始时间
				Date invalidTimeBegin = channel.getInvalidTimeBegin();
				// 排除监控时间  结束时间
				Date invalidTimeEnd = channel.getInvalidTimeEnd();
				
				if(invalidTimeBegin!=null &&invalidTimeEnd!=null){
					/**
					 * 在排除时间区间内，不处理
					 */
					if(convertTime(invalidTimeBegin,invalidTimeEnd,creataTime)){
						continue;
					}
				}
					
				//配置了提醒
				if(hotel.getIsMasterListener()){
					MasterPms mp = map.get(m.getMasterId());
					if(mp!=null){
						if(m.getSta().equalsIgnoreCase(mp.getSta())){
							mp.setNumber(mp.getNumber()+1);
						}else{
							mp.setNumber(1);
						}
						mp.setArr(m.getArr());
						mp.setSta(m.getSta());
						mpList_update.add(mp);
					}else{
						mp = new MasterPms();
						mp.setArr(m.getArr());
						mp.setHotelId(hotelId);
						mp.setMasterId(m.getMasterId());
						mp.setSta(m.getSta());
						mp.setNumber(1);
						mp.setCreatedTime(createdTime);
						mp.setChannelId(m.getChannelId());
						mpList_add.add(mp);
					}
				}
			}
			//新增
			for(MasterPms mp :mpList_add){
				masterDao.saveMasterPms(mp);
			}
			
			//修改
			for(MasterPms mp :mpList_update){
				masterDao.updateMasterPms(mp.getMasterId(), mp.getNumber(),mp.getSta(),mp.getArr());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMasterPms() {
		//新建订单，临时表中的订单有pmsId
		List<String> masterIdList_add = masterDao.getHasPmsIdMaster();
		//取消单，临时表中的订单有cancelPmsId,且cancelPmsId非默认值
		List<String> masterIdList_cancel = masterDao.getHasCancelIdMaster(RemindStatus.order_cancel);
		
		//取消单，临时表中的订单无pmsId
		List<String> masterIdList_cancel_nonepmsId = masterDao.getNonePmsIdMasterByCancel(OrderStatus.CANCEL);
		
		Date twoDayAgo = DateUtil.addDays(new Date(), -2);
		//删除两天前的数据
		List<String> twoDayAgomasterIdList = masterDao.getMasterPmsListBYCreatedTime(twoDayAgo);
		
		List<String> masterIdList = new ArrayList<String>();
		masterIdList.addAll(twoDayAgomasterIdList);
		masterIdList.addAll(masterIdList_add);
		masterIdList.addAll(masterIdList_cancel);
		masterIdList.addAll(masterIdList_cancel_nonepmsId);
		
		//删除MasterPms表中这次轮询之前已经拥有的pmsid的记录
		if(!masterIdList.isEmpty()){
			masterDao.deleteMasterPms(masterIdList);
		}
	}

	@Override
	public void remindHotel() {
		List<MasterPms> mpList =  masterDao.getAllMasterPms();
		
		/*
		 * key=hotelid
		 */
		Map<String,HotelVO> hotelMap = new HashMap<String, HotelVO>();
		/*
		 * key=fax
		 */
		Map<String,List<MasterPms>> faxMap = new HashMap<String,List<MasterPms>>();
		/*
		 * key=fax list<String> String masterid
		 */
		Map<String,List<String>> map = new HashMap<String, List<String>>();
		/*
		 * key ==hotelid
		 */
		Map<String,List<MasterPms>> shijicareMap = new HashMap<String,List<MasterPms>>();
		Date time =   DateUtil.convertDateToDate("HH:mm", new Date());
		for(MasterPms mp :mpList){
			if(mp.getNumber()>1){
				continue;
			}
			HotelVO hotel = hotelMap.get(mp.getHotelId());
			/**
			 * 过滤不存在的酒店
			 */
			if(hotel==null){
				hotel = hotelMCDao.gethotelRemindInfo(mp.getHotelId());
			}
			if(hotel==null){
				continue;
			}
			hotelMap.put(mp.getHotelId(), hotel);
			
			FaxSendTime faxSendTime = new FaxSendTime();
			faxSendTime.setTime(time);
			faxSendTime.setHotelId(hotel.getHotelId());
			List<FaxSendTime> faxSendTimeList = faxSendTimeDao.searchFaxSendTimeList(faxSendTime);
			String remindEfax="";
			/**fax 时间段配置*/
			for(FaxSendTime fs : faxSendTimeList){
				if(StringUtils.hasLength(fs.getFaxNumber())){
					remindEfax = fs.getFaxNumber();
					continue;
				}
			}
			/**fax 时间段未配置，获取默认的*/
			if(!StringUtils.hasLength(remindEfax)){
				remindEfax = hotel.getRemindEfax();
			}
			
			
			/**
			 * fax发送
			 * 将需要提醒的订单按照fax 号
			 */
			if(StringUtils.hasLength(remindEfax)){
				if(faxMap.get(remindEfax)==null){
					List<MasterPms> list = new ArrayList<MasterPms>(); 
					list.add(mp);
					faxMap.put(remindEfax, list);
				}else{
					List<MasterPms> list = faxMap.get(remindEfax); 
					list.add(mp);
				}
			}
			/**
			 * shijicare 按照酒店分组，
			 */
			if(StringUtils.hasLength(hotel.getHotelId())){
				if(shijicareMap.get(hotel.getHotelId())==null){
					List<MasterPms> list = new ArrayList<MasterPms>(); 
					list.add(mp);
					shijicareMap.put(mp.getHotelId(), list);
				}else{
					List<MasterPms> list = shijicareMap.get(hotel.getHotelId()); 
					list.add(mp);
				}
			}
		}
		
		/*
		 * 发送fax key=fax
		 */
		for(Map.Entry<String,List<MasterPms>> entry : faxMap.entrySet()){
			/*
			 * entry=fax
			 */
			List<MasterPms> list = faxMap.get(entry.getKey());
			
			List<MasterPms> ingList = new ArrayList<MasterPms>();
			List<String> masterIdList = new ArrayList<String>(); 
			for(int i=0;i<list.size();i++){
				MasterPms m = list.get(i);
				if(i>=10){
					//保存排队
					masterPmsDao.saveMasterPms2(m);
				}else{
					ingList.add(m);
					masterIdList.add(m.getMasterId());
				}
			}
			map.put(entry.getKey(), masterIdList);
			/*
			 * 每个fax号，发送一个合并的fax
			 */
			sendFax(map,0);
		}
		
		
		/*
		 * 创建case
		 */
		for (Map.Entry<String,List<MasterPms>> entry : shijicareMap.entrySet()) {
			List<MasterPms> list = shijicareMap.get(entry.getKey());
			StringBuffer sb = new StringBuffer();
			Boolean flag=false;
			for(MasterPms m :list){
				if(m.getNumber()==1){
					sb.append(m.getMasterId()+",");
					flag=true;
				}
			}
			/*
			 * 第一次的时候才发shijicare
			 */
			if(flag){
				HotelVO hotel = hotelMap.get(entry.getKey());
				ShijiCare sc = new ShijiCare();
				sc.setCreatedTime(new Date());
				sc.setHotelCode(hotel.getHotelCode());
				sc.setFullDescription("Product内容/"+"酒店代码："+hotel.getHotelCode()+" "+hotel.getHotelName()+"订单号："+sb+" PMS.ID未回传");
				sc.setBriefDescription("Product内容/"+"酒店代码："+hotel.getHotelCode()+" "+hotel.getHotelName()+" PMS.ID未回传");
				sc.setSuite(ShijiCare.SUITE_COL);
				sc.setProductId(hotel.getPmsType());
				sc.setProblemType(ShijiCare.PROBLEMTYPE_PMS_ORDER);
				sc.setAssignTo(sc.getProblemAssignTo(ShijiCare.PROBLEMTYPE_PMS_ORDER));
				
				//建立shiji care
				shijiCareCaseManage.newCase(sc);
			}
		} 
	}
	
	

	@Override
	public void remindHotel2() {
		List<MasterPms> mpList = masterPmsDao.getAllMasterPms();
		/*
		 * key=fax
		 */
		Map<String,List<MasterPms>> faxMap = new HashMap<String,List<MasterPms>>();
		/*
		 * key=fax list<String> String masterid
		 */
		Map<String,List<String>> map = new HashMap<String, List<String>>();
		/*
		 * key=hotelid
		 */
		Map<String,HotelVO> hotelMap = new HashMap<String, HotelVO>();
		Date time =   DateUtil.convertDateToDate("HH:mm", new Date());
		for(MasterPms mp :mpList){
			if(mp.getNumber()>1){
				continue;
			}
			HotelVO hotel = hotelMap.get(mp.getHotelId());
			/**
			 * 过滤不存在的酒店
			 */
			if(hotel==null){
				hotel = hotelMCDao.gethotelRemindInfo(mp.getHotelId());
			}
			if(hotel==null){
				continue;
			}
			hotelMap.put(mp.getHotelId(), hotel);
			
			FaxSendTime faxSendTime = new FaxSendTime();
			faxSendTime.setTime(time);
			faxSendTime.setHotelId(hotel.getHotelId());
			List<FaxSendTime> faxSendTimeList = faxSendTimeDao.searchFaxSendTimeList(faxSendTime);
			String remindEfax="";
			/**fax 时间段配置*/
			for(FaxSendTime fs : faxSendTimeList){
				if(StringUtils.hasLength(fs.getFaxNumber())){
					remindEfax = fs.getFaxNumber();
					continue;
				}
			}
			/**fax 时间段未配置，获取默认的*/
			if(!StringUtils.hasLength(remindEfax)){
				remindEfax = hotel.getRemindEfax();
			}
			
			/**
			 * fax发送
			 * 将需要提醒的订单按照fax 号
			 */
			if(StringUtils.hasLength(remindEfax)){
				if(faxMap.get(remindEfax)==null){
					List<MasterPms> list = new ArrayList<MasterPms>(); 
					list.add(mp);
					faxMap.put(remindEfax, list);
				}else{
					List<MasterPms> list = faxMap.get(remindEfax); 
					list.add(mp);
				}
			}
		}
		
		/*
		 * 发送fax key=fax
		 */
		for(Map.Entry<String,List<MasterPms>> entry : faxMap.entrySet()){
			/*
			 * entry=fax
			 */
			List<MasterPms> list = faxMap.get(entry.getKey());
			List<String> masterIdList = new ArrayList<String>(); 
			
			List<MasterPms> ingList = new ArrayList<MasterPms>();
			for(int i=0;i<list.size();i++){
				MasterPms m = list.get(i);
				if(i<10){
					ingList.add(m);
					masterIdList.add(m.getMasterId());
				}
			}
			map.put(entry.getKey(), masterIdList);
			/*
			 * 每个fax号，发送一个合并的fax
			 */
			sendFax(map,0);
			for(String masterId:masterIdList){
				masterPmsDao.deleteMasterPms(masterId);
			}
		}
		
		
	}
	

	/**
	 * 计算time 是否在排除区间内
	 * @param startTime
	 * @param endTime
	 * @param time
	 * @return
	 */
	private boolean convertTime(Date invalidTimeBegin,Date invalidTimeEnd,Date creataTime){
		/*
		 * 排除开始时间和结束时间相等，则全天监控
		 */
		if(invalidTimeBegin.getTime()==invalidTimeEnd.getTime()){//如：8：00-8：00
		}else if(invalidTimeBegin.before(invalidTimeEnd)){//开始时间小于结束时间 如4：00-5：00
			/*
			 * 大于开始时间，小于结束时间
			 */
			if(creataTime.after(invalidTimeBegin)&&creataTime.before(invalidTimeEnd)){//
				return true;
			}
		}else{//开始时间大于结束时间，则时间区间是跨天，如18：00-9：00  排除区间为18：00-0：00，0：00-8：00  2个区间
			// 一下三种情况均处于排除区间内
			/*
			 * 1. 开始开始时间，当前20：00
			 */
			if(creataTime.after(invalidTimeBegin)){
				return true;
			}
			/*
			 * 2 小于开始时间，小于结束时间，当前4：00
			 */
			else if(creataTime.before(invalidTimeBegin) && creataTime.before(invalidTimeEnd)){
				return true;
			}
		}
		
		return false;
	}
	
	private void html2pdf(Map<String, String> map,String filePath){
		try {
			Document document = new Document();
			PdfWriter.getInstance(document,new FileOutputStream(filePath));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  // 设置字体

		   	PdfWriter pw = PdfWriter.getInstance(document, baos);
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
			Image image =Image.getInstance(EfaxType.email_pic_path);
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

	@Override
	public void reissueFax(List<FaxSend> list) {
		Map<String,List<String>> map = new HashMap<String, List<String>>();
		
		Map<String,List<FaxSend>> faxSend_map = new HashMap<String, List<FaxSend>>();
		
		/*
		 * 将同一个消息号的FaxSend 组合在一起
		 */
		for(FaxSend faxSend :list){
			if(faxSend_map.get(faxSend.getMsgId())==null){
				List<FaxSend> l = new ArrayList<FaxSend>();
				l.add(faxSend);
				faxSend_map.put(faxSend.getMsgId(), l);
			}else{
				List<FaxSend> l = faxSend_map.get(faxSend.getMsgId());
				l.add(faxSend);
			}
		}
		
		List<FaxSend> newList = new ArrayList<FaxSend>();
		/*
		 * 发送fax key=fax
		 */
		for(Map.Entry<String,List<FaxSend>> entry : faxSend_map.entrySet()){
			String result = faxManager.queryTask(entry.getKey());
			if(!StringUtils.hasText(result)){
				continue;
			}
			JSONObject json = JSONObject.parseObject(result);
			String resultCode = json.getString("status");
			if(resultCode.equalsIgnoreCase("9")){
				JSONArray faxitem = json.getJSONArray("faxitem") ;
				JSONObject faxitemJson = faxitem.getJSONObject(0);
				String description = faxitemJson.getString("description");
				if(!description.equalsIgnoreCase("成功")){//不是发送给成功，例如：占线
					resultCode = "-1";
				}
			}
			for(FaxSend f:faxSend_map.get(entry.getKey())){
				f.setFaxStatus(resultCode);
				f.setFaxMsg(result);
				newList.add(f);
			}
		}
		
		for(FaxSend faxSend :newList){
			String resultCode = faxSend.getFaxStatus();
			if(resultCode.equalsIgnoreCase("0") ||//接受任务
					resultCode.equalsIgnoreCase("1") ||//正在初始化
					resultCode.equalsIgnoreCase("3") ||//等待发送
					resultCode.equalsIgnoreCase("4") ||//正在发送
					resultCode.equalsIgnoreCase("5")//任务暂停
					){
				continue;
			}
			/*
			 * 发送打印失败，重试1次
			 * 
			 */
			if(!resultCode.equalsIgnoreCase("9")){
				String faxNumber = faxSend.getFaxNumber();
				String masterId = faxSend.getBizId();
				List<String> masterIdList = map.get(faxNumber);
				if(masterIdList==null){
					masterIdList = new ArrayList<String>();
				}
				map.put(faxNumber, masterIdList);
				/*
				 * 大于10的时候，等下一次处理
				 */
				if(masterIdList.size()==10){
					continue;
				}
				masterIdList.add(masterId);
				faxSend.setNumber(faxSend.getNumber()+1);
			}
			faxSendDao.save(faxSend);
		}
		sendFax(map,1);
	}

	
	/**
	 * 按照fax号，将相同的fax号的fax合并，再次发送fax
	 */
	private void sendFax(Map<String,List<String>> masterIdMap,int number){
		Date now = new Date();
		String date = DateUtil.convertDateToString(now);
		
		for(Map.Entry<String,List<String>> entry : masterIdMap.entrySet()){
			String resultCode = null;
			String resultMsg = null;
			String msgid = null;
			List<String>  filelist = new ArrayList<String>();
			List<FaxSend>  faxSendlist = new ArrayList<FaxSend>();
			List<String> masterIdList = masterIdMap.get(entry.getKey());
			/*
			 * key=hotelid
			 */
			Map<String,HotelVO> hotelMap = new HashMap<String, HotelVO>();
			try {
				/*
				 * 发送第一批次的10个合成1个的fax
				 */
				for(String masterId:masterIdList){
					FaxSend faxSend = new FaxSend();
					faxSend.setFaxNumber(entry.getKey());
					faxSend.setFaxType(SmsType.SMS_TYPE_EFAX);
					faxSend.setVerifyCode(LanguageCode.MAIN_LANGUAGE_CODE);
					faxSend.setBizId(masterId);
					faxSend.setSendTime(now);
					faxSend.setNumber(number);
					faxSendlist.add(faxSend);
					
					Master m = masterDao.getMasterOrderByOrderId(masterId);
					
					HotelVO hotel = hotelMap.get(m.getHotelId());
					/**
					 * 过滤不存在的酒店
					 */
					if(hotel==null){
						hotel = hotelMCDao.gethotelRemindInfo(m.getHotelId());
					}
					if(hotel==null){
						continue;
					}
					hotelMap.put(m.getHotelId(), hotel);
					
					Map<String,String> mapParam = new HashMap<String, String>();
					mapParam.put("tel",  !StringUtils.hasText(hotel.getHotelName())?" ":hotel.getHotelName() );
					mapParam.put("fax", hotel.getRemindEfax());
					mapParam.put("date", date);
					mapParam.put("reservationNo", m.getMasterId());
					mapParam.put("guestName", !StringUtils.hasText(m.getName4())?" ":m.getName4());
					mapParam.put("guestNameEn", m.getName()+" "+m.getName2() );
					mapParam.put("checkInDate",  DateUtil.convertDateToString(m.getArr()));
					mapParam.put("checkOutDate", DateUtil.convertDateToString(m.getDep()));
					mapParam.put("roomType", m.getType());
					mapParam.put("numberOfRoom", m.getNumberOfUnits()+"");
					mapParam.put("remarks",  !StringUtils.hasText(m.getRef())?" ":m.getRef());
					mapParam.put("roomRate", m.getCharge().toString());
					mapParam.put("payMent", m.getPayment());
					mapParam.put("channelCode", m.getChannel());
					String filePath =EfaxType.email_pic_dir+"/"+"temp"+CommonUtil.generatePrimaryKey()+".pdf"; 
					html2pdf(mapParam,filePath);
					filelist.add(filePath);
				}
				msgid = "interface-"+"chinaonline@gfax.cn"+"-"+System.currentTimeMillis();  
				String result = faxManager.sendFax(entry.getKey(), filelist,msgid);
				
				JSONObject json = JSONObject.parseObject(result);
				resultCode = json.getString("result");
				if("-1".equalsIgnoreCase(resultCode)){
					resultMsg = "传真提交失败"+result;
				}else if("0".equalsIgnoreCase(resultCode)){
					resultMsg ="传真提交成功"+result;
				}else if("-3".equalsIgnoreCase(resultCode)){
					resultMsg ="身份认证失败"+result;
				}
			} catch (Exception e) {
				log.error(e);
				String msg = CommonUtil.getExceptionMsg(e, new String[] { "ccm" });
				if (msg.length() > 200) {
					msg = msg.substring(0, 200);
				}
				resultMsg = msg;
				resultCode = "-99";
			}finally{
				 /*
			     * 删除生成的pdf
			     */
				for(String filePath :filelist){
					File file = new File(filePath);
					if(file.isFile()){
						file.delete();
					}
				}
			}
			for(FaxSend faxSend:faxSendlist){
				faxSend.setMsgId(msgid);
				faxSend.setResultCode(resultCode);
				faxSend.setResultMsg(resultMsg);
				faxSendDao.saveFaxSend(faxSend);
			}
		}
	}
	
}
