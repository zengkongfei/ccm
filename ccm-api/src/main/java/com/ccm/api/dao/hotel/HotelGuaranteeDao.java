package com.ccm.api.dao.hotel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.hotel.HotelGuarantee;
import com.ccm.api.model.hotel.HotelGuaranteeI18n;
import com.ccm.api.model.hotel.vo.HotelGuaranteeCreteria;
import com.ccm.api.model.hotel.vo.HotelGuaranteeVO;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;


public interface HotelGuaranteeDao extends GenericDao<HotelGuarantee, String>{

	/**
	 * 获取酒店级别的担保规则名称
	 * @param hotelId
	 * @param language
	 * @param guaranteeCode
	 * @return
	 */
	HotelGuaranteeI18n getHotelGuaranteeIdAndPolicyName(String hotelId,String language,String guaranteeCode);
	
	/**
	 * 新增一个酒店担保规则对象
	 * @param hotelGuarantee
	 * @return
	 */
	HotelGuarantee addHotelGuarantee(HotelGuarantee hotelGuarantee);
	
	/**
	 * 添加酒店担保规则语言对象
	 * @param i18n
	 * @return
	 */
	HotelGuaranteeI18n addHotelGuaranteeI18n(HotelGuaranteeI18n i18n);
	
	/**
	 * 修改酒店担保规则
	 * @param hotelGuarantee
	 */
	void updaetHotelGuarantee(HotelGuarantee hotelGuarantee);
	
	/**
	 * 修改酒店担保规则多语言记录
	 * @param hotelGuaranteeI18n
	 */
	void updateHotelGuaranteeI18n(HotelGuaranteeI18n hotelGuaranteeI18n);
	
	
	/**
	 * 通过酒店ID软删除所有的酒店担保规则
	 * @param hotelId
	 * @return
	 */
	void deleteHotelGuaranteeByHotelId(String hotelId);
	
	/**
	 * 通过酒店担保规则ID删除所有多语言对象
	 * @param hotelGuaranteeId
	 */
	void deleteHotelGuaranteeI18nById(String hotelGuaranteeId);
	
	/**
	 * 通过ID获取酒店担保规则
	 * @param hotelGuaranteeId
	 * @return
	 */
	HotelGuaranteeVO getHotelGuaranteeById(String hotelGuaranteeId);
	
	/**
	 * 通过ID获取酒店担保规则(多语言)
	 * @param hotelGuaranteeId
	 * @param language
	 * @return
	 */
	HotelGuaranteeVO getHotelGuaranteeById(String hotelGuaranteeId,String language);
	
	/**
	 * 通过酒店担保规则ID获取所有的多语言对象
	 * @param hotelGuaranteeId
	 * @return
	 */
	List<HotelGuaranteeI18n> getHotelGuaranteeI18ns(String hotelGuaranteeId);
	
	
	/**
	 * 通过酒店取消VO获取对应的酒店担保规则列表
	 * @param vo 酒店打包VO对象
	 * @return
	 */
	List<HotelGuaranteeVO> getHotelGuaranteeByObj(HotelGuaranteeVO vo);
	
	/**
	 * 根据酒店取消VO对象查询酒店担保规则列表
	 * @param vo
	 * @return
	 */
	List<HotelGuaranteeVO> searchHotelGuarantee(HotelGuaranteeCreteria creteria);
	
	/**
	 * 查询条数
	 * @param vo
	 * @return
	 */
	Integer searchHotelGuaranteecount(HotelGuaranteeCreteria creteria);
	
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
	 * 根据ID删除绑定关系
	 * @param vo
	 */
	void deleteHotelGuaranteeById(HotelGuaranteeVO vo);

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
	 * 获取已经使用的当前酒店下的房价应用到的担保规则
	 * @param hotelId
	 * @param cancelId
	 * @return
	 */
	List<Rateplan> getUseRateGuarantee(String hotelId, String guaranteeId);

}
