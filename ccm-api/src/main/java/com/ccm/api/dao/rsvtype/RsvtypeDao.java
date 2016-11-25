package com.ccm.api.dao.rsvtype;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.vo.RateCodeWithRoomVO;
import com.ccm.api.model.rsvtype.vo.RentSearchResult;
import com.ccm.api.model.rsvtype.vo.SearchRentCriteria;
import com.ccm.api.model.ws.vo.availability.AvailabilityRQVO;
import com.ccm.api.model.ws.vo.availability.RoomRateRSVO;
import com.ccm.api.model.ws.vo.fetchCalendar.FetchCalendarRQVO;
import com.ccm.api.model.ws.vo.fetchCalendar.FetchCalendarRSVO;

public interface RsvtypeDao extends GenericDao<Rsvtype, String> {

	/**
	 * 修改房价日历表发布状态
	 */
	public void updateRsvtypeSta(String hotelId, String roomTypeCode, int sta);

	/**
	 * 查询房价日历表,获取自当天起至90天的房价房量
	 */
	public List<Rsvtype> getDate90RsvtypeList(String hotelId, String roomTypeCode, String sta);

	/**
	 * 查询房价日历表,获取自当天起至90天的房量(淘宝和本地不同步的)
	 */
	public List<Rsvtype> getDate90RsvtypeListOfChange(String hotelId, String roomTypeCode, String sta);

	/**
	 * 查询房量
	 */
	public List<Integer> getAvailableListOfRsvtype(String hotelId, String roomTypeCode);

	Rsvtype getRsvtypeByHotelIdDateType(String hotelId, String roomTypeCode, String date);

	void updateRsvtype(Rsvtype rt);
	
	void saveRsvtype(Rsvtype rt);
	
	void addBatchRsvtypes(List<Rsvtype> rsvList);
	
	void deleteBatchRsvtypes(List<Rsvtype> rsvList);
	
	void updateBatchRsvtypes(List<Rsvtype> rsvList);

	/**
	 * 获取首日房价
	 */
	public List<RoomRateRSVO> getGeneralBaseAmount(AvailabilityRQVO availability);

	/**
	 * 获取房价合计金额
	 */
	public List<RoomRateRSVO> getGeneralTotalAmount(AvailabilityRQVO availability);

	/**
	 * 房价总价合计（含服务费），计算方法为：每日单价累加*房间数量
	 */
	public RoomRateRSVO getDetailTotalRoomRateandpackages(AvailabilityRQVO availability);

	/**
	 * 获取单日房价
	 */
	public List<RoomRateRSVO> getDetailRoomRateAndPackages(AvailabilityRQVO availability);

	/**
	 * 查询房价日历表
	 */
	public Rsvtype getRsvtype(Rsvtype rsvtype);

	/**
	 * 订单获取可用房量
	 * 
	 * @param rsvtype
	 * @return
	 */
	public Map<Object, Object> getResvTypeAvail(String roomTypeCode, String hotelCode, Date checkinDate, Date checkoutDate);
	
	/**
	 * 获取一天的可用房量
	 */
	public Map<Object, Object> getResvTypeAvailByDay(String roomTypeCode, String hotelCode, Date date);

	/**
	 * ows接口Availability-FetchCalendar
	 */
	public List<FetchCalendarRSVO> getFetchCalendar(FetchCalendarRQVO vo);

    public List<Rsvtype> searchResvType(Rsvtype rsv);

    public List<Rsvtype> getRsvtypeByDateSpan(String hotelCode,String[] arrayOfRoom, Date dateStart,Date dateEnd);
    
	List<Rsvtype> getRsvtypeByHotelRoomDate(Map<String, Object> paramMap);

	/**
	 * 酒店房量出租率
	 * @param criteria
	 * @return
	 */
    public RentSearchResult searchHotelRoomRentRates(SearchRentCriteria criteria);

    public List<RateCodeWithRoomVO> getRateCodeFromRoomType(String hotelCode,String startDate,String endDate,List<String> roomCodeList,String weekRange);
}
