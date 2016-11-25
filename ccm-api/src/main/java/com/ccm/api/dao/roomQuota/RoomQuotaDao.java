package com.ccm.api.dao.roomQuota;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.roomQuota.RoomQuota;
import com.ccm.api.model.roomQuota.vo.RoomsCreateVO;
import com.ccm.api.model.roomQuota.vo.RoomsInitVO;
import com.ccm.api.model.taobaoVO.RoomVO;

public interface RoomQuotaDao extends GenericDao<RoomQuota, String> {

	/**
	 * 根据酒店ID查询已发布或未发布的商品
	 * flag：0表示已发布；1表示未发布;2表示全部
	 */
	public List<RoomsInitVO> getRoomsInitvos(String hid,String flag);
	
	/**
	 * 根据渠道商品主键查询商品
	 * hotelId:酒店ID
	 * roomTypeId:房型ID
	 */
	public RoomVO getRoomVO(String hotelId,String roomTypeId);
	
	/**
	 * 保存房态
	 */
	public void saveRoomQuota(RoomQuota roomQuota);
	
	/**
	 * 从价格日历表查询房价是否改动
	 * true：表示有改动
	 */
	public int queryPriceCalendar(RoomsCreateVO vo);
	
	/**
	 * 保存渠道商品代码
	 * channelGoodsCode:淘宝商品ID
	 * roomTypeId：房型ID
	 */
	public void updateChannelGoodsCode(String channelGoodsCode,String roomTypeId);
	
	/**
	 * 获取酒店设施
	 * roomTypeId:房型ID
	 */
	public List<String> getRoomsService(String roomTypeId);
	
	/**
	 * 根据房型ID取RoomTypeCode
	 */
	public String getRoomTypeCode(String roomTypeId);
	
	/**
	 * 根据房型code取RoomTypeId
	 */
	public String getRoomTypeID(String roomTypeCode);
	
	/**
	 *  根据酒店ID和房型CODE查询房态房量
	 */
	public Integer getRoomQuotaAmount(String hotelId,String roomTypeCode);
	
	/**
	 *  根据UserId获取没有发布的商品总数
	 */
	public Integer getGidIsNullCount(String userId);
	
	/**
	 *  删除房态
	 */
	public void deleteRoomquota(RoomQuota roomQuota);

	RoomVO getRoomVO(String hotelId, String roomTypeId, String language);

	List<RoomsInitVO> getRoomsInitvos(String hid, String flag, String language);
}
