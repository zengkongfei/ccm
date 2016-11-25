package com.ccm.api.service.hotel;

import java.util.List;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.hotel.HotelGuarantee;
import com.ccm.api.model.hotel.HotelGuaranteeI18n;
import com.ccm.api.model.hotel.vo.HotelGuaranteeCreteria;
import com.ccm.api.model.hotel.vo.HotelGuaranteeResult;
import com.ccm.api.model.hotel.vo.HotelGuaranteeVO;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.service.base.GenericManager;

public interface HotelGuaranteeManager extends GenericManager<HotelGuarantee, String>{

	/**
	 * 添加HotelGuarantee
	 * @param vo
	 */
	HotelGuaranteeVO saveHotelGuarantee(HotelGuaranteeVO vo);
	
	/**
	 * 添加酒店担保规则对语言对象
	 * @param i18n
	 * @return
	 */
	HotelGuaranteeI18n addHotelGuaranteeI18n(HotelGuaranteeI18n i18n);
	
	/**
	 * 修改HotelGuarantee
	 * @param vo
	 */
	void updateHotelGuarantee(HotelGuaranteeVO vo);
	
	/**
	 * 根据酒店ID删除对应的酒店担保列表
	 * @param hotelId
	 */
	void deleteHotelGuaranteeByHotelId(String hotelId);
	
	/**
	 * 删除一个酒店和担保规则服务ID
	 * @param vo
	 */
	void deleteHotelGuaranteeById(HotelGuaranteeVO vo);
	
	/**
	 * 通过酒店担保规则ID删除所有多语言对象
	 * @param hotelGuaranteeId
	 */
	void deleteHotelGuaranteeI18nById(String hotelGuaranteeId);
	
	/**
	 * 通过ID获取酒店担保规则对象
	 * @param hotelGuaranteeId
	 * @return
	 */
	HotelGuaranteeVO getHotelGuaranteeById(String hotelGuaranteeId);
	/**
	 * 通过ID获取酒店担保规则对象
	 * @param hotelGuaranteeId
	 * @param language
	 * @return
	 */
	HotelGuaranteeVO getHotelGuaranteeById(String hotelGuaranteeId,String language);
	
	/**
	 * 通过酒店担保规则VO获取对应的酒店规则列表
	 * @param vo 酒店打包VO对象
	 * @return
	 */
	List<HotelGuaranteeVO> getHotelGuaranteeByObj(HotelGuaranteeVO vo);
	
	/**
	 * 根据酒店担保规则VO对象查询酒店担保规则列表
	 * @param vo
	 * @return
	 */
	HotelGuaranteeResult searchHotelGuarantee(HotelGuaranteeCreteria creteria);
	
	/**
	 * 根据酒店ID获取HotelGuaranteeVO
	 * @param hotelId
	 * @return
	 */
	HotelGuaranteeVO getHotelGuaranteeByHotelId(String hotelId);
	
	/**
	 * 获取酒店下的担保规则代码
	 * 
	 * @param hotelId
	 * @return
	 */
	List<HotelGuaranteeVO> getHotelGuaranteeCodeByHotelId(String hotelId);
	
	/**
	 * 获取当前酒店未使用的酒店担保规则
	 * @param hotelId
	 * @return
	 */
	List<GuaranteePolicyVO> getDontUseHotelGuarantee(String hotelId);

	/**
	 * 获取当前酒店未使用的酒店担保规则(以语言判断)
	 * @param hotelId
	 * @return
	 */
	List<GuaranteePolicyVO> getDontUseHotelGuarantee(String hotelId, String language);
	
	/**
	 * 获取已经使用的当前酒店下的房价应用到担保规则的房价
	 * @param hotelId
	 * @param guaranteeId
	 * @return
	 */
	List<Rateplan> getUseRateGuarantee(String hotelId, String guaranteeId);
	
	/**
	 * 通过酒店担保规则ID获取所有的多语言对象
	 * @param hotelGuaranteeId
	 * @return
	 */
	List<HotelGuaranteeI18n> getHotelGuaranteeI18ns(String hotelGuaranteeId);
	
	/**
	 * 获取默认语言
	 * @param vo
	 * @return
	 */
	HotelGuaranteeI18n getDefaultLanguageI18n(HotelGuaranteeVO vo);

}
