package com.ccm.api.dao.master;

import java.util.Date;
import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.bdp.OWSReservation;
import com.ccm.api.model.log.vo.ChannelOrderCriteria;
import com.ccm.api.model.log.vo.ChannelOrderResult;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterCancel;
import com.ccm.api.model.order.MasterOrder;
import com.ccm.api.model.order.MasterPackage;
import com.ccm.api.model.order.MasterPms;
import com.ccm.api.model.order.MasterProfile;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.order.vo.OrderSearchResult;
import com.ccm.api.model.order.vo.SearchOrderCriteria;
import com.ccm.api.model.ws.vo.stayHistory.StayHistoryRQVO;

public interface MasterDao extends GenericDao<MasterOrder, String> {

	/**
	 * 保存订单主表
	 * 
	 * @param m
	 * @return
	 */
	public String saveMaster(Master m);

	/**
	 * 保存订单附表
	 * 
	 * @param mo
	 */
	public void saveMasterOrder(MasterOrder mo);

	/**
	 * 保存订单房价明细快照
	 * 
	 * @param mr
	 */
	public void saveMasterRate(MasterRate mr);

	/**
	 * 保存订单包价明细快照
	 * 
	 * @param mr
	 */
	public void saveMasterCancel(MasterCancel mr);

	/**
	 * 保存订单取消规则明细快照
	 * 
	 * @param mr
	 */
	public void saveMasterPackage(MasterPackage mr);

	void saveMasterProfile(MasterProfile mp);

	/**
	 * 根据订单号删除订单的房价明细快照
	 * 
	 * @param orderId
	 */
	public void deleteMasterRateByOrderId(String orderId);

	/**
	 * 根据订单号删除订单的包价明细快照
	 * 
	 * @param orderId
	 */
	public void deleteMasterCancelByOrderId(String orderId);

	/**
	 * 根据订单号删除订单的取消规则明细快照
	 * 
	 * @param orderId
	 */
	public void deleteMasterPackageByOrderId(String orderId);

	void deleteMasterProfileByOrderId(String orderId);

	/**
	 * 修改订单主表
	 * 
	 * @param m
	 */
	public void updateMaster(Master m);

	void updateOstaByMasterIds(String osta, List<String> masterIds);

	/**
	 * 根据订单号更新订单下传消息状态
	 * 
	 * @param masterId
	 * @param downPmsMsgStatus
	 */
	void updateDownPmsMsgStatusByMasterId(String masterId, String downPmsMsgStatus);

	/**
	 * 修改订单附表
	 * 
	 * @param mo
	 */
	public void updateMasterOrder(MasterOrder mo);

	/**
	 * 修改订单的PMS预订号
	 * 
	 * @param mo
	 */
	public void updateMasterOrderPmsId(MasterOrder mo);

	/**
	 * 取消订单时记录信息
	 * 
	 * @param mo
	 */
	public void cancelOrderById(MasterOrder mo);

	void updateAccountMsgId(Master master);

	/**
	 * 根据渠道订单号查询订单表master
	 * 
	 * @param crsno
	 * @return
	 */
	List<Master> getMasterByCrsno(String crsno);

	Master getParentOrderById(String parentId);

	List<Master> getChildOrderByParentId(String parentId);

	/**
	 * 根据订单号获取渠道的订单信息
	 * 
	 * @param orderId
	 * @return
	 */
	Master getMasterOrderByOrderId(String orderId);

	Master getMasterByOrderId(String masterId);

	List<Master> getMasterOrderByPmsIdHotelCode(String pmsId, String hotelCode);

	/**
	 * 根据订单号查询子订单
	 * 
	 * @param orderId
	 * @return
	 */
	public List<Master> getOrderChildByOrderId(String orderId);

	/**
	 * 根据订单号获取价格明细
	 * 
	 * @param masterId
	 * @return
	 */
	public List<MasterRate> getMasterRate(String masterId);

	List<MasterPackage> getMasterPackage(String masterId);

	List<MasterCancel> getMasterCancel(String masterId);

	List<MasterCancel> getMasterCancelByObj(MasterCancel mc);

	List<MasterProfile> getMasterProfile(String masterId);

	/**
	 * 根据条件查询订单
	 * 
	 * @param criteria
	 */
	public OrderSearchResult searchList(SearchOrderCriteria criteria);

	int getChildOrderNum(String masterId, String orderStatus);

	/**
	 * 获取订单号
	 * 
	 * @return
	 */
	public String getMaxOrderIdMasterOrder();

	/**
	 * 获取档案号
	 * 
	 * @return
	 */
	public String getProfileId();

	String getHotelIdByMasterId(String masterId);

	/**
	 * 获取Date之后创建的订单中占用的房量
	 */
	public Integer getMasterAvailableByDate(String hotelCode, String roomTypeCode, Date date);

	/**
	 * 获取stayHistory信息的方法
	 * 
	 * @param rqvo
	 * @return
	 */
	public List<Master> searchOrdersOfStayHistory(StayHistoryRQVO rqvo);

	List<String> getMasterByOsta(String osta, String hotelId, String payment);

	/**
	 * 预订产量会汇总
	 * 
	 * @param criteria
	 * @return
	 */
	public OrderSearchResult searchSummaryOrders(SearchOrderCriteria criteria);

	/**
	 * 根据订单号,日期获取房价明细
	 * 
	 * @param orderNo
	 * @param date
	 * @return
	 */
	public List<MasterRate> getMasterRateByOrderNo(String masterId, String date);

	/**
	 * 根据订单号,获取首日房价明细
	 * 
	 * @param orderNo
	 * @param date
	 * @return
	 */
	public MasterRate getFirstMasterRateByOrderNo(String masterId);

	/**
	 * 修改订单的取消罚金
	 * 
	 * @param master
	 */
	public void updateDeduct(Master master);

	/**
	 * 根据hotel获取相应的market
	 * 
	 * @param HotelIds
	 * @return
	 */
	public List<String> getMarketsByHotelId(String hotelId);

	public Master getMasterOrderByOrderNo(String hotelId, String channelId, String name1, String masterId,String crsno);

	/**
	 * 统计酒店的渠道订单
	 */
	public ChannelOrderResult getChannelOrders(ChannelOrderCriteria channelOrderCriteria);

	/**
	 * 通过入住时间和订单状态获取没有pmsID上传的酒店预定号的订单，订单创建时间大于等于createdTime
	 * 
	 * @param changed
	 * @param sta
	 * @return
	 */
	public List<Master> getMasterByArrSta(Date changed, String sta,Date createdTime);

	/**
	 * 通过入住时间和订单状态获取没有pms上传CANCELPMSID的酒店预定号的订单，订单取消时间大于等于createdTime
	 * 
	 * @param sta
	 * @param endArr
	 * @return
	 */
	public List<Master> getMasterCancelByArrSta(Date startArr,Date createdTime);

	/**
	 * 找到新建订单在MasterPms 表中含有pmsId的订单
	 * 
	 * @param startArr
	 * @param endArr
	 * @return
	 */
	public List<String> getHasPmsIdMaster();

	/**
	 * 找到取消订单在MasterPms 表中含有cancelPmsId的订单
	 * 
	 * @param startArr
	 * @param endArr
	 * @return
	 */
	public List<String> getHasCancelIdMaster(String sta);
	
	/**
	 * 找到取消订单在MasterPms 表中没有PmsId的订单
	 * 
	 * @param startArr
	 * @param endArr
	 * @return
	 */
	public List<String> getNonePmsIdMasterByCancel(String sta);

	/**
	 * 保存无pmsid的订单信息
	 * 
	 * @param masterPms
	 */
	public void saveMasterPms(MasterPms masterPms);

	/**
	 * 修改轮询次数
	 * 
	 * @param masterId
	 * @param number
	 */
	public void updateMasterPms(String masterId, int number, String sta, Date arr);

	/**
	 * 物理删除无需轮询的订单 master_pms表数据
	 * 
	 * @param masterId
	 */
	public void deleteMasterPms(List<String> idList);

	/**
	 * 获取所有需要提醒的MasterPms 数据
	 * 
	 * @return
	 */
	public List<MasterPms> getAllMasterPms();

	public List<OWSReservation> getCreatedOrderHistoryYesterDay(String effectiveDate);

	public List<OWSReservation> getModifedOrderHistoryYesterDay(String effectiveDate);
	
	/**
	 * 修改master中的cancelPmsId
	 * @param masterId
	 * @param cancelPmsId
	 */
	public void updateCancelPmsId(String masterId,String cancelPmsId);
	
	/**
	 * 获取创建日期为指定日期之前的MasterPms
	 * @param time
	 * @return
	 */
	public List<String> getMasterPmsListBYCreatedTime(Date time);
}
