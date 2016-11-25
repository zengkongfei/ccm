package com.ccm.mc.webapp.action.ratePlan;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.ratePlan.GuaranteePolicyI18n;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyCriteria;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyResult;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.ratePlan.GuaranteePolicyManager;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


/**
 * 担保规则
 * @author carr
 *
 */
public class GuaranteePolicyAction extends BaseAction {
	
	private static final long serialVersionUID = 1384285905467703446L;
	
	@Autowired
	private GuaranteePolicyManager guaranteePolicyManager;
	@Autowired
	private HotelMCManager hotelMCManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	
	private GuaranteePolicyCriteria criteria;
	private GuaranteePolicyResult result;
	private GuaranteePolicyVO guaranteePolicyvo;
	private List<HotelVO> hotelList;
	private List<DictCode> languageList;
	
	/**
	 * 担保规则列表显示
	 */
	public String list(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//酒店列表
		hotelList = hotelMCManager.getAllHotelMC(language);
		
		if(null==criteria){
			criteria = new GuaranteePolicyCriteria();
		}
		criteria.setLanguage(language);
		int pageSize = this.getCurrentPageSize("guaranteeList");
		int pageNo = this.getCurrentPageNo("guaranteeList");
		criteria.setPageNum(pageNo);
		criteria.setPageSize(pageSize);
		result = guaranteePolicyManager.searchGuaranteePolicy(criteria);
		
		
		
		HashMap<String, String> guaranteeType = new HashMap<String, String>();
		guaranteeType.put("", getText("common.Unlimited"));
		guaranteeType.put("1", "GuaranteeRequired");
		guaranteeType.put("2", "None");
		guaranteeType.put("3", "CC/DC/Voucher");
		guaranteeType.put("4", "Profile");
		guaranteeType.put("5", "Deposit");
		guaranteeType.put("6", "PrePay");
		guaranteeType.put("7", "DepositRequired");
		guaranteeType.put("8", "CREDIT_CARD");
		guaranteeType.put("9", "CORPORATE");
		guaranteeType.put("10", "GUARANTEE");
		guaranteeType.put("11", "HOLD");
		guaranteeType.put("12", "TRAVEL_AGENT");
		this.getRequest().setAttribute("guaranteeType",guaranteeType);
		
		
		
		return "list";
	}
	
	/**
	 * 创建/编辑
	 */
	public String toEdit(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//酒店列表
		hotelList = hotelMCManager.getAllHotelMC(language);
		
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
		
		if(null!=guaranteePolicyvo && null!=guaranteePolicyvo.getGuaranteeId()){
			guaranteePolicyvo = guaranteePolicyManager.getGuaranteePolicyById(
					guaranteePolicyvo.getGuaranteeId(),language);
			
			//设置多语言列表
			List<GuaranteePolicyI18n> guaranteePolicyI18nList = 
				guaranteePolicyManager.getGuaranteePolicyI18ns(guaranteePolicyvo.getGuaranteeId());
			
			if(guaranteePolicyI18nList!=null&&guaranteePolicyI18nList.size()>0){
				for (int i = 0; i < guaranteePolicyI18nList.size(); i++) {
					if(language.equals(guaranteePolicyI18nList.get(i).getLanguage())){
						guaranteePolicyI18nList.remove(i);
						break;
					}
				}
			}
			guaranteePolicyvo.setGuaranteePolicyI18nList(guaranteePolicyI18nList);
			
		}else{
			guaranteePolicyvo = new GuaranteePolicyVO();
		}
		HashMap<String, String> guaranteeType = new HashMap<String, String>();
		guaranteeType.put("", getText("common.select.plesesSelect"));
		guaranteeType.put("1", "GuaranteeRequired");
		guaranteeType.put("2", "None");
		guaranteeType.put("3", "CC/DC/Voucher");
		guaranteeType.put("4", "Profile");
		guaranteeType.put("5", "Deposit");
		guaranteeType.put("6", "PrePay");
		guaranteeType.put("7", "DepositRequired");
		guaranteeType.put("8", "CREDIT_CARD");
		guaranteeType.put("9", "CORPORATE");
		guaranteeType.put("10", "GUARANTEE");
		guaranteeType.put("11", "HOLD");
		guaranteeType.put("12", "TRAVEL_AGENT");
		this.getRequest().setAttribute("guaranteeType",guaranteeType);
		
		HashMap<String, String> retributionType = new HashMap<String, String>();
		retributionType.put("", getText("common.select.plesesSelect"));
		retributionType.put("1", "Res Automatically Cancelled");
		retributionType.put("2", "Res No Longer Guaranteed");
		this.getRequest().setAttribute("retributionType",retributionType);
		
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 * @throws ParseException 
	 */
	public String save() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date holdTime = null;
		Date validTime = null;
		if(null!=guaranteePolicyvo){
			//设置日期
			String holdTimeStr = guaranteePolicyvo.getHoldTimeStr();
			String validTimeStr = guaranteePolicyvo.getValidTimeStr();
			if(null!=holdTimeStr && !"".equals(holdTimeStr)){
				holdTime = sdf.parse(holdTimeStr);
			}
			if(null!=validTimeStr && !"".equals(validTimeStr)){
				validTime = DateUtil.convertStringToDate("HH:mm:ss", validTimeStr);
			}
			guaranteePolicyvo.setHoldTime(holdTime);
			guaranteePolicyvo.setValidTime(validTime);
			
			String guaranteePolicyI18ns = getRequest().getParameter("f_guaranteePolicyI18ns");

			//保存多语言列表
			List<GuaranteePolicyI18n> guaranteePolicyI18nList = new ArrayList<GuaranteePolicyI18n>();
			try {
				Locale locale = ActionContext.getContext().getLocale();
				String language = locale.getLanguage()+"_"+locale.getCountry();
				guaranteePolicyvo.setLanguage(language);
				//将默认语言添加进去
				guaranteePolicyI18nList.add(guaranteePolicyManager.getDefaultLanguageI18n(guaranteePolicyvo));
				//组装多语言数据
				if(StringUtils.isNotBlank(guaranteePolicyI18ns)){
					List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(guaranteePolicyI18ns);

					for (Map<String, String> i18nMap : i18nMapList) {
						//创建多语言对象,并且设置值
						GuaranteePolicyI18n i18n = new GuaranteePolicyI18n();
						i18n.setLanguage(i18nMap.get("codeNo"));
						i18n.setPolicyName(i18nMap.get("name"));
						i18n.setDescription(i18nMap.get("description"));
						guaranteePolicyI18nList.add(i18n);
					}
				}
				//将多语言设置进去
				guaranteePolicyvo.setGuaranteePolicyI18nList(guaranteePolicyI18nList);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(null!=guaranteePolicyvo.getGuaranteeId() && !"".equals(guaranteePolicyvo.getGuaranteeId())){
				guaranteePolicyManager.updateGuaranteePolicy(guaranteePolicyvo);
			}else{
				guaranteePolicyManager.saveGuaranteePolicy(guaranteePolicyvo);
			}
		}
		HashMap<String, String> guaranteeType = new HashMap<String, String>();
		guaranteeType.put("", getText("common.Unlimited"));
		guaranteeType.put("1", "GuaranteeRequired");
		guaranteeType.put("2", "None");
		guaranteeType.put("3", "CC/DC/Voucher");
		guaranteeType.put("4", "Profile");
		guaranteeType.put("5", "Deposit");
		guaranteeType.put("6", "PrePay");
		guaranteeType.put("7", "DepositRequired");
		guaranteeType.put("8", "CREDIT_CARD");
		guaranteeType.put("9", "CORPORATE");
		guaranteeType.put("10", "GUARANTEE");
		guaranteeType.put("11", "HOLD");
		guaranteeType.put("12", "TRAVEL_AGENT");
		this.getRequest().setAttribute("guaranteeType",guaranteeType);
		return "save";
	}
	
	

	/**
	 * 验证担保规则代码是否存在
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isGuaranteeCode() throws IOException{
		String message = "";
		guaranteePolicyvo = guaranteePolicyManager.getGuaranteePolicyByCode(guaranteePolicyvo.getGuaranteeCode());
		if(null==guaranteePolicyvo){
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
		guaranteePolicyvo.setDelFlag(true);
		guaranteePolicyManager.deleteGuaranteePolicy(guaranteePolicyvo);
		return "delete";
	}
	
	public GuaranteePolicyResult getResult() {
		return result;
	}
	public void setResult(GuaranteePolicyResult result) {
		this.result = result;
	}
	public GuaranteePolicyVO getGuaranteePolicyvo() {
		return guaranteePolicyvo;
	}
	public void setGuaranteePolicyvo(GuaranteePolicyVO guaranteePolicyvo) {
		this.guaranteePolicyvo = guaranteePolicyvo;
	}
	public GuaranteePolicyCriteria getCriteria() {
		return criteria;
	}
	public void setCriteria(GuaranteePolicyCriteria criteria) {
		this.criteria = criteria;
	}
	public List<HotelVO> getHotelList() {
		return hotelList;
	}
	public void setHotelList(List<HotelVO> hotelList) {
		this.hotelList = hotelList;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
	
}
