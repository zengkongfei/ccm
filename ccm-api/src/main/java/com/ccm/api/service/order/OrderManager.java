/**
 * 
 */
package com.ccm.api.service.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterCancel;
import com.ccm.api.model.order.MasterOrder;
import com.ccm.api.model.order.MasterPackage;
import com.ccm.api.model.order.MasterProfile;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.order.vo.OrderEmailConfirmResult;
import com.ccm.api.model.order.vo.OrderSearchResult;
import com.ccm.api.model.order.vo.SearchOrderCriteria;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.rsvtype.vo.SearchOrderEmailConfirmCriteria;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny Liao 订单相关API
 */
public interface OrderManager extends GenericManager<Master, String> {

	// 一般的CRUD操作 和 查询 操作在GenericService里面已经有了
	
	/**
	 * 
	 * @param m
	 */
	void updateMaster(Master m,MasterOrder mo);
	
	/**
	 * 保存或修改OWS订单
	 * 
	 * @param m
	 * @param mList
	 * @return
	 * @throws Exception
	 */
	String saveOrUpdateMasterOrderRate(Master m, List<Master> mList) throws Exception;

	/**
	 * 保存或修改WBE订单信息,返回总订单号
	 * 
	 * @param m
	 * @param mList
	 * @param dbMaster
	 * @return
	 * @throws Exception
	 */
	String saveOrUpdateWBEOrder(Master m, List<Master> mList, Master dbMaster) throws Exception;

	void updateMasterDealRestype(Master m);

	/**
	 * 取消订单,修改master,master_order表及加回房量
	 * 
	 * @param m
	 */
	void cancelOrder(Master m);

	/**
	 * ccm 取消订单,修改master,master_order表及加回房量
	 * 
	 * @param m
	 */
	void cancelOrderCCM(Master m);

	void updateMasterOrderPmsId(MasterOrder mo);

	/**
	 * WBE未支付订单二十五分钟后自动取消
	 */
	void autoCancelNotPaidOrder();

	/**
	 * 订单列表页
	 * 
	 * @param model
	 * @return
	 */
	OrderSearchResult searchOrder(SearchOrderCriteria model);

	/**
	 * 根据订单号查询订单
	 * 
	 * @param orderNo
	 * @return
	 */
	public Master getOrderByOrderNo(String orderNo);

	/**
	 * 根据订单号获取房价明细
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<MasterRate> getMasterRateByOrderNo(String orderNo);

	List<MasterPackage> getMasterPackageByOrderNo(String orderNo);

	List<MasterCancel> getMasterCancelByOrderNo(String orderNo);

	List<MasterProfile> getMasterProfile(String masterId);

	/**
	 * 验证房量
	 * 
	 * @param master
	 */
	void verifyRoomNumbers(Master master);

	BigDecimal validCancelPolicy(Master master, Date arr, BigDecimal roomPrice, BigDecimal staticPackage, int days);

	/**
	 * */
	/**
	 * 获取一间房的动态和静态包价
	 * 
	 * @param master
	 * @param pcList
	 * @return
	 * @throws Exception
	 */
	List<MasterPackage> getPackageInfo(Master master, List<PriceCalc> pcList) throws Exception;

	int getChildOrderNum(String masterId, String orderStatus);

	/**
	 * 根据订单号查询子订单
	 * 
	 * @param orderId
	 * @return
	 */
	List<Master> getOrderChildByOrderId(String orderId);

	Master getParentOrderById(String parentId);

	/**
	 * 根据渠道订单号查询master主表
	 * 
	 * @param channelOrderId
	 * @return
	 */
	List<Master> getMasterByCrsno(String channelOrderId);

	List<Master> getChildOrderByParentId(String parentId);

	/**
	 * 验证信用卡信息
	 * 
	 * @param master
	 * @throws Exception
	 */
	void getEnableGuaranteeType(Master master) throws Exception;

	/**
	 * 预订产量会汇总
	 * 
	 * @param criteria
	 * @return
	 */
	public OrderSearchResult searchSummaryOrders(SearchOrderCriteria criteria);

	/**
	 * 订单邮件确认函报表
	 * 
	 * @param criteria
	 * @return
	 */
	public OrderEmailConfirmResult searchOrderEmailConfirm(SearchOrderEmailConfirmCriteria criteria);

	/**
	 * 根据订单号,日期获取房价明细
	 * 
	 * @param orderNo
	 * @param date
	 * @return
	 */
	public List<MasterRate> getMasterRateByOrderNo(String masterId, String date);

	/**
	 * 开启酒店配置hardcancel为true,启用保证金，且为预付订单，此时，只接收hardcancel的取消请求 暂时废弃，此逻辑无用
	 * 
	 * @param master
	 * @param custom
	 */
	public boolean isCancel(Master master);

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

	/**
	 * 是否可以下单
	 * 
	 * @param key
	 * @param channelId
	 * @return
	 */
	public boolean isReservation(String key, String channelId);

	/**
	 * 渠道1分钟内下单次数+1，如果没有，就插入
	 * 
	 * @param channelId
	 */
	public void addReverationTime(String channelId);

	/**
	 * 删除redis中存的key=渠道id+客人名字+房型+入住时间 的数据
	 * 
	 * @param key
	 */
	public void deleReverationKey(String key);

	/**
	 * 订单处理状态，value=true,处理中，value=false,处理结束
	 * key=ChannelId+firstName+lastName+nationName+房型+入住时间
	 * 
	 * @param key
	 * @param value
	 * @param time
	 */
	public void addRedisDealOrder(String key, Boolean value, long time);

}
