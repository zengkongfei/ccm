package com.ccm.mc.webapp.action.log;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.log.SendOTALog;
import com.ccm.api.model.log.vo.SendOTALogCriteria;
import com.ccm.api.model.log.vo.SendOTALogResult;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.log.SendOTALogManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.XMLUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;

public class SendOtaLogAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4034429043858083617L;
	
	// 查询条件
	private SendOTALogCriteria soc=new SendOTALogCriteria();
	private String hotelCode;
	private String sendOTALogId;
	// 返回结果
	private SendOTALogResult sendOTALogResult=new SendOTALogResult();
	
	@Autowired
	private SendOTALogManager sendOTALogManager;
	@Resource
	private HotelManager hotelManager;
	@Resource
	private ChannelManager channelManager;
	// 渠道列表
	List<Channel> channelList;
	// 酒店列表
	private List<Hotel> hotelList=new ArrayList<Hotel>();

	public String list() {
	  
		//Transform Status/传输状态 , transform Status 就是 转换状态Conversion Status
		List<String> transformStatus = new ArrayList<String>();
		transformStatus.add("");
		transformStatus.add("SUCCESS");
		transformStatus.add("FAILED");
		this.getRequest().setAttribute("transformStatus",transformStatus);
		
		//Action/事件
		List<String> action = new ArrayList<String>();
		action.add("");
		//action.add("HEADERWITHDETAIL");
		//action.add("HEADER");
		//action.add("DETAIL");
		action.add("SYNC");
		action.add("NEW");
		action.add("CHANGE");		
		action.add("DELETE");
		action.add("NA");
		this.getRequest().setAttribute("action",action);
		
		//Status/状态
		List<String> status = new ArrayList<String>();
		status.add("");
		status.add("SUCCESS");
		status.add("FAILED");
		status.add("READY");
		this.getRequest().setAttribute("status",status);
		
		//messageType
		List<String> messageType = new ArrayList<String>();
		messageType.add("");
		messageType.add("ALLOTMENT");
		this.getRequest().setAttribute("messageType",messageType);
	
		//渠道用户渠道权限
        B2BUser user = getCurLoginUser();
        List<Channel> channels=null;
        List<String> channel = new ArrayList<String>();
        if(user!=null&&CompanyType.CHANNEL.equals(user.getCompanyId())){
            channels=user.getChannels();
            for (Channel c : channels) {
            	channel.add(c.getChannelCode());
            }
        }else{
        	channelList = channelManager.getAll();
    		for (Channel c : channelList) {
    			channel.add(c.getChannelCode());
    		}
        }
		this.getRequest().setAttribute("channel",channel);
		
		//集团代码  集团代码为CCM
		List<String> chainCode = new ArrayList<String>();
		//chainCode.add("");
		chainCode.add("CCM");
		this.getRequest().setAttribute("chainCode",chainCode);
		
		//集团代码CCM下属的酒店代码
		List<String> hotelCode = new ArrayList<String>();
		hotelList=hotelManager.getHotelByChainCode("CCM");
		
		//hotelCode.add("");
		for (Hotel h : hotelList) {
			hotelCode.add(h.getHotelCode());
		}
		this.getRequest().setAttribute("hotelCode",hotelCode);
		
		//酒店代码必填
		if (( soc.getHotelCode() == null )||( !StringUtils.hasText(soc.getHotelCode()) )) {
			//saveMessage(getText("ccm.PMSMessageLog.ErrorMessage.PleasePropertyCode"));
			return "list";
		}
	
		// 分页
		int pageSize = this.getCurrentPageSize("order");
		int pageNo = this.getCurrentPageNo("order");
		soc.setPageNum(pageNo);
		soc.setPageSize(pageSize);
		
		sendOTALogResult=sendOTALogManager.searchList(soc);
		
		//在查询结果“注释”这一栏，当转换状态和发送状态为SUCCESS时候，注释一栏下面为空
		List<SendOTALog> otaLogs=sendOTALogResult.getResultList();
		for (SendOTALog sendOTALog : otaLogs) {
			//转换状态
			String tStatus=sendOTALog.getTransformStatus();
			//发送状态
			String sStatus=sendOTALog.getStatus();
			if( ("SUCCESS".equals(tStatus)) && ("SUCCESS".equals(sStatus)) ){
				sendOTALog.setComments("");
			}
		}
		
		this.getRequest().setAttribute("resultSize", sendOTALogResult.getTotalCount());
		
		return "list";
	}
	
	public String detail() {		
		SendOTALog sol=sendOTALogManager.getOTALogById(hotelCode, sendOTALogId);
		try {
			if(sol!=null){
				sol.setMessage(XMLUtil.formatXml(sol.getMessage()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getRequest().setAttribute("sendOTALog", sol);
		return "detail";
	}
	
	private List<String[]> getExcelDataByObjList(List<SendOTALog> sendOTALogList) {
		List<String[]> dataList = new ArrayList<String[]>();
		
		if (sendOTALogList != null && sendOTALogList.size() > 0) {
			for (SendOTALog sendOTALog : sendOTALogList) {
				String[] arr = new String[14];
				
				arr[0] = sendOTALog.getChannelCode();
				arr[1] = sendOTALog.getChainCode();
				arr[2] = sendOTALog.getHotelCode();
				arr[3] = DateUtil.convertDateTimeToString(sendOTALog.getCreatedTime());
				arr[4] = DateUtil.convertDateTimeToString(sendOTALog.getLastModifyTime());
				arr[5] = sendOTALog.getAction();
				arr[6] = sendOTALog.getTransformStatus();
				arr[7] = sendOTALog.getStatus();
				arr[8] = sendOTALog.getOxiTrxId();
				arr[9] = sendOTALog.getMessageType();
				arr[10] = sendOTALog.getComments();
				arr[11] = sendOTALog.getRatePlanCode();
				arr[12] = sendOTALog.getRoomTypeCode();
				arr[13] =  DateUtil.convertDateToString(sendOTALog.getStartDate());
				dataList.add(arr);
			}
		}
		return dataList;
	}
	
	public String export() throws IOException {
		
		// 设置导出参数
		soc.setNeedPage(Boolean.FALSE);
		
		try {
			// 查询数据
			sendOTALogResult=sendOTALogManager.searchList(soc);
			
			//在查询结果“注释”这一栏，当转换状态和发送状态为SUCCESS时候，注释一栏下面为空
			List<SendOTALog> otaLogs=sendOTALogResult.getResultList();
			for (SendOTALog sendOTALog : otaLogs) {
				//转换状态
				String tStatus=sendOTALog.getTransformStatus();
				//发送状态
				String sStatus=sendOTALog.getStatus();
				if( ("SUCCESS".equals(tStatus)) && ("SUCCESS".equals(sStatus)) ){
					sendOTALog.setComments("");
				}
			}
			
			// excel表头
			String[] colName = { 
					getText("ccm.Channel.ChannelCode"),
					getText("ccm.BasicConfiguration.ChainCode"),
					getText("ccm.ReservationMonitorReport.PropertyCode"),
					getText("ccm.ReservationMonitorReport.CreatedTime"),
					getText("ccm.OTALog.LastModifyTime"),
					getText("ccm.OTALog.Action"),
					getText("ccm.OTALog.TransformStatus"),
					getText("ccm.OTALog.Status"),					
					getText("ccm.OTALog.OxiTrxId"),
					getText("ccm.OTALog.MessageType"),	
					getText("ccm.OTALog.Comments"),
					getText("ccm.otalog.RatePlanCode"),
					getText("ccm.otalog.RoomTypeCode"),	
					getText("ccm.otalog.RoomTypeAllocationStart")};
			
			// excel要导出的数据
			List<String[]> dataList = getExcelDataByObjList(sendOTALogResult.getResultList());
			
			SXSSFWorkbook workbook = ExportUtil.createExcel2("orderProductCollectList", colName, dataList);

			// 生成excel文件名
			String excelName = ExportUtil.createFileName(getText("Send OTA Log"));
			// 设置导出的excel文件从页面中下载
			getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + new String(excelName.getBytes(), "ISO8859-1") + ".xlsx" + "\"");

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
	
	public String getExportFileName() {
		return ExportUtil.createFileName("export");
	}

	public InputStream getTxtFile() throws Exception {
		try {
				SendOTALog sol = sendOTALogManager.getOTALogById(hotelCode, sendOTALogId);
				ByteArrayInputStream bais = new ByteArrayInputStream(XMLUtil.formatXml(sol.getMessage()).getBytes("UTF-8"));
				return bais;
		} catch (Exception e) {
			log.info(e);
		}
		return null;
	}
	
	public SendOTALogCriteria getSoc() {
		return soc;
	}
	public void setSoc(SendOTALogCriteria soc) {
		this.soc = soc;
	}
	public SendOTALogResult getSendOTALogResult() {
		return sendOTALogResult;
	}
	public void setSendOTALogResult(SendOTALogResult sendOTALogResult) {
		this.sendOTALogResult = sendOTALogResult;
	}
	public SendOTALogManager getSendOTALogManager() {
		return sendOTALogManager;
	}
	public void setSendOTALogManager(SendOTALogManager sendOTALogManager) {
		this.sendOTALogManager = sendOTALogManager;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getSendOTALogId() {
		return sendOTALogId;
	}

	public void setSendOTALogId(String sendOTALogId) {
		this.sendOTALogId = sendOTALogId;
	}

	public HotelManager getHotelManager() {
		return hotelManager;
	}

	public void setHotelManager(HotelManager hotelManager) {
		this.hotelManager = hotelManager;
	}

	public ChannelManager getChannelManager() {
		return channelManager;
	}

	public void setChannelManager(ChannelManager channelManager) {
		this.channelManager = channelManager;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	public List<Hotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}
		
}