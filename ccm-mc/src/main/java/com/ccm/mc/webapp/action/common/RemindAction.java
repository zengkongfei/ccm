/**
 * 
 */
package com.ccm.mc.webapp.action.common;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import com.ccm.api.model.common.Menu;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.RemindStatus;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.MenuManager;
import com.ccm.api.service.common.RemindManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Jenny
 * 
 */
public class RemindAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8427683771797724354L;

	@Resource
	private RemindManager remindManager;
	@Resource 
	private MenuManager menuManager;

	@SuppressWarnings("unchecked")
	public String search() {
		boolean flag = false;
		StringBuffer msg = new StringBuffer();
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		boolean newSound = false;
		boolean modifySound = false;
		boolean cancelSound = false;
		boolean feedbSound = false;
		
		
		try {
			B2BUser user = getCurLoginUser();
			if (user == null) {
				msg.append("{success:").append(false).append("}");
				ajaxResponse(msg.toString());
				return null;
			}
			HotelVO hvo = user.getHotelvo();
			if (hvo == null) {
				msg.append("{success:").append(false).append("}");
				ajaxResponse(msg.toString());
				return null;
			}
			if (CompanyType.PLAT_COMPANY_ID.equals(user.getCompanyId())) {
				msg.append("{success:").append(false).append("}");
				ajaxResponse(msg.toString());
				return null;
			}
			
			String hotelId = hvo.getHotelId();
			if (hvo.getOrderRemind() != null && hvo.getOrderRemind()) {
				
				//如果没有订单管理权限，订单提醒功能作废
				Locale locale = ActionContext.getContext().getLocale();
				String language = locale.getLanguage() + "_" + locale.getCountry();
				String url = "order_list.do";
				List<Menu> menuList = menuManager.getAuthedTwoMenusByUrl(user.getUserId(), url,language);
				if(menuList==null||menuList.isEmpty()){
					return null;
				}
				
				// 新增
				List<String> mListNew = remindManager.getMasterByOsta(RemindStatus.order_new, hotelId);
				if (!mListNew.isEmpty()) {
					if (getSession().getAttribute(hotelId + "new") != null) {
						List<String> mListSrcNew = (List<String>) getSession().getAttribute(hotelId + "new");
						for (String masterId : mListNew) {
							if (!mListSrcNew.contains(masterId)) {
								newSound = true;
								break;
							}
						}
					} else {
						newSound = true;
					}
					getSession().setAttribute(hotelId + "new", mListNew);
					count1 = mListNew.size();
				}

				// 修改
				List<String> mListModify = remindManager.getMasterByOsta(RemindStatus.order_modify, hotelId);
				if (!mListModify.isEmpty()) {
					if (getSession().getAttribute(hotelId + "modify") != null) {
						List<String> mListSrcNew = (List<String>) getSession().getAttribute(hotelId + "modify");
						for (String masterId : mListModify) {
							if (!mListSrcNew.contains(masterId)) {
								modifySound = true;
								break;
							}
						}
					} else {
						modifySound = true;
					}
					getSession().setAttribute(hotelId + "modify", mListModify);
					count2 = mListModify.size();
				}
				// 取消
				List<String> mListCancel = remindManager.getMasterByOsta(RemindStatus.order_cancel, hotelId);
				if (!mListCancel.isEmpty()) {
					if (getSession().getAttribute(hotelId + "cancel") != null) {
						List<String> mListSrcNew = (List<String>) getSession().getAttribute(hotelId + "cancel");
						for (String masterId : mListCancel) {
							if (!mListSrcNew.contains(masterId)) {
								cancelSound = true;
								break;
							}
						}
					} else {
						cancelSound = true;
					}
					getSession().setAttribute(hotelId + "cancel", mListCancel);
					count3 = mListCancel.size();
				}
			}
			if (hvo.getMessRemind() != null && hvo.getMessRemind()) {
				// 留言
				List<String> feedbList = remindManager.getFeedBackByStatus(RemindStatus.feedback, hotelId);
				if (!feedbList.isEmpty()) {
					if (getSession().getAttribute(hotelId + "feedb") != null) {
						List<String> mListSrcNew = (List<String>) getSession().getAttribute(hotelId + "feedb");
						for (String masterId : feedbList) {
							if (!mListSrcNew.contains(masterId)) {
								feedbSound = true;
								break;
							}
						}
					} else {
						feedbSound = true;
					}
					getSession().setAttribute(hotelId + "feedb", feedbList);
					count4 = feedbList.size();
				}
			}
			flag = true;
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		msg.append("{success:").append(flag).append(",").append("count1:").append(count1).append(",count2:").append(count2).append(",count3:").append(count3).append(",count4:").append(count4).append(",newSound:").append(newSound).append(",modifySound:").append(modifySound).append(",cancelSound:").append(cancelSound).append(",feedbSound:").append(feedbSound).append("}");
		ajaxResponse(msg.toString());
		return null;
	}

	public String haveSeen() {
		boolean flag = false;
		try {
			B2BUser user = getCurLoginUser();
			String hotelId = user.getHotelvo().getHotelId();
			remindManager.havedSeenRemind(hotelId);
			flag = true;
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		writeMessage(flag, "");
		return null;
	}

}
