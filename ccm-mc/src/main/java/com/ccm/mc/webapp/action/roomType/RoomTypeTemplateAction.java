package com.ccm.mc.webapp.action.roomType;

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
import com.ccm.api.model.roomType.RoomTypeTemplateI18n;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateCreteria;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateResult;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateVO;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.roomType.RoomTypeTemplateManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


public class RoomTypeTemplateAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1030512876315891947L;
	
	@Autowired
	private RoomTypeTemplateManager roomTypeTemplateManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	
	
	private RoomTypeTemplateVO roomTypeTemplateVo;
	private RoomTypeTemplateCreteria creteria;
	private RoomTypeTemplateResult result;
	private List<RoomTypeTemplateVO> roomTypeTempList;
	private List<DictCode> languageList;
	
	/**
	 * 房型模板列表
	 * @return
	 */
	public String list(){
		
		if(null==creteria){
			creteria = new RoomTypeTemplateCreteria();
		}
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		creteria.setLanguage(language);
		int pageSize = this.getCurrentPageSize("roomTypeTemp");
		int pageNo = this.getCurrentPageNo("roomTypeTemp");
		
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		result = roomTypeTemplateManager.searchRoomTypeTemplate(creteria);
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
		
		if(roomTypeTemplateVo!=null&&StringUtils.isNotBlank(roomTypeTemplateVo.getRoomTypeTemplateId())){
			roomTypeTemplateVo = roomTypeTemplateManager.getRoomTypeTemplateById(roomTypeTemplateVo.getRoomTypeTemplateId(),language);
			
			//设置多语言列表
			List<RoomTypeTemplateI18n> roomTypeTemplateI18nList = 
				roomTypeTemplateManager.getRoomTypeTemplateI18ns(roomTypeTemplateVo.getRoomTypeTemplateId());
			
			if(roomTypeTemplateI18nList!=null&&roomTypeTemplateI18nList.size()>0){
				for (int i = 0; i < roomTypeTemplateI18nList.size(); i++) {
					if(language.equals(roomTypeTemplateI18nList.get(i).getLanguage())){
						roomTypeTemplateI18nList.remove(i);
						break;
					}
				}
			}
			roomTypeTemplateVo.setRoomTypeTempI18nList(roomTypeTemplateI18nList);
		}else{
			roomTypeTemplateVo = new RoomTypeTemplateVO();
		}
		
		return "toEdit";
	}
	
	
	public String save(){
		
		String roomTypeTemplateI18ns = getRequest().getParameter("f_roomTypeTemplateI18ns");
		
		//创建房型模板多语言列表对象
		List<RoomTypeTemplateI18n> roomTypeTemplateI18nList = new ArrayList<RoomTypeTemplateI18n>();
		
		try {
			//将默认语言添加进去
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			roomTypeTemplateVo.setLanguage(language);
			roomTypeTemplateI18nList.add(roomTypeTemplateManager.getDefaultLanguageI18n(roomTypeTemplateVo));
			
			//组装多语言数据
			if(StringUtils.isNotBlank(roomTypeTemplateI18ns)){
				
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(roomTypeTemplateI18ns);
				for (Map<String, String> i18nMap : i18nMapList) {
					
					//创建多语言对象,并且设置值
					RoomTypeTemplateI18n i18n = new RoomTypeTemplateI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setRoomTypeTemplateName(i18nMap.get("name"));
					i18n.setDescription(i18nMap.get("description"));
					roomTypeTemplateI18nList.add(i18n);
				}
			}
			
			//将多语言设置进去
			roomTypeTemplateVo.setRoomTypeTempI18nList(roomTypeTemplateI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(roomTypeTemplateVo!=null&&StringUtils.isNotBlank(roomTypeTemplateVo.getRoomTypeTemplateId())){
			roomTypeTemplateManager.updateRoomTypeTempldate(roomTypeTemplateVo);
		}else{
			roomTypeTemplateManager.saveRoomTypeTemplate(roomTypeTemplateVo);
		}
		return "save";
	}
	
	

	/**
	 * 删除
	 * @throws IOException 
	 */
	public String delete(){
		roomTypeTemplateVo.setDelFlag(true);
		roomTypeTemplateManager.deleteRoomTypeTempldate(roomTypeTemplateVo);
		
		return "delete";
	}
	
	/**
	 * 验证房型模板代码是否存在
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isRoomTypeTempCode() throws IOException{
		String message = "";
		roomTypeTemplateVo = 
			roomTypeTemplateManager.getRoomTypeTemplateByCode(roomTypeTemplateVo.getRoomTypeTemplateCode());
		if(null==roomTypeTemplateVo){
			message = "true";
		}else{
			message = "false";
		}
		this.getResponse().getWriter().print(message);
	}
	
	
	
	public RoomTypeTemplateVO getRoomTypeTemplateVo() {
		return roomTypeTemplateVo;
	}
	public void setRoomTypeTemplateVo(RoomTypeTemplateVO roomTypeTemplateVo) {
		this.roomTypeTemplateVo = roomTypeTemplateVo;
	}
	public RoomTypeTemplateCreteria getCreteria() {
		return creteria;
	}
	public void setCreteria(RoomTypeTemplateCreteria creteria) {
		this.creteria = creteria;
	}
	public RoomTypeTemplateResult getResult() {
		return result;
	}
	public void setResult(RoomTypeTemplateResult result) {
		this.result = result;
	}
	public List<RoomTypeTemplateVO> getRoomTypeTempList() {
		return roomTypeTempList;
	}
	public void setRoomTypeTempList(List<RoomTypeTemplateVO> roomTypeTempList) {
		this.roomTypeTempList = roomTypeTempList;
	}
	public List<DictCode> getLanguageList() {
		return languageList;
	}
	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
}
