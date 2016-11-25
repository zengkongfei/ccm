
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
 *         &lt;choice>
 *           &lt;element name="RegionalSearchCode" type="{http://webservices.micros.com/og/4.3/Availability/}RegionalSearchCode"/>
 *           &lt;element name="HotelReferences" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReferenceList"/>
 *           &lt;element name="GeographicSearch" type="{http://webservices.micros.com/og/4.3/Availability/}RegionalSearch"/>
 *         &lt;/choice>
 *         &lt;element name="StayDateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan"/>
 *         &lt;element name="NumberOfRooms" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NumberOfGuests" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MinMaxRate" type="{http://webservices.micros.com/og/4.3/HotelCommon/}MinMaxRate" minOccurs="0"/>
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
    "regionalSearchCode",
    "hotelReferences",
    "geographicSearch",
    "stayDateRange",
    "numberOfRooms",
    "numberOfGuests",
    "minMaxRate"
})
@XmlRootElement(name = "RegionalAvailabilityExtRequest", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
public class RegionalAvailabilityExtRequest {

    @XmlElement(name = "RegionalSearchCode", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected RegionalSearchCode regionalSearchCode;
    @XmlElement(name = "HotelReferences", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected HotelReferenceList hotelReferences;
    @XmlElement(name = "GeographicSearch", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected RegionalSearch geographicSearch;
    @XmlElement(name = "StayDateRange", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected TimeSpan stayDateRange;
    @XmlElement(name = "NumberOfRooms", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected Integer numberOfRooms;
    @XmlElement(name = "NumberOfGuests", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected int numberOfGuests;
    @XmlElement(name = "MinMaxRate", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected MinMaxRate minMaxRate;

    /**
     * 获取regionalSearchCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RegionalSearchCode }
     *     
     */
    public RegionalSearchCode getRegionalSearchCode() {
        return regionalSearchCode;
    }

    /**
     * 设置regionalSearchCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RegionalSearchCode }
     *     
     */
    public void setRegionalSearchCode(RegionalSearchCode value) {
        this.regionalSearchCode = value;
    }

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
     * 获取geographicSearch属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RegionalSearch }
     *     
     */
    public RegionalSearch getGeographicSearch() {
        return geographicSearch;
    }

    /**
     * 设置geographicSearch属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RegionalSearch }
     *     
     */
    public void setGeographicSearch(RegionalSearch value) {
        this.geographicSearch = value;
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

}
