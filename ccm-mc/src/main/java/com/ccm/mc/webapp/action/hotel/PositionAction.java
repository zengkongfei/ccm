package com.ccm.mc.webapp.action.hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.hotel.PositionI18n;
import com.ccm.api.model.hotel.vo.PositionCreteria;
import com.ccm.api.model.hotel.vo.PositionResult;
import com.ccm.api.model.hotel.vo.PositionVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.PositionManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


/**
 * 地标
 * @author carr
 *
 */
public class PositionAction extends BaseAction {
	
	private static final long serialVersionUID = -973822326390513832L;

	@Autowired
	private PositionManager positionManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	
	private PositionCreteria creteria;
	private PositionResult result;
	private PositionVO positionvo;
	
	private List<DictCode> languageList;
	
	/**
	 * 地标列表显示
	 */
	public String list(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		if(null==creteria){
			creteria = new PositionCreteria();
		}
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		int pageSize = this.getCurrentPageSize("positionList");
		int pageNo = this.getCurrentPageNo("positionList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		creteria.setHotelId(curHotelId);
		creteria.setLanguageCode(language);
		result = positionManager.searchPosition(creteria);
		return "list";
	}
	
	/**
	 * 创建/编辑
	 */
	public String toEdit(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		languageList = dictCodeManager.searchByDictName(DictName.MORELANGUAGE,language);
		
		//筛选不需要的语言
		String[] languageFilter = {language};
		
		//将中文编码的多语言去除(默认的就是中国)
		for (int i = 0; i < languageList.size(); i++) {
			for (String lf : languageFilter) {
				if(lf.equalsIgnoreCase(languageList.get(i).getCodeNo())){
					languageList.remove(i);
				}
			}
		}
		
		if(null!=positionvo && null!=positionvo.getPositionId()){
			positionvo = positionManager.getPositionById(positionvo.getPositionId(),language);
			
			//设置多语言列表
			List<PositionI18n> positionI18nList =  positionManager.getPositionI18ns(positionvo.getPositionId());
			
			if(positionI18nList!=null&&positionI18nList.size()>0){
				for (int i = 0; i < positionI18nList.size(); i++) {
					if(language.equals(positionI18nList.get(i).getLanguageCode())){
						positionI18nList.remove(i);
						break;
					}
				}
			}
			positionvo.setPositionI18nList(positionI18nList);
			
		}else{
			positionvo = new PositionVO();
		}
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){
		
		String positionI18ns = getRequest().getParameter("f_positionI18ns");
		
		//保存多语言列表
		List<PositionI18n> positionI18nList = new ArrayList<PositionI18n>();
		try {
			//将默认语言添加进去
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			positionvo.setLanguageCode(language);
			positionI18nList.add(positionManager.getDefaultLanguageI18n(positionvo));
			//组装多语言数据
			if(StringUtils.isNotBlank(positionI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(positionI18ns);

				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					PositionI18n i18n = new PositionI18n();
					i18n.setLanguageCode(i18nMap.get("codeNo"));
					i18n.setName(i18nMap.get("name"));
					i18n.setDescription(i18nMap.get("description"));
					positionI18nList.add(i18n);
				}
			}

			//将多语言设置进去
			positionvo.setPositionI18nList(positionI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		positionvo.setHotelId(curHotelId);
		if(null!=positionvo && null!=positionvo.getPositionId() && !"".equals(positionvo.getPositionId())){
			positionManager.updatePosition(positionvo);
		}else{
			positionManager.savePosition(positionvo);
		}
		return "save";
	}
	
	

	/**
	 * 删除
	 * @throws IOException 
	 */
	public String delete(){
		positionvo.setDelFlag(true);
		positionManager.deletePosition(positionvo);
		return "delete";
	}
	
	public PositionCreteria getCreteria() {
		return creteria;
	}
	public void setCreteria(PositionCreteria creteria) {
		this.creteria = creteria;
	}
	public PositionResult getResult() {
		return result;
	}
	public void setResult(PositionResult result) {
		this.result = result;
	}
	public PositionVO getPositionvo() {
		return positionvo;
	}
	public void setPositionvo(PositionVO positionvo) {
		this.positionvo = positionvo;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
}
