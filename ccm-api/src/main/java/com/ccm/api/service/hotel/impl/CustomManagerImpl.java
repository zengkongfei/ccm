/**
 * 
 */
package com.ccm.api.service.hotel.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.hotel.CustomDao;
import com.ccm.api.dao.log.CustomLogDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.constant.ChannelCode;
import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.CustomCreteria;
import com.ccm.api.model.hotel.vo.CustomResult;
import com.ccm.api.model.log.CustomLog;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.order.vo.DepositSearchResult;
import com.ccm.api.model.order.vo.SearchDepositCriteria;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.CustomManager;
import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
@Service("customManager")
public class CustomManagerImpl extends GenericManagerImpl<Custom, String> implements CustomManager {

	private CustomDao customDao;

	@Autowired
	private RateCustomRelationshipDao rateCustomRelationshipDao;

	@Autowired
	private MasterDao masterDao;
	@Autowired
	private CustomLogDao customLogDao;

	@Autowired
	public CustomManagerImpl(CustomDao customDao) {
		super(customDao);
		this.customDao = customDao;
	}

	public void saveCustom(Custom c) {
		customDao.save(c);
	}

	public void updateDelFlag(String customId) {
		customDao.softDelete(customId);
	}

	/**
	 * 根据逻辑判断是否重复记录
	 */
	public boolean existCustom(Custom c) {
		Custom accessCode = new Custom();
		accessCode.setAccessCode(c.getAccessCode());
		accessCode.setHotelId(c.getHotelId());
		List<Custom> cList = customDao.getCustomByObj(accessCode);
		// 更新
		if (StringUtils.hasText(c.getCustomId())) {
			if (cList == null || cList.isEmpty()) {
				return false;
			} else if (cList.size() == 1 && cList.get(0).getCustomId().equals(c.getCustomId())) {
				return false;
			} else {
				return true;
			}
		}
		// 添加
		else if (cList != null && !cList.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 根据条件查询记录
	 */
	public List<Custom> getCustomByObj(Custom c) {
		return customDao.getCustomByObj(c);
	}

	/**
	 * 根据酒店ID查询记录
	 */
	public List<Custom> getCustomByHotelId(String hotelId) {
		return customDao.getCustomByHotelId(hotelId);
	}

	/**
	 * 查询列表并可分页显示
	 */
	public CustomResult searchCustom(CustomCreteria creteria) {
		return customDao.searchCustom(creteria);
	}

	public List<String> getCustomIdByRatePlanId(String ratePlanId) {
		return rateCustomRelationshipDao.getCustomIdByRatePlanId(ratePlanId);
	}

	public List<Custom> getCustomByRatePlanId(String ratePlanId) {
		return rateCustomRelationshipDao.getCustomByRatePlanId(ratePlanId);
	}

	/*
	 * 根据customId查找
	 */
	@Override
	public Custom searchCustomById(String customId) {
		// TODO Auto-generated method stub
		return customDao.searchCustomById(customId);
	}

	/**
	 * 根据customId累加
	 * 
	 * @param customId
	 */
	@Override
	public void updateCustomCumulative(Custom c) {
		customDao.updateCustomCumulative(c);
	}

	@Override
	public DepositSearchResult queryBookingDepositReport(SearchDepositCriteria sdc) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hotelIdList", sdc.getHotelIdList());
		paramMap.put("accessCodeList", sdc.getAccessCodeList());
		paramMap.put("name", sdc.getProfileName());
		paramMap.put("corpIATANo", sdc.getCorpIATANo());
		DepositSearchResult depositSearchResult = new DepositSearchResult();
		if (sdc.isNeedPage()) {
			paramMap.put("skipResults", sdc.getPageNum() * sdc.getPageSize() - sdc.getPageSize());
			paramMap.put("maxResults", sdc.getPageSize());
			depositSearchResult.setTotalCount(customDao.getBookingDepositReportCount(paramMap));
		}
		depositSearchResult.setResultList(customDao.queryBookingDepositReport(paramMap));
		return depositSearchResult;
	}

	@Override
	public boolean creditMgmtManage(Master master, Custom custom) {
		// 未开启保证金
		if (custom.getBookingManagment() != null && !custom.getBookingManagment()) {
			return true;
		}
		// 开启保证金
		// 预付订单
		String channelCode = master.getChannel();
		// TAOBAO 预付：TAGAD 非TAOBAO预付:DUE
		if (master.getPayment().equalsIgnoreCase(GuaranteeCode.DUE) || master.getPayment().equalsIgnoreCase(GuaranteeCode.PREPAID) || (ChannelCode.TAOBAO.equalsIgnoreCase(channelCode) && GuaranteeCode.TAGTD.equalsIgnoreCase(master.getPayment())) || (ChannelCode.TAOBAO.equalsIgnoreCase(channelCode) && GuaranteeCode.TAGTD_GT.equalsIgnoreCase(master.getPayment())) || GuaranteeCode.VOUCHER.equalsIgnoreCase(master.getPayment())) {
			// originalCreditLimit
			if (custom.getOriginalCreditLimit() != null && !custom.getOriginalCreditLimit().isEmpty()) {
				// 原始保证金
				BigDecimal originalCreditLimit = new BigDecimal(custom.getOriginalCreditLimit());
				// 总房晚收入
				BigDecimal totalRoomRev = new BigDecimal(0);
				String totalRoomRevStr = custom.getTotalRoomRev();
				if (totalRoomRevStr != null && !totalRoomRevStr.isEmpty()) {
					totalRoomRev = new BigDecimal(totalRoomRevStr);
				}
				// 已收总和
				BigDecimal income = new BigDecimal(custom.getIncome());
				String incomeStr = custom.getIncome();
				if (incomeStr != null && !incomeStr.isEmpty()) {
					income = new BigDecimal(incomeStr);
				}
				// 最低限额
				BigDecimal minLimit = new BigDecimal(0);
				String minLimitStr = custom.getMinLimit();
				if (minLimitStr != null && !minLimitStr.isEmpty()) {
					minLimit = new BigDecimal(minLimitStr);
				}
				// originalCreditLimit+income-totalRoomRev-minLimit
				BigDecimal number = originalCreditLimit.add(income).subtract(totalRoomRev.add(minLimit));
				BigDecimal charge = master.getCharge();
				// number<charge 验证失败
				if (number.compareTo(charge) > -1) {
					return true;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void updateTotalRoomRev(Master master, Custom custom) {
		String channelCode = master.getChannel();
		if (master.getPayment().equalsIgnoreCase(GuaranteeCode.DUE) || master.getPayment().equalsIgnoreCase(GuaranteeCode.PREPAID) || (GuaranteeCode.TAGTD.equalsIgnoreCase(master.getPayment()) && ChannelCode.TAOBAO.equalsIgnoreCase(channelCode)) || (GuaranteeCode.TAGTD_GT.equalsIgnoreCase(master.getPayment()) && ChannelCode.TAOBAO.equalsIgnoreCase(channelCode)) || GuaranteeCode.VOUCHER.equalsIgnoreCase(master.getPayment())) {
			// custom 总房晚收入
			BigDecimal totalRoomRev = new BigDecimal(0);
			if (custom.getTotalRoomRev() != null && !custom.getTotalRoomRev().isEmpty()) {
				totalRoomRev = new BigDecimal(custom.getTotalRoomRev());
			}
			// 订单的总价格
			BigDecimal charge = master.getCharge();

			// 预定 直接加
			if (OrderStatus.ADD.equals(master.getSta())) {
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
				totalRoomRev = totalRoomRev.subtract(charge).add(master.getDeduct());
				// //入住时间
				// Date arr = master.getArr();
				// //入住时间的48小时前日期
				// Date newDate = DateUtil.addDays(arr, -2);
				// //当前时间
				// Date now = DateUtil.currentDate();
				//
				// //48小时之前,释放所有
				// if(now.before(newDate)){
				// totalRoomRev = totalRoomRev.subtract(charge);
				// }
				// //48小时之内，扣除首日价
				// else {
				// List<MasterRate> list =
				// masterDao.getMasterRateByOrderNo(master.getMasterId(),
				// DateUtil.getDate(master.getArr()));
				// MasterRate m = list.get(0);
				// //首日价
				// BigDecimal firstDateRate =
				// m.getSetrate().add(m.getPackages());
				// //只有一晚,不释放
				// if(firstDateRate.compareTo(charge)==0){
				// //totalRoomRev = totalRoomRev.subtract(charge);
				// }else{
				// totalRoomRev =
				// totalRoomRev.subtract(charge).add(firstDateRate);
				// }
				// }
			}

			custom.setTotalRoomRev(totalRoomRev.toString());

			// update custom TotalRoomRev
			customDao.updateTotalRoomRev(custom);

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
			cl.setOriginalCreditLimit(custom.getOriginalCreditLimit());
			cl.setTotalRoomRev(totalRoomRev.toString());
			cl.setIncome(custom.getIncome());
			cl.setMinLimit(custom.getMinLimit());
			customLogDao.saveCustomLog(cl);
		}
	}

	@Override
	public Custom searchCustomByHotelIdAndAccessCode(String hotelId, String accessCode) {
		return customDao.searchCustomByHotelIdAndAccessCode(hotelId, accessCode);
	}

	/**
	 * 根据逻辑判断是否重复ProfileID记录
	 */
	@Override
	public boolean existProfileID(Custom c) {
		Custom profileID = new Custom();
		profileID.setProfileID(c.getProfileID());
		profileID.setHotelId(c.getHotelId());
		List<Custom> cList = customDao.getCustomByObj(profileID);
		// 更新
		if (StringUtils.hasText(c.getCustomId())) {
			if (cList == null || cList.isEmpty()) {
				return false;
			} else if (cList.size() == 1 && cList.get(0).getCustomId().equals(c.getCustomId())) {
				return false;
			} else {
				return true;
			}
		}
		// 添加
		else if (cList != null && !cList.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 根据profileID 和 hotelId添加profileMessage
	 * 
	 * @param custom
	 */
	@Override
	public void updateProfileMessage(Custom custom) {
		customDao.updateProfileMessage(custom);
	}
	
	@Override
	public List<String> getAccessRatePlanIdByChannelIds(String hotelId,
			List<Channel> channelList){
		return rateCustomRelationshipDao.getAccessRatePlanIdByChannelIds(hotelId, channelList);
	}
}
