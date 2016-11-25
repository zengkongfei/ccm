package com.ccm.api.service.data;

import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.model.ads.AdsOrderMessage;
import com.ccm.api.service.ads.AdsOrderManager;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

public class adsOrderReaderTest extends BaseManagerTestCase{

	@Resource
    private AdsOrderManager adsOrderManager;
	
	private String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * this test case has been refined.
	 * 
	 * @throws Exception
	 */
	@Test
	@Rollback(false)
	public void readerXMLData() throws Exception {
		String errMsg = null;
        //xml文档对象以及sax读取对象
        Document document =  null;
        SAXReader saxReader = new SAXReader();
        String message = this.getXMLString();
        
        try {
			document = saxReader.read(new StringReader(message));
			
			//开始读取节点:StayInfoList 下的  HotelReference节点
			List<Node> hotelRefList = 
				document.selectNodes("/StayHistoryRequest/*/*/*");
			
			if(hotelRefList.size()>0){
				for (Node hotelRefNode : hotelRefList) {
					Element hotelRefEle = (Element) hotelRefNode;
					
					//获取集团代码和酒店代码
					String chainCode = hotelRefEle.attributeValue("chainCode").trim();
					String hotelCode = hotelRefEle.attributeValue("hotelCode").trim();
					
					//获取stayInfo标签下的StayInfoDetail标签组
					List<Element> stayDetailList = hotelRefEle.element("StayInfo").elements("StayInfoDetail");
					if(stayDetailList.size()>0){
						for (Element stayelement : stayDetailList) {
							String channelResId = stayelement.elementTextTrim("ChannelResID");
							String confirmationNo = stayelement.elementTextTrim("ConfirmationNo");
							String pmsResId = stayelement.elementTextTrim("PmsResID");
							String status = stayelement.elementTextTrim("Status");
							String guestFirstName = stayelement.elementTextTrim("GuestFirstName");
							String guestLastName = stayelement.elementTextTrim("GuestLastName");
							String arrivalTimeStr = stayelement.elementTextTrim("Arrival");
							String departureTimeStr = stayelement.elementTextTrim("Departure");
							String roomType = stayelement.elementTextTrim("RoomType");
							String rateCode = stayelement.elementTextTrim("RateCode");
							String roomNo = stayelement.elementTextTrim("RoomNo");
							String channelCode = stayelement.elementTextTrim("Channel");
							String resDateStr = stayelement.elementTextTrim("ResDate");
							String remark = stayelement.elementTextTrim("Remark");

							//转换时间
							Date arrivalTime = null;
							Date departureTime = null;
							Date resDate = null;
							
							//去掉时间字符串中可能出现的T并转成Date类型
							if(CommonUtil.isNotEmpty(arrivalTimeStr)){
								arrivalTimeStr = arrivalTimeStr.replace("T", " ");
								arrivalTime = DateUtil.convertStringToDate(FORMAT_YYYY_MM_DD_HH_MM_SS, arrivalTimeStr);
							}
							if(CommonUtil.isNotEmpty(departureTimeStr)){
								departureTimeStr = departureTimeStr.replace("T", " ");
								departureTime = DateUtil.convertStringToDate(FORMAT_YYYY_MM_DD_HH_MM_SS, departureTimeStr);
							}								
							if(CommonUtil.isNotEmpty(resDateStr)){
								resDateStr = resDateStr.replace("T", " ");
								resDate = DateUtil.convertStringToDate(FORMAT_YYYY_MM_DD_HH_MM_SS, resDateStr);
							}							

							//创建对象并设置值
							AdsOrderMessage adsOrderMessage = new AdsOrderMessage();
							adsOrderMessage.setChainCode(chainCode);
							adsOrderMessage.setHotelCode(hotelCode);
							adsOrderMessage.setChannelResId(channelResId);
							adsOrderMessage.setConfirmationNo(confirmationNo);
							adsOrderMessage.setPmsResId(pmsResId);
							adsOrderMessage.setStatus(status);
							adsOrderMessage.setGuestFirstName(guestFirstName);
							adsOrderMessage.setGuestLastName(guestLastName);
							adsOrderMessage.setArrivalTime(arrivalTime);
							adsOrderMessage.setDepartureTime(departureTime);
							adsOrderMessage.setResDate(resDate);
							adsOrderMessage.setRoomType(roomType);
							adsOrderMessage.setRateCode(rateCode);
							adsOrderMessage.setRoomNo(roomNo);
							adsOrderMessage.setChannelCode(channelCode);
							adsOrderMessage.setRemark(remark);
							
							this.saveAdsOrderMessage(adsOrderMessage);
						}
					}
					
				}
			}

		} catch (DocumentException e) {
			errMsg = "xml文档解析失败,"+CommonUtil.getExceptionMsg(e,"ccm");
           
		} catch (ParseException e) {
			errMsg = "时间格式错误,"+CommonUtil.getExceptionMsg(e,"ccm");
			
		} catch (BizException e) {
			errMsg = e.getErrMsg();
		}
		
		System.out.println(errMsg);
	}
	
	/**
     * 保存ads订单推送数据
     * @param adsOrderMessage
     * @throws BizException
     */
    private void saveAdsOrderMessage(AdsOrderMessage adsOrderMessage) throws BizException{
    	
    	try {
    		//校验集团编号和酒店编号不能为空
			if(CommonUtil.isEmpty(adsOrderMessage.getChainCode())){
				throw new BizException("adsOrder error", "集团代码{chainCode}不能为空");
			}
			if(CommonUtil.isEmpty(adsOrderMessage.getHotelCode())){
				throw new BizException("adsOrder error", "酒店代码{hotelCode}不能为空");
			}
			
			//校验长度
			if(adsOrderMessage.getChainCode().length()>8){
				throw new BizException("adsOrder error", "集团代码{chainCode}:["+adsOrderMessage.getChainCode()+"]长度超过8位");
			}
			if(adsOrderMessage.getHotelCode().length()>16){
				throw new BizException("adsOrder error", "酒店代码{hotelCode}:["+adsOrderMessage.getHotelCode()+"]长度超过16位");
			}
			if(adsOrderMessage.getChannelResId().length()>32){
				throw new BizException("adsOrder error", "渠道订单号{ChannelResID}:["+adsOrderMessage.getChannelResId()+"]长度超过32位");
			}
			if(adsOrderMessage.getConfirmationNo().length()>10){
				throw new BizException("adsOrder error", "CRS订单号{ConfirmationNo}:["+adsOrderMessage.getConfirmationNo()+"]长度超过10位");
			}
			if(adsOrderMessage.getPmsResId().length()>32){
				throw new BizException("adsOrder error", "PMS编号{PmsResID}:["+adsOrderMessage.getPmsResId()+"]长度超过32位");
			}
			if(adsOrderMessage.getChannelCode().length()>10){
				throw new BizException("adsOrder error", "渠道代码{Channel}:["+adsOrderMessage.getChannelCode()+"]长度超过10位");
			}

    		adsOrderManager.createAdsMessage(adsOrderMessage);
		} catch (BizException e) {
			throw new BizException(e.getErrKey(), e.getErrMsg());
		}catch (Exception e) {
			throw new BizException("adsOrder save", "信息保存失败!");
		}
    }
	
	private String getXMLString(){
		String xml = "<?xml version='1.0' encoding='Gb2312'?>"+
		"<StayHistoryRequest xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns='http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayHistory/'>"+
			"<StayHistoryNotification>"+
				"<StayInfoList>"+
					"<HotelReference chainCode='JLG2' hotelCode='078'>"+
						"<StayInfo xmlns='http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayInfo/'>"+
							"<StayInfoDetail>"+
								"<ChannelResID>TAOBAO333233</ChannelResID>"+
								"<ConfirmationNo>C140670038</ConfirmationNo>"+
								"<PmsResID>F14C110199</PmsResID>"+
								"<Status>CHECKED OUT</Status>"+
								"<GuestFirstName>WU</GuestFirstName>"+
								"<GuestLastName>FUMENG</GuestLastName>"+
								"<Arrival>2014-03-11T17:08:09</Arrival>"+
								"<Departure>2014-03-12T09:03:41</Departure>"+
								"<RoomType>ST</RoomType>"+
								"<RateCode>CNS-TJ</RateCode>"+
								"<RoomNo>1516 </RoomNo>"+
								"<Channel>TAOBAO</Channel>"+
								"<ResDate>2014-03-11T16:22:07</ResDate>"+
								"<Remark/>"+
							"</StayInfoDetail>"+
						"</StayInfo>"+
					"</HotelReference>"+
				"</StayInfoList>"+
			"</StayHistoryNotification>"+
		"</StayHistoryRequest>";
		
		return xml;
	}
	
}
