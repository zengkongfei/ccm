package com.ccm.mc.webapp.action.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.vo.OrderSearchResult;
import com.ccm.api.model.order.vo.SearchOrderCriteria;
import com.ccm.api.service.order.OrderManager;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;
import com.opensymphony.xwork2.Preparable;

public class ExcelAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	protected final Log log = LogFactory.getLog(this.getClass().getName());

	/**
	 * 查询条件
	 */

	private String exportFileName; // 文件名
	private SearchOrderCriteria soc = new SearchOrderCriteria();

	@Resource
	private OrderManager orderManager;

	public String getExportFileName() {
		return exportFileName;
	}

	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

	public SearchOrderCriteria getSoc() {
		return soc;
	}

	public void setSoc(SearchOrderCriteria soc) {
		this.soc = soc;
	}

	public void prepare() {

	}

	/**
	 * 预订监控报表导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String orderExport() throws Exception {
		exportFileName = ExportUtil.createFileName(null);
		return "orderExcelExport";
	}

	/**
	 * 预订监控报表导出
	 * 
	 * @return
	 */
	public InputStream getOrderExcelFile() {

		soc.setExcelFlag(true);
		soc.setNeedPage(false);
		OrderSearchResult result = orderManager.searchOrder(soc);
		List<Master> mList = result.getResultList();

		HSSFWorkbook workbook = new HSSFWorkbook();
		//订单信息
		HSSFSheet sheet = workbook.createSheet(getText("ccm.ReservationMonitorReport.OrderInformation"));
		// 创建表头
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell cell = titleRow.createCell(0);
		int i = 0;
		HSSFRow headerRow = sheet.createRow(0);

		String[] colName = { getText("ccm.ReservationMonitorReport.PropertyCode"), 
				getText("ccm.InventoryManagement.Channels"), 
				getText("ccm.ReservationsManagment.CRSNo"), 
				getText("ccm.ReservationMonitorReport.ReservationStatus"), 
				getText("ccm.GuaranteeRules.GuaranteeType"), 
				getText("ccm.ReservationMonitorReport.Arrival"), 
				getText("ccm.ReservationMonitorReport.Departure"), 
				getText("ccm.ReservationMonitorReport.CreatedTime"), 
				getText("ccm.Channel.RoomTypeCode"), 
				getText("ccm.ReservationsManagment.Comments") };
		for (String name : colName) {
			cell = headerRow.createCell(i++);
			cell.setCellValue(name);
		}

		int rowIndex = 1;

		for (int n = 0; n < mList.size(); n++) {

			HSSFRow valueRow = sheet.createRow(rowIndex);
			i = 0;

			Master m = mList.get(n);

			cell = valueRow.createCell(i++);
			cell.setCellValue(m.getHotelCode());

			cell = valueRow.createCell(i++);
			cell.setCellValue(m.getChannel());

			cell = valueRow.createCell(i++);
			cell.setCellValue(m.getMasterId());

			cell = valueRow.createCell(i++);
			cell.setCellValue(m.getSta());

			cell = valueRow.createCell(i++);
			cell.setCellValue(m.getPayment());

			cell = valueRow.createCell(i++);
			cell.setCellValue(DateUtil.getDate(m.getArr()));

			cell = valueRow.createCell(i++);
			cell.setCellValue(DateUtil.getDate(m.getDep()));

			cell = valueRow.createCell(i++);
			cell.setCellValue(DateUtil.convertDateTimeToString(m.getChanged()));

			cell = valueRow.createCell(i++);
			cell.setCellValue(m.getType());

			cell = valueRow.createCell(i++);
			cell.setCellValue(m.getRef());

			rowIndex++;

		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			workbook.write(baos);
		} catch (IOException e) {

			e.printStackTrace();
		}

		byte[] ba = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(ba);
		return bais;

	}
}