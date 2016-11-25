package com.ccm.api.service.fax.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccm.api.dao.fax.FaxSendTimeDao;
import com.ccm.api.model.fax.FaxSendTime;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.fax.FaxSendTimeManager;

@Service("faxSendTimeManager")
public class FaxSendTimeManagerImpl extends GenericManagerImpl<FaxSendTime, String> implements FaxSendTimeManager {

	@Resource
	private FaxSendTimeDao faxSendTimeDao;
	
	@Override
	public List<FaxSendTime> searchFaxSendTimeList(FaxSendTime faxSendTime) {
		return faxSendTimeDao.searchFaxSendTimeList(faxSendTime);
	}

	@Override
	public void saveOrUpdateFaxSendTime(List<FaxSendTime> list) {
		if(list.isEmpty()){
			return;
		}
		List<FaxSendTime> nullArr = new ArrayList<FaxSendTime>();  
		nullArr.add(null); 
		list.removeAll(nullArr);
		String hotelId = list.get(0).getHotelId();
		
		FaxSendTime faxSendTime = new FaxSendTime();
		faxSendTime.setHotelId(hotelId);
		List<FaxSendTime> dBList = searchFaxSendTimeList(faxSendTime);
		
		List<FaxSendTime> needDel = new ArrayList<FaxSendTime>();
		
		for(FaxSendTime dbfaxSendTime :dBList){
			boolean flag = false;
			for(FaxSendTime editfaxSendTime :list){
				if(editfaxSendTime!=null){
					if(dbfaxSendTime.getFaxSendTimeId().equalsIgnoreCase(editfaxSendTime.getFaxSendTimeId())){
						flag = true;
						continue;
					}
				}
			}
			if(!flag){
				needDel.add(dbfaxSendTime);
			}
		}
		
		for(FaxSendTime f :list){
			if(f!=null){
				faxSendTimeDao.save(f);
			}
		}
		
		for(FaxSendTime f :needDel){
			faxSendTimeDao.softDelete(f.getFaxSendTimeId());
		}
		
	}
	


}
