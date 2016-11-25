package com.ccm.api.service.taobaoAPI;

import com.ccm.api.model.taobaoVO.TbHotelVO;
import com.taobao.api.domain.Hotel;
import com.taobao.api.domain.HotelImage;
import com.taobao.api.response.HotelsSearchResponse;

/**
 * 酒店定义
 * 
 * @author carr
 * 
 */
public interface TaobaoHotelManager {
	/**
	 * taobao.hotels.search 多酒店查询接口
	 * province:省级编码
	 * city:市级编码
	 * district:区域编码
	 * pageNo:分页页码。取值范围，大于零的整数，默认值1，即返回第一页的数据。页面大小为20
	 */
	public HotelsSearchResponse hotelsSearch(Long province,Long city,Long district, String hotelName, long pageNo, String sessionKey);

	/**
	 * taobao.hotel.add 酒店发布接口
	 */
	public Hotel hotelAdd(TbHotelVO vo, String sessionKey);

	/**
	 * taobao.hotel.update 酒店更新接口
	 */
	public Hotel hotelUpdate(TbHotelVO vo, String sessionKey);

	/**
	 * taobao.hotel.image.upload 酒店图片上传 pic:图片路径
	 */
	public HotelImage hotelImageUpload(long HotelId, String pic, String sessionKey);

}
