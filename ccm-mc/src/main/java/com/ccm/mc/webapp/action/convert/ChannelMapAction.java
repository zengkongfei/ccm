/**
 * 
 */
package com.ccm.mc.webapp.action.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.common.Dictionary;
import com.ccm.api.model.constant.DictType;
import com.ccm.api.model.convert.CodeListMapping;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.convert.CodeListMappingManager;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Jenny
 * 
 */
public class ChannelMapAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6791565145522880321L;

	private Log log = LogFactory.getLog(ChannelMapAction.class);

	private String dictId;
	private CodeListMapping codeMap = new CodeListMapping();

	private List<CodeListMapping> codeMapList = new ArrayList<CodeListMapping>();

	@Autowired
	private DictCodeManager dictCodeManager;
	@Autowired
	private CodeListMappingManager codeListMappingManager;
	@Resource
	private ChannelManager channelManager;

	public String list() {
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		List<Dictionary> dictionaryList = dictCodeManager.getDictionaryByDictType(DictType.CCM_CHANNEL,language);
		getRequest().setAttribute("dictionary", dictionaryList);

		if (StringUtils.hasText(dictId)) {
			log.info(dictId);
			codeMapList = codeListMappingManager.getCodeMapByCodeId(dictId, null);

		}
		return "list";

	}

	public String edit() {
		log.info(codeMap.getCodeListMappingId());
		getRequest().setAttribute("channelList", channelManager.getAll());
		if (StringUtils.hasText(dictId)) {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			B2BUser user = getCurLoginUser();
			String hotelId = user.getHotelvo().getHotelId();
			List<DictCode> dcList = dictCodeManager.getDictCodeByDictId(dictId, hotelId,language);
			getRequest().setAttribute("dictCode", dcList);
		}
		if (StringUtils.hasText(codeMap.getCodeListMappingId())) {
			codeMap = codeListMappingManager.get(codeMap.getCodeListMappingId());
		}
		return "edit";
	}

	public String save() {
		log.info(codeMap);
		if (!StringUtils.hasText(codeMap.getChannelId())) {
			saveMessage(getText("ccm.RestrictionsManagement.error.channelNull"));
			return "save";
		}
		CodeListMapping cm = codeListMappingManager.getCodeListMappingByCond(codeMap);
		if (cm != null && !cm.getCodeListMappingId().equals(codeMap.getCodeListMappingId())) {
			saveMessage(getText("ccm.error.088"));
			return "save";
		}
		codeMap.setCcm2external("1");
		codeMap.setExternal2ccm("1");
		codeListMappingManager.save(codeMap);
		dictCodeManager.cleanHashMap(dictId, codeMap.getChannelId());
		dictCodeManager.sendWs(dictId, codeMap.getChannelId());
		return "save";
	}

	public String delete() {
		log.info(codeMap.getCodeListMappingId());
		codeListMappingManager.remove(codeMap.getCodeListMappingId());
		dictCodeManager.cleanHashMap(dictId, codeMap.getChannelId());
		dictCodeManager.sendWs(dictId, codeMap.getChannelId());
		return "save";
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public CodeListMapping getCodeMap() {
		return codeMap;
	}

	public void setCodeMap(CodeListMapping codeMap) {
		this.codeMap = codeMap;
	}

	public List<CodeListMapping> getCodeMapList() {
		return codeMapList;
	}

	public void setCodeMapList(List<CodeListMapping> codeMapList) {
		this.codeMapList = codeMapList;
	}

}
