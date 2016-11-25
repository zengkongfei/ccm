package com.ccm.api.service.taobaoAPI.impl;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ccm.api.SecurityHolder;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.model.constant.TaobaoApi;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.taobaoAPI.TaobaoRoomTypeManager;
import com.taobao.api.FileItem;
import com.taobao.api.domain.Hotel;
import com.taobao.api.domain.Room;
import com.taobao.api.domain.RoomImage;
import com.taobao.api.domain.RoomType;
import com.taobao.api.request.HotelGetRequest;
import com.taobao.api.request.HotelRoomGetRequest;
import com.taobao.api.request.HotelRoomImgDeleteRequest;
import com.taobao.api.request.HotelRoomImgUploadRequest;
import com.taobao.api.request.HotelRoomsSearchRequest;
import com.taobao.api.request.HotelTypeAddRequest;
import com.taobao.api.request.HotelTypeNameGetRequest;
import com.taobao.api.response.HotelGetResponse;
import com.taobao.api.response.HotelRoomGetResponse;
import com.taobao.api.response.HotelRoomImgDeleteResponse;
import com.taobao.api.response.HotelRoomImgUploadResponse;
import com.taobao.api.response.HotelRoomsSearchResponse;
import com.taobao.api.response.HotelTypeAddResponse;
import com.taobao.api.response.HotelTypeNameGetResponse;

@Service("taobaoRoomTypeManager")
public class TaobaoRoomTypeManagerImpl implements TaobaoRoomTypeManager {

	private Log log = LogFactory.getLog(TaobaoRoomTypeManagerImpl.class);

	@Override
	public RoomType getRoomType(long hotelId, String roomTypeName, String sessionKey) {
		HotelTypeNameGetRequest req = new HotelTypeNameGetRequest();
		req.setHid(hotelId);
		req.setName(roomTypeName);
		HotelTypeNameGetResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null != response.getErrorCode()) {
				if (null == response.getSubMsg()) {
					throw new BizException("TB00001", "淘宝api接口调用失败!");
				}
				throw new BizException("TB00001", response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1));
			}
			return response.getRoomType();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
	}

	@Override
	public HotelRoomsSearchResponse roomsSearch(String hotelId, String roomTypeId, long pageNo, String sessionKey) {
		HotelRoomsSearchRequest req = new HotelRoomsSearchRequest();
		req.setHids(hotelId);
		req.setRids(roomTypeId);
		req.setPageNo(pageNo);
		req.setNeedHotel(true);
		req.setNeedRoomType(true);
		req.setNeedRoomQuotas(true);
		req.setNeedRoomDesc(true);

		HotelRoomsSearchResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null != response.getErrorCode()) {
				throw new BizException("TB00001", response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1));
			}
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
		return response;
	}

	@Override
	public RoomType roomTypeAdd(long hotelId, String roomTypeName, String sessionKey) {
		HotelTypeAddRequest req = new HotelTypeAddRequest();
		req.setHid(hotelId);
		req.setName(roomTypeName);
		HotelTypeAddResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null != response.getErrorCode()) {
				throw new BizException("TB00001", response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1));
			}
			return response.getRoomType();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
	}

	@Override
	public Hotel getHotel(long hotelId, String sessionKey, boolean checkAudit) {
		HotelGetRequest req = new HotelGetRequest();
		req.setHid(hotelId);
		req.setNeedRoomType(false);
		req.setCheckAudit(checkAudit);
		HotelGetResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null != response.getErrorCode()) {
				throw new BizException("TB00001", response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1));
			}
			return response.getHotel();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
	}

	@Override
	public RoomImage roomImgUpload(long roomId, long position, String pic, String sessionKey) {
		FileItem fItem = new FileItem(new File(pic));
		HotelRoomImgUploadRequest req = new HotelRoomImgUploadRequest();
		req.setGid(roomId);
		req.setPosition(position);
		req.setPic(fItem);
		HotelRoomImgUploadResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null == response.getRoomImage()) {
				if (null == response.getSubMsg()) {
					throw new BizException("TB00001", "发布失败，后台没有要返回的消息（请检查图片是否过大）！");
				}
				throw new BizException("TB00001", response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1));
			}
			return response.getRoomImage();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
	}

	@Override
	public Room getRoom(long roomId, String sessionKey) {
		HotelRoomGetRequest req = new HotelRoomGetRequest();
		req.setGid(roomId);
		req.setNeedHotel(false);
		req.setNeedRoomType(true);
		req.setNeedRoomQuotas(true);
		req.setNeedRoomDesc(true);
		HotelRoomGetResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null != response.getErrorCode()) {
				throw new BizException("TB00001", response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1));
			}
			return response.getRoom();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
	}

	@Override
	public Hotel getHotelAndRoomTypes(long hotelId, String sessionKey) {
		HotelGetRequest req = new HotelGetRequest();
		req.setHid(hotelId);
		req.setNeedRoomType(true);
		req.setCheckAudit(false);
		HotelGetResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null != response.getErrorCode()) {
				throw new BizException("TB00001", response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1));
			}
			return response.getHotel();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
	}

	@Override
	public RoomImage RoomImgDelete(long gid, long position, String sessionKey) {
		HotelRoomImgDeleteRequest req = new HotelRoomImgDeleteRequest();
		req.setGid(gid);
		req.setPosition(position);
		try {
			HotelRoomImgDeleteResponse response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if (null == response.getRoomImage()) {
				throw new BizException("TB00001", response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1));
			}
			return response.getRoomImage();
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
	}

	@Override
	public HotelRoomsSearchResponse roomsSearch(Long pageNo) {
		HotelRoomsSearchRequest req = new HotelRoomsSearchRequest();
		req.setPageNo(pageNo);
		req.setNeedHotel(true);
		req.setNeedRoomType(true);
		req.setNeedRoomQuotas(true);
		req.setNeedRoomDesc(true);
		req.setItemIds("-1");
		HotelRoomsSearchResponse response = null;
		try {
			B2BUser b2bUser = SecurityHolder.getB2BUser();
			response = TaobaoApi.taobaoClient.execute(req, b2bUser.getSessionKey());

			if (null != response.getErrorCode()) {
				throw new BizException("TB00001", response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":") + 1));
			}
		} catch (Exception e) {
			log.error(e);
			throw new BizException("TB00001", "淘宝api接口调用失败!");
		}
		return response;
	}

}
