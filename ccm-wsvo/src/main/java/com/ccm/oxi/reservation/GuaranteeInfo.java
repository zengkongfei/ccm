//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.10 at 03:58:32 下午 CST 
//


package com.ccm.oxi.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GuaranteeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GuaranteeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mfGuaranteeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dropTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="resCreditCardRPH" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="resProfileRPH" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="GuaranteeDeposit" type="{reservation.fidelio.2.0}GuaranteeDeposit" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="guaranteeType" type="{reservation.fidelio.2.0}guaranteeType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GuaranteeInfo", propOrder = {
    "mfGuaranteeType",
    "dropTime",
    "resCreditCardRPH",
    "resProfileRPH",
    "guaranteeDeposit"
})
public class GuaranteeInfo {

    protected String mfGuaranteeType;
    protected XMLGregorianCalendar dropTime;
    @XmlSchemaType(name = "unsignedInt")
    protected Long resCreditCardRPH;
    @XmlSchemaType(name = "unsignedInt")
    protected Long resProfileRPH;
    @XmlElement(name = "GuaranteeDeposit")
    protected GuaranteeDeposit guaranteeDeposit;
    @XmlAttribute
    protected GuaranteeType guaranteeType;

    /**
     * Gets the value of the mfGuaranteeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMfGuaranteeType() {
        return mfGuaranteeType;
    }

    /**
     * Sets the value of the mfGuaranteeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMfGuaranteeType(String value) {
        this.mfGuaranteeType = value;
    }

    /**
     * Gets the value of the dropTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDropTime() {
        return dropTime;
    }

    /**
     * Sets the value of the dropTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDropTime(XMLGregorianCalendar value) {
        this.dropTime = value;
    }

    /**
     * Gets the value of the resCreditCardRPH property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getResCreditCardRPH() {
        return resCreditCardRPH;
    }

    /**
     * Sets the value of the resCreditCardRPH property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setResCreditCardRPH(Long value) {
        this.resCreditCardRPH = value;
    }

    /**
     * Gets the value of the resProfileRPH property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getResProfileRPH() {
        return resProfileRPH;
    }

    /**
     * Sets the value of the resProfileRPH property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setResProfileRPH(Long value) {
        this.resProfileRPH = value;
    }

    /**
     * Gets the value of the guaranteeDeposit property.
     * 
     * @return
     *     possible object is
     *     {@link GuaranteeDeposit }
     *     
     */
    public GuaranteeDeposit getGuaranteeDeposit() {
        return guaranteeDeposit;
    }

    /**
     * Sets the value of the guaranteeDeposit property.
     * 
     * @param value
     *     allowed object is
     *     {@link GuaranteeDeposit }
     *     
     */
    public void setGuaranteeDeposit(GuaranteeDeposit value) {
        this.guaranteeDeposit = value;
    }

    /**
     * Gets the value of the guaranteeType property.
     * 
     * @return
     *     possible object is
     *     {@link GuaranteeType }
     *     
     */
    public GuaranteeType getGuaranteeType() {
        return guaranteeType;
    }

    /**
     * Sets the value of the guaranteeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link GuaranteeType }
     *     
     */
    public void setGuaranteeType(GuaranteeType value) {
        this.guaranteeType = value;
    }

}