package com.ccm.mc.webapp.action.ratePlan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.ratePlan.PackageI18n;
import com.ccm.api.model.ratePlan.vo.PackageCriteria;
import com.ccm.api.model.ratePlan.vo.PackageResult;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.ratePlan.PackageManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 打包服务
 * @author carr
 *
 */
public class PackageAction extends BaseAction {

	private static final long serialVersionUID = -5271890662541825203L;
	
	@Autowired
	private PackageManager packageManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	
	private PackageCriteria criteria;
	private PackageResult result;
	private PackageVO packagevo;
	
	private List<DictCode> languageList;
	
	/**
	 * 打包服务列表显示
	 */
	public String list(){
		if(null==criteria){
			criteria = new PackageCriteria();
		}
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		criteria.setLanguage(language);
		int pageSize = this.getCurrentPageSize("packageList");
		int pageNo = this.getCurrentPageNo("packageList");
		criteria.setPageNum(pageNo);
		criteria.setPageSize(pageSize);
		result = packageManager.searchPackage(criteria);
		
		List<String> calculationList = new ArrayList<String>();
		calculationList.add(getText("ccm.PackageService.ByPerNight"));
		calculationList.add(getText("ccm.PackageService.OnlyCountArrival"));
		calculationList.add(getText("ccm.PackageService.OnlyCountDeparture"));
		calculationList.add(getText("ccm.PackageService.AFewDaysWeek"));
		calculationList.add(getText("ccm.PackageService.SeveralDays"));
		this.getRequest().setAttribute("calculationList", calculationList);
		
		HashMap<String, String> rule = new HashMap<String, String>();
		rule.put("", getText("common.select.plesesSelect"));
		rule.put("1", getText("ccm.PackageService.FixedAmount"));
		rule.put("4", getText("ccm.PackageService.PerPerson"));
		rule.put("5", getText("ccm.PackageService.PerRoom"));
		this.getRequest().setAttribute("rule", rule);
		
		List<String> basicTypeList = new ArrayList<String>();
		basicTypeList.add(getText("ccm.PackageService.ByPercentage"));
		basicTypeList.add(getText("ccm.PackageService.ByConcreteValue"));
		this.getRequest().setAttribute("basicTypeList", basicTypeList);
		
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
		
		
		if(null!=packagevo && null!=packagevo.getPackageId()){
			packagevo = packageManager.getPackageById(packagevo.getPackageId(),language);
			
			//设置多语言列表
			List<PackageI18n> packageI18nList =  packageManager.getPackageI18ns(packagevo.getPackageId());
			
			if(packageI18nList!=null&&packageI18nList.size()>0){
				for (int i = 0; i < packageI18nList.size(); i++) {
					if(language.equals(packageI18nList.get(i).getLanguage())){
						packageI18nList.remove(i);
						break;
					}
				}
			}
			packagevo.setPackageI18nList(packageI18nList);
			
		}else{
			packagevo = new PackageVO();
			packagevo.setIsExtraCharge(true);
		}
		
		List<String> calculationList = new ArrayList<String>();
		calculationList.add(getText("ccm.PackageService.ByPerNight"));
		calculationList.add(getText("ccm.PackageService.OnlyCountArrival"));
		calculationList.add(getText("ccm.PackageService.OnlyCountDeparture"));
		calculationList.add(getText("ccm.PackageService.AFewDaysWeek"));
		calculationList.add(getText("ccm.PackageService.SeveralDays"));
		this.getRequest().setAttribute("calculationList", calculationList);
		
		HashMap<String, String> rule = new HashMap<String, String>();
		rule.put("", getText("common.select.plesesSelect"));
		rule.put("1", getText("ccm.PackageService.FixedAmount"));
		rule.put("4", getText("ccm.PackageService.PerPerson"));
		rule.put("5", getText("ccm.PackageService.PerRoom"));
		this.getRequest().setAttribute("rule", rule);
		
		List<String> basicTypeList = new ArrayList<String>();
		basicTypeList.add(getText("ccm.PackageService.ByPercentage"));
		basicTypeList.add(getText("ccm.PackageService.ByConcreteValue"));
		this.getRequest().setAttribute("basicTypeList", basicTypeList);
		
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){
		
		String packageI18ns = getRequest().getParameter("f_packageI18ns");
		
		//保存多语言列表
		List<PackageI18n> packageI18nList = new ArrayList<PackageI18n>();
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			packagevo.setLanguage(language);
			//将默认语言添加进去
			packageI18nList.add(packageManager.getDefaultLanguageI18n(packagevo));
			//组装多语言数据
			if(StringUtils.isNotBlank(packageI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(packageI18ns);
				
				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					PackageI18n i18n = new PackageI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setPackageName(i18nMap.get("name"));
					i18n.setDescription(i18nMap.get("description"));
					i18n.setMessage(i18nMap.get("message"));
					packageI18nList.add(i18n);
				}
			}
			//将多语言设置进去
			packagevo.setPackageI18nList(packageI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(null!=packagevo && null!=packagevo.getPackageId() && !"".equals(packagevo.getPackageId())){
			packageManager.updatePackage(packagevo);
		}else{
			packageManager.savePackage(packagevo);
		}
		return "save";
	}
	
	

	/**
	 * 验证打包服务代码是否存在
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isPackageCode() throws IOException{
		String message = "";
		packagevo = packageManager.getPackageByCode(packagevo.getPackageCode());
		if(null==packagevo){
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
		packagevo.setDelFlag(true);
		packageManager.deletePackage(packagevo);
		return "delete";
	}
	
	public PackageResult getResult() {
		return result;
	}
	public void setResult(PackageResult result) {
		this.result = result;
	}
	public PackageVO getPackagevo() {
		return packagevo;
	}
	public void setPackagevo(PackageVO packagevo) {
		this.packagevo = packagevo;
	}
	public PackageCriteria getCriteria() {
		return criteria;
	}
	public void setCriteria(PackageCriteria criteria) {
		this.criteria = criteria;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
	
}
