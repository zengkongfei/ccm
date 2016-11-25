//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.17 at 04:00:55 下午 CST 
//


package com.ccm.oxi.ravl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ravl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ravl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelReference" type="{ravl.fidelio.2.0}HotelReference"/>
 *         &lt;element name="rateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="days" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DaysOfWeek" type="{ravl.fidelio.2.0}DaysOfWeek" minOccurs="0"/>
 *         &lt;element name="yieldGenerated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RavlDetails" type="{ravl.fidelio.2.0}RavlDetails"/>
 *       &lt;/sequence>
 *       &lt;attribute name="rateCriteria" type="{ravl.fidelio.2.0}rateCriteria" default="RATECODE" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ravl", propOrder = {
    "hotelReference",
    "rateCode",
    "days",
    "daysOfWeek",
    "yieldGenerated",
    "ravlDetails"
})
@XmlRootElement(name="Ravl")
public class Ravl {

    @XmlElement(name = "HotelReference", required = true)
    protected HotelReference hotelReference;
    @XmlElement(required = true)
    protected String rateCode;
    protected Integer days;
    @XmlElement(name = "DaysOfWeek")
    protected DaysOfWeek daysOfWeek;
    protected String yieldGenerated;
    @XmlElement(name = "RavlDetails", required = true)
    protected RavlDetails ravlDetails;
    @XmlAttribute
    protected RateCriteria rateCriteria;

    /**
     * Gets the value of the hotelReference property.
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelReference() {
        return hotelReference;
    }

    /**
     * Sets the value of the hotelReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelReference(HotelReference value) {
        this.hotelReference = value;
    }

    /**
     * Gets the value of the rateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCode() {
        return rateCode;
    }

    /**
     * Sets the value of the rateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCode(String value) {
        this.rateCode = value;
    }

    /**
     * Gets the value of the days property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDays() {
        return days;
    }

    /**
     * Sets the value of the days property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDays(Integer value) {
        this.days = value;
    }

    /**
     * Gets the value of the daysOfWeek property.
     * 
     * @return
     *     possible object is
     *     {@link DaysOfWeek }
     *     
     */
    public DaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    /**
     * Sets the value of the daysOfWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link DaysOfWeek }
     *     
     */
    public void setDaysOfWeek(DaysOfWeek value) {
        this.daysOfWeek = value;
    }

    /**
     * Gets the value of the yieldGenerated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYieldGenerated() {
        return yieldGenerated;
    }

    /**
     * Sets the value of the yieldGenerated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYieldGenerated(String value) {
        this.yieldGenerated = value;
    }

    /**
     * Gets the value of the ravlDetails property.
     * 
     * @return
     *     possible object is
     *     {@link RavlDetails }
     *     
     */
    public RavlDetails getRavlDetails() {
        return ravlDetails;
    }

    /**
     * Sets the value of the ravlDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link RavlDetails }
     *     
     */
    public void setRavlDetails(RavlDetails value) {
        this.ravlDetails = value;
    }

    /**
     * Gets the value of the rateCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link RateCriteria }
     *     
     */
    public RateCriteria getRateCriteria() {
        if (rateCriteria == null) {
            return RateCriteria.RATECODE;
        } else {
            return rateCriteria;
        }
    }

    /**
     * Sets the value of the rateCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateCriteria }
     *     
     */
    public void setRateCriteria(RateCriteria value) {
        this.rateCriteria = value;
    }

}