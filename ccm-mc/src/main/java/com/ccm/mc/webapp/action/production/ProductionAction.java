package com.ccm.mc.webapp.action.production;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;




import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ccm.api.dao.ratePlan.mongodb.RoomMsgDaoMongo;
import com.ccm.api.jms.RoomJmsManager;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RestrictionCalc;
import com.ccm.api.model.sell.vo.ProductionJsonVO;
import com.ccm.api.model.sell.vo.ProductionVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.channel.ChannelHotelManager;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.ratePlan.RestrictionCalcManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 产品
 * 
 * @author carr
 * 
 */
public class ProductionAction extends BaseAction {

	private static final long serialVersionUID = -8462493244583075129L;

	@Autowired
	private ChannelHotelManager channelHotelManager;
	@Autowired
	private ChannelgoodsManager channelgoodsManager;
	@Autowired
	private RestrictionCalcManager restrictionCalcManager;
	@Autowired
	private ChannelManager channelManager;
	@Resource
	private PriceCalcManager priceCalcManager;
	@Resource
	private RoomMsgDaoMongo roomMsgDaoMongo;
	
	private ProductionVO product;// 产品VO
	private List<Channel> channelList;// 渠道列表
	private List<ChannelGoodsVO> ratePlanList;// 房价列表
	private List<ChannelGoodsVO> roomTypeList; //房型列表
	private List<List<List<List<ProductionJsonVO>>>> productionCalendars;// 房态日历
	private String channelCodes;// 多个渠道代码
	private String ratePlanCodes;// 多个房价代码
	private String roomTypeCodes;// 多个房型代码
	private String channelIds;// 多个渠道ID
	private String ratePlanIds;// 多个房价ID
	private String queryDate;// 查询日期,201401
	private String dataStr;// 查询条件json


	/**
	 * 产品列表显示
	 */
	public String list() {
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();
		// 获取渠道列表
//		channelList = channelHotelManager.getChannelHotelCodeByHotelId(hotelvo.getHotelId());
		channelList = channelHotelManager.getChannelsByHotelId(hotelvo.getHotelId());
		if(user.getCompanyId().equals(CompanyType.CHANNEL)){
			if(CommonUtil.isNotEmpty(channelList)){
				for (Iterator <Channel> iterator = channelList.iterator(); iterator.hasNext();) {
					boolean isFind=false;
					Channel c1=iterator.next();
					for(Channel c2:user.getChannels()){
						if(c1.getChannelId().equals(c2.getChannelId())){
							isFind=true;
							break;
						}
					}
					if(isFind==false){
						iterator.remove();
						continue;
					}
				}
			}else{
				channelList.clear();
			}
		}
		return "list";
	}

	/**
	 * AJAX获取产品日历
	 */
	public void ajaxGetCalendars() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		if ("".equals(channelCodes) || "".equals(ratePlanCodes) || "".equals(roomTypeCodes) || "".equals(queryDate)) {
			productionCalendars = new ArrayList<List<List<List<ProductionJsonVO>>>>();
		} else {
			product = new ProductionVO();
			// 获取查询时间段
			int year = Integer.parseInt(queryDate.substring(0, 4));
			int month = Integer.parseInt(queryDate.substring(5, 7));
			startDate = sdf.parse(year + "-" + month + "-01");
			endDate = sdf.parse(DateUtil.getMonthLastDay(year, month));
			// 取当前酒店
			B2BUser user = getCurLoginUser();
			HotelVO hotelvo = user.getHotelvo();
			// 构造查询条件
			product.setHotelId(hotelvo.getHotelId());
			product.setChainCode(hotelvo.getChainCode());
			product.setHotelCode(hotelvo.getHotelCode());
			product.setChannelCodeArray(channelCodes.split(","));
			product.setRatePlanCodeArray(ratePlanCodes.split(","));
			product.setRoomTypeCodeArray(roomTypeCodes.split(","));
			product.setStartDate(startDate);
			product.setEndDate(endDate);
			productionCalendars = restrictionCalcManager.getProductionCalendars(product);
		}
		String json = JSONArray.toJSONString(productionCalendars);
		this.getResponse().getWriter().print(json);
	}

	/**
	 * AJAX设置房价开关
	 */
	public void ajaxOnOffSet() throws Exception {
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			RestrictionCalc r = new RestrictionCalc();
			r.setChannelCode(product.getChannelCode());
			r.setChainCode(product.getChainCode());
			r.setHotelCode(product.getHotelCode());
			r.setRoomTypeCode(product.getRoomTypeCode());
			r.setRatePlanCode(product.getRatePlanCode());
			r.setDate(sdf.parse(product.getDate()));
			r.setOnOff(product.getOnOff());

			restrictionCalcManager.updateRestrictionCalcOnOff(r);
			Channel channel = channelManager.getChannelByChannelCode(r.getChannelCode());
			if(channel!=null && channel.getPushRtav() && channel.isPush(r.getDate())){
				ChannelGoodsVO cgvo = new ChannelGoodsVO();
	              cgvo.setChannelCode(product.getChannelCode());
	              cgvo.setRoomTypeCode(product.getRoomTypeCode());
	              cgvo.setHotelId(hotelvo.getHotelId());
	              cgvo.setIsBind(true);
	              List<ChannelGoodsVO> crpList = channelgoodsManager.getEnabledChannelGoods(cgvo);
	              if(CommonUtil.isNotEmpty(crpList)){
	            	//查询价格detail
	            	  PriceCalc pc = new PriceCalc();
	            	  pc.setHotelId(hotelvo.getHotelId());
	            	  pc.setStartDate(product.getDate());
	            	  pc.setEndDate(product.getDate());
	            	  
	            	  List<String> rtCodeList = new ArrayList<String>();
	            	  rtCodeList.add(product.getRoomTypeCode());
	            	  pc.setRoomTypeCodeList(rtCodeList);
	            	  
	            	  List<String> rateCodeList = new ArrayList<String>();
	            	  rateCodeList.add(product.getRatePlanCode());
	            	  pc.setRatePlanCodeList(rateCodeList);
	            	  HashSet<String> roomRateDateSet = priceCalcManager.getRateDetailRoomRateDate(pc);
	            	  
	            	  if(CommonUtil.isNotEmpty(roomRateDateSet) 
	            			  && roomRateDateSet.contains(product.getRoomTypeCode()+product.getRatePlanCode()+product.getDate())){
		            		RoomMsg rm = new RoomMsg();
							rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
							rm.setOnOff(r.getOnOff().intValue() == 1);
							rm.setChannelCode(r.getChannelCode());
							rm.setChainCode(r.getChainCode());
							rm.setHotelCode(r.getHotelCode());
							rm.setRoomTypeCode(r.getRoomTypeCode());
							rm.setRateCode(r.getRatePlanCode());
							rm.setStartDate(DateUtil.convertDateToString(r.getDate()));
							rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
							rm.setCreatedTime(new Date());
							List<RoomMsg> rmList = new ArrayList<RoomMsg>();
							rmList.add(rm);
							roomMsgDaoMongo.batchSave(rmList);
	            	  }
	              }
			}
			this.getResponse().getWriter().print(JSONArray.toJSONString("success"));
		} catch (Exception e) {
			this.getResponse().getWriter().print(JSONArray.toJSONString("fail"));
			e.printStackTrace();
		}
	}

	/**
	 * 进入批量设置房价开关页面
	 */
	public String toSetOnOff() {
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();
		// 获取渠道列表
//		channelList = channelHotelManager.getChannelHotelCodeByHotelId(hotelvo.getHotelId());
		channelList = channelHotelManager.getChannelsByHotelId(hotelvo.getHotelId());
		return "toSetOnOff";
	}

	/**
	 * AJAX批量设置房价开关
	 */
	public void ajaxBatchOnOffSet() throws IOException {
		B2BUser user = getCurLoginUser();
		HotelVO hotelvo = user.getHotelvo();

		try {
			product.setHotelId(hotelvo.getHotelId());
			product.setChainCode(hotelvo.getChainCode());
			product.setHotelCode(hotelvo.getHotelCode());
			restrictionCalcManager.batchUpdateRestrictionCalcOnOff(product);
			this.getResponse().getWriter().print(JSON.toJSONString("success"));
		} catch (Exception e) {
			this.getResponse().getWriter().print(JSON.toJSONString("fail"));
			e.printStackTrace();
		}
	}

	/**
	 * AJAX获取房价
	 */
	public void ajaxGetRatePlanList() throws IOException {
		if (null != channelIds && !"".equals(channelIds)) {
			B2BUser user = getCurLoginUser();
			HotelVO hotelvo = user.getHotelvo();
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			String[] channelArray = channelIds.split(",");
			List<String> channelIdList = Arrays.asList(channelArray);
			ChannelGoodsVO cgvo = new ChannelGoodsVO();
			cgvo.setHotelId(hotelvo.getHotelId());
			cgvo.setChannelIds(channelIdList);
			cgvo.setStatus(ChannelGoodsStatus.publish);
			cgvo.setLanguage(language);
			ratePlanList = channelgoodsManager.getChannelRatePlanByChannelGoods(cgvo);
			
			Collections.sort(ratePlanList, new Comparator<ChannelGoodsVO>() {
				@Override
				public int compare(ChannelGoodsVO o1, ChannelGoodsVO o2) {
					if(StringUtils.isBlank(o1.getRatePlanCode())
							||StringUtils.isBlank(o2.getRatePlanCode())){
						return 0;
					}
					return o1.getRatePlanCode().compareToIgnoreCase(o2.getRatePlanCode());
				}
			});
		} else {
			ratePlanList = new ArrayList<ChannelGoodsVO>();
		}
		this.getResponse().getWriter().print(JSON.toJSONString(ratePlanList));
	}

	/**
	 * AJAX获取房型
	 */
	public void ajaxGetRoomTypeList() throws IOException {
		if ((null != channelIds && !"".equals(channelIds)) && (null != ratePlanIds && !"".equals(ratePlanIds))) {
			B2BUser user = getCurLoginUser();
			HotelVO hotelvo = user.getHotelvo();
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			String[] channelArray = channelIds.split(",");
			List<String> channelIdList = Arrays.asList(channelArray);
			String[] ratePlanArray = ratePlanIds.split(",");
			List<String> ratePlanIdList = Arrays.asList(ratePlanArray);
			ChannelGoodsVO cgvo = new ChannelGoodsVO();
			cgvo.setHotelId(hotelvo.getHotelId());
			cgvo.setChannelIds(channelIdList);
			cgvo.setRatePlanIds(ratePlanIdList);
			cgvo.setStatus(ChannelGoodsStatus.publish);
			cgvo.setLanguage(language);
			roomTypeList = channelgoodsManager.getChannelRoomTypeByChannelGoods(cgvo);
			
			Collections.sort(roomTypeList, new Comparator<ChannelGoodsVO>() {
				@Override
				public int compare(ChannelGoodsVO o1, ChannelGoodsVO o2) {
					if(StringUtils.isBlank(o1.getRoomTypeCode())
							||StringUtils.isBlank(o2.getRoomTypeCode())){
						return 0;
					}
					return o1.getRoomTypeCode().compareToIgnoreCase(o2.getRoomTypeCode());
				}
			});
			
		} else {
			roomTypeList = new ArrayList<ChannelGoodsVO>();
		}
		this.getResponse().getWriter().print(JSON.toJSONString(roomTypeList));
	}

	public ProductionVO getProduct() {
		return product;
	}

	public void setProduct(ProductionVO product) {
		this.product = product;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	public List<ChannelGoodsVO> getRatePlanList() {
		return ratePlanList;
	}

	public void setRatePlanList(List<ChannelGoodsVO> ratePlanList) {
		this.ratePlanList = ratePlanList;
	}

	public List<ChannelGoodsVO> getRoomTypeList() {
		return roomTypeList;
	}

	public void setRoomTypeList(List<ChannelGoodsVO> roomTypeList) {
		this.roomTypeList = roomTypeList;
	}

	public List<List<List<List<ProductionJsonVO>>>> getProductionCalendars() {
		return productionCalendars;
	}

	public void setProductionCalendars(
			List<List<List<List<ProductionJsonVO>>>> productionCalendars) {
		this.productionCalendars = productionCalendars;
	}

	public String getChannelCodes() {
		return channelCodes;
	}

	public void setChannelCodes(String channelCodes) {
		this.channelCodes = channelCodes;
	}

	public String getRatePlanCodes() {
		return ratePlanCodes;
	}

	public void setRatePlanCodes(String ratePlanCodes) {
		this.ratePlanCodes = ratePlanCodes;
	}

	public String getRoomTypeCodes() {
		return roomTypeCodes;
	}

	public void setRoomTypeCodes(String roomTypeCodes) {
		this.roomTypeCodes = roomTypeCodes;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public String getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}

	public String getRatePlanIds() {
		return ratePlanIds;
	}

	public void setRatePlanIds(String ratePlanIds) {
		this.ratePlanIds = ratePlanIds;
	}

	public String getDataStr() {
		return dataStr;
	}

	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}
}
