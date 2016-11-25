package com.ccm.api.service.hotel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.hotel.BrandDao;
import com.ccm.api.dao.hotel.ChainDao;
import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.dao.hotel.Hoteli18nDao;
import com.ccm.api.model.city.City;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.constant.WBEConstant;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.HotelI18n;
import com.ccm.api.model.hotel.vo.BrandVO;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.model.hotel.vo.HotelCreteria;
import com.ccm.api.model.hotel.vo.HotelResult;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.hotel.vo.SpecialOfferVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.SpecialOfferManager;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.PropertiesUtil;

@Service("hotelMCManager")
public class HotelMCManagerImpl extends GenericManagerImpl<Hotel, String> implements HotelMCManager {

	@Autowired
	private HotelMCDao hotelMCDao;
	@Autowired
	private BrandDao brandDao;
	@Autowired
	private ChainDao chainDao;
	
	@Autowired
	private Hoteli18nDao hotelI18nDao;
	
	@Resource
	private PictureManager pictureManager;
	
	@Autowired
	private ChainManager chainManager;
	
	@Autowired
	private SpecialOfferManager specialOfferManager; //优惠信息

	@Override
	public HotelVO saveHotelMC(HotelVO vo) {
		Hotel vo1 = new Hotel();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = hotelMCDao.addHotelMC(vo1);
		vo.setHotelId(vo1.getHotelId());
		
		if(vo.getHotelI18nList()==null||vo.getHotelI18nList().size()==0){
			HotelI18n i18n = this.getDefaultLanguageI18n(vo);
			List<HotelI18n> i18nList = new ArrayList<HotelI18n>();
			i18nList.add(i18n);
			vo.setHotelI18nList(i18nList);
		}
		
		//循环遍历多语言列表
		for (HotelI18n hotelI18n : vo.getHotelI18nList()) {
			HotelI18n i18n = new HotelI18n();
			i18n.setHotelId(vo.getHotelId());
			i18n.setLanguageCode(hotelI18n.getLanguageCode());
			i18n.setHotelName(hotelI18n.getHotelName());
			i18n.setHotelShortName(hotelI18n.getHotelShortName());
			i18n.setHotelUsedName(hotelI18n.getHotelUsedName());
			i18n.setBusiness(hotelI18n.getBusiness());
			i18n.setAddress(hotelI18n.getAddress());
			i18n.setSalutatory(hotelI18n.getSalutatory());
			i18n.setDescription(hotelI18n.getDescription());
			i18n.setDelFlag(Boolean.FALSE);  //设置为非软删
			i18n.setCheckInTimeDesc(hotelI18n.getCheckInTimeDesc());
			i18n.setCheckOutTimeDesc(hotelI18n.getCheckOutTimeDesc());
			i18n.setCancelPolicyDesc(hotelI18n.getCancelPolicyDesc());
			i18n.setPayRemind(hotelI18n.getPayRemind());
			i18n.setPickUpService(hotelI18n.getPickUpService());
			hotelMCDao.addHotelI18nMC(i18n);
		}
		
		
		//如果上传了图片,则将酒店记录和图片匹配上
		if(StringUtils.isNotBlank(vo.getPicId())){
			pictureManager.updatePictureBizIdToPId(vo.getHotelId(), vo.getPicId());
		}
		
		return vo;
	}

	@Override
	public void updateHotelMC(HotelVO vo) {
		Hotel vo1 = new Hotel();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//保存酒店信息
		hotelMCDao.updateHotelMC(vo1);

		//如果不为空
		if(vo.getHotelI18nList()!=null){
			//先删除酒店下的所有多语言记录
			hotelI18nDao.deleteHotelI18nByHotelId(vo.getHotelId());
			
			//循环遍历多语言列表
			for (HotelI18n hotelI18n : vo.getHotelI18nList()) {
				HotelI18n i18n = new HotelI18n();
				i18n.setHotelId(vo.getHotelId());
				i18n.setLanguageCode(hotelI18n.getLanguageCode());
				i18n.setHotelName(hotelI18n.getHotelName());
				i18n.setHotelShortName(hotelI18n.getHotelShortName());
				i18n.setHotelUsedName(hotelI18n.getHotelUsedName());
				i18n.setBusiness(hotelI18n.getBusiness());
				i18n.setAddress(hotelI18n.getAddress());
				i18n.setSalutatory(hotelI18n.getSalutatory());
				i18n.setDescription(hotelI18n.getDescription());
				i18n.setDelFlag(Boolean.FALSE);  //设置为非软删
				i18n.setCheckInTimeDesc(hotelI18n.getCheckInTimeDesc());
				i18n.setCheckOutTimeDesc(hotelI18n.getCheckOutTimeDesc());
				i18n.setCancelPolicyDesc(hotelI18n.getCancelPolicyDesc());
				i18n.setPayRemind(hotelI18n.getPayRemind());
				i18n.setPickUpService(hotelI18n.getPickUpService());
				hotelMCDao.addHotelI18nMC(i18n);
			}
		}else{
			HotelI18n i18n = new HotelI18n();
			i18n.setHotelMId(vo.getHotelMId());
			i18n.setHotelId(vo.getHotelId());
			i18n.setLanguageCode(vo.getLanguageCode());
			i18n.setHotelName(vo.getHotelName());
			i18n.setHotelShortName(vo.getHotelShortName());
			i18n.setHotelUsedName(vo.getHotelUsedName());
			i18n.setBusiness(vo.getBusiness());
			i18n.setAddress(vo.getAddress());
			i18n.setSalutatory(vo.getSalutatory());
			i18n.setDescription(vo.getDescription());
			i18n.setDelFlag(Boolean.FALSE);  //设置为非软删
			i18n.setCheckInTimeDesc(vo.getCheckInTimeDesc());
			i18n.setCheckOutTimeDesc(vo.getCheckOutTimeDesc());
			i18n.setCancelPolicyDesc(vo.getCancelPolicyDesc());
			i18n.setPayRemind(vo.getPayRemind());
			i18n.setPickUpService(vo.getPickUpService());
			hotelMCDao.updateHotelI18nMC(i18n);
		}
	}

	@Override
	public void deleteHotelMC(HotelVO vo) {
		Hotel vo1 = new Hotel();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hotelMCDao.deleteHotelMC(vo1);
		
		//删除多语言表中的数据
		hotelI18nDao.deleteHotelI18nByHotelId(vo.getHotelId());

	}

	@Override
	public HotelVO getHotelByCodeMC(HotelVO vo) {
		return hotelMCDao.getHotelByCodeMC(vo);
	}
	
	@Override
	public HotelVO getHotelByIdMC(String hotelId) {
		return hotelMCDao.getHotelByIdMC(hotelId);
	}

	@Override
	public HotelVO getHotelByIdMC(String hotelId,String languageCode) {
		return hotelMCDao.getHotelByIdMC(hotelId,languageCode);
	}

	@Override
	public HotelResult searchHotelMC(HotelCreteria creteria) {
		HotelResult result = new HotelResult();
		
		//如果没有设置多语言编码,则默认设置中文
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguageCode())){
			creteria.setLanguageCode(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		
		List<HotelVO> voList = hotelMCDao.searchHotelMC(creteria);
		Map<String, BrandVO> brandMap = new HashMap<String, BrandVO>();
		Map<String, ChainVO> chainMap = new HashMap<String, ChainVO>();
		
		if (!voList.isEmpty()) {
			for (HotelVO vo : voList) {
				if (null != vo.getBrandId() && !"".equals(vo.getBrandId())) {
					//从Map中加载
					BrandVO brand = brandMap.get(vo.getBrandId());
					if(brand == null){
						brand = brandDao.getBrandById(vo.getBrandId(),creteria.getLanguageCode());
					}
					
					if (brand != null) {
						brandMap.put(vo.getBrandId(), brand);
						vo.setBrandName(brand.getBrandName());
					}
				}
				if (null != vo.getChainId() && !"".equals(vo.getChainId())) {
					//从Map中加载
					ChainVO chain = chainMap.get(vo.getChainId());
					if(chain == null){
						chain = chainDao.getChainById(vo.getChainId(),creteria.getLanguageCode());
					}
					
					if (chain != null) {
						chainMap.put(vo.getChainId(), chain);
						vo.setChainName(chain.getChainName());
					}
				}
			}
		}
		Integer count = hotelMCDao.searchHotelCountMC(creteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}

	@Override
	public List<HotelVO> getAllHotelMC() {
		return hotelMCDao.getAllHotelMC();
	}
	
	@Override
	public HotelI18n getDefaultLanguageI18n(HotelVO hotelVo){
		//添加中文的语言编码
		HotelI18n i18n = new HotelI18n();
		if(hotelVo.getLanguageCode()==null){
			i18n.setLanguageCode(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			i18n.setLanguageCode(hotelVo.getLanguageCode());
		}
		i18n.setHotelName(hotelVo.getHotelName());
		i18n.setHotelShortName(hotelVo.getHotelShortName());
		i18n.setHotelUsedName(hotelVo.getHotelUsedName());
		i18n.setAddress(hotelVo.getAddress());
		i18n.setBusiness(hotelVo.getBusiness());
		i18n.setSalutatory(hotelVo.getSalutatory());
		i18n.setDescription(hotelVo.getDescription());
		i18n.setCheckInTimeDesc(hotelVo.getCheckInTimeDesc());
		i18n.setCheckOutTimeDesc(hotelVo.getCheckOutTimeDesc());
		i18n.setCancelPolicyDesc(hotelVo.getCancelPolicyDesc());
		i18n.setPayRemind(hotelVo.getPayRemind());
		i18n.setPickUpService(hotelVo.getPickUpService());
		return i18n;
	}

	@Override
	public List<HotelVO> getAllHotelMC(String languageCode) {
		return hotelMCDao.getAllHotelMC(languageCode);
	}

	@Override
	public HotelVO initHotelVo(String chainCode, String hotelCode,
			String languageCode) throws BizException {
		//获取集团
		ChainVO chainVo = chainManager.getChainByCode(chainCode, languageCode);
		if(chainVo == null){
			throw new BizException("WBE-Exception","集团不存在:chainCode="+chainCode);
		}
		
		//查询得到酒店对象
		HotelVO hotelVo = new HotelVO();
		hotelVo.setHotelCode(hotelCode);
		hotelVo.setLanguageCode(languageCode);
		hotelVo.setChainId(chainVo.getChainId());
		hotelVo = hotelMCDao.getHotelByCodeMC(hotelVo);
		if(hotelVo == null){
			throw new BizException("WBE-Exception","酒店不存在:hotelCode="+hotelCode+"&chainCode="+chainCode);
		}
		
		//如果不是英文语言
		if(!LanguageCode.EN_US.equalsIgnoreCase(languageCode)){
			hotelVo.setLanguageCode(LanguageCode.EN_US);
			HotelVO en_hotelVo = hotelMCDao.getHotelByCodeMC(hotelVo);
			if(en_hotelVo!=null){
				hotelVo.setHotelUsedName(en_hotelVo.getHotelName());
			}
		}
		
		hotelVo.setChainCode(chainCode);
		hotelVo.setChainId(chainVo.getChainId());
		hotelVo.setLanguageCode(languageCode);
		hotelVo.setChannelCode(WBEConstant.CHANNEL_CODE); //渠道代码(默认是OWS)
		if(StringUtils.isNotBlank(hotelVo.getDescription())){
			hotelVo.setDescription(hotelVo.getDescription().replaceAll("\n", "<br/>"));
		}
		
		//获取优惠信息
		List<SpecialOfferVO> allOfferList = 
			specialOfferManager.getAllSpecialOfferByHotelId(hotelVo.getHotelId(), languageCode);
		
		if(allOfferList!=null&&allOfferList.size()>0){
			List<SpecialOfferVO> specialOfferList = new ArrayList<SpecialOfferVO>();
			for (SpecialOfferVO specialOfferVO : allOfferList) {
				if(specialOfferVO.getBeginTime()!=null
						&&specialOfferVO.getEndTime()!=null){
					/**
					 * 促销时间在有效时间内
					 */
					if(DateUtil.currentDateTime().compareTo(specialOfferVO.getBeginTime())>=0
							&&DateUtil.currentDateTime().compareTo(specialOfferVO.getEndTime())<=0){
						specialOfferList.add(specialOfferVO);
					}
				}
			}
			hotelVo.setSpecialOfferVoList(specialOfferList);
		}
		
		//获取酒店图片和读取目录
		hotelVo.setPictureList(pictureManager.getBizPictureList("1", hotelVo.getHotelId()));
		
		List<Picture> logoPicList = pictureManager.getBizPictureList("1", hotelVo.getLogoPicId());
		if(logoPicList!=null&&logoPicList.size()>0){
			//获取logo
			hotelVo.setLogoPic(logoPicList.get(0));
		}
		
		hotelVo.setPictureUrlFolder(PropertiesUtil.getProperty(ProjectConfigConstant.pictureUrlContext));
		
		return hotelVo;
	}
	
	@Override
	public List<String> loadPrivinces(){
		return hotelMCDao.loadPrivinces();
	}
	
	@Override
	public List<String> loadCitysByPrivince(String privinceCode){
		return hotelMCDao.loadCitysByPrivince(privinceCode);
	}

	@Override
	public List<HotelVO> loadHotelsByCity(String city,String language) {
		return hotelMCDao.loadHotelsByCity(city,language);
	}

	@Override
	public Hotel getHotelByCodeMC3(String chainCode, String hotelCode) {
		return hotelMCDao.getHotelByCodeMC3(chainCode, hotelCode);
	}

	@Override
	public List<Hotel> getUserHotelByChainAndUserId(String chainId, String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("chainId", chainId);
		params.put("userId", userId);
		return hotelMCDao.getUserHotelByChainAndUserId(params);
	}
	
	public List<Hotel> getUserHotelByChainCodeAndUserId(String chainCode, String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("chainCode", chainCode);
		params.put("userId", userId);
		return hotelMCDao.getUserHotelByChainCodeAndUserId(params);
	}

	@Override
	public List<Hotel> getUserHotelByChainAndUserId(String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return hotelMCDao.getUserHotelByChainAndUserId(params);
	}

	@Override
	public List<HotelVO> getHotelByChannelCode(String chainCode, String channel, String language) {
		return hotelMCDao.getHotelByChannelCode(chainCode,channel,language);
	}

	@Override
	public List<City> getPrivinces(String language) {
		return hotelMCDao.getPrivinces(language);
	}

	@Override
	public List<City> getCitysByPrivince(String privinceCode,String language) {
		return hotelMCDao.getCitysByPrivince(privinceCode,language);
	}


	
}
