package com.ccm.api.dao.hotel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.hotel.HotelCancel;
import com.ccm.api.model.hotel.HotelCancelI18n;
import com.ccm.api.model.hotel.vo.HotelCancelCreteria;
import com.ccm.api.model.hotel.vo.HotelCancelVO;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;

public interface HotelCancelDao extends GenericDao<HotelCancel, String>{

	/**
	 * 新增一个酒店取消规则对象
	 * @param hotelCancel
	 * @return
	 */
	HotelCancel addHotelCancel(HotelCancel hotelCancel);
	
	/**
	 * 添加酒店取消规则语言对象
	 * @param i18n
	 * @return
	 */
	HotelCancelI18n addHotelCancelI18n(HotelCancelI18n i18n);
	
	/**
	 * 修改酒店取消规则
	 * @param hotelCancel
	 */
	void updaetHotelCancel(HotelCancel hotelGuarantee);
	
	/**
	 * 修改酒店取消规则多语言记录
	 * @param hotelCancelI18n
	 */
	void updateHotelCancelI18n(HotelCancelI18n hotelCancelI18n);
	
	/**
	 * 通过酒店ID软删除所有的酒店取消规则
	 * @param hotelId
	 * @return
	 */
	void deleteHotelCancelByHotelId(String hotelId);
	
	/**
	 * 通过酒店取消规则ID删除所有多语言对象
	 * @param hotelCancelId
	 */
	void deleteHotelCancelI18nById(String hotelCancelId);
	
	/**
	 * 通过ID获取酒店取消规则
	 * @param hotelCancelId
	 * @return
	 */
	HotelCancelVO getHotelCancelById(String hotelCancelId);
	
	/**
	 * 通过ID获取酒店取消规则(多语言)
	 * @param hotelCancelId
	 * @param language
	 * @return
	 */
	HotelCancelVO getHotelCancelById(String hotelCancelId,String language);
	
	/**
	 * 通过酒店取消规则ID获取所有的多语言对象
	 * @param hotelGuaranteeId
	 * @return
	 */
	List<HotelCancelI18n> getHotelCancelI18ns(String hotelCancelId);
	
	/**
	 * 通过酒店取消VO获取对应的酒店取消规则列表
	 * @param vo 酒店打包VO对象
	 * @return
	 */
	List<HotelCancelVO> getHotelCancelByObj(HotelCancelVO vo);
	
	/**
	 * 根据酒店取消VO对象查询酒店取消规则列表
	 * @param vo
	 * @return
	 */
	List<HotelCancelVO> searchHotelCancel(HotelCancelCreteria creteria);
	
	/**
	 * 查询条数
	 * @param vo
	 * @return
	 */
	Integer searchHotelCancelcount(HotelCancelCreteria creteria);
	
	/**
	 * 根据酒店ID获取HotelCancelVO
	 * @param hotelId
	 * @return
	 */
	HotelCancelVO getHotelCancelByHotelId(String hotelId);

	/**
	 * 根据ID删除绑定关系
	 * @param vo
	 */
	void deleteHotelCancelById(HotelCancelVO vo);

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
	 * 获取已经使用的当前酒店下的房价应用到的取消规则
	 * @param hotelId
	 * @param cancelId
	 * @return
	 */
	List<Rateplan> getUseRateCancel(String hotelId, String cancelId);

}
