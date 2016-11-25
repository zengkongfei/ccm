package com.ccm.api.dao.guestRoom;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.guestroom.GuestRoom;
import com.ccm.api.model.guestroom.GuestroomI18n;
import com.ccm.api.model.ws.vo.availability.AvailabilityRQVO;
import com.ccm.api.model.ws.vo.availability.RoomTypeRSVO;

public interface GuestRoomDao extends GenericDao<GuestRoom,String>{

    List<Map<String, String>> getChannelGoodList(String hotelId);

    /**获取客房房型名称和客房房型ID*/
    List<Map<String, String>> getGuestRoomIdMap(String userId);

    List<GuestroomI18n> checkExistsRoomTypeName(String hotelId, String roomtypename);
    
    /**
     * 根据userId获取房型Code
     * @param userId
     * @return
     */
    public List<String> getRoomTypeCodeByUserId(String userId);
    
    /**
     * 根据用户ID获取已编辑过的GuestRoom
     * @param userId
     * @return
     */
    public List<GuestRoom> getEditedGuestRoom(String userId);
    
    /**
     *  根据gid获取GuestRoom
     * @param gid
     * @return
     */
    public GuestRoom getGuestRoomByGid(String gid);
    
    /**
     *  查询酒店下所有未删除并且有Gid的宝贝
     * @param hotelId
     * @return
     */
    public List<GuestRoom> getGuestRoomByHotelIdAndGidIsNotNull(String hotelId);
    
    /**
     *  查询房型
     * @param hotelId
     * @return
     */
    public List<RoomTypeRSVO> getGeneralRoomTypeRSVO(AvailabilityRQVO availability);
    
    /**
     *  明细查询房型
     * @param hotelId
     * @return
     */
    public List<RoomTypeRSVO> getDetailRoomTypeRSVO(AvailabilityRQVO availability);
}
