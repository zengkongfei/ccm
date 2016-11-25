package com.ccm.api.service.city.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.city.CityDao;
import com.ccm.api.model.city.City;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.city.CityManager;

@Service("cityManager")
public class CityManagerImpl extends GenericManagerImpl<City, Integer> implements CityManager {

	@Autowired
	private CityDao cityDao;

	@Override
	public int saveCity(City city) {
		return cityDao.saveCity(city);
	}

	@Override
	public void updateCity(City city) {
		cityDao.updateCity(city);
	}

	@Override
	public void deleteCity(City city) {
		cityDao.deleteCity(city);
	}

	@Override
	public List<City> getCityList(int parentId, String language) {
		return cityDao.getCityList(parentId, language);
	}

	@Override
	public City getCity(int id, String language) {
		return cityDao.getCity(id, language);
	}

	@Override
	public Boolean checkCityCode(String cityCode, String language) {
		return cityDao.checkCityCode(cityCode, language);
	}

	@Override
	public List<City> getCityByCityCode(String cityCode, String language) {
		return cityDao.getCityByCityCode(cityCode, language);
	}

	public City getCityByCityName(String cityName, String language) {
		List<City> cityList = cityDao.getCityByCityName(cityName, language);
		if (cityList != null && !cityList.isEmpty()) {
			return cityList.get(0);
		}
		return null;
	}

}
