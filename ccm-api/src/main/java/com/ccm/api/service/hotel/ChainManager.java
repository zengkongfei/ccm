package com.ccm.api.service.hotel;

import java.util.List;

import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.ChainI18n;
import com.ccm.api.model.hotel.vo.ChainCreteria;
import com.ccm.api.model.hotel.vo.ChainResult;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.service.base.GenericManager;

public interface ChainManager extends GenericManager<Chain, String> {

	/**
	 * 新增集团
	 */
	ChainVO saveChain(ChainVO vo);

	/**
	 * 修改集团
	 */
	void updateChainI18n(ChainVO vo);

	/**
	 * 删除集团
	 */
	void deleteChain(ChainVO vo);

	/**
	 * 根据集团代码取集团信息
	 */
	Chain getChainByCode(String chainCode);

	/**
	 * 根据集团代码和语言对象取集团信息
	 */
	ChainVO getChainByCode(String chainCode, String language);

	/**
	 * 根据集团ID取集团信息
	 */
	ChainVO getChainById(String chainId);

	/**
	 * 根据集团ID和语言得到集团VO对象
	 * 
	 * @param chainId
	 * @param language
	 * @return
	 */
	ChainVO getChainById(String chainId, String language);

	/**
	 * 根据条件取集团信息
	 */
	ChainResult searchChain(ChainCreteria creteria);

	/**
	 * 查询所有集团
	 */
	List<Chain> getAllChain();

	/**
	 * 根据语言种类查询所有集团
	 */
	List<ChainVO> getAllChainI18n(String language);

	/**
	 * 删除集团多语言中的记录
	 */
	void deleteChainI18nByChainId(String chainId);

	/**
	 * 根据集团ID获取集团对应的多语言列表
	 * 
	 * @param chainId
	 * @return
	 */
	List<ChainI18n> getChainI18ns(String chainId);

	/**
	 * 获取默认语言的集团对象
	 * 
	 * @param chainvo
	 * @return
	 */
	ChainI18n getDefaultLanguageI18n(ChainVO chainvo);
}
