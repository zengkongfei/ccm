package com.ccm.api.dao.rsvtype.ibatis;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.model.rsvtype.Rent;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.vo.RateCodeWithRoomVO;
import com.ccm.api.model.rsvtype.vo.RentSearchResult;
import com.ccm.api.model.rsvtype.vo.SearchRentCriteria;
import com.ccm.api.model.ws.vo.availability.AvailabilityRQVO;
import com.ccm.api.model.ws.vo.availability.RoomRateRSVO;
import com.ccm.api.model.ws.vo.fetchCalendar.FetchCalendarRQVO;
import com.ccm.api.model.ws.vo.fetchCalendar.FetchCalendarRSVO;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ibatis.sqlmap.client.SqlMapExecutor;

@Repository("rsvtypeDao")
public class RsvtypeDaoibatis extends GenericDaoiBatis<Rsvtype, String> implements RsvtypeDao {
    
	public RsvtypeDaoibatis() {
		super(Rsvtype.class);
	}

	@Override
	public void updateRsvtypeSta(String hotelId, String roomTypeCode, int sta) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotelId", hotelId);
		map.put("roomTypeCode", roomTypeCode);
		map.put("sta", sta);
		getSqlMapClientTemplate().update("updateRsvtypeSta", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rsvtype> getDate90RsvtypeList(String hotelId, String roomTypeCode, String sta) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotelId", hotelId);
		map.put("roomTypeCode", roomTypeCode);
		map.put("sta", sta);
		return getSqlMapClientTemplate().queryForList("getDate90RsvtypeList", map);
	}

	public Rsvtype save(Rsvtype rt) {
	    rt.setCreatedBy(SecurityHolder.getUserId());
        rt.setCreatedTime(new Date());
        rt = (Rsvtype) getSqlMapClientTemplate().insert("addRsvtype", rt);
		return rt;
	}

	@Override
	public Rsvtype getRsvtypeByHotelIdDateType(String hotelId, String roomTypeCode, String date) {
		Rsvtype rt = new Rsvtype();
		rt.setHotelid(hotelId);
		rt.setType(roomTypeCode);
		try {
			rt.setDate(DateUtil.convertStringToDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (Rsvtype) getSqlMapClientTemplate().queryForObject("getRsvtypeByHotelIdDateType", rt);
	}

	@Override
	public void updateRsvtype(Rsvtype rt) {
	    rt.setUpdatedBy(SecurityHolder.getUserId());
        rt.setLastModifyTime(new Date());
        getSqlMapClientTemplate().update("updateRsvtype", rt);
	}
	
	@Override
	public void saveRsvtype(Rsvtype rt) {
		rt.setRsvtypeId(UUID.randomUUID().toString().replace("-", ""));
		rt.setCreatedBy(SecurityHolder.getUserId());
		rt.setCreatedTime(new Date());
		getSqlMapClientTemplate().update("addRsvtype", rt);
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Rsvtype> getDate90RsvtypeListOfChange(String hotelId, String roomTypeCode, String sta) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hotelId", hotelId);
		map.put("roomTypeCode", roomTypeCode);
		map.put("sta", sta);
		return getSqlMapClientTemplate().queryForList("getDate90RsvtypeListOfChange", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getAvailableListOfRsvtype(String hotelId, String roomTypeCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hotelId", hotelId);
		map.put("roomTypeCode", roomTypeCode);
		return getSqlMapClientTemplate().queryForList("getAvailableListOfRsvtype", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomRateRSVO> getGeneralBaseAmount(AvailabilityRQVO availability) {
		return getSqlMapClientTemplate().queryForList("getGeneralBaseAmount", availability);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomRateRSVO> getGeneralTotalAmount(AvailabilityRQVO availability) {
		return getSqlMapClientTemplate().queryForList("getGeneralTotalAmount", availability);
	}

	@Override
	public RoomRateRSVO getDetailTotalRoomRateandpackages(AvailabilityRQVO availability) {
		return (RoomRateRSVO) getSqlMapClientTemplate().queryForObject("getDetailTotalRoomRateandpackages", availability);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomRateRSVO> getDetailRoomRateAndPackages(AvailabilityRQVO availability) {
		return getSqlMapClientTemplate().queryForList("getDetailRoomRateAndPackages", availability);
	}

	@Override
	public Rsvtype getRsvtype(Rsvtype rsvtype) {
			return (Rsvtype) getSqlMapClientTemplate().queryForObject("getRsvtype", rsvtype);
	}
	
	@Override
	public Rsvtype get(String id) {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap=new HashMap<String,Object> ();
		paramMap.put("rsvtypeId",id);
		return (Rsvtype) getSqlMapClientTemplate().queryForObject("getRsvtypeById",paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rsvtype> getRsvtypeByDateSpan(String hotelCode, String[] arrayOfRoom, Date dateStart,Date dateEnd){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hotelCode", hotelCode);
		params.put("dateStart", dateStart);
		params.put("dateEnd", dateEnd);
		params.put("roomTypeCodes", arrayOfRoom);
		return getSqlMapClientTemplate().queryForList("getRsvtypeByDateSpan", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Rsvtype> getRsvtypeByHotelRoomDate(Map<String, Object> paramMap) {
		return getSqlMapClientTemplate().queryForList("getRsvtypeByHotelRoomDate", paramMap);
	}
	
	/**
	 * 订单获取可用房量
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> getResvTypeAvail(String roomTypeCode, String hotelCode, Date checkinDate, Date checkoutDate) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelCode", hotelCode);
		param.put("type", roomTypeCode);
		param.put("checkinDate", checkinDate);
		param.put("checkoutDate", checkoutDate);
		return getSqlMapClientTemplate().queryForMap("getResvTypeAvail", param, "count", "available");
	}
	
	/**
	 * 获取一天的可用房量
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> getResvTypeAvailByDay(String roomTypeCode, String hotelCode, Date date) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelCode", hotelCode);
		param.put("type", roomTypeCode);
		param.put("date", date);
		return getSqlMapClientTemplate().queryForMap("getResvTypeAvailByDay", param, "count", "available");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FetchCalendarRSVO> getFetchCalendar(FetchCalendarRQVO vo) {
		return getSqlMapClientTemplate().queryForList("getFetchCalendar", vo);
	}
	
    @SuppressWarnings("unchecked")
	@Override
    public List<Rsvtype> searchResvType(Rsvtype rsv) {
        return getSqlMapClientTemplate().queryForList("searchResvType", rsv);
    }

	@Override
	public void addBatchRsvtypes(List<Rsvtype> rsvList) {
		if(CommonUtil.isNotEmpty(rsvList)){
			long t1=System.currentTimeMillis();
			getSqlMapClientTemplate().insert("addBatchRsvtypes", rsvList);
			 System.out.println("addBatchRsvtypes 这段代码运行了:"+ (System.currentTimeMillis()-t1)/1000.0 + "秒！"+"一共  "+rsvList.size()+" 条");
		}
	}
	
	@Override
	public void deleteBatchRsvtypes(List<Rsvtype> rsvList){
		getSqlMapClientTemplate().delete("deleteBatchRsvtypes", rsvList);
	}

	@Override
	public void updateBatchRsvtypes(List<Rsvtype> rsvList) {
		//getSqlMapClientTemplate().update("updateBatchRsvtypes", rsvList);
		this.batchUpdate("updateRsvtype", rsvList);
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void batchUpdate(final String statementName, final List list) {
		long t1=System.currentTimeMillis();
		try {
			if (list != null) {
				this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
	                  public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
	                     executor.startBatch();
	                     for (int i = 0, n = list.size(); i < n; i++) {
	                         executor.update(statementName, list.get(i));
	                     }
	                     executor.executeBatch();
	                     return null;
	                  }
	            });  
	        }   
	    } catch (Exception e) {
	        if (log.isDebugEnabled()) {
	            e.printStackTrace();
	            log.debug("batchUpdate error: id [" + statementName + "], parameterObject ["+ list + "].  Cause: "+ e.getMessage());
	        }
	    }
	    System.out.println("batchUpdate 这段代码运行了:"+ (System.currentTimeMillis()-t1)/1000.0 + "秒！"+"一共  "+list.size()+" 条");
	}    
	
	/**
	 * 酒店房量出租率报表
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public RentSearchResult searchHotelRoomRentRates(SearchRentCriteria criteria){
		RentSearchResult searchResult = new RentSearchResult();
		
		//导出excel标识
		if(criteria.isExcelFlag()){
			List<Rent> resultList = getSqlMapClientTemplate().queryForList("searchHotelRoomRentRate", criteria);
			searchResult.setResultList(resultList);
			
		}else{
			//查询一页数据
			List<Rent> resultList = getSqlMapClientTemplate().queryForList("searchHotelRoomRentRate", criteria);
			searchResult.setResultList(resultList);
			
			//得到总条数
			Integer totalCount = 
				(Integer) getSqlMapClientTemplate().queryForObject("searchHotelRoomRentRateCount" ,criteria);
			searchResult.setTotalCount(totalCount);
		}
		return searchResult;
	}

	@Override
	public List<RateCodeWithRoomVO> getRateCodeFromRoomType(String hotelCode,
			String startDate, String endDate, List<String> roomCodeList,String weekRange) {
		// TODO Auto-generated method stub
		Map paramMap=new HashMap();
		paramMap.put("hotelCode", hotelCode);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("roomCodeList", roomCodeList);
		paramMap.put("weekRange", weekRange);
		return getSqlMapClientTemplate().queryForList("getRateCodeFromRoomType", paramMap);
	}
}
