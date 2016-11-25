package com.ccm.api.service.hotel.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccm.api.dao.hotel.CreditLimitDao;
import com.ccm.api.dao.hotel.HotelCreditLimitBindingDao;
import com.ccm.api.dao.log.CustomLogDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.model.constant.ChannelCode;
import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.hotel.CreditLimit;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.HotelCreditLimitBinding;
import com.ccm.api.model.hotel.vo.CreditLimitCreteria;
import com.ccm.api.model.hotel.vo.CreditLimitResult;
import com.ccm.api.model.hotel.vo.CreditLimitVO;
import com.ccm.api.model.log.CustomLog;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.AsyncManager;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.email.impl.EmailManagerImpl;
import com.ccm.api.service.hotel.CreditLimitManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("creditLimitManager")
public class CreditLimitManagerImpl extends GenericManagerImpl<CreditLimit, String> implements CreditLimitManager{
	@Resource
	private CreditLimitDao creditLimitDao;
	@Resource
	private HotelCreditLimitBindingDao hotelCreditLimitBindingDao;
	@Resource
	private RateCustomRelationshipDao rateCustomRelationshipDao;
	@Resource
	private MasterDao masterDao;
	@Resource
	private CustomLogDao customLogDao;
	@Resource
	private AsyncManager asyncManager;
	@Override
	public CreditLimit save(CreditLimit object) {
		// TODO Auto-generated method stub
		return super.save(object);
	}
	
	@Override
	public CreditLimit getCreditLimitByDetail(String hotelId,String channelId){
		HotelCreditLimitBinding hotelCreditLimitBinding =new HotelCreditLimitBinding();
		hotelCreditLimitBinding.setHotelId(hotelId);
		hotelCreditLimitBinding.setChannelId(channelId);
		return creditLimitDao.getCreditLimitByDetail(hotelCreditLimitBinding);
	}
	
	@Override
	public void saveCreditLimit(CreditLimit cl,
			List<HotelCreditLimitBinding> hotelCreditLimitBindingList) {
		// TODO Auto-generated method stub
		//save main
		if(CommonUtil.isEmpty(cl.getEmailLimit()))
			cl.setEmailLimit(new BigDecimal(0));
		cl=creditLimitDao.save(cl);
		//get old detail
		List<HotelCreditLimitBinding> existedHotelBindingList=hotelCreditLimitBindingDao.findHotelsCreditLimit(cl.getCreditLimitId());
		Set<String> hotelSet=new HashSet<String>();
		if(CommonUtil.isNotEmpty(existedHotelBindingList)){
			for(HotelCreditLimitBinding existedHotelBinding:existedHotelBindingList){
				hotelSet.add(existedHotelBinding.getHotelId());
			}
		}
		//save detail
		for(HotelCreditLimitBinding hotelCreditLimitBinding:hotelCreditLimitBindingList){
			if(hotelSet.contains(hotelCreditLimitBinding.getHotelId())){
				hotelSet.remove(hotelCreditLimitBinding.getHotelId());
			}else{
				hotelCreditLimitBinding.getHotelId();
				hotelCreditLimitBinding.setCreditLimitId(cl.getCreditLimitId());
				hotelCreditLimitBindingDao.save(hotelCreditLimitBinding);
			}
		}
		//remove old detail
		for(String removingHotelId:hotelSet){
			hotelCreditLimitBindingDao.removeBindingHotel(removingHotelId,cl.getChannelId());
		}
	}

	@Override
	public void removeCreditLimit(String creditLimitId) {
		// TODO Auto-generated method stub
		creditLimitDao.removeCreditLimit(creditLimitId);
		hotelCreditLimitBindingDao.removeBindingHotels(creditLimitId);
	}

	@Override
	public boolean validCreditLimitForOrder(Master master) {
		// TODO Auto-generated method stub
		CreditLimit cl=getCreditLimitByDetail(master.getHotelId(),master.getChannelId());
		// 未开启保证金
		if(CommonUtil.isEmpty(cl)){
			return true;
		}
		// 开启保证金
		// 预付订单
		String channelCode = master.getChannel();
		// TAOBAO 预付：TAGAD 非TAOBAO预付:DUE
		if (master.getPayment().equalsIgnoreCase(GuaranteeCode.DUE) || master.getPayment().equalsIgnoreCase(GuaranteeCode.PREPAID) || GuaranteeCode.TAGTD.equalsIgnoreCase(master.getPayment()) || (ChannelCode.TAOBAO.equalsIgnoreCase(channelCode) && GuaranteeCode.TAGTD_GT.equalsIgnoreCase(master.getPayment())) || GuaranteeCode.VOUCHER.equalsIgnoreCase(master.getPayment())) {
			// originalCreditLimit
			if (cl.getOriginalCreditLimit() != null) {
				// 原始保证金
				BigDecimal originalCreditLimit = cl.getOriginalCreditLimit();
				// 总房晚收入
				BigDecimal totalRoomRev = cl.getTotalRoomRev();
				// 已收总和
				BigDecimal income = cl.getIncome();
				// 最低限额
				BigDecimal minLimit =cl.getMinLimit();
				// 邮件通知限额
				BigDecimal emailLimit =cl.getEmailLimit();
				//订单金额
				BigDecimal charge = master.getCharge();
				//原始保证金+已收总和-总房晚收入-最低限额
				// originalCreditLimit+income-totalRoomRev-minLimit
				BigDecimal balance = originalCreditLimit.add(income).subtract(totalRoomRev.add(minLimit));
				// balance>charge 验证成功
				if (balance.compareTo(charge) > -1) {
					// 计算是否应该发提醒email
					//原始保证金+已收总和-总房晚收入-邮件通知限额
					BigDecimal emailBalance = originalCreditLimit.add(income).subtract(totalRoomRev.add(emailLimit));
					if (emailBalance.compareTo(charge) < 1) {
						//下单后的提醒余额
						//原始保证金+已收总和-总房晚收入-订单金额
						BigDecimal emailBalance_send = originalCreditLimit.add(income).subtract(totalRoomRev.add(charge)).setScale(2);
						//send email
						if(cl.getHasSentEmailLimit()==false){
							if(cl.getEmailLimitPct()!=null){
								String percentageMsg=cl.getEmailLimitPct().toString()+"%";
								asyncManager.sendCreditLimitEmail(master.getHotelId(), channelCode,percentageMsg,EmailManagerImpl.MsgLimitNotifEmail,cl);
							}else{
								asyncManager.sendCreditLimitEmail(master.getHotelId(), channelCode,emailBalance_send.toString(),EmailManagerImpl.MsgLimitNotifEmail,cl);
							}
							cl.setHasSentEmailLimit(true);
							creditLimitDao.updateSentSwitchOfCreditLimit(cl);
						}
					}
					
					
					return true;
				} else {
					//不能下单
					//send email
					if(cl.getHasSentMinLimit()==false){
						asyncManager.sendCreditLimitEmail(master.getHotelId(), channelCode,balance.toString(),EmailManagerImpl.MiniLimitNotifEmail,cl);
						cl.setHasSentMinLimit(true);
						creditLimitDao.updateSentSwitchOfCreditLimit(cl);
					}
					return false;
				}
			}
		}
		return true;
	}

	private void updateTotalRoomRev(Master master, Custom custom,CreditLimit creditLimit) {
			// custom 总房晚收入
			BigDecimal totalRoomRev = creditLimit.getTotalRoomRev();
			// 订单的总价格
			BigDecimal charge = master.getCharge();
			//增量金额
			BigDecimal variable=new BigDecimal("0");
			// 预定 直接加
			if (OrderStatus.ADD.equals(master.getSta())) {
				variable=variable.add(charge);
				totalRoomRev = totalRoomRev.add(charge);
			}
			// NOSHOW扣除首晚，释放其他
			else if (OrderStatus.NOSHOW.equals(master.getSta())) {
				List<MasterRate> list = masterDao.getMasterRateByOrderNo(master.getMasterId(), DateUtil.getDate(master.getArr()));
				MasterRate m = list.get(0);
				// 首日价
				BigDecimal firstDateRate = m.getSetrate().add(m.getPackages());
				// 只有一晚
				if (firstDateRate.compareTo(charge) == 0) {
					// totalRoomRev = totalRoomRev.subtract(charge);
				} else {
					variable=variable.subtract(charge).add(firstDateRate);
					totalRoomRev = totalRoomRev.subtract(charge).add(firstDateRate);
				}
			}
			// CANCEL
			else if (OrderStatus.CANCEL.equals(master.getSta()) || OrderStatus.HARDCANCEL.equals(master.getSta())) {

				if (master.getDeduct() == null) {
					master.setDeduct(BigDecimal.ZERO);
				}
				/**
				 * 总房晚收入-订单总价+罚金 totalRoomRev-charge+deduct
				 */
				variable=variable.subtract(charge).add(master.getDeduct());
				totalRoomRev = totalRoomRev.subtract(charge).add(master.getDeduct());
			}
			
			//save total room revenue
			creditLimit.setVariable(variable);
			creditLimitDao.updateTotalRoomRev(creditLimit);
			HotelCreditLimitBinding updateHotelBinding=new HotelCreditLimitBinding();
			updateHotelBinding.setHotelId(master.getHotelId());
			updateHotelBinding.setChannelId(master.getChannelId());
			updateHotelBinding.setVariable(variable);
			hotelCreditLimitBindingDao.updateTotalRoomRev(updateHotelBinding);

			// insert customLog
			CustomLog cl = new CustomLog();
			cl.setCustomId(custom.getCustomId());
			cl.setHotelCode(master.getHotelCode());
			cl.setName(custom.getName());
			cl.setType(custom.getType());
			cl.setCorpIATANo(custom.getCorpIATANo());
			cl.setAccessCode(custom.getAccessCode());
			cl.setBusiness(custom.getBusiness());
			cl.setMobile(custom.getMobile());
			cl.setFax(custom.getFax());
			cl.setAddress(custom.getAddress());
			cl.setEmail(custom.getEmail());
			cl.setOriginalCreditLimit(creditLimit.getOriginalCreditLimit().toString());
			cl.setTotalRoomRev(totalRoomRev.toString());
			cl.setIncome(creditLimit.getIncome().toString());
			cl.setMinLimit(creditLimit.getMinLimit().toString());
			customLogDao.saveCustomLog(cl);
	}
	
	@Override
	public void increaseTotalRoomRev(Master master){
		System.out.println("enter credit limit");
		//是否启用保证金
		boolean flag = false;
		// TODO Auto-generated method stub
		CreditLimit creditLimit=getCreditLimitByDetail(master.getHotelId(),master.getChannelId());
		// 未开启保证金
		if(CommonUtil.isNotEmpty(creditLimit)){
			flag= true;
		}
		
		//执行credit limit逻辑
		if(flag){
			System.out.println("begin cal credit limit");
			String channelCode = master.getChannel();
			Custom c = new Custom();
			c.setHotelId(master.getHotelId());
			c.setAccessCode(master.getQualifyingIdValue());
			c.setType(master.getQualifyingIdType());
			Custom custom = rateCustomRelationshipDao.getCustomIdByRateCustom(c, master.getRatePlanId());
			//TAOBAO渠道单独处理
			if(ChannelCode.TAOBAO.equalsIgnoreCase(channelCode)){
				if(GuaranteeCode.VOUCHER.equalsIgnoreCase(master.getPayment())||GuaranteeCode.TAGTD.equalsIgnoreCase(master.getPayment())||GuaranteeCode.TAGTD_GT.equalsIgnoreCase(master.getPayment())){
					updateTotalRoomRev(master,custom,creditLimit);
				}
			}else{
				if(master.getPayment().equalsIgnoreCase(GuaranteeCode.DUE)||master.getPayment().equalsIgnoreCase(GuaranteeCode.PREPAID)||GuaranteeCode.TAGTD.equalsIgnoreCase(master.getPayment())){
					updateTotalRoomRev(master,custom,creditLimit);
				}
			}
			
		}
	}

	@Override
	public CreditLimitResult getCreditLimitList(CreditLimitCreteria creteria) {
		CreditLimitResult result = new CreditLimitResult();
		if(creteria.isNeedPage()){
			result.setResultList(getCreditLimitVOList(creteria));
			result.setTotalCount(getCreditLimitVOCount(creteria));
		}
		if(creteria.isExcelFlag()){
			result.setResultList(getCreditLimitVOList(creteria));
		}
		return result;
	}

	@Override
	public List<CreditLimitVO> getCreditLimitVOList(CreditLimitCreteria creteria) {
		return creditLimitDao.getCreditLimitVOList(creteria);
	}

	@Override
	public Integer getCreditLimitVOCount(CreditLimitCreteria creteria) {
		return creditLimitDao.getCreditLimitVOCount(creteria);
	}

	@Override
	public Boolean checkExisted(String channelId, String hotelId) {
		HotelCreditLimitBinding o = hotelCreditLimitBindingDao.checkExisted(channelId,hotelId);
		if(o==null){
			return false;
		}
		return true;
	}

	@Override
	public CreditLimitVO getCreditLimitVO(String creditLimitId) {
		CreditLimitVO creditLimitVO =creditLimitDao.getCreditLimitVO(creditLimitId);
		creditLimitVO.setHotelIds(hotelCreditLimitBindingDao.findHotelIds(creditLimitId));
		return creditLimitVO;
	} 
		
}