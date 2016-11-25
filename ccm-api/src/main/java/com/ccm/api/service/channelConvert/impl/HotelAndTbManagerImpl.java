/**
 * 
 */
package com.ccm.api.service.channelConvert.impl;

import java.io.File;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.enume.ChannelCodeEnum;
import com.ccm.api.model.enume.PictureType;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.taobaoVO.TbHotelVO;
import com.ccm.api.service.channelConvert.HotelAndTbManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.common.PictureManager;
import com.ccm.api.service.taobaoAPI.TaobaoHotelManager;
import com.ccm.api.service.taobaoAPI.TaobaoRoomTypeManager;
import com.ccm.api.util.DateUtil;
import com.taobao.api.domain.Hotel;
import com.taobao.api.domain.HotelImage;

/**
 * @author Jenny
 * 
 */
@Service("hotelAndTbManager")
public class HotelAndTbManagerImpl implements HotelAndTbManager {

	@Autowired
	private TaobaoRoomTypeManager taobaoRoomTypeManager;

	@Autowired
	private TaobaoHotelManager taobaoHotelManager;

	@Autowired
	private DictCodeManager dictCodeManager;

	@Autowired
	private PictureManager pictureManager;

	/**
	 * 淘宝酒店对象转换为系统酒店业务对象
	 */
	public HotelVO tbHotel2HotelVO(Long hid, String sessionKey) throws ParseException {
		Hotel h = taobaoRoomTypeManager.getHotel(hid, sessionKey, false);
		if (h != null) {
			// 淘宝酒店对象转换为系统对象
			return hotelConvertTBToHotelVO(h);
		}
		return null;
	}

	/**
	 * 从淘宝上查询酒店信息，如果审核通过的则返回酒店信息，否则还回审核状态
	 */
	public HotelVO getSysHotelToTB(Long hid, String sessionKey, HotelVO hvo) throws ParseException {
		try {
			Hotel h = taobaoRoomTypeManager.getHotel(hid, sessionKey, false);
			if (h != null) {
				// 淘宝酒店对象转换为系统对象
				return hotelConvertTBToHotelVO(h);
			}
		} catch (Exception e) {
			if (e.getMessage().lastIndexOf("酒店不存在") > 0) {
				hvo.setFlagSysHotel(true);
				return hvo;
			}
		}
		return null;
	}

	/**
	 * 系统中酒店信息添加或更新后发布到淘宝上
	 */
	public Hotel releaseHotel(HotelVO hVo, String sessionKey) throws Exception {

		Hotel result = null;

		// 获取默认图片
		Picture p = pictureManager.queryDefaultPicture(hVo.getBizId(), PictureType.HOTEL.getName());
		if (p != null) {
			String faPtha = pictureManager.getUploadPath();
			String fileName = faPtha + p.getUrl();
			File f = new File(fileName);
			if (!f.exists()) {
				throw new BizException("", hVo.getHotelName() + "的服务器上不存在默认图片!");
			} else {
				hVo.setPicUrl(fileName);
			}

		} else {
			throw new BizException("", hVo.getHotelName() + "的酒店图片不能为空!");
		}

		// 修改渠道的酒店信息，调用渠道的更新酒店接口
		if (StringUtils.hasText(hVo.getHotelId())) {
			TbHotelVO tbReq = hotelConvertToTB(hVo);
			result = taobaoHotelManager.hotelUpdate(tbReq, sessionKey);
		}
		// 渠道没有些酒店,调用渠道的新增酒店接口
		else {
			TbHotelVO tbReq = hotelConvertToTB(hVo);
			result = taobaoHotelManager.hotelAdd(tbReq, sessionKey);
		}

		return result;

	}

	/**
	 * 系统中上传图片后发布到淘宝上
	 */
	public void uploadPicToChannel(String bizId, String channelHotelCode, String sessionKey) {
		List<Picture> pList = pictureManager.getUploadChannelPicture(bizId, PictureType.HOTEL.getName(), channelHotelCode);
		String faPtha = pictureManager.getUploadPath();
		if (pList != null && !pList.isEmpty()) {
			for (Picture p : pList) {
				HotelImage hi = taobaoHotelManager.hotelImageUpload(Long.valueOf(channelHotelCode), faPtha + p.getUrl(), sessionKey);
				if (hi != null) {
					// 更新图片库为已发布到渠道标志
					p.setChannelCode(hi.getHid().toString());
					pictureManager.save(p);

				}
			}
		}
	}

	/**
	 * 酒店系统对象转换为淘宝对象
	 * 
	 * @param hv
	 * @param service
	 * @return
	 */
	private TbHotelVO hotelConvertToTB(HotelVO hv) {
		TbHotelVO tbReq = new TbHotelVO();
		tbReq.setName(hv.getHotelName());
		tbReq.setDomestic(true);
		tbReq.setCountry(hv.getCountryCode());
		tbReq.setProvince(hv.getProvinceSix());
		tbReq.setCity(hv.getCitySix());
		tbReq.setDistrict(hv.getAreaCode());
		tbReq.setAddress(hv.getAddress());
		tbReq.setLevel(hv.getLevel());
		tbReq.setOrientation(hv.getOrientation());
		tbReq.setTel(hv.getTelephone());
		if (hv.getOpeningTime() != null) {
			tbReq.setOpeningTime(DateUtil.convertDateToString(hv.getOpeningTime()).substring(0, 4));
		}
		if (hv.getDecorateTime() != null) {
			tbReq.setDecorateTime(DateUtil.convertDateToString(hv.getOpeningTime()).substring(0, 4));
		}
		if (hv.getStoreys() != null) {
			tbReq.setStoreys(hv.getStoreys());
		}
		if (hv.getRooms() != null) {
			tbReq.setRooms(hv.getRooms());
		}
		tbReq.setDesc(hv.getDescription());

		// 服务
		// 查询服务设施的当前渠道与系统的映射关系
		JSONObject j = new JSONObject();
		Map<String, String> hotelService = dictCodeManager.searchChannelCodeNoByChannel(DictName.HOTEL_AMENITY, ChannelCodeEnum.TAOBAO.getName(), true);
		Iterator<String> service = hv.getService().keySet().iterator();
		while (service.hasNext()) {
			String key = service.next().toString();
			if (hotelService.get(key) != null) {
				j.put(key, true);
			}
		}

		if (hv.getCityCenterDistance() != null) {
			j.put("cityCenterDistance", hv.getCityCenterDistance());
		}
		if (hv.getRailwayDistance() != null) {
			j.put("railwayDistance", hv.getRailwayDistance());
		}
		if (hv.getAirportDistance() != null) {
			j.put("airportDistance", hv.getAirportDistance());
		}

		tbReq.setService(j.toString());

		// 图片
		tbReq.setPic(hv.getPicUrl());

		tbReq.setSiteParam(hv.getHotelId());

		return tbReq;
	}

	/**
	 * 淘宝酒店对象转换为系统对象
	 * 
	 * @param tb
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("rawtypes")
	private HotelVO hotelConvertTBToHotelVO(Hotel tb) throws ParseException {

		HotelVO hotel = new HotelVO();

		// 交通距离与设施服务
		if (StringUtils.hasText(tb.getService())) {
			JSONObject jo = JSONObject.parseObject(tb.getService());
			Set nameSet = jo.keySet();
			Map<String, String> service = new HashMap<String, String>();
			// 查询服务设施的当前渠道与系统的映射关系
			Map<String, String> hotelService = dictCodeManager.searchChannelCodeNoByChannel(DictName.HOTEL_AMENITY, ChannelCodeEnum.TAOBAO.getName(), true);
			for (Object object : nameSet) {
				Object v = jo.get(object);
				if (v instanceof Boolean) {
					Boolean value = (Boolean) jo.get(object);
					if (value && hotelService.get(object) != null) {
						service.put(hotelService.get(object), object.toString());
					}
				}
			}
			hotel = (HotelVO) JSONObject.toJavaObject(jo, HotelVO.class);
			// value:渠道的字段名称（codeListMapping中的codeName）
			// key:系统中真实的值(dictCode中的codeNo)
			hotel.setService(service);
		}

		hotel.setHotelName(tb.getName());
		hotel.setCountryCode(tb.getCountry());
		if (tb.getDistrict() != null) {
			hotel.setAreaCode(tb.getDistrict().longValue());
		}
		hotel.setAddress(tb.getAddress());

		hotel.setProvinceStr(tb.getProvinceStr());
		hotel.setCityStr(tb.getCityStr());
		hotel.setAreaStr(tb.getDistrictStr());

		// 酒店级别
		hotel.setLevel(tb.getLevel());

		hotel.setOrientation(tb.getOrientation());
		hotel.setTelephone(tb.getTel());
		if (StringUtils.hasText(tb.getOpeningTime())) {
			hotel.setOpeningTime(DateUtil.convertStringToDate("yyyy", tb.getOpeningTime()));
		}
		if (StringUtils.hasText(tb.getDecorateTime())) {
			hotel.setDecorateTime(DateUtil.convertStringToDate("yyyy", tb.getDecorateTime()));
		}
		if (tb.getStoreys() != null) {
			hotel.setStoreys(tb.getStoreys().intValue());
		}
		if (tb.getRooms() != null) {
			hotel.setRooms(tb.getRooms().intValue());
		}
		hotel.setDescription(tb.getDesc());

		// 酒店图片url
		hotel.setPicUrl(tb.getPicUrl());

		return hotel;

	}

}
