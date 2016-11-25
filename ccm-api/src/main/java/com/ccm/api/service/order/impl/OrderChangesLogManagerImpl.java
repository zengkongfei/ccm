/**
 * 
 */
package com.ccm.api.service.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.master.MasterChangesLogDao;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterChangesLog;
import com.ccm.api.model.order.MasterOrder;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.order.OrderChangesLogManager;
import com.ccm.api.util.DateUtil;

/**
 * 
 * 
 * @author Jenny Liao
 * 
 */
@Service("orderChangesLogManager")
public class OrderChangesLogManagerImpl extends GenericManagerImpl<MasterChangesLog, String> implements OrderChangesLogManager {

	private Log log = LogFactory.getLog("reservation");

	@Resource
	private MasterChangesLogDao masterChangesLogDao;

	public void saveMasterChangesLog(Master m, List<Master> mList) {
		if (mList != null && !mList.isEmpty()) {
			for (Master master : mList) {
				saveMasterChangesLog(master);
			}
		}
		saveMasterChangesLog(m);

	}

	/**
	 * 1. 更改操作人 User 2. 更改日期 Change Date 3. 更改内容 Change Details
	 * （PMS确认号，客人名字，抵店日期，离店日期，预定创建时间， 预定取消时间，预定修改时间，状态，房价代码，房型代码，来源）
	 */
	@Override
	public void updateMasterLog(Master m,MasterOrder mo) {
		log.info("start updateMasterLog,masterId:" + m.getMasterId());
		
		Master master = new Master();
		BeanUtils.copyProperties(mo, master);
			
		MasterChangesLog mcl = new MasterChangesLog();
		
		
		mcl.setExt(m.getRoomno());		//房号
		mcl.setRestype(m.getRestype()); //状态
		
		mcl.setPmsId(master.getPmsId());
		mcl.setMasterId(master.getMasterId());
		mcl.setUser(master.getCreateBY());
		if (StringUtils.hasText(master.getUpdatedBy())) {
			mcl.setUser(master.getUpdatedBy());
		}
		mcl.setCreatedTime(DateUtil.currentDateTime());
		mcl.setPmsId(master.getPmsId());
		if (StringUtils.hasText(master.getName2())) {
			mcl.setLastName(master.getName2());
		}
		if (StringUtils.hasText(master.getName())) {
			mcl.setFirstName(master.getName());
		}
		if (StringUtils.hasText(master.getName4())) {
			mcl.setChineseName(master.getName4());
		}
		mcl.setArrivalDate(master.getArr());
		mcl.setDepartureDate(master.getDep());
		mcl.setMasterCreateTime(master.getChanged());
		mcl.setMasterCancelTime(master.getCancelTime());
		mcl.setMasterModifyTime(master.getLastModifyTime());
		mcl.setStatus(master.getSta());
		mcl.setRestype(master.getRestype());
		mcl.setRateCode(master.getRatePlanCode());
		mcl.setRoomCode(master.getType());
		mcl.setSource(master.getSource());
		masterChangesLogDao.save(mcl);
	}
	
	/**
	 * 1. 更改操作人 User 2. 更改日期 Change Date 3. 更改内容 Change Details
	 * （PMS确认号，客人名字，抵店日期，离店日期，预定创建时间， 预定取消时间，预定修改时间，状态，房价代码，房型代码，来源）
	 */
	public void saveMasterChangesLog(Object order) {
		log.info("startSaveMasterChangesLog");
		Master master = null;
		if (order instanceof Master) {
			master = (Master) order;
		} else if (order instanceof MasterOrder) {
			MasterOrder mo = (MasterOrder) order;
			master = new Master();
			BeanUtils.copyProperties(mo, master);
		}
		log.info("masterId:" + master.getMasterId());
		MasterChangesLog mcl = new MasterChangesLog();
		mcl.setMasterId(master.getMasterId());
		mcl.setUser(master.getCreateBY());
		if (StringUtils.hasText(master.getUpdatedBy())) {
			mcl.setUser(master.getUpdatedBy());
		}
		mcl.setCreatedTime(DateUtil.currentDateTime());
		mcl.setPmsId(master.getPmsId());
		if (StringUtils.hasText(master.getName2())) {
			mcl.setLastName(master.getName2());
		}
		if (StringUtils.hasText(master.getName())) {
			mcl.setFirstName(master.getName());
		}
		if (StringUtils.hasText(master.getName4())) {
			mcl.setChineseName(master.getName4());
		}
		mcl.setArrivalDate(master.getArr());
		mcl.setDepartureDate(master.getDep());
		mcl.setMasterCreateTime(master.getChanged());
		mcl.setMasterCancelTime(master.getCancelTime());
		mcl.setMasterModifyTime(master.getLastModifyTime());
		mcl.setStatus(master.getSta());
		mcl.setRestype(master.getRestype());
		mcl.setRateCode(master.getRatePlanCode());
		mcl.setRoomCode(master.getType());
		mcl.setSource(master.getSource());
		mcl.setExt(master.getRoomno());
		masterChangesLogDao.save(mcl);
	}

	public List<MasterChangesLog> getMasterChangesLogByOrderNo(String orderNo) {
		return masterChangesLogDao.getMasterChangesLogByMasterId(orderNo);
	}

}
