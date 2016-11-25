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
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.SpecialOfferI18n;
import com.ccm.api.model.hotel.vo.SpecialOfferCreteria;
import com.ccm.api.model.hotel.vo.SpecialOfferResult;
import com.ccm.api.model.hotel.vo.SpecialOfferVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.SpecialOfferManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class SpecialOfferAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5847650859535363231L;
	
	@Autowired
	private SpecialOfferManager specialOfferManager;
	
	@Autowired
	private DictCodeManager dictCodeManager;
	
	private SpecialOfferCreteria creteria;
	private SpecialOfferResult result;
	private SpecialOfferVO specialOfferVO;
	private List<SpecialOfferVO> specialOfferVOList;
	private List<DictCode> languageList;
	
	/**
	 * 酒店优惠列表
	 */
	public String list(){
		if(null==creteria){
			creteria = new SpecialOfferCreteria();
		}
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		
		int pageSize = this.getCurrentPageSize("specialOfferList");
		int pageNo = this.getCurrentPageNo("specialOfferList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		creteria.setLanguage(language);
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		creteria.setHotelId(curHotelId);
		result = specialOfferManager.searchSpecialOffer(creteria);
		return "list";
	}
	
	/**
	 * 创建/编辑
	 * @param marketvo 
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
		
		if(specialOfferVO!=null&&StringUtils.isNotBlank(specialOfferVO.getSpecialOfferId())){
			specialOfferVO = specialOfferManager.getSpecialOfferById(specialOfferVO.getSpecialOfferId());
			
			//设置多语言列表
			List<SpecialOfferI18n> specialOfferI18nList = 
				specialOfferManager.getSpecialOfferI18ns(specialOfferVO.getSpecialOfferId());
		
			if(specialOfferI18nList!=null&&specialOfferI18nList.size()>0){
				for (int i = 0; i < specialOfferI18nList.size(); i++) {
					if(language.equals(specialOfferI18nList.get(i).getLanguage())){
						specialOfferI18nList.remove(i);
						break;
					}
				}
			}
			specialOfferVO.setSpecialOfferI18nList(specialOfferI18nList);
		}else{
			specialOfferVO = new SpecialOfferVO();
		}
		
		return "toEdit";
	}
	
	
	/**
	 * 保存/修改
	 */
	public String save(){
		
		String specialOfferI18ns = getRequest().getParameter("f_specialOfferI18ns");
		//保存多语言列表
		List<SpecialOfferI18n> specialOfferI18nList = new ArrayList<SpecialOfferI18n>();
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			specialOfferVO.setLanguage(language);
			specialOfferI18nList.add(specialOfferManager.getDefaultLanguageI18n(specialOfferVO));
			
			//组装多语言数据
			if(StringUtils.isNotBlank(specialOfferI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(specialOfferI18ns);
				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					SpecialOfferI18n i18n = new SpecialOfferI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setSummary(i18nMap.get("summary"));
					i18n.setDetail(i18nMap.get("detail"));
					specialOfferI18nList.add(i18n);
				}
			}
			
			//将多语言设置进去
			specialOfferVO.setSpecialOfferI18nList(specialOfferI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		specialOfferVO.setHotelId(curHotelId);
		if(null!=specialOfferVO && StringUtils.isNotBlank(specialOfferVO.getSpecialOfferId())){
			specialOfferManager.updateSpecialOffer(specialOfferVO);
		}else{
			specialOfferManager.saveSpecialOffer(specialOfferVO);
		}
		return "save";
	}
	
	/**
	 * 删除
	 * @throws IOException 
	 */
	public String delete(){
		if(specialOfferVO != null && StringUtils.isNotBlank(specialOfferVO.getSpecialOfferId())){
			specialOfferManager.deleteSpecialOffer(specialOfferVO);
		}
		
		return "delete";
	}
	
	public SpecialOfferCreteria getCreteria() {
		return creteria;
	}
	public void setCreteria(SpecialOfferCreteria creteria) {
		this.creteria = creteria;
	}
	public SpecialOfferResult getResult() {
		return result;
	}
	public void setResult(SpecialOfferResult result) {
		this.result = result;
	}
	public SpecialOfferVO getSpecialOfferVO() {
		return specialOfferVO;
	}
	public void setSpecialOfferVO(SpecialOfferVO specialOfferVO) {
		this.specialOfferVO = specialOfferVO;
	}
	
	public List<SpecialOfferVO> getSpecialOfferVOList() {
		return specialOfferVOList;
	}
	public void setSpecialOfferVOList(List<SpecialOfferVO> specialOfferVOList) {
		this.specialOfferVOList = specialOfferVOList;
	}
	public List<DictCode> getLanguageList() {
		return languageList;
	}
	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}

}
