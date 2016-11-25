package com.ccm.api.service.availability;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.ws.vo.availability.AvailabilityRQVO;

public interface AvailabilityManager {

	/**
	 * general协议
	 * @param availability
	 * @return
	 * @throws Exception
	 */
	public HotelVO getHotelWithGeneral(AvailabilityRQVO availability) throws Exception;
	
	/**
	 * detail协议
	 * @param availability
	 * @return
	 * @throws Exception
	 */
	public HotelVO getHotelWithDetail(AvailabilityRQVO availability) throws Exception;
	
	/**
	 * 验证最小/最大连住天数和最小/最大提前预订天数
	 * 
	 * @param priceCalcList
	 * @param pc
	 * @return
	 * @throws Exception
	 */
	List<PriceCalc> getPriceCalcListDays(List<PriceCalc> priceCalcList, PriceCalc pc) throws Exception;

	/**
	 * 获取房价日历，根据可用房量筛选
	 * 
	 * @param priceCalcList
	 * @param numberOfRooms
	 * @return
	 * @throws Exception
	 */
	List<PriceCalc> getPriceCalcListByAvail(List<PriceCalc> priceCalcList, int numberOfRooms) throws Exception;

	/**
	 * 根据ratePlanIdList获取所有的包价信息
	 * 
	 * @param ratePlanIds
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	List<PackageVO> getPackageVOs(List<String> ratePlanIds, Date startDate, Date endDate, String language) throws Exception;

	/**
	 * 根据房价Id和房型Id获取所有的包价信息
	 * 
	 * @param voList
	 * @param ratePlanId
	 * @param roomTypeId
	 * @return
	 * @throws Exception
	 */
	List<PackageVO> getPackageVOsByRatePlanAndRoomType(List<PackageVO> voList, String ratePlanId, String roomTypeId) throws Exception;

	/**
	 * 计算包价金额
	 * 
	 * @param pvolist
	 * @param startDate
	 * @param endDate
	 * @param numberOfRooms
	 * @param totalNumberOfGuests
	 * @param p
	 * @return
	 * @throws Exception
	 */
	public List<PackageVO> countPackageAmount(List<PackageVO> pvolist, Date startDate, Date endDate, Integer numberOfRooms, Integer totalNumberOfGuests, PriceCalc p) throws Exception;
	
	/**
	 * 获取每日包价合计金额
	 * @param pvolist
	 * @return
	 */
	public BigDecimal getDayPackageAmount(List<PackageVO> pvolist);
	
	List<PackageVO> getRoomTypePackageByRateDetailId(String rateDetailId, String language, Date start, Date end) throws Exception;
}
