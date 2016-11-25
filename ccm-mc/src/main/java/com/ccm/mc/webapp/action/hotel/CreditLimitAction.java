package com.ccm.mc.webapp.action.hotel;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.hotel.CreditLimit;
import com.ccm.api.model.hotel.HotelCreditLimitBinding;
import com.ccm.api.model.hotel.vo.CreditLimitCreteria;
import com.ccm.api.model.hotel.vo.CreditLimitResult;
import com.ccm.api.model.hotel.vo.CreditLimitVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.service.hotel.CreditLimitManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.mc.webapp.action.base.BaseAction;

public class CreditLimitAction extends BaseAction {
	private Log log = LogFactory.getLog(CreditLimitAction.class);
	private CreditLimitCreteria creteria = new CreditLimitCreteria();
	private CreditLimitResult result = new CreditLimitResult();
	
	@Autowired
	private CreditLimitManager creditLimitManager;
	
	private CreditLimit creditLimit;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5398911578816805521L;
	public String list(){
		List<HotelVO> hotelList = getCurLoginUser().getHotelVOs();
		List<Channel> channelList = getCurLoginUser().getChannels();
		getRequest().setAttribute("channelList",channelList);
		getRequest().setAttribute("hotelList", hotelList);
		
		if(!StringUtils.hasText(creteria.getChannelId())){
			return "list";
		}
		int pageSize = this.getCurrentPageSize("creditLimitList");
		int pageNo = this.getCurrentPageNo("creditLimitList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		creteria.setNeedPage(Boolean.TRUE);
		result = creditLimitManager.getCreditLimitList(creteria);
		
		return "list";
	}
	
	public String edit(){
		List<HotelVO> hotelList = getCurLoginUser().getHotelVOs();
		List<Channel> channelList = getCurLoginUser().getChannels();
		getRequest().setAttribute("channelList",channelList);
		getRequest().setAttribute("hotelList", hotelList);
		
		String creditLimitId = getRequest().getParameter("creditLimitId");
		if(StringUtils.hasText(creditLimitId)){
			CreditLimitVO creditLimitVO = creditLimitManager.getCreditLimitVO(creditLimitId);
			getRequest().setAttribute("creditLimit", creditLimitVO);
		}
		return "edit";
	}
	
	public String delete(){
		String creditLimitId = getRequest().getParameter("creditLimitId");
		creditLimitManager.removeCreditLimit(creditLimitId);
		return list();
	}
	
	public void save(){
		try {
			String hoteIds = getRequest().getParameter("hotelIds");
			String hoteCodes = getRequest().getParameter("hotelCodes");
			String[] hotelIdList = hoteIds.split(",");
			String[] hotelCodeList = hoteCodes.split(",");
			List<HotelCreditLimitBinding> hotelCreditLimitBindingList = new ArrayList<HotelCreditLimitBinding>();
			
			if(CommonUtil.isNotEmpty(creditLimit.getVariable())){
				if(CommonUtil.isEmpty(creditLimit.getIncome())){
					creditLimit.setIncome(new BigDecimal("0"));
				}
				creditLimit.setIncome(creditLimit.getIncome().add(creditLimit.getVariable()));
			}
			
			for(int i=0;i<hotelIdList.length;i++){
				HotelCreditLimitBinding hotelCreditLimitBinding = new HotelCreditLimitBinding();
				hotelCreditLimitBinding.setChannelId(creditLimit.getChannelId());
				hotelCreditLimitBinding.setChannelCode(creditLimit.getChannelCode());
				hotelCreditLimitBinding.setHotelCode(hotelCodeList[i].trim());
				hotelCreditLimitBinding.setHotelId(hotelIdList[i].trim());
				hotelCreditLimitBindingList.add(hotelCreditLimitBinding);
			}
			creditLimitManager.saveCreditLimit(creditLimit, hotelCreditLimitBindingList);
			ajaxResponse("success");
		} catch (Exception e) {
			log.equals(e);
			ajaxResponse("error");
		}
	}
	
	public void checkBind(){
		String channelId = getRequest().getParameter("channelId");
		String channelCode = getRequest().getParameter("channelCode");
		String hoteIds = getRequest().getParameter("hotelIds");
		String hoteCodes = getRequest().getParameter("hotelCodes");
		String creditLimitId = getRequest().getParameter("creditLimitId");
		String[] hotelIdList = hoteIds.split(",");
		String[] hotelCodeList = hoteCodes.split(",");
		CreditLimitVO creditLimitVO = null;
		if(StringUtils.hasText(creditLimitId)){
			creditLimitVO = creditLimitManager.getCreditLimitVO(creditLimitId);
		}
		List<String> hotelIds = new ArrayList<String>();
		if(creditLimitVO!=null){
			hotelIds = creditLimitVO.getHotelIds();
		}
		Map<String,String> map = new HashMap<String, String>();
		/**
		 * 新绑定的酒店
		 */
		for(int i=0;i<hotelIdList.length;i++){
			if(!hotelIds.contains(hotelIdList[i].trim())){
				map.put(hotelIdList[i].trim(), hotelCodeList[i].trim());
			}
		}
		for (String key : map.keySet()) {  
			Boolean bool = creditLimitManager.checkExisted(channelId,key);
			if(bool){
				String msg = MessageFormat.format(getText("ccm.creditlimit.message1"), channelCode,map.get(key));
				ajaxResponse(msg);
				return;
			}
		}  
		ajaxResponse("true");
	}

	public CreditLimitCreteria getCreteria() {
		return creteria;
	}

	public void setCreteria(CreditLimitCreteria creteria) {
		this.creteria = creteria;
	}

	public CreditLimitResult getResult() {
		return result;
	}

	public void setResult(CreditLimitResult result) {
		this.result = result;
	}

	public CreditLimit getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(CreditLimit creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	
}
