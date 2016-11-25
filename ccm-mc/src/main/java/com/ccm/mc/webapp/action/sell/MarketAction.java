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
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.sell.MarketI18n;
import com.ccm.api.model.sell.vo.MarketCreteria;
import com.ccm.api.model.sell.vo.MarketResult;
import com.ccm.api.model.sell.vo.MarketVO;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.sell.MarketManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


/**
 * 市场
 * @author carr
 *
 */
public class MarketAction extends BaseAction {

	private static final long serialVersionUID = 606977878028278371L;

	@Autowired
	private MarketManager marketManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	
	private MarketCreteria creteria;
	private MarketResult result;
	private MarketVO marketvo;
	private List<DictCode> languageList;
	
	/**
	 * 市场列表显示
	 */
	public String list(){
		if(null==creteria){
			creteria = new MarketCreteria();
		}
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		int pageSize = this.getCurrentPageSize("marketList");
		int pageNo = this.getCurrentPageNo("marketList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		creteria.setLanguage(language);
		result = marketManager.searchMarket(creteria);
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
		
		if(null!=marketvo && null!=marketvo.getMarketId()){
			marketvo = marketManager.getMarketById(marketvo.getMarketId());
			
			//设置多语言列表
			List<MarketI18n> marketI18nList =  marketManager.getMarketI18ns(marketvo.getMarketId());
			
			if(marketI18nList!=null&&marketI18nList.size()>0){
				for (int i = 0; i < marketI18nList.size(); i++) {
					if(language.equals(marketI18nList.get(i).getLanguage())){
						marketI18nList.remove(i);
						break;
					}
				}
			}
			marketvo.setMarketI18nList(marketI18nList);
			
		}else{
			marketvo = new MarketVO();
		}
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){
		
		String marketI18ns = getRequest().getParameter("f_marketI18ns");
		//保存多语言列表
		List<MarketI18n> marketI18nList = new ArrayList<MarketI18n>();
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			marketvo.setLanguage(language);
			//将默认语言添加进去
			marketI18nList.add(marketManager.getDefaultLanguageI18n(marketvo));
			//组装多语言数据
			if(StringUtils.isNotBlank(marketI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(marketI18ns);

				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					MarketI18n i18n = new MarketI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setDescription(i18nMap.get("description"));
					marketI18nList.add(i18n);
				}
				
			}
			//将多语言设置进去
			marketvo.setMarketI18nList(marketI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(null!=marketvo && null!=marketvo.getMarketId() && !"".equals(marketvo.getMarketId())){
			marketManager.updateMarket(marketvo);
		}else{
			marketManager.saveMarket(marketvo);
		}
		return "save";
	}
	
	

	/**
	 * 验证市场代码是否存在
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isMarketCode() throws IOException{
		String message = "";
		marketvo = marketManager.getMarketByCode(marketvo.getMarketCode());
		if(null==marketvo){
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
		marketvo.setDelFlag(true);
		marketManager.deleteMarket(marketvo);
		return "delete";
	}
	
	public MarketCreteria getCreteria() {
		return creteria;
	}
	public void setCreteria(MarketCreteria creteria) {
		this.creteria = creteria;
	}
	public MarketResult getResult() {
		return result;
	}
	public void setResult(MarketResult result) {
		this.result = result;
	}
	public MarketVO getMarketvo() {
		return marketvo;
	}
	public void setMarketvo(MarketVO marketvo) {
		this.marketvo = marketvo;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
	
}
