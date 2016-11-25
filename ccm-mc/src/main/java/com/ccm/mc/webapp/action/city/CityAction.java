package com.ccm.mc.webapp.action.city;

import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.city.City;
import com.ccm.api.service.city.CityManager;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class CityAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5556794659039538339L;
	private Log log = LogFactory.getLog(CityAction.class);
	private City city;

	@Autowired
	private CityManager cityManager;
	private int parentId;
	private String cityCode;
	private String cityName;
	private String language;

	public String list() {
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage() + "_" + locale.getCountry();
			List<City> cityList = cityManager.getCityList(parentId, language);
			getRequest().setAttribute("cityList", cityList);
		} catch (Exception e) {
			log.error(e);
		}
		return "list";
	}

	public void getCityList() {
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage() + "_" + locale.getCountry();
			List<City> cityList = cityManager.getCityList(parentId, language);
			String jsonArray = JSON.toJSONString(cityList);
			getResponse().getWriter().write(jsonArray);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void saveCity() {
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage() + "_" + locale.getCountry();
			city.setLanguage(language);
			city.setCityCode(city.getCityCode().trim());
			int id = cityManager.saveCity(city);
			List<City> cityList = cityManager.getCityList(city.getParentId(), language);
			String jsonArray = JSON.toJSONString(cityList);
			JSONObject json = new JSONObject();
			json.put("type", "success");
			json.put("id", id);
			json.put("cityList", jsonArray);
			getResponse().getWriter().write(json.toJSONString());
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void updateCity() {
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage() + "_" + locale.getCountry();
			city.setLanguage(language);
			city.setCityCode(city.getCityCode().trim());
			cityManager.updateCity(city);
			List<City> cityList = cityManager.getCityList(parentId, language);
			String jsonArray = JSON.toJSONString(cityList);
			JSONObject json = new JSONObject();
			json.put("type", "success");
			json.put("cityList", jsonArray);
			getResponse().getWriter().write(json.toJSONString());
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * 验证cityCode是否存在
	 */
	public void ajaxCheckCityCode() {
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage() + "_" + locale.getCountry();
			Boolean bool = cityManager.checkCityCode(cityCode, language);
			getResponse().getWriter().write(bool.toString());
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * 用于bridge通过城市名称和语言代码查询城市编码
	 */
	public void fetchCityCode() {
		City city = cityManager.getCityByCityName(cityName, language);
		if (city != null) {
			ajaxResponse(city.getCityCode());
		}
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
