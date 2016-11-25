package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * anonymous complex type的 Java 类。
 * 
 * <p>
 * 以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelReservation" type="{http://webservices.micros.com/og/4.3/Reservation/}HotelReservation"/>
 *         &lt;element name="ExternalSystemNumber" type="{http://webservices.micros.com/og/4.3/Reservation/}ExternalReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "hotelReservation", "externalSystemNumber" })
@XmlRootElement(name = "CreateBookingRequest", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
public class CreateBookingRequest {

	@XmlElement(name = "HotelReservation", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
	protected HotelReservation hotelReservation;
	@XmlElement(name = "ExternalSystemNumber", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
	protected ExternalReference externalSystemNumber;

	/**
	 * 获取hotelReservation属性的值。
	 * 
	 * @return possible object is {@link HotelReservation }
	 * 
	 */
	public HotelReservation getHotelReservation() {
		return hotelReservation;
	}

	/**
	 * 设置hotelReservation属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link HotelReservation }
	 * 
	 */
	public void setHotelReservation(HotelReservation value) {
		this.hotelReservation = value;
	}

	/**
	 * 获取externalSystemNumber属性的值。
	 * 
	 * @return possible object is {@link ExternalReference }
	 * 
	 */
	public ExternalReference getExternalSystemNumber() {
		return externalSystemNumber;
	}

	/**
	 * 设置externalSystemNumber属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link ExternalReference }
	 * 
	 */
	public void setExternalSystemNumber(ExternalReference value) {
		this.externalSystemNumber = value;
	}

}
