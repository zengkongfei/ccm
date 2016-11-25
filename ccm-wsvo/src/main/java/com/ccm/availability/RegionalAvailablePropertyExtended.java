
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RegionalAvailablePropertyExtended complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RegionalAvailablePropertyExtended">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference"/>
 *         &lt;element name="PropertyStatus" type="{http://webservices.micros.com/og/4.3/Availability/}PropertyStatusCode" minOccurs="0"/>
 *         &lt;element name="isNegotiatedRateAvailable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="NumberToSell" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="HotelContact" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelContact" minOccurs="0"/>
 *         &lt;element name="HotelExtendedInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ExtendedHotelInfo" minOccurs="0"/>
 *         &lt;element name="MinimumRate" type="{http://webservices.micros.com/og/4.3/Availability/}MinMaxRateExtended" minOccurs="0"/>
 *         &lt;element name="MaximumRate" type="{http://webservices.micros.com/og/4.3/Availability/}MinMaxRateExtended" minOccurs="0"/>
 *         &lt;element name="RateRoomTypes" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RatePlanRoomTypeList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegionalAvailablePropertyExtended", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "hotelReference",
    "propertyStatus",
    "isNegotiatedRateAvailable",
    "numberToSell",
    "hotelContact",
    "hotelExtendedInformation",
    "minimumRate",
    "maximumRate",
    "rateRoomTypes"
})
public class RegionalAvailablePropertyExtended {

    @XmlElement(name = "HotelReference", required = true)
    protected HotelReference hotelReference;
    @XmlElement(name = "PropertyStatus")
    protected PropertyStatusCode propertyStatus;
    protected Boolean isNegotiatedRateAvailable;
    @XmlElement(name = "NumberToSell")
    protected Integer numberToSell;
    @XmlElement(name = "HotelContact")
    protected HotelContact hotelContact;
    @XmlElement(name = "HotelExtendedInformation")
    protected ExtendedHotelInfo hotelExtendedInformation;
    @XmlElement(name = "MinimumRate")
    protected MinMaxRateExtended minimumRate;
    @XmlElement(name = "MaximumRate")
    protected MinMaxRateExtended maximumRate;
    @XmlElement(name = "RateRoomTypes", required = true)
    protected RatePlanRoomTypeList rateRoomTypes;

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
     * 获取propertyStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PropertyStatusCode }
     *     
     */
    public PropertyStatusCode getPropertyStatus() {
        return propertyStatus;
    }

    /**
     * 设置propertyStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyStatusCode }
     *     
     */
    public void setPropertyStatus(PropertyStatusCode value) {
        this.propertyStatus = value;
    }

    /**
     * 获取isNegotiatedRateAvailable属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsNegotiatedRateAvailable() {
        return isNegotiatedRateAvailable;
    }

    /**
     * 设置isNegotiatedRateAvailable属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsNegotiatedRateAvailable(Boolean value) {
        this.isNegotiatedRateAvailable = value;
    }

    /**
     * 获取numberToSell属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberToSell() {
        return numberToSell;
    }

    /**
     * 设置numberToSell属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberToSell(Integer value) {
        this.numberToSell = value;
    }

    /**
     * 获取hotelContact属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelContact }
     *     
     */
    public HotelContact getHotelContact() {
        return hotelContact;
    }

    /**
     * 设置hotelContact属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelContact }
     *     
     */
    public void setHotelContact(HotelContact value) {
        this.hotelContact = value;
    }

    /**
     * 获取hotelExtendedInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ExtendedHotelInfo }
     *     
     */
    public ExtendedHotelInfo getHotelExtendedInformation() {
        return hotelExtendedInformation;
    }

    /**
     * 设置hotelExtendedInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendedHotelInfo }
     *     
     */
    public void setHotelExtendedInformation(ExtendedHotelInfo value) {
        this.hotelExtendedInformation = value;
    }

    /**
     * 获取minimumRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MinMaxRateExtended }
     *     
     */
    public MinMaxRateExtended getMinimumRate() {
        return minimumRate;
    }

    /**
     * 设置minimumRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MinMaxRateExtended }
     *     
     */
    public void setMinimumRate(MinMaxRateExtended value) {
        this.minimumRate = value;
    }

    /**
     * 获取maximumRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MinMaxRateExtended }
     *     
     */
    public MinMaxRateExtended getMaximumRate() {
        return maximumRate;
    }

    /**
     * 设置maximumRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MinMaxRateExtended }
     *     
     */
    public void setMaximumRate(MinMaxRateExtended value) {
        this.maximumRate = value;
    }

    /**
     * 获取rateRoomTypes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RatePlanRoomTypeList }
     *     
     */
    public RatePlanRoomTypeList getRateRoomTypes() {
        return rateRoomTypes;
    }

    /**
     * 设置rateRoomTypes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RatePlanRoomTypeList }
     *     
     */
    public void setRateRoomTypes(RatePlanRoomTypeList value) {
        this.rateRoomTypes = value;
    }

}
