package com.ccm.api.model.roomType;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.util.SpringContextUtil;

/**
 * 房型
 * 
 * @author carr
 * 
 */
public class RoomType extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3163585623266123009L;

	private String roomTypeId;// 序号
	private String hotelId;// 酒店序号
	private String roomTypeCode;// 房型代码
	private Integer roomCategory;// 房型类型
	private Integer bedTypeCode;// 床型
	private Integer physicalRooms;// 物理房量
	private Integer roomClassificationCode;// 房间分类

	private Boolean window_type;// 0:无窗/1:有窗
	private Integer maxOccupancy;// 最大入住人数
	private String area;// 面积
	private String floor;// 楼层
	private String bed_type;// 床型（淘宝）
	private String bed_size;// 床宽
	private String internet;// 宽带服务
	private String picId;// 图片路径
	private Boolean has_receipt;// 是否提供发票
	private String receipt_type;// 发票类型
	private String roomTypeTemplateId; //房型模板ID
	private Integer sortNum;	//排序号
	private String pmsCode;     //pms代码
	private String dictId;		//转换代码

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public Integer getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(Integer roomCategory) {
		this.roomCategory = roomCategory;
	}

	public Integer getBedTypeCode() {
		return bedTypeCode;
	}

	public void setBedTypeCode(Integer bedTypeCode) {
		this.bedTypeCode = bedTypeCode;
	}

	public Integer getPhysicalRooms() {
		return physicalRooms;
	}

	public void setPhysicalRooms(Integer physicalRooms) {
		this.physicalRooms = physicalRooms;
	}

	public Integer getRoomClassificationCode() {
		return roomClassificationCode;
	}

	public void setRoomClassificationCode(Integer roomClassificationCode) {
		this.roomClassificationCode = roomClassificationCode;
	}

	public Boolean getWindow_type() {
		return window_type;
	}

	public void setWindow_type(Boolean window_type) {
		this.window_type = window_type;
	}

	public Integer getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(Integer maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getBed_type() {
		return bed_type;
	}

	public void setBed_type(String bed_type) {
		this.bed_type = bed_type;
	}

	public String getBed_size() {
		return bed_size;
	}

	public void setBed_size(String bed_size) {
		this.bed_size = bed_size;
	}

	public String getInternet() {
		return internet;
	}

	public void setInternet(String internet) {
		this.internet = internet;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public Boolean getHas_receipt() {
		return has_receipt;
	}

	public void setHas_receipt(Boolean has_receipt) {
		this.has_receipt = has_receipt;
	}

	public String getReceipt_type() {
		return receipt_type;
	}

	public void setReceipt_type(String receipt_type) {
		this.receipt_type = receipt_type;
	}
	
	
	public String getRoomTypeTemplateId() {
		return roomTypeTemplateId;
	}

	public void setRoomTypeTemplateId(String roomTypeTemplateId) {
		this.roomTypeTemplateId = roomTypeTemplateId;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	
	public String getPmsCode() {
		DictCodeManager dictCodeManager = (DictCodeManager) SpringContextUtil.getBean("dictCodeManager");
		Map<String, String> pms2ccmMap = dictCodeManager.searchCodeMapByHotelId(OXIConstant.roomTypeCode, hotelId, true);
		pmsCode = pms2ccmMap.get(roomTypeCode);
		if(!StringUtils.hasText(pmsCode)){
			return "";
		}
		return pmsCode;
	}

	public void setPmsCode(String pmsCode) {
		this.pmsCode = pmsCode;
	}

	@Override
	public String toString() {
		return "RoomType [roomTypeId=" + roomTypeId + ", hotelId=" + hotelId
				+ ", roomTypeCode=" + roomTypeCode + ", roomCategory="
				+ roomCategory + ", bedTypeCode=" + bedTypeCode
				+ ", physicalRooms=" + physicalRooms
				+ ", roomClassificationCode=" + roomClassificationCode
				+ ", window_type=" + window_type + ", maxOccupancy="
				+ maxOccupancy + ", area=" + area + ", floor=" + floor
				+ ", bed_type=" + bed_type + ", bed_size=" + bed_size
				+ ", internet=" + internet + ", picId=" + picId
				+ ", has_receipt=" + has_receipt + ", receipt_type="
				+ receipt_type + ", roomTypeTemplateId=" + roomTypeTemplateId
				+ ", sortNum=" + sortNum + ", pmsCode=" + pmsCode + ", dictId="
				+ dictId + "]";
	}

}
