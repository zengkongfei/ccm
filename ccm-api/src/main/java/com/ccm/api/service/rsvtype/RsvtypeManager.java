package com.ccm.api.service.rsvtype;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.vo.RateCodeWithRoomVO;
import com.ccm.api.model.rsvtype.vo.RentSearchResult;
import com.ccm.api.model.rsvtype.vo.RoomStatusSetVO;
import com.ccm.api.model.rsvtype.vo.RoomStatusVO;
import com.ccm.api.model.rsvtype.vo.SearchRentCriteria;
import com.ccm.api.service.base.GenericManager;

public interface RsvtypeManager extends GenericManager<Rsvtype, String> {

	/**
	 * 查询淘宝上房量与本地放量不一致
	 * 
	 * @param hotelIds
	 *            当前用户下所有酒店ID
	 */
	public List<Rsvtype> roomNumCompare(List<String> hotelIds);

	/**
	 * 查询房价日历表,获取自当天起至90天的房价房量
	 */
	public List<Rsvtype> getDate90RsvtypeList(List<String> hotelIds, String roomTypeCode, String sta);

	public void saveRsvtype(Rsvtype rt);

	public void updateRsvtype(Rsvtype rt);

	public Rsvtype getRsvtype(Rsvtype rt);


	/**
	 * 保存房态设置数据
	 */
	public void saveSell(RoomStatusSetVO setvo,Boolean isPush);
	
    public List<Rsvtype> searchResvType(Rsvtype rsv);

    public void setOverBooking(RoomStatusVO roomStatusVO, Boolean isPush);

    public void setBatchOverBooking(RoomStatusSetVO setvo, Boolean isPush);
    
    
	public void addBatchRsvtypes(List<Rsvtype> rsvList);

	public void deleteBatchRsvtypes(List<Rsvtype> rsvList);
	
	
	public void updateBatchRsvtypes(List<Rsvtype> rsvList);
	
	
	public List<Rsvtype> getRsvtypeByDateSpan(String hotelCode, String[] arrayOfRoom, Date dateStart,Date dateEnd);

	public void setBatchOverBooking(List<String> daysList,
			RoomStatusSetVO setvo, Boolean isPush) throws Exception;
	
	/**批量保存销售数据  保留房、freeSell*/
	public void setBatchSaveSell(List<String> subList, RoomStatusSetVO setvo,
			boolean isPush) throws Exception;
	
	public void removeBatchSell(List<String> dayList, RoomStatusSetVO setvo,
			boolean isPush) throws Exception;
	/**
	 * 酒店房量出租率
	 * @param criteria
	 * @return
	 */
    public RentSearchResult searchHotelRoomRentRates(SearchRentCriteria criteria);

    public List<RateCodeWithRoomVO> getRateCodeFromRoomType(String hotelCode,
			String startDate, String endDate, List<String> roomCodeList,String weekRange);
}
