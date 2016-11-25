package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * 酒店周边
 * @author carr
 *
 */
public class RelativePosition extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6672857454185988830L;

	private String relativePositionId;//序号    
	private String hotelId;//酒店序号           
	private String positionId;//地标序号        
	private String direction;//方位             
	private Double distance;//距离              
	private Integer unitOfMeasureCode;//距离单位 
	private Boolean isNearest;//是否最近         
	private String isPrimary;//是否主要的    
	
	public String getRelativePositionId() {
		return relativePositionId;
	}
	public void setRelativePositionId(String relativePositionId) {
		this.relativePositionId = relativePositionId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Integer getUnitOfMeasureCode() {
		return unitOfMeasureCode;
	}
	public void setUnitOfMeasureCode(Integer unitOfMeasureCode) {
		this.unitOfMeasureCode = unitOfMeasureCode;
	}
	public Boolean getIsNearest() {
		return isNearest;
	}
	public void setIsNearest(Boolean isNearest) {
		this.isNearest = isNearest;
	}
	public String getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}
	
	@Override
	public String toString() {
		return "RelativePosition [relativePositionId=" + relativePositionId
				+ ", hotelId=" + hotelId + ", positionId=" + positionId
				+ ", direction=" + direction + ", distance=" + distance
				+ ", unitOfMeasureCode=" + unitOfMeasureCode + ", isNearest="
				+ isNearest + ", isPrimary=" + isPrimary + "]";
	}
}
