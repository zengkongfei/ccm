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
import com.ccm.api.model.hotel.HotelCancelI18n;
import com.ccm.api.model.hotel.vo.HotelCancelCreteria;
import com.ccm.api.model.hotel.vo.HotelCancelResult;
import com.ccm.api.model.hotel.vo.HotelCancelVO;
import com.ccm.api.model.ratePlan.CancelPolicyI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.HotelCancelManager;
import com.ccm.api.service.ratePlan.CancelPolicyManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class HotelCancelAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 620101970059458600L;
	
	@Autowired
	private HotelCancelManager hotelCancelManager;
	
	@Autowired
	private CancelPolicyManager cancelPolicyManager;
	
	@Autowired
	private DictCodeManager dictCodeManager;
	
	private HotelCancelVO hotelCancelVo;
	
	private List<CancelPolicyVO> cancelPolicyList;
	
	private HotelCancelResult result;
	
	private HotelCancelCreteria creteria;
	
	private List<DictCode> languageList;
	
	/**
	 * 酒店取消规则列表
	 */
	public String list(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		cancelPolicyList = cancelPolicyManager.getAllCancelPolicys(language);
		
		if(hotelCancelVo==null){
			hotelCancelVo = new HotelCancelVO();
		}
		
		if(creteria==null){
			creteria = new HotelCancelCreteria();
		}
		creteria.setLanguage(language);
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		hotelCancelVo.setHotelId(curHotelId);
		
		int pageSize = this.getCurrentPageSize("hotelCancelList");
		int pageNo = this.getCurrentPageNo("hotelCancelList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		creteria.setHotelId(curHotelId);
		result = hotelCancelManager.searchHotelCancel(creteria);
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
		
		if(hotelCancelVo!=null&&StringUtils.isNotBlank(hotelCancelVo.getHotelCancelId())){
			cancelPolicyList = cancelPolicyManager.getAllCancelPolicys(language);
			hotelCancelVo = hotelCancelManager.getHotelCancelById(hotelCancelVo.getHotelCancelId(),language);
			//设置多语言列表
			List<HotelCancelI18n> hotelCancelI18nList = 
				hotelCancelManager.getHotelCancelI18ns(hotelCancelVo.getHotelCancelId());
		
			if(hotelCancelI18nList!=null&&hotelCancelI18nList.size()>0){
				for (int i = 0; i < hotelCancelI18nList.size(); i++) {
					if(language.equals(hotelCancelI18nList.get(i).getLanguage())){
						hotelCancelI18nList.remove(i);
						break;
					}
				}
			}
			hotelCancelVo.setHotelCancelI18nList(hotelCancelI18nList);
		}else{
			//获取没有被当前酒店绑定的包价
			cancelPolicyList = this.loadDontUseCancel();
			hotelCancelVo = new HotelCancelVO();
		}
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){
		String hotelCancelI18ns = getRequest().getParameter("f_hotelCancelI18ns");
		List<HotelCancelI18n> hotelCancelI18nList = new ArrayList<HotelCancelI18n>();
		
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			hotelCancelVo.setLanguage(language);
			hotelCancelI18nList.add(hotelCancelManager.getDefaultLanguageI18n(hotelCancelVo));
			
			//组装多语言数据
			if(StringUtils.isNotBlank(hotelCancelI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(hotelCancelI18ns);
				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					HotelCancelI18n i18n = new HotelCancelI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setPolicyName(i18nMap.get("name"));
					i18n.setDescription(i18nMap.get("description"));
					hotelCancelI18nList.add(i18n);
				}
			}
			hotelCancelVo.setHotelCancelI18nList(hotelCancelI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		hotelCancelVo.setHotelId(curHotelId);
		
		if(hotelCancelVo!=null&&StringUtils.isNotBlank(hotelCancelVo.getHotelCancelId())){
			hotelCancelManager.updateHotelCancel(hotelCancelVo);
		}else{
			hotelCancelManager.saveHotelCancel(hotelCancelVo);
		}
		return "save";
	}
	
	/**
	 * 删除
	 * @throws IOException 
	 */
	public String delete(){
		if(hotelCancelVo!=null&&StringUtils.isNotBlank(hotelCancelVo.getHotelCancelId())){
			hotelCancelManager.deleteHotelCancelById(hotelCancelVo);
			hotelCancelManager.deleteHotelCancelI18nById(hotelCancelVo.getHotelCancelId());
		}
		return "delete";
	}
	
	
	/**
	 * 获取取消规则信息
	 * @return
	 * @throws IOException 
	 */
	public void loadCanelInfo() throws IOException{
		String message = "{policyName:'',description:''}";
		String cancelId = getRequest().getParameter("cancelId");
		
		CancelPolicyVO vo = cancelPolicyManager.getCancelPolicyById(cancelId);
		if(vo!=null){
			message = "{policyName:'"+vo.getPolicyName()+"',description:'"+vo.getDescription()+"'}";
		}
		
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 获取取消规则信息
	 * @return
	 * @throws IOException 
	 */
	public void ajaxloadCancelInfo() throws IOException{
		StringBuffer message = new StringBuffer("");
		String cancelId = getRequest().getParameter("cancelId");
		List<CancelPolicyI18n> cancelPolicyI18ns = cancelPolicyManager.getCancelPolicyI18ns(cancelId);
		
		if(cancelPolicyI18ns!=null&&cancelPolicyI18ns.size()>0){
			message.append("[");
			
			for (int i = 0; i < cancelPolicyI18ns.size(); i++) {
				CancelPolicyI18n i18n = cancelPolicyI18ns.get(i);
				String isDefault = "No";
				//如果与当前的默认语言相同,标识一下
				if(LanguageCode.MAIN_LANGUAGE_CODE.equals(i18n.getLanguage())){
					isDefault = "Yes";
				}
				
				message.append("{language:'"+this.setStringValue(i18n.getLanguage())+"',policyName:'"+this.setStringValue(i18n.getPolicyName())
						+"',description:'"+this.setStringValue(i18n.getDescription())+"',isDefault:'"+isDefault+"'}");
				if(i<cancelPolicyI18ns.size()-1){
					message.append(",");
				}
			}
			message.append("]");
		}
		
		this.getResponse().getWriter().print(message.toString());
	}
	
	/**
	 * 验证是否都已经绑定了所有的取消规则
	 * @throws IOException 
	 */
	public void isNotHaveCancel() throws IOException{
		String message = "true";
		List<CancelPolicyVO> cList = this.loadDontUseCancel();
		if(cList==null||cList.size()==0){
			message = "false";
		}
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 获取当前酒店没有利用的取消规则
	 * @return
	 */
	private List<CancelPolicyVO> loadDontUseCancel() {
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		return hotelCancelManager.getDontUseHotelCancel(curHotelId,language);
	}

	/**
	 * 验证酒店取消规则关系是否已经绑定
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isHotelCancel() throws IOException{
		String message = "true";
		//如果传入的包ID不为空
		if(StringUtils.isNotBlank(hotelCancelVo.getCancelId())){
			//得到当前酒店ID
			B2BUser user = getCurLoginUser();
			String hotelId = user.getHotelvo().getHotelId();
			hotelCancelVo.setHotelId(hotelId);
			
			List<HotelCancelVO> list = hotelCancelManager.getHotelCancelByObj(hotelCancelVo);
			if(list!=null&&list.size()>0){
				message = "false#"+list.get(0).getCancelPolicyCode();
			}
		}
	
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 删除取消规则前验证是否已经被其他东东引用了
	 * @throws IOException 
	 */
	public void validateDeleteCancel() throws IOException{
		String message = "{result:'success'}";
		boolean flag = true;  //标示可以往下执行
		
		HotelCancelVO hcvo = hotelCancelManager.getHotelCancelById(hotelCancelVo.getHotelCancelId());
		//执行正常
		if(flag){
			List<Rateplan> ratePlanList = hotelCancelManager.getUseRateCancel(hcvo.getHotelId(), hcvo.getCancelId());
			if(ratePlanList!=null&&ratePlanList.size()>0){
				message = "{result:'fail',type:'rateplan',pcode:'"+hcvo.getCancelPolicyCode()+"',code:'"+ratePlanList.get(0).getRatePlanCode()+"'}";
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

	public HotelCancelVO getHotelCancelVo() {
		return hotelCancelVo;
	}

	public void setHotelCancelVo(HotelCancelVO hotelCancelVo) {
		this.hotelCancelVo = hotelCancelVo;
	}

	public List<CancelPolicyVO> getCancelPolicyList() {
		return cancelPolicyList;
	}

	public void setCancelPolicyList(List<CancelPolicyVO> cancelPolicyList) {
		this.cancelPolicyList = cancelPolicyList;
	}

	public HotelCancelResult getResult() {
		return result;
	}

	public void setResult(HotelCancelResult result) {
		this.result = result;
	}

	public HotelCancelCreteria getCreteria() {
		return creteria;
	}

	public void setCreteria(HotelCancelCreteria creteria) {
		this.creteria = creteria;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
	
}
