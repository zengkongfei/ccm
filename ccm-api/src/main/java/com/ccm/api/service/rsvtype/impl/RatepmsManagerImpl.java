package com.ccm.api.service.rsvtype.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.rsvtype.RatepmsDao;
import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.dao.rsvtype.RsvtypeDetailDao;
import com.ccm.api.model.rsvtype.Ratepms;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeDetail;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.rsvtype.RatepmsManager;
import com.ccm.api.util.DateUtil;

@Service("ratepmsManager")
public class RatepmsManagerImpl extends GenericManagerImpl<Ratepms, String> implements RatepmsManager {

	@Autowired
	private RatepmsDao ratepmsDao;
	@Autowired
	private RsvtypeDao rsvtypeDao;
	@Autowired
	private RsvtypeDetailDao rsvtypeDetailDao;

	@Override
	public void addRatepms(Ratepms ratepms) {
		ratepmsDao.addRatepms(ratepms);
	}

	@Override
	public void updateRatepmsStatus(String ratepmsId) {
		ratepmsDao.updateRatepmsStatus(ratepmsId);
	}

	@Override
	public List<Ratepms> getRatepms() {
		return ratepmsDao.getRatepms();
	}

	@Override
	@Async
	public void parseRatepms(Ratepms ratepms) {
		// 查询所有未处理的从PMS过来的数据
		List<Ratepms> ratepmsList = getRatepms();

		for (Ratepms ra : ratepmsList) {
			// 保存价格日历表
			List<Rsvtype> rsvtypeList = ratepmsToRsvtype(ratepms);
			for (Rsvtype rsvtype : rsvtypeList) {
				Rsvtype rs = rsvtypeDao.getRsvtype(rsvtype);
				if (null == rs) {
					rsvtypeDao.save(rsvtype);
				} else {
//					log.info("pmsFreesell:" + rsvtype.getFreeSell() + ",ccmSrcFreeSell:" + rs.getFreeSell());
//					rsvtype.setFreeSell(rs.getFreeSell());
					rsvtypeDao.updateRsvtype(rsvtype);
				}
			}

			// 保存价格日历明细表
			List<RsvtypeDetail> detailList = ratepmsToRsvtypeDetail(ratepms);
			for (RsvtypeDetail detail : detailList) {
				RsvtypeDetail rd = rsvtypeDetailDao.getRsvtypeDetail(detail);
				if (null == rd) {
					rsvtypeDetailDao.addRsvtypeDetail(detail);
				} else {
					rsvtypeDetailDao.updateRsvtypeDetail(detail);
				}
			}
			// 修改PMS过来数据状态
			updateRatepmsStatus(ra.getRatepmsId());
		}
	}

	/**
	 * Ratepms对象转换为Rsvtype
	 */
	private List<Rsvtype> ratepmsToRsvtype(Ratepms ratepms) {
		List<Rsvtype> list = new ArrayList<Rsvtype>();
		Rsvtype rsvtype = null;

		Date startDate = ratepms.getStartDate();
		Date endDate = ratepms.getEndDate();
		int days = DateUtil.dateDiff(startDate, endDate);
		String[] roomTypes = ratepms.getRoomTypeList().split(",");

		for (int i = 0; i < days; i++) {
			Date date = new Date(startDate.getTime() + i * 24 * 60 * 60 * 1000);
			if (!isValidDate(ratepms, date)) {
				continue;
			}
			for (int j = 0; j < roomTypes.length; j++) {
				rsvtype = new Rsvtype();
				rsvtype.setHotelid(ratepms.getHotelCode());
				rsvtype.setDate(date);
				rsvtype.setType(roomTypes[j]);
				rsvtype.setRateCode(ratepms.getRateCode());
				rsvtype.setRate(ratepms.getRate2().doubleValue());
				rsvtype.setChannel("TB");
				rsvtype.setSta(1);
				list.add(rsvtype);
			}
		}
		return list;
	}

	/**
	 * Ratepms对象转换为RsvtypeDetail
	 */
	private List<RsvtypeDetail> ratepmsToRsvtypeDetail(Ratepms ratepms) {
		List<RsvtypeDetail> list = new ArrayList<RsvtypeDetail>();
		RsvtypeDetail detail = null;

		Date startDate = ratepms.getStartDate();
		Date endDate = ratepms.getEndDate();
		int days = DateUtil.dateDiff(startDate, endDate);
		String[] roomTypes = ratepms.getRoomTypeList().split(",");

		for (int i = 0; i < days; i++) {
			Date date = new Date(startDate.getTime() + i * 24 * 60 * 60 * 1000);
			if (!isValidDate(ratepms, date)) {
				continue;
			}
			for (int j = 0; j < roomTypes.length; j++) {
				detail = new RsvtypeDetail();
				detail.setHotelid(ratepms.getHotelCode());
				detail.setDate(date);
				detail.setType(roomTypes[j]);
				detail.setRateCode(ratepms.getRateCode());
				detail.setRate1(ratepms.getRate1());
				detail.setRate2(ratepms.getRate2());
				detail.setRate3(ratepms.getRate3());
				detail.setRate4(ratepms.getRate4());
				detail.setRate5(ratepms.getRate5());
				detail.setChild1RateParentRoom(ratepms.getChild1RateParentRoom());
				detail.setChild2RateParentRoom(ratepms.getChild2RateParentRoom());
				detail.setChild3RateParentRoom(ratepms.getChild3RateParentRoom());
				detail.setChild1RateOwnRoom(ratepms.getChild1RateOwnRoom());
				detail.setChild2RateOwnRoom(ratepms.getChild2RateOwnRoom());
				detail.setChild3RateOwnRoom(ratepms.getChild3RateOwnRoom());
				detail.setChild4RateOwnRoom(ratepms.getChild4RateOwnRoom());
				detail.setExtraBed(ratepms.getExtraBed());
				detail.setPayingChildren(ratepms.getPayingChildren());
				detail.setFreeChildren(ratepms.getFreeChildren());
				detail.setMarketCode(ratepms.getMarketCode());
				list.add(detail);
			}
		}
		return list;
	}

	/**
	 * 判断日期在星期中是否有效(选中) true:表示有效；false：表示无效
	 */
	private boolean isValidDate(Ratepms ratepms, Date date) {
		boolean boo = false;
		int weekday = DateUtil.getWeekday(date);
		switch (weekday) {
		case 2:
			if (ratepms.getMonday()) {
				boo = true;
			}
			break;
		case 3:
			if (ratepms.getTuesday()) {
				boo = true;
			}
			break;
		case 4:
			if (ratepms.getWednesday()) {
				boo = true;
			}
			break;
		case 5:
			if (ratepms.getThursday()) {
				boo = true;
			}
			break;
		case 6:
			if (ratepms.getFriday()) {
				boo = true;
			}
			break;
		case 7:
			if (ratepms.getSaturday()) {
				boo = true;
			}
			break;
		case 1:
			if (ratepms.getSunday()) {
				boo = true;
			}
			break;
		}
		return boo;
	}
}
