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
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.ChainI18n;
import com.ccm.api.model.hotel.vo.ChainCreteria;
import com.ccm.api.model.hotel.vo.ChainResult;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


/**
 * 集团
 * @author carr
 *
 */
public class ChainAction extends BaseAction {

	private static final long serialVersionUID = 2167584660562066440L;

	@Autowired
	private ChainManager chainManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	
	private ChainCreteria creteria;
	private ChainResult result;
	private ChainVO chainvo;
	private List<DictCode> languageList;
	
	/**
	 * 集团列表显示
	 */
	public String list(){
		if(null==creteria){
			creteria = new ChainCreteria();
		}
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		int pageSize = this.getCurrentPageSize("chainList");
		int pageNo = this.getCurrentPageNo("chainList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		creteria.setLanguage(language);
		result = chainManager.searchChain(creteria);
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
		
		if(null!=chainvo && null!=chainvo.getChainId()){
			chainvo = chainManager.getChainById(chainvo.getChainId(),language);
			
			//设置多语言列表
			List<ChainI18n> chainI18nList =  chainManager.getChainI18ns(chainvo.getChainId());
			
			if(chainI18nList!=null&&chainI18nList.size()>0){
				for (int i = 0; i < chainI18nList.size(); i++) {
					if(language.equals(chainI18nList.get(i).getLanguage())){
						chainI18nList.remove(i);
						break;
					}
				}
			}
			chainvo.setChainI18nList(chainI18nList);
			
		}else{
			chainvo = new ChainVO();
		}
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){
		
		String chainI18ns = getRequest().getParameter("f_chainI18ns");
		
		//chainI18ns = CommonUtil.escapeAcutes(chainI18ns);
		//保存多语言列表
		List<ChainI18n> chainI18nList = new ArrayList<ChainI18n>();
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			chainvo.setLanguage(language);
			chainI18nList.add(chainManager.getDefaultLanguageI18n(chainvo));
			
			//组装多语言数据
			if(StringUtils.isNotBlank(chainI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(chainI18ns);

				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					ChainI18n i18n = new ChainI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setChainName(i18nMap.get("name"));
					i18n.setDescription(i18nMap.get("description"));
					chainI18nList.add(i18n);
				}
			}
			
			//将多语言设置进去
			chainvo.setChainI18nList(chainI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(null!=chainvo && null!=chainvo.getChainId() && !"".equals(chainvo.getChainId())){
			chainManager.updateChainI18n(chainvo);
		}else{
			chainManager.saveChain(chainvo);
		}
		return "save";
	}
	
	

	/**
	 * 验证集团代码是否存在
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isChainCode() throws IOException{
		String message = "";
		Chain c = chainManager.getChainByCode(chainvo.getChainCode());
		if (null == c) {
			message = "true";
		} else {
			message = "false";
		}
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 删除
	 * @throws IOException 
	 */
	public String delete(){
		chainvo.setDelFlag(true);
		chainManager.deleteChain(chainvo);
		return "delete";
	}
	
	public ChainCreteria getCreteria() {
		return creteria;
	}
	public void setCreteria(ChainCreteria creteria) {
		this.creteria = creteria;
	}
	public ChainResult getResult() {
		return result;
	}
	public void setResult(ChainResult result) {
		this.result = result;
	}
	public ChainVO getChainvo() {
		return chainvo;
	}
	public void setChainvo(ChainVO chainvo) {
		this.chainvo = chainvo;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
	
}
