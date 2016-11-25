package com.ccm.mc.webapp.action.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.rsvtype.Rent;
import com.ccm.api.model.rsvtype.vo.RentSearchResult;
import com.ccm.api.model.rsvtype.vo.SearchRentCriteria;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.rsvtype.RsvtypeManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;

public class HotelRoomRentRateAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6175521680775413849L;
	
	private Log log = LogFactory.getLog(HotelRoomRentRateAction.class);
	
	// 查询条件
	private SearchRentCriteria src = new SearchRentCriteria();
	
	//返回结果
	private RentSearchResult rentSearchResult = new RentSearchResult();
	
	@Resource
	private HotelManager hotelManager;
	
	@Resource
	private HotelMCManager hotelMCManager;
	
	@Resource
	private RsvtypeManager rsvtypeManager;
	
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
		// 分页
		int pageSize = this.getCurrentPageSize("hotelRoomRents");
		int pageNo = this.getCurrentPageNo("hotelRoomRents");
		src.setPageNum(pageNo);
		src.setPageSize(pageSize);
		
		//设置查询参数
		src.setExcelFlag(Boolean.FALSE);
		log.info(src.toString());
		rentSearchResult = rsvtypeManager.searchHotelRoomRentRates(src);
		
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
	
	/**
	 * 导出
	 * @return
	 * @throws IOException 
	 */
	public String export() throws IOException{
		//导出excel标识
		src.setExcelFlag(Boolean.TRUE);
		src.setNeedPage(Boolean.FALSE);
		try{
			rentSearchResult = rsvtypeManager.searchHotelRoomRentRates(src);
			
			// excel表头
			String[] colName = { getText("ccm.PropertyRoomOccupancyReport.TrainingFacilitationServicesSupervisor"),
					getText("ccm.ReservationMonitorReport.PropertyCode"),
					getText("common.date"),
					getText("ccm.PropertyRoomOccupancyReport.Occupancy") };
			
			// excel要导出的数据
			List<String[]> dataList = this.getExcelDataByRentList(rentSearchResult.getResultList());
			
			SXSSFWorkbook workbook = ExportUtil.createExcel2("hotelRoomRentRateList", colName, dataList);
			
			// 生成excel文件名
			String excelName = ExportUtil.createFileName(getText("ccm.PropertyRoomOccupancyReport"));
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

	/**
	 * 组装数据excel数组
	 * @param resultList
	 * @return
	 */
	private List<String[]> getExcelDataByRentList(List<Rent> rentList) {
		List<String[]> dataList = new ArrayList<String[]>();
		
		//如果订单结果不为空
		if(rentList!=null && rentList.size()>0){
			for (Rent rent : rentList) {
				String[] arr = new String[4];
				arr[0] = rent.getSpecialist();
				arr[1] = rent.getHotelCode();
				arr[2] = DateUtil.convertDateToString(rent.getDate());
				arr[3] = rent.getRentRate()+"%";
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

	public SearchRentCriteria getSrc() {
		return src;
	}

	public void setSrc(SearchRentCriteria src) {
		this.src = src;
	}

	public RentSearchResult getRentSearchResult() {
		return rentSearchResult;
	}

	public void setRentSearchResult(RentSearchResult rentSearchResult) {
		this.rentSearchResult = rentSearchResult;
	}
	
	
}
