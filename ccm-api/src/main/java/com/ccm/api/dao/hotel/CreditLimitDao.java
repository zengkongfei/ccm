package com.ccm.api.dao.hotel;


import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.CreditLimit;
import com.ccm.api.model.hotel.HotelCreditLimitBinding;
import com.ccm.api.model.hotel.vo.CreditLimitCreteria;
import com.ccm.api.model.hotel.vo.CreditLimitVO;

public interface CreditLimitDao extends GenericDao<CreditLimit, String>{

	CreditLimit getCreditLimitByDetail(HotelCreditLimitBinding hotelCreditLimitBinding);
	void removeCreditLimit(String creditLimitId);
	void updateTotalRoomRev(CreditLimit creditLimit);
	void updateSentSwitchOfCreditLimit(CreditLimit creditLimit);
	/**
	 * 记录总条数
	 * @param creteria
	 * @return
	 */
	List<CreditLimitVO> getCreditLimitVOList(CreditLimitCreteria creteria);
	
	/**
	 * 分页记录
	 * @param creteria
	 * @return
	 */
	Integer getCreditLimitVOCount(CreditLimitCreteria creteria);
	/**
	 * 获取CreditLimitVO
	 * @param creditLimitId
	 * @return
	 */
	CreditLimitVO getCreditLimitVO(String creditLimitId);
}
