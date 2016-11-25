package com.ccm.api.service.order.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.dao.log.HotelReceivepmsDao;
import com.ccm.api.dao.log.ReceivePmsLogDao;
import com.ccm.api.dao.shijiCare.ShijiCareDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.log.HotelReceivepms;
import com.ccm.api.model.log.ReceivePmsLog;
import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.order.HotelReceivepmsManager;
import com.ccm.api.service.sms.SmsManager;
import com.ccm.api.service.system.ShijiCareCaseManage;
import com.ccm.api.util.DateUtil;

@Service("hotelReceivepmsManager")
public class HotelReceivepmsManagerImpl extends GenericManagerImpl<HotelReceivepms, String> implements HotelReceivepmsManager {

	@Autowired
	private HotelReceivepmsDao hotelReceivepmsDao;
	@Autowired
	private HotelMCDao hotelMCDao;
	
	@Autowired
	private EmailManager emailManager;
	@Autowired
	private SmsManager smsManager;
	
	@Autowired
	private ShijiCareCaseManage shijiCareCaseManage;
	@Autowired
	private ShijiCareDao shijiCareDao;
	
	@Autowired
	private ReceivePmsLogDao receivePmsLogDao;
	
	/**
	 * 关闭酒店case，开启监控
	 * @param hotelcode
	 * @param hotelid
	 */
	private void closeCase(String hotelcode,String hotelid){
		/*
		 * 酒店活跃，关闭改酒店接口断开时创建的case
		 */
		ShijiCare shijiCare = new ShijiCare();
		shijiCare.setAssignTo(shijiCare.getProblemAssignTo(ShijiCare.PROBLEMTYPE_PMSINTEFACE));
		shijiCare.setHotelCode(hotelcode);
		shijiCare.setStatus("SUCCESS");
		shijiCare.setIsclose(false);
		List<ShijiCare> shijiCareList = shijiCareDao.shijiCareList(shijiCare);
		Boolean flag = false;
		for(ShijiCare s :shijiCareList){
			/*
			 * 关闭shijicare
			 */
			Boolean result = shijiCareCaseManage.closeCase(s.getCareId(), s.getResultMsg());
			if(result){
				flag=result;
			}
		}
		if(flag){
			/*
			 * 开启酒店监控
			 */
			Hotel hotel = new Hotel();
			hotel.setHotelId(hotelid);
			hotel.setIsPMSHeartBeat(true);
			hotelMCDao.updateHotelPMSHeartBeat(hotel);
		}
	}
	
	@Override
	public void deleteHotelReceivepmsByIds() {
		Map<String,String> hotelcodeMap = new HashMap<String, String>();
		/**
		 * 所有活跃的酒店
		 */
		List<ReceivePmsLog> receivePmsLogList = receivePmsLogDao.getOnReceivePmsLog();
		
		for(ReceivePmsLog receivePmsLog:receivePmsLogList){
			hotelcodeMap.put(receivePmsLog.getHotelCode(), receivePmsLog.getHotelCode());
			closeCase(receivePmsLog.getHotelCode(),receivePmsLog.getHotelId());
		}
		
		/*
		 * 还在监控中，酒店由断开变为活跃
		 */
		List<HotelReceivepms> hotelReceivepmsList = hotelReceivepmsDao.getlive();
		/*
		 * 活跃的酒店id
		 */
		List<String> list = new ArrayList<String>();
		
		for(HotelReceivepms hotelReceivepms:hotelReceivepmsList){
			list.add(hotelReceivepms.getHotelId());
			/**
			 * 
			 */
			if(hotelcodeMap.get(hotelReceivepms.getHotelCode())==null){
				closeCase(hotelReceivepms.getHotelCode(),hotelReceivepms.getHotelId());
			}
			
		}
		/**
		 * 轮询大于等于三次的数据
		 */
		List<String> hotelidList = hotelReceivepmsDao.getHotelIdS(3);
		List<Hotel> hotelList = hotelMCDao.getAllHoteleRemind();
		List<String> ids = new ArrayList<String>();
		for(Hotel h : hotelList){
			if(!h.getIsPMSHeartBeat()){
				ids.add(h.getHotelId());
			}
		}
		list.addAll(hotelidList);
		list.addAll(ids);
		if(list!=null && !list.isEmpty()){
			hotelReceivepmsDao.deleteHotelReceivepmsByHotelIds(list);
		}
		
	}

	@Override
	public void addOrupdateHotelReceivepms(List<HotelReceivepms> hotelReceivepmsList) {
		List<HotelReceivepms> hotelReceivepms_db = hotelReceivepmsDao.getAllHotelReceivepms();
		
		List<HotelReceivepms> hotelReceivepms_add = new ArrayList<HotelReceivepms>();
		List<HotelReceivepms> hotelReceivepms_update = new ArrayList<HotelReceivepms>();
		
		Map<String,HotelReceivepms> map = new HashMap<String, HotelReceivepms>();
		
		for(HotelReceivepms h :hotelReceivepms_db){
			map.put(h.getHotelId(), h);
		}
		Date createdTime = new Date();
		for(HotelReceivepms h:hotelReceivepmsList){
			HotelReceivepms hotelReceivepms = map.get(h.getHotelId());
			if(hotelReceivepms!=null){
				hotelReceivepms.setNumber(hotelReceivepms.getNumber()+1);
				hotelReceivepms.setSpaceSec(h.getSpaceSec());
				hotelReceivepms.setLastModifyTime(createdTime);
				hotelReceivepms_update.add(hotelReceivepms);
			}else{
				h.setNumber(1);
				h.setCreatedTime(createdTime);
				hotelReceivepms_add.add(h);
			}
		}
		for(HotelReceivepms h:hotelReceivepms_add){
			hotelReceivepmsDao.save(h);
		}
		for(HotelReceivepms h:hotelReceivepms_update){
			hotelReceivepmsDao.updateHotelReceivepms(h);
		}
	}

	@Override
	public List<HotelReceivepms> getIsRemindHotelReceivepms() {
		return hotelReceivepmsDao.getIsRemindHotelReceivepms();
	}

	@Override
	public void reminder() {
		List<HotelReceivepms> hotelReceivepmsList = hotelReceivepmsDao.getAllHotelReceivepms();
		for(HotelReceivepms hotelReceivepms:hotelReceivepmsList){
			HotelVO hotel = hotelMCDao.gethotelRemindInfo(hotelReceivepms.getHotelId());
			//发送sms
			Date effectiveTime = hotel.getEffectiveTime();
			Date expireTime = hotel.getExpireTime();
			
			//转换提日期输出格式
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			dateFormat.format(new Date());
			SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");//格式化类型
			String time = format2.format(new Date());
			Date now = null;
			try {
				now = format2.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Boolean isSendSms=false;
			//发送短信的时候必须在发送时间区间内
			if(hotelReceivepms.getNumber()<3&&StringUtils.hasLength(hotel.getSms()) && effectiveTime!=null && expireTime!=null && effectiveTime.before(now) && expireTime.after(now)&&hotel.getIsPMSHeartBeat()){
				sendSms2HotelOff(hotel.getHotelCode(),hotel.getHotelName(), hotel.getSms());
				isSendSms = true;
			}
			//配置了邮件提醒
			if(hotelReceivepms.getNumber()<3&&StringUtils.hasLength(hotel.getRemindEmail())&&hotel.getIsPMSHeartBeat()){
				emailManager.sendEmail2HotelOff(hotel.getHotelCode(),hotel.getHotelName(),hotel.getRemindEmail(),SmsType.SMS_SOURCE_EMAIL_INGERFACE);
			}
			//发送shijicare
			if(hotelReceivepms.getNumber()==3&&hotel.getIsPMSHeartBeat()){
				ShijiCare sc = new ShijiCare();
				sc.setCreatedTime(new Date());
				sc.setHotelCode(hotel.getHotelCode());
				sc.setFullDescription("酒店代码："+hotel.getHotelCode()+","+hotel.getHotelName()+" 酒店接口断开,请查找原因");
				sc.setBriefDescription("酒店代码："+hotel.getHotelCode()+","+hotel.getHotelName()+" 酒店接口断开");
				sc.setSuite(ShijiCare.SUITE_COL);
				sc.setProductId(hotel.getPmsType());
				sc.setProblemType(ShijiCare.PROBLEMTYPE_PMSINTEFACE);
				sc.setAssignTo(sc.getProblemAssignTo(ShijiCare.PROBLEMTYPE_PMSINTEFACE));
				sc.setIsSendSms(isSendSms);
				//建立shiji care
				shijiCareCaseManage.newCase(sc);
				//去除监控勾选
				hotel.setIsPMSHeartBeat(false);
				hotelMCDao.updateHotelPMSHeartBeat(hotel);
				//删除表中记录
				List<String> hotelReceivepmsIds = new ArrayList<String>();
				hotelReceivepmsIds.add(hotelReceivepms.getHotelReceivepmsId());
				hotelReceivepmsDao.deleteHotelReceivepmsByIds(hotelReceivepmsIds);
			}
		}
		
	}


	/**
	 * 发送提醒sms
	 * @param hotelCode
	 * @param hotelName
	 * @param sms
	 * @return
	 */
	private String sendSms2HotelOff(String hotelCode, String hotelName, String sms) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("hotelName", hotelName);
		String resultMsg = smsManager.smsSendI18n(SmsType.SMS_TYPE_SMSREMIND, sms, map, LanguageCode.MAIN_LANGUAGE_CODE, hotelCode);
		return resultMsg;
	}

	@Override
	public void smsRemind() {
		ShijiCare shijiCare = new ShijiCare();
		shijiCare.setAssignTo(shijiCare.getProblemAssignTo(ShijiCare.PROBLEMTYPE_PMSINTEFACE));
		//1个月前的时间
		Date time = DateUtil.addMonths(new Date(), -1);
		shijiCare.setCreatedTime(time);
		shijiCare.setIsSendSms(false);//未发送过短信的case
		
		/*
		 * 最近1个月的case
		 */
		List<ShijiCare> shijiCareList = shijiCareDao.getShijiCareList(shijiCare);
		/*
		 * 所有断开的酒店
		 */
		List<ReceivePmsLog> receivePmsLogList = receivePmsLogDao.getOffReceivePmsLog();
		
		Map<String,String> hoteCodeMap = new HashMap<String, String>();
		for(ReceivePmsLog receivePmsLog :receivePmsLogList){
			hoteCodeMap.put(receivePmsLog.getHotelCode(),receivePmsLog.getHotelId() );
		}
		for(ShijiCare sc : shijiCareList){
			if(hoteCodeMap.get(sc.getHotelCode())!=null){
				HotelVO hotel = hotelMCDao.gethotelRemindInfo(hoteCodeMap.get(sc.getHotelCode()));
				sendSms2HotelOff(hotel.getHotelCode(),hotel.getHotelName(), hotel.getSms());
			}
			sc.setIsSendSms(true);
			sc.setLastModifyTime(new Date());
			shijiCareDao.updateShjicareSendSms(shijiCare);
		}
		
	}
	
}
