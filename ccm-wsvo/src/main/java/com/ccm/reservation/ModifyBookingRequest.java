
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="HotelReservation" type="{http://webservices.micros.com/og/4.3/Reservation/}HotelReservation"/>
 *         &lt;element name="KeyTrack" type="{http://webservices.micros.com/og/4.3/Common/}KeyTrack" minOccurs="0"/>
 *         &lt;element name="ExternalSystemNumber" type="{http://webservices.micros.com/og/4.3/Reservation/}ExternalReference" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="overrideInventoryCheck" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="cancelUpsell" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hotelReservation",
    "keyTrack",
    "externalSystemNumber"
})
@XmlRootElement(name = "ModifyBookingRequest", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
public class ModifyBookingRequest {

    @XmlElement(name = "HotelReservation", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected HotelReservation hotelReservation;
    @XmlElement(name = "KeyTrack", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected KeyTrack keyTrack;
    @XmlElement(name = "ExternalSystemNumber", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected ExternalReference externalSystemNumber;
    @XmlAttribute(name = "overrideInventoryCheck")
    protected Boolean overrideInventoryCheck;
    @XmlAttribute(name = "cancelUpsell")
    protected Boolean cancelUpsell;

    /**
     * 获取hotelReservation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReservation }
     *     
     */
    public HotelReservation getHotelReservation() {
        return hotelReservation;
    }

    /**
     * 设置hotelReservation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReservation }
     *     
     */
    public void setHotelReservation(HotelReservation value) {
        this.hotelReservation = value;
    }

    /**
     * 获取keyTrack属性的值。
     * 
     * @return
     *     possible object is
     *     {@link KeyTrack }
     *     
     */
    public KeyTrack getKeyTrack() {
        return keyTrack;
    }

    /**
     * 设置keyTrack属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link KeyTrack }
     *     
     */
    public void setKeyTrack(KeyTrack value) {
        this.keyTrack = value;
    }

    /**
     * 获取externalSystemNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ExternalReference }
     *     
     */
    public ExternalReference getExternalSystemNumber() {
        return externalSystemNumber;
    }

    /**
     * 设置externalSystemNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalReference }
     *     
     */
    public void setExternalSystemNumber(ExternalReference value) {
        this.externalSystemNumber = value;
    }

    /**
     * 获取overrideInventoryCheck属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOverrideInventoryCheck() {
        return overrideInventoryCheck;
    }

    /**
     * 设置overrideInventoryCheck属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOverrideInventoryCheck(Boolean value) {
        this.overrideInventoryCheck = value;
    }

    /**
     * 获取cancelUpsell属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCancelUpsell() {
        return cancelUpsell;
    }

    /**
     * 设置cancelUpsell属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCancelUpsell(Boolean value) {
        this.cancelUpsell = value;
    }

}
