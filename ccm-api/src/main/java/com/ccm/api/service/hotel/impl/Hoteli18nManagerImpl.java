/**
 * 
 */
package com.ccm.api.service.hotel.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.hotel.Hoteli18nDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.HotelI18n;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.Hoteli18nManager;

/**
 * @author Jenny
 * 
 */
@Service("hoteli18nManager")
public class Hoteli18nManagerImpl extends GenericManagerImpl<HotelI18n, String> implements Hoteli18nManager {

	private Hoteli18nDao hoteli18nDao;

	@Autowired
	public Hoteli18nManagerImpl(Hoteli18nDao hoteli18nDao) {
		super(hoteli18nDao);
		this.hoteli18nDao = hoteli18nDao;
	}
	
	/**
	 * 查询酒店下面的所有多语言列表
	 */
	public List<HotelI18n> getHotelI18nListByHotelId(String hotelId){
		HotelI18n hi = new HotelI18n();
		hi.setHotelId(hotelId);
		return hoteli18nDao.getHoteli18nByObj(hi);
	}

	/**
	 * 根据酒店ID,语言查询酒店多语言表
	 */
	public HotelI18n getHoteli18nByHotelIdLanguage(String hotelId, String language) {
		HotelI18n hi = new HotelI18n();
		hi.setHotelId(hotelId);
		hi.setLanguageCode(language);
		List<HotelI18n> resultList = hoteli18nDao.getHoteli18nByObj(hi);
		if (resultList != null && !resultList.isEmpty()) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 保存或更新酒店多语言信息
	 * 
	 * @param hv
	 */
	public void saveOrUpdateHotelI18n(HotelVO hv) {

		String language = LanguageCode.ZH_CN;
		HotelI18n hoteli = getHoteli18nByHotelIdLanguage(hv.getHotelId(), language);

		String hotelMid = null;
		if (hoteli == null) {
			// 新增
			hoteli = new HotelI18n();
		} else {
			// 更新
			hotelMid = hoteli.getHotelMId();
			if (hotelMid != null) {
				hoteli.setHotelMId(hotelMid);
			}
		}

		BeanUtils.copyProperties(hv, hoteli);
		hoteli.setLanguageCode(language);

		hoteli18nDao.save(hoteli);
	}
	
	/**
	 * 删除酒店下的多语言列表
	 * @param hotelId
	 */
	public void deleteHotelI18nByHotelId(String hotelId) {
		hoteli18nDao.deleteHotelI18nByHotelId(hotelId);
	}

}
