package com.ccm.mc.webapp.action.roomRate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.compass.core.util.StringUtils;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.ccm.api.dao.pms.utils.DateUtils;
import com.ccm.api.dao.ratePlan.mongodb.RateDetailVODaoMongo;
import com.ccm.api.dao.ratePlan.mongodb.RoomMsgDaoMongo;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.HotelCancelVO;
import com.ccm.api.model.hotel.vo.HotelGuaranteeVO;
import com.ccm.api.model.hotel.vo.HotelPackageCreteria;
import com.ccm.api.model.hotel.vo.HotelPackageResult;
import com.ccm.api.model.hotel.vo.HotelPackageVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RateDetail;
import com.ccm.api.model.ratePlan.RatePlanI18n;
import com.ccm.api.model.ratePlan.RatePlanTemplateI18n;
import com.ccm.api.model.ratePlan.RoomRate;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.RateDetailVOCriteria;
import com.ccm.api.model.ratePlan.vo.RateDetailVOResult;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateVO;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.sell.vo.MarketCreteria;
import com.ccm.api.model.sell.vo.MarketVO;
import com.ccm.api.model.sell.vo.SourceCreteria;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.channel.RateplanAsyncManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.CustomManager;
import com.ccm.api.service.hotel.HotelCancelManager;
import com.ccm.api.service.hotel.HotelGuaranteeManager;
import com.ccm.api.service.hotel.HotelPackageManager;
import com.ccm.api.service.ratePlan.CallAddPriceCalcManager;
import com.ccm.api.service.ratePlan.CancelPolicyManager;
import com.ccm.api.service.ratePlan.DealRateDetailManager;
import com.ccm.api.service.ratePlan.GuaranteePolicyManager;
import com.ccm.api.service.ratePlan.PackageManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.ratePlan.RateDetailManager;
import com.ccm.api.service.ratePlan.RatePlanTemplateManager;
import com.ccm.api.service.ratePlan.RoomRateManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.sell.MarketManager;
import com.ccm.api.service.sell.SourceManager;
import com.ccm.api.service.system.Push2ChannelStaticMsgManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class RoomRateAction extends BaseAction {

    private static final long serialVersionUID = 1211430382838090130L;
    
	private HotelPackageCreteria creteria;
	private HotelPackageResult result;
	
    @Resource
    private DictCodeManager dictCodeManager;
    @Resource
    private RoomTypeManager roomTypeManager;
    @Resource
    private PackageManager packageManager;
    @Resource
    private MarketManager marketManager;
    @Resource
    private SourceManager sourceManager;
    @Resource
    private GuaranteePolicyManager guaranteePolicyManager;
    @Resource
    private CancelPolicyManager cancelPolicyManager;
    @Resource
    private RatePlanManager ratePlanManager;
    @Resource
    private PriceCalcManager priceCalcManager;
    @Resource
    private RateDetailManager rateDetailManager;
    @Resource
    private RateplanAsyncManager rateplanAsyncManager;
    @Resource
    private RoomRateManager roomRateManager;
    @Resource
    private CustomManager customManager;
    @Resource
    private RatePlanTemplateManager ratePlanTemplateManager;
    @Resource
    private HotelCancelManager hotelCancelManager;
    @Resource
    private HotelGuaranteeManager hotelGuaranteeManager;
    @Resource
    private HotelPackageManager hotelPackageManager;
    @Resource
    private Push2ChannelStaticMsgManager push2ChannelStaticMsgManager;
    @Resource
	private RateDetailVODaoMongo rateDetailVODaoMongo;
    @Resource
    private CallAddPriceCalcManager callAddPriceCalcManager;
    @Resource
    private ChannelManager channelManager;
    @Resource
	private DealRateDetailManager dealRateDetailManager;
    
    private String ratePlanId;
    private String ratePlanName;
    private Rateplan rp;
    private RatePlanI18n rpi18n;
    private HashMap<String,Object> configMap = new HashMap<String, Object>();
    private String roomTypeCode;//包含房型 code1,code2
    private String packageId;//打包服务id a,b,c
    private String guaranteeJsonArr;//担保规则json字符串
    private String cancelRuleJsonArr;//取消规则
    private String rateDetailListStr;//房价json字符串
    private String rateDetailId;//房价明细序号
    private String languageJsonArr; //多语言json字符串
    
    private List<HashMap<String,String>> rateMapList;
    private List<HashMap<String,Object>> noInheritRateMapList;
    private List<RateDetailVO> rateDetailVOList;
    private RatePlanVO ratePlanVO;
    @Resource
	private RoomMsgDaoMongo roomMsgDaoMongo;
	
    
    
    
    public String list(){
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();
		String hotelId = hotelvo.getHotelId();

		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage() + "_" + locale.getCountry();

		long startMili2 = System.currentTimeMillis();
		rateMapList = ratePlanManager.getRateNameByHotelId(hotelId, language);
		System.out.println("getRateNameByHotelId 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		if(user.getCompanyId().equalsIgnoreCase(CompanyType.CHANNEL)){
			if(CommonUtil.isNotEmpty(user.getChannels())){
				List<String> accessRateIdList=customManager.getAccessRatePlanIdByChannelIds(hotelId, user.getChannels());
				for(Iterator<HashMap<String, String>> it=rateMapList.iterator();it.hasNext();){
					Map<String, String>rateMap=it.next();
					boolean isFind=false;
					for(String rateId:accessRateIdList){
						if(rateMap.get("ratePlanId").equals(rateId)){
							isFind=true;
						}
					}
					if(isFind==false)
						it.remove();
						continue;
				}
			}else{
				rateMapList.clear();
			}
		}
		if (!StringUtils.hasText(ratePlanId) && rateMapList != null&& rateMapList.size() > 0) {
			ratePlanId = rateMapList.get(0).get("ratePlanId");
			ratePlanName = CommonUtil.filterHtml(rateMapList.get(0).get("description"));
		}
		
		if (StringUtils.hasText(ratePlanId)) {
			startMili2 = System.currentTimeMillis();
			ratePlanVO = ratePlanManager.getRatePlanVO(ratePlanId, true,language);
			System.out.println("getRatePlanVO 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
			cancelRuleJsonArr = JSON.toJSONStringWithDateFormat(ratePlanVO.getCancelRuleList(), DateUtils.YYYYMMDD);
			guaranteeJsonArr = JSON.toJSONStringWithDateFormat(ratePlanVO.getGuarrnteeList(), DateUtils.YYYYMMDD);
			rateDetailListStr = JSON.toJSONStringWithDateFormat(ratePlanVO.getRateDetailVOList(), DateUtils.YYYYMMDD);
			startMili2 = System.currentTimeMillis();
			List<Rateplan> rpList = ratePlanManager.getRefRateplan(hotelId,ratePlanId);
			System.out.println("getRefRateplan 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
			
			if (CommonUtil.isEmpty(rpList)) {
				configMap.put("showRefRateplan", true);
			} else {
				configMap.put("showRefRateplan", false);
			}
			List<String> rtCodeList = null;
			roomTypeCode = ratePlanVO.getRp().getIncludeRoomType();
			if (StringUtils.hasText(roomTypeCode)) {
				rtCodeList = Arrays.asList(roomTypeCode.split(","));
			}
			startMili2 = System.currentTimeMillis();
			List<RoomTypeVO> roomTypeList = roomTypeManager.getRoomTypeByCodes(rtCodeList, hotelId, language);
			configMap.put("selRoomTypeList", roomTypeList);
			System.out.println("getRoomTypeByCodes 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");

			List<String> accessCodeSelList = customManager.getCustomIdByRatePlanId(ratePlanId);
			configMap.put("accessCodeSelList", accessCodeSelList);

			// 过滤默认语言对象
			if (ratePlanVO.getRatePlanI18nList() != null&& ratePlanVO.getRatePlanI18nList().size() > 0) {
				for (int i = 0; i < ratePlanVO.getRatePlanI18nList().size(); i++) {
					if (language.equals(ratePlanVO.getRatePlanI18nList().get(i).getLanguage())) {
						ratePlanVO.getRatePlanI18nList().remove(i);
						break;
					}
				}
			}
			if (ratePlanVO.getRpi18n() != null&& StringUtils.hasText(ratePlanVO.getRpi18n().getDescription())) {
				ratePlanName = CommonUtil.filterHtml(ratePlanVO.getRpi18n().getDescription());
			}
		}

		// List<Rateplan> rpList =
		// ratePlanManager.getRatePlanByHotelId(hotelId);
		// configMap.put("rpList", rpList);
		startMili2 = System.currentTimeMillis();
		initPageData(hotelId);
		System.out.println("initPageData 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		
		return "roomRateList";
    }
    public String add(){
        B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        String hotelId =hotelvo.getHotelId();
        Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
        
        initPageData(hotelId);
        
//        List<Rateplan> rpList = ratePlanManager.getRatePlanByHotelId(hotelId);
//        configMap.put("rpList", rpList);
        rateMapList = ratePlanManager.getRateNameByHotelId(hotelId,language);
        return "addRoomRate";
    }
    public String next(){
    	Locale locale = ActionContext.getContext().getLocale();
 		String language = locale.getLanguage()+"_"+locale.getCountry();
    	
        List<String> rtCodeList = null;
        roomTypeCode = rp.getIncludeRoomType();
        if(StringUtils.hasText(roomTypeCode)){
            rtCodeList = Arrays.asList(roomTypeCode.split(","));
        }
        B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        String hotelId =hotelvo.getHotelId();
        List<RoomTypeVO> roomTypeList = roomTypeManager.getRoomTypeByCodes(rtCodeList,hotelId,language);
        configMap.put("roomTypeList", roomTypeList);

        //打包服务
		if(null==creteria){
			creteria = new HotelPackageCreteria();
		}
		creteria.setHotelId(hotelId);
		creteria.setLanguage(language);
		result = hotelPackageManager.searchHotelPackage(creteria);
		configMap.put("packages",result.getResultList());

        //保存房价定义基本信息到session
//        guaranteeJsonArr = "{'guaranteeId':'6181ce6abc9d4e1880bc1eb3db8a0e57','policyName':'测试担保规则','effectiveDate':'','expireDate':'','isApplyToSunday':'1','isApplyToMonday':'1','isApplyToTuesday':'1','isApplyToWednesday':'1','isApplyToThursday':'1','isApplyToFriday':'1','isApplyToSaturday':'1'},{'guaranteeId':'6181ce6abc9d4e1880bc1eb3db8a0e57','policyName':'测试担保规则','effectiveDate':'22','expireDate':'33','isApplyToSunday':'1','isApplyToMonday':'1','isApplyToTuesday':'1','isApplyToWednesday':'1','isApplyToThursday':'1','isApplyToFriday':'1','isApplyToSaturday':'1'}";
        
        HttpSession session = getSession();
        RatePlanVO ratePlanVO = null;
        String sKey=hotelId + rp.getRatePlanCode();
		Object obj = session.getAttribute(sKey);
        if(obj == null){
            ratePlanVO = new RatePlanVO();
        }else{
            ratePlanVO = (RatePlanVO)obj;
        }
        ratePlanVO.setRp(rp);
        ratePlanVO.setRpi18n(rpi18n);
        ratePlanVO.setRoomTypeCode(roomTypeCode);
        ratePlanVO.setPackageId(packageId);
        ratePlanVO.setCancelRuleJsonArr(cancelRuleJsonArr);
        ratePlanVO.setGuaranteeJsonArr(guaranteeJsonArr);
        ratePlanVO.setLanguageJsonArr(languageJsonArr);
		if (this.ratePlanVO != null) {
			ratePlanVO.setCustomList(this.ratePlanVO.getCustomList());
		}
        session.setAttribute(sKey, ratePlanVO);
        
        if(CommonUtil.isNotEmpty(rp.getInheritRatePlanId())){
            rateDetailListStr = JSON.toJSONStringWithDateFormat(ratePlanManager.getRateDetailVOList(rp),DateUtils.YYYYMMDD);
        }
        
        return "to_refRoomType";//重定向到关联房型
    }
    
    public String finish() {
    	Locale locale = ActionContext.getContext().getLocale();
 		String language = locale.getLanguage()+"_"+locale.getCountry();
        //从session中取出基本信息，保存到数据库
        //保存管理房型
        B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        String hotelId =hotelvo.getHotelId();
        HttpSession session = getSession();
        RatePlanVO ratePlanVO = null;
        String sKey=hotelId + rp.getRatePlanCode();
        Object obj = session.getAttribute(sKey);
        if(obj != null){
            ratePlanVO = (RatePlanVO)obj;
            ratePlanVO.getRp().setHotelId(hotelId);
            String rateCode = ratePlanVO.getRp().getRatePlanCode();
            Rateplan rateplan =ratePlanManager.getRateplanByRateplanCode(rateCode, hotelvo.getHotelCode());
            if(rateplan != null ){
            	//该房价已创建！
                saveMessage(getText("ccm.error.089"));
                return list();
            }
            if(CommonUtil.isEmpty(ratePlanVO.getRp().getInheritRatePlanId())){
                ratePlanVO.setRateDetailListStr(rateDetailListStr);
            }
        }else{
            //session已丢失，回到第一步，重新添加
            saveMessage(getText("ccm.error.011"));
            return list();
        }
        try {
        	ratePlanVO = ratePlanManager.addRateplanVO(ratePlanVO,language);
        } catch (Exception e) {
        	saveMessage(getText("ccm.error.007"));
            return list();
		}
        //添加每日房价
        ratePlanVO.setRateDetailListStr(rateDetailListStr);
        try {
			priceCalcManager.addPriceCalc(ratePlanVO,false);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        ratePlanId = ratePlanVO.getRp().getRatePlanId();
        session.removeAttribute(sKey);
        
        return SUCCESS;//到房价定义列表，并选中刚保存的
    }
    
    public String delRatePlan(){
        priceCalcManager.deletePriceCalcByRatePlanId(ratePlanId);
        ratePlanManager.remove(ratePlanId);
        ratePlanManager.deleteRatePlanI18nByRatePlanId(ratePlanId);
        ratePlanId=null;
        return list();
    }
    public void delRateDetail(){
    	B2BUser user = getCurLoginUser();
    	if(user != null){
    		HotelVO hotelvo = user.getHotelvo();
            if(StringUtils.hasText(rateDetailId)){
            	RateDetail rateDetail = rateDetailManager.get(rateDetailId);
                boolean b = rateDetailManager.delRateDetail(rateDetailId);
                if(b){
                	try {
    	            	//获取有效渠道
    	            	Rateplan rp = ratePlanManager.get(ratePlanId);
    	            	RatePlanVO rpvo = new RatePlanVO();
    	        		rpvo.setRp(rp);
    	        		rp.setHotelCode(hotelvo.getHotelCode());
    	    			HashMap<String,ArrayList<ChannelGoodsVO>> channelRateRoomMap =  ratePlanManager.getValidChannel(rp);
    	    			
    	    			rateDetailVOList = new ArrayList<RateDetailVO>();
    	    			RateDetailVO rdvo = new RateDetailVO();
    	    			BeanUtils.copyProperties(rateDetail, rdvo);
    	    			rateDetailVOList.add(rdvo);
    	    			List<RateDetailVO> pricelist = ratePlanManager.getRateDetailVOList(rp, rateDetailVOList);
        			
    					List<PriceCalc> pcList = priceCalcManager.getPriceCalcListByDetail(pricelist, rpvo);
    					
    					Map<String,Channel> channelMap =new HashMap<String, Channel>();
    					ArrayList<RoomMsg> closePriceList = new ArrayList<RoomMsg>();
    					for (PriceCalc priceCalc : pcList) {
    						String channelBindkey = priceCalc.getHotelCode()+priceCalc.getRatePlanCode()+priceCalc.getRoomTypeCode();
    						//匹配渠道绑定
    						if(channelRateRoomMap.containsKey(channelBindkey)){
    							ArrayList<ChannelGoodsVO> cgList = channelRateRoomMap.get(channelBindkey);
    							for (ChannelGoodsVO cg : cgList) {
    								if(cg.getEffectiveDate()==null){
    									continue;
    								}
    								if(CommonUtil.isEmpty(cg.getExpireDate())){
    									cg.setExpireDate(DateUtil.addMonths(new Date(), 36));
    								}
    								Channel channel = null;
    								if(channelMap.get(cg.getChannelCode())==null){
    									channel = channelManager.getChannelByChannelCode(cg.getChannelCode());
    									channelMap.put(cg.getChannelCode(), channel);
    								}else{
    									channel = channelMap.get(cg.getChannelCode());
    								}
    								if(channel==null || !channel.isPush(DateUtil.convertStringToDate(priceCalc.getDate()))){
    									continue;
    								}
    								if(DateUtil.judge(cg.getEffectiveDate(), cg.getExpireDate(), DateUtil.convertStringToDate(priceCalc.getDate()))){
	    								RoomMsg rm = new RoomMsg();
	    				                rm.setOnOff(Boolean.FALSE);
	    				                rm.setChannelCode(cg.getChannelCode());
	    				                rm.setChainCode(priceCalc.getChainCode());
	    				                rm.setHotelCode(priceCalc.getHotelCode());
	    				                rm.setRoomTypeCode(priceCalc.getRoomTypeCode());
	    				                rm.setRateCode(priceCalc.getRatePlanCode());
	    				                rm.setStartDate(priceCalc.getDate());
	    				                rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
	    								rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
	    								rm.setCreatedTime(new Date());
	    								closePriceList.add(rm);
    								}
    							}
    						}
    					}
    					List<Rateplan> refRpList =  ratePlanManager.getRefRateplan(hotelvo.getHotelId(),ratePlanId);
						for(Rateplan refRp :refRpList){
	    	            	refRp.setHotelCode(hotelvo.getHotelCode());
	    	    			HashMap<String,ArrayList<ChannelGoodsVO>> refChannelRateRoomMap =  ratePlanManager.getValidChannel(refRp);
	    	    			Map<String,Channel> refChannelMap =new HashMap<String, Channel>();
	    	    			for (PriceCalc priceCalc : pcList) {
	    						String channelBindkey = priceCalc.getHotelCode()+refRp.getRatePlanCode()+priceCalc.getRoomTypeCode();
	    						//匹配渠道绑定
	    						if(refChannelRateRoomMap.containsKey(channelBindkey)){
	    							ArrayList<ChannelGoodsVO> cgList = refChannelRateRoomMap.get(channelBindkey);
	    							for (ChannelGoodsVO cg : cgList) {
	    								if(cg.getEffectiveDate()==null){
	    									continue;
	    								}
	    								if(CommonUtil.isEmpty(cg.getExpireDate())){
	    									cg.setExpireDate(DateUtil.addMonths(new Date(), 36));
	    								}
	    								Channel channel = null;
	    								if(refChannelMap.get(cg.getChannelCode())==null){
	    									channel = channelManager.getChannelByChannelCode(cg.getChannelCode());
	    									refChannelMap.put(cg.getChannelCode(), channel);
	    								}else{
	    									channel = refChannelMap.get(cg.getChannelCode());
	    								}
	    								if(channel==null || !channel.isPush(DateUtil.convertStringToDate(priceCalc.getDate()))){
	    									continue;
	    								}
	    								if(DateUtil.judge(cg.getEffectiveDate(), cg.getExpireDate(), DateUtil.convertStringToDate(priceCalc.getDate()))){
		    								RoomMsg rm = new RoomMsg();
		    				                rm.setOnOff(Boolean.FALSE);
		    				                rm.setChannelCode(cg.getChannelCode());
		    				                rm.setChainCode(priceCalc.getChainCode());
		    				                rm.setHotelCode(priceCalc.getHotelCode());
		    				                rm.setRoomTypeCode(priceCalc.getRoomTypeCode());
		    				                rm.setRateCode(refRp.getRatePlanCode());
		    				                rm.setStartDate(priceCalc.getDate());
		    				                rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
		    								rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
		    								rm.setCreatedTime(new Date());
		    								closePriceList.add(rm);
		    							
	    								}
	    								
	    							}
	    						}
	    					}
						}
    					roomMsgDaoMongo.batchSave(closePriceList);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
                }
            }
    	}
    }
    public String cancel(){
    	 B2BUser user = getCurLoginUser();
         HotelVO hotelvo = user.getHotelvo();
         String hotelId =hotelvo.getHotelId();
        //清除session数据
        HttpSession session = getSession();
		session.removeAttribute(hotelId + rp.getRatePlanCode());
        return list();
    }
    public void initPageData(String hotelId){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage() + "_" + locale.getCountry();
		
		long startMili2 = System.currentTimeMillis();
		List<DictCode> dictList = dictCodeManager.searchByDictName(DictCode.DICT_RPT,language);
		System.out.println("searchByDictName 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		configMap.put(DictCode.DICT_RPT, dictList);
		
		// 获取房型列表
		startMili2 = System.currentTimeMillis();
		List<RoomTypeVO> roomTypeList = roomTypeManager.getAllRoomTypeByHotelId(hotelId,language);
		System.out.println("getAllRoomTypeByHotelId 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		configMap.put("roomTypeList", roomTypeList);
		
		// 打包服务
		HotelPackageVO hvo = new HotelPackageVO();
		hvo.setHotelId(hotelId);
		hvo.setLanguage(language);
		startMili2 = System.currentTimeMillis();
		configMap.put("packages", hotelPackageManager.getHotelPackageByObj(hvo));
		System.out.println("getHotelPackageByObj 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		
		// 市场
		startMili2 = System.currentTimeMillis();
		MarketCreteria m = new MarketCreteria();
		m.setLanguage(language);
		List<MarketVO> MarketVOList = marketManager.searchMarket(m).getResultList();		
		System.out.println("searchMarket 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		configMap.put("MarketVOList", MarketVOList);
		
		// 来源
		startMili2 = System.currentTimeMillis();
		SourceCreteria s = new SourceCreteria();
		s.setLanguage(language);
		configMap.put("sourceVOList", sourceManager.searchSource(s).getResultList());
		System.out.println("searchSource 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		
		// 担保规则
		HotelGuaranteeVO hgVo = new HotelGuaranteeVO();
		hgVo.setHotelId(hotelId);
		hgVo.setLanguage(language);
		startMili2 = System.currentTimeMillis();
		configMap.put("guaranteePolicyVOList",hotelGuaranteeManager.getHotelGuaranteeByObj(hgVo));
		System.out.println("getHotelGuaranteeByObj 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		
		// 取消规则
		HotelCancelVO hcVo = new HotelCancelVO();
		hcVo.setHotelId(hotelId);
		hcVo.setLanguage(language);
		startMili2 = System.currentTimeMillis();
		configMap.put("cancelPolicyVOList",hotelCancelManager.getHotelCancelByObj(hcVo));
		System.out.println("getHotelCancelByObj 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		
		noInheritRateMapList = ratePlanManager.getNoInheritRateNameByHotelId(hotelId, language);
		if (CommonUtil.isNotEmpty(noInheritRateMapList) && ratePlanId != null) {
			for (Iterator<HashMap<String, Object>> iterator = noInheritRateMapList.iterator(); iterator.hasNext();) {
				HashMap<String, Object> rateIdMap = iterator.next();
				if (ratePlanId.equals(rateIdMap.get("ratePlanId"))) {
					iterator.remove();
				}

				// 如果失效时间比当前日期小
				else if (rateIdMap.get("expireDate") != null&& DateUtil.currentDate().compareTo((Date) rateIdMap.get("expireDate")) > 0) {
					iterator.remove();
				}
			}
		}

		// AccessCode
		List<Custom> accessCodeList = customManager.getCustomByHotelId(hotelId);
		configMap.put("accessCodeList", accessCodeList);
		
		// 多语言语种
		// 所有多语言字典项
		startMili2 = System.currentTimeMillis();
		List<DictCode> languageList = dictCodeManager.searchByDictName(DictName.MORELANGUAGE,language);
		System.out.println("searchByDictName 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		
		// 筛选不需要的语言
		String[] languageFilter = { language };
		// 将中文编码的多语言去除(默认的就是中国)
		for (int i = 0; i < languageList.size(); i++) {
			for (String lf : languageFilter) {
				if (lf.equalsIgnoreCase(languageList.get(i).getCodeNo())) {
					languageList.remove(i);
				}
			}
		}
		configMap.put("languageList", languageList);
		
		// 获取所有的房价模板
		startMili2 = System.currentTimeMillis();
		List<RatePlanTemplateVO> ratePlanTempList = ratePlanTemplateManager.getDontUseRatePlanTemplate(hotelId, language);
		System.out.println("getDontUseRatePlanTemplate 耗时为："+ (System.currentTimeMillis() - startMili2) / 1000.0 + "秒");
		configMap.put("ratePlanTempList", ratePlanTempList);
    }
    /**编辑房价定义**/
    public String edit(){
    	Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage() + "_" + locale.getCountry();
		
    	ratePlanVO.setRateDetailListStr(null);
    	ratePlanVO.setRatePlanId(ratePlanId);
        ratePlanManager.addRateplanVO(ratePlanVO,language);
        ratePlanId = ratePlanVO.getRp().getRatePlanId();
        
		// 推送到渠道
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();
		String hotelId = hotelvo.getHotelId();
		
		push2ChannelStaticMsgManager.pushStaticMsgRatePaln(hotelId, ratePlanId, language, true);
        return list();
    }
    
    public void startSendPrice(){
    	for (int i = 1; i < 4; i++) {
    		dealRateDetailManager.dealRateDetail(i+"");
		}
    }
    
    public void validateRateDetail(){
    	Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
        ratePlanVO = new RatePlanVO();
        ratePlanVO.setRatePlanId(ratePlanId);
        ratePlanVO.setRateDetailListStr(rateDetailListStr);
        boolean isEffective = ratePlanManager.validateRateDetail(ratePlanVO,language);
        try {
            ajaxResponse(isEffective+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void validateRateplanCode(){
        B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        
        Rateplan rp = ratePlanManager.getRateplanByRateplanCode(getRequest().getParameter("rateplanCode"), hotelvo.getHotelCode());
        try {
            if(CommonUtil.isEmpty(rp)){
                ajaxResponse("true");//表示可以用
            }else{
                ajaxResponse("false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String splitRateDetail()throws ParseException{
    	B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        String chainCode = hotelvo.getChainCode();
        String hotelCode = hotelvo.getHotelCode();
        if(ratePlanVO == null){
        	ratePlanVO = new RatePlanVO();
        }
        ratePlanVO.setRatePlanId(ratePlanId);
        ratePlanVO.setRateDetailListStr(rateDetailListStr);
        ratePlanVO.setHotelCode(hotelCode);
        ratePlanVO.setChainCode(chainCode);
        ratePlanManager.autoHandleRateDetail(ratePlanVO,true);
        rp = ratePlanManager.get(ratePlanId);
        ratePlanVO.setRp(rp);
        return "to_roomRateList";
    }
    /**编辑房价定义明细**/
    public String editRateDetail() throws ParseException{
    	B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        String chainCode = hotelvo.getChainCode();
        String hotelCode = hotelvo.getHotelCode();
        if(ratePlanVO == null){
        	ratePlanVO = new RatePlanVO();
        }
        ratePlanVO.setRatePlanId(ratePlanId);
        ratePlanVO.setRateDetailListStr(rateDetailListStr);
        ratePlanVO.setHotelCode(hotelCode);
        ratePlanVO.setChainCode(chainCode);
        ratePlanManager.autoHandleRateDetail(ratePlanVO,false);
        rp = ratePlanManager.get(ratePlanId);
        ratePlanVO.setRp(rp);
        return "to_roomRateList";
    }
    
//    private void rateDetatilBatchSaveMongo(String ratePlanCode,
//			List<RateDetailVO> rateDetailVOList) {
//		B2BUser user = getCurLoginUser();
//        HotelVO hotelvo = user.getHotelvo();
//        String chainCode = hotelvo.getChainCode();
//        String hotelCode = hotelvo.getHotelCode();
//        rateDetailVODaoMongo.batchSaveMongo(rateDetailVOList,chainCode,hotelCode,ratePlanCode);
//    }
    
	/**
	 * 拆分房价明细
	 * 
	 * @return
	 */
	public String splitDetail() throws ParseException {
		return splitRateDetail();
	}
    
    public void checkRateRoomIsExists() throws Exception{
        String roomTypeId = getRequest().getParameter("roomTypeId");
        List<RoomRate> rrList = roomRateManager.getRoomRateByRatePlanIdAndRoomTypeId(ratePlanId,roomTypeId);
        if(CommonUtil.isNotEmpty(rrList)){
            ajaxResponse("true");
        }
    }
    
    /**
	 * 获取房价模板信息
	 * @return
	 * @throws IOException 
	 */
	public void ajaxloadRateTempInfo() throws IOException{
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		
		StringBuffer message = new StringBuffer("");
		String ratePlanTempCode = getRequest().getParameter("ratePlanTempCode");
		
		//获取房价模板多语言列表
		List<RatePlanTemplateI18n> i18nList = ratePlanTemplateManager.getRatePlanTemplateI18ns2(ratePlanTempCode);
		if(i18nList!=null&&i18nList.size()>0){
			message.append("[");
			for (int i = 0; i < i18nList.size(); i++) {
				RatePlanTemplateI18n i18n = i18nList.get(i);
				String isDefault = "No";
				
				//如果与当前的默认语言相同,标识一下
				if(language.equals(i18n.getLanguage())){
					isDefault = "Yes";
				}
				
				message.append("{language:'"+this.setStringValue(i18n.getLanguage())
						+"',ratePlanName:'"+this.setStringValue(i18n.getRatePlanTemplateName())
						+"',description:'"+this.setStringValue(i18n.getDescription())
						+"',isDefault:'"+isDefault+"'}");
				
				if(i<i18nList.size()-1){
					message.append(",");
				}
			}
			message.append("]");
		}
		
		this.getResponse().getWriter().print(message.toString());
	}
    
	public void getPriceFromRateDetail(){
		String channelCode = getRequest().getParameter("channelCode");
		String startDate = getRequest().getParameter("startDate");
		String endDate = getRequest().getParameter("endDate");
		String channelId=channelManager.getChannelByChannelCode(channelCode).getChannelId();
		B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        String hotelId =hotelvo.getHotelId();
        Date maxPushDate = DateUtil.addDays(DateUtil.currentDate(), 89);
		PriceCalc pc = new PriceCalc();
    	pc.setHotelId(hotelId);
    	pc.setChannelId(channelId);
        if(CommonUtil.isNotEmpty(startDate)){
        	pc.setStartDate(startDate);
        }else{
        	pc.setStartDate(DateUtil.convertDateToString(DateUtil.currentDate()));
        }
        if(CommonUtil.isNotEmpty(endDate)){
        	pc.setEndDate(endDate);
        }else{
        	pc.setEndDate(DateUtil.convertDateToString(maxPushDate));
        }
        List<PriceCalc> pcList = priceCalcManager.getPriceFromRateDetail(pc);
        ajaxResponse(JSON.toJSONString(pcList));
	}
	public void getRateDetailMongo(){
		B2BUser user = getCurLoginUser();
        HotelVO hotelvo = user.getHotelvo();
        
		RateDetailVOCriteria criteria = new RateDetailVOCriteria();
		criteria.setChainCode(hotelvo.getChainCode());
		criteria.setHotelCode(hotelvo.getHotelCode());
		criteria.setStatus(new String[]{RateDetailVO.MONGO_STATUS_INIT,RateDetailVO.MONGO_STATUS_ERR});
		RateDetailVOResult result = rateDetailVODaoMongo.searchRateDetailVO(criteria);
		List<RateDetailVO> rateDetailVOList = result.getResultList();
		ajaxResponse(JSON.toJSONString(rateDetailVOList));
	}
	/**
	 * 设置String类型的字符串
	 * @param sring
	 * @return
	 */
	private String setStringValue(String str){
		if(!StringUtils.hasText(str)){
			return "";
		}
		return str;
	}
    
    public Rateplan getRp() {
        return rp;
    }
    public void setRp(Rateplan rp) {
        this.rp = rp;
    }
   
    public RatePlanI18n getRpi18n() {
        return rpi18n;
    }
    public void setRpi18n(RatePlanI18n rpi18n) {
        this.rpi18n = rpi18n;
    }
    public HashMap<String, Object> getConfigMap() {
        return configMap;
    }
    public void setConfigMap(HashMap<String, Object> configMap) {
        this.configMap = configMap;
    }
    public String getRoomTypeCode() {
        return roomTypeCode;
    }
    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }
    public String getGuaranteeJsonArr() {
        return guaranteeJsonArr;
    }
    public void setGuaranteeJsonArr(String guaranteeJsonArr) {
        this.guaranteeJsonArr = guaranteeJsonArr;
    }
    public String getPackageId() {
        return packageId;
    }
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
    public String getCancelRuleJsonArr() {
        return cancelRuleJsonArr;
    }
    public void setCancelRuleJsonArr(String cancelRuleJsonArr) {
        this.cancelRuleJsonArr = cancelRuleJsonArr;
    }
    public String getRateDetailListStr() {
        return rateDetailListStr;
    }
    public void setRateDetailListStr(String rateDetailListStr) {
        this.rateDetailListStr = rateDetailListStr;
    }
    
    public List<HashMap<String, String>> getRateMapList() {
        return rateMapList;
    }
    public void setRateMapList(List<HashMap<String, String>> rateMapList) {
        this.rateMapList = rateMapList;
    }
    public List<RateDetailVO> getRateDetailVOList() {
        return rateDetailVOList;
    }
    public void setRateDetailVOList(List<RateDetailVO> rateDetailVOList) {
        this.rateDetailVOList = rateDetailVOList;
    }
    public String getRatePlanId() {
        return ratePlanId;
    }
    public void setRatePlanId(String ratePlanId) {
        this.ratePlanId = ratePlanId;
    }
    public RatePlanVO getRatePlanVO() {
        return ratePlanVO;
    }
    public void setRatePlanVO(RatePlanVO ratePlanVO) {
        this.ratePlanVO = ratePlanVO;
    }
    public String getRatePlanName() {
        return ratePlanName;
    }
    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }
    public String getRateDetailId() {
        return rateDetailId;
    }
    public void setRateDetailId(String rateDetailId) {
        this.rateDetailId = rateDetailId;
    }
    public List<HashMap<String, Object>> getNoInheritRateMapList() {
        return noInheritRateMapList;
    }
  
	public String getLanguageJsonArr() {
		return languageJsonArr;
	}
	public void setLanguageJsonArr(String languageJsonArr) {
		this.languageJsonArr = languageJsonArr;
	}
	public void setNoInheritRateMapList(
            List<HashMap<String, Object>> noInheritRateMapList) {
        this.noInheritRateMapList = noInheritRateMapList;
    }
    
}
