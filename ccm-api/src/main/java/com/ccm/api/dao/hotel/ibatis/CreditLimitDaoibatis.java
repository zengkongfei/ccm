package com.ccm.api.dao.hotel.ibatis;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.CreditLimitDao;
import com.ccm.api.model.hotel.CreditLimit;
import com.ccm.api.model.hotel.HotelCreditLimitBinding;
import com.ccm.api.model.hotel.vo.CreditLimitCreteria;
import com.ccm.api.model.hotel.vo.CreditLimitVO;
import com.ccm.api.util.CommonUtil;

@Repository("creditLimitDao")
public class CreditLimitDaoibatis extends GenericDaoiBatis<CreditLimit,String> implements CreditLimitDao{

	public CreditLimitDaoibatis() {
		super(CreditLimit.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CreditLimit getCreditLimitByDetail(
			HotelCreditLimitBinding hotelCreditLimitBinding) {
		// TODO Auto-generated method stub
		List<CreditLimit> creditLimitList=getSqlMapClientTemplate().queryForList("getCreditLimitByDetail", hotelCreditLimitBinding);
		if(CommonUtil.isNotEmpty(creditLimitList)){
			return creditLimitList.get(0);
		}
		return null;
	}

	@Override
	public void removeCreditLimit(String creditLimitId) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("removeCreditLimit", creditLimitId);
	}
	
	@Override
	public void updateTotalRoomRev(CreditLimit creditLimit){
		getSqlMapClientTemplate().update("updateTotalRoomRevOfCreditLimit",creditLimit);
	}

	@Override
	public List<CreditLimitVO> getCreditLimitVOList(CreditLimitCreteria creteria) {
		return getSqlMapClientTemplate().queryForList("getCreditLimitVOList", creteria);
	}

	@Override
	public Integer getCreditLimitVOCount(CreditLimitCreteria creteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("getCreditLimitVOCount",creteria);
	}

	@Override
	public CreditLimitVO getCreditLimitVO(String creditLimitId) {
		// TODO Auto-generated method stub
		return (CreditLimitVO) getSqlMapClientTemplate().queryForObject("getCreditLimitVO",creditLimitId);
	}

	@Override
	public void updateSentSwitchOfCreditLimit(CreditLimit creditLimit) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("updateSentSwitchOfCreditLimit",creditLimit);
	}

}
