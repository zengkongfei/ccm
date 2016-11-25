package com.ccm.mc.webapp.action.hotel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.vo.CustomCreteria;
import com.ccm.api.model.hotel.vo.CustomResult;
import com.ccm.api.model.hotel.vo.HotelCancelVO;
import com.ccm.api.model.hotel.vo.HotelGuaranteeVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.log.CustomLog;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.hotel.CustomManager;
import com.ccm.api.service.hotel.HotelCancelManager;
import com.ccm.api.service.hotel.HotelGuaranteeManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.log.CustomLogManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 客户
 * 
 * @author carr
 * 
 */
public class CustomAction extends BaseAction {

	private static final long serialVersionUID = -4834810001607710441L;

	@Autowired
	private CustomManager customManager;
	
	@Autowired
	private HotelManager hotelManager;
	
	@Resource
	private ChannelManager channelManager;
	// 渠道列表
	List<Channel> channelList;
	//酒店担保规则
	private List<HotelGuaranteeVO> hotelGuaranteeVOList;
	
	private List<HotelCancelVO> hotelCancelVOList;
	
	@Autowired
	private CustomLogManager customLogManager;

	@Resource
	private HotelGuaranteeManager hotelGuaranteeManager;
	
	@Resource
	private HotelCancelManager hotelCancelManager;
	
	
	private CustomCreteria creteria = new CustomCreteria();
	private CustomResult result = new CustomResult();
	private Custom custom;
	private String customId;
	private CustomLog customLog=new CustomLog();

	/**
	 * 客户列表显示
	 */
	public String list() {

		int pageSize = this.getCurrentPageSize("hotelList");
		int pageNo = this.getCurrentPageNo("hotelList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);

		B2BUser user = getCurLoginUser();
		creteria.setHotelId(user.getHotelvo().getHotelId());
		result = customManager.searchCustom(creteria);
		//客户类型
		HashMap<String, String> profileType = new HashMap<String, String>();
		profileType.put(null, getText("common.select.plesesSelect"));
		profileType.put("TRAVEL_AGENT", "TRAVEL_AGENT");
		profileType.put("CORPORATE", "CORPORATE");
		this.getRequest().setAttribute("profileType", profileType);
		return "list";
	}

	/**
	 * 创建/编辑
	 */
	public String edit() {
		this.setInitCondition();
		if (StringUtils.hasText(customId)) {
			custom = customManager.get(customId);
			
			Locale locale = ActionContext.getContext().getLocale();
			String language = locale.getLanguage()+"_"+locale.getCountry();
			
			HotelGuaranteeVO hgVo = new HotelGuaranteeVO();
			hgVo.setHotelId(custom.getHotelId());
			hgVo.setLanguage(language);
			hotelGuaranteeVOList = hotelGuaranteeManager.getHotelGuaranteeByObj(hgVo);
			
			// 取消规则
			HotelCancelVO hcVo = new HotelCancelVO();
			hcVo.setHotelId(custom.getHotelId());
			hcVo.setLanguage(language);
			hotelCancelVOList = hotelCancelManager.getHotelCancelByObj(hcVo);
			
			
			String channelId=custom.getChannelId();
			if((channelId!=null)&&(channelId.length()>0)){
				Channel channel=channelManager.get(channelId);
				if(null!=channel){
					//默认选中的ChannelCode
					custom.setChannelCode(channel.getChannelCode());
				}
			}
				
			this.getSession().setAttribute("customId", customId);
			this.getSession().setAttribute("isEdit", "true");
			//Balance为保证金管理公式的余额，通过公式OriginalCreditLimit+Income-TotalRoomRev-MinLimit计算
			if(custom.getTotalRoomRev()==null || custom.getTotalRoomRev().length()<1){
				custom.setTotalRoomRev("0.00");
			}
			if(custom.getIncome()==null || custom.getIncome().length()<1){
				custom.setIncome("0.00");
			}
			if(custom.getMinLimit()==null || custom.getMinLimit().length()<1){
				custom.setMinLimit("0.00");
			}
			if(custom.getOriginalCreditLimit()==null || custom.getOriginalCreditLimit().length()<1){
				custom.setOriginalCreditLimit("0.00");
			}
			
		    String incomeS=custom.getIncome();  
			String minLimitS=custom.getMinLimit();
			String originalCreditLimitS=custom.getOriginalCreditLimit();
			String totalRoomRevS=custom.getTotalRoomRev();
			
			BigDecimal incomeB=new BigDecimal(incomeS);
			BigDecimal minLimitB=new BigDecimal(minLimitS);
			BigDecimal originalCreditLimitB=new BigDecimal(originalCreditLimitS);
			BigDecimal totalRoomRevB=new BigDecimal(totalRoomRevS);	
			custom.setBalance((originalCreditLimitB.add(incomeB)).subtract((totalRoomRevB.add(minLimitB))).toString());
			this.getSession().setAttribute("balance", custom.getBalance());
		} else {
			custom = new Custom();
		}
		
		//客户类型
		HashMap<String, String> profileType = new HashMap<String, String>();
		profileType.put(null, getText("common.select.plesesSelect"));
		profileType.put("TRAVEL_AGENT", "TRAVEL_AGENT");
		profileType.put("CORPORATE", "CORPORATE");
		this.getRequest().setAttribute("profileType", profileType);
		return "edit";
	}

	/**
	 * 保存/修改
	 */
	public String save() {
		this.setInitCondition();
		if (!StringUtils.hasText(custom.getAccessCode())) {
			saveMessage("请输入AccessCode");
			return "edit";
		}
		
		//保存channelId
		Channel channel=channelManager.getChannelByChannelCode(custom.getChannelCode());
		if(null!=channel){
			custom.setChannelId(channel.getChannelId());
		}
			
		//客户类型
		HashMap<String, String> profileType = new HashMap<String, String>();
		profileType.put(null, getText("common.select.plesesSelect"));
		profileType.put("TRAVEL_AGENT", "TRAVEL_AGENT");
		profileType.put("CORPORATE", "CORPORATE");
		this.getRequest().setAttribute("profileType", profileType);
		B2BUser user = getCurLoginUser();
		custom.setHotelId(user.getHotelvo().getHotelId());
		if (customManager.existCustom(custom)) {
			saveMessage(getText("ccm.custom.DuplicateAccessCode"));
			return "edit";
		}
		String profileID=custom.getProfileID();
		if ((null!=profileID)&&(profileID.length()>0)&&(customManager.existProfileID(custom))) {
			saveMessage(getText("ccm.custom.DuplicateProfileID"));
			return "edit";
		}
		if("".equals(profileID)){
			custom.setProfileID(null);
		}
		//判断AccessCode与channelCode是否一致
		/*
		String channelCode=custom.getChannelCode();
		String accessCode=custom.getAccessCode();
		if((null==channelCode)||(null==accessCode)||(!channelCode.equals(accessCode))){
			saveMessage(getText("ccm.ProfileList.ProfileConfirmTip2"));
			return "edit";
		}else{
			Channel channel=channelManager.getChannelByChannelCode(custom.getChannelCode());	
			custom.setChannelId(channel.getChannelId());
		}
		*/
		
		if(custom.getMinLimit()==null || custom.getMinLimit().length()<1){
			custom.setMinLimit("0.00");
		}
		if(custom.getOriginalCreditLimit()==null || custom.getOriginalCreditLimit().length()<1){
			custom.setOriginalCreditLimit("0.00");
		}
		if(customLog.getReceivable()==null || customLog.getReceivable().length()<1){
			customLog.setReceivable("0.00");
		}	
		//保存
		customManager.saveCustom(custom);
		
		//根据receiveable计算income的值，并保存在custom表
		String customIdCumulative="";
		String isEdit="";
		String balance="";
		customIdCumulative=(String) this.getSession().getAttribute("customId");
		isEdit=(String) this.getSession().getAttribute("isEdit");
		balance=(String) this.getSession().getAttribute("balance");
		if( 
				(isEdit!=null) && (isEdit.length()>1) && ("true".equals(isEdit)) 
				&& (customIdCumulative!=null) && (customIdCumulative.length()>0)
		){
			custom=customManager.searchCustomById(customIdCumulative);
			
			String income=custom.getIncome();
			String receivable=customLog.getReceivable();
			
			if(income==(null)){
				income="0.00";
			}
			if(receivable==(null)){
				receivable="0.00";
			}
			 
			BigDecimal incomeB=new BigDecimal(income);
			BigDecimal receivableB=new BigDecimal(receivable);
			custom.setBalance(balance);
			custom.setIncome(incomeB.add(receivableB).toString());
			//保存累加的Income字段
			customManager.updateCustomCumulative(custom);
			
			this.getSession().setAttribute("customId", null);
			this.getSession().setAttribute("isEdit", null);
			
		}
		
		//保存customLog:receivable Balance不在custom表保存，仅在customLog表保存
		customLog.setCustomLogId(CommonUtil.generatePrimaryKey());
		customLog.setAccessCode(custom.getAccessCode());
		customLog.setAddress(custom.getAddress());
		customLog.setBusiness(custom.getBusiness());
		customLog.setCorpIATANo(custom.getCorpIATANo());
		customLog.setCreatedBy(custom.getCreatedBy());
		customLog.setCreatedTime(custom.getCreatedTime());
		customLog.setCustomId(customIdCumulative);
		customLog.setEmail(custom.getEmail());
		customLog.setFax(custom.getFax());
		customLog.setLastModifyTime(custom.getLastModifyTime());
		customLog.setMobile(custom.getMobile());
		customLog.setName(custom.getName());
		customLog.setType(custom.getType());
		customLog.setUpdatedBy(custom.getUpdatedBy());
		
		HotelVO hotelVO=hotelManager.getHotelVoByHotelId(custom.getHotelId());
		hotelVO.getHotelCode();
		if(hotelVO!=null){
			customLog.setHotelCode(hotelVO.getHotelCode());
		}
		
		customLog.setIncome(custom.getIncome());
		customLog.setMinLimit(custom.getMinLimit());
		customLog.setOriginalCreditLimit(custom.getOriginalCreditLimit());
		customLog.setTotalRoomRev(custom.getTotalRoomRev());
		customLog.setBalance(custom.getBalance());
	
		customLogManager.saveCustomLog(customLog);	
		
		return list();
	}
	private void setInitCondition() {
		//获取所有渠道用户
		channelList = channelManager.getAll();
	}

	/**
	 * 删除
	 * 
	 * @throws IOException
	 */
	public String delete() {
		customManager.updateDelFlag(customId);
		return list();
	}

	public CustomManager getCustomManager() {
		return customManager;
	}

	public void setCustomManager(CustomManager customManager) {
		this.customManager = customManager;
	}

	public CustomLog getCustomLog() {
		return customLog;
	}

	public void setCustomLog(CustomLog customLog) {
		this.customLog = customLog;
	}

	public CustomCreteria getCreteria() {
		return creteria;
	}

	public void setCreteria(CustomCreteria creteria) {
		this.creteria = creteria;
	}

	public CustomResult getResult() {
		return result;
	}

	public void setResult(CustomResult result) {
		this.result = result;
	}

	public Custom getCustom() {
		return custom;
	}

	public void setCustom(Custom custom) {
		this.custom = custom;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public CustomLogManager getCustomLogManager() {
		return customLogManager;
	}

	public void setCustomLogManager(CustomLogManager customLogManager) {
		this.customLogManager = customLogManager;
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
	
	public List<HotelGuaranteeVO> getHotelGuaranteeVOList() {
		return hotelGuaranteeVOList;
	}

	public void setHotelGuaranteeVOList(List<HotelGuaranteeVO> hotelGuaranteeVOList) {
		this.hotelGuaranteeVOList = hotelGuaranteeVOList;
	}

	public List<HotelCancelVO> getHotelCancelVOList() {
		return hotelCancelVOList;
	}

	public void setHotelCancelVOList(List<HotelCancelVO> hotelCancelVOList) {
		this.hotelCancelVOList = hotelCancelVOList;
	}
}
