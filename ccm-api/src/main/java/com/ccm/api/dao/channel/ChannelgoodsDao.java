/**
 * 
 */
package com.ccm.api.dao.channel;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.AdsGoods;
import com.ccm.api.model.channel.ChannelGoods;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.GoodsVO;

public interface ChannelgoodsDao extends GenericDao<ChannelGoods, String> {

	/**
	 * 批量添加渠道绑定关系
	 * 
	 * @param cgList
	 */
	void saveChannelGoods(List<ChannelGoods> cgList);

	/**
	 * 逻辑删除绑定关系
	 * 
	 * @param cg
	 */
	void deleteChannelGoods(ChannelGoods cg);

	/**
	 * 根据酒店发布绑定关系
	 * 
	 * @param ChannelGoodsVO
	 *            cgv
	 */
	void publishChannelGoods(ChannelGoodsVO cgv);

	/**
	 * 根据酒店和房价id发布绑定关系
	 */
	public void publishChannelGoodsByRatePlanId(ChannelGoodsVO cgv);
	
	/**
	 * 
	 * @param goods
	 * @param isBind
	 */
	void updateChannelGoodsIsBind(AdsGoods goods, Boolean isBind);
	
	/**
	 * 根据GID获取Channelgoods
	 * 
	 * @param gid
	 * @return
	 */
	ChannelGoods getChannelgoodsbyGid(String gid);

	/**
	 * 根据酒店ID or房价代码 or房型代码 or渠道IDS or房价IDS查询渠道,房型,房价
	 * 
	 * @param cgvo
	 * @return
	 */
	public List<ChannelGoodsVO> getChannelGoodsVORoomTypeByChannelGoods(ChannelGoodsVO cgvo);

	/**
	 * 
	 * @return
	 */
	public List<ChannelGoodsVO> getChannelCodeByChannelGoods2(ChannelGoodsVO cgvo);

	/**
	 * 根据酒店，渠道，房价查询绑定关系
	 * 
	 * @param cg
	 * @return
	 */
	public List<ChannelGoods> getChannelGoodsByHotelIdChannelIdRatePlanId(ChannelGoods cg);

	/**
	 * 查询某酒店下的渠道绑定房价列表
	 * 
	 * @param hotelId
	 * @return
	 */
	public List<ChannelGoodsVO> searchChannelGoods(String hotelId);
	
	public List<ChannelGoodsVO> searchChannelGoodsByRatePlanId(String hotelId,String ratePlanId,String channelId);

	List<ChannelGoodsVO> getPushChannelRelationByHotelRateId(String hotelId, String ratePlanId, String roomTypeId);

	/**
	 * 查询酒店和渠道下的渠道绑定房价列表
	 * 
	 * @param cgvo
	 * @return
	 */
	public List<ChannelGoodsVO> searchChannelgoodsByChannelHotel(ChannelGoodsVO cgvo);

	/**
	 * 查询酒店和渠道下的渠道绑定房型列表
	 * 
	 * @param cgvo
	 * @return
	 */
	public List<ChannelGoodsVO> searchChannelgoodsByChannelHotelGroupByRoomType(ChannelGoodsVO cgvo);

	/**
	 * 根据条件判断绑定关系是否存在
	 * 
	 * @param cgvo
	 * @return
	 */
	List<String> existsChannelGoods(ChannelGoodsVO cgvo);

	/**
	 * 根据条件查询宝贝
	 * 
	 * @param userId
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

	/***
	 * 根据渠道id、房型id获取渠道宝贝，返回带channelgoodsCode的那条
	 * 
	 * @param channelId
	 * @param roomTypeId
	 * @return
	 */
	ChannelGoods getChannelgoodsByChannelIdAndRoomTypeId(String channelId, String roomTypeId);

	List<ChannelGoods> getChannelgoodsByCodeMap(Map<String, String> map);

	List<ChannelGoodsVO> searchRateplansByChannelGoods(ChannelGoodsVO channelGoodsVO);
	
	/**
	 * 根据酒店ID 渠道IDS 查询房型
	 * @param cgvo
	 * @return
	 */
	public List<ChannelGoodsVO> getChannelGoodsVORoomTypeByChannelGoods2(ChannelGoodsVO cgvo);
	/**
	 * 根据酒店ID 渠道IDS 查询
	 * @param cgvo
	 * @return
	 */
	public List<ChannelGoodsVO> getChannelGoodsVOyChannelGoods(ChannelGoodsVO cgvo);

	List<ChannelGoodsVO> getChannelgoodsByBind(String hotelId);

}
