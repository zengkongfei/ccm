
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ExtendedHotelInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ExtendedHotelInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelInfoList" minOccurs="0"/>
 *         &lt;element name="PaymentMethods" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PaymentsAccepted" minOccurs="0"/>
 *         &lt;element name="AmenityInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmenityInfo" minOccurs="0"/>
 *         &lt;element name="Position" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GeoCode" minOccurs="0"/>
 *         &lt;element name="FacilityInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}FacilityInfoType" minOccurs="0"/>
 *         &lt;element name="AlternateProperties" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReferenceList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtendedHotelInfo", propOrder = {
    "hotelInformation",
    "paymentMethods",
    "amenityInfo",
    "position",
    "facilityInfo",
    "alternateProperties"
})
public class ExtendedHotelInfo {

    @XmlElement(name = "HotelInformation")
    protected HotelInfoList hotelInformation;
    @XmlElement(name = "PaymentMethods")
    protected PaymentsAccepted paymentMethods;
    @XmlElement(name = "AmenityInfo")
    protected AmenityInfo amenityInfo;
    @XmlElement(name = "Position")
    protected GeoCode position;
    @XmlElement(name = "FacilityInfo")
    protected FacilityInfoType facilityInfo;
    @XmlElement(name = "AlternateProperties")
    protected HotelReferenceList alternateProperties;

    /**
     * 获取hotelInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelInfoList }
     *     
     */
    public HotelInfoList getHotelInformation() {
        return hotelInformation;
    }

    /**
     * 设置hotelInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelInfoList }
     *     
     */
    public void setHotelInformation(HotelInfoList value) {
        this.hotelInformation = value;
    }

    /**
     * 获取paymentMethods属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaymentsAccepted }
     *     
     */
    public PaymentsAccepted getPaymentMethods() {
        return paymentMethods;
    }

    /**
     * 设置paymentMethods属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentsAccepted }
     *     
     */
    public void setPaymentMethods(PaymentsAccepted value) {
        this.paymentMethods = value;
    }

    /**
     * 获取amenityInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AmenityInfo }
     *     
     */
    public AmenityInfo getAmenityInfo() {
        return amenityInfo;
    }

    /**
     * 设置amenityInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AmenityInfo }
     *     
     */
    public void setAmenityInfo(AmenityInfo value) {
        this.amenityInfo = value;
    }

    /**
     * 获取position属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GeoCode }
     *     
     */
    public GeoCode getPosition() {
        return position;
    }

    /**
     * 设置position属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GeoCode }
     *     
     */
    public void setPosition(GeoCode value) {
        this.position = value;
    }

    /**
     * 获取facilityInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FacilityInfoType }
     *     
     */
    public FacilityInfoType getFacilityInfo() {
        return facilityInfo;
    }

    /**
     * 设置facilityInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FacilityInfoType }
     *     
     */
    public void setFacilityInfo(FacilityInfoType value) {
        this.facilityInfo = value;
    }

    /**
     * 获取alternateProperties属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReferenceList }
     *     
     */
    public HotelReferenceList getAlternateProperties() {
        return alternateProperties;
    }

    /**
     * 设置alternateProperties属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReferenceList }
     *     
     */
    public void setAlternateProperties(HotelReferenceList value) {
        this.alternateProperties = value;
    }

}
