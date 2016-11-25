package com.ccm.api.service.hotel.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.hotel.ChainDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.ChainI18n;
import com.ccm.api.model.hotel.vo.ChainCreteria;
import com.ccm.api.model.hotel.vo.ChainResult;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.ChainManager;

@Service("chainManager")
public class ChainManagerImpl extends GenericManagerImpl<Chain, String> implements ChainManager {

	private ChainDao chainDao;

	@Autowired
	public ChainManagerImpl(ChainDao chainDao) {
		super(chainDao);
		this.chainDao = chainDao;
	}

	@Override
	public ChainVO saveChain(ChainVO vo) {
		Chain vo1 = new Chain();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = chainDao.addChain(vo1);
		vo.setChainId(vo1.getChainId());

		if (vo.getChainI18nList() == null || vo.getChainI18nList().size() == 0) {
			List<ChainI18n> i18nList = new ArrayList<ChainI18n>();
			i18nList.add(this.getDefaultLanguageI18n(vo));
			vo.setChainI18nList(i18nList);
		}

		// 循环添加多语言数据
		for (ChainI18n chainI18n : vo.getChainI18nList()) {
			ChainI18n i18n = new ChainI18n();
			i18n.setChainId(vo.getChainId());
			i18n.setLanguage(chainI18n.getLanguage());
			i18n.setChainName(chainI18n.getChainName());
			i18n.setDescription(chainI18n.getDescription());
			chainDao.addChainI18n(i18n);
		}

		return vo;
	}

	@Override
	public void updateChainI18n(ChainVO vo) {

		if (vo.getChainI18nList() != null) {
			// 批量删除多语言记录
			chainDao.deleteChainI18nByChainId(vo.getChainId());

			// 循环添加多语言数据
			for (ChainI18n chainI18n : vo.getChainI18nList()) {
				ChainI18n i18n = new ChainI18n();
				i18n.setChainId(vo.getChainId());
				i18n.setLanguage(chainI18n.getLanguage());
				i18n.setChainName(chainI18n.getChainName());
				i18n.setDescription(chainI18n.getDescription());
				chainDao.addChainI18n(i18n);
			}
		} else {
			ChainI18n i18n = new ChainI18n();
			i18n.setChainId(vo.getChainId());
			i18n.setChainMId(vo.getChainMId());
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setChainName(vo.getChainName());
			i18n.setDescription(vo.getDescription());
			chainDao.updateChainI18n(i18n);
		}

	}

	@Override
	public void deleteChain(ChainVO vo) {
		Chain vo1 = new Chain();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		chainDao.deleteChain(vo1);

		chainDao.deleteChainI18nByChainId(vo.getChainId());
	}

	@Override
	public Chain getChainByCode(String chainCode) {
		return chainDao.getChainByCode(chainCode);
	}

	@Override
	public ChainVO getChainByCode(String chainCode, String language) {
		return chainDao.getChainByCode(chainCode, language);
	}

	@Override
	public ChainVO getChainById(String chainId) {
		return chainDao.getChainById(chainId);
	}

	@Override
	public ChainVO getChainById(String chainId, String language) {
		return chainDao.getChainById(chainId, language);
	}

	@Override
	public ChainResult searchChain(ChainCreteria creteria) {
		ChainResult result = new ChainResult();
		List<ChainVO> voList = chainDao.searchChain(creteria);
		Integer count = chainDao.searchChainCount(creteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}

	@Override
	public List<Chain> getAllChain() {
		return chainDao.getAllChain();
	}

	@Override
	public List<ChainVO> getAllChainI18n(String language) {
		return chainDao.getAllChainI18n(language);
	}

	@Override
	public ChainI18n getDefaultLanguageI18n(ChainVO chainvo) {
		ChainI18n chainI18n = new ChainI18n();
		if (chainvo.getLanguage() == null) {
			chainI18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		} else {
			chainI18n.setLanguage(chainvo.getLanguage());
		}
		chainI18n.setChainName(chainvo.getChainName());
		chainI18n.setDescription(chainvo.getDescription());
		return chainI18n;
	}

	@Override
	public void deleteChainI18nByChainId(String chainId) {
		chainDao.deleteChainI18nByChainId(chainId);
	}

	@Override
	public List<ChainI18n> getChainI18ns(String chainId) {
		return chainDao.getChainI18ns(chainId);
	}

}
