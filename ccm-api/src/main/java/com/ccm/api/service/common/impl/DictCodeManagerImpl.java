/**
 * 
 */
package com.ccm.api.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.ccm.api.dao.common.DictCodeDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.common.Dictionary;
import com.ccm.api.model.common.vo.DictCodeChannelCodeNameVO;
import com.ccm.api.model.constant.DictType;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.util.HttpclientUtil;

/**
 * @author Jenny Liao
 * 
 */
@Service("dictCodeManager")
public class DictCodeManagerImpl extends GenericManagerImpl<DictCode, String> implements DictCodeManager {

	private DictCodeDao dictCodeDao;
	@Resource
	private HotelDao hotelDao;

	@Resource
	private ChainManager chainManager;

	private Map<String, List<DictCode>> dictListMap = new HashMap<String, List<DictCode>>();

	private Map<String, DictCode> dictMap = new HashMap<String, DictCode>();

	private Map<String, List<DictCodeChannelCodeNameVO>> dictCodeListMap = new HashMap<String, List<DictCodeChannelCodeNameVO>>();

	private Map<String, Map<String, String>> channelValueMap = new HashMap<String, Map<String, String>>();

	private Map<String, Map<String, String>> valueChannelMap = new HashMap<String, Map<String, String>>();

	@Autowired
	public void setDictCodeDao(DictCodeDao dictCodeDao) {
		this.dao = dictCodeDao;
		this.dictCodeDao = dictCodeDao;
	}

	@Autowired
	public DictCodeManagerImpl(DictCodeDao dictCodeDao) {
		super(dictCodeDao);
		this.dictCodeDao = dictCodeDao;
	}

	/**
	 * 根据字典类型找字典代码列表
	 * 
	 * @param dictType
	 * @param language
	 * @return
	 */
	public List<Dictionary> getDictionaryByDictType(String dictType, String language) {
		List<String> dictTypes = new ArrayList<String>();
		dictTypes.add(dictType);
		dictTypes.add(DictType.CCM_PMS_CHANNEL);
		return dictCodeDao.getDictionaryByDictType(dictTypes, language);
	}

	/**
	 * 根据字典ID找系统值
	 */
	public List<DictCode> getDictCodeByDictId(String dictId, String hotelId, String language) {
		return dictCodeDao.getDictCodeByDictId(dictId, hotelId, language);
	}

	/**
	 * 根据字典名称找字典代码列表
	 * 
	 * @param language
	 */
	public List<DictCode> searchByDictName(String dictName) {
		List<DictCode> dictList = dictListMap.get(dictName);
		if (dictList == null || dictList.isEmpty()) {
			dictList = dictCodeDao.searchByDictName(dictName);
			dictListMap.put(dictName, dictList);
		}
		return dictList;
	}

	/**
	 * 根据字典名称找字典代码列表
	 * 
	 * @param language
	 */
	public List<DictCode> searchByDictName(String dictName, String language) {
		List<DictCode> dictList = dictListMap.get(dictName + "-" + language);
		if (dictList == null || dictList.isEmpty()) {
			dictList = dictCodeDao.searchByDictName(dictName, language);
			dictListMap.put(dictName + "-" + language, dictList);
		}
		return dictList;
	}

	/**
	 * 根据字典名称与代码找具体字典代码
	 */
	public DictCode searchByCodeNo(String dictName, String codeNo) {
		String key = getKeyOfDict(dictName, codeNo);
		DictCode dict = dictMap.get(key);
		if (dict == null) {
			// 直接从大类开始查
			List<DictCode> dictList = searchByDictName(dictName, LanguageCode.MAIN_LANGUAGE_CODE);
			for (DictCode code : dictList) {
				dictMap.put(getKeyOfDict(dictName, code.getCodeNo()), code);
			}
			dict = dictMap.get(key);
		}
		return dict;
	}

	/**
	 * 根据字典名称与代码找具体字典代码
	 */
	public DictCode searchByCodeNo(String dictName, String codeNo, String language) {
		String key = getKeyOfDict(dictName, codeNo, language);
		DictCode dict = dictMap.get(key);
		if (dict == null) {
			// 直接从大类开始查
			List<DictCode> dictList = searchByDictName(dictName, language);
			for (DictCode code : dictList) {
				dictMap.put(getKeyOfDict(dictName, code.getCodeNo(), language), code);
			}
			dict = dictMap.get(key);
		}
		return dict;
	}

	/**
	 * 根据字典名称与渠道代码名称查询此渠道对应的所有设施
	 */
	public List<DictCodeChannelCodeNameVO> searchCodeByChannelDictName(String dictName, String channelCode) {
		String key = getKeyOfDict(dictName, channelCode);
		List<DictCodeChannelCodeNameVO> dictList = dictCodeListMap.get(key);
		if (dictList == null || dictList.isEmpty()) {
			dictList = dictCodeDao.searchCodeByChannelDictName(dictName, channelCode);
			dictCodeListMap.put(key, dictList);
		}
		return dictList;
	}

	/**
	 * 根据字典名称与渠道代码名称查询此渠道对应的所有设施。
	 * 
	 * key:渠道的字段名称（codeListMapping中的codeName）， value:系统中真实的值(dictCode中的codeNo)
	 */
	public Map<String, String> searchChannelCodeNoByChannel(String dictName, String channelCode, Boolean codeName) {
		if (codeName) {
			String key = getKeyOfDict(dictName, channelCode + "1");
			Map<String, String> dictMap = channelValueMap.get(key);
			if (dictMap == null || dictMap.isEmpty()) {
				dictMap = (Map<String, String>) dictCodeDao.searchChannelCodeNoByChannel(dictName, channelCode, codeName);
				channelValueMap.put(key, dictMap);
			}
			return dictMap;
		} else {
			String key = getKeyOfDict(dictName, channelCode + "2");
			Map<String, String> dictMap = valueChannelMap.get(key);
			if (dictMap == null || dictMap.isEmpty()) {
				dictMap = (Map<String, String>) dictCodeDao.searchChannelCodeNoByChannel(dictName, channelCode, codeName);
				valueChannelMap.put(key, dictMap);
			}
			return dictMap;
		}

	}

	/**
	 * kvf为true:key:系统中的值，value:pms的值. kvf为false:key:pms的值，value:系统中的值
	 */
	public Map<String, String> searchCodeMapByHotelId(String dictName, String hotelId, Boolean kvf) {
		return searchCodeMapByChannelHotel(dictName, null, hotelId, kvf);
	}

	/**
	 * kvf为true:key:系统中的值，value:pms的值. kvf为false:key:pms的值，value:系统中的值
	 */
	public Map<String, String> searchCodeMapByHotelCode(String dictName, String chainCode, String hotelCode, Boolean kvf) {

		String chainId = null;
		if (StringUtils.hasLength(chainCode)) {
			Chain c = chainManager.getChainByCode(chainCode);
			if (c != null) {
				chainId = c.getChainId();
			}
		}
		// 酒店代码不能为空
		List<HotelVO> hotelList = hotelDao.getHotelI18nByCode(hotelCode, chainId);
		if (hotelList != null && !hotelList.isEmpty()) {
			HotelVO hvo = hotelList.get(0);
			return searchCodeMapByHotelId(dictName, hvo.getHotelId(), kvf);
		}
		return new HashMap<String, String>();

	}

	/**
	 * kvf为true:key:系统中的值，value:pms的值. kvf为false:key:pms的值，value:系统中的值
	 */
	public Map<String, String> searchCodeMapByHotelCode3(String hotelId, Boolean kvf) {
		String f;
		if (kvf) {
			f = "3";
		} else {
			f = "4";
		}
		String key = getKeyOfDict(OXIConstant.roomTypeCode, hotelId + f);
		Map<String, String> dictMap = valueChannelMap.get(key);
		if (dictMap == null || dictMap.isEmpty()) {
			dictMap = dictCodeDao.searchRoomCodeMapByHotel(hotelId, kvf);
			if (dictMap == null) {
				return new HashMap<String, String>();
			} else {
				valueChannelMap.put(key, dictMap);
			}
		}
		return dictMap;
	}

	public Map<String, String> searchCodeMapByChannelHotel(String dictName, String channelId, String hotelId, Boolean kvf) {
		String f;
		if (kvf) {
			f = "3";
		} else {
			f = "4";
		}
		String bizId = "";
		if (StringUtils.hasLength(channelId)) {
			bizId = bizId + channelId;
		}
		if (StringUtils.hasLength(hotelId)) {
			bizId = bizId + hotelId;
		}
		String key = getKeyOfDict(dictName, bizId + f);
		Map<String, String> dictMap = valueChannelMap.get(key);
		if (dictMap == null || dictMap.isEmpty()) {
			dictMap = (Map<String, String>) dictCodeDao.searchCodeMapByChannelHotel(dictName, channelId, hotelId, kvf);
			valueChannelMap.put(key, dictMap);
		}
		return dictMap;

	}

	/**
	 * 清除ccm与PMS映射的hashMap缓存
	 */
	public void cleanHashMap(String dictId, String bizId) {
		if (!valueChannelMap.isEmpty()) {
			if (!StringUtils.hasText(dictId)) {
				valueChannelMap.clear();
			} else {
				Dictionary d = dictCodeDao.getDictionaryByDictId(dictId);
				if (d != null) {
					log.info("start clean");
					valueChannelMap.remove(getKeyOfDict(d.getDictName(), bizId + "3"));
					valueChannelMap.remove(getKeyOfDict(d.getDictName(), bizId + "4"));
					log.info("end clean");
				}
			}
		}
	}

	@Async
	public void sendWs(String dictId, String bizId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dictId", dictId);
		param.put("bizId", bizId);
		String[] strArray = ProjectConfigConstant.cleanCodeMapCacheUrl.split(",");
		for (String url : strArray) {
			try {
				HttpclientUtil.doGet(url, param);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	/**
	 * 产生map的key
	 * 
	 * @param dictName
	 * @param codeNo
	 * @return
	 */
	private String getKeyOfDict(String dictName, String codeNo) {
		return dictName + "-" + codeNo;
	}

	/**
	 * 产生map的key
	 * 
	 * @param dictName
	 * @param codeNo
	 * @return
	 */
	private String getKeyOfDict(String dictName, String codeNo, String language) {
		return dictName + "-" + codeNo + "-" + language;
	}
}
