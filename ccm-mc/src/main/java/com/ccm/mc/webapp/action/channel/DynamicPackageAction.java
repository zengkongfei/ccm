/**
 * 
 */
package com.ccm.mc.webapp.action.channel;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.channel.vo.DynamicPackageCreteria;
import com.ccm.api.model.channel.vo.DynamicPackageResult;
import com.ccm.api.model.hotel.vo.HotelPackageVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.channel.DynamicPackageManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.hotel.HotelPackageManager;
import com.ccm.mc.webapp.action.base.BaseAction;

/**
 * @author Jenny
 * 
 */
public class DynamicPackageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2805588778880057840L;

	private Log log = LogFactory.getLog(DynamicPackageAction.class);

	@Autowired
	private DynamicPackageManager dynamicPackageManager;
	@Autowired
	private ChannelManager channelManager;
	@Resource
	private HotelPackageManager hotelPackageManager;

	private DynamicPackageCreteria creteria;
	private DynamicPackageResult result;
	private DynamicPackage dp = new DynamicPackage();

	/**
	 * 来源列表显示
	 */
	public String list() {

		B2BUser user = getCurLoginUser();
		if (user == null) {
			return "index";
		}

		if (null == creteria) {
			creteria = new DynamicPackageCreteria();
		}
		creteria.setHotelId(user.getHotelvo().getHotelId());

		int pageNo = this.getCurrentPageNo("channel");
		creteria.setPageNum(pageNo);
		result = dynamicPackageManager.searchList(creteria);

		getRequest().setAttribute("channelList", channelManager.getAll());
		HotelPackageVO hpvo = new HotelPackageVO();
		hpvo.setHotelId(user.getHotelvo().getHotelId());
		getRequest().setAttribute("packageList", hotelPackageManager.getHotelPackageByObj(hpvo));
		return "list";
	}

	/**
	 * 创建/编辑
	 */
	public String edit() {
		if (StringUtils.hasText(dp.getDynamicPackageId())) {
			dp = dynamicPackageManager.get(dp.getDynamicPackageId());
		}
		getRequest().setAttribute("channelList", channelManager.getAll());
		HotelPackageVO hpvo = new HotelPackageVO();
		hpvo.setHotelId(getCurLoginUser().getHotelvo().getHotelId());
		getRequest().setAttribute("packageList", hotelPackageManager.getHotelPackageByObj(hpvo));
		return "edit";
	}

	/**
	 * 保存/修改
	 */
	public String save() {
		log.info("start save dynamicPackage");
		B2BUser user = getCurLoginUser();
		String hotelId = user.getHotelvo().getHotelId();
		DynamicPackage dPackage = dynamicPackageManager.getDynamicPackagesById(hotelId, dp.getChannelId(), dp.getPackageId());
		if (dPackage != null) {
			// 添加
			if (!StringUtils.hasText(dp.getDynamicPackageId())) {
				saveMessage("动态包价已存在");
				return edit();
			}
			// 更新
			else if (!dPackage.getDynamicPackageId().equals(dp.getDynamicPackageId())) {
				saveMessage("动态包价已存在");
				return edit();
			}

		}
		dp.setHotelId(hotelId);
		dp.setCreatedBy(user.getUserId());
		dp.setUpdatedBy(user.getUserId());
		dynamicPackageManager.save(dp);
		return "save";
	}

	/**
	 * 删除
	 * 
	 * @throws IOException
	 */
	public String delete() {
		if (StringUtils.hasText(dp.getDynamicPackageId())) {
			dynamicPackageManager.updateDelFlag(dp.getDynamicPackageId(), true);
		}
		return "save";
	}

	public DynamicPackageCreteria getCreteria() {
		return creteria;
	}

	public void setCreteria(DynamicPackageCreteria creteria) {
		this.creteria = creteria;
	}

	public DynamicPackageResult getResult() {
		return result;
	}

	public void setResult(DynamicPackageResult result) {
		this.result = result;
	}

	public DynamicPackage getDp() {
		return dp;
	}

	public void setDp(DynamicPackage dp) {
		this.dp = dp;
	}

}
