package com.ccm.api.service.taobaoAPI.impl;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.model.constant.TaobaoApi;
import com.ccm.api.model.taobaoVO.TbHotelVO;
import com.ccm.api.service.taobaoAPI.TaobaoHotelManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.HTMLSpirit;
import com.taobao.api.FileItem;
import com.taobao.api.domain.Hotel;
import com.taobao.api.domain.HotelImage;
import com.taobao.api.request.HotelAddRequest;
import com.taobao.api.request.HotelImageUploadRequest;
import com.taobao.api.request.HotelUpdateRequest;
import com.taobao.api.request.HotelsSearchRequest;
import com.taobao.api.response.HotelAddResponse;
import com.taobao.api.response.HotelImageUploadResponse;
import com.taobao.api.response.HotelUpdateResponse;
import com.taobao.api.response.HotelsSearchResponse;

@Service("taobaoHotelManager")
public class TaobaoHotelManagerImpl implements TaobaoHotelManager {

	private Log log = LogFactory.getLog(TaobaoHotelManagerImpl.class);

	@Override
	public HotelsSearchResponse hotelsSearch(Long province,Long city,Long district, String hotelName, long pageNo, String sessionKey) {
		HotelsSearchRequest req = new HotelsSearchRequest();
		req.setName(hotelName);
		req.setDomestic(true);
		req.setProvince(province);
		req.setCity(city);
		req.setDistrict(district);
		req.setPageNo(pageNo);

		HotelsSearchResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if(null!=response.getErrorCode()){
				throw new BizException("TB00001",response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":")+1));
			}
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e,new String[]{"ccm"}));
			throw new BizException("TB00001","淘宝api接口调用失败!");
		}
		return response;
	}

	@Override
	public Hotel hotelAdd(TbHotelVO vo, String sessionKey) {
		FileItem fItem = new FileItem(new File(vo.getPic()));
		HotelAddRequest req = new HotelAddRequest();
		req.setName(vo.getName());
		req.setDomestic(vo.getDomestic());
		req.setCountry(vo.getCountry());
		req.setProvince(vo.getProvince());
		req.setCity(vo.getCity());
		req.setDistrict(vo.getDistrict());
		req.setAddress(vo.getAddress());
		req.setLevel(vo.getLevel());
		req.setOrientation(vo.getOrientation());
		req.setTel(vo.getTel());
		req.setOpeningTime(vo.getOpeningTime());
		req.setDecorateTime(vo.getDecorateTime());
		req.setStoreys(vo.getStoreys());
		req.setRooms(vo.getRooms());
		req.setDesc(HTMLSpirit.delHTMLTag(vo.getDesc()));
		req.setService(vo.getService());
		req.setSiteParam(vo.getSiteParam());
		req.setPic(fItem);
		HotelAddResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if(null!=response.getErrorCode()){
				String error = response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":")+1);
				throw new BizException("TB00001",vo.getName()+"的发布失败，错误消息为："+error);
			}
			return response.getHotel();
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e,new String[]{"ccm"}));
			throw new BizException("TB00001","淘宝api接口调用失败!");
		}
	}

	@Override
	public Hotel hotelUpdate(TbHotelVO vo, String sessionKey) {
		FileItem fItem = new FileItem(new File(vo.getPic()));
		HotelUpdateRequest req = new HotelUpdateRequest();
		req.setHid(vo.getHid());
		// req.setName(vo.getName());
		req.setDomestic(vo.getDomestic());
		req.setCountry(vo.getCountry());
		req.setProvince(vo.getProvince());
		req.setCity(vo.getCity());
		req.setDistrict(vo.getDistrict());
		req.setAddress(vo.getAddress());
		req.setLevel(vo.getLevel());
		req.setOrientation(vo.getOrientation());
		req.setTel(vo.getTel());
		req.setOpeningTime(vo.getOpeningTime());
		req.setDecorateTime(vo.getDecorateTime());
		req.setStoreys(vo.getStoreys());
		req.setRooms(vo.getRooms());
		req.setDesc(HTMLSpirit.delHTMLTag(vo.getDesc()));
		req.setService(vo.getService());
		req.setPic(fItem);
		HotelUpdateResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if(null!=response.getErrorCode()){
				String error = response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":")+1);
				throw new BizException("TB00001",vo.getName()+"的发布失败，错误消息为："+error);
			}
			return response.getHotel();
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e,new String[]{"ccm"}));
			throw new BizException("TB00001","淘宝api接口调用失败!");
		}
	}

	@Override
	public HotelImage hotelImageUpload(long HotelId, String pic, String sessionKey) {
		FileItem fItem = new FileItem(new File(pic));
		HotelImageUploadRequest req = new HotelImageUploadRequest();
		req.setHid(HotelId);
		req.setPic(fItem);
		HotelImageUploadResponse response = null;
		try {
			response = TaobaoApi.taobaoClient.execute(req, sessionKey);
			if(null!=response.getErrorCode()){
				throw new BizException("TB00001",response.getSubMsg().substring(response.getSubMsg().lastIndexOf(":")+1));
			}
			return response.getHotelImage();
		} catch (Exception e) {
			log.error(CommonUtil.getExceptionMsg(e,new String[]{"ccm"}));
			throw new BizException("TB00001","淘宝api接口调用失败!");
		}
	}

}
