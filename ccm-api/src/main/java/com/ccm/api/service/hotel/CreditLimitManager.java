package com.ccm.api.service.hotel;

import java.util.List;

import com.ccm.api.model.hotel.CreditLimit;
import com.ccm.api.model.hotel.HotelCreditLimitBinding;
import com.ccm.api.model.hotel.vo.CreditLimitCreteria;
import com.ccm.api.model.hotel.vo.CreditLimitResult;
import com.ccm.api.model.hotel.vo.CreditLimitVO;
import com.ccm.api.model.order.Master;
import com.ccm.api.service.base.GenericManager;

public interface CreditLimitManager extends GenericManager<CreditLimit, String> {
	void saveCreditLimit(CreditLimit cl, List<HotelCreditLimitBinding> hotelCreditLimitBindingList);

	void removeCreditLimit(String creditLimitId);

	CreditLimit getCreditLimitByDetail(String hotelId, String channelId);

	boolean validCreditLimitForOrder(Master master);

	void increaseTotalRoomRev(Master master);

	/**
	 * 分页
	 * 
	 * @param creteria
	 * @return
	 */
	CreditLimitResult getCreditLimitList(CreditLimitCreteria creteria);

	/**
	 * 分页记录
	 * @param creteria
	 * @return
	 */
	public List<CreditLimitVO> getCreditLimitVOList(CreditLimitCreteria creteria);
	
	/**
	 * 记录总条数
	 * @param creteria
	 * @return
	 */
	public Integer getCreditLimitVOCount(CreditLimitCreteria creteria);

	Boolean checkExisted(String channelId, String hotelId);
	
	/**
	 * 获取CreditLimitVO
	 * @param creditLimitId
	 * @return
	 */
	public CreditLimitVO getCreditLimitVO(String creditLimitId);
}
