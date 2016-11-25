/**
 * 
 */
package com.ccm.mc.webapp.action.channel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.Constants;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.common.ConstantDao;
import com.ccm.api.dao.common.PessimisticLockDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelGoods;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.common.Constant;
import com.ccm.api.model.common.PessimisticLock;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.Employee;
import com.ccm.api.model.user.vo.UserRoleVO;
import com.ccm.api.service.channel.ChannelGoodsExportManager;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.system.Push2ChannelManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.PropertiesUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.ExportUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Jenny
 * 
 */
public class ChannelGoodsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2805588778880057840L;

	private Log log = LogFactory.getLog(ChannelGoodsAction.class);
	// 查询条件
	private ChannelGoodsVO cgv = new ChannelGoodsVO();

	private String channelGoodsId;
	private String rateplanid;
	private Boolean isActiveImmediately;
	private String onOff;

	private ChannelGoods channelgoods;

	private List<ChannelGoodsVO> cgList;
	private List<Channel> chList = new ArrayList<Channel>();
	private List<String> roomTypeIds = new ArrayList<String>();
	private List<String> channelIds = new ArrayList<String>();

	@Resource
	private PessimisticLockDao pessimisticLockDao;
	@Resource
	private ConstantDao constantDao;

	@Autowired
	private ChannelgoodsManager channelgoodsManager;
	@Autowired
	private ChannelManager channelManager;
	@Autowired
	private RatePlanManager ratePlanManager;
	@Autowired
	private ChannelGoodsExportManager channelgoodsExportManager;
	@Resource
	private HotelManager hotelManager;
	@Resource
	private Push2ChannelManager push2ChannelManager;

	private Boolean showJointwisdom=false;//是否包含众荟渠道
	private String hotelCode="";//hotelCode
	private String pmsAccount="";//pmsAccount
	private static final String JointwisdomURL = PropertiesUtil.getProperty("JointwisdomURL");
	/**
	 * 渠道绑定列表
	 * 
	 * @return
	 */
	public String list() {
		B2BUser user = getCurLoginUser();
		String language = getLanguage();
		cgList = channelgoodsManager.searchChannelGoods(user.getHotelvo().getHotelId(), language);

		queryCGList(cgList, chList);
		
		return "list";
	}

	/**
	 * 渠道绑定查询
	 * 
	 * @return
	 */
	public String search() {

		String language = getLanguage();
		B2BUser user = getCurLoginUser();
		ChannelGoodsVO cgvo = new ChannelGoodsVO();
		cgvo.setHotelId(user.getHotelvo().getHotelId());
		cgvo.setLanguage(language);

		List<String> channelIdList = cgv.getChannelIds();
		// 如果渠道ID不为空
		if (null != channelIdList && channelIdList.size() > 0) {
			cgvo.setChannelIds(channelIdList);
		}

		cgList = channelgoodsManager.searchChannelgoodsByChannelHotel(cgvo);

		// 得到酒店所有渠道
		List<ChannelGoodsVO> cgListN = channelgoodsManager.searchChannelGoods(user.getHotelvo().getHotelId(), language);

		queryCGList(cgListN, chList);

		return "list";
	}

	/**
	 * 渠道绑定编辑
	 * 
	 * @return
	 */
	public String edit() {

		String language = getLanguage();

		B2BUser user = getCurLoginUser();
		String hotelId = user.getHotelvo().getHotelId();

		if (StringUtils.hasText(channelGoodsId)) {
			channelgoods = channelgoodsManager.get(channelGoodsId);

			if (channelgoods != null && !ChannelGoodsStatus.OFF.equals(channelgoods.getStatus())) {
				saveMessage(getText("ccm.error.009"));
				getRequest().setAttribute("flag", "no");
				return "edit";
			}

			List<ChannelGoods> cgList = channelgoodsManager.getChannelGoodsByHotelIdChannelIdRatePlanId(hotelId, channelgoods.getChannelId(), channelgoods.getRateplanid(),channelgoods.getIsBind(),channelgoods.getEffectiveDate());
			if (cgList != null && !cgList.isEmpty()) {
				for (ChannelGoods cg : cgList) {
					roomTypeIds.add(cg.getRoomTypeId());
				}
			}

			getRoomTypeList(channelgoods.getRateplanid(), language);
		}

		// 查询所有渠道
		List<Channel> cList = channelManager.getAll();
		cList = channelManager.getEnabledChannel(cList);
		getRequest().setAttribute("cList", cList);

		// 查询所有房价定义
		List<HashMap<String, String>> ratePan = ratePlanManager.getValidRatePlanByHotelIdLang(hotelId, language);
		getRequest().setAttribute("ratePan", ratePan);

		return "edit";
	}

	/**
	 * 根据房价查询房型
	 * 
	 * @return
	 */
	public String channelRoomType() {
		if (channelgoods != null && StringUtils.hasText(channelgoods.getRateplanid())) {
			String language = getLanguage();
			log.info(channelgoods.getRateplanid());
			getRoomTypeList(channelgoods.getRateplanid(), language);
		}
		return "roomType";
	}

	/**
	 * 渠道绑定保存
	 * 
	 * @return
	 * @throws IOException
	 */
	public String save() {
		String language = getLanguage();
		if (!getEnableOper()) {
			ajaxResponse(getText("ccm.error.008"));
			return null;
		}

		// 修改时判断
		if (channelgoods != null && StringUtils.hasText(channelgoods.getChannelGoodsId()) && !ChannelGoodsStatus.OFF.equals(channelgoods.getStatus())) {
			ajaxResponse(getText("ccm.error.009"));
			return null;
		}

		log.info(channelgoods.getChannelGoodsId() + channelgoods.getEffectiveDate() + channelgoods.getExpireDate());

		if (!StringUtils.hasText(rateplanid)) {
			ajaxResponse(getText("ccm.RestrictionsManagement.error.rateCodeNull"));
			return null;
		}

		if (roomTypeIds == null || roomTypeIds.isEmpty()) {
			ajaxResponse(getText("ccm.InventoryManagement.SelectRoomType"));
			return null;
		}

		B2BUser user = getCurLoginUser();
		if (!StringUtils.hasText(user.getUserId())) {
			ajaxResponse(getText("ccm.Channel.message.UserFailure"));
			return null;
		}

		String hotelId = user.getHotelvo().getHotelId();
		channelgoods.setHotelId(hotelId);
		channelgoods.setRateplanid(rateplanid);
		if (isActiveImmediately) {
			channelgoods.setEffectiveDate(new Date());
			channelgoods.setExpireDate(null);
			channelgoods.setStatus(ChannelGoodsStatus.DEFAULT);
		} else {
			if (channelgoods.getEffectiveDate() == null || channelgoods.getExpireDate() == null) {
				ajaxResponse(getText("ccm.Channel.message.CustomTime"));
				return null;
			}
		}
		channelgoods.setIsActiveImmediately(isActiveImmediately);

		PessimisticLock pl = new PessimisticLock();
		pl.setBizId(user.getUserId());
		pl.setBizType("saveOrUpdateChannelGoods");
		pl.setCreatedTime(new Date());
		try {
			pessimisticLockDao.savePessimisticLock(pl);
		} catch (Exception e) {
			log.warn(e);
			ajaxResponse(getText("ccm.error.006"));
			return null;
		}
		try {
			channelgoodsManager.saveOrUpdateChannelGoods(channelgoods, roomTypeIds, channelIds);
			getSession().setAttribute("channelGoods", true);
			push2ChannelManager.pushHotelRoomContent(language, hotelId, channelIds, roomTypeIds);
			ajaxResponse("success");
		} catch (BizException e) {
			ajaxResponse(e.getErrKey());
		} catch (Exception e) {
			ajaxResponse(getText("ccm.error.007"));
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		} finally {
			pessimisticLockDao.deletePessimisticLock(pl);
		}
		return null;
	}

	/**
	 * 渠道绑定删除
	 * 
	 * @return
	 */
	public String delete() {
		try {
			if (!getEnableOper()) {
				saveMessage(getText("ccm.error.008"));
				return "save";
			}
			log.info(channelGoodsId);
			B2BUser user = getCurLoginUser();
			String hotelId = user.getHotelvo().getHotelId();
			log.info(hotelId);
			ChannelGoods cgs = channelgoodsManager.get(channelGoodsId);
			if (cgs != null && !ChannelGoodsStatus.OFF.equals(cgs.getStatus())) {
				saveMessage(getText("ccm.error.009"));
				return "save";
			}
			channelgoodsManager.deleteChannelGoods(channelGoodsId, hotelId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "save";
	}

	/**
	 * 渠道绑定开通，关闭
	 * 
	 * @return
	 */
	public String onOff() {
		if (!getEnableOper()) {
			saveMessage(getText("ccm.error.008"));
			return "save";
		}

		B2BUser user = getCurLoginUser();
		String hotelId = user.getHotelvo().getHotelId();
		if (channelgoods == null) {
			channelgoods = new ChannelGoods();
		}
		channelgoods.setChannelGoodsId(channelGoodsId);
		channelgoods.setUpdatedBy(user.getUsername());
		channelgoods.setHotelId(hotelId);

		// 关闭
		if (ChannelGoodsStatus.OFF.equals(onOff)) {
			channelgoods.setStatus(ChannelGoodsStatus.OFF);
			channelgoodsManager.changeChannelGoodsStatus(channelgoods, getLanguage());
		}
		// 开通
		else if (ChannelGoodsStatus.DEFAULT.equals(onOff)) {
			channelgoods.setStatus(ChannelGoodsStatus.DEFAULT);
			channelgoodsManager.changeChannelGoodsStatus(channelgoods, getLanguage());
			getSession().setAttribute("channelGoods", true);
		}
		return "save";
	}

	/**
	 * 渠道绑定发布
	 * 
	 * @return
	 */
	public String publish() {
		if (!getEnableOper()) {
			writeMessage(false, getText("ccm.error.008"));
			return null;
		}

		B2BUser user = getCurLoginUser();
		String hotelId = user.getHotelvo().getHotelId();

		// 防并发，使用数据库表锁
		PessimisticLock pl = new PessimisticLock();
		pl.setBizId(hotelId);
		pl.setBizType("publishChannelGoods");
		pl.setCreatedTime(new Date());
		try {
			pessimisticLockDao.savePessimisticLock(pl);
		} catch (Exception e) {
			log.warn(e);
			writeMessage(false, getText("ccm.error.006"));
			return null;
		}
		// 发布绑定关系先更新相应状态为发布进行中，再更新房价日历
		String errMsg = null;
		boolean rFlag = false;
		try {
			channelgoodsManager.publishChannelGoods(hotelId, getLanguage());
			getSession().setAttribute("channelGoods", false);
			rFlag = true;
		} catch (BizException e) {
			errMsg = e.getErrKey();
		} catch (Exception e) {
			errMsg = getText("ccm.Channel.Distributed.Failure");
		} finally {
			pessimisticLockDao.deletePessimisticLock(pl);
		}
		writeMessage(rFlag, errMsg);
		return null;
	}

	/**
	 * 批量发布酒店的渠道绑定关系,针对系统管理员开放此权限
	 */
	public void publishRateCalendar() {

		log.info("startPushlishRateCalendar");
		int i = 0;

		while (true) {

			Constant constant = new Constant();
			constant.setName("hotelCode");
			constant.setFlag(1);
			constant.setDelFlag(null);
			List<Constant> ccList = constantDao.getConstantByObj(constant);

			// 超过30分钟置为99表示出错
			for (Constant c : ccList) {
				if (DateUtil.addMinutes(c.getLastModifyTime(), 30).before(new Date())) {
					c.setFlag(99);
					constantDao.save(c);
				}
			}

			int len = ccList.size();
			log.info("flag1" + len + "i=" + i++);
			if (len > 8) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					log.info(e);
				}
				ajaxResponse("BatchDealChannelGoodsSleep(10000)Index:" + i + "\n");
			} else {
				List<Constant> cdList = constantDao.getConstantByHotelCode();
				if (cdList.size() > 0) {
					dealHotel(cdList.get(0));
				} else {
					ajaxResponse("BatchDealChannelGoodsFinish.");
					break;
				}
				ajaxResponse("BatchDealChannelGoodsFinishIndex:" + i + "\n");
			}
		}

	}

	/**
	 * 批量发布酒店用户及权限,针对系统管理员开放此权限
	 */
	public void createUser() {
		Constant constant = new Constant();
		constant.setName("hotelCode");
		constant.setDelFlag(false);
		List<Constant> cList = constantDao.getConstantByObj(constant);
		for (Constant c : cList) {
			String srcValue = c.getValue();
			List<Hotel> hList = hotelManager.getHotelByHotelCode(c.getId());
			c.setValue("QueryHotelByHotelCode");
			for (int i = 0; i < hList.size(); i++) {
				StringBuffer exceptionMsg = new StringBuffer();
				Hotel h = hList.get(i);
				String hotelId = h.getHotelId();
				try {
					// 保存用户
					B2BUser user = new B2BUser();
					user.setUsername(h.getHotelCode());
					user.setPassword("123456");
					user.setCompanyId(CompanyType.HOTEL);
					Employee em = new Employee();
					em.setCompanyId(user.getCompanyId());
					em.setName(user.getUsername());
					user.setEmployee(em);
					userManager.createUserInfo(user);
					// 保存用户酒店权限
					UserRoleVO userRoleVO = new UserRoleVO();
					userRoleVO.setUserId(user.getUserId());
					userRoleVO.setRoleId(Constants.ROLE_COADMIN_HOTEL);
					userRoleVO.setHotelId(hotelId);
					List<UserRoleVO> userRoleList = new ArrayList<UserRoleVO>();
					userRoleList.add(userRoleVO);
					userManager.setRoleOfUserOfMc(user.getUserId(), userRoleList);
					c.setDelFlag(true);
					c.setValue(get200len("ProcessSuccess:", null, i, srcValue));
				} catch (Exception e) {
					exceptionMsg.append("ProcessUserException:").append(i);
					c.setValue(get200len("ProcessUserException:", null, i, srcValue));
				}
			}
			c.setUpdatedBy(OXIConstant.creatorCode);
			constantDao.save(c);
		}
	}

	private void dealHotel(Constant c) {

		String srcValue = c.getValue();
		List<Hotel> hList = hotelManager.getHotelByHotelCode(c.getId());
		log.info(c.getId() + c.getFlag() + c.getLastModifyTime());
		c.setValue("QueryHotelByHotelCode" + hList.size());
		c.setFlag(5);
		int len = hList.size();
		for (int i = 0; i < len; i++) {
			Hotel h = hList.get(i);
			String hotelId = h.getHotelId();
			// 发布绑定关系先更新相应状态为发布进行中，再更新房价日历
			// 防并发，使用数据库表锁
			PessimisticLock pl = new PessimisticLock();
			pl.setBizId(hotelId);
			pl.setBizType("publishChannelGoods");
			pl.setCreatedTime(new Date());
			try {
				pessimisticLockDao.savePessimisticLock(pl);
				boolean result = channelgoodsManager.publishChannelGoodsBatch(hotelId);
				if (result) {
					c.setValue(get200len("ProcessPublishSuccess:", null, i, srcValue));
					c.setFlag(1);
				} else {
					c.setValue(get200len("ProcessPublishFail:", null, i, srcValue));
					c.setFlag(4);
					pessimisticLockDao.deletePessimisticLock(pl);
				}
			} catch (Exception e) {
				pessimisticLockDao.deletePessimisticLock(pl);
				c.setValue(get200len("ProcessPublishException:", e, i, srcValue));
				c.setFlag(3);
				log.warn(e);
			}
		}
		c.setUpdatedBy(OXIConstant.creatorCode);
		constantDao.save(c);

	}

	private String get200len(String str, Exception e, int i, String srcStr) {
		StringBuffer msg = new StringBuffer();
		if (i > 0) {
			msg.append(srcStr).append(str);
		} else {
			msg.append(str);
		}
		if (e != null) {
			msg.append(CommonUtil.getExceptionMsg(e, "CCM"));
		}
		msg.append(i);
		String msgN = msg.toString();
		if (msgN.length() > 200) {
			return msgN.substring(0, 200);
		} else {
			return msgN;
		}
	}

	/**
	 * 若存在基础房价则通基础房价ID查询房型，否则通过房价ID查询
	 * 
	 * @param ratePlanId
	 */
	private void getRoomTypeList(String ratePlanId, String language) {
		log.info(ratePlanId);
		if (StringUtils.hasText(ratePlanId)) {
			Rateplan r = ratePlanManager.get(ratePlanId);
			if (r != null && StringUtils.hasText(r.getInheritRatePlanId())) {
				ratePlanId = r.getInheritRatePlanId();
			}
			log.info(ratePlanId);
			List<RoomTypeVO> rtList = channelgoodsManager.getRoomTypeByRatePlanId(ratePlanId, language);
			getRequest().setAttribute("rtList", rtList);
		}
	}

	private void queryCGList(List<ChannelGoodsVO> cgList, List<Channel> chList) {

		// 加载酒店绑定的渠道列表
		if (cgList != null && cgList.size() > 0) {
			for (ChannelGoodsVO cg : cgList) {
				//判断是否包含众荟渠道 status=3 同步状态
				if((!showJointwisdom)&&(ChannelGoodsStatus.synch.equals( cg.getStatus()))){
					Channel c=channelManager.get(cg.getChannelId());
					if(null!=c && c.getIsJointwisdom()){
						showJointwisdom=true;
						getRequest().setAttribute("JointwisdomURL", JointwisdomURL);
						/*设置链接参数
						  hotelGroupCode=厂商代码&hotelCode=酒店代码&hotelAccount=PMS使用者账号
						 */
						B2BUser user = getCurLoginUser();
						HotelVO hvo=null;
						if(user!=null){
							hvo=user.getHotelvo();
						}
						if(hvo!=null){
							hotelCode=hvo.getHotelCode();
							Hotel h=hotelManager.getHotel(hvo.getHotelId());
							if(null!=h){
								pmsAccount=h.getPmsAccount();
							}
						}
					}
				}
				
				// 去重复
				boolean flag = false;
				for (Channel c : chList) {
					if (c.getChannelId().equals(cg.getChannelId())) {
						flag = true;
						break;
					}
				}
				if (flag)
					continue;

				Channel c = new Channel();
				c.setChannelId(cg.getChannelId());
				c.setChannelCode(cg.getChannelCode());
				chList.add(c);
			}
		}
	}

	private boolean getEnableOper() {
		Object obj = getSession().getAttribute("enableOper");
		if (obj instanceof Boolean) {
			return (Boolean) obj;
		}
		return true;
	}

	private String getLanguage() {
		Locale locale = ActionContext.getContext().getLocale();
		return locale.getLanguage() + "_" + locale.getCountry();
	}

	/**
	 * 数据导出
	 * 
	 * @return
	 * @throws IOException
	 */
	public String export() throws IOException {
		B2BUser user = getCurLoginUser();
		ChannelGoodsVO cgvo = new ChannelGoodsVO();
		cgvo.setHotelId(user.getHotelvo().getHotelId());
		String language = getLanguage();
		cgvo.setLanguage(language);

		List<String> channelIdList = cgv.getChannelIds();
		// 如果渠道ID不为空
		if (null != channelIdList && channelIdList.size() > 0) {
			cgvo.setChannelIds(channelIdList);
		}

		try {
			List<String[]> dataList = channelgoodsExportManager.getChannelGoodsData(cgvo);

			// 生成excel文件名
			String excelName = ExportUtil.createFileName("channelgoods");
			// excel表头
			String[] colName = { "Hotel Code", "Hotel EName", "Hotel CName", "RoomType Code", "RoomType Description", "Rate Code", "RateCode Description", "Channel Code", "Channel RoomType", "Channel RoomType Description", "InActiveDate" };
			// 得到excel工作薄对象
			HSSFWorkbook workbook = ExportUtil.createExcel("channelgoodsList", colName, dataList);

			// 设置导出的excel文件从页面中下载
			getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			getResponse().addHeader("Content-Disposition", "attachment;filename=\"" + excelName + ".xls" + "\"");
			// getResponse().setContentType("application/vnd.ms-excel;charset=gb2312");

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

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getPmsAccount() {
		return pmsAccount;
	}

	public void setPmsAccount(String pmsAccount) {
		this.pmsAccount = pmsAccount;
	}

	public Boolean getShowJointwisdom() {
		return showJointwisdom;
	}

	public void setShowJointwisdom(Boolean showJointwisdom) {
		this.showJointwisdom = showJointwisdom;
	}

	public String getChannelGoodsId() {
		return channelGoodsId;
	}

	public void setChannelGoodsId(String channelGoodsId) {
		this.channelGoodsId = channelGoodsId;
	}

	public ChannelGoods getChannelgoods() {
		return channelgoods;
	}

	public void setChannelgoods(ChannelGoods channelgoods) {
		this.channelgoods = channelgoods;
	}

	public List<ChannelGoodsVO> getCgList() {
		return cgList;
	}

	public void setCgList(List<ChannelGoodsVO> cgList) {
		this.cgList = cgList;
	}

	public String getRateplanid() {
		return rateplanid;
	}

	public void setRateplanid(String rateplanid) {
		this.rateplanid = rateplanid;
	}

	public Boolean getIsActiveImmediately() {
		return isActiveImmediately;
	}

	public void setIsActiveImmediately(Boolean isActiveImmediately) {
		this.isActiveImmediately = isActiveImmediately;
	}

	public List<String> getRoomTypeIds() {
		return roomTypeIds;
	}

	public void setRoomTypeIds(List<String> roomTypeIds) {
		this.roomTypeIds = roomTypeIds;
	}

	public List<String> getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(List<String> channelIds) {
		this.channelIds = channelIds;
	}

	public String getOnOff() {
		return onOff;
	}

	public void setOnOff(String onOff) {
		this.onOff = onOff;
	}

	public List<Channel> getChList() {
		return chList;
	}

	public void setChList(List<Channel> chList) {
		this.chList = chList;
	}

	public ChannelGoodsVO getCgv() {
		return cgv;
	}

	public void setCgv(ChannelGoodsVO cgv) {
		this.cgv = cgv;
	}
	
}
