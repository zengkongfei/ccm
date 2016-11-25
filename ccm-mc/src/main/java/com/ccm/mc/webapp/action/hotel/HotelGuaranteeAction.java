package com.ccm.mc.webapp.action.hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.HotelGuaranteeI18n;
import com.ccm.api.model.hotel.vo.HotelGuaranteeCreteria;
import com.ccm.api.model.hotel.vo.HotelGuaranteeResult;
import com.ccm.api.model.hotel.vo.HotelGuaranteeVO;
import com.ccm.api.model.ratePlan.GuaranteePolicyI18n;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.HotelGuaranteeManager;
import com.ccm.api.service.ratePlan.GuaranteePolicyManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class HotelGuaranteeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 620101970059458600L;
	
	@Autowired
	private HotelGuaranteeManager hotelGuaranteeManager;
	
	@Autowired
	private GuaranteePolicyManager guaranteePolicyManager;
	
	@Autowired
	private DictCodeManager dictCodeManager;
	
	private HotelGuaranteeVO hotelGuaranteeVo;
	
	private List<GuaranteePolicyVO> guaranteePolicyList;
	
	private HotelGuaranteeResult result;
	
	private HotelGuaranteeCreteria creteria;
	
	private List<DictCode> languageList;
	
	/**
	 * 酒店取消规则列表
	 */
	public String list(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		guaranteePolicyList = guaranteePolicyManager.getAllGuaranteePolicys(language);
		
		if(hotelGuaranteeVo==null){
			hotelGuaranteeVo = new HotelGuaranteeVO();
		}
		
		if(creteria==null){
			creteria = new HotelGuaranteeCreteria();
		}
		creteria.setLanguage(language);
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		hotelGuaranteeVo.setHotelId(curHotelId);
		
		int pageSize = this.getCurrentPageSize("hotelGuaranteeList");
		int pageNo = this.getCurrentPageNo("hotelGuaranteeList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		creteria.setHotelId(curHotelId);
		result = hotelGuaranteeManager.searchHotelGuarantee(creteria);
		return "list";
	}
	
	/**
	 * 创建
	 * @param hotelCancelVo 
	 */
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
		
		if(hotelGuaranteeVo!=null&&StringUtils.isNotBlank(hotelGuaranteeVo.getHotelGuaranteeId())){
			guaranteePolicyList = guaranteePolicyManager.getAllGuaranteePolicys(language);
			hotelGuaranteeVo = hotelGuaranteeManager.getHotelGuaranteeById(hotelGuaranteeVo.getHotelGuaranteeId(),language);
			//设置多语言列表
			List<HotelGuaranteeI18n> hotelGuaranteeI18nList = 
				hotelGuaranteeManager.getHotelGuaranteeI18ns(hotelGuaranteeVo.getHotelGuaranteeId());
		
			if(hotelGuaranteeI18nList!=null&&hotelGuaranteeI18nList.size()>0){
				for (int i = 0; i < hotelGuaranteeI18nList.size(); i++) {
					if(language.equals(hotelGuaranteeI18nList.get(i).getLanguage())){
						hotelGuaranteeI18nList.remove(i);
						break;
					}
				}
			}
			hotelGuaranteeVo.setHotelGuaranteeI18nList(hotelGuaranteeI18nList);
		}else{
			//获取没有被当前酒店绑定的包价
			guaranteePolicyList = this.loadDontUseGuarantee();
			hotelGuaranteeVo = new HotelGuaranteeVO();
		}
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){
		String hotelGuaranteeI18ns = getRequest().getParameter("f_hotelGuaranteeI18ns");
		List<HotelGuaranteeI18n> hotelGuaranteeI18nList = new ArrayList<HotelGuaranteeI18n>();
		
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			hotelGuaranteeVo.setLanguage(language);
			hotelGuaranteeI18nList.add(hotelGuaranteeManager.getDefaultLanguageI18n(hotelGuaranteeVo));
			
			//组装多语言数据
			if(StringUtils.isNotBlank(hotelGuaranteeI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(hotelGuaranteeI18ns);
				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					HotelGuaranteeI18n i18n = new HotelGuaranteeI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setPolicyName(i18nMap.get("name"));
					i18n.setDescription(i18nMap.get("description"));
					hotelGuaranteeI18nList.add(i18n);
				}
			}
			hotelGuaranteeVo.setHotelGuaranteeI18nList(hotelGuaranteeI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		hotelGuaranteeVo.setHotelId(curHotelId);
		
		if(hotelGuaranteeVo!=null&&StringUtils.isNotBlank(hotelGuaranteeVo.getHotelGuaranteeId())){
			hotelGuaranteeManager.updateHotelGuarantee(hotelGuaranteeVo);
		}else{
			hotelGuaranteeManager.saveHotelGuarantee(hotelGuaranteeVo);
		}
		return "save";
	}
	
	/**
	 * 删除
	 * @throws IOException 
	 */
	public String delete(){
		if(hotelGuaranteeVo!=null&&StringUtils.isNotBlank(hotelGuaranteeVo.getHotelGuaranteeId())){
			hotelGuaranteeManager.deleteHotelGuaranteeById(hotelGuaranteeVo);
			hotelGuaranteeManager.deleteHotelGuaranteeI18nById(hotelGuaranteeVo.getHotelGuaranteeId());
		}
		return "delete";
	}
	
	/**
	 * 获取担保规则信息
	 * @return
	 * @throws IOException 
	 */
	public void loadGuaranteeInfo() throws IOException{
		String message = "{policyName:'',description:''}";
		String guaranteeId = getRequest().getParameter("guaranteeId");
		GuaranteePolicyVO vo = guaranteePolicyManager.getGuaranteePolicyById(guaranteeId);
		if(vo!=null){
			message = "{policyName:'"+vo.getPolicyName()+"',description:'"+vo.getDescription()+"'}";
		}
		
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 获取担保规则信息
	 * @return
	 * @throws IOException 
	 */
	public void ajaxloadGuaranteeInfo() throws IOException{
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		StringBuffer message = new StringBuffer("");
		String guaranteeId = getRequest().getParameter("guaranteeId");
		List<GuaranteePolicyI18n> guaranteePolicyI18ns = guaranteePolicyManager.getGuaranteePolicyI18ns(guaranteeId);
		
		if(guaranteePolicyI18ns!=null&&guaranteePolicyI18ns.size()>0){
			message.append("[");
			
			for (int i = 0; i < guaranteePolicyI18ns.size(); i++) {
				GuaranteePolicyI18n i18n = guaranteePolicyI18ns.get(i);
				String isDefault = "No";
				//如果与当前的默认语言相同,标识一下
				if(language.equals(i18n.getLanguage())){
					isDefault = "Yes";
				}
				
				message.append("{language:'"+this.setStringValue(i18n.getLanguage())+"',policyName:'"+this.setStringValue(i18n.getPolicyName())
						+"',description:'"+this.setStringValue(i18n.getDescription())+"',isDefault:'"+isDefault+"'}");
				if(i<guaranteePolicyI18ns.size()-1){
					message.append(",");
				}
			}
			message.append("]");
		}
		
		this.getResponse().getWriter().print(message.toString());
	}
	
	/**
	 * 验证是否都已经绑定了所有的担保规则
	 * @throws IOException 
	 */
	public void isNotHaveGuarantee() throws IOException{
		String message = "true";
		List<GuaranteePolicyVO> gList = this.loadDontUseGuarantee();
		if(gList==null||gList.size()==0){
			message = "false";
		}
		this.getResponse().getWriter().print(message);
	}
	/**
	 * 获取当前酒店没有利用的担保规则
	 * @return
	 */
	private List<GuaranteePolicyVO> loadDontUseGuarantee() {
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		return hotelGuaranteeManager.getDontUseHotelGuarantee(curHotelId,language);
	}

	/**
	 * 验证酒店担保规则关系是否已经绑定
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isHotelGuarantee() throws IOException{
		String message = "true";
		//如果传入的包ID不为空
		if(StringUtils.isNotBlank(hotelGuaranteeVo.getGuaranteeId())){
			String[] guaranteeIdArry = hotelGuaranteeVo.getGuaranteeId().split(",");
			//得到当前酒店ID
			B2BUser user = getCurLoginUser();
			String hotelId = user.getHotelvo().getHotelId();
			
			for (String guaranteeId : guaranteeIdArry) {
				HotelGuaranteeVO hgvo = new HotelGuaranteeVO();
				hgvo.setHotelId(hotelId);
				hgvo.setGuaranteeId(guaranteeId);
				List<HotelGuaranteeVO> list = hotelGuaranteeManager.getHotelGuaranteeByObj(hgvo);
				if(list!=null&&list.size()>0){
					message = "false#"+list.get(0).getGuaranteeCode();
					break;
				}
			}
			
		}
	
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 删除担保规则前验证是否已经被其他东东引用了
	 * @throws IOException 
	 */
	public void validateDeleteGuarantee() throws IOException{
		String message = "{result:'success'}";
		boolean flag = true;  //标示可以往下执行
		
		HotelGuaranteeVO hgvo = hotelGuaranteeManager.getHotelGuaranteeById(hotelGuaranteeVo.getHotelGuaranteeId());
		//执行正常
		if(flag){
			List<Rateplan> ratePlanList = hotelGuaranteeManager.getUseRateGuarantee(hgvo.getHotelId(), hgvo.getGuaranteeId());
			if(ratePlanList!=null&&ratePlanList.size()>0){
				message = "{result:'fail',type:'rateplan',gcode:'"+hgvo.getGuaranteeCode()+"',code:'"+ratePlanList.get(0).getRatePlanCode()+"'}";
			}
		}
		
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 设置String类型的字符串
	 * @param sring
	 * @return
	 */
	private String setStringValue(String str){
		if(StringUtils.isBlank(str)){
			return "";
		}
		return str;
	}

	public HotelGuaranteeVO getHotelGuaranteeVo() {
		return hotelGuaranteeVo;
	}

	public void setHotelGuaranteeVo(HotelGuaranteeVO hotelGuaranteeVo) {
		this.hotelGuaranteeVo = hotelGuaranteeVo;
	}

	public List<GuaranteePolicyVO> getGuaranteePolicyList() {
		return guaranteePolicyList;
	}

	public void setGuaranteePolicyList(List<GuaranteePolicyVO> guaranteePolicyList) {
		this.guaranteePolicyList = guaranteePolicyList;
	}

	public HotelGuaranteeResult getResult() {
		return result;
	}

	public void setResult(HotelGuaranteeResult result) {
		this.result = result;
	}

	public HotelGuaranteeCreteria getCreteria() {
		return creteria;
	}

	public void setCreteria(HotelGuaranteeCreteria creteria) {
		this.creteria = creteria;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
}
