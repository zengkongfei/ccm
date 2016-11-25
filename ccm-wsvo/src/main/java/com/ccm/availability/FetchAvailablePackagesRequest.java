
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference"/>
 *         &lt;element name="ConfirmationNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="ResvNameId" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="LegNumber" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="StayDateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan"/>
 *         &lt;element name="NumberOfRooms" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NumberOfAdults" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NumberOfChildren" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ChildAges" type="{http://webservices.micros.com/og/4.3/Availability/}ChildAgeList" minOccurs="0"/>
 *         &lt;element name="RateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuaranteeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hotelReference",
    "confirmationNumber",
    "resvNameId",
    "legNumber",
    "stayDateRange",
    "numberOfRooms",
    "numberOfAdults",
    "numberOfChildren",
    "childAges",
    "rateCode",
    "guaranteeCode"
})
@XmlRootElement(name = "FetchAvailablePackagesRequest", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
public class FetchAvailablePackagesRequest {

    @XmlElement(name = "HotelReference", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected HotelReference hotelReference;
    @XmlElement(name = "ConfirmationNumber", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected UniqueID confirmationNumber;
    @XmlElement(name = "ResvNameId", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected UniqueID resvNameId;
    @XmlElement(name = "LegNumber", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected UniqueID legNumber;
    @XmlElement(name = "StayDateRange", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected TimeSpan stayDateRange;
    @XmlElement(name = "NumberOfRooms", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected Integer numberOfRooms;
    @XmlElement(name = "NumberOfAdults", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected Integer numberOfAdults;
    @XmlElement(name = "NumberOfChildren", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected Integer numberOfChildren;
    @XmlElement(name = "ChildAges", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected ChildAgeList childAges;
    @XmlElement(name = "RateCode", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected String rateCode;
    @XmlElement(name = "GuaranteeCode", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected String guaranteeCode;

    /**
     * 获取hotelReference属性的值。
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
     * 设置hotelReference属性的值。
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
     * 获取confirmationNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * 设置confirmationNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setConfirmationNumber(UniqueID value) {
        this.confirmationNumber = value;
    }

    /**
     * 获取resvNameId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getResvNameId() {
        return resvNameId;
    }

    /**
     * 设置resvNameId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setResvNameId(UniqueID value) {
        this.resvNameId = value;
    }

    /**
     * 获取legNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getLegNumber() {
        return legNumber;
    }

    /**
     * 设置legNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setLegNumber(UniqueID value) {
        this.legNumber = value;
    }

    /**
     * 获取stayDateRange属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getStayDateRange() {
        return stayDateRange;
    }

    /**
     * 设置stayDateRange属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setStayDateRange(TimeSpan value) {
        this.stayDateRange = value;
    }

    /**
     * 获取numberOfRooms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * 设置numberOfRooms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfRooms(Integer value) {
        this.numberOfRooms = value;
    }

    /**
     * 获取numberOfAdults属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    /**
     * 设置numberOfAdults属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfAdults(Integer value) {
        this.numberOfAdults = value;
    }

    /**
     * 获取numberOfChildren属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    /**
     * 设置numberOfChildren属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfChildren(Integer value) {
        this.numberOfChildren = value;
    }

    /**
     * 获取childAges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ChildAgeList }
     *     
     */
    public ChildAgeList getChildAges() {
        return childAges;
    }

    /**
     * 设置childAges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ChildAgeList }
     *     
     */
    public void setChildAges(ChildAgeList value) {
        this.childAges = value;
    }

    /**
     * 获取rateCode属性的值。
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
     * 设置rateCode属性的值。
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
     * 获取guaranteeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuaranteeCode() {
        return guaranteeCode;
    }

    /**
     * 设置guaranteeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuaranteeCode(String value) {
        this.guaranteeCode = value;
    }

}
