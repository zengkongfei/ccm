package com.ccm.api.dao.hotel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.ChainI18n;
import com.ccm.api.model.hotel.vo.ChainCreteria;
import com.ccm.api.model.hotel.vo.ChainVO;

public interface ChainDao extends GenericDao<Chain, String> {

	Chain addChain(Chain chain);

	ChainI18n addChainI18n(ChainI18n chainI18n);

	void updateChainI18n(ChainI18n chainI18n);

	void deleteChain(Chain chain);

	void deleteChainI18n(ChainI18n chainI18n);

	Chain getChainByCode(String chainCode);

	ChainVO getChainById(String chainId);

	List<ChainVO> searchChain(ChainCreteria creteria);

	Integer searchChainCount(ChainCreteria creteria);

	List<Chain> getAllChain();

	void deleteChainI18nByChainId(String chainId);

	ChainVO getChainById(String chainId, String language);

	List<ChainI18n> getChainI18ns(String chainId);

	ChainVO getChainByCode(String chainCode, String language);

	List<ChainVO> getAllChainI18n(String language);
}
