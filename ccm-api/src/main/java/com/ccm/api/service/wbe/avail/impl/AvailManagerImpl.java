package com.ccm.api.service.wbe.avail.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.hotel.HotelCancelDao;
import com.ccm.api.dao.hotel.HotelGuaranteeDao;
import com.ccm.api.dao.hotel.HotelPackageDao;
import com.ccm.api.dao.ratePlan.RateCancelRelationshipDao;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.dao.ratePlan.RateGuaranteeRelationshipDao;
import com.ccm.api.dao.ratePlan.RestrictionCalcDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.constant.WBEConstant;
import com.ccm.api.model.enume.ProfileType;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.HotelCancelVO;
import com.ccm.api.model.hotel.vo.HotelGuaranteeVO;
import com.ccm.api.model.hotel.vo.HotelPackageVO;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RateCancelRelationship;
import com.ccm.api.model.ratePlan.RateCustomRelationship;
import com.ccm.api.model.ratePlan.RateGuaranteeRelationship;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.wbe.vo.query.AvailRoomVO;
import com.ccm.api.service.availability.AvailabilityManager;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.ratePlan.CancelPolicyManager;
import com.ccm.api.service.ratePlan.GuaranteePolicyManager;
import com.ccm.api.service.ratePlan.PackageManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.ratePlan.RateGuaranteeManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.service.wbe.avail.AvailManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("availManager")
public class AvailManagerImpl extends GenericManagerImpl<AvailRoomVO, String> implements AvailManager{
	
	private Log log = LogFactory.getLog(AvailManagerImpl.class);

	@Autowired
	private RestrictionCalcDao restrictionCalcDao;
	
	@Autowired
	private PriceCalcManager priceCalcManager;
	
	@Autowired
	private ChannelgoodsManager channelgoodsManager;
	
	@Autowired
	private RateCustomRelationshipDao rateCustomRelationshipDao;
	
	@Autowired
    private RsvtypeChannelManager rsvtypeChannelManager;
	
	@Autowired
	private RoomTypeManager roomTypeManager;
	
	@Autowired
	private RatePlanManager ratePlanManager;
	
	@Autowired
	private PackageManager packageManager;
	
	@Autowired
	private RateGuaranteeRelationshipDao rateGuaranteeRelationshipDao;
	
	@Autowired
	private RateCancelRelationshipDao rateCancelRelationshipDao;
	
	@Autowired
	private GuaranteePolicyManager guaranteePolicyManager;
	
	@Autowired
	private RateGuaranteeManager rateGuaranteePolicyManager;
	
	@Autowired
	private HotelPackageDao hotelpackageDao;
	
	@Autowired
	private HotelGuaranteeDao hotelGuaranteeDao;
	
	@Autowired
	private HotelCancelDao hotelCancelDao;
	
	@Autowired
	private CancelPolicyManager cancelPolicyManager;
	
	@Autowired
	private PictureManager pictureManager;// 图片信息
	
	@Resource
	private ChannelManager channelManager;//
	@Resource
	private AvailabilityManager availabilityManager;
	
	
	/**
	 * 房型为单位的查询
	 */
	@Override
	public List<RoomTypeVO> queryRoomTypeVoList(List<PriceCalc> priceCalcList, AvailRoomVO availRoomVO) throws Exception{
		List<RoomTypeVO> roomTypeVOList = new ArrayList<RoomTypeVO>();
		
		try {
			long s1 = System.currentTimeMillis();
			if(priceCalcList==null||priceCalcList.size()==0){
				return null;
			}
			
			//用来去除房型重复
			Map<String, String> roomMap = new HashMap<String, String>();
			
			//循环遍历房价日历信息
			for (PriceCalc priceCalc : priceCalcList) {
				//如果已存在房型Map中
				if(roomMap.containsKey(priceCalc.getRoomTypeCode())){
					//循环房型列表
					for (RoomTypeVO rtvo : roomTypeVOList) {
						//如果对应上了房型
						if(rtvo.getRoomTypeCode().equals(priceCalc.getRoomTypeCode())){
							boolean flag = false;
							for (RatePlanVO rpvo : rtvo.getRateplanVoList()) {
								//如果房价代码对应上了
								if(rpvo.getRp().getRatePlanCode().equals(priceCalc.getRatePlanCode())){
									//添加房价日历
									rpvo.getPriceCalcList().add(priceCalc);
									flag = true;
								}
							}
							//如果没有在当前房型下找到房价
							if(!flag){
								//获取房价VO
								RatePlanVO ratePlanVo = 
									ratePlanManager.getRatePlanI18nByCodeHotelId(
											priceCalc.getRatePlanCode(), availRoomVO.getHotelId(), availRoomVO.getLanguage());
								
								if(ratePlanVo!=null){
									ratePlanVo.getPriceCalcList().add(priceCalc);
									rtvo.getRateplanVoList().add(ratePlanVo);
								}
							}
						}
					}	
				}else{
					roomMap.put(priceCalc.getRoomTypeCode(), "");  //添加房型代码到Map中，用来下次区分重复
					//获取房型
					RoomTypeVO roomTypeVo = 
						roomTypeManager.getRoomTypeByHotelCode(
								priceCalc.getRoomTypeCode(), availRoomVO.getHotelCode(),availRoomVO.getLanguage());
					//如果房型未找到
					if(roomTypeVo!=null){
						
						//获取房价VO
						RatePlanVO ratePlanVo = 
							ratePlanManager.getRatePlanI18nByCodeHotelId(
									priceCalc.getRatePlanCode(), availRoomVO.getHotelId(), availRoomVO.getLanguage());
						ratePlanVo.getPriceCalcList().add(priceCalc);
						roomTypeVo.getRateplanVoList().add(ratePlanVo);
						roomTypeVOList.add(roomTypeVo);
					}
				}
			}
			long e2 = System.currentTimeMillis();
			log.info("处理房型房价关系耗费:"+(e2-s1)+"毫秒");
			
			Map<String, RatePlanVO> rateplanMap = new HashMap<String, RatePlanVO>();
			
			//设置基础值
			for (RoomTypeVO rtvo : roomTypeVOList) {
				this.setRoomTypeVoProps(rtvo);
				for (RatePlanVO rpvo : rtvo.getRateplanVoList()) {
					//如果已经设置过一次
					if(rateplanMap.containsKey(rpvo.getRp().getRatePlanId())){
						RatePlanVO historyRateplan = rateplanMap.get(rpvo.getRp().getRatePlanId());
						rpvo.getRpi18n().setDescription(historyRateplan.getRpi18n().getDescription());
						rpvo.setGuaranteePolicyVoList(historyRateplan.getGuaranteePolicyVoList());
						rpvo.setCancelPolicyVoList(historyRateplan.getCancelPolicyVoList());
						//rpvo.setPrice(historyRateplan.getPrice());
						this.setRatePlanPrice(rpvo);
						rpvo.setPackageVOList(historyRateplan.getPackageVOList());
					}else{
						this.setRatePlanVoProps(rpvo, 
								availRoomVO.getHotelId(),
								availRoomVO.getStartDate(), 
								availRoomVO.getEndDate(), 
								availRoomVO.getCustomCode(), //客户代码
								availRoomVO.getLanguage());
						this.setRatePlanPackages(rpvo, rtvo, 
								availRoomVO.getHotelId(),
								availRoomVO.getStartDate(), 
								availRoomVO.getEndDate(), 
								availRoomVO.getLanguage());
						rateplanMap.put(rpvo.getRp().getRatePlanId(), rpvo);
					}
					
				}
				//对房型的房价列表排序
				this.sortRatePlanList(rtvo.getRateplanVoList());
			}
			//对房型列表排序
			this.sortRoomTypeList(roomTypeVOList);
			
			long e3 = System.currentTimeMillis();
			log.info("设置房型数据耗费:"+(e3-e2)+"毫秒");
		} catch (BizException e) {
			log.info(e.getErrMsg());
			throw new BizException(e.getErrKey(),e.getErrMsg());
		} catch (Exception e) {
			log.info(CommonUtil.getExceptionMsg(e));
			e.printStackTrace();
		}
		return roomTypeVOList;
	}

	@Override
	public List<RatePlanVO> queryRatePlanVoList(List<PriceCalc> priceCalcList, AvailRoomVO availRoomVO) {
		List<RatePlanVO> ratePlanVOList = new ArrayList<RatePlanVO>();
		
		try {
			long s1 = System.currentTimeMillis();
			if(priceCalcList==null||priceCalcList.size()==0){
				return null;
			}
			//用来去除房价重复
			Map<String, String> rateMap = new HashMap<String, String>();
			//循环遍历房价日历信息
			for (PriceCalc priceCalc : priceCalcList) {
				//如果已存在房价Map中
				if(rateMap.containsKey(priceCalc.getRatePlanCode())){
					//循环房价列表
					for (RatePlanVO rpvo : ratePlanVOList) {
						//如果对应上了房价
						if(rpvo.getRp().getRatePlanCode().equals(priceCalc.getRatePlanCode())){
							boolean flag = false;
							for (RoomTypeVO rtvo : rpvo.getRoomTypeVoList()) {
								//如果房型代码对应上了
								if(rtvo.getRoomTypeCode().equals(priceCalc.getRoomTypeCode())){			
									rtvo.getPriceCalcList().add(priceCalc);
									flag = true;
								}
							}
							//如果没有在当前房价下找到房型
							if(!flag){
								//获取房型
								RoomTypeVO roomTypeVo = 
									roomTypeManager.getRoomTypeByHotelCode(
											priceCalc.getRoomTypeCode(), availRoomVO.getHotelCode(),availRoomVO.getLanguage());
								//添加房价日历到房型VO中
								roomTypeVo.getPriceCalcList().add(priceCalc);
								rpvo.getRoomTypeVoList().add(roomTypeVo);
							}
							
						}
					}	
				}else{
					rateMap.put(priceCalc.getRatePlanCode(), "");  //添加房价代码到Map中，用来下次区分重复
					//获取房价VO
					RatePlanVO ratePlanVo = 
						ratePlanManager.getRatePlanI18nByCodeHotelId(
								priceCalc.getRatePlanCode(), availRoomVO.getHotelId(), availRoomVO.getLanguage());
					
					//如果房型未找到
					if(ratePlanVo!=null){
						//获取房型
						RoomTypeVO roomTypeVo = 
							roomTypeManager.getRoomTypeByHotelCode(
									priceCalc.getRoomTypeCode(), availRoomVO.getHotelCode(),availRoomVO.getLanguage());
						
						if(roomTypeVo!=null){
							ratePlanVo.getRoomTypeVoList().add(roomTypeVo);
						}
						
						//添加房价日历
						roomTypeVo.getPriceCalcList().add(priceCalc);
						ratePlanVOList.add(ratePlanVo);
					}
				}
			}

			long e2 = System.currentTimeMillis();
			log.info("处理房型房价关系耗费:"+(e2-s1)+"毫秒");
			
			Map<String, RatePlanVO> rateplanMap = new HashMap<String, RatePlanVO>();
			//设置基础值
			for (RatePlanVO rpvo : ratePlanVOList) {
				//如果已经设置过一次
				if(rateplanMap.containsKey(rpvo.getRp().getRatePlanId())){
					RatePlanVO historyRateplan = rateplanMap.get(rpvo.getRp().getRatePlanId());
					rpvo.getRpi18n().setDescription(historyRateplan.getRpi18n().getDescription());
					rpvo.setGuaranteePolicyVoList(historyRateplan.getGuaranteePolicyVoList());
					rpvo.setCancelPolicyVoList(historyRateplan.getCancelPolicyVoList());
					//rpvo.setPrice(historyRateplan.getPrice());
					this.setRatePlanPrice(rpvo);
					rpvo.setPackageVOList(historyRateplan.getPackageVOList());
				}else{
					this.setRatePlanVoProps(rpvo,
							availRoomVO.getHotelId(),
							availRoomVO.getStartDate(), 
							availRoomVO.getEndDate(), 
							availRoomVO.getCustomCode(), //客户代码
							availRoomVO.getLanguage());
					this.setRatePlanPackages(rpvo, null, 
							availRoomVO.getHotelId(),
							availRoomVO.getStartDate(), 
							availRoomVO.getEndDate(), 
							availRoomVO.getLanguage());
					rateplanMap.put(rpvo.getRp().getRatePlanId(), rpvo);
				}
				
				for (RoomTypeVO rtvo : rpvo.getRoomTypeVoList()) {
					this.setRoomTypeVoProps(rtvo);
				}
				//排序
				this.sortRoomTypeList(rpvo.getRoomTypeVoList());
			}
			//排序
			this.sortRatePlanList(ratePlanVOList);
			
			long e3 = System.currentTimeMillis();
			log.info("设置房价数据耗费:"+(e3-e2)+"毫秒");
		} catch (BizException e) {
			log.info(e.getErrMsg());
			throw new BizException(e.getErrKey(),e.getErrMsg());
		} catch (Exception e) {
			log.info(CommonUtil.getExceptionMsg(e));
			e.printStackTrace();
		}
		return ratePlanVOList;
	}
	
	
	@Override
	public List<PriceCalc> getPriceCalcList(AvailRoomVO availRoomVO) throws Exception {
		log.info("availRoomVO:"+availRoomVO.toString());
		//如果开始时间大于结束时间
		if(availRoomVO.getStartDate().after(availRoomVO.getEndDate()) 
				|| availRoomVO.getStartDate().equals(availRoomVO.getEndDate())){
			log.info("开始和结束时间冲突");
			throw new BizException("wbe-exception","开始和结束时间冲突");//开始和结束时间冲突
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<PriceCalc> list = new ArrayList<PriceCalc>();
		try {
			//获取日期
			Date startDate = availRoomVO.getStartDate();
			Date endDate = availRoomVO.getEndDate();
			
			//查询价格日历
			PriceCalc priceCalc = new PriceCalc();
			priceCalc.setChannelCode(availRoomVO.getChannelCode());
			priceCalc.setChainCode(availRoomVO.getChainCode());
			priceCalc.setHotelCode(availRoomVO.getHotelCode());
			priceCalc.setNumberOfUnits(availRoomVO.getTotalNumberOfGuests()); //最大入住人数
			priceCalc.setStartDate(sdf.format(startDate));
			priceCalc.setEndDate(sdf.format(endDate));
			priceCalc.setOnOff(1);//查询所有开的房价
			priceCalc.setHotelId(availRoomVO.getHotelId());
			long s1 = System.currentTimeMillis();
			Channel c = channelManager.getChannelByChannelCode(availRoomVO.getChannelCode());
			long s2 = System.currentTimeMillis();
			log.info("getChannelByChannelCode,cost:"+(s2-s1));
			if (c == null) {
				throw new BizException("ChannelCodeError", "ChannelCodeIsNotFound");
			}
			priceCalc.setChannelId(c.getChannelId());
			
			//查询房间日历,保存符合条件的房价日历
			list = restrictionCalcDao.searchRestrictionCalcAndRateForWBE(priceCalc);
			long s3 = System.currentTimeMillis();
			log.info("searchRestrictionCalcAndRate,cost:"+(s3-s2));
			if(list==null||list.size()==0){
				return null;
			}
			
			//校验最小/最大连住天数和最小/最大提前预订天数
			list = availabilityManager.getPriceCalcListDays(list, priceCalc);
			long s4 = System.currentTimeMillis();
			log.info("getPriceCalcListDays,cost:"+(s4-s3));
			if(list==null || list.size()==0){
				return null;
			}
			
			//校验渠道绑定关系
			list = this.getPriceCalcListByChannel(list, availRoomVO);
			long s5 = System.currentTimeMillis();
			log.info("getPriceCalcListByChannel,cost:"+(s5-s4));
			if(list==null || list.size()==0){
				return null;
			}
			
			//校验符合日期范围内的有效房量记录(如3天内必须都有房,且每天的可用放量都大于预订房间数,关房的即可排除)
			list = this.getPriceCalcListEvenDays(list, availRoomVO);
			long s6 = System.currentTimeMillis();
			log.info("getPriceCalcListEvenDays,cost:"+(s6-s5));
			if(list==null || list.size()==0){
				return null;
			}
			
			//校验 酒店,房型,房价 的关系是否正确
			list = this.checkPriceCalcHotelRelation(list, availRoomVO);
			long s7 = System.currentTimeMillis();
			log.info("checkPriceCalcHotelRelation,cost:"+(s7-s6));
			if(list==null || list.size()==0){
				return null;
			}
			
			//校验房价和客户绑定关系
			list = this.getPriceCalcListByCustom(list, availRoomVO);
			long s8 = System.currentTimeMillis();
			log.info("getPriceCalcListByCustom,cost:"+(s8-s7));
			if(list==null || list.size()==0){
				return null;
			}
			
		}  catch (BizException e) {
			log.info(e.getErrMsg());
			throw new BizException(e.getErrKey(), e.getErrMsg());
		}  catch (Exception e) {
			log.info(CommonUtil.getExceptionMsg(e));
			e.printStackTrace();
			throw new BizException("wbe-exception", CommonUtil.getExceptionMsg(e));
		}
		
		
		return list;
	}
	
	/**
	 * 校验符合日期范围内的有效房量记录(如3天内必须都有房,关房的即可排除)
	 * @param priceCalcList
	 * @param start
	 * @param end
	 * @return
	 */
	private List<PriceCalc> getPriceCalcListEvenDays(
			List<PriceCalc> priceCalcList, AvailRoomVO availRoomVO) {
		List<PriceCalc> list = new ArrayList<PriceCalc>();
		Date start = availRoomVO.getStartDate();
		Date end = availRoomVO.getEndDate();
		int numberOfRooms = availRoomVO.getNumberOfRooms();
		String hotelCode=availRoomVO.getHotelCode();
		String channelCode=availRoomVO.getChannelCode();
		//用来保存每个酒店+房型+房价的分组记录条数
		Map<String, Integer> hotelRoomRateMap = new HashMap<String, Integer>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//循环分组设置Map列表
		for(PriceCalc pc:priceCalcList){
			//判断房价金额小于等于0
			if(null==pc.getAmount() 
					|| pc.getAmount().compareTo(new BigDecimal("0"))<=0){
				continue;
			}
			
			int available = 0;
			try {
				
				available = rsvtypeChannelManager.queryRsvtypeChannelAvailableByDate(
						pc.getRoomTypeCode(), hotelCode, sdf.parse(pc.getDate()),channelCode,pc.getRatePlanCode());
				//available = rsvtypeManager.getResvTypeAvailByDay(pc.getRoomTypeCode(), pc.getHotelCode(),sdf.parse(pc.getDate()));
			} catch (ParseException e) {
				available = 0;
				throw new BizException("wbe-exception","DATE:"+pc.getDate()+"DATAFORMATE ERROR");//日期格式化失败
			}
			if(available < numberOfRooms){//判断可用房量如果小于要预定的房间数
				continue;
			}
			
			//酒店+房型+房价的代码组合
			String codes = hotelCode 
				+ "," + pc.getRoomTypeCode() 
				+ "," + pc.getRatePlanCode();

			//如果已经存在了,加一项
			if(hotelRoomRateMap.containsKey(codes)){
				Integer count = hotelRoomRateMap.get(codes) + 1;
				hotelRoomRateMap.put(codes, count);
			}else{
				hotelRoomRateMap.put(codes, 1);
			}
		}
		//日期间隔
		Integer evenDay = DateUtil.dateDiff(start, end);
		//开始筛选
		for (PriceCalc pc : priceCalcList) {
			//酒店+房型+房价的代码组合
			String codes = hotelCode 
				+ "," + pc.getRoomTypeCode() 
				+ "," + pc.getRatePlanCode();
			
			if(hotelRoomRateMap.get(codes)!=null 
					&& evenDay.equals(hotelRoomRateMap.get(codes))){
				list.add(pc);
			}
		}

		return list;
	}
	
	/**
	 * 根据渠道筛选房价日历
	 * @param priceCalcList
	 * @param hotelId
	 * @return
	 */
	private List<PriceCalc> getPriceCalcListByChannel(List<PriceCalc> priceCalcList,AvailRoomVO availRoomVO) throws Exception{
		String hotelId = availRoomVO.getHotelId();
		String channelCode = availRoomVO.getChannelCode();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<PriceCalc> list = new ArrayList<PriceCalc>();
		ChannelGoodsVO cgvo = null;
		for(PriceCalc p:priceCalcList){
			cgvo = new ChannelGoodsVO();
			cgvo.setChannelCode(channelCode);
			cgvo.setHotelId(hotelId);
			cgvo.setRatePlanCode(p.getRatePlanCode());
			cgvo.setRoomTypeCode(p.getRoomTypeCode());
			try {
				cgvo.setExpireDate(sdf.parse(p.getDate()));
			} catch (ParseException e) {
				throw new BizException("wbe-exception","ExpireDate:"+p.getDate()+"DATEFORMAT ERROR");//日期格式化失败
			}
			List<ChannelGoodsVO> cgvoList = channelgoodsManager.getEnabledChannelGoods(cgvo);
			if(cgvoList!=null&&cgvoList.size()>0){
				list.add(p);
			}
		}
		
		return list;
	}
	
	/**
	 * 判断房价日历中酒店是否和房价房型的绑定关系
	 * @param priceCalcList
	 * @param availRoomVO
	 * @return
	 */
	private List<PriceCalc> checkPriceCalcHotelRelation(List<PriceCalc> priceCalcList,AvailRoomVO availRoomVO){
		List<PriceCalc> list = new ArrayList<PriceCalc>();
		for (PriceCalc priceCalc : priceCalcList) {
			RoomType roomType = 
				roomTypeManager.getRoomTypeByHotelCode(priceCalc.getRoomTypeCode(), availRoomVO.getHotelCode());
			//如果房型为空,则进入到吓一跳房价日历
			if(roomType == null){
				continue;
			}
			
			RatePlanVO ratePlanVO = 
				ratePlanManager.getRatePlanI18nByCodeHotelId(priceCalc.getRatePlanCode(), availRoomVO.getHotelId());
			//如果房型为空,则进入到吓一跳房价日历
			if(ratePlanVO == null){
				continue;
			}
			
			list.add(priceCalc);
		}
		
		return list;
	}
	
	
	/**
	 * 根据客户信息筛选房价日历
	 * @param priceCalcList
	 * @param availability
	 * @return
	 * @throws Exception 
	 */
	private List<PriceCalc> getPriceCalcListByCustom(
			List<PriceCalc> priceCalcList, AvailRoomVO availRoomVO) throws Exception {
		List<PriceCalc> list = new ArrayList<PriceCalc>();
		//组装客户查询对象
		Custom c = new Custom();
		c.setHotelId(availRoomVO.getHotelId());
		//如果协议客户不为空
		if(StringUtils.isNotBlank(availRoomVO.getCustomCode())){
			c.setType(ProfileType.CORPORATE.name());
			c.setAccessCode(availRoomVO.getCustomCode());
		//如果促销代码不为空
		}else if(StringUtils.isNotBlank(availRoomVO.getPromoCode())){
			c.setType(ProfileType.PROMOTION.name());
			c.setAccessCode(availRoomVO.getPromoCode());
		//如果两项代码都为空
		}else{
			c.setType(ProfileType.CORPORATE.name());
			c.setAccessCode(WBEConstant.CHANNEL_CODE);
		}
		
		Map<String, String> rateplanMap = new HashMap<String, String>();
		for(PriceCalc p:priceCalcList){
			//如果存在
			if(rateplanMap.containsKey(p.getRatePlanCode())){
				list.add(p);
			}else{
				RateCustomRelationship rcr = null;
				try {
					rcr = rateCustomRelationshipDao.getCustomByRateCustom(c, p.getRatePlanCode());
				} catch (Exception e) {
					throw new BizException("13", "GENERAL UPD FAILURE");// 房价客户关系对象查询失败
				}
				if(rcr!=null){//房价客户关系是否成立
					list.add(p);
					//放入到map中
					rateplanMap.put(p.getRatePlanCode(), "");
				}
			}
		}
		return list;
	}
	
	/**
	 * 设置房型vo对象的一些属性
	 * @param roomTypeVo
	 */
	private void setRoomTypeVoProps(RoomTypeVO roomTypeVo){
		//如果描述为空则显示名称
		if(StringUtils.isBlank(roomTypeVo.getDescription())){
			roomTypeVo.setDescription(roomTypeVo.getRoomTypeName());
		}
		
		try {
			
			//设置房型图片
			roomTypeVo.setPictureList(
				pictureManager.getBizPictureList("2", roomTypeVo.getRoomTypeId()));
			
		} catch (Exception e) {
			log.info(CommonUtil.getExceptionMsg(e, "exception"));
			e.printStackTrace();
		}
		
		try {
			//设置房型均价
			if(roomTypeVo.getPriceCalcList()!=null&&roomTypeVo.getPriceCalcList().size()>0){
				//得到房价总和
				BigDecimal sum = new BigDecimal(0.00);
				for (PriceCalc pc : roomTypeVo.getPriceCalcList()) {
					pc.setAmount(
							pc.getAmount().setScale(2, RoundingMode.HALF_UP));
					sum = sum.add(pc.getAmount());
				}
				
				int size = roomTypeVo.getPriceCalcList().size();
				BigDecimal price = sum.divide(new BigDecimal(size*1.0), 2, RoundingMode.HALF_UP);
				roomTypeVo.setPrice(price);
			}
			
		} catch (Exception e) {
			log.info(CommonUtil.getExceptionMsg(e, "exception"));
			e.printStackTrace();
		}
		
	}

	/**
	 * 设置房价vo对象的一些属性(担保规则,取消规则,包价)
	 * @param ratePlanVo
	 * @param language
	 */
	private void setRatePlanVoProps(RatePlanVO ratePlanVo,String hotelId,Date startDate,Date endDate,String corporateCode,String language){
		//如果房价描述为空，则设置为名称
		if(ratePlanVo.getRpi18n()!=null&&StringUtils.isBlank(ratePlanVo.getRpi18n().getDescription())){
			ratePlanVo.getRpi18n().setDescription(ratePlanVo.getRpi18n().getRatePlanName());
		}
		
		try {
			//担保规则
			List<RateGuaranteeRelationship> rateGuaranteeShipList =
				rateGuaranteeRelationshipDao.getRateGuaranteeRelationshipByRatePlanId(
						ratePlanVo.getRp().getRatePlanId());
			
			List<GuaranteePolicyVO> guaranteePolicyList = new ArrayList<GuaranteePolicyVO>();
			for (RateGuaranteeRelationship ship : rateGuaranteeShipList) {
				//获取担保规则
				GuaranteePolicyVO gpvo = guaranteePolicyManager.getGuaranteePolicyById(
						ship.getGuaranteeId(), language);
				
				//如果担保类型为:预付担保  则不显示到房价的 列表中
				if(GuaranteeCode.DPGTD.equalsIgnoreCase(gpvo.getGuaranteeCode())){
					continue;
				}
				
				// 当协议客户是默认OWS时不显示COGTD
				if (GuaranteeCode.COGTD.equalsIgnoreCase(gpvo.getGuaranteeCode()) 
						&& WBEConstant.CHANNEL_CODE.equalsIgnoreCase(corporateCode)) {
					continue;
				}
				
				//如果验证通过
				if(gpvo!=null && this.isEnableGuarantee(
						gpvo.getGuaranteeCode(), 
						ratePlanVo.getRp().getRatePlanId(), 
						startDate, 
						endDate)){
					
					//组装查询条件
					HotelGuaranteeVO hgvo = new HotelGuaranteeVO();
					hgvo.setHotelId(hotelId);
					hgvo.setGuaranteeId(gpvo.getGuaranteeId());
					hgvo.setLanguage(language);
					
					List<HotelGuaranteeVO> hgList = hotelGuaranteeDao.getHotelGuaranteeByObj(hgvo);
					if(hgList != null && hgList.size()>0){
						if(StringUtils.isNotBlank(hgList.get(0).getDescription())){
							gpvo.setDescription(hgList.get(0).getDescription());
						}else{
							gpvo.setDescription(hgList.get(0).getPolicyName());
						}
					}
					guaranteePolicyList.add(gpvo);
				}
			}
			ratePlanVo.setGuaranteePolicyVoList(guaranteePolicyList);
			
		} catch (Exception e) {
			log.info(CommonUtil.getExceptionMsg(e, "exception"));
			e.printStackTrace();
		}
		
		
		try {
			//取消规则
			List<RateCancelRelationship> rateCancelshipList = 
				rateCancelRelationshipDao.getRateCancelRelationshipByRatePlanId(
						ratePlanVo.getRp().getRatePlanId());
			
			List<CancelPolicyVO> cancelPolicyList = new ArrayList<CancelPolicyVO>();
			for (RateCancelRelationship ship : rateCancelshipList) {
				//获取取消规则
				CancelPolicyVO cpvo = cancelPolicyManager.getCancelPolicyById(
						ship.getCancelId(), language);
				if(cpvo!=null){
					//组装查询条件
					HotelCancelVO hcvo = new HotelCancelVO();
					hcvo.setHotelId(hotelId);
					hcvo.setCancelId(cpvo.getCancelId());
					hcvo.setLanguage(language);

					List<HotelCancelVO> hcList = hotelCancelDao.getHotelCancelByObj(hcvo);
					if(hcList != null && hcList.size()>0){
						if(StringUtils.isNotBlank(hcList.get(0).getDescription())){
							cpvo.setDescription(hcList.get(0).getDescription());
						}else{
							cpvo.setDescription(hcList.get(0).getPolicyName());
						}
					}

					cancelPolicyList.add(cpvo);
				}
			}
			ratePlanVo.setCancelPolicyVoList(cancelPolicyList);
			
		} catch (Exception e) {
			log.info(CommonUtil.getExceptionMsg(e, "exception"));
			e.printStackTrace();
		}
		
		try {
			//设置房价均价
			if(ratePlanVo.getPriceCalcList()!=null&&ratePlanVo.getPriceCalcList().size()>0){
				//得到房价总和
				BigDecimal sum = new BigDecimal(0.00);
				for (PriceCalc pc : ratePlanVo.getPriceCalcList()) {
					pc.setAmount(
							pc.getAmount().setScale(2, RoundingMode.HALF_UP));
					sum = sum.add(pc.getAmount());
				}
				
				int size = ratePlanVo.getPriceCalcList().size();
				BigDecimal price = sum.divide(new BigDecimal(size*1.0), 2, RoundingMode.HALF_UP);
				ratePlanVo.setPrice(price);
			}
			
		} catch (Exception e) {
			log.info(CommonUtil.getExceptionMsg(e, "exception"));
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置均价
	 * @param ratePlanVo
	 */
	private void setRatePlanPrice(RatePlanVO ratePlanVo){
		try {
			//设置房价均价
			if(ratePlanVo.getPriceCalcList()!=null&&ratePlanVo.getPriceCalcList().size()>0){
				//得到房价总和
				BigDecimal sum = new BigDecimal(0.00);
				for (PriceCalc pc : ratePlanVo.getPriceCalcList()) {
					pc.setAmount(
							pc.getAmount().setScale(2, RoundingMode.HALF_UP));
					sum = sum.add(pc.getAmount());
				}
				
				int size = ratePlanVo.getPriceCalcList().size();
				BigDecimal price = sum.divide(new BigDecimal(size*1.0), 2, RoundingMode.HALF_UP);
				ratePlanVo.setPrice(price);
			}
			
		} catch (Exception e) {
			log.info(CommonUtil.getExceptionMsg(e, "exception"));
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置房价下的包价
	 * @throws Exception 
	 */
	private void setRatePlanPackages(RatePlanVO rpvo,RoomTypeVO rtvo,String hotelId,Date startDate,Date endDate,String language) throws Exception{
		List<String> ratePlanIds = new ArrayList<String>();
		ratePlanIds.add(rpvo.getRp().getRatePlanId());
		//保存包价列表
		List<PackageVO> pvoList = new ArrayList<PackageVO>();
		
		//获取房价包
		List<PackageVO> list = packageManager.getPackageByRatePlan(ratePlanIds,language);
		if(list!=null&&list.size()>0){
			for(PackageVO pvo:list){
				if(isNotEmptyPackage(pvo) && isValidPackage(pvo,startDate,endDate)){
					pvoList.add(pvo);
				}
			}
		}
		
		//获取房价日历下的包价
		list = this.getRoomTypePackageByRateDetailIds(rpvo.getPriceCalcList(),startDate,endDate,language);
		if(list!=null&&list.size()>0){
			pvoList.addAll(list);
		}
		
		//如果房型不为空,且已存在包价
		if(rtvo!=null&&pvoList.size()>0){
			pvoList = this.getPackageVOsByRatePlanAndRoomType(pvoList, rpvo.getRp().getRatePlanId(), rtvo.getRoomTypeId());
		}

		//设置值,需要获取酒店级别的 包价描述和名称
		for (PackageVO packageVO : pvoList) {
			
			HotelPackageVO hpvo = new HotelPackageVO();
			hpvo.setHotelId(hotelId);
			hpvo.setPackageId(packageVO.getPackageId());
			hpvo.setLanguage(language);
			List<HotelPackageVO> hpList = hotelpackageDao.getHotelPackageByObj(hpvo);
			
			if(hpList!=null&&hpList.size()>0){
				if(StringUtils.isNotBlank(hpList.get(0).getDescription())){
					packageVO.setDescription(hpList.get(0).getDescription());
				}else{
					packageVO.setDescription(hpList.get(0).getPackageName());
				}
			}
		}
		rpvo.setPackageVOList(pvoList);
	}
	
	/**
	 * 获取房价日历下的包价
	 * @param priceCalcList
	 * @return
	 */
	private List<PackageVO> getRoomTypePackageByRateDetailIds(List<PriceCalc> priceCalcList,Date startDate,Date endDate,String language){
		if(priceCalcList==null||priceCalcList.size()==0){
			return null;
		}
		
		List<PackageVO> list = new ArrayList<PackageVO>();
		for (PriceCalc pc : priceCalcList) {
			Map<String, String> map = new HashMap<String, String>();
			// 取房价房型包价
			List<PackageVO> pList = packageManager.getRoomTypePackageByRatePlanId(pc.getRateDetailId(), language);
			for (PackageVO vo : pList) {
				String key = vo.getRoomTypeId() + "_" + vo.getPackageCode();				
				if (!map.containsKey(key)) {
					list.add(vo);
					map.put(key, key);
				}
			}
		}
		
		//保存包价列表
		List<PackageVO> pvoRtlist = new ArrayList<PackageVO>();
		//校验包价列表
		for (PackageVO packageVO : list) {
			if(isNotEmptyPackage(packageVO) && isValidPackage(packageVO,startDate,endDate)){
				pvoRtlist.add(packageVO);
			}
		}

		return pvoRtlist;
	}
	
	/**
	 * 根据房价Id和房型Id获取所有的包价信息
	 */
	private List<PackageVO> getPackageVOsByRatePlanAndRoomType(List<PackageVO> voList,String ratePlanId,String roomTypeId)throws Exception {
		List<PackageVO> list = new ArrayList<PackageVO>();
		if(!voList.isEmpty()){
			for(PackageVO vo:voList){
				
				boolean flag = false;
				for (PackageVO packageVo : list) {
					if(packageVo.getPackageCode().equals(vo.getPackageCode())){
						flag = true;
					}
				}
				//如果存在重复
				if(flag)continue;

				PackageVO vo2 = new PackageVO();
				if(null==vo.getRoomTypeId()){
					if(vo.getRatePlanId().equals(ratePlanId)){
						PropertyUtils.copyProperties(vo2, vo);
						list.add(vo2);
					}
				}else if(null==vo.getRatePlanId()){
					if(vo.getRoomTypeId().equals(roomTypeId)){
						PropertyUtils.copyProperties(vo2, vo);
						list.add(vo2);
					}
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 对房型列表排序
	 * @param roomTypeList
	 */
	private void sortRoomTypeList(List<RoomTypeVO> roomTypeList){
		Collections.sort(roomTypeList, new Comparator<RoomTypeVO>(){
			@Override
			public int compare(RoomTypeVO vo1, RoomTypeVO vo2) {
				if(vo1.getSortNum()==null){
					vo1.setSortNum(-10);
				}
				
				if(vo2.getSortNum()==null){
					vo2.setSortNum(-10);
				}
				
				return vo1.getSortNum().compareTo(vo2.getSortNum());
			}
			
		});
	}
	
	
	/**
	 * 对房价列表排序
	 * @param roomTypeList
	 */
	private void sortRatePlanList(List<RatePlanVO> ratePlanList){
		Collections.sort(ratePlanList, new Comparator<RatePlanVO>(){
			@Override
			public int compare(RatePlanVO vo1, RatePlanVO vo2) {
				if(vo1.getRp().getOrderNum()==null){
					vo1.getRp().setOrderNum(-10);
				}
				
				if(vo2.getRp().getOrderNum()==null){
					vo2.getRp().setOrderNum(-10);
				}
				
				return vo1.getRp().getOrderNum().compareTo(vo2.getRp().getOrderNum());
			}
			
		});
	}
	
	/**
	 * 验证担保规则的有效性
	 * @param guaranteeCode 担保规则代码
	 * @param rateplanId    房价ID
	 * @param checkinDate   开始日期
	 * @param checkOutDate  离店日期
	 * @return
	 */
	private boolean isEnableGuarantee(String guaranteeCode,String rateplanId,Date checkinDate,Date checkOutDate){
		//获取日期间隔
		int days = DateUtil.dateDiff(checkinDate, checkOutDate);
		for(int i=0;i<days;i++){
			checkinDate = DateUtil.addDays(checkinDate, i);
			if(rateGuaranteePolicyManager.validGuaranteePolicy(
					guaranteeCode, 
					rateplanId, 
					checkinDate) == false){
				return false;
			}
		}
		return true;
	}
	

	/**
	 * 判断packageVO中是否有必填项为空
	 * true:表示数据完整；false：数据不完整
	 */
	private boolean isNotEmptyPackage(PackageVO vo){
		if(vo.getIsExtraCharge()){
			if(null==vo.getCalculation() || "".equals(vo.getCalculation())){//计算方式
				return false;
			}
			if(null==vo.getRule() || "".equals(vo.getRule())){//计算规则
				return false;
			}
			if(null==vo.getBasicType() || "".equals(vo.getBasicType())){//计算类型
				return false;
			}
			if(vo.getCalculation()==4){//星期
				if(!vo.getIsApplyToMonday() && !vo.getIsApplyToTuesday() && !vo.getIsApplyToWednesday() && 
						!vo.getIsApplyToThursday() && !vo.getIsApplyToFriday() && !vo.getIsApplyToSaturday() && 
						!vo.getIsApplyToSunday()){
					return false;
				}
			}
			if(vo.getCalculation()==5){//日期
				if(null==vo.getBeginDate() || null==vo.getBeginDate()){
					return false;
				}
			}
			if(vo.getBasicType()==1){//比率
				if(new BigDecimal(vo.getPercent()).compareTo(new BigDecimal("0"))<=0){
					return false;
				}
			}
			if(vo.getBasicType()==2){//金额
				if(new BigDecimal(vo.getAmount()).compareTo(new BigDecimal("0"))<=0){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 检查package是否在查询时间段内有效
	 * true：表示有效，false：无效
	 */
	private boolean isValidPackage(PackageVO vo,Date startDate,Date endDate){
		endDate = new Date(endDate.getTime()-1000 * 24 * 60 * 60);//取前一天的日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int days = DateUtil.dateDiff(startDate, endDate);
		for(int i=0;i<=days;i++){
			if(i>0){
				cal.add(Calendar.DATE, 1);
			}
			if(vo.getIsExtraCharge()){
				if(vo.getCalculation()==4){
					int week = cal.get(Calendar.DAY_OF_WEEK)-1;
					if(vo.getIsApplyToSunday() && week==0){
						return true;
					}
					if(vo.getIsApplyToMonday() && week==1){
						return true;
					}
					if(vo.getIsApplyToTuesday() && week==2){
						return true;
					}
					if(vo.getIsApplyToWednesday() && week==3){
						return true;
					}
					if(vo.getIsApplyToThursday() && week==4){
						return true;
					}
					if(vo.getIsApplyToFriday() && week==5){
						return true;
					}
					if(vo.getIsApplyToSaturday() && week==6){
						return true;
					}
				}else if(vo.getCalculation()==5){
					Date date = cal.getTime();
					if((vo.getBeginDate().before(date) || vo.getBeginDate().equals(date)) && 
							(vo.getEndDate().after(date) || vo.getEndDate().equals(date))){
						return true;
					}
				}else{
					return true;
				}
			}else{
				return true;
			}
		}
		return false;
	}
	
}
