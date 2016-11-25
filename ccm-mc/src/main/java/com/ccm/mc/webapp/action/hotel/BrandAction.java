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
import com.ccm.api.model.hotel.BrandI18n;
import com.ccm.api.model.hotel.vo.BrandCreteria;
import com.ccm.api.model.hotel.vo.BrandResult;
import com.ccm.api.model.hotel.vo.BrandVO;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.BrandManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


/**
 * 品牌
 * @author carr
 *
 */
public class BrandAction extends BaseAction {

	private static final long serialVersionUID = -6334118658761756922L;
	
	@Autowired
	private BrandManager brandManager;
	@Autowired
	private ChainManager chainManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	
	private BrandCreteria creteria;
	private BrandResult result;
	private BrandVO brandvo;
	private List<ChainVO> chainvoList;
	private List<DictCode> languageList;
	
	
	/**
	 * 品牌列表显示
	 */
	public String list(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//获取集团
		chainvoList = chainManager.getAllChainI18n(language);
		
		if(null==creteria){
			creteria = new BrandCreteria();
		}
		creteria.setLanguage(language);
		int pageSize = this.getCurrentPageSize("brandList");
		int pageNo = this.getCurrentPageNo("brandList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		result = brandManager.searchBrand(creteria);
		return "list";
	}
	
	/**
	 * 创建/编辑
	 * @param marketvo 
	 */
	public String toEdit(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//获取集团
		chainvoList = chainManager.getAllChainI18n(language);
		
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
		
		if(null!=brandvo && null!=brandvo.getBrandId()){
			brandvo = brandManager.getBrandById(brandvo.getBrandId());
			
			//设置多语言列表
			List<BrandI18n> brandI18nList =  brandManager.getBrandI18ns(brandvo.getBrandId());
			
			if(brandI18nList!=null&&brandI18nList.size()>0){
				for (int i = 0; i < brandI18nList.size(); i++) {
					if(language.equals(brandI18nList.get(i).getLanguage())){
						brandI18nList.remove(i);
						break;
					}
				}
			}
			brandvo.setBrandI18nList(brandI18nList);
		}else{
			brandvo = new BrandVO();
		}
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){
		
		String brandI18ns = getRequest().getParameter("f_brandI18ns");
		
		//保存多语言列表
		List<BrandI18n> brandI18nList = new ArrayList<BrandI18n>();
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			brandvo.setLanguage(language);
			//将默认语言添加进去
			brandI18nList.add(brandManager.getDefaultLanguageI18n(brandvo));
			
			//组装多语言数据
			if(StringUtils.isNotBlank(brandI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(brandI18ns);
				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					BrandI18n i18n = new BrandI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setBrandName(i18nMap.get("name"));
					brandI18nList.add(i18n);
				}
				
			}
			//将多语言设置进去
			brandvo.setBrandI18nList(brandI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(null!=brandvo && null!=brandvo.getBrandId() && !"".equals(brandvo.getBrandId())){
			brandManager.updateBrand(brandvo);
		}else{
			brandManager.saveBrand(brandvo);
		}
		return "save";
	}
	
	

	/**
	 * 验证品牌代码是否存在
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isBrandCode() throws IOException{
		String message = "";
		brandvo = brandManager.getBrandByCode(brandvo.getBrandCode());
		if(null==brandvo){
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
		brandvo.setDelFlag(true);
		brandManager.deleteBrand(brandvo);
		return "delete";
	}
	
	/**
	 * ajax 获取品牌
	 * @throws IOException 
	 */
	public void ajaxGetAllBrandByChainId() throws IOException{
		String chainId=brandvo.getChainId();
		chainId=chainId==null?"0":chainId.trim();
		StringBuffer brandSelect=new StringBuffer("");
		if(!chainId.equals("0")){
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			List<BrandVO> brandList=brandManager.getAllBrandByChainId(chainId,language);
			for(BrandVO vo:brandList){
				brandSelect.append("<option value=\"")
				.append(vo.getBrandId())
				.append("\">")
				.append(vo.getBrandName())
				.append("</option>");
			}
		}
		this.getResponse().getWriter().print(brandSelect.toString());
	}	
	
	public BrandCreteria getCreteria() {
		return creteria;
	}
	public void setCreteria(BrandCreteria creteria) {
		this.creteria = creteria;
	}
	public BrandResult getResult() {
		return result;
	}
	public void setResult(BrandResult result) {
		this.result = result;
	}
	public BrandVO getBrandvo() {
		return brandvo;
	}
	public void setBrandvo(BrandVO brandvo) {
		this.brandvo = brandvo;
	}
	public List<ChainVO> getChainvoList() {
		return chainvoList;
	}
	public void setChainvoList(List<ChainVO> chainvoList) {
		this.chainvoList = chainvoList;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
	
}
