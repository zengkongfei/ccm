package com.ccm.api.dao.bdp;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.model.bdp.OxiApiDisconnectedMsg;
import com.ccm.api.util.CommonUtil;

@Repository("bdpDisconnectedMsgMongo")
public class BdpDisconnectedMsgMongo extends
		MongodbBaseDao<OxiApiDisconnectedMsg> {
	protected Class<OxiApiDisconnectedMsg> getEntityClass() {
		return OxiApiDisconnectedMsg.class;
	}

	public void batchSave(List<OxiApiDisconnectedMsg> rdList) {
		this.saveALL(rdList);
	}

	public OxiApiDisconnectedMsg findMsgByParam(OxiApiDisconnectedMsg msg){
		return this.findOne(buildQuery(msg));
	}
	private Query buildQuery(OxiApiDisconnectedMsg msg) {
		Query query = new Query();
		if (CommonUtil.isNotEmpty(msg.getHotelCode())) {
			query.addCriteria(Criteria.where("hotelCode")
					.is(msg.getHotelCode()));
		}
		if (CommonUtil.isNotEmpty(msg.getMsgDate())) {
			query.addCriteria(Criteria.where("msgDate").is(msg.getMsgDate()));
		}
		if (CommonUtil.isNotEmpty(msg.getHour())) {
			query.addCriteria(Criteria.where("hour").is(msg.getHour()));
		}
		return query;
	}

	private Update buildUpdate(OxiApiDisconnectedMsg msg) {
		Update update = new Update();
		update.set("numberOfTimes",msg.getNumberOfTimes()).set("disconnectedQuantum",
				msg.getDisconnectedQuantum());
		return update;
	}

	public void saveOrUpdateMsg(OxiApiDisconnectedMsg msg) {
		Query query = buildQuery(msg);
		Update update = buildUpdate(msg);
		List<OxiApiDisconnectedMsg> msgList = this.find(query);
		if (CommonUtil.isEmpty(msgList)) {
			this.save(msg);
		} else {
			this.update(query, update);
		}
	}
	
	public void findAndUpdateMsg(OxiApiDisconnectedMsg msg) {
		Query query = buildQuery(msg);
		Update update = buildUpdate(msg);
		this.findAndModify(query, update);
	}
	
	public OxiApiDisconnectedMsg findMsg(OxiApiDisconnectedMsg msg){
		return this.findOne(buildQuery(msg));
	}
	
	public List<OxiApiDisconnectedMsg> findMsgList(OxiApiDisconnectedMsg msg){
		return this.find(buildQuery(msg));
	}
	
	public void removeMsg(OxiApiDisconnectedMsg msg){
		this.remove(buildQuery(msg));
	}
}
