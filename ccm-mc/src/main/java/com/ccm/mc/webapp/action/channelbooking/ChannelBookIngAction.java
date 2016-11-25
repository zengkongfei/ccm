package com.ccm.mc.webapp.action.channelbooking;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterChangesLog;
import com.ccm.api.model.order.MasterOrder;
import com.ccm.api.model.order.MasterRate;
import com.ccm.api.model.order.vo.OrderSearchResult;
import com.ccm.api.model.order.vo.SearchOrderCriteria;
import com.ccm.api.model.ows.vo.OrderDailyRateVO;
import com.ccm.api.model.ows.vo.OrderReservationRetVO;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.wbe.EffectiveGuarrntee;
import com.ccm.api.model.wbe.WbeCalendarCell;
import com.ccm.api.model.wbe.WbeCalendarRow;
import com.ccm.api.model.wbe.WbeGuestVO;
import com.ccm.api.model.wbe.WbeOrderVO;
import com.ccm.api.model.wbe.WbeSearchCreteria;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.common.RemindManager;
import com.ccm.api.service.hotel.CustomManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.hotel.HotelSwitchManager;
import com.ccm.api.service.order.OrderChangesLogManager;
import com.ccm.api.service.order.OrderManager;
import com.ccm.api.service.order.ReservationService;
import com.ccm.api.service.ratePlan.RateAmountManager;
import com.ccm.api.service.wbe.channelBooking.ChannelBookIngReservationManager;
import com.ccm.api.service.wbe.channelBooking.ChannelBookingManager;
import com.ccm.api.util.AmountUtil;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionContext;

public class ChannelBookIngAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1981169998154965405L;
	private Log log = LogFactory.getLog(ChannelBookIngAction.class);
	
	private WbeSearchCreteria wbeSearchCreteria;
	private ChannelGoodsVO channelGoodsVO;
	private WbeOrderVO wbeOrderVO;
	private String orderNo;
	private Master order;
	private List<MasterRate> priceDetail;
	// 查询条件
	private SearchOrderCriteria soc = new SearchOrderCriteria();
	
	// 返回结果
	private OrderSearchResult orderSearchResult = new OrderSearchResult();
	@Autowired
	private ChannelBookingManager channelBookingManager;
	@Autowired
	private ChannelgoodsManager channelgoodsManager;
	@Autowired
	private CustomManager customManager;
	@Autowired
	private ChannelBookIngReservationManager channelBookIngReservationManager;
	@Resource
	private RemindManager remindManager;
	@Autowired
	private OrderManager orderManager;
	@Resource
	private ReservationService reservationService;
	@Autowired
	private HotelSwitchManager hotelSwitchManager;
	
	private String hotelId;
	@Resource
	private RateAmountManager rateAmountManager;
	@Resource
	private OrderChangesLogManager orderChangesLogManager;
	@Autowired
	private HotelManager hotelManager;
	
	/**
	 * 查询预定
	 * @return
	 */
	public String list(){
		try {
			B2BUser user = getCurLoginUser();
			Channel channel = user.getChannels().get(0);
			soc.setChannelCode(channel.getChannelCode());
			// 列出当前用户选择的酒店
			if (user.getHotelvo() == null || !StringUtils.hasText(user.getHotelvo().getHotelId())) {
				log.info("noSelectHotel");
				return "list";
			}
			String hotelId = user.getHotelvo().getHotelId();

			// 分页
			int pageSize = this.getCurrentPageSize("order");
			int pageNo = this.getCurrentPageNo("order");
			soc.setPageNum(pageNo);
			soc.setPageSize(pageSize);

			// 是从订单提醒过来的则更新状态
			if (StringUtils.hasLength(from)) {
				List<String> masterIds = (List<String>) getSession().getAttribute(hotelId + from);
				if (masterIds != null) {
					soc.setMasterIdList(masterIds);
					soc.setHotelId(hotelId);
				}
				remindManager.updateHaveSeen(hotelId, from);
			}

			if (StringUtils.hasText(soc.getHotelId())) {
				orderSearchResult = orderManager.searchOrder(soc);
			}
			// 进入订单页面，默认查询条件为空，无需显示任何内容
			soc.setHotelId(hotelId);
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		this.getRequest().setAttribute("currentTime", DateUtil.getCleanDate(new Date()));
		this.getRequest().setAttribute("resultSize", orderSearchResult.getTotalCount());
		return "list";
	}
	
	/**
	 * 订单详情
	 */
	public String detail() {
		if (StringUtils.hasText(orderNo)) {
			findOrderInfo();

		} else {
			order = new Master();
		}

		return "detail";
	}
	
	/**
	 * 订单详情及打印页的公共部分
	 */
	private void findOrderInfo() {

		order = orderManager.getOrderByOrderNo(orderNo);
		
		//信用卡是否掩码
		String cardNumber = order.getCardNumber();
		if(StringUtils.hasText(cardNumber)&&cardNumber.length()>4){
			B2BUser user = getCurLoginUser();
			HotelVO hotelVO=user.getHotelvo();
			String hotelId=hotelVO.getHotelId();
			HotelSwitch hotelSwitch=hotelSwitchManager.getByHotelId(hotelId);
			StringBuffer x = new StringBuffer();
			if(hotelSwitch!=null&&hotelSwitch.getIsMask()!=null && hotelSwitch.getIsMask()){
				for(int i=0;i<cardNumber.length()-4;i++){
					x.append("X");
				}
				order.setCardNumber(cardNumber.substring(0, 4)+x.toString());
			}
		}

		try {
			priceDetail = orderManager.getMasterRateByOrderNo(orderNo);
			getRequest().setAttribute("packageDetail", orderManager.getMasterPackageByOrderNo(orderNo));
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	/**
	 * 重新同步订单到PMS
	 * 
	 * @return
	 */
	public String resync() {
		try {
			Master master = orderManager.getOrderByOrderNo(orderNo);
			if (OrderStatus.ADD.equals(master.getSta())) {
				master.setSta(OrderStatus.EDIT);
			}
			reservationService.buildReservation(master);
			ajaxResponse("true");
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
			ajaxResponse("false");
		}
		return null;
	}
	
	public String save() {
		try {
			MasterOrder mo = new MasterOrder();
			mo.setMasterId(order.getMasterId());
			mo.setPmsId(order.getPmsId());
			orderManager.updateMasterOrderPmsId(mo);
			ajaxResponse("true");
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
			ajaxResponse("false");
		}
		return null;
	}
	
	/**
	 * 预定---查询界面
	 * @return
	 */
	public String bookPage(){
		B2BUser user = getCurLoginUser();
		//USER 权限的channel  hotelVOList
		Channel channel = user.getChannels().get(0);
		List<HotelVO> hotelVOList = user.getHotelVOs();
		
		getRequest().setAttribute("channel", channel);
		getRequest().setAttribute("hotelVOList", hotelVOList);
		return "bookPage";
	}
	
	/**
	 * 获取房型列表
	 * ajax request
	 * @return
	 */
	public void getRoomTypeList(){
		try {
			channelGoodsVO.setLanguage(getLanguage());
			B2BUser user = getCurLoginUser();
			//USER 权限的channel  hotelVOList
			Channel channel = user.getChannels().get(0);
			channelGoodsVO.setChannelId(channel.getChannelId());
			channelGoodsVO.setStatus(ChannelGoodsStatus.publish);
			
			List<ChannelGoodsVO> channelGoodsVOList = channelgoodsManager.getChannelGoodsVORoomTypeListByChannelGoods(channelGoodsVO);
			String channelGoodsVOListJsonArr = JSON.toJSONString(channelGoodsVOList);
			getResponse().getWriter().write(channelGoodsVOListJsonArr);
		} catch (IOException e) {
			log.error("ChannelBookIngAction.getRoomTypeList()  error");
			log.error(e);
		}
	}
	
	/**
	 * 预定---查询
	 * ajax request
	 * @return
	 */
	public void bookSearch(){
		try {
			String roomTypeIdStrs = getRequest().getParameter("roomTypeIds");
			String[] roomTypeIdStrList = roomTypeIdStrs.split(",");
			List<String> roomTypeIds = new ArrayList<String>();
			for(String roomTypeId :roomTypeIdStrList){
				if(StringUtils.hasLength(roomTypeId)){
					roomTypeIds.add(roomTypeId);
				}
			}
			wbeSearchCreteria.setRoomTypeIds(roomTypeIds);
			wbeSearchCreteria.setLanguage(getLanguage());
			B2BUser user = getCurLoginUser();
			//USER 权限的channel  hotelVOList
			Channel channel = user.getChannels().get(0);
			wbeSearchCreteria.setChannelCode(channel.getChannelCode());
			wbeSearchCreteria.setChannelId(channel.getChannelId());
			if(!StringUtils.hasLength(wbeSearchCreteria.getAccessCode())){
				wbeSearchCreteria.setAccessCode(channel.getChannelCode());
			}
			
			Map<String, List<WbeCalendarRow>> map = channelBookingManager.getBookingCanlendar(wbeSearchCreteria);
			String channelGoodsVOListJsonArr = JSON.toJSONString(map);
			List<String> dateList = DateUtil.getDays(wbeSearchCreteria.getArrDate(), wbeSearchCreteria.getDepDate());
			dateList.remove(dateList.size()-1);
			JSONObject json = new JSONObject();
			json.put("dateList", dateList);
			json.put("channelGoodsVOListJsonArr", channelGoodsVOListJsonArr);
			
			getResponse().getWriter().write(json.toJSONString());
		} catch (Exception e) {
			log.error("ChannelBookIngAction.bookSearch()  error");
			log.error(e);
		}
	}
	
	
	/**
	 * 预定---信息
	 * @return
	 */
	public String bookInfo(){
		try {
			String channelGoodsVOListJsonArr = getRequest().getParameter("channelGoodsVOListJsonArr");
			B2BUser user = getCurLoginUser();
			Channel channel = user.getChannels().get(0);
			String accesscode = wbeOrderVO.getAccessCode();
			wbeOrderVO.setChannelCode(channel.getChannelCode());
			wbeOrderVO.setChannelId(channel.getChannelId());
			wbeOrderVO.setCreateUDFBy(user.getUsername());
			Map<String, List<JSONObject>> resultMap=JSONObject.toJavaObject(JSONObject.parseObject(channelGoodsVOListJsonArr), Map.class);
			
			List<JSONObject>  webCalendarRowList=resultMap.get(wbeOrderVO.getRoomTypeCode());
			
			Date endDate=DateUtil.addDays(wbeOrderVO.getDep(),-1);
			for(JSONObject jsonCalendarRow:webCalendarRowList){
				//WbeCalendarRow wbeCalendarRow=JSONObject.toJavaObject(jsonCalendarRow,WbeCalendarRow.class);
				if((jsonCalendarRow.getString("inventType").equals(wbeOrderVO.getInventType())
						&&jsonCalendarRow.getString("ratePlanCode").equalsIgnoreCase(wbeOrderVO.getRatePlanCode()))==false)
					continue;
				
				JSONObject jsonCalendarCellMap=jsonCalendarRow.getJSONObject("cellMap");
//				rateAmountManager.getAmountAfterTaxForJSON(channel.getChannelCode(),wbeOrderVO.getRatePlanCode(),wbeOrderVO.getHotelCode(),jsonCalendarCellMap,endDate);
				rateAmountManager.getAmountAfterTaxWithDetailPackForJSON(channel,wbeOrderVO.getRatePlanCode(),wbeOrderVO.getHotelCode(),wbeOrderVO.getRoomTypeCode(),
							jsonCalendarCellMap,endDate);
				
				jsonCalendarRow.put("cellMap", jsonCalendarCellMap);
			}
			//未填写accesscode ，默认使用channelCode作为accesscode
			if(!StringUtils.hasText(accesscode)){
				wbeOrderVO.setAccessCode(channel.getChannelCode());
			}
			
			wbeOrderVO.setCurrencyCode(hotelManager.getHotel(wbeOrderVO.getHotelId()).getCurrencyCode());
			
			getRequest().setAttribute("wbeOrderVO", wbeOrderVO);
			getRequest().setAttribute("dateList", JSON.toJSONString(wbeOrderVO.getDateList()));
			channelGoodsVOListJsonArr =JSONObject.toJSON(resultMap).toString();
			getRequest().setAttribute("channelGoodsVOListJsonArr",channelGoodsVOListJsonArr);
			System.out.println("Booking DATA result=>"+channelGoodsVOListJsonArr);
		} catch (Exception e) {
			log.error("ChannelBookIngAction.bookInfo()  error");
			log.error(e);
		}
		
		return "bookInfo";
	}

	//判断数组中是否有重复值
	private boolean checkRepeat(String[] array){
	  Set<String> set = new HashSet<String>();
	  for(String str : array){
		  if(StringUtils.hasText(str)){
			  set.add(str);
		  }
	  }
	  if(set.size() != array.length){
	    return false;//有重复
	  }else{
	    return true;//不重复
	  }
	}

	/**
	 * 新建预定
	 * @return
	 */
	public String booking(){
		try {
			Custom custom = customManager.searchCustomByHotelIdAndAccessCode(wbeOrderVO.getHotelId(), wbeOrderVO.getAccessCode());
			if(custom==null){
				saveMessage("客户协议代码错误");
				return null;
			}
			wbeOrderVO.setAccessCodeType(custom.getType());
			
			B2BUser user = getCurLoginUser();
			wbeOrderVO.setCreateUDFBy(user.getUsername());
			
			StringBuffer sb = new StringBuffer();
			//测试数据
			//wbeOrderVO = getWbeOrderVOTest();
			if(!StringUtils.hasLength(wbeOrderVO.getMasterId())||!StringUtils.hasLength(wbeOrderVO.getSta())){
				
				String crsnos=wbeOrderVO.getCrsno().trim();
				String[] crsnArr=null;
				if(StringUtils.hasText(crsnos)){
					crsnArr=crsnos.split(",");
					if(!checkRepeat(crsnArr) || crsnArr.length!=wbeOrderVO.getNumber()){//填写的渠道订单号有重复或者渠道订单号的个数与房间数不等
						log.error("CRSNo. Error");
						wbeOrderVO.setSta("FAIL");
						wbeOrderVO.setStaDesp(getText("ccm.WBEUI.message.002")+"(CRSNo. Error)");
						return "exportBookingPDF";
					}
				}
				//遍历所有的房间 
				for(int i=0;i<wbeOrderVO.getNumber();i++){
					WbeOrderVO wovo = new WbeOrderVO();
					BeanUtils.copyProperties(wbeOrderVO, wovo);
					wovo.setNumber(1);
					wovo.setWbeGuestVO(wbeOrderVO.getWbeGuestVoList().get(i));
					if(crsnArr!=null && crsnArr.length>0){
						//渠道订单号
						wovo.setCrsno(crsnArr[i]);
					}else{
						wovo.setCrsno("");
					}
					
					OrderReservationRetVO response = channelBookIngReservationManager.createBooking(wovo);
					wbeOrderVO.setSta(response.getResultStatusFlag());
					if("SUCCESS".equalsIgnoreCase(response.getResultStatusFlag())){
						sb.append(response.getResevationUniqueID());
						sb.append(",");
						wbeOrderVO.setStaDesp(getText("ccm.WBEUI.message.001"));
					}else{
						log.error("errorCode:"+response.getErrorCode()+"ErrMsg:"+response.getErrMsg());
						wbeOrderVO.setStaDesp(getText("ccm.WBEUI.message.002")+"("+response.getErrMsg()+")");
						break;
					}
				}
				
				BigDecimal total = BigDecimal.ZERO;
				if("SUCCESS".equalsIgnoreCase(wbeOrderVO.getSta())){
					for(OrderDailyRateVO vo:wbeOrderVO.getDailRateList()){
						total = total.add(AmountUtil.convert2ByFloorMode(new BigDecimal(vo.getPrice()+"")));
					}
					total = AmountUtil.convert2ByFloorMode(total);
					wbeOrderVO.setOneRoomCharge(total);
					
					total = total.multiply(new BigDecimal(wbeOrderVO.getNumber()));
					total = AmountUtil.convert2ByFloorMode(total);
					wbeOrderVO.setMasterId(sb.substring(0, sb.length()-1));
					wbeOrderVO.setCharge(total);
				}else{
					wbeOrderVO.setOneRoomCharge(total);
					wbeOrderVO.setCharge(total);
				}
			}
			getRequest().setAttribute("wbeOrderVO", wbeOrderVO);
		
				
		} catch (Exception e) {
			log.error("ChannelBookIngAction.bookInfo()  error");
			log.error(e);
			return null;
		}
		return "exportBookingPDF";
	}
	
	/**
	 * 预定---下载确认函 pdf格式
	 * 
	 * @return
	 */
	public void exportBookingPDF() {
		try {

			// Step 1—Create a Document.
			Document document = new Document();

			getResponse().reset();

			getResponse().setContentType("application/pdf");
			getResponse().setHeader("content-disposition", "attachment; filename=\""+wbeOrderVO.getMasterId()+"\".pdf");
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  // 设置字体

		   	PdfWriter pw = PdfWriter.getInstance(document, baos);

			// Step 3—Open the Document.
			document.open();
			
			// 添加字体
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfChinese, 12, Font.NORMAL);

			// Step 4—Add content.

			//标题
			PdfPTable th = new PdfPTable(4);
			th.setTotalWidth(500);
			th.setLockedWidth(true);
			PdfPCell ch = new PdfPCell(new Paragraph(getText("ccm.WBEUI.ConfirmationLetter"), font));
			ch.setColspan(4);
			ch.setBorder(PdfPCell.NO_BORDER);
			th.addCell(ch);
			
			// 一个 4列的表格
			PdfPTable ta = new PdfPTable(4);
			
			ta.setTotalWidth(500);
			ta.setLockedWidth(true);
			
			//酒店名称
			PdfPCell c1_ = new PdfPCell(new Paragraph(getText("ccm.UserActivityLog.PropertyName"), font));
			c1_.setFixedHeight(25);
			c1_.setBorderColor(BaseColor.BLACK);
			c1_.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c1_);

			PdfPCell c2_ = new PdfPCell(new Paragraph(wbeOrderVO.getHotelName(), font));
			//c2_.setColspan(3);
			c2_.setFixedHeight(25);
			c2_.setBorderColor(BaseColor.BLACK);
			c2_.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c2_);
			
			//订单状态
			PdfPCell c3 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.Status"), font));
			c3.setFixedHeight(25);
			c3.setBorderColor(BaseColor.BLACK);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c3);

			PdfPCell c4 = new PdfPCell(new Paragraph(wbeOrderVO.getStaDesp(), font));
			c4.setFixedHeight(25);
			c4.setBorderColor(BaseColor.BLACK);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c4);

			
			//预定确认号
			PdfPCell c1 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.ConfirmationNumber"), font));
			c1.setFixedHeight(25);
			c1.setBorderColor(BaseColor.BLACK);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c1);

			PdfPCell c2 = new PdfPCell(new Paragraph(wbeOrderVO.getMasterId(), font));
			c2.setFixedHeight(25);
			c2.setBorderColor(BaseColor.BLACK);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c2);


			/*渠道订单号*/
			PdfPCell cCRSN = new PdfPCell(new Paragraph(getText("ccm.Channel.ChannelReservationNumber"), font));
			cCRSN.setFixedHeight(25);
			cCRSN.setBorderColor(BaseColor.BLACK);
			cCRSN.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(cCRSN);
			
			PdfPCell cCRSNum = new PdfPCell(new Paragraph(wbeOrderVO.getCrsno(), font));
			cCRSNum.setFixedHeight(25);
			cCRSNum.setBorderColor(BaseColor.BLACK);
			cCRSNum.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(cCRSNum);
			
			
			PdfPCell c5 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.ArrivalDate"), font));
			c5.setFixedHeight(25);
			c5.setBorderColor(BaseColor.BLACK);
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c5);

			PdfPCell c6 = new PdfPCell(new Paragraph(DateUtil.convertDateToString(wbeOrderVO.getArr()), font));
			c6.setFixedHeight(25);
			c6.setBorderColor(BaseColor.BLACK);
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c6);

			PdfPCell c7 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.DepartureDate"), font));
			c7.setFixedHeight(25);
			c7.setBorderColor(BaseColor.BLACK);
			c7.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c7);

			PdfPCell c8 = new PdfPCell(new Paragraph(DateUtil.convertDateToString(wbeOrderVO.getDep()), font));
			c8.setFixedHeight(25);
			c8.setBorderColor(BaseColor.BLACK);
			c8.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c8);

			PdfPCell c9 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.RoomType"), font));
			c9.setFixedHeight(25);
			c9.setBorderColor(BaseColor.BLACK);
			c9.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c9);

			PdfPCell c10 = new PdfPCell(new Paragraph(wbeOrderVO.getRoomTypeName(), font));
			c10.setFixedHeight(25);
			c10.setBorderColor(BaseColor.BLACK);
			c10.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c10);

			PdfPCell c11 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.RateCode"), font));
			c11.setFixedHeight(25);
			c11.setBorderColor(BaseColor.BLACK);
			c11.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c11);

			PdfPCell c12 = new PdfPCell(new Paragraph(wbeOrderVO.getRatePlanCode(), font));
			c12.setFixedHeight(25);
			c12.setBorderColor(BaseColor.BLACK);
			c12.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c12);
			

			PdfPCell c13 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.RoomsandAudlts"), font));
			c13.setFixedHeight(25);
			c13.setBorderColor(BaseColor.BLACK);
			c13.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c13);

			PdfPCell c14 = new PdfPCell(new Paragraph(wbeOrderVO.getNumber()+"/"+wbeOrderVO.getNumberOfUnits(), font));
			c14.setFixedHeight(25);
			c14.setBorderColor(BaseColor.BLACK);
			c14.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c14);

			PdfPCell c15 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.TotalAmount"), font));
			c15.setFixedHeight(25);
			c15.setBorderColor(BaseColor.BLACK);
			c15.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c15);

			PdfPCell c16 = new PdfPCell(new Paragraph(wbeOrderVO.getCharge()+wbeOrderVO.getCurrencyCode(), font));
			c16.setFixedHeight(25);
			c16.setBorderColor(BaseColor.BLACK);
			c16.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c16);

			PdfPCell c17 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.Rateperroompernight"), font));
			c17.setColspan(1);
			c17.setRowspan(wbeOrderVO.getDailRateList().size()+1);
			c17.setFixedHeight(25);
			c17.setBorderColor(BaseColor.BLACK);
			c17.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c17);
			for(int i=0;i<wbeOrderVO.getDailRateList().size();i++){
				OrderDailyRateVO orderDailyRateVO = wbeOrderVO.getDailRateList().get(i);
				
				PdfPCell c18 = new PdfPCell(new Paragraph(DateUtil.convertDateToString(orderDailyRateVO.getPriceDate()), font));
				c18.setFixedHeight(25);
				c18.setBorderColor(BaseColor.BLACK);
				c18.setHorizontalAlignment(Element.ALIGN_CENTER);
				ta.addCell(c18);
				
				PdfPCell c19 = new PdfPCell(new Paragraph(orderDailyRateVO.getPrice()+wbeOrderVO.getCurrencyCode(), font));
				c19.setColspan(2);
				c19.setFixedHeight(25);
				c19.setBorderColor(BaseColor.BLACK);
				c19.setHorizontalAlignment(Element.ALIGN_CENTER);
				ta.addCell(c19);
				
			}

			Paragraph p=new Paragraph(getText("ccm.WBEUI.RatesPernight")+"："+wbeOrderVO.getOneRoomCharge()+wbeOrderVO.getCurrencyCode(), font);
			p.setAlignment(1);
			PdfPCell c26 = new PdfPCell(p);
			c26.setColspan(3);
			c26.setFixedHeight(25);
			c26.setBorderColor(BaseColor.BLACK);
			c26.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c26);
			
			//间隔
			PdfPTable tab = new PdfPTable(4);
			tab.setTotalWidth(500);
			tab.setLockedWidth(true);
			PdfPCell cab = new PdfPCell(new Paragraph(" ", font));
			cab.setColspan(4);
			cab.setBorder(PdfPCell.NO_BORDER);
			tab.addCell(cab);
			
			
			document.add(th);
			document.add(tab);
			document.add(ta);
			document.add(tab);
			
			// tb一个 4列的表格 
			for(int i=0;i<wbeOrderVO.getWbeGuestVoList().size();i++){
				WbeGuestVO wbeGuestVO = wbeOrderVO.getWbeGuestVoList().get(i);
				
				PdfPTable tb = new PdfPTable(4);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p201=new Paragraph(getText("ccm.WBEUI.GuestInformation")+(i+1), font);
				p201.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c201 = new PdfPCell(p201);
				c201.setFixedHeight(25);
				c201.setBorderColor(BaseColor.BLACK);
				c201.setColspan(4);
				c201.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c201);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p202=new Paragraph(getText("ccm.WBEUI.GuestName"), font);
				p202.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c202 = new PdfPCell(p202);
				c202.setFixedHeight(25);
				c202.setBorderColor(BaseColor.BLACK);
				c202.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c202);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p203=new Paragraph(wbeGuestVO.getName2()+" "+wbeGuestVO.getName1()+" "+wbeGuestVO.getName3(), font);
				p203.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c203 = new PdfPCell(p203);
				c203.setFixedHeight(25);
				c203.setBorderColor(BaseColor.BLACK);
				c203.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c203);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p204=new Paragraph(getText("ccm.WBEUI.MobilePhone"), font);
				p204.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c204 = new PdfPCell(p204);
				c204.setFixedHeight(25);
				c204.setBorderColor(BaseColor.BLACK);
				c204.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c204);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p205=new Paragraph(wbeGuestVO.getTel(), font);
				p205.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c205 = new PdfPCell(p205);
				c205.setFixedHeight(25);
				c205.setBorderColor(BaseColor.BLACK);
				c205.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c205);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p206=new Paragraph(getText("ccm.WBEUI.EstimatedTimeofArrival"), font);
				p206.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c206 = new PdfPCell(p206);
				c206.setFixedHeight(25);
				c206.setBorderColor(BaseColor.BLACK);
				c206.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c206);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p207=new Paragraph(DateUtil.getTimeNow(wbeGuestVO.getArr()), font);
				p207.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c207 = new PdfPCell(p207);
				c207.setFixedHeight(25);
				c207.setBorderColor(BaseColor.BLACK);
				c207.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c207);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p208=new Paragraph("", font);
				p208.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c208 = new PdfPCell(p208);
				c208.setFixedHeight(25);
				c208.setBorderColor(BaseColor.BLACK);
				c208.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c208);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p209=new Paragraph("", font);
				p209.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c209 = new PdfPCell(p209);
				c209.setFixedHeight(25);
				c209.setBorderColor(BaseColor.BLACK);
				c209.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c209);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p210=new Paragraph(getText("ccm.WBEUI.SpecialRequest"), font);
				p210.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c210 = new PdfPCell(p210);
				c210.setFixedHeight(25);
				c210.setBorderColor(BaseColor.BLACK);
				c210.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c210);
				
				tb.setTotalWidth(500);
				tb.setLockedWidth(true);
				Paragraph p211=new Paragraph(wbeGuestVO.getNeed(), font);
				p211.setAlignment(Element.ALIGN_CENTER);
				PdfPCell c211 = new PdfPCell(p211);
				c211.setFixedHeight(55);
				c211.setBorderColor(BaseColor.BLACK);
				c211.setColspan(3);
				c211.setHorizontalAlignment(Element.ALIGN_CENTER);
				tb.addCell(c211);
				
				document.add(tb);
			}

			// Step 5—Close the Document.
			document.close();

			getResponse().setContentLength(baos.size());    
	        ServletOutputStream out = getResponse().getOutputStream();    
	        baos.writeTo(out);    
	        out.flush();
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 取消预定---查询界面
	 * 
	 * @return
	 */
	public String cancelPage() {
		B2BUser user = getCurLoginUser();
		//USER 权限的channel  hotelVOList
		Channel channel = user.getChannels().get(0);
		List<HotelVO> hotelVOList = user.getHotelVOs();
		
		getRequest().setAttribute("channel", channel);
		getRequest().setAttribute("hotelVOList", hotelVOList);
		return "cancelPage";
	}

	/**
	 * 取消预定---查询
	 * 
	 * @return
	 */
	public String cancelSearch() {
		B2BUser user = getCurLoginUser();
		//USER 权限的channel  hotelVOList
		Channel channel = user.getChannels().get(0);
		String hotelId = getRequest().getParameter("hotelId");
		String channelId = channel.getChannelId();
		String name1 = getRequest().getParameter("name1");
		String masterId = getRequest().getParameter("masterId");
		String crsno=getRequest().getParameter("crsno");
		WbeOrderVO wbeOrderVO =null;
		if(StringUtils.hasLength(masterId)){
			wbeOrderVO = channelBookingManager.getWbeOrderVO(hotelId, channelId, name1, masterId,crsno);
		}
		
		List<HotelVO> hotelVOList = user.getHotelVOs();
		getRequest().setAttribute("hotelVOList", hotelVOList);
		getRequest().setAttribute("wbeOrderVO", wbeOrderVO);
		return "cancelPage";
	}

	/**
	 * 取消预定
	 * 
	 * @return
	 */
	public String cancel() {
		try {
			
			wbeOrderVO.setWbeGuestVO(wbeOrderVO.getWbeGuestVoList().get(0));
			if(wbeOrderVO.getCancelReason()==null){
				wbeOrderVO.setCancelReason("");
			}
			OrderReservationRetVO response = channelBookIngReservationManager.cacelBooking(wbeOrderVO);
		
			wbeOrderVO.setSta(response.getResultStatusFlag());
			if("SUCCESS".equals(response.getResultStatusFlag())){
				wbeOrderVO.setStaDesp(getText("ccm.WBEUI.message.003"));
			}else{
				wbeOrderVO.setStaDesp(getText("ccm.WBEUI.message.004"));
			}
			getRequest().setAttribute("wbeOrderVO", wbeOrderVO);
		} catch (Exception e) {
			log.error(e);
			return null;
		}
		return "exportCancelPDF";
	}

	/**
	 * 取消预定---下载确认函 pdf格式
	 * 
	 * @return
	 */
	public void exportCancelPDF() {
		wbeOrderVO.setWbeGuestVO(wbeOrderVO.getWbeGuestVoList().get(0));
		
		try {
			getResponse().reset();

			getResponse().setContentType("application/pdf");
			getResponse().setHeader("content-disposition", "attachment; filename="+wbeOrderVO.getMasterId()+".pdf");
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  // 设置字体

			Document document = new Document();
		   	PdfWriter pw = PdfWriter.getInstance(document, baos);
			
			// Step 1—Create a Document.

			// Step 3—Open the Document.
			document.open();
			
			// 添加字体
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfChinese, 12, Font.NORMAL);

			// Step 4—Add content.

			//标题
			PdfPTable th = new PdfPTable(4);
			th.setTotalWidth(500);
			th.setLockedWidth(true);
			PdfPCell ch = new PdfPCell(new Paragraph(getText("ccm.WBEUI.ConfiramtionofCancellation"), font));
			ch.setColspan(4);
			ch.setBorder(PdfPCell.NO_BORDER);
			th.addCell(ch);
			
			// 一个 4列的表格
			PdfPTable ta = new PdfPTable(4);
			
			ta.setTotalWidth(500);
			ta.setLockedWidth(true);

			//订单状态
			PdfPCell c3 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.Status"), font));
			c3.setFixedHeight(25);
			c3.setBorderColor(BaseColor.BLACK);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c3);

			PdfPCell c4 = new PdfPCell(new Paragraph(wbeOrderVO.getStaDesp(), font));
			c4.setColspan(3);
			c4.setFixedHeight(25);
			c4.setBorderColor(BaseColor.BLACK);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c4);
			
			
			//预定确认号
			PdfPCell c1 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.ConfirmationNumber"), font));
			c1.setFixedHeight(25);
			c1.setBorderColor(BaseColor.BLACK);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c1);

			PdfPCell c2 = new PdfPCell(new Paragraph(wbeOrderVO.getMasterId(), font));
			c2.setFixedHeight(25);
			c2.setBorderColor(BaseColor.BLACK);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c2);

			
			/*渠道订单号*/
			PdfPCell cCRSN = new PdfPCell(new Paragraph(getText("ccm.Channel.ChannelReservationNumber"), font));
			cCRSN.setFixedHeight(25);
			cCRSN.setBorderColor(BaseColor.BLACK);
			cCRSN.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(cCRSN);
			
			PdfPCell cCRSNum = new PdfPCell(new Paragraph(wbeOrderVO.getCrsno(), font));
			cCRSNum.setFixedHeight(25);
			cCRSNum.setBorderColor(BaseColor.BLACK);
			cCRSNum.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(cCRSNum);

			
			PdfPCell c5 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.ArrivalDate"), font));
			c5.setFixedHeight(25);
			c5.setBorderColor(BaseColor.BLACK);
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c5);

			PdfPCell c6 = new PdfPCell(new Paragraph(DateUtil.convertDateToString(wbeOrderVO.getArr()), font));
			c6.setFixedHeight(25);
			c6.setBorderColor(BaseColor.BLACK);
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c6);

			PdfPCell c7 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.DepartureDate"), font));
			c7.setFixedHeight(25);
			c7.setBorderColor(BaseColor.BLACK);
			c7.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c7);

			PdfPCell c8 = new PdfPCell(new Paragraph(DateUtil.convertDateToString(wbeOrderVO.getDep()), font));
			c8.setFixedHeight(25);
			c8.setBorderColor(BaseColor.BLACK);
			c8.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c8);

			PdfPCell c9 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.RoomType"), font));
			c9.setFixedHeight(25);
			c9.setBorderColor(BaseColor.BLACK);
			c9.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c9);

			PdfPCell c10 = new PdfPCell(new Paragraph(wbeOrderVO.getRoomTypeCode(), font));
			c10.setFixedHeight(25);
			c10.setBorderColor(BaseColor.BLACK);
			c10.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c10);

			PdfPCell c11 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.RateCode"), font));
			c11.setFixedHeight(25);
			c11.setBorderColor(BaseColor.BLACK);
			c11.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c11);

			PdfPCell c12 = new PdfPCell(new Paragraph(wbeOrderVO.getRatePlanCode(), font));
			c12.setFixedHeight(25);
			c12.setBorderColor(BaseColor.BLACK);
			c12.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c12);
			

			PdfPCell c13 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.RoomsandAudlts"), font));
			c13.setFixedHeight(25);
			c13.setBorderColor(BaseColor.BLACK);
			c13.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c13);

			PdfPCell c14 = new PdfPCell(new Paragraph("1/"+wbeOrderVO.getNumberOfUnits(), font));
			c14.setFixedHeight(25);
			c14.setBorderColor(BaseColor.BLACK);
			c14.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c14);

			PdfPCell c15 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.TotalAmount"), font));
			c15.setFixedHeight(25);
			c15.setBorderColor(BaseColor.BLACK);
			c15.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c15);

			PdfPCell c16 = new PdfPCell(new Paragraph(wbeOrderVO.getCharge()+wbeOrderVO.getCurrencyCode(), font));
			c16.setFixedHeight(25);
			c16.setBorderColor(BaseColor.BLACK);
			c16.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c16);
			
			for(int i=0;i<wbeOrderVO.getDailRateList().size();i++){
				OrderDailyRateVO orderDailyRateVO = wbeOrderVO.getDailRateList().get(i);
				if(i==0){
					PdfPCell c17 = new PdfPCell(new Paragraph(getText("ccm.WBEUI.Rateperroompernight"), font));
					c17.setColspan(1);
					c17.setRowspan(wbeOrderVO.getDailRateList().size()+1);
					c17.setFixedHeight(25);
					c17.setBorderColor(BaseColor.BLACK);
					c17.setHorizontalAlignment(Element.ALIGN_CENTER);
					ta.addCell(c17);
				}
				PdfPCell c18 = new PdfPCell(new Paragraph(DateUtil.convertDateToString(orderDailyRateVO.getPriceDate()), font));
				c18.setFixedHeight(25);
				c18.setBorderColor(BaseColor.BLACK);
				c18.setHorizontalAlignment(Element.ALIGN_CENTER);
				ta.addCell(c18);
				
				PdfPCell c19 = new PdfPCell(new Paragraph(orderDailyRateVO.getPrice()+wbeOrderVO.getCurrencyCode(), font));
				c19.setColspan(2);
				c19.setFixedHeight(25);
				c19.setBorderColor(BaseColor.BLACK);
				c19.setHorizontalAlignment(Element.ALIGN_CENTER);
				ta.addCell(c19);
				
			}

			Paragraph p=new Paragraph(getText("ccm.WBEUI.RatesPernight")+"："+wbeOrderVO.getCharge()+wbeOrderVO.getCurrencyCode(), font);
			p.setAlignment(1);
			PdfPCell c26 = new PdfPCell(p);
			c26.setColspan(3);
			c26.setFixedHeight(25);
			c26.setBorderColor(BaseColor.BLACK);
			c26.setHorizontalAlignment(Element.ALIGN_CENTER);
			ta.addCell(c26);
			
			//间隔
			PdfPTable tab = new PdfPTable(4);
			tab.setTotalWidth(500);
			tab.setLockedWidth(true);
			PdfPCell cab = new PdfPCell(new Paragraph(" ", font));
			cab.setColspan(4);
			cab.setBorder(PdfPCell.NO_BORDER);
			tab.addCell(cab);
			
			// tb一个 4列的表格 
			PdfPTable tb = new PdfPTable(4);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p201=new Paragraph(getText("ccm.WBEUI.GuestInformation"), font);
			p201.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c201 = new PdfPCell(p201);
			c201.setFixedHeight(25);
			c201.setBorderColor(BaseColor.BLACK);
			c201.setColspan(4);
			c201.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c201);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p202=new Paragraph(getText("ccm.WBEUI.GuestName"), font);
			p202.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c202 = new PdfPCell(p202);
			c202.setFixedHeight(25);
			c202.setBorderColor(BaseColor.BLACK);
			c202.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c202);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p203=new Paragraph(wbeOrderVO.getWbeGuestVO().getName2()+" "+wbeOrderVO.getWbeGuestVO().getName1()+" "+wbeOrderVO.getWbeGuestVO().getName3(), font);
			p203.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c203 = new PdfPCell(p203);
			c203.setFixedHeight(25);
			c203.setBorderColor(BaseColor.BLACK);
			c203.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c203);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p204=new Paragraph(getText("ccm.WBEUI.MobilePhone"), font);
			p204.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c204 = new PdfPCell(p204);
			c204.setFixedHeight(25);
			c204.setBorderColor(BaseColor.BLACK);
			c204.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c204);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p205=new Paragraph(wbeOrderVO.getWbeGuestVO().getTel(), font);
			p205.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c205 = new PdfPCell(p205);
			c205.setFixedHeight(25);
			c205.setBorderColor(BaseColor.BLACK);
			c205.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c205);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p206=new Paragraph(getText("ccm.WBEUI.EstimatedTimeofArrival"), font);
			p206.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c206 = new PdfPCell(p206);
			c206.setFixedHeight(25);
			c206.setBorderColor(BaseColor.BLACK);
			c206.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c206);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p207=new Paragraph(DateUtil.getTimeNow(wbeOrderVO.getWbeGuestVO().getArr()), font);
			p207.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c207 = new PdfPCell(p207);
			c207.setFixedHeight(25);
			c207.setBorderColor(BaseColor.BLACK);
			c207.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c207);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p208=new Paragraph("", font);
			p208.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c208 = new PdfPCell(p208);
			c208.setFixedHeight(25);
			c208.setBorderColor(BaseColor.BLACK);
			c208.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c208);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p209=new Paragraph("", font);
			p209.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c209 = new PdfPCell(p209);
			c209.setFixedHeight(25);
			c209.setBorderColor(BaseColor.BLACK);
			c209.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c209);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p210=new Paragraph(getText("ccm.WBEUI.SpecialRequest"), font);
			p210.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c210 = new PdfPCell(p210);
			c210.setFixedHeight(25);
			c210.setBorderColor(BaseColor.BLACK);
			c210.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c210);
			
			tb.setTotalWidth(500);
			tb.setLockedWidth(true);
			Paragraph p211=new Paragraph(wbeOrderVO.getWbeGuestVO().getNeed(), font);
			p211.setAlignment(Element.ALIGN_CENTER);
			PdfPCell c211 = new PdfPCell(p211);
			c211.setFixedHeight(55);
			c211.setBorderColor(BaseColor.BLACK);
			c211.setColspan(3);
			c211.setHorizontalAlignment(Element.ALIGN_CENTER);
			tb.addCell(c211);
			
			document.add(th);
			document.add(tab);
			document.add(ta);
			document.add(tab);
			document.add(tb);

			// Step 5—Close the Document.
			document.close();

			getResponse().setContentLength(baos.size());    
	        ServletOutputStream out = getResponse().getOutputStream();    
	        baos.writeTo(out);    
	        out.flush();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String changesLog() {
		if (StringUtils.hasText(orderNo)) {
			List<MasterChangesLog> masterChangesLogList = orderChangesLogManager.getMasterChangesLogByOrderNo(orderNo);
			getRequest().setAttribute("masterChangesLogList", masterChangesLogList);
		}
		return "changesLog";
	}
	
	private String getLanguage() {
		Locale locale = ActionContext.getContext().getLocale();
		return locale.getLanguage() + "_" + locale.getCountry();
	}
	
	public WbeSearchCreteria getWbeSearchCreteria() {
		return wbeSearchCreteria;
	}

	public void setWbeSearchCreteria(WbeSearchCreteria wbeSearchCreteria) {
		this.wbeSearchCreteria = wbeSearchCreteria;
	}
	public ChannelGoodsVO getChannelGoodsVO() {
		return channelGoodsVO;
	}

	public void setChannelGoodsVO(ChannelGoodsVO channelGoodsVO) {
		this.channelGoodsVO = channelGoodsVO;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	
	public WbeOrderVO getWbeOrderVO() {
		return wbeOrderVO;
	}

	public void setWbeOrderVO(WbeOrderVO wbeOrderVO) {
		this.wbeOrderVO = wbeOrderVO;
	}

	public SearchOrderCriteria getSoc() {
		return soc;
	}

	public void setSoc(SearchOrderCriteria soc) {
		this.soc = soc;
	}

	public OrderSearchResult getOrderSearchResult() {
		return orderSearchResult;
	}

	public void setOrderSearchResult(OrderSearchResult orderSearchResult) {
		this.orderSearchResult = orderSearchResult;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Master getOrder() {
		return order;
	}

	public void setOrder(Master order) {
		this.order = order;
	}

	public List<MasterRate> getPriceDetail() {
		return priceDetail;
	}

	public void setPriceDetail(List<MasterRate> priceDetail) {
		this.priceDetail = priceDetail;
	}

	@SuppressWarnings("static-access")
	private Map<String, List<WbeCalendarRow>> getMap(){
		Map<String, List<WbeCalendarRow>> map= new HashMap<String, List<WbeCalendarRow>>();
		try {	
				List<WbeCalendarRow> wbeCalendarRowList1 = new ArrayList<WbeCalendarRow>();
				
				map.put("1KBUBS", wbeCalendarRowList1);
				
				WbeCalendarRow wbeCalendarRow1_1 = new WbeCalendarRow();
				WbeCalendarRow wbeCalendarRow1_2 = new WbeCalendarRow();
				wbeCalendarRowList1.add(wbeCalendarRow1_1);
				wbeCalendarRowList1.add(wbeCalendarRow1_2);
				
				
				wbeCalendarRow1_2.setInventType(wbeCalendarRow1_2.INVENT_FREESELL);
				wbeCalendarRow1_2.setRoomTypeCode("1KBUBS");
				wbeCalendarRow1_2.setRowNumberOfUnits(1);
				wbeCalendarRow1_2.setRatePlanCode("WHL19");
				wbeCalendarRow1_2.setRoomTypeName("房型A");
				wbeCalendarRow1_2.setArrDate(DateUtil.convertStringToDate("2016-06-17"));
				wbeCalendarRow1_2.setDepDate(DateUtil.convertStringToDate("2016-06-20"));
				
				Map<Date, WbeCalendarCell> cellMap2_1 = new HashMap<Date, WbeCalendarCell>();
				wbeCalendarRow1_2.setCellMap(cellMap2_1);
				
				WbeCalendarCell wbeCalendarCell2_1 = new WbeCalendarCell();
				WbeCalendarCell wbeCalendarCell2_2 = new WbeCalendarCell();
				WbeCalendarCell wbeCalendarCell2_3 = new WbeCalendarCell();
				WbeCalendarCell wbeCalendarCell2_4 = new WbeCalendarCell();
				
				
				CancelPolicyVO cancelPolicyVO1_1 = new CancelPolicyVO();
				CancelPolicyVO cancelPolicyVO2_1 = new CancelPolicyVO();
				CancelPolicyVO cancelPolicyVO3_1 = new CancelPolicyVO();
				CancelPolicyVO cancelPolicyVO4_1 = new CancelPolicyVO();
				cancelPolicyVO1_1.setLanguage("zh_CN");
				cancelPolicyVO1_1.setPolicyName("policyName1");
				cancelPolicyVO1_1.setDescription("description1");
				
				cancelPolicyVO2_1.setLanguage("zh_CN");
				cancelPolicyVO2_1.setPolicyName("policyName2");
				cancelPolicyVO2_1.setDescription("description2..description2..description2..description2");
				
				cancelPolicyVO3_1.setLanguage("zh_CN");
				cancelPolicyVO3_1.setPolicyName("policyName3");
				cancelPolicyVO3_1.setDescription("description3");
				
				cancelPolicyVO4_1.setLanguage("zh_CN");
				cancelPolicyVO4_1.setPolicyName("policyName4");
				cancelPolicyVO4_1.setDescription("description4...description4...description4...description4");
				
				wbeCalendarCell2_1.setCancelPolicyVO(cancelPolicyVO1_1);
				wbeCalendarCell2_2.setCancelPolicyVO(cancelPolicyVO2_1);
				wbeCalendarCell2_3.setCancelPolicyVO(cancelPolicyVO3_1);
				wbeCalendarCell2_4.setCancelPolicyVO(cancelPolicyVO4_1);
				
				cellMap2_1.put(DateUtil.convertStringToDate("2016-06-17"), wbeCalendarCell2_2);
				cellMap2_1.put(DateUtil.convertStringToDate("2016-06-18"), wbeCalendarCell2_1);
				cellMap2_1.put(DateUtil.convertStringToDate("2016-06-19"), wbeCalendarCell2_3);
				cellMap2_1.put(DateUtil.convertStringToDate("2016-06-20"), wbeCalendarCell2_4);
				
				Map<Integer, RateAmount> rateAmountMap = new HashMap<Integer, RateAmount>();
				RateAmount rateAmount1 = new RateAmount();
				RateAmount rateAmount2 = new RateAmount();
				RateAmount rateAmount3 = new RateAmount();
				RateAmount rateAmount4 = new RateAmount();
				rateAmountMap.put(1, rateAmount1);
				rateAmountMap.put(2, rateAmount2);
				rateAmountMap.put(3, rateAmount3);
				rateAmountMap.put(4, rateAmount4);
				
				rateAmount1.setNumberOfUnits(1);
				rateAmount1.setBaseAmount(new BigDecimal(300));
				
				rateAmount2.setNumberOfUnits(2);
				rateAmount2.setBaseAmount(new BigDecimal(300));
				
				rateAmount3.setNumberOfUnits(3);
				rateAmount3.setBaseAmount(new BigDecimal(300));
				
				rateAmount4.setNumberOfUnits(4);
				rateAmount4.setBaseAmount(new BigDecimal(300));
				Map<Integer, RateAmount> rateAmountMap2 = new HashMap<Integer, RateAmount>();
				RateAmount rateAmount1_2 = new RateAmount();
				RateAmount rateAmount2_2 = new RateAmount();
				RateAmount rateAmount3_2 = new RateAmount();
				RateAmount rateAmount4_2 = new RateAmount();
				rateAmountMap2.put(1, rateAmount1_2);
				rateAmountMap2.put(2, rateAmount2_2);
				rateAmountMap2.put(3, rateAmount3_2);
				rateAmountMap2.put(4, rateAmount4_2);
				
				rateAmount1_2.setNumberOfUnits(1);
				rateAmount1_2.setBaseAmount(new BigDecimal(300));
				
				rateAmount2_2.setNumberOfUnits(2);
				rateAmount2_2.setBaseAmount(new BigDecimal(500));
				
				rateAmount3_2.setNumberOfUnits(3);
				rateAmount3_2.setBaseAmount(new BigDecimal(600));
				
				rateAmount4_2.setNumberOfUnits(4);
				rateAmount4_2.setBaseAmount(new BigDecimal(700));
				
				Map<Integer, RateAmount> rateAmountMap3 = new HashMap<Integer, RateAmount>();
				RateAmount rateAmount1_3 = new RateAmount();
				RateAmount rateAmount2_3 = new RateAmount();
				RateAmount rateAmount3_3 = new RateAmount();
				RateAmount rateAmount4_3 = new RateAmount();
				rateAmountMap3.put(1, rateAmount1_3);
				rateAmountMap3.put(2, rateAmount2_3);
				rateAmountMap3.put(3, rateAmount3_3);
				rateAmountMap3.put(4, rateAmount4_3);
				
				rateAmount1_3.setNumberOfUnits(1);
				rateAmount1_3.setBaseAmount(new BigDecimal(300));
				
				rateAmount2_3.setNumberOfUnits(2);
				rateAmount2_3.setBaseAmount(new BigDecimal(300));
				
				rateAmount3_3.setNumberOfUnits(3);
				rateAmount3_3.setBaseAmount(new BigDecimal(700));
				
				rateAmount4_3.setNumberOfUnits(4);
				rateAmount4_3.setBaseAmount(new BigDecimal(100));
				
				Map<Integer, RateAmount> rateAmountMap1 = new HashMap<Integer, RateAmount>();
				RateAmount rateAmount1_1 = new RateAmount();
				RateAmount rateAmount2_1 = new RateAmount();
				RateAmount rateAmount3_1 = new RateAmount();
				RateAmount rateAmount4_1 = new RateAmount();
				rateAmountMap1.put(1, rateAmount1_1);
				rateAmountMap1.put(2, rateAmount2_1);
				rateAmountMap1.put(3, rateAmount3_1);
				rateAmountMap1.put(4, rateAmount4_1);
				
				rateAmount1_1.setNumberOfUnits(1);
				rateAmount1_1.setBaseAmount(new BigDecimal(300));
				
				rateAmount2_1.setNumberOfUnits(2);
				rateAmount2_1.setBaseAmount(new BigDecimal(400));
				
				rateAmount3_1.setNumberOfUnits(3);
				rateAmount3_1.setBaseAmount(new BigDecimal(400));
				
				rateAmount4_1.setNumberOfUnits(4);
				rateAmount4_1.setBaseAmount(new BigDecimal(400));
				
				wbeCalendarCell2_1.setCellDate(DateUtil.convertStringToDate("2016-06-17"));
				wbeCalendarCell2_1.setCellInventAvai(15);
				wbeCalendarCell2_1.setCellPrice(new BigDecimal(300));
				wbeCalendarCell2_1.setCellNumberOfUnits(1);
				wbeCalendarCell2_1.setRateAmountMap(rateAmountMap);
				
				wbeCalendarCell2_2.setCellDate(DateUtil.convertStringToDate("2016-06-18"));
	//			wbeCalendarCell2_2.setCellInventAvai(14);
				wbeCalendarCell2_2.setCellPrice(new BigDecimal(300));
				wbeCalendarCell2_2.setCellNumberOfUnits(1);
				wbeCalendarCell2_2.setRateAmountMap(rateAmountMap);
				
				wbeCalendarCell2_3.setCellDate(DateUtil.convertStringToDate("2016-06-19"));
				wbeCalendarCell2_3.setCellInventAvai(41);
				wbeCalendarCell2_3.setCellPrice(new BigDecimal(300));
				wbeCalendarCell2_3.setCellNumberOfUnits(1);
				wbeCalendarCell2_3.setRateAmountMap(rateAmountMap);
				
				wbeCalendarCell2_4.setCellDate(DateUtil.convertStringToDate("2016-06-20"));
				wbeCalendarCell2_4.setCellInventAvai(14);
				wbeCalendarCell2_4.setCellPrice(new BigDecimal(300));
				wbeCalendarCell2_4.setCellNumberOfUnits(1);
				wbeCalendarCell2_4.setRateAmountMap(rateAmountMap1);
				
				
				wbeCalendarRow1_1.setInventType(wbeCalendarRow1_1.INVENT_ALLOT);
				wbeCalendarRow1_1.setRoomTypeCode("1KBUBS");
				wbeCalendarRow1_1.setRoomTypeName("房型A");
				wbeCalendarRow1_1.setRowNumberOfUnits(1);
				wbeCalendarRow1_1.setRatePlanCode("WHL20");
				wbeCalendarRow1_1.setArrDate(DateUtil.convertStringToDate("2016-06-17"));
				wbeCalendarRow1_1.setDepDate(DateUtil.convertStringToDate("2016-06-20"));
				List<EffectiveGuarrntee> guarrnteeList = new ArrayList<EffectiveGuarrntee>();
				
				EffectiveGuarrntee rateGuaranteeRelationship1 = new EffectiveGuarrntee();
				EffectiveGuarrntee rateGuaranteeRelationship2 = new EffectiveGuarrntee();
				guarrnteeList.add(rateGuaranteeRelationship1);
				guarrnteeList.add(rateGuaranteeRelationship2);
				
				rateGuaranteeRelationship1.setPolicyName("6PM");
				
				rateGuaranteeRelationship2.setPolicyName("CCGTD");
				List<EffectiveGuarrntee> guarrnteeList1_1 = new ArrayList<EffectiveGuarrntee>();
				
				EffectiveGuarrntee rateGuaranteeRelationship1_1 = new EffectiveGuarrntee();
				EffectiveGuarrntee rateGuaranteeRelationship2_1 = new EffectiveGuarrntee();
				guarrnteeList1_1.add(rateGuaranteeRelationship1_1);
				guarrnteeList1_1.add(rateGuaranteeRelationship2_1);
				
				rateGuaranteeRelationship1_1.setPolicyName("7PM");
				
				rateGuaranteeRelationship2_1.setPolicyName("CCGTD");
				rateGuaranteeRelationship1.setPolicyName("7PM");
				
				rateGuaranteeRelationship2.setPolicyName("CCGTD");
				
				
				Map<Date, WbeCalendarCell> cellMap1_1 = new HashMap<Date, WbeCalendarCell>();
				wbeCalendarRow1_1.setCellMap(cellMap1_1);
				
				WbeCalendarCell wbeCalendarCell1_1 = new WbeCalendarCell();
				WbeCalendarCell wbeCalendarCell1_2 = new WbeCalendarCell();
				WbeCalendarCell wbeCalendarCell1_3 = new WbeCalendarCell();
				WbeCalendarCell wbeCalendarCell1_4 = new WbeCalendarCell();
				
				wbeCalendarCell1_1.setGuarrnteeList(guarrnteeList);
				wbeCalendarCell1_2.setGuarrnteeList(guarrnteeList1_1);
				wbeCalendarCell1_3.setGuarrnteeList(guarrnteeList);
				wbeCalendarCell1_4.setGuarrnteeList(guarrnteeList);
				
				CancelPolicyVO cancelPolicyVO1 = new CancelPolicyVO();
				CancelPolicyVO cancelPolicyVO2 = new CancelPolicyVO();
				CancelPolicyVO cancelPolicyVO3 = new CancelPolicyVO();
				CancelPolicyVO cancelPolicyVO4 = new CancelPolicyVO();
				cancelPolicyVO1.setLanguage("zh_CN");
				cancelPolicyVO1.setPolicyName("policyName1");
				cancelPolicyVO1.setDescription("description1");
				
				cancelPolicyVO2.setLanguage("zh_CN");
				cancelPolicyVO2.setPolicyName("policyName2");
				cancelPolicyVO2.setDescription("description2..description2..description2..description2");
				
				cancelPolicyVO3.setLanguage("zh_CN");
				cancelPolicyVO3.setPolicyName("policyName3");
				cancelPolicyVO3.setDescription("description3");
				
				cancelPolicyVO4.setLanguage("zh_CN");
				cancelPolicyVO4.setPolicyName("policyName4");
				cancelPolicyVO4.setDescription("description4...description4...description4...description4");
				
				wbeCalendarCell1_1.setCancelPolicyVO(cancelPolicyVO1);
				wbeCalendarCell1_2.setCancelPolicyVO(cancelPolicyVO2);
				wbeCalendarCell1_3.setCancelPolicyVO(cancelPolicyVO3);
				wbeCalendarCell1_4.setCancelPolicyVO(cancelPolicyVO4);
				
				
				cellMap1_1.put(DateUtil.convertStringToDate("2016-06-17"), wbeCalendarCell1_1);
				cellMap1_1.put(DateUtil.convertStringToDate("2016-06-18"), wbeCalendarCell1_2);
				cellMap1_1.put(DateUtil.convertStringToDate("2016-06-19"), wbeCalendarCell1_3);
				cellMap1_1.put(DateUtil.convertStringToDate("2016-06-20"), wbeCalendarCell1_4);
				
				wbeCalendarCell1_1.setCellDate(DateUtil.convertStringToDate("2016-06-17"));
				wbeCalendarCell1_1.setCellInventAvai(5);
				wbeCalendarCell1_1.setCellPrice(new BigDecimal(500));
				wbeCalendarCell1_1.setCellNumberOfUnits(1);
				wbeCalendarCell1_1.setRateAmountMap(rateAmountMap);
			
				wbeCalendarCell1_2.setCellDate(DateUtil.convertStringToDate("2016-06-18"));
				wbeCalendarCell1_2.setCellInventAvai(4);
				wbeCalendarCell1_2.setCellPrice(new BigDecimal(600));
				wbeCalendarCell1_2.setCellNumberOfUnits(1);
				wbeCalendarCell1_2.setRateAmountMap(rateAmountMap1);
				
				wbeCalendarCell1_3.setCellDate(DateUtil.convertStringToDate("2016-06-19"));
				wbeCalendarCell1_3.setCellInventAvai(4);
				wbeCalendarCell1_3.setCellPrice(new BigDecimal(600));
				wbeCalendarCell1_3.setCellNumberOfUnits(1);
				wbeCalendarCell1_3.setRateAmountMap(rateAmountMap2);
				
				wbeCalendarCell1_4.setCellDate(DateUtil.convertStringToDate("2016-06-20"));
				wbeCalendarCell1_4.setCellInventAvai(4);
				wbeCalendarCell1_4.setCellPrice(new BigDecimal(800));
				wbeCalendarCell1_4.setCellNumberOfUnits(1);
				wbeCalendarCell1_4.setRateAmountMap(rateAmountMap3);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return map;
	}
	private WbeOrderVO getWbeOrderVOTest(){
		WbeOrderVO wbeOrderVO=null;
		try {
			wbeOrderVO = new WbeOrderVO();
			wbeOrderVO.setChainCode("CCM");
			wbeOrderVO.setHotelCode("SIHJI");
			wbeOrderVO.setChannelCode("TAOBAO");
			wbeOrderVO.setRatePlanCode("WHL20");
			wbeOrderVO.setAccessCode("TAOBAO");
			wbeOrderVO.setAccessCodeType("TRAVEL_AGENT");
			wbeOrderVO.setRoomTypeCode("1KETS");
			wbeOrderVO.setNumberOfUnits(1);
			wbeOrderVO.setNumber(1);
			wbeOrderVO.setArr(DateUtil.convertStringToDate("2016-02-25"));
			wbeOrderVO.setDep(DateUtil.convertStringToDate("2016-02-26"));
			wbeOrderVO.setPayment("TAGTD");
			wbeOrderVO.setCardCode("AX");
			wbeOrderVO.setCardHolderName("nora huang");
			wbeOrderVO.setCardNumber("370000000000002");
			wbeOrderVO.setExpirationDate("2016-05");
			List<WbeGuestVO> wbeGuestVoList = new ArrayList<WbeGuestVO>();
			WbeGuestVO wbeGuestVO = new WbeGuestVO();
			wbeGuestVO.setName1("NORA");
			wbeGuestVO.setName2("NORA");
			wbeGuestVO.setName3("HUANG");
			wbeGuestVO.setTel("13812345678");
			wbeGuestVoList.add(wbeGuestVO);
			wbeOrderVO.setWbeGuestVoList(wbeGuestVoList);
			
			List<OrderDailyRateVO> dailRateList = new ArrayList<OrderDailyRateVO>();
			OrderDailyRateVO orderDailyRateVO = new OrderDailyRateVO();
			orderDailyRateVO.setPrice(91.00);
			orderDailyRateVO.setPriceDate(DateUtil.convertStringToDate("2016-02-25"));
			dailRateList.add(orderDailyRateVO);
			wbeOrderVO.setDailRateList(dailRateList);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return wbeOrderVO;
	}
	
	
}
