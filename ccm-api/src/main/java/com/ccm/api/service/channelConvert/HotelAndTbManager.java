/**
 * 
 */
package com.ccm.api.service.channelConvert;

import java.text.ParseException;

import com.ccm.api.model.hotel.vo.HotelVO;
import com.taobao.api.domain.Hotel;

/**
 * @author Jenny
 * 
 */
public interface HotelAndTbManager {

	/**
	 * 淘宝酒店对象转换为系统酒店业务对象
	 * 
	 * @param hid
	 * @param sessionKey
	 * @return
	 * @throws ParseException
	 */
	public HotelVO tbHotel2HotelVO(Long hid, String sessionKey) throws ParseException;

	/**
	 * 从淘宝上查询酒店信息，如果审核通过的则返回酒店信息，否则还回审核状态
	 * 
	 * @param hid
	 * @param sessionKey
	 * @param hvo
	 *            -hvo不为空表示在系统新建酒店后发布到淘宝
	 * @return
	 * @throws ParseException
	 */
	public HotelVO getSysHotelToTB(Long hid, String sessionKey, HotelVO hvo) throws ParseException;

	/**
	 * 系统中酒店信息添加或更新后发布到渠道上
	 * 
	 * @param hVo
	 * @param sessionKey
	 */
	public Hotel releaseHotel(HotelVO hVo, String sessionKey) throws Exception;

	/**
	 * 系统中上传图片后发布到渠道上
	 * 
	 * @param bizId
	 * @param channelHotelCode
	 * @param sessionKey
	 */
	public void uploadPicToChannel(String bizId, String channelHotelCode, String sessionKey);

}
