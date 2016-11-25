/**
 * 
 */
package com.ccm.mc.webapp.action.channel;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelHotelConfig;
import com.ccm.api.model.channel.vo.ChannelCreteria;
import com.ccm.api.model.channel.vo.ChannelResult;
import com.ccm.api.model.email.vo.EmailVO;
import com.ccm.api.model.hotel.vo.HotelGuaranteeVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.channel.ChannelHotelConfigManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.hotel.HotelGuaranteeManager;
import com.ccm.api.service.system.PushManage;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.mc.webapp.action.base.BaseAction;

/**
 * @author Jenny
 * 
 */
public class ChannelAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2805588778880057840L;

	private Log log = LogFactory.getLog(ChannelAction.class);

	@Autowired
	private ChannelManager channelManager;
	
	@Resource
	private PushManage pushManage;
	@Resource
	private HotelGuaranteeManager hotelGuaranteeManager;

	private ChannelCreteria creteria;
	private ChannelResult result;
	private Channel channel = new Channel();
	private ChannelHotelConfig channelHotelConfig=new ChannelHotelConfig();
	
	private String startDate;

	private String endDate;
	private String channelCode;
	@Resource
	private EmailManager emailManager;
	
	@Autowired
	private ChannelHotelConfigManager channelHotelConfigManager;

	/**
	 * 来源列表显示
	 */
	public String list() {
		if (null == creteria) {
			creteria = new ChannelCreteria();
		}
		int pageSize = this.getCurrentPageSize("channelList");
		int pageNo = this.getCurrentPageNo("channelList");
		creteria.setPageNum(pageNo);
		creteria.setPageSize(pageSize);
		result = channelManager.searchChannel(creteria);
		return "list";
	}

	/**
	 * 创建/编辑
	 */
	public String toEdit() {
		if (StringUtils.hasText(channel.getChannelId())) {
			channel = channelManager.get(channel.getChannelId());
		}else{
			channel.setOrdernNumberOfTimes(99999999);
		}
		String hotelId = getCurLoginUser().getHotelvo().getHotelId();
		List<HotelGuaranteeVO> hgvoList = hotelGuaranteeManager.getHotelGuaranteeCodeByHotelId(hotelId);
		getRequest().setAttribute("hgvoList", hgvoList);
		return "toEdit";
	}

	/**
	 * 保存/修改
	 */
	public String save() {
		Channel c = channelManager.getChannelByChannelCode(channel.getChannelCode());
		if (c != null) {
			// 更新
			if (StringUtils.hasText(channel.getChannelId()) && !c.getChannelId().equals(channel.getChannelId())) {
				saveMessage(getText("ccm.error.030"));
				return "toEdit";
			}
			// 添加
			else if (!StringUtils.hasText(channel.getChannelId())) {
				saveMessage(getText("ccm.error.030"));
				return "toEdit";
			}

		}
		B2BUser user = getCurLoginUser();
		channel.setCreatedBy(user.getUsername());
		channel.setUpdatedBy(user.getUsername());
		channelManager.save(channel);
		return "save";
	}

	/**
	 * 删除
	 * 
	 * @throws IOException
	 */
	public String delete() {
		if (StringUtils.hasText(channel.getChannelId())) {
			channel.setDelFlag(true);
			channel.setLastModifyTime(new Date());
			channel.setUpdatedBy(getCurLoginUser().getUsername());
			channelManager.deleteChannel(channel);
		}
		return "save";
	}

	/**
	 * 渠道酒店配置
	 */
	public String channelHotelConfig() {
		if (StringUtils.hasText(channel.getChannelId())) {
			channel = channelManager.get(channel.getChannelId());
			channelHotelConfig = channelHotelConfigManager.getChannelHotelConfigByChannelId(channel.getChannelId());
			//如果初次显示此渠道的配置，则显示设置的默认值
			if(channelHotelConfig==null){
				channelHotelConfig=new ChannelHotelConfig();
				channelHotelConfig.setChannelId(channel.getChannelId());//渠道id
				channelHotelConfig.setPushMethod(1);//推送方法：单独推送=1  全量推送=2    页面单选  默认1 
				channelHotelConfig.setAriSwitch(false);//即时推送ARI开关
				/*
				 酒店配置和房型配置默认全部为空
				 */
				/*	
				//酒店配置,默认为true,不勾选的则不在字段中存
				channelHotelConfig.setChannelHotelConfig(
				"'address':'true','chainCode':'true','city':'true','cityName':'true','countryCode':'true','email':'true','fax':'true','hotelCode':'true','hotelName':'true','languageCode':'true','postCode':'true','tbShopName':'true','telephone':'true'"
				);
				//房型配置,默认为true,不勾选的则不在字段中存
				channelHotelConfig.setChannelRoomTypeConfig(
				"'bedTypeName':'true','description':'true','maxOccupancy':'true','roomTypeCode':'true','roomTypeName':'true'"		
				);
				//房价配置
				channelHotelConfig.setChannelRateConfig(channelRateConfig);
				*/
				channelHotelConfigManager.addChannelHotelConfig(channelHotelConfig);
				getRequest().setAttribute("channelHotelConfig", channelHotelConfig);
			}else{
				channel.setOrdernNumberOfTimes(99999999);
			}
			
			return "channelHotelConfig";
		}
			
		return "list";
	
	}
	/**
	 * 渠道酒店配置 保存
	 * 
	 */
	public String saveConfig() {
		if (channelHotelConfig!=null && StringUtils.hasText(channelHotelConfig.getChannelId()) 
		&& channel!=null && StringUtils.hasText(channel.getChannelId())
		&& StringUtils.hasText(channel.getChannelCode())) {		
			Channel c = channelManager.getChannelByChannelCode(channel.getChannelCode());
			if (c != null) {
				if (StringUtils.hasText(c.getChannelId()) 
						&& !c.getChannelId().equals(channelHotelConfig.getChannelId())
						&& !channel.getChannelId().equals(channelHotelConfig.getChannelId())) {
					saveMessage(getText("ccm.error.030"));
					return "toEdit";
				}
			}
			B2BUser user = getCurLoginUser();
			c.setUpdatedBy(user.getUsername());
			c.setIsJointwisdom(channel.getIsJointwisdom());
			channelManager.save(c);
			channelHotelConfigManager.updateChannelHotelConfig(channelHotelConfig);
		}
		return "saveConfig";
	}
	
	@SuppressWarnings("deprecation")
	public void dailySend() throws Exception {
		pushManage.dailySend();
		ajaxResponse("success " + new Date().toLocaleString());
	}
	
	@SuppressWarnings("deprecation")
	public void xeSeWqZData() throws Exception {
		log.info("sendByDate...");
		try {
			if(CommonUtil.isNotEmpty(startDate) && CommonUtil.isNotEmpty(endDate)){
				log.info(startDate+" : "+endDate+" :"+channelCode);				
				pushManage.sendByDate(DateUtil.convertStringToDate(startDate), DateUtil.convertStringToDate(endDate),channelCode);
				sendMail(startDate, endDate, "success action");
				ajaxResponse("sendByDate success " + new Date().toLocaleString());
			}else{
				log.info("参数错误 ");
				ajaxResponse("sendByDate fail 参数错误 " + new Date().toLocaleString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendMail(startDate, endDate, "fail："+ CommonUtil.getExceptionMsg(e, "ccm"));
			ajaxResponse("fail " + CommonUtil.getExceptionMsg(e, "ccm"));
		}
		log.info("end sendByDate...");
	}
	
	public void sendMail(String sDate,String eDate,String content){
		String[] toArray = new String[]{"davin.deng@shijinet.com.cn","nora.huang@shijinet.com.cn"};
		EmailVO emailVO = new EmailVO();
		emailVO.setToArray(toArray);
		emailVO.setContent(content);
		emailVO.setSubject( "sendByDate startDate:"+ sDate +" eDate:"+eDate);
		emailManager.sendText(emailVO);
	}
	
	public ChannelHotelConfig getChannelHotelConfig() {
		return channelHotelConfig;
	}

	public void setChannelHotelConfig(ChannelHotelConfig channelHotelConfig) {
		this.channelHotelConfig = channelHotelConfig;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public ChannelManager getChannelManager() {
		return channelManager;
	}

	public void setChannelManager(ChannelManager channelManager) {
		this.channelManager = channelManager;
	}

	public ChannelCreteria getCreteria() {
		return creteria;
	}

	public void setCreteria(ChannelCreteria creteria) {
		this.creteria = creteria;
	}

	public ChannelResult getResult() {
		return result;
	}

	public void setResult(ChannelResult result) {
		this.result = result;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

}
