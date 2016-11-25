package com.ccm.api.service.taobaoAPI;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.taobaoVO.RoomQuotaVO;
import com.ccm.api.model.taobaoVO.RoomVO;
import com.taobao.api.domain.Room;

/**
 * 商品发布
 * @author carr
 *
 */
public interface TaobaoRoomManager {
	/**
	 * 房态发布，单商品发布（西软调用）
	 * hotelId:酒店ID
	 * roomTypeCode：房型Code
	 * flag:0发布，1修改房态，2修改状态，3商品编辑（状态和房态外）
	 */
	public Room releaseRoomQuota(String hotelId,String roomTypeCode,String flag);
	
	/**
	 * 房态发布，单商品发布（本地调用）
	 * hotelId:酒店ID
	 * roomTypeId：房型ID
	 * voList：日期；价格；房量
	 */
	public Room releaseRoomQuota(String hotelId,String roomTypeId,
			List<RoomQuotaVO> voList,String sessionKey,String flag);
	
	/**
	 * taobao.hotel.room.add 商品发布接口
	 */
	public Room roomAdd(RoomVO vo,String sessionKey);
	
	/**
	 * taobao.hotel.room.update 商品更新接口
	 */
	public Room roomUpdate(RoomVO vo,String sessionKey,String flag);
	
	/**
	 * 发送房态到淘宝
	 */
	public Room roomQuotasUpdate(RoomVO vo,String sessionKey);
	
	/**
	 * taobao.logistics.dummy.send 无需物流（虚拟）发货处理 
	 * @param tid 淘宝交易ID
	 * @return 是否发布成功
	 */
	public boolean dummySend(String tid,String sessionKey);
	
	/**
	 * 畅连接口数据发送到淘宝
	 * @param channelCode渠道编码
	 * @param gid淘宝商品ID
	 * @param date发送结束日期
	 * @param echoToken发送接口标识
	 * @param adsType接口名称
	 */
	public void onlineToTaobao(String channelCode,String gid,Date date,String echoToken,String adsType);
}
