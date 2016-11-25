package com.ccm.mc.webapp.action.log;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.log.vo.UpDownPmsLogCriteria;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.log.ReceiveMsgLogManager;
import com.ccm.api.service.log.SendMsgLogManager;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.XMLUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;

/**
 * @author Jenny
 * 
 */
public class UpDownPmsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2153377910584706563L;

	private Log log = LogFactory.getLog(UpDownPmsAction.class);

	// 查询条件
	private UpDownPmsLogCriteria soc = new UpDownPmsLogCriteria();
	private String udpcId;
	private String upDown;
	private String hotelCode;

	// 返回结果
	private UpDownPmsLogResult upDownPmsLogResult = new UpDownPmsLogResult();
	private List<Hotel> hotelList = new ArrayList<Hotel>();

	@Autowired
	private SendMsgLogManager sendMsgLogManager;
	@Autowired
	private ReceiveMsgLogManager receiveMsgLogManager;
	@Resource
	private HotelManager hotelManager;
	/**
	 * PMS消息上传下传列表
	 * 
	 * @return
	 */
	public String list() {
		//酒店代码
		B2BUser user = getCurLoginUser();
		String chainId = user.getHotelvo().getChainId();
		this.getRequest().setAttribute("hotelList", hotelManager.getHotelByChainId(chainId));
		//上传下传消息
		HashMap<String, String> uploadorDownloadMessage = new HashMap<String, String>();
		uploadorDownloadMessage.put(null, getText("common.select.plesesSelect"));
		uploadorDownloadMessage.put("up", getText("common.Upload"));
		uploadorDownloadMessage.put("down", getText("common.Download"));
		this.getRequest().setAttribute("uploadorDownloadMessage",uploadorDownloadMessage);
		
		//消息模块
		List<String> messageModule = new ArrayList<String>();
		messageModule.add("ALLOTMENT");
		messageModule.add("RESERVATION");
		messageModule.add("FINTRX");
		messageModule.add("RESULT");
		messageModule.add("RATE");
		messageModule.add("RTAV");
		messageModule.add("RAVL");
		messageModule.add("RAVR");
		messageModule.add("INVENTORY");
		messageModule.add("PROFILE");
		this.getRequest().setAttribute("messageModule",messageModule);
		
		//日志状态
		List<String> logStatus = new ArrayList<String>();
		logStatus.add("PROCESSED");
		logStatus.add("READY");
		logStatus.add("RESULT_FAIL");
		logStatus.add("RESULT_SUCCESS");
		logStatus.add("REQUEUED");
		logStatus.add("NEW");
		logStatus.add("SUCCESS");
		logStatus.add("FAIL");
		logStatus.add("WARNTING");
		logStatus.add("HALTED");
		logStatus.add("PROCESSING");
		logStatus.add("SUSPENDED"); 
		logStatus.add("IGNORE"); 
		this.getRequest().setAttribute("logStatus",logStatus);
		
		//消息类型
		List<String> messageTypePage = new ArrayList<String>();
		messageTypePage.add("ALLOTMENT");
		messageTypePage.add("RESERVATION");
		messageTypePage.add("ADD");
		messageTypePage.add("EDIT");
		messageTypePage.add("CANCEL");
		messageTypePage.add("RATE");
		messageTypePage.add("RTAV");
		messageTypePage.add("RAVL");
		messageTypePage.add("INVENTORY");
		messageTypePage.add("PROFILE");
		this.getRequest().setAttribute("messageType",messageTypePage);
		
		if (soc.getHotelCodeList() == null) {
			if (soc.getCreateStart() == null) {
				soc.setCreateStart(DateUtil.currentDate());
			}
			if (soc.getCreateEnd() == null) {
				soc.setCreateEnd(DateUtil.addDays(soc.getCreateStart(), 1));
			}
			return "list";
		}
		//酒店代码必选
		if (null==soc.getHotelCodeList() || soc.getHotelCodeList().size()<1) {
			saveMessage(getText("ccm.PMSMessageLog.ErrorMessage.PleasePropertyCode"));
			return "list";
		}
		//酒店代码单选
		if(soc.getHotelCodeList().size()>1){
			return "list";
		}
		
		if (!StringUtils.hasText(soc.getUpDown())) {
			saveMessage(getText("ccm.PMSMessageLog.ErrorMessage.SelectUploadOrDownload"));
			return "list";
		}

		if (soc.getCreateStart() == null || soc.getCreateEnd() == null) {
			saveMessage(getText("ccm.PMSMessageLog.ErrorMessage.PleaseStartTimeAndEndTime"));
			return "list";
		}
		
		if (DateUtil.dateDiff(soc.getCreateStart(), soc.getCreateEnd()) > 14) {
			saveMessage(getText("ccm.error.005"));
			return "list";
		}
		
		// 分页
		int pageSize = this.getCurrentPageSize("order");
		int pageNo = this.getCurrentPageNo("order");
		soc.setPageNum(pageNo);
		soc.setPageSize(pageSize);
		
		if ("up".equals(soc.getUpDown())) {
			log.info("up");
			upDownPmsLogResult = receiveMsgLogManager.searchList(soc);
			
			List<ReceiveMsgLog> upMsgLogList = upDownPmsLogResult.getUpMsgLogList();
			
			//遍历查询结果 begin
			for (int i=0;i < upMsgLogList.size();) {
				ReceiveMsgLog receiveMsgLog=upMsgLogList.get(i);
				String messageType=receiveMsgLog.getMessageType();//消息模块
		
				//消息内容
				String message="";
				//“消息模块”为“RTAV”	消息内容=房型  “查看详情”的roomType，多个值用空格分隔
				if(MessageType.RTAV.equals(messageType))
				{
					ReceiveMsgLog receiveMsgLog2=receiveMsgLogManager.getReceiveMsgLog(receiveMsgLog.getReceiveMsgLogId(), receiveMsgLog.getHotelCode());				
					String message2=receiveMsgLog2.getMessage();
					//如果“消息内容日期”查询条件有值，则根据消息内容日期获取其包含的所有房型
					Date mcd=soc.getMessageContentDate();
					if(null!=mcd){
						Map<String, Set<String>> reMap=getRoomTypeMapFromXmlString(message2); 
						Set<String> rtSet=reMap.get(DateUtil.convertDateToString(mcd));
						if(null!=rtSet){
							Iterator<String> iterator=rtSet.iterator();
							while(iterator.hasNext()){
								message=message+iterator.next()+" ";
							}
						}
					//否则获取所有房型
					}else{
						message=getRoomTypeFromXmlString(message2);
					}
					
				}
				//“消息模块”为“RATE”  消息内容=房价  “查看详情”的<rateCode> </rateCode>当中的房价代码来获取
				else if(MessageType.RATE.equals(messageType))
				{			
					ReceiveMsgLog receiveMsgLog3=receiveMsgLogManager.getReceiveMsgLog(receiveMsgLog.getReceiveMsgLogId(), receiveMsgLog.getHotelCode());				
					String message3=receiveMsgLog3.getMessage();
					message=getRateCodeFromXmlString(message3);
				}
				//消息为Allotment
				else if(MessageType.ALLOTMENT.equals(messageType))
				{			
					ReceiveMsgLog receiveMsgLog4=receiveMsgLogManager.getReceiveMsgLog(receiveMsgLog.getReceiveMsgLogId(), receiveMsgLog.getHotelCode());				
					String message4=receiveMsgLog4.getMessage();
					message=getAllotmentFromXmlString(message4);
				}
				//当“消息模块”为“INVENTORY”,截取roomType的值，显示在“消息内容”的下方
				else if(MessageType.INVENTORY.equals(messageType))
				{			
					ReceiveMsgLog receiveMsgLog4=receiveMsgLogManager.getReceiveMsgLog(receiveMsgLog.getReceiveMsgLogId(), receiveMsgLog.getHotelCode());				
					String message4=receiveMsgLog4.getMessage();
					message=getInventoryFromXmlString(message4);
				}
				String mcrt=soc.getMessageContentRoomType();
				//查询条件“消息内容房型” 有值的时候
				if(StringUtils.hasText(mcrt)){
					//MessageTyp为RTAV时，查询条件包含查询结果中的“消息内容房型”，则把查询条件赋值给查询结果
					if((MessageType.RTAV.equals(messageType)) && 
						StringUtils.hasText(message) &&(message.indexOf(mcrt)>=0)){
						receiveMsgLog.setMessage(mcrt);
					//MessageTyp不为RTAV时，仅判断查询结果和查询条件是否一致
					}else if(mcrt.equals(message)){
						receiveMsgLog.setMessage(message);
					}
				}else{
					receiveMsgLog.setMessage(message);
				}
				//“消息内容房型”或者“消息内容日期”有值的时候
				if(StringUtils.hasText(soc.getMessageContentRoomType()) || (null!=soc.getMessageContentDate())){
					//则消息模块必须为RTAV且消息内容房型必须有值
					if((!MessageType.RTAV.equals(messageType))||(!StringUtils.hasText(receiveMsgLog.getMessage()))){
						upMsgLogList.remove(receiveMsgLog);		
					}else{
						i++;
					}
				}else{
					i++;
				}
				
			}
			//遍历查询结果 end
			
		} else if ("down".equals(soc.getUpDown())) {
			upDownPmsLogResult = sendMsgLogManager.searchList(soc);
		}

		this.getRequest().setAttribute("resultSize", upDownPmsLogResult.getTotalCount());
	
		return "list";

	}

	public String detail() {
		if ("up".equals(upDown)) {
			ReceiveMsgLog rml = receiveMsgLogManager.getReceiveMsgLog(udpcId, hotelCode);
			try {
				rml.setMessage(XMLUtil.formatXml(rml.getMessage()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.getRequest().setAttribute("receiveMsgLog", rml);
		} else if ("down".equals(upDown)) {
			SendMsgLog sml = sendMsgLogManager.getSendMsgLogById(udpcId, hotelCode);
			try {
				sml.setMessage(XMLUtil.formatXml(sml.getMessage()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.getRequest().setAttribute("sendMsgLog", sml);
		}
		return "detail";
	}

	public String export() {
		return "export";
	}

	public String getExportFileName() {
		return ExportUtil.createFileName("export");
	}

	public InputStream getTxtFile() throws Exception {
		try {
			if ("up".equals(upDown)) {
				ReceiveMsgLog rml = receiveMsgLogManager.getReceiveMsgLog(udpcId, hotelCode);
				ByteArrayInputStream bais = new ByteArrayInputStream(XMLUtil.formatXml(rml.getMessage()).getBytes("UTF-8"));
				return bais;
			} else if ("down".equals(upDown)) {
				SendMsgLog sml = sendMsgLogManager.getSendMsgLogById(udpcId, hotelCode);
				ByteArrayInputStream bais = new ByteArrayInputStream(XMLUtil.formatXml(sml.getMessage()).getBytes("UTF-8"));
				return bais;
			}
		} catch (Exception e) {
			log.info(e);
		}
		return null;
	}

	public String getUdpcId() {
		return udpcId;
	}

	public void setUdpcId(String udpcId) {
		this.udpcId = udpcId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getUpDown() {
		return upDown;
	}

	public void setUpDown(String upDown) {
		this.upDown = upDown;
	}

	public UpDownPmsLogCriteria getSoc() {
		return soc;
	}

	public void setSoc(UpDownPmsLogCriteria soc) {
		this.soc = soc;
	}

	public UpDownPmsLogResult getUpDownPmsLogResult() {
		return upDownPmsLogResult;
	}

	public void setUpDownPmsLogResult(UpDownPmsLogResult upDownPmsLogResult) {
		this.upDownPmsLogResult = upDownPmsLogResult;
	}

	public List<Hotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}
	
	public static Map<String,Set<String>> getRoomTypeMapFromXmlString(String xmlString) 
	{  
	 	//key为datum节点;value为datum节点下包含的所有roomType
	 	Map<String,Set<String>> reMap=new HashMap<String,Set<String>>();
        if(xmlString!=null && xmlString.length()>1){
        	 Document doc = XMLUtil.getXmlDocument(xmlString);      
	            //取得根元素  
	            Element root = doc.getRootElement();  
	            List<?> node = root.elements();  
	            for (int i = 0; i < node.size();i++) 
	            {      
	                Element element=(Element)node.get(i);      
	                List<?> subNode = element.elements();  
	                
	                for(int j=0;j<subNode.size();j++)
	                {
	                    Element subElement=(Element)subNode.get(j);
	                    List<?> subNode2 = subElement.elements(); 
	                    String datum="";
	                    Set<String> rtSet=new HashSet<String>();
	                    //----------------
	                    @SuppressWarnings({ "unchecked"})
	                    List<Attribute> aList2=subElement.attributes();
	                    for (Attribute attribute : aList2) {
                			if(attribute.getName().equals("datum")){	        
                				datum=attribute.getText();
                			}
						}
	                    //----------------
	                    for(int k=0;k<subNode2.size();k++)
	                    {
	                    	Element subElement2=(Element)subNode2.get(k);
	                    	List<?> subNode3 = subElement2.elements(); 
	                    	
	                    	for(int m=0;m<subNode3.size();m++)
	                    	{
	                    		Element subElement3=(Element)subNode3.get(m);
	                    		@SuppressWarnings("unchecked")
								List<Attribute> aList=subElement3.attributes();
	                    		for (Attribute attribute : aList) {
	                    			if(attribute.getName().equals("roomType")){	        
	                    				String temp=attribute.getText();
	                    				if(StringUtils.hasText(temp)){
	                    					rtSet.add(temp);
	                    				}
	                    			}
								}
	                    	}	
	                    }
	                    if(StringUtils.hasText(datum)){
	                    	 reMap.put(datum, rtSet);
	                    }  
	                }         
	            }      
        }
        return reMap;    
	}
	public static String getRoomTypeFromXmlString(String xmlString) 
	{  
	 	String roomTypeMessage=""; 
	 	Map<String,List<String>> reMap=new HashMap<String,List<String>>();
	 	
        if(xmlString!=null && xmlString.length()>1){
        	 Document doc = XMLUtil.getXmlDocument(xmlString);      
	            //取得根元素  
	            Element root = doc.getRootElement();  
	           
	            List<?> node = root.elements();  
	       
	            for (int i = 0; i < node.size();i++) 
	            {      
	                Element element=(Element)node.get(i);      
	                List<?> subNode = element.elements();  
	                
	                for(int j=0;j<subNode.size();j++)
	                {
	                    Element subElement=(Element)subNode.get(j);
	                    List<?> subNode2 = subElement.elements(); 
	                    String datum="";
	                    List<String> rtList=new ArrayList<String>();
	                    //----------------
	                    @SuppressWarnings({ "unchecked"})
	                    List<Attribute> aList2=subElement.attributes();
	                    for (Attribute attribute : aList2) {
                			if(attribute.getName().equals("datum")){	        
                				datum=attribute.getText();
                			}
						}
	                    //----------------
	                    for(int k=0;k<subNode2.size();k++)
	                    {
	                    	Element subElement2=(Element)subNode2.get(k);
	                    	List<?> subNode3 = subElement2.elements(); 
	                    	
	                    	for(int m=0;m<subNode3.size();m++)
	                    	{
	                    		Element subElement3=(Element)subNode3.get(m);
	                    		@SuppressWarnings("unchecked")
								List<Attribute> aList=subElement3.attributes();
	                    		for (Attribute attribute : aList) {
	                    			if(attribute.getName().equals("roomType")){	        
	                    				String temp=attribute.getText();
	                    				
	                    				rtList.add(temp);
	                    				//去除重复
	    	                    		String temp2=roomTypeMessage.indexOf(temp)>=0?"":(temp+" ");              		
	    	                    		roomTypeMessage+=temp2;
	                    			}
								}
	                    	}	
	                    }        
	                    
	                    reMap.put(datum, rtList);
	                }         
	            }      
        }
        return roomTypeMessage;    
	 }
	 
	 public static String getRateCodeFromXmlString(String xmlString)
	 {  
		 	String rateCodeMessage=""; 
	        if(xmlString!=null && xmlString.length()>1){
	        	Document doc = XMLUtil.getXmlDocument(xmlString); 
	        	  if(doc!=null){	       
	  	        	//取的根元素  
	  		        Element root = doc.getRootElement();   
	  		        List<?> node = root.elements();       
	  		        for (int i = 0; i < node.size();i++) 
	  		        {
	  		           Element element=(Element)node.get(i);
	  		           if(element.getName().equals("rateCode")){  
	  		            	 rateCodeMessage=element.getText();
	  		            }        
	  		        }
	  	        }
	        }     
	        return rateCodeMessage;    
	  }
	 
	 public static String getAllotmentFromXmlString(String xmlString)
	 {  
		 	String allotmentMessage=""; 
	       
		 	if(xmlString!=null && xmlString.length()>1){
		 		 Document doc = XMLUtil.getXmlDocument(xmlString);      
			        //取的根元素  
			        Element root = doc.getRootElement();   
			        List<?> node = root.elements();       
			        for (int i = 0; i < node.size();i++) 
			        {
			           Element element=(Element)node.get(i);
			           if(element.getName().equals("inventoryBlockGroupingCode")){  
			        	   allotmentMessage="Block code:"+element.getText();
			            }        
			        }
		 	}
	        
	        return allotmentMessage;    
	    }

	 public static String getInventoryFromXmlString(String xmlString)
	 {  
			StringBuffer inventoryMessage=new StringBuffer(""); 
			if(xmlString!=null && xmlString.length()>1){
				 Document doc = XMLUtil.getXmlDocument(xmlString);      
			        //取的根元素  
			        Element root = doc.getRootElement();   
			        List<?> node = root.elements();       
			        for (int i = 0; i < node.size();i++) 
			        {
			           Element element=(Element)node.get(i);
			           List<?> node2 = element.elements();
			           for (int j = 0; j < node2.size();j++) 
			           {
			        	   Element element2=(Element)node2.get(j);
			        	   List<?> node3 = element2.elements();
			        	   for (int k = 0; k < node3.size();k++) 
				           {
			        		   Element element3=(Element)node3.get(k);
				        	   if(element3.getName().equals("roomType")){
				        		   //重复的roomType只取一次
				        		   String temp=element3.getText();
		                   		   String temp2=inventoryMessage.indexOf(temp)>=0?"":(temp);  
				        		   inventoryMessage=inventoryMessage.append(" "+temp2);
					            }   
				           }
			           }

			        }     
			}
	        return inventoryMessage.toString();    
	  }
	 
}