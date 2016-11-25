package com.ccm.mc.webapp.action.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.ratePlan.PriceValid;
import com.ccm.api.model.ratePlan.vo.PriceValidSearchResult;
import com.ccm.api.model.ratePlan.vo.SearchPriceValidCriteria;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.ratePlan.RateDetailManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;
import com.opensymphony.xwork2.ActionContext;

public class PriceValidTimeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6199413934881748611L;
	
	private Log log = LogFactory.getLog(PriceValidTimeAction.class);
	
	// 查询条件
	private SearchPriceValidCriteria spvc = new SearchPriceValidCriteria();
	
	//返回结果
	private PriceValidSearchResult priceSearchResult = new PriceValidSearchResult();
	
	@Resource
	private HotelManager hotelManager;
	
	@Resource
	private HotelMCManager hotelMCManager;
	
	@Resource
	private RateDetailManager rateDetailManager;
	
	//酒店列表
	private List<Hotel> hotelList;
	
	/**
	 * 初始化
	 * @return
	 */
	public String list(){
		//初始化查询条件
		B2BUser user = getCurLoginUser();
		//如果是运营人员
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hotelList = hotelManager.getAll();
		}else{
			hotelList = hotelMCManager.getUserHotelByChainAndUserId(user.getUserId());
		}
		
		//设置初始化标识
		getRequest().setAttribute("isInit", "1");
		
		return "list";
	}

	/**
	 * 查询
	 * @return
	 */
	public String query(){
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		// 分页
		int pageSize = this.getCurrentPageSize("priceValidTimes");
		int pageNo = this.getCurrentPageNo("priceValidTimes");
		spvc.setPageNum(pageNo);
		spvc.setPageSize(pageSize);
		
		//设置查询参数
		spvc.setExcelFlag(Boolean.FALSE);
		log.info(spvc.toString());
		spvc.setLanguage(language);
		
		
		priceSearchResult = rateDetailManager.searchPriceValidTimes(spvc);
		updateWeeks(priceSearchResult);
		//初始化查询条件
		B2BUser user = getCurLoginUser();
		//如果是运营人员
		if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
			hotelList = hotelManager.getAll();
		}else{
			hotelList = hotelMCManager.getUserHotelByChainAndUserId(user.getUserId());
		}
		
		return "list";
	}
	
	private void updateWeeks(PriceValidSearchResult priceSearchResult){
		for(PriceValid p :priceSearchResult.getResultList()){
			String w = p.getWeeks();
			if(w.contains("一")){
				w = w.replaceAll("一", getText("calendar.week.monday"));
			}
			if(w.contains("二")){
				w = w.replaceAll("二", getText("calendar.week.tuesday"));
			}
			if(w.contains("三")){
				w = w.replaceAll("三", getText("calendar.week.wednesday"));
			}
			if(w.contains("四")){
				w = w.replaceAll("四", getText("calendar.week.thursday"));
			}
			if(w.contains("五")){
				w = w.replaceAll("五", getText("calendar.week.friday"));
			}
			if(w.contains("六")){
				w = w.replaceAll("六", getText("calendar.week.saturday"));
			}
			if(w.contains("日")){
				w = w.replaceAll("日", getText("calendar.week.sunday"));
			}
			p.setWeeks(w);
		}
	}
	
	
	/**
	 * 导出
	 * @return
	 * @throws IOException 
	 */
	public String export() throws IOException{
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		//导出excel标识
		spvc.setExcelFlag(Boolean.TRUE);
		spvc.setLanguage(language);
		try{
			priceSearchResult = rateDetailManager.searchPriceValidTimes(spvc);
			updateWeeks(priceSearchResult);
			// excel表头
			String[] colName = { getText("ccm.PropertyRoomOccupancyReport.TrainingFacilitationServicesSupervisor"),
					getText("ccm.ReservationMonitorReport.PropertyCode"),
					getText("ccm.RestrictionsManagement.RateCode"),
					getText("ccm.RateValidityPeriodReport.RateName"),
					getText("ccm.Channel.RoomTypeCode"),
					getText("ccm.RateValidityPeriodReport.RoomTypeName"),
					getText("ccm.Rates.Weeks"),
					getText("common.time.BeginDate"),
					getText("common.time.EndDate") };
			
			// excel要导出的数据
			List<String[]> dataList = this.getExcelDataByPriceList(priceSearchResult.getResultList());
			
			SXSSFWorkbook workbook = ExportUtil.createExcel2("priceValidTimesList", colName, dataList);
			
			// 生成excel文件名
			String excelName = ExportUtil.createFileName(getText("ccm.RateValidityPeriodReport"));
			// 设置导出的excel文件从页面中下载
			getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");
			// getResponse().setContentType("application/vnd.ms-excel;charset=gb2312");
	
			// 输出流
			OutputStream os = getResponse().getOutputStream();
	
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			getResponse().getWriter().print(CommonUtil.getExceptionMsg(e, "export fail"));
			getResponse().getWriter().flush();
		}
		
		return null;
	}

	private List<String[]> getExcelDataByPriceList(List<PriceValid> resultList) {
		List<String[]> dataList = new ArrayList<String[]>();
		
		//如果订单结果不为空
		if(resultList!=null && resultList.size()>0){
			for (PriceValid priceValid : resultList) {
				String[] arr = new String[9];
				arr[0] = priceValid.getSpecialist();
				arr[1] = priceValid.getHotelCode();
				arr[2] = priceValid.getRatePlanCode();
				arr[3] = priceValid.getRatePlanName();
				arr[4] = priceValid.getRoomTypeCode();
				arr[5] = priceValid.getRoomTypeName();
				arr[6] = priceValid.getWeeks();
				arr[7] = DateUtil.convertDateToString(priceValid.getEffectiveDate());
				arr[8] = DateUtil.convertDateToString(priceValid.getExpireDate());
				
				dataList.add(arr);
			}
		}
		return dataList;
	}

	public List<Hotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	public SearchPriceValidCriteria getSpvc() {
		return spvc;
	}

	public void setSpvc(SearchPriceValidCriteria spvc) {
		this.spvc = spvc;
	}

	public PriceValidSearchResult getPriceSearchResult() {
		return priceSearchResult;
	}

	public void setPriceSearchResult(PriceValidSearchResult priceSearchResult) {
		this.priceSearchResult = priceSearchResult;
	}

	
	
}
