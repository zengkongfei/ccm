package com.ccm.api.dao.bdp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.bdp.BdpTb;
import com.ccm.api.model.bdp.DSInfo;
import com.ccm.api.model.bdp.OWSReservation;
import com.ccm.api.service.log.BdpMsgManager;
import com.ccm.api.service.log.ReceivePmsLogManager;
import com.ccm.api.util.BdpDataUtil;

import static org.junit.Assert.assertEquals;
public class BdpTest extends BaseDaoTestCase{
	
	@Resource
	private BdpMsgManager bdpMsgManager;
	@Resource
	private ReceivePmsLogManager receivePmsLogManager;
	@Resource
	private DSInfoMongo dsInfoMongo;
	@Test
	@Rollback(value=false)
	public void putAllMsgIntoMongo(){
//		Map m=new HashMap();
//		m.put("dsName", "BDP");
//		DSInfo info=dsInfoMongo.getDSInfo(m);
//		
//		System.out.println(info.getTbIdMap().keySet().size());
		BdpTb.cleanData(BdpDataUtil.CCM_OWS_RESERVATION_ID);
		bdpMsgManager.createOWSReservationOfLastDate();
//		BdpTb.createTable(OWSReservation.class);
//		BdpTb.getTableInfo();
//		dsInfoMongo.addBDPInfo();
//		bdpMsgManager.addAllDisconnectedMsgOfTmr();
//		bdpMsgManager.createOxiApiDisconnectedMsgOfLastDate();
//		bdpMsgManager.createAdsMsgCountOfLastDate();
//		BdpTb.cleanData(BdpDataUtil.OXI_ADS_PUSH_ERROR_MSG_ID);
//		List <ReceivePmsLog> receivePmsLogList =receivePmsLogManager.getAll();
//		for(ReceivePmsLog oldReceivePmsLog:receivePmsLogList){
//			ReceivePmsLog  newReceivePmsLog=new  ReceivePmsLog();
//			newReceivePmsLog.setHotelCode(oldReceivePmsLog.getHotelCode());
//			newReceivePmsLog.setReceiveMsgLogLastTime(new Date());
//			bdpMsgManager.updateOxiApiDisconnectedMsg(newReceivePmsLog, oldReceivePmsLog);
//		}
	}
}
