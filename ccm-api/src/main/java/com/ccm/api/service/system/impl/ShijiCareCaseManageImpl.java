package com.ccm.api.service.system.impl;

import java.util.Date;
import java.util.List;

import javax.xml.soap.SOAPElement;

import org.apache.axis.message.SOAPHeaderElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tempuri.CCMServicesLocator;
import org.tempuri.CCMServicesSoapStub;

import com.ccm.api.dao.shijiCare.ShijiCareDao;
import com.ccm.api.model.constant.ProjectConfigConstant;
import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.system.ShijiCareCaseManage;
import com.ccm.api.util.CommonUtil;

@Service("shijiCareCaseManage")
public class ShijiCareCaseManageImpl extends GenericManagerImpl<ShijiCare, String> implements ShijiCareCaseManage {
	@Autowired    
	private ShijiCareDao shijiCareDao;
	@Autowired  
	public void setBookChannelDao(ShijiCareDao shijiCareDao) {
	    this.dao = shijiCareDao;
	    this.shijiCareDao = shijiCareDao;
	}
	
	/**
	 * 建立shiji care
	 */
	@Override
	public ShijiCare newCase(ShijiCare shijiCare) {
		
		 try {
			CCMServicesLocator service = new CCMServicesLocator();
			java.net.URL url = new java.net.URL(ProjectConfigConstant.shijicareUrl);
			String username = ProjectConfigConstant.shijicareUsername;
			String pwd = ProjectConfigConstant.shijicarePassword;
			SOAPHeaderElement  oHeaderElement = new SOAPHeaderElement("http://tempuri.org/","SoapHeaderOut");
			oHeaderElement.setPrefix("tem");
			oHeaderElement.setMustUnderstand(true);
			SOAPElement oElement = oHeaderElement.addChildElement("Username"); 
			oElement.addTextNode(username);
			oElement = oHeaderElement.addChildElement("Password");
			oElement.addTextNode(pwd);
			CCMServicesSoapStub stub = new CCMServicesSoapStub(url, service);
			
			stub.setHeader(oHeaderElement);
			//0:用户合法验证失败, 1：添加成功，返回添加成功的CaseID，2：添加失败，3：没操作权限号1003，4：参数必须输入,不能为空，5：Priority参数比需是整形类型,6: 酒店ID不存在
			/*
			 * 0:用户合法验证失败, 1：添加成功，返回添加成功的CaseID，2：添加失败，3：没操作权限号1003，
			 * 4：参数必须输入,不能为空，5：Priority参数比需是整形类型,6: PropertyID不存在,
			 * 7:ProblemType不存在,8:Attention不存在，9:该酒店不存在Suite,10:该酒店不存在ProductID,
			 * 11:Suite下没有该ProductID
			 */
			String caseStr = stub.newCase(shijiCare.getHotelCode(), shijiCare.getSuite(), shijiCare.getProductId(), 1, shijiCare.getBriefDescription(), shijiCare.getFullDescription(), shijiCare.getProblemType(), shijiCare.getAssignTo());
//			String caseStr = "等待shiji care";
			if("0".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg("用户合法验证失败");
				shijiCare.setStatus("FAIL");
			}else if("1".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg(caseStr);
				shijiCare.setStatus("SUCCESS");
			}else if("2".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg("没操作权限号");
				shijiCare.setStatus("FAIL");
			}else if("4".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg("参数必须输入,不能为空");
				shijiCare.setStatus("FAIL");
			}else if("5".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg("Priority参数比需是整形类型");
				shijiCare.setStatus("FAIL");
			}else if("6".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg("酒店ID不存在");
				shijiCare.setStatus("FAIL");
			}else if("7".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg("ProblemType不存在");
				shijiCare.setStatus("FAIL");
			}else if("8".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg("Attention不存在");
				shijiCare.setStatus("FAIL");
			}else if("9".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg("该酒店不存在Suite");
				shijiCare.setStatus("FAIL");
			}else if("10".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg("该酒店不存在ProductID");
				shijiCare.setStatus("FAIL");
			}else if("11".equalsIgnoreCase(caseStr)){
				shijiCare.setResultMsg("Suite下没有该ProductID");
				shijiCare.setStatus("FAIL");
			}else{
				shijiCare.setResultMsg(caseStr);
				shijiCare.setStatus("SUCCESS");
			}
		 } catch (Exception e) {
			shijiCare.setResultMsg( CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
			shijiCare.setStatus("FAIL");
		}  finally{
			shijiCare.setCloseCode("99");//默认给99
			shijiCareDao.save(shijiCare);
		}
		return shijiCare;
	}

	@Override
	public Boolean closeCase(String shijicareId, String careId) {
		ShijiCare shijiCare = new ShijiCare();
		shijiCare.setResultMsg(careId);
		shijiCare.setCareId(shijicareId);
		Boolean result = false;
		 try {
				CCMServicesLocator service = new CCMServicesLocator();
				java.net.URL url = new java.net.URL(ProjectConfigConstant.shijicareUrl);
				String username = ProjectConfigConstant.shijicareUsername;
				String pwd = ProjectConfigConstant.shijicarePassword;
//				java.net.URL url = new java.net.URL("https://shijicare-service.shijicloud.com:8443/WebServices/CCMServices.asmx");
//				String username = "kyy1oiKpOtf29YkGlpyKlA==";
//				String pwd = "hGfw2EePUyo=";
				SOAPHeaderElement  oHeaderElement = new SOAPHeaderElement("http://tempuri.org/","SoapHeaderOut");
				oHeaderElement.setPrefix("tem");
				oHeaderElement.setMustUnderstand(true);
				SOAPElement oElement = oHeaderElement.addChildElement("Username"); 
				oElement.addTextNode(username);
				oElement = oHeaderElement.addChildElement("Password");
				oElement.addTextNode(pwd);
				CCMServicesSoapStub stub = new CCMServicesSoapStub(url, service);
				
				stub.setHeader(oHeaderElement);
				//返回值 ->0:用户合法验证失败, 1：成功关闭Case，2：关闭Case失败（参数合法性验证），3：没操作权限号1003
				//20.服务器内部错误，21.case不存在，22.case已关闭，23.case id为空，24.reasoncode为空，25.solution为空
				String caseStr = stub.closeCase(careId, ShijiCare.CLOSE_REASONCODE, ShijiCare.SUITE_SOLUTION);
				shijiCare.setCloseCode(caseStr);
				shijiCare.setIsclose(true);
				if("0".equalsIgnoreCase(caseStr)){
					shijiCare.setCloseDesp("用户合法验证失败");
				}else if("1".equalsIgnoreCase(caseStr)){
					shijiCare.setCloseDesp("成功关闭Case");
				}else if("2".equalsIgnoreCase(caseStr)){
					shijiCare.setCloseDesp("关闭Case失败（参数合法性验证）");
				}else if("3".equalsIgnoreCase(caseStr)){
					shijiCare.setCloseDesp("没操作权限号1003");
				}else if("20".equalsIgnoreCase(caseStr)){
					shijiCare.setCloseDesp("shijicare服务器内部错误");
				}else if("21".equalsIgnoreCase(caseStr)){
					shijiCare.setCloseDesp("case不存在");
				}else if("22".equalsIgnoreCase(caseStr)){
					shijiCare.setCloseDesp("case表示已关闭");
				}else if("23".equalsIgnoreCase(caseStr)){
					shijiCare.setCloseDesp("case id为空");
				}else if("24".equalsIgnoreCase(caseStr)){
					shijiCare.setCloseDesp("reasoncode为空");
				}else if("25".equalsIgnoreCase(caseStr)){
					shijiCare.setCloseDesp("solution为空");
				}
				result = true;
			 } catch (Exception e) {
				shijiCare.setIsclose(false);
				result = false;
				shijiCare.setCloseDesp( CommonUtil.getExceptionMsg(e, new String[] { "ccm" }));
			}  finally{
				shijiCare.setLastModifyTime(new Date());
				shijiCareDao.closeShijicareBycareId(shijiCare);
			}
			return result;
	}

	@Override
	public List<ShijiCare> shijiCareList(ShijiCare shijiCare) {
		return shijiCareDao.shijiCareList(shijiCare);
	}

}
