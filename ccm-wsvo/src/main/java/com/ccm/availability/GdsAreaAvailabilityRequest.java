
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
 *         &lt;element name="HotelReferences" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReferenceList"/>
 *         &lt;element name="StayDateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan"/>
 *         &lt;element name="NumberOfRooms" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NumberOfGuests" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MinMaxRate" type="{http://webservices.micros.com/og/4.3/HotelCommon/}MinMaxRate" minOccurs="0"/>
 *         &lt;element name="MatchingQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RateCalculationMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RateCodes" type="{http://webservices.micros.com/og/4.3/Availability/}RateCodeInformationList" minOccurs="0"/>
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
    "hotelReferences",
    "stayDateRange",
    "numberOfRooms",
    "numberOfGuests",
    "minMaxRate",
    "matchingQualifier",
    "rateCalculationMethod",
    "rateCodes"
})
@XmlRootElement(name = "GdsAreaAvailabilityRequest", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
public class GdsAreaAvailabilityRequest {

    @XmlElement(name = "HotelReferences", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected HotelReferenceList hotelReferences;
    @XmlElement(name = "StayDateRange", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected TimeSpan stayDateRange;
    @XmlElement(name = "NumberOfRooms", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected Integer numberOfRooms;
    @XmlElement(name = "NumberOfGuests", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected int numberOfGuests;
    @XmlElement(name = "MinMaxRate", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected MinMaxRate minMaxRate;
    @XmlElement(name = "MatchingQualifier", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected String matchingQualifier;
    @XmlElement(name = "RateCalculationMethod", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected String rateCalculationMethod;
    @XmlElement(name = "RateCodes", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected RateCodeInformationList rateCodes;

    /**
     * 获取hotelReferences属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReferenceList }
     *     
     */
    public HotelReferenceList getHotelReferences() {
        return hotelReferences;
    }

    /**
     * 设置hotelReferences属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReferenceList }
     *     
     */
    public void setHotelReferences(HotelReferenceList value) {
        this.hotelReferences = value;
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
     * 获取numberOfGuests属性的值。
     * 
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * 设置numberOfGuests属性的值。
     * 
     */
    public void setNumberOfGuests(int value) {
        this.numberOfGuests = value;
    }

    /**
     * 获取minMaxRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MinMaxRate }
     *     
     */
    public MinMaxRate getMinMaxRate() {
        return minMaxRate;
    }

    /**
     * 设置minMaxRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MinMaxRate }
     *     
     */
    public void setMinMaxRate(MinMaxRate value) {
        this.minMaxRate = value;
    }

    /**
     * 获取matchingQualifier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchingQualifier() {
        return matchingQualifier;
    }

    /**
     * 设置matchingQualifier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchingQualifier(String value) {
        this.matchingQualifier = value;
    }

    /**
     * 获取rateCalculationMethod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCalculationMethod() {
        return rateCalculationMethod;
    }

    /**
     * 设置rateCalculationMethod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCalculationMethod(String value) {
        this.rateCalculationMethod = value;
    }

    /**
     * 获取rateCodes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RateCodeInformationList }
     *     
     */
    public RateCodeInformationList getRateCodes() {
        return rateCodes;
    }

    /**
     * 设置rateCodes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RateCodeInformationList }
     *     
     */
    public void setRateCodes(RateCodeInformationList value) {
        this.rateCodes = value;
    }

}
