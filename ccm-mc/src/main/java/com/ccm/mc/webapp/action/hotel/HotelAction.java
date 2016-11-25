package com.ccm.mc.webapp.action.hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.model.city.City;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.common.Textfile;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.fax.FaxSendTime;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.HotelI18n;
import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.model.hotel.vo.HotelCancelVO;
import com.ccm.api.model.hotel.vo.HotelCreteria;
import com.ccm.api.model.hotel.vo.HotelGuaranteeVO;
import com.ccm.api.model.hotel.vo.HotelResult;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.service.city.CityManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.common.TextfileManager;
import com.ccm.api.service.fax.FaxSendTimeManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.service.hotel.HotelCancelManager;
import com.ccm.api.service.hotel.HotelGuaranteeManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelSwitchManager;
import com.ccm.api.service.hotel.Hoteli18nManager;
import com.ccm.api.service.system.Push2ChannelStaticMsgManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


/**
 * 酒店
 * @author carr
 *
 */
public class HotelAction extends BaseAction {

	private static final long serialVersionUID = -4834810001607710441L;
	
	@Autowired
	private HotelMCManager hotelMCManager;
	@Autowired
	private ChainManager chainManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	@Resource
	private Push2ChannelStaticMsgManager push2ChannelStaticMsgManager;
	
	@Autowired
	private Hoteli18nManager hotelI18nManager;
	@Autowired
	private PictureManager pictureManager;// 图片信息
	@Autowired
	private HotelMCDao hotelMCDao;  //酒店Dao
	
	@Autowired
	private TextfileManager textfileManager;
	@Autowired
	private CityManager cityManager;//城市
	@Resource
    private HotelCancelManager hotelCancelManager;
    @Resource
    private HotelGuaranteeManager hotelGuaranteeManager;
    @Autowired
	private HotelSwitchManager hotelSwitchManager;
    
    @Autowired
    private FaxSendTimeManager faxSendTimeManager;
    
	private int parentId;
	
	private HotelCreteria creteria;
	private HotelResult result;
	private HotelVO hotelvo;
	private List<ChainVO> chainvoList;
	private List<DictCode> pmsTypeList;
	private List<DictCode> positionTypeList;
	private List<DictCode> currencyCodeList;
	private List<DictCode> hotelTypeList;
	private List<DictCode> languageList;
	private List<DictCode> dateFormatList;
	private List<DictCode> paymentMethodList;
	private List<DictCode> hotelStarList;
	private List<HotelGuaranteeVO> hotelGuaranteeList;
	private List<HotelCancelVO> hotelCancelList;
	
	private List<FaxSendTime> faxSendTimeList;
	
	/**
	 * 酒店列表显示
	 */
	public String list(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//获取集团
		chainvoList = chainManager.getAllChainI18n(language);
		
		if(null==creteria){
			creteria = new HotelCreteria();
		}
		creteria.setLanguageCode(language);
		int pageSize = this.getCurrentPageSize("hotelList");
		int pageNo = this.getCurrentPageNo("hotelList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		result = hotelMCManager.searchHotelMC(creteria);
		return "list";
	}
	
	/**
	 * 创建/编辑
	 */
	public String toEdit(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//获取集团
		chainvoList = chainManager.getAllChainI18n(language);
		pmsTypeList = dictCodeManager.searchByDictName(DictName.PMSTYPE,language);
		positionTypeList = dictCodeManager.searchByDictName(DictName.POSITIONTYPE,language);
		currencyCodeList = dictCodeManager.searchByDictName(DictName.CURRENCYCODE,language);
		hotelTypeList = dictCodeManager.searchByDictName(DictName.HOTELTYPE,language);
		dateFormatList = dictCodeManager.searchByDictName(DictName.DATEFORMAT,language);
		paymentMethodList = dictCodeManager.searchByDictName(DictName.PAYMENTMETHOD,language);
		hotelStarList = dictCodeManager.searchByDictName(DictName.HOTELSTAR,language);
		
		// 担保规则
		if(hotelvo != null && CommonUtil.isNotEmpty(hotelvo.getHotelId())){
			HotelGuaranteeVO hgVo = new HotelGuaranteeVO();
			hgVo.setHotelId(hotelvo.getHotelId());
			hgVo.setLanguage(language);
			hotelGuaranteeList = hotelGuaranteeManager.getHotelGuaranteeByObj(hgVo);
			
			// 取消规则
			HotelCancelVO hcVo = new HotelCancelVO();
			hcVo.setHotelId(hotelvo.getHotelId());
			hcVo.setLanguage(language);
			hotelCancelList = hotelCancelManager.getHotelCancelByObj(hcVo);
		}else{
			hotelGuaranteeList = new ArrayList<HotelGuaranteeVO>();
			hotelCancelList = new ArrayList<HotelCancelVO>();
		}
		
		List<City> countryList = cityManager.getCityList(0,language);
		List<City> privinceList = cityManager.getCityList(countryList.get(0).getId(),language);
		getRequest().setAttribute("countryList", countryList);
		getRequest().setAttribute("privinceList", privinceList);
		
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
		
		if(null!=hotelvo && null!=hotelvo.getHotelId()){
			hotelvo = hotelMCManager.getHotelByIdMC(hotelvo.getHotelId(),language);
			
			if(hotelvo.getCity()!=null){
				List<City> cityList = cityManager.getCityByCityCode(hotelvo.getCity().toString(), language);
				getRequest().setAttribute("cityList", cityList);
			}
			if(hotelvo.getAreaCode()!=null){
				List<City> areaCodeList = cityManager.getCityByCityCode(hotelvo.getAreaCode().toString(), language);
				getRequest().setAttribute("areaCodeList", areaCodeList);
			}
			// 用于图片关联
			hotelvo.setPicId(hotelvo.getHotelId());
			
			//如果logo图片尚未设置
			if(StringUtils.isBlank(hotelvo.getLogoPicId())){
				hotelvo.setLogoPicId(CommonUtil.generatePrimaryKey());
			}
			
			//设置多语言列表
			List<HotelI18n> hotelI18nList = hotelI18nManager.getHotelI18nListByHotelId(hotelvo.getHotelId());
			if(hotelI18nList!=null&&hotelI18nList.size()>0){
				for (int i = 0; i < hotelI18nList.size(); i++) {
					if(language.equals(hotelI18nList.get(i).getLanguageCode())){
						hotelI18nList.remove(i);
						break;
					}
				}
			}
			//加载酒店图片列表
			hotelvo.setHotelI18nList(hotelI18nList);
			hotelvo.setPictureList(
					pictureManager.getBizPictureList("1", hotelvo.getHotelId()));
			//加载logo图片
			if(StringUtils.isNotBlank(hotelvo.getLogoPicId())){
				List<Picture> logoPicList = pictureManager.getBizPictureList("1", hotelvo.getLogoPicId());
				if(logoPicList!=null&&logoPicList.size()>0){
					hotelvo.setLogoPic(logoPicList.get(0));
				}
			}
			FaxSendTime faxSendTime = new FaxSendTime();
			faxSendTime.setHotelId(hotelvo.getHotelId());
			faxSendTimeList = faxSendTimeManager.searchFaxSendTimeList(faxSendTime);
		}else{
			hotelvo = new HotelVO();
			// 用于图片关联
			hotelvo.setPicId(CommonUtil.generatePrimaryKey());
			// 用于logo图片关联
			hotelvo.setLogoPicId(CommonUtil.generatePrimaryKey());
			
			hotelvo.setCheckInTimeDesc(this.getDefaultDesc("checkInTimeDesc", language));
			hotelvo.setCheckOutTimeDesc(this.getDefaultDesc("checkOutTimeDesc", language));
			hotelvo.setCancelPolicyDesc(this.getDefaultDesc("cancelPolicyDesc", language));
			hotelvo.setIsPMSHeartBeat(false);
			
		}
		
		hotelvo.setPictureUrlFolder(projectProperties.getProperty(ProjectConfigConstant.pictureUrlContext));

		//css文件上传
		String hid=hotelvo.getHotelId();
		Textfile t=textfileManager.getTextfile(hid);
		if((t!=null)&&(t.getFileId().equals(hid))){
			hotelvo.setCssFileId(hid);
			hotelvo.setCssName(t.getFileName());
			hotelvo.setCssUrl( projectProperties.getProperty(ProjectConfigConstant.pictureUrlContext) + t.getFileUrl() );
		}
		
		
		
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){

		String hotelI18ns = getRequest().getParameter("f_hotelI18ns");
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//保存多语言列表
		List<HotelI18n> hotelI18nList = new ArrayList<HotelI18n>();
		try {
			hotelvo.setLanguageCode(language);
			hotelI18nList.add(hotelMCManager.getDefaultLanguageI18n(hotelvo));
			//组装多语言数据
			if(StringUtils.isNotBlank(hotelI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(hotelI18ns);
				
				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					HotelI18n i18n = new HotelI18n();
					i18n.setLanguageCode(i18nMap.get("codeNo"));
					i18n.setHotelName(i18nMap.get("name"));
					i18n.setHotelShortName(i18nMap.get("shortName"));
					i18n.setHotelUsedName(i18nMap.get("userdName"));
					i18n.setAddress(i18nMap.get("address"));
					i18n.setBusiness(i18nMap.get("business"));
					i18n.setSalutatory(i18nMap.get("salutatory"));
					i18n.setDescription(i18nMap.get("description"));
					i18n.setCheckInTimeDesc(i18nMap.get("checkInTimeDesc"));
					i18n.setCheckOutTimeDesc(i18nMap.get("checkOutTimeDesc"));		
					i18n.setCancelPolicyDesc(i18nMap.get("cancelPolicyDesc"));
					i18n.setPayRemind(i18nMap.get("payRemind"));
					i18n.setPickUpService(i18nMap.get("pickUpService"));
					hotelI18nList.add(i18n);
				}
			}
			//将多语言设置进去
			hotelvo.setHotelI18nList(hotelI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null!=hotelvo && null!=hotelvo.getHotelId() && !"".equals(hotelvo.getHotelId())){
			hotelMCManager.updateHotelMC(hotelvo);
			push2ChannelStaticMsgManager.pushStaticMsgHotelInfo(hotelvo.getHotelId(), language, true);
		}else{
			hotelMCManager.saveHotelMC(hotelvo);
			
			HotelSwitch hotelSwitch = new HotelSwitch();
			hotelSwitch.setHotelId(hotelvo.getHotelId());
			hotelSwitch.setChainId(hotelvo.getChainId());
			hotelSwitch.setHotelCode(hotelvo.getHotelCode());
			hotelSwitch.setChainCode(chainManager.get(hotelvo.getChainId()).getChainCode());
			
			hotelSwitch.setIsGenerates(false);
			hotelSwitch.setIsHardCancel(false);
			hotelSwitch.setIsUploadRateHeader(false);
			hotelSwitch.setIsMask(true);
			hotelSwitch.setIsMonitorADSPending(true);
			hotelSwitch.setCreatedTime(new Date());
			
			hotelSwitchManager.addHotelSwitch(hotelSwitch);
		}
		return "save";
	}
	
	/**
	 * 验证酒店代码是否存在
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isHotelCode() throws IOException{
		String message = "";
		hotelvo = hotelMCManager.getHotelByCodeMC(hotelvo);
		if(null==hotelvo){
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
		hotelvo.setDelFlag(true);
		hotelMCManager.deleteHotelMC(hotelvo);
		return "delete";
	}
	
	public void updateLogPic() throws IOException{
		Picture logoPic = null;
		//加载logo图片
		if(StringUtils.isNotBlank(hotelvo.getLogoPicId())){
			//如果酒店ID不为空,则表示为修改
			if(StringUtils.isNotBlank(hotelvo.getHotelId())){
				//修改到数据库中
				Hotel hotel = hotelMCManager.getHotelByIdMC(hotelvo.getHotelId());
				hotel.setLogoPicId(hotelvo.getLogoPicId());
				hotelMCDao.updateHotelMC(hotel);
			}
			
			//获取图片
			List<Picture> logoPicList = pictureManager.getBizPictureList("1", hotelvo.getLogoPicId());
			if(logoPicList!=null&&logoPicList.size()>0){
				logoPic = logoPicList.get(0);
			}
		}
		if(logoPic!=null){
			this.getResponse().getWriter().print("{result:'"+SUCCESS+"',url:'"+logoPic.getUrl()+"'}");
		}
	}
	
	/**
	 * 获取默认的信息描述()
	 * @throws IOException 
	 */
	public void ajaxloadDefaultDesc() throws IOException{
		String descType = getRequest().getParameter("descType");
		String language = getRequest().getParameter("language");
		String desc = this.getDefaultDesc(descType, language);
		this.getResponse().getWriter().print(desc);
	}

	public void deleteLogPic() throws IOException{
		pictureManager.deletePictureByBizId(hotelvo.getLogoPicId());
		this.getResponse().getWriter().print(SUCCESS);
	}
	
	/**
	 * 描述类型(checkInTimeDesc:入住时间描述,checkOutTimeDesc:退房时间描述,cancelPolicyDesc:取消政策)
	 */
	private String getDefaultDesc(String descType,String language){
		Map<String, String> descMap = new HashMap<String, String>();
		descMap.put("checkInTimeDesc-zh_CN", "客房入住时间为下午14:00,如果您在这个时间之前到达我们的酒店,您的房间将被优先安排."
				+"如果您需要提前入住，请提前一晚通知我们,以确保您提前入住.");
		descMap.put("checkInTimeDesc-en_US", "Hotel rooms are generally available for guest check-in after 2.00pm."
				+"Guests arriving prior to this time will be allocated rooms as soon as they become available. "
				+"For all early checkin, we strongly recommend  that you reserve the rooms the night before in "
				+"order to guarantee early check-in.");
		descMap.put("checkInTimeDesc-ja_JP", "チェックインは14時より.お客様はこの時間前にホテルご到着された場合は、， それでは条件が満足されたら、即時にお"
				+"客様に部屋をご手配致します，夜前に部屋のご予約をお勧めします，ご予約の成功を確保するために.");
		

		descMap.put("checkOutTimeDesc-zh_CN", "退房的时间为中午12:00,客人在推迟退房时间要收取额外的费用.如退房时间延长到晚上18:00,"
				+"要收取的费用为房价一半金额,如退房时间超过晚上18:00,则要收取的费用为房价全额.");
		descMap.put("checkOutTimeDesc-en_US", "Check-out time is 12.00 noon. Guests requiring special consideration "
				+"for late check-out should make their request when placing reservations. Rooms may be extended "
				+"until 6.00pm at a charge of half of room rate , non-commissionable, subject to availability. "
				+"After 6.00pm rooms will be charged at the full rate ");
		descMap.put("checkOutTimeDesc-ja_JP", "チェックアウトは正午まで ,チェックアウト時間は夕方の６：００迄に遅延する場合は、"
				+"部屋料金半分，チェックアウト時間は夕方の６：００を超えた場合は、部屋料金全額.");
		
		descMap.put("cancelPolicyDesc-zh_CN", "所有的预订应该有信用卡担保或公司担保。担保预订在日前取消,预订取消按酒店规定处理,不入住将收取一晚的房费.");
		descMap.put("cancelPolicyDesc-en_US", "All reservation must be guaranteed by a credit card deposit or company guarantees. "
				+"Guaranteed reservation may by cancelled before . Cancellation rules will base on Hotel Regulation. "
				+"No-show will be chargeed for one night\'s room charge.");
		descMap.put("cancelPolicyDesc-ja_JP", "すべての予約はクレジットカードの沈殿物保証されたBuでなければか会社の保証ならない。保証された宿泊予約を、"
				+"宿泊日前日の午後4時（現地時刻）から宿泊当日の午後4時（現地時刻）までにキャンセルされた場合は  キャンセル料なし 予約のキャンセルについて。宿泊料金の1泊分をキャンセル料として申し受けます");
		
		//如果语言编码为空,则获取默认语言
		if(StringUtils.isBlank(language)){
			language = LanguageCode.MAIN_LANGUAGE_CODE;
		}
		
		String desc = descMap.get(descType+"-"+language);
		return StringUtils.isBlank(desc)?"":desc;
	}

	
	/**
	 * 获取默认的信息描述()
	 * @throws IOException 
	 */
	public void loadDefaultDesc() throws IOException{
		String descType = getRequest().getParameter("descType");
		String language = getRequest().getParameter("language");
		String desc = this.getDefaultDesc(descType, language);
		this.getResponse().getWriter().print(desc);
	}
	
	/**
	 * 获取城市下拉
	 */
	public void ajaxGetCityList(){
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			List<City> cityList = cityManager.getCityList(parentId,language);
			String jsonArray = JSON.toJSONString(cityList);
			getResponse().getWriter().write(jsonArray);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	//添加css文件
	public void updateCssFile() throws IOException{
		//如果酒店ID不为空,则表示为修改
		if(StringUtils.isNotBlank(hotelvo.getHotelId())){
			//修改到数据库
			Hotel hotel = hotelMCManager.getHotelByIdMC(hotelvo.getHotelId());
			hotel.setCssFileId(hotelvo.getHotelId());
			hotelMCDao.updateHotelMC(hotel);
			this.getResponse().getWriter().print(SUCCESS);
		}
	}
	//删除css文件
	public void deleteCssFile() throws IOException{
		
		textfileManager.deleteTextfile(hotelvo.getHotelId());
		
		Hotel hotel = hotelMCManager.getHotelByIdMC(hotelvo.getHotelId());
		hotel.setCssFileId(null);
		hotelMCDao.updateHotelMC(hotel);
		this.getResponse().getWriter().print(SUCCESS);
	}
	
	/**
	 * 修改faxDetail
	 */
	public void editEfaxDetail(){
		try {
			faxSendTimeManager.saveOrUpdateFaxSendTime(faxSendTimeList);
			this.getResponse().getWriter().print(SUCCESS);
		} catch (Exception e) {
			try {
				log.equals(e);
				this.getResponse().getWriter().print(ERROR);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public HotelCreteria getCreteria() {
		return creteria;
	}
	public void setCreteria(HotelCreteria creteria) {
		this.creteria = creteria;
	}
	public HotelResult getResult() {
		return result;
	}
	public void setResult(HotelResult result) {
		this.result = result;
	}
	public HotelVO getHotelvo() {
		return hotelvo;
	}
	public void setHotelvo(HotelVO hotelvo) {
		this.hotelvo = hotelvo;
	}
	public List<ChainVO> getChainvoList() {
		return chainvoList;
	}
	public void setChainvoList(List<ChainVO> chainvoList) {
		this.chainvoList = chainvoList;
	}

	public List<DictCode> getPmsTypeList() {
		return pmsTypeList;
	}

	public void setPmsTypeList(List<DictCode> pmsTypeList) {
		this.pmsTypeList = pmsTypeList;
	}

	public List<DictCode> getPositionTypeList() {
		return positionTypeList;
	}

	public void setPositionTypeList(List<DictCode> positionTypeList) {
		this.positionTypeList = positionTypeList;
	}

	public List<DictCode> getCurrencyCodeList() {
		return currencyCodeList;
	}

	public void setCurrencyCodeList(List<DictCode> currencyCodeList) {
		this.currencyCodeList = currencyCodeList;
	}

	public List<DictCode> getHotelTypeList() {
		return hotelTypeList;
	}

	public void setHotelTypeList(List<DictCode> hotelTypeList) {
		this.hotelTypeList = hotelTypeList;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}

	public List<DictCode> getDateFormatList() {
		return dateFormatList;
	}

	public void setDateFormatList(List<DictCode> dateFormatList) {
		this.dateFormatList = dateFormatList;
	}

	public List<DictCode> getPaymentMethodList() {
		return paymentMethodList;
	}

	public void setPaymentMethodList(List<DictCode> paymentMethodList) {
		this.paymentMethodList = paymentMethodList;
	}

	public List<DictCode> getHotelStarList() {
		return hotelStarList;
	}

	public void setHotelStarList(List<DictCode> hotelStarList) {
		this.hotelStarList = hotelStarList;
	}

	public List<HotelGuaranteeVO> getHotelGuaranteeList() {
		return hotelGuaranteeList;
	}

	public void setHotelGuaranteeList(List<HotelGuaranteeVO> hotelGuaranteeList) {
		this.hotelGuaranteeList = hotelGuaranteeList;
	}

	public List<HotelCancelVO> getHotelCancelList() {
		return hotelCancelList;
	}

	public void setHotelCancelList(List<HotelCancelVO> hotelCancelList) {
		this.hotelCancelList = hotelCancelList;
	}

	public TextfileManager getTextfileManager() {
		return textfileManager;
	}

	public void setTextfileManager(TextfileManager textfileManager) {
		this.textfileManager = textfileManager;
	}

	public List<FaxSendTime> getFaxSendTimeList() {
		return faxSendTimeList;
	}

	public void setFaxSendTimeList(List<FaxSendTime> faxSendTimeList) {
		this.faxSendTimeList = faxSendTimeList;
	}

}
