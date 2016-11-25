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
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference"/>
 *         &lt;element name="ConfirmationNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID"/>
 *         &lt;element name="CancelTerm" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CancelTerm"/>
 *         &lt;element name="GDSID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID"/>
 *         &lt;element name="LegNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
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
@XmlType(name = "", propOrder = { "hotelReference", "confirmationNumber", "cancelTerm", "gdsid", "legNumber", "externalSystemNumber" })
@XmlRootElement(name = "CancelBookingRequest", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
public class CancelBookingRequest {

	@XmlElement(name = "HotelReference", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
	protected HotelReference hotelReference;
	@XmlElement(name = "ConfirmationNumber", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
	protected UniqueID confirmationNumber;
	@XmlElement(name = "CancelTerm", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
	protected CancelTerm cancelTerm;
	@XmlElement(name = "GDSID", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
	protected UniqueID gdsid;
	@XmlElement(name = "LegNumber", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
	protected UniqueID legNumber;
	@XmlElement(name = "ExternalSystemNumber", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
	protected ExternalReference externalSystemNumber;

	/**
	 * 获取hotelReference属性的值。
	 * 
	 * @return possible object is {@link HotelReference }
	 * 
	 */
	public HotelReference getHotelReference() {
		return hotelReference;
	}

	/**
	 * 设置hotelReference属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link HotelReference }
	 * 
	 */
	public void setHotelReference(HotelReference value) {
		this.hotelReference = value;
	}

	/**
	 * 获取confirmationNumber属性的值。
	 * 
	 * @return possible object is {@link UniqueID }
	 * 
	 */
	public UniqueID getConfirmationNumber() {
		return confirmationNumber;
	}

	/**
	 * 设置confirmationNumber属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link UniqueID }
	 * 
	 */
	public void setConfirmationNumber(UniqueID value) {
		this.confirmationNumber = value;
	}

	/**
	 * 获取cancelTerm属性的值。
	 * 
	 * @return possible object is {@link CancelTerm }
	 * 
	 */
	public CancelTerm getCancelTerm() {
		return cancelTerm;
	}

	/**
	 * 设置cancelTerm属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link CancelTerm }
	 * 
	 */
	public void setCancelTerm(CancelTerm value) {
		this.cancelTerm = value;
	}

	/**
	 * 获取gdsid属性的值。
	 * 
	 * @return possible object is {@link UniqueID }
	 * 
	 */
	public UniqueID getGDSID() {
		return gdsid;
	}

	/**
	 * 设置gdsid属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link UniqueID }
	 * 
	 */
	public void setGDSID(UniqueID value) {
		this.gdsid = value;
	}

	/**
	 * 获取legNumber属性的值。
	 * 
	 * @return possible object is {@link UniqueID }
	 * 
	 */
	public UniqueID getLegNumber() {
		return legNumber;
	}

	/**
	 * 设置legNumber属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link UniqueID }
	 * 
	 */
	public void setLegNumber(UniqueID value) {
		this.legNumber = value;
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
