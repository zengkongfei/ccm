
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
 *         &lt;element name="StayDateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan"/>
 *         &lt;element name="GuestCount" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GuestCountList" minOccurs="0"/>
 *         &lt;element name="RatePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RoomTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BlockCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorporateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PromotionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "stayDateRange",
    "guestCount",
    "ratePlanCode",
    "roomTypeCode",
    "blockCode",
    "corporateCode",
    "promotionCode"
})
@XmlRootElement(name = "FetchCalendarRequest", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
public class FetchCalendarRequest {

    @XmlElement(name = "HotelReference", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected HotelReference hotelReference;
    @XmlElement(name = "StayDateRange", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected TimeSpan stayDateRange;
    @XmlElement(name = "GuestCount", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected GuestCountList guestCount;
    @XmlElement(name = "RatePlanCode", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected String ratePlanCode;
    @XmlElement(name = "RoomTypeCode", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected String roomTypeCode;
    @XmlElement(name = "BlockCode", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected String blockCode;
    @XmlElement(name = "CorporateCode", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected String corporateCode;
    @XmlElement(name = "PromotionCode", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected String promotionCode;

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
     * 获取guestCount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GuestCountList }
     *     
     */
    public GuestCountList getGuestCount() {
        return guestCount;
    }

    /**
     * 设置guestCount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GuestCountList }
     *     
     */
    public void setGuestCount(GuestCountList value) {
        this.guestCount = value;
    }

    /**
     * 获取ratePlanCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanCode() {
        return ratePlanCode;
    }

    /**
     * 设置ratePlanCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanCode(String value) {
        this.ratePlanCode = value;
    }

    /**
     * 获取roomTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    /**
     * 设置roomTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeCode(String value) {
        this.roomTypeCode = value;
    }

    /**
     * 获取blockCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlockCode() {
        return blockCode;
    }

    /**
     * 设置blockCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlockCode(String value) {
        this.blockCode = value;
    }

    /**
     * 获取corporateCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorporateCode() {
        return corporateCode;
    }

    /**
     * 设置corporateCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorporateCode(String value) {
        this.corporateCode = value;
    }

    /**
     * 获取promotionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionCode() {
        return promotionCode;
    }

    /**
     * 设置promotionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionCode(String value) {
        this.promotionCode = value;
    }

}
