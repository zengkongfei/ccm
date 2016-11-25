package com.ccm.api.dao.hotel;

import java.util.List;
import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.hotel.HotelPackage;
import com.ccm.api.model.hotel.HotelPackageI18n;
import com.ccm.api.model.hotel.vo.HotelPackageCreteria;
import com.ccm.api.model.hotel.vo.HotelPackageVO;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.roomType.RoomType;

public interface HotelPackageDao extends GenericDao<HotelPackage, String>{

	/**
	 * 保存酒店打包服务关联
	 * @param hotelPackage
	 * @return
	 */
	HotelPackage addHotelPackage(HotelPackage hotelPackage);
	
	/**
	 * 添加酒店打包对语言对象
	 * @param i18n
	 * @return
	 */
	HotelPackageI18n addHotelPackageI18n(HotelPackageI18n i18n);
	
	/**
	 * 修改酒店打包
	 * @param hotelPackage
	 */
	void updaetHotelPackage(HotelPackage hotelPackage);
	
	/**
	 * 修改酒店打包多语言记录
	 * @param hotelPackage
	 */
	void updateHotelPackageI18n(HotelPackageI18n hotelPackageI18n);
	
	
	/**
	 * 通过酒店ID软删除所有的酒店打包服务关联
	 * @param hotelId
	 * @return
	 */
	void deleteHotelPackageByHotelId(String hotelId);
	
	/**
	 * 通过酒店打包ID删除所有多语言对象
	 * @param hotelPackageId
	 */
	void deleteHotelPackageI18nById(String hotelPackageId);
	
	/**
	 * 通过ID获取酒店打包对象
	 * @param hotelPackageId
	 * @return
	 */
	HotelPackageVO getHotelPackageById(String hotelPackageId);
	
	/**
	 * 通过ID获取酒店打包对象(多语言)
	 * @param hotelPackageId
	 * @param language
	 * @return
	 */
	HotelPackageVO getHotelPackageById(String hotelPackageId,String language);
	
	/**
	 * 通过酒店打包ID获取所有的多语言对象
	 * @param hotelPackageId
	 * @return
	 */
	List<HotelPackageI18n> getHotelPackageI18ns(String hotelPackageId);
	
	/**
	 * 通过酒店打包VO获取对应的酒店打包列表
	 * @param vo 酒店打包VO对象
	 * @return
	 */
	List<HotelPackageVO> getHotelPackageByObj(HotelPackageVO vo);
	
	/**
	 * 根据酒店打包VO对象查询酒店打包服务列表
	 * @param vo
	 * @return
	 */
	List<HotelPackageVO> searchHotelPackage(HotelPackageCreteria creteria);
	
	/**
	 * 查询条数
	 * @param vo
	 * @return
	 */
	Integer searchHotelPackagecount(HotelPackageCreteria creteria);
	
	/**
	 * 根据酒店ID获取HotelPackageVO
	 * @param hotelId
	 * @return
	 */
	HotelPackageVO getHotelPackageByHotelId(String hotelId);
	
	/**
	 * 根据酒店ID获取HotelPackageVO(多语言)
	 * @param hotelId
	 * @return
	 */
	HotelPackageVO getHotelPackageByHotelId(String hotelId,String language);

	/**
	 * 根据ID删除绑定关系
	 * @param vo
	 */
	void deleteHotelPackageById(HotelPackageVO vo);

	/**
	 * 获取当前酒店未使用的酒店打包服务
	 * @param hotelId
	 * @return
	 */
	List<PackageVO> getDontUseHotelPackage(String hotelId);

	/**
	 * 获取当前酒店未使用的酒店打包服务(以语言判断)
	 * @param hotelId
	 * @return
	 */
	List<PackageVO> getDontUseHotelPackage(String hotelId, String language);

	/**
	 * 获取已经使用的当前酒店下的房价应用到包价的房价
	 * @param hotelId
	 * @param packageId
	 * @return
	 */
	List<Rateplan> getUseRatePackage(String hotelId, String packageId);

	/**
	 * 获取已经使用的当前酒店下的房型应用到的包价的房型
	 * @param hotelId
	 * @param packageId
	 * @return
	 */
	List<RoomType> getUseRoomPackage(String hotelId, String packageId);

	/**
	 * 获取已经使用的当前酒店下的动态包价
	 * @param hotelId
	 * @param packageId
	 * @return
	 */
	List<DynamicPackage> getUseDynamicPackage(String hotelId, String packageId);

}
