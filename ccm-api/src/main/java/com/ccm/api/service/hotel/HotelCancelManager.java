package com.ccm.api.service.hotel;

import java.util.List;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.hotel.HotelCancel;
import com.ccm.api.model.hotel.HotelCancelI18n;
import com.ccm.api.model.hotel.vo.HotelCancelCreteria;
import com.ccm.api.model.hotel.vo.HotelCancelResult;
import com.ccm.api.model.hotel.vo.HotelCancelVO;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.service.base.GenericManager;

public interface HotelCancelManager extends GenericManager<HotelCancel, String>{

	/**
	 * 添加HotelCancel
	 * @param vo
	 */
	HotelCancelVO saveHotelCancel(HotelCancelVO vo);
	
	/**
	 * 添加酒店取消规则对语言对象
	 * @param i18n
	 * @return
	 */
	HotelCancelI18n addHotelCancelI18n(HotelCancelI18n i18n);
	
	/**
	 * 修改HotelCancel
	 * @param vo
	 */
	void updateHotelCancel(HotelCancelVO vo);
	
	/**
	 * 根据酒店ID删除对应的酒店取消列表
	 * @param hotelId
	 */
	void deleteHotelCancelByHotelId(String hotelId);
	
	/**
	 * 删除一个酒店和取消规则服务ID
	 * @param vo
	 */
	void deleteHotelCancelById(HotelCancelVO vo);
	
	/**
	 * 通过酒店取消规则ID删除所有多语言对象
	 * @param hotelCancelId
	 */
	void deleteHotelCancelI18nById(String hotelCancelId);
	
	/**
	 * 通过ID获取酒店取消规则对象
	 * @param HotelCancelId
	 * @return
	 */
	HotelCancelVO getHotelCancelById(String hotelCancelId);
	
	/**
	 * 通过酒店取消规则VO获取对应的酒店规则列表
	 * @param vo 酒店打包VO对象
	 * @return
	 */
	List<HotelCancelVO> getHotelCancelByObj(HotelCancelVO vo);
	
	/**
	 * 根据酒店取消规则VO对象查询酒店取消规则列表
	 * @param vo
	 * @return
	 */
	HotelCancelResult searchHotelCancel(HotelCancelCreteria creteria);
	
	/**
	 * 根据酒店ID获取HotelCancelVO
	 * @param hotelId
	 * @return
	 */
	HotelCancelVO getHotelCancelByHotelId(String hotelId);
	
	/**
	 * 获取当前酒店未使用的酒店取消规则
	 * @param hotelId
	 * @return
	 */
	List<CancelPolicyVO> getDontUseHotelCancel(String hotelId);

	/**
	 * 获取当前酒店未使用的酒店取消规则(以语言判断)
	 * @param hotelId
	 * @return
	 */
	List<CancelPolicyVO> getDontUseHotelCancel(String hotelId, String language);
	
	/**
	 * 获取已经使用的当前酒店下的房价应用到取消规则的房价
	 * @param hotelId
	 * @param cancelId
	 * @return
	 */
	List<Rateplan> getUseRateCancel(String hotelId, String cancelId);
	
	
	/**
	 * 通过酒店取消规则ID获取所有的多语言对象
	 * @param hotelCancelId
	 * @return
	 */
	List<HotelCancelI18n> getHotelCancelI18ns(String hotelCancelId);
	
	/**
	 * 获取默认语言
	 * @param vo
	 * @return
	 */
	HotelCancelI18n getDefaultLanguageI18n(HotelCancelVO vo);

	/**
	 * 通过ID获取酒店取消规则对象
	 * @param hotelCancelId
	 * @param language
	 * @return
	 */
	HotelCancelVO getHotelCancelById(String hotelCancelId, String language);

}
