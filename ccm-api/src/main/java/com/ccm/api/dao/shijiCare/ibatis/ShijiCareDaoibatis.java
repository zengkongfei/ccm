package com.ccm.api.dao.shijiCare.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.shijiCare.ShijiCareDao;
import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.model.shijicare.vo.ShijicareCriteria;
import com.ccm.api.model.shijicare.vo.ShijicareVO;

@Repository
public class ShijiCareDaoibatis extends GenericDaoiBatis<ShijiCare, String> implements ShijiCareDao {

	public ShijiCareDaoibatis(){
		super(ShijiCare.class);
	}

	@Override
	public Integer getCount(ShijicareCriteria sc) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("getShijicareCount", sc);
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShijicareVO> getList(ShijicareCriteria sc) {
		List<ShijicareVO> list = getSqlMapClientTemplate().queryForList("getShijicareList",sc);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShijicareVO> getExcelList(ShijicareCriteria sc) {
		List<ShijicareVO> list = getSqlMapClientTemplate().queryForList("getShijicareExeclList",sc);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShijiCare> getShijiCareList(ShijiCare shijiCare) {
		List<ShijiCare> list = getSqlMapClientTemplate().queryForList("getSuccessShijiCareList",shijiCare);
		return list;
	}

	@Override
	public void closeShijicareBycareId(ShijiCare shijiCare) {
		getSqlMapClientTemplate().update("closeShijicareBycareId", shijiCare);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShijiCare> shijiCareList(ShijiCare shijiCare) {
		List<ShijiCare> list = getSqlMapClientTemplate().queryForList("shijiCareList",shijiCare);
		return list;
	}

	@Override
	public void updateShjicareSendSms(ShijiCare shijiCare) {
		getSqlMapClientTemplate().update("updateShjicareSendSms", shijiCare);
		
	}
	
}
