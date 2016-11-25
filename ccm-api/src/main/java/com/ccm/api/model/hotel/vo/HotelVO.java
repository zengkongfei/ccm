/**
 * 
 */
package com.ccm.api.model.hotel.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.common.Picture;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.HotelI18n;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.roomType.vo.RoomTypeVO;

/**
 * @author Jenny
 * 
 */
public class HotelVO extends Hotel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1175646623758773888L;

	private String address;
	private String description;

	private Long province;
	private String provinceStr;
	private String cityStr;
	private String areaStr;

	private Map<String, String> service = new HashMap<String, String>();// 服务设施

	private String picUrl;// 淘宝图片
	private String picId; // 图片ID
	private String pictureUrlFolder; // 图片目录

	private Boolean flagSysHotel = false;// true:查询酒店审核状态

	private String hotelMId;// 序号
	private String languageCode;// 语言
	private String hotelName;// 酒店名称
	private String hotelUsedName;// 酒店曾用名
	private String business;// 商业区（圈）长度不超过20字

	private String hotelShortName;// 酒店简称
	private String chainName;// 集团名称
	private String chainCode;// 集团代码
	private String brandName;// 品牌名称
	private List<RatePlanVO> ratePlanVOList;// 房价信息
	private List<RoomTypeVO> roomTypeVOList;// 房型信息
	private List<PriceCalc> priceCalcList;// 房价日历
	private List<HotelI18n> hotelI18nList; // 多语言数据列表
	private List<SpecialOfferVO> specialOfferVoList; // 优惠信息
	private List<Picture> pictureList; // 酒店图片列表
	private Picture logoPic; // logo图片

	private String channelHotelCode;// 淘宝hid
	private String channelCode;
	private Integer channelStatus;
	private Long matchStatus;
	private String salutatory; // 欢迎词
	private String exception;// 异常错误

	private String checkInTimeDesc; // 入住时间描述
	private String checkOutTimeDesc; // 退房时间描述
	private String cancelPolicyDesc; // 取消政策描述
	private String payRemind; // 支付提醒
	private String pickUpService; // 接机服务提醒
	//酒店样式css文件
	private String cssFileId;
	private String cssName; //上传时的css文件名
	private String cssUrl;
	private String privinceName;//省份名称
	
	private String pmsAccount;//PMS使用者账号
	
	public String getPmsAccount() {
		return pmsAccount;
	}
	public void setPmsAccount(String pmsAccount) {
		this.pmsAccount = pmsAccount;
	}
	
	public String getCssUrl() {
		return cssUrl;
	}

	public void setCssUrl(String cssUrl) {
		this.cssUrl = cssUrl;
	}

	public String getCssName() {
		return cssName;
	}

	public void setCssName(String cssName) {
		this.cssName = cssName;
	}

	public String getCssFileId() {
		return cssFileId;
	}

	public void setCssFileId(String cssFileId) {
		this.cssFileId = cssFileId;
	}
	public String getHotelMId() {
		return hotelMId;
	}

	public void setHotelMId(String hotelMId) {
		this.hotelMId = hotelMId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelShortName() {
		return hotelShortName;
	}

	public void setHotelShortName(String hotelShortName) {
		this.hotelShortName = hotelShortName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取六位省份编码，后四位补0
	 * 
	 * @return
	 */
	public Long getProvinceSix() {
		if (province != null) {
			if (province.toString().length() > 2) {
				return province;
			} else {
				return province.longValue() * 10000;
			}
		} else {
			Long areaCode = getAreaCode();
			if (areaCode != null) {
				province = areaCode.longValue() / 10000 * 10000;
			}
			return province;
		}
	}

	/**
	 * 获取两位省份编码
	 * 
	 * @return
	 */
	public Long getProvince() {
		if (province != null) {
			if (province.toString().length() > 2) {
				return province / 10000;
			} else {
				return province;
			}
		} else {
			Long areaCode = getAreaCode();
			if (areaCode != null) {
				province = areaCode.longValue() / 10000;
			} else if (getCity() != null) {
				province = getCity().longValue() / 10000;
			}
			return province;
		}
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	/**
	 * 获取六位城市编码，后四位补0
	 * 
	 * @return
	 */
	public Long getCitySix() {
		Long city = getCity();
		if (city != null) {
			if (city.toString().length() > 4) {
				return city;
			} else {
				return city.longValue() * 100;
			}
		} else {
			Long areaCode = getAreaCode();
			if (areaCode != null) {
				city = areaCode.longValue() / 100 * 100;
			}
			return city;
		}
	}

	public Map<String, String> getService() {
		return service;
	}

	public void setService(Map<String, String> service) {
		this.service = service;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getProvinceStr() {
		return provinceStr;
	}

	public void setProvinceStr(String provinceStr) {
		this.provinceStr = provinceStr;
	}

	public String getCityStr() {
		return cityStr;
	}

	public void setCityStr(String cityStr) {
		this.cityStr = cityStr;
	}

	public String getAreaStr() {
		return areaStr;
	}

	public void setAreaStr(String areaStr) {
		this.areaStr = areaStr;
	}

	public Boolean getFlagSysHotel() {
		return flagSysHotel;
	}

	public void setFlagSysHotel(Boolean flagSysHotel) {
		this.flagSysHotel = flagSysHotel;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getChainName() {
		return chainName;
	}

	public void setChainName(String chainName) {
		this.chainName = chainName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<RatePlanVO> getRatePlanVOList() {
		return ratePlanVOList;
	}

	public void setRatePlanVOList(List<RatePlanVO> ratePlanVOList) {
		this.ratePlanVOList = ratePlanVOList;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public List<RoomTypeVO> getRoomTypeVOList() {
		return roomTypeVOList;
	}

	public void setRoomTypeVOList(List<RoomTypeVO> roomTypeVOList) {
		this.roomTypeVOList = roomTypeVOList;
	}

	public List<PriceCalc> getPriceCalcList() {
		return priceCalcList;
	}

	public void setPriceCalcList(List<PriceCalc> priceCalcList) {
		this.priceCalcList = priceCalcList;
	}

	public String getHotelUsedName() {
		return hotelUsedName;
	}

	public void setHotelUsedName(String hotelUsedName) {
		this.hotelUsedName = hotelUsedName;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getChannelHotelCode() {
		return channelHotelCode;
	}

	public void setChannelHotelCode(String channelHotelCode) {
		this.channelHotelCode = channelHotelCode;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Integer getChannelStatus() {
		return channelStatus;
	}

	public void setChannelStatus(Integer channelStatus) {
		this.channelStatus = channelStatus;
	}

	public Long getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(Long matchStatus) {
		this.matchStatus = matchStatus;
	}

	public String getSalutatory() {
		return salutatory;
	}

	public void setSalutatory(String salutatory) {
		this.salutatory = salutatory;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public List<HotelI18n> getHotelI18nList() {
		return hotelI18nList;
	}

	public void setHotelI18nList(List<HotelI18n> hotelI18nList) {
		this.hotelI18nList = hotelI18nList;
	}

	public String getCheckInTimeDesc() {
		return checkInTimeDesc;
	}

	public void setCheckInTimeDesc(String checkInTimeDesc) {
		this.checkInTimeDesc = checkInTimeDesc;
	}

	public String getCheckOutTimeDesc() {
		return checkOutTimeDesc;
	}

	public void setCheckOutTimeDesc(String checkOutTimeDesc) {
		this.checkOutTimeDesc = checkOutTimeDesc;
	}

	public String getCancelPolicyDesc() {
		return cancelPolicyDesc;
	}

	public void setCancelPolicyDesc(String cancelPolicyDesc) {
		this.cancelPolicyDesc = cancelPolicyDesc;
	}

	public String getPayRemind() {
		return payRemind;
	}

	public void setPayRemind(String payRemind) {
		this.payRemind = payRemind;
	}

	public String getPickUpService() {
		return pickUpService;
	}

	public void setPickUpService(String pickUpService) {
		this.pickUpService = pickUpService;
	}

	public List<SpecialOfferVO> getSpecialOfferVoList() {
		return specialOfferVoList;
	}

	public void setSpecialOfferVoList(List<SpecialOfferVO> specialOfferVoList) {
		this.specialOfferVoList = specialOfferVoList;
	}

	public List<Picture> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<Picture> pictureList) {
		this.pictureList = pictureList;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getPictureUrlFolder() {
		return pictureUrlFolder;
	}

	public void setPictureUrlFolder(String pictureUrlFolder) {
		this.pictureUrlFolder = pictureUrlFolder;
	}

	public Picture getLogoPic() {
		return logoPic;
	}

	public void setLogoPic(Picture logoPic) {
		this.logoPic = logoPic;
	}

	public String getPrivinceName() {
		return privinceName;
	}

	public void setPrivinceName(String privinceName) {
		this.privinceName = privinceName;
	}

	@Override
	public String toString() {
		return "HotelVO [address=" + address + ", description=" + description
				+ ", province=" + province + ", provinceStr=" + provinceStr
				+ ", cityStr=" + cityStr + ", areaStr=" + areaStr
				+ ", service=" + service + ", picUrl=" + picUrl + ", picId="
				+ picId + ", pictureUrlFolder=" + pictureUrlFolder
				+ ", flagSysHotel=" + flagSysHotel + ", hotelMId=" + hotelMId
				+ ", languageCode=" + languageCode + ", hotelName=" + hotelName
				+ ", hotelUsedName=" + hotelUsedName + ", business=" + business
				+ ", hotelShortName=" + hotelShortName + ", chainName="
				+ chainName + ", chainCode=" + chainCode + ", brandName="
				+ brandName + ", ratePlanVOList=" + ratePlanVOList
				+ ", roomTypeVOList=" + roomTypeVOList + ", priceCalcList="
				+ priceCalcList + ", hotelI18nList=" + hotelI18nList
				+ ", specialOfferVoList=" + specialOfferVoList
				+ ", pictureList=" + pictureList + ", logoPic=" + logoPic
				+ ", channelHotelCode=" + channelHotelCode + ", channelCode="
				+ channelCode + ", channelStatus=" + channelStatus
				+ ", matchStatus=" + matchStatus + ", salutatory=" + salutatory
				+ ", exception=" + exception + ", checkInTimeDesc="
				+ checkInTimeDesc + ", checkOutTimeDesc=" + checkOutTimeDesc
				+ ", cancelPolicyDesc=" + cancelPolicyDesc + ", payRemind="
				+ payRemind + ", pickUpService=" + pickUpService
				+ ", cssFileId=" + cssFileId + ", cssName=" + cssName
				+ ", cssUrl=" + cssUrl + ", privinceName=" + privinceName + "]";
	}


}
