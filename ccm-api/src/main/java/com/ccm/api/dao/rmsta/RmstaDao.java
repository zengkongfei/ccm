package com.ccm.api.dao.rmsta;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.dao.pms.form.Rmsta;
import com.ccm.api.model.rsvtype.Rsvtype;

public interface RmstaDao extends GenericDao<Rsvtype, String> {
	
	/**
	 * 根据酒店ID和房型code查询房间表中有没有记录
	 */
	public int getCountRmsta(String hotelId,String roomTypeCode);
	
	/**
	 * 根据userID和房型code查询房间表中有没有记录
	 */
	public int getIsNotRmstaCount(String userId,String roomTypeCode);
	
	/**
	 * 根据房号获取可删除的记录大于0表示不能删除
	 * @param roomNo 
	 * @return
	 */
	public int getValidRoomno(String roomNo,String roomTypeCode);
	
	public List<Rmsta> getRmsta(String hotelId, String roomTypeCode);

    public boolean delRmstaByRoomNoAndHotelId(String hotelId, String roomNo);

    public void delRmstaByHotelIdAndRoomTypeCode(String hotelId,String roomTypeCode);
    
    /**
     * 保存房号
     * @param hotelId
     * @param roomNo
     * @param roomTypeCode
     */
    public void saveRmsta(String hotelId,List<String> roomNos,String roomTypeCode);
}
