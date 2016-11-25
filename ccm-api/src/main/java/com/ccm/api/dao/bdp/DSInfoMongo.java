package com.ccm.api.dao.bdp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.model.bdp.AriPushErrorMsg;
import com.ccm.api.model.bdp.DSInfo;
import com.ccm.api.model.bdp.OWSReservation;
import com.ccm.api.model.bdp.OxiApiDisconnectedMsg;

@Repository("dsInfoMongo")
public class DSInfoMongo extends MongodbBaseDao<DSInfo>{
	protected Class<DSInfo> getEntityClass() {
		return DSInfo.class;
	}

	public DSInfo getDSInfo(String dsName){
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("dsName",dsName);
		return this.findOne(buildQuery(map));
	}
	private Query buildQuery(Map<String,Object> map){
		Query query = new Query();
		Criteria criteria=null;
		int i=0;
		for(String key:map.keySet()){
			if(i==0){
				criteria=Criteria.where(key).is(map.get(key));
			}else{
				criteria=criteria.and(key).is(map.get(key));
			}
		}
		return query.addCriteria(criteria);
	}
	public DSInfo addBDPInfo(){
		DSInfo dsInfo=this.getDSInfo("BDP");
		if(dsInfo==null){
			dsInfo=new DSInfo();
			dsInfo.getTbIdMap().put(OxiApiDisconnectedMsg.class.getSimpleName(), "tb_36a7d0c6b8e94d0dafc996e3c681682e");
			dsInfo.getTbIdMap().put(AriPushErrorMsg.class.getSimpleName(), "tb_c29264e6e4a74dea9072463f9ab7cfc3");
			dsInfo.getTbIdMap().put(OWSReservation.class.getSimpleName(), "tb_d7f5c641d87c42a082e1cb3397ae391c");
			this.save(dsInfo);
		}
		return dsInfo;
	}
}
