package com.ccm.api.dao.city;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.city.City;

public interface CityDao extends GenericDao<City, Integer> {

	/**
	 * 添加
	 * 
	 * @param city
	 */
	public int saveCity(City city);

	/**
	 * 修改
	 * 
	 * @param city
	 */
	public void updateCity(City city);

	/**
	 * 删除，直接删除数据
	 * 
	 * @param city
	 */
	public void deleteCity(City city);

	/**
	 * 获取父类下的城市/区域列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<City> getCityList(int parentId, String language);

	/**
	 * 查找城市
	 * 
	 * @param id
	 * @return
	 */
	public City getCity(int id, String language);

	/**
	 * 验证cityCode是否存在
	 * 
	 * @param cityCode
	 * @param language
	 * @return
	 */
	public Boolean checkCityCode(String cityCode, String language);

	/**
	 * 通过城市代码查找同一层次的城市列表
	 * 
	 * @param cityCode
	 * @param language
	 * @return
	 */
	public List<City> getCityByCityCode(String cityCode, String language);

	List<City> getCityByCityName(String cityName, String language);
}
