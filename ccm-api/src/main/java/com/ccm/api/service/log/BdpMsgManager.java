package com.ccm.api.service.log;
import java.util.Date;
import java.util.List;

import com.ccm.api.model.bdp.AdsPushErrorCount;
import com.ccm.api.model.bdp.BdpRecord;
import com.ccm.api.model.log.ReceivePmsLog;



public interface BdpMsgManager {

	public void addAllDisconnectedMsgOfTmr();
	public void updateOxiApiDisconnectedMsg(ReceivePmsLog receivePmsLog,ReceivePmsLog oldReceivePmsLog);
	public void createOxiApiDisconnectedMsgOfLastDate();
	public void createAdsMsgCountOfLastDate();
	public void createOWSReservationOfLastDate();
	public void modifyOWSReservationOfLastDate();
	public void pushOWSReservationOfLastDate();
	public void addAllDisconnectedMsgOfToday();
	List<AdsPushErrorCount> getAdsMessageErrorCountLastDate();
	void printRecords(List<BdpRecord> records);
	void removeDisconnectedMsgsOfLastHour(Date lastHour);
	void createOxiApiDisconnectedMsgOfYesterday();
}
