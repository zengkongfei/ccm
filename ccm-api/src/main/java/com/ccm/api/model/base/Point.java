/**
 * 
 */
package com.ccm.api.model.base;

import java.io.Serializable;

/**
 * @author Jenny Liao
 *
 */
public class Point implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5874424171492735729L;

	private Double latitude; //维度
	private Double longitude; //经度
	
	public Point() {
		
	}
	
	public Point(Double longitude, Double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}
