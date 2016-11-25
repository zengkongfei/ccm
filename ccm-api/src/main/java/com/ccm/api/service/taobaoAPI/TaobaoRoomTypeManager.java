package com.ccm.api.service.taobaoAPI;

import com.taobao.api.domain.Hotel;
import com.taobao.api.domain.Room;
import com.taobao.api.domain.RoomImage;
import com.taobao.api.domain.RoomType;
import com.taobao.api.response.HotelRoomsSearchResponse;

/**
 * 房型商品定义
 * 
 * @author carr
 * 
 */
public interface TaobaoRoomTypeManager {
	/**
	 * taobao.hotel.type.name.get 根据房型名称/别名获得对应房型
	 */
	public RoomType getRoomType(long hotelId, String roomTypeName, String sessionKey);

	/**
	 * taobao.hotel.rooms.search 多商品查询接口
	 * pageNo:分页页码。取值范围，大于零的整数，默认值1，即返回第一页的数据。页面大小为20
	 */
	public HotelRoomsSearchResponse roomsSearch(String hotelId, String roomTypeId, long pageNo, String sessionKey);

	/**
	 * taobao.hotel.type.add 房型发布接口
	 */
	public RoomType roomTypeAdd(long hotelId, String roomTypeName, String sessionKey);

	/**
	 * taobao.hotel.get 单酒店查询接口
	 */
	public Hotel getHotel(long hotelId, String sessionKey, boolean checkAudit);

	/**
	 * taobao.hotel.get 获取单酒店和房型接口
	 */
	public Hotel getHotelAndRoomTypes(long hotelId, String sessionKey);

	/**
	 * taobao.hotel.room.img.upload 商品图片上传接口 roomId:酒店商品id position:图片序号
	 * pic:图片上传路径
	 */
	public RoomImage roomImgUpload(long roomId, long position, String pic, String sessionKey);

	/**
	 * taobao.hotel.room.get 单商品查询接口 roomId:酒店商品ID
	 */
	public Room getRoom(long roomId, String sessionKey);

	/**
	 * taobao.hotel.room.img.delete 商品图片删除接口 roomId:酒店商品id position:图片序号
	 */
	public RoomImage RoomImgDelete(long gid, long position, String sessionKey);
	
	/**
     * taobao.hotel.rooms.search 多商品查询接口
     */
    public HotelRoomsSearchResponse roomsSearch(Long pageNo);

}
