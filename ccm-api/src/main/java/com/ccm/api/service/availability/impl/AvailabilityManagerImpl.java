package com.ccm.api.service.availability.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.hotel.ChainDao;
import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.dao.ratePlan.RestrictionCalcDao;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RateCustomRelationship;
import com.ccm.api.model.ratePlan.RatePlanI18n;
import com.ccm.api.model.ratePlan.SoldableCondition;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.ws.vo.availability.AvailabilityRQVO;
import com.ccm.api.service.availability.AvailabilityManager;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.hotel.CustomManager;
import com.ccm.api.service.ratePlan.PackageManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("availabilityManager")
public class AvailabilityManagerImpl implements AvailabilityManager {
	@Autowired
	private RateplanDao rateplanDao;
	@Autowired
	private RestrictionCalcDao restrictionCalcDao;
	@Autowired
	private HotelMCDao hotelDao;
	@Autowired
	private ChainDao chainDao;
	@Autowired
	private RoomTypeDao roomTypeDao;
	@Autowired
	private RateCustomRelationshipDao rateCustomRelationshipDao;
	@Autowired
	private ChannelgoodsManager channelgoodsManager;
	@Autowired
	private PackageManager packageManager;
	@Autowired
	private CustomManager customManager;
	@Autowired
	private RsvtypeChannelManager rsvtypeChannelManager;
	@Autowired
	private RatePlanManager ratePlanManager;
	@Autowired
	private PriceCalcManager priceCalcManager;
	@Resource
	private ChannelManager channelManager;

	protected final Log log = LogFactory.getLog("availabilityImpl");

	/**
	 * 获取房价日历
	 */
	private List<PriceCalc> getPriceCalcList(AvailabilityRQVO availability) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (availability.getStartDate().after(availability.getEndDate()) || availability.getStartDate().equals(availability.getEndDate())) {
			throw new BizException("7", "INVALIDGDS");// 开始和结束时间冲突
		}
		// 获取日期
		Date startDate = availability.getStartDate();
		Date endDate = availability.getEndDate();
		// 查询价格日历
		PriceCalc priceCalc = new PriceCalc();
		priceCalc.setChannelCode(availability.getChannelCode());
		priceCalc.setChainCode(availability.getChainCode());
		priceCalc.setHotelCode(availability.getHotelCode());
		priceCalc.setNumberOfUnits(availability.getTotalNumberOfGuests());
		priceCalc.setStartDate(sdf.format(startDate));
		priceCalc.setEndDate(sdf.format(endDate));
		priceCalc.setOnOff(1);// 查询所有开的房价
		priceCalc.setHotelId(availability.getHotelId());
		priceCalc.setChannelId(availability.getChannelId());
		priceCalc.setRoomTypeIdsql(availability.getRoomTypeIdsql());
		//设置房型和房价代码
		if(availability.getRoomTypeCodeList() != null && availability.getRoomTypeCodeList().size() > 0){
			priceCalc.setRoomTypeCode(availability.getRoomTypeCodeList().get(0));
		}
		if(availability.getRatePlanCodeList() != null && availability.getRatePlanCodeList().size() > 0){
			priceCalc.setRatePlanCode(availability.getRatePlanCodeList().get(0));
		}
		
		log.info("priceCalc:" + JSON.toJSONString(priceCalc));
		List<PriceCalc> priceCalcList = restrictionCalcDao.searchRestrictionCalcAndRate(priceCalc,true);
		if (priceCalcList.isEmpty()) {
			log.info("saul testing=> getPriceCalcList().priceCalcList is empty");
			throw new BizException("28", "RATE CODE UNAVAILABLE");// 没有房价或关房
		}
		List<PriceCalc> list = new ArrayList<PriceCalc>();
		for (PriceCalc p : priceCalcList) {
			if (null != p.getAmount() && p.getAmount().compareTo(new BigDecimal("0")) > 0) {// 判断房价金额大于0
				p.setHotelCode(availability.getHotelCode());
				p.setChannelCode(availability.getChannelCode());
				list.add(p);
			}
		}
		if (list.isEmpty()) {
			log.info("saul testing=> getPriceCalcList().list is empty");
			throw new BizException("28", "RATE CODE UNAVAILABLE");// 没有房价或关房
		}
		
		return getPriceCalcListDays(list, priceCalc);
	}

	/**
	 * 验证最小/最大连住天数和最小/最大提前预订天数
	 * 
	 * @throws Exception
	 */
	public List<PriceCalc> getPriceCalcListDays(List<PriceCalc> priceCalcList, PriceCalc pc) throws Exception {
		List<PriceCalc> list = new ArrayList<PriceCalc>();
		// 组装成按 房价代码 分组的房价日历Map
		Map<String, List<PriceCalc>> priceCalcMap = new HashMap<String, List<PriceCalc>>();
		for (PriceCalc priceCalc : priceCalcList) {
			// 房价+房型代码组
			String rate_room_code = priceCalc.getRatePlanCode() + "_" + priceCalc.getRoomTypeCode();
			// 如果已经存在 键值为房型代码的列表
			if (priceCalcMap.containsKey(rate_room_code)) {
				List<PriceCalc> tempList = priceCalcMap.get(rate_room_code);
				tempList.add(priceCalc);
			} else {
				List<PriceCalc> newList = new ArrayList<PriceCalc>();
				newList.add(priceCalc);
				priceCalcMap.put(rate_room_code, newList);
			}
		}
		
		Map<String, Rateplan> useRateMap = new HashMap<String, Rateplan>();

		// 循环遍历
		for (String rate_room_code : priceCalcMap.keySet()) {
			// 获取房价代码
			String ratePlanCode = rate_room_code.substring(0, rate_room_code.indexOf("_"));
			
			Rateplan rv = useRateMap.get(ratePlanCode);
			if(rv == null){
				rv = ratePlanManager.getRateplanByRateplanCode(ratePlanCode, pc.getHotelCode());
			}

			List<SoldableCondition> scList = rv.getSCList();
			List<PriceCalc> priceList = priceCalcMap.get(rate_room_code);
			if (CommonUtil.isEmpty(scList)) {
				list.addAll(priceList);
			} else {
				for (SoldableCondition soldableCondition : scList) {
					if (priceCalcManager.validataSoldableCondition(soldableCondition, pc, priceList)) {
						list.addAll(priceList);
						useRateMap.put(ratePlanCode, rv);
						break;
					}
				}
			}
		}

		return list;
	}

	/**
	 * 获取房价日历，根据渠道绑定关系筛选
	 */
	private List<PriceCalc> getPriceCalcListByChannel(List<PriceCalc> priceCalcList, String hotelId) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<PriceCalc> list = new ArrayList<PriceCalc>();
		ChannelGoodsVO cgvo = null;
		for (PriceCalc p : priceCalcList) {
			cgvo = new ChannelGoodsVO();
			cgvo.setChannelCode(p.getChannelCode());
			cgvo.setHotelId(hotelId);
			cgvo.setRatePlanCode(p.getRatePlanCode());
			cgvo.setRoomTypeCode(p.getRoomTypeCode());
			try {
				cgvo.setExpireDate(sdf.parse(p.getDate()));
			} catch (ParseException e) {
				throw new BizException("43", "INVALID DATE FOMAT");// 日期格式化失败
			}
			List<ChannelGoodsVO> cgvoList = channelgoodsManager.getEnabledChannelGoods(cgvo);
			if (cgvoList != null && cgvoList.size() > 0) {
				list.add(p);
			}
		}
		if (list.isEmpty()) {
			log.info("saul testing=> getPriceCalcListByChannel().list is empty");
			throw new BizException("25", "PROPERTY RESTRICTED");// 没有绑定渠道关系
		}
		return list;
	}

	/**
	 * 根据客户信息筛选房价日历
	 * 
	 * @param priceCalcList
	 * @param availability
	 * @return
	 * @throws Exception
	 */
	private List<PriceCalc> getPriceCalcListByCustom(List<PriceCalc> priceCalcList, AvailabilityRQVO availability, String hotelId) throws Exception {
		List<PriceCalc> list = new ArrayList<PriceCalc>();
		// 组装客户查询对象
		Custom c = new Custom();
		c.setType(getCustomType(availability.getQualifyingIdType()));
		c.setAccessCode(availability.getQualifyingIdValue());
		c.setHotelId(hotelId);
		log.info(availability.getHotelCode() + "custom:" + JSON.toJSONString(c));

		Map<String, String> rateplanMap = new HashMap<String, String>();
		for (PriceCalc p : priceCalcList) {
			// 如果存在
			if (rateplanMap.containsKey(p.getRatePlanCode())) {
				list.add(p);
			} else {
				RateCustomRelationship rcr = null;
				try {
					rcr = rateCustomRelationshipDao.getCustomByRateCustom(c, p.getRatePlanCode());
				} catch (Exception e) {
					throw new BizException("13", "GENERAL UPD FAILURE");// 房价客户关系对象查询失败
				}
				if (rcr != null) {// 判断客户绑定关系
					list.add(p);
					// 放入到map中
					rateplanMap.put(p.getRatePlanCode(), "");
				}
				
			}
		}
		if (list.isEmpty()) {
			throw new BizException("10", "ROOM UNAVAILABLE");// 没有房量
		}
		return list;
	}

	/**
	 * 获取房价日历，根据可用房量筛选
	 */
	public List<PriceCalc> getPriceCalcListByAvail(List<PriceCalc> priceCalcList, int numberOfRooms) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<PriceCalc> list = new ArrayList<PriceCalc>();
		for (PriceCalc p : priceCalcList) {
			int available = 0;
			try {
				available = rsvtypeChannelManager.queryRsvtypeChannelAvailableByDate(p.getRoomTypeCode(), p.getHotelCode(), sdf.parse(p.getDate()), p.getChannelCode(),p.getRatePlanCode());
			} catch (ParseException e) {
				throw new BizException("43", "INVALID DATE FOMAT");// 日期格式化失败
			}
			if (available >= numberOfRooms) {// 判断可用房量
				list.add(p);
			}
		}
		if (list.isEmpty()) {
			throw new BizException("10", "ROOM UNAVAILABLE");// 没有房量
		}
		return list;
	}

	/**
	 * 获取房价日历，根据房型条件筛选
	 */
	private List<PriceCalc> getPriceCalcListByRoomType(List<PriceCalc> priceCalcList, List<String> roomTypeCodeList, HotelVO hotelVO) throws Exception {
		// 验证房型
		RoomTypeVO roomTypeVO = new RoomTypeVO();
		roomTypeVO.setHotelId(hotelVO.getHotelId());
		roomTypeVO.setRoomTypeCode(roomTypeCodeList.get(0));
		roomTypeVO = roomTypeDao.getRoomTypeByCode(roomTypeVO);
		if (null == roomTypeVO) {
			throw new BizException("02", "INVALID ROOM CATEGORY");// 错误房型代码
		}

		List<PriceCalc> list = new ArrayList<PriceCalc>();
		for (PriceCalc p : priceCalcList) {
			if (roomTypeCodeList.contains(p.getRoomTypeCode())) {
				list.add(p);
			}
		}
		return list;
	}

	/**
	 * 获取房价日历，根据房价条件筛选
	 * 
	 * @throws Exception
	 */
	private List<PriceCalc> getPriceCalcListByRatePlan(List<PriceCalc> priceCalcList, List<String> ratePlanCodeList, HotelVO hotelVO) throws Exception {
		// 验证房价
		Rateplan ratePlan = rateplanDao.getRateplanByRateplanCode(ratePlanCodeList.get(0), hotelVO.getHotelCode());
		if (null == ratePlan) {
			throw new BizException("5", "INVALID RATE CODE");// 错误房价代码
		}

		List<PriceCalc> list = new ArrayList<PriceCalc>();
		for (PriceCalc p : priceCalcList) {
			if (ratePlanCodeList.contains(p.getRatePlanCode())) {
				list.add(p);
			}
		}
		return list;
	}

	/**
	 * 在查询detail时需检查 房价代码和房型代码是一个时，判断日期间隔和数据总数是否相等 如果不相等，表示有其中一天没有房价，需抛出异常
	 */
	private boolean checkPriceCalcListByDetail(List<PriceCalc> priceCalcList, AvailabilityRQVO availability) throws Exception {
		int listSize = priceCalcList.size();
		int days = DateUtil.dateDiff(availability.getStartDate(), availability.getEndDate());
		log.info("listSize.size:" + listSize);
		log.info("days:" + days);
		if (listSize != days) {
			throw new BizException("10", "ROOM UNAVAILABLE");// 没有房价或关房
		}

		return false;
	}

	/**
	 * 在查询general时需检查
	 */
	private List<PriceCalc> checkPriceCalcListByGeneral(List<PriceCalc> priceCalcList, AvailabilityRQVO availability) throws Exception {
		List<PriceCalc> pList = null;
		int days = DateUtil.dateDiff(availability.getStartDate(), availability.getEndDate());
		List<Rateplan> ratePlanList = null;
		List<RoomType> roomTypeList = null;
		// 取集团信息
		Chain chain = chainDao.getChainByCode(availability.getChainCode());
		if (null == chain) {
			throw new BizException("00", "Chain Code is Missing");// 集团代码错误
		}
		// 取酒店信息
		HotelVO hotelVO = new HotelVO();
		hotelVO.setChainId(chain.getChainId());
		hotelVO.setHotelCode(availability.getHotelCode());
		hotelVO = hotelDao.getHotelByCodeMC(hotelVO);
		if (null == hotelVO) {
			throw new BizException("36", "PROPERTY CODE ERROR");// 酒店代码错误
		}
		ratePlanList = rateplanDao.getRatePlanByHotelId(hotelVO.getHotelId());
		roomTypeList = roomTypeDao.getHotelRoomTypesByHotelId(hotelVO.getHotelId());
		for (Rateplan Rateplan : ratePlanList) {
			for (RoomType roomType : roomTypeList) {
				pList = new ArrayList<PriceCalc>();
				for (PriceCalc p : priceCalcList) {
					if (p.getRatePlanCode().equals(Rateplan.getRatePlanCode()) && p.getRoomTypeCode().equals(roomType.getRoomTypeCode())) {
						pList.add(p);
					}
				}
				if (pList.size() != days) {
					priceCalcList.removeAll(pList);
				}
			}
		}
		return priceCalcList;
	}

	@Override
	public HotelVO getHotelWithGeneral(AvailabilityRQVO availability) throws Exception {
		HotelVO hotelVO = null;
		List<RoomTypeVO> roomTypeVOList = new ArrayList<RoomTypeVO>();
		List<RatePlanVO> ratePlanVOList = new ArrayList<RatePlanVO>();
		// 取集团信息
		Chain chain = chainDao.getChainByCode(availability.getChainCode());
		if (null == chain) {
			throw new BizException("00", "Chain Code is Missing");// 集团代码错误
		}
		
		// 取酒店信息
		hotelVO = new HotelVO();
		hotelVO.setChainId(chain.getChainId());
		hotelVO.setHotelCode(availability.getHotelCode());
		hotelVO.setLanguageCode(availability.getLanguage());
		hotelVO = hotelDao.getHotelByCodeMC(hotelVO);
		if (null == hotelVO) {
			throw new BizException("36", "PROPERTY CODE ERROR");// 酒店代码错误
		}
		availability.setHotelId(hotelVO.getHotelId());
		
		// 获取渠道
		Channel c = channelManager.getChannelByChannelCode(availability.getChannelCode());
		if (c == null) {
			throw new BizException("00", "Channel Code is Missing");// 渠道代码错误
		}
		availability.setChannelId(c.getChannelId());
		
		// 当进行 generalAvailability操作时，需要校 验客户类型和accessCode
		if (availability.isSummaryOnly()) {
			if (StringUtils.isBlank(availability.getQualifyingIdType()) || StringUtils.isBlank(availability.getQualifyingIdValue())) {
				throw new BizException("07", "INVALIDGDS");// QualifyingIdType或者QualifyingIdValue为空
			}
			// 组装查询条件
			Custom custom = new Custom();
			custom.setType(getCustomType(availability.getQualifyingIdType()));
			custom.setAccessCode(availability.getQualifyingIdValue());

			List<Custom> customList = customManager.getCustomByObj(custom);
			if (customList == null || customList.size() == 0) {
				throw new BizException("07", "INVALIDGDS"); // 客户不存在
			}
		}
		
		long startMili2 = System.currentTimeMillis();
		List<PriceCalc> priceCalcList = getPriceCalcList(availability);
		log.info(availability.getHotelCode() + " getPriceCalcList.size:" + priceCalcList.size() + " getPriceCalcList：" + (System.currentTimeMillis() - startMili2) + "豪秒");

		priceCalcList = getPriceCalcListByChannel(priceCalcList, hotelVO.getHotelId());
		if (null != availability.getRoomTypeCodeList()) {
			priceCalcList = getPriceCalcListByRoomType(priceCalcList, availability.getRoomTypeCodeList(), hotelVO);
		}
		if (null != availability.getRatePlanCodeList()) {
			priceCalcList = getPriceCalcListByRatePlan(priceCalcList, availability.getRatePlanCodeList(), hotelVO);
		}
		// 在查询detail时需检查
		if (availability.isSummaryOnly()) {
			// 根据客户信息筛选
			long startMili9 = System.currentTimeMillis();
			priceCalcList = getPriceCalcListByCustom(priceCalcList, availability, hotelVO.getHotelId());
			log.info(availability.getHotelCode() + " getPriceCalcListByCustom：" + (System.currentTimeMillis() - startMili9) + "豪秒");
			// 根据最小可用放量筛选
			long startMili10 = System.currentTimeMillis();
			priceCalcList = getPriceCalcListByAvail(priceCalcList, availability.getNumberOfRooms());
			log.info(availability.getHotelCode() + " getPriceCalcListByAvail：" + (System.currentTimeMillis() - startMili10) + "豪秒");
			priceCalcList = checkPriceCalcListByGeneral(priceCalcList, availability);
		} else {
			checkPriceCalcListByDetail(priceCalcList, availability);
			priceCalcList = getPriceCalcListByAvail(priceCalcList, availability.getNumberOfRooms());
			try {
				checkPriceCalcListByDetail(priceCalcList, availability);
			} catch (Exception e) {
				throw new BizException("28", "RATE CODE UNAVAILABLE");// 没有房价或关房
			}
		}
		// 验证priceCalcList为空
		if (priceCalcList.isEmpty()) {
			throw new BizException("28", "RATE CODE UNAVAILABLE");// 没有房价或关房
		}
		
		Set<String> rateplanCodeSet = new HashSet<String>();
		Set<String> roomTypeCodeSet = new HashSet<String>();
		List<String> rateplanIds = new ArrayList<String>();
		List<PackageVO> pvolist = new ArrayList<PackageVO>();
		Map <String,String> rateCodeMap=new HashMap<String,String>();
		// 取房价代码和房型代码
		for (PriceCalc p : priceCalcList) {
			rateplanCodeSet.add(p.getRatePlanCode());
			roomTypeCodeSet.add(p.getRoomTypeCode());
			rateCodeMap.put(p.getRoomTypeCode(), p.getRatePlanCode());
		}
		// 取房价ID
		for (String rateplanCode : rateplanCodeSet) {
			Rateplan ratePlan = rateplanDao.getRateplanByRateplanCode(rateplanCode, hotelVO.getHotelCode());
			rateplanIds.add(ratePlan.getRatePlanId());
		}
		// 取包价信息
		pvolist = getPackageVOs(rateplanIds, availability.getStartDate(), availability.getEndDate(), availability.getLanguage());
		Map<String, RatePlanVO> repeatRate = new HashMap<String, RatePlanVO>();
		// 设置包价信息
		for (PriceCalc p : priceCalcList) {

			// 取房价
			Rateplan ratePlan = rateplanDao.getRateplanByRateplanCode(p.getRatePlanCode(), hotelVO.getHotelCode());
			if (null == ratePlan) {
				throw new BizException("5", "INVALID RATE CODE");// 错误房价代码
			}
			RatePlanI18n ratePlanI18n = rateplanDao.getRatePlanI18nById(ratePlan.getRatePlanId(), availability.getLanguage());
			// 取房价包价
			List<PackageVO> rateRoomPackage = new ArrayList<PackageVO>();
			rateRoomPackage.addAll(pvolist);
			// 取房型包价
			rateRoomPackage.addAll(getRoomTypePackageByRateDetailId(p.getRateDetailId(), availability.getLanguage(), availability.getStartDate(), availability.getEndDate()));
			// 取满足条件包价并去重复
			RoomTypeVO roomTypeVO = new RoomTypeVO();
			roomTypeVO.setHotelId(hotelVO.getHotelId());
			roomTypeVO.setRoomTypeCode(p.getRoomTypeCode());
			roomTypeVO = roomTypeDao.getRoomTypeByCode(roomTypeVO);
			rateRoomPackage = getPackageVOsByRatePlanAndRoomType(rateRoomPackage, ratePlan.getRatePlanId(), roomTypeVO.getRoomTypeId());
			// 计算包价金额
			List<PackageVO> rpAndRtlist = countPackageAmount(rateRoomPackage, availability.getStartDate(), availability.getEndDate(), availability.getNumberOfRooms(), availability.getTotalNumberOfGuests(), p);

			// 计算每日包价合计金额
			BigDecimal packageAmount = getDayPackageAmount(rpAndRtlist);
			// 设置房价日历中包信息
			p.setPackageAmount(packageAmount);
			p.setPackageVOList(rpAndRtlist);

			// 设置房价信息,重复的房价
			if (repeatRate.get(ratePlan.getRatePlanId()) != null) {
				repeatRate.get(ratePlan.getRatePlanId()).getPackageVOList().addAll(rateRoomPackage);
			} else {
				RatePlanVO rateplanVO = new RatePlanVO();
				rateplanVO.setRp(ratePlan);
				rateplanVO.setRpi18n(ratePlanI18n);
				rateplanVO.setPackageVOList(rateRoomPackage);
				repeatRate.put(ratePlan.getRatePlanId(), rateplanVO);
				ratePlanVOList.add(rateplanVO);
			}
		}
		// 同一房价去重复包价
		for (int i = 0; i < ratePlanVOList.size(); i++) {
			List<String> packageids = new ArrayList<String>();
			List<PackageVO> pvoListP = new ArrayList<PackageVO>();
			for (PackageVO pvo : ratePlanVOList.get(i).getPackageVOList()) {
				if (!packageids.contains(pvo.getPackageId())) {
					packageids.add(pvo.getPackageId());
					pvoListP.add(pvo);
				}
			}
			ratePlanVOList.get(i).setPackageVOList(pvoListP);
		}
		long startMili21 = System.currentTimeMillis();

		for (String roomTypeCode : roomTypeCodeSet) {
			// 设置房型信息
			RoomTypeVO roomTypeVO = new RoomTypeVO();
			roomTypeVO.setHotelId(hotelVO.getHotelId());
			roomTypeVO.setRoomTypeCode(roomTypeCode);
			roomTypeVO.setLanguage(availability.getLanguage());
			roomTypeVO = roomTypeDao.getRoomTypeByCode(roomTypeVO);

			if (null == roomTypeVO) {
				throw new BizException("02", "INVALID ROOM CATEGORY");// 错误房型代码
			}

			// 此处获取时间段内的最小可用房量
			int available = rsvtypeChannelManager.queryRsvtypeChannelMinAvailable(roomTypeCode, hotelVO.getHotelCode(), availability.getStartDate(), availability.getEndDate(), availability.getChannelCode(),rateCodeMap.get(roomTypeCode));

			// 如果房量大于零
			if (available > 0) {
				roomTypeVO.setAvailable(available);
				roomTypeVOList.add(roomTypeVO);
			}
		}
		log.info(availability.getHotelCode() + " roomTypeCodeSet：" + (System.currentTimeMillis() - startMili21) + "豪秒");

		hotelVO.setRatePlanVOList(ratePlanVOList);
		hotelVO.setRoomTypeVOList(roomTypeVOList);
		hotelVO.setPriceCalcList(priceCalcList);
		return hotelVO;
	}
	
	@Override
	public HotelVO getHotelWithDetail(AvailabilityRQVO availability) throws Exception {
		// 取集团信息
		Chain chain = chainDao.getChainByCode(availability.getChainCode());
		if (null == chain) {
			throw new BizException("00", "Chain Code is Missing");// 集团代码错误
		}
		
		// 取酒店信息
		HotelVO hotelVO = new HotelVO();
		hotelVO.setChainId(chain.getChainId());
		hotelVO.setHotelCode(availability.getHotelCode());
		hotelVO.setLanguageCode(availability.getLanguage());
		hotelVO = hotelDao.getHotelByCodeMC(hotelVO);
		if (null == hotelVO) {
			throw new BizException("36", "PROPERTY CODE ERROR");// 酒店代码错误
		}
		availability.setHotelId(hotelVO.getHotelId());
		
		// 获取渠道
		Channel c = channelManager.getChannelByChannelCode(availability.getChannelCode());
		if (c == null) {
			throw new BizException("00", "Channel Code is Missing");// 渠道代码错误
		}
		availability.setChannelId(c.getChannelId());
		
		// 设置房型信息
		RoomTypeVO roomTypeVO = new RoomTypeVO();
		roomTypeVO.setHotelId(hotelVO.getHotelId());
		roomTypeVO.setRoomTypeCode(availability.getRoomTypeCodeList().get(0));
		roomTypeVO.setLanguage(availability.getLanguage());
		roomTypeVO = roomTypeDao.getRoomTypeByCode(roomTypeVO);
		if (null == roomTypeVO) {
			throw new BizException("2", "INVALID ROOM CATEGORY");// 没有房型
		}

		long startMili2 = System.currentTimeMillis();
		String roomTypeIdsql = " and roomTypeId = '"+roomTypeVO.getRoomTypeId()+"' ";
		availability.setRoomTypeIdsql(roomTypeIdsql);
		
		List<PriceCalc> priceCalcList = getPriceCalcList(availability);
		log.info("saul testing=>getHotelWithDetail() "+availability.getHotelCode() + " getPriceCalcList.size:" + priceCalcList.size() + " getPriceCalcList：" + (System.currentTimeMillis() - startMili2) + "豪秒");
		
		// 验证渠道绑定关系
		priceCalcList = getPriceCalcListByChannel(priceCalcList, hotelVO.getHotelId());
		//验证可预定房量和日期范围
		priceCalcList = getPriceCalcListByAvail(priceCalcList, availability.getNumberOfRooms());
		checkPriceCalcListByDetail(priceCalcList, availability);
		
		List<String> rateplanIds = new ArrayList<String>();
		Rateplan ratePlan = rateplanDao.getRateplanByRateplanCode(
				availability.getRatePlanCodeList().get(0), hotelVO.getHotelCode());
		if (null == ratePlan) {
			throw new BizException("5", "INVALID RATE CODE");// 错误房价代码
		}
		rateplanIds.add(ratePlan.getRatePlanId());

		// 取包价信息
		List<PackageVO> pvolist = 
			getPackageVOs(rateplanIds, availability.getStartDate(), availability.getEndDate(), availability.getLanguage());
		Map<String, RatePlanVO> repeatRate = new HashMap<String, RatePlanVO>();
		
		List<RoomTypeVO> roomTypeVOList = new ArrayList<RoomTypeVO>();
		List<RatePlanVO> ratePlanVOList = new ArrayList<RatePlanVO>();
		// 设置包价信息
		for (PriceCalc p : priceCalcList) {
			// 取房价包价
			List<PackageVO> rateRoomPackage = new ArrayList<PackageVO>();
			rateRoomPackage.addAll(pvolist);
			// 取房型包价
			rateRoomPackage.addAll(
					getRoomTypePackageByRateDetailId(p.getRateDetailId(), availability.getLanguage(), availability.getStartDate(), availability.getEndDate()));
			rateRoomPackage = getPackageVOsByRatePlanAndRoomType(rateRoomPackage, ratePlan.getRatePlanId(), roomTypeVO.getRoomTypeId());
			// 计算包价金额
			List<PackageVO> rpAndRtlist = 
				countPackageAmount(rateRoomPackage, availability.getStartDate(), availability.getEndDate(), availability.getNumberOfRooms(), availability.getTotalNumberOfGuests(), p);

			// 计算每日包价合计金额
			BigDecimal packageAmount = getDayPackageAmount(rpAndRtlist);
			// 设置房价日历中包信息
			p.setPackageAmount(packageAmount);
			p.setPackageVOList(rpAndRtlist);

			// 设置房价信息,重复的房价
			if (repeatRate.get(ratePlan.getRatePlanId()) != null) {
				repeatRate.get(ratePlan.getRatePlanId()).getPackageVOList().addAll(rateRoomPackage);
			} else {
				RatePlanI18n ratePlanI18n = 
					rateplanDao.getRatePlanI18nById(ratePlan.getRatePlanId(), availability.getLanguage());
				RatePlanVO rateplanVO = new RatePlanVO();
				rateplanVO.setRp(ratePlan);
				rateplanVO.setRpi18n(ratePlanI18n);
				rateplanVO.setPackageVOList(rateRoomPackage);
				repeatRate.put(ratePlan.getRatePlanId(), rateplanVO);
				ratePlanVOList.add(rateplanVO);
			}
		}
		
		// 同一房价去重复包价
		for (int i = 0; i < ratePlanVOList.size(); i++) {
			List<String> packageids = new ArrayList<String>();
			List<PackageVO> pvoListP = new ArrayList<PackageVO>();
			for (PackageVO pvo : ratePlanVOList.get(i).getPackageVOList()) {
				if (!packageids.contains(pvo.getPackageId())) {
					packageids.add(pvo.getPackageId());
					pvoListP.add(pvo);
				}
			}
			ratePlanVOList.get(i).setPackageVOList(pvoListP);
		}

		// 此处获取时间段内的最小可用房量
		int available = rsvtypeChannelManager.queryRsvtypeChannelMinAvailable(
				roomTypeVO.getRoomTypeCode(), hotelVO.getHotelCode(), availability.getStartDate(), availability.getEndDate(), availability.getChannelCode(),ratePlan.getRatePlanCode());
		// 如果房量大于零
		if (available > 0) {
			roomTypeVO.setAvailable(available);
			roomTypeVOList.add(roomTypeVO);
		}
	
		hotelVO.setRatePlanVOList(ratePlanVOList);
		hotelVO.setRoomTypeVOList(roomTypeVOList);
		hotelVO.setPriceCalcList(priceCalcList);
		return hotelVO;
	}

	public List<PackageVO> getRoomTypePackageByRateDetailId(String rateDetailId, String language, Date start, Date end) throws Exception {
		List<PackageVO> pvoRtlist = new ArrayList<PackageVO>();
		// 取房价房型包价
		pvoRtlist = packageManager.getRoomTypePackageByRatePlanId(rateDetailId, language);
		// 根据packageCode去掉重复
		List<PackageVO> list = new ArrayList<PackageVO>();
		Map<String, String> map = new HashMap<String, String>();
		for (PackageVO vo : pvoRtlist) {
			String key = vo.getRoomTypeId() + "_" + vo.getPackageCode();
			// 如果存在了
			if (!map.containsKey(key)) {
				list.add(vo);
				map.put(key, key);
			}
		}
		// 验证数据完整性
		return getRatePlanPackageVOs(list, start, end);
	}

	/**
	 * 根据ratePlanIdList获取所有的包价信息
	 */
	public List<PackageVO> getPackageVOs(List<String> ratePlanIds, Date startDate, Date endDate, String language) throws Exception {
		List<PackageVO> packagevoList = packageManager.getPackageByRatePlan(ratePlanIds, language);
		// 验证数据完整性
		packagevoList = getRatePlanPackageVOs(packagevoList, startDate, endDate);
		return packagevoList;
	}

	/**
	 * 验证PackageVO的数据完整性
	 */
	private List<PackageVO> getRatePlanPackageVOs(List<PackageVO> packagevoList, Date startDate, Date endDate) throws Exception {
		List<PackageVO> list = new ArrayList<PackageVO>();
		for (PackageVO pvo : packagevoList) {
			if (isEmptyPackage(pvo) && isValidPackage(pvo, startDate, endDate)) {
				list.add(pvo);
			}
		}
		return list;
	}

	/**
	 * 判断packageVO中是否有必填项为空 true:表示数据完整；false：数据不完整
	 */
	private boolean isEmptyPackage(PackageVO vo) {
		if (vo.getIsExtraCharge()) {
			if (null == vo.getCalculation() || "".equals(vo.getCalculation())) {// 计算方式
				return false;
			}
			if (null == vo.getRule() || "".equals(vo.getRule())) {// 计算规则
				return false;
			}
			if (null == vo.getBasicType() || "".equals(vo.getBasicType())) {// 计算类型
				return false;
			}
			if (vo.getCalculation() == 4) {// 星期
				if (!vo.getIsApplyToMonday() && !vo.getIsApplyToTuesday() && !vo.getIsApplyToWednesday() && !vo.getIsApplyToThursday() && !vo.getIsApplyToFriday() && !vo.getIsApplyToSaturday() && !vo.getIsApplyToSunday()) {
					return false;
				}
			}
			if (vo.getCalculation() == 5) {// 日期
				if (null == vo.getBeginDate() || null == vo.getBeginDate()) {
					return false;
				}
			}
			if (vo.getBasicType() == 1) {// 比率
				if (new BigDecimal(vo.getPercent()).compareTo(new BigDecimal("0")) <= 0) {
					return false;
				}
			}
			if (vo.getBasicType() == 2) {// 金额
				if (new BigDecimal(vo.getAmount()).compareTo(new BigDecimal("0")) <= 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 检查package是否在查询时间段内有效 true：表示有效，false：无效
	 */
	private boolean isValidPackage(PackageVO vo, Date startDate, Date endDate) {
		endDate = new Date(endDate.getTime() - 1000 * 24 * 60 * 60);// 取前一天的日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int days = DateUtil.dateDiff(startDate, endDate);
		for (int i = 0; i <= days; i++) {
			if (i > 0) {
				cal.add(Calendar.DATE, 1);
			}
			if (vo.getIsExtraCharge()) {
				if (vo.getCalculation() == 4) {
					int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
					if (vo.getIsApplyToSunday() && week == 0) {
						return true;
					}
					if (vo.getIsApplyToMonday() && week == 1) {
						return true;
					}
					if (vo.getIsApplyToTuesday() && week == 2) {
						return true;
					}
					if (vo.getIsApplyToWednesday() && week == 3) {
						return true;
					}
					if (vo.getIsApplyToThursday() && week == 4) {
						return true;
					}
					if (vo.getIsApplyToFriday() && week == 5) {
						return true;
					}
					if (vo.getIsApplyToSaturday() && week == 6) {
						return true;
					}
				} else if (vo.getCalculation() == 5) {
					Date date = cal.getTime();
					if ((vo.getBeginDate().before(date) || vo.getBeginDate().equals(date)) && (vo.getEndDate().after(date) || vo.getEndDate().equals(date))) {
						return true;
					}
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据房价Id和房型Id获取所有的包价信息
	 */
	public List<PackageVO> getPackageVOsByRatePlanAndRoomType(List<PackageVO> voList, String ratePlanId, String roomTypeId) throws Exception {
		List<PackageVO> list = new ArrayList<PackageVO>();
		if (!voList.isEmpty()) {
			for (PackageVO vo : voList) {

				boolean flag = false;
				for (PackageVO packageVo : list) {
					if (packageVo.getPackageCode().equals(vo.getPackageCode())) {
						flag = true;
					}
				}
				// 如果存在重复
				if (flag)
					continue;

				PackageVO vo2 = new PackageVO();
				if (null == vo.getRoomTypeId()) {
					if (vo.getRatePlanId().equals(ratePlanId)) {
						PropertyUtils.copyProperties(vo2, vo);
						list.add(vo2);
					}
				} else if (null == vo.getRatePlanId()) {
					if (vo.getRoomTypeId().equals(roomTypeId)) {
						PropertyUtils.copyProperties(vo2, vo);
						list.add(vo2);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 计算包价金额
	 */
	public List<PackageVO> countPackageAmount(List<PackageVO> pvolist, Date startDate, Date endDate, Integer numberOfRooms, Integer totalNumberOfGuests, PriceCalc p) throws Exception {
		if (pvolist == null) {
			return new ArrayList<PackageVO>();
		}
		endDate = new Date(endDate.getTime() - 1000 * 24 * 60 * 60);// 取前一天的日期
		if (numberOfRooms == null) {
			numberOfRooms = 0;
		}
		if (totalNumberOfGuests == null) {
			totalNumberOfGuests = 0;
		}
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		int week = 0;
		try {
			date = sdf.parse(p.getDate());
			cal.setTime(date);
			week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		} catch (ParseException e) {
			throw new BizException("43", "INVALID DATE FOMAT");// 日期格式化失败
		}
		BigDecimal amount = null;
		for (PackageVO vo : pvolist) {
			amount = new BigDecimal("0");
			if (vo.getIsExtraCharge()) {
				if (vo.getCalculation() == 1) {// 每晚
					if (vo.getRule() == 1) {// 固定价格
						if (vo.getBasicType() == 1) {
							amount = new BigDecimal(vo.getPercent()).multiply(p.getAmount()).multiply(new BigDecimal("0.01")).multiply(new BigDecimal(numberOfRooms));
						} else if (vo.getBasicType() == 2) {
							amount = new BigDecimal(vo.getAmount());
						}
					} else if (vo.getRule() == 4) {// 人数
						amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(totalNumberOfGuests));
					} else if (vo.getRule() == 5) {// 房间数
						amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(numberOfRooms));
					}
				} else if (vo.getCalculation() == 2) {// 到店日
					if (date.equals(startDate)) {
						if (vo.getRule() == 1) {// 固定价格
							if (vo.getBasicType() == 1) {
								amount = new BigDecimal(vo.getPercent()).multiply(p.getAmount()).multiply(new BigDecimal("0.01")).multiply(new BigDecimal(numberOfRooms));
							} else if (vo.getBasicType() == 2) {
								amount = new BigDecimal(vo.getAmount());
							}
						} else if (vo.getRule() == 4) {// 人数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(totalNumberOfGuests));
						} else if (vo.getRule() == 5) {// 房间数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(numberOfRooms));
						}
					}
				} else if (vo.getCalculation() == 3) {// 离店日
					if (date.equals(endDate)) {
						if (vo.getRule() == 1) {// 固定价格
							if (vo.getBasicType() == 1) {
								amount = new BigDecimal(vo.getPercent()).multiply(p.getAmount()).multiply(new BigDecimal("0.01")).multiply(new BigDecimal(numberOfRooms));
							} else if (vo.getBasicType() == 2) {
								amount = new BigDecimal(vo.getAmount());
							}
						} else if (vo.getRule() == 4) {// 人数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(totalNumberOfGuests));
						} else if (vo.getRule() == 5) {// 房间数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(numberOfRooms));
						}
					}
				} else if (vo.getCalculation() == 4) {// 星期
					if ((vo.getIsApplyToSunday() && week == 0) || (vo.getIsApplyToMonday() && week == 1) || (vo.getIsApplyToTuesday() && week == 2) || (vo.getIsApplyToWednesday() && week == 3) || (vo.getIsApplyToThursday() && week == 4) || (vo.getIsApplyToFriday() && week == 5) || (vo.getIsApplyToSaturday() && week == 6)) {
						if (vo.getRule() == 1) {// 固定价格
							if (vo.getBasicType() == 1) {
								amount = new BigDecimal(vo.getPercent()).multiply(p.getAmount()).multiply(new BigDecimal("0.01")).multiply(new BigDecimal(numberOfRooms));
							} else if (vo.getBasicType() == 2) {
								amount = new BigDecimal(vo.getAmount());
							}
						} else if (vo.getRule() == 4) {// 人数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(totalNumberOfGuests));
						} else if (vo.getRule() == 5) {// 房间数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(numberOfRooms));
						}
					}
				} else if (vo.getCalculation() == 5) {// 日期
					if ((vo.getBeginDate().before(date) || vo.getBeginDate().equals(date)) && (vo.getEndDate().after(date) || vo.getEndDate().equals(date))) {
						if (vo.getRule() == 1) {// 固定价格
							if (vo.getBasicType() == 1) {
								amount = new BigDecimal(vo.getPercent()).multiply(p.getAmount()).multiply(new BigDecimal("0.01")).multiply(new BigDecimal(numberOfRooms));
							} else if (vo.getBasicType() == 2) {
								amount = new BigDecimal(vo.getAmount());
							}
						} else if (vo.getRule() == 4) {// 人数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(totalNumberOfGuests));
						} else if (vo.getRule() == 5) {// 房间数
							amount = new BigDecimal(vo.getAmount()).multiply(new BigDecimal(numberOfRooms));
						}
					}
				}
			} else {
				amount = new BigDecimal("0");
			}
			vo.setPackageAmount(amount.toString());
		}
		List<PackageVO> list = new ArrayList<PackageVO>();
		for (PackageVO pvo : pvolist) {
			if (new BigDecimal(pvo.getPackageAmount()).compareTo(new BigDecimal("0")) > 0) {
				list.add(pvo);
			}
		}
		return list;
	}

	/**
	 * 获取每日包价合计金额
	 */
	public BigDecimal getDayPackageAmount(List<PackageVO> pvolist) {
		BigDecimal amount = new BigDecimal("0");
		for (PackageVO vo : pvolist) {
			amount = amount.add(new BigDecimal(vo.getPackageAmount()));
		}
		return amount;
	}

	/**
	 * 获取客户类型
	 * 
	 * @param customType
	 * @return
	 */
	private String getCustomType(String customType) {
		if ("COMPANY".equals(customType)) {
			return "CORPORATE";
		} else if ("TRAVEL".equals(customType)) {
			return "TRAVEL_AGENT";
		}
		return customType;
	}
}
