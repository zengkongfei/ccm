/**
 * 
 */
package com.ccm.api.service.common;

import java.util.List;
import java.util.Map;

import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.common.Dictionary;
import com.ccm.api.model.common.vo.DictCodeChannelCodeNameVO;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny Liao
 * 
 */
public interface DictCodeManager extends GenericManager<DictCode, String> {

	/**
	 * 根据字典类型找字典代码列表
	 * 
	 * @param dictType
	 * @param language 
	 * @return
	 */
	public List<Dictionary> getDictionaryByDictType(String dictType, String language);

	/**
	 * 根据字典ID找系统值
	 * 
	 * @param dictId
	 * @return
	 */
	public List<DictCode> getDictCodeByDictId(String dictId, String hotelId,String language);

	/**
	 * 根据字典名称找字典代码列表
	 * 
	 * @param dictKey
	 * @return
	 */
	public List<DictCode> searchByDictName(String dictName);
	/**
	 * 根据字典名称找字典代码列表
	 * 
	 * @param dictKey
	 * @param language
	 * @return
	 */
	public List<DictCode> searchByDictName(String dictName,String language);

	/**
	 * 根据字典名称与代码找具体字典代码
	 * 
	 * @param dictKey
	 * @param codeNo
	 * @return
	 */
	public DictCode searchByCodeNo(String dictName, String codeNo);
	
	/**
	 * 根据字典名称与代码找具体字典代码
	 * 
	 * @param dictKey
	 * @param codeNo
	 * @param language
	 * @return
	 */
	public DictCode searchByCodeNo(String dictName, String codeNo,String language);

	/**
	 * 根据字典名称与渠道代码名称查询此渠道对应的所有设施
	 * 
	 * @param dictName
	 * @param channelCode
	 * @return
	 */
	public List<DictCodeChannelCodeNameVO> searchCodeByChannelDictName(String dictName, String channelCode);

	/**
	 * 根据字典名称与渠道代码名称查询此渠道对应的所有设施。 key:渠道的字段名称（codeListMapping中的codeName），
	 * value:系统中真实的值(dictCode中的codeNo)
	 * 
	 * @param dictName
	 * @param channelCode
	 * @param codeName
	 *            ：true时key:codeName,value:codeNo;
	 *            false时key:codeNo,value:codeName
	 * @return
	 */
	public Map<String, String> searchChannelCodeNoByChannel(String dictName, String channelCode, Boolean codeName);

	/**
	 * kvf为true:key:系统中的值，value:pms的值 . kvf为false:key:pms的值，value:系统中的值
	 * 
	 * @param dictName
	 * @param hotelId
	 * @param kvf
	 * @return
	 */
	public Map<String, String> searchCodeMapByHotelId(String dictName, String hotelId, Boolean kvf);

	/**
	 * kvf为true:key:系统中的值，value:pms的值 . kvf为false:key:pms的值，value:系统中的值
	 * 
	 * @param dictName
	 * @param chainCode
	 * @param hotelCode
	 * @param kvf
	 * @return
	 */
	public Map<String, String> searchCodeMapByHotelCode(String dictName, String chainCode, String hotelCode, Boolean kvf);

	
	public Map<String, String> searchCodeMapByHotelCode3(String hotelId, Boolean kvf);
	/**
	 * kvf为true:key:系统中的值，value:pms的值 . kvf为false:key:pms的值，value:系统中的值
	 * 
	 * @param dictName
	 * @param channelId
	 * @param hotelId
	 * @param kvf
	 * @return
	 */
	public Map<String, String> searchCodeMapByChannelHotel(String dictName, String channelId, String hotelId, Boolean kvf);

	/**
	 * 清除ccm与PMS映射的hashMap缓存
	 * 
	 * @param dictId
	 * @param bizId
	 */
	void cleanHashMap(String dictId, String bizId);

	/**
	 * mc发起异步请求到ws处理映射缓存
	 * 
	 * @param dictId
	 * @param bizId
	 */
	void sendWs(String dictId, String bizId);
}
