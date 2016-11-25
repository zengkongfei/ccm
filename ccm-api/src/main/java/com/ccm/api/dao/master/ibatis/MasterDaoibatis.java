package com.ccm.api.dao.master.ibatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.model.bdp.OWSReservation;
import com.ccm.api.model.constant.RemindStatus;
import com.ccm.api.model.log.ChannelOrder;
import com.ccm.api.model.log.vo.ChannelOrderCriteria;
import com.ccm.api.model.log.vo.ChannelOrderResult;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterCancel;
import com.ccm.api.model.order.MasterOrder;
import com.ccm.api.model.order.MasterPackage;
import com.ccm.api.model.order.MasterPms;
import com.ccm.api.model.order.MasterProfile;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.order.vo.MasterVO;
import com.ccm.api.model.order.vo.OrderSearchResult;
import com.ccm.api.model.order.vo.SearchOrderCriteria;
import com.ccm.api.model.ws.vo.stayHistory.StayHistoryRQVO;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.RSAEncrypt;

@Repository("masterDao")
public class MasterDaoibatis extends GenericDaoiBatis<MasterOrder, String> implements MasterDao {

	private Map<String, Long> orderIdMap = new HashMap<String, Long>();
	private Map<String, Long> profileIdMap = new HashMap<String, Long>();

	public MasterDaoibatis() {
		super(MasterOrder.class);
	}

	/**
	 * 保存订单主表
	 */
	public String saveMaster(Master m) {
		m.setOsta(RemindStatus.order_new);
		// 更新原订单信息
		m.setOcheckinDate(m.getArr());
		m.setOcheckoutDate(m.getDep());
		m.setOnumberOfRooms(m.getNumberOfUnits());
		m.setOtype(m.getType());
		m.setOchainCode(m.getChainCode());
		m.setOhotelCode(m.getHotelCode());
		m.setOchannel(m.getChannel());
		m.setOratePlanCode(m.getRatePlanCode());
		m.setOgstno(m.getGstno());
		m.setOchild(m.getChild());
		Master mNew = (Master) getSqlMapClientTemplate().insert("saveMaster", m);
		if (mNew != null) {
			return mNew.getMasterId();
		}
		return null;

	}

	/**
	 * 保存订单附表
	 */
	public void saveMasterOrder(MasterOrder mo) {
		mo.setCardNumber(RSAEncrypt.encrypt(mo.getCardNumber()));
		getSqlMapClientTemplate().insert("saveMasterOrder", mo);

	}

	/**
	 * 保存订单包价明细快照
	 */
	public void saveMasterRate(MasterRate mr) {
		getSqlMapClientTemplate().insert("saveMasterRate", mr);
	}

	/**
	 * 保存订单取消规则明细快照
	 */
	public void saveMasterCancel(MasterCancel mc) {
		getSqlMapClientTemplate().insert("saveMasterCancel", mc);
	}

	/**
	 * 保存订单房价明细快照
	 */
	public void saveMasterPackage(MasterPackage mp) {
		getSqlMapClientTemplate().insert("saveMasterPackage", mp);
	}

	public void saveMasterProfile(MasterProfile mp) {
		getSqlMapClientTemplate().insert("saveMasterProfile", mp);
	}

	/**
	 * 根据订单号删除订单的房价明细快照
	 */
	public void deleteMasterRateByOrderId(String orderId) {
		getSqlMapClientTemplate().delete("deleteMasterRateByOrderId", orderId);
	}

	/**
	 * 根据订单号删除订单的包价明细快照
	 */
	public void deleteMasterCancelByOrderId(String orderId) {
		getSqlMapClientTemplate().delete("deleteMasterCancelByOrderId", orderId);
	}

	/**
	 * 根据订单号删除订单的取消规则明细快照
	 */
	public void deleteMasterPackageByOrderId(String orderId) {
		getSqlMapClientTemplate().delete("deleteMasterPackageByOrderId", orderId);
	}

	public void deleteMasterProfileByOrderId(String orderId) {
		getSqlMapClientTemplate().delete("deleteMasterProfileByOrderId", orderId);
	}

	/**
	 * 修改订单主表
	 */
	public void updateMaster(Master m) {
		getSqlMapClientTemplate().update("updateMaster", m);

	}

	public void updateOstaByMasterIds(String osta, List<String> masterIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("osta", osta);
		param.put("masterIds", masterIds);
		getSqlMapClientTemplate().update("updateOstaByMasterIds", param);
	}

	/**
	 * 根据订单号更新订单下传消息状态
	 */
	public void updateDownPmsMsgStatusByMasterId(String masterId, String downPmsMsgStatus) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("masterId", masterId);
		param.put("downPmsMsgStatus", downPmsMsgStatus);
		getSqlMapClientTemplate().update("updateDownPmsMsgStatusByMasterId", param);
	}

	/**
	 * 修改订单附表
	 * 
	 */
	public void updateMasterOrder(MasterOrder mo) {
		mo.setCardNumber(RSAEncrypt.encrypt(mo.getCardNumber()));
		getSqlMapClientTemplate().update("updateMasterOrder", mo);

	}

	/**
	 * 修改订单的PMS预订号
	 */
	public void updateMasterOrderPmsId(MasterOrder mo) {
		mo.setLastModifyTime(DateUtil.currentDateTime());
		getSqlMapClientTemplate().update("updateMasterOrderPmsId", mo);

	}

	/**
	 * 取消订单时记录信息
	 */
	public void cancelOrderById(MasterOrder mo) {
		getSqlMapClientTemplate().update("cancelOrderById", mo);

	}

	public void updateAccountMsgId(Master master) {
		getSqlMapClientTemplate().update("updateAccountMsgId", master);
	}

	/**
	 * 根据渠道订单号查询订单表master
	 */
	@SuppressWarnings("unchecked")
	public List<Master> getMasterByCrsno(String crsno) {
		return getSqlMapClientTemplate().queryForList("getMasterByCrsno", crsno);
	}

	public Master getParentOrderById(String parentId) {
		return (Master) getSqlMapClientTemplate().queryForObject("getParentOrderById", parentId);
	}

	@SuppressWarnings("unchecked")
	public List<Master> getChildOrderByParentId(String parentId) {
		return getSqlMapClientTemplate().queryForList("getChildOrderByParentId", parentId);
	}

	/**
	 * 根据订单号获取渠道的订单信息
	 * 
	 */
	public Master getMasterOrderByOrderId(String orderId) {
		if (!StringUtils.hasText(orderId)) {
			return null;
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("masterId", orderId);
		Master m = (Master) getSqlMapClientTemplate().queryForObject("getMasterOrderByParam", param);
		if (m != null) {
			if (m.getCardNumber() != null) {
				m.setCardNumber(RSAEncrypt.decrypt(m.getCardNumber()));
			}
		}
		return m;
	}

	public Master getMasterByOrderId(String masterId) {
		return (Master) getSqlMapClientTemplate().queryForObject("getMasterByOrderId", masterId);
	}

	@SuppressWarnings("unchecked")
	public List<Master> getMasterOrderByPmsIdHotelCode(String pmsId, String hotelCode) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("pmsId", pmsId);
		param.put("hotelCode", hotelCode);
		return getSqlMapClientTemplate().queryForList("getMasterOrderByParam", param);
	}

	@SuppressWarnings("unchecked")
	public List<Master> getOrderChildByOrderId(String orderId) {
		if (!StringUtils.hasText(orderId)) {
			return new ArrayList<Master>();
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("pcrec", orderId);
		return getSqlMapClientTemplate().queryForList("getMasterOrderByParam", param);
	}

	/**
	 * 根据订单号获取价格明细
	 * 
	 * @param masterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MasterRate> getMasterRate(String masterId) {
		return getSqlMapClientTemplate().queryForList("getMasterRate", masterId);
	}

	@SuppressWarnings("unchecked")
	public List<MasterPackage> getMasterPackage(String masterId) {
		return getSqlMapClientTemplate().queryForList("getMasterPackage", masterId);
	}

	@SuppressWarnings("unchecked")
	public List<MasterCancel> getMasterCancel(String masterId) {
		return getSqlMapClientTemplate().queryForList("getMasterCancel", masterId);
	}

	@SuppressWarnings("unchecked")
	public List<MasterCancel> getMasterCancelByObj(MasterCancel mc) {
		return getSqlMapClientTemplate().queryForList("getMasterCancelByObj", mc);
	}

	@SuppressWarnings("unchecked")
	public List<MasterProfile> getMasterProfile(String masterId) {
		return getSqlMapClientTemplate().queryForList("getMasterProfile", masterId);
	}

	/**
	 * 根据条件查询订单
	 */
	@SuppressWarnings("unchecked")
	public OrderSearchResult searchList(SearchOrderCriteria criteria) {
		
		OrderSearchResult searchResult = new OrderSearchResult();

		if (criteria.isExcelFlag()) {
			if (criteria.isNeedPage()) {
				// 如果是第一页,系统计算该条件下总共多少条记录
				int count = (Integer) getSqlMapClientTemplate().queryForObject("countOrdersExcel", criteria);
				searchResult.setTotalCount(count);

				List<Master> list = getSqlMapClientTemplate().queryForList("searchOrdersExcel", criteria);
				searchResult.setResultList(list);
			} else {
				List<Master> orderExcel = getSqlMapClientTemplate().queryForList("searchOrdersExcel", criteria, 0, 1000);
				searchResult.setResultList(orderExcel);
			}
		} else {
			if (criteria.isNeedPage()) {
				// 如果是第一页,系统计算该条件下总共多少条记录
				int count = (Integer) getSqlMapClientTemplate().queryForObject("searchMOrderCount", criteria);
				searchResult.setTotalCount(count);

				List<Master> list = getSqlMapClientTemplate().queryForList("searchOrders", criteria);
	
				searchResult.setResultList(list);
			} else {
				List<Master> list = getSqlMapClientTemplate().queryForList("searchOrders", criteria);
				searchResult.setResultList(list);
			}
		}
		return searchResult;
	}

	public int getChildOrderNum(String masterId, String orderStatus) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("parentId", masterId);
		param.put("orderStatus", orderStatus);
		return (Integer) getSqlMapClientTemplate().queryForObject("countChildOrder", param);
	}

	/**
	 * 获取档案号
	 */
	public synchronized String getProfileId() {
		long result = 1;
		if (profileIdMap.get("profileId") != null) {
			result = profileIdMap.get("profileId");
		} else {
			Object o = getSqlMapClientTemplate().queryForObject("getMaxProfileIdMasterOrder");
			if (o != null) {
				result = Long.valueOf((String) o);
			}
		}
		result = result + 1;
		profileIdMap.put("profileId", result);
		return String.valueOf(result);
	}

	/**
	 * 获取订单号
	 */
	public synchronized String getMaxOrderIdMasterOrder() {
		Long result = 90000000l;
		if (orderIdMap.get("orderId") != null) {
			result = orderIdMap.get("orderId");
		} else {
			Object o = getSqlMapClientTemplate().queryForObject("getMaxOrderIdMasterOrder");
			if (o != null) {
				result = Long.valueOf((String) o);
			}
		}
		result = result + 1;
		orderIdMap.put("orderId", result);
		return String.valueOf(result);
	}

	public String getHotelIdByMasterId(String masterId) {
		return (String) getSqlMapClientTemplate().queryForObject("getHotelIdByMasterId", masterId);
	}

	@Override
	public Integer getMasterAvailableByDate(String hotelCode, String roomTypeCode, Date date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotelCode", hotelCode);
		map.put("roomTypeCode", roomTypeCode);
		map.put("date", date);
		return (Integer) getSqlMapClientTemplate().queryForObject("getMasterAvailableByDate", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Master> searchOrdersOfStayHistory(StayHistoryRQVO rqvo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("confirmationIDs", rqvo.getConfirmationIDs());
		params.put("checkinDate", rqvo.getStartDate());
		params.put("checkoutDate", rqvo.getEndDate());
		params.put("channelCode", rqvo.getAdsCode());
		return getSqlMapClientTemplate().queryForList("searchOrdersOfStayHistory", params);
	}

	@SuppressWarnings("unchecked")
	public List<String> getMasterByOsta(String osta, String hotelId, String payment) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("osta", osta);
		map.put("hotelId", hotelId);
		map.put("payment", payment);
		return getSqlMapClientTemplate().queryForList("getMasterByOsta", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderSearchResult searchSummaryOrders(SearchOrderCriteria criteria) {
		OrderSearchResult searchResult = new OrderSearchResult();

		if (criteria.isNeedPage()) {
			// 如果是第一页,系统计算该条件下总共多少条记录
			int count = (Integer) getSqlMapClientTemplate().queryForObject("searchSummaryOrdersCount", criteria);
			searchResult.setTotalCount(count);

			List<MasterVO> list = getSqlMapClientTemplate().queryForList("searchSummaryOrders", criteria);
			searchResult.setMasterVOList(list);
		} else {
			List<MasterVO> list = getSqlMapClientTemplate().queryForList("searchSummaryOrders", criteria);
			searchResult.setMasterVOList(list);
		}

		return searchResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MasterRate> getMasterRateByOrderNo(String masterId, String date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("masterId", masterId);
		map.put("date", date);
		return getSqlMapClientTemplate().queryForList("getMasterRateByOrderNoDay", map);
	}

	@Override
	public MasterRate getFirstMasterRateByOrderNo(String masterId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("masterId", masterId);
		return (MasterRate) getSqlMapClientTemplate().queryForObject("getFirstMasterRateByOrderNo", map);
	}

	@Override
	public void updateDeduct(Master master) {
		getSqlMapClientTemplate().update("updateDeduct", master);

	}

	/**
	 * 根据hotel获取相应的market
	 * 
	 * @param HotelIds
	 * @return
	 */
	@Override
	public List<String> getMarketsByHotelId(String hotelId) {
		@SuppressWarnings("unchecked")
		List<String> list = getSqlMapClientTemplate().queryForList("getMarketsByHotelId", hotelId);
		return list;
	}

	@Override
	public Master getMasterOrderByOrderNo(String hotelId, String channelId, String name1, String masterId,String crsno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("masterId", masterId);
		map.put("channelId", channelId);
		map.put("name1", name1);
		map.put("hotelId", hotelId);
		map.put("crsno", crsno);
		
		return (Master) getSqlMapClientTemplate().queryForObject("getMasterOrderByParam", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Master> getMasterByArrSta(Date changed, String sta,Date createdTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("changed", changed);
		map.put("sta", sta);
		map.put("createdTime", createdTime);
		return getSqlMapClientTemplate().queryForList("getMasterByArrSta", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Master> getMasterCancelByArrSta(Date cancelTime,Date createdTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cancelTime", cancelTime);
		map.put("cancelPmsId", RemindStatus.order_cancel);
		map.put("createdTime", createdTime);
		return getSqlMapClientTemplate().queryForList("getMasterCancelByArrSta", map);
	}

	@Override
	public void saveMasterPms(MasterPms masterPms) {
		getSqlMapClientTemplate().insert("saveMasterPms", masterPms);
	}

	@Override
	public void updateMasterPms(String masterId, int number, String sta, Date arr) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("masterId", masterId);
		map.put("sta", sta);
		map.put("number", number);
		map.put("arr", arr);
		getSqlMapClientTemplate().update("updateMasterPms", map);

	}

	@Override
	public void deleteMasterPms(List<String> masterIdList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("masterIdList", masterIdList);
		getSqlMapClientTemplate().delete("deleteMasterPms", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getHasPmsIdMaster() {
		return getSqlMapClientTemplate().queryForList("getHasPmsIdMaster");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MasterPms> getAllMasterPms() {
		return getSqlMapClientTemplate().queryForList("getAllMasterPms");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getHasCancelIdMaster(String sta) {
		return getSqlMapClientTemplate().queryForList("getHasCancelIdMaster",sta);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getNonePmsIdMasterByCancel(String sta) {
		return getSqlMapClientTemplate().queryForList("getNonePmsIdMasterByCancel",sta);
	}
	
	/**
	 * 统计酒店的渠道订单
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ChannelOrderResult getChannelOrders(ChannelOrderCriteria channelOrderCriteria) {

		ChannelOrderResult channelOrderResult = new ChannelOrderResult();

		List<ChannelOrder> resultList = getSqlMapClientTemplate().queryForList("getChannelOrders", channelOrderCriteria);
		if (null != resultList) {
			channelOrderResult.setTotalCount(resultList.size());
		}
		channelOrderResult.setResultList(resultList);

		return channelOrderResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OWSReservation> getCreatedOrderHistoryYesterDay(String effectiveDate) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("effectiveDate", effectiveDate);
		return getSqlMapClientTemplate().queryForList("getCreatedOrderHistoryYesterDay", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OWSReservation> getModifedOrderHistoryYesterDay(String effectiveDate) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("effectiveDate", effectiveDate);
		return getSqlMapClientTemplate().queryForList("getModifedOrderHistoryYesterDay", map);
	}

	@Override
	public void updateCancelPmsId(String masterId, String cancelPmsId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("masterId", masterId);
		map.put("cancelPmsId", cancelPmsId);
		getSqlMapClientTemplate().update("updateCancelPmsId", map);
		
	}

	@Override
	public List<String> getMasterPmsListBYCreatedTime(Date time) {
		return getSqlMapClientTemplate().queryForList("getMasterPmsListBYCreatedTime", time);
	}

}
