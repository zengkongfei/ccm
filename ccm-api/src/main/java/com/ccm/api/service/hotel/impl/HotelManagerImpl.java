/**
 * 
 */
package com.ccm.api.service.hotel.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.ChannelHotelDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelHotel;
import com.ccm.api.model.common.vo.InvokeResponse;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.constant.UserInitStatus;
import com.ccm.api.model.enume.ChannelCodeEnum;
import com.ccm.api.model.enume.PictureType;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.log.ReceiveReqResLog;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelHotelManager;
import com.ccm.api.service.channelConvert.HotelAndTbManager;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.hotel.ChainManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.hotel.Hoteli18nManager;
import com.ccm.api.service.log.ReceiveReqResManager;
import com.ccm.api.service.system.BookChannelManage;
import com.ccm.api.service.taobaoAPI2.TaobaoApiManager;
import com.ccm.api.service.user.UserManager;
import com.ccm.api.util.CommonUtil;
import com.taobao.api.domain.XHotel;
import com.taobao.api.request.XhotelAddRequest;
import com.taobao.api.request.XhotelUpdateRequest;

/**
 * @author Jenny
 * 
 */
@Service("hotelManager")
public class HotelManagerImpl extends GenericManagerImpl<Hotel, String> implements HotelManager {
	@Autowired
	private HotelDao hotelDao;

	@Autowired
	private Hoteli18nManager hoteli18nManager;

	@Autowired
	private ChannelHotelManager channelHotelManager;

	@Autowired
	private HotelAndTbManager hotelAndTbManager;

	@Resource
	private BookChannelManage bookChannelManage;

	@Autowired
	private PictureManager pictureManager;

	@Resource
	private UserManager userManager;

	@Autowired
	private ChainManager chainManager;

	@Resource
	private TaobaoApiManager taobaoApiManager;
	@Resource
	private ChannelHotelDao channelHotelDao;
	@Resource
	private ChannelDao channelDao;
	@Resource
	private ReceiveReqResManager receiveReqResManager;

	private Map<String, Hashtable<String, String>> hidHotelIdMap = new HashMap<String, Hashtable<String, String>>();

	@Autowired
	public HotelManagerImpl(HotelDao hotelDao) {
		super(hotelDao);
		this.hotelDao = hotelDao;
	}

	/**
	 * 新建或更新酒店
	 */
	public HotelVO saveOrUpdateHotelInfo(HotelVO hvo) throws Exception {

		String hotelId = hvo.getHotelId();

		// 用于酒店图片关联的业务ID,更新时不修改此值
		if (!StringUtils.hasText(hvo.getBizId())) {
			hvo.setBizId(CommonUtil.generatePrimaryKey());
		}

		Chain c = chainManager.getChainByCode(hvo.getChainCode());
		if (c != null) {
			hvo.setChainId(c.getChainId());
		} else {
			ChainVO cvo = new ChainVO();
			cvo.setChainCode(hvo.getChainCode());
			cvo.setChainName(hvo.getChainCode());
			cvo = chainManager.saveChain(cvo);
			hvo.setChainId(cvo.getChainId());
		}

		// 保存hotel
		Hotel h = new Hotel();
		BeanUtils.copyProperties(hvo, h);
		h = hotelDao.save(h);

		// 保存多语言酒店信息
		hvo.setHotelId(h.getHotelId());
		hoteli18nManager.saveOrUpdateHotelI18n(hvo);

		// 当前用户第一次新增酒店
		if (!StringUtils.hasText(hotelId)) {
			userManager.updateStatus(UserInitStatus.HotelCreated);
		}

		return hvo;
	}

	/************* 以上是整理过的 *****************/

	/**
	 * 从渠道淘宝上初始化酒店信息到本系统中
	 * 
	 * @param tbHotel
	 * @return
	 */
	public Map<String, String> saveHotelFromTBByHid(com.taobao.api.domain.Hotel tbHotel) {

		B2BUser user = SecurityHolder.getB2BUser();

		Hashtable<String, String> result = new Hashtable<String, String>();

		// 从淘宝获取酒店信息
		if (tbHotel.getHid() != null && user != null) {

			if (hidHotelIdMap.get(user.getUserId()) != null) {
				result = hidHotelIdMap.get(user.getUserId());
			}

			if (result != null && result.get(tbHotel.getHid()) != null) {
				return null;
			}

			String hotelId = channelHotelManager.getHotelIdByChannelCodeChannelHotelCode(ChannelCodeEnum.TAOBAO.getName(), tbHotel.getHid().toString());

			// 系统中已存在渠道的酒店则查看或更新
			if (!StringUtils.hasText(hotelId)) {

				// 淘宝酒店对象转换为系统对象
				HotelVO hotel = null;
				try {
					hotel = hotelAndTbManager.tbHotel2HotelVO(tbHotel.getHid(), user.getSessionKey());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (hotel == null) {
					return null;
				}

				try {
					hotel = saveOrUpdateHotelInfo(hotel);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (hotel == null) {
					return null;
				}

				try {
					pictureManager.saveChannelPic(hotel.getPicUrl(), hotel.getBizId(), PictureType.HOTEL.getName(), null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				result.put(tbHotel.getHid().toString(), hotel.getHotelId());
				hidHotelIdMap.put(user.getUserId(), result);
			}
		}
		return result;
	}

	/**
	 * 查询酒店信息(hotelId,chainId,hotelCode,orderRemind,messRemind,cityName,
	 * telephone
	 * ,email,currencyCode,tbShopName,hotelName,languageCode,address,chainCode)
	 */
	public HotelVO getHotelI18nChainByHotelId(String hotelId) {
		return hotelDao.getHotelI18nChainByHotelId(hotelId);
	}

	/**
	 * 查询酒店信息(hotelId,chainId,hotelCode,orderRemind,messRemind,cityName,
	 * telephone
	 * ,email,currencyCode,tbShopName,hotelName,languageCode,address,chainCode)
	 */
	public HotelVO getHotelI18nChainByHotelId(String hotelId, String languageCode) {
		return hotelDao.getHotelI18nChainByHotelId(hotelId, languageCode);
	}

	/**
	 * 根据酒店ID获取酒店已存在的服务设施
	 */
	public Map<String, String> getHotelAmenity(String hotelId) {
		return hotelDao.getHotelAmenity(hotelId);
	}

	/**
	 * 根据用户ID获取酒店
	 */
	public List<HotelVO> getHotelInfoChainByUserId(String userId) {
		return hotelDao.getHotelInfoChainByUserId(userId);
	}
	/**
	 * 根据用户ID获取酒店
	 */
	public List<HotelVO> getHotelInfoChainByUserId(String userId,String language) {
		return hotelDao.getHotelInfoChainByUserId(userId,language);
	}

	@Override
	public void updateHotelStatus(String hotelId, String status) {
		hotelDao.updateHotelStatus(hotelId, status);
	}

	/**
	 * 设置酒店为启动或停用
	 * 
	 * @param hotelId
	 * @param delFlag
	 */
	public void updateHotelDelFlag(String hotelId, String delFlag) {
		hotelDao.updateHotelDelFlag(hotelId, delFlag);
	}

	@Override
	public boolean cleanCCMData(String userId) {
		String result = "";
		List<String> hotelIdList = userManager.getHotelIdListByUserId(userId);
		if (hotelIdList != null && !hotelIdList.isEmpty()) {
			for (String hotelId : hotelIdList) {
				if (StringUtils.hasText(hotelId)) {
					result += hotelDao.cleanCCMData(hotelId);
				}
			}
		}
		log.info(result);
		return !(result.indexOf("false") > -1);
	}

	public BookChannelManage getBookChannelManage() {
		return bookChannelManage;
	}

	public void setBookChannelManage(BookChannelManage bookChannelManage) {
		this.bookChannelManage = bookChannelManage;
	}

	/**
	 * 根据酒店ID查询酒店与集团的ID和代码及币种
	 */
	public HotelVO getHotelChainByHotelId(String hotelId) {
		return hotelDao.getHotelChainByHotelId(hotelId);
	}

	private boolean updateTBStatus(String hotelId, XHotel h) {
		ChannelHotel ch = new ChannelHotel();
		Channel c = channelDao.getChannelByChannelCode(ChannelCodeEnum.TAOBAO.getName());
		ch.setChannelId(c.getChannelId());
		ch.setHotelId(hotelId);
		List<ChannelHotel> chList = channelHotelDao.getChannelHotelByHotelIdChannelId(ch);// 从系统中匹配该酒店的淘宝状态
		if (CommonUtil.isNotEmpty(chList) && chList.size() > 0) {
			ChannelHotel ch2 = chList.get(0);
			ch2.setChannelStatus(h.getStatus().intValue());
			ch2.setMatchStatus(h.getMatchStatus());
			channelHotelDao.save(ch2); // 更新匹配状态
			log.info("已更新hotelId：" + hotelId + " 淘宝匹配状态：" + h.getMatchStatus());
			return true;
		}
		log.error("未找到hotelId：" + hotelId + " 淘宝匹配状态：" + h.getMatchStatus());
		return false;
	}

	@Async
	public Map<String, String> publishHotelToTB(HotelVO hotel, String sessionKey) {
		Map<String, String> resMap = new HashMap<String, String>();
		try {
			if (CommonUtil.isEmpty(hotel.getChannelHotelCode())) {
				XhotelAddRequest req = new XhotelAddRequest();
				req.setOuterId(CommonUtil.generateSequenceNo());
				req.setName(hotel.getHotelName());
				req.setUsedName(hotel.getHotelUsedName());
				req.setDomestic(hotel.getDomestic() ? 1l : 0l);
				req.setCountry(hotel.getCountryCode());
				req.setProvince(hotel.getProvinceSix());
				req.setCity(hotel.getCitySix());

				if (CommonUtil.isNotEmpty(hotel.getAreaCode())) {
					req.setDistrict(hotel.getAreaCode());
				}

				req.setBusiness(hotel.getBusiness());
				req.setAddress(hotel.getAddress());
				req.setLongitude(hotel.getLongitude());
				req.setLatitude(hotel.getLatitude());
				req.setPositionType(hotel.getPosition_type());
				req.setTel(hotel.getTelephone());
				// sessionKey
				// ="6102107ad7fb0891744f22b9a4e50a270d14f04d1e1653f2049618273";
				InvokeResponse ivkRes = taobaoApiManager.hotelAdd(req, sessionKey);

				if (ivkRes.isSuccess()) {
					XHotel h = (XHotel) ivkRes.getResObj();
					ChannelHotel ch = new ChannelHotel();
					ch.setChannelHotelCode(h.getHid() + "");
					ch.setHotelId(hotel.getHotelId());
					ch.setChannelStatus(h.getStatus().intValue());
					ch.setMatchStatus(h.getMatchStatus());
					ch.setOuterId(req.getOuterId());
					channelHotelManager.saveOrUpdateChannelHotel(ch);
					resMap.put("isSuccess", "true");
				} else {
					resMap.put("isSuccess", "false");
					resMap.put("errMsg", ivkRes.getErrMsg());
				}

				ReceiveReqResLog receiveReqResLog = new ReceiveReqResLog();
				receiveReqResLog.setInterfaceId(ChannelCodeEnum.TAOBAO.getName());
				receiveReqResLog.setType(MessageType.TB_HOTEL);
				receiveReqResLog.setExtId(hotel.getHotelId());
				receiveReqResLog.setRequest(ivkRes.getReqData());
				receiveReqResLog.setResponse(ivkRes.getResDate());
				receiveReqResLog.setException(ivkRes.getErrMsg());
				receiveReqResManager.saveOrUpdateReceiveReqRes(receiveReqResLog);
			} else {
				XhotelUpdateRequest req = new XhotelUpdateRequest();
				req.setHid(Long.parseLong(hotel.getChannelHotelCode()));
				req.setName(hotel.getHotelName());
				req.setUsedName(hotel.getHotelUsedName());
				req.setDomestic(hotel.getDomestic() ? 1l : 0l);
				req.setCountry(hotel.getCountryCode());
				req.setProvince(hotel.getProvinceSix());
				req.setCity(hotel.getCitySix());

				if (CommonUtil.isNotEmpty(hotel.getAreaCode())) {
					req.setDistrict(hotel.getAreaCode());
				}

				req.setBusiness(hotel.getBusiness());
				req.setAddress(hotel.getAddress());
				req.setLongitude(hotel.getLongitude());
				req.setLatitude(hotel.getLatitude());
				req.setPositionType(hotel.getPosition_type());
				req.setTel(hotel.getTelephone());
				InvokeResponse ivkRes = taobaoApiManager.hotelUpdate(req, sessionKey);
				if (ivkRes.isSuccess()) {
					XHotel h = (XHotel) ivkRes.getResObj();
					if (updateTBStatus(hotel.getHotelId(), h)) {
						resMap.put("isSuccess", "true");
					}
				} else {
					resMap.put("isSuccess", "false");
					resMap.put("errMsg", ivkRes.getErrMsg());
				}
				ReceiveReqResLog receiveReqResLog = new ReceiveReqResLog();
				receiveReqResLog.setInterfaceId(ChannelCodeEnum.TAOBAO.getName());
				receiveReqResLog.setType(MessageType.TB_HOTEL);
				receiveReqResLog.setExtId(hotel.getHotelId());
				receiveReqResLog.setRequest(ivkRes.getReqData());
				receiveReqResLog.setResponse(ivkRes.getResDate());
				receiveReqResLog.setException(ivkRes.getErrMsg());
				receiveReqResManager.saveOrUpdateReceiveReqRes(receiveReqResLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("isSuccess", "false");
			resMap.put("errMsg", CommonUtil.getExceptionMsg(e, new String[] { "ccm", "taobao" }));
		}
		return null;
	}

	@Override
	public HotelVO getHotelVoByHotelId(String hotelId) {
		return hotelDao.getHotelVoByHotelId(hotelId);
	}

	@Override
	public HotelVO getHotelVoByHotelId(String hotelId, String languageCode) {
		return hotelDao.getHotelVoByHotelId(hotelId, languageCode);
	}

	@Override
	public List<HotelVO> getAllHotels() {
		return hotelDao.getAllHotels();
	}

	@Override
	public List<HotelVO> getAllHotels(String languageCode) {
		return hotelDao.getAllHotels(languageCode);
	}

	@Override
	public List<Hotel> getHotelByHotelCode(String hotelCode) {
		return hotelDao.getHotelByHotelCode(hotelCode);
	}

	public List<Hotel> getHotelByChainId(String chainId) {
		return hotelDao.getHotelByChainId(chainId);
	}
	
	public List<Hotel> getHotelByChainCode(String chainCode) {
		return hotelDao.getHotelByChainCode(chainCode);
	}

	@Override
	public HotelVO getHotelI18nByCode(String hotelCode, String chainId) {
		List<HotelVO> hList = hotelDao.getHotelI18nByCode(hotelCode, chainId);
		if (hList != null && !hList.isEmpty()) {
			return hList.get(0);
		}
		return null;
	}

	@Override
	public HotelVO getHotelI18nByCode(String hotelCode, String chainId, String languageCode) {
		List<HotelVO> hList = hotelDao.getHotelI18nByCode(hotelCode, chainId, languageCode);
		if (hList != null && !hList.isEmpty()) {
			return hList.get(0);
		}
		return null;
	}
	
	// 获取与PMS直连的所有酒店 
	@Override
	public List<HotelVO> getAllDirectPmsHotel(String languageCode,
			Boolean isDirectPms) {
		return hotelDao.getAllDirectPmsHotel(languageCode, isDirectPms);
	}

	@Override
	public Hotel getHotel(String hotelId) {
		return hotelDao.getHotel(hotelId);
	}

}
