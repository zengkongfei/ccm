package com.ccm.api.dao.ratePlan.ibatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.compass.core.util.StringUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.RoomPackageDao;
import com.ccm.api.dao.ratePlan.RoomRateDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.RoomPackage;
import com.ccm.api.model.ratePlan.RoomRate;
import com.ccm.api.model.ratePlan.vo.RoomRateVO;
import com.ccm.api.util.CommonUtil;

@Repository("roomRateDaoiBatis")
public class RoomRateDaoibatis extends GenericDaoiBatis<RoomRate, String> implements RoomRateDao {

    @Resource
    private RoomPackageDao roomPackageDao;
    
	public RoomRateDaoibatis() {
		super(RoomRate.class);
	}

    @Override
    public RoomRate addRoomRate(RoomRate roomRate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteRoomRateByRateDetailId(String rateDetailId) {
        
    }

    @Override
    public List<RoomRate> getRoomRateByRateDetailId(String rateDetailId) {
        return null;
    }

    @Override
    public void addRoomRateVO(RoomRateVO roomRateVO) {
        if(StringUtils.hasText(roomRateVO.getRoomRateId())){
            roomRateVO.setLastModifyTime(new Date());
            roomRateVO.setUpdatedBy(SecurityHolder.getUserId());
            updateRoomRateVO(roomRateVO);
        }else{
            roomRateVO.setRoomRateId(UUID.randomUUID().toString().replace("-", ""));
            getSqlMapClientTemplate().insert("addRoomRate",roomRateVO);
        }
        //删除房型打包
        roomPackageDao.deleteRoomPackageByRoomRateId(roomRateVO.getRoomRateId());
        //添加打包到该房型
        List<RoomPackage> roomPackageList = roomRateVO.getRoomPackageList();
        if(roomPackageList !=null && roomPackageList.size() > 0){
            for (RoomPackage roomPackage : roomPackageList) {
                roomPackage.setRoomRateId(roomRateVO.getRoomRateId());
                roomPackage.setRoomPackageId(null);
                roomPackageDao.save(roomPackage);
            }
        }
    }

    @Override
    public void updateRoomRateVO(RoomRateVO roomRateVO) {
        getSqlMapClientTemplate().update("updateRoomRate",roomRateVO);
    }
    
    @Override
    public List<RoomRateVO> getRoomRateVOByRateDetailId(String rateDetailId) {
        return this.getRoomRateVOByRateDetailId(rateDetailId, LanguageCode.MAIN_LANGUAGE_CODE);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<RoomRateVO> getRoomRateVOByRateDetailId(String rateDetailId,String language) {
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("rateDetailId", rateDetailId);
    	params.put("language", language);
    	List<RoomRateVO> roomRateList = getSqlMapClientTemplate().queryForList("getRoomRateVOByRateDetailId",params);
        if(roomRateList != null && roomRateList.size()>0){
            return roomRateList;
        }
        return new ArrayList<RoomRateVO>();
    }

    @Override
    public List<RoomRate> getRoomRateByRatePlanIdAndRoomTypeId(String ratePlanId,String roomTypeId) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ratePlanId", ratePlanId);
        map.put("roomTypeId", roomTypeId);
        List<RoomRate> roomRateList = getSqlMapClientTemplate().queryForList("getRoomRateByRatePlanIdAndRoomTypeId",map);
        return roomRateList;
    }
    
    @Override
    public void addBatchRoomRates(List<RoomRateVO> roomRateList) {
		if(CommonUtil.isNotEmpty(roomRateList)){
			getSqlMapClientTemplate().insert("addBatchRoomRates", roomRateList);
		}
	}
       
    /**
     * 删除不存在detail中的房型,返回删除操作影响的记录条数
     */
	@Override
	public int deleteRoomRateNotExistsInDetail() {
		return getSqlMapClientTemplate().delete("deleteRoomRateNotExistsInDetail");
	}

	@Override
	public int deleteRoomRateById(String roomRateId) {
		// TODO Auto-generated method stub
		Map<String,String>paramMap=new HashMap<String,String>();
		paramMap.put("value", roomRateId);
		return getSqlMapClientTemplate().update("deleteRoomRate",paramMap);
	}
	
	@Override
	public int deleteRoomRateByRoomTypeId(String rateDetailId,String roomTypeId) {
		// TODO Auto-generated method stub
		Map<String,String>paramMap=new HashMap<String,String>();
		paramMap.put("rateDetailId", rateDetailId);
		paramMap.put("roomTypeId", roomTypeId);
		return getSqlMapClientTemplate().update("deleteRoomRateByRoomTypeId",paramMap);
	}

	@Override
	public RoomRateVO getRoomRateVOByRateDetailIdAndRoomTYpeCode(
			String rateDetailId, String roomTypeCode, String language) {
		Map<String, String> params = new HashMap<String, String>();
    	params.put("rateDetailId", rateDetailId);
    	params.put("roomTypeCode", roomTypeCode);
    	params.put("language", language);
    	return (RoomRateVO)getSqlMapClientTemplate().queryForObject("getRoomRateVOByRateDetailIdAndRoomTYpeCode",params);
	}
}
