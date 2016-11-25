package com.ccm.api.dao.ratePlan.ibatis;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.ratePlan.RatePackageDao;
import com.ccm.api.dao.ratePlan.RestrictionCalcDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.ratePlan.Package;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RestrictionCalc;
import com.ccm.api.model.ratePlan.RoomStatusAndProductSwitch;
import com.ccm.api.model.ratePlan.vo.RoomStatusAndProductSwitchCriteria;
import com.ccm.api.model.ratePlan.vo.RoomStatusAndProductSwitchResult;
import com.ccm.api.model.sell.vo.ProductionVO;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.ratePlan.RateAmountManager;
import com.ccm.api.service.ratePlan.RateDetailMongoManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Repository("restrictionCalcDao")
public class RestrictionCalcDaoibatis extends GenericDaoiBatis<RestrictionCalc, String> implements RestrictionCalcDao {

	public RestrictionCalcDaoibatis() {
		super(RestrictionCalc.class);
	}

	@Resource
	private RateDetailMongoManager rateDetailMongoManager;
	@Resource
	private PriceCalcManager priceCalcManager;
	@Resource
	private RateAmountManager rateAmountManager;
	@Resource
	private ChannelDao channelDao;
	@Resource
	private RateplanDao rateplanDao;
	@Resource
	private RatePackageDao ratePackageDao;
	@SuppressWarnings("unchecked")
	@Override
	public List<RestrictionCalc> searchRestrictionCalcOnOff(ProductionVO vo) {
		return getSqlMapClientTemplate().queryForList("searchRestrictionCalcOnOff", vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RestrictionCalc> searchRestrictionCalcOnOff2(ProductionVO vo) {
		return getSqlMapClientTemplate().queryForList("searchRestrictionCalcOnOff2", vo);
	}

	@Override
	public RestrictionCalc getRestrictionCalc(RestrictionCalc rc) {
		return (RestrictionCalc) getSqlMapClientTemplate().queryForObject("getRestrictionCalc", rc);
	}

	@Override
	public void updateRestrictionCalcOnOff(RestrictionCalc rc) {
		getSqlMapClientTemplate().update("updateRestrictionCalcOnOff", rc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PriceCalc> searchRestrictionCalcAndRate(PriceCalc pc,Boolean isUpdateAmount) {
		PriceCalc pcNew = null;
		try {
			pcNew = (PriceCalc) BeanUtils.cloneBean(pc);
			Date checkOutDate = DateUtil.convertStringToDate(pc.getEndDate());
			pcNew.setEndDate(DateUtil.convertDateToString(DateUtil.addDays(checkOutDate, -1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 工作表存在
		log.info("priceCalc:" + JSON.toJSONString(pcNew));
//		if (rateDetailMongoManager.isExistsRateDetailMongo(pcNew)) {
		log.info("existsRateDetail");
		// 产生临时房价日历
		List<PriceCalc> pcList = priceCalcManager.getPriceFromRateDetail(pcNew);
		
		Channel channel=channelDao.getChannelByChannelCode(pcNew.getChannelCode());
		if(channel.getUseAmountAfterTax()==true){
			Date svcOrTaxDate=null;
			try {
				svcOrTaxDate=DateUtil.convertStringToDate(pcNew.getEndDate());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				throw new BizException("4");
			}
			
			if(CommonUtil.isNotEmpty(pcList)){
				for(PriceCalc pcal:pcList){
					pcal.setHotelCode(pcNew.getHotelCode());
					pcal.setHotelId(pcNew.getHotelId());
					pcal.setAmountAfterTax(pcal.getAmount());
					rateAmountManager.setAmountAfterTaxForOrder(pcal,svcOrTaxDate,isUpdateAmount);
				}
			}
		}
		log.info("restrictionCalcOnOff:" + pcNew.getOnOff());
		if (pcNew.getOnOff() != null && pcList != null && !pcList.isEmpty()) {
			if (pcNew.getOnOff() == 1) {
				return pcList;
			} else {
				return null;
			}
		}
		return pcList;
	}
	
	@Override
	public List<PriceCalc> searchRestrictionCalcAndRateOws(PriceCalc pc,Boolean isUpdateAmount) {
		PriceCalc pcNew = null;
		try {
			pcNew = (PriceCalc) BeanUtils.cloneBean(pc);
			Date checkOutDate = DateUtil.convertStringToDate(pc.getEndDate());
			pcNew.setEndDate(DateUtil.convertDateToString(DateUtil.addDays(checkOutDate, -1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 工作表存在
		log.info("priceCalc:" + JSON.toJSONString(pcNew));
		// 产生临时房价日历
		List<PriceCalc> pcList = priceCalcManager.getPriceFromRateDetail(pcNew);
		
		Channel channel=channelDao.getChannelByChannelCode(pcNew.getChannelCode());
		if(channel.getUseAmountAfterTax()==true){
			Date svcOrTaxDate=null;
			try {
				svcOrTaxDate=DateUtil.convertStringToDate(pcNew.getEndDate());
			} catch (ParseException e1) {
				throw new BizException("4");
			}
				
			if(CommonUtil.isNotEmpty(pcList)){
				PriceCalc pcal_maxDate = pcList.get(0);
				for(PriceCalc pcal:pcList){
					try {
						if((DateUtil.convertStringToDate(pcal.getDate())).after((DateUtil.convertStringToDate(pcal_maxDate.getDate())))){
							pcal_maxDate = pcal;
						}
					} catch (ParseException e) {
						throw new BizException("99");
					}
				}
				for(PriceCalc pcal:pcList){
					pcal.setUseAmountAfterTax(channel.getUseAmountAfterTax());
					pcal.setHotelCode(pcNew.getHotelCode());
					pcal.setHotelId(pcNew.getHotelId());
					rateAmountManager.setAmountAfterTaxForOrderOws(pcal,svcOrTaxDate,isUpdateAmount);
					try {
						if((DateUtil.convertStringToDate(pcal.getDate())).before((DateUtil.convertStringToDate(pcal_maxDate.getDate())))){
							pcal.setTaxFeeS(new BigDecimal("0"));//固定税费 每间房每次入住
							pcal.setTaxFeePS(new BigDecimal("0"));//固定税费 每人每次入住
						}
					} catch (ParseException e) {
						throw new BizException("99");
					}
				}
			}
		}
		log.info("restrictionCalcOnOff:" + pcNew.getOnOff());
		if (pcNew.getOnOff() != null && pcList != null && !pcList.isEmpty()) {
			if (pcNew.getOnOff() == 1) {
				return pcList;
			} else {
				return null;
			}
		}
		return pcList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PriceCalc> searchRestrictionCalcAndRateForWBE(PriceCalc pc){
		PriceCalc pcNew = null;
		try {
			pcNew = (PriceCalc) BeanUtils.cloneBean(pc);
			Date checkOutDate = DateUtil.convertStringToDate(pc.getEndDate());
			pcNew.setEndDate(DateUtil.convertDateToString(DateUtil.addDays(checkOutDate, -1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 工作表存在
		log.info("priceCalc:" + JSON.toJSONString(pcNew));
//		if (rateDetailMongoManager.isExistsRateDetailMongo(pcNew)) {
			log.info("existsRateDetail");
			// 产生临时房价日历
			List<PriceCalc> pcList = priceCalcManager.getPriceFromRateDetail(pcNew);
			
			Channel channel=channelDao.getChannelByChannelCode(pcNew.getChannelCode());
			if(channel.getUseAmountAfterTax()==true){
				if(CommonUtil.isNotEmpty(pcList)){
					for(PriceCalc pcal:pcList){
						pcal.setHotelCode(pcNew.getHotelCode());
						pcal.setHotelId(pcNew.getHotelId());
						rateAmountManager.setAmountAfterTaxForCalendar(pcal);
					}
				}
			}
			log.info("restrictionCalcOnOff:" + pcNew.getOnOff());
			if (pcNew.getOnOff() != null && pcList != null && !pcList.isEmpty()) {
				if (pcNew.getOnOff() == 1) {
					return pcList;
				} else {
					return null;
				}
			}
			return pcList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<RestrictionCalc> searchRestrictionCalc(RestrictionCalc rcalc) {
		return getSqlMapClientTemplate().queryForList("searchRestrictionCalc", rcalc);
	}

	@Override
	public Integer getRestrictionCalcCountByObj(RestrictionCalc rc) {
		return (Integer)getSqlMapClientTemplate().queryForObject("getRestrictionCalcCountByObj", rc);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RestrictionCalc> getRestrictionCalcByObj(RestrictionCalc rc) {
		return getSqlMapClientTemplate().queryForList("getRestrictionCalcByObj", rc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public RoomStatusAndProductSwitchResult searchRoomStatusAndProductSwitchs(RoomStatusAndProductSwitchCriteria criteria) {
		RoomStatusAndProductSwitchResult searchResult = new RoomStatusAndProductSwitchResult();

		// 导出excel标识
		if (criteria.isExcelFlag()) {
			List<RoomStatusAndProductSwitch> resultList = getSqlMapClientTemplate().queryForList("searchRoomStatusAndProductSwitchs", criteria);
			searchResult.setResultList(resultList);

		} else {
			// 查询一页数据
			List<RoomStatusAndProductSwitch> resultList = getSqlMapClientTemplate().queryForList("searchRoomStatusAndProductSwitchs", criteria);
			searchResult.setResultList(resultList);

			// 得到总条数
			Integer totalCount = (Integer) getSqlMapClientTemplate().queryForObject("searchRoomStatusAndProductSwitchsCount", criteria);
			searchResult.setTotalCount(totalCount);
		}
		return searchResult;
	}

	@Override
	public List<RestrictionCalc> searchRestrictionCalcOnOffForPush(
			ProductionVO product) {
		return getSqlMapClientTemplate().queryForList("searchRestrictionCalcOnOffForPush", product);
	}
}
