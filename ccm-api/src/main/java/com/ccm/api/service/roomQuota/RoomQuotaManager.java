package com.ccm.api.service.roomQuota;

import java.util.List;

import com.ccm.api.model.roomQuota.RoomQuota;
import com.ccm.api.model.roomQuota.vo.RoomService;
import com.ccm.api.model.roomQuota.vo.RoomsCreateVO;
import com.ccm.api.model.roomQuota.vo.RoomsInitVO;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.taobaoVO.RoomQuotaVO;
import com.ccm.api.model.taobaoVO.RoomVO;
import com.ccm.api.service.base.GenericManager;

public interface RoomQuotaManager extends GenericManager<RoomQuota, String> {

	/**
	 * 从价格日历表查询房价是否改动 true：表示有改动
	 */
	public boolean queryPriceCalendar(RoomsCreateVO vo);

	/**
	 * 根据淘宝酒店ID查询所有已发布和未发布的商品(本地查询) flag：0表示已发布(包括上架和下架的商品)；1表示未发布;2表示全部
	 */
	public List<RoomsInitVO> getRoomsInitvos(String hid, String flag);
	
	/**
	 *  根据淘宝酒店ID查询所有已发布和未发布的商品(本地查询) flag：0表示已发布(包括上架和下架的商品)；1表示未发布;2表示全部,包含语言
	 * @param hid
	 * @param flag
	 * @param language
	 * @return
	 */
	public List<RoomsInitVO> getRoomsInitvos(String hid, String flag, String language);
	
	/**
	 * 根据酒店ID和房型ID,语言
	 * @param hotelId
	 * @param roomTypeId
	 * @param language
	 * @return
	 */
	public RoomVO getRoomVO(String hotelId, String roomTypeId, String language);

	/**
	 * 从淘宝获得已发布的商品 查询上架，下架或删除的商品 用List<List<RoomsInitVO>>表示
	 * status:1表示全部，2表示上架，3表示下架
	 */
	// public List<List<RoomsInitVO>> getReleasedRoomsInitvosList(String userId,
	// int status, String sessionKey);

	/**
	 * 获得本地未发布的和淘宝已发布的商品 status:1表示全部，2表示上架，3表示下架 用List<List<RoomsInitVO>>表示
	 * flag：0表示已发布；1表示未发布;2表示全部
	 */
	// public List<List<RoomsInitVO>> getUnReleaseRoomsInitvosList(String
	// userId, int status, String flag, String sessionKey);

	/**
	 * 获取酒店设施 roomTypeId:房型ID
	 * 
	 * @param roomTypeId
	 * @return
	 */
	public RoomService getRoomsService(String roomTypeId);

	/**
	 * 根据价格日历表获取List<RoomQuotaVO>
	 */
	public List<RoomQuotaVO> getRoomQuotaVOList(String hotelId, String roomTypeCode);

	/**
	 * List<Rsvtype>转换为List<RoomQuotaVO>
	 * 
	 * @param rList
	 * @return
	 */
	public List<RoomQuotaVO> getRoomQuotaList(List<Rsvtype> rList);

	/**
	 * 根据房型ID获取code
	 * 
	 * @param roomTypeId
	 * @return
	 */
	public String getRoomTypeCode(String roomTypeId);

	/**
	 * 获得房量 如果房间表中没有记录，则设置房量为初始化的房量
	 */
	public int getRoomQuoAmount(Rsvtype rsvtype);

	/**
	 * 当前用户下是否有未发布的商品或没有创建房间的商品
	 */
	public String getInitMessage(String userId);

	/**
	 * 酒店启用后发布淘宝下房态
	 * 
	 * @param hotelId
	 * @return
	 */
	public String releaseHotelRoomQuota(String hotelId);
}
