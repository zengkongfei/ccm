package com.ccm.mc.webapp.action.report;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.CustomReportRecord;
import com.ccm.api.model.order.vo.DepositSearchResult;
import com.ccm.api.model.order.vo.SearchDepositCriteria;

import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.service.hotel.CustomManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;

public class BookingDepositAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5027517239719215025L;

	/**
	 * 
	 */
	@Resource
	private HotelManager hotelManager;
	@Resource
	private ChainManager chainManager;
	@Resource
	private HotelMCManager hotelMCManager;
	@Resource
	private CustomManager customManager;
	// 酒店列表
	private List<Hotel> hotelList;
	// 订单状态
	// 集团列表
	private List<Chain> chainList;
	// 渠道列表
	private List<Channel> channelList = new ArrayList<Channel>();
	
	private DepositSearchResult depositSearchResult=new DepositSearchResult();
	
	private SearchDepositCriteria sdc=new SearchDepositCriteria();
	
	public DepositSearchResult getDepositSearchResult() {
		return depositSearchResult;
	}
	public void setDepositSearchResult(DepositSearchResult depositSearchResult) {
		this.depositSearchResult = depositSearchResult;
	}
	public SearchDepositCriteria getSdc() {
		return sdc;
	}
	public void setSdc(SearchDepositCriteria sdc) {
		this.sdc = sdc;
	}
	public String list(){
		setInitCondition("1");
		return "list";
	}
	
	public String export()throws Exception{	
		try {
			// 设置导出参数
			sdc.setExcelFlag(Boolean.FALSE);
			sdc.setNeedPage(Boolean.FALSE);
			depositSearchResult=customManager.queryBookingDepositReport(sdc);
			
			Collections.sort(depositSearchResult.getResultList(),new Comparator<CustomReportRecord>() {
				 /**
			     * 如果m1小于m2,返回一个负数;如果m1大于m2，返回一个正数;如果他们相等，则返回0;
			     */
			    @Override
			    public int compare(CustomReportRecord m1, CustomReportRecord m2) {   
			        //降序排序
			    	StringBuffer sb1=new StringBuffer();
			    	sb1.append(m1.getChainCode());
			    	sb1.append(m1.getHotelCode());
			    	sb1.append(m1.getName());
			    	sb1.append(m1.getAccessCode());
			    	sb1.append(m1.getCorpIATANo());
			    	
			    	StringBuffer sb2=new StringBuffer();
			    	sb2.append(m2.getChainCode());
			    	sb2.append(m2.getHotelCode());
			    	sb2.append(m2.getName());
			    	sb2.append(m2.getAccessCode());
			    	sb2.append(m2.getCorpIATANo());
			        return sb1.toString().compareTo(sb2.toString());
			    }
			});
			
			// excel表头
			String[] colName = {getText("ccm.BasicConfiguration.ChainCode"),
					getText("ccm.ReservationMonitorReport.PropertyCode"), 
					getText("ccm.ProfileList.ProfileName"),
					getText("ccm.BookingDepositReport.AccessCode"),
					getText("ccm.BookingDepositReport.CorpIATANo"),
					getText("ccm.BookingDepositReport.OriginalCredit"),
					getText("ccm.BookingDepositReport.MinCredit"),
					getText("ccm.BookingDepositReport.TotalRoomRev"),
					getText("ccm.BookingDepositReport.Income"),
					getText("ccm.BookingDepositReport.Balance")};
			
			// excel要导出的数据
			List<String[]> dataList = this.getExcelDataByCustomReportRecordList(depositSearchResult.getResultList());

			SXSSFWorkbook workbook = ExportUtil.createExcel2("bookingDepositReport", colName, dataList);

			// 生成excel文件名
			String excelName = ExportUtil.createFileName(getText("ccm.BookingDepositReport"));
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
		this.setInitCondition("0");
		return null;
	}
	public String query(){
		
		// 分页
		int pageSize = this.getCurrentPageSize("depositResultTable");
		int pageNo = this.getCurrentPageNo("depositResultTable");
		sdc.setPageNum(pageNo);
		sdc.setPageSize(pageSize);

		// 设置查询参数
		sdc.setExcelFlag(Boolean.FALSE);
		sdc.setNeedPage(Boolean.TRUE);
		depositSearchResult=customManager.queryBookingDepositReport(sdc);
		this.setInitCondition("0");
		return "list";
	}

	private void setInitCondition(String isInit) {
		// 初始化查询条件
		chainList = chainManager.getAllChain();
		if (chainList != null && chainList.size() > 0) {
			// 得到当前酒店ID
			B2BUser user = getCurLoginUser();
			// 如果是运营人员
			if (user != null && CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
				hotelList = hotelManager.getHotelByChainId(chainList.get(0).getChainId());
			} else {
				hotelList = hotelMCManager.getUserHotelByChainAndUserId(chainList.get(0).getChainId(), user.getUserId());
			}
		}
		//渠道代码
		channelList = getCurLoginUser().getChannels();
		getRequest().setAttribute("channelList",channelList);
		
		// 设置初始化标识
		getRequest().setAttribute("isInit", isInit);
	}
	public List<Hotel> getHotelList() {
		return hotelList;
	}
	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}
	public List<Chain> getChainList() {
		return chainList;
	}
	public void setChainList(List<Chain> chainList) {
		this.chainList = chainList;
	}
	
	private List<String[]> getExcelDataByCustomReportRecordList(List<CustomReportRecord> recordList) {
		List<String[]> dataList = new ArrayList<String[]>();
		// 如果订单结果不为空
		if (recordList != null && recordList.size() > 0) {
			for (CustomReportRecord record : recordList) {
				String[] arr = new String[19];
				arr[0] = record.getChainCode();
				arr[1] = record.getHotelCode();
				arr[2] = record.getName();
				arr[3] = record.getAccessCode();
				arr[4] = record.getCorpIATANo();
				arr[5] = record.getOriginalCreditLimit();
				arr[6] = record.getMinLimit();
				arr[7] = record.getTotalRoomRev();
				arr[8] = record.getIncome();
				arr[9] = record.getBalance();
				dataList.add(arr);
			}
		}
		return dataList;
	}
}
