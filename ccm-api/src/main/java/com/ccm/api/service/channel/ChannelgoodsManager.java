/**
 * 
 */
package com.ccm.api.service.channel;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelGoods;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.GoodsVO;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.base.GenericManager;

/**
 * 
 */
public interface ChannelgoodsManager extends GenericManager<ChannelGoods, String> {

	/**
	 * 保存渠道绑定关系
	 * 
	 * @param cg
	 * @param rtList
	 * @param channelIds
	 */
	void saveOrUpdateChannelGoods(ChannelGoods cg, List<String> rtList, List<String> channelIds);
	
	/**
	 * 保存渠道绑定关系
	 * 
	 * @param cg
	 * @param rtList
	 * @param channelIds
	 */
	void saveOrUpdateChannelGoodsForProfile(ChannelGoods cg, List<String> rtList, List<String> channelIds,String hotelId);

	/**
	 * 开通或关闭渠道绑定关系
	 * 
	 * @param cg
	 * @param language
	 */
	void changeChannelGoodsStatus(ChannelGoods cg, String language);

	/**
	 * 逻辑删除绑定关系
	 * 
	 * @param channelgoodsId
	 * @param hotelId
	 */
	void deleteChannelGoods(String channelgoodsId, String hotelId);

	/**
	 * 根据酒店发布绑定关系
	 * 
	 * @param hotelId
	 * @param language
	 */
	void publishChannelGoods(String hotelId, String language);

	/**
	 * 批量发布绑定关系与房价日历
	 * 
	 * @param hotelId
	 * @return
	 */
	boolean publishChannelGoodsBatch(String hotelId);

	/**
	 * 修改酒店渠道绑定关系发布状态
	 * 
	 * @param hotelId
	 * @param status
	 */
	void updateChannelGoodsStatus(String hotelId, String status);

	/**
	 * 根据GID获取Channelgoods
	 * 
	 * @param gid
	 * @return
	 */
	ChannelGoods getChannelgoodsbyGid(String gid);

	/**
	 * 根据酒店ID或(房价代码,房型代码,渠道IDS)查询渠道代码,房价ID,房价代码,房价描述(房价代码不重复)
	 * 
	 * @param cgvo
	 * @return
	 */
	List<ChannelGoodsVO> getChannelRatePlanByChannelGoods(ChannelGoodsVO cgvo);

	/**
	 * 根据酒店ID或(渠道IDS,房价IDS)查询房型ID,房型代码,房价名称(房型代码不重复)
	 * 
	 * @param cgvo
	 * @return
	 */
	List<ChannelGoodsVO> getChannelRoomTypeByChannelGoods(ChannelGoodsVO cgvo);

	/**
	 * 根据酒店ID或(房价代码,房型代码,渠道代码,生效与失效时间)查询酒店ID,房型代码,房价ID,房价代码,生效时间,失效时间,渠道档案ID,
	 * 验证房价类型..已发布的绑定关系
	 * 
	 * @param cgvo
	 * @return
	 */
	List<ChannelGoodsVO> getEnabledChannelGoods(ChannelGoodsVO cgvo);

	/**
	 * 简化版本(根据酒店id和房型代码数组获取正常绑定关系)
	 * 
	 * @param cgvo
	 * @return
	 */
	List<ChannelGoodsVO> getEnabledChannelGoods2(ChannelGoodsVO cgvo);

	/**
	 * 根据酒店ID或(房价代码,房型代码,渠道代码,生效与失效时间)查询酒店ID,房型代码,房价ID,房价代码,生效时间,失效时间,渠道档案ID,
	 * 验证房价类型..未发布与已发布的绑定关系
	 * 
	 * @param cgvo
	 * @return
	 */
	List<ChannelGoodsVO> getEnabledUnOrPublishChannelGoods(ChannelGoodsVO cgvo);

	/**
	 * 根据酒店，房价ID查询绑定关系
	 * 
	 * @param hotelId
	 * @param channelId
	 * @param ratePlanId
	 * @return
	 */
	List<ChannelGoods> getChannelGoodsByHotelIdChannelIdRatePlanId(String hotelId, String channelId, String ratePlanId,Boolean isBind,Date effectiveDate);

	/**
	 * 查询某酒店下的渠道绑定房价列表
	 * 
	 * @param hotelId
	 * @return
	 */
	public List<ChannelGoodsVO> searchChannelGoods(String hotelId, String language);

	/**
	 * 查询酒店和渠道下的渠道绑定房价列表
	 * 
	 * @param cgvo
	 * @return
	 */
	public List<ChannelGoodsVO> searchChannelgoodsByChannelHotel(ChannelGoodsVO cgvo);

	public List<ChannelGoodsVO> searchChannelgoodsByChannelHotelGroupByRoomType(ChannelGoodsVO cgvo);

	/**
	 * 根据房价定义查询房型
	 * 
	 * @param ratePlanId
	 * @return
	 */
	List<RoomTypeVO> getRoomTypeByRatePlanId(String ratePlanId, String language);

	/**
	 * 根据条件判断绑定关系是否存在
	 * 
	 * @param cg
	 * @param channelIds
	 * @return
	 */
	boolean existsChannelGoods(ChannelGoods cg, List<String> channelIds);

	/**
	 * 根据条件查询宝贝
	 * 
	 * @param GoodsVO
	 * @return
	 */
	List<GoodsVO> searchGoodsByCreteria(GoodsVO gvo);

	/**
	 * 根据主键查询宝贝
	 * 
	 * @param channelGoodsId
	 * @return
	 */
	GoodsVO getGoodsByChanelGoodsId(String channelGoodsId);

	/**
	 * 根据主键和语言编码查询宝贝
	 * 
	 * @param channelGoodsId
	 * @return
	 */
	GoodsVO getGoodsByChanelGoodsId(String channelGoodsId, String language);

	String checkGoodsName(String goodsName);

	GoodsVO getGoodsVOById(String channelGoodsId);

	void saveOrUpdateChannelGoods(ChannelGoods cg);

	String getChannelGoodsCodeByRTid(String roomTypeId);

	boolean checkGoods(ChannelGoods cg);

	Map<String, Object> getInventoryPrice(List<Map<String, Object>> inventoryList);

	public List<ChannelGoodsVO> searchRateplansByChannelGoods(ChannelGoodsVO channelGoodsVO);

	/**
	 * 下架产品
	 * 
	 * @throws Exception
	 */
	boolean stopSale(List<String> cg) throws Exception;

	HashMap<String, String> getEnabledChannelCodeMap();

	HashMap<String, HashSet<String>> getChannelRatePlanMap();

	HashMap<String, HashSet<String>> getRatePlanRoomTypeMap();
	/**
	 * 根据酒店ID or房价代码 or房型代码 or渠道IDS or房价IDS查询渠道,房型,房价
	 * @param cgvo
	 * @return
	 */
	public List<ChannelGoodsVO> getChannelGoodsVORoomTypeListByChannelGoods(ChannelGoodsVO cgvo);
	
	public void publishChannelGoodsForProfile(String hotelId, String language,String ratePlanId,String ChannelId);
	
	/**
	 * 根据当前用户渠道权限赛选
	 * @param list
	 * @return
	 */
	List<ChannelGoodsVO> getEnabledChannelGoodsVO(List<ChannelGoodsVO> list);
	
}
