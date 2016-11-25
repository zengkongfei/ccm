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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SpecialRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpecialRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="specialRequestRPH" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="requestCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requestComments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mfResort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="mfInactiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="reservationActionType" type="{profile.fidelio.2.0}reservationActionType" />
 *       &lt;attribute name="mfSpecialRequestType" type="{profile.fidelio.2.0}mfSpecialRequestType" default="FEA" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpecialRequest", propOrder = {
    "specialRequestRPH",
    "requestCode",
    "requestComments",
    "mfResort"
})
public class SpecialRequest {

    protected Integer specialRequestRPH;
    @XmlElement(required = true)
    protected String requestCode;
    protected String requestComments;
    protected String mfResort;
    @XmlAttribute
    protected XMLGregorianCalendar mfInactiveDate;
    @XmlAttribute
    protected ReservationActionType reservationActionType;
    @XmlAttribute
    protected MfSpecialRequestType mfSpecialRequestType;

    /**
     * Gets the value of the specialRequestRPH property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSpecialRequestRPH() {
        return specialRequestRPH;
    }

    /**
     * Sets the value of the specialRequestRPH property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSpecialRequestRPH(Integer value) {
        this.specialRequestRPH = value;
    }

    /**
     * Gets the value of the requestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestCode() {
        return requestCode;
    }

    /**
     * Sets the value of the requestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestCode(String value) {
        this.requestCode = value;
    }

    /**
     * Gets the value of the requestComments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestComments() {
        return requestComments;
    }

    /**
     * Sets the value of the requestComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestComments(String value) {
        this.requestComments = value;
    }

    /**
     * Gets the value of the mfResort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfResort() {
        return mfResort;
    }

    /**
     * Sets the value of the mfResort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfResort(String value) {
        this.mfResort = value;
    }

    /**
     * Gets the value of the mfInactiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMfInactiveDate() {
        return mfInactiveDate;
    }

    /**
     * Sets the value of the mfInactiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMfInactiveDate(XMLGregorianCalendar value) {
        this.mfInactiveDate = value;
    }

    /**
     * Gets the value of the reservationActionType property.
     * 
     * @return
     *     possible object is
     *     {@link ReservationActionType }
     *     
     */
    public ReservationActionType getReservationActionType() {
        return reservationActionType;
    }

    /**
     * Sets the value of the reservationActionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationActionType }
     *     
     */
    public void setReservationActionType(ReservationActionType value) {
        this.reservationActionType = value;
    }

    /**
     * Gets the value of the mfSpecialRequestType property.
     * 
     * @return
     *     possible object is
     *     {@link MfSpecialRequestType }
     *     
     */
    public MfSpecialRequestType getMfSpecialRequestType() {
        if (mfSpecialRequestType == null) {
            return MfSpecialRequestType.FEA;
        } else {
            return mfSpecialRequestType;
        }
    }

    /**
     * Sets the value of the mfSpecialRequestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MfSpecialRequestType }
     *     
     */
    public void setMfSpecialRequestType(MfSpecialRequestType value) {
        this.mfSpecialRequestType = value;
    }

}
