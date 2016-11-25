package com.ccm.api.service.block;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.opentravel.ota._2003._05.OTAHotelInvBlockNotifRQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.w3c.dom.Element;

import _0._2.fidelio.allotment.AssociatedProfiles;
import _0._2.fidelio.allotment.InventoryBlockNotification;
import _0._2.fidelio.profile.Profile;

import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.rsvtype.RsvchanBlockDao;
import com.ccm.api.dao.rsvtype.RsvtypeChannelDao;
import com.ccm.api.jms.OTAJmsManager;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.service.allotment.AllotmentManager;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.service.log.SendOTALogManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.PushDataUtil;
import com.ccm.api.util.XMLUtil;


public class AllotmentManagerTest extends BaseManagerTestCase {
	@Resource
	private AllotmentManager allotmentManager;
	
	@Resource
	private SendOTALogManager sendOTALogManager;
	
	@Resource
	private OTAJmsManager otaJmsManager;
	
	@Resource
	private RsvchanBlockDao rsvchanBlockDao;
	
	// 项目配置属性
	private Properties projectProperties;
	@Resource
	public void setProjectProperties(Properties projectProperties) {
		this.projectProperties = projectProperties;
	}

	
	public void getBlockFromCCM(){
		List<RsvtypeChannel> rcList=new Vector<RsvtypeChannel>();
		RsvtypeChannel rc1=new RsvtypeChannel();
		rc1.setRsvtypeChannelId("5c4c475dfc5e4bf29ac836cbf6a5863b");
		RsvtypeChannel rc2=new RsvtypeChannel();
		rc2.setRsvtypeChannelId("66a36f3aee034d7fbcc204b3a176c516");
		RsvtypeChannel rc3=new RsvtypeChannel();
		rc3.setRsvtypeChannelId("c");
		rcList.add(rc1);
		rcList.add(rc2);
		rcList.add(rc3);
		Map<String,List<RsvchanBlock>> rsvchanBlocksMap =rsvchanBlockDao.getBlockChannelAilable(rcList);
	
		List<RsvchanBlock> blookList=rsvchanBlocksMap.get("5c4c475dfc5e4bf29ac836cbf6a5863b");
		for(RsvchanBlock rb:blookList){
			rb.setBlockSold(5);
			rsvchanBlockDao.updateRsvchanBlockSold(rb);
		}
		System.out.println("end");
	}
	
	@Test
	@Rollback(false)
	public void importBlock(){
			List<Thread> l = new ArrayList<Thread>();
			for (int i = 0; i < 5; i++) {  
				Thread thread =  new Thread(new Runnable() {  
	                @Override  
	                public void run() {
	                	try {
		                	String message = readByNIO("c:/XMl.txt");
//		                	URL url=new URL("http://ccmtest.chinaonline.net.cn/ows/reservation?wsdl");
		                	URL url=new URL("http://ccmtest.chinaonline.net.cn/ows/reservation?wsdl");
		                	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		                	conn.setReadTimeout(30000);
		                	conn.setDoInput(true);
		                	conn.setDoOutput(true);
		                	conn.setRequestMethod("POST");
		                	conn.setRequestProperty("Content-Type","text/xml; charset=UTF-8");
		                	conn.setUseCaches(false);
		                	OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
		                	osw.write(message);
		                	osw.flush();
		                	osw.close();
		                	
		                	InputStream is=conn.getInputStream();
		                	BufferedReader br = new BufferedReader(new InputStreamReader(is));
		                	String respLine=null;
		                	while ((respLine = br.readLine()) != null) {  
		                		System.out.println(respLine);
		                	}  
		                	br.close();
		                	is.close();
		                	conn.disconnect();
	                	} catch (Exception e) {
	                		e.printStackTrace();
	                	}
	                }  
	            }); 
				l.add(thread);
	        }  
			
			for(Thread t :l){
				t.start();
				try {
					t.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	
	public void testProgram(){
		Map<String, String>  reqMap=new HashMap<String, String>();
		String message = readByNIO("D:/test/EDITALLOTMENT2.xml");
		reqMap.put("charsetName", "UTF-8");
		reqMap.put("MessageType", MessageType.ALLOTMENT);// 消息类型
		reqMap.put("Message", message);// 消息
		// 用于切面使用
		reqMap.put("interfaceName", "");
		reqMap.put("propertyName", "TESTCCM");// 酒店代码
		reqMap.put("transactionId", "6666");// 交易代码
		//map.put("status", status);
		reqMap.put("namespace", "allotment.fidelio.2.0"); // 命名空间
		reqMap.put("receiveMsgLogId", CommonUtil.generatePrimaryKey()); // 生成主键ID

		
		allotmentManager.proxyHandleAllotment(reqMap);
	}
	public void testPost(){
		try {
			String resp=PushDataUtil.postData("http://www.sina.com.cn","");
			System.out.println(resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
//	@Rollback(false)
	public void testOXI() {
		InventoryBlockNotification inventoryBlockNotification = readOXIBlock("D:/test/EDITALLOTMENT.xml");
		AssociatedProfiles associatedProfiles = inventoryBlockNotification
				.getAssociatedProfiles();
		List<Profile> associatedProfileList = associatedProfiles
				.getProfile();
		for (Profile profile : associatedProfileList) {
			profile.getMfNameCode();
		}
		try {
			String handledXML = XMLUtil
					.JAXBParseToXml(inventoryBlockNotification);
			writeByNIO(handledXML,"D:/test/CONVERTEDITALLOTMENT.xml");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
//	@Rollback(false)
	public void testOXIProfile() {
		Profile p = readOXIProfile("D:/test/Company_Profile.xml");
		p.getMfNameCode();
	}

//	@Test
//	@Rollback(false)
//	public void testOXIRes() {
//		Reservation  r = readOXIReservation("D:/test/Res1.xml");
//		ResProfiles resProfiles=r.getResProfiles();
//		List<ResProfile> profileList=resProfiles.getResProfile();
//		for(ResProfile resProfile:profileList){
//			Profile prof=resProfile.getProfile();
//			prof.getCreatorCode();
//		}
//	}
//	@Test
	public void testOTA(){
		
		OTAHotelInvBlockNotifRQ otaHotelInvBlockNotifRQ=readOTABlock("D:/test/testOTABlock.xml");
		Element ele=otaHotelInvBlockNotifRQ.getTPAExtensions().getAny().get(0);
		try {
			String handledXML = XMLUtil
			.JAXBParseToXml(otaHotelInvBlockNotifRQ);
			writeByNIO(handledXML,"D:/test/CONVERT_HotelInvBlockNotifRQ.xml");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Channel channel = channelDao.getChannelByChannelCode("TAOBAO");
		String channelId = channel.getChannelId();
		String invBlockGroupingCode = "1234567";
		String hotelCode = "TESTCCM";
		String roomType = "1BSTS";
		// Calendar invCalendar = Calendar.getInstance();
		// invCalendar.set(2015, 10, 16,0,0,0);
		// invCalendar.getTime();
		Date inventoryDate = null;
		Date cutOffDate = null;
		try {
			inventoryDate = df.parse("2015-10-17");
			cutOffDate = df.parse("2015-10-16");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Integer allotmentQty = 9;
		Integer allotmentSold = 2;
		
		List<Map<String, String>> invBlockList=new Vector<Map<String, String>> ();
		Map<String, String> otaBlockMap = new HashMap<String, String>();
		otaBlockMap.put("actionType", "NEW");
		otaBlockMap.put("hotelCode", hotelCode);
		otaBlockMap.put("chainCode",
				channel.getChannelCode());
		otaBlockMap.put("invBlockCode", invBlockGroupingCode);
		otaBlockMap.put("invBlockGroupingCode",
				invBlockGroupingCode);
		otaBlockMap.put("startDate",
				DateUtil.convertDateToString(inventoryDate));
		otaBlockMap.put("endDate",
				DateUtil.convertDateToString(inventoryDate));
		otaBlockMap.put("cutOfDate",
				DateUtil.convertDateToString(cutOffDate));
		otaBlockMap.put("roomTypeCode",roomType);
		otaBlockMap.put("numberOfUnits",String.valueOf(allotmentQty));
		invBlockList.add(otaBlockMap);
		SendOTABLockThread tendOTABLockThread=new SendOTABLockThread(invBlockList);
		OTAHotelInvBlockNotifRQ o=tendOTABLockThread.createBlockNotification(invBlockList);
		try {
			String handledXML = XMLUtil
			.JAXBParseToXml(o);
			System.out.println(handledXML);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
	}
	
	public OTAHotelInvBlockNotifRQ readOTABlock(String filePath) {
		try {
			String xml = readByNIO(filePath);
			OTAHotelInvBlockNotifRQ otaHotelInvBlockNotifRQ = (OTAHotelInvBlockNotifRQ) XMLUtil
					.JAXBParseToBean(xml, OTAHotelInvBlockNotifRQ.class,
							"UTF-8");

			return otaHotelInvBlockNotifRQ;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public InventoryBlockNotification readOXIBlock(String filePath) {
		try {
			String xml = readByNIO(filePath);
			InventoryBlockNotification inventoryBlockNotification = (InventoryBlockNotification) XMLUtil
					.JAXBParseToBean(xml, InventoryBlockNotification.class,
							"UTF-8");

			return inventoryBlockNotification;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Profile readOXIProfile(String filePath) {
		try {
			String xml = readByNIO(filePath);
			Profile p = (Profile) XMLUtil
					.JAXBParseToBean(xml,Profile.class,
							"UTF-8");

			return p;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//	public Reservation readOXIReservation(String filePath) {
//		try {
//			String xml = readByNIO(filePath);
//			Reservation res = (Reservation) XMLUtil
//					.JAXBParseToBean(xml,Reservation.class,
//							"UTF-8");
//
//			return res;
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public String readByNIO(String file) {
		// 第一步 获取通道
		StringBuffer sb = new StringBuffer();
		FileInputStream fis = null;
		FileChannel channel = null;
		try {
			fis = new FileInputStream(file);
			channel = fis.getChannel();
			// 文件内容的大小

			// 第二步 指定缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			// 第三步 将通道中的数据读取到缓冲区中
			while (true) {
				buffer.clear();
				int index = channel.read(buffer);
				if (index == -1) {
					break;
				}
				buffer.flip();
				byte[] bt = buffer.array();
				sb.append(new String(bt, 0, index));
			}
			buffer.clear();
			buffer = null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				channel.close();
				fis.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sb.toString();
	}

	public boolean writeByNIO(String content,String path) {
		// 第一步 获取通道
		FileOutputStream fos = null;
		FileChannel channel = null;
		try {
			
			File file=new File(path);
			file.createNewFile();
			fos = new FileOutputStream(file);
			channel = fos.getChannel();
			// 文件内容的大小
			int len=content.getBytes().length;
			double cycleCount=Math.ceil(len/1024);
			int cap=1024;
			for(int i=0;i<cycleCount;i++){
				if(i+1==cycleCount){
					ByteBuffer buffer = ByteBuffer.wrap(content.getBytes(),i*cap,len-i*cap);
					channel.write(buffer);
				}else{
					ByteBuffer buffer = ByteBuffer.wrap(content.getBytes(),i*cap,cap);
					channel.write(buffer);
				}
			}
			// 第二步 指定缓冲区
//			ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());
//			channel.write(buffer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				channel.close();
				fos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

		}
		return true;
	}
	

	public void testConvertaion(){
		InventoryBlockNotification inventoryBlockNotification = readOXIBlock("D:/test/ADDALLOTMENT.xml");
	}
	
	public void getBlockCode() throws Exception{
		String hotelCode= "AHHAN";
		String channelCode= "TAOBAO";
		String roomTypeCode= "1KBDS";
		String ratePlanCode= "WHL01";
		Date d1 = DateUtil.convertStringToDate("2016-01-11");
		Date d2 = DateUtil.convertStringToDate("2016-01-12");
		List<Date> dateList = DateUtil.getDayList(d1, DateUtil.addDays(d2, -1));
		List<String>dateStrList=new ArrayList<String>();
		for(Date d:dateList){
			dateStrList.add(DateUtil.convertDateToString(d));
		}
		List<RsvchanBlock>  rsvchanBlockList = rsvchanBlockDao.getIsSendToPMSRsvchanBlock(hotelCode, channelCode, dateStrList, roomTypeCode);
		Set<String> blockSet=new HashSet<String> ();
		for(Date d:dateList){
			String blockCode=null;
			inner:for(int i=0;i<rsvchanBlockList.size();i++){
				//使用block code
				if(rsvchanBlockList.get(i).getDate().equals(d)){
					if(rsvchanBlockList.get(i).getRatePlanCodes()==null){
						blockCode =  rsvchanBlockList.get(i).getBlockCode();
						break inner;
					}else{
						//使用block code
						if(rsvchanBlockList.get(i).getRatePlanCodes().contains(ratePlanCode)){
							blockCode = rsvchanBlockList.get(i).getBlockCode();
							break inner;
						}
					}
				}
			}
			if(blockCode==null){
				System.out.println("----------");
			}else{
				blockSet.add(blockCode);
			}
		}
		if(blockSet.size()==1){
			List<String> list = new ArrayList<String>(blockSet);   
//			return list.get(0);
			System.out.println(list.get(0));
		}
	}
}
