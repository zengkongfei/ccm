//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.10 at 03:58:32 下午 CST 
//

package com.ccm.oxi.profile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for PhoneNumber complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PhoneNumber">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="countryAccessNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneExtension" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfPrimaryYN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="mfInactiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="phoneNumberType" type="{profile.fidelio.2.0}phoneNumberType" default="HOME" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneNumber", propOrder = { "countryAccessNumber", "cityCode", "phoneNumber", "phoneExtension", "mfPrimaryYN" })
public class PhoneNumber {

	protected String countryAccessNumber;
	protected String cityCode;
	protected String phoneNumber;
	protected String phoneExtension;
	protected String mfPrimaryYN;
	@XmlAttribute
	protected XMLGregorianCalendar mfInactiveDate;
	@XmlAttribute
	protected String phoneNumberType;

	/**
	 * Gets the value of the countryAccessNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCountryAccessNumber() {
		return countryAccessNumber;
	}

	/**
	 * Sets the value of the countryAccessNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCountryAccessNumber(String value) {
		this.countryAccessNumber = value;
	}

	/**
	 * Gets the value of the cityCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * Sets the value of the cityCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCityCode(String value) {
		this.cityCode = value;
	}

	/**
	 * Gets the value of the phoneNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the value of the phoneNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}

	/**
	 * Gets the value of the phoneExtension property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhoneExtension() {
		return phoneExtension;
	}

	/**
	 * Sets the value of the phoneExtension property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhoneExtension(String value) {
		this.phoneExtension = value;
	}

	/**
	 * Gets the value of the mfPrimaryYN property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMfPrimaryYN() {
		return mfPrimaryYN;
	}

	/**
	 * Sets the value of the mfPrimaryYN property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMfPrimaryYN(String value) {
		this.mfPrimaryYN = value;
	}

	/**
	 * Gets the value of the mfInactiveDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getMfInactiveDate() {
		return mfInactiveDate;
	}

	/**
	 * Sets the value of the mfInactiveDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setMfInactiveDate(XMLGregorianCalendar value) {
		this.mfInactiveDate = value;
	}

	/**
	 * Gets the value of the phoneNumberType property.
	 * 
	 * @return possible object is {@link PhoneNumberType }
	 * 
	 */
	public String getPhoneNumberType() {
		if (phoneNumberType == null) {
			return PhoneNumberType.HOME.name();
		} else {
			return phoneNumberType;
		}
	}

	/**
	 * Sets the value of the phoneNumberType property.
	 * 
	 * @param value
	 *            allowed object is {@link PhoneNumberType }
	 * 
	 */
	public void setPhoneNumberType(String value) {
		this.phoneNumberType = value;
	}

}