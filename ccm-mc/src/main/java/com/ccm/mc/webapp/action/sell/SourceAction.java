package com.ccm.mc.webapp.action.sell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.sell.SourceI18n;
import com.ccm.api.model.sell.vo.SourceCreteria;
import com.ccm.api.model.sell.vo.SourceResult;
import com.ccm.api.model.sell.vo.SourceVO;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.sell.SourceManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


/**
 * 来源
 * @author carr
 *
 */
public class SourceAction extends BaseAction {

	private static final long serialVersionUID = 606977878028278371L;

	@Autowired
	private SourceManager sourceManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	
	private SourceCreteria creteria;
	private SourceResult result;
	private SourceVO sourcevo;
	private List<DictCode> languageList;
	
	/**
	 * 来源列表显示
	 */
	public String list(){
		if(null==creteria){
			creteria = new SourceCreteria();
		}
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		int pageSize = this.getCurrentPageSize("sourceList");
		int pageNo = this.getCurrentPageNo("sourceList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		creteria.setLanguage(language);
		result = sourceManager.searchSource(creteria);
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
		
		
		if(null!=sourcevo && null!=sourcevo.getSourceId()){
			sourcevo = sourceManager.getSourceById(sourcevo.getSourceId(),language);
			
			//设置多语言列表
			List<SourceI18n> sourceI18nList =  sourceManager.getSourceI18ns(sourcevo.getSourceId());
			
			if(sourceI18nList!=null&&sourceI18nList.size()>0){
				for (int i = 0; i < sourceI18nList.size(); i++) {
					if(language.equals(sourceI18nList.get(i).getLanguage())){
						sourceI18nList.remove(i);
						break;
					}
				}
			}
			sourcevo.setSourceI18nList(sourceI18nList);
			
		}else{
			sourcevo = new SourceVO();
		}
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){
		
		String sourceI18ns = getRequest().getParameter("f_sourceI18ns");
		//保存多语言列表
		List<SourceI18n> sourceI18nList = new ArrayList<SourceI18n>();
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			sourcevo.setLanguage(language);
			//将默认语言添加进去
			sourceI18nList.add(sourceManager.getDefaultLanguageI18n(sourcevo));
			
			//组装多语言数据
			if(StringUtils.isNotBlank(sourceI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(sourceI18ns);
				
				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					SourceI18n i18n = new SourceI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setDescription(i18nMap.get("description"));
					sourceI18nList.add(i18n);
				}
			}
			
			//将多语言设置进去
			sourcevo.setSourceI18nList(sourceI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(null!=sourcevo && null!=sourcevo.getSourceId() && !"".equals(sourcevo.getSourceId())){
			sourceManager.updateSource(sourcevo);
		}else{
			sourceManager.saveSource(sourcevo);
		}
		return "save";
	}
	
	

	/**
	 * 验证来源代码是否存在
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isSourceCode() throws IOException{
		String message = "";
		sourcevo = sourceManager.getSourceByCode(sourcevo.getSourceCode());
		if(null==sourcevo){
			message = "true";
		}else{
			message = "false";
		}
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 删除
	 * @throws IOException 
	 */
	public String delete(){
		sourcevo.setDelFlag(true);
		sourceManager.deleteSource(sourcevo);
		return "delete";
	}
	
	public SourceCreteria getCreteria() {
		return creteria;
	}
	public void setCreteria(SourceCreteria creteria) {
		this.creteria = creteria;
	}
	public SourceResult getResult() {
		return result;
	}
	public void setResult(SourceResult result) {
		this.result = result;
	}
	public SourceVO getSourcevo() {
		return sourcevo;
	}
	public void setSourcevo(SourceVO sourcevo) {
		this.sourcevo = sourcevo;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
}
