package com.ccm.api.service.taobaoAPI.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccm.api.SecurityHolder;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.roomQuota.RoomQuotaDao;
import com.ccm.api.dao.rsvtype.AdsToTBLogDao;
import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.dao.rsvtype.RsvtypeOnlineDao;
import com.ccm.api.dao.user.UserDao;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.constant.TaobaoApi;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.roomQuota.vo.RoomService;
import com.ccm.api.model.rsvtype.AdsToTBLog;
import com.ccm.api.model.rsvtype.RsvtypeOnline;
import com.ccm.api.model.taobaoVO.RoomQuotaVO;
import com.ccm.api.model.taobaoVO.RoomVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.roomQuota.RoomQuotaManager;
import com.ccm.api.service.taobaoAPI.TaobaoRoomManager;
import com.ccm.api.service.taobaoAPI.TaobaoRoomTypeManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.taobao.api.FileItem;
import com.taobao.api.domain.Room;
import com.taobao.api.domain.Shipping;
import com.taobao.api.request.HotelRoomAddRequest;
import com.taobao.api.request.HotelRoomUpdateRequest;
import com.taobao.api.request.LogisticsDummySendRequest;
import com.taobao.api.response.HotelRoomAddResponse;
import com.taobao.api.response.HotelRoomUpdateResponse;
import com.taobao.api.response.LogisticsDummySendResponse;

@Service("taobaoRoomManager")
public class TaobaoRoomManagerImpl implements TaobaoRoomManager {

	private Log log = LogFactory.getLog(TaobaoRoomManagerImpl.class);
	@Autowired
	private RoomQuotaDao roomQuotaDao;
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private RsvtypeDao rsvtypeDao;
	@Autowired
	private PictureManager pictureManager;
	@Autowired
	private RoomQuotaManager roomQuotaManager;
	@Autowired
	private TaobaoRoomTypeManager taobaoRoomTypeManager;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AdsToTBLogDao adsToTBLogDao;
	@Autowired
	private RsvtypeOnlineDao rsvtypeOnlineDao;

	@Override
	public Room roomAdd(RoomVO vo, String sessionKey) {
		// 获取发票
		B2BUser user = SecurityHolder.getB2BUser();
		String companyId = user.getCompanyId();
		String receiptType = null;
		String receiptOtherTypeDesc = null;
		if (!"1".equals(companyId)) {
			user = userDao.getUserById(companyId);
		}
		if (null != user.getHotelInvoiceType()) {
			if (user.getHotelInvoiceType()) {
				receiptType = "A";
			} else {
				receiptType = "B";
				receiptOtherTypeDesc = user.getOtherInvoiceType();
			}
		}

		Hotel hotel = hotelDao.getHotelOfHid(vo.getHid().toString());
		if (hotel.getDelFlag()) {
			return null;
		}
		FileItem fItem = new FileItem(new File(vo.getPicPath()));
		HotelRoomAddRequest req = new HotelRoomAddRequest();
		req.setHid(vo.getHid());
		req.setRid(vo.getRid());
		req.setTitle(vo.getTitle());
		req.setBedType(vo.getBedType());
		req.setBreakfast(vo.getBreakfast());
		req.setPic(fItem);
		req.setPaymentType("A");
		req.setGuide(vo.getGuide());
		req.setDesc(vo.getDesc());
		req.setRoomQuotas(vo.getRoomQuotas());
		req.setService(vo.getService());
		// 发票
		if (null != receiptType) {
			req.setReceiptType(receiptType);
			req.setHasReceipt(true);
			if (receiptType.equals("B") && null != receiptOtherTypeDesc) {
				req.setReceiptOtherTypeDesc(receiptOtherTypeDesc);
			}
		}
		// 退款规则
		if (null != vo.getRefundPolicyInfo() && !vo.getRefundPolicyInfo().equals("")) {
			req.setRefundPolicyInfo(vo.getRefundPolicyInfo());
		}
		HotelRoomAddResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null == response.getRoom()) {
				if (null == response.getSubMsg()) {
					throw new BizException("", vo.getHname() + "的" + vo.getGname() + "发布失败！错误消息是：" + response.getMsg());
				}
				String error = response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1);
				throw new BizException("", vo.getHname() + "的" + vo.getGname() + "发布失败！错误消息是：" + error);
			}
			return response.getRoom();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
	}

	@Override
	public Room roomUpdate(RoomVO vo, String sessionKey, String flag) {
		Hotel hotel = hotelDao.getHotelOfHid(vo.getHid().toString());
		if (hotel.getDelFlag()) {
			return null;
		}
		HotelRoomUpdateRequest req = new HotelRoomUpdateRequest();
		req.setGid(vo.getGid());
		if ("1".equals(flag)) {// 修改房态
			req.setRoomQuotas(vo.getRoomQuotas());
		}
		if ("2".equals(flag)) {// 修改状态
			req.setStatus(vo.getStatus());
		}
		if ("3".equals(flag)) {// 商品编辑（状态和房态外）
			FileItem fItem = null;
			if (null != vo.getPicPath()) {
				fItem = new FileItem(new File(vo.getPicPath()));
			}
			req.setTitle(vo.getTitle());
			req.setBedType(vo.getBedType());
			req.setBreakfast(vo.getBreakfast());
			req.setPic(fItem);
			req.setGuide(vo.getGuide());
			req.setDesc(vo.getDesc());
			req.setService(vo.getService());
			if (null != vo.getRefundPolicyInfo() && !vo.getRefundPolicyInfo().equals("")) {
				req.setRefundPolicyInfo(vo.getRefundPolicyInfo());
			}
		}
		HotelRoomUpdateResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null == response.getRoom()) {
				if (null == response.getSubMsg()) {
					throw new BizException("", vo.getHname() + "的" + vo.getGname() + "发布失败！错误消息是：" + response.getMsg());
				}
				String error = response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1);
				throw new BizException("", vo.getHname() + "的" + vo.getGname() + "发布失败！错误消息是：" + error);
			}
			return response.getRoom();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
	}

	@Override
	public Room roomQuotasUpdate(RoomVO vo, String sessionKey) {
		HotelRoomUpdateRequest req = new HotelRoomUpdateRequest();
		req.setGid(vo.getGid());
		if (StringUtils.hasText(vo.getPaymentType())) {
			req.setPaymentType(vo.getPaymentType());
		}
		req.setRoomQuotas(vo.getRoomQuotas());
		HotelRoomUpdateResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null == response.getRoom()) {
				if (null == response.getSubMsg()) {
					throw new BizException("发布失败！错误消息是：" + response.getMsg());
				} else {
					String error = response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1);
					throw new BizException("发布失败！错误消息是：" + error);
				}
			}
			return response.getRoom();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!" + e.getMessage());
		}
	}

	@Override
	public Room releaseRoomQuota(String hotelId, String roomTypeId, List<RoomQuotaVO> voList, String sessionKey, String flag) {
		Room room = null;
		String roomQuotas = null;
		String roomService = null;
		// 查询要发布的商品，获得roomVO
		RoomVO vo = roomQuotaDao.getRoomVO(hotelId, roomTypeId);
		if (null == vo) {
			throw new BizException("", "酒店ID为：" + hotelId + "房型ID为：" + roomTypeId + "的宝贝在本地数据库中没有记录！");
		}
		if ("0".equals(flag)) {// 发布
			// 房态
			if (null != voList) {
				roomQuotas = JSONArray.toJSONString(voList);
				vo.setRoomQuotas(roomQuotas.toString());
			}

			// 获取酒店设施
			RoomService rs = roomQuotaManager.getRoomsService(roomTypeId);
			if (null != rs) {
				roomService = JSONObject.toJSONString(rs);
				vo.setService(roomService.toString());
			}

			// 图片
			String imagePath = pictureManager.getUploadPath();
			String picPath = imagePath + vo.getPicPath();
			if (null == imagePath || null == picPath) {
				throw new BizException("", vo.getHname() + "的" + vo.getGname() + "图片路径不存在！");
			}
			vo.setPicPath(picPath);

			// 发布
			room = roomAdd(vo, sessionKey);
			if (null != room) {
				roomQuotaDao.updateChannelGoodsCode(room.getGid().toString(), roomTypeId);
				String roomTypeCode = roomQuotaDao.getRoomTypeCode(roomTypeId);
				rsvtypeDao.updateRsvtypeSta(hotelId, roomTypeCode, 1);
				releaseOtherRoomImg(room, vo, roomTypeId, sessionKey);
			}

		}
		if ("1".equals(flag)) {// 修改房态
			// 房态
			if (null != voList) {
				roomQuotas = JSONArray.toJSONString(voList);
				vo.setRoomQuotas(roomQuotas.toString());
			}

			// 发布
			room = roomUpdate(vo, sessionKey, flag);
		}
		if ("3".equals(flag)) {// 商品编辑（状态和房态外）
			// 获取酒店设施
			RoomService rs = roomQuotaManager.getRoomsService(roomTypeId);
			if (null != rs) {
				roomService = JSONObject.toJSONString(rs);
				vo.setService(roomService.toString());
			}

			// 图片
			String imagePath = pictureManager.getUploadPath();
			String picPath = imagePath + vo.getPicPath();
			if (null == imagePath || null == picPath) {
				throw new BizException("", vo.getHname() + "的" + vo.getGname() + "图片路径不存在！");
			}
			vo.setPicPath(picPath);

			// 发布
			room = roomUpdate(vo, sessionKey, flag);
			if (null != room) {
				releaseOtherRoomImg(room, vo, roomTypeId, sessionKey);
			}
		}
		return room;
	}

	@Override
	public Room releaseRoomQuota(String hotelId, String roomTypeCode, String flag) {
		B2BUser user = SecurityHolder.getB2BUser();
		String sessionKey = user.getSessionKey();
		List<RoomQuotaVO> voList = roomQuotaManager.getRoomQuotaVOList(hotelId, roomTypeCode);
		if (null == sessionKey) {
			throw new BizException("roomQuotaRelease.getSessionKey", "sessionKey为Null值，请确认该用户是否授权用户！");
		}
		String roomTypeId = roomQuotaDao.getRoomTypeID(roomTypeCode);
		Room room = null;
		if (null != voList) {
			room = releaseRoomQuota(hotelId, roomTypeId, voList, sessionKey, flag);
		}
		if (null != room) {
			// 修改发布状态
			rsvtypeDao.updateRsvtypeSta(hotelId, roomTypeCode, 1);
		}
		return room;
	}

	/**
	 * 发布完成后发布其他图片
	 * 
	 * @param room
	 * @param roomTypeId
	 * @param sessionKey
	 */
	public void releaseOtherRoomImg(Room room, RoomVO vo, String roomTypeId, String sessionKey) {
		List<Picture> picList = pictureManager.getBizPictureList("2", roomTypeId);
		if (picList.size() > 5) {
			throw new BizException("", vo.getHname() + "的" + vo.getGname() + "图片发布失败，图片最多只能上传5张，请删除后再上传！");
		}
		if (!picList.isEmpty()) {
			for (int i = 0; i < picList.size(); i++) {
				Picture picture = picList.get(i);
				try {
					taobaoRoomTypeManager.roomImgUpload(room.getGid(), i + 1, pictureManager.getUploadPath() + picture.getUrl(), sessionKey);
				} catch (BizException e) {
					throw new BizException("", vo.getHname() + "的" + vo.getGname() + "图片发布失败，请修改后提交！");
				}
			}
			room = taobaoRoomTypeManager.getRoom(room.getGid(), sessionKey);
			String[] tbPic = room.getPicUrl() != null ? room.getPicUrl().split(",") : null;
			for (int i = picList.size() + 1; i <= tbPic.length; i++) {
				taobaoRoomTypeManager.RoomImgDelete(room.getGid(), picList.size() + 1, sessionKey);
			}
		}
	}

	@Override
	public boolean dummySend(String tid, String sessionKey) {
		log.info("***TID: " + tid + " sessionKey：" + sessionKey);
		if (!StringUtils.hasText(tid)) {
			return false;
		}
		boolean isSuccess = false;

		try {
			LogisticsDummySendRequest req = new LogisticsDummySendRequest();
			req.setTid(Long.parseLong(tid));
			LogisticsDummySendResponse response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null == response.getShipping()) {
				if (null != response.getSubMsg()) {
					String error = response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1);
					log.error(error);
				} else {
					log.error("SubCode:" + response.getSubCode() + " ErrorCode:" + response.getErrorCode() + " Body:" + response.getBody() + " Msg:" + response.getMsg());
				}
				return isSuccess;
			}
			Shipping sp = response.getShipping();
			isSuccess = sp.getIsSuccess();
			if (!isSuccess) {
				log.error("发货失败,tid:" + tid + "," + response.getSubMsg());
			}
			log.info("TID: " + tid + "发货：" + isSuccess);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public void onlineToTaobao(String channelCode, String gid, Date date, String echoToken, String adsType) {
		String errMsg = "";
		String content = "";
		if (!StringUtils.hasText(gid)) {
			errMsg = "不能找到产品代码";
			saveAdsToTBLog(echoToken, adsType, content, errMsg);
			return;
		}

		RoomQuotaVO vo = null;
		List<RoomQuotaVO> voList = new ArrayList<RoomQuotaVO>();

		// 验证date
		date = checkDate(date, echoToken, adsType);
		if (null == date) {
			return;
		}
		// 查询RsvtypeOnline数据
		RsvtypeOnline rsvtypeOnline = new RsvtypeOnline();
		rsvtypeOnline.setChannelGoodsCode(gid);
		rsvtypeOnline.setDate(date);
		List<RsvtypeOnline> roList = rsvtypeOnlineDao.getRsvtypeOnlineByGid(rsvtypeOnline);
		// 查询sessionKey
		String sessionKey = userDao.getSessionKeyByChainCode(channelCode);
		if (null == sessionKey || sessionKey.equals("")) {
			errMsg = "没有获取到SessionKey";
			saveAdsToTBLog(echoToken, adsType, content, errMsg);
			return;
		}
		if (roList.isEmpty()) {
			errMsg = "从RsvtypeOnline表中没有找到数据！";
			saveAdsToTBLog(echoToken, adsType, content, errMsg);
			return;
		}
		for (RsvtypeOnline ro : roList) {
			String roDate = DateUtil.convertDateToString(ro.getDate());
			// if(null==ro.getAvailable() || null==ro.getRate()||
			// !StringUtils.hasText(ro.getChannelGoodsCode())){
			if (null == ro.getAvailable()) {
				errMsg += roDate + " 房量不能为空；";
			}
			if (null == ro.getRate()) {
				errMsg += roDate + " 房价不能为空；";
			}
			if (!StringUtils.hasText(ro.getChannelGoodsCode())) {
				errMsg += roDate + " 产品代码不能为空。";
			}

			vo = new RoomQuotaVO();
			vo.setDate(roDate);
			vo.setPrice((int) (ro.getRate() * 100));
			if ("Close".equals(ro.getActionCode())) {
				vo.setNum(0);
			} else if (vo.getPrice() == 0) {
				vo.setNum(0);
				vo.setPrice(999999 * 100);
				log.info(roDate + "房价为0 ，已将该条数据房量改为0房价为999999发送淘宝！");
			} else {
				vo.setNum(ro.getAvailable());
			}
			voList.add(vo);
			// }
		}

		content = JSONArray.toJSONString(voList);
		if (StringUtils.hasText(errMsg)) {
			saveAdsToTBLog(echoToken, adsType, content, errMsg);
			return;
		}

		AdsToTBLog log = saveAdsToTBLog(echoToken, adsType, content, errMsg);

		try {
			RoomVO roomVO = new RoomVO();
			roomVO.setGid(Long.parseLong(gid));
			roomVO.setRoomQuotas(content);
			roomVO.setPaymentType("E");// E：前台面付
			roomVO.setPriceType("A");// A：参考预订价
			roomQuotasUpdate(roomVO, sessionKey);// 发送到淘宝
			// 更新AdsToTBLog发送状态
			log.setStatus(1);
		} catch (Exception e) {
			// e.printStackTrace();
			log.setStatus(0);
			log.setErrMsg(CommonUtil.getExceptionMsg(e, new String[] { "ccm", "taobao" }));
		}
		adsToTBLogDao.updateAdsToTBLog(log);
	}

	/**
	 * 检查日期是否可用,不可用则保存日志
	 */
	private Date checkDate(Date date, String echoToken, String adsType) {
		Date nowDate = DateUtil.getCleanDate(new Date());
		Date date89 = new Date(nowDate.getTime() + 89 * 24 * 60 * 60 * 1000L);

		// 日期不在当天到89天范围中
		if (null == date || nowDate.after(date) || date89.before(date)) {
			String errMsg = "结束日期为：" + DateUtil.convertDateToString(date) + "不合规范";
			saveAdsToTBLog(echoToken, adsType, "", errMsg);
			return null;
		}
		// 日期是当天
		// if(nowDate.equals(date)){
		// String errMsg = "请发布自当天起2天或以上的90天内的价格和可售间数 ";
		// saveAdsToTBLog(echoToken,adsType,"",errMsg);
		// return null;
		// }
		return date89;
	}

	/**
	 * 保存日志
	 * 
	 * @param errMsg
	 */
	private AdsToTBLog saveAdsToTBLog(String echoToken, String adsType, String content, String errMsg) {
		AdsToTBLog log = new AdsToTBLog();
		log.setEchoToken(echoToken);
		log.setAdsType(adsType);
		log.setContent(content);
		log.setErrMsg(errMsg);
		log.setStatus(0);
		log.setCreatedTime(new Date());
		return adsToTBLogDao.save(log);
	}
}
