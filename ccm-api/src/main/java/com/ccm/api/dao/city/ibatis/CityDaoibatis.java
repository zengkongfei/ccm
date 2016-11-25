package com.ccm.api.dao.city.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.city.CityDao;
import com.ccm.api.model.city.City;

/**
 * 城市dao
 * 
 * @author chay.huang
 * 
 */
@Repository("cityDao")
public class CityDaoibatis extends GenericDaoiBatis<City, Integer> implements CityDao {

	public CityDaoibatis() {
		super(City.class);
	}

	@Override
	public int saveCity(City city) {
		city.setCreatedBy(SecurityHolder.getUserId());
		city.setCreatedTime(new Date());
		return Integer.parseInt(getSqlMapClientTemplate().insert("saveCity", city).toString());
	}

	@Override
	public void updateCity(City city) {
		city.setUpdatedBy(SecurityHolder.getUserId());
		city.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateCity", city);

	}

	@Override
	public void deleteCity(City city) {
		getSqlMapClientTemplate().delete("deleteCity", city.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCityList(int parentId, String language) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parentId", parentId);
		param.put("language", language);
		return getSqlMapClientTemplate().queryForList("getCityList", param);
	}

	@Override
	public City getCity(int id, String language) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parentId", id);
		param.put("language", language);
		return (City) getSqlMapClientTemplate().queryForObject("getCity", param);
	}

	@Override
	public Boolean checkCityCode(String cityCode, String language) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cityCode", cityCode);
		param.put("language", language);
		int count = (Integer) getSqlMapClientTemplate().queryForObject("checkCityCode", param);
		if (count == 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCityByCityCode(String cityCode, String language) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cityCode", cityCode);
		param.put("language", language);
		return getSqlMapClientTemplate().queryForList("getCityByCityCode", param);
	}

	@SuppressWarnings("unchecked")
	public List<City> getCityByCityName(String cityName, String language) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cityName", cityName);
		param.put("language", language);
		return getSqlMapClientTemplate().queryForList("getCityCodeList", param);
	}

}
