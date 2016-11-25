//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.18 at 04:07:49 pmCST 
//


package com.ccm.api.model.ads.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Telephone number data.
 * 
 * <p>Java class for BaseTelephone complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseTelephone">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.micros.com/2002A}PhoneNumber"/>
 *         &lt;element ref="{http://www.micros.com/2002A}PhoneData"/>
 *       &lt;/choice>
 *       &lt;attribute name="primary" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="phoneUseType">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Home"/>
 *             &lt;enumeration value="Business"/>
 *             &lt;enumeration value="Mobile"/>
 *             &lt;enumeration value="HomeFax"/>
 *             &lt;enumeration value="BusinessFax"/>
 *             &lt;enumeration value="Pager"/>
 *             &lt;enumeration value="Telex"/>
 *             &lt;enumeration value="TTY"/>
 *             &lt;enumeration value="Other"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="otherPhoneType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseTelephone", propOrder = {
    "phoneNumber",
    "phoneData"
})
@XmlSeeAlso({
    Telephone.class
})
public class BaseTelephone {

    @XmlElement(name = "PhoneNumber")
    protected String phoneNumber;
    @XmlElement(name = "PhoneData")
    protected PhoneData phoneData;
    @XmlAttribute
    protected Boolean primary;
    @XmlAttribute
    protected String phoneUseType;
    @XmlAttribute
    protected String otherPhoneType;

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the phoneData property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneData }
     *     
     */
    public PhoneData getPhoneData() {
        return phoneData;
    }

    /**
     * Sets the value of the phoneData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneData }
     *     
     */
    public void setPhoneData(PhoneData value) {
        this.phoneData = value;
    }

    /**
     * Gets the value of the primary property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrimary() {
        return primary;
    }

    /**
     * Sets the value of the primary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrimary(Boolean value) {
        this.primary = value;
    }

    /**
     * Gets the value of the phoneUseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneUseType() {
        return phoneUseType;
    }

    /**
     * Sets the value of the phoneUseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneUseType(String value) {
        this.phoneUseType = value;
    }

    /**
     * Gets the value of the otherPhoneType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherPhoneType() {
        return otherPhoneType;
    }

    /**
     * Sets the value of the otherPhoneType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherPhoneType(String value) {
        this.otherPhoneType = value;
    }

}