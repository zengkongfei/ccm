/**
 * 
 */
package com.ccm.api.dao.channel.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.model.channel.AdsGoods;
import com.ccm.api.model.channel.ChannelGoods;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.GoodsVO;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.util.CommonUtil;

/**
 * 
 */
@Repository("channelgoodsDao")
public class ChannelgoodsDaoibatis extends GenericDaoiBatis<ChannelGoods, String> implements ChannelgoodsDao {

	public ChannelgoodsDaoibatis() {
		super(ChannelGoods.class);
	}

	/**
	 * 批量添加渠道绑定关系
	 */
	public void saveChannelGoods(List<ChannelGoods> cgList) {
		getSqlMapClientTemplate().insert("addChannelgoodsList", cgList);
	}

	/**
	 * 逻辑删除绑定关系
	 * 
	 * @param cg
	 */
	public void deleteChannelGoods(ChannelGoods cg) {
		getSqlMapClientTemplate().update("deleteChannelgoods", cg);
	}

	/**
	 * 根据酒店发布绑定关系
	 */
	public void publishChannelGoods(ChannelGoodsVO cgv) {
		getSqlMapClientTemplate().update("publishChannelGoods", cgv);
	}

	/**
	 * 根据酒店和房价id发布绑定关系
	 */
	public void publishChannelGoodsByRatePlanId(ChannelGoodsVO cgv) {
		getSqlMapClientTemplate().update("publishChannelGoodsByRatePlanId", cgv);
	}
	
	public void updateChannelGoodsIsBind(AdsGoods goods, Boolean isBind) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotelId", goods.getHotelId());
		map.put("ratePlanCode", goods.getRatePlanCode());
		map.put("channelCode", goods.getChannelCode());
		map.put("roomTypeCode", goods.getRoomTypeCode());
		map.put("isBind", isBind);
		getSqlMapClientTemplate().update("updateChannelGoodsIsBind", map);
	}

	@Override
	public ChannelGoods getChannelgoodsbyGid(String gid) {
		return (ChannelGoods) getSqlMapClientTemplate().queryForObject("getChannelgoodsbyGid", gid);
	}

	/**
	 * 根据酒店ID or房价代码 or房型代码 or渠道IDS or房价IDS查询渠道,房型,房价
	 */
	@SuppressWarnings("unchecked")
	public List<ChannelGoodsVO> getChannelGoodsVORoomTypeByChannelGoods(ChannelGoodsVO cgvo) {
		// 如果没有指定语言编码,则设定为默认语言
		if (cgvo != null && StringUtils.isBlank(cgvo.getLanguage())) {
			cgvo.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("getChannelCodeByChannelGoods", cgvo);
	}

	@SuppressWarnings("unchecked")
	public List<ChannelGoodsVO> searchChannelGoods(String hotelId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hotelId", hotelId);
		return getSqlMapClientTemplate().queryForList("getChannelgoodsByHotelRateId", map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ChannelGoodsVO> getChannelgoodsByBind(String hotelId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hotelId", hotelId);
		return getSqlMapClientTemplate().queryForList("getChannelgoodsByBind", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<ChannelGoodsVO> searchChannelGoodsByRatePlanId(String hotelId,String ratePlanId,String channelId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hotelId", hotelId);
		map.put("ratePlanId", ratePlanId);
		map.put("channelId", channelId);
		return getSqlMapClientTemplate().queryForList("getChannelgoodsByHotelAndRateId", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<ChannelGoodsVO> getPushChannelRelationByHotelRateId(String hotelId, String ratePlanId, String roomTypeId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hotelId", hotelId);
		map.put("ratePlanId", ratePlanId);
		map.put("roomTypeId", roomTypeId);
		return getSqlMapClientTemplate().queryForList("getChannelgoodsByHotelRateId", map);
	}

	@SuppressWarnings("unchecked")
	public List<ChannelGoodsVO> searchChannelgoodsByChannelHotel(ChannelGoodsVO cgvo) {
		// 如果没有指定语言编码,则设定为默认语言
		if (cgvo != null && StringUtils.isBlank(cgvo.getLanguage())) {
			cgvo.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("getChannelgoodsByChannelHotel", cgvo);
	}

	@SuppressWarnings("unchecked")
	public List<ChannelGoodsVO> searchChannelgoodsByChannelHotelGroupByRoomType(ChannelGoodsVO cgvo) {
		// 如果没有指定语言编码,则设定为默认语言
		if (cgvo != null && StringUtils.isBlank(cgvo.getLanguage())) {
			cgvo.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("getChannelgoodsByChannelHotelGroupByRoomType", cgvo);
	}

	@SuppressWarnings("unchecked")
	public List<ChannelGoods> getChannelGoodsByHotelIdChannelIdRatePlanId(ChannelGoods cg) {
		return getSqlMapClientTemplate().queryForList("getChannelGoodsByHotelIdChannelIdRatePlanId", cg);
	}

	/**
	 * 根据条件判断绑定关系是否存在
	 */
	@SuppressWarnings("unchecked")
	public List<String> existsChannelGoods(ChannelGoodsVO cgvo) {
		return getSqlMapClientTemplate().queryForList("existsChannelGoods", cgvo);

	}

	/**
	 * 根据条件查询宝贝
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsVO> searchGoodsByCreteria(GoodsVO gvo) {
		if (gvo != null && StringUtils.isBlank(gvo.getLanguage())) {
			gvo.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchGoodsByCreteria", gvo);
	}

	/**
	 * 根据主键查询宝贝
	 */
	public GoodsVO getGoodsByChanelGoodsId(String channelGoodsId) {
		return this.getGoodsByChanelGoodsId(channelGoodsId, LanguageCode.MAIN_LANGUAGE_CODE);
	}

	@Override
	public GoodsVO getGoodsByChanelGoodsId(String channelGoodsId, String language) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("channelGoodsId", channelGoodsId);
		params.put("language", language);
		return (GoodsVO) getSqlMapClientTemplate().queryForObject("getGoodsByChanelGoodsId", params);
	}

	public String checkGoodsName(String goodsName) {
		return (String) getSqlMapClientTemplate().queryForObject("checkGoodsName", goodsName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ChannelGoods getChannelgoodsByChannelIdAndRoomTypeId(String channelId, String roomTypeId) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("channelId", channelId);
		map.put("roomTypeId", roomTypeId);
		List<ChannelGoods> cgList = getSqlMapClientTemplate().queryForList("getChannelgoodsByChannelIdAndRoomTypeId", map);
		if (CommonUtil.isNotEmpty(cgList)) {
			return cgList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelGoods> getChannelgoodsByCodeMap(Map<String, String> map) {
		return getSqlMapClientTemplate().queryForList("getChannelgoodsByCodeMap", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelGoodsVO> searchRateplansByChannelGoods(ChannelGoodsVO channelGoodsVO) {
		if (channelGoodsVO != null && StringUtils.isBlank(channelGoodsVO.getLanguage())) {
			channelGoodsVO.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchRateplansByChannelGoods", channelGoodsVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelGoodsVO> getChannelCodeByChannelGoods2(ChannelGoodsVO cgvo) {
		return getSqlMapClientTemplate().queryForList("getChannelCodeByChannelGoods2", cgvo);
	}
	
	/**
	 * 根据酒店ID 渠道IDS 查询房型
	 */
	@SuppressWarnings("unchecked")
	public List<ChannelGoodsVO> getChannelGoodsVORoomTypeByChannelGoods2(ChannelGoodsVO cgvo) {
		// 如果没有指定语言编码,则设定为默认语言
		if (cgvo != null && StringUtils.isBlank(cgvo.getLanguage())) {
			cgvo.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("getChannelGoodsVORoomTypeByChannelGoods2", cgvo);
	}
	/**
	 * 根据酒店ID 渠道IDS 查询
	 */
	@SuppressWarnings("unchecked")
	public List<ChannelGoodsVO> getChannelGoodsVOyChannelGoods(ChannelGoodsVO cgvo) {
		// 如果没有指定语言编码,则设定为默认语言
		if (cgvo != null && StringUtils.isBlank(cgvo.getLanguage())) {
			cgvo.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("getChannelGoodsVOByChannelGoods", cgvo);
	}

}
