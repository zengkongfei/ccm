package com.ccm.mc.webapp.action.hotel;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.hotel.vo.HotelSwitchCriteria;
import com.ccm.api.model.hotel.vo.HotelSwitchResult;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.B2BUser;

import com.ccm.api.service.hotel.HotelSwitchManager;
import com.ccm.mc.webapp.action.base.BaseAction;

/**
 * 酒店开关/控制按钮
 * 
 * @author Water
 * 
 */
public class HotelSwitchAction extends BaseAction {
	private static final long serialVersionUID = -4834810001607710441L;

	private HotelSwitchCriteria soc=new HotelSwitchCriteria();
	HotelSwitchResult hotelSwitchResult=new HotelSwitchResult();
	@Autowired
	private HotelSwitchManager hotelSwitchManager;
	private HotelSwitch hotelSwitch;
	private String hsId;
	
	public String list(){
		B2BUser user = getCurLoginUser();
		HotelVO hotelVO=user.getHotelvo();
		String hotelId=hotelVO.getHotelId();
		hotelSwitch=hotelSwitchManager.getByHotelId(hotelId);
		
		if (null==hotelSwitch) {
			hotelSwitch = new HotelSwitch();
			
			hotelSwitch.setHotelId(hotelId);
			hotelSwitch.setChainId(hotelVO.getChainId());
			hotelSwitch.setHotelCode(hotelVO.getHotelCode());
			hotelSwitch.setChainCode(hotelVO.getChainCode());
			
			hotelSwitch.setIsGenerates(false);
			hotelSwitch.setIsHardCancel(false);
			hotelSwitch.setIsUploadRateHeader(false);
			hotelSwitch.setIsMask(true);
			hotelSwitch.setIsMonitorADSPending(true);
			hotelSwitch.setIsDiscount(false);
			
			hotelSwitchManager.addHotelSwitch(hotelSwitch);
		}
		
		return "list";
	}
	public String save(){
		//System.out.println("getIsHardCancel==="+hotelSwitch.getIsHardCancel());
		//System.out.println("getIsGenerates==="+hotelSwitch.getIsGenerates());
		//System.out.println("getIsGenerates==="+hotelSwitch.getIsUploadRateHeader());
	
		B2BUser user = getCurLoginUser();
		HotelVO hotelVO=user.getHotelvo();
		String hotelId=hotelVO.getHotelId();
		hotelSwitch.setHotelId(hotelId);
		//更新酒店控制按钮
		hotelSwitchManager.updateByHotelId(hotelSwitch);
		
		return list();
	}
	
	public String toEdit(){
		return "toEdit";
	}
	
	public HotelSwitchResult getHotelSwitchResult() {
		return hotelSwitchResult;
	}
	public void setHotelSwitchResult(HotelSwitchResult hotelSwitchResult) {
		this.hotelSwitchResult = hotelSwitchResult;
	}
	public HotelSwitch getHotelSwitch() {
		return hotelSwitch;
	}
	public void setHotelSwitch(HotelSwitch hotelSwitch) {
		this.hotelSwitch = hotelSwitch;
	}
	public String getHsId() {
		return hsId;
	}
	public void setHsId(String hsId) {
		this.hsId = hsId;
	}
	public HotelSwitchCriteria getSoc() {
		return soc;
	}
	public void setSoc(HotelSwitchCriteria soc) {
		this.soc = soc;
	}
	public HotelSwitchManager getHotelSwitchManager() {
		return hotelSwitchManager;
	}
	public void setHotelSwitchManager(HotelSwitchManager hotelSwitchManager) {
		this.hotelSwitchManager = hotelSwitchManager;
	}
	
}
