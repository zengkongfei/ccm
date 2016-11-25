package com.ccm.api.service.ratePlan.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.ratePlan.RestrictionCalcDao;
import com.ccm.api.dao.ratePlan.mongodb.RoomMsgDaoMongo;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RestrictionCalc;
import com.ccm.api.model.ratePlan.vo.RoomStatusAndProductSwitchCriteria;
import com.ccm.api.model.ratePlan.vo.RoomStatusAndProductSwitchResult;
import com.ccm.api.model.sell.vo.ProductionJsonVO;
import com.ccm.api.model.sell.vo.ProductionVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.ratePlan.RestrictionCalcManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("restrictionCalcManager")
public class RestrictionCalcManagerImpl extends GenericManagerImpl<RestrictionCalc, String> implements RestrictionCalcManager {
    
    @Resource
    private RestrictionCalcDao restrictionCalcDao;
    @Resource
    private ChannelgoodsManager channelgoodsManager;
    @Resource
    private ChannelManager channelManager;
    @Resource
	private RoomMsgDaoMongo roomMsgDaoMongo;
    @Resource
	private PriceCalcManager priceCalcManager;
    
    @Autowired
    public RestrictionCalcManagerImpl(RestrictionCalcDao genericDao) {
        super(genericDao);
    }

    @Override
    public List<List<List<List<ProductionJsonVO>>>> getProductionCalendars(ProductionVO vo) {
    	long s1 = System.currentTimeMillis();
    	
        List<List<List<List<ProductionJsonVO>>>> listlistlistlist = new ArrayList<List<List<List<ProductionJsonVO>>>>();
        List<List<List<ProductionJsonVO>>> listlistlist = null;
        List<List<ProductionJsonVO>> listlist = null;
        List<ProductionJsonVO> jsonList = null;
        
        String[] channelCodeArray = vo.getChannelCodeArray();
        String[] ratePlanCodeArray = vo.getRatePlanCodeArray();
        String[] roomTypeCodeArray = vo.getRoomTypeCodeArray();
        List<RestrictionCalc> restrictionCalcList = restrictionCalcDao.searchRestrictionCalcOnOff2(vo);
        long e1 = System.currentTimeMillis();
        log.info("searchRestrictionCalcOnOff2:"+(e1-s1)+"ms");
        
        //查询渠道绑定关系
        ChannelGoodsVO cgvo = new ChannelGoodsVO();
        cgvo.setHotelId(vo.getHotelId());
        cgvo.setChannelCodes(channelCodeArray);
        cgvo.setRatePlanCodes(ratePlanCodeArray);
        cgvo.setRoomTypeCodes(roomTypeCodeArray);
        cgvo.setStatus(ChannelGoodsStatus.publish);
        List<ChannelGoodsVO> channelGoodsList = channelgoodsManager.getEnabledChannelGoods2(cgvo);
        long e2 = System.currentTimeMillis();
        log.info("getEnabledChannelGoods2:"+(e2-e1)+"ms");
        
        Date startDate = vo.getStartDate();
        Date endDate = vo.getEndDate();
        int dateLength = DateUtil.dateDiff(startDate, endDate);
        
        ProductionJsonVO jsvo = null;
        for(String channelCode:channelCodeArray){
            List<String> ratePlanCodes = getChannelRatePlan(channelCode, channelGoodsList);//取渠道下所有房价
            ratePlanCodes = getListSameElements(ratePlanCodes, ratePlanCodeArray);//取参数选中房价
            listlistlist = new ArrayList<List<List<ProductionJsonVO>>>();
            for(String ratePlanCode:ratePlanCodes){
            	listlist = new ArrayList<List<ProductionJsonVO>>();
                List<String> roomTypeCodes = getChannelRoomType(channelCode, ratePlanCode, channelGoodsList);//取房价下所有房型
                roomTypeCodes = getListSameElements(roomTypeCodes, roomTypeCodeArray);//取参数中选中房型
                for(String roomTypeCode:roomTypeCodes){
                	jsonList = new ArrayList<ProductionJsonVO>();
                    for (int i = 0; i <= dateLength; i++) {
                    	Date now = DateUtil.addDays(startDate, i);
                    	RestrictionCalc rc = new RestrictionCalc();
                    	rc.setChainCode(vo.getChainCode());
                    	rc.setChannelCode(channelCode);
                    	rc.setHotelCode(vo.getHotelCode());
                    	rc.setRatePlanCode(ratePlanCode);
                    	rc.setRoomTypeCode(roomTypeCode);
                    	rc.setDate(now);
                    	rc.setOnOff(1);
                    	
                    	jsvo = getProductionJsonVO(restrictionCalcList, rc);
                    	jsonList.add(jsvo);
					}
                    listlist.add(jsonList);
                }
                listlistlist.add(listlist);
            }
            listlistlistlist.add(listlistlist);
        }    
        long e3 = System.currentTimeMillis();
        log.info("buildList:"+(e3-e2)+"ms");
        
        return listlistlistlist;
    }

	@Override
    public void updateRestrictionCalcOnOff(RestrictionCalc rc) {
    	// 检查是否存在，不存在保存；存在修改。
        RestrictionCalc restrictionCalc = restrictionCalcDao.getRestrictionCalc(rc);
        if (null==restrictionCalc) {
            save(rc);
        } else {
        	rc.setRestrictionCalcId(restrictionCalc.getRestrictionCalcId());
        	restrictionCalcDao.updateRestrictionCalcOnOff(rc);
        }
    }
    
    @Override
    public void batchUpdateRestrictionCalcOnOff(ProductionVO vo){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = new GregorianCalendar();
        String channelCodes = vo.getChannelCode();
        String ratePlanCodes = vo.getRatePlanCode();
        String roomTypeCodes = vo.getRoomTypeCode();
        String[] channelCodeArray = channelCodes.split(",");
        String[] ratePlanCodeArray = ratePlanCodes.split(",");
        String[] roomTypeCodeArray = roomTypeCodes.split(",");
        String weeks = vo.getWeeks();
        Date startDate = vo.getStartDate();
        Date endDate = vo.getEndDate();
        int dateLength = DateUtil.dateDiff(startDate, endDate);
        
        List<RoomMsg> rmList = new ArrayList<RoomMsg>();
        
        //查询渠道绑定关系
        ChannelGoodsVO cgvo = new ChannelGoodsVO();
        cgvo.setHotelId(vo.getHotelId());
        cgvo.setChannelCodes(channelCodeArray);
        cgvo.setRatePlanCodes(ratePlanCodeArray);
        cgvo.setRoomTypeCodes(roomTypeCodeArray);
        cgvo.setStatus(ChannelGoodsStatus.publish);
        List<ChannelGoodsVO> channelGoodsList = channelgoodsManager.getEnabledChannelGoods2(cgvo);
        
        //查询价格detail
		PriceCalc pc = new PriceCalc();
		pc.setHotelId(vo.getHotelId());
		pc.setStartDate(DateUtil.convertDateToString(vo.getStartDate()));
		pc.setEndDate(DateUtil.convertDateToString(vo.getEndDate()));
		
		List<String> rtCodeList = Arrays.asList(roomTypeCodeArray);
		pc.setRoomTypeCodeList(rtCodeList);

		List<String> rateCodeList = Arrays.asList(ratePlanCodeArray);
		pc.setRatePlanCodeList(rateCodeList);
		HashSet<String> roomRateDateSet = priceCalcManager.getRateDetailRoomRateDate(pc);

        for (String channelCode : channelCodeArray) {
        	Channel channel = channelManager.getChannelByChannelCode(channelCode);
            List<String> ratePlanCodeList = getChannelRatePlan(channelCode, channelGoodsList);//取渠道下所有房价
            ratePlanCodeList = getListSameElements(ratePlanCodeList, ratePlanCodeArray);//取参数选中房价
            for (String ratePlanCode : ratePlanCodeList) {
                List<String> roomTypeCodeList = getChannelRoomType(channelCode, ratePlanCode, channelGoodsList);//取房价下所有房型
                roomTypeCodeList = getListSameElements(roomTypeCodeList, roomTypeCodeArray);//取参数中选中房型
                for (String roomTypeCode : roomTypeCodeList) {
                    for (int i = 0; i <= dateLength; i++) {
                        cal.setTime(startDate);
                        cal.add(Calendar.DATE, i);
                        // 判断当前日期是否有效，无效不保存。
                        int week = cal.get(Calendar.DAY_OF_WEEK);
                        if (weeks.indexOf(String.valueOf(week)) < 0) {
                            continue;
                        }
                        RestrictionCalc restrictionCalc = new RestrictionCalc();
                        restrictionCalc.setChannelCode(channelCode);
                        restrictionCalc.setChainCode(vo.getChainCode());
                        restrictionCalc.setHotelCode(vo.getHotelCode());
                        restrictionCalc.setRatePlanCode(ratePlanCode);
                        restrictionCalc.setRoomTypeCode(roomTypeCode);
                        restrictionCalc.setStartDate(sdf.format(cal.getTime()));
                        restrictionCalc.setEndDate(sdf.format(cal.getTime()));
                        restrictionCalc.setDate(cal.getTime());
                        restrictionCalc.setOnOff(vo.getOnOff());
                        // 检查是否存在，不存在保存；存在修改。
                        RestrictionCalc rc = restrictionCalcDao.getRestrictionCalc(restrictionCalc);
                        if (null==rc) {
                            save(restrictionCalc);
                        } else {
                        	restrictionCalc.setRestrictionCalcId(rc.getRestrictionCalcId());
                        	restrictionCalcDao.updateRestrictionCalcOnOff(restrictionCalc);
                        }
                    	log.info("restrictionCalcManager.batchUpdateRestrictionCalcOnOff->"+AdsMessage.ADSTYPE_AVAILUPDATE);
                        //推送开关消息
                        if(channel!=null && channel.getPushRtav() && channel.isPush(cal.getTime())){
                        	if (CommonUtil.isNotEmpty(roomRateDateSet)
                    				&& roomRateDateSet.contains(roomTypeCode + ratePlanCode + sdf.format(cal.getTime()))) {
                        		RoomMsg rm = new RoomMsg();
                                rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
                                rm.setOnOff(vo.getOnOff().intValue()==1);
                                rm.setChannelCode(channelCode);
                                rm.setChainCode(vo.getChainCode());
                                rm.setHotelCode(vo.getHotelCode());
                                rm.setRoomTypeCode(roomTypeCode);
                                rm.setRateCode(ratePlanCode);
                                rm.setStartDate(sdf.format(cal.getTime()));
                        		rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
                        		rm.setCreatedTime(new Date());
                                rmList.add(rm);
                    		}
                        }
                    }
                }
            }
        }
        roomMsgDaoMongo.batchSave(rmList);
    }
    
    private ProductionJsonVO getProductionJsonVO(
			List<RestrictionCalc> restrictionCalcList, RestrictionCalc rc) {
    	
    	if(restrictionCalcList != null){
    		for(RestrictionCalc r:restrictionCalcList){
                if(rc.getChannelCode().equals(r.getChannelCode())
               		 &&rc.getRatePlanCode().equals(r.getRatePlanCode())
                        &&rc.getRoomTypeCode().equals(r.getRoomTypeCode())
                        &&DateUtil.convertDateToString(r.getDate()
                       		 ).equals(DateUtil.convertDateToString(rc.getDate()))){
                    ProductionJsonVO jsonvo = new ProductionJsonVO();
                    jsonvo.setChannelCode(r.getChannelCode());
                    jsonvo.setChainCode(r.getChainCode());
                    jsonvo.setHotelCode(r.getHotelCode());
                    jsonvo.setRatePlanCode(r.getRatePlanCode());
                    jsonvo.setRoomTypeCode(r.getRoomTypeCode());
                    jsonvo.setDate(DateUtil.convertDateToString(r.getDate()));
                    jsonvo.setOnOff(r.getOnOff());
                    return jsonvo;
                }
           }
    	}
    	
    	ProductionJsonVO jsonvo = new ProductionJsonVO();
        jsonvo.setChannelCode(rc.getChannelCode());
        jsonvo.setChainCode(rc.getChainCode());
        jsonvo.setHotelCode(rc.getHotelCode());
        jsonvo.setRatePlanCode(rc.getRatePlanCode());
        jsonvo.setRoomTypeCode(rc.getRoomTypeCode());
        jsonvo.setDate(DateUtil.convertDateToString(rc.getDate()));
        jsonvo.setOnOff(rc.getOnOff());
        return jsonvo;
	}
    
    /**
     * 根据渠道代码取渠道下所有房价
     */
    private List<String> getChannelRatePlan(String channelCode,List<ChannelGoodsVO> channelGoodsList) {
        List<String> ratePlanCodeList = new ArrayList<String>();
        Map<String, String> map = new HashMap<String, String>();
        if(!channelGoodsList.isEmpty()){
            for(ChannelGoodsVO crp:channelGoodsList){
            	if(crp.getChannelCode().equalsIgnoreCase(channelCode)
            			&&!map.containsKey(crp.getRatePlanCode())){
            		ratePlanCodeList.add(crp.getRatePlanCode());
            		map.put(crp.getRatePlanCode(), crp.getRatePlanCode());
            	}
            }
        }
        return ratePlanCodeList;
    }
    
    /**
     * 根据渠道代码取渠道下所有房型
     */
    private List<String> getChannelRoomType(String channelCode, String ratePlanCode, List<ChannelGoodsVO> channelGoodsList) {
        List<String> roomTypeCodeList = new ArrayList<String>();
        Map<String, String> map = new HashMap<String, String>();
        if(!channelGoodsList.isEmpty()){
            for(ChannelGoodsVO crp:channelGoodsList){
            	if(crp.getChannelCode().equalsIgnoreCase(channelCode)
            			&&crp.getRatePlanCode().equalsIgnoreCase(ratePlanCode)
            			&&!map.containsKey(crp.getRatePlanCode())){
            		roomTypeCodeList.add(crp.getRoomTypeCode());
            		map.put(crp.getRoomTypeCode(), crp.getRoomTypeCode());
            	}
                
            }
        }
        return roomTypeCodeList;
    }

    /**
     * 取两个list中相同元素
     */
    private List<String> getListSameElements (List<String> list1,String[] list2) {
        List<String> list = new ArrayList<String>();
        Map<String,Integer> mapStr = new HashMap<String,Integer>();
        for(String str:list1){
            mapStr.put(str, 1);
        }
        for(String str:list2){
            if(null!=mapStr.get(str)){
                list.add(str);
            }
        }
        return list;
    }

    @Override
    public List<RestrictionCalc> searchRestrictionCalc(RestrictionCalc rcalc) {
        return restrictionCalcDao.searchRestrictionCalc(rcalc);
    }

	@Override
	public RoomStatusAndProductSwitchResult searchRoomStatusAndProductSwitchs(
			RoomStatusAndProductSwitchCriteria criteria) {
		return restrictionCalcDao.searchRoomStatusAndProductSwitchs(criteria);
	}
}
