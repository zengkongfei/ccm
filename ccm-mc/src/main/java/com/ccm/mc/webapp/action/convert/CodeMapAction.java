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
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.convert.CodeListMappingManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Jenny
 * 
 */
public class CodeMapAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6791565145522880321L;

	private Log log = LogFactory.getLog(CodeMapAction.class);

	private String dictId;
	private String hotelId;
	private CodeListMapping codeMap = new CodeListMapping();
	private String hotelIdFormHidden;
	private List<CodeListMapping> codeMapList = new ArrayList<CodeListMapping>();

	@Autowired
	private DictCodeManager dictCodeManager;

	@Autowired
	private CodeListMappingManager codeListMappingManager;
	@Resource
	private HotelManager hotelManager;

	public String list() {
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		List<Dictionary> dictionaryList = dictCodeManager.getDictionaryByDictType(DictType.CCM_PMS,language);
		getRequest().setAttribute("dictionary", dictionaryList);

		if (StringUtils.hasText(dictId)) {
			B2BUser user = getCurLoginUser();
			codeMapList = codeListMappingManager.getCodeMapByCodeId(dictId, user.getHotelvo().getHotelId());

		}
		return "list";

	}

	public String showCurChainHotels() {
		HotelVO hvo = getCurLoginUser().getHotelvo();
		if (hvo != null) {
			String chainId = hvo.getChainId();
			this.getRequest().setAttribute("hotelList", hotelManager.getHotelByChainId(chainId));
			hotelId = hvo.getHotelId();
		}
		return "showCurChainHotels";
	}

	public String copyCodeMap() {
		if (!StringUtils.hasText(hotelId)) {
			saveMessage("hotelIsNull");
			return "save";
		}

		B2BUser user = getCurLoginUser();
		if (user != null) {
			String curHotelId = user.getHotelvo().getHotelId();
			if (hotelId.equals(curHotelId)) {
				saveMessage("org2desHotelIdIsSame");
				return "save";
			}
			String curUserId = user.getUserId();
			codeListMappingManager.copyCodeMapByHotelId(hotelId, curHotelId, curUserId);
			dictCodeManager.cleanHashMap(null, curHotelId);
			dictCodeManager.sendWs(null, curHotelId);
		}
		dictId = "1";
		return "save";
	}

	public String edit() {
		log.info(codeMap.getCodeListMappingId());
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
		B2BUser user = getCurLoginUser();
		log.info(user);
		String hotelId = user.getHotelvo().getHotelId();
		codeMap.setHotelId(hotelId);
		CodeListMapping cm = codeListMappingManager.getCodeListMappingByCond(codeMap);
		if (cm != null && !cm.getCodeListMappingId().equals(codeMap.getCodeListMappingId())) {
			saveMessage(getText("ccm.error.088"));
			return "save";
		}
		
		codeListMappingManager.save(codeMap);
		dictCodeManager.cleanHashMap(dictId, hotelId);
		dictCodeManager.sendWs(dictId, hotelId);
		return "save";
	}

	public String delete() {
		log.info(codeMap.getCodeListMappingId());
		codeListMappingManager.remove(codeMap.getCodeListMappingId());
		B2BUser user = getCurLoginUser();
		String hotelId = user.getHotelvo().getHotelId();
		dictCodeManager.cleanHashMap(dictId, hotelId);
		dictCodeManager.sendWs(dictId, hotelId);
		return "save";
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
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

	public String getHotelIdFormHidden() {
		return hotelIdFormHidden;
	}

	public void setHotelIdFormHidden(String hotelIdFormHidden) {
		this.hotelIdFormHidden = hotelIdFormHidden;
	}

}
