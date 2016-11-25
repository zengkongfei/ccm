package com.ccm.mc.webapp.action.hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.hotel.HotelPackageI18n;
import com.ccm.api.model.hotel.vo.HotelPackageCreteria;
import com.ccm.api.model.hotel.vo.HotelPackageResult;
import com.ccm.api.model.hotel.vo.HotelPackageVO;
import com.ccm.api.model.ratePlan.PackageI18n;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.HotelPackageManager;
import com.ccm.api.service.ratePlan.PackageManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class HotelPackageAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1671675391976847298L;
	
	@Autowired
	private HotelPackageManager hotelPackageManager;
	
	@Autowired
	private PackageManager packageManager;
	
	@Autowired
	private DictCodeManager dictCodeManager;

	private HotelPackageVO hotelPackageVo;
	
	private List<PackageVO> packageList;
	
	private HotelPackageResult result;
	
	private HotelPackageCreteria creteria;
	
	private List<DictCode> languageList;
	
	/**
	 * 酒店打包服务列表
	 */
	public String list(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		packageList = packageManager.getAllPackages(language);
		
		if(hotelPackageVo == null){
			hotelPackageVo = new HotelPackageVO();
		}

		if(null==creteria){
			creteria = new HotelPackageCreteria();
		}
		creteria.setLanguage(language);
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		hotelPackageVo.setHotelId(curHotelId);
		
		int pageSize = this.getCurrentPageSize("hotelPackageList");
		int pageNo = this.getCurrentPageNo("hotelPackageList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		creteria.setHotelId(curHotelId);
		result = hotelPackageManager.searchHotelPackage(creteria);
		return "list";
	}
	
	/**
	 * 创建
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
		
		if(hotelPackageVo!=null&&StringUtils.isNotBlank(hotelPackageVo.getHotelPackageId())){
			packageList = packageManager.getAllPackages();
			hotelPackageVo = hotelPackageManager.getHotelPackageById(hotelPackageVo.getHotelPackageId(),language);
			//设置多语言列表
			List<HotelPackageI18n> hotelPackageI18nList = 
				hotelPackageManager.getHotelPackageI18ns(hotelPackageVo.getHotelPackageId());
		
			if(hotelPackageI18nList!=null&&hotelPackageI18nList.size()>0){
				for (int i = 0; i < hotelPackageI18nList.size(); i++) {
					if(language.equals(hotelPackageI18nList.get(i).getLanguage())){
						hotelPackageI18nList.remove(i);
						break;
					}
				}
			}
			hotelPackageVo.setHotelPackageI18nList(hotelPackageI18nList);
		}else{
			//获取没有被当前酒店绑定的包价
			packageList = this.loadDontUsePackage();
			hotelPackageVo = new HotelPackageVO();
		}
		return "toEdit";
	}
	
	/**
	 * 保存/修改
	 */
	public String save(){
		
		String hotelPackageI18ns = getRequest().getParameter("f_hotelPackageI18ns");
		List<HotelPackageI18n> hotelPackageI18nList = new ArrayList<HotelPackageI18n>();
		
		try {
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			hotelPackageVo.setLanguage(language);
			hotelPackageI18nList.add(hotelPackageManager.getDefaultLanguageI18n(hotelPackageVo));
			
			//组装多语言数据
			if(StringUtils.isNotBlank(hotelPackageI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(hotelPackageI18ns);
				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					HotelPackageI18n i18n = new HotelPackageI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setPackageName(i18nMap.get("name"));
					i18n.setDescription(i18nMap.get("description"));
					hotelPackageI18nList.add(i18n);
				}
			}
			hotelPackageVo.setHotelPackageI18nList(hotelPackageI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		hotelPackageVo.setHotelId(curHotelId);
		
		if(hotelPackageVo!=null&&StringUtils.isNotBlank(hotelPackageVo.getHotelPackageId())){
			hotelPackageManager.updateHotelPackage(hotelPackageVo);
		}else{
			hotelPackageManager.saveHotelPackage(hotelPackageVo);
		}
		
		
		return "save";
	}


	/**
	 * 删除
	 * @throws IOException 
	 */
	public String delete(){
		if(hotelPackageVo != null && StringUtils.isNotBlank(hotelPackageVo.getHotelPackageId())){
			hotelPackageManager.deleteHotelPackageById(hotelPackageVo);
			hotelPackageManager.deleteHotelPackageI18nById(hotelPackageVo.getHotelPackageId());
		}
		return "delete";
	}
	
	/**
	 * 获取报价信息
	 * @return
	 * @throws IOException 
	 */
	public void loadPackageInfo() throws IOException{
		String message = "{packageName:'',description:''}";
		String packageId = getRequest().getParameter("packageId");
		
		PackageVO packageVo = packageManager.getPackageById(packageId);
		if(packageVo!=null){
			message = "{packageName:'"+packageVo.getPackageName()+"',description:'"+packageVo.getDescription()+"'}";
		}
		
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 获取报价信息
	 * @return
	 * @throws IOException 
	 */
	public void ajaxloadPackageInfo() throws IOException{
		StringBuffer message = new StringBuffer("");
		String packageId = getRequest().getParameter("packageId");
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		List<PackageI18n> packageI18ns = packageManager.getPackageI18ns(packageId);
		if(packageI18ns!=null&&packageI18ns.size()>0){
			message.append("[");
			
			for (int i = 0; i < packageI18ns.size(); i++) {
				PackageI18n i18n = packageI18ns.get(i);
				String isDefault = "No";
				//如果与当前的默认语言相同,标识一下
				if(language.equals(i18n.getLanguage())){
					isDefault = "Yes";
				}
				
				message.append("{language:'"+this.setStringValue(i18n.getLanguage())+"',packageName:'"+this.setStringValue(i18n.getPackageName())
						+"',description:'"+this.setStringValue(i18n.getDescription())+"',isDefault:'"+isDefault+"'}");
				
				if(i<packageI18ns.size()-1){
					message.append(",");
				}
			}
			message.append("]");
		}
		
		this.getResponse().getWriter().print(message.toString());
	}
	
	/**
	 * 验证是否都已经绑定了所有的包
	 * @throws IOException 
	 */
	public void isNotHavePackage() throws IOException{
		String message = "true";
		List<PackageVO> pList = this.loadDontUsePackage();
		if(pList==null||pList.size()==0){
			message = "false";
		}
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 验证酒店包价关系是否已经绑定
	 * true:表示不存在，可以保存；false：不能保存
	 * @throws IOException 
	 */
	public void isHotelPackage() throws IOException{
		String message = "true";
		//如果传入的包ID不为空
		if(StringUtils.isNotBlank(hotelPackageVo.getPackageId())){
			//得到当前酒店ID
			B2BUser user = getCurLoginUser();
			String hotelId = user.getHotelvo().getHotelId();
			hotelPackageVo.setHotelId(hotelId);
			List<HotelPackageVO> list = hotelPackageManager.getHotelPackageByObj(hotelPackageVo);
			if(list!=null&&list.size()>0){
				message = "false#"+list.get(0).getPackageCode();
			}
		}
	
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 删除报价前验证是否已经被其他东东引用了
	 * @throws IOException 
	 */
	public void validateDeletePackage() throws IOException{
		String message = "{result:'success'}";
		boolean flag = true;  //标示可以往下执行
		
		HotelPackageVO hpvo = hotelPackageManager.getHotelPackageById(hotelPackageVo.getHotelPackageId());
		if(hpvo==null)flag = false;
		//执行正常
		if(flag){
			List<Rateplan> ratePlanList = hotelPackageManager.getUseRatePackage(hpvo.getHotelId(), hpvo.getPackageId());
			if(ratePlanList!=null&&ratePlanList.size()>0){
				message = "{result:'fail',type:'rateplan',pcode:'"+hpvo.getPackageCode()+"',code:'"+ratePlanList.get(0).getRatePlanCode()+"'}";
				flag = false;
			}
		}
		//执行正常
		if(flag){
			List<RoomType> roomTypeList = hotelPackageManager.getUseRoomPackage(hpvo.getHotelId(), hpvo.getPackageId());
			if(roomTypeList!=null&&roomTypeList.size()>0){
				message = "{result:'fail',type:'roomtype',pcode:'"+hpvo.getPackageCode()+"',code:'"+roomTypeList.get(0).getRoomTypeCode()+"'}";
				flag = false;
			}
		}
		//执行正常
		if(flag){
			List<DynamicPackage> dynamicList = hotelPackageManager.getUseDynamicPackage(hpvo.getHotelId(), hpvo.getPackageId());
			if(dynamicList!=null&&dynamicList.size()>0){
				message = "{result:'fail',type:'dynamic',pcode:'"+hpvo.getPackageCode()+"'}";
				flag = false;
			}
		}
		
		this.getResponse().getWriter().print(message);
	}
	
	/**
	 * 获取当前酒店没有利用的包
	 * @return
	 */
	public List<PackageVO> loadDontUsePackage(){
		//得到当前酒店ID
		B2BUser user = getCurLoginUser();
		String curHotelId = user.getHotelvo().getHotelId();
		//获取没有被当前酒店绑定的包价
		return hotelPackageManager.getDontUseHotelPackage(curHotelId);
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

	public HotelPackageResult getResult() {
		return result;
	}

	public void setResult(HotelPackageResult result) {
		this.result = result;
	}

	public HotelPackageCreteria getCreteria() {
		return creteria;
	}

	public void setCreteria(HotelPackageCreteria creteria) {
		this.creteria = creteria;
	}

	public List<PackageVO> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<PackageVO> packageList) {
		this.packageList = packageList;
	}

	public HotelPackageVO getHotelPackageVo() {
		return hotelPackageVo;
	}

	public void setHotelPackageVo(HotelPackageVO hotelPackageVo) {
		this.hotelPackageVo = hotelPackageVo;
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}
	
	
	
}
