package com.ccm.api.service.hotel;

import java.util.List;

import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.hotel.HotelPackage;
import com.ccm.api.model.hotel.HotelPackageI18n;
import com.ccm.api.model.hotel.vo.HotelPackageCreteria;
import com.ccm.api.model.hotel.vo.HotelPackageResult;
import com.ccm.api.model.hotel.vo.HotelPackageVO;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.service.base.GenericManager;

public interface HotelPackageManager extends GenericManager<HotelPackage, String>{

	/**
	 * 添加HotelPackage
	 * @param vo
	 */
	HotelPackageVO saveHotelPackage(HotelPackageVO vo);
	
	/**
	 * 添加酒店打包对语言对象
	 * @param i18n
	 * @return
	 */
	HotelPackageI18n addHotelPackageI18n(HotelPackageI18n i18n);
	
	/**
	 * 修改HotelPackage
	 * @param vo
	 */
	void updateHotelPackage(HotelPackageVO vo);
	
	/**
	 * 根据酒店ID删除对应的酒店打包列表
	 * @param hotelId
	 */
	void deleteHotelPackageByHotelId(String hotelId);
	
	/**
	 * 删除一个酒店和打包服务ID
	 * @param vo
	 */
	void deleteHotelPackageById(HotelPackageVO vo);
	
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
	HotelPackageResult searchHotelPackage(HotelPackageCreteria creteria);
	
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
	
	/**
	 * 通过酒店打包ID获取所有的多语言对象
	 * @param hotelPackageId
	 * @return
	 */
	List<HotelPackageI18n> getHotelPackageI18ns(String hotelPackageId);
	
	/**
	 * 获取默认语言
	 * @param vo
	 * @return
	 */
	HotelPackageI18n getDefaultLanguageI18n(HotelPackageVO vo);
	

}
