package com.ccm.api.dao.ratePlan.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.RoomPackageDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.RoomPackage;
import com.ccm.api.util.CommonUtil;

@Repository("roomPackageDao")
public class RoomPackageDaoibatis extends GenericDaoiBatis<RoomPackage, String> implements RoomPackageDao {

	public RoomPackageDaoibatis() {
		super(RoomPackage.class);
	}

    @Override
    public RoomPackage addRoomPackage(RoomPackage roomPackage) {
        return (RoomPackage) getSqlMapClientTemplate().insert("addRoomPackage",roomPackage);
    }

    @Override
    public void deleteRoomPackageByRoomRateId(String roomRateId) {
        getSqlMapClientTemplate().delete("deleteRoomPackageByRoomRateId",roomRateId);
    }

    @Override
    public List<RoomPackage> getRoomPackageByRoomRateId(String roomRateId) {
        return this.getRoomPackageByRoomRateId(roomRateId, LanguageCode.MAIN_LANGUAGE_CODE);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<RoomPackage> getRoomPackageByRoomRateId(String roomRateId,String language) {
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("roomRateId", roomRateId);
    	params.put("language", language);
        return getSqlMapClientTemplate().queryForList("getRoomPackageByRoomRateId",params);
    }

	@Override
	public void addBatchRoomPackages(List<RoomPackage> roomPackageList) {
		if(CommonUtil.isNotEmpty(roomPackageList)){
			getSqlMapClientTemplate().insert("addBatchRoomPackages", roomPackageList);
		}
	}
    
}
