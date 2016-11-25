package com.ccm.mc.webapp.action.ratePlan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.ratePlan.RatePlanTemplateI18n;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateCreteria;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateResult;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateVO;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.ratePlan.RatePlanTemplateManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


public class RatePlanTemplateAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1030512876315891947L;
	
	@Autowired
	private RatePlanTemplateManager ratePlanTemplateManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	
	
	private RatePlanTemplateVO ratePlanTemplateVo;
	private RatePlanTemplateCreteria creteria;
	private RatePlanTemplateResult result;
	private List<RatePlanTemplateVO> roomTypeTempList;
	private List<DictCode> languageList;
	
	/**
	 * 房型模板列表
	 * @return
	 */
	public String list(){
		
		if(null==creteria){
			creteria = new RatePlanTemplateCreteria();
		}
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		creteria.setLanguage(language);
		int pageSize = this.getCurrentPageSize("ratePlanTemp");
		int pageNo = this.getCurrentPageNo("ratePlanTemp");
	
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		result = ratePlanTemplateManager.searchRatePlanTemplate(creteria);
		return "list";
	}
	
	public String toEdit(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//所有多语言字典项
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
		
		if(ratePlanTemplateVo!=null&&StringUtils.isNotBlank(ratePlanTemplateVo.getRatePlanTemplateId())){
			ratePlanTemplateVo = ratePlanTemplateManager.getRatePlanTemplateById(ratePlanTemplateVo.getRatePlanTemplateId(),language);
			
			//设置多语言列表
			List<RatePlanTemplateI18n> ratePlanTemplateI18nList = 
				ratePlanTemplateManager.getRatePlanTemplateI18ns(ratePlanTemplateVo.getRatePlanTemplateId());
			
			if(ratePlanTemplateI18nList!=null&&ratePlanTemplateI18nList.size()>0){
				for (int i = 0; i < ratePlanTemplateI18nList.size(); i++) {
					if(language.equals(ratePlanTemplateI18nList.get(i).getLanguage())){
						ratePlanTemplateI18nList.remove(i);
						break;
					}
				}
			}
			ratePlanTemplateVo.setRatePlanTempI18nList(ratePlanTemplateI18nList);
		}else{
			ratePlanTemplateVo = new RatePlanTemplateVO();
		}
		
		return "toEdit";
	}
	
	
	public String save(){
		
		String ratePlanTemplateI18ns = getRequest().getParameter("f_ratePlanTemplateI18ns");
		
		//创建房型模板多语言列表对象
		List<RatePlanTemplateI18n> ratePlanTemplateI18nList = new ArrayList<RatePlanTemplateI18n>();
		
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			ratePlanTemplateVo.setLanguage(language);
			//将默认语言添加进去
			ratePlanTemplateI18nList.add(ratePlanTemplateManager.getDefaultLanguageI18n(ratePlanTemplateVo));
			
			//组装多语言数据
			if(StringUtils.isNotBlank(ratePlanTemplateI18ns)){
				
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(ratePlanTemplateI18ns);
				for (Map<String, String> i18nMap : i18nMapList) {
					
					//创建多语言对象,并且设置值
					RatePlanTemplateI18n i18n = new RatePlanTemplateI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setRatePlanTemplateName(i18nMap.get("name"));
					i18n.setDescription(i18nMap.get("description"));
					ratePlanTemplateI18nList.add(i18n);
				}
			}
			
			//将多语言设置进去
			ratePlanTemplateVo.setRatePlanTempI18nList(ratePlanTemplateI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(ratePlanTemplateVo!=null&&StringUtils.isNotBlank(ratePlanTemplateVo.getRatePlanTemplateId())){
			ratePlanTemplateManager.updateRoomTypeTempldate(ratePlanTemplateVo);
		}else{
			ratePlanTemplateManager.saveRatePlanTemplate(ratePlanTemplateVo);
		}
		return "save";
	}
	
	

	/**
	 * 删除
	 * @throws IOException 
	 */
	public String delete(){
		ratePlanTemplateVo.setDelFlag(true);
		ratePlanTemplateManager.deleteRoomTypeTempldate(ratePlanTemplateVo);
		
		return "delete";
	}
	
	/**
	 * 验证房价模板代码是否存在
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isRatePlanTempCode() throws IOException{
		String message = "";
		ratePlanTemplateVo = 
			ratePlanTemplateManager.getRatePlanTemplateByCode(ratePlanTemplateVo.getRatePlanTemplateCode());
		if(null==ratePlanTemplateVo){
			message = "true";
		}else{
			message = "false";
		}
		this.getResponse().getWriter().print(message);
	}
	
	
	
	public RatePlanTemplateVO getRatePlanTemplateVo() {
		return ratePlanTemplateVo;
	}
	public void setRatePlanTemplateVo(RatePlanTemplateVO ratePlanTemplateVo) {
		this.ratePlanTemplateVo = ratePlanTemplateVo;
	}
	public RatePlanTemplateCreteria getCreteria() {
		return creteria;
	}
	public void setCreteria(RatePlanTemplateCreteria creteria) {
		this.creteria = creteria;
	}
	public RatePlanTemplateResult getResult() {
		return result;
	}
	public void setResult(RatePlanTemplateResult result) {
		this.result = result;
	}
	public List<RatePlanTemplateVO> getRoomTypeTempList() {
		return roomTypeTempList;
	}
	public void setRoomTypeTempList(List<RatePlanTemplateVO> roomTypeTempList) {
		this.roomTypeTempList = roomTypeTempList;
	}
	public List<DictCode> getLanguageList() {
		return languageList;
	}
	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
}
