package com.ccm.mc.webapp.action.roomType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.roomType.RoomTypeI18n;
import com.ccm.api.model.roomType.RoomTypeTemplateI18n;
import com.ccm.api.model.roomType.vo.RoomTypeCreteria;
import com.ccm.api.model.roomType.vo.RoomTypeResult;
import com.ccm.api.model.roomType.vo.RoomTypeTemplateVO;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.roomType.RoomTypeTemplateManager;
import com.ccm.api.service.system.Push2ChannelStaticMsgManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


/**
 * 房型
 * @author carr
 *
 */
public class RoomTypeAction extends BaseAction {

	private static final long serialVersionUID = 5033359798358255976L;

	@Autowired
	private RoomTypeManager roomTypeManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	@Autowired
	private HotelMCManager hotelMCManager;
	@Autowired
	private RoomTypeTemplateManager roomTypeTemplateManager;
	@Autowired
	private PictureManager pictureManager;// 图片信息
	@Resource
	private Push2ChannelStaticMsgManager push2ChannelStaticMsgManager;
	
	private RoomTypeCreteria creteria;
	private RoomTypeResult result;
	private RoomTypeVO roomTypevo;
	private List<DictCode> dictCodeList;
	private List<HotelVO> hotelList;
	private List<RoomTypeTemplateVO> roomTypeTempList;
	private List<DictCode> languageList;
	
	/**
	 * 房型列表显示
	 */
	public String list(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//酒店列表
		hotelList = hotelMCManager.getAllHotelMC(language);
		
		if(null==creteria){
			creteria = new RoomTypeCreteria();
		}
		int pageSize = this.getCurrentPageSize("roomTypeList");
		int pageNo = this.getCurrentPageNo("roomTypeList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		creteria.setLanguage(language);
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		creteria.setHotelId(curHotelId);
		result = roomTypeManager.searchRoomType(creteria);
		return "list";
	}
	
	/**
	 * 创建/编辑
	 */
	public String toEdit(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//床型列表
		dictCodeList = dictCodeManager.searchByDictName(DictName.BEDTYPE,language);
		//酒店列表
		hotelList = hotelMCManager.getAllHotelMC();
		
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
		
		if(null!=roomTypevo && null!=roomTypevo.getRoomTypeId()){
			roomTypevo = roomTypeManager.getRoomTypeById(roomTypevo.getRoomTypeId(),language);
			// 用于图片关联
			roomTypevo.setPicId(roomTypevo.getRoomTypeId());
			//默认语言为中文
			roomTypeTempList = roomTypeTemplateManager.getAllRoomTypeTemplate(language);
			
			//设置多语言列表
			List<RoomTypeI18n> roomTypeI18nList = roomTypeManager.getRoomTypeI18ns(roomTypevo.getRoomTypeId());
			
			if(roomTypeI18nList!=null&&roomTypeI18nList.size()>0){
				for (int i = 0; i < roomTypeI18nList.size(); i++) {
					if(language.equals(roomTypeI18nList.get(i).getLanguage())){
						roomTypeI18nList.remove(i);
						break;
					}
				}
			}
			roomTypevo.setRoomTypeI18nList(roomTypeI18nList);
			
			//设置图片列表
			roomTypevo.setPictureList(
					pictureManager.getBizPictureList("2", roomTypevo.getRoomTypeId()));
			//图片读取目录
			roomTypevo.setRoomPicUrl(
					projectProperties.getProperty(ProjectConfigConstant.pictureUrlContext));
			
		}else{
			//得到当前酒店ID
			B2BUser user = getCurLoginUser();
			String curHotelId = user.getHotelvo().getHotelId();
			roomTypeTempList = roomTypeTemplateManager.getDontUseRoomTypeTemplate(curHotelId,language);
			roomTypevo = new RoomTypeVO();
			
			roomTypevo.setHotelId(curHotelId);
			
			// 用于图片关联
			roomTypevo.setPicId(CommonUtil.generatePrimaryKey());
		}
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){
		
		String roomTypeI18ns = getRequest().getParameter("f_roomTypeI18ns");
		//保存多语言列表
		List<RoomTypeI18n> roomTypeI18nList = new ArrayList<RoomTypeI18n>();
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		try {
			roomTypevo.setLanguage(language);
			roomTypeI18nList.add(roomTypeManager.getDefaultLanguageI18n(roomTypevo));
			
			//组装多语言数据
			if(StringUtils.isNotBlank(roomTypeI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(roomTypeI18ns);
				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					RoomTypeI18n i18n = new RoomTypeI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setRoomTypeName(i18nMap.get("name"));
					i18n.setDescription(i18nMap.get("description"));
					roomTypeI18nList.add(i18n);
				}
			}
			
			//将多语言设置进去
			roomTypevo.setRoomTypeI18nList(roomTypeI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null!=roomTypevo && null!=roomTypevo.getRoomTypeId() && !"".equals(roomTypevo.getRoomTypeId())){
			roomTypeManager.updateRoomType(roomTypevo);
			push2ChannelStaticMsgManager.pushStaticMsgRoomType(roomTypevo.getHotelId(), roomTypevo.getRoomTypeId(), language, true);
		}else{
			roomTypeManager.saveRoomType(roomTypevo);
		}
		return "save";
	}
	
	/**
	 * 验证房型代码是否存在
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isRoomTypeCode() throws IOException{
		String message = "";
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		roomTypevo.setHotelId(curHotelId);
		roomTypevo = roomTypeManager.getRoomTypeByCode(roomTypevo);
		if(null==roomTypevo){
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
		roomTypevo.setDelFlag(true);
		roomTypeManager.deleteRoomType(roomTypevo);
		return "delete";
	}
	
	/**
	 * 验证是否都已经绑定了所有的房型
	 * @throws IOException 
	 */
	public void isNotHaveRoomTypeTemplate() throws IOException{
		String message = "true";
		List<RoomTypeTemplateVO> rList = this.loadDontUseRoomTypeTemplate();
		if(rList==null||rList.size()==0){
			message = "false";
		}
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 获取未被使用的房型模板
	 * @return
	 */
	private List<RoomTypeTemplateVO> loadDontUseRoomTypeTemplate() {
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		//默认语言为中文
		return roomTypeTemplateManager.getDontUseRoomTypeTemplate(curHotelId);
	}

	/**
	 * 获取房型模板
	 * @throws IOException 
	 */
	public void ajaxloadRoomTypeTemplateInfo() throws IOException{
		StringBuffer message = new StringBuffer("");
		String roomTypeTemplateId = getRequest().getParameter("roomTypeTemplateId");
		
		List<RoomTypeTemplateI18n> i18nList = roomTypeTemplateManager.getRoomTypeTemplateI18ns(roomTypeTemplateId);
		if(i18nList!=null&&i18nList.size()>0){
			message.append("[");
			for (int i = 0; i < i18nList.size(); i++) {
				RoomTypeTemplateI18n i18n = i18nList.get(i);
				String isDefault = "No";
				//如果与当前的默认语言相同,标识一下
				if(LanguageCode.MAIN_LANGUAGE_CODE.equals(i18n.getLanguage())){
					isDefault = "Yes";
				}
				
				message.append("{language:'"+this.setStringValue(i18n.getLanguage())+"',tempName:'"+this.setStringValue(i18n.getRoomTypeTemplateName())
						+"',description:'"+this.setStringValue(i18n.getDescription())+"',isDefault:'"+isDefault+"'}");
				
				if(i<i18nList.size()-1){
					message.append(",");
				}
			}
			message.append("]");
		}
		
		this.getResponse().getWriter().print(message.toString());
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
	
	public RoomTypeCreteria getCreteria() {
		return creteria;
	}
	public void setCreteria(RoomTypeCreteria creteria) {
		this.creteria = creteria;
	}
	public RoomTypeResult getResult() {
		return result;
	}
	public void setResult(RoomTypeResult result) {
		this.result = result;
	}
	public RoomTypeVO getRoomTypevo() {
		return roomTypevo;
	}
	public void setRoomTypevo(RoomTypeVO roomTypevo) {
		this.roomTypevo = roomTypevo;
	}
	public List<DictCode> getDictCodeList() {
		return dictCodeList;
	}
	public void setDictCodeList(List<DictCode> dictCodeList) {
		this.dictCodeList = dictCodeList;
	}
	public List<HotelVO> getHotelList() {
		return hotelList;
	}
	public void setHotelList(List<HotelVO> hotelList) {
		this.hotelList = hotelList;
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
